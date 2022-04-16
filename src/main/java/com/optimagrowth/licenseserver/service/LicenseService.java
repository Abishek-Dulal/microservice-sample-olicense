package com.optimagrowth.licenseserver.service;

import com.optimagrowth.licenseserver.contextholder.UserContextHolder;
import com.optimagrowth.licenseserver.model.License;
import com.optimagrowth.licenseserver.repository.LicenseRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Organization;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author abishek on 2022-03-05
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LicenseService {

    private final MessageSource messageSource;

    private final LicenseRepository licenseRepository;

    private  final ServiceConfig serviceConfig;
    private final OrganisationDiscoveryClient organisationDiscoveryClient;

    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = licenseRepository.findByOrganisationIdAndLicenseId(organizationId,licenseId);
        if (null ==license){
            throw  new IllegalArgumentException(String.format(messageSource.getMessage("license.search.error.message",null,null)));
        }
        if (license==null){
            throw new IllegalArgumentException(String.format(messageSource.getMessage("license.search.error.message",new Object[]{null,null,licenseId,organizationId},Locale.US)));
        }
        Organization organization = retrieveOrganizationInfo(organizationId, clientType);


        return license.withComment(serviceConfig.getProperty());

    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        return null;
    }

    public License createLicense(License license, String organisationId, Locale locale) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(serviceConfig.getProperty());
    }

    public License updateLicense(License license, String organizationId, Locale locale) {
        licenseRepository.save(license);
        return license.withComment(serviceConfig.getProperty());
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messageSource.getMessage("license.delete.message", null, null),licenseId);
        return responseMessage;
    }


    @CircuitBreaker(name="licenseService",
            fallbackMethod = "buildFallbackLicesenList"
    )
    @Bulkhead(name="bulkheadLicenseService",
         type = Bulkhead.Type.THREADPOOL,
         fallbackMethod = "buildFallbackLicesenList"
    )
    @Retry(name = "retryLicenseService", fallbackMethod=
                    "buildFallbackLicenseList")
    @RateLimiter(name = "licenseService",
            fallbackMethod = "buildFallbackLicenseList")
    public List<License> getLicenseByOrganisation(String organisationId){
        return licenseRepository.findByOrganisationId(organisationId);
    }


    public List<License> buildFallbackLicesenList(String organistionId){
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId("0000000-00-00000");
        license.setOrganisationId(organistionId);
        license.setProductName(
                "Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }


    @CircuitBreaker(name = "organizationService")
    private Organization getOrganization(String organizationId) {
        log.debug("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return organisationDiscoveryClient.getOrganisation(organizationId);
    }


}

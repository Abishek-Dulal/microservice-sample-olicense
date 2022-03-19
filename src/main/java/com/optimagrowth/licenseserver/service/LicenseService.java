package com.optimagrowth.licenseserver.service;

import com.optimagrowth.licenseserver.controller.LicenseController;
import com.optimagrowth.licenseserver.model.License;
import com.optimagrowth.licenseserver.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * @author abishek on 2022-03-05
 */
@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messageSource;

    private final LicenseRepository licenseRepository;

    private  final ServiceConfig serviceConfig;

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository.findByOrganisationIdAndLicenseId(organizationId,licenseId);
        if (license==null){
            throw new IllegalArgumentException(String.format(messageSource.getMessage("license.search.error.message",new Object[]{null,null,licenseId,organizationId},Locale.US)));
        }
        return license.withComment(serviceConfig.getProperty());
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

}

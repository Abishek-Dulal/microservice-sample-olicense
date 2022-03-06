package com.optimagrowth.licenseserver.service;

import com.optimagrowth.licenseserver.controller.LicenseController;
import com.optimagrowth.licenseserver.model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * @author abishek on 2022-03-05
 */
@Service
@RequiredArgsConstructor
public class LicenseService {


    private final MessageSource messageSource;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganisationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");

        license.add(linkTo(methodOn(LicenseController.class)
                        .getLicense(organizationId, license.getLicenseId()))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, license, null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organizationId, license,null))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(organizationId, license.getLicenseId(),null))
                        .withRel("deleteLicense"));



        return license;
    }

    public String createLicense(License license, String organisationId, Locale locale) {
        String responseMessage = null;
        if (Objects.nonNull(license)) {
            license.setOrganisationId(organisationId);
            responseMessage = String.format(messageSource.getMessage("license.create.message", null, locale));
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganisationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.update.message",
                    new String[]{},
                    locale));
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        String responseMessage = null;
        responseMessage = String.format(messageSource.getMessage("license.delete.message",
                new String[]{licenseId, organizationId},
                locale));
        return responseMessage;
    }





}

package com.optimagrowth.licenseserver.controller;

import com.optimagrowth.licenseserver.model.License;
import com.optimagrowth.licenseserver.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * @author abishek on 2022-03-05
 */
@RestController
@RequestMapping(value = "v1/organisation/{organaisationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organisationId") String organisationId,
                                              @PathVariable("licenseId") String licenseId
                                              ) {
        License license = licenseService.getLicense(licenseId, organisationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId, locale));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("licenseId") String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }


}
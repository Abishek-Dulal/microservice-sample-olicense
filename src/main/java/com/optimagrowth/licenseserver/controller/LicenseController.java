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

    @GetMapping(value = "/{licenseId}/{clientType}")
    public ResponseEntity<License> getLicense(@PathVariable("organisationId") String organisationId,
                                              @PathVariable("licenseId") String licenseId,
                                              @PathVariable("clientType") String clientType
                                              ) {
        License license = licenseService.getLicense(licenseId, organisationId,clientType);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<?> updateLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId, locale));
    }

    @PostMapping
    public ResponseEntity<?> createLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<?> deleteLicense(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("licenseId") String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }


}

package com.optimagrowth.licenseserver.repository;

import com.optimagrowth.licenseserver.model.License;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author abishek on 2022-03-19
 */
public interface LicenseRepository  extends CrudRepository<License,String> {

     List<License> findByOrganisationId(String organisationId);

     License findByOrganisationIdAndLicenseId(String organisationId,String licenseId);

}

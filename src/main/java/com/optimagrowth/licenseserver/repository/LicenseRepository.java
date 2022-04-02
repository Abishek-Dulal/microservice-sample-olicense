package com.optimagrowth.licenseserver.repository;

import com.optimagrowth.licenseserver.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abishek on 2022-03-19
 */
@Repository
public interface LicenseRepository  extends CrudRepository<License,String> {

     List<License> findByOrganisationId(String organisationId);

     License findByOrganisationIdAndLicenseId(String organisationId,String licenseId);

}

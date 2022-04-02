package com.optimagrowth.licenseserver.service;

import org.apache.maven.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author abishek on 2022-03-27
 */
@FeignClient("Organisation-service")
public interface OrganisationFeignClint {

    @RequestMapping(
            method = RequestMethod.GET,
            value="/v1/orgaization/{organisationId}",
            consumes = "application/json")
    Organization getOrganisation(@PathVariable("organisationId") String organisationId);


    @RequestMapping(
            method = RequestMethod.GET,
            value= "v1/check/feign"
    )
    String check();


}

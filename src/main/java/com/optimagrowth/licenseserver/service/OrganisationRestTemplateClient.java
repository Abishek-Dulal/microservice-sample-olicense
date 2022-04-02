package com.optimagrowth.licenseserver.service;

import org.apache.maven.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author abishek on 2022-03-27
 */
@Component
public class OrganisationRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    public Organization getOrganisation(String organisationId){
        ResponseEntity<Organization> exchange = restTemplate.exchange("http://orgainsation-service/v1/organisation/{organisationId}",
                HttpMethod.GET, null, Organization.class, organisationId);
        return exchange.getBody();
    }


    public String check(){
        ResponseEntity<String> exchange = restTemplate.exchange("http://organisation-service/v1/check/rest",
                HttpMethod.GET,null,String.class );
        return exchange.getBody();
    }
}

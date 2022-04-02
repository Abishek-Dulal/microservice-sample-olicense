package com.optimagrowth.licenseserver.service;

import org.apache.maven.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author abishek on 2022-03-27
 */
@Component
public class OrganisationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;


    public Organization getOrganisation(String organisationId){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
        if (instances.size()==0 )
            return null;
        String serviceUri = String.format("%s/v1/organization/%s",instances.get(0).getUri().toString(),organisationId);

        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organisationId);
        return  exchange.getBody();
    }


}

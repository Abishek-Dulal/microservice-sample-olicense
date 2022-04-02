package com.optimagrowth.licenseserver.controller;

import com.optimagrowth.licenseserver.service.OrganisationDiscoveryClient;
import com.optimagrowth.licenseserver.service.OrganisationFeignClint;
import com.optimagrowth.licenseserver.service.OrganisationRestTemplateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abishek on 2022-04-02
 */
@RestController
@RequestMapping("/v1/check")
@RequiredArgsConstructor
public class ClientCheck {


    private final OrganisationFeignClint organisationFeignClint;
    private final OrganisationRestTemplateClient organisationRestTemplateClient;
    private final OrganisationDiscoveryClient organisationDiscoveryClient;



     @GetMapping("/{clientType}")
     public ResponseEntity<String> checkclient(@PathVariable("clientType") String clientType){
         String response="";
           switch (clientType){
               case "feign":{
                response=   organisationFeignClint.check();
                break;
               }
               case "restTemplate":{
               response=    organisationRestTemplateClient.check();
               break;
               }
               case "discoveryClient":{
                   response =  organisationDiscoveryClient.check();
                   break;
               }
           }
           return  ResponseEntity.ok(response);

     }

}

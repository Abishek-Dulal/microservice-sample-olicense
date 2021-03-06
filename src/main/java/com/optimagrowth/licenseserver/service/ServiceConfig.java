package com.optimagrowth.licenseserver.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author abishek on 2022-03-19
 */
@Configuration
@ConfigurationProperties(prefix = "example")
@Getter
@Setter
public class ServiceConfig {

    private String property;


}

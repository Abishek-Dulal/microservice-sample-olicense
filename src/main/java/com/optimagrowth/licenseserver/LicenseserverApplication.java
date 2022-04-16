package com.optimagrowth.licenseserver;

import com.optimagrowth.licenseserver.contextholder.UserContextInterceptor;
import org.hibernate.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
public class LicenseserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseserverApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (Objects.isNull(interceptors)){
             restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }else {
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }

}

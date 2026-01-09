package com.zhkh.apigateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "services")
@Data
public class ServicesProperties {

    private Service apartment;
    private Service building;
    private Service counterparty;
    private Service employee;
    private Service office;
    private Service registration;
    private Service request;
    private Service resident;
    private Service review;
    private Service serviceCatalog;
    private Service user;

    @Data
    public static class Service {
        private String url;
    }
}

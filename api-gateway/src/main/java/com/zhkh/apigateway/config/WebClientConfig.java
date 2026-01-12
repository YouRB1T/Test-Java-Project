package com.zhkh.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ServicesProperties services;

    private WebClient buildClient(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public WebClient apartmentWebClient() {
        return buildClient(services.getApartment().getUrl());
    }

    @Bean
    public WebClient buildingWebClient() {
        return buildClient(services.getBuilding().getUrl());
    }

    @Bean
    public WebClient counterpartyWebClient() {
        return buildClient(services.getCounterparty().getUrl());
    }

    @Bean
    public WebClient employeeWebClient() {
        return buildClient(services.getEmployee().getUrl());
    }

    @Bean
    public WebClient officeWebClient() {
        return buildClient(services.getOffice().getUrl());
    }

    @Bean
    public WebClient registrationWebClient() {
        return buildClient(services.getRegistration().getUrl());
    }

    @Bean
    public WebClient requestWebClient() {
        return buildClient(services.getRequest().getUrl());
    }

    @Bean
    public WebClient residentWebClient() {
        return buildClient(services.getResident().getUrl());
    }

    @Bean
    public WebClient reviewWebClient() {
        return buildClient(services.getReview().getUrl());
    }

    @Bean
    public WebClient serviceCatalogWebClient() {
        return buildClient(services.getServiceCatalog().getUrl());
    }

    @Bean
    public WebClient userWebClient() {
        return buildClient(services.getUser().getUrl());
    }
}

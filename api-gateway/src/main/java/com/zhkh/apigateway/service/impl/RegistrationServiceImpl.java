package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.registration.*;
import com.zhkh.apigateway.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    @Qualifier("registrationWebClient")
    private final WebClient registrationWebClient;

    @Override
    public Mono<RegistrationUserResponse> registrationUser(
            RegistrationUserRequest request
    ) {
        return registrationWebClient
                .post()
                .uri("/api/registration/user")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RegistrationUserResponse.class);
    }

    @Override
    public Mono<Void> registrationCounterparty(
            RegistrationCounterpartyRequest request
    ) {
        return registrationWebClient
                .post()
                .uri("/api/registration/counterparty")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> registrationEmployee(
            RegistrationEmployeeRequest request
    ) {
        return registrationWebClient
                .post()
                .uri("/api/registration/employee")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> registrationResident(
            RegistrationResidentRequest request
    ) {
        return registrationWebClient
                .post()
                .uri("/api/registration/resident")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }
}


package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.registration.*;
import reactor.core.publisher.Mono;

public interface RegistrationService {

    Mono<RegistrationUserResponse> registrationUser(RegistrationUserRequest request);

    Mono<Void> registrationCounterparty(RegistrationCounterpartyRequest request);

    Mono<Void> registrationEmployee(RegistrationEmployeeRequest request);

    Mono<Void> registrationResident(RegistrationResidentRequest request);
}


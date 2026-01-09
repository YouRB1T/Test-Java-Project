package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.counterparty.CounterpartyRequest;
import com.zhkh.apigateway.api.counterparty.CounterpartyResponse;
import com.zhkh.apigateway.api.service.ServiceResponse;

import reactor.core.publisher.Mono;
import java.util.List;
import java.util.UUID;

public interface CounterpartyService {

    Mono<List<CounterpartyResponse>> getAll();

    Mono<CounterpartyResponse> getById(UUID id);

    Mono<CounterpartyResponse> create(CounterpartyRequest request);

    Mono<CounterpartyResponse> update(UUID id, CounterpartyRequest request);

    Mono<Void> delete(UUID id);

    Mono<List<ServiceResponse>> getServices(UUID counterpartyId);

    Mono<List<ServiceResponse>> addService(UUID counterpartyId, UUID serviceId);

    Mono<List<ServiceResponse>> deleteService(UUID counterpartyId, UUID serviceId);
}


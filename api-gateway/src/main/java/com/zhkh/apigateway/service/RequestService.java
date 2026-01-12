package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.request.RequestRequestModel;
import com.zhkh.apigateway.api.request.ResponseRequestModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RequestService {

    Mono<ResponseRequestModel> create(RequestRequestModel dto, UUID residentId);

    Mono<ResponseRequestModel> update(UUID requestId, RequestRequestModel dto);

    Mono<ResponseRequestModel> getById(UUID id);

    Flux<ResponseRequestModel> getAll();

    Mono<Void> delete(UUID id);

    Flux<ResponseRequestModel> getByResident(UUID residentId);

    Mono<Set<UUID>> getServices(UUID requestId);

    Mono<Set<UUID>> addService(UUID requestId, UUID serviceId);

    Mono<Set<UUID>> removeService(UUID requestId, UUID serviceId);

}


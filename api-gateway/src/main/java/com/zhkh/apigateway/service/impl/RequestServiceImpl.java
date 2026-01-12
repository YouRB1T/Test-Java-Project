package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.request.RequestRequestModel;
import com.zhkh.apigateway.api.request.ResponseRequestModel;
import com.zhkh.apigateway.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Qualifier("requestWebClient")
    private final WebClient requestWebClient;

    @Override
    public Mono<ResponseRequestModel> create(
            RequestRequestModel dto,
            UUID residentId
    ) {
        return requestWebClient
                .post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/requests")
                                .queryParam("residentId", residentId)
                                .build()
                )
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ResponseRequestModel.class);
    }

    @Override
    public Mono<ResponseRequestModel> update(
            UUID requestId,
            RequestRequestModel dto
    ) {
        return requestWebClient
                .put()
                .uri("/api/requests/{id}", requestId)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ResponseRequestModel.class);
    }

    @Override
    public Mono<ResponseRequestModel> getById(UUID id) {
        return requestWebClient
                .get()
                .uri("/api/requests/{id}", id)
                .retrieve()
                .bodyToMono(ResponseRequestModel.class);
    }

    @Override
    public Flux<ResponseRequestModel> getAll() {
        return requestWebClient
                .get()
                .uri("/api/requests")
                .retrieve()
                .bodyToFlux(ResponseRequestModel.class);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return requestWebClient
                .delete()
                .uri("/api/requests/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<ResponseRequestModel> getByResident(UUID residentId) {
        return requestWebClient
                .get()
                .uri("/api/requests/resident/{residentId}", residentId)
                .retrieve()
                .bodyToFlux(ResponseRequestModel.class);
    }

    @Override
    public Mono<Set<UUID>> getServices(UUID requestId) {
        return requestWebClient
                .get()
                .uri("/api/requests/{id}/services", requestId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<UUID>>() {});
    }

    @Override
    public Mono<Set<UUID>> addService(UUID requestId, UUID serviceId) {
        return requestWebClient
                .post()
                .uri("/api/requests/{id}/services/{serviceId}", requestId, serviceId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<UUID>>() {});
    }

    @Override
    public Mono<Set<UUID>> removeService(UUID requestId, UUID serviceId) {
        return requestWebClient
                .delete()
                .uri("/api/requests/{id}/services/{serviceId}", requestId, serviceId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<UUID>>() {});
    }
}


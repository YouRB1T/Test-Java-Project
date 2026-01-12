package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.counterparty.CounterpartyRequest;
import com.zhkh.apigateway.api.counterparty.CounterpartyResponse;
import com.zhkh.apigateway.api.counterparty.CounterpartyResponseWithServices;
import com.zhkh.apigateway.api.service.ServiceResponse;
import com.zhkh.apigateway.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CounterpartyServiceImpl implements CounterpartyService {

    @Qualifier("counterpartyWebClient")
    private final WebClient counterpartyWebClient;

    @Qualifier("serviceCatalogWebClient")
    private final WebClient serviceCatalogWebClient;

    @Override
    public Mono<List<CounterpartyResponse>> getAll() {
        return counterpartyWebClient
                .get()
                .uri("/api/counterparties")
                .retrieve()
                .bodyToFlux(CounterpartyResponse.class)
                .collectList();
    }

    @Override
    public Mono<CounterpartyResponse> getById(UUID id) {
        return counterpartyWebClient
                .get()
                .uri("/api/counterparties/{id}", id)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class);
    }

    @Override
    public Mono<CounterpartyResponse> create(CounterpartyRequest request) {
        return counterpartyWebClient
                .post()
                .uri("/api/counterparties")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class);
    }

    @Override
    public Mono<CounterpartyResponse> update(UUID id, CounterpartyRequest request) {
        return counterpartyWebClient
                .put()
                .uri("/api/counterparties/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return counterpartyWebClient
                .delete()
                .uri("/api/counterparties/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .then();
    }

    @Override
    public Mono<List<ServiceResponse>> getServices(UUID counterpartyId) {
        return getServiceIds(counterpartyId)
                .flatMap(this::loadServices);
    }

    @Override
    public Mono<List<ServiceResponse>> addService(UUID counterpartyId, UUID serviceId) {
        return counterpartyWebClient
                .post()
                .uri("/api/counterparties/{cid}/services/{sid}", counterpartyId, serviceId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .map(CounterpartyResponseWithServices::getServiceResponseList)
                .flatMap(this::loadServices);
    }

    @Override
    public Mono<List<ServiceResponse>> deleteService(UUID counterpartyId, UUID serviceId) {
        return counterpartyWebClient
                .delete()
                .uri("/api/counterparties/{cid}/services/{sid}", counterpartyId, serviceId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .map(CounterpartyResponseWithServices::getServiceResponseList)
                .flatMap(this::loadServices);
    }


    private Mono<List<UUID>> getServiceIds(UUID counterpartyId) {
        return counterpartyWebClient
                .get()
                .uri("/api/counterparties/{id}/services", counterpartyId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .map(CounterpartyResponseWithServices::getServiceResponseList);
    }

    private Mono<List<ServiceResponse>> loadServices(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return Mono.just(List.of());
        }

        return Flux.fromIterable(ids)
                .flatMap(id ->
                        serviceCatalogWebClient
                                .get()
                                .uri("/api/services/{id}", id)
                                .retrieve()
                                .bodyToMono(ServiceResponse.class)
                )
                .collectList();
    }
}


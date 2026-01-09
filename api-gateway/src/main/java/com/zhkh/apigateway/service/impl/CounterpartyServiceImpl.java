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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CounterpartyServiceImpl implements CounterpartyService {

    @Qualifier(value = "counterpartyWebClient")
    private final WebClient counterpartyWebClient;

    @Qualifier(value = "serviceCatalogWebClient")
    private final WebClient serviceCatalogWebClient;

    @Override
    public List<CounterpartyResponse> getAll() {
        return counterpartyWebClient
                .get()
                .uri("/api/counterparties")
                .retrieve()
                .bodyToFlux(CounterpartyResponse.class)
                .collectList()
                .block();
    }

    @Override
    public CounterpartyResponse getById(UUID id) {
        return counterpartyWebClient
                .get()
                .uri("/api/counterparties/{id}", id)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class)
                .block();
    }

    @Override
    public CounterpartyResponse create(CounterpartyRequest request) {
        return counterpartyWebClient
                .post()
                .uri("/api/counterparties")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class)
                .block();
    }

    @Override
    public CounterpartyResponse update(UUID id, CounterpartyRequest request) {
        return counterpartyWebClient
                .put()
                .uri("/api/counterparties/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CounterpartyResponse.class)
                .block();
    }

    @Override
    public void delete(UUID id) {
        counterpartyWebClient
                .delete()
                .uri("/api/counterparties/{id}", id)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }

    @Override
    public List<ServiceResponse> addService(UUID counterpartyId, UUID serviceId) {
        CounterpartyResponseWithServices counterpartyResponseWithServices
                = counterpartyWebClient
                .post()
                .uri("/api/counterparties/{counterpartyId}/services/{serviceId}", counterpartyId, serviceId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .block();

        assert counterpartyResponseWithServices != null;

        List<UUID> servicesIds = counterpartyResponseWithServices.getServiceResponseList();

        return servicesIds.stream()
                .map(id -> serviceCatalogWebClient
                        .get()
                        .uri("/api/services/{id}" , id)
                        .retrieve()
                        .bodyToMono(ServiceResponse.class)
                        .block())
                .toList();
    }

    @Override
    public List<ServiceResponse> deleteService(UUID counterpartyId, UUID serviceId) {
        CounterpartyResponseWithServices counterpartyResponseWithServices
                = counterpartyWebClient
                .delete()
                .uri("/api/counterparties/{counterpartyId}/services/{serviceId}", counterpartyId, serviceId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .block();

        assert counterpartyResponseWithServices != null;

        List<UUID> servicesIds = counterpartyResponseWithServices.getServiceResponseList();

        return servicesIds.stream()
                .map(id -> serviceCatalogWebClient
                        .get()
                        .uri("/api/services/{id}" , id)
                        .retrieve()
                        .bodyToMono(ServiceResponse.class)
                        .block())
                .toList();
    }

    @Override
    public List<ServiceResponse> getServices(UUID counterpartyId) {
        CounterpartyResponseWithServices counterpartyResponseWithServices
                = counterpartyWebClient
                .get()
                .uri("/api/counterparties/{counterpartyId}/services", counterpartyId)
                .retrieve()
                .bodyToMono(CounterpartyResponseWithServices.class)
                .block();

        assert counterpartyResponseWithServices != null;

        List<UUID> servicesIds = counterpartyResponseWithServices.getServiceResponseList();

        return servicesIds.stream()
                .map(id -> serviceCatalogWebClient
                        .get()
                        .uri("/api/services/{id}" , id)
                        .retrieve()
                        .bodyToMono(ServiceResponse.class)
                        .block())
                .toList();
    }
}

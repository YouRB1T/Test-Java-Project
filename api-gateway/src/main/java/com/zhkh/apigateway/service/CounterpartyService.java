package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.counterparty.CounterpartyRequest;
import com.zhkh.apigateway.api.counterparty.CounterpartyResponse;
import com.zhkh.apigateway.api.service.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface CounterpartyService {
    List<CounterpartyResponse> getAll();
    CounterpartyResponse getById(UUID id);
    CounterpartyResponse create(CounterpartyRequest request);
    CounterpartyResponse update(UUID id, CounterpartyRequest request);
    void delete(UUID id);
    List<ServiceResponse> addService(UUID counterpartyId, UUID serviceId);
    List<ServiceResponse> deleteService(UUID counterpartyId, UUID serviceId);
    List<ServiceResponse> getServices(UUID counterpartyId);
}

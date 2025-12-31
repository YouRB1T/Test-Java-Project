package com.zhkh.counterparty.service;

import com.zhkh.counterparty.api.CounterpartyRequest;
import com.zhkh.counterparty.api.CounterpartyResponse;
import com.zhkh.counterparty.api.CounterpartyServicesResponse;

import java.util.List;
import java.util.UUID;

public interface CounterpartyService {
    CounterpartyResponse create(CounterpartyRequest request);
    List<CounterpartyResponse> readAll();
    CounterpartyResponse readById(UUID id);
    CounterpartyResponse update(CounterpartyRequest request, UUID id);
    void deleteById(UUID id);
    CounterpartyServicesResponse addService(UUID serviceId, UUID counterpartyId);
    CounterpartyServicesResponse deleteService(UUID serviceId, UUID counterpartyId);
    CounterpartyServicesResponse getServices(UUID counterpartyId);
}

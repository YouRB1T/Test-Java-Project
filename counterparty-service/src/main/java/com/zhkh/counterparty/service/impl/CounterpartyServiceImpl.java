package com.zhkh.counterparty.service.impl;

import com.zhkh.counterparty.api.CounterpartyRequest;
import com.zhkh.counterparty.api.CounterpartyResponse;
import com.zhkh.counterparty.api.CounterpartyServicesResponse;
import com.zhkh.counterparty.mapper.CounterpartyMapper;
import com.zhkh.counterparty.model.Counterparty;
import com.zhkh.counterparty.repository.CounterpartyRepository;
import com.zhkh.counterparty.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounterpartyServiceImpl implements CounterpartyService {
    private final CounterpartyRepository repository;
    @Override
    public CounterpartyResponse create(CounterpartyRequest request) {
        Counterparty counterparty = CounterpartyMapper.INSTANCE.toEntity(request);
        repository.save(counterparty);
        return CounterpartyMapper.INSTANCE.toResponse(counterparty);
    }

    @Override
    public List<CounterpartyResponse> readAll() {
        List<Counterparty> counterparties = repository.findAll();

        return counterparties.stream()
                .map(CounterpartyMapper.INSTANCE::toResponse)
                .toList();
    }

    @Override
    public CounterpartyResponse readById(UUID id) {
        Counterparty counterparty = repository.findById(id).get();
        return CounterpartyMapper.INSTANCE.toResponse(counterparty);
    }

    @Override
    public CounterpartyResponse update(CounterpartyRequest request, UUID id) {
        Counterparty counterparty = CounterpartyMapper.INSTANCE.toEntity(request);
        counterparty.setId(id);
        repository.save(counterparty);
        return CounterpartyMapper.INSTANCE.toResponse(counterparty);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public CounterpartyServicesResponse addService(UUID serviceId, UUID counterpartyId) {
        repository.addServiceToCounterparty(serviceId, counterpartyId);
        Counterparty counterparty = repository.findById(counterpartyId).get();
        List<UUID> services = repository.findServiceIdsById(counterpartyId);

        return new CounterpartyServicesResponse(
                CounterpartyMapper.INSTANCE.toResponse(counterparty),
                services
        );
    }

    @Override
    public CounterpartyServicesResponse deleteService(UUID serviceId, UUID counterpartyId) {
        repository.deleteServiceFromCounterparty(serviceId, counterpartyId);
        Counterparty counterparty = repository.findById(counterpartyId).get();
        List<UUID> services = repository.findServiceIdsById(counterpartyId);

        return new CounterpartyServicesResponse(
                CounterpartyMapper.INSTANCE.toResponse(counterparty),
                services
        );
    }

    @Override
    public CounterpartyServicesResponse getServices(UUID counterpartyId) {
        Counterparty counterparty = repository.findById(counterpartyId).get();
        List<UUID> services = repository.findServiceIdsById(counterpartyId);

        return new CounterpartyServicesResponse(
                CounterpartyMapper.INSTANCE.toResponse(counterparty),
                services
        );
    }
}

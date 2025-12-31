package com.zhkh.servicescatalog.service.impl;

import com.zhkh.servicescatalog.api.ServiceRequest;
import com.zhkh.servicescatalog.api.ServiceResponse;
import com.zhkh.servicescatalog.mapper.ServiceMapper;
import com.zhkh.servicescatalog.model.ServiceEntity;
import com.zhkh.servicescatalog.repository.ServiceRepository;
import com.zhkh.servicescatalog.service.ServiceCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceCatalogServiceImpl implements ServiceCatalogService {

    private final ServiceRepository repository;
    private final ServiceMapper mapper;

    @Override
    public List<ServiceResponse> getAllServices() {
        return mapper.toResponse(repository.findAll());
    }
    @Override
    public ServiceResponse getService(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found")));
    }
    @Override
    public ServiceResponse createService(ServiceRequest request) {
        ServiceEntity entity = mapper.toEntity(request);
        entity.setServiceId(UUID.randomUUID());
        return mapper.toResponse(repository.save(entity));
    }
    @Override
    public ServiceResponse updateService(UUID id, ServiceRequest request) {
        ServiceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        entity.setServiceName(request.getServiceName());
        entity.setDescription(request.getDescription());
        entity.setIsActive(true);

        return mapper.toResponse(repository.save(entity));
    }
    @Override
    public void deleteService(UUID id) {
        repository.deleteById(id);
    }
}

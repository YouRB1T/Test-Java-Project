package com.zhkh.servicescatalog.service;

import com.zhkh.servicescatalog.api.ServiceRequest;
import com.zhkh.servicescatalog.api.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface ServiceCatalogService {
    List<ServiceResponse> getAllServices();
    ServiceResponse getService(UUID id);
    ServiceResponse createService(ServiceRequest request);
    ServiceResponse updateService(UUID id, ServiceRequest request);
    void deleteService(UUID id);
}

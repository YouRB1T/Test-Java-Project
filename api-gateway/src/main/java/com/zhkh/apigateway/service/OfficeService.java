package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.office.OfficeRequest;
import com.zhkh.apigateway.api.office.OfficeResponse;

import java.util.List;
import java.util.UUID;

public interface OfficeService {
    List<OfficeResponse> getAll();

    OfficeResponse getById(UUID id);

    OfficeResponse create(OfficeRequest request);

    OfficeResponse update(UUID id, OfficeRequest request);

    void delete(UUID id);

    OfficeResponse getByBuildingId(UUID buildingId);
}

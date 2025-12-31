package com.zhkh.office.service;

import com.zhkh.office.api.OfficeRequest;
import com.zhkh.office.api.OfficeResponse;

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

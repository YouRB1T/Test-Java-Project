package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.building.BuildingApartmentsResponse;
import com.zhkh.apigateway.api.building.BuildingRequest;
import com.zhkh.apigateway.api.building.BuildingResponse;

import java.util.List;
import java.util.UUID;

public interface BuildingService {
    BuildingResponse createBuilding(BuildingRequest request);
    BuildingResponse updateBuilding(UUID id, BuildingRequest request);
    BuildingResponse getBuilding(UUID id);
    void deleteBuilding(UUID id);
    List<BuildingResponse> getBuildings();
    BuildingApartmentsResponse getBuildingsWithApartments(UUID id);

}

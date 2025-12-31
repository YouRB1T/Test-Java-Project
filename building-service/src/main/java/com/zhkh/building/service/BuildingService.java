package com.zhkh.building.service;

import com.zhkh.building.api.BuildingApartmentsResponse;
import com.zhkh.building.api.BuildingRequest;
import com.zhkh.building.api.BuildingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface BuildingService {
    BuildingResponse getBuilding(UUID uuid);
    List<BuildingResponse> getBuildings();
    BuildingResponse updateBuilding(UUID uuid, BuildingRequest request);
    BuildingResponse createBuilding(BuildingRequest request);
    void deleteBuilding(UUID uuid);
    BuildingApartmentsResponse getBuildingsWithApartments(UUID uuid);
}

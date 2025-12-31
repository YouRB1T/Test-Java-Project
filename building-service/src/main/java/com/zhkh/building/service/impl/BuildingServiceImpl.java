package com.zhkh.building.service.impl;

import com.zhkh.building.api.BuildingApartmentsResponse;
import com.zhkh.building.api.BuildingRequest;
import com.zhkh.building.api.BuildingResponse;
import com.zhkh.building.mapper.BuildingMapper;
import com.zhkh.building.model.Building;
import com.zhkh.building.repository.BuildingRepository;
import com.zhkh.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingMapper mapper;
    private final BuildingRepository repository;

    @Autowired
    public BuildingServiceImpl(BuildingMapper mapper, BuildingRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public BuildingResponse getBuilding(UUID uuid) {
        return mapper.toBuildingResponse(
                repository.findById(uuid).orElse(null)
        );
    }

    @Override
    public List<BuildingResponse> getBuildings() {
        return repository.findAll().stream()
                .map(building -> mapper.toBuildingResponse(building))
                .toList();
    }

    @Override
    public BuildingResponse updateBuilding(UUID uuid, BuildingRequest request) {
        Building building = repository.findById(uuid).orElse(null);
        building.setAddress(request.getAddress());
        return mapper.toBuildingResponse(repository.save(building));
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        return mapper.toBuildingResponse(
                repository.save(
                        new Building(UUID.randomUUID(), request.getAddress())
                )
        );
    }

    @Override
    public void deleteBuilding(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public BuildingApartmentsResponse getBuildingsWithApartments(UUID uuid) {
        try {
            return BuildingApartmentsResponse.builder()
                    .building(
                            mapper.toBuildingResponse(
                                    repository.findById(uuid).orElse(null)
                            )
                    )
                    .apartmentIds(
                            repository.findApartmentIdsById(uuid)
                    )
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}

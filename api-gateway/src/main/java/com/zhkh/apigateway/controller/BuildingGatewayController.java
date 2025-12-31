package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.building.BuildingApartmentsResponse;
import com.zhkh.apigateway.api.building.BuildingRequest;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingGatewayController {
    private final WebClient.Builder  webClientBuilder;
    private final BuildingService buildingService;

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponse> getBuilding(@PathVariable("id") UUID buildingId) {
        return ResponseEntity.ok(
                buildingService.getBuilding(buildingId)
        );
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getBuildings() {
        return ResponseEntity.ok(
                buildingService.getBuildings()
        );
    }

    @PostMapping
    public ResponseEntity<BuildingResponse> createBuilding(@RequestBody BuildingRequest buildingRequest) {
        return ResponseEntity.ok(
                buildingService.createBuilding(buildingRequest)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> updateBuilding(@PathVariable("id") UUID buildingId, @RequestBody BuildingRequest buildingRequest) {
        return ResponseEntity.ok(
                buildingService.updateBuilding(buildingId, buildingRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable("id") UUID buildingId) {
        buildingService.deleteBuilding(buildingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/apartments")
    public ResponseEntity<BuildingApartmentsResponse> getBuildingApartments(@PathVariable("id") UUID buildingId) {
        return ResponseEntity.ok(
                buildingService.getBuildingsWithApartments(buildingId)
        );
    }
}

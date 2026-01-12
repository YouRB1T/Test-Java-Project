package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.api.building.BuildingApartmentResponseDTO;
import com.zhkh.apigateway.api.building.BuildingApartmentsResponse;
import com.zhkh.apigateway.api.building.BuildingRequest;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.config.WebClientConfig;
import com.zhkh.apigateway.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//TODO: Подправить urls
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    @Qualifier(value = "buildingWebClient")
    private final WebClient buildingWebClient;

    @Qualifier(value = "apartmentWebClient")
    private final WebClient apartmentWebClient;

    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        return buildingWebClient
                .post()
                .uri("/api/buildings")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public BuildingResponse updateBuilding(UUID id, BuildingRequest request) {
        return buildingWebClient
                .put()
                .uri("/api/buildings/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public BuildingResponse getBuilding(UUID id) {
        return buildingWebClient
                .get()
                .uri("/api/buildings/{id}", id)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public void deleteBuilding(UUID id) {
       buildingWebClient
                .delete()
                .uri("/api/buildings/{id}", id)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }

    @Override
    public List<BuildingResponse> getBuildings() {
        return buildingWebClient
                .get()
                .uri("/api/buildings")
                .retrieve()
                .bodyToFlux(BuildingResponse.class)
                .collectList()
                .block();
    }

    @Override
    public BuildingApartmentsResponse getBuildingsWithApartments(UUID id) {
        BuildingApartmentResponseDTO response = buildingWebClient
                .get()
                .uri("/api/buildings/{id}/apartments", id)
                .retrieve()
                .bodyToMono(BuildingApartmentResponseDTO.class)
                .block();

        List<ApartmentResponse> apartments = response.getApartmentIds()
                .stream().map(apartmentId -> apartmentWebClient
                        .get()
                        .uri("/api/apartments/{apartmentId}", apartmentId)
                        .retrieve()
                        .bodyToMono(ApartmentResponse.class)
                        .block())
                .collect(Collectors.toList());

        return new BuildingApartmentsResponse(response.getBuilding(), apartments);
    }
}

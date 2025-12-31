package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.api.building.BuildingApartmentResponseDTO;
import com.zhkh.apigateway.api.building.BuildingApartmentsResponse;
import com.zhkh.apigateway.api.building.BuildingRequest;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//TODO: Подправить urls
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final WebClient.Builder webClientBuilder;

    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        return webClientBuilder.build()
                .post()
                .uri("/buildings")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public BuildingResponse updateBuilding(UUID id, BuildingRequest request) {
        return webClientBuilder.build()
                .put()
                .uri("/buildings/" + id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public BuildingResponse getBuilding(UUID id) {
        return webClientBuilder.build()
                .get()
                .uri("/buildings/" + id)
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }

    @Override
    public void deleteBuilding(UUID id) {
        webClientBuilder.build()
                .delete()
                .uri("/buildings/" + id)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }

    @Override
    public List<BuildingResponse> getBuildings() {
        return webClientBuilder.build()
                .get()
                .uri("/buildings")
                .retrieve()
                .bodyToFlux(BuildingResponse.class)
                .collectList()
                .block();
    }

    @Override
    public BuildingApartmentsResponse getBuildingsWithApartments(UUID id) {
        BuildingApartmentResponseDTO response = webClientBuilder.build()
                .get()
                .uri("/buildings/" + id + "/apartments")
                .retrieve()
                .bodyToMono(BuildingApartmentResponseDTO.class)
                .block();

        List<ApartmentResponse> apartments = response.getApartmentIds()
                .stream().map(apartmentId -> webClientBuilder.build()
                        .get()
                        .uri("/apartments/" + apartmentId)
                        .retrieve()
                        .bodyToMono(ApartmentResponse.class)
                        .block())
                .collect(Collectors.toList());

        return new BuildingApartmentsResponse(response.getBuilding(), apartments);
    }
}

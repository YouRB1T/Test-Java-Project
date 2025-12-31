package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.apartment.ApartmentRequest;
import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.api.apartment.ApartmentResponseDTO;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


//TODO: Подправить urls
@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final WebClient.Builder  webClientBuilder;

    @Override
    public ApartmentResponse createApartment(ApartmentRequest request) {
        ApartmentResponseDTO createdApartment = webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/api/apartments")
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        ApartmentResponse response = new ApartmentResponse(
                createdApartment.getApartmentId(),
                createdApartment.getApartmentNumber(),
                webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/buildings/" + createdApartment.getBuildingId())
                        .retrieve()
                        .bodyToMono(BuildingResponse.class)
                        .block(),
                createdApartment.getSquareMeters(),
                createdApartment.getNumberOfRooms()
        );
        return response;
    }

    @Override
    public ApartmentResponse updateApartment(UUID id, ApartmentRequest request) {
        ApartmentResponseDTO updatedApartment = webClientBuilder.build()
                .put()
                .uri("http://localhost:8080/api/apartments/{id}", id)
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        BuildingResponse buildingResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/buildings/" + updatedApartment.getBuildingId())
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();

        return new ApartmentResponse(
                        updatedApartment.getApartmentId(),
                        updatedApartment.getApartmentNumber(),
                        buildingResponse,
                        updatedApartment.getSquareMeters(),
                        updatedApartment.getNumberOfRooms()
                );
    }

    @Override
    public ApartmentResponse getApartment(UUID id) {
        ApartmentResponseDTO apartmentResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/apartments/{id}", id)
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        BuildingResponse buildingResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/buildings/" + apartmentResponse.getBuildingId())
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();

        return new ApartmentResponse(
                        apartmentResponse.getApartmentId(),
                        apartmentResponse.getApartmentNumber(),
                        buildingResponse,
                        apartmentResponse.getSquareMeters(),
                        apartmentResponse.getNumberOfRooms()
                );
    }

    @Override
    public void deleteApartment(UUID id) {
        webClientBuilder.build()
                .delete()
                .uri("http://localhost:8080/api/apartments/{id}", id)
                .retrieve();
    }

    @Override
    public List<ApartmentResponse> getApartments() {
        List<ApartmentResponseDTO> apartments = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/apartments")
                .retrieve()
                .bodyToFlux(ApartmentResponseDTO.class)
                .collectList()
                .block();

        List<ApartmentResponse> apartmentResponses = apartments.stream()
                .map(apartment -> {
                    BuildingResponse buildingResponse = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8081/api/buildings/" + apartment.getBuildingId())
                            .retrieve()
                            .bodyToMono(BuildingResponse.class)
                            .block();
                    return new ApartmentResponse(
                            apartment.getApartmentId(),
                            apartment.getApartmentNumber(),
                            buildingResponse,
                            apartment.getSquareMeters(),
                            apartment.getNumberOfRooms()
                    );
                }).collect(Collectors.toList());
        return apartmentResponses;
    }
}

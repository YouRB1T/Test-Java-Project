package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.apartment.ApartmentRequest;
import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.api.apartment.ApartmentResponseDTO;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.config.WebClientConfig;
import com.zhkh.apigateway.mapper.ApartmentMapper;
import com.zhkh.apigateway.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    @Qualifier(value = "apartmentWebClient")
    private final WebClient apartmentWebClient;

    @Qualifier(value = "buildingWebClient")
    private final WebClient buildingWebClient;

    private final ApartmentMapper apartmentMapper;

    @Override
    public ApartmentResponse createApartment(ApartmentRequest request) {
        ApartmentResponseDTO createdApartment = apartmentWebClient
                .post()
                .uri("/api/apartments")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        assert createdApartment != null;

        return apartmentMapper.toResponse(
                createdApartment, getBuildingData(createdApartment)
        );
    }

    @Override
    public ApartmentResponse updateApartment(UUID id, ApartmentRequest request) {
        ApartmentResponseDTO updatedApartment = apartmentWebClient
                .put()
                .uri("/api/apartments/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        assert updatedApartment != null;

        return apartmentMapper.toResponse(
                updatedApartment, getBuildingData(updatedApartment)
        );
    }

    @Override
    public ApartmentResponse getApartment(UUID id) {
        ApartmentResponseDTO apartmentResponse = apartmentWebClient
                .get()
                .uri("/api/apartments/{id}", id)
                .retrieve()
                .bodyToMono(ApartmentResponseDTO.class)
                .block();

        assert apartmentResponse != null;

        return apartmentMapper.toResponse(
                apartmentResponse, getBuildingData(apartmentResponse)
        );
    }

    @Override
    public void deleteApartment(UUID id) {
        apartmentWebClient
                .delete()
                .uri("/api/apartments/{id}", id)
                .retrieve();
    }

    @Override
    public List<ApartmentResponse> getApartments() {
        List<ApartmentResponseDTO> apartments = apartmentWebClient
                .get()
                .uri("/api/apartments")
                .retrieve()
                .bodyToFlux(ApartmentResponseDTO.class)
                .collectList()
                .block();

        assert apartments != null;

        return apartments.stream()
                .map(apartment -> apartmentMapper
                        .toResponse(
                            apartment, getBuildingData(apartment)
                        )
                ).collect(Collectors.toList());
    }

    private BuildingResponse getBuildingData(ApartmentResponseDTO apartment) {

        return buildingWebClient.get()
                .uri("/api/buildings/{id}", apartment.getBuildingId())
                .retrieve()
                .bodyToMono(BuildingResponse.class)
                .block();
    }
}

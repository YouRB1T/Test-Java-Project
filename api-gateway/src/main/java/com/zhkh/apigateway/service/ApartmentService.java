package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.apartment.ApartmentRequest;
import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ApartmentService {
    ApartmentResponse createApartment(ApartmentRequest request);
    ApartmentResponse updateApartment(UUID id, ApartmentRequest request);
    ApartmentResponse getApartment(UUID id);
    void deleteApartment(UUID id);
    List<ApartmentResponse> getApartments();
}

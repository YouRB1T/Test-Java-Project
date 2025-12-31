package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.resident.ResidentRequest;
import com.zhkh.apigateway.api.resident.ResidentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ResidentService {
    ResidentResponse createResident(ResidentRequest request);
    ResidentResponse updateResident(UUID id, ResidentRequest request);
    ResidentResponse getResident(UUID id);
    void deleteResident(UUID id);
    List<ResidentResponse> getResidents();
}

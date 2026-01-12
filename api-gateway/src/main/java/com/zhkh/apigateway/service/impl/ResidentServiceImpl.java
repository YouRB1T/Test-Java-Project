package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.resident.ResidentRequest;
import com.zhkh.apigateway.api.resident.ResidentResponse;
import com.zhkh.apigateway.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    @Override
    public ResidentResponse createResident(ResidentRequest request) {

        return null;
    }

    @Override
    public ResidentResponse updateResident(UUID id, ResidentRequest request) {
        return null;
    }

    @Override
    public ResidentResponse getResident(UUID id) {
        return null;
    }

    @Override
    public void deleteResident(UUID id) {
    }

    @Override
    public List<ResidentResponse> getResidents() {
        return null;
    }
}

package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.resident.ResidentRequest;
import com.zhkh.apigateway.api.resident.ResidentResponse;
import com.zhkh.apigateway.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentGatewayController {
    private final ResidentService residentService;

    @GetMapping
    public ResponseEntity<List<ResidentResponse>> getResidents() {
        return ResponseEntity.ok(residentService.getResidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse> getResident(@PathVariable UUID id) {
        return ResponseEntity.ok(residentService.getResident(id));
    }

    @PostMapping
    public ResponseEntity<ResidentResponse> createResident(@RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.createResident(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponse> updateResident(@PathVariable UUID id, @RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.updateResident(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteResident(@PathVariable UUID id) {
        residentService.deleteResident(id);
    }
}

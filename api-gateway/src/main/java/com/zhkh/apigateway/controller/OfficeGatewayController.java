package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.office.OfficeRequest;
import com.zhkh.apigateway.api.office.OfficeResponse;
import com.zhkh.apigateway.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offices")
@RequiredArgsConstructor
public class OfficeGatewayController {
    private final OfficeService officeService;

    @GetMapping
    public ResponseEntity<List<OfficeResponse>> getOffices() {
        return ResponseEntity.ok(officeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeResponse> getOffice(@PathVariable UUID id) {
        return ResponseEntity.ok(officeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OfficeResponse> createOffice(@RequestBody OfficeRequest request) {
        return ResponseEntity.ok(officeService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfficeResponse> updateOffice(@PathVariable UUID id,
                                                       @RequestBody OfficeRequest request) {
        return ResponseEntity.ok(officeService.update(id, request));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteOffice(@PathVariable UUID id) {
        officeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
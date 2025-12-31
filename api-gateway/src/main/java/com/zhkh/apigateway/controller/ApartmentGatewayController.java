package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.apartment.ApartmentRequest;
import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentGatewayController {
    private final WebClient.Builder  webClientBuilder;
    private final ApartmentService apartmentService;

    @Operation(
            summary = "Получить квартиру с информацией о здании",
            description = "Возвращает данные квартиры и автоматически подгружает информацию о здании"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Квартира не найдена"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentResponse> getApartment(@PathVariable("id") UUID apartmentId) {
        return ResponseEntity.ok(
                apartmentService.getApartment(apartmentId)
        );
    }
    
    @GetMapping
    public ResponseEntity<List<ApartmentResponse>> getAllApartments() {
        return ResponseEntity.ok(
                apartmentService.getApartments()
        );
    }

    @PostMapping
    public ResponseEntity<ApartmentResponse> createApartment(@RequestBody ApartmentRequest apartmentRequest) {
        return ResponseEntity.ok(
                apartmentService.createApartment(apartmentRequest)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentResponse> updateApartment(@PathVariable("id") UUID apartmentId, @RequestBody ApartmentRequest apartmentRequest) {
        return ResponseEntity.ok(
                apartmentService.updateApartment(apartmentId, apartmentRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable("id") UUID apartmentId) {
        apartmentService.deleteApartment(apartmentId);
        return ResponseEntity.noContent().build();
    }
}


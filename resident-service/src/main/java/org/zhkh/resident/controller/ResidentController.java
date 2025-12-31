package org.zhkh.resident.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhkh.resident.api.request.ResidentRequest;
import org.zhkh.resident.api.response.ResidentResponse;
import org.zhkh.resident.service.ResidentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/resident")
@RequiredArgsConstructor
@Tag(name = "Residents", description = "Операции с жильцами")
public class ResidentController {

    private final ResidentService residentService;

    @Operation(summary = "Получить список всех жильцов")
    @ApiResponse(responseCode = "200", description = "Список жильцов успешно получен")
    @GetMapping
    public ResponseEntity<List<ResidentResponse>> getAllResidents() {
        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @Operation(summary = "Получить жильца по ID")
    @ApiResponse(responseCode = "200", description = "Жилец найден",
            content = @Content(schema = @Schema(implementation = ResidentResponse.class)))
    @ApiResponse(responseCode = "404", description = "Жилец не найден")
    @GetMapping("{id}")
    public ResponseEntity<ResidentResponse> getResidentById(@PathVariable UUID id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @Operation(summary = "Создать нового жильца")
    @ApiResponse(responseCode = "200", description = "Жилец успешно создан",
            content = @Content(schema = @Schema(implementation = ResidentResponse.class)))
    @PostMapping
    public ResponseEntity<ResidentResponse> createResident(
            @RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.createResident(request));
    }

    @Operation(summary = "Обновить данные жильца")
    @ApiResponse(responseCode = "200", description = "Жилец успешно обновлён")
    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponse> updateResident(
            @Parameter(description = "UUID жильца", required = true)
            @PathVariable UUID id,
            @RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.updateResident(id, request));
    }

    @Operation(summary = "Удалить жильца по ID")
    @ApiResponse(responseCode = "204", description = "Жилец успешно удалён")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResident(
            @Parameter(description = "UUID жильца", required = true)
            @PathVariable UUID id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список жильцов по ID квартиры")
    @ApiResponse(responseCode = "200", description = "Список жильцов успешно получен")
    @GetMapping("/apartments/{apartmentId}")
    public ResponseEntity<List<ResidentResponse>> getAllResidentsInApartments(
            @Parameter(description = "UUID квартиры", required = true)
            @PathVariable UUID apartmentId) {
        return ResponseEntity.ok(residentService.getAllResidentsInApartments(apartmentId));
    }

}

package com.zhkh.building.controller;

import com.zhkh.building.api.BuildingApartmentsResponse;
import com.zhkh.building.api.BuildingRequest;
import com.zhkh.building.api.BuildingResponse;
import com.zhkh.building.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
@Tag(name = "Buildings", description = "Операции со зданиями")
public class BuildingController {
    private final BuildingService buildingService;

    @Operation(
            summary = "Получить здание по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BuildingResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponse> getBuilding(@PathVariable UUID id) {
        return ResponseEntity.ok(buildingService.getBuilding(id));
    }

    @Operation(
            summary = "Получить здания",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(schema = @Schema(implementation = BuildingResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getBuildings() {
        return ResponseEntity.ok(buildingService.getBuildings());
    }

    @Operation(
            summary = "Создать новое здание",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Создано",
                            content = @Content(schema = @Schema(implementation = BuildingResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
            }
    )
    @PostMapping
    public ResponseEntity<BuildingResponse> createBuilding(@RequestBody BuildingRequest request) {
        return ResponseEntity.ok(buildingService.createBuilding(request));
    }

    @Operation(
            summary = "Обновить здание",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновлено",
                            content = @Content(schema = @Schema(implementation = BuildingResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> updateBuilding(@PathVariable UUID id,
                                                           @RequestBody BuildingRequest request) {
        return ResponseEntity.ok(buildingService.updateBuilding(id, request));
    }

    @Operation(
            summary = "Удалить здание",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Удалено"),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable UUID id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить здание с квартирами",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(schema = @Schema(implementation = BuildingApartmentsResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @GetMapping("/{id}/apartments")
    public ResponseEntity<BuildingApartmentsResponse> getBuildingsWithApartments(@PathVariable UUID id) {
        return ResponseEntity.ok(buildingService.getBuildingsWithApartments(id));
    }
}

package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.building.BuildingApartmentsResponse;
import com.zhkh.apigateway.api.building.BuildingRequest;
import com.zhkh.apigateway.api.building.BuildingResponse;
import com.zhkh.apigateway.service.BuildingService;
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
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingGatewayController {
    private final WebClient.Builder  webClientBuilder;
    private final BuildingService buildingService;

    @Operation(
            summary = "Получить здание",
            description = "Возвращает данные о здании"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Здание не найдена"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponse> getBuilding(@PathVariable("id") UUID buildingId) {
        return ResponseEntity.ok(
                buildingService.getBuilding(buildingId)
        );
    }

    @Operation(
            summary = "Получить информацию о всех зданиях",
            description = "Возвращает данные о всех зданиях"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Таблица не найдена"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getBuildings() {
        return ResponseEntity.ok(
                buildingService.getBuildings()
        );
    }

    @Operation(
            summary = "Создать здание",
            description = "Создает здание по данным"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Здание уже есть"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<BuildingResponse> createBuilding(@RequestBody BuildingRequest buildingRequest) {
        return ResponseEntity.ok(
                buildingService.createBuilding(buildingRequest)
        );
    }

    @Operation(
            summary = "Обновить здание",
            description = "Обновляет информацию о здании по данным"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Здание не найдено"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> updateBuilding(@PathVariable("id") UUID buildingId, @RequestBody BuildingRequest buildingRequest) {
        return ResponseEntity.ok(
                buildingService.updateBuilding(buildingId, buildingRequest)
        );
    }

    @Operation(
            summary = "Удалить здание",
            description = "Удалить здание по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Отсутствие здаяния"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable("id") UUID buildingId) {
        buildingService.deleteBuilding(buildingId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить квартиры в здании",
            description = "Получить все квартиры здания по id здания"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет таблицы"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/{id}/apartments")
    public ResponseEntity<BuildingApartmentsResponse> getBuildingApartments(@PathVariable("id") UUID buildingId) {
        return ResponseEntity.ok(
                buildingService.getBuildingsWithApartments(buildingId)
        );
    }
}

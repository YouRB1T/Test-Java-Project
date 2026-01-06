package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.resident.ResidentRequest;
import com.zhkh.apigateway.api.resident.ResidentResponse;
import com.zhkh.apigateway.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Получить всех жильцов",
            description = "Получение всех жильцов списком"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет таблицы"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<ResidentResponse>> getResidents() {
        return ResponseEntity.ok(residentService.getResidents());
    }

    @Operation(
            summary = "Получить жильца",
            description = "Получение жильца по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найден"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse> getResident(@PathVariable UUID id) {
        return ResponseEntity.ok(residentService.getResident(id));
    }

    @Operation(
            summary = "Создать жильца",
            description = "Создание жильца по данным"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Уже есть"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<ResidentResponse> createResident(@RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.createResident(request));
    }

    @Operation(
            summary = "Обновить жильца",
            description = "Обновление информации о жильце по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найден"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponse> updateResident(@PathVariable UUID id, @RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.updateResident(id, request));
    }

    @Operation(
            summary = "Удалить жильца",
            description = "Удаление жильца по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найден"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @DeleteMapping("/{id}")
    public void deleteResident(@PathVariable UUID id) {
        residentService.deleteResident(id);
    }
}

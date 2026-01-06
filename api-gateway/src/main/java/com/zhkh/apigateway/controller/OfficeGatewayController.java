package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.employee.EmployeeResponse;
import com.zhkh.apigateway.api.office.OfficeRequest;
import com.zhkh.apigateway.api.office.OfficeResponse;
import com.zhkh.apigateway.service.EmployeeService;
import com.zhkh.apigateway.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final EmployeeService employeeService;

    @Operation(
            summary = "Получить все офисы",
            description = "Получение всех офисов списком"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет таблицы"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<OfficeResponse>> getOffices() {
        return ResponseEntity.ok(officeService.getAll());
    }

    @Operation(
            summary = "Получить офис",
            description = "Получение офиса по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет такого офиса"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OfficeResponse> getOffice(@PathVariable UUID id) {
        return ResponseEntity.ok(officeService.getById(id));
    }

    @Operation(
            summary = "Создать офис",
            description = "Создание нового офиса по данным"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Уже есть такой офис"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<OfficeResponse> createOffice(@RequestBody OfficeRequest request) {
        return ResponseEntity.ok(officeService.create(request));
    }

    @Operation(
            summary = "Обновить данные об офисе",
            description = "Обновление данных офиса по его id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет такого офиса"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OfficeResponse> updateOffice(@PathVariable UUID id,
                                                       @RequestBody OfficeRequest request) {
        return ResponseEntity.ok(officeService.update(id, request));
    }

    @Operation(
            summary = "Удалить офис",
            description = "Удаление офиса по его id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет такого офиса"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable UUID id) {
        officeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить все офисы",
            description = "Получение всех офисов списком"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Нет таблицы"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesOfOffice(@PathVariable UUID id) {
        return ResponseEntity.ok(
                employeeService.getEmployeesByOffice(id)
        );
    }
}
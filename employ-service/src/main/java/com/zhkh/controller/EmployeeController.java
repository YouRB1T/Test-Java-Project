package com.zhkh.controller;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;
import com.zhkh.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employService;

    @Operation(
            summary = "Получить всех работников",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(
                employService.getAll()
        );
    }

    @Operation(
            summary = "Получить работника по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Работник не найден")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                employService.getById(id)
        );
    }

    @Operation(
            summary = "Создать работника",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Создан",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(
                employService.create(request)
        );
    }

    @Operation(
            summary = "Обновить данные о работнике",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Работник не найден")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable UUID id,
            @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(
                employService.update(id, request)
        );
    }

    @Operation(
            summary = "Удалить работника",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Удалено"),
                    @ApiResponse(responseCode = "404", description = "Работник не найден")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        employService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить услуги работника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Set.class))),
                    @ApiResponse(responseCode = "404", description = "Работник не найден")
            }
    )
    @GetMapping("/{id}/services")
    public ResponseEntity<Set<UUID>> getServices(@PathVariable UUID id) {
        return ResponseEntity.ok(
                employService.getServices(id)
        );
    }

    @Operation(
            summary = "Добавить услугу работнику",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Set.class))),
                    @ApiResponse(responseCode = "404", description = "Работник не найден")
            }
    )
    @PostMapping("/{id}/services")
    public ResponseEntity<Set<UUID>> addService(
            @PathVariable UUID id,
            @RequestParam UUID serviceId) {
        return ResponseEntity.ok(
                employService.addService(id, serviceId)
        );
    }

    @Operation(
            summary = "Удалить услугу у работника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Set.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @DeleteMapping("/{id}/services/{serviceId}")
    public ResponseEntity<Set<UUID>> removeService(
            @PathVariable UUID id,
            @PathVariable UUID serviceId) {
        return ResponseEntity.ok(
                employService.removeService(id, serviceId)
        );
    }

    @Operation(
            summary = "Получить работников по ID офиса",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Здание не найдено")
            }
    )
    @GetMapping("/offices/{officeId}")
    public ResponseEntity<List<EmployeeResponse>> getByOffice(@PathVariable UUID officeId) {
        return ResponseEntity.ok(
                employService.getByOffice(officeId)
        );
    }
}


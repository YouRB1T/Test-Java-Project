package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.counterparty.CounterpartyRequest;
import com.zhkh.apigateway.api.counterparty.CounterpartyResponse;
import com.zhkh.apigateway.api.service.ServiceResponse;
import com.zhkh.apigateway.service.CounterpartyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/counterparties")
@RequiredArgsConstructor
@Tag(name = "Counterparty API", description = "Управление контрагентами")
public class CounterpartyGatewayController {

    private final CounterpartyService counterpartyService;

    @Operation(summary = "Получить всех контрагентов")
    @ApiResponse(responseCode = "200", description = "Список контрагентов")
    @GetMapping
    public ResponseEntity<List<CounterpartyResponse>> getAll() {
        return ResponseEntity.ok(counterpartyService.getAll());
    }

    @Operation(summary = "Получить контрагента по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Контрагент найден"),
            @ApiResponse(responseCode = "404", description = "Контрагент не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CounterpartyResponse> getById(
            @Parameter(description = "ID контрагента")
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(counterpartyService.getById(id));
    }

    @Operation(summary = "Создать контрагента")
    @ApiResponse(responseCode = "201", description = "Контрагент создан")
    @PostMapping
    public ResponseEntity<CounterpartyResponse> create(
            @RequestBody CounterpartyRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(counterpartyService.create(request));
    }

    @Operation(summary = "Обновить контрагента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Контрагент обновлён"),
            @ApiResponse(responseCode = "404", description = "Контрагент не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CounterpartyResponse> update(
            @PathVariable UUID id,
            @RequestBody CounterpartyRequest request
    ) {
        return ResponseEntity.ok(counterpartyService.update(id, request));
    }

    @Operation(summary = "Удалить контрагента")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Контрагент удалён"),
            @ApiResponse(responseCode = "404", description = "Контрагент не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        counterpartyService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Получить услуги контрагента")
    @ApiResponse(responseCode = "200", description = "Список услуг")
    @GetMapping("/{counterpartyId}/services")
    public ResponseEntity<List<ServiceResponse>> getServices(
            @PathVariable UUID counterpartyId
    ) {
        return ResponseEntity.ok(counterpartyService.getServices(counterpartyId));
    }

    @Operation(summary = "Добавить услугу контрагенту")
    @ApiResponse(responseCode = "200", description = "Услуга добавлена")
    @PostMapping("/{counterpartyId}/services/{serviceId}")
    public ResponseEntity<List<ServiceResponse>> addService(
            @PathVariable UUID counterpartyId,
            @PathVariable UUID serviceId
    ) {
        return ResponseEntity.ok(
                counterpartyService.addService(counterpartyId, serviceId)
        );
    }

    @Operation(summary = "Удалить услугу у контрагента")
    @ApiResponse(responseCode = "200", description = "Услуга удалена")
    @DeleteMapping("/{counterpartyId}/services/{serviceId}")
    public ResponseEntity<List<ServiceResponse>> deleteService(
            @PathVariable UUID counterpartyId,
            @PathVariable UUID serviceId
    ) {
        return ResponseEntity.ok(
                counterpartyService.deleteService(counterpartyId, serviceId)
        );
    }
}


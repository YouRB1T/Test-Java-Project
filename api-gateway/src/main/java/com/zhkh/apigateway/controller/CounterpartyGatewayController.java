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
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<List<CounterpartyResponse>>> getAll() {
        return counterpartyService.getAll().map(
                ResponseEntity::ok
        );
    }

    @Operation(summary = "Получить контрагента по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Контрагент найден"),
            @ApiResponse(responseCode = "404", description = "Контрагент не найден")
    })
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CounterpartyResponse>> getById(
            @Parameter(description = "ID контрагента")
            @PathVariable UUID id
    ) {
        return counterpartyService.getById(id)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Создать контрагента")
    @ApiResponse(responseCode = "201", description = "Контрагент создан")
    @PostMapping
    public Mono<ResponseEntity<CounterpartyResponse>> create(
            @RequestBody CounterpartyRequest request
    ) {
        return counterpartyService.create(request)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Обновить контрагента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Контрагент обновлён"),
            @ApiResponse(responseCode = "404", description = "Контрагент не найден")
    })
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CounterpartyResponse>> update(
            @PathVariable UUID id,
            @RequestBody CounterpartyRequest request
    ) {
        return counterpartyService.update(id, request)
                .map(ResponseEntity::ok);
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
    public Mono<ResponseEntity<List<ServiceResponse>>> getServices(
            @PathVariable UUID counterpartyId
    ) {
        return counterpartyService.getServices(counterpartyId)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Добавить услугу контрагенту")
    @ApiResponse(responseCode = "200", description = "Услуга добавлена")
    @PostMapping("/{counterpartyId}/services/{serviceId}")
    public Mono<ResponseEntity<List<ServiceResponse>>> addService(
            @PathVariable UUID counterpartyId,
            @PathVariable UUID serviceId
    ) {
        return counterpartyService.addService(counterpartyId, serviceId)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Удалить услугу у контрагента")
    @ApiResponse(responseCode = "200", description = "Услуга удалена")
    @DeleteMapping("/{counterpartyId}/services/{serviceId}")
    public Mono<ResponseEntity<List<ServiceResponse>>> deleteService(
            @PathVariable UUID counterpartyId,
            @PathVariable UUID serviceId
    ) {
        return counterpartyService.deleteService(counterpartyId, serviceId)
                .map(ResponseEntity::ok);
    }
}


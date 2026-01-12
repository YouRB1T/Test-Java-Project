package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.request.RequestRequestModel;
import com.zhkh.apigateway.api.request.ResponseRequestModel;
import com.zhkh.apigateway.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@Tag(name = "Requests", description = "Управление заявками")
public class RequestController {

    private final RequestService requestService;

    @Operation(summary = "Создать заявку")
    @PostMapping
    public Mono<ResponseEntity<ResponseRequestModel>> create(
            @RequestParam UUID residentId,
            @RequestBody RequestRequestModel request
    ) {
        return requestService.create(request, residentId)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r));
    }

    @Operation(summary = "Обновить заявку")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResponseRequestModel>> update(
            @PathVariable UUID id,
            @RequestBody RequestRequestModel request
    ) {
        return requestService.update(id, request)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Получить заявку по ID")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseRequestModel>> getById(
            @PathVariable UUID id
    ) {
        return requestService.getById(id)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Получить все заявки")
    @GetMapping
    public Flux<ResponseRequestModel> getAll() {
        return requestService.getAll();
    }

    @Operation(summary = "Удалить заявку")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID id) {
        return requestService.delete(id)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Operation(summary = "Получить заявки жителя")
    @GetMapping("/resident/{residentId}")
    public Flux<ResponseRequestModel> getByResident(
            @PathVariable UUID residentId
    ) {
        return requestService.getByResident(residentId);
    }

    @Operation(summary = "Получить услуги заявки")
    @GetMapping("/{id}/services")
    public Mono<Set<UUID>> getServices(@PathVariable UUID id) {
        return requestService.getServices(id);
    }

    @Operation(summary = "Добавить услугу в заявку")
    @PostMapping("/{id}/services/{serviceId}")
    public Mono<Set<UUID>> addService(
            @PathVariable UUID id,
            @PathVariable UUID serviceId
    ) {
        return requestService.addService(id, serviceId);
    }

    @Operation(summary = "Удалить услугу из заявки")
    @DeleteMapping("/{id}/services/{serviceId}")
    public Mono<Set<UUID>> removeService(
            @PathVariable UUID id,
            @PathVariable UUID serviceId
    ) {
        return requestService.removeService(id, serviceId);
    }
}


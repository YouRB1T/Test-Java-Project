package com.zhkh.servicescatalog.controller;

import com.zhkh.servicescatalog.api.ServiceRequest;
import com.zhkh.servicescatalog.api.ServiceResponse;
import com.zhkh.servicescatalog.service.ServiceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Service Catalog", description = "Управление услугами")
public class ServiceCatalogController {

    private final ServiceCatalogService service;

    @Operation(summary = "Получить все услуги")
    @GetMapping
    public List<ServiceResponse> getAll() {
        return service.getAllServices();
    }

    @Operation(summary = "Получить услугу по ID")
    @GetMapping("/{id}")
    public ServiceResponse getService(@PathVariable UUID id) {
        return service.getService(id);
    }

    @Operation(summary = "Создать услугу")
    @PostMapping
    public ServiceResponse create(@RequestBody @Valid ServiceRequest request) {
        return service.createService(request);
    }

    @Operation(summary = "Обновить услугу")
    @PutMapping("/{id}")
    public ServiceResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid ServiceRequest request
    ) {
        return service.updateService(id, request);
    }

    @Operation(summary = "Удалить услугу")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteService(id);
    }
}


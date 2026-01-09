package com.zhkh.counterparty.controller;

import com.zhkh.counterparty.api.CounterpartyRequest;
import com.zhkh.counterparty.api.CounterpartyResponse;
import com.zhkh.counterparty.api.CounterpartyServicesResponse;
import com.zhkh.counterparty.service.CounterpartyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/counterparties")
@RequiredArgsConstructor
@Tag(name = "Counterparty Controller", description = "CRUD операции для контрагентов")
public class CounterpartyController {
    private final CounterpartyService counterpartyService;

    @Operation(summary = "Получить все контрагенты")
    @GetMapping
    public ResponseEntity<List<CounterpartyResponse>> getAll() {
        return ResponseEntity.ok(counterpartyService.readAll());
    }

    @Operation(summary = "Получить контрагента по ID")
    @GetMapping("/{office_id}")
    public ResponseEntity<CounterpartyResponse> getById(@PathVariable("office_id") UUID id) {
        return ResponseEntity.ok(counterpartyService.readById(id));
    }

    @Operation(summary = "Создать контрагента")
    @PostMapping
    public ResponseEntity<CounterpartyResponse> create(@RequestBody @Valid CounterpartyRequest request) {
        return ResponseEntity.ok(counterpartyService.create(request));
    }

    @Operation(summary = "Обновить контрагента")
    @PutMapping("/{office_id}")
    public ResponseEntity<CounterpartyResponse> update(
            @PathVariable("office_id") UUID id,
            @RequestBody @Valid CounterpartyRequest request
    ) {
        return ResponseEntity.ok(counterpartyService.update(request, id));
    }

    @Operation(summary = "Удалить контрагента")
    @DeleteMapping("/{office_id}")
    public void delete(@PathVariable("office_id") UUID id) {
        counterpartyService.deleteById(id);
    }

    @Operation(summary = "Добавить услугу контрагенту")
    @PostMapping("/{counterparty_id}/services/{service_id}")
    public ResponseEntity<CounterpartyServicesResponse> addService(
            @PathVariable("service_id") UUID serviceId, @PathVariable("counterparty_id") UUID counterpartyId
    ) {
        return ResponseEntity.ok(
                counterpartyService.addService(serviceId, counterpartyId)
        );
    }

    @Operation(summary = "Удаление услуги контрагента")
    @DeleteMapping("/{counterparty_id}/services/{service_id}")
    public ResponseEntity<CounterpartyServicesResponse> deleteService(
            @PathVariable("service_id") UUID serviceId, @PathVariable("counterparty_id") UUID counterpartyId
    ) {
        return ResponseEntity.ok(
                counterpartyService.deleteService(serviceId, counterpartyId)
        );
    }

    @Operation(summary = "Получение всех услуг контрагента")
    @GetMapping("/{counterparty_id}/services")
    public ResponseEntity<CounterpartyServicesResponse> getServices(@PathVariable("counterparty_id") UUID counterpartyId) {
        return ResponseEntity.ok(
                counterpartyService.getServices(counterpartyId)
        );
    }
}

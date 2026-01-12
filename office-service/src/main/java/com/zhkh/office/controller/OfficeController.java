package com.zhkh.office.controller;

import com.zhkh.office.api.OfficeRequest;
import com.zhkh.office.api.OfficeResponse;
import com.zhkh.office.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offices")
@RequiredArgsConstructor
@Tag(name = "Office Controller", description = "CRUD операции для офисов ЖКХ")
public class OfficeController {

    private final OfficeService officeService;

    @Operation(summary = "Получить все офисы")
    @GetMapping
    public ResponseEntity<List<OfficeResponse>> getAll() {
        return ResponseEntity.ok(officeService.getAll());
    }

    @Operation(summary = "Получить офис по ID")
    @GetMapping("/{office_id}")
    public ResponseEntity<OfficeResponse> getById(@PathVariable("office_id") UUID id) {
        return ResponseEntity.ok(officeService.getById(id));
    }

    @Operation(summary = "Создать офис")
    @PostMapping
    public ResponseEntity<OfficeResponse> create(@RequestBody @Valid OfficeRequest request) {
        return ResponseEntity.ok(officeService.create(request));
    }

    @Operation(summary = "Обновить офис")
    @PutMapping("/{office_id}")
    public ResponseEntity<OfficeResponse> update(
            @PathVariable("office_id") UUID id,
            @RequestBody @Valid OfficeRequest request
    ) {
        return ResponseEntity.ok(officeService.update(id, request));
    }

    @Operation(summary = "Удалить офис")
    @DeleteMapping("/{office_id}")
    public void delete(@PathVariable("office_id") UUID id) {
        officeService.delete(id);
    }

    @Operation(summary = "Получить офис по зданию")
    @GetMapping("/building/{building_id}")
    public ResponseEntity<OfficeResponse> getByBuilding(
            @PathVariable("building_id") UUID buildingId
    ) {
        return ResponseEntity.ok(officeService.getByBuildingId(buildingId));
    }
}


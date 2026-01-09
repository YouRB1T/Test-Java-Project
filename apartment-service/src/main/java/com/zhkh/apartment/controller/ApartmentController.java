package com.zhkh.apartment.controller;

import com.zhkh.apartment.api.ApartmentRequest;
import com.zhkh.apartment.api.ApartmentResponse;
import com.zhkh.apartment.mapper.ApartmentMapper;
import com.zhkh.apartment.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
@Tag(name = "Apartment Service", description = "CRUD операции над квартирами")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Operation(summary = "Получить список всех квартир")
    @GetMapping
    public ResponseEntity<List<ApartmentResponse>> getAll() {
        return ResponseEntity.ok(
                apartmentService.findAll()
        );
    }

    @Operation(summary = "Получить квартиру по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(apartmentService.findById(id));
    }

    @Operation(summary = "Создать квартиру")
    @PostMapping
    public ResponseEntity<ApartmentResponse> create(@RequestBody @Valid ApartmentRequest dto) {
        return ResponseEntity.ok(apartmentService.create(dto));
    }

    @Operation(summary = "Обновить квартиру")
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentResponse> update(@PathVariable UUID id,
                                       @RequestBody @Valid ApartmentRequest dto) {
        return ResponseEntity.ok(apartmentService.update(id, dto));
    }

    @Operation(summary = "Удалить квартиру")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        apartmentService.delete(id);
    }
}


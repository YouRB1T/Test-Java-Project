package controller;

import api.ReviewRequest;
import api.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReviewService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Управление отзывами")
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    @Operation(summary = "Создать отзыв")
    public ResponseEntity<ReviewResponse> create(@RequestBody @Valid ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "Обновить отзыв")
    public ResponseEntity<ReviewResponse> update(
            @PathVariable UUID reviewId,
            @RequestBody @Valid ReviewRequest request
    ) {
        return ResponseEntity.ok(service.update(reviewId, request));
    }

    @GetMapping
    @Operation(summary = "Получить все отзывы")
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "Получить отзыв по ID")
    public ResponseEntity<ReviewResponse> getById(@PathVariable UUID reviewId) {
        return ResponseEntity.ok(service.getById(reviewId));
    }

    @GetMapping("/resident/{residentId}")
    @Operation(summary = "Получить отзывы жителя")
    public ResponseEntity<List<ReviewResponse>> getByResident(
            @PathVariable UUID residentId
    ) {
        return ResponseEntity.ok(service.getByResident(residentId));
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Удалить отзыв")
    public ResponseEntity<Void> delete(@PathVariable UUID reviewId) {
        service.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}


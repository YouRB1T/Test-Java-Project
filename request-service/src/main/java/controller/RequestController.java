package controller;

import api.RequestChange;
import api.RequestRequestModel;
import api.ResponseChange;
import api.ResponseRequestModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ChangeService;
import service.RequestService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@Tag(
        name = "Requests",
        description = "API для управления заявками и их изменениями"
)
public class RequestController {

    private final RequestService requestService;
    private final ChangeService changeService;

    // -------------------- REQUESTS --------------------

    @Operation(
            summary = "Создание заявки",
            description = "Создаёт новую заявку от жителя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Заявка успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<ResponseRequestModel> createRequest(
            @RequestBody @Valid RequestRequestModel request,
            @Parameter(description = "ID жителя", required = true)
            @RequestParam UUID residentId
    ) {
        ResponseRequestModel response =
                requestService.create(request, residentId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "Обновление заявки",
            description = "Обновляет содержимое и приоритет заявки"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заявка обновлена"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @PutMapping("/{requestId}")
    public ResponseEntity<ResponseRequestModel> updateRequest(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId,
            @RequestBody @Valid RequestRequestModel request
    ) {
        return ResponseEntity.ok(
                requestService.update(requestId, request)
        );
    }

    @Operation(
            summary = "Получить все заявки"
    )
    @ApiResponse(responseCode = "200", description = "Список заявок")
    @GetMapping
    public ResponseEntity<List<ResponseRequestModel>> getAllRequests() {
        return ResponseEntity.ok(
                requestService.getAll()
        );
    }

    @Operation(
            summary = "Получить заявку по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заявка найдена"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @GetMapping("/{requestId}")
    public ResponseEntity<ResponseRequestModel> getRequestById(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId
    ) {
        return ResponseEntity.ok(
                requestService.getById(requestId)
        );
    }

    @Operation(
            summary = "Удалить заявку"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Заявка удалена"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId
    ) {
        requestService.delete(requestId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить заявки жителя"
    )
    @ApiResponse(responseCode = "200", description = "Список заявок жителя")
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<ResponseRequestModel>> getRequestsByResident(
            @Parameter(description = "ID жителя", required = true)
            @PathVariable UUID residentId
    ) {
        return ResponseEntity.ok(
                requestService.getByResident(residentId)
        );
    }

    // -------------------- CHANGES --------------------

    @Operation(
            summary = "Получить историю изменений заявки"
    )
    @ApiResponse(responseCode = "200", description = "История изменений")
    @GetMapping("/{requestId}/changes")
    public ResponseEntity<List<ResponseChange>> getRequestChanges(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId
    ) {
        return ResponseEntity.ok(
                changeService.getByRequest(requestId)
        );
    }

    @Operation(
            summary = "Добавить изменение статуса заявки",
            description = "Создаёт запись изменения и обновляет статус заявки"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Изменение добавлено"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @PostMapping("/{requestId}/changes")
    public ResponseEntity<ResponseChange> addRequestChange(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId,
            @RequestBody @Valid RequestChange request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        changeService.addChange(requestId, request)
                );
    }

    @Operation(
            summary = "Получить список услуг заявки",
            description = "Возвращает список serviceId, связанных с заявкой"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список услуг"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @GetMapping("/{requestId}/services")
    public ResponseEntity<Set<UUID>> getRequestServices(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId
    ) {
        return ResponseEntity.ok(
                requestService.getServices(requestId)
        );
    }

    @Operation(
            summary = "Добавить услугу к заявке",
            description = "Привязывает услугу к заявке"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Услуга добавлена"),
            @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    })
    @PostMapping("/{requestId}/services")
    public ResponseEntity<Set<UUID>> addServiceToRequest(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId,

            @Parameter(description = "ID услуги", required = true)
            @RequestParam UUID serviceId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        requestService.addService(requestId, serviceId)
                );
    }

    @Operation(
            summary = "Удалить услугу из заявки",
            description = "Удаляет связь заявки с услугой"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Услуга удалена"),
            @ApiResponse(responseCode = "404", description = "Связь не найдена")
    })
    @DeleteMapping("/{requestId}/services/{serviceId}")
    public ResponseEntity<Set<UUID>> removeServiceFromRequest(
            @Parameter(description = "ID заявки", required = true)
            @PathVariable UUID requestId,

            @Parameter(description = "ID услуги", required = true)
            @PathVariable UUID serviceId
    ) {
        return ResponseEntity.ok(
                requestService.removeService(requestId, serviceId)
        );
    }
}



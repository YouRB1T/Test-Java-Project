package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.registration.*;
import com.zhkh.apigateway.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@Tag(
        name = "Registration",
        description = "Регистрация пользователей и связанных сущностей"
)
public class RegistrationGatewayController {

    private final RegistrationService registrationService;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Создание учетной записи пользователя"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Пользователь зарегистрирован",
                    content = @Content(
                            schema = @Schema(implementation = RegistrationUserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content
            )
    })
    @PostMapping("/user")
    public Mono<ResponseEntity<RegistrationUserResponse>> registerUser(
            @RequestBody RegistrationUserRequest request
    ) {
        return registrationService.registrationUser(request)
                .map(response -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response)
                );
    }

    @Operation(
            summary = "Регистрация контрагента",
            description = "Создание контрагента в системе"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Контрагент зарегистрирован"
            )
    })
    @PostMapping("/counterparty")
    public Mono<ResponseEntity<Void>> registerCounterparty(
            @RequestBody RegistrationCounterpartyRequest request
    ) {
        return registrationService.registrationCounterparty(request)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Operation(
            summary = "Регистрация сотрудника",
            description = "Создание сотрудника организации"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Сотрудник зарегистрирован"
            )
    })
    @PostMapping("/employee")
    public Mono<ResponseEntity<Void>> registerEmployee(
            @RequestBody RegistrationEmployeeRequest request
    ) {
        return registrationService.registrationEmployee(request)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Operation(
            summary = "Регистрация жителя",
            description = "Создание жителя дома"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Житель зарегистрирован"
            )
    })
    @PostMapping("/resident")
    public Mono<ResponseEntity<Void>> registerResident(
            @RequestBody RegistrationResidentRequest request
    ) {
        return registrationService.registrationResident(request)
                .thenReturn(ResponseEntity.noContent().build());
    }
}


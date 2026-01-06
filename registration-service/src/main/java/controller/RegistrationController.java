package controller;

import api.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serice.RegistrationService;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(
            summary = "Зарегистрировать пользователя",
            description = "Регистрация пользователя по данным"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Уже есть"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/user")
    public ResponseEntity<RegistrationUserResponse> registerUser(
            @RequestBody RegistrationUserRequest request
    ) {
        RegistrationUserResponse response =
                registrationService.registrationUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "Зарегистрировать жильца",
            description = "Регистрация жильца по данным и создание для него пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/resident")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerResident(
            @RequestBody RegistrationResidentRequest request
    ) {
        registrationService.registrationResident(request);
    }

    @Operation(
            summary = "Зарегистрировать работника",
            description = "Регистрация работника по данным и создание пользователя под него"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(
            @RequestBody RegistrationEmployeeRequest request
    ) {
        registrationService.registrationEmployee(request);
    }

    @Operation(
            summary = "Зарегистрировать контрагента",
            description = "Регистрация контрагента по данным и создать пользователя под него"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/counterparty")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCounterparty(
            @RequestBody RegistrationCounterpartyRequest request
    ) {
        registrationService.registrationCounterparty(request);
    }
}

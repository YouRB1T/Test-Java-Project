package controller;

import api.*;
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

    @PostMapping("/resident")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerResident(
            @RequestBody RegistrationResidentRequest request
    ) {
        registrationService.registrationResident(request);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(
            @RequestBody RegistrationEmployeeRequest request
    ) {
        registrationService.registrationEmployee(request);
    }

    @PostMapping("/counterparty")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCounterparty(
            @RequestBody RegistrationCounterpartyRequest request
    ) {
        registrationService.registrationCounterparty(request);
    }
}

package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.employee.EmployeeRequest;
import com.zhkh.apigateway.api.employee.EmployeeResponse;
import com.zhkh.apigateway.api.employee.EmployeeResponseDto;
import com.zhkh.apigateway.api.office.OfficeResponse;
import com.zhkh.apigateway.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Qualifier("employeeWebClient")
    private final WebClient employeeWebClient;

    @Qualifier("officeWebClient")
    private final WebClient officeWebClient;

    @Override
    public Mono<EmployeeResponse> getById(UUID id) {
        return employeeWebClient
                .get()
                .uri("/api/employees/{id}", id)
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .flatMap(this::mapToEmployeeResponse);
    }

    @Override
    public Mono<List<EmployeeResponse>> getAll() {
        return employeeWebClient
                .get()
                .uri("/api/employees")
                .retrieve()
                .bodyToFlux(EmployeeResponseDto.class)
                .flatMap(this::mapToEmployeeResponse)
                .collectList();
    }

    @Override
    public Mono<EmployeeResponse> create(EmployeeRequest request) {
        return employeeWebClient
                .post()
                .uri("/api/employees")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .flatMap(this::mapToEmployeeResponse);
    }

    @Override
    public Mono<EmployeeResponse> update(UUID id, EmployeeRequest request) {
        return employeeWebClient
                .put()
                .uri("/api/employees/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .flatMap(this::mapToEmployeeResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return employeeWebClient
                .delete()
                .uri("/api/employees/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .then();
    }

    @Override
    public Mono<Set<UUID>> getServices(UUID employeeId) {
        return employeeWebClient
                .get()
                .uri("/api/employees/{id}/services", employeeId)
                .retrieve()
                .bodyToFlux(UUID.class)
                .collect(Collectors.toSet());
    }

    @Override
    public Mono<Set<UUID>> addService(UUID employeeId, UUID serviceId) {
        return employeeWebClient
                .post()
                .uri("/api/employees/{eid}/services/{sid}", employeeId, serviceId)
                .retrieve()
                .bodyToFlux(UUID.class)
                .collect(Collectors.toSet());
    }

    @Override
    public Mono<Set<UUID>> removeService(UUID employeeId, UUID serviceId) {
        return employeeWebClient
                .delete()
                .uri("/api/employees/{eid}/services/{sid}", employeeId, serviceId)
                .retrieve()
                .bodyToFlux(UUID.class)
                .collect(Collectors.toSet());
    }

    @Override
    public Mono<List<EmployeeResponse>> getEmployeesByOffice(UUID officeId) {
        return employeeWebClient
                .get()
                .uri("/api/employees/office/{officeId}", officeId)
                .retrieve()
                .bodyToFlux(EmployeeResponseDto.class)
                .flatMap(this::mapToEmployeeResponse)
                .collectList();
    }

    private Mono<EmployeeResponse> mapToEmployeeResponse(EmployeeResponseDto dto) {
        return officeWebClient
                .get()
                .uri("/api/offices/{id}", dto.getOfficeId())
                .retrieve()
                .bodyToMono(OfficeResponse.class)
                .map(office -> new EmployeeResponse(
                        dto.getId(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getMiddleName(),
                        dto.getExperienceYears(),
                        office,
                        dto.getPhone(),
                        dto.getEmail(),
                        dto.getHireDate()
                ));
    }
}


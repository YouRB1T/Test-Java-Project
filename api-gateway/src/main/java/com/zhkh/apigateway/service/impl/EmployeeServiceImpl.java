package com.zhkh.apigateway.service.impl;

import com.zhkh.apigateway.api.employee.EmployeeResponse;
import com.zhkh.apigateway.api.employee.EmployeeResponseDto;
import com.zhkh.apigateway.api.office.OfficeResponse;
import com.zhkh.apigateway.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final WebClient.Builder webClientBuilder;

    @Override
    public List<EmployeeResponse> getEmployeesByOffice(UUID officeId) {
        List<EmployeeResponseDto> listDTOs =
                webClientBuilder.build()
                        .get()
                        .uri("")
                        .retrieve()
                        .bodyToFlux(EmployeeResponseDto.class)
                        .collectList()
                        .block();

        List<EmployeeResponse> employeeResponseList =
                listDTOs.stream()
                        .map(employee -> {
                                    OfficeResponse officeResponse = webClientBuilder.build()
                                            .get()
                                            .uri("/" + employee.getOfficeId())
                                            .retrieve()
                                            .bodyToMono(OfficeResponse.class)
                                            .block();
                                return new EmployeeResponse(
                                        employee.getId(),
                                        employee.getFirstName(),
                                        employee.getLastName(),
                                        employee.getMiddleName(),
                                        employee.getExperienceYears(),
                                        officeResponse,
                                        employee.getPhone(),
                                        employee.getEmail(),
                                        employee.getHireDate()
                                );
                        }
                        ).toList();

        return employeeResponseList;
    }
}

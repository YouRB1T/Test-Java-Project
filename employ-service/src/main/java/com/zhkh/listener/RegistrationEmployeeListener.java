package com.zhkh.listener;

import com.zhkh.api.EmployeePayload;
import com.zhkh.api.EmployeeRequest;
import com.zhkh.event.RegistrationEvent;
import com.zhkh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationEmployeeListener {
    private final EmployeeService service;

    @KafkaListener(
            topics = "employee-registration",
            groupId = "employee-service"
    )
    public void handle(RegistrationEvent<EmployeePayload> event) {
        EmployeePayload payload = event.getPayload();

        service.create(
                new EmployeeRequest(
                        payload.getFirstName(),
                        payload.getLastName(),
                        payload.getMiddleName(),
                        payload.getExperienceYears(),
                        payload.getOfficeId(),
                        event.getUserId(),
                        payload.getPhone(),
                        event.getEmail(),
                        payload.getHireDate()
                )
        );
    }
}

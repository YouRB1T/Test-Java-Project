package serice.impl;

import api.*;
import kafka.events.RegistrationEvent;
import kafka.payloads.CounterpartyPayload;
import kafka.payloads.EmployeePayload;
import kafka.payloads.ResidentPayload;
import lombok.RequiredArgsConstructor;
import mapper.CounterpartyMapper;
import mapper.EmployeeMapper;
import mapper.ResidentMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serice.RegistrationService;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, RegistrationEvent> kafkaTemplate;

    private final CounterpartyMapper counterpartyMapper;
    private final EmployeeMapper employeeMapper;
    private final ResidentMapper residentMapper;

    @Override
    public RegistrationUserResponse registrationUser(RegistrationUserRequest request) {

        return webClientBuilder.build()
                .post()
                .uri("")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RegistrationUserResponse.class)
                .block();
    }

    @Override
    public void registrationCounterparty(RegistrationCounterpartyRequest request) {
        RegistrationUserResponse responseUser = registrationUser(
                new RegistrationUserRequest(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CounterpartyPayload payload = counterpartyMapper.toPayload(request);
        kafkaTemplate.send("counterparty-registration", new RegistrationEvent(
                responseUser.getUserId(),
                responseUser.getEmail(),
                payload
        ));

    }

    @Override
    public void registrationEmployee(RegistrationEmployeeRequest request) {
        RegistrationUserResponse responseUser = registrationUser(
                new RegistrationUserRequest(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );

        EmployeePayload payload = employeeMapper.toPayload(request);
        kafkaTemplate.send("employee-registration", new RegistrationEvent(
                responseUser.getUserId(),
                responseUser.getEmail(),
                payload
        ));
    }

    @Override
    public void registrationResident(RegistrationResidentRequest request) {

        RegistrationUserResponse responseUser = registrationUser(
                new RegistrationUserRequest(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );

        ResidentPayload payload = residentMapper.toPayload(request);

        kafkaTemplate.send("resident-registration", new RegistrationEvent(
                responseUser.getUserId(),
                responseUser.getEmail(),
                payload
        ));
    }
}

package com.zhkh.counterparty.listener;

import com.zhkh.counterparty.api.CounterpartyPayload;
import com.zhkh.counterparty.api.CounterpartyRequest;
import com.zhkh.counterparty.event.RegistrationEvent;
import com.zhkh.counterparty.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationCounterpartyListener {
    private final CounterpartyService service;

    @KafkaListener(
            topics = "employee-registration",
            groupId = "employee-service"
    )
    public void handle(RegistrationEvent<CounterpartyPayload> event) {
        CounterpartyPayload payload = event.getPayload();

        service.create(
                new CounterpartyRequest(
                        payload.getName(),
                        payload.getTaxId(),
                        payload.getRrc(),
                        payload.getPsrn(),
                        payload.getAddress(),
                        payload.getPhone(),
                        event.getEmail(),
                        payload.getContactPerson(),
                        event.getUserId()
                )
        );
    }
}

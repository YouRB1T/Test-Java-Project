package org.zhkh.resident.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhkh.resident.api.ResidentPayload;
import org.zhkh.resident.api.request.ResidentRequest;
import org.zhkh.resident.event.RegistrationEvent;
import org.zhkh.resident.service.ResidentService;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentRegistrationListener {

    private final ResidentService residentService;

    @KafkaListener(
            topics = "resident-registration",
            groupId = "resident-service"
    )
    public void handle(RegistrationEvent<ResidentPayload> event) {
        ResidentPayload payload = event.getPayload();

        residentService.createResident(
                new ResidentRequest(
                        payload.getFirstName(),
                        payload.getFirstName(),
                        payload.getMiddleName(),
                        payload.getApartmentId(),
                        event.getUserId(),
                        payload.getPhone(),
                        event.getEmail()
                )
        );
    }
}

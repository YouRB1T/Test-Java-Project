package kafka.events;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationEvent {
    private UUID userId;
    private String email;
    private Object payload;
}


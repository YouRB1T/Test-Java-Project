package kafka.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentPayload {
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID apartmentId;
    private String phone;
}


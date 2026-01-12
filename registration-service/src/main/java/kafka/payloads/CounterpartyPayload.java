package kafka.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterpartyPayload {
    private UUID userId;
    private String email;
    private String name;
    private String taxId;
    private String RRC;
    private String PSRN;
    private String address;
    private String phone;
    private String contactPerson;
}


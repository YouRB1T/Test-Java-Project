package org.zhkh.resident.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ResidentResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID apartmentId;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
}

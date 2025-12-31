package org.zhkh.resident.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ResidentRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID apartmentId;
    private UUID userId;
    private String phone;
    private String email;
}

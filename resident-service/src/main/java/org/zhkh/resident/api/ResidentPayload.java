package org.zhkh.resident.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentPayload {
    private String firstName;
    private String lastName;
    private String middleName;
    private UUID apartmentId;
    private String phone;
}


package com.zhkh.office.api;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeResponse {

    private UUID officeId;
    private UUID buildingId;
    private String phone;
    private String email;
}


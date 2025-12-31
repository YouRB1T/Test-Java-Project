package com.zhkh.apigateway.api.office;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeResponseDTO {

    private UUID officeId;
    private UUID buildingId;
    private String phone;
    private String email;
}

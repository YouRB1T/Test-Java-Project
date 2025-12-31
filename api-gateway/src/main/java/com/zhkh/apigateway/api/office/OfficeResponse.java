package com.zhkh.apigateway.api.office;

import com.zhkh.apigateway.api.building.BuildingResponse;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeResponse {

    private UUID officeId;
    private BuildingResponse building;
    private String phone;
    private String email;
}

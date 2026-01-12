package com.zhkh.apigateway.api.counterparty;

import com.zhkh.apigateway.api.service.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterpartyResponseWithServices {
    private UUID id;
    private String name;
    private String taxId;
    private String RRC;
    private String PSRN;
    private String address;
    private String phone;
    private String email;
    private String contactPerson;
    private boolean isActive;
    private Date createAt;
    private Date updateAt;
    private List<UUID> serviceResponseList;
}

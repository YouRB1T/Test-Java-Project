package com.zhkh.apigateway.api.counterparty;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CounterpartyResponse {
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
}

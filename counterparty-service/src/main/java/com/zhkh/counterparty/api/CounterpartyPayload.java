package com.zhkh.counterparty.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterpartyPayload {
    private String name;
    private String taxId;
    private String rrc;
    private String psrn;
    private String address;
    private String phone;
    private String contactPerson;
}


package com.zhkh.counterparty.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CounterpartyServicesResponse {
    CounterpartyResponse counterpartyResponse;
    List<UUID> services;
}

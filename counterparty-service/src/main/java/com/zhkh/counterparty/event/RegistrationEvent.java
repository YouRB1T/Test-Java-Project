package com.zhkh.counterparty.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEvent<T> {
    private UUID userId;
    private String email;
    private T payload;
}

package com.zhkh.counterparty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "counterparty_services")
public class CounterpartyServiceEntity {
    @Id
    @Column(name = "service_id", nullable = false)
    private UUID serviceId;
    @Id
    @Column(name = "counterparty_id", nullable = false)
    private UUID counterpartyId;
 }

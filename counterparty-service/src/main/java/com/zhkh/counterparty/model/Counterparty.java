package com.zhkh.counterparty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "counterparty")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counterparty {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "counterparty_id", nullable = false)
    private UUID id;

    @Column(name = "organization_name", nullable = false)
    private String name;

    @Column(name = "tax_id", nullable = false)
    private String taxId;

    @Column(name = "registration_reason_code", nullable = false)
    private String RRC;

    @Column(name = "primary_state_registration_number", nullable = false)
    private String PSRN;

    @Column(name = "legal_address", nullable = false)
    private String address;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_person", nullable = false)
    private String contactPerson;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;
}

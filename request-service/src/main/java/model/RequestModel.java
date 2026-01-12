package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestModel {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private UUID id;

    private String content;

    @Column(name = "resident_id")
    private UUID residentId;

    @Column(name = "status_id")
    private Integer statusId;

    private Integer priority;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


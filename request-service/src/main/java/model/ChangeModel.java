package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_changes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeModel {

    @Id
    @GeneratedValue
    @Column(name = "change_id")
    private UUID id;

    private LocalDateTime changeDate;

    private Integer newStatusId;
    private Integer oldStatusId;

    @Column(name = "request_id")
    private UUID requestId;

    private String changeNotes;
}

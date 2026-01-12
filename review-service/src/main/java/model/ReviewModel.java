package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private UUID id;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "written_date")
    private LocalDateTime writtenDate;

    @Column(name = "request_id", nullable = false, unique = true)
    private UUID requestId;

    @Column(name = "resident_id", nullable = false)
    private UUID residentId;

    @Column(name = "is_approved")
    private Boolean isApproved = false;

    @Column(name = "is_updated")
    private Boolean isUpdated = false;

    @Column(name = "last_date_update")
    private LocalDateTime lastDateUpdate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}


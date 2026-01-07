package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    private UUID id;
    private Integer rating;
    private String content;

    private LocalDateTime writtenDate;

    private UUID requestId;
    private UUID residentId;

    private Boolean isApproved;
    private Boolean isUpdated;

    private LocalDateTime lastDateUpdate;
}


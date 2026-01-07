package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChange {
    private UUID id;
    private LocalDateTime changeDate;
    private Integer newStatusId;
    private Integer oldStatusId;
    private UUID requestId;
    private String changeNotes;
}

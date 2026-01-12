package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRequestModel {
    private UUID id;
    private String content;
    private Integer priority;
    private Integer statusId;
    private UUID residentId;
    private LocalDateTime createdAt;
}

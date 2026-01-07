package api;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @Min(1)
    @Max(10)
    private Integer rating;

    private String content;

    private UUID requestId;
    private UUID residentId;
}


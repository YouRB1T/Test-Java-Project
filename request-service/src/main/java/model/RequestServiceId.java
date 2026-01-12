package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestServiceId implements Serializable {

    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "service_id", nullable = false)
    private UUID serviceId;
}

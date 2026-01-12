package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "request_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestServiceLink {

    @EmbeddedId
    private RequestServiceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("requestId")
    @JoinColumn(name = "request_id", nullable = false)
    private RequestModel request;

}

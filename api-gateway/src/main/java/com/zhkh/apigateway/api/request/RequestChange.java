package com.zhkh.apigateway.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestChange {
    private Integer newStatusId;
    private String notes;
}

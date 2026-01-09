package com.zhkh.apigateway.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRequestModel {
    private String content;
    private Integer priority;
}

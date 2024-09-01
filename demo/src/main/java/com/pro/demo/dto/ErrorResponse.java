package com.pro.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
       
    private Integer status;
    private String message;


}

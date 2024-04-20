package com.example.demo.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ResponseMessage {
    private String message;
    private HttpStatus status;
    private long timeStamp;
}

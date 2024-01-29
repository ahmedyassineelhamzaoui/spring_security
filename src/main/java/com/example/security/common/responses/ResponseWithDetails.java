package com.example.security.common.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithDetails {

    private LocalDateTime timesTamps;
    private String message;
    private String status;
    private Map<String,Object> details;

}

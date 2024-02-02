package com.example.security.common.responses;


import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseWithDetails {

    private LocalDateTime timesTamps;
    private String message;
    private String status;
    private Map<String,Object> details;

}

package com.example.security.common.responses;


import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithoutDetails {

    private LocalDateTime timesTamps;
    private String message;
    private String status;
}

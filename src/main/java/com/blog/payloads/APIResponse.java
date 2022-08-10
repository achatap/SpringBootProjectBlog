package com.blog.payloads;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIResponse {
    private String message;
    private boolean success;
    private  LocalDateTime timeStamp;
}

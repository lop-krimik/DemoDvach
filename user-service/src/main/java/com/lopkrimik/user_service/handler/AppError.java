package com.lopkrimik.user_service.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private int StatusCode;
    private String message;
}

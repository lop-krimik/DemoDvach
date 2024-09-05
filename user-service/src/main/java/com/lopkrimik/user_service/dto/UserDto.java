package com.lopkrimik.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
}

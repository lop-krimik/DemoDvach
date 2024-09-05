package com.lopkrimik.paste_service.dto;

import com.lopkrimik.paste_service.paste.Access;
import com.lopkrimik.paste_service.paste.Lifecycle;
import com.lopkrimik.paste_service.paste.Paste;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PasteDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String text;
    private String url;
    private String email;
    private Lifecycle lifecycle;
    private LocalDateTime expiredTime;
    private Access access;

    }


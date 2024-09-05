package com.lopkrimik.paste_service.paste;

import ch.qos.logback.core.spi.LifeCycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table( name = "paste")
public class Paste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column (name = "text")
    private String text;

    @Column (name = "url")
    private String url;

    @Column (name = "email")
    private String email;

    @Column (name = "lifecycle")
    @Enumerated(EnumType.STRING)
    private Lifecycle lifecycle;

    @Column (name = "expiredTime")
    private LocalDateTime expiredTime;

    @Column (name = "access")
    @Enumerated(EnumType.STRING)
    private Access access;


}

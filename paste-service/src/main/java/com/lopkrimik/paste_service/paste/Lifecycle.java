package com.lopkrimik.paste_service.paste;


import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

@RequiredArgsConstructor
@Getter
public enum Lifecycle {
    TWENTY_SEC(Duration.ofSeconds(20)),
    TEN_MIN(Duration.ofMinutes(10)),
    ONE_HOUR(Duration.ofHours(1)),
    THREE_HOURS(Duration.ofHours(3)),
    ONE_DAY(Duration.ofDays(1)),
    ONE_WEEK(Duration.ofDays(7)),
    ONE_MONTH(Duration.ofDays(30)),
    INFINITY(Duration.ofDays(999999));

    private final Duration duration;
}

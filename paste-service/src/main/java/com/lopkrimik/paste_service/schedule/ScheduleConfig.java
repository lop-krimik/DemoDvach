package com.lopkrimik.paste_service.schedule;

import com.lopkrimik.paste_service.repository.PasteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ScheduleConfig {
    private final PasteRepository pasteRepository;

    @Transactional
    @Scheduled(fixedDelay = 3000)
    public void deleteExpiredPaste(){
        LocalDateTime currentDateTimeInMoscow = LocalDateTime.now(ZoneId.of("Europe/Moscow")); // Московское время
        pasteRepository.deleteAllByExpiredTimeIsBefore(currentDateTimeInMoscow);
        log.info("Expired pastes have been deleted successfully");
    }
}

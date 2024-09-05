package com.lopkrimik.notification_service.kafka;

import com.domain.basedomain.events.PasteEvent;
import com.lopkrimik.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationPasteConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "paste_topics",groupId = "notification")
    public void consume (PasteEvent pasteEvent) {
        log.info(String.format("Paste has been received -> %s", pasteEvent.toString()));
        notificationService.sendPasteNotification(pasteEvent);
    }


}


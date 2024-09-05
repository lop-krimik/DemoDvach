package com.lopkrimik.notification_service.kafka;

import com.domain.basedomain.events.UserEvent;
import com.lopkrimik.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationUserConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "user_topics", groupId = "notification")
    public void consume (UserEvent userEvent){
        log.info(String.format("UserEvent has been received ->%s", userEvent.toString()));
        notificationService.sendUserNotification(userEvent);
    }
}

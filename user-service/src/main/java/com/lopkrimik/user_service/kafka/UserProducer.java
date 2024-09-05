package com.lopkrimik.user_service.kafka;

import com.domain.basedomain.events.UserEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserProducer {

    private KafkaTemplate<String, UserEvent> kafkaTemplate;
    private NewTopic userTopic;

    public void sendMessage (UserEvent userEvent){
        log.info(String.format("User has been created -> %s", userEvent.toString()));
        Message<UserEvent> message = MessageBuilder.withPayload(userEvent)
                .setHeader(KafkaHeaders.TOPIC,userTopic.name())
                .build();
        kafkaTemplate.send(message);
    }
}

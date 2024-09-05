package com.lopkrimik.paste_service.kafka;

import com.domain.basedomain.events.PasteEvent;
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
public class PasteProducer {

    private NewTopic newTopic;
    private KafkaTemplate<String, PasteEvent> kafkaTemplate;


    public void sendMessage(PasteEvent pasteEvent){
        log.info(String.format("Paste Event -> %s", pasteEvent.toString()));
        Message<PasteEvent> message = MessageBuilder
                .withPayload(pasteEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        kafkaTemplate.send(message);
    }

}

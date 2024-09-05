package com.lopkrimik.paste_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

@Service
public class PasteTopicConfig {
    @Value("paste_topics")
    private String topicName;

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("paste_topics")
                .build();
    }
}

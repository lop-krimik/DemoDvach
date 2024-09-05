package com.lopkrimik.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

@Configuration
public class UserTopicConfig {

    @Bean
    public NewTopic userTopic(){
      return  TopicBuilder.name("user_topics")
                .build();
    }
}

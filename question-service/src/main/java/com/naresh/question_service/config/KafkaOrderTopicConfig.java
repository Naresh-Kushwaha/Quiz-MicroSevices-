package com.naresh.question_service.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic questionTopic() {
        return TopicBuilder
                .name("question-topic")
                .build();
    }
    @Bean
    public NewTopic scoreTopic() {
        return TopicBuilder
                .name("score-topic")
                .build();
    }
}

package com.naresh.question_service.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreProducer {
    private final KafkaTemplate<String,ScorePayload>kafkaTemplate;
    public void sendScore(ScorePayload scorePayload){
        kafkaTemplate.send("score-topic",scorePayload);
    }
}

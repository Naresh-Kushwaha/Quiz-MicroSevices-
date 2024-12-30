package com.naresh.question_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionProducer {
    private final KafkaTemplate<String, QuestionPayload> kafkaTemplate;
     public void sendQuestion(QuestionPayload kafkapayload){
         log.info("Sending kafka payload: ");
         Message<QuestionPayload> message= MessageBuilder
                 .withPayload(kafkapayload)
                 .setHeader(TOPIC,"question-topic")
                 .build();
         kafkaTemplate.send(message);
     }
}

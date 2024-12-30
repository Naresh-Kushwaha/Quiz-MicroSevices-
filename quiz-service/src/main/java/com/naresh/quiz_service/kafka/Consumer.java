package com.naresh.quiz_service.kafka;


import com.naresh.quiz_service.Quiz.Quiz;
import com.naresh.quiz_service.Quiz.QuizRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {
    private final QuizRepo repo;

    @KafkaListener(topics = "question-topic")
    public void QuestionConsumer(QuestionPayload questionPayload){
        log.info(format("Consuming the message form Question Service %s", questionPayload));
        Optional<Quiz> quiz=repo.findById(questionPayload.quizId());
        if(quiz.isPresent()){
            quiz.get().setQuestionsId(questionPayload.questionsIds());
            repo.save(quiz.get());

        }

    }
}

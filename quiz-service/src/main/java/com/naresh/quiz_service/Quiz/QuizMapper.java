package com.naresh.quiz_service.Quiz;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QuizMapper {
    public Quiz toQuiz(QuizRequest req){
        return Quiz.builder()
                .id(UUID.randomUUID())
                .title(req.title())
                .description(req.description())
                .startTime(req.startTime())
                .endTime(req.endTime())
                .createdBy(req.createdBy())
                .questionsId(req.questionId()).build();

    }
}

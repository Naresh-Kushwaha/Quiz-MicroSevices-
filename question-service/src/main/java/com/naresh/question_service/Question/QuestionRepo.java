package com.naresh.question_service.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface QuestionRepo extends JpaRepository<Question, UUID> {
}

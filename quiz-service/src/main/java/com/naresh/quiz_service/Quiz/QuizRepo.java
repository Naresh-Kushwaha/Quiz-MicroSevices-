package com.naresh.quiz_service.Quiz;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepo  extends JpaRepository<Quiz, UUID> {


    List<Quiz> findByCreatedBy(UUID id);
}

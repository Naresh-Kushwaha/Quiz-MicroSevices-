package com.naresh.quiz_service.Quiz;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    private UUID id;
    private String title;
    private String description;
    private UUID createdBy;
    private List<UUID> questionsId;
    @Enumerated(EnumType.STRING)
    private QuizStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}

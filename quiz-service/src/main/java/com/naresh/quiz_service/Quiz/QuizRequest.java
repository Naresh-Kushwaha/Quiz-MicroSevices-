package com.naresh.quiz_service.Quiz;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record QuizRequest(
         @NotNull(message="Title is required")
          String title,
          @NotNull(message="Description is required")
          String description,
          @NotNull(message="QuizMaster id is required")
          UUID createdBy,
          @NotNull(message="Provide Atlest one question")
         List<UUID> questionId,
          @NotNull(message="Start Time is required")
          @FutureOrPresent(message = "Start Time should be in future")
          LocalDateTime startTime,
          @NotNull(message="End Time is required")
          @FutureOrPresent(message="End Time should be in future")
          LocalDateTime endTime


) {


}

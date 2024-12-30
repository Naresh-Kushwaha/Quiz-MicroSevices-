package com.naresh.question_service.kafka;

import java.util.List;
import java.util.UUID;

public record QuestionPayload(
        UUID quizId,
        List<UUID> questionsIds
) {
}

package com.naresh.question_service.kafka;

import java.util.UUID;

public record ScorePayload(

        UUID quizId,
        UUID playerId,
        Integer score
) {
}

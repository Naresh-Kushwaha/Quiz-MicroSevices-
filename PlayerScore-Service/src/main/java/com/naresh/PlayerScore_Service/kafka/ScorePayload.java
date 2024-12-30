package com.naresh.PlayerScore_Service.kafka;

import java.util.UUID;

public record ScorePayload(
        UUID quizId,
        UUID playerId,
        Integer score
) {
}

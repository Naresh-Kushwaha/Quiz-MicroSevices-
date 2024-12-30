package com.naresh.PlayerScore_Service.kafka;

import java.util.UUID;

public record LeaderBoard(
        UUID playerId,
        Integer score
) {
}

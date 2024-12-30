package com.naresh.PlayerScore_Service.playerScore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerScoreRepo extends JpaRepository<PlayerScore,UUID> {
}

package com.naresh.PlayerScore_Service.kafka;


import com.naresh.PlayerScore_Service.config.WebSocketHandler;
import com.naresh.PlayerScore_Service.playerScore.PlayerScore;
import com.naresh.PlayerScore_Service.playerScore.PlayerScoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Consumer {
    private  final WebSocketHandler webSocketHandler;

   private final PlayerScoreRepo playerScoreRepo;
   @KafkaListener(topics = "score-topic")
   public void scoreConsumer(ScorePayload scorePayload){

       playerScoreRepo.save(
               PlayerScore.builder()
                       .playerId(scorePayload.playerId())
                       .quizId(scorePayload.quizId())
                       .score(scorePayload.score())
                       .timeStamp(LocalDateTime.now())
                       .build()
       );

       try {
           webSocketHandler.sendScoreToWebSocket(new LeaderBoard( scorePayload.playerId(),scorePayload.score()));
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

}

package com.naresh.PlayerScore_Service.playerScore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerScore {
    @Id
    private UUID playerId;
    private UUID quizId;
    private Integer score;
    @CreatedDate
    @Column(updatable=false,nullable=false)
    private LocalDateTime timeStamp;

}

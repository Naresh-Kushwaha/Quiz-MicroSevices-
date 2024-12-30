package com.naresh.question_service.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    private UUID id;
    private String questionText;
    @ElementCollection
    private List<String> options;
    private Integer ansIdx;

}

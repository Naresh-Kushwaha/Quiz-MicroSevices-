package com.naresh.question_service.Question;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionDTO {

    private String questionText;
    private List<String> options;
    private Integer ansIdx;
}

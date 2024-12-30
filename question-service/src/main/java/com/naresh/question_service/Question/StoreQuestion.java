package com.naresh.question_service.Question;


import jakarta.persistence.ElementCollection;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Data
@Component
public class StoreQuestion implements Serializable {
    private UUID id;
    private String questionText;
    private List<String> options;
}

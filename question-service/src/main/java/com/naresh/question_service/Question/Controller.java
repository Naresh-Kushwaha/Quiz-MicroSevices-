package com.naresh.question_service.Question;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class Controller {
    private final QuestionService service;

    @PostMapping("/{quiz-id}")
    public ResponseEntity<List<UUID>> addQuestionsToQuiz( @PathVariable("quiz-id") UUID quizId,@RequestBody List<QuestionDTO> questionDTOs) throws JsonProcessingException {

        return ResponseEntity.ok(service.addQuestionsToQuiz(quizId,questionDTOs));
    }
    @GetMapping("/joinQuiz")
    public ResponseEntity<Object> joinQuiz(@RequestParam("quizId") UUID quizId){
        return ResponseEntity.ok(service.joinQuiz(quizId));
    }
    @PostMapping("/submitQuiz/{quizId}/{user-Id}")
    public ResponseEntity<Map<UUID,Integer>> submitQuiz(@PathVariable UUID quizId,@PathVariable("user-Id") UUID userId,@RequestBody Map<UUID,Integer> answers){
        return ResponseEntity.ok(service.submitQuiz(quizId,userId,answers));
    }

}

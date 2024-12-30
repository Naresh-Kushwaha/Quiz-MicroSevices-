package com.naresh.quiz_service.Quiz;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService service;
    @PostMapping
    public ResponseEntity<UUID> createQuiz(@RequestBody @Valid QuizRequest req){
         return ResponseEntity.ok(service.createQuiz(req));
    }
    @PostMapping("/{id}/questions")
    public ResponseEntity<UUID> addQuestionToQuiz(@PathVariable UUID id,@RequestBody List<UUID> Questid){
        return ResponseEntity.ok(service.addQuestionToQuiz(id,Questid));
    }
    @PostMapping("/{quiz-id}/start")
    public ResponseEntity<String> startQuiz(@PathVariable("quiz-id") UUID id){
        return ResponseEntity.ok(service.startQuiz(id));

    }
    @PostMapping("/{quiz-id}/join")
    public ResponseEntity<String> joinQuiz(@PathVariable("quiz-id") UUID id,@RequestBody UUID playerId){

        return service.joinQuiz(id,playerId);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<Quiz>> getQuizByUserId(@PathVariable("user-id") UUID id){
         return service.getQuizByUserId(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable UUID id){
        return service.getQuizById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuizById(@PathVariable UUID id){
        return service.deleteQuizById(id);
    }


}

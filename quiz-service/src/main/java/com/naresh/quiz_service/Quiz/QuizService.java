package com.naresh.quiz_service.Quiz;
import com.naresh.quiz_service.User.UserClient;
import com.naresh.quiz_service.User.UserResponse;
import com.naresh.quiz_service.exception.QuizNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepo repository;
    private final QuizMapper mapper;
    private final UserClient userClient;

    public UUID createQuiz(QuizRequest req){
      var user=userClient.findUserById(req.createdBy());
       Quiz quiz= mapper.toQuiz(req);
       quiz.setStatus(QuizStatus.CREATED);
        return repository.save(quiz).getId();

    }
    public UUID addQuestionToQuiz(UUID id, List<UUID>questid) {
        Optional<Quiz> quiz=repository.findById(id);
        quiz.get().setQuestionsId(questid);
        return  repository.save(quiz.get()).getId();
    }

    public String startQuiz(UUID id) {
        Quiz quiz=repository.findById(id).orElseThrow(()->new QuizNotFoundException("Cannot start Quiz :: No Quiz exists with the provided ID"));
          quiz.setStatus(QuizStatus.STARTED);
          repository.save(quiz);

        return "Quiz started";
    }
    public ResponseEntity<String> joinQuiz(UUID id, UUID playerId) {

        return ResponseEntity.ok("Quiz joined successfully");
    }

    public ResponseEntity<List<Quiz>> getQuizByUserId(UUID id) {
     Optional<UserResponse> user= userClient.findUserById(id);
       if(user.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
        List<Quiz> QuizList=repository.findByCreatedBy(id);
        return  ResponseEntity.ok(QuizList);
    }

    public ResponseEntity<Quiz> getQuizById(UUID id) {
       Optional<Quiz> quiz=repository.findById(id);
        return quiz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    public  ResponseEntity<String> deleteQuizById(UUID id){
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz Not Found !");}
        repository.deleteById(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Quiz Deleted Successfully");
    }



}

package com.naresh.question_service.Question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naresh.question_service.kafka.QuestionPayload;
import com.naresh.question_service.kafka.QuestionProducer;
import com.naresh.question_service.kafka.ScorePayload;
import com.naresh.question_service.kafka.ScoreProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo repo;
    private final QuestionProducer producer;
    private final ScoreProducer scoreProducer;
    private final RedisTemplate<String, Object> redisTemplate;


    @Transactional
    public List<UUID> addQuestionsToQuiz(UUID quizId, List<QuestionDTO> questionDTOs) throws JsonProcessingException {
        List<UUID> ids = new ArrayList<>();

        // Map DTOs to Question entities for database storage
        List<Question> questions = questionDTOs.stream().map(dto -> {
            Question question = new Question();
            UUID idGenerated = UUID.randomUUID();
            ids.add(idGenerated);
            question.setId(idGenerated);
            question.setQuestionText(dto.getQuestionText());
            question.setOptions(dto.getOptions());
            question.setAnsIdx(dto.getAnsIdx());
            return question;
        }).collect(Collectors.toList());

        // Map DTOs to StoreQuestion objects for Redis storage
        List<StoreQuestion> storeQuestions = questions.stream().map(dto -> {
            StoreQuestion question = new StoreQuestion();
            question.setId(dto.getId());
            question.setQuestionText(dto.getQuestionText());
            question.setOptions(dto.getOptions());
            return question;
        }).collect(Collectors.toList());

        // Store questions in Redis
        ObjectMapper objectMapper=new ObjectMapper();
        String JsonValue= objectMapper.writeValueAsString(storeQuestions);
        redisTemplate.opsForValue().set(quizId.toString(), JsonValue);

        // Save questions in the database
        repo.saveAll(questions);

        // Publish question data to Kafka
        producer.sendQuestion(new QuestionPayload(quizId, ids));

        return ids;
    }

    public Object joinQuiz(UUID quizId) {
        return  redisTemplate.opsForValue().get(quizId.toString());
    }


    public Map<UUID, Integer> submitQuiz(UUID quizId, UUID userId, Map<UUID, Integer> answers) {

        int score = 0;
        for (Map.Entry<UUID, Integer> entry : answers.entrySet()) {
            UUID questionId = entry.getKey();
            int answerIndex = entry.getValue();

            // Check if the answer is correct
            Optional<Question> question = repo.findById(questionId);
            if (question.isPresent() && question.get().getAnsIdx() == answerIndex) {
                score++;
            }
        }

        // Prepare score data
        Map<UUID, Integer> data = new HashMap<>();
        data.put(userId, score);

        // Publish score data to Kafka
        scoreProducer.sendScore(new ScorePayload(quizId, userId, score));

        return data;
    }
}

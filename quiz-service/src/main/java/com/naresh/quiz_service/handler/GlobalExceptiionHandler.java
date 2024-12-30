package com.naresh.quiz_service.handler;


import com.naresh.quiz_service.exception.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptiionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<String> handle(QuizNotFoundException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handelMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        var errors=new HashMap<String,String>();
        exp.getBindingResult().getAllErrors().forEach(error->{
            var fieldName=((FieldError)error).getField();
            var errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return  ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }
}

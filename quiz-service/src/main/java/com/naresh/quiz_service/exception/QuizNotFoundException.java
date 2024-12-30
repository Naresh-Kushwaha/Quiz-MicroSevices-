package com.naresh.quiz_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class QuizNotFoundException extends RuntimeException {
   private final String  message;

}

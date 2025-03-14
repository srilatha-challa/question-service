package com.micro.question.service.exception;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.micro.question.service.dto.ErrorReponse;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionHandler {
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorReponse> constraintViolationException(ConstraintViolationException exception){
		return new ResponseEntity<ErrorReponse>(new ErrorReponse(null, HttpStatus.BAD_REQUEST.value(), List.of(extractQueryErrorName(exception.getMessage()))), HttpStatus.BAD_REQUEST);		
	}
	
	private String extractQueryErrorName(String errorMessage) {
        try {
			/* Define a regex pattern to match field names  to remove unwanted message 
			 * before column in error message*/
            Pattern pattern = Pattern.compile(".+:(.+)");
            Matcher matcher = pattern.matcher(errorMessage);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
			/* If no match is found, return null or an appropriate default value */
            return null;
        } catch (Exception e) {
            return errorMessage;
        }
    }

}

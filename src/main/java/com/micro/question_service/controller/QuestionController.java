package com.micro.question_service.controller;

import com.micro.question_service.dto.QuestionResponse;
import com.micro.question_service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> findAllQuestions(){
        return new ResponseEntity<>(questionService.findAllQuestions(), HttpStatus.OK);
    }
}

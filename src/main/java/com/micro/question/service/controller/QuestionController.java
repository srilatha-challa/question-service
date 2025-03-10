package com.micro.question.service.controller;

import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
@Tag(name = "Question Controller", description = "Question Operations API")
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

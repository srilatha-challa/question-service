package com.micro.question.service.controller;

import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("question")
@Tag(name = "Question Controller", description = "Question Operations API")
public class QuestionController {
	
	
    private final QuestionService questionService;
    

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    @GetMapping("allQuestions")
    @Operation(summary = "Get All Questions")
    public ResponseEntity<List<QuestionResponse>> findAllQuestions(){
        return new ResponseEntity<>(questionService.findAllQuestions(), HttpStatus.OK);   
    }
    
    @GetMapping("category/{category}")
    @Operation(summary = "Get Questions for given Category")
    public ResponseEntity<List<QuestionResponse>> findQuestionsByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(questionService.findQuestionsByCategory(category), HttpStatus.OK);
    }
    
    @PostMapping("add/questions")
    @Operation(summary = "Add Questions")
    public ResponseEntity<String> addQuestions(@Valid @RequestBody List<QuestionRequest> questionReqList){
    	return new ResponseEntity<String>(questionService.addQuestions(questionReqList), HttpStatus.CREATED);
    }
    
    @DeleteMapping("delete/questions")
    @Operation(summary = "Delete Questions")
    public ResponseEntity<String> deleteQuestions(@NotEmpty(message = "Id list should not empty") @RequestBody List<Integer> idList){
    	return new ResponseEntity<String>(questionService.deleteQuestions(idList), HttpStatus.OK);   	
    }
}

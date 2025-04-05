package com.micro.question.service.controller;

import com.micro.question.service.dto.AnswersRequest;
import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.dto.QuestionWrapper;
import com.micro.question.service.exception.NotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("all")
    @Operation(summary = "Get All Questions")
    public ResponseEntity<List<QuestionResponse>> findAllQuestions(){
        return new ResponseEntity<>(questionService.findAllQuestions(), HttpStatus.OK);   
    }
    
    @GetMapping("category/{category}")
    @Operation(summary = "Get Questions for given Category")
    public ResponseEntity<List<QuestionResponse>> findQuestionsByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(questionService.findQuestionsByCategory(category), HttpStatus.OK);
    }
    
    @PostMapping("add")
    @Operation(summary = "Add Questions")
    public ResponseEntity<String> addQuestions(@Valid @RequestBody List<QuestionRequest> questionReqList){
    	return new ResponseEntity<>(questionService.addQuestions(questionReqList), HttpStatus.CREATED);
    }
    
    @DeleteMapping("delete")
    @Operation(summary = "Delete Questions")
    public ResponseEntity<String> deleteQuestions(@NotEmpty(message = "Id list {should.not.empty}")@RequestBody List<Integer> idList) throws NotFoundException{
    	return new ResponseEntity<>(questionService.deleteQuestions(idList), HttpStatus.OK);   	
    }
    
    @GetMapping("generate")
    @Operation(summary = "Get Questions For Quiz")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam("category") String category, 
    										@RequestParam("numOfQuestions") Integer numOfQuestions){
    	return new ResponseEntity<>(questionService.getQuestionForQuiz(category, numOfQuestions), HttpStatus.OK);   	    	
    }
    
    @PostMapping("getQuestions")
    @Operation(summary = "Get Questions For Id's")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@NotEmpty(message = "Id list {should.not.empty}") @RequestBody List<Integer> idList) throws NotFoundException{
    	return new ResponseEntity<>(questionService.getQuestionsFromId(idList), HttpStatus.OK);   	    	   	
    }
    
    
    @PostMapping("getScore")
    @Operation(summary = "Get Score For Quiz")
    public ResponseEntity<Integer> getScore(@Valid @RequestBody List<AnswersRequest> answersRequests){
    	return new ResponseEntity<>(questionService.getScore(answersRequests), HttpStatus.OK);
    }
}

package com.micro.question.service.service;

import java.util.List;

import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;

public interface QuestionService {

    public List<QuestionResponse> findAllQuestions();

	public List<QuestionResponse> findQuestionsByCategory(String category);
	
	public String addQuestions(List<QuestionRequest> questionList);

	public String deleteQuestions(List<Integer> idList);
}

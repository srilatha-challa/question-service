package com.micro.question.service.service;

import java.util.List;

import com.micro.question.service.dto.AnswersRequest;
import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.dto.QuestionWrapper;
import com.micro.question.service.exception.NotFoundException;

public interface QuestionService {

    public List<QuestionResponse> findAllQuestions();

	public List<QuestionResponse> findQuestionsByCategory(String category);
	
	public String addQuestions(List<QuestionRequest> questionList);

	public String deleteQuestions(List<Integer> idList) throws NotFoundException;

	public List<Integer> getQuestionForQuiz(String category, Integer numOfQuestions);

	public List<QuestionWrapper> getQuestionsFromId(List<Integer> idList) throws NotFoundException;

	public Integer getScore(List<AnswersRequest> answersRequests);
}

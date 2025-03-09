package com.micro.question_service.service;

import com.micro.question_service.dto.QuestionResponse;

import java.util.List;

public interface QuestionService {

    public List<QuestionResponse> findAllQuestions();
}

package com.micro.question.service.service;

import java.util.List;

import com.micro.question.service.dto.QuestionResponse;

public interface QuestionService {

    public List<QuestionResponse> findAllQuestions();
}

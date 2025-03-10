package com.micro.question.service.service.impl;

import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.repository.QuestionRepository;
import com.micro.question.service.service.QuestionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionResponse> findAllQuestions() {
        return questionRepository.findAll().stream().map(question -> {
            return QuestionResponse.builder().id(question.getId())
                    .questionTitle(question.getQuestionTitle())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .option3(question.getOption3())
                    .option4(question.getOption4())
                    .rightAnswer(question.getRightAnswer())
                    .difficultyLevel(question.getDifficultyLevel())
                    .category(question.getCategory()).build();
        }).collect(Collectors.toList());
    }
}

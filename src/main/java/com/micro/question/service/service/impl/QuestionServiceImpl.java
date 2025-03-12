package com.micro.question.service.service.impl;

import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.entity.QuestionEntity;
import com.micro.question.service.repository.QuestionRepository;
import com.micro.question.service.service.QuestionService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionResponse> findAllQuestions() {
    	ModelMapper modelMapper = new ModelMapper();
        return questionRepository.findAll().stream()
        		.map(question -> modelMapper.map(question, QuestionResponse.class))
        		.collect(Collectors.toList());
    }

	@Override
	public List<QuestionResponse> findQuestionsByCategory(String category) {		
		return questionRepository.findByCategory(category);
	}

	@Override
	public String addQuestions(List<QuestionRequest> questionList) {		
		questionRepository.saveAll(questionList.stream()
		.map(question -> 
			QuestionEntity.builder()
			.questionTitle(question.getQuestionTitle())
			.option1(question.getOption1())
	        .option2(question.getOption2())
	        .option3(question.getOption3())
	        .option4(question.getOption4())
	        .rightAnswer(question.getRightAnswer())
	        .difficultyLevel(question.getDifficultyLevel())
	        .category(question.getCategory()).build()	
		).collect(Collectors.toList()));			
		return "Added Successfully";
	}

	@Override
	public String deleteQuestions(List<Integer> idList) {
		questionRepository.deleteAllById(idList);
		return "Deleted Successfully";
	}
}

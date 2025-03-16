package com.micro.question.service.service.impl;

import com.micro.question.service.dto.AnswersRequest;
import com.micro.question.service.dto.QuestionRequest;
import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.dto.QuestionWrapper;
import com.micro.question.service.entity.QuestionEntity;
import com.micro.question.service.exception.NotFoundException;
import com.micro.question.service.repository.QuestionRepository;
import com.micro.question.service.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@PropertySource("classpath:/ValidationMessages.properties")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Environment environment;
    
    ModelMapper modelMapper = new ModelMapper();
    
    public QuestionServiceImpl(QuestionRepository questionRepository, Environment environment) {
        this.questionRepository = questionRepository;
		this.environment = environment;
    }

    @Override
    public List<QuestionResponse> findAllQuestions() {   	
        return questionRepository.findAll().stream()
        		.map(question -> modelMapper.map(question, QuestionResponse.class)).collect(Collectors.toList());
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
	public String deleteQuestions(List<Integer> idList) throws NotFoundException {
		Map<Integer, QuestionEntity> idMap = questionRepository.findAllById(idList).stream().collect
												(Collectors.toMap(QuestionEntity::getId, Function.identity()));
		for(Integer id : idList) {
			if(!idMap.containsKey(id)) {
				throw new NotFoundException(environment.getProperty("question.id.not.found"));
			}
		}
		questionRepository.deleteAllById(idList);
		return "Deleted Successfully";
	}

	@Override
	public List<Integer> getQuestionForQuiz(String category, Integer numOfQuestions) {	
		return questionRepository.findIdByCategory(category, numOfQuestions);
	}

	@Override
	public List<QuestionWrapper> getQuestionsFromId(List<Integer> idList) throws NotFoundException {
		Map<Integer, QuestionEntity> idMap = questionRepository.findAllById(idList).stream().collect
												(Collectors.toMap(QuestionEntity::getId, Function.identity()));
		for(Integer id : idList) {
			if(!idMap.containsKey(id)) {
				throw new NotFoundException(environment.getProperty("question.id.not.found"));
			}
		}
		return idMap.values().stream().map(question -> 
				modelMapper.map(question, QuestionWrapper.class)).collect(Collectors.toList());		
	}

	@Override
	public Integer getScore(List<AnswersRequest> answersRequests) {
		Integer result = 0;
		Map<Integer, QuestionEntity> idMap = questionRepository.findAllById(answersRequests.stream()
				.map(AnswersRequest::getId).collect(Collectors.toList()))
				.stream().collect(Collectors.toMap(QuestionEntity::getId, Function.identity()));
		for(AnswersRequest answerReq : answersRequests) {
			if(idMap.get(answerReq.getId()).getRightAnswer().equalsIgnoreCase(answerReq.getResponse())) {
				result++;
			}
		}
		return result;
	}
}

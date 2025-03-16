package com.micro.question.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.micro.question.service.dto.QuestionResponse;
import com.micro.question.service.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
	
	@Query("select new com.micro.question.service.dto.QuestionResponse("
			+ " q.id, q.questionTitle, q.option1, q.option2, q.option3, q.option4, "
			+ " q.rightAnswer, q.difficultyLevel, q.category) "
			+ " from QuestionEntity q "
			+ " where q.category = :category")
	List<QuestionResponse> findByCategory(@Param("category") String category);
	
	@Query("SELECT q.id FROM QuestionEntity q WHERE q.category = :category ORDER BY Rand() LIMIT :numOfQuestions")
	List<Integer> findIdByCategory(@Param("category") String category, @Param("numOfQuestions") Integer numOfQuestions);
}

package com.micro.question.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class QuestionRequest {
	
	@NotBlank(message = "QuestionTitle {should.not.blank}")
    private String questionTitle;
	
	@NotBlank(message = "Option1 {should.not.blank}")
    private String option1;
	
	@NotBlank(message = "Option2 {should.not.blank}")
    private String option2;
	
	@NotBlank(message = "Option3 {should.not.blank}")
    private String option3;
	
	@NotBlank(message = "Option4 {should.not.blank}")
    private String option4;
	
	@NotBlank(message = "RightAnswer {should.not.blank}")
    private String rightAnswer;
	
	@NotBlank(message = "DifficultyLevel {should.not.blank}")
    private String difficultyLevel;
	
	@NotBlank(message = "Category {should.not.blank}")
    private String category;

}

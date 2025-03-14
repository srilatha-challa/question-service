package com.micro.question.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
	
	@NotBlank(message = "QuestionTitle is required")
    private String questionTitle;
	
	@NotBlank(message = "Option1 is required")
    private String option1;
	
	@NotBlank(message = "Option2 is required")
    private String option2;
	
	@NotBlank(message = "Option3 is required")
    private String option3;
	
	@NotBlank(message = "Option4 is required")
    private String option4;
	
	@NotBlank(message = "RightAnswer is required")
    private String rightAnswer;
	
	@NotBlank(message = "DifficultyLevel is required")
    private String difficultyLevel;
	
	@NotBlank(message = "Category is required")
    private String category;

}

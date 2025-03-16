package com.micro.question.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersRequest {
	
	@NotNull(message = "Id {should.not.null}")
	private Integer id;
	private String response;

}

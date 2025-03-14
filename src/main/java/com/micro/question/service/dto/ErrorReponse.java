package com.micro.question.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorReponse {
	
	private String errorMessage;
	private Integer errorCode;
	private List<String> errorMessages;
	
	public LocalDateTime getTimestamp() {
	   return LocalDateTime.now();
	}

}

package com.micro.question.service.exception;

public class BadRequestException extends Exception{

	private static final long serialVersionUID = 1896075189407816093L;
	
	public BadRequestException(String message) {
		super(message);
	}

}

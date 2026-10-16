package com.kishan.exception;

public class JobApplicationNotFoundException extends RuntimeException {

   
	private static final long serialVersionUID = 1L;

	public JobApplicationNotFoundException(String message) {
        super(message);
    }
}

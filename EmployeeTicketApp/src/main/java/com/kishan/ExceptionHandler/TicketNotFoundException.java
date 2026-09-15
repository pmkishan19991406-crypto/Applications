package com.kishan.ExceptionHandler;

public class TicketNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketNotFoundException(String message) {
        super(message);
    }
}
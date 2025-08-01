package com.kaba4cow.pastex.common.exception;

public class ExpirationParseException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public ExpirationParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpirationParseException(String message) {
		super(message);
	}

}

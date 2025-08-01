package com.kaba4cow.pastex.domain.paste.service;

public class ExpirationParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExpirationParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpirationParseException(String message) {
		super(message);
	}

}

package com.kaba4cow.pastex.common.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class WrongPasswordException extends BadCredentialsException {

	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message) {
		super(message);
	}

}

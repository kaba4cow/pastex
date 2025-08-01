package com.kaba4cow.pastex.common.exception;

import org.springframework.security.access.AccessDeniedException;

public class WrongPastePasswordException extends AccessDeniedException {

	private static final long serialVersionUID = 1L;

	public WrongPastePasswordException(String message) {
		super(message);
	}

}

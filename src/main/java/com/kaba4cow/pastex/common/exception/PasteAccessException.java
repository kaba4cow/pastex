package com.kaba4cow.pastex.common.exception;

import org.springframework.security.access.AccessDeniedException;

public class PasteAccessException extends AccessDeniedException {

	private static final long serialVersionUID = 1L;

	public PasteAccessException(String message) {
		super(message);
	}

}

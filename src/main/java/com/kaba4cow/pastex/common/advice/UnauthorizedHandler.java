package com.kaba4cow.pastex.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kaba4cow.pastex.common.advice.response.ExceptionHandlerResponseEntity;

@ControllerAdvice
public class UnauthorizedHandler extends AbstractExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	public ExceptionHandlerResponseEntity handleAuthentication(AuthenticationException exception) {
		return defaultResponse(HttpStatus.UNAUTHORIZED, exception);
	}

}

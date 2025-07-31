package com.kaba4cow.pastex.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kaba4cow.pastex.common.advice.response.ExceptionHandlerResponseEntity;
import com.kaba4cow.pastex.common.exception.NotFoundException;

@ControllerAdvice
public class NotFoundHandler extends AbstractExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ExceptionHandlerResponseEntity handleNotFound(NotFoundException exception) {
		return defaultResponse(HttpStatus.NOT_FOUND, exception);
	}

}

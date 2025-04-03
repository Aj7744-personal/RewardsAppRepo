package com.example.reward.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling application-wide exceptions.
 * 
 * This class provides centralized exception handling for specific exceptions
 * thrown within the application. It ensures that appropriate HTTP responses are
 * returned when exceptions occur.
 * 
 * @author Your Name
 * @version 1.0
 * @since 2025-04-03
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles {@link RewardCalculationException} and returns a meaningful error
	 * message.
	 * 
	 * When a {@link RewardCalculationException} occurs, this method catches it and
	 * responds with an HTTP 400 (Bad Request) status code, along with the exception
	 * message.
	 * 
	 * @param ex The caught {@link RewardCalculationException}.
	 * @return A string message describing the error.
	 */

	@ExceptionHandler(RewardCalculationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleRewardException(RewardCalculationException ex) {
		return ex.getMessage();
	}

}

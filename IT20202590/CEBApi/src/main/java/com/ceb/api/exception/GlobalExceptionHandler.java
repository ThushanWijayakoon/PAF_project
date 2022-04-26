package com.ceb.api.exception;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceb.api.dto.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, List<String>> body = new HashMap<>();

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorMessage> sqlExceptionHandler(SQLException ex, HttpServletRequest request) {

		return new ResponseEntity<ErrorMessage>(new ErrorMessage(ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), new Date().toString(), "Internel Server Error Exception"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.toString(),
				new Date().toString(), "Global Exception"), HttpStatus.NOT_FOUND);

	}
}

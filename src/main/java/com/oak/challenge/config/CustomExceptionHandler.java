package com.oak.challenge.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotAcceptable;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oak.challenge.exception.InputNotFoudException;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Custom {@link ResponseEntityExceptionHandler}
 * 
 * @author anderson
 *
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Catch the {@link  InputNotFoudException}
	 * 
	 * @return {@link NotAcceptable} http status
	 */
	@ExceptionHandler(InputNotFoudException.class)
	public ResponseEntity<Object> exception(InputNotFoudException ex, WebRequest request) {
		String[] arr = new String[]{String.valueOf(ex.getId()), ex.getWhichDiff().toString()};
		String userMessage = messageSource.getMessage("input.not-found", arr,
				LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, new Error(userMessage), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String devMessage = ex.getCause().toString();
		return handleExceptionInternal(ex, new Error(userMessage, devMessage), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = listBindResultErrors(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = ex.getSupportedMediaTypes().stream().map(mt -> mt.toString()).collect(Collectors.toList());
		String[] arr = new String[]{String.valueOf(StringUtils.join(list).toString())};
		String userMessage = messageSource.getMessage("media.type-not.supported.specifically", arr, LocaleContextHolder.getLocale());
		String devMessage = ExceptionUtils.getStackTrace(ex);
		
		return handleExceptionInternal(ex, new Error(userMessage, devMessage), headers, status, request);
	}

	private List<Error> listBindResultErrors(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String devMessage = fieldError.toString();
			errors.add(new Error(StringUtils.capitalize(userMessage), devMessage));
		}

		return errors;
	}

	/**
	 * provides a user "friendly" error
	 * 
	 * @author anderson
	 *
	 */
	@AllArgsConstructor
	static class Error {

		@Getter
		private String userMessage;
		@Getter
		private String devMessage;

		public Error(String userMessage) {
		}

	}
}

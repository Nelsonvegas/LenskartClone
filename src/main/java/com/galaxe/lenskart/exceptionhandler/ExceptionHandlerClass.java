package com.galaxe.lenskart.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<String> duplicateEmailException(DuplicateKeyException ex,Throwable e) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BadEmailPassException.class)
	public ResponseEntity<String> badCredentialsException(BadEmailPassException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalException(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

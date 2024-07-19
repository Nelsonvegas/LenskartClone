package com.galaxe.lenskart.exceptionhandler;

public class BadEmailPassException extends RuntimeException{

	public BadEmailPassException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadEmailPassException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BadEmailPassException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadEmailPassException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadEmailPassException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

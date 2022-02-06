package com.ivanmoreno.user.common;

import lombok.Data;

@Data
public class InvalidUserException extends Exception{

	private String message;
	private Object[] args;
	
	public InvalidUserException(String name) {
		this.message = name + "is an invalid user";
	}
	
	public InvalidUserException(Object[] args) {
		this.args = args;
	}
	
	public InvalidUserException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	private static final long serialVersionUID = 1L;
}

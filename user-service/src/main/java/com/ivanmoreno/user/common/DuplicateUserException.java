package com.ivanmoreno.user.common;

import lombok.Data;

@Data
public class DuplicateUserException extends Exception{

	private String message;
	private Object[] args;
	
	public DuplicateUserException(String name) {
		this.message = "There is already a user with the name - " + name;
	}
	
	public DuplicateUserException(Object[] args) {
		this.args = args;
	}
	
	public DuplicateUserException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	private static final long serialVersionUID = 1L;
}

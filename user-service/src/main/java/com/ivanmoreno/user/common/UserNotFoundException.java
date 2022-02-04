package com.ivanmoreno.user.common;

import lombok.Data;

@Data
public class UserNotFoundException extends Exception{

	private String message;
	private Object[] args;
	
	public UserNotFoundException(String name) {
		this.message = "User [" + name + "] is not found";
	}
	
	public UserNotFoundException(Object[] args) {
		this.args = args;
	}
	
	public UserNotFoundException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	private static final long serialVersionUID = 1L;
	
}

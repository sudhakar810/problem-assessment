package com.password.validator;
// Class for user-defined InvalidPasswordException
public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String conditionViolated)
	{
		
		super("Invalid Password: " + conditionViolated);
	}
}
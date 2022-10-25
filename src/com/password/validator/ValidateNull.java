package com.password.validator;

import java.util.concurrent.Callable;

public class ValidateNull implements Callable<Integer> {
	
	private String password;
	public ValidateNull(String password) {
      this.password = password;
	}

	@Override
	public Integer call() throws InvalidPasswordException {
		// TODO Auto-generated method stub
		// to check space
				if (password==null) {
					throw new InvalidPasswordException("Password should not be null");
				}
			return 1;
	}

}

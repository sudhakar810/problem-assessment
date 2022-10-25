package com.password.validator;

import java.util.concurrent.Callable;

public class ValidateLength implements Callable<Integer> {
	
	private String password;
	public ValidateLength(String password) {
      this.password = password;
	}

	@Override
	public Integer call() throws InvalidPasswordException {
		// TODO Auto-generated method stub
		if (password!=null && !((password.length() >= 8))) {
			throw new InvalidPasswordException("Password should be larger than 8 chars");
		}else {
			return 1;
		}
	}

}

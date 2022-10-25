package com.password.validator;

import java.util.concurrent.Callable;

public class ValidateLowerCase implements Callable<Integer> {
	
	private String password;
	public ValidateLowerCase(String password) {
      this.password = password;
	}

	@Override
	public Integer call() throws InvalidPasswordException {
		int count = 0;
		// checking small letters
		for (int i = 90; i <= 122; i++) {
			// type casting
			char c = (char)i;
			String str1 = Character.toString(c);
			if (password!=null && password.contains(str1)) {
				count = 1;
			}
		}
		if (count == 0) {
			throw new InvalidPasswordException("Password should have one lowercase letter at least");
		}
		return -1;
	}

}

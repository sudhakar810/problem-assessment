package com.password.validator;

import java.util.concurrent.Callable;

public class ValidateUpperCase implements Callable<Integer> {
	
	private String password;
	public ValidateUpperCase(String password) {
      this.password = password;
	}

	@Override
	public Integer call() throws InvalidPasswordException {
		int count = 1;
		// checking capital letters
		for (int i = 65; i <= 90; i++) {
			// type casting
			char c = (char)i;
			String str1 = Character.toString(c);
			if (password!=null && password.contains(str1)) {
				count = 1;
			}
		}
		if (count == 0) {
			throw new InvalidPasswordException("Password should have one uppercase letter at least");
		}
		return count;
	}

}

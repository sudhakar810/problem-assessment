package com.password.validator;

import java.util.concurrent.Callable;

public class ValidateNumber implements Callable<Integer> {
	
	private String password;
	public ValidateNumber(String password) {
      this.password = password;
	}

	@Override
	public Integer call() throws Exception {
		int count = 0;
		// check digits from 0 to 9
		for (int i = 0; i <= 9; i++) {
			// to convert int to string
			String str1 = Integer.toString(i);
			if (password!=null && password.contains(str1)) {
				count = 1;
			}
		}
		if (count == 0) {
			count = 0;
			throw new InvalidPasswordException("Password should have one number at least");
		}
		return count;
	}

}

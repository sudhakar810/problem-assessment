package com.password.validator;
// Java Program to check the validity
// of a Password using User-Defined Exception

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Driver Class
public class PasswordValidator {

	// A utility function to check
	// whether a password is valid or not
	static int validCount = 0;
	public static boolean isValid(String password) throws Exception {
		
		ExecutorService executor = Executors.newFixedThreadPool(6);
		
		// for checking if password length
		// is between 8 and 15
		Callable<Integer> validateLenght = new ValidateLength(password);
		Callable<Integer> validateNull = new ValidateNull(password);
		Callable<Integer> validateUpperCase = new ValidateUpperCase(password);
		Callable<Integer> validateLowerCase = new ValidateLowerCase(password);
		Callable<Integer> validateNumber = new ValidateNumber(password);
		List<Integer> results = new ArrayList<>();
		List<Future<Integer>> futures;
		try {
			futures = executor.invokeAll(Arrays.asList(validateLenght,validateNull,validateUpperCase,validateLowerCase,validateNumber));
			 
			for (Future<Integer> future: futures) 
			{
				try {
					   Integer count = future.get();
					   if(count==-1) break;
					   if(count==1)
						   results.add(count);
					} catch (Exception ex) {
						throw ex;
					}
			}
			
		} catch (InterruptedException e) {
			throw e;
		} 
		
		return results.size()>=3;
		// The password is valid
	}
}

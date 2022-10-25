package com.password.validation.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import com.password.validator.InvalidPasswordException;
import com.password.validator.PasswordValidator;

import junit.framework.Assert; 
public class Validate {
	
	PasswordValidator passwordValidator;
	
	
	 @Before
     public void beforeEachTestMethod() {
		 passwordValidator = new PasswordValidator();
		 
	 }
	
	@Test
	 public final void validate_password_greater_than_8() throws InterruptedException, ExecutionException {
		String password1 = "Test";
		try {
			 System.out.println("Password isValid"
                     + password1 + " valid?");
			 if(passwordValidator.isValid(password1))
	        	 System.out.println("Valid Password");
        }
        catch (InvalidPasswordException e) {
            System.out.print(e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 System.out.print(e.getMessage());
		}
    }
	
	@Test
	 public final void validate_password_Null_Check() throws InterruptedException, ExecutionException {
		String password1 = null;
		try {
			 System.out.println("Password isValid"
                     + password1 + " valid?");
			 if(passwordValidator.isValid(password1))
	        	 System.out.println("Valid Password");
       }
       catch (InvalidPasswordException e) {
           System.out.print(e.getMessage());
       } catch (Exception e) {
		// TODO Auto-generated catch block
    	   System.out.print(e.getMessage());
	}
   }
	
	@Test
	 public final void validate_password_upper_case() throws InterruptedException, ExecutionException {
		String password1 = "test123wer";
		try {
          System.out.println("Password isValid"
                             + password1 + " valid?");
          if(passwordValidator.isValid(password1))
         	 System.out.println("Valid Password");
      }
      catch (InvalidPasswordException e) {
          System.out.print(e.getMessage());
      } catch (Exception e) {
		// TODO Auto-generated catch block
    	  System.out.print(e.getMessage());
	}
  }
	
	@Test
	 public final void validate_password_lower_case() throws InterruptedException, ExecutionException {
		String password1 = "TEUT123WERTT";
		try {
         System.out.println("Password isValid"
                            + password1 + " valid?");
         if(passwordValidator.isValid(password1))
        	 System.out.println("Valid Password");
     }
     catch (InvalidPasswordException e) {
         System.out.print(e.getMessage());
     } catch (Exception e) {
		// TODO Auto-generated catch block
    	 System.out.print(e.getMessage());
	}
 }
	
	@Test
	 public final void validate_password_at_least_one_number() throws InterruptedException, ExecutionException {
		String password1 = "tetorRRTorw";
		try {
        System.out.println("Password isValid"
                           + password1 + " valid?");
        if(passwordValidator.isValid(password1))
       	 System.out.println("Valid Password");
    }
    catch (InvalidPasswordException e) {
        System.out.print(e.getMessage());
        //System.out.println(e.printMessage());
    } catch (Exception e) {
		// TODO Auto-generated catch block
    	 System.out.print(e.getMessage());
	}
}
		

		@Test
		 public final void validate_password() throws InterruptedException, ExecutionException {
			String password = "Teutororwertt123";
			try {
	        System.out.println("Password isValid"
	                           + password + " valid?");
	        Assert.assertEquals(passwordValidator.isValid(password), true);
	       	System.out.println("Valid Password");
	    }
	    catch (InvalidPasswordException e) {
	        System.out.print(e.getMessage());
	    } catch (Exception e) {
			// TODO Auto-generated catch block
	    	 System.out.print(e.getMessage());
		}
	
	}


}

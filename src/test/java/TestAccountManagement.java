

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import team8.social.Account;
import team8.social.Database;

public class TestAccountManagement {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.hardReset();
		Account.createAccount("schoi", "1234pass", "1997-11-22", "Sherman", "Choi",
						"Who is your favorite actor, musician, or artist?", "What high school did you attend?",
						"What is your favorite movie?", "Pendulum", "SME", "Toy Story");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Database.hardReset();
	}

	@Test
	public void testValidLogin() {
		//This should be a valid login
		assertNotNull("Valid Account Login Failed",Account.login("schoi", "1234pass"));
	}
	
	@Test
	public void testIncorrectPasswordLogin() {
		//This should NOT be a valid login due to password being incorrect
		assertNull("Invalid Account Login Allowed (password is incorrect)",Account.login("schoi", "this is not the right password"));
	}
	
	@Test
	public void testUsernameDoesNotExistLogin() {
		//This should NOT be allowed
		assertNull("Invalid Account Login Allowed (username does not exist)",Account.login("ishouldnotexist", "dictionaryAttacked"));
	}
	
	@Test
	public void testChangePasswordThenLogin(){
		//Successfully change password of schoi
		Account.changePassword("schoi", "1234pass", "1234567890Password");
		assertNotNull("Valid Account Login Failed (password change malfunctioned)",Account.login("schoi", "1234567890Password"));
	}
	
	@Test
	public void testChangePasswordFailure(){
		//Attempt (but fail) to change the password of schoi
		Account.changePassword("schoi", "1234pass", "newPassword");
		assertNull("Invalid Account Login Allowed (password change malfunctioned)",Account.login("schoi", "newPassword"));
	}
	
	@Test
	public void testAnswerSecurityQuestionsCorrectly(){
		//This should be true
		assertTrue("Security question answers falsely denied",Account.securityQuestionCheck("schoi", "Pendulum", "SME", "Toy Story"));
	}
	
	@Test
	public void testAnswerSecurityQuestionsIncorrectly(){
		//This should be false
		assertFalse("Security question answers falsely accepted",Account.securityQuestionCheck("schoi", "SomethingBad", "OverTheRainbow", "Something"));
	}
	
	@Test
	public void testForceChangePassword(){
		//Forcefully change the password of an account.
		Account.changePassword("schoi", "newPassword");
		//This should be a valid input now.
		assertNotNull("Invalid Account Login Allowed (password change malfunctioned)",Account.login("schoi", "newPassword"));
	}
}
package com.amazon.automateTest;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pom.CheckSignIn;

public class checkSignInTest {
	CheckSignIn pom;
	
	@BeforeClass
	public void initPOM() {
		pom = new CheckSignIn();
		pom.navigateToSignInPage();
	}
	@AfterClass
	public void closeBrowser() {
		pom.closeBrowser();
	}
	@Test
	public void checkSignInText() {
		Assert.assertTrue(pom.validateSignIn());
	}
	@Test
	public void checkEmailOrPhone() {
		Assert.assertTrue(pom.validateEmailText());
	}
	@Test
	public void checkContinue() {
		Assert.assertTrue(pom.validateContinueButton());
	}
	@Test
	public void checkAgreement() {
		Assert.assertTrue(pom.validateAgreement());
	}
	@Test
	public void checkCreateNew() {
		Assert.assertTrue(pom.validateCreateNew());
		
	}
}

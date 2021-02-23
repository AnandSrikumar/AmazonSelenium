package com.amazon.automateTest;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pom.CheckCreateNew;

public class CheckCreateNewTest {
	CheckCreateNew pom;
	
	@BeforeClass
	public void initPom() {
		pom = new CheckCreateNew();
		pom.navigateToCreateNew();
	}
	@AfterClass
	public void closeBrowser() {
		pom.closeBrowser();
	}
	@Test
	public void checkLabels() {
		Assert.assertTrue(pom.validateHeading());
		Assert.assertTrue(pom.validateMobile());
		Assert.assertTrue(pom.validateEmail());
		Assert.assertTrue(pom.validatePassword());
		
	}
	@Test
	public void checkPasswordSug() {
		Assert.assertTrue(pom.validatePasswordReq());
	}
	@Test
	public void validateTexts() {
		Assert.assertTrue(pom.validateInfo());
		Assert.assertTrue(pom.validateAlreadyCustomer());
		Assert.assertTrue(pom.validateContinueButton());
		Assert.assertTrue(pom.validateSignIn());
	}
}

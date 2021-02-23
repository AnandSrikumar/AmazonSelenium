package com.amazon.automateTest;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pom.SignInAction;

public class SignInActionTest {
	SignInAction pom;
	
	@BeforeClass
	public void initPom() {
		pom = new SignInAction();
		pom.navigateToSignIn();
	}
	@AfterClass
	public void closeBrowser() {
		pom.closeBrowser();
	}
	
	@Test
	public void loginUsers() {
		Assert.assertTrue(pom.loginWithUserDetails());
	}
	
}

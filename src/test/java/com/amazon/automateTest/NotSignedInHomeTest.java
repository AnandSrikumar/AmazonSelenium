package com.amazon.automateTest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pom.NotSignedInHome;

public class NotSignedInHomeTest {
	NotSignedInHome pom;
	@BeforeClass
	public void initPOM() {
		pom = new NotSignedInHome();
		
	}
	@AfterClass
	public void closeBrowser() {
		pom.closeBrowser();
	}
	@Test
	public void testLogo() {
		Assert.assertTrue(pom.validateLogoText());
	}
	@Test
	public void testAddress() {
		Assert.assertTrue(pom.validateAddress());
	}
	@Test
	public void testSignin() {
		Assert.assertTrue(pom.validateSignIn());
	}
	@Test
	public void testSignInButton() {
		Assert.assertTrue(pom.validateSignInButtons());
	}
	@Test
	public void testCartCount() {
		Assert.assertTrue(pom.checkCart());
	}
}

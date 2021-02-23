package com.amazon.pom;

import org.openqa.selenium.By;

public class CheckSignIn extends POMMaster{
	private By signInTex = By.xpath("//h1");
	private By emailOrPhone = By.xpath("//input[@type='email']/ancestor::div[1]/label");
	private By continueButton = By.xpath("//input[@type='submit']/following-sibling::span");
	private By agreement = By.xpath("//input[@type='submit']/ancestor::div[1]/div[1]");
	private By createNew = By.xpath("//a[contains(text(),'Create you')]");
	private By signInPage = By.xpath("//a[contains(@href,'signin')]");
	private String pomName = "checkSignIn";
	public CheckSignIn() {
		super();
	}
	public void navigateToSignInPage() {
		driver.findElement(signInPage).click();
		waiting.explicitWait(10, continueButton, 2);
	}
	public boolean validateSignIn() {
		truthVal = compareTwoElementStrings(signInTex, pomName, "signIn");
		return truthVal;
	}
	
	public boolean validateEmailText() {
		truthVal = compareTwoElementStrings(emailOrPhone, pomName, "emailOrPhone");
		return truthVal;
	}
	public boolean validateContinueButton() {
		truthVal = compareTwoElementStrings(continueButton, pomName, "continue");
		return truthVal;
	}
	public boolean validateAgreement() {
		truthVal = compareTwoElementStrings(agreement, pomName, "agreement");
		return truthVal;
	}
	public boolean validateCreateNew() {
		truthVal = compareTwoElementStrings(createNew, pomName, "createNew");
		return truthVal;
	}
}

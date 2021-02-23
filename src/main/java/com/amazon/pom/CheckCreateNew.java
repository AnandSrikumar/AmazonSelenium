package com.amazon.pom;

import org.openqa.selenium.By;

public class CheckCreateNew extends POMMaster{
	public CheckCreateNew() {
		super();
	}
	private By labels = By.xpath("//label");
	private By heading = By.xpath("//h1");
	private  By mobileNo = By.xpath("//span[contains(@class,'dropdown-pro')]");
	private By passwordReq = By.xpath("//div[contains(text(),'at least')]");
	private By info = By.xpath("//div[contains(text(),'verify your')]");
	private By info2 = By.xpath("//div[contains(text(),'may apply')]");
	private By continueButton = By.xpath("//input[@type='submit']/following-sibling::span");
	private By alreadyCustomer = By.xpath("//div[contains(text(),'Already have')]");
	private By signIn = By.xpath("//a[contains(@href,'signin')]");
	private By createNew = By.xpath("//a[contains(text(),'Create your')]");
	private String pomName = "checkCreateNew";
	
	public void navigateToCreateNew() {
		driver.findElement(signIn).click();
		waiting.explicitWait(10, createNew, 2);
		driver.findElement(createNew).click();
	}
	public boolean validateHeading() {
		truthVal = compareTwoElementStrings(heading, pomName, "createAccount");
		return truthVal;
	}
	public boolean validateName() {
		element = driver.findElements(labels).get(0);
		truthVal = compareTwoElementStrings(element, pomName, "yourName");
		return truthVal;
	}
	public boolean validateMobile() {
		String url = driver.getCurrentUrl();
		int stIn =  url.indexOf("www")+5;
		url = url.substring(url.indexOf(".", stIn)+1, url.indexOf("/", stIn));
		
		url = url.toUpperCase();
		element = driver.findElement(mobileNo);
		s1 = element.getText();
		element = driver.findElements(labels).get(1);
		logger.info(s1+"=="+url+"=="+s1.startsWith(url));
		truthVal = s1.startsWith(url) && compareTwoElementStrings(element, pomName, "mobileNo");
		return truthVal;
	}
	public boolean validateEmail() {
		element = driver.findElements(labels).get(2);
		truthVal = compareTwoElementStrings(element, pomName, "email");
		return truthVal;
	}
	public boolean validatePassword() {
		element = driver.findElements(labels).get(3);
		truthVal = compareTwoElementStrings(element, pomName, "password");
		return truthVal;
	}
	public boolean validatePasswordReq() {
		truthVal = compareTwoElementStrings(passwordReq, pomName, "suggest");
		return truthVal;
	}
	public boolean validateInfo() {
		truthVal = compareTwoElementStrings(info, pomName, "info")
				&& compareTwoElementStrings(info2, pomName, "info2");
		return truthVal;
	}
	public boolean validateAlreadyCustomer() {
		truthVal = compareTwoElementStrings(alreadyCustomer, pomName, "alreadyCustomer");
		return truthVal;
	}
	public boolean validateSignIn() {
		truthVal = compareTwoElementStrings(signIn, pomName, "signIn");
		return truthVal;
	}
	public boolean validateContinueButton() {
		truthVal = compareTwoElementStrings(continueButton, pomName, "continue");
		return truthVal;
	}
}

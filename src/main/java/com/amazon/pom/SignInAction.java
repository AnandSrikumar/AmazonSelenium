package com.amazon.pom;

import org.openqa.selenium.By;


public class SignInAction extends POMMaster{
	public SignInAction() {
		super();
	}
	private By signIn = By.xpath("//a[contains(@href,'signin')]");
	private By continueButton = By.xpath("//input[@type='submit']");
	private By inputSignIn = By.xpath("//input[@type='email']");
	private By password = By.xpath("//input[@type='password']");
	private By deliverTo = By.xpath("//span[contains(text(),'Deliver to')]");
	private By address = By.xpath("//span[contains(text(),'Deliver to')]/following-sibling::span");
	private By hello = By.xpath("//span[contains(text(),'Hello,')]");
	private By cartCount = By.xpath("//div[contains(@id,'cart-count')]/span");
	private By signOut = By.xpath("//span[contains(text(),'Sign Out')]");
	private By fakeD = By.xpath("//h4");
	private final String pomName = "signInAction";
	private String[][] userData;
	
	public void navigateToSignIn() {
		driver.findElement(signIn).click();
		waiting.explicitWait(10, continueButton, 2);
	}
	
	public boolean loginWithUserDetails() {
		userData = userLogin.getUserData();
		boolean res[] = new boolean[userData.length];
		int in =0;
		for(String[] row: userData) {
			if(row.length == 0) continue;
			enterData(inputSignIn, row[1]);
			clickContinue(continueButton);
			waiting.explicitWait(10, continueButton, 2);
			enterData(password, row[2]);
			clickContinue(continueButton);
			res[in] = checkGui(row);
			in++;
			try {
				element = driver.findElement(hello);
				moveToElement(element);
				driver.findElement(signOut).click();
			}catch(Exception e) {
				
			}
		}
		for(boolean r: res) {
			if(!r) return r;
		}
		return true;
	}
	
	private boolean checkGui(String[] row) {
		logger.info(row[5]+".....");
		if(row[5].equalsIgnoreCase("fake")) return fakeData();
		waiting.explicitWait(10, address, 2);
		element = driver.findElement(deliverTo);
		s1 = element.getText().trim();
		logger.info(s1+"==>"+row[0]);
		if(!s1.contains(row[0])) return false;
		element = driver.findElement(address);
		s1 = element.getText().toLowerCase().trim();
		logger.info(s1+"==>"+row[4]);
		if(!s1.contains(row[4].toLowerCase())) return false;
		s1 = driver.findElement(hello).getText().trim();
		logger.info(s1+"==>"+row[0]);
		if(!s1.contains(row[0])) return false;
		s1 = driver.findElement(cartCount).getText().trim();
		logger.info(s1+"==>"+row[3]);
		if(!s1.equals(row[3])) return false;
		return true;
	}
	
	private boolean fakeData() {
		s1 = driver.findElement(fakeD).getText();
		return s1.contains(prop.getUiExpected(pomName, "incorrect")) 
				|| s1.contains(prop.getUiExpected(pomName, "there"));
	}
	
	private void enterData(By loc, String data) {
		try {
			element = driver.findElement(loc);
			element.clear();
			element.sendKeys(data);
		}catch(Exception e) {
			
		}
	}
	private void clickContinue(By loc) {
		driver.findElement(loc).click();
	}
	/*
	 * public static void main(String[] args) { SignInAction ac = new
	 * SignInAction(); ac.navigateToSignIn(); ac.loginWithUserDetails(); }
	 */
	
}

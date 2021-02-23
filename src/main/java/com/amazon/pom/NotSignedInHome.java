package com.amazon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NotSignedInHome extends POMMaster {

	private By logoTxt = By.xpath("//a[contains(@class,'logo')]/span[3]");
	private By addressText = By.xpath("//div[contains(@id,'location')]/span/a/div[2]/span[2]");
	private By signInText = By.xpath("//a[contains(@href,'signin')]");
	private By signInButton = By.xpath("//a[@class='action-button' and contains(@href,'signin')]/span[contains(text(),'Sign in')]");
	private By cartCount = By.xpath("//div[contains(@id,'cart-count')]/span[1]");
	private final String pomName="notSignedInHome";
	public NotSignedInHome() {
		super();
	}
	
	
	public boolean validateLogoText() {
		
		truthVal = compareTwoElementStrings(logoTxt, pomName, "logo");
		
		return truthVal;
		
	}
	public boolean validateAddress() {
		truthVal = compareTwoElementStrings(addressText, pomName, "address");
		return truthVal;
	}
	public boolean validateSignIn() {
		elements = driver.findElements(signInText);
		element = elements.get(0);
		element = element.findElement(By.xpath("div/span"));
		s1 = element.getText();
		element = elements.get(0);
		element = element.findElement(By.xpath("span[2]"));
		s2 = element.getAttribute("innerHTML");
		pomS = prop.getUiExpected(pomName, "helloSign");
		String pomS2 = prop.getUiExpected(pomName, "account");
		s2 = s2.substring(0,s2.indexOf("<span")).trim();
		driver.close();
		truthVal = s1.trim().equals(pomS) && s2.equals(pomS2);
		printTruthVal(truthVal, s1, pomS, pomName+".validateSignIn");
		return truthVal;
	}
	public boolean validateSignInButtons() {
		waiting.implicitWait(2);
		scroll.scrollYEnd(0);
		scroll.scrollY("-100");
		waiting.explicitWait(10, signInButton, 0);
		elements = driver.findElements(signInButton);
		element = elements.get(elements.size()-1);
		s1 = element.getText();
		pomS = prop.getUiExpected(pomName, "signIn");
		truthVal = s1.trim().equals(pomS);
		printTruthVal(truthVal, s1, pomS,pomName+".SignInButton");
		return truthVal;
	}
	public boolean checkCart() {
		scroll.scrollYEnd(1);
		compareTwoElementStrings(cartCount, pomName, "cartCunt");
		return truthVal;
	}
	
}

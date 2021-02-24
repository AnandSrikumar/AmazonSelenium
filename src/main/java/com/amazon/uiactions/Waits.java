package com.amazon.uiactions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
WebDriver driver;
public Waits(WebDriver driver) {
	this.driver = driver;
}
public void implicitWait(long time) {
	driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
}
public WebElement explicitWait(long time, By locator,int condition) {
	 WebElement el = null;
	 WebDriverWait wait = new WebDriverWait(driver, time);
	 switch(condition) {
	 case 0:
		 el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 break;
	 case 1:
		 el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 
		 break;
	 case 2:
		 el = wait.until(ExpectedConditions.elementToBeClickable(locator));
		 break;
	 default:
		 el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 
	 }
	 return el;
}

}

package com.amazon.uiactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {
	private WebDriver driver;
	private Actions builder;
	
	public MouseActions(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
	}
	
	public void moveToElement(WebElement loc) {
		Action move = builder.moveToElement(loc).build();
		move.perform();
	}
	
}

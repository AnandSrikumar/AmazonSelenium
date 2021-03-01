package com.amazon.uiactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectAction {
	private WebDriver driver;
	
	public SelectAction(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectByVisibleText(WebElement element, String text, int cond) {
		Select select = new Select(element);
		if(cond == 0) {
			select.selectByVisibleText(text);
		}
		else {
			select.deselectByVisibleText(text);
		}
		
	}
	public void selectByIndex(WebElement element, int num, int cond) {
		Select select = new Select(element);
		if(cond == 0) {
			select.selectByIndex(num);
		}
		else {
			select.deselectByIndex(num);
		}
		
	}

}

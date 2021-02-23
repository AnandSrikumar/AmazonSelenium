package com.amazon.uiactions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {
	private WebDriver driver;
	private JavascriptExecutor executor;
	
	public Scrolling(WebDriver driver, JavascriptExecutor executor) {
		this.driver = driver;
		this.executor = executor;
	}
	
	public void scrollYEnd(int dir) {
		if(dir == 0)
			executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		else
			executor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		
	}
	public void scrollY(String offset) {
		
		
		executor.executeScript("window.scrollBy(0,"+offset+");");
	}
	public void scrollTillElement(By elm, long time) {
		long start = System.currentTimeMillis();
		while(true) {
		try {
			WebElement el = driver.findElement(elm);
			break;
		}
		catch(Exception e) {
			driver.findElement(By.xpath("//html")).sendKeys(Keys.PAGE_DOWN);
			long end = System.currentTimeMillis();
			if(end-start > time)
				break;
		}
	}
	}
	public void scrollXEnd() {
		
	}
	public void scrollX(String offset) {
		
	}
}

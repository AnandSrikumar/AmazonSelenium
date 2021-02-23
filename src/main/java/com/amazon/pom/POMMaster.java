package com.amazon.pom;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.amazon.config.Properties;
import com.amazon.config.UserConfiguration;
import com.amazon.uiactions.Scrolling;
import com.amazon.uiactions.Waits;

public class POMMaster {
	WebDriver driver;
	Properties prop;
	String url;
	static Logger logger = LogManager.getLogger(POMMaster.class);
	UserConfiguration userLogin;
	WebElement element;
	List<WebElement> elements;
	JavascriptExecutor executor;
	Scrolling scroll;
	Waits waiting;
	String s1, s2, pomS;
	boolean truthVal;
	Actions builder;
	
	public POMMaster() {
		prop = new Properties();
		System.setProperty(prop.getDriverUrl(), prop.getDriver());
		getBrowser();
		executor = (JavascriptExecutor) driver;
		url = prop.getUrl();
		userLogin = new UserConfiguration("userlogin.xlsx");
		BasicConfigurator.configure();
		scroll = new Scrolling(driver, executor);
		waiting =  new Waits(driver);
		builder = new Actions(driver);
		navigateToAmazon();
	}
	
	private void getBrowser() {
		String browser = prop.getBrowser();
		switch(browser) {
		case "chrome": driver= new ChromeDriver();
		}
	}
	public void printTruthVal(boolean truthVal, String s1, String pomS,String tag) {
		if(!truthVal) {
			logger.info(tag+": test Failed, GUI value and expected val are different");
			logger.info(tag+": GUI Val: "+s1+" != Expected: "+pomS);
			
		}
		else {
			logger.info(tag+": test passed, GUI value and expected val are same");
			logger.info(tag+": GUI Val: "+s1+" = Expected: "+pomS);
		}
		logger.info("*****************************************************");
	}
	
	public void closeBrowser() {
		driver.close();
	}
	public boolean compareTwoElementStrings(By loc, String pomName, String pomStr) {
		element = driver.findElement(loc);
		pomS = prop.getUiExpected(pomName, pomStr);
		s1 = element.getText();
		boolean tV =  s1.trim().equals(pomS);
		printTruthVal(tV, s1, pomS, pomName);
		return tV;
	}
	
	public boolean compareTwoElementStrings(WebElement loc, String pomName, String pomStr) {
		pomS = prop.getUiExpected(pomName, pomStr);
		s1 = loc.getText();
		boolean tV =  s1.trim().equals(pomS);
		printTruthVal(tV, s1, pomS, pomName);
		return tV;
	}
	
	public void navigateToAmazon() {
		driver.manage().window().maximize();
		driver.get(url);
		logger.info("We successfully configured properties");
	}
	public void moveToElement(WebElement loc) {
		Action move = builder.moveToElement(loc).build();
		move.perform();
	}
}

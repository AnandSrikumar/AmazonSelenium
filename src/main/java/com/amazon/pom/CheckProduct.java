package com.amazon.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CheckProduct extends POMMaster {
	public CheckProduct() {
		super();
	}
	
	private int status;
	private String[][] data;
	private String pomName = "CheckProducts";
	public CheckProduct(int status) {
		this();
		this.status = status;
		
	}
	
	private By select = By.xpath("//select");
	private By searchAr = By.xpath("//div/input[@type='text']");
	private By prodResRow = By.xpath("//h2");
	private By prodResTitle = By.xpath("a/span");
	private By prodResRating = By.xpath("ancestor::div[1]/following-sibling::div[1]/descendant::span[contains(@aria-label,'out of')]");
			//h2/ancestor::div[1]/following-sibling::div[1]/div/span
	private By prodResPrice = By.xpath("ancestor::div[4]/following-sibling::div/descendant::span[contains(@class,'price-whole')]");
	private By prodPageTitle = By.xpath("//h1/span");
	private By prodPageRating = By.xpath("//span[contains(text(),'out of')]");
	private By prodPagePrice = By.xpath("//span[contains(@id,'priceblock')]");
	
	public void initData() {
		data = userLogin.getUserData(1);	
		if(status == 1) {
			signIn();
		}
	}
	private void signIn() {
		
	}
	
	public boolean productInSearch(int n) {
		boolean[] res = new boolean[data.length];
		int i =0;
		for(String row[]: data) {
			logger.info(row[0]+"..");
			selectBy(select, row[1], 0);
			element = driver.findElement(searchAr);
			element.clear();
			element.sendKeys(row[0]);
			element.sendKeys(Keys.ENTER);
			scroll.scrollY("300");
			try {
				if(n == 0)
					res[i] = checkProdDets(row, 0);
				else res[i] = checkProdDets(row, 1);
			}catch(Exception e) {
				e.printStackTrace();
				res[i] = false;
			}
			if(!res[i]) {
				logger.info(row[0]+" has failed the test");
				return false;
			}
			i++;
			scroll.scrollY("-300");
		}
		
		return true;
	}
	
	private boolean checkProdDets(String[] row, int n)throws Exception {
		waiting.explicitWait(10, prodResRow, 2);
		elements = driver.findElements(prodResRow);
		for(WebElement el: elements) {
			element = el.findElement(prodResTitle);
			s1 = element.getText();
			if(s1.equals(row[0])) {
				if(n ==0) {
					if(!searchDets(el, row)) return false;
				}
				else{
					element.click();
					switchToNewTab(1);
					if(!productPage(row)) {
						driver.close();
						switchToNewTab(0);
						return false;
					}
					driver.close();
					switchToNewTab(0);
				}
				
				truthVal = true;
				break;
			}
		}
		return truthVal;
	}
	
	private void switchToNewTab(int n) {
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(n));
	}
	
	private boolean searchDets(WebElement el, String[] row) {
		
		element = el.findElement(prodResRating);
		s1 = element.getAttribute("aria-label");
		logger.info("found "+row[0]);
		logger.info(row[3]+"==>"+s1);
		if(!s1.contains(row[3])) return false;
		element = el.findElement(prodResPrice);
		s1 = element.getText();
		s1 = s1.replaceAll(",", "").trim();
		logger.info(row[2]+"==>"+s1);
		if(!s1.equals(row[2])) return false;
		
		return true;
	}
	
	public boolean productPage(String[] row) throws Exception{
		waiting.explicitWait(10, prodPageTitle, 0);
		s1 = driver.findElement(prodPageTitle).getText().trim();
		logger.info(s1+"-->"+row[0]);
		if(!s1.equals(row[0])) {
			
			return false;		
			}
		
		
		s1 = driver.findElement(prodPageRating).getAttribute("innerHTML");
		logger.info(s1+"-->"+row[3]);
		if(!s1.contains(row[3])) {
			return false;
		}
		
		s1 = driver.findElement(prodPagePrice).getText().trim();
		s1 = s1.substring(2, s1.indexOf("."));
		s1 = s1.replaceAll(",", "");
		logger.info(s1+"-->"+row[2]);
		if(!s1.equals(row[2])) return false;
		return true;
	}
	public boolean addToCart() {
		
		return truthVal;
	}
	public boolean checkCart() {
		return truthVal;
	}
}

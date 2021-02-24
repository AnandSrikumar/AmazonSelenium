package com.amazon.automateTest;

import org.testng.annotations.BeforeClass;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.amazon.pom.CheckProduct;

public class CheckProductTest {
	CheckProduct pom;
	
	@BeforeClass
	public void init() {
		pom = new CheckProduct(0);
		pom.initData();
	}
	@AfterClass
	public void destroy() {
		pom.closeBrowser();
	}
	@Test(priority = 1)
	public void checkProductInSearch() {
		Assert.assertTrue(pom.productInSearch(0));
	}
	@Test(priority = 3)
	public void checkProductInPage() {
		Assert.assertTrue(pom.productInSearch(1));
	}
}

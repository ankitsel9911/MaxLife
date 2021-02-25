package com.vtiger.qa.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.pages.HomePage;
import com.vtiger.qa.pages.LoginPage;
import com.vtiger.qa.pages.MarketingPage;
import com.vtiger.qa.pages.SalesPage;
import com.vtiger.qa.util.TestUtil;

public class MarketingPageTest extends TestBase{

	MarketingPage marketingPage;
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	String sheet="Marketing";
	
	
	
	public MarketingPageTest() {
		super();
	}
	
	@BeforeMethod 
	public void setUp() {
		initialization();
		testUtil=new TestUtil();
		marketingPage = new MarketingPage();
		loginPage= new LoginPage();
		homePage =loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		marketingPage=homePage.clickOnMarketing();
		
	}
	
	@Test
	public void createLeadsTest() throws InterruptedException {
		marketingPage.markertingOver();
		//marketingPage.c
	}
	
}

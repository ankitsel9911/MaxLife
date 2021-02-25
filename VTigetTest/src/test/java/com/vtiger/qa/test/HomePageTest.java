package com.vtiger.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.pages.HomePage;
import com.vtiger.qa.pages.LoginPage;
import com.vtiger.qa.pages.MarketingPage;
import com.vtiger.qa.pages.SalesPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	SalesPage salesPage;
	MarketingPage marketingPage;
	
	
	@Test
	public void verifyHomePageTitle() {
		String homePagetitle=homePage.getHomePageTitle();
		Assert.assertEquals(homePagetitle, "admin - My Home Page - Home - vtiger CRM 5 - Commercial Open Source CRM");
	}
	
	@Test()
	public void verifySignBtn() {
		boolean flag=homePage.verifySignOutButton();
		Assert.assertTrue(flag);
	}
	
	@Test()
	public void verifySalesLinkTest() {
		salesPage= homePage.clickOnSales();
	}
	
}

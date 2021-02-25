package com.vtiger.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.pages.HomePage;
import com.vtiger.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	


	@Test(priority = 1)
	public void loginPageTitle() {
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "vtiger CRM 5 - Commercial Open Source CRM");
	}

	@Test(priority = 2)
	public void crmImageLogoTest() {
		boolean flag=loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void loginTest() {
		
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	
}

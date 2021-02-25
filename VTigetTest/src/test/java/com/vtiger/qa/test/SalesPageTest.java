package com.vtiger.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.pages.HomePage;
import com.vtiger.qa.pages.LoginPage;
import com.vtiger.qa.pages.SalesPage;
import com.vtiger.qa.util.TestUtil;

public class SalesPageTest extends TestBase {

	SalesPage salesPage;
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	String sheetName="Sales";
	
	public SalesPageTest() {
		super();
	}
	
	@BeforeMethod 
	public void setUp() {
		initialization();
		testUtil=new TestUtil();
		salesPage = new SalesPage();
		loginPage= new LoginPage();
		homePage =loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		salesPage=homePage.clickOnSales();
		
	}
	
	@Test(priority=1)
	public void verifySalesPageLabel() {
		boolean  flag=salesPage.verifySalesLabel();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 2)
	public void selectSalesTest() {
		salesPage.salesOver();
		salesPage.selectSales("Dorothy");
		salesPage.selectSales("Susan");
	} 
	
	@Test(priority = 3)
	public void createLeadsTest() throws InterruptedException {
		salesPage.salesOver();
		salesPage.CreatNewLeads();
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]=testUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void createdNewLeadsTest( String SType, String FirstName, String LastName, String CompanyName, String DesignName) {
		salesPage.salesOver();
		//salesPage.createdNewLeads("Mr.", "Tom", "Petar", "Google", "Software Tester");
	salesPage.createdNewLeads(  SType,  FirstName,  LastName,  CompanyName,  DesignName);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}

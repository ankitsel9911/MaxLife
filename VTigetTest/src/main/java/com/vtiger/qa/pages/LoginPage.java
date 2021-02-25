package com.vtiger.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.util.WebDriverUtil;

public class LoginPage extends TestBase{
	WebDriverUtil webDriverUtil=WebDriverUtil.getObject();
	
	@FindBy(name="user_name")
	private WebElement username_Ed;
	
	@FindBy(name="user_password")
	private WebElement userPassword_Ed;
	
	@FindBy(name="Login")
	private WebElement login_Btn;
	
	@FindBy(xpath="//img[@title='vtiger CRM']")
	private WebElement crmlogo;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	} 

	public boolean validateCRMImage() {
		return crmlogo.isDisplayed();
	}
	
	public HomePage login(String username, String password) {
		
		webDriverUtil.setTextBoxValue(username_Ed, username);
		webDriverUtil.setTextBoxValue(userPassword_Ed, password);
		webDriverUtil.click(login_Btn);
		return new HomePage();
		
	}
}

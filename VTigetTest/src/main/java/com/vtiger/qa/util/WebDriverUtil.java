package com.vtiger.qa.util;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.qa.base.TestBase;

public class WebDriverUtil extends TestBase{
	public static Logger logger=Logger.getLogger(WebDriverUtil.class);
	public WebDriver driver;
	private ExtentTest testLogger;
	private static WebDriverUtil webUtilObj;
	
	private WebDriverUtil() {
		
	}
	
	public static WebDriverUtil getObject() {
		
		if(webUtilObj==null) {
			webUtilObj=new WebDriverUtil();
		}
		return webUtilObj;
	}
	
	public ExtentTest getExtentTestLogger() {
		return testLogger;
	}
    public void setExtentTestLogger(ExtentTest testLoggerObj) {
		testLogger=testLoggerObj;
	}
	
	public static void initialize() throws IOException {
		logger=Logger.getLogger("WebDriverUtil");
		FileInputStream fis=new FileInputStream("log4j.properties");
		Properties prop1=new Properties();
		prop1.load(fis);
		PropertyConfigurator.configure(prop1);

	}
	
	

	public String getTest(WebElement elment) {
		return elment.getText();
	}
	
	public String getAttributeValue(WebElement element, String AttributeName) {
		return element.getAttribute(AttributeName);
	}
	
	public void setTextBoxValue(WebElement element, String input) {
		
		element.sendKeys(input);
		logger.info("This is Input:- "+element);
		testLogger.info(input +" is entered in "+element);

	}

	public void click(WebElement element) {
		element.click();
		logger.info("This is for clicking element:- "+element);
	}

	public void mouseOver(WebElement element, WebDriver driver) {
		//Actions actObj=new Actions(driver);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		logger.info("To mouseover:- "+element);
	}

	public void selectByText(WebElement element, String txt) {
		Select selObj=new Select(element);
		selObj.selectByVisibleText(txt);
		logger.info("To select by text element:- "+element);
	}

	public void selectByIndex(WebElement element, int index) {
		Select selObj=new Select(element);
		selObj.selectByIndex(index);
		logger.info("To select by index:- "+element);
	}

	public void selectByValue(WebElement element, String value) {
		Select selObj=new Select(element);
		selObj.selectByValue(value);
		logger.info("To select by value:- "+element);
	}

}

package com.vtiger.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.util.WebDriverUtil;

public class SalesPage  extends TestBase{
	WebDriverUtil webDriverUtil=WebDriverUtil.getObject();
	
	@FindBy(xpath= "//a[text()='Sales']")
	private WebElement salesLabel;

	@FindBy(xpath = "(//a[text()='Leads'])[4]")
	private WebElement leadsLabel;
	
	@FindBy(xpath = "//select[@name='salutationtype']")
	private WebElement salutationType;
	
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLeads;

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement fstName;

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lstName;

	@FindBy(xpath = "//input[@name='company']")
	private WebElement companyName;
	////
	@FindBy(xpath = "//input[@name='designation']")
	private WebElement designation;

	@FindBy(xpath = "//select[@name='leadsource']")
	private WebElement leadSource;
	
	@FindBy(xpath = "//select[@name='leadstatus']")
	private WebElement leadStatus;
	
	@FindBy(xpath = "//input[@name='phone']")
	private WebElement phone;
	
	@FindBy(xpath = "//input[@name='mobile']")
	private WebElement mobile;
	
	@FindBy(xpath = "//input[@name='fax']")
	private WebElement fax;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement email;
	
	@FindBy(xpath = "//textarea[@name='lane']")
	private WebElement lane;
	
	@FindBy(xpath = "//input[@name='code']")
	private WebElement code;
	
	@FindBy(xpath = "//input[@name='country']")
	private WebElement country;
	
	@FindBy(xpath = "//input[@name='pobox']")
	private WebElement postBox;
	
	@FindBy(xpath = "//input[@name='city']")
	private WebElement city;
	
	@FindBy(xpath = "//input[@name='state']")
	private WebElement state;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement description;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
	private WebElement saveButton;
	
	@FindBy(xpath = "//span[@id='dtlview_First Name']")
	private WebElement viewFirstName;
	
	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	private WebElement viewLstName;

	public SalesPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifySalesLabel() {
		return salesLabel.isDisplayed();
	}

	public void salesOver() {
		leadsLabel.click();
	}

	public void selectSales(String name) {
		driver.findElement(By.xpath("//a[text()='"+name+"']//ancestor::tr[1]//input")).click();
	}

	public void createNewLeads(String title, String lstName, String fstName, String comName, String designation) {
		Select select=new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
		select.selectByValue(title);
	}

	public void createdNewLeads(String sType, String ftName, String ltName, String cmpName, String desName) {
		webDriverUtil.click(createLeads);
		webDriverUtil.selectByValue(salutationType, sType);
		webDriverUtil.setTextBoxValue(fstName, ftName);
		webDriverUtil.setTextBoxValue(lstName, ltName);
		webDriverUtil.setTextBoxValue(companyName, cmpName);
		webDriverUtil.setTextBoxValue(designation, desName);
		webDriverUtil.click(saveButton);
	}
	
	public void CreatNewLeads() throws InterruptedException {
		webDriverUtil.click(createLeads);
		//webDriverUtil.selectByValue(salutationType, prop.getProperty("SalutationType"));
		webDriverUtil.setTextBoxValue(fstName, "Ankit");
		String atrValue=webDriverUtil.getAttributeValue(fstName, "value");
		webDriverUtil.setTextBoxValue(lstName, "Yadav");
		String lstValue=webDriverUtil.getAttributeValue(lstName, "value");
		webDriverUtil.setTextBoxValue(companyName, "QualityKiosh");
		webDriverUtil.setTextBoxValue(designation,"Software Engneer");
		
		webDriverUtil.selectByValue(leadSource, "Public Relations");
		webDriverUtil.selectByValue(leadStatus, "Hot");
		webDriverUtil.setTextBoxValue(phone,"120- 627522");
		webDriverUtil.setTextBoxValue(mobile,"9990111587");
		webDriverUtil.setTextBoxValue(fax,"ankit2bsb@gmail.com");
		webDriverUtil.setTextBoxValue(email,"ankit2bsb@gmail.com");
		webDriverUtil.setTextBoxValue(lane, "Vill- Rampur(Babirchha), Post- Thanetampur, Dist- Varansi");
		webDriverUtil.setTextBoxValue(code, "221207");
		webDriverUtil.setTextBoxValue(country, "India");
		webDriverUtil.setTextBoxValue(postBox, "221207");
		webDriverUtil.setTextBoxValue(city, "Banaras");
		webDriverUtil.setTextBoxValue(description, "This is my first lead i assign to Mr. Rohit");
		Thread.sleep(5000);
		webDriverUtil.click(saveButton);
		Thread.sleep(5000);
		String fstTest=webDriverUtil.getTest(viewFirstName);
		String lstTest=webDriverUtil.getTest(viewLstName);
		Assert.assertEquals(atrValue, fstTest);
		Assert.assertEquals(lstValue, lstTest);
	}
	
	public String validateSavePage() {
		return webDriverUtil.getTest(viewFirstName);
		
	}
}

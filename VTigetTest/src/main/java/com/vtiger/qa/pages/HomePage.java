package com.vtiger.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.util.WebDriverUtil;

public class HomePage extends TestBase {

	WebDriverUtil webDriverUtil=WebDriverUtil.getObject();

	@FindBy(xpath= "//a[text()='Marketing']")
	private WebElement marketingLink;

	@FindBy(xpath= "//a[text()='Sales']")
	private WebElement salesLink;
	
	@FindBy(xpath = "(//a[@class='drop_down' and text()='Leads'])[1]")
	private WebElement leadsLabel;

	@FindBy(xpath= "//a[text()='Support']")
	private WebElement supportLink;

	@FindBy(xpath= "//a[text()='Analytics']")
	private WebElement analyticsLink;

	@FindBy(xpath= "//a[text()='Inventory']")
	private WebElement inventoryLink;

	@FindBy(xpath= "//a[text()='Tools']")
	private WebElement toolsLink;

	@FindBy(xpath= "//a[text()='Settings']")
	private WebElement settingsLink;

	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signOutButton;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String getHomePageTitle() {
		
		return driver.getTitle();
	}

	public boolean verifySignOutButton() {
		return signOutButton.isDisplayed();
	}

	public SalesPage clickOnSales() {
		Actions action=new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		return new SalesPage();
	}
	
	public void clickOnLeadsLink() {
		
		leadsLabel.click();
		//createLeads.click();
	}
	

	public MarketingPage clickOnMarketing() {
		webDriverUtil.click(marketingLink);
		return new MarketingPage();
	}

	public SupportPage clickOnSupport() {
		webDriverUtil.click(supportLink);
		return new SupportPage();
	}

	public AnalyticsPage clickOnAnalytics() {
		webDriverUtil.click(analyticsLink);
		return new AnalyticsPage();
	}

	public InventoryPage clickOnInventory() {
		webDriverUtil.click(inventoryLink);
		return new InventoryPage();
	}

	public ToolsPage clickOnTools() {
		webDriverUtil.click(toolsLink);
		return new ToolsPage();
	}

	public SettingPage clickOnSetting() {
		webDriverUtil.click(settingsLink);
		return new SettingPage();
	}

}

package com.vtiger.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.qa.base.TestBase;
import com.vtiger.qa.util.WebDriverUtil;

public class MarketingPage extends TestBase{

	WebDriverUtil webDriverUtil=WebDriverUtil.getObject();
	
	@FindBy(xpath="//a[text()='Marketing']")
	private WebElement marketingLabel;
	
	@FindBy(xpath = "//div[@class='drop_mnu']//a[text()='Campaigns']")
	private WebElement campaignsLabel;
	
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement createCompaign;
	
	@FindBy(xpath = "//input[@name='campaignname']")
	private WebElement marketingCampaignname;
	
	@FindBy(xpath = "//select[@name='campaigntype']")
	private WebElement marketingCampaigntype;
	
	@FindBy(xpath = "//input[@name='targetaudience']")
	private WebElement marketingTargetAudience;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]")
	private WebElement saveButton;
	
	public MarketingPage() {
		PageFactory.initElements(driver, this);
	}

	public void markertingOver() {
		webDriverUtil.click(campaignsLabel);
	}
	
	public MarketingPage clickOnMarketing() {
		Actions action=new Actions(driver);
		action.moveToElement(marketingLabel).build().perform();
		return new MarketingPage();
	}
	
	public void createMarketingPage(String camaignName, int index, String targetAudience  ) {
		webDriverUtil.click(createCompaign);
		webDriverUtil.setTextBoxValue(marketingCampaignname, camaignName);
		webDriverUtil.selectByIndex(marketingCampaigntype, index);
		webDriverUtil.setTextBoxValue(marketingTargetAudience, targetAudience);
		webDriverUtil.click(saveButton);
	}
	
}

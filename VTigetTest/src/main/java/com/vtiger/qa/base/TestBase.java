package com.vtiger.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.qa.pages.HomePage;
import com.vtiger.qa.pages.LoginPage;
import com.vtiger.qa.pages.SalesPage;
import com.vtiger.qa.util.TestUtil;
import com.vtiger.qa.util.WebDriverUtil;
import com.vtiger.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	protected HomePage homePage;
	private ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;
	private ExtentTest logger;

	public TestBase() {

		try {
			prop= new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/vtiger/qa/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod 
	public void beforeMethod(Method method) {

		logger = extent.createTest(method.getName());
		WebDriverUtil.getObject().setExtentTestLogger(logger);
		initialization();
		homePage =new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeSuite
	public void beforeSuite() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/MaxLifeAutomationReport.html");
		// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "MaxLife");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Ankit Yadav");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		// Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		// Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD);	
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = getScreenShot(driver, result.getName());
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		driver.quit();
		extent.flush();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

	public static void initialization() {
		//WebDriverUtil.initialize();   
		String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browserName.contains("ff")){
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}else {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}

		e_driver=new EventFiringWebDriver(driver);
		eventListener=new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;   

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}


}

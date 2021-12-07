package CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static ConfigReader configReader;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports reporter;
	public static ExtentTest logger;

	@BeforeMethod

	public void beforeTestMethod(Method testMethod) throws IOException {
		logger = reporter.createTest(testMethod.getName());
		getDriver("Chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		configReader = new ConfigReader("QA");
		driver.get(configReader.readPropertyFile("applicationUrl"));
	}

	@AfterMethod
	public void afterTestMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logtext = " Test Case " + methodName + " Passed";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		}

		else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logtext = " Test Case " + methodName + " Failed";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		} else {
			String methodName = result.getMethod().getMethodName();
			String logtext = " Test Case " + methodName + " Skipped";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.AMBER);
			logger.log(Status.SKIP, m);
		}

		//driver.quit();

	}

	@BeforeTest
	public void beforeTest() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("usr.dir") + "Reports/AutomationExecutionReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Execution Results");
		htmlReporter.config().setTheme(Theme.DARK);

		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter);
		reporter.setSystemInfo("Executed By ", "QAStack");

	}

	public WebDriver getDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else {
			// Handle other browsers or throw an exception for an unsupported browser
			throw new IllegalArgumentException("Unsupported browser: " + browserName);

		}
	
		return driver;
	}
	
	
	public static WebDriver HighlightElement(WebDriver driver, By locator) {
	    WebElement element = driver.findElement(locator); // Resolve By to WebElement
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].style.border='3px solid red'", element);
	    }
	    return driver;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
}

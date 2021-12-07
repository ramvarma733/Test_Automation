package PageWiseActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonFunctions.BaseTest;

public class HomePageActions extends BaseTest {
	public By emailInputLocator = By.id("email");
	public By passwordInputLocator = By.id("pass");
	public By loginButtonLocator = By.name("login");

	

	@SuppressWarnings("static-access")
	public HomePageActions(WebDriver driver) {
		this.driver = driver;
	}

	public void login(WebDriver driver, String email, String password) {
		
		HighlightElement(driver,emailInputLocator);
		WebElement emailInput = driver.findElement(emailInputLocator);
		HighlightElement(driver,passwordInputLocator);
		WebElement passwordInput = driver.findElement(passwordInputLocator);
		WebElement loginButton = driver.findElement(loginButtonLocator);

		emailInput.sendKeys(configReader.readPropertyFile(email));
		passwordInput.sendKeys(configReader.readPropertyFile(password));
		loginButton.click();
	}

}
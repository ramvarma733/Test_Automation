package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import CommonFunctions.BaseTest;

public class ParabankLoginPage extends BaseTest {
	public   By emailInputLocator = By.name("username");
	public By passwordInputLocator = By.name("password");
	public By loginButtonLocator = By.xpath("//input[@type = 'submit']");
	
	
    public ParabankLoginPage() {
		// TODO Auto-generated constructor stub`
	}
	public void login(String email, String password) {
		HighlightElement(driver,emailInputLocator);
        WebElement emailInput = driver.findElement(emailInputLocator);
        HighlightElement(driver,passwordInputLocator);
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        WebElement loginButton = driver.findElement(loginButtonLocator);

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

}

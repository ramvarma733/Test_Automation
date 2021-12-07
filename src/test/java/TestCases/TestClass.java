package TestCases;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import CommonFunctions.BaseTest;
import PageObjects.ParabankLoginPage;

@SuppressWarnings("unused")
public class TestClass extends BaseTest {

//	@Test
//	public void validateTitle() {
//		String actualTitle = driver.getTitle();
//		Assert.assertEquals(actualTitle, "Facebook – log in or sign up");
//	}

	@Test
	public void login() {
		ParabankLoginPage homePageActions = new ParabankLoginPage();
		homePageActions.login(configReader.readPropertyFile("email"), configReader.readPropertyFile("password"));
	}

}

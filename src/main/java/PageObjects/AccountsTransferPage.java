package PageObjects;

import CommonFunctions.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AccountsTransferPage extends BaseTest{
	
	public By  trasferLink = By.linkText("//a[text()='Transfer Funds']");
	
	public By transferpage = By.xpath("//h1[@class='title' and text()='Transfer Funds']");
			
	
	
	public AccountsTransferPage() {
		// TODO Auto-generated constructor stub`
	}

	
}



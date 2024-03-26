package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{


	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[@class='ico-account']")
	WebElement lnkMyAcc;

	public boolean myAccLink() {
		try {
			boolean status=lnkMyAcc.isDisplayed();
			return status;
		}
		catch(Exception e) {
			return false;
		}

	}


}

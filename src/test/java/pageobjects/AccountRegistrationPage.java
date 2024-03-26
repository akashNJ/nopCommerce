package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {


	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}


	@FindBy(xpath="//input[@id='FirstName']")
	WebElement txtFirstName;

	@FindBy(xpath="//input[@id='LastName']")
	WebElement txtLasttname;

	@FindBy(xpath="//input[@id='Email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='Password']")
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='ConfirmPassword']")
	WebElement txtConfirmPassword;

	@FindBy(xpath="//button[@id='register-button']")
	WebElement btnRegister;

	@FindBy(xpath="//div[@class='result']")
	WebElement msgConfirmation;


	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLasttname.sendKeys(lname);

	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);

	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void clickRegister() {
		btnRegister.click();
	}

	public String getConfirmationMsg() {
		try {
			String msg=msgConfirmation.getText();
			return msg;
		}
		catch(Exception e) {
			e.getMessage();
			return null;
		}


	}

}

package testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC_002_LoginTest extends BaseClass{

	@Test(groups= {"regression","master"})
	public void verify_login() throws InterruptedException {
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("userName"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		MyAccountPage acc=new MyAccountPage(driver);
		Assert.assertTrue(acc.myAccLink());
	}
}

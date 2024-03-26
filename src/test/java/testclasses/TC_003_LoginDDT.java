package testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviderClass;

public class TC_003_LoginDDT extends BaseClass {
		
	@Test (dataProvider="dp",dataProviderClass=DataProviderClass.class)
	public void verify_loginDDT(String mail, String pwd, String exp) {
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(mail);
		lp.setPassword(pwd);
		lp.clickLogin();
		MyAccountPage acc=new MyAccountPage(driver);
		if(exp.equals("invalid")) {
			Assert.assertEquals(acc.myAccLink(),false);
		}
	}

}

package testclasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;


public class TC_001_AccountRegistrationTest extends BaseClass{

	FileInputStream file;
	FileOutputStream file1;
	Properties prop;
	@Test (groups= {"sanity","master"})
	public void verify_account_registration() throws IOException{

		HomePage hp=new HomePage(driver);
		hp.clickRegister();

		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString());
		regpage.setLastName(randomString());
		String user=randomAlphaNumeric()+"@gmail.com";
		regpage.setEmail(user);

		String pass=randomAlphaNumeric();

		regpage.setPassword(pass);
		regpage.setConfirmPassword(pass);

		regpage.clickRegister();;


		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your registration completed");

		String propertiesFilePath=".//src/test/resources//config.properties";
		prop=new Properties();
		file=new FileInputStream(propertiesFilePath);
		prop.load(file);
		file1=new FileOutputStream(propertiesFilePath);
		prop.setProperty("userName",user );
		prop.setProperty("password",pass );
		prop.store(file1,"Data modified");
	}


}

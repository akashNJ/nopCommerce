package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;
import com.google.common.io.Files;

public class BaseClass {

	static public WebDriver driver;
	public Properties p;
	public Logger logs;
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"browser","os"})
	public void setUp(String br,String os) throws IOException{

		//loading properties file
		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src/test/resources\\config.properties");
		p=new Properties();
		p.load(file);

		//loading log4j 
		logs = LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("env").equals("remote")) {
			
			//using selenium grid
			
			DesiredCapabilities cap=new DesiredCapabilities();
			switch(os) {
			case "windows":cap.setPlatform(Platform.WINDOWS);break;
			case "mac": cap.setPlatform(Platform.MAC);break;
			default: System.out.println("No matching OS");
			return;
			}
			switch(br) {
			case "chrome": cap.setBrowserName("chrome");break;
			case "edge": cap.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching browser");
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		else {
			//passing browser name on local system

			switch(br) {
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			default: System.out.println("No matching browser found");
			return;
			}
			
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		logs.info("opening application");
	}

	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		Faker fake=new Faker();
		String name=fake.name().firstName();
		return name;
	}

	public String randomNumber() {
		Faker fake=new Faker();
		String number=fake.number().digits(10);
		return number;
	}

	public String randomAlphaNumeric()
	{
		Faker fake=new Faker();
		String alphanumeric=fake.lorem().characters(8);
		return alphanumeric;
	}

	public String captureScreen(String testname) throws IOException {
		SimpleDateFormat df=new SimpleDateFormat("ddMMYYYHHmm"); 
		Date d=new Date();
		String format=df.format(d);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String targetfile=System.getProperty("user.dir")+"/screenshots/"+testname+format+".png";
		File target=new File(targetfile);
		Files.copy(source, target);
		return targetfile;
	}
}

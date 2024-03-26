package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import testbase.BaseClass;

public class ExtentReportUtility implements ITestListener {

	ExtentSparkReporter spark; //for handling UI of report
	ExtentReports report; // for adding common info in report
	ExtentTest test; //for adding test case entries

	public void onStart(ITestContext context) {
		SimpleDateFormat fr=new SimpleDateFormat("ddMMyyyyHHmm");
		Date d=new Date();
		String formateddate=fr.format(d);
		String reponame="Test-Report-"+formateddate+".html";
		spark=new ExtentSparkReporter(".//report//"+reponame);


		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.STANDARD);

		report=new ExtentReports();
		report.attachReporter(spark);

		report.setSystemInfo("Tester",System.getProperty("user.name"));

		String browser_name=context.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser", browser_name);

		String os=context.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("Platform", os);

		List <String> groups=context.getCurrentXmlTest().getIncludedGroups();
		if(!groups.isEmpty()) {
			report.setSystemInfo("Groups", groups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getRealClass().getSimpleName());
		test.log(Status.PASS,result.getName());
	}

	public  void onTestFailure(ITestResult result){
		BaseClass base=new BaseClass();
		test=report.createTest(result.getTestClass().getRealClass().getSimpleName());
		test.log(Status.FAIL, result.getName());
		test.log(Status.FAIL, result.getThrowable());


		try {
			String path = base.captureScreen(result.getTestClass().getRealClass().getSimpleName());
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public  void onTestSkipped(ITestResult result) {
		test=report.createTest(result.getTestClass().getRealClass().getSimpleName());
		test.log(Status.SKIP, result.getName());
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}




}

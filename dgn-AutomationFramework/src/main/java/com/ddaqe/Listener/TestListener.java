package com.ddaqe.Listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ddaqe.base.BaseTest;
import com.ddaqe.utils.ExtentManager;
import com.ddaqe.utils.FrameworkConfigurationReader;

public class TestListener implements ITestListener {

	static Logger log = Logger.getLogger(TestListener.class);

	private static ExtentReports extent = ExtentManager.createInstance();

	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentTest parent;
	private static ExtentTest child;
	private WebDriver driver;

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("OnStart begin");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("On test Start begin");
		parent = extent.createTest(result.getTestClass().getRealClass().getSimpleName());
		parentTest.set(parent);
		child = parentTest.get().createNode(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.set(child);
		test.get().assignCategory(result.getTestClass().getRealClass().getSimpleName());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println("On test Fail start");
		test.get().fail(result.getThrowable());
		this.driver = ((BaseTest) result.getInstance()).getBrowser().getDriver();
		String screenshotPath = "";
		try {
			screenshotPath = takeScreenShot(this.driver, result);
		} catch (IOException e) {
		}
		try {
			test.get().fail("details").addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		if (test != null) {
			if (result != null) {
			}
			test.get().skip(result.getThrowable());
		}
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public static ExtentTest getExtentTest() {
		return test.get();
	}

	public String takeScreenShot(WebDriver webDriver, ITestResult iTestResult) throws IOException {
		FrameworkConfigurationReader frameworkConfigurationReader = FrameworkConfigurationReader.getInstance();
		String screenshotFile = iTestResult.getTestClass().getRealClass().getSimpleName() + "_" + iTestResult.getName()
				+ "_" + new SimpleDateFormat(frameworkConfigurationReader.getProperty("report.name.timestamp.format"))
						.format(new Date())
				+ "." + frameworkConfigurationReader.getProperty("screenshot.extension");
		String screenshotPath = createReportsScreenshotsDirectory() + File.separator + screenshotFile;
		File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(screenshotPath));
		return screenshotPath;
	}

	private String createReportsScreenshotsDirectory() {
		String reportsScreenshotsDirectoryPath = System.getProperty("user.dir") + File.separator + "test-output"
				+ File.separator + "Reports" + File.separator + "ReportsScreenshots";
		File directory = new File(reportsScreenshotsDirectoryPath);
		if (!directory.exists()) {
			try {
				FileUtils.forceMkdir(directory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reportsScreenshotsDirectoryPath;
	}

}
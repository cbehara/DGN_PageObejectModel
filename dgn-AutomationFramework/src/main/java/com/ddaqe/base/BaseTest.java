package com.ddaqe.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import com.browserstack.local.Local;
import com.ddaqe.helper.Browser;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LoginPage;
import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.SeleniumUtils;

public class BaseTest {

	private static Local local;
	protected String browserType;
	protected String applicationURL;
	protected Browser browser;
	private static boolean isParallel = false;
	protected ThreadLocal<Browser> browser1 = new ThreadLocal<>();
	private static ConfigurationReader configurationReader = ConfigurationReader.getInstance();

	protected String downloadDestination = "";
	protected int retryAttempts = 0;
	protected int retryDelay = 0;

	private Properties properties = null;
	private String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

	static {
		isParallel = Boolean.valueOf(configurationReader.getProperty("execute.test.parallel"));
	}

	@BeforeSuite(alwaysRun = true)
	public void setupSuite() throws Exception {
		System.out.println("Before suite called..");
		// To run from jenkins

		if (null == local && isParallel) {
			local = new Local();
			Map<String, String> options = new HashMap<String, String>();
			if (accessKey == null) {
				accessKey = "4ys5uYjcqPbWsc1AmHz2";
			}
			System.out.println("Getting accesskey..:" + accessKey);
			options.put("key", accessKey);
			local.start(options);
		}

		System.out.println("Before suite ended..");
	}

	@AfterSuite(alwaysRun = true)
	public void quit() throws Exception {
		System.out.println("After Suite Started");
		// JEnkins manages to stop automatically
		if (null != local) {
			local.stop();
			local = null;
		}

		System.out.println("After Suite Ended");
	}

	@BeforeMethod(alwaysRun = true)
	@org.testng.annotations.Parameters(value = { "config", "environment" })
	public void setupMethod(@Optional String config_file, @Optional String environment, @Optional Method methodName)
			throws Exception {
		System.out.println("Before Method Started");
		ConfigurationReader configurationReader = ConfigurationReader.getInstance();
		browserType = configurationReader.getProperty("browser.type");
		applicationURL = configurationReader.getProperty("application.url");
		downloadDestination = createDownloadDirectory() + File.separator;
		retryAttempts = Integer.parseInt(configurationReader.getProperty("retry.attempts"));
		retryDelay = Integer.parseInt(configurationReader.getProperty("retry.delay.millisecs"));

		// Capabilities to download file in a specific destination.
		Map<String, Object> features = new HashMap<>();
		features.put("profile.default_content_settings.popups", 0);
		features.put("download.default_directory", downloadDestination);
		features.put("safebrowsing.enabled", "true");

		if (isParallel) {
			this.browser1.set(new Browser());
			getBrowser().setUp(config_file, environment, methodName.getName(), features);
			getBrowser().initiateApplication(applicationURL);
		} else {
			browser = new Browser();
			browser.initiateBrowserWithAdditionalFeatures(browserType, applicationURL, features);
		}
		System.out.println("Before Method Ended");
	}

	@AfterMethod
	public void quitMethod() throws Exception {
		getBrowser().tearDown();
	}

	public String getBrowserType() {
		return browserType;
	}

	public String getApplicationURL() {
		return applicationURL;
	}

	public Browser getBrowser() {
		if (isParallel) {
			return this.browser1.get();
		} else {
			return this.browser;
		}
	}

	public HomePage loginAs(String emailAddress, String password) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginPage.loginAs(emailAddress, password);
		Assert.assertTrue(verifySuccessfulLogin(homePage),
				"Login with EmailAddress: " + emailAddress + " Password: " + password + " was unsuccessful.");
		return homePage;
	}

	public HomePage loginAsWithOutHomeMenu(String emailAddress, String password) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginPage.loginAs(emailAddress, password);
		return homePage;
	}

	public Boolean verifySuccessfulLogin(HomePage homePage) {
		if (null != homePage) {
			return homePage.isHomeMenuLinkDisplayed();
		}
		return false;

	}

	public WebDriver getDriver() {
		return getBrowser().getDriver();
	}

	/*
	 * public Local getLocal() { return l; }
	 */

	public void goToBackPage() {
		SeleniumUtils.goToBackPage(getBrowser().getDriver());
	}

	public void PropertiesReader() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\java\\com\\com.dgn_Ecommerce\\ssoUserFields.properties"));
		} catch (Exception e) {
		}
	}

	public void PropertiesReaderCreditCarddetails() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\java\\com\\dgn_customer_admin_tools\\customerDetails.properties"));
		} catch (Exception e) {
		}
	}

	// get property value by name
	public String getProperty(String key) {
		String value = null;
		if (properties.containsKey(key)) {
			value = (String) properties.get(key);
		}
		return value;
	}

	private String createDownloadDirectory() {
		String downloadDirectoryPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator
				+ "Downloads";
		File directory = new File(downloadDirectoryPath);
		if (!directory.exists()) {
			try {
				FileUtils.forceMkdir(directory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return downloadDirectoryPath;
	}
}

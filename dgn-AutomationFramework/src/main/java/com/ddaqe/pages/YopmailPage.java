package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.SeleniumUtils;

public class YopmailPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String selectedProject;

	@FindBy(how = How.ID, using = "login")
	private WebElement emailAddressesSection;

	@FindBy(how = How.XPATH, using = "//input[@class='sbut']")
	private WebElement checkInboxBtn;

	@FindBy(how = How.ID, using = "mailhaut")
	private WebElement bodyMailHeader;

	@FindBy(how = How.XPATH, using = "//div[@id='m1']/div/a//span[@class='lmf']")
	private WebElement subjectLatest;

	@FindBy(how = How.XPATH, using = "//div[@id='m1']/div/a//span[@class='lms']")
	private WebElement subjectResetPwd;

	@FindBy(how = How.XPATH, using = "//a[@id='affim']")
	private WebElement showPictureslbl;

	@FindBy(how = How.XPATH, using = "//*[@id='mailhaut']/div[@class='f16']")
	private WebElement mailHeader;

	@FindBy(how = How.XPATH, using = "//div[@id='mailmillieu']//img")
	private List<WebElement> mailFooterLogo;

	@FindBy(how = How.XPATH, using = "//div[@id='mailmillieu']//a[contains(@href,'Project.aspx')]")
	private List<WebElement> projectList;

	@FindBy(how = How.XPATH, using = "//div[@id='mailmillieu']//a[contains(@href,'ProjectAddenda')]")
	private List<WebElement> projectAddenda;

	public YopmailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Email Alerts");
	}

	public void enterYopmailEmailAddress(String emailAddress) {
		extentTest.log(Status.INFO, "Entering Email Address");
		emailAddressesSection.clear();
		emailAddressesSection.sendKeys(emailAddress);
	}

	public void clickOnCheckInbox() {
		extentTest.log(Status.INFO, "Clicking the Check Inbox button");
		checkInboxBtn.click();
	}

	public void CheckInbox(String alternateEmailAddress) {
		ConfigurationReader configurationReader = ConfigurationReader.getInstance();
		EmailAlertsPage emailAlertsPage = new EmailAlertsPage(driver);
		YopmailPage yopmailPage = emailAlertsPage.goToYopmail(configurationReader.getProperty("yopmail.url"));
		yopmailPage.enterYopmailEmailAddress(alternateEmailAddress);
		yopmailPage.clickOnCheckInbox();
	}

	public String getSubject() {
		extentTest.log(Status.INFO, "Get the latest subject in the mail");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(subjectLatest));
		return subjectLatest.getText();
	}

	public void clickLatestSubject() {
		extentTest.log(Status.INFO, "Clicking the latest subject in the mail");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(subjectLatest));
		subjectLatest.click();
	}

	public void clickSubjectResetPwd() {
		extentTest.log(Status.INFO, "Clicking the latest subject in the mail for password reset");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(subjectResetPwd));
		subjectResetPwd.click();
	}

	public boolean isMailReceivedFromDodge() {
		extentTest.log(Status.INFO, "Verify if the mail received from Dodge Data and Analytics");
		return getSubject().equalsIgnoreCase("dodge.network@construction.com");
	}

	public boolean isMailReceivedForPasswordReset() {
		extentTest.log(Status.INFO, "Verify if the mail received from Dodge Data and Analytics for password reset");
		return getSubject().equalsIgnoreCase("DoNotReply@construction.com");
	}

	public void clickShowPictures() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(showPictureslbl));
		if (showPictureslbl.getText().trim().contains("Show pictures"))
			showPictureslbl.click();
	}

	public boolean isDodgeLogoDisplayed() {
		extentTest.log(Status.INFO, "Verify if the footer logo is Dodge Data and Analytics");
		SeleniumUtils.scrollToView(driver, mailFooterLogo.get(0));
		return mailFooterLogo.get(0).getAttribute("src").contains("logo.png");
	}

	public void switchToInboxFrame() {
		driver.switchTo().frame("ifinbox");
	}

	public void switchToBodyFrame() {
		driver.switchTo().frame("ifmail");
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public Boolean isProjectTitleListDisplayed() {
		extentTest.log(Status.INFO, "Verify if the project title list is displayed");
		try {
			return projectList.size() > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	public String clickProjectTitle() {
		extentTest.log(Status.INFO, "Click the project title");
		String name = projectList.get(0).getText();
		projectList.get(0).click();
		CommonUtils.switchToNewTab(driver);
		return name;
	}

	public void clickProjectAddendaLink() {
		extentTest.log(Status.INFO, "Click the project Addenda Link");
		projectAddenda.get(0).click();
		CommonUtils.switchToNewTab(driver);
	}

	@Override
	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public String getMailHeader() throws InterruptedException {
		extentTest.log(Status.INFO, "Get the mail header");
		Thread.sleep(10000);
		return mailHeader.getText().trim();
	}
}

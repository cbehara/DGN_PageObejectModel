package com.ddaqe.pages;

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
import com.ddaqe.utils.SeleniumUtils;

public class EmailAlertsPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String selectedProject;

	@FindBy(how = How.ID, using = "emailAddressesSection")
	private WebElement emailAddressesSection;

	@FindBy(how = How.ID, using = "optionalMessagesSection")
	private WebElement optionalMessagesSection;

	@FindBy(how = How.ID, using = "emailPopupSubmit")
	private WebElement emailPopupSave;

	@FindBy(how = How.ID, using = "emailPopupClose")
	private WebElement emailPopupCancel;

	public EmailAlertsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Email Alerts");
	}

	public void enterEmailAddress(String emailAddress) {
		extentTest.log(Status.INFO, "Entering Email Address");
		SeleniumUtils.isVisible(emailAddressesSection, driver);
		emailAddressesSection.clear();
		emailAddressesSection.sendKeys(emailAddress);
	}

	public boolean isEmailAddressDisplayed() {
		extentTest.log(Status.INFO, "Check the email address section displayed in the pop up");
		return emailAddressesSection.isDisplayed();
	}

	public void enterOptionalMessage(String msg) {
		extentTest.log(Status.INFO, "Enter Optional Message");
		optionalMessagesSection.clear();
		optionalMessagesSection.sendKeys(msg);
	}

	public void clickOnPopUpSaveButton() {
		extentTest.log(Status.INFO, "Clicking on the save button");
		emailPopupSave.click();
	}

	public void clickOnPopUpCancelButton() {
		extentTest.log(Status.INFO, "Clicking on the cancel button");
		emailPopupCancel.click();
	}

	public YopmailPage goToYopmail(String url) {
		extentTest.log(Status.INFO, "Go To Yopmail");
		driver.get(url);
		return new YopmailPage(driver);
	}

}

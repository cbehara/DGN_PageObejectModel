package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class ActivationPage {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "MainContent_txtEmail")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtPassword")
	private WebElement passwordTxtField;

	@FindBy(how = How.ID, using = "MainContent_btnLogin")
	private WebElement signInBtn;

	@FindBy(how = How.ID, using = "MainContent_lblErrorMessage")
	private WebElement loginErrTxt;
	@FindBy(how = How.ID, using = "haveALicenseLText")
	private WebElement haveALicenseLink;

	@FindBy(how = How.ID, using = "welcomeText")
	private WebElement welcomeText;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_txtLicenseNo")
	private WebElement licenseTextField;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSubmit")
	private WebElement submitBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblError")
	private WebElement expiredLicenseMessage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblMsg")
	private WebElement invalidLicenseMessage;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lnk_SignOut")
	private WebElement signOutLink;

	public ActivationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();

	}

	public boolean isLicenseInputPageDisplayed() {
		extentTest.log(Status.INFO, "Verify if license page has displayed for Non license user");
		try {
			return haveALicenseLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isWelcomeTextdisplayed() {
		extentTest.log(Status.INFO, "Verify the Enter license page validation");
		try {
			return welcomeText.isDisplayed();
		} catch (Exception ex) {
			return false;
		}

	}

	public boolean isExpiredLicenseDisplayed() {
		extentTest.log(Status.INFO, "Verify the Error message on submitting Expired License ");

		String expected = expiredLicenseMessage.getText();
		return expected.equals(
				"The license that you are attempting to assign to this user is not an active Dodge Network v2.0 License, please contact Customer Care 1.800.393.6343.");

	}

	public boolean isInvalidLicenseDisplayed() {
		extentTest.log(Status.INFO, "Verify the Error message on submitting Invalid License ");
		String expected = invalidLicenseMessage.getText();
		return expected.equals(
				"The license number you have entered is invalid. Please check you license number and enter again");

	}

	public void clickOnLicenseField() {
		extentTest.log(Status.INFO, "Click on Enter License Field");
		licenseTextField.click();
	}

	public void enterValueInLicenseField(String License_Number) {
		extentTest.log(Status.INFO, " Enter value in Activate License Field");
		licenseTextField.sendKeys(License_Number);
	}

	public void clickOnSubmitButton() {
		extentTest.log(Status.INFO, " Click on Submit field");
		submitBtn.click();
	}

	public TermsOfUsePage clickOnSubmitLicenseButtonToGetTermsPage() {
		extentTest.log(Status.INFO, " Click on Submit field");
		submitBtn.click();
		return new TermsOfUsePage(driver);
	}

	public AccessPendingPage clickOnSubmitLicenseButtonToGetAccessPendingPage() {
		extentTest.log(Status.INFO, " Click on Submit field");
		submitBtn.click();
		return new AccessPendingPage(driver);
	}

	public void clickOnSignOut() {
		extentTest.log(Status.INFO, " Sign out from Activation page");
		signOutLink.click();
	}

	public boolean isExpiredLicenseErrorMsgDisplayed() {
		extentTest.log(Status.INFO, "Verify the Error message on submitting Expired License ");

		String expected = expiredLicenseMessage.getText();
		return expected.equals(
				"Your Global Network license has expired or has been cancelled, please contact Customer Care 1.800.393.6343.");

	}
}

package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.helper.Browser;

public class BidProHomePage {
	private WebDriver driver;
	private ExtentTest extentTest;
	protected Browser browser;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lnkSignout")
	private WebElement SignOutLink;

	@FindBy(how = How.ID, using = "MainContent_txtEmail")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtPassword")
	private WebElement passwordTxtField;

	@FindBy(how = How.ID, using = "MainContent_btnLogin")
	private WebElement signInBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_txtLicenseNo")
	private WebElement licenseKeyField;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSubmit")
	private WebElement submitButton;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblMsg")
	private WebElement ErrorMsg;

	public BidProHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean Is_SignOutLink_Displayed() {
		extentTest.log(Status.INFO, "Ensured that signout link is displayed");
		return SignOutLink.isDisplayed();
	}

	public BidProHomePage loginAs(String emailAddress, String password, String url) {
		extentTest.log(Status.INFO, "Navigate to BidPro URL");
		driver.get(url);
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.clear();
		emailAddressTxtField.sendKeys(emailAddress);
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.clear();
		passwordTxtField.sendKeys(password);
		extentTest.log(Status.INFO, "Click on Login button.");
		signInBtn.click();
		return new BidProHomePage(driver);
	}

	public void ClickOnLicenseField() {
		extentTest.log(Status.INFO, "Click on license field");
		licenseKeyField.click();
	}

	public void enterValueInLicenseField(String License_Number) {
		extentTest.log(Status.INFO, " Enter value in Activate License Field");
		licenseKeyField.sendKeys(License_Number);
	}

	public void clickOnSubmitButton() {
		extentTest.log(Status.INFO, " Click on Submit field");
		submitButton.click();
	}

	public boolean isExpiredLicenseErrorMsgDisplayed() {
		extentTest.log(Status.INFO, "Verify the Error message on submitting Expired License ");

		String expected = ErrorMsg.getText();
		return expected.equals(
				"The license number you have entered is invalid. Please check your license number and enter again");

	}

}
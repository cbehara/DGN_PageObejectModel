package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class LoginPage {

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

	@FindBy(how = How.ID, using = "MainContent_hlConstructionRegister")
	private WebElement registerBtn;

	@FindBy(how = How.ID, using = "MainContent_hlResetPassword")
	private WebElement resetPwd;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Login Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean isLoginErrorMessageDisplayed() {
		return loginErrTxt.isDisplayed();
	}

	public HomePage loginAs(String emailAddress, String password) {
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.clear();
		emailAddressTxtField.sendKeys(emailAddress);
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.clear();
		passwordTxtField.sendKeys(password);
		extentTest.log(Status.INFO, "Click on Login button.");
		signInBtn.click();

		return new HomePage(this.driver);

	}

	public ActivationPage loginAsNonLicenseUser(String emailAddress, String password) {
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.sendKeys(emailAddress);
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.sendKeys(password);
		extentTest.log(Status.INFO, "Click on Login button.");
		signInBtn.click();

		return new ActivationPage(this.driver);

	}

	public void enterEmailAddress(String emailAddress) {
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.sendKeys(emailAddress);
	}

	public void enterPassword(String password) {
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.sendKeys(password);
	}

	public void clickOnSignInButton() {
		extentTest.log(Status.INFO, "Click on Login button.");
		signInBtn.click();
	}

	public boolean isEmailAddressTxtFieldDisplayed() {
		return emailAddressTxtField.isDisplayed();
	}

	public boolean isPasswordTxtFieldDisplayed() {
		return passwordTxtField.isDisplayed();
	}

	public HomePage navigateToHomePage() {
		extentTest.log(Status.INFO, "Navigate to Home Page");
		signInBtn.click();
		return new HomePage(this.driver);
	}

	public SSOCreateUserPage clickOnRegisterNowButton() {
		extentTest.log(Status.INFO, "Click on Register Now button");
		registerBtn.click();
		return new SSOCreateUserPage(driver);
	}

	public ResetPasswordPage clickResetPwd() {
		extentTest.log(Status.INFO, "Clicking on the reset password link");
		resetPwd.click();
		return new ResetPasswordPage(driver);
	}
	
	public boolean isSignInButtonDisplayed() {
		extentTest.log(Status.INFO, "Verifying Login button.");
		return signInBtn.isDisplayed();
	}
}

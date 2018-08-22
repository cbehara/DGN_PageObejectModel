package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class ResetPasswordPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "MainContent_txtEmailAddress")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtRetypedEmailAddress")
	private WebElement emailAddressReTypeTxtField;

	@FindBy(how = How.ID, using = "MainContent_ibtEmailAddressSubmit")
	private WebElement btnSubmit;

	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Reset Password Page");
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void EnterEmailAddress(String email) {
		extentTest.log(Status.INFO, "Enter the email address for password reset");
		emailAddressTxtField.clear();
		emailAddressTxtField.sendKeys(email);
	}

	public void EnterConfirmEmailAddress(String email) {
		extentTest.log(Status.INFO, "Enter the Confirm email address for password reset");
		emailAddressReTypeTxtField.clear();
		emailAddressReTypeTxtField.sendKeys(email);
	}

	public void clickSubmitButton() {
		extentTest.log(Status.INFO, "Clicking on the submit button for reset password");
		btnSubmit.click();
	}

}

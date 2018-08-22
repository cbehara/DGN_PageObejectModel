package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.ddaqe.Listener.TestListener;

public class RegisterForUnderstandingTheHomeTabDashboardPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.NAME, using = "enrollForm.standardQuestions.questions[0].answer")
	private WebElement firstNameTextfield;

	@FindBy(how = How.NAME, using = "enrollForm.standardQuestions.questions[1].answer")
	private WebElement lastNameTextfield;

	@FindBy(how = How.NAME, using = "enrollForm.standardQuestions.questions[2].answer")
	private WebElement emailAddressTextfield;

	@FindBy(how = How.ID, using = "okbutton")
	private WebElement registerBtn;

	public RegisterForUnderstandingTheHomeTabDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public void register(String firstName, String lastName, String email, String company) {
		firstNameTextfield.sendKeys(firstName);
		lastNameTextfield.sendKeys(lastName);
		emailAddressTextfield.sendKeys(email);
		registerBtn.click();
	}

}

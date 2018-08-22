package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.SeleniumUtils;

public class TermsOfUsePage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnAccept")
	private WebElement acceptBtn;

	@FindBy(how = How.LINK_TEXT, using = "Sign Out")
	private WebElement signOutLink;

	public TermsOfUsePage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Terms Of Use Page");
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public HomePage clickAcceptBtn() {
		extentTest.log(Status.INFO, "Clicking the 'I Accept' button in the terms of use page");
		try {
			if (acceptBtn.isDisplayed())
				acceptBtn.click();
		} catch (Exception ex) {

		}
		return new HomePage(driver);
	}

	public LoginPage clickOnSignOutButton() {
		extentTest.log(Status.INFO, "Click on SignOut Link");
		SeleniumUtils.isClickable(signOutLink, driver);
		signOutLink.click();
		return new LoginPage(driver);
	}
}

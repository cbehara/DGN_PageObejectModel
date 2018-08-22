package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.SeleniumUtils;

public class AccessPendingPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.LINK_TEXT, using = "Sign Out")
	private WebElement signOutLink;

	@FindBy(how = How.XPATH, using = "//div[@id='licenseSectionText']/div")
	private List<WebElement> msgText;

	public AccessPendingPage(WebDriver driver) {
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

	public LoginPage clickOnSignOutButton() {
		extentTest.log(Status.INFO, "Click on SignOut Link");
		SeleniumUtils.isClickable(signOutLink, driver);
		signOutLink.click();
		return new LoginPage(driver);
	}

	public boolean isMessageAsExpected() {
		Boolean isMatch = false;
		extentTest.log(Status.INFO, "Verify if the message is as expected");
		String msg1 = msgText.get(0).getText().trim();
		String msg2 = msgText.get(1).getText().trim();
		if (msg1.equalsIgnoreCase("Your access is pending the account administrator's approval"))
			isMatch = true;
		if (msg2.equalsIgnoreCase("The account administrator will notify you"))
			isMatch = true;
		return isMatch;
	}
}

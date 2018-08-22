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

public class UserSelectionPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'LinkButtonAdminUsers')]")
	private WebElement linkManageUsers;
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'LinkButtonAdminTemplates')]")
	private WebElement linkManageProfiles;
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'LinkButtonAdminPreferences')]")
	private WebElement linkGlobalPreferences;
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'LinkButtonAdminReporting')]")
	private WebElement linkReports;

	public UserSelectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public ManageUsers clickOnManageUserLink() {
		extentTest.log(Status.INFO, "click on manage User Link");
		linkManageUsers.click();
		return new ManageUsers(driver);
	}

	public ManageUsers clickOnManageProfileLink() {
		extentTest.log(Status.INFO, "click on manage profile Link");
		linkManageProfiles.click();
		return new ManageUsers(driver);
	}

	public ManageUsers clickOnReportsLink() {
		extentTest.log(Status.INFO, "click on Reports Link");
		linkReports.click();
		return new ManageUsers(driver);
	}

	public boolean isManageUsersLinkPresent() {
		extentTest.log(Status.INFO, "Verify Manage User link.");
		SeleniumUtils.isVisible(linkManageUsers, driver);
		return linkManageUsers.isDisplayed();
	}

	public boolean isManageProfilesLinkPresent() {
		extentTest.log(Status.INFO, "Verify Manage Profiles link.");
		SeleniumUtils.isVisible(linkManageProfiles, driver);
		return linkManageProfiles.isDisplayed();
	}

	public boolean isGlobalPreferencesLinkPresent() {
		extentTest.log(Status.INFO, "Verify Global Preferences link.");
		SeleniumUtils.isVisible(linkGlobalPreferences, driver);
		return linkGlobalPreferences.isDisplayed();
	}

	public boolean isReportsLinkPresent() {
		extentTest.log(Status.INFO, "Verify Reports link.");
		SeleniumUtils.isVisible(linkReports, driver);
		return linkReports.isDisplayed();
	}

}

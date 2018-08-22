package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class PrintCompanyDetailsPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@id='dvProjectDetail']//h2/span")
	private List<WebElement> projectTitlesWithSpecAlerts;

	@FindBy(how = How.ID, using = "ctl011_SpecListLabel")
	private WebElement specAlertsLabel;

	@FindBy(how = How.ID, using = "ctl011_TrackingListLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.LINK_TEXT, using = "Print")
	private WebElement printbutton;

	@FindBy(how = How.ID, using = "lnkBtnBack")
	private WebElement backButton;

	@FindBy(how = How.CSS, using = "div[class='trackListingNames']>span[class*='printCompanyNames']")
	private List<WebElement> trackingNameListForPrintCompanyDetails;

	@FindBy(how = How.CSS, using = "[id*='TrackListName']")
	private List<WebElement> trackingNameListForPrintCompanyList;

	@FindBy(how = How.CSS, using = "div[class*='companyDetailsSection']")
	private List<WebElement> companyTitleList;

	public PrintCompanyDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Print Project Details Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean IsProjectTitlesSameAsSelectedFromProjectResults(
			List<String> selectedProjectWithSpecAlertsInProjectResults) {
		extentTest.log(Status.INFO,
				"Verify if the selected projects having spec alerts from project results matches the projects displayed in the print project details page");
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(projectTitlesWithSpecAlerts),
				selectedProjectWithSpecAlertsInProjectResults);
	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the trackign list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public Boolean isPrintButtonDisplayed() {
		extentTest.log(Status.INFO, "Check if print button is displayed");
		return printbutton.isDisplayed();
	}

	public Boolean isBackButtonDisplayed() {
		extentTest.log(Status.INFO, "Check if back button is displayed");
		return backButton.isDisplayed();
	}

	public void clickBackButton() {
		extentTest.log(Status.INFO, "Clickf back button.");
		backButton.click();
	}

	public List<String> getTrackingListNameForPrintCompanyDetails() {
		extentTest.log(Status.INFO, "Get tracking list name.");
		List<String> trackNameList = new ArrayList<String>();
		for (WebElement trackingName : trackingNameListForPrintCompanyDetails) {
			trackNameList.add(trackingName.getText());
		}
		return trackNameList;
	}

	public List<String> getTrackingListNameForPrintCompanyList() {
		extentTest.log(Status.INFO, "Get tracking list name for print company list.");
		List<String> trackNameList = new ArrayList<String>();
		for (WebElement trackingName : trackingNameListForPrintCompanyList) {
			trackNameList.add(trackingName.getText());
		}
		return trackNameList;
	}

	public List<String> getCompanyTitleList() {
		extentTest.log(Status.INFO, "Get Company titles on print company details page.");
		return CommonUtils.getListFromWebElements(companyTitleList);
	}

}

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
import com.ddaqe.utils.CommonUtils;

public class PrintCompanyListPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'specAlertOnSummary')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithSpecAlertsName;

	@FindBy(how = How.CSS, using = "#_specText>strong")
	private WebElement specAlertsLabel;

	@FindBy(how = How.CSS, using = "#ctl01_TrackingListLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'companyName')]")
	private List<WebElement> companyTitleList;

	@FindBy(how = How.ID, using = "chkIncludeFilters")
	private WebElement includeFilters_cbk;
	@FindBy(how = How.ID, using = "ctl000_subHeader")
	private WebElement AppliedFilter_subheader_txt;

	public PrintCompanyListPage(WebDriver driver) {
		this.driver = driver;
		getTitle();
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Print Project List Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean IsProjectTitlesSameAsSelectedFromProjectResults(
			List<String> selectedProjectWithSpecAlertsInProjectResults) {
		extentTest.log(Status.INFO,
				"Verify if the selected projects having spec alerts from project results matches the projects displayed in the print project list page");
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(projectTitlesWithSpecAlertsName),
				selectedProjectWithSpecAlertsInProjectResults);
	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public boolean compareCompanyTitleList(List<String> exptList) {
		boolean compTitleFlag = false;

		for (String expCompTitle : exptList) {
			if (expCompTitle.trim() != null && expCompTitle.trim().length() > 0) {
				compTitleFlag = false;
				for (WebElement compTitle : companyTitleList) {
					if (compTitle.getText().trim().equalsIgnoreCase(expCompTitle.trim())) {
						compTitleFlag = true;
						break;
					}
				}
			}
			if (compTitleFlag == false) {
				extentTest.log(Status.INFO, expCompTitle.trim() + " is not present on print company detail page.");
				break;
			}
		}
		return compTitleFlag;
	}

	// check if Include Filter Checkbox Present
	public boolean isIncludeFilterCheckboxPresent() {
		extentTest.log(Status.INFO, "Check if Include Filter Checkbox Present.");
		return includeFilters_cbk.isDisplayed();
	}

	public boolean isIncludeFilterCheckboxSelected() {
		extentTest.log(Status.INFO, "Check if Include Filter Checkbox Present.");
		return includeFilters_cbk.isSelected();
	}

	public String getAppliedFilter_subheader_txt() {
		includeFilters_cbk.click();
		extentTest.log(Status.INFO, "Get applied filter text");
		return AppliedFilter_subheader_txt.getText();
	}
}

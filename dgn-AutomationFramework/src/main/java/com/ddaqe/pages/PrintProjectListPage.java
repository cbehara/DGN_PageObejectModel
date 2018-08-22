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
import com.ddaqe.utils.SeleniumUtils;

public class PrintProjectListPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'specAlertOnSummary')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithSpecAlertsName;

	@FindBy(how = How.XPATH, using = "//div[@id='_specText']/strong")
	private WebElement specAlertsLabel;

	@FindBy(how = How.ID, using = "ctl01_TrackingListLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.XPATH, using = "//table[contains(@class,'printProjectHeader')]//a[contains(.,'Print')]")
	private WebElement printLink;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/logo.png']")
	private WebElement printDodgeLogo;

	@FindBy(how = How.ID, using = "lnkBtnBack")
	private WebElement backButton;

	@FindBy(how = How.ID, using = "chkFilter")
	private WebElement includeFilters_cbk;
	@FindBy(how = How.ID, using = "ctl04_header")
	private WebElement ForthFilterHeader_lbl;
	@FindBy(how = How.ID, using = "ctl040_subHeader")
	private WebElement ForthFilterSubHeader1_lbl;
	@FindBy(how = How.ID, using = "ctl041_subHeader")
	private WebElement ForthFilterSubHeader2_lbl;
	@FindBy(how = How.ID, using = "ctl03_headerText")
	private WebElement ThridFilterHeader_txt;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'projectTitle')]")
	private List<WebElement> projectTitleList;

	@FindBy(how = How.ID, using = "ctl00_header")
	private WebElement IncludeFilterSectionPublisher;

	public PrintProjectListPage(WebDriver driver) {
		this.driver = driver;
		getTitle();
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Print Project List Page");
	}

	public boolean isprintDodgeLogoDisplayed() {
		extentTest.log(Status.INFO, "Checking for visiblity of Dodge logo when printing");
		SeleniumUtils.scrollToView(driver, printDodgeLogo);
		return printDodgeLogo.isDisplayed();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean IsProjectTitlesSameAsSelectedFromProjectResults(
			List<String> selectedProjectWithSpecAlertsInProjectResults) {
		extentTest.log(Status.INFO,
				"Verify if the selected projects having spec alerts from project results matches the projects displayed in the print project list page");
		CommonUtils.IterateList(CommonUtils.getListFromWebElements(projectTitlesWithSpecAlertsName));
		CommonUtils.IterateList(selectedProjectWithSpecAlertsInProjectResults);
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(projectTitlesWithSpecAlertsName),
				selectedProjectWithSpecAlertsInProjectResults);
	}

	public void clickOnBackButton() {
		extentTest.log(Status.INFO, "Click on back button on Print project details Page");
		backButton.click();
	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public boolean isPrintLinkClickable() {
		extentTest.log(Status.INFO, "Verify whether the print link is clickable");
		return SeleniumUtils.isClickable(printLink, driver);
	}

	public void ClickonincludeFilters_cbk() {
		extentTest.log(Status.INFO, "Click on include filters checkbox");
		includeFilters_cbk.click();
		SeleniumUtils.isVisible(IncludeFilterSectionPublisher, driver);
	}

	// check if Include Filter Checkbox Present
	public boolean isIncludeFilterCheckboxPresent() {
		extentTest.log(Status.INFO, "Check if Include Filter Checkbox Present.");
		return CommonUtils.checkElementExist(includeFilters_cbk, driver);
	}

	// check if Include Filter is selected
	public boolean isIncludeFilterCheckboxSelected() {
		extentTest.log(Status.INFO, "Check if Include Filter is selected.");
		return includeFilters_cbk.isSelected();
	}

	// check if Include Filter result present on selection of Include Filter
	// checkbox
	public boolean verifyResultOfIncludeFilterCheckboxSelection() {
		String expectedResultValue = "block";
		extentTest.log(Status.INFO, "Check Result Of Include Filter Checkbox Selection.");
		String resultOfSelection = includeFilters_cbk.getCssValue("display");

		return resultOfSelection.toLowerCase().trim().contains(expectedResultValue.toLowerCase());
	}

	public String getForthFilterHeader_lbl() {
		extentTest.log(Status.INFO, "Get forth filter header label");
		return ForthFilterHeader_lbl.getText();
	}

	public String getForthFilterSubHeader1_lbl() {
		extentTest.log(Status.INFO, "Get forth filter first subheader label");
		return ForthFilterSubHeader1_lbl.getText();
	}

	public String getForthFilterSubHeader2_lbl() {
		extentTest.log(Status.INFO, "Get forth filter second subheader label");
		return ForthFilterSubHeader2_lbl.getText();
	}

	public String getThridFilterHeader_txt() {
		extentTest.log(Status.INFO, "Get ThridFilterHeader text");
		return ThridFilterHeader_txt.getText();
	}

	// Get the list of project title present on print project details page.
	public List<String> getListProjectTitlePrintProjectDetails() {
		extentTest.log(Status.INFO, "Get the list of project title present on print project details page.");
		SeleniumUtils.isVisible(backButton, driver);
		return CommonUtils.getListFromWebElements(projectTitleList);
	}

	public boolean compareProjectTitleList(List<String> exptList) {
		boolean projTitleFlag = false;

		for (String expProjTitle : exptList) {
			if (expProjTitle.trim() != null && expProjTitle.trim().length() > 0) {
				projTitleFlag = false;
				for (WebElement projTitle : projectTitleList) {
					if (projTitle.getText().trim().equalsIgnoreCase(expProjTitle.trim())) {
						projTitleFlag = true;
						break;
					}
				}
			}
			if (projTitleFlag == false) {
				extentTest.log(Status.INFO, expProjTitle.trim() + " is not present on print project detail page.");
				break;
			}
		}
		return projTitleFlag;
	}
}

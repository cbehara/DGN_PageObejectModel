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
import com.ddaqe.utils.SeleniumUtils;

public class PrintProjectDetailsPage {

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

	@FindBy(how = How.XPATH, using = "//img[@src='Images/logo.png']")
	private WebElement printDodgeLogo;

	@FindBy(how = How.ID, using = "lnkBtnBack")
	private WebElement backButton;

	@FindBy(how = How.XPATH, using = "//table[@class='table-print-header']//tr[2]/td/b")
	private WebElement printProjectTitle;

	@FindBy(how = How.XPATH, using = "//table[@class='table-print-header']//tr[3]/td/b")
	private WebElement printDRNumber;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Valuation:')]/..//td[4]")
	private WebElement printValuationCode;

	@FindBy(how = How.XPATH, using = "//th[text()='Key Contacts and Bid Documents']")
	private WebElement lowestBidderSection;

	@FindBy(how = How.XPATH, using = "//span[text()='Planholders']")
	private WebElement planholdersSection;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'notesText')]")
	private WebElement notesText;

	@FindBy(how = How.XPATH, using = "//table[@class='table-print']//tr//td[2]")
	private List<WebElement> planTitleListOnPrintDetailPage;

	@FindBy(how = How.XPATH, using = "//*[text()[contains(.,'Door')]]")
	private List<WebElement> locatingDoorKeyword;

	@FindBy(how = How.XPATH, using = "//*[text()[contains(.,'Keywords')]]")
	private WebElement keywordInHeader;

	@FindBy(how = How.CSS, using = "div[id*='TrackingListContainer']>a")
	private List<WebElement> trackingNameList;

	public boolean isprintDodgeLogoDisplayed() {
		extentTest.log(Status.INFO, "Checking for visiblity of Dodge logo when printing");
		SeleniumUtils.scrollToView(driver, printDodgeLogo);
		return printDodgeLogo.isDisplayed();
	}

	public PrintProjectDetailsPage(WebDriver driver) {
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

	public void clickOnBackButton() {
		extentTest.log(Status.INFO, "Click on back button on Print project details Page");
		backButton.click();
	}

	// Click on back button on Print project details Page and return object of
	// Project Addenda Page.
	public ProjectAddendaPage clickOnBackButtonReturnToAddendaPage() {
		extentTest.log(Status.INFO,
				"Click on back button on Print project details Page and return to Project Addenda Page.");
		backButton.click();
		return new ProjectAddendaPage(driver);
	}

	// Click on back button on Print project details Page and return object of
	// Spec alerts result Page.
	public SpecAlertsResultsPage clickOnBackButtonReturnToSpecAlertResult() {
		extentTest.log(Status.INFO,
				"Click on back button on Print project details Page and return to Project Addenda Page.");
		backButton.click();
		return new SpecAlertsResultsPage(driver);
	}

	// verify Print Project Title and return the result.
	public boolean verifyPrintProjectTitlePrintAddendaList(String exptedPrintProjectTitle) {
		extentTest.log(Status.INFO, "verify Print Project Title.");
		return printProjectTitle.getText().trim().toUpperCase().equals(exptedPrintProjectTitle.trim().toUpperCase());
	}

	// verify Print DR Number and return the result.
	public boolean verifyPrintDRNumberPrintAddendaList(String exptedDRNumber) {
		extentTest.log(Status.INFO, "verify Print DR Number.");
		return printDRNumber.getText().trim().toUpperCase().contains(exptedDRNumber.trim().toUpperCase());
	}

	// verify Project Valuation code and return the result.
	public boolean verifyValuationcodePrintProjectDetails(String exptedValuationcode) {
		extentTest.log(Status.INFO, "verify Project Valuation code.");
		return printValuationCode.getText().trim().toUpperCase().equals(exptedValuationcode.trim().toUpperCase());
	}

	// Check add notes is present or not on print detail page and return the
	// result.
	public boolean checkAddNotesPresent(String addedNotes) {
		extentTest.log(Status.INFO, "Check add notes is present or not on print detail page.");
		return driver.getPageSource().contains(addedNotes);
	}

	// Verify the lowest bidder section is present on print project detail page
	public boolean verifyLowestBidderSection() {
		extentTest.log(Status.INFO, "Verify the lowest bidder section is present on print project detail page.");
		return lowestBidderSection.isDisplayed();
	}

	// Verify the Planholders section is present on print project detail page
	public boolean verifyPlanholdersSection() {
		extentTest.log(Status.INFO, "Verify the Planholders section is present on print project detail page.");
		return planholdersSection.isDisplayed();
	}

	public String getnotesText() {
		extentTest.log(Status.INFO, "get notes text");
		return notesText.getText();
	}

	// Compare the count of Plan title displayed on Print Plan List page.
	public boolean checkPlanTitleCount(int count) {
		extentTest.log(Status.INFO, "Compare the count of Plan title displayed on Print Plan List page.");
		return planTitleListOnPrintDetailPage.size() - 1 == count;
	}

	public boolean checkForMatchWordHighlightedInYellowForFirstKeyword() {
		extentTest.log(Status.INFO, "Check highlighted keyword background color");
		return locatingDoorKeyword.get(0).getCssValue("background").equals("#ffff00");

	}

	public Boolean checkForDoorKeywordPresentInPrintPageHeader() {
		extentTest.log(Status.INFO, "Check match keyword in print page header");
		try {
			keywordInHeader.getText();
			if (keywordInHeader.getText() == "door") {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public List<String> getTrackingListName() {
		extentTest.log(Status.INFO, "get tracking list name.");
		List<String> trackNameList = new ArrayList<String>();
		for (WebElement trackingName : trackingNameList) {
			trackNameList.add(trackingName.getText());
		}
		return trackNameList;
	}

}

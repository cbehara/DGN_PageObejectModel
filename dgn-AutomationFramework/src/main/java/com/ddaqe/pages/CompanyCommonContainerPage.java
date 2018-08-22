package com.ddaqe.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.SeleniumUtils;

public class CompanyCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkTrackCompany")
	private WebElement trackCompanyLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkNotes")
	private WebElement notesTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkProfileSpan")
	private WebElement companyProfileLink;

	@FindBy(how = How.LINK_TEXT, using = "Contacts")
	private WebElement contactsLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkProjects")
	private WebElement projectsLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Company Details")
	private WebElement printCompanyDetailsLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Company List")
	private WebElement printCompanyListLink;

	@FindBy(how = How.LINK_TEXT, using = "View Projects")
	private WebElement viewProjectsActionsOptions;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ddlPageSize")
	private WebElement resultsPerPage;

	@FindBy(how = How.ID, using = "actions-link")
	private WebElement actionsDiv;

	public CompanyCommonContainerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public Boolean mouseOverActionandChecktrackCompanyDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Track Company Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Track Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyLink));
		return trackCompanyLink.isDisplayed();
	}

	public TrackPopUpPage clickOnTrackCompanyActionsLink() {
		extentTest.log(Status.INFO, "Click on the Track Company Actions Link");
		trackCompanyLink.click();
		return new TrackPopUpPage(driver);
	}

	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions Dropdown");
		actionsDropdowm.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyLink));
	}

	public Boolean isNotesTabDisplayed() {
		extentTest.log(Status.INFO, "Check if Notes Tab is displayed");
		return notesTab.isDisplayed();
	}

	public void selectResultsPerPage(String value) {
		Select select = new Select(resultsPerPage);
		select.selectByVisibleText(value);
	}

	public String getResultsPerPage() {
		Select select = new Select(resultsPerPage);
		return select.getFirstSelectedOption().toString();
	}

	public CompanyNotesPage clickOnNotesTab() {
		extentTest.log(Status.INFO, "Clicking on Notes Tab");
		notesTab.click();
		return new CompanyNotesPage(driver);
	}

	public CompanyContactsPage clickOnCompanyContactsLink() {
		extentTest.log(Status.INFO, "Click on the Company Contacts Link");
		SeleniumUtils.isVisible(contactsLink, driver);
		contactsLink.click();
		return new CompanyContactsPage(driver);
	}

	public CompanyProjectsPage clickOnCompanyProjectsLink() {
		extentTest.log(Status.INFO, "Click on the Company Projects Link");
		SeleniumUtils.isVisible(projectsLink, driver);
		projectsLink.click();
		return new CompanyProjectsPage(driver);
	}

	public boolean isNotesTabDisabled() {
		extentTest.log(Status.INFO, "Check if Notes tab is Disabled");
		return notesTab.getAttribute("class").equals("disable");
	}

	public CompanyPage clickOnCompanyProfile() {
		extentTest.log(Status.INFO, "Clicking on Company Profile");
		companyProfileLink.click();
		return new CompanyPage(driver);
	}

	public PrintCompanyDetailsPage clickOnPrintCompanyDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Company Details' under Actions Dropdown");
		printCompanyDetailsLink.click();
		return new PrintCompanyDetailsPage(driver);
	}

	public PrintCompanyListPage clickOnPrintCompanyListUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Company List' under Actions Dropdown");
		printCompanyListLink.click();
		return new PrintCompanyListPage(driver);
	}

	public ProjectPage clickOnViewProjectsFromActions() {
		extentTest.log(Status.INFO, "Clicking on View Projects under Actions Dropdown in Company Project Page");
		viewProjectsActionsOptions.click();
		return new ProjectPage(driver);
	}

	public String verifyActionsDropdownValues() {
		String actionsDropdownValues = actionsDiv.getText();
		Assert.assertTrue(actionsDiv.isDisplayed());
		return actionsDropdownValues;
	}

	public boolean isPrintProjectDetailsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Print Company Details link present under actions dropdown'");
		return printCompanyDetailsLink.isDisplayed();
	}

}

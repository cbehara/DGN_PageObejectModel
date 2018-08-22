package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class CompanyPage extends CompanyCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "lnkAddnotes")
	private WebElement addnotesMenu;

	@FindBy(how = How.ID, using = "lnkEditnotes")
	private WebElement editnotesMenu;

	@FindBy(how = How.CLASS_NAME, using = "totalProjects")
	private WebElement totalProjectsSection;

	@FindBy(how = How.ID, using = "lnkEmailCompany")
	private WebElement emailCompaniesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.ID, using = "lnkDownloadCompany")
	private WebElement downloadCompanyActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_divCompanyName")
	private WebElement companyOverviewSection;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_divAddress")
	private WebElement companyAddressInOverviewSection;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkNotes")
	private WebElement notesTab;

	@FindBy(how = How.XPATH, using = "//div[@class='companyTrackedIcon']//img")
	private WebElement trackCompanyIcon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_imgAlertIcon")
	private WebElement alertCompanyIcon;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_companyOverview_divCompanyTrackedSection']//a")
	private List<WebElement> trackCompanyList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkTrackCompany")
	private WebElement trackCompanyLink;

	@FindBy(how = How.ID, using = "lnkHostCompany")
	private WebElement hostCompanyLink;

	@FindBy(how = How.LINK_TEXT, using = "Map Company")
	private WebElement mapCompanyLink;

	@FindBy(how = How.CLASS_NAME, using = "trackCompanyNames")
	private WebElement trackingListLabel;

	@FindBy(how = How.XPATH, using = ".//*[@id='divCompanyDetails']/div[1]")
	private WebElement breadCrumb;

	@FindBy(how = How.ID, using = "Project-Results")
	private WebElement projectResultsBreadCrumb;

	@FindBy(how = How.ID, using = "Project")
	private WebElement projectBreadCrumb;

	@FindBy(how = How.ID, using = "Firms")
	private WebElement firmsBreadCrumb;

	@FindBy(how = How.LINK_TEXT, using = "Next ")
	private WebElement nextLink;

	@FindBy(how = How.ID, using = "prev-next-nav")
	private WebElement paginationDiv;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblTotalValuations")
	private WebElement lblTotalValuations;

	@FindBy(how = How.XPATH, using = "//img[@src='images/map_icon.png']")
	private WebElement mapIconLink;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'companyNavigator_actionsNav')]//a")
	private List<WebElement> companyActionMenuList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblTotalProjectsVal")
	private WebElement totalProjectCount;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblTotalProjects")
	private WebElement totalProjects;

	@FindBy(how = How.ID, using = "downloadCompaniesPopup")
	private WebElement downloadCompanyPopUp;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblTotalValuations")
	private WebElement valuations;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_divAka")
	private WebElement alsoKnownas;

	@FindBy(how = How.ID, using = "pagination")
	private WebElement pagination;

	@FindBy(how = How.LINK_TEXT, using = "Print Company Details")
	private WebElement printCompanyDetailsLink;

	@FindBy(how = How.CSS, using = "a[id*='lnkPrintCompanyListing']")
	private WebElement printCompanyListLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lblCompProject')]")
	private List<WebElement> projectUpdateHyperLink;

	@FindBy(how = How.XPATH, using = "//div[@id='tdMapIcon']//img")
	private WebElement mapIcon;

	@FindBy(how = How.ID, using = "googleMap")
	private WebElement mapSection;

	@FindBy(how = How.ID, using = "Company-Results-(My-Companies)")
	private WebElement companyResultsLink;

	public CompanyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Company Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getPaginationDivText() {
		return paginationDiv.getText().toString();
	}

	public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText().trim();
	}

	public String getCurrentURl() {
		return driver.getCurrentUrl();
	}

	@Override
	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions Dropdown");
		actionsDropdown.click();
	}

	// Check Actions Dropdown is displayed
	public boolean isActionsDropDownDisplayed() {
		extentTest.log(Status.INFO, "Check Actions Dropdown is displayed");
		return CommonUtils.checkElementExist(actionsDropdown, driver);
	}

	public void clickOnNextLink() {
		extentTest.log(Status.INFO, "Click on Next Link");
		nextLink.click();
	}

	public NotesPage clickOnAddNotesMenu() {
		extentTest.log(Status.INFO, "Click on Add notes from the dropdown menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addnotesMenu));
		addnotesMenu.click();
		return new NotesPage(driver);
	}

	public NotesPage clickOnEditNotesMenu() {
		extentTest.log(Status.INFO, "Click on Edit notes from the dropdown menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(editnotesMenu));
		editnotesMenu.click();
		return new NotesPage(driver);
	}

	public Boolean isCompanyOverviewDisplayed() {
		extentTest.log(Status.INFO, "Check if compnay overview section is displayed");
		return companyOverviewSection.isDisplayed();
	}

	public String getCompanyAddressInOverviewSection() {
		extentTest.log(Status.INFO, "getting compnay address in overview section.");
		return companyAddressInOverviewSection.getText();
	}

	public Boolean isTotalProjectsDisplayed() {
		extentTest.log(Status.INFO, "Check if total projects is displayed");
		return totalProjects.isDisplayed();
	}

	public Boolean isValuationDisplayed() {
		extentTest.log(Status.INFO, "Check if valuation is displayed");
		return valuations.isDisplayed();
	}

	public Boolean isAlsoKnownAsDisplayed() {
		extentTest.log(Status.INFO, "Check if alsoknownas is displayed");
		return alsoKnownas.isDisplayed();
	}

	public EmailAlertsPage clickEmailProjectsLinkUnderActionsDrpDwn() {
		extentTest.log(Status.INFO, "Click email companies under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailCompaniesMenu));
		emailCompaniesMenu.click();
		return new EmailAlertsPage(driver);
	}

	@Override
	public Boolean isNotesTabDisplayed() {
		extentTest.log(Status.INFO, "Check if Notes Tab is displayed");
		return notesTab.isDisplayed();
	}

	public boolean isNotesTabClickable() {
		extentTest.log(Status.INFO, "Check if Notes tab is Clickable");
		return SeleniumUtils.isClickable(notesTab, driver);
	}

	@Override
	public CompanyNotesPage clickOnNotesTab() {
		extentTest.log(Status.INFO, "Clicking on Notes Tab");
		notesTab.click();
		return new CompanyNotesPage(driver);
	}

	public ProjectResultsPage clickOnProjectResultsreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on Project ResultsBreadCrumb");
		projectResultsBreadCrumb.click();
		return new ProjectResultsPage(driver);
	}

	public ProjectPage clickOnProjectBreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on Project BreadCrumb");
		projectBreadCrumb.click();
		return new ProjectPage(driver);
	}

	public ProjectFirmsPage clickOnFirmsBreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on Firms BreadCrumb");
		firmsBreadCrumb.click();
		return new ProjectFirmsPage(driver);
	}

	@Override
	public boolean isNotesTabDisabled() {
		extentTest.log(Status.INFO, "Check if Notes tab is Disabled");
		return notesTab.getAttribute("class").equals("disable");
	}

	public String getURL() {
		extentTest.log(Status.INFO, "Get URL");
		return driver.getCurrentUrl();
	}

	public boolean verifyTrackNameInTrackingList(String TrackName) {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		CommonUtils.IterateWebElementsList(trackCompanyList);
		return CommonUtils.getListFromWebElements(trackCompanyList).contains(TrackName);
	}

	public boolean verifyTrackNameInExistingTrackingList(List<String> TrackName) {
		Boolean isMatched = false;
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		for (int i = 0; i < TrackName.size(); i++) {
			for (int j = 0; j < trackCompanyList.size(); j++) {
				if (TrackName.get(i).trim().equalsIgnoreCase(trackCompanyList.get(j).getText().trim())) {
					isMatched = true;
				}
			}
		}
		return isMatched;
	}

	public int getLengthTrackingListNameMatched(String TrackName) {
		CommonUtils.IterateWebElementsList(trackCompanyList);
		if (CommonUtils.getListFromWebElements(trackCompanyList).contains(TrackName)) {
			return TrackName.length();
		}
		return 0;

	}

	public boolean isTrackingIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Tracking Icon displayed");
		return trackCompanyIcon.isDisplayed();
	}

	public String getOriginalTitleTracking() {
		return trackCompanyIcon.getAttribute("original-title");
	}

	public boolean isAlertIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Alert Icon displayed");
		return alertCompanyIcon.isDisplayed();
	}

	public boolean isTrackingIconNotDisplayed() {
		try {
			extentTest.log(Status.INFO, "Verify Tracking Icon is not displayed");
			if (trackCompanyIcon.getSize().equals(0)) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException ex) {
			extentTest.log(Status.INFO, "Tracking Icon is not displayed successfully");
			return true;
		}
	}

	@Override
	public Boolean mouseOverActionandChecktrackCompanyDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Track Company Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Track Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyLink));
		return trackCompanyLink.isDisplayed();
	}

	public Boolean mouseOverActionandCheckMapCompanyDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Map Company Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Map Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mapCompanyLink));
		return mapCompanyLink.isDisplayed();
	}

	@Override
	public TrackPopUpPage clickOnTrackCompanyActionsLink() {
		extentTest.log(Status.INFO, "Click on the Track Company Actions Link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyLink));
		trackCompanyLink.click();
		return new TrackPopUpPage(driver);
	}

	public HostToDocuProPopupDialog clickOnHostCompanyActionsLink() {
		extentTest.log(Status.INFO, "Click on the Host Company Actions Link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hostCompanyLink));
		hostCompanyLink.click();
		return new HostToDocuProPopupDialog(driver);
	}

	public TrackPopUpPage mouseOverActionandClickTrackProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Click tracking companies under Actions menu");
		return clickOnTrackCompanyActionsLink();
	}

	public PrintCompanyDetailsPage mouseOverActionAndClickPrintCompanyDetailsLink() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Click Company Details Link under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printCompanyDetailsLink));
		printCompanyDetailsLink.click();
		return new PrintCompanyDetailsPage(driver);
	}

	public void ScrollCompanyPageToViewAllTrackingList() {
		SeleniumUtils.scrollToView(driver, totalProjectsSection);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public boolean verifyPageDisplayed(String text) {
		extentTest.log(Status.INFO, "Verify whether the pagination div contains" + text);
		return paginationDiv.getText().contains(text);
	}

	public ProjectResultsPage clickTrackNameInExistingTrackingList() {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		for (int i = 0; i < trackCompanyList.size(); i++) {
			trackCompanyList.get(i).click();
			break;
		}
		return new ProjectResultsPage(driver);
	}

	public boolean IsPageinationDisplayed() {
		extentTest.log(Status.INFO, "Ensure that the Pagination is displayed");
		return paginationDiv.isDisplayed();
	}

	public String get_lblTotalValuations() {
		extentTest.log(Status.INFO, "Get total valuation label");
		return lblTotalValuations.getText();
	}

	// Download Company action menu is displayed.
	public boolean isDownloadCompanyActionMenuDisplayed() {
		extentTest.log(Status.INFO, "Download Company action menu is displayed.");
		return CommonUtils.checkElementExist(downloadCompanyActionMenu, driver);
	}

	public DownloadCompanies mouseOverActionandClickDownloadCompanies() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Download Companies is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadCompanyActionMenu));
		downloadCompanyActionMenu.click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("rbPdf")));
		return new DownloadCompanies(driver);
	}

	// Download Company action menu is displayed.
	public Boolean mouseOverActionandCheckdownloadCompanyDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Doenload Company Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Download Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadCompanyActionMenu));
		return downloadCompanyActionMenu.isDisplayed();
	}

	public Boolean isPopUpDownloadCompanyDisplayed() {
		extentTest.log(Status.INFO, "Download Project is displayed");
		return downloadCompanyPopUp.isDisplayed();
	}

	// Check map icon displayed on the company result page.
	public boolean isMapIconLinkDisplayed() {
		extentTest.log(Status.INFO, "Check map icon displayed on the company result page.");
		return CommonUtils.checkElementExist(mapIconLink, driver);
	}

	// Verify options of Company Actions List and return the result.
	public boolean verifyCompanyPageActionsList() {
		extentTest.log(Status.INFO, "Verify options of Company Actions List.");
		List<String> actionsOptionsUIList = new ArrayList<String>();
		actionsOptionsUIList.addAll(CommonUtils.getListFromWebElements(companyActionMenuList));
		int count = 0;
		for (String optionText : actionsOptionsUIList) {
			if (StringUtils.isNotEmpty(optionText)) {
				count++;
			}
		}
		return count > 0;
	}

	public List<String> getCompanyActionsMenuItemList() {
		extentTest.log(Status.INFO, "Get Menu item list from Company Actions.");
		return CommonUtils.getListFromWebElements(companyActionMenuList);
	}

	// Get the total Project count.
	public String getTotalProjectCount() {
		extentTest.log(Status.INFO, "Get the total Project count.");
		return totalProjectCount.getText().split("\\(")[0].trim();
	}

	// Check the project total count is thousand seperated
	public boolean checkProjectCountThausandSeparated() {
		extentTest.log(Status.INFO, "Check the project total count is thousand seperated.");
		return String.format("%,d", Integer.parseInt(getTotalProjectCount().replace(",", "")))
				.equals(getTotalProjectCount());
	}

	// Check project update on the company page is hyperlink.
	public boolean checkProjectUpdateIsHyperLink() {
		extentTest.log(Status.INFO, "Check project update on the company page is hyperlink.");
		SeleniumUtils.isVisible(projectUpdateHyperLink.get(0), driver);
		return projectUpdateHyperLink.get(0).getAttribute("href") != null;
	}

	public void clickMapIcon() {
		extentTest.log(Status.INFO, "Click the map icon");
		mapIcon.click();
	}

	public void clickMapSection() {
		extentTest.log(Status.INFO, "Click the map ection");
		mapSection.click();
		CommonUtils.switchToNewTab(driver);
	}

	public boolean isMapSectionDisplayed() {
		extentTest.log(Status.INFO, "Verify if the map section displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mapSection));
		return mapSection.isDisplayed();
	}

	public CompanyResultsPage clickOnCompanyResultsLink() {
		extentTest.log(Status.INFO, "Click on Company Results (My Companies) link.");
		companyResultsLink.click();
		return new CompanyResultsPage(driver);
	}

}

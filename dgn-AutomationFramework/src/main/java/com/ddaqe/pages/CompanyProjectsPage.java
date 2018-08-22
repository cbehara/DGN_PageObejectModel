package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

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

public class CompanyProjectsPage extends ProjectResultsPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkDownloadCompany")
	private WebElement downloadCompanyActionMenu;

	@FindBy(how = How.ID, using = "lnkEmailCompany")
	private WebElement emailCompanyActionMenu;

	@FindBy(how = How.ID, using = "lnkTrackCompany")
	private WebElement trackCompanyLink;

	@FindBy(how = How.ID, using = "lnkViewCompanyProjects")
	private WebElement viewProjectsActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_imgTrackedIcon")
	private WebElement trackCompanyIcon;

	@FindBy(how = How.LINK_TEXT, using = "Track Projects")
	private WebElement trackProjectsLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Company Details")
	private WebElement printCompanyDetailsLink;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'profileUI')]")
	private WebElement profileTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_lnkPrintProjectListing")
	private WebElement printProjectList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_companyOverview_divCompanyTrackedSection']//a")
	private List<WebElement> trackCompanyList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkProfile")
	private WebElement companyProfileLink;

	@FindBy(how = How.ID, using = "ctl00_chkProjectSelect")
	private WebElement firstProjectchkBox;

	@FindBy(how = How.ID, using = "ctl01_chkProjectSelect")
	private WebElement secondProjectchkBox;
	
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm_companyNavigator;

	@FindBy(how = How.ID, using = "lnkHostProject")
	private WebElement hostProjectActionsLink;
	
	@FindBy(how = How.ID, using = "lblErr")
	private WebElement errLabel;

	@FindBy(how = How.ID, using = "ctl00_lblProjectTitle")
	private WebElement firstProjectTitle;

	@FindBy(how = How.XPATH, using = ".//span[contains(@id,'lblProjectTitle')]")
	private WebElement ProjectTitles;
	@FindBy(how = How.XPATH, using = ".//span[contains(@id,'lblProjectTitle')]")
	private List<WebElement> ProjectTitlesList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.XPATH, using = ".//*[@id='main-content']/div[contains(@class,'breadcrumb-container clearfix')]")
	private WebElement breadCrumb;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_outOfLicenseMenu_lnkMyProjects")
	private WebElement myProjectsTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_outOfLicenseMenu_lnkOutsideLicense")
	private WebElement projectsNotInMyLicense;

	@FindBy(how = How.ID, using = "actions-link")
	private WebElement actionsDiv;

	@FindBy(how = How.XPATH, using = ".//a[contains(@id,'contentPlaceHolderHeader_ucPublishedDateFilter_lnkPublishedDate')]")
	private List<WebElement> publishedDateRangeList;

	@FindBy(how = How.ID, using = "iframe")
	private WebElement projectTypeEditLink;

	@FindBy(how = How.XPATH, using = ".//span[text()='Transportation']//ancestor::div[contains(@class,'subSectionLabel')]//preceding-sibling::div[contains(@class,'subSectionChkBox')]")
	private WebElement transportOptonselect;

	@FindBy(how = How.ID, using = "btnUpdateFancyBox")
	private WebElement clickOnUpdateButton;

	@FindBy(how = How.ID, using = "actionStageNav")
	private WebElement actionStageFilterTab;

	@FindBy(how = How.CSS, using = "[id*='lnkSelecetedFacetItem']")
	private List<WebElement> lnkSelecetedFacetItem;

	@FindBy(how = How.LINK_TEXT, using = "Action Stage")
	private WebElement actionStageOnCompanyPage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_btnSearch")
	private WebElement saveSearchBtn;

	@FindBy(how = How.XPATH, using = ".//a[contains(@id,'ucBaseNav_lnkHeader')]")
	private List<WebElement> filterList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucActionStageNav_ucBaseNav_lnkSeeMore")
	private WebElement actionStageFilterSeeMoreButton;

	@FindBy(how = How.XPATH, using = "//div[@id='dvActStage']//span[text()='Design']//..//..//input[@type='checkbox']")
	private WebElement designOptionselect;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucSearchBreadCrumb_lnkBtnClearAll")
	private WebElement clickOnClear;

	@FindBy(how = How.LINK_TEXT, using = "Geography")
	private WebElement clearAllFilterLink;

	@FindBy(how = How.ID, using = "geographyNavigatorFacet")
	private WebElement geographyFilter;

	@FindBy(how = How.XPATH, using = ".//div[@class='appliedFilter filterPanelCon']//span//span")
	private List<WebElement> filterCrumbDisplayed;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblAddress")
	private WebElement companyAddress;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyProjects_pagerTop_lblTopSearchResults")
	private WebElement resultPerPageAtTopOnCompanyProjPage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyProjects_pagerBottom_lblBottomSearchResults")
	private WebElement resultPerPageAtBottomOnCompanyProjPage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_lblTrackCompanyProjects")
	private WebElement trackProjectsMenu;

	public CompanyProjectsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Company Projects Page");
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
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

	// Check Track Projects Actions Link is displayed.
	public boolean isTrackProjectActionsMenDisplayed() {
		extentTest.log(Status.INFO, "Check Track Projects Actions Link is displayed.");
		return CommonUtils.checkElementExist(trackProjectsLink, driver);
	}

	public TrackPopUpPage clickOnTrackProjectActionsLink() {
		extentTest.log(Status.INFO, "Click on the Track Projects Actions Link");
		trackProjectsLink.click();
		return new TrackPopUpPage(driver);
	}

	public boolean verifyTrackNameInTrackingList(String TrackName) {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		CommonUtils.IterateWebElementsList(trackCompanyList);
		return CommonUtils.getListFromWebElements(trackCompanyList).contains(TrackName);
	}

	public boolean isTrackingIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Tracking Icon displayed");
		return trackCompanyIcon.isDisplayed();
	}

	public CompanyPage clickOnCompanyProfile() {
		extentTest.log(Status.INFO, "Clicking on Company Profile");
		companyProfileLink.click();
		return new CompanyPage(driver);
	}

	@Override
	public void clickOnFistProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		SeleniumUtils.isVisible(firstProjectchkBox, driver);
		if (!firstProjectchkBox.isSelected()) {
			firstProjectchkBox.click();
		}
	}

	@Override
	public void clickOnSecondProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on second project checkbox");
		SeleniumUtils.isVisible(secondProjectchkBox, driver);
		if (!secondProjectchkBox.isSelected()) {
			secondProjectchkBox.click();
		}
	}

	public ProjectPage clickFirstProjectTitle() {
		extentTest.log(Status.INFO, "Clicking the first project title");
		firstProjectTitle.click();
		return new ProjectPage(driver);
	}

	public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText().trim();
	}

	public Boolean isMyProjectsTabDisplayed() {
		extentTest.log(Status.INFO, "Check if my Projects Tab is displayed");
		return myProjectsTab.isDisplayed();
	}

	public Boolean isMyProjectsTabEnabled() {
		extentTest.log(Status.INFO, "Check if my Projects Tab is displayed");
		return myProjectsTab.isEnabled();
	}

	public Boolean isProjectsNotInMyLicenseTabEnabled() {
		extentTest.log(Status.INFO, "Check if Projects not in my License Tab is displayed");
		return projectsNotInMyLicense.getAttribute("disabled").equals("disabled");
	}

	public void clickOnActionsDropdownCompany() {
		extentTest.log(Status.INFO, "Clicking on Actions Dropdown");
		actionsDropdown.click();
		waitforLoadingRing();
	}

	public PrintCompanyDetailsPage clickOnPrintCompanyDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on Print company Details under Actions Dropdown");
		printCompanyDetailsLink.click();
		return new PrintCompanyDetailsPage(driver);
	}

	public Boolean isProjectsNotInMyLicenseTabDisplayed() {
		extentTest.log(Status.INFO, "Check if Projects not in my License Tab is displayed");
		return projectsNotInMyLicense.isDisplayed();
	}

	public Boolean isProjectCountInMyLicenseTabGreaterthanzero() {
		extentTest.log(Status.INFO, "Check if Projects in my License are present");
		return ProjectTitles.isDisplayed();
	}

	// Check if Projects in my project tab are present.
	public Boolean isProjectCountInMyProjectTabGreaterthanzero() {
		extentTest.log(Status.INFO, "Check if Projects in my project tab are present.");
		return ProjectTitlesList.size() > 0;
	}

	// Check Result Per Page Dropdown At Top Displayed
	public Boolean isResultPerPageDropdownAtTopDisplayed() {
		extentTest.log(Status.INFO, "Check Result Per Page Dropdown At Top Displayed.");
		return CommonUtils.checkElementExist(resultPerPageAtTopOnCompanyProjPage, driver);
	}

	// Check Result Per Page Dropdown At Bottom Displayed
	public Boolean isResultPerPageDropdownAtBottomDisplayed() {
		extentTest.log(Status.INFO, "Check Result Per Page Dropdown At Bottom Displayed.");
		return CommonUtils.checkElementExist(resultPerPageAtBottomOnCompanyProjPage, driver);
	}

	public String getActionsDropdownValues() {
		extentTest.log(Status.INFO, "get the values in actions dropdown");
		return actionsDiv.getText();
	}

	// Download Company action menu is displayed on Company project page.
	public boolean isDownloadCompanyActionMenuDisplayed() {
		extentTest.log(Status.INFO, "Download Company action menu is displayed on Company project page.");
		return CommonUtils.checkElementExist(downloadCompanyActionMenu, driver);
	}

	// View Project action menu is displayed on Company project page.
	public boolean isViewProjectActionMenuDisplayed() {
		extentTest.log(Status.INFO, "View Project action menu is displayed on Company project page.");
		return CommonUtils.checkElementExist(viewProjectsActionMenu, driver);
	}

	// Click on View Project action menu on Company project page.
	public void clickOnViewProjectActionMenu() {
		extentTest.log(Status.INFO, "Click on View Project action menu on Company project page.");
		SeleniumUtils.isVisible(viewProjectsActionMenu, driver);
		viewProjectsActionMenu.click();
	}

	// Click on Download Company action menu on Company project page.
	public DownloadCompanies clickOnDownloadCompanyActionMenu() {
		extentTest.log(Status.INFO, "Click on Download Company action menu on Company project page.");
		downloadCompanyActionMenu.click();
		return new DownloadCompanies(driver);
	}

	// Click on Download Company action menu on Company project page.
	public EmailCompanyPage clickOnEmailCompanyActionMenu() {
		extentTest.log(Status.INFO, "Click on Eamil Company action menu on Company project page.");
		emailCompanyActionMenu.click();
		return new EmailCompanyPage(driver);
	}

	public String getCompanyAddressOnCompanyProjectPage() {
		extentTest.log(Status.INFO, "getting compnay address on Company Project page.");
		return companyAddress.getText();
	}

	public void clickProfileTab() {
		extentTest.log(Status.INFO, "Click the Profile TaB");
		profileTab.click();
	}

	/** This method is used to get Published Date Range. */
	public List<String> getPublishedDateRangeList() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		SeleniumUtils.isVisible(publishedDateRangeList.get(0), driver);
		List<String> publishedDateRngList = new ArrayList<String>();
		for (WebElement publishedDateRange : publishedDateRangeList) {
			publishedDateRngList.add(publishedDateRange.getText());
		}
		return publishedDateRngList;
	}

	public void clickOnEditlink() {
		extentTest.log(Status.INFO, "Get click on edit link.");
		projectTypeEditLink.click();
		driver.switchTo().activeElement();
		waitforLoadingRing();
	}

	public void selectTransportOption() {
		extentTest.log(Status.INFO, "Get click on Transport Option.");
		transportOptonselect.click();
	}

	public boolean isTransportOptionSelected() {
		extentTest.log(Status.INFO, "Verifying Transport Option selected.");
		CommonUtils.scrollDownTillElement(transportOptonselect, driver);
		return transportOptonselect.isSelected();
	}

	public void clickEditButton() {
		extentTest.log(Status.INFO, "Get click on edit button.");
		CommonUtils.scrollDownTillElement(clickOnUpdateButton, driver);
		clickOnUpdateButton.click();
	}

	public Boolean isActionStageFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if action stage filter Tab is displayed");
		SeleniumUtils.isVisible(actionStageFilterTab, driver);
		return actionStageFilterTab.isDisplayed();
	}

	public List<String> getSelecetedFacetItemList() {
		extentTest.log(Status.INFO, "Get selected facet item list.");
		SeleniumUtils.isVisible(lnkSelecetedFacetItem.get(0), driver);
		return CommonUtils.getListFromWebElements(lnkSelecetedFacetItem);
	}

	@Override
	public boolean isSaveSearchButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if save search buton is displayed or not.");
		if (saveSearchBtn.isDisplayed())
			return false;
		else
			return true;

	}

	/** This method is used to get list of filters Range. */
	public List<String> getFilterList() {
		extentTest.log(Status.INFO, "Get All Filter list.");
		List<String> filters = new ArrayList<String>();
		for (WebElement filter : filterList) {
			filters.add(filter.getText());
		}
		return filters;
	}

	public void clickOnActionStageFilterSeeMoreButton() {
		extentTest.log(Status.INFO, "click on Action Stage see more Button.");
		SeleniumUtils.isClickable(actionStageFilterSeeMoreButton, driver);
		actionStageFilterSeeMoreButton.click();

	}

	public void selectDesignOption() {
		extentTest.log(Status.INFO, "Get click on edit link.");
		CommonUtils.scrollDownTillElement(designOptionselect, driver);
		designOptionselect.click();
		CommonUtils.scrollDownTillElement(clickOnUpdateButton, driver);
		clickOnUpdateButton.click();
	}

	public void clickOnClearButton() {
		extentTest.log(Status.INFO, "Get click on edit link.");
		clickOnClear.click();
		waitforLoadingRing();
	}

	public Boolean isClearAllLinkInFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if clear all filter Tab is displayed");
		return clearAllFilterLink.isDisplayed();
	}

	public boolean isFilterCrumbDisplayed() {
		return !filterCrumbDisplayed.isEmpty();
	}

	public boolean actionstagedispalyOnComapnyPage() {
		return actionStageOnCompanyPage.isDisplayed();
	}

	public void clickDesignOptionOnPopup() {
		extentTest.log(Status.INFO, "Click On Commercial Check box");
		designOptionselect.click();
		waitforLoadingRing();
	}

	public void clickOnGeographyFilter() {
		extentTest.log(Status.INFO, "Click on the geography filter.");
		geographyFilter.click();
	}

	// Click on the search icon of geography filter.
	public boolean checkGeographyFilter() {
		return geographyFilter.isDisplayed();
	}

	public Boolean mouseOverActionandChecktrackProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Check if Track Projects is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		return trackProjectsMenu.isDisplayed();
	}

	public TrackPopUpPage mouseOverActionandClicktrackProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click track projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public void mouseOverActionandClickViewProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click View Projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(viewProjectsActionMenu));
		viewProjectsActionMenu.click();
		//return new projectPage(driver);
	}
	
	public void clickOnactionsDropdowm_companyNavigator() {
		extentTest.log(Status.INFO, "Click on actionsDropdowm_companyNavigator");
		SeleniumUtils.isVisible(actionsDropdowm_companyNavigator, driver);
		actionsDropdowm_companyNavigator.click();
	}
	
	public void ClickOn_hostProjectActionsLink() {
		extentTest.log(Status.INFO, "Click on hostProjectActionsLink");
		SeleniumUtils.isVisible(hostProjectActionsLink, driver);
		hostProjectActionsLink.click();
	}
	
	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Get the error message");
		SeleniumUtils.isVisible(errLabel, driver);
		return errLabel.getText().trim();
	}
}

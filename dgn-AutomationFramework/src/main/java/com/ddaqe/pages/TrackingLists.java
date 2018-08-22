package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class TrackingLists {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterTrackingLists_ctl01_HyperLink3")
	private WebElement firstTrackingList;

	@FindBy(how = How.ID, using = "dvAddTrackList")
	private WebElement addNewTrackListBtn;

	@FindBy(how = How.ID, using = "dvAddTrackList")
	private WebElement addNewTrackingName;

	@FindBy(how = How.ID, using = "lblMyTrackingLists")
	private WebElement myTrackingListLabel;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement resultPerPageLst;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlType")
	private WebElement typeTrackingListDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlType")
	private List<WebElement> typeTrackingListDD;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'HyperLink')]")
	private List<WebElement> trackingNameList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblType')]")
	private List<WebElement> statusOfTrackingNameList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'tlPagerTop_Prev')]")
	private WebElement paginationAtTop;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'tlPagerBottom_Prev')]")
	private WebElement paginationAtBottom;

	@FindBy(how = How.LINK_TEXT, using = "Company")
	private WebElement companyTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_TLProjectCompanyNav_companySpan")
	private WebElement companyTabSelectWithIdOnActive;

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private WebElement editTrackingNameLink;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private WebElement deleteTrackingNameLink;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private List<WebElement> deleteLinkList;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private WebElement shareTrackingNameLink;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private List<WebElement> shareTrackingNameLinkList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'projectSpan')]")
	private WebElement projectTab;

	@FindBy(how = How.LINK_TEXT, using = "Project")
	private WebElement projectLinkTextTab;

	@FindBy(how = How.ID, using = "My-Account---My-Tracking-Lists")
	private WebElement myTrackingBreadCrum;

	@FindBy(how = How.LINK_TEXT, using = "Hidden Projects")
	private WebElement hiddenProjectsTrackingList;

	@FindBy(how = How.LINK_TEXT, using = "My Projects")
	private WebElement myProjectsTrackingList;

	@FindBy(how = How.LINK_TEXT, using = "My Companies")
	private WebElement myCompaniesTrackingList;

	@FindBy(how = How.XPATH, using = "//a[@class='listCommands' and text() = 'Edit']")
	private List<WebElement> editLink;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrackingListRename']//input[@type='text']")
	private WebElement nameTextfieldInEditDialog;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-submit']")
	private WebElement saveButtonfieldInEditDialog;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-close']")
	private WebElement cancelButtonfieldInEditDialog;

	@FindBy(how = How.NAME, using = "TrackingListType")
	private WebElement typeListOnEditDialog;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblTopShowingPageNo')]")
	private WebElement trackingListPaginationCount;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/shared.png']")
	private WebElement imgShareTrackingListPeopleIcon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement resultPerPageDisplay;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private List<WebElement> resultPerPageDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterTrackingLists_ctl03_lbDelete")
	private WebElement deleteLink;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[4]//div[5]//table//tbody//tr[8]//td[4]//div//a[2]")
	private WebElement trackingListEditLinkPLATUser;

	@FindBy(how = How.ID, using = "myAccountMenu12")
	private WebElement myTrackingList;

	@FindBy(how = How.ID, using = "myAccountNav11")
	private WebElement myTrackingMenu;

	@FindBy(how = How.ID, using = "myAccountNav13")
	private WebElement mySavedSearchesMenu;

	@FindBy(how = How.ID, using = "My-Account---My-Tracking-Lists")
	private WebElement breadcumbMenu;

	@FindBy(how = How.ID, using = "myAccountHeader")
	private WebElement myAccountHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='popUpLBTrackingListRename']//div[3]//div//div[1]//a")
	private WebElement cancelBtnPLATUser;

	@FindBy(how = How.ID, using = "ADMIN TOOLS")
	private WebElement adminToolsHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='submenu_1']")
	private WebElement accountToolsHeader;

	@FindBy(how = How.ID, using = "TrackingListType")
	private WebElement trackingListType;

	@FindBy(how = How.ID, using = "project-results-bottom")
	private WebElement projectTabBar;

	@FindBy(how = How.LINK_TEXT, using = "Sign Out")
	private WebElement signOutLink;

	@FindBy(how = How.ID, using = "addNewBlankText")
	private WebElement addNewTrackingListName;

	@FindBy(how = How.ID, using = "addNewTrackingListType")
	private WebElement addNewTrackingListType;

	@FindBy(how = How.ID, using = "addNewPopUpSubmit")
	private WebElement addNewDialogSaveBtn;

	@FindBy(how = How.LINK_TEXT, using = "My Preferences")
	private WebElement myPreferences;

	@FindBy(how = How.CSS, using = "a[id*='repeaterTrackingLists']")
	private List<WebElement> AllTrackingList;

	@FindBy(how = How.CSS, using = "a[id*='TLProjectCompanyNav_lnkCompany']")
	private WebElement companyLink;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;
	
	@FindBy(how = How.XPATH, using = "//table[@class='batchDownloadDetailsContainer']")
	private WebElement trackingListTable;
	

	public TrackingLists(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		PageFactory.initElements(driver, this);
	}

	public boolean ismyTrackingListsMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My TrackingLists Menu option is present");
		return myTrackingMenu.isDisplayed();
	}

	public void setNewTrackinListName(String trackingListName) {
		extentTest.log(Status.INFO, "Set new tracking list name : " + trackingListName);
		addNewTrackingListName.sendKeys(trackingListName);
	}

	public void selectAddNewTrackingListType(String optionToSelect) {
		extentTest.log(Status.INFO,
				"Setting the type filter option on Add New tracking list dialog: " + optionToSelect);
		Select typeList = new Select(addNewTrackingListType);
		typeList.selectByVisibleText(optionToSelect);
	}

	public void clickOnSaveButtonAddNewTrackingDialog() {
		extentTest.log(Status.INFO, "Click on add save button on Add New dialog.");
		addNewDialogSaveBtn.click();
	}

	public void trackingMenu() {
		extentTest.log(Status.INFO, "Click On My Tracking Lists Menu Link");
		myTrackingList.click();

	}

	public void clickOnAddNewButton() {
		extentTest.log(Status.INFO, "Click on add new button present on tracking list page");
		addNewTrackListBtn.click();
	}

	public boolean ismySavedSearchesMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Saved Search option is present");

		return mySavedSearchesMenu.isDisplayed();
	}

	public boolean isBreadcrumbMyTrackingDisplayed() {
		extentTest.log(Status.INFO, "Verify that Breadcrumb for My Account - My Tracking Lists is  displayed ");

		return breadcumbMenu.isDisplayed();

	}

	public boolean isProjectTabSelectedDefault() {
		extentTest.log(Status.INFO, "Verify Project tab by default active at My tracking lists page.");
		return projectTab.getAttribute("class").toUpperCase().equals("ACTIVE");
	}

	public boolean isEditLinkPresent() {
		extentTest.log(Status.INFO, "Verify Edit link contain in tracking list table on My Tracking Lists page.");
		return CommonUtils.checkElementExist(editTrackingNameLink, driver);
	}

	public boolean isShareLinkPresent() {
		extentTest.log(Status.INFO, "Verify Share link contain in tracking list table on My Tracking Lists page.");
		return CommonUtils.checkElementExist(shareTrackingNameLink, driver);
	}

	public ShareUsers clickShareLink() {
		extentTest.log(Status.INFO, "Clicking Share Link on tracking list table");
		shareTrackingNameLink.click();
		return new ShareUsers(driver);
	}

	public void deleteProject() {
		extentTest.log(Status.INFO, "Verify that  click on Delete Link ");
		deleteLink.click();
	}

	// Check color of tracking list menus on right side-Checking only by
	// verifying the color on the elements
	public boolean checkColorofElementsTrackingList() {
		extentTest.log(Status.INFO,
				"Verify color of tracking list menus on right side-Checking only by verifying the color on the elements ");
		String MyAccountHeader = myAccountHeader.getCssValue("background-color");
		String AccountToolheader = accountToolsHeader.getCssValue("background-color");
		return MyAccountHeader.equals(AccountToolheader) == false;
	}

	// Verifying Alert text
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public void confirmAlert(boolean confirm) {
		if (confirm) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();

		}

	}

	public void clickOnTrackingListEditLinkPLATuser() {
		extentTest.log(Status.INFO, "Click on Edit  under Tracking Link ");
		trackingListEditLinkPLATUser.click();
	}

	public boolean isShareTrackingPeopleIconPresent() {
		extentTest.log(Status.INFO,
				"Verify Share tracking people icon contain in tracking list table on My Tracking Lists page.");
		return CommonUtils.checkElementExist(imgShareTrackingListPeopleIcon, driver);
	}

	public boolean isDeleteLinkPresent() {
		extentTest.log(Status.INFO, "Verify Delete link contain on tracking list table.");
		return CommonUtils.checkElementExist(deleteTrackingNameLink, driver);
	}

	public void clickOnProjectTabForMyTrackingLists() {
		extentTest.log(Status.INFO, "Click on Porject tab present on My Tracking Lists page.");
		projectLinkTextTab.click();
	}

	public void clickOnCompanyTabForMyTrackingListsTestData() {
		extentTest.log(Status.INFO, "Click on Company tab present on My Tracking Lists page.");
		if (!CommonUtils.checkElementExist(companyTabSelectWithIdOnActive, driver)) {
			companyTab.click();
		}
	}

	public void clickOnCompanyTabForMyTrackingLists() {
		extentTest.log(Status.INFO, "Click on Company tab present on My Tracking Lists page.");
		companyTab.click();
	}

	public TrackingListPopupDialog clickOnEditTrackingNameLink() {
		extentTest.log(Status.INFO, "Click on edit tracking name link on My Tracking Lists table.");
		try {
			if (CommonUtils.checkElementExist(editTrackingNameLink, driver)) {
				editTrackingNameLink.click();
			}

		} catch (Exception e) {
			extentTest.log(Status.INFO, "Edit link is not present on the My tracking list table.");
		}
		return new TrackingListPopupDialog(driver);
	}

	public void clickOnEditTrackingNameLinkPlusUser() {
		extentTest.log(Status.INFO, "Click on edit tracking name link on My Tracking Lists table.");
		try {
			if (CommonUtils.checkElementExist(editTrackingNameLink, driver)) {
				editTrackingNameLink.click();

			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Edit link is not present on the My tracking list table.");
		}

	}

	public ShareUsers clickOnShareTrackingNameLink() {
		extentTest.log(Status.INFO, "Click on share tracking name link on My Tracking Lists table.");
		try {
			if (CommonUtils.checkElementExist(shareTrackingNameLink, driver)) {
				shareTrackingNameLink.click();
			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Share link is not present on the My tracking list table.");
		}

		return new ShareUsers(driver);
	}

	public void selectTypeListOption(String optionToSelect) {
		extentTest.log(Status.INFO, "Setting the type filter option as asked to set i.e : " + optionToSelect);
		Select typeList = new Select(typeTrackingListDropdown);
		typeList.selectByVisibleText(optionToSelect);
	}

	public void selectTypeListDropdown(String optionToSelect) {
		extentTest.log(Status.INFO, "Setting the type filter option as asked to set i.e : " + optionToSelect);
		if (!typeTrackingListDD.isEmpty()) {
			Select typeList = new Select(typeTrackingListDD.get(0));
			typeList.selectByVisibleText(optionToSelect);
		}
	}

	// check the edit name replace with the existing tracking name.
	public boolean checkEditTrackingNameReplace(String trackingName) {
		return MyAccountCommonContainerPage.checkEditStringPresentInTableNameList(trackingName, trackingNameList);
	}

	// Check the tracking name status as required i.e Public/Private/Shared is
	// present in the table check only first occurrence of status.
	public boolean checkTrackingListStatus(String trackingNameStatus) {
		extentTest.log(Status.INFO, "Check the status of tracking list.");
		return MyAccountCommonContainerPage.checkEditStringPresentInTableNameList(trackingNameStatus,
				statusOfTrackingNameList);
	}

	public boolean checkAllTrackingListStatus() {
		extentTest.log(Status.INFO, "Check status of tracking list i.e privat, public, shared.");
		return MyAccountCommonContainerPage.checkStatusOfEachRecord(statusOfTrackingNameList);
	}

	public void clickOnCancelButtonForDeleteDialog() {
		extentTest.log(Status.INFO, "click on Delete link and select Cancel button.");
		try {
			if (CommonUtils.checkElementExist(deleteTrackingNameLink, driver)) {
				deleteTrackingNameLink.click();
				driver.switchTo().alert().dismiss();
			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the My tracking list table.");
		}
	}

	public void clickOnCancelButtonForDeleteDialogOnly() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the My tracking list table.");
		}
	}

	public void deleteTrackingList() {
		extentTest.log(Status.INFO, "Deleting the tracking list present on My Tracking Lists table.");
		try {
			if (CommonUtils.checkElementExist(deleteTrackingNameLink, driver)) {
				deleteTrackingNameLink.click();
				driver.switchTo().alert().accept();
			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the My tracking list table.");
		}
	}

	public boolean deleteTrackingListComfirmDialogPresent() {
		extentTest.log(Status.INFO,
				"Deleting the tracking list confirmation dialog present on My Tracking Lists table.");
		try {
			deleteTrackingNameLink.click();
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	// return the result of option list in Result Per Page
	public boolean checkResultPerPageOption() {
		extentTest.log(Status.INFO, "Verify option list of result per page dropdown.");
		SeleniumUtils.isVisible(resultPerPageLst, driver);
		return MyAccountCommonContainerPage.checkResultPerPageDropdownOption(resultPerPageLst);
	}

	// return the result of Filter In Type Header Column
	public boolean checkTypeDropdownOption() {
		extentTest.log(Status.INFO, "Check type filter dropdown contain Private,Public and Shared option.");
		return MyAccountCommonContainerPage.checkTableTypeHeaderContent(typeTrackingListDropdown);
	}

	public boolean isTrackingListResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Tracking menu ");

		return typeTrackingListDropdown.isDisplayed();

	}

	public boolean isProjectBarDisplayed() {

		extentTest.log(Status.INFO, "Verify if Project Dropdown menu is displayed ");

		return projectTabBar.isDisplayed();

	}

	// return the result of Filter In Type Header Column
	public boolean checkTrackingNameSortedList() {
		extentTest.log(Status.INFO, "Check the tracking name is in sorting format.");
		return MyAccountCommonContainerPage.verifySortingTrackingName(trackingNameList);
	}

	// check the default selection of type dropdown.
	public boolean checkDefaultTypeSelection() {
		extentTest.log(Status.INFO, "Verify the default selection for Type dropdown.");
		String defaultSelectionTypeValue = "ALL";
		Select typeSelect = new Select(typeTrackingListDropdown);
		return typeSelect.getFirstSelectedOption().getText().toUpperCase().equals(defaultSelectionTypeValue);
	}

	// check the default selection of type dropdown.
	public boolean checkTypeFilterSelectedOption(String option) {
		extentTest.log(Status.INFO, "Verify type filter seleted option is as same.");
		Select typeSelect = new Select(typeTrackingListDropdown);
		return typeSelect.getFirstSelectedOption().getText().toUpperCase().equals(option.toUpperCase());
	}

	public boolean isPaginationDetailAtTop() {
		extentTest.log(Status.INFO, "Verify Pagination is present at top.");
		return paginationAtTop.isDisplayed();
	}

	public boolean isPaginationDetailAtBotton() {
		extentTest.log(Status.INFO, "Verify Pagination is present at bottom.");
		return paginationAtBottom.isDisplayed();
	}

	public boolean isProjectTabDisplayed() {
		extentTest.log(Status.INFO, "Check Project tab present at My tracking lists page.");
		return CommonUtils.checkElementExist(projectTab, driver);
	}

	public boolean isCompanyTabDisplayed() {
		extentTest.log(Status.INFO, "Verify Company tab present at My tracking lists page.");
		return CommonUtils.checkElementExist(companyTab, driver);
	}

	public boolean isMyCompaniesTrackingNameDisplayed() {
		extentTest.log(Status.INFO,
				"Check My Companies tracking name is present at company tab at My tracking lists page.");
		return CommonUtils.checkElementExist(myCompaniesTrackingList, driver);
	}

	public boolean checkTrackingListBreadCrumb(String expBreadCrum) {
		extentTest.log(Status.INFO, "Verify my tracking lists bread crumb.");
		SeleniumUtils.isVisible(myTrackingBreadCrum, driver);
		return MyAccountCommonContainerPage.verifyBreadCrumb(myTrackingBreadCrum, expBreadCrum);
	}

	public ProjectResultsPage clickHiddenProjectsTrackingList() {
		extentTest.log(Status.INFO, "click on the hidden projects tracking list on My Tracking Lists page.");
		hiddenProjectsTrackingList.click();

		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickOnMyProjectsTrackingList() {
		extentTest.log(Status.INFO, "click on the my projects tracking list on My Tracking Lists page.");
		myProjectsTrackingList.click();

		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickOnTrackingList(String projectTrackingName) {
		extentTest.log(Status.INFO, "click on desired project tracking list on My Tracking Lists page.");
		WebElement trackingLink = driver.findElement(By.linkText(projectTrackingName));
		if (CommonUtils.checkElementExist(trackingLink, driver)) {
			trackingLink.click();
		}
		return new ProjectResultsPage(driver);
	}

	public CompanyResultsPage clickOnCompanyTrackingList(String companyTrackingName) {
		extentTest.log(Status.INFO, "click on desired company tracking list on My Tracking Lists page.");
		WebElement trackingLink = driver.findElement(By.linkText(companyTrackingName));
		if (CommonUtils.checkElementExist(trackingLink, driver)) {
			trackingLink.click();
		}
		return new CompanyResultsPage(driver);
	}

	public ProjectResultsPage clickMyCompaniesTrackingList() {
		extentTest.log(Status.INFO, "click on the My Companies tracking list on My Tracking Lists page.");
		myCompaniesTrackingList.click();

		return new ProjectResultsPage(driver);
	}

	public boolean checkColorOfProjectBar() {
		extentTest.log(Status.INFO, "Verify color of project Bar in download list ");
		String tabBarColor = projectTabBar.getCssValue("color");
		return tabBarColor.equals("rgba(16, 44, 66, 1)");
	}

	public String getTimeStamp() {
		return CommonUtils.getTimeStamp();
	}

	public String getTrackingNameSize() {
		return trackingListPaginationCount.getText();
	}

	public boolean verifyTrackingNameCountAfterDeletion(String countBeforeDeletion) {
		return getTrackingNameSize().toUpperCase().equals(countBeforeDeletion.toUpperCase());
	}

	public boolean checkShareLinkForPrivateTrackingList_ProjectTab(String typeFilterOption) {
		boolean result = false;
		if (typeFilterOption.toUpperCase().equals("PRIVATE")) {
			result = (trackingNameList.size() - 2) == shareTrackingNameLinkList.size();
		} else {
			if (shareTrackingNameLinkList.size() == 0) {
				result = true;
			}
		}
		return result;
	}

	public boolean checkShareLinkForPrivateTrackingList_CompanyTab() {
		return (trackingNameList.size() - 1) == shareTrackingNameLinkList.size();
	}

	public ProjectResultsPage clickOnFirstTrackingList() {
		extentTest.log(Status.INFO, "Click on first Tracking List");
		firstTrackingList.click();
		return new ProjectResultsPage(driver);
	}

	// Click on first company Tracking List.
	public CompanyResultsPage clickOnFirstCompanyTrackingList() {
		extentTest.log(Status.INFO, "Click on first company Tracking List.");
		firstTrackingList.click();
		return new CompanyResultsPage(driver);
	}

	public ProjectResultsPage clickOnATrackingList(String trackingListName) {
		extentTest.log(Status.INFO, "Click on a given Tracking List");
		driver.findElement(By.linkText(trackingListName)).click();
		return new ProjectResultsPage(driver);

	}

	public boolean isResultPerPageDisplayed() {
		extentTest.log(Status.INFO, "Verify Result per page is present at top at Save Searchs page.");
		return resultPerPageDisplay.isDisplayed();
	}

	public void selectResultPerPage(String optionToSelect) {
		extentTest.log(Status.INFO, "Set the value in Result per page option dropdown.");
		Select resultPerPageList = new Select(resultPerPageDisplay);
		resultPerPageList.selectByVisibleText(optionToSelect);
	}

	public void selectResultPerPageOption(String optionToSelect) {
		extentTest.log(Status.INFO, "Set the value in Result per page option dropdown.");
		if (!resultPerPageDropdown.isEmpty()) {
			Select resultPerPageList = new Select(resultPerPageDropdown.get(0));
			resultPerPageList.selectByVisibleText(optionToSelect);
		}
	}

	public boolean isMyTrackingListDisplayed() {
		extentTest.log(Status.INFO, "Verify My Tracking list page is displayed.");
		return myTrackingListLabel.isDisplayed();
	}

	// Return the first tracking name list with Share link
	public String getFirstTrackingNameWithShareLink() {
		return MyAccountCommonContainerPage.returnFirstTrackingNameWithShareLink(trackingNameList);
	}

	public void clickOnOKButtonForOverrideTrackingList() {
		extentTest.log(Status.INFO, "Click on the OK button of override tracking list dialog.");
		driver.switchTo().alert().accept();
	}

	public ShareUsers clickOnSelectedTrackingListShareLink(String trackingListName) {
		extentTest.log(Status.INFO, "Click on the share link for the tracking list : " + trackingListName);
		int count = 0;
		for (String user : CommonUtils.getListFromWebElements(trackingNameList)) {
			if (user.toUpperCase().contains(trackingListName.toUpperCase())) {
				break;
			}
			count++;
		}

		if (isProjectTabSelectedDefault()) {
			shareTrackingNameLinkList.get(count - 2).click();
		} else {
			shareTrackingNameLinkList.get(count - 1).click();
		}
		return new ShareUsers(driver);
	}

	public void clickOnSingoutLink() {
		extentTest.log(Status.INFO, "Click on Sign Out Link.");
		signOutLink.click();
	}

	public MyPreferencesPage clickOnMyPreferences() {
		extentTest.log(Status.INFO, "Verify that Clicks on My Preferences menu");
		myPreferences.click();
		return new MyPreferencesPage(driver);
	}

	public TrackPopUpPage deleteTrackingList(final String trackingListName) {
		final List<WebElement> trackingList = driver
				.findElements(By.cssSelector("a[onclick*='" + trackingListName + "']+a"));
		if (!trackingList.isEmpty()) {
			trackingList.get(0).click();
		}
		driver.switchTo().alert().accept();
		return new TrackPopUpPage(driver);
	}

	public List<String> AllTrackingList() {
		List<String> allTrackingList = new ArrayList<String>();
		for (final WebElement trackingList : AllTrackingList) {
			allTrackingList.add(trackingList.getText());
		}
		return allTrackingList;
	}

	public String getTrackingListType(final String trackingListName) {
		final List<WebElement> trackingList = driver
				.findElements(By.xpath("//a[text()='" + trackingListName + "']/parent::td//following-sibling::td[2]"));
		String trackingListType = "";
		if (!trackingList.isEmpty()) {
			trackingListType = trackingList.get(0).findElement(By.tagName("span")).getAttribute("innerHTML");
		}
		return trackingListType;
	}

	public void switchToCompanyTrackingListSection() {
		extentTest.log(Status.INFO, "Click on company link to switch to tracking list section.");
		companyLink.click();
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}

	public int getDeleteLinkListSize() {
		return deleteLinkList.size();
	}
	
	public boolean checkTrackingListTableExist(){
		extentTest.log(Status.INFO, "Check tracking list are displayed as grid.");
		return CommonUtils.checkElementExist(trackingListTable, driver);
	}
}

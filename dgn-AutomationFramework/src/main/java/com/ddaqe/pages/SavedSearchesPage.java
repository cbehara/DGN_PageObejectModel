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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class SavedSearchesPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "lblMySavedSearches")
	private WebElement mySavedSearchesLbl;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl01_HyperLink3")
	private WebElement firstSavedSearch;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyLink")
	private WebElement companyLink;

	@FindBy(how = How.LINK_TEXT, using = "Company")
	private WebElement companyTab;

	@FindBy(how = How.LINK_TEXT, using = "Project")
	private WebElement projectLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl02_HyperLink3")
	private WebElement secondSavedSearch;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_ucFooter_logoDiv']/img")
	private WebElement footerLogo;

	@FindBy(how = How.XPATH, using = ".//*[@class='batchDownloadDetailsContainer']//a[contains(@id,'Delete')]")
	private List<WebElement> savedSearchDeleteBtnList;

	@FindBy(how = How.XPATH, using = ".//*[@class='batchDownloadDetailsContainer']//a[contains(.,'Edit')]")
	private List<WebElement> savedSearchEditBtnList;

	@FindBy(how = How.XPATH, using = ".//*[@class='batchDownloadDetailsContainer']//tr[1]//td[1]//span")
	private WebElement tableFirstColumnHeader;

	@FindBy(how = How.CLASS_NAME, using = "batchDownloadDetailsContainer")
	private WebElement savedSearchesTable;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'projectLink')]")
	private WebElement projectTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlType")
	private WebElement typeTrackingListDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlType")
	private List<WebElement> typeTrackingListDD;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlUsers")
	private WebElement usersDropdown;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'HyperLink')]")
	private List<WebElement> trackingNameList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement resultPerPageDisplay;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private List<WebElement> resultPerPageDropdown;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'tlPagerTop_Prev')]")
	private WebElement paginationAtTop;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'tlPagerBottom_Prev')]")
	private WebElement paginationAtBottom;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$tlPagerTop$lblTopSearchResults")
	private WebElement resultPerPageLst;

	@FindBy(how = How.ID, using = "savedSearchName")
	private WebElement nameTextfieldInEditDialog;

	@FindBy(how = How.ID, using = "savePopupSubmit")
	private WebElement saveButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "savePopupClose")
	private WebElement cancelButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "savePopupNew")
	private WebElement newsaveButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "rdbDoNotEmail")
	private WebElement doNotEmailRadioBtn;

	@FindBy(how = How.ID, using = "rdbTemplateSummary")
	private WebElement summaryTemplateRadioBtn;

	@FindBy(how = How.ID, using = "SavedSearchtType")
	private WebElement typeFilterEditSaveSearchList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_projectLink")
	private WebElement projectTabSelection;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblType')]")
	private List<WebElement> statusOfTrackingNameList;

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private WebElement editSaveSearchLink;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private WebElement deleteSaveSearchLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement resultsDropdown;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private List<WebElement> shareTrackingNameLinkList;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/shared.png']")
	private WebElement imgShareTrackingListPeopleIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblTopShowingPageNo')]")
	private List<WebElement> trackingListPaginationCount;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'myAccountMenu')]")
	private List<WebElement> myAccountMenuList;

	@FindBy(how = How.ID, using = "My-Account---My-Saved-Searches")
	private WebElement mySavedSearchesBreadCrum;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private WebElement shareSavedSearchLink;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl01_lblOwner']")
	private WebElement ownerNameSavedSearchCompany;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl01_lblOwner']")
	private WebElement ownerNameSavedSearchProject;

	@FindBy(how = How.ID, using = "project-results-bottom")
	private WebElement projectTabBar;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement savedSaearchCompanyResultDropDown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement savedSaearchResultDropDown;

	@FindBy(how = How.ID, using = "myAccountNav18")
	private WebElement myPreferencesmenuleftNav;

	@FindBy(how = How.ID, using = "myAccountNav11")
	private WebElement myTrackingMenu;

	@FindBy(how = How.ID, using = "myAccountNav13")
	private WebElement mySavedSearchesMenu;

	@FindBy(how = How.NAME, using = "savedSearchListType")
	private WebElement typeListOnEditDialogForSavedSearch;

	@FindBy(how = How.LINK_TEXT, using = "Sign Out")
	private WebElement signOutLink;

	@FindBy(how = How.ID, using = "users-select-all")
	private WebElement selectAllChkbox;

	@FindBy(how = How.LINK_TEXT, using = "Done")
	private WebElement doneBtn;

	@FindBy(how = How.ID, using = "btnManageUserActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_lnkShare")
	private WebElement shareLink;

	@FindBy(how = How.LINK_TEXT, using = "Unshare")
	private WebElement unshareLink;

	@FindBy(how = How.CSS, using = "a[id*='repeaterSavedSearches']")
	private List<WebElement> AllSavedSearchesList;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private List<WebElement> deleteLinkList;

	@FindBy(how = How.XPATH, using = "//table[@class='batchDownloadDetailsContainer']")
	private WebElement saveSearchTable;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_repUserList_ctl01_chkSelect")
	private WebElement firstUserToShare;

	public SavedSearchesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickFirstUserToShare() {
		extentTest.log(Status.INFO, "click first user to be shared");
		SeleniumUtils.isVisible(firstUserToShare, driver);
		firstUserToShare.click();
	}

	public Boolean IsMySavedSearchesLabelDispalyed() {
		extentTest.log(Status.INFO, "Check if my saved searches label is dispalyed");
		return mySavedSearchesLbl.isDisplayed();
	}

	public List<String> VerifyResultDropdownValues() {
		Select select = new Select(resultsDropdown);
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public List<String> VerifyTypeDropdownValues() {
		Select select = new Select(typeTrackingListDropdown);
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public String getTypeDropdownSelectedValue() {
		Select select = new Select(typeTrackingListDropdown);
		return select.getFirstSelectedOption().getText();
	}

	public String getUsersDropdownSelectedValue() {
		Select select = new Select(usersDropdown);
		return select.getFirstSelectedOption().getText();
	}

	public void selectUserType(String userType) {
		extentTest.log(Status.INFO, "Select user type as " + userType);
		Select select = new Select(usersDropdown);
		select.selectByVisibleText(userType);
	}

	public String getResultDropdownSelectedValue() {
		Select select = new Select(resultsDropdown);
		return select.getFirstSelectedOption().getText();
	}

	// click on the first project save search and return the project result page
	// object
	public ProjectResultsPage clickOnfirstProjectSavedSearch() {
		extentTest.log(Status.INFO, "Click On First Saved Search");
		firstSavedSearch.click();
		return new ProjectResultsPage(driver);
	}

	// click on the first company save search and return the project result page
	// object
	public CompanyResultsPage clickOnfirstCompanySavedSearch() {
		extentTest.log(Status.INFO, "Click On First Saved Search");
		firstSavedSearch.click();
		return new CompanyResultsPage(driver);
	}

	public void clickOnCompanyTab() {
		extentTest.log(Status.INFO, "Click On Company Link");
		companyTab.click();
	}

	public void clickOnCompanyTabForSaveSearchTestData() {
		extentTest.log(Status.INFO, "Click on Company tab present on My Tracking Lists page.");
		if (!companyLink.getAttribute("class").equals("active")) {
			companyTab.click();
		}
	}

	public void clickOnSelectallCheckBox() {
		extentTest.log(Status.INFO, "Click On Select All Checkbox");
		selectAllChkbox.click();
	}

	public void clickOnActionsDropdown() {
		extentTest.log(Status.INFO, "Click On Actions dropdown");
		actionsDropdown.click();
	}

	public void clickOnShareUnderActions() {
		extentTest.log(Status.INFO, "Click On Share under Actions");
		SeleniumUtils.isVisible(shareLink, driver);
		shareLink.click();
	}

	public boolean isShareUnderActionsDisplayed() {
		extentTest.log(Status.INFO, "Is Share under Actions displayed");
		return SeleniumUtils.isVisible(shareLink, driver);
	}

	public boolean isUnshareUnderActionsDisplayed() {
		extentTest.log(Status.INFO, "Is Unshare under Actions displayed");
		return SeleniumUtils.isVisible(unshareLink, driver);

	}

	public void clickOnUnShareUnderActions() {
		extentTest.log(Status.INFO, "Click On UnShare under Actions");
		unshareLink.click();
	}

	public void clickOnDoneBtn() {
		extentTest.log(Status.INFO, "Click On Done Button");
		doneBtn.click();
	}

	public void clickOnProjectLink() {
		extentTest.log(Status.INFO, "Click On Project Link");
		projectLink.click();
	}

	public boolean isProjectTabSelectedDefault() {
		extentTest.log(Status.INFO, "Verify Project tab by default active at My Saved Searches page.");
		return projectTabSelection.getAttribute("class").toUpperCase().equals("ACTIVE");
	}

	public boolean isMessageDisplayedOnPage(String message) {
		extentTest.log(Status.INFO, "Verify Message displayed");
		return driver.getPageSource().contains(message);
	}

	public String getFirstColumnHeaderText() {
		extentTest.log(Status.INFO, "Get the name of the first column header of table on Saved Searches Page");
		return tableFirstColumnHeader.getText();
	}

	public ProjectResultsPage clickOnSpecificProjectSavedSearch(String searchName) {
		extentTest.log(Status.INFO, "Click On specific Saved Search");
		driver.findElement(By.linkText(searchName)).click();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickSpecificProjectSavedSearch(String searchName) {
		extentTest.log(Status.INFO, "Delete Saved Search" + searchName);
		List<WebElement> rows = savedSearchesTable.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				
				if (cols.get(j).getText().equals(searchName)) {
					driver.findElement(By.linkText(searchName)).click();

					driver.switchTo().alert().accept();
					
				} else {
					continue;
				}
			}
		}
		return new ProjectResultsPage(driver);
	}
	
	public void deleteSpecificProjectSavedSearch(String searchName) {
		extentTest.log(Status.INFO, "Delete Saved Search" + searchName);
		List<WebElement> rows = savedSearchesTable.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				List<WebElement> EditDeleteLinks = cols.get(cols.size() - 1).findElements(By.tagName("a"));
				if (cols.get(j).getText().equals(searchName)) {
					EditDeleteLinks.get(EditDeleteLinks.size() - 1).click();

					driver.switchTo().alert().accept();
				} else {
					continue;
				}
			}
		}
	}

	public Boolean isfooterLogoDisplayed() {
		extentTest.log(Status.INFO, "Verify if the footer logo is displayed");
		return footerLogo.isDisplayed();
	}

	public Boolean isSavedSearchPresent(String searchName) {
		extentTest.log(Status.INFO, "Verify if the saved search is present on the page");
		WebElement element = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(searchName)));
		if (SeleniumUtils.isClickable(element, driver))
			return true;
		else
			return false;
	}

	public String getfooterLogoText() throws InterruptedException {
		extentTest.log(Status.INFO, "Verify footer logo text");
		Thread.sleep(2000);
		return footerLogo.getAttribute("alt");
	}

	public void deleteSavedSearches() {
		while (savedSearchDeleteBtnList.size() >= 5) {
			savedSearchDeleteBtnList.get(0).click();
			driver.switchTo().alert().accept();
			if (!savedSearchDeleteBtnList.isEmpty()) {
				SeleniumUtils.isClickable(savedSearchDeleteBtnList.get(0), driver);
			}
		}
	}

	public void deleteSavedSearchesPostTest() {
		while (savedSearchDeleteBtnList.size() != 0) {
			savedSearchDeleteBtnList.get(0).click();
			driver.switchTo().alert().accept();
			if (!savedSearchDeleteBtnList.isEmpty()) {
				SeleniumUtils.isClickable(savedSearchDeleteBtnList.get(0), driver);
			}
		}
	}

	public Integer getSavedSearchesSize() {
		return savedSearchDeleteBtnList.size();
	}

	public SavedSearchPopUp clickEditLink(int index) {
		savedSearchEditBtnList.get(index).click();
		return new SavedSearchPopUp(driver);
	}

	public boolean isProjectBarDisplayed() {

		extentTest.log(Status.INFO, "Verify if Project Dropdown menu is displayed ");

		return projectTabBar.isDisplayed();

	}

	public boolean clickPublicSavedSearch() throws InterruptedException {

		List<WebElement> rows = savedSearchesTable.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains("public"))) {
				String searchName = cols.get(0).getText();

				driver.findElement(By.linkText(searchName)).click();
				Thread.sleep(3000);

				return true;
			}
		}

		return false;
	}

	public boolean checkFontSize() {
		extentTest.log(Status.INFO, "Verify Font size of myPreferences menu ");

		// Read font-size property and print It In console.
		String fontSize = myPreferencesmenuleftNav.getCssValue("font-size");
		return fontSize.equals("11px");

	}

	public boolean verifyOwnerNameFormatCompany(String OwnerName) {
		extentTest.log(Status.INFO,
				"Verify that Owner name 'format should be last name, first name: Reading from test data file");
		return ownerNameSavedSearchCompany.getText().trim().toUpperCase().equals(OwnerName.toUpperCase());

	}

	public boolean checkSavedSearchesBreadCrumb(String expBreadCrum) {
		extentTest.log(Status.INFO, "Verify my saved searches bread crumb.");
		SeleniumUtils.isVisible(mySavedSearchesBreadCrum, driver);
		return MyAccountCommonContainerPage.verifyBreadCrumb(mySavedSearchesBreadCrum, expBreadCrum);
	}

	public void verifyFormatAndFrequencyForOwnSavedSearches(String username, String searchName, String format,
			String frequency) throws InterruptedException {

		List<WebElement> rows = savedSearchesTable.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) {

			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			if (cols.get(1).getText().equals(username) && cols.get(0).getText().equals(searchName)) {
				Assert.assertEquals(cols.get(3).getText(), format);
				Assert.assertEquals(cols.get(4).getText(), frequency);

			} else {
				continue;
			}
		}

	}

	public void verifyFormatAndFrequencyForNotOwnedSavedSearches(String username, String searchName, String format,
			String frequency) throws InterruptedException {

		List<WebElement> rows = savedSearchesTable.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) {

			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			if (!(cols.get(1).getText().equals(username)) && cols.get(0).getText().equals(searchName)) {
				Assert.assertEquals(cols.get(3).getText(), format);
				Assert.assertEquals(cols.get(4).getText(), frequency);

			} else {
				continue;
			}
		}

	}

	public boolean ismyTrackingListsMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My TrackingLists Menu option is present");

		return myTrackingMenu.isDisplayed();
	}

	public boolean ismySavedSearchesMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Saved Search option is present");

		return mySavedSearchesMenu.isDisplayed();
	}

	public boolean isProjectTabDisplayed() {
		extentTest.log(Status.INFO, "Check Project tab present at Save Searchs page.");
		return CommonUtils.checkElementExist(projectTab, driver);
	}

	public boolean isCompanyTabDisplayed() {
		extentTest.log(Status.INFO, "Verify Company tab present at Save Searchs page.");
		return CommonUtils.checkElementExist(companyLink, driver);
	}

	// check the default selection of type dropdown.
	public boolean checkDefaultTypeSelection() {
		extentTest.log(Status.INFO, "Verify the default selection for Type dropdown at Save Searches page.");
		String defaultSelectionTypeValue = "ALL";
		Select typeSelect = new Select(typeTrackingListDropdown);
		return typeSelect.getFirstSelectedOption().getText().toUpperCase().equals(defaultSelectionTypeValue);
	}

	// return the result of Filter In Type Header Column
	public boolean checkTypeDropdownOption() {
		extentTest.log(Status.INFO,
				"Check the type filter contain Private,Public and Shared options at Save Searchs page.");
		return MyAccountCommonContainerPage.checkTableTypeHeaderContent(typeTrackingListDropdown);
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

	// check the default selection of type dropdown.
	public boolean checkTypeFilterSelectedOption(String option) {
		extentTest.log(Status.INFO, "Verify type filter seleted option is as same.");
		Select typeSelect = new Select(typeTrackingListDropdown);
		return typeSelect.getFirstSelectedOption().getText().toUpperCase().equals(option.toUpperCase());
	}

	// return the result of Filter In Type Header Column
	public boolean checkTrackingNameSortedList() {
		extentTest.log(Status.INFO, "Check the tracking name is in sorting format at Save Searches page.");
		return MyAccountCommonContainerPage.verifySortingTrackingName(trackingNameList);
	}

	public boolean checkSavedSearchListPresent() {
		extentTest.log(Status.INFO, "Check the tracking name is in sorting format at Save Searches page.");
		return ((WebElement) trackingNameList).isDisplayed();
	}

	public boolean isResultPerPageDisplayed() {
		extentTest.log(Status.INFO, "Verify Result per page is present at top at Save Searchs page.");
		return resultPerPageDisplay.isDisplayed();
	}

	public boolean isPaginationDetailAtTop() {
		extentTest.log(Status.INFO, "Verify Pagination is present at top.");
		return paginationAtTop.isDisplayed();
	}

	public boolean isPaginationDetailAtBotton() {
		extentTest.log(Status.INFO, "Verify Pagination is present at bottom.");
		return paginationAtBottom.isDisplayed();
	}

	// return the result of option list in Result Per Page
	public boolean checkResultPerPageOption() {
		extentTest.log(Status.INFO, "Verify option list of result per page dropdown.");
		return MyAccountCommonContainerPage.checkResultPerPageDropdownOption(resultPerPageLst);
	}

	public boolean isSavedSearchCompanyResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Company tab ");

		return savedSaearchCompanyResultDropDown.isDisplayed();

	}

	public boolean checkColorOfProjectBar() {
		extentTest.log(Status.INFO, "Verify color of project Bar in download list ");
		String tabBarColor = projectTabBar.getCssValue("color");
		return tabBarColor.equals("rgba(16, 44, 66, 1)");
	}

	public SavedSearchPopUp clickOnEditSaveSearchLink() {
		extentTest.log(Status.INFO, "Click on edit saved search link on Saved Searches .");
		try {
			if (CommonUtils.checkElementExist(editSaveSearchLink, driver)) {
				editSaveSearchLink.click();
			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Edit link is not present on the Saved Searches");
		}
		return new SavedSearchPopUp(driver);
	}

	public boolean isEditLinkPresent() {
		extentTest.log(Status.INFO, "Verify Edit link contain in tracking list table on Saved Search page.");
		return CommonUtils.checkElementExist(editSaveSearchLink, driver);
	}

	public boolean isDeleteLinkPresent() {
		extentTest.log(Status.INFO, "Verify Delete link contain on saved searches table.");
		return CommonUtils.checkElementExist(deleteSaveSearchLink, driver);
	}

	public boolean isDeleteLinkPresentSavedSearch() {
		extentTest.log(Status.INFO, "Verify Delete link contain on saved searches table.");
		return deleteSaveSearchLink.isDisplayed();
	}

	// check the edit name replace with the existing tracking name.
	public boolean checkEditSaveSearch(String editSaveSearchName) {
		extentTest.log(Status.INFO, "Verify the edit save search name is done.");
		return MyAccountCommonContainerPage.checkEditStringPresentInTableNameList(editSaveSearchName, trackingNameList);
	}

	public void clickOnDeleteSaveSearch() {
		extentTest.log(Status.INFO, "Deleting the save search available in save search page.");
		try {
			deleteSaveSearchLink.click();
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the save search table.");
		}
	}

	public void clickOnDeleteLink() {
		extentTest.log(Status.INFO, "Deleting the save search available in save search page.");
		try {
			deleteSaveSearchLink.click();

		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the save search table.");
		}
	}

	public void clickCancelOnalert() {
		extentTest.log(Status.INFO, "click Cancxel on alert box");
		driver.switchTo().alert().dismiss();
	}

	public boolean checkDeleteSaveSearchComfirmDialogPresent() {
		extentTest.log(Status.INFO, "Deleting the save search available in save search page.");
		try {
			deleteSaveSearchLink.click();
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void clickOnCancelBtnDeleteSaveSearch() {
		extentTest.log(Status.INFO, "Click on Cancel button of Delete dialog of save search page.");
		try {
			deleteSaveSearchLink.click();
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the save search table.");
		}
	}

	public void clickOnCancelBtnDeleteSaveSearchOnly() {
		extentTest.log(Status.INFO, "Click on Cancel button of Delete dialog of save search page.");
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Delete link is not present in the save search table.");
		}
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

	public String getTimeStamp() {
		extentTest.log(Status.INFO, "Return the timestamb");
		return CommonUtils.getTimeStamp();
	}

	public String getTrackingNameSize() {
		extentTest.log(Status.INFO, "Return size of tracking list");
		String trackingName;
		if (trackingListPaginationCount.isEmpty()) {
			trackingName = "";
		} else {
			trackingName = trackingListPaginationCount.get(0).getText();
		}
		return trackingName;
	}

	public boolean verifyTrackingNameCountAfterDeletion(String countBeforeDeletion) {
		extentTest.log(Status.INFO, "Verify the count of tracking list present in the table.");
		return getTrackingNameSize().toUpperCase().equals(countBeforeDeletion.toUpperCase());
	}

	public boolean checkMyTrackingListsPositionForPlusUser(String mySavedSearchs) {
		extentTest.log(Status.INFO, "Verify My Saved Searched is at second position in the list for Plus user");
		return myAccountMenuList.get(1).getText().toUpperCase().equals(mySavedSearchs.toUpperCase());
	}

	public ShareUsers clickOnShareSavedSearchLink() {
		extentTest.log(Status.INFO, "Click on share save search link on save search table.");
		try {
			if (CommonUtils.checkElementExist(shareSavedSearchLink, driver)) {
				shareSavedSearchLink.click();
			}

		} catch (Exception e) {
			extentTest.log(Status.INFO, "Sahre link is not present on the saved search table.");
		}
		return new ShareUsers(driver);

	}

	public void selectResultPerPageDisplayed(String results) {
		extentTest.log(Status.INFO, "select" + results + " results to be displayed on the page");
		Select select = new Select(resultPerPageDisplay);
		select.selectByVisibleText(results);
	}

	public void selectResultPerPageOption(String optionToSelect) {
		extentTest.log(Status.INFO, "Set the value in Result per page option dropdown.");
		if (!resultPerPageDropdown.isEmpty()) {
			Select resultPerPageList = new Select(resultPerPageDropdown.get(0));
			resultPerPageList.selectByVisibleText(optionToSelect);
		}
	}

	public boolean isShareTrackingPeopleIconPresent() {
		extentTest.log(Status.INFO,
				"Verify Share tracking people icon contain in tracking list table on Saved Searchs page.");
		return CommonUtils.checkElementExist(imgShareTrackingListPeopleIcon, driver);
	}

	public boolean isShareLinkPresent() {
		extentTest.log(Status.INFO, "Verify Share link contain in tracking list table on Saved Searchs page.");
		return CommonUtils.checkElementExist(shareSavedSearchLink, driver);
	}

	// Check the tracking name status as required i.e Public/Private/Shared is
	// present in the table check only first occurrence of status.
	public boolean checkTrackingListStatus(String trackingNameStatus) {
		return MyAccountCommonContainerPage.checkEditStringPresentInTableNameList(trackingNameStatus,
				statusOfTrackingNameList);
	}

	public boolean isSavedSearchResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Project ");

		return savedSaearchResultDropDown.isDisplayed();

	}

	public boolean verifyOwnerNameFormat(String OwnerName) {
		extentTest.log(Status.INFO,
				"Verify that Owner name 'format should be last name, first name: Reading from test data file");
		return ownerNameSavedSearchProject.getText().toUpperCase().equals(OwnerName.toUpperCase());
	}

	public boolean checkShareLinkForAllSavedSearch_ProjectTab(String typeFilterOption) {
		boolean result = false;
		if (typeFilterOption.toUpperCase().equals("PRIVATE")) {
			result = (trackingNameList.size() - 1) == shareTrackingNameLinkList.size();
		} else {
			if (shareTrackingNameLinkList.size() == 0) {
				result = true;
			}
		}
		return result;
	}

	public boolean checkAllSavedSearchedStatus() {
		extentTest.log(Status.INFO,
				"Check the Status is present for all the Saved searches i.e Privat,Public and Shared.");
		return MyAccountCommonContainerPage.checkStatusOfEachRecord(statusOfTrackingNameList);
	}

	// Return the first saved search name list with Share link
	public String getFirstSavedSearchesNameWithShareLink() {
		return MyAccountCommonContainerPage.returnFirstTrackingNameWithShareLink(trackingNameList);
	}

	public boolean checkAllUsersWithShareLink() {
		extentTest.log(Status.INFO, "Verify Share OR Unshare link should be displayed for each user.");
		return trackingNameList.size() == shareTrackingNameLinkList.size();
	}

	// Return the first saved search name list with Share link
	public String getFirstSavedSearchWithShareLink() {
		return MyAccountCommonContainerPage.returnFirstTrackingNameWithShareLink(trackingNameList);
	}

	public ShareUsers clickOnSelectedSaveSearchShareLink(String saveSearchName) {
		extentTest.log(Status.INFO, "Click on the share link for the saved search : " + saveSearchName);
		int count = 0;
		for (String user : CommonUtils.getListFromWebElements(trackingNameList)) {
			if (user.toUpperCase().contains(saveSearchName.toUpperCase())) {
				break;
			}
			count++;
		}

		List<WebElement> shareTrackingNameLinkList2 = shareTrackingNameLinkList;
		if (shareTrackingNameLinkList2.size() > 0) {
			if (isProjectTabSelectedDefault()) {
				if (count > 0) {
					shareTrackingNameLinkList2.get(count - 1).click();
				}
			} else {
				shareTrackingNameLinkList2.get(count).click();
			}
		}
		return new ShareUsers(driver);
	}

	public void clickOnSingoutLink() {
		extentTest.log(Status.INFO, "Click on Sign Out Link.");
		signOutLink.click();
	}

	public SavedSearchPopUp performUserAction(String owner, String type, String action) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains(owner.toLowerCase()))
					&& (actualStr.toLowerCase().contains(type.toLowerCase()))) {
				cols.get(5).findElement(By.linkText(action)).click();

				return new SavedSearchPopUp(driver);
			}
		}

		return new SavedSearchPopUp(driver);
	}

	public Boolean verifyShareLinkNotVisible(String owner, String type, String share) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 2; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains(owner.toLowerCase()))
					&& (actualStr.toLowerCase().contains(type.toLowerCase()))) {
				return false;
			}
		}

		return true;
	}

	public SavedSearchPopUp editPowerRankedSearch(String search) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains(search.toLowerCase()))) {
				cols.get(5).findElement(By.linkText("Edit")).click();
				return new SavedSearchPopUp(driver);
			}
		}

		return new SavedSearchPopUp(driver);
	}

	public Boolean verifyPowerRankedSearchIcon(String search) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 2; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains(search.toLowerCase()))) {
				return cols.get(0).findElement(By.tagName("img")).getAttribute("src").toString()
						.contains("Images/ranking.png");
			}
		}

		return false;
	}

	public Boolean verifyTypeOfSavedSearchDisplayed(String type) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 2; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			if (!cols.get(2).getText().equals(type))
				return false;

		}
		return true;
	}

	public SavedSearchPopUp clickEditLink(final String savedSearchName) {
		final List<WebElement> savedSeach = driver
				.findElements(By.cssSelector("a[onclick*='" + savedSearchName + "']"));
		if (!savedSeach.isEmpty()) {
			savedSeach.get(0).click();
		}
		return new SavedSearchPopUp(driver);
	}

	public SavedSearchesPage deleteSavedSearch(final String savedSearchName) {
		final List<WebElement> savedSeach = driver
				.findElements(By.cssSelector("a[onclick*='" + savedSearchName + "']+a"));
		if (!savedSeach.isEmpty()) {
			savedSeach.get(0).click();
		}
		driver.switchTo().alert().accept();
		SeleniumUtils.isClickable(companyLink, driver);
		return new SavedSearchesPage(driver);
	}

	public List<String> AllSavedSearchesList() {
		List<String> allSavedSearchesList = new ArrayList<String>();
		for (final WebElement savedSearch : AllSavedSearchesList) {
			allSavedSearchesList.add(savedSearch.getText());
		}
		return allSavedSearchesList;
	}

	public String getSavedSearchType(final String savedSearchName) {
		final List<WebElement> savedSearchList = driver
				.findElements(By.xpath("//a[text()='" + savedSearchName + "']/parent::td//following-sibling::td[2]"));
		String savedSearchType = "";
		if (!savedSearchList.isEmpty()) {
			savedSearchType = savedSearchList.get(0).findElement(By.tagName("span")).getAttribute("innerHTML");
		}
		return savedSearchType;
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}

	public int getDeleteLinkListSize() {
		return deleteLinkList.size();
	}

	public boolean checkSaveSearchTableExist() {
		extentTest.log(Status.INFO, "Check Save Search are displayed as grid.");
		return CommonUtils.checkElementExist(saveSearchTable, driver);
	}

	public Boolean isSavedSearchDisplayed(String savedSearchName) {
		extentTest.log(Status.INFO, "Verify if the saved search is displayed on the page");
		List<WebElement> savedSeach = driver.findElements(By.cssSelector("a[onclick*='" + savedSearchName + "']+a"));
		return savedSeach.size()>0;
	}
}

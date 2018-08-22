package com.ddaqe.pages;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class CustomerAccountToolsPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterTrackingLists_ctl01_HyperLink3")
	private WebElement firstTrackingList;

	@FindBy(how = How.ID, using = "MainContent_txtEmail")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtPassword")
	private WebElement passwordTxtField;

	@FindBy(how = How.ID, using = "MainContent_btnLogin")
	private WebElement signInBtn;

	@FindBy(how = How.ID, using = "myAccountMenu12")
	private WebElement myTrackingList;

	@FindBy(how = How.ID, using = "myAccountNav11")
	private WebElement myTrackingMenu;

	@FindBy(how = How.ID, using = "myAccountNav13")
	private WebElement mySavedSearchesMenu;

	@FindBy(how = How.ID, using = "My-Account---My-Tracking-Lists")
	private WebElement breadcumbMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblErrorMsg")
	private WebElement noFileMessage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_projectLink")
	private WebElement projectTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyLink")
	private WebElement companyTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repeaterTrackingLists_ctl03_lbDelete")
	private WebElement deleteLink;

	@FindBy(how = How.LINK_TEXT, using = "______de7871")
	private WebElement savedSearchResult;

	@FindBy(how = How.LINK_TEXT, using = "TestDelete")
	private WebElement projectExists;

	@FindBy(how = How.ID, using = "My-Account---My-Preferences")
	private WebElement myPreferencesbreadcumbMenu;

	@FindBy(how = How.LINK_TEXT, using = "My Preferences")
	private WebElement myPreferencesOption;

	@FindBy(how = How.ID, using = "haveALicenseLText")
	private WebElement haveALicenseLink;

	@FindBy(how = How.ID, using = "welcomeText")
	private WebElement welcomeText;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_txtLicenseNo")
	private WebElement licenseTextField;

	@FindBy(how = How.ID, using = "myAccountNav18")
	private WebElement myPreferencesmenuleftNav;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerTop$lblTopSearchResults")
	private WebElement resultDropDown;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerBottom$lblBottomSearchResults")
	private WebElement resultDropDownBottom;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement savedSaearchResultDropDown;

	@FindBy(how = How.ID, using = "project-results-bottom")
	private WebElement projectTabBar;

	@FindBy(how = How.LINK_TEXT, using = "Company")
	private WebElement companyTabSavedSearch;

	@FindBy(how = How.LINK_TEXT, using = "Company")
	private WebElement companyTabTrackingList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement savedSaearchCompanyResultDropDown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement trackingListResultDropDown;

	@FindBy(how = How.XPATH, using = "//*[@id='rename-tracking-list-text']//div[2]//input")
	private WebElement textBox;

	@FindBy(how = How.XPATH, using = "//*[@id='popUpLBTrackingListRename']//div[3]//div//div[2]//a")
	private WebElement saveBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='popUpLBTrackingListRename']//div[3]//div//div[1]//a")
	private WebElement cancelBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[4]//div[5]//table//tbody//tr[5]//td[4]//div//a[1]")
	private WebElement trackingListEditLink;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[4]//div[5]//table//tbody//tr[8]//td[4]//div//a[2]")
	private WebElement trackingListEditLinkPLATUser;

	@FindBy(how = How.ID, using = "TrackingListType")
	private WebElement trackingListType;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl02_lbDelete']")
	private WebElement savedSearchDeleteLink;

	@FindBy(how = How.XPATH, using = "//*[@id='rename-tracking-list-text']//div[2]//input")
	private WebElement nameTextFieldPLATUser;

	@FindBy(how = How.XPATH, using = "//*[@id='popUpLBTrackingListRename']//div[3]//div//div[2]//a")
	private WebElement saveBtnPLATUser;

	@FindBy(how = How.XPATH, using = "//*[@id='popUpLBTrackingListRename']//div[3]//div//div[1]//a")
	private WebElement cancelBtnPLATUser;
	@FindBy(how = How.XPATH, using = "//*[@id='master']/div[20]/div[2]/div[1]/div[2]/span")
	private WebElement topNotchDropDown;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']/div[4]/div[5]/table/tbody/tr[8]/td[4]/div/a[1]")
	private WebElement sharelink;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[4]//div[5]//table/tbody//tr[27]//td[4]//div//a[1]")
	private WebElement sharelinkPrivateTrackingList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_TLProjectCompanyNav_projectSpan")
	private WebElement projectTabTrackingList;

	@FindBy(how = How.ID, using = "lblShareTrackingList")
	private WebElement sharePageheader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlUsers")
	private WebElement sharePageDropDown;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl01_lblOwner']")
	private WebElement ownerNameSavedSearchProject;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_repeaterSavedSearches_ctl01_lblOwner']")
	private WebElement ownerNameSavedSearchCompany;

	@FindBy(how = How.CLASS_NAME, using = "Edit")
	private WebElement editLink;

	@FindBy(how = How.LINK_TEXT, using = "My Purchases")
	private WebElement myPurchasesMenuLink;
	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[3]//table//tbody//tr[2]//td[1]//span")
	private WebElement orderDate;

	@FindBy(how = How.LINK_TEXT, using = "Company Filter")
	private WebElement companyFilterMyPreferences;

	@FindBy(how = How.LINK_TEXT, using = "Documents")
	private WebElement documentsFilterMyPreferences;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSavePreference")
	private WebElement saveBTNMyPreferences;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$chkCrossSearchLeads")
	private WebElement checkboxCompanyMyPrefences;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$chkDocument")
	private WebElement checkboxDocumentsMyPrefences;

	@FindBy(how = How.XPATH, using = "//*[@id='main-content-right']//div[4]//div[5]//table//tbody//tr[27]//td[4]//div//a[2]")
	private WebElement editLinkPrivateTrackingList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repUserList_ctl01_chkSelect")
	private WebElement shareTrackingListCheckbox;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private WebElement share;

	@FindBy(how = How.LINK_TEXT, using = "Unshare")
	private WebElement unshare;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_repUserList_ctl01_lnkShareStatus")
	private WebElement shareLink;

	@FindBy(how = How.ID, using = "myAccountHeader")
	private WebElement myAccountHeader;

	@FindBy(how = How.ID, using = "ADMIN TOOLS")
	private WebElement adminToolsHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='submenu_1']")
	private WebElement accountToolsHeader;

	public CustomerAccountToolsPage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Customer Account Tools Page");
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	// Formats into AlphaNumeric Characters
	public String formatIntoAlphanumeric(String Name) {
		return Name.replaceAll("[^A-Za-z0-9]", "");
	}

	// Append Date/TimeStamp in order to have a unique name
	public boolean appendDateTimeStamp() {
		return new SimpleDateFormat("mm/dd/yyyy").equals(orderDate);
	};

	public boolean ismyTrackingListsProjectTabIsEnabled() {
		extentTest.log(Status.INFO, "Verify if Project tab option is present and active in tracking list-PLAT User");

		return projectTabTrackingList.isEnabled();
	}

	public boolean ismyTrackingListsCompanyTabIsDisplayed() {
		extentTest.log(Status.INFO, "Verify if Company tab option is present in tracking list-PLAT User");

		return companyTabTrackingList.isDisplayed();

	}

	public void isShareLinkPrivateisDisplayed() {
		extentTest.log(Status.INFO, "Verify if Share link is present in tracking list-PLAT User");

		sharelink.equals("Share");
		share.click();
		unshare.click();
	}

	public ProjectResultsPage clickOnFirstTrackingList() {
		extentTest.log(Status.INFO, "Click on first Tracking List");
		firstTrackingList.click();
		return new ProjectResultsPage(driver);

	}

	public boolean isShareLinkisDisplayed() {
		extentTest.log(Status.INFO, "Verify if Share link is present in tracking list-PLAT User");

		return sharelink.isDisplayed();

	}

	public void clickOnShareLink() {
		extentTest.log(Status.INFO, "Clicking on Share link is present in tracking list-PLAT User");

		sharelink.click();

	}

	public void clickOnEditPrivateLink() {
		extentTest.log(Status.INFO, "Clicking on Edit link for private tracking list-PLAT User");

		editLinkPrivateTrackingList.click();
		cancelBtnPLATUser.click();

	}

	public void clickOnSharePrivateLink() {
		extentTest.log(Status.INFO, "Clicking on Share link for private tracking list-PLAT User");

		sharelinkPrivateTrackingList.click();

	}

	public void clickOnCompanyFilter() {
		extentTest.log(Status.INFO, "Clicking on Company Filter under my Preferences");

		companyFilterMyPreferences.click();

	}

	public void clickOnDocumentFilter() {
		extentTest.log(Status.INFO, "Clicking on Document Filter under my Preferences");

		documentsFilterMyPreferences.click();

	}

	public void checkProjectFilterCheckBox() {
		extentTest.log(Status.INFO, "clickOnProjectFilterCheckBox");

		checkboxCompanyMyPrefences.click();
		saveBTNMyPreferences.click();

	}

	public void checkDocumentFilterCheckBox() {
		extentTest.log(Status.INFO, "clickOnDocumentFilterCheckBox");

		checkboxDocumentsMyPrefences.click();
		saveBTNMyPreferences.click();

	}

	public void clickOnEditLink() {
		extentTest.log(Status.INFO, "Clicking on Edit link is present in tracking list-PLAT User");

		editLink.click();

	}

	public boolean ismyTrackingListsMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My TrackingLists Menu option is present");

		return myTrackingMenu.isDisplayed();
	}

	public void trackingMenu() {
		extentTest.log(Status.INFO, "Click On My Tracking Lists Menu Link");
		myTrackingList.click();

	}

	public void clickSavedSearchDeleteLink() {
		extentTest.log(Status.INFO, "Click On Delete Link on Saved Searches-Plus user");
		savedSearchDeleteLink.click();
	}

	public boolean ismySavedSearchesMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Saved Search option is present");

		return mySavedSearchesMenu.isDisplayed();
	}

	public boolean isBreadcrumbAndMyAccountMyTrackingDisplayed() {
		extentTest.log(Status.INFO, "Verify that Breadcrumb for My Account - My Tracking Lists is  displayed ");

		return breadcumbMenu.isDisplayed();

	}

	public boolean isNameTextBoxDisplayed() {
		extentTest.log(Status.INFO, "Verify that name text box- My Tracking Lists is  displayed ");

		return textBox.isDisplayed();

	}

	public boolean isSavebtnDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that Save button on Edit pop up- My Tracking Lists is  displayed-PLUS user ");

		return saveBtn.isDisplayed();

	}

	public boolean isTrackingListTypeDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that Tracking list type Drop Down- My Tracking Lists is  displayed-PLAT user ");

		return trackingListType.isDisplayed();

	}

	public boolean isCancelbtnDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that Cancel button on Edit pop up- My Tracking Lists is  displayed-PLUS user ");

		return cancelBtn.isDisplayed();

	}

	public boolean isNameTextFieldDisplayedPLATUser() {
		extentTest.log(Status.INFO,
				"Verify that Name text field on Edit pop up- My Tracking Lists is  displayed-PLAT user ");

		return nameTextFieldPLATUser.isDisplayed();

	}

	public boolean isSavebtnDisplayedPLATUser() {
		extentTest.log(Status.INFO,
				"Verify that Save button on Edit pop up- My Tracking Lists is  displayed-PLAT user ");

		return saveBtnPLATUser.isDisplayed();

	}

	public boolean isCancelbtnDisplayedPLATUser() {
		extentTest.log(Status.INFO,
				"Verify that Cancel button on Edit pop up- My Tracking Lists is  displayed-PLAT user ");

		return cancelBtnPLATUser.isDisplayed();

	}

	public boolean isBreadcrumbAndMyAccountMyPreferencesDisplayed() {
		extentTest.log(Status.INFO, "Verify that Breadcrumb for My-Account---My-Preferences is  displayed ");

		return myPreferencesbreadcumbMenu.isDisplayed();

	}

	public boolean myPreferencesOptionDisplayed() {
		extentTest.log(Status.INFO, "Verify that My-Preferences option is displayed under Account Tools");

		return myPreferencesOption.isDisplayed();

	}

	public boolean isNoDownloadMessageDisplayed() {
		extentTest.log(Status.INFO, "Verify that No Download message is displayed ");

		String expected = noFileMessage.getText();
		return expected.equals("You do not have any batch downloads for projects");

	}

	public boolean verifyShareHeaderMessage() {
		extentTest.log(Status.INFO, "Verify the share page header title ");
		String actual = sharePageheader.getText();
		return actual.equals("Share Tracking List *");

	}

	public boolean isShareDropDownDisplayed() {
		extentTest.log(Status.INFO, "Verify that drop down is present-Share Tracking list");

		return sharePageDropDown.isDisplayed();

	}

	public boolean filterDropdownValue() {
		extentTest.log(Status.INFO, "Verify that 'All' value is selected and present in Share page drop down");
		WebElement mySelectElm = sharePageDropDown;
		Select mySelect = new Select(mySelectElm);
		String Option = mySelect.getFirstSelectedOption().getText();
		return Option.equals("All");

	}

	public boolean allFilterDropdownValue() {
		extentTest.log(Status.INFO, "Verify that 'All' value is selected and present in Share page drop down");
		WebElement mySelectElm = trackingListType;
		Select mySelect = new Select(mySelectElm);
		String Option = mySelect.getFirstSelectedOption().getText();
		return Option.equals("All");

	}

	public boolean verifyOwnerNameFormat(String OwnerName) {
		extentTest.log(Status.INFO,
				"Verify that Owner name 'format should be last name, first name: Reading from test data file");
		return ownerNameSavedSearchProject.equals(OwnerName);
	}

	public boolean verifyOwnerNameFormatCompany(String OwnerName) {
		extentTest.log(Status.INFO,
				"Verify that Owner name 'format should be last name, first name: Reading from test data file");
		return ownerNameSavedSearchCompany.equals(OwnerName);
	}

	public boolean isCompanyTabDisplayed() {
		extentTest.log(Status.INFO, "Verify that company tab is displayed ");

		return companyTab.isDisplayed();

	}

	public boolean isProjectTabDisplayed() {
		extentTest.log(Status.INFO, "Verify that  project is displayed ");

		return projectTab.isDisplayed();
	}

	public void deleteProject() {
		extentTest.log(Status.INFO, "Verify that  click on Delete Link ");
		deleteLink.click();
	}

	public Boolean isProjectDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that project is present in Tracking list result list when Delete is Canceled ");
		return projectExists.isDisplayed();

	}

	public Boolean isSavedSearchResultDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that project is present in Saved Search list result list when Delete is Canceled ");
		return savedSearchResult.isDisplayed();

	}

	public boolean checkFontSize() {
		extentTest.log(Status.INFO, "Verify Font size of myPreferences menu ");
		// Read font-size property and print It In console.
		String fontSize = myPreferencesmenuleftNav.getCssValue("font-size");
		return fontSize.equals("11px");

	}

	public boolean checkColorofElements() {
		extentTest.log(Status.INFO, "Verify color of myPreferences menu ");
		String fontColor = myPreferencesmenuleftNav.getCssValue("color");
		return fontColor.equals("rgba(242, 77, 0, 1)");
	}

	public boolean checkifElementisBold() {
		extentTest.log(Status.INFO, "Verify Font Weight of myPreferences menu ");
		String fontWeight = myPreferencesmenuleftNav.getCssValue("font-weight");
		return fontWeight.equals("Bold");
	}

	public boolean checkColorofElementsTrackingList() {
		extentTest.log(Status.INFO,
				"Verify color of tracking list menus on right side-Checking only by verifying the color on the elements ");
		String MyAccountHeader = myAccountHeader.getCssValue("background-color");
		String AccountToolheader = accountToolsHeader.getCssValue("background-color");
		return MyAccountHeader.equals(AccountToolheader) == false;
	}

	public boolean isResultDropDowndisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed ");

		return resultDropDown.isDisplayed();

	}

	public boolean isResultDropDownBottomdisplayed() {
		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed at the bottom");
		return resultDropDownBottom.isDisplayed();
	}

	public boolean isSavedSearchResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Project ");

		return savedSaearchResultDropDown.isDisplayed();

	}

	public boolean isTrackingListResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Tracking menu ");

		return trackingListResultDropDown.isDisplayed();

	}

	public void clickOnCompanyTabTrackingList() {

		extentTest.log(Status.INFO, "Click on Company tab under Tracking list ");

		companyTabTrackingList.click();

	}

	public void clickOnCompanyTab() {

		extentTest.log(Status.INFO, "Click on Company tab under Saved Search page ");

		companyTabSavedSearch.click();

	}

	public void clickOnTrackingListEditLink() {

		extentTest.log(Status.INFO, "Click on Edit  under Tracking Link ");

		trackingListEditLink.click();

	}

	public void clickOnTrackingListEditLinkPLATuser() {

		extentTest.log(Status.INFO, "Click on Edit  under Tracking Link ");

		trackingListEditLinkPLATUser.click();

	}

	public boolean isMyAccountTopNotchDisplayed() {
		extentTest.log(Status.INFO, "Verify that Tracking list/Saved Search has Drop down top notch");
		return topNotchDropDown.isDisplayed();

	}

	public boolean isSavedSearchCompanyResultDropDownDisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed-Company tab ");

		return savedSaearchCompanyResultDropDown.isDisplayed();

	}

	public boolean isProjectBarDisplayed() {

		extentTest.log(Status.INFO, "Verify if Project Dropdown menu is displayed ");

		return projectTabBar.isDisplayed();

	}

	public boolean checkColorOfProjectBar() {
		extentTest.log(Status.INFO, "Verify color of project Bar in download list ");
		String tabBarColor = projectTabBar.getCssValue("color");
		return tabBarColor.equals("rgba(16, 44, 66, 1)");
	}

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

	public String noFileMessage() {

		extentTest.log(Status.INFO, "Verify the message when No Downloads are available ");
		return noFileMessage.getText();
	}

	public boolean isResultPerPageCentered() {
		if (resultDropDown.findElement(By.xpath("./ancestor::td")).getAttribute("align").equals("center"))
			return true;
		else
			return false;
	}

	public boolean isResultPerPageBottomCentered() {
		if (resultDropDownBottom.findElement(By.xpath("./ancestor::td")).getAttribute("align").equals("center"))
			return true;
		else
			return false;
	}

}
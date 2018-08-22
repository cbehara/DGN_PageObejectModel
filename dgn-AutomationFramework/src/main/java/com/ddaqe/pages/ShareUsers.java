package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class ShareUsers {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblUserName')]")
	private List<WebElement> shareTrackingList;

	@FindBy(how = How.XPATH, using = "//img[contains(@id,'imgInfo')]")
	private List<WebElement> infoIconList;

	@FindBy(how = How.ID, using = "imgInfo_0")
	private WebElement firstInfoIcon;

	@FindBy(how = How.ID, using = "ssoEmailId")
	private WebElement ssoEmailID;

	@FindBy(how = How.ID, using = "usrAddress")
	private WebElement ssoUserAddress;

	@FindBy(how = How.ID, using = "orgName")
	private WebElement ssoOrgName;

	@FindBy(how = How.ID, using = "ssoPhone")
	private WebElement ssoPhone;

	@FindBy(how = How.ID, using = "ssoIndustryName")
	private WebElement ssoIndustryName;

	@FindBy(how = How.ID, using = "ssoCompanyType")
	private WebElement ssoCompanyType;

	@FindBy(how = How.LINK_TEXT, using = "[X]")
	private WebElement ssoCloseIcon;

	@FindBy(how = How.ID, using = "lblShareTrackingList")
	private WebElement lblShareTrackingLists;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlUsers")
	private WebElement usersSharedUnshareDropdown;

	@FindBy(how = How.ID, using = "btnManageUserActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lnkShare")
	private WebElement actionsSharedLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lnkUnShare")
	private WebElement actionsUnsharedLink;

	@FindBy(how = How.ID, using = "users-select-all")
	private WebElement userSelectAllChk;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkShareStatus')]")
	private List<WebElement> sharedLinkList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblUserName')]")
	private List<WebElement> sharedUserLabelList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblUserName')]//..//..//..//input[@type='checkbox']")
	private List<WebElement> eachSharedUserCheckboxList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblCount")
	private WebElement totalShareUserCount;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lnkBtnDone")
	private WebElement doneBtn;

	@FindBy(how = How.LINK_TEXT, using = "My Account - My Tracking Lists")
	private WebElement navigateTrackingListViaBreadCrumb;

	@FindBy(how = How.LINK_TEXT, using = "My Account - My Saved Searches")
	private WebElement navigateSavedSearchViaBreadCrumb;

	@FindBy(how = How.LINK_TEXT, using = "Share")
	private WebElement shareLink;

	@FindBy(how = How.LINK_TEXT, using = "Unshare")
	private WebElement unshareLink;

	@FindBy(how = How.XPATH, using = ".//*[@id='main-content']/div[1]")
	private WebElement breadCrumb;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblUserName')]")
	private List<WebElement> shareUserList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkShareStatus')]")
	private List<WebElement> shareLinkList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkShareStatus')]")
	private List<WebElement> unShareLinkList;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_lnkBtnDone']")
	private WebElement doneBTNShare;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ddlUsers")
	private WebElement sharePageDropDown;

	public boolean isShareTrackingListDisplayed() {
		extentTest.log(Status.INFO, "Verify Share tracking list name is displayed on share user page.");
		return lblShareTrackingLists.isDisplayed();
	}

	public String getShareTrackingListLabel() {
		extentTest.log(Status.INFO, "Get the label for Share tracking list name is displayed on share user page.");
		return lblShareTrackingLists.getText();
	}

	public ShareUsers(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		PageFactory.initElements(driver, this);
	}

	public boolean verifyInfoIconForShareUser() {
		extentTest.log(Status.INFO, "Verify that an info icon next to each share user is displayed.");
		return shareTrackingList.size() == infoIconList.size();
	}

	public void clickOnFirstInfoIcon() {
		extentTest.log(Status.INFO, "Click on first info icon of the share user.");
		firstInfoIcon.click();
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

	public boolean isSSOEmailIDPresent() {
		extentTest.log(Status.INFO, "Verify SSO Email address is present in SSO Info dialog.");
		return ssoEmailID.getText().length() > 0;
	}

	public boolean isSSOEmailIDIsLink() {
		extentTest.log(Status.INFO, "Verify SSO Email address present in SSO Info dialog is LINK.");
		return ssoEmailID.getAttribute("href").length() > 0;
	}

	public boolean isSSOUserAddressPresent() {
		extentTest.log(Status.INFO, "Verify SSO user address is present in SSO Info dialog.");
		return ssoUserAddress.getText().length() > 0;
	}

	public boolean isSSOOrgNamePresent() {
		extentTest.log(Status.INFO, "Verify SSO orgName is present in SSO Info dialog.");
		return ssoOrgName.getText().length() > 0;
	}

	public boolean isSSOPhonePresent() {
		extentTest.log(Status.INFO, "Verify SSO Phone is present in SSO Info dialog.");
		return ssoPhone.getText().length() > 0;
	}

	public boolean isSSOIndustryNamePresent() {
		extentTest.log(Status.INFO, "Verify SSO Insdustry Name is present in SSO Info dialog.");
		return ssoIndustryName.getText().length() > 0;
	}

	public boolean isSSOCompanyTypePresent() {
		extentTest.log(Status.INFO, "Verify SSO company type is present in SSO Info dialog.");
		return ssoCompanyType.getText().length() > 0;
	}

	public boolean checkSSOInfoDialog() {
		extentTest.log(Status.INFO, "Verify SSO info dialog.");
		boolean result = true;
		if (!isSSOEmailIDPresent()) {
			result = false;
		} else if (!isSSOEmailIDIsLink()) {
			result = false;
		} else if (!isSSOUserAddressPresent()) {
			result = false;
		} else if (!isSSOOrgNamePresent()) {
			result = false;
		} else if (!isSSOPhonePresent()) {
			result = false;
		} else if (!isSSOIndustryNamePresent()) {
			result = false;
		} else if (!isSSOCompanyTypePresent()) {
			result = false;
		}

		return result;
	}

	public void clickOnCloseInfoDialog() {
		extentTest.log(Status.INFO, "Close the info dialog.");
		ssoCloseIcon.click();
	}

	// return the result of Users dropdown option check
	public boolean checkUsersDropdownOption() {
		extentTest.log(Status.INFO, "Check the Users dropdown contain All, Shared, Unshared Option.");
		return MyAccountCommonContainerPage.checkUsersDropdownOptionOnShareUser(usersSharedUnshareDropdown);
	}

	public void shareTrackingWithUser(String userName) {
		MyAccountCommonContainerPage.shareSpecificUser(shareUserList, shareLinkList, userName);
	}

	public void moveToActionsDropdown() {
		extentTest.log(Status.INFO, "Move/Mouse over the Actions user list on Shared user page.");
		Actions userAction = new Actions(driver);
		userAction.moveToElement(actionsDropdown).build().perform();
	}

	public void isUnshareDisplayed(String userName) {
		MyAccountCommonContainerPage.UnshareSpecificUser(shareUserList, shareLinkList, userName);
	}

	public boolean isActionSharedLinkPresent() {
		extentTest.log(Status.INFO, "Verify actions dropdown contain Shared link.");
		return CommonUtils.checkElementExist(actionsSharedLink, driver);
	}

	public boolean isActionUnsharedLinkPresent() {
		extentTest.log(Status.INFO, "Verify actions dropdown contain Unshared link.");
		return CommonUtils.checkElementExist(actionsUnsharedLink, driver);
	}

	public boolean isSelectAllCheckboxPresent() {
		extentTest.log(Status.INFO, "Verify Select All checkbox is present.");
		return userSelectAllChk.isDisplayed();
	}

	public boolean checkAllUsersWithCheckbox() {
		extentTest.log(Status.INFO, "Verify All users should be displayed with checkbox .");
		return sharedUserLabelList.size() == eachSharedUserCheckboxList.size();
	}

	public boolean checkAllUsersWithShareLink() {
		extentTest.log(Status.INFO, "Verify Share OR Unshare link should be displayed for each user.");
		return sharedUserLabelList.size() == sharedLinkList.size();
	}

	public boolean verifyTotalNoUsers() {
		extentTest.log(Status.INFO, "Verify Total number of users on the license is displayed on the header.");
		return totalShareUserCount.isDisplayed();
	}

	// check the default selection of type dropdown.
	public boolean checkDefaultUserSelection() {
		extentTest.log(Status.INFO, "Verify the default selection for User dropdown.");
		String defaultSelectionUserValue = "ALL";
		Select userSelect = new Select(usersSharedUnshareDropdown);
		return userSelect.getFirstSelectedOption().getText().toUpperCase().equals(defaultSelectionUserValue);
	}

	public void selectUserOptionFromDropdown(String optionToSelect) {
		extentTest.log(Status.INFO, "Setting the user filter option as asked to set i.e : " + optionToSelect);
		Select userList = new Select(usersSharedUnshareDropdown);
		userList.selectByVisibleText(optionToSelect);
	}

	public void adjustShareUnshareList(String optionToSelect) {
		String sharedListUser = "Shared";

		if (sharedLinkList.size() == 0) {
			selectUserOptionFromDropdown("All");
			if (optionToSelect.equals(sharedListUser)) {
				clickOnShareLink();
			} else {
				clickOnUnshareLink();
			}
			selectUserOptionFromDropdown(optionToSelect);
		}
	}

	public void clickOnShareLink() {
		extentTest.log(Status.INFO, "Click on Share link present on share user page.");
		shareLink.click();
	}

	public void clickOnUnshareLink() {
		extentTest.log(Status.INFO, "Click on Unhare link present on share user page.");
		unshareLink.click();
	}

	public TrackingLists clickOnDoneButton_TrackingList() {
		extentTest.log(Status.INFO, "Click on done button present on share user page.");
		doneBtn.click();
		return new TrackingLists(driver);
	}

	public SavedSearchesPage clickOnDoneButton_SavedSearch() {
		extentTest.log(Status.INFO, "Click on done button present on share user page.");
		doneBtn.click();
		return new SavedSearchesPage(driver);
	}

	public TrackingLists navigateViaMyTrackingBreadCrumb() {
		extentTest.log(Status.INFO, "Navigate to My Tracking page via breadcrum from Share user page.");
		navigateTrackingListViaBreadCrumb.click();
		return new TrackingLists(driver);
	}

	public SavedSearchesPage navigateViaSavedSearchBreadCrumb() {
		extentTest.log(Status.INFO, "Navigate to Saved search via breadcrum from Share user page.");
		navigateSavedSearchViaBreadCrumb.click();
		return new SavedSearchesPage(driver);
	}

	// return the breadcrumb
	public String getShareUserBreadCrumb() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText();
	}

	// compare the breadcrumb
	public boolean verifyBreadCrumb(String compareString) {
		extentTest.log(Status.INFO, "verify the Breadcrumb Text");
		return getShareUserBreadCrumb().trim().toUpperCase().equals(compareString.toUpperCase());
	}

	// compare the share page lable
	public boolean verifySharePageLabel(String compareString) {
		extentTest.log(Status.INFO, "Verify the share page label Text");
		return getShareTrackingListLabel().trim().toUpperCase().contains(compareString.toUpperCase());
	}

	public void clickOnSpecificUserShareUnshareLink(String userName) {
		extentTest.log(Status.INFO, "Click on the share/unshare link for the specific user : " + userName);
		int count = 0;
		for (String user : CommonUtils.getListFromWebElements(sharedUserLabelList)) {
			if (user.trim().toUpperCase().contains(userName.toUpperCase())) {
				break;
			}
			count++;
		}
		sharedLinkList.get(count).click();
	}

	public void clickOnMultipleUserShareUnshareLink(List<String> list) {
		extentTest.log(Status.INFO, "Click on the share/unshare link for multiple user on share user page.");
		for (String name : list) {
			clickOnSpecificUserShareUnshareLink(name);
		}
	}

	public boolean verifyShareStatusWithSpecificUser(String userName, String shareStatus) {
		extentTest.log(Status.INFO, "Verify the status (Share/unshare) for respective user : " + userName);
		int count = 0;
		for (String user : CommonUtils.getListFromWebElements(sharedUserLabelList)) {
			if (user.toUpperCase().contains(userName.toUpperCase())) {
				break;
			}
			count++;
		}
		return sharedLinkList.get(count).getText().toUpperCase().equals(shareStatus.toUpperCase());
	}

	public boolean verifyUserPresent(String userName) {
		return CommonUtils.checkExpectedStringContainInCompleteList(userName, sharedUserLabelList);
	}

}

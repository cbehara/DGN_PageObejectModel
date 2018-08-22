package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CoreFrameworkException;
import com.ddaqe.utils.DGNEnum;

public class AdminUsersPage extends MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblUserName')]")
	private List<WebElement> userNameList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkRemoveUser')]/span")
	private List<WebElement> removeUserLicense;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'cbBypass')]")
	private List<WebElement> byPassUsers;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_aHeader")
	private WebElement dodgeHeader;

	@FindBy(how = How.ID, using = "ctl00_cplBody_ddlUser")
	private WebElement ddlUserFilter;

	@FindBy(how = How.ID, using = "dvSelectAll")
	private WebElement selectAllChk;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblCount")
	private WebElement lblUserCount;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'cplBody_actions')]//a")
	private List<WebElement> userActions;

	@FindBy(how = How.ID, using = "btnManageUserActions")
	private WebElement manageUserActions;

	@FindBy(how = How.ID, using = "lnkApprove")
	private WebElement approveLnk;

	@FindBy(how = How.ID, using = "lnkRemove")
	private WebElement removeLnk;

	@FindBy(how = How.ID, using = "ssoEmailId")
	private WebElement ssoEmailId;

	@FindBy(how = How.ID, using = "usrAddress")
	private WebElement userAddress;

	@FindBy(how = How.ID, using = "orgName")
	private WebElement orgName;

	@FindBy(how = How.ID, using = "ssoPhone")
	private WebElement ssoPhone;

	@FindBy(how = How.ID, using = "ssoIndustryName")
	private WebElement ssoIndustryName;

	@FindBy(how = How.ID, using = "ssoCompanyType")
	private WebElement ssoCompanyType;

	@FindBy(how = How.LINK_TEXT, using = "Make Admin")
	private WebElement makeAdminLink;

	@FindBy(how = How.LINK_TEXT, using = "Make User")
	private WebElement makeUserLink;

	@FindBy(how = How.LINK_TEXT, using = "Profiles")
	private WebElement profilesLink;

	@FindBy(how = How.LINK_TEXT, using = "Reports")
	private WebElement reportsLink;

	@FindBy(how = How.LINK_TEXT, using = "Do Not Allow User to Bypass Profile")
	private WebElement doNotAllowUserByPassProfile;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'chkSelect')]")
	private List<WebElement> chkUsers;

	@FindBy(how = How.XPATH, using = "//img[contains(@id,'imgInfo')]")
	private List<WebElement> imgUserIcon;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'cancelPopup')]")
	private WebElement imgCloseIcon;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'btnRole')]")
	private List<WebElement> btnUserRole;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'btnStatus')]")
	private List<WebElement> btnUserStatus;

	@FindBy(how = How.CLASS_NAME, using = "notApproved")
	private List<WebElement> btnPendingStatus;

	@FindBy(how = How.CLASS_NAME, using = "approved")
	private List<WebElement> btnApprovedStatus;

	@FindBy(how = How.CLASS_NAME, using = "adminErrorLabel")
	private WebElement errorMsg;

	@FindBy(how = How.XPATH, using = "//div[@class='mngUserChckBox']//input")
	private List<WebElement> userChk;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_ddlProfileList")
	private WebElement ddlProfileList;

	@FindBy(how = How.XPATH, using = "//input[@class='notApproved']//parent::div//preceding-sibling::div[@class='mngUserChckBox']//input")
	private WebElement chkUserNotApproved;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix adminContainer']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.CLASS_NAME, using = "projectProfileLabel")
	private WebElement marketingProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_ddlLeadProfileList")
	private WebElement marketingProfileUser;

	public AdminUsersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Accounts - Users Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void removeUserFromLicense(String userEmail) {
		extentTest.log(Status.INFO, "Remove the user from license");

		String email = "";
		for (int i = 0; i < removeUserLicense.size(); i++) {
			imgUserIcon.get(i).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ssoEmailId));
			email = ssoEmailId.getText().trim();
			if (email.equalsIgnoreCase(userEmail)) {
				removeUserLicense.get(i).click();
				break;
			}
			imgCloseIcon.click();

		}
	}

	public void clickOnDodgeHeader() {
		extentTest.log(Status.INFO, "Click the Dodge Header");
		dodgeHeader.click();
	}

	public boolean verifyUserFilterOptions() {
		extentTest.log(Status.INFO, "Verify all the options in users filter drop down");
		boolean isMatch = false;
		int i = 0;
		ddlUserFilter.click();
		Select se = new Select(ddlUserFilter);
		List<WebElement> options = se.getOptions();
		for (DGNEnum.MyAccountUserFilterOptions opt : DGNEnum.MyAccountUserFilterOptions.values()) {
			if (options.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		ddlUserFilter.click();
		return isMatch;

	}

	public void clickUserFilterOptions(DGNEnum.MyAccountUserFilterOptions opt) {
		extentTest.log(Status.INFO, "Verify all the options in users filter drop down");
		ddlUserFilter.click();
		Select se = new Select(ddlUserFilter);
		List<WebElement> options = se.getOptions();
		options.get(opt.status()).click();
	}

	public boolean verifyUserActions() {

		extentTest.log(Status.INFO, "Verify all the options in users Actions drop down");
		boolean isMatch = false;
		int i = 0;
		manageUserActions.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(doNotAllowUserByPassProfile));
		for (DGNEnum.MyAccountUserActions opt : DGNEnum.MyAccountUserActions.values()) {
			if (userActions.get(i).getText().equalsIgnoreCase(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public boolean verifyAllowDoNotAllowUserActions() {
		extentTest.log(Status.INFO, "Verify specific option of users Actions drop down");
		int matchCount = 0;

		for (DGNEnum.MyAccountAllowDoNotAllowUserAction opt : DGNEnum.MyAccountAllowDoNotAllowUserAction.values()) {
			for (WebElement userAction : userActions) {
				if (userAction.getText().equalsIgnoreCase(opt.description())) {
					matchCount++;
					break;
				}
			}
		}

		if (matchCount == 2) {
			return true;
		}
		return false;
	}

	public void clickOnActionsMenu() {
		extentTest.log(Status.INFO, "Clicking on Actions drop down");
		manageUserActions.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(doNotAllowUserByPassProfile));
	}

	public boolean isSelectAllCheckBoxDisplayed() {
		extentTest.log(Status.INFO, "Verify if the select all checkbox is displayed");
		return selectAllChk.isDisplayed();
	}

	public boolean isUserCountDisplayed() {
		extentTest.log(Status.INFO, "Verify if the user count is displayed");
		return lblUserCount.isDisplayed();
	}

	public Integer getUserCount() {
		String lblCount = lblUserCount.getText();
		lblCount = lblCount.replaceAll("[^0-9]", "");
		return Integer.parseInt(lblCount);
	}

	public Integer getChkUsers() {
		return chkUsers.size();
	}

	public Integer getUserNamesSize() {
		return userNameList.size();
	}

	public Integer getUserIconSize() {
		return imgUserIcon.size();
	}

	public Integer getUserRemoveLicenseSize() {
		return removeUserLicense.size();
	}

	public Integer getUserRoleSize() {
		return btnUserRole.size();
	}

	public Integer getUserStatusSize() {
		return btnUserStatus.size();
	}

	public boolean isErrorMsgDisplayed() {
		try {
			extentTest.log(Status.INFO, "Verify is the error message is displayed");
			return errorMsg.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean verifyUserNameFormat() {
		extentTest.log(Status.INFO, "Verify user name format");
		for (int i = 0; i < userNameList.size(); i++) {
			String[] name = userNameList.get(i).getText().split(",");
			if (name.length == 2) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyRoleSelected(DGNEnum.MyAccountUserFilterOptions opt) {
		extentTest.log(Status.INFO, "Verify Role Selected ->" + opt.name());
		boolean isRoleSelected = false;
		if (isErrorMsgDisplayed()) {
			return false;
		}
		for (int i = 0; i < btnUserRole.size(); i++) {
			if (opt.name().contains(btnUserRole.get(i).getAttribute("value"))) {
				isRoleSelected = true;
			} else {
				isRoleSelected = false;
			}
		}
		return isRoleSelected;
	}

	public boolean verifyStatusSelected(DGNEnum.MyAccountUserFilterOptions opt) {
		extentTest.log(Status.INFO, "Verify Status Selected ->" + opt.name());
		boolean isSelected = false;
		if (isErrorMsgDisplayed()) {
			return true;
		}
		for (int i = 0; i < btnUserStatus.size(); i++) {
			if (opt.name().contains(btnUserStatus.get(i).getAttribute("value"))) {
				isSelected = true;
			} else {
				isSelected = false;
			}
		}
		return isSelected;
	}

	public String userRoleToggle(DGNEnum.UserRoleOptions opt, String userEmail, boolean toggle) {
		extentTest.log(Status.INFO, "Verify Role Selected ->" + opt.name());
		String name = "", isMatchedName = "";
		if (isErrorMsgDisplayed()) {
			throw new CoreFrameworkException("The list is empty..");
		}
		for (int i = 0; i < btnUserRole.size(); i++) {
			imgUserIcon.get(i).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ssoEmailId));
			name = ssoEmailId.getText().trim();
			imgCloseIcon.click();
			if (name.equalsIgnoreCase(userEmail)) {
				isMatchedName = name;
				if (btnUserRole.get(i).getAttribute("value").trim().equalsIgnoreCase(opt.name().toUpperCase().trim())) {
					if (toggle) {
						btnUserRole.get(i).click();
						break;
					} else {
						userChk.get(i).click();
						break;
					}
				}
				break;
			}
		}
		return isMatchedName;
	}

	public String userRemove(DGNEnum.MyAccountUserFilterOptions opt, String userEmail, boolean toggle) {
		extentTest.log(Status.INFO, "Verify Role Selected ->" + opt.name());
		String name = "";
		if (isErrorMsgDisplayed()) {
			throw new CoreFrameworkException("The list is empty..");
		}
		for (int i = 0; i < removeUserLicense.size(); i++) {
			imgUserIcon.get(i).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ssoEmailId));
			name = ssoEmailId.getText().trim();
			if (name.equals(userEmail)
					&& btnUserRole.get(i).getAttribute("value").equalsIgnoreCase(opt.name().toUpperCase())) {
				userChk.get(i).click();
				if (toggle) {
					removeUserLicense.get(i).click();
				}
			}
			imgCloseIcon.click();
			break;
		}
		return name;
	}

	public String byPassUser(String userEmail) {
		extentTest.log(Status.INFO, "By Pass User");
		String name = "";
		if (isErrorMsgDisplayed()) {
			throw new CoreFrameworkException("The list is empty..");
		}
		for (int i = 0; i < byPassUsers.size(); i++) {
			imgUserIcon.get(i).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ssoEmailId));
			name = ssoEmailId.getText().trim();
			if (name.equals(userEmail)) {
				userChk.get(i).click();
				byPassUsers.get(i).click();
			}
			imgCloseIcon.click();
			break;
		}
		return name;
	}

	public Boolean verifyUserToolTip() {
		extentTest.log(Status.INFO, "Verify the user tooltip");
		Boolean isMatch = false;
		if (isErrorMsgDisplayed()) {
			throw new CoreFrameworkException("The list is empty..");
		}
		imgUserIcon.get(1).click();
		if (ssoEmailId.isDisplayed() && userAddress.isDisplayed() && orgName.isDisplayed() && ssoPhone.isDisplayed()
				&& ssoCompanyType.isDisplayed() && ssoIndustryName.isDisplayed())
			isMatch = true;
		return isMatch;
	}

	public void clickOnApproveLink() {
		extentTest.log(Status.INFO, "Clicking on approve link under actions menu");
		approveLnk.click();
	}

	public void clickOnRemoveLink() {
		extentTest.log(Status.INFO, "Clicking on remove link under actions menu");
		removeLnk.click();
	}

	public String verifyErrorMessage() {
		extentTest.log(Status.INFO, "Verify the error message");
		return errorMsg.getText();
	}

	public void clickOnMakeAdminActions() {
		extentTest.log(Status.INFO, "Clicking on the 'Make Admin' Link under Actions");
		makeAdminLink.click();
	}

	public void clickOnMakeUserActions() {
		extentTest.log(Status.INFO, "Clicking on the 'Make User' Link under Actions");
		makeUserLink.click();
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public ManageProfilesPage clickOnProfilesLink() {
		extentTest.log(Status.INFO, "Clicking on the 'Profiles' Link under Admin Tools menu on the left side");
		profilesLink.click();
		return new ManageProfilesPage(driver);
	}

	public void clickUserProfileListOptions() {
		extentTest.log(Status.INFO, "Clicking the options in profile drop down");
		ddlProfileList.click();
		Select se = new Select(ddlProfileList);
		List<WebElement> options = se.getOptions();
		options.get(2).click();
		ddlProfileList.click();
	}

	public AdminReportsPage clickReportsLink() {
		extentTest.log(Status.INFO, "Clicking the 'Reports' link");
		reportsLink.click();
		return new AdminReportsPage(driver);
	}

	public String getButtonID(List<WebElement> element, int index) {
		return element.get(index).getAttribute("id");
	}

	public String getButtonText(List<WebElement> element, int index) {
		return element.get(index).getAttribute("value");
	}

	public String getFirstPendingButtonID() {
		String buttonID = "";
		if(!btnPendingStatus.isEmpty()){
			buttonID = getButtonID(btnPendingStatus, 0);
		}
		return buttonID;
	}

	public void clickFirstPendingButton() {
		if(!btnPendingStatus.isEmpty()){
			btnPendingStatus.get(0).click();
		}
	}

	public String getFirstApprovedButtonID() {
		return getButtonID(btnApprovedStatus, 0);
	}

	public void clickFirstApprovedButton() {
		btnApprovedStatus.get(0).click();
	}

	public List<WebElement> findElements(String id) {
		return driver.findElements(By.id(id));
	}

	public boolean isElementFoundTextMatched(String id, String text) {
		List<WebElement> webelements = findElements(id);
		boolean isPresent = false;
		if(!webelements.isEmpty()){
			isPresent = webelements.get(0).getAttribute("value").equalsIgnoreCase(text);
		}
		return isPresent;

	}

	public String getTextFromAlert() {
		String alert = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return alert;
	}

	public void clickChkUserNotApproved() {
		extentTest.log(Status.INFO, "Click the check box for the user who is not approved");
		chkUserNotApproved.click();
	}

	public boolean ckeckMarketingProfileDisplay() {
		extentTest.log(Status.INFO, "Check weather marketing profile butoon is displayed");
		return marketingProfile.isDisplayed();
	}

	public boolean marketProfileUserDisplay() {
		extentTest.log(Status.INFO, "Check weather marketing profile butoon is displayed");
		return marketingProfileUser.isDisplayed();
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);

	}
}

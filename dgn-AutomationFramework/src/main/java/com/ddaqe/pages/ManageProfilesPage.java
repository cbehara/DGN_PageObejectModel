package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.CoreFrameworkException;
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class ManageProfilesPage extends MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblCount")
	private WebElement lblProfileCount;

	@FindBy(how = How.ID, using = "ssoEmailId")
	private WebElement ssoEmailId;

	@FindBy(how = How.ID, using = "ctl00_cplBody_btnAddProfile")
	private WebElement addProfileBtn;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_lblUserName")
	private WebElement profileName;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileName")
	private WebElement profileNameTextBox;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_lbManageProfDescription")
	private WebElement profileDescription;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileDescription")
	private WebElement profileDescriptionTextBox;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblProfileName")
	private WebElement showUsersProfileName;

	@FindBy(how = How.ID, using = "ctl00_cplBody_btnDoneBtn")
	private WebElement doneBtn;

	@FindBy(how = How.ID, using = "ctl00_cplBody_ddlUserProfile")
	private WebElement ddlUserProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblErr")
	private List<WebElement> lblError;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblErr")
	private WebElement lblErrorMessage;
	
	@FindBy(how = How.ID, using = "btnManageProfileActions")
	private WebElement actionsDropDown;

	@FindBy(how = How.ID, using = "ctl00_cplBody_manageProfileActions_lnkAssignProfile")
	private WebElement lnkAssignProfileUnderActionsDrpDown;

	@FindBy(how = How.ID, using = "ctl00_cplBody_manageProfileActions_lnkRemoveProfile")
	private WebElement lnkRemoveProfileUnderActionsDrpDown;

	@FindBy(how = How.ID, using = "ctl00_cplBody_rptUserList_ctl00_lnkAssign")
	private WebElement lnkAssignThisProfileFirstProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_rptUserList_ctl00_lnkRemove")
	private WebElement lnkRemoveThisProfileFirstProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_rptUserList_ctl00_chkCouSysId")
	private WebElement chkFirstProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_manageProfileActions_lnkAssignProfile")
	private WebElement assignProfileLinkUnderActionsDropDown;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblProjectPro")
	private WebElement lblAddEditProjectProfile;

	@FindBy(how = How.ID, using = "dvGeography")
	private WebElement geographyAttributesPanel;

	@FindBy(how = How.ID, using = "selectAll")
	private WebElement selectAllGeography;

	@FindBy(how = How.ID, using = "dvProjType")
	private WebElement projectTypesAttributesPanel;

	@FindBy(how = How.ID, using = "dvActStage")
	private WebElement actionStageAttributesPanel;

	@FindBy(how = How.ID, using = "error-sections")
	private WebElement errorSectionInAddEditProfile;

	@FindBy(how = How.NAME, using = "TrackingListType")
	private WebElement typeListOnEditDialog;

	@FindBy(how = How.NAME, using = "//*[@id='aspnetForm']//div[3]//div[3]//div[5]//div[1]//div[1]//div[3]//a[1]")
	private WebElement geographyEdit;

	@FindBy(how = How.CLASS_NAME, using = "mngProfileShowUsers")
	private List<WebElement> showUsersBtn;

	@FindBy(how = How.XPATH, using = "//*[@class='facetCheckBoxes']//*[@class='lastLevelValues clearfix']//div[@class='lastLevelChkBox']//input")
	private WebElement geographyState;

	@FindBy(how = How.XPATH, using = "//div[@class='subSectionChkBox']//input[@value='USA' and @type='checkbox']")
	private WebElement geographyStateUSAChkbox;

	@FindBy(how = How.CSS, using = ".mngProfileEdit a")
	private List<WebElement> editBtn;

	@FindBy(how = How.XPATH, using = "//div[@class='mngProfileDelete']//a")
	private List<WebElement> deleteBtn;

	@FindBy(how = How.CLASS_NAME, using = "userName")
	private List<WebElement> userNameList;

	@FindBy(how = How.XPATH, using = "//div[@class='noOfUsers']//span")
	private WebElement lblUserCount;

	@FindBy(how = How.ID, using = "users-select-all")
	private WebElement chkSelectAll;

	@FindBy(how = How.CLASS_NAME, using = "manage-profile-popup-close")
	private List<WebElement> manageProfileCancelBtn;

	@FindBy(how = How.CLASS_NAME, using = "editProfileContentContainer")
	private WebElement editProjectProfile;

	@FindBy(how = How.CLASS_NAME, using = "nameText")
	private WebElement addProfileName;

	@FindBy(how = How.CLASS_NAME, using = "descriptionText")
	private WebElement addProfileDescription;

	@FindBy(how = How.CLASS_NAME, using = "manage-profile-popup-submit")
	private List<WebElement> addProfileSaveBtn;

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private WebElement editLink;

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private List<WebElement> editLinkList;

	@FindBy(how = How.LINK_TEXT, using = "Remove from Profile")
	private List<WebElement> removeFromProfileList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'breadcrumb')]//span")
	private WebElement breadCrumb;

	@FindBy(how = How.XPATH, using = "//div[@class='actionsForUser']//a")
	private List<WebElement> assignLinkForUsers;

	@FindBy(how = How.XPATH, using = "//span[@id='My-Account---Profiles']/a")
	private WebElement breadCrumbManageProfilesLink;

	@FindBy(how = How.XPATH, using = "//img[contains(@id,'imgInfo')]")
	private List<WebElement> imgUserIcon;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrackingListRename']//input[@type='text']")
	private WebElement nameTextfieldInEditDialog;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-submit']")
	private WebElement saveButtonfieldInEditDialog;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-close']")
	private WebElement cancelButtonfieldInEditDialog;

	@FindBy(how = How.XPATH, using = "//div[@class='progreeBarForIframe']//img")
	private WebElement progressBarForIFrame;

	@FindBy(how = How.XPATH, using = "//div[@class='progreeBarForIframe']//img")
	private List<WebElement> progressBarForIFrameInvisibleCheck;

	@FindBy(how = How.XPATH, using = "//div[@class='geographyFacetContainer']//div[contains(@class,'firstChild')]/Div[@class='facetLabel']")
	private WebElement geographyFacetContainer;

	@FindBy(how = How.XPATH, using = "//div[@class='projectTypeFacetContainer']//div[contains(@class,'editMode')]/Div[@class='facetLabel']")
	private WebElement projectTypeFacetContainer;

	@FindBy(how = How.XPATH, using = "//div[@class='actionStageFacetContainer']//div[contains(@class,'editMode')]/Div[@class='facetLabel']")
	private WebElement actionStageFacetContainer;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileEdit' and @parent='geographyFacetContainer']")
	private WebElement geographyEditBtn;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileDone' and @parent='geographyFacetContainer']")
	private WebElement geographyDoneBtn;

	@FindBy(how = How.XPATH, using = "//a[@id='lnkState']//following-sibling::span")
	private WebElement geographyStateLbl;

	@FindBy(how = How.ID, using = "lnkCounty")
	private WebElement geographyCountryLbl;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileEdit' and @parent='projectTypeFacetContainer']")
	private WebElement projectTTypeEditBtn;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileDone' and @parent='projectTypeFacetContainer']")
	private WebElement projectTTypeDoneBtn;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileEdit' and @parent='actionStageFacetContainer']")
	private WebElement actionStageEditBtn;

	@FindBy(how = How.XPATH, using = "//a[@class='lnkProfileDone' and @parent='actionStageFacetContainer']")
	private WebElement actionStageDoneBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='dvGeography']//div[@class='subSectionChkBox']/input[@value='USA']")
	private WebElement geographySectionUSA;

	@FindBy(how = How.XPATH, using = "//div[@id='dvProjType']//div[@class='facetCheckBoxes']/div[@class=1]/div[contains(@class,'subSectionContainer')]")
	private WebElement projectTypeLevel1;

	@FindBy(how = How.XPATH, using = "//div[@id='dvProjType']//div[@class='facetCheckBoxes']/div[@class=1]/div[contains(@class,'subSectionContainer')]//following-sibling::div[@class='subLevelContainer']/div[@class='1.1']")
	private WebElement projectTypeLevel2;

	@FindBy(how = How.XPATH, using = "//div[@id='dvProjType']//div[@class='facetCheckBoxes']/div[@class=2]/div[contains(@class,'subSectionContainer')]//following-sibling::div[@class='subLevelContainer']/div[@class='2.1']/div[@class='subLevelContainer']//div[contains(@class,'lastLevelValues')]")
	private WebElement projectTypeLevel3;

	@FindBy(how = How.XPATH, using = "//div[@id='dvActStage']//div[@class='1']/div[contains(@class,'subSectionContainer')]")
	private WebElement actionStageLevel1;

	@FindBy(how = How.XPATH, using = "//div[@id='dvActStage']//div[@class='1']/div[contains(@class,'subSectionContainer')]/following-sibling::div[@class='subLevelContainer']//div[contains(@class,'lastLevelValues ')]")
	private List<WebElement> actionStageLevel2;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix adminContainer']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.LINK_TEXT, using = "SpecAlerts")
	private WebElement selectSpecAlert;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lnkProjectProfile")
	private WebElement projectLink;

	@FindBy(how = How.ID, using = "error-sections")
	private WebElement errorMessage;

	@FindBy(how = How.XPATH, using = ".//*[@class='editProfileContentContainer']//div[@id='error-sections']")
	private WebElement breadCrumbDisplay;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileName")
	private WebElement profileNameTxtField;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileDescription")
	private WebElement descriptionTxtField;

	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']//div//div//input")
	private List<WebElement> specAlertCheckboxDisplay;

	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']//div//div//div//input[@value='QA1FlooringCPTX']")
	private WebElement clickOnspecAlertsCheckBox;

	@FindBy(how = How.ID, using = "licensedUser")
	private WebElement liscensNumberDisplay;

	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']//div//div//input[@id='selectAll']")
	private WebElement clickOnspecAlertsSelectAllCheckBox;

	@FindBy(how = How.XPATH, using = ".//div[@class='odd mngUserDetails clearfix']//div[@class='mngProfileEdit']//a")
	private WebElement clickOnEditProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_lblUserName")
	private WebElement editedProfileName;

	@FindBy(how = How.XPATH, using = ".//*[@class='editProfileBtnContainer clearfix']//div[@class='saveBtnContainer']//input[@id='manageProfilePopupSaveBottom']")
	private WebElement clickOnspecAlertssaveButton;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_lblUserName")
	private WebElement newProfileDisplay;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileDescription")
	private WebElement descriptionTextBox;

	@FindBy(how = How.ID, using = "manageProfilePopupSaveBottom")
	private WebElement clickOnAddProfileSaveButton;

	@FindBy(how = How.ID, using = "manageProfilePopupDestroyBtm")
	private WebElement clickOnCacelButton;

	@FindBy(how = How.CSS, using = "div[class='mngUserNameLabel']>span[id*='lblUserName']")
	private List<WebElement> profileNameList;

	@FindBy(how = How.CSS, using = "a[class='manageProfileOpenPopup']")
	private List<WebElement> manageProfileEditLinks;

	public ManageProfilesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Manage Profiles Page");
		PageFactory.initElements(driver, this);
	}

	public void clickOnEditLink() {
		extentTest.log(Status.INFO, "Click on edit link.");
		try {
			if (CommonUtils.checkElementExist(editLink, driver)) {
				editLink.click();
				SeleniumUtils.isVisible(clickOnspecAlertsSelectAllCheckBox, driver);
			}

		} catch (Exception e) {
			extentTest.log(Status.INFO, "Edit link is not present on the My tracking list table.");
		}
	}

	public void clickOnManageProfileEditLink() {
		extentTest.log(Status.INFO, "Click on manage profile Edit link.");
		try {
			if (CommonUtils.checkElementExist(editBtn.get(0), driver)) {
				editBtn.get(0).click();
			}
		} catch (Exception e) {
			extentTest.log(Status.INFO, "Edit link is not present on the manage profile table.");
		}
	}

	public void clickOnGoegraphyState() {
		extentTest.log(Status.INFO, "Click on geographyState.");
		SeleniumUtils.isVisible(geographyState, driver);
		geographyState.click();
	}

	public boolean checkGoegraphyStateUSACheckbox() {
		extentTest.log(Status.INFO, "Check Goegraphy State USA Checkbox status.");
		return geographyStateUSAChkbox.isSelected();
	}

	public void clickOnGoegraphyEdit() {

		geographyEdit.click();
	}

	public boolean checkEditTrackingNameDialog() {
		boolean result = true;
		if (!nameTextfieldInEditDialog.isDisplayed()) {
			result = false;
		} else if (!saveButtonfieldInEditDialog.isDisplayed()) {
			result = false;
		} else if (!cancelButtonfieldInEditDialog.isDisplayed()) {
			result = false;
		}
		return result;
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	@Override
	public boolean isBreadCrumbCorrect(String name) {
		extentTest.log(Status.INFO, "Get the breadcrumb of the users page");
		try {
			return breadCrumb.getText().contains(name);
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickOnAddProfile() {
		extentTest.log(Status.INFO, "Clicking on the Add Profile button");
		addProfileBtn.click();
	}

	public void clickOnFirstEditProfile() {
		extentTest.log(Status.INFO, "Clicking on the Edit Profile button for the first record");
		editBtn.get(0).click();
	}

	public void clickOnSecondEditProfile() {
		extentTest.log(Status.INFO, "Clicking on the Edit Profile button for the second record");
		editBtn.get(1).click();
	}

	public void clickOnFirstDeleteProfileWithOK() {
		extentTest.log(Status.INFO, "Clicking on the Delete Profile button for the first record");
		for (int i = 0; i < deleteBtn.size(); i++) {
			deleteBtn.get(i).click();
			driver.switchTo().alert().accept();
			SeleniumUtils.isClickable(addProfileBtn, driver);
			break;
		}
		SeleniumUtils.isClickable(addProfileBtn, driver);

	}

	public void deleteProfileList() {
		int size = deleteBtn.size();
		if (deleteBtn.size() > 0) {
			for (int i = 0; i < size; i++) {
				deleteBtn.get(0).click();
				driver.switchTo().alert().accept();
				SeleniumUtils.isClickable(addProfileBtn, driver);
			}
		}
	}

	public void clickOnFirstDeleteProfileWithCancel() {
		extentTest.log(Status.INFO, "Clicking on the Delete Profile button for the first record and cancel it");
		deleteBtn.get(0).click();
		SeleniumUtils.clickCancelInAlert(driver);
	}

	public boolean verifyCursorFocusOnName() {
		extentTest.log(Status.INFO,
				"If cursor focus is present on the Profile Name text box under Add Project Profile");
		return SeleniumUtils.isCursorFocusPresent(profileNameTextBox, driver);
	}

	public void enterTextInProfileName(String Name) {
		extentTest.log(Status.INFO, "Enter the text in Profile Name under 'Add Project Profile'");
		profileNameTextBox.clear();
		profileNameTextBox.sendKeys(Name);
	}

	public String formatValid(String Name) {
		return CommonUtils.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(Name));
	}

	public void enterTextInProfileDescription(String Name) {
		extentTest.log(Status.INFO, "Enter the text in Profile Name under 'Add Project Profile'");
		profileDescriptionTextBox.clear();
		profileDescriptionTextBox.sendKeys(formatValid(Name));
	}

	public boolean isAddProfileButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Add Profile' button is displayed");
		return addProfileBtn.isDisplayed();
	}

	public boolean isProfileCountLabelDisplayed() {
		extentTest.log(Status.INFO, "Verify if the profile count is displayed");
		return lblProfileCount.isDisplayed();
	}

	public boolean isShowUsersLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Show Users' link is displayed");
		return showUsersBtn.get(0).isDisplayed();
	}

	public String clickOnShowUsersLink() {
		extentTest.log(Status.INFO, "Clicking on the 'Show Users' link");
		String name = profileName.getText();
		showUsersBtn.get(0).click();
		return name;
	}

	public String getShowUsersProfileName() {
		extentTest.log(Status.INFO, "Get the user name from Show Users page");
		return showUsersProfileName.getText();
	}

	public boolean isEditLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Edit' link is displayed");
		return !editBtn.isEmpty();
	}

	public boolean isDeleteLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Delete' link is displayed");
		return deleteBtn.get(0).isDisplayed();
	}

	public boolean isProfileNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Profile Name is displayed");
		return profileName.isDisplayed();
	}

	public boolean isProfileTxtNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Profile Name text box is displayed");
		return profileNameTextBox.isDisplayed();
	}

	public boolean isProfileTxtDescriptionDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Profile Name text box is displayed");
		return profileDescriptionTextBox.isDisplayed();
	}

	public boolean isProfileDescriptionDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Profile Description is displayed");
		return profileDescription.isDisplayed();
	}

	public boolean isProfileDescriptionLengthBelow50() {
		extentTest.log(Status.INFO, "Verify if the Profile Description length is below 50 chars");
		return profileDescription.getText().length() < 50;
	}

	public boolean isShowUsersDoneButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Done button is displayed for show users");
		return doneBtn.isDisplayed();
	}

	public boolean isAddProfileNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Name is displayed for Add Project Profile");
		return addProfileName.isDisplayed();
	}

	public boolean isAddProfileDescriptionDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Description is displayed for Add Project Profile");
		return addProfileDescription.isDisplayed();
	}

	public boolean verifyShowUserProfileOptions() {
		extentTest.log(Status.INFO, "Verify all the options in user profile drop down for show users page");
		boolean isMatch = false;
		int i = 0;
		ddlUserProfile.click();
		Select se = new Select(ddlUserProfile);
		List<WebElement> options = se.getOptions();
		for (DGNEnum.MyAccountShowUserOptions opt : DGNEnum.MyAccountShowUserOptions.values()) {
			if (options.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		ddlUserProfile.click();
		return isMatch;

	}

	public String getSelectedShowUserProfileOptions() {
		extentTest.log(Status.INFO, "Get the selected option in user profile drop down for show users page");
		ddlUserProfile.click();
		Select se = new Select(ddlUserProfile);
		WebElement selectedOptions = se.getFirstSelectedOption();
		return selectedOptions.getText();
	}

	public void clickOnAssignProfileForFirstUser() {
		extentTest.log(Status.INFO, "Assign this profile for show users page");
		assignLinkForUsers.get(0).click();
	}

	public void clickOnBreadCrumbManageProfileLink() {
		extentTest.log(Status.INFO, "Clicking on the manage profiles link from the bread crumb");
		breadCrumbManageProfilesLink.click();
		waitforLoadingRing();
	}

	public void clickOnRemoveFromProfile() {
		extentTest.log(Status.INFO, "Clicking on the remove from profile link");
		removeAllFromProfile();
	}

	public void removeAllFromProfile() {
		for (int i = 0; i < removeFromProfileList.size(); i++) {
			removeFromProfileList.get(i).click();
		}
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Get the message when the list is empty");
		String errorMessage = "";
		if (isErrorMessageDisplayed()) {
			errorMessage = lblError.get(0).getText();
		}
		return errorMessage;
	}

	public String getErrorMessageonPage() {
		extentTest.log(Status.INFO, "Get the message when the list is empty");
		String errorMessage = "";
		errorMessage = lblErrorMessage.getText();
		return errorMessage;
	}
	public boolean isErrorMessageDisplayed() {
		extentTest.log(Status.INFO, "Verify if the message is displayed when the list is empty");
		try {
			return lblError.size() > 1;
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickActionsDropDownForRemove() {
		extentTest.log(Status.INFO, "Clicking on the Actions drop down");
		actionsDropDown.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(lnkRemoveProfileUnderActionsDrpDown));
	}

	public void clickActionsDropDownForAssign() {
		extentTest.log(Status.INFO, "Clicking on the Actions drop down");
		actionsDropDown.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(lnkAssignProfileUnderActionsDrpDown));
	}

	public boolean isAssignProfileLinkDisplayedUnderActionsDropDown() {
		extentTest.log(Status.INFO, "Verify if the Assign This Profile Link is displayed under the Actions drop down");
		return assignProfileLinkUnderActionsDropDown.isDisplayed();
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

	public Integer getUserIconSize() {
		return imgUserIcon.size();
	}

	public Integer getUserNamesSize() {
		return userNameList.size();
	}

	public Integer getUserCount() {
		waitforLoadingRing();
		String lblCount = lblUserCount.getText();
		lblCount = lblCount.replaceAll("[^0-9]", "");
		return Integer.parseInt(lblCount);
	}

	public Integer getProfileCount() {
		String lblCount = lblProfileCount.getText();
		lblCount = lblCount.replaceAll("[^0-9]", "");
		return Integer.parseInt(lblCount);
	}

	public Boolean verifyUserToolTip() {
		extentTest.log(Status.INFO, "Verify the user tooltip");
		if (isErrorMessageDisplayed()) {
			throw new CoreFrameworkException("The list is empty..");
		}
		imgUserIcon.get(1).click();
		return ssoEmailId.isDisplayed();
	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(progressBarForIFrame, driver);
		SeleniumUtils.isLoadingIconInvisible(progressBarForIFrameInvisibleCheck, driver);
	}

	public boolean isAddEditProjectProfileModalDialogDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Add/Edit Modal Dialog is displayed");
		try {
			return editProjectProfile.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public String getAddEditProjectProfileText() {
		extentTest.log(Status.INFO, "Get the text in Add/Edit Project Profile");
		return lblAddEditProjectProfile.getText().trim();
	}

	public String clickOnEditProjectProfile() {
		extentTest.log(Status.INFO, "Click on the Edit Project Profile");
		String name = profileName.getText();
		editBtn.get(0).click();
		return name;
	}

	public boolean isGeographyFacetContainerDisplayed() {
		extentTest.log(Status.INFO, "Verify if the geography container is displayed");
		return geographyFacetContainer.isDisplayed();
	}

	public boolean isProjectTypeFacetContainerDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Project Type container is displayed");
		return projectTypeFacetContainer.isDisplayed();
	}

	public boolean isActionStageFacetContainerDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Action Stage container is displayed");
		return actionStageFacetContainer.isDisplayed();
	}

	public void clickOnGeographyEditButton() {
		extentTest.log(Status.INFO, "Clicking on the Geography Edit Button");
		SeleniumUtils.isVisible(geographyEditBtn, driver);
		geographyEditBtn.click();
		waitforLoadingRing();
	}

	public boolean isEditGeographyDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Geography Edit Button is displayed");
		return geographyEditBtn.isDisplayed();
	}

	public void clickOnGeographyDoneButton() {
		extentTest.log(Status.INFO, "Clicking on the Geography Done Button");
		geographyDoneBtn.click();
	}

	public void clickOnProjectTypeEditButton() {
		extentTest.log(Status.INFO, "Clicking on the Project Type Edit Button");
		SeleniumUtils.scrollToView(driver, projectTTypeEditBtn);
		projectTTypeEditBtn.click();
		waitforLoadingRing();
	}

	public boolean isEditProjectTypeDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Project Type Edit Button is displayed");
		return projectTTypeEditBtn.isDisplayed();
	}

	public void clickOnProjectTypeDoneButton() {
		extentTest.log(Status.INFO, "Clicking on the Project Type Done Button");
		projectTTypeDoneBtn.click();
	}

	public void clickOnActionStageEditButton() {
		extentTest.log(Status.INFO, "Clicking on the Action Stage Edit Button");
		SeleniumUtils.scrollToView(driver, actionStageEditBtn);
		actionStageEditBtn.click();
		waitforLoadingRing();
	}

	public boolean isSelectAllChecked() {
		extentTest.log(Status.INFO, "Verify if Select All checked");
		try {
			return selectAllGeography.getAttribute("checked").equals("true");
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isEditActionStageDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Action Stage Edit Button is displayed");
		return actionStageEditBtn.isDisplayed();
	}

	public void clickOnActionStageDoneButton() {
		extentTest.log(Status.INFO, "Clicking on the Action Stage Done Button");
		actionStageDoneBtn.click();
	}

	public boolean isGeographyAttributesPanelDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Geography Attributes Panel is displayed");
		return geographyAttributesPanel.isDisplayed();
	}

	public boolean isProjectTypesPanelDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Project Types Attributes Panel is displayed");
		return projectTypesAttributesPanel.isDisplayed();
	}

	public boolean isActionStagePanelDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Action Stage Attributes Panel is displayed");
		return actionStageAttributesPanel.isDisplayed();
	}

	public void clickOnSaveButton() {
		extentTest.log(Status.INFO, "Clicking on the save button under the 'Add Project Profile'");
		addProfileSaveBtn.get(0).click();
	}

	public void clickOnCancelButton() {
		extentTest.log(Status.INFO, "Clicking on the cancel button under the 'Add Project Profile'");
		manageProfileCancelBtn.get(0).click();
	}

	public Integer getProfileNameLength() {
		return profileNameTextBox.getAttribute("value").length();
	}

	public String getErrorMessageInAddEditProfile() {
		extentTest.log(Status.INFO, "Get the error message under the 'Add Project Profile'");
		return errorSectionInAddEditProfile.getText();
	}

	public boolean isErrorMessageInAddEditProfileDisplayed() {
		extentTest.log(Status.INFO, "Verify if the error message under the 'Add Project Profile' is displayed");
		try {
			return errorSectionInAddEditProfile.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickSelectAllGeography() {
		extentTest.log(Status.INFO, "Click the 'select all' option under the 'Add Project Profile'");
		selectAllGeography.click();
	}

	public boolean isSaveButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the save button displayed under the 'Add Project Profile'");
		return addProfileSaveBtn.get(0).isDisplayed();
	}

	public boolean isCancelButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the cancel button displayed under the 'Add Project Profile'");
		return manageProfileCancelBtn.get(0).isDisplayed();
	}

	public void clickAvailShowUserProfileOptions() throws InterruptedException {
		extentTest.log(Status.INFO,
				"Clicking the 'Availed Assigned Profile' options in user profile drop down for show users page");
		SeleniumUtils.isVisible(ddlUserProfile, driver);
		SeleniumUtils.selectByVisibleText(ddlUserProfile,
				DGNEnum.MyAccountShowUserOptions.AVAIL_ASSIGN_PROFILE.description());
	}

	public void clickCurrentShowUserProfileOptions() {
		extentTest.log(Status.INFO,
				"Clicking the 'Currently Assigned Profile' options in user profile drop down for show users page");
		SeleniumUtils.isVisible(ddlUserProfile, driver);
		SeleniumUtils.selectByVisibleText(ddlUserProfile,
				DGNEnum.MyAccountShowUserOptions.CURRENTLY_ASSIGNED_PROFILE.description());
	}

	public void clickOnFirstProfileChk() {
		extentTest.log(Status.INFO, "Clicking the first profile check box for show users page");
		chkFirstProfile.click();
	}

	public void clickAssignThisProfileLinkUnderActionsDropdown() {
		extentTest.log(Status.INFO,
				"Clicking the 'Assign This Profile' link under Actions drop down for show users page");
		lnkAssignProfileUnderActionsDrpDown.click();
	}

	public void clickRemoveThisProfileLinkUnderActionsDropdown() {
		extentTest.log(Status.INFO,
				"Clicking the 'Remove This Profile' link under Actions drop down for show users page");
		lnkRemoveProfileUnderActionsDrpDown.click();
	}

	public void clickAssignThisProfileLinkForFirstProfile() {
		extentTest.log(Status.INFO, "Clicking the first profile 'Assign This Profile' show users page");
		lnkAssignThisProfileFirstProfile.click();
	}

	public void clickRemoveThisProfileLinkForFirstProfile() {
		extentTest.log(Status.INFO, "Clicking the first profile 'Remove This Profile' show users page");
		lnkRemoveThisProfileFirstProfile.click();
	}

	public boolean isGeographyDoneColorAsExpected() {
		extentTest.log(Status.INFO, "Verify if 'Done' button in the geography tab");
		SeleniumUtils.scrollToView(driver, geographyDoneBtn);
		return SeleniumUtils.getBackgroundColor(geographyDoneBtn).equals("#f24d00");
	}

	public boolean isSelectAllDisplayed() {
		extentTest.log(Status.INFO, "Verify if 'Select All' checkbox is displayed");
		return selectAllGeography.isDisplayed();
	}

	public void deselectUSAchk() {
		extentTest.log(Status.INFO, "Deselecting USA");
		if (geographySectionUSA.getAttribute("checked").equals("true"))
			geographySectionUSA.click();
	}

	public boolean isProfileLevel1Displayed() {
		extentTest.log(Status.INFO, "Verify if Project Type level 1 is displayed");
		return projectTypeLevel1.isDisplayed();
	}

	public boolean isProfileLevel2Displayed() {
		extentTest.log(Status.INFO, "Verify if Project Type level 2 is displayed");
		return projectTypeLevel2.isDisplayed();
	}

	public boolean isProfileLevel3Displayed() {
		extentTest.log(Status.INFO, "Verify if Project Type level 3 is displayed");
		return projectTypeLevel3.isDisplayed();
	}

	public boolean isActionStageLevel1Displayed() {
		extentTest.log(Status.INFO, "Verify if Action Stage level 1 is displayed");
		return actionStageLevel1.isDisplayed();
	}

	public boolean isActionStageLevel2Displayed() {
		extentTest.log(Status.INFO, "Verify if Action Stage level 1 is displayed");
		return actionStageLevel2.size() == 3;
	}

	public void clickShowUsersSelectAll() {
		extentTest.log(Status.INFO, "Clicking the select all in show users");
		chkSelectAll.click();
	}

	public void AddUserProfile() {
		switchToFrame();
		clickOnAddProfile();
		char[] validName = new char[11];
		Arrays.fill(validName, 'A');
		String textName = new String(validName);
		textName = formatValid(textName);
		enterTextInProfileName(textName);
		char[] validDesc = new char[11];
		Arrays.fill(validDesc, 'B');
		String textDesc = new String(validDesc);
		enterTextInProfileDescription(textDesc);
		clickOnGeographyEditButton();
		clickSelectAllGeography();
		clickOnGeographyDoneButton();
		clickOnSaveButton();
		switchToDefault();
	}

	public boolean isStateDisplayed() {
		extentTest.log(Status.INFO, "Verify if the state is displayed");
		return geographyStateLbl.isDisplayed();
	}

	public boolean isCountryDisplayed() {
		extentTest.log(Status.INFO, "Verify if the country is displayed");
		return geographyCountryLbl.isDisplayed();
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}

	public void clickOnSpecAlert() {
		switchToFrame();
		extentTest.log(Status.INFO, "Click On Spec Alert Link");
		selectSpecAlert.click();
		SeleniumUtils.isVisible(projectLink, driver);
	}

	public void clickOnspecEditLink() {
		extentTest.log(Status.INFO, "Click on edit link.");
		if (CommonUtils.checkElementExist(editLink, driver)) {
			editLink.click();
		}
	}

	public boolean isErrorMessageDisplay() {
		extentTest.log(Status.INFO, "Check wheather error message is displayed");
		return errorMessage.isDisplayed();
	}

	public boolean isBreadCrumbEditspec() {
		extentTest.log(Status.INFO, "Get the breadcrumb of the users page");
		return breadCrumbDisplay.isDisplayed();
	}

	public void clickOnSpeccheckBox() {
		extentTest.log(Status.INFO, "click On Check Box");
		if (!clickOnspecAlertsCheckBox.isSelected()) {
			clickOnspecAlertsCheckBox.click();
			SeleniumUtils.isClickable(clickOnspecAlertsCheckBox, driver);
		}
	}

	public boolean liscenseNumberDisplay() {
		extentTest.log(Status.INFO, "check wheather Liscense number is Displayed On Page");
		return liscensNumberDisplay.isDisplayed();
	}

	public void clickOnSpecSelectAll() {
		extentTest.log(Status.INFO, "click On select all Check Box");
		clickOnspecAlertsSelectAllCheckBox.click();
		;
	}

	public void clickOnEditSpecBtn() {
		extentTest.log(Status.INFO, "click On edit link on spec alert");
		clickOnEditProfile.click();
	}

	public boolean checkEditedProfileNamedisplayed() {
		extentTest.log(Status.INFO, "check profile name does not change after edition");
		SeleniumUtils.isVisible(editedProfileName, driver);
		return editedProfileName.isDisplayed();
	}

	public void clickOnSpecSaveBtn() {
		extentTest.log(Status.INFO, "click On save button");
		clickOnspecAlertssaveButton.click();
	}

	public boolean checkNewProfileDispaly() {
		extentTest.log(Status.INFO, "check weather new profile is display ");
		return newProfileDisplay.isDisplayed();
	}

	public boolean checkAddProfileButtonDisplay() {
		extentTest.log(Status.INFO, "check weather Add profile button is display ");
		SeleniumUtils.isVisible(addProfileBtn, driver);
		return addProfileBtn.isDisplayed();
	}

	public boolean checkSaveButtonDisplay() {
		extentTest.log(Status.INFO, "check weather Save button is display ");
		return clickOnAddProfileSaveButton.isDisplayed();
	}

	public boolean checkCancelButtonDispaly() {
		extentTest.log(Status.INFO, "check weather Cancel button is display ");
		return clickOnCacelButton.isDisplayed();
	}

	public boolean checkProfileNameTextBoxDisplay() {
		extentTest.log(Status.INFO, "check weather name text box is display ");
		return profileNameTextBox.isDisplayed();
	}

	public boolean checkDescriptionTextboxDisplay() {
		extentTest.log(Status.INFO, "check weather Description text box is display ");
		return descriptionTextBox.isDisplayed();
	}

	public List<String> getSpecAlertProgram() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		List<String> specAlertProgramm = new ArrayList<String>();
		for (WebElement specAlert : specAlertCheckboxDisplay) {
			specAlertProgramm.add(specAlert.getText());
		}
		return specAlertProgramm;
	}

	public List<String> getExistingProfileNameList() {
		extentTest.log(Status.INFO, "Get existing profile name list.");
		return CommonUtils.getListFromWebElements(profileNameList);
	}

	public void deleteSavedProfile(final String savedProfileName) {
		final List<WebElement> savedProfile = driver.findElements(
				By.cssSelector("div[class='mngProfileDelete']>a[profilename='" + savedProfileName + "']"));
		if (!savedProfile.isEmpty()) {
			savedProfile.get(0).click();
		}
		driver.switchTo().alert().accept();
		SeleniumUtils.isClickable(selectSpecAlert, driver);
	}

	public void editSavedProfile(final String savedProfileName) {
		final List<WebElement> savedProfile = driver
				.findElements(By.cssSelector("div[class='mngProfileEdit']>a[href*='" + savedProfileName + "']"));
		if (!savedProfile.isEmpty()) {
			savedProfile.get(0).click();
		}
		SeleniumUtils.isClickable(selectSpecAlert, driver);
	}
}

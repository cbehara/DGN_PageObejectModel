package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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

public class ManageUsers {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.CSS, using = "a[id*='LinkButtonAdminUsers']")
	private WebElement linkManageUsers;
	@FindBy(how = How.CSS, using = "a[id*='LinkButtonAdminTemplates']")
	private WebElement linkManageProfiles;
	@FindBy(how = How.CSS, using = "a[id*='LinkLicensePreferences']")
	private WebElement linkLicensePreferences;
	@FindBy(how = How.CSS, using = "a[id*='LinkButtonAdminReporting']")
	private WebElement linkReports;
	@FindBy(how = How.ID, using = "ctl00_bodyPlaceHolder_ucAdminMenu_ButtonHome")
	private WebElement clickOnCustomerServiceHome;
	@FindBy(how = How.ID, using = "ctl00_bodyPlaceHolder_ButtonReports")
	private WebElement checkrReportBtnDisplay;
	@FindBy(how = How.CSS, using =".mngProfileShowUsers a")
	private WebElement linkShowUsers;
	@FindBy(how = How.CSS, using =".actionsForUser a")
	private List<WebElement> linkAssignThisProfile;
	@FindBy(how = How.CSS, using ="[id*='ddlReportType']")
	private WebElement ddlReportType;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_Button1")
	private WebElement runButton;
	@FindBy(how = How.ID, using = "ctl00_bodyPlaceHolder_iframeManageUsers")
	private WebElement manageUsersFrame;
	@FindBy(how = How.CSS, using ="input[id='txtUser']")
	private WebElement reportUserDropdown;
	@FindBy(how = How.CSS, using ="input[id='ctl00_cplBody_chkSelectAll']")
	private WebElement selectAllUsersDDOption;
	@FindBy(how = How.CSS, using ="a[class='manageProfileOpenPopup']")
	private WebElement manageProfileEditLink;
	@FindBy(how = How.CSS, using ="a[class='lnkProfileEdit']")
	private List<WebElement> lnkProfileEdit;
	@FindBy(how = How.CSS, using ="a[class='lnkProfileDone']")
	private List<WebElement> lnkProfileDone;
	@FindBy(how = How.CSS, using ="div[class='actionStageFacetContainer'] [class='facetCheckBoxes'] [class*='subSectionContainer'] div>input")
	private List<WebElement> actionStageCheckBoxes;
	@FindBy(how = How.CSS, using ="div[class='actionStageFacetContainer'] [class='facetCheckBoxes'] [class*='subLevelContainer'] div[class*='lastLevelValues']")
	private List<WebElement> lastLevelValuesActionStage;
	@FindBy(how = How.CSS, using ="div[class='projectTypeFacetContainer'] [class='facetCheckBoxes'] [class*='subLevelContainer'] div[class*='lastLevelValues']")
	private List<WebElement> lastLevelValuesProjectType;
	@FindBy(how = How.CSS, using =".actionStageFacetContainer #ctl00_cplBody_lblActStageHeader")
	private WebElement selectedActStageHeader;
	@FindBy(how = How.CSS, using =".editProfileBtnContainer .manage-profile-popup-submit")
	private List<WebElement> manageProfileSaveButton;
	@FindBy(how = How.CSS, using ="div[class='manageProfileLabel']")
	private WebElement profilesLabel;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_btnAddProfile")
	private WebElement addProfileButton;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_txtProjectProfileName")
	private WebElement txtProjectProfileName;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_txtProjectProfileDescription")
	private WebElement txtProjectProfileDescription;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_divUserList div[class*='mngUserDetails']>div>div>span")
	private List<WebElement> projectProfileNameList;
	@FindBy(how = How.CSS, using ="#ctl00_cplBody_lblError")
	private List<WebElement> errorMessage;
	
	public ManageUsers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}
	public void SwitchToFrame() {
		SeleniumUtils.switchToFrame(driver, manageUsersFrame);
	}
	public String getErrorMessage(){
		extentTest.log(Status.INFO, "Get error message.");
		return errorMessage.get(0).getText();
	}
	public boolean verifyErrorMessage(String errorMessageToBeVerified){
		extentTest.log(Status.INFO, "Get error message.");
		boolean isVerified = false;
		if(!errorMessage.isEmpty()){
			isVerified = errorMessageToBeVerified.equalsIgnoreCase(errorMessage.get(0).getText().trim());
		}
		return isVerified;
	}
	public void deleteProjectProfile(String projectProfileName){
		extentTest.log(Status.INFO, "Delete project profile.");
		WebElement projectProfileDeleteLink=driver.findElement(By.cssSelector(".mngProfileDelete a[profilename='"+projectProfileName+"']"));
		projectProfileDeleteLink.click();
		driver.switchTo().alert().accept();
	}
	public List<String> getProjectProfileNames(){
		extentTest.log(Status.INFO, "Get project profile name list.");
		SeleniumUtils.isVisible(projectProfileNameList.get(0), driver);
		return CommonUtils.getListFromWebElements(projectProfileNameList);
	}
	public void enterProjectProfileName(String profileName){
		extentTest.log(Status.INFO, "Enter project profile name.");
		SeleniumUtils.isVisible(txtProjectProfileName, driver);
		txtProjectProfileName.sendKeys(profileName);
	}
	public void enterProjectProfileDescription(String profileDescription){
		extentTest.log(Status.INFO, "Enter project profile description.");
		SeleniumUtils.isVisible(txtProjectProfileDescription, driver);
		txtProjectProfileDescription.sendKeys(profileDescription);
	}
	public void clickOnAddProfileButton(){
		extentTest.log(Status.INFO, "Click on Add Profile button.");
		SeleniumUtils.isVisible(addProfileButton, driver);
		addProfileButton.click();
	}
	public boolean isManageProfileSectionDisplayed(){
		extentTest.log(Status.INFO, "Verify manage profile section.");
		SeleniumUtils.isVisible(profilesLabel, driver);
		return profilesLabel.isDisplayed() && profilesLabel.getText().trim().equalsIgnoreCase("Profiles");
	}
	public void clickOnManageProfileSaveButton(){
		extentTest.log(Status.INFO, "Click on manage profile Save button.");
		SeleniumUtils.isVisible(manageProfileSaveButton.get(0), driver);
		manageProfileSaveButton.get(0).click();
	}
	public String getSelectedActionStageHeader(){
		extentTest.log(Status.INFO, "Select Action Stage/Project Type values.");
		SeleniumUtils.isVisible(selectedActStageHeader, driver);
		return selectedActStageHeader.getAttribute("original-title");
	}
	public List<String> selectActionStageLastValues(int selectionCount){
		extentTest.log(Status.INFO, "Select Action Stage values.");
		SeleniumUtils.isVisible(lastLevelValuesActionStage.get(0), driver);
		List<String> valueList = new ArrayList<String>();
		int count=0;
		for(WebElement actionStageChBox : lastLevelValuesActionStage){
			WebElement checkBoxToBeSelected = actionStageChBox.findElement(By.cssSelector("div:nth-of-type(1)>input"));
			WebElement checkBoxLabelToBeSelected = actionStageChBox.findElement(By.cssSelector("div:nth-of-type(1)>input"));
			if(count>selectionCount){
				break;
			}
			if(!checkBoxToBeSelected.isSelected()){
				checkBoxToBeSelected.click();
				valueList.add(checkBoxLabelToBeSelected.getText().trim());
				count++;
			}
		}
		return valueList;
	}
	public List<String> getSelectedActionStageLastValues(){
		extentTest.log(Status.INFO, "Get Selected Action Stage values.");
		SeleniumUtils.isVisible(lastLevelValuesActionStage.get(0), driver);
		List<String> valueList = new ArrayList<String>();
		for(WebElement actionStageChBox : lastLevelValuesActionStage){
			WebElement checkBoxLabelToBeSelected = actionStageChBox.findElement(By.cssSelector("div:nth-of-type(1)>input"));
				valueList.add(checkBoxLabelToBeSelected.getText().trim());
		}
		return valueList;
	}
	public ManageUsers selectActionStageCheckBoxes(int selectionCount){
		extentTest.log(Status.INFO, "Select unselected Action Stage check boxes.");
		SeleniumUtils.isVisible(actionStageCheckBoxes.get(0), driver);
		int count=0;
		for(WebElement actionStageChBox : actionStageCheckBoxes){
			if(count>selectionCount){
				break;
			}
			if(!actionStageChBox.isSelected()){
				actionStageChBox.click();
				count++;
			}
		}
		return new ManageUsers(driver);
	}
	/**
	 * 0 for Geography 
	 * 1 for Project Type 
	 * 2 for Action Stage 
	 * @return Object ManageUsers
	 */
	public ManageUsers clickProfileEditLink(int index){
		extentTest.log(Status.INFO, "click on Profile Edit link.");
		SeleniumUtils.isVisible(lnkProfileEdit.get(index), driver);
		lnkProfileEdit.get(index).click();
		return new ManageUsers(driver);
	}
	/**
	 * 0 for Geography 
	 * 1 for Project Type 
	 * 2 for Action Stage 
	 * @return Object ManageUsers
	 */
	public ManageUsers clickProfileDoneLink(int index){
		extentTest.log(Status.INFO, "click on  Profile Done link.");
		SeleniumUtils.isVisible(lnkProfileDone.get(index), driver);
		lnkProfileDone.get(index).click();
		return new ManageUsers(driver);
	}
	public ManageUsers clickManageProfileEditLink(){
		extentTest.log(Status.INFO, "click on manage profile Edit link.");
		SeleniumUtils.isVisible(manageProfileEditLink, driver);
		manageProfileEditLink.click();
		return new ManageUsers(driver);
	}
	public ManageUsers clickReportUserDropdown(){
		extentTest.log(Status.INFO, "click on Report User Dropdown.");
		SeleniumUtils.isVisible(reportUserDropdown, driver);
		reportUserDropdown.click();
		return new ManageUsers(driver);
	}
	public ManageUsers clickSelectAllUsersDDOption(){
		extentTest.log(Status.INFO, "click on select All Users drop down Option.");
		SeleniumUtils.isVisible(selectAllUsersDDOption, driver);
		selectAllUsersDDOption.click();
		return new ManageUsers(driver);
	}
	public ManageUsers clickRunButton(){
		extentTest.log(Status.INFO, "click on Run button.");
		SeleniumUtils.isVisible(runButton, driver);
		runButton.click();
		return new ManageUsers(driver);
	}
	
	public ManageUsers clickReportTypeDropDown(){
		extentTest.log(Status.INFO, "click on Report Type drop down.");
		SeleniumUtils.isVisible(ddlReportType, driver);
		ddlReportType.click();
		return new ManageUsers(driver);
	}
	
	public ManageUsers selectReportType(String reportTypeToBeSelected){
		extentTest.log(Status.INFO, "Select Report Type from drop down.");
		ddlReportType.click();
		List<WebElement> optionList =ddlReportType.findElements(By.tagName("option"));
		for(WebElement reportType : optionList){
			if(reportType.getText().equalsIgnoreCase(reportTypeToBeSelected)){
				reportType.click();
				break;
			}
		}
		return new ManageUsers(driver);
	}
	
	public ManageUsers clickShowUsersLink(){
		extentTest.log(Status.INFO, "click on Show Users Link");
		SeleniumUtils.isVisible(linkShowUsers, driver);
		linkShowUsers.click();
		return new ManageUsers(driver);
	}
	
	public ManageUsers clickAssignThisProfileLink(){
		extentTest.log(Status.INFO, "click on Assign This Profile Link");
		SeleniumUtils.isVisible(linkAssignThisProfile.get(0), driver);
		linkAssignThisProfile.get(0).click();
		return new ManageUsers(driver);
	}
	
	public LicensePreferencePage clickOnManageUserLink() {
		extentTest.log(Status.INFO, "click on manage User Link");
		SeleniumUtils.isVisible(linkManageUsers, driver);
		linkManageUsers.click();
		return new LicensePreferencePage(driver);
	}
	
	public ManageUsers clickOnManageProfileLink() {
		extentTest.log(Status.INFO, "click on manage profile Link");
		SeleniumUtils.isVisible(linkManageProfiles, driver);
		linkManageProfiles.click();
		return new ManageUsers(driver);
	}
	
	public ManageUsers clickOnLicensePreferencesLink() {
		extentTest.log(Status.INFO, "click on License Preferences Link");
		SeleniumUtils.isVisible(linkLicensePreferences, driver);
		linkLicensePreferences.click();
		return new ManageUsers(driver);
	}
	
	public boolean isManageUsersLinkPresent(){
		extentTest.log(Status.INFO, "Verify Manage User link.");
		SeleniumUtils.isVisible(linkManageUsers, driver);
		return linkManageUsers.isDisplayed();
	}
	public boolean isManageProfilesLinkPresent(){
		extentTest.log(Status.INFO, "Verify Manage Profiles link.");
		SeleniumUtils.isVisible(linkManageProfiles, driver);
		return linkManageProfiles.isDisplayed();
	}
	public boolean isGlobalPreferencesLinkPresent(){
		extentTest.log(Status.INFO, "Verify License Preferences link.");
		SeleniumUtils.isVisible(linkLicensePreferences, driver);
		return linkLicensePreferences.isDisplayed();
	}
	public boolean isReportsLinkPresent(){
		extentTest.log(Status.INFO, "Verify Reports link.");
		SeleniumUtils.isVisible(linkReports, driver);
		return linkReports.isDisplayed();
	}
	
	public void clickOnCustomerServiceHomeBtn()
	{
		extentTest.log(Status.INFO, "click On Customer service Home");
		SeleniumUtils.isVisible(clickOnCustomerServiceHome, driver);
		clickOnCustomerServiceHome.click();	
	}
	public boolean checkReportBtnDisplay()
	{
		extentTest.log(Status.INFO, "user navigate to license search Page");
		return checkrReportBtnDisplay.isDisplayed();
	}
}

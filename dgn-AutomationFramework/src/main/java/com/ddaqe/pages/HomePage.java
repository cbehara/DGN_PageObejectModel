package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.SeleniumUtils;

public class HomePage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.LINK_TEXT, using = "HOME")
	private WebElement homeMenuLink;

	@FindBy(how = How.LINK_TEXT, using = "PROJECTS")
	private WebElement projectsLink;

	@FindBy(how = How.LINK_TEXT, using = "SWEETS ACTIVITY")
	private WebElement sweetsActivityLink;

	@FindBy(how = How.LINK_TEXT, using = "Projects")
	private WebElement dashboardProjectsTab;

	@FindBy(how = How.LINK_TEXT, using = "Companies")
	private WebElement dashboardCompaniesTab;

	@FindBy(how = How.LINK_TEXT, using = "PLANROOM")
	private WebElement planroomLink;

	@FindBy(how = How.LINK_TEXT, using = "INTERNATIONAL")
	private WebElement internationalLink;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_lnkCompany")
	private WebElement companiesLink;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lnkShoppingCart")
	private WebElement lnkShoppingCart;

	@FindBy(how = How.CLASS_NAME, using = "customiseSavedSearches")
	private WebElement customiseSavedSearchesInPopup;

	@FindBy(how = How.CLASS_NAME, using = "customiseTrackingLists")
	private WebElement customiseTrackingListsInPopup;

	@FindBy(how = How.XPATH, using = "//a[@id='customisePopupSubmit'][@class='customize-popup-submit']")
	private WebElement customiseDashboardPopUpSave;

	@FindBy(how = How.XPATH, using = "//a[@id='customisePopupClose'][@class='customize-popup-close']")
	private WebElement customiseDashboardPopUpCancel;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeSpecAlertPopup']//a[@id='customisePopupClose']")
	private WebElement customiseSpecAlertPopUpClose;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeSpecAlertPopup']//a[@id='customisePopupSave']")
	private WebElement customiseSpecAlertPopUpSave;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeSpecAlertPopup']//*[@class='header']")
	private WebElement customiseSpecAlertPopUpHeader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_LastLoginActivity_ddlRecentLogin")
	private WebElement recentLoginDrpDown;

	@FindBy(how = How.XPATH, using = "//select[@id='ctl00_contentPlaceHolderHeader_LastLoginActivity_ddlRecentLogin']//option")
	private List<WebElement> recentLogin;

	@FindBy(how = How.ID, using = "customiseDynamicText")
	private WebElement customiseDashboardLink;

	@FindBy(how = How.ID, using = "lnkLeadEmail")
	private WebElement leadEmailLink;

	@FindBy(how = How.ID, using = "specAlertPopup")
	private WebElement leadEmailDialog;

	@FindBy(how = How.ID, using = "ctl00_specEmailPopup_chkSpecChckBox")
	private WebElement leadEmailDialogChkbox;

	@FindBy(how = How.XPATH, using = "//div[@id='specAlertDataContainer']//textarea")
	private WebElement leadEmailDialogTextArea;

	@FindBy(how = How.ID, using = "specAlertPopupSubmit")
	private WebElement leadEmailDialogSavebtn;

	@FindBy(how = How.ID, using = "specAlertPopupClose")
	private WebElement leadEmailDialogCancelbtn;

	@FindBy(how = How.ID, using = "spnWariningMsg")
	private WebElement leadEmailDialogErrorMsg;

	@FindBy(how = How.LINK_TEXT, using = "Sign Out")
	private WebElement signOutLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'specCommon')]")
	private WebElement specAlertsTab;

	@FindBy(how = How.ID, using = "myAccount")
	private WebElement myAccountLink;

	@FindBy(how = How.LINK_TEXT, using = "My SpecAlerts")
	private WebElement mySpecAlertsLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSpecAlertDD")
	private WebElement mySpecAlertsDropdown;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'searchText')]")
	private WebElement searchTxtField;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'keywordSearch_btnSearch')]")
	private WebElement searchBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_KeywordUI1_btnHomeSearch")
	private WebElement homeSearchBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchSpan")
	private WebElement mySearchesDropDown;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'mySearchesSpecAlert')]")
	private WebElement specAlertsDropDownValue;

	@FindBy(how = How.LINK_TEXT, using = "My Tracking Lists")
	private WebElement MyTrackingListsMenuLink;

	@FindBy(how = How.XPATH, using = "//tbody[@id='trackingListSearch']//td/p[@class='wrapText']")
	private List<WebElement> dashboardMyProjectsTrackingList;
	
	@FindBy(how = How.XPATH, using = "//tbody[@id='trackingListSearch']//td[2]/span")
	private List<WebElement> dashboardMyProjectsTrackingListTodayLinks;
	
	@FindBy(how = How.XPATH, using = "//tbody[@id='trackingListSearch']//td[3]/span")
	private List<WebElement> dashboardMyProjectsTrackingListLastVisitLinks;
	
	@FindBy(how = How.XPATH, using = "//tbody[@id='savedSearch']//td/p[@class='wrapText']")
	private List<WebElement> dashboardMyProjectsSavedSearch;

	@FindBy(how = How.XPATH, using = "//tbody[@id='savedSearch']//td[3]/span")
	private List<WebElement> dashboardMyProjectsLastVisitSavedSearchList;
	@FindBy(how = How.XPATH, using = "//tbody[@id='savedSearch']//td[2]/span")
	private List<WebElement> dashboardMyProjectsTodayVisitSavedSearchList;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companySavedSearch']//span")
	private List<WebElement> dashboardMyCompaniesSavedSearch;

	@FindBy(how = How.LINK_TEXT, using = "Reports")
	private WebElement reportsLink;

	@FindBy(how = How.LINK_TEXT, using = "Help")
	private WebElement helpLink;

	@FindBy(how = How.LINK_TEXT, using = "terms of use")
	private WebElement termsOfUseLink;

	@FindBy(how = How.LINK_TEXT, using = "privacy policy")
	private WebElement privacyPolicyLink;

	@FindBy(how = How.LINK_TEXT, using = "about us")
	private WebElement aboutUsLink;

	@FindBy(how = How.LINK_TEXT, using = "customer care")
	private WebElement customercareLink;

	@FindBy(how = How.LINK_TEXT, using = "My Saved Searches")
	private WebElement mySavedSearchesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesTrackingListDD")
	private WebElement myTrackingListSubmenu;

	@FindBy(how = How.XPATH, using = ".//*[@id='trackingListSearch']//td[1]//p")
	private WebElement firstTrackingList;

	@FindBy(how = How.XPATH, using = ".//*[@id='savedSearch']//td[1]//p")
	private WebElement firstSavedSearch;

	@FindBy(how = How.ID, using = "HomeSearch")
	private WebElement HomeSearchDropdown;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'MySearchesDropDown_mySearchesSavedSearchDD')]")
	private WebElement savedSearchMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_saveSearchProj")
	private WebElement saveSearchBtnProj;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_saveSearchCompany")
	private WebElement saveSearchBtnCompany;

	@FindBy(how = How.XPATH, using = "//div[@id='HomeSearchDrawr']//div[contains(.,'Projects')]")
	private WebElement HomeSearchDropdownProjectsOpton;
	@FindBy(how = How.XPATH, using = "//div[@id='HomeSearchDrawr']//div[contains(.,'Companies')]")
	private WebElement HomeSearchDropdownCompaniesOpton;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_projectCommon")
	private WebElement CommonProjectsTab;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyCommon")
	private WebElement CommonCompaniesTab;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_specCommon")
	private WebElement CommonSpecAlertsTab;
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']/div[1]")
	private WebElement ChartOneDashboard;
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']/div[2]")
	private WebElement ChartTwoDashboard;
	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'WA')]")
	private WebElement mapChartProjectDensityWashington;
	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'MT')]")
	private WebElement mapChartProjectDensityMontana;
	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'TX')]")
	private WebElement mapChartProjectDensityTexas;
	@FindBy(how = How.ID, using = "closeExpiredPopup")
	private WebElement closeExpiredPopup;

	@FindBy(how = How.ID, using = "sessionExpire1")
	private WebElement expiredSessionMsg1;
	@FindBy(how = How.ID, using = "sessionExpire2")
	private WebElement expiredSessionMsg2;
	@FindBy(how = How.XPATH, using = "//tbody[@id='tbodySpecAlerts']//td[1]")
	private List<WebElement> dashboardSpecAlertsProgramsList;

	@FindBy(how = How.XPATH, using = "//tbody[@id='tbodySpecAlerts']//span")
	private List<WebElement> dashboardSpecAlertsProgramListAll;

	@FindBy(how = How.XPATH, using = "//div[@class='page-not-found']")
	private WebElement internationalPageNotFound;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeDashBoardPopup']//div[@class='trackingListContainer']//input[@type='checkbox']")
	private List<WebElement> customiseDashboardTracklists;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeDashBoardPopup']//div[@class='saveSearchCheckBoxContainer']//input[@type='checkbox']")
	private List<WebElement> customiseDashboardSavedSearch;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeDashBoardPopup']//div[@class='saveSearchCheckBoxContainer']//input[@checked='checked']//following-sibling::label")
	private List<WebElement> customiseDashboardSavedSearchList;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_aHeader")
	private WebElement dodgeLogo;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'ucTopTabMenu_liHome')]")
	private WebElement homeTabIdentifier;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'commonDashboard')]")
	private List<WebElement> dashboardHeaders;

	@FindBy(how = How.XPATH, using = "//table[@id='specProgramsList']//label")
	private List<WebElement> SpecProgramListInCustomizeDialog;

	@FindBy(how = How.XPATH, using = "//table[@id='specProgramsList']//input")
	private List<WebElement> SpecProgramListCheckBoxInCustomizeDialog;

	@FindBy(how = How.CSS, using = ".customiseDashBoardHeader")
	private WebElement specAlertsLabel;

	@FindBy(how = How.CSS, using = ".customiseTrackingListsText>span")
	private WebElement trackingListLabel;

	@FindBy(how = How.CSS, using = "#Td1")
	private WebElement lblCompanySummary;

	@FindBy(how = How.CLASS_NAME, using = "todaySummHdr")
	private List<WebElement> lblTodaySummHdr;

	@FindBy(how = How.CLASS_NAME, using = "lastVisitSummHdr")
	private List<WebElement> lblLastVisitSummHdr;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companySummary']//p[@class='wrapText']")
	private WebElement lblAllCompanies;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_dvBreadCrumbCon")
	private WebElement breadCrumb;

	@FindBy(how = How.XPATH, using = ".//*[@id='HomeSearch']/div[2]/span")
	private WebElement projectsCompaniesDropdown;

	@FindBy(how = How.XPATH, using = ".//*[@id='HomeSearchDrawr']/div[1]")
	private WebElement projectsDropdownMenu;

	@FindBy(how = How.XPATH, using = ".//*[@id='HomeSearchDrawr']/div[2]")
	private WebElement companiesDropdownMenu;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'ctl00_ucTopTabMenu_li')]")
	private List<WebElement> commonHeaderTabs;

	@FindBy(how = How.XPATH, using = "//td[@id='usaSummHdr']//following-sibling::td[@class='todaySummHdr']")
	private WebElement usaTodayHeader;

	@FindBy(how = How.XPATH, using = "//td[@id='usaSummHdr']//following-sibling::td[@class='lastVisitSummHdr']")
	private WebElement usaLastVist;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d1']//td[1]//span")
	private WebElement usaAllProject;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d1']//td[2]//span")
	private WebElement usaAllProjectTodayCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d1']//td[3]//span")
	private WebElement usaAllProjectLastVisitCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d0']//td[1]//span")
	private WebElement usaVersion1;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d0']//td[2]//span")
	private WebElement usaVersion1TodayCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='usaSummary']//tr[@class='d0']//td[3]//span")
	private WebElement usaVersion1LastVisitCount;

	@FindBy(how = How.XPATH, using = "//td[@id='intSummHdr']//following-sibling::td[@class='todaySummHdr']")
	private WebElement intTodayHeader;

	@FindBy(how = How.XPATH, using = "//td[@id='intSummHdr']//following-sibling::td[@class='lastVisitSummHdr']")
	private WebElement intLastVist;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d1']//td[1]//span")
	private WebElement intAllProject;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d1']//td[2]//span")
	private WebElement intAllProjectTodayCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d1']//td[3]//span")
	private WebElement intAllProjectLastVisitCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d0']//td[1]//span")
	private WebElement intVersion1;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d0']//td[2]//span")
	private WebElement intVersion1TodayCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='internationalSummary']//tr[@class='d0']//td[3]//span")
	private WebElement intVersion1LastVisitCount;

	@FindBy(how = How.XPATH, using = "//a[contains(@href,'construction.com/Help/dodgenetwork/WhatsNew.asp')]")
	private List<WebElement> allLinksOfConstructionSiteWhatsNewSection;

	@FindBy(how = How.XPATH, using = "//ul[@class='networkSupport']//a")
	private List<WebElement> allLinksOfConstructionSiteMyResourcesSection;

	@FindBy(how = How.LINK_TEXT, using = "Download eTakeoff")
	private WebElement downloadTakeOffLink;

	@FindBy(how = How.XPATH, using = "//*[@class='training trainingVideo']/following-sibling::*[@class='trainingList']//*[@class='trainingListItem']//a")
	private List<WebElement> trainingVideosLinks;

	@FindBy(how = How.XPATH, using = "//*[@class='training trainingLiveVideo']/following-sibling::*[@class='trainingCalendar']//a")
	private List<WebElement> attendLiveOnlineTrainingLinks;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Dodge Global Network Crash Course")
	private WebElement dodgeGlobalNetworkCrashCourseLink;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companySummary']/tr/td[2]/span")
	private WebElement companySummaryTodayCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companySummary']/tr/td[3]/span")
	private WebElement companySummaryLastVisitCount;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companySavedSearch']/tr/td[1]/span")
	private WebElement companySavedSearchName;

	@FindBy(how = How.XPATH, using = "//tbody[@id='companyTrackingListSearch']/tr/td[1]/span")
	private WebElement companyTrackingListName;

	@FindBy(how = How.XPATH, using = "//div[@class='keywordError']")
	private WebElement keywordSearchErroMessageLocater;

	@FindBy(how = How.ID, using = "welcomeText")
	private WebElement welcomeMessage;

	@FindBy(how = How.ID, using = "myAccount")
	private WebElement clickOnMyAccountDropDown;

	@FindBy(how = How.XPATH, using = ".//*[@id='accountDropDownLinks']//div//a[text()='Profiles']")
	private WebElement clickOnMyAccountProfile;

	@FindBy(how = How.XPATH, using = ".//*[@id='accountDropDownLinks']//div//a[text()='Users']")
	private WebElement clickOnMyAccountUsers;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lnkLeadProfile")
	private WebElement selectSpecAlert;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_cplBody_divUserList']/div/div/div/div[@class='mngProfileEdit']/a")
	private WebElement clickOnEditLink;

	@FindBy(how = How.ID, using = "manageProfilePopupSaveBottom")
	private WebElement clickOnAddProfileSaveButton;

	@FindBy(how = How.ID, using = "error-sections")
	private WebElement errorMessage;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileName")
	private WebElement profileNameTxtField;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileDescription")
	private WebElement descriptionTxtField;

	@FindBy(how = How.ID, using = "manageProfilePopupDestroyBtm")
	private WebElement clickOnCacelButton;

	@FindBy(how = How.ID, using = "licensedUser")
	private WebElement liscensNumberDisplay;

	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']//div//div//div//input[@value='QA1FlooringCPTX']")
	private WebElement clickOnspecAlertsCheckBox;

	@FindBy(how = How.XPATH, using = ".//*[@class='editProfileBtnContainer clearfix']//div[@class='saveBtnContainer']//input[@id='manageProfilePopupSaveBottom']")
	private WebElement clickOnspecAlertssaveButton;

	@FindBy(how = How.XPATH, using = ".//*[@class='mngProfileControls clearfix']//div//span[text()='TC1079_spec_search']")
	private WebElement checkSpecProfileDisplay;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl00_lblUserName")
	private WebElement newProfileDisplay;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileName")
	private WebElement profileNameTextBox;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtProjectProfileDescription")
	private WebElement descriptionTextBox;

	@FindBy(how = How.LINK_TEXT, using = "My Account - Profiles")
	private WebElement pageTitleProfile;

	@FindBy(how = How.LINK_TEXT, using = "My Account - Users")
	private WebElement pageTitleUser;

	@FindBy(how = How.LINK_TEXT, using = " Marketing Profile:")
	private WebElement marketingProfile;

	@FindBy(how = How.ID, using = "ctl00_cplBody_repUserList_ctl03_lblUserName")
	private WebElement profilenewaddedtc1080;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Home Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void openURL(String URL) {
		driver.get(URL);
	}

	public LoginPage clickOnSignOutButton() {
		extentTest.log(Status.INFO, "Click on SignOut Link");
		SeleniumUtils.isClickable(signOutLink, driver);
		signOutLink.click();
		return new LoginPage(driver);
	}

	public ProjectResultsPage clickOnFirstTrackingList() {
		extentTest.log(Status.INFO, "Click on First Tracking List");
		SeleniumUtils.isClickable(firstTrackingList, driver);
		firstTrackingList.click();
		return new ProjectResultsPage(driver);
	}

	public boolean isSpecAlertsTabDisplayed() {
		extentTest.log(Status.INFO, "Check if Spec alerts tab is displayed");
		try {
			return specAlertsTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isCustomiseSavedSearchesInPopupDisplayed() {
		extentTest.log(Status.INFO,
				"Check if Customise saved Searched in Pupup displayed");
		try {
			return customiseSavedSearchesInPopup.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isInternationalTabDisplayed() {
		extentTest.log(Status.INFO, "Check if International tab is displayed");
		try {
			return internationalLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnHomeTab() {
		extentTest.log(Status.INFO, "Clicking on Home Tab");
		homeMenuLink.click();

	}

	public boolean isCustomiseTrackingListsInPopupDisplayed() {
		extentTest.log(Status.INFO,
				"Check if Tracking Lists in Pupup displayed");
		try {
			return customiseTrackingListsInPopup.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isInternationalPageNotFoundDisplayed() {
		extentTest.log(Status.INFO,
				"Check if international PageNotFound displayed");
		try {
			return internationalPageNotFound.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public MyAccount clickOnMyAccountLink() {
		extentTest.log(Status.INFO, "Click On MyAccount Link");
		SeleniumUtils.isVisible(myAccountLink, driver);
		myAccountLink.click();
		return new MyAccount(driver);
	}

	public HelpPage clickOnHelpLink() {
		extentTest.log(Status.INFO, "Click On Help Link");
		helpLink.click();
		return new HelpPage(driver);
	}

	@FindBy(how = How.ID, using = "welcomeUser")
	private WebElement welcomeUser;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lblUser")
	private WebElement headerUsername;

	@FindBy(how = How.XPATH, using = "//input[@id='0']")
	private WebElement saveSearchFirstCheckbox;

	@FindBy(how = How.XPATH, using = "(//input[@id='0'])[2]")
	private WebElement trackingListFirstCheckbox;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),concat('What',\"'\",'s New'))]")
	private WebElement whatsNew;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'USA Dodge Project Summary')]")
	private WebElement usaDodgeProjectSummary;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'My Project Saved Searches')]")
	private WebElement myProjectSavedSearches;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Intl Dodge Project Summary')]")
	private WebElement intlDodgeProjectSummary;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'My Project Tracking Lists')]")
	private WebElement myProjectTrackingLists;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/logo.png']")
	private WebElement printDodgeLogo;

	@FindBy(how = How.XPATH, using = "//img[@src='/3010100/planroom/Content/images/logo.png']")
	private WebElement planroomLogo;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_aHeader")
	private WebElement headerDodgeLogo;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Dodge Project Summary')]")
	private WebElement dodgeProjectSummary;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Dodge Business Intelligence')]")
	private WebElement dodgeBusinessIntelligence;

	@FindBy(how = How.ID, using = "specshareLink")
	private WebElement dodgeBusinessIntelligenceLink;

	@FindBy(how = How.XPATH, using = "//div[@class='seperateEmail']")
	private WebElement leadEmailDialogTip;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'1.800.393.6343')]")
	private WebElement footerPhoneNumber;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'My Resources')]")
	private WebElement myResourcesSection;

	@FindBy(how = How.XPATH, using = "(//div[@class='error-messages'])[1]")
	private WebElement customizeDialogErrorMessage;

	@FindBy(how = How.XPATH, using = "//div[@id='customizeDashBoardPopup']/div[1]")
	private WebElement customizeCompanyDashboardTitle;

	@FindBy(how = How.ID, using = "ctl00_ucFooter_lblNetworkVersion")
	private WebElement footerAppVersionAndBestViewedIn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_KeywordUI1_HomeSearchText")
	private WebElement projCompDropdown;

	@FindBy(how = How.CSS, using = "#trackingListSearch td:nth-of-type(1)")
	private List<WebElement> myProjectsTrackingList;

	@FindBy(how = How.CSS, using = "#savedSearch td:nth-of-type(1)")
	private List<WebElement> myProjectsSavedSearch;

	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']/div/div/div/div/span")
	private WebElement assignedUsersBtn;

	@FindBy(how = How.ID, using = "My-Account---Profiles")
	private WebElement breadCrumbDisplay;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_PlanRoom_planRoomWrapper")
	private WebElement planRoomWidget;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_PlanRoom_syncPrpAdmin")
	private WebElement planRoomWidgetTextMessage;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_PlanRoom_planRoomWrapper']//a[@class='assignUser']")
	private List<WebElement> profileSpecAlert;

	@FindBy(how = How.ID, using = "licenseSectionText")
	private WebElement licenseSectionTextLocator;

	@FindBy(how = How.XPATH, using = ".//*[@class='projectDashboard']/table/tbody/tr")
	private List<WebElement> listOfProgramm;

	@FindBy(how = How.XPATH, using = ".//*[@class='specDashboard']//table//tbody//tr")
	private List<WebElement> specList;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisibleCheck;

	public boolean isfooterAppVersionAndBestViewedInDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of version number and Best Viewed Browser details in footer");
		SeleniumUtils.scrollToView(driver, footerAppVersionAndBestViewedIn);
		return footerAppVersionAndBestViewedIn.isDisplayed();
	}

	public String getcustomizeCompanyDashboardTitle() {
		extentTest.log(Status.INFO,
				"Getting title of Customize Company Dashboard dialog");
		SeleniumUtils.scrollToView(driver, dodgeBusinessIntelligenceLink);
		String customizeCompanyDashboardTitle1 = customizeCompanyDashboardTitle
				.getText();
		return customizeCompanyDashboardTitle1;
	}

	public boolean iscustomizeDialogErrorMessageDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for the error message when more than 10 save search or 20 tracking list is selected");
		try {
			return customizeDialogErrorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getTextEnteredInTheSearchBox() {
		extentTest.log(Status.INFO,
				"Getting the text entered in the search box");
		SeleniumUtils.scrollToView(driver, searchTxtField);
		return searchTxtField.getAttribute("value");
	}

	public boolean isCompaniesInDropdownMenuDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of Companies option in Dropdown Menu");
		SeleniumUtils.scrollToView(driver, projectsCompaniesDropdown);
		projectsCompaniesDropdown.click();
		return companiesDropdownMenu.isDisplayed();
	}

	public boolean isProjectsInDropdownMenuDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of Projects option in Dropdown Menu");
		SeleniumUtils.scrollToView(driver, projectsCompaniesDropdown);
		projectsCompaniesDropdown.click();
		return projectsDropdownMenu.isDisplayed();
	}

	public boolean isSearchTxtFieldDisplayed() {
		extentTest
				.log(Status.INFO, "Checking for visiblity of search Text box");
		SeleniumUtils.scrollToView(driver, searchTxtField);
		return searchTxtField.isDisplayed();
	}

	public boolean isMyResourcesSectionDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of My Resources Section");
		SeleniumUtils.scrollToView(driver, myResourcesSection);
		return myResourcesSection.isDisplayed();
	}

	public boolean isfooterPhoneNumberDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of phone number in footer");
		SeleniumUtils.scrollToView(driver, footerPhoneNumber);
		return footerPhoneNumber.isDisplayed();
	}

	public CompanyResultsPage enterAndSearchTextUsingEnterKeyForCompanies(
			String searchText) {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest
				.log(Status.INFO,
						"Search any company by entering Search Text and then pressing Enter key");
		searchTxtField.clear();
		projectsCompaniesDropdown.click();
		companiesDropdownMenu.click();
		searchTxtField.sendKeys(searchText);
		searchTxtField.sendKeys(Keys.ENTER);
		return new CompanyResultsPage(driver);
	}

	public ProjectResultsPage enterAndSearchTextUsingEnterKeyForProjects(
			String searchText) {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest
				.log(Status.INFO,
						"Search any project by entering Search Text and then pressing Enter key");
		searchTxtField.clear();
		projectsCompaniesDropdown.click();
		projectsDropdownMenu.click();
		searchTxtField.sendKeys(searchText);
		searchTxtField.sendKeys(Keys.ENTER);
		return new ProjectResultsPage(driver);
	}

	public boolean isPlanroomTabDisplayed() {
		extentTest.log(Status.INFO, "Check Planroom tab is displayed.");
		return CommonUtils.checkElementExist(planroomLink, driver);
	}

	public boolean isPlanroomWidgetDisplayed() {
		extentTest.log(Status.INFO, "Check Planroom tab is displayed.");
		return CommonUtils.checkElementExist(planRoomWidget, driver);
	}

	public String getPlanroomWidgetMessage() {
		extentTest.log(Status.INFO, "Return the Planroom tab message.");
		return planRoomWidgetTextMessage.getText();
	}

	public String getPlanroomMarketingMessageText() {
		extentTest.log(Status.INFO, "Get Marketing Message Text");
		return planRoomWidget.getText();
	}

	public boolean isAssignUsersBtnDisplayed() {
		extentTest.log(Status.INFO, "Check Planroom tab is displayed.");
		return CommonUtils.checkElementExist(assignedUsersBtn, driver);
	}

	public void clickOnAssignedUsersBtn() {
		extentTest.log(Status.INFO, "Click on Assign users");
		assignedUsersBtn.click();
	}

	public void clickOnPlanroomTab() {
		extentTest.log(Status.INFO, "Click the Common Planroom Tab.");
		planroomLink.click();
	}

	public void clickOnInternationalTab() {
		extentTest.log(Status.INFO, "Click the Common International Tab.");
		internationalLink.click();
	}

	public void clickOnCompaniesTab() {
		extentTest.log(Status.INFO, "Click the Common companies Tab");
		CommonCompaniesTab.click();
	}

	public String leadEmailDialogTip() {
		extentTest.log(Status.INFO, "Fetching lead Email Dialog Tip");
		String leadEmailDialogTipMess = leadEmailDialogTip.getText();
		return leadEmailDialogTipMess;
	}

	public boolean isdodgeBusinessIntelligenceDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of Dodge Business Intelligence section");
		SeleniumUtils.scrollToView(driver, dodgeBusinessIntelligence);
		return dodgeBusinessIntelligence.isDisplayed();
	}

	public String dodgeBusinessIntelligenceLinkUrl() {
		extentTest.log(Status.INFO,
				"Getting Url of Dodge Business Intelligence link");
		SeleniumUtils.scrollToView(driver, dodgeBusinessIntelligenceLink);
		String urlint = dodgeBusinessIntelligenceLink.getAttribute("href");
		return urlint;
	}

	public boolean isDodgeProjectSummaryDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of Dodge Project Summary on Dashboard for regional license");
		SeleniumUtils.scrollToView(driver, dodgeProjectSummary);
		return dodgeProjectSummary.isDisplayed();
	}

	public boolean isUSADodgeProjectSummaryDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of USA Dodge Project Summary on Dashboard");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(usaDodgeProjectSummary));
		SeleniumUtils.scrollToView(driver, usaDodgeProjectSummary);
		return usaDodgeProjectSummary.isDisplayed();
	}

	public boolean isheaderDodgeLogoDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of Dodge logo in header");
		SeleniumUtils.scrollToView(driver, headerDodgeLogo);
		return headerDodgeLogo.isDisplayed();
	}

	public boolean isprintDodgeLogoDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for visiblity of Dodge logo when printing");
		SeleniumUtils.scrollToView(driver, printDodgeLogo);
		return printDodgeLogo.isDisplayed();
	}

	public boolean isMyProjectSavedSearchesDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of My Project Saved Searches on Dashboard");
		SeleniumUtils.scrollToView(driver, myProjectSavedSearches);
		return myProjectSavedSearches.isDisplayed();
	}

	public boolean isIntlDodgeProjectSummaryDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of My Project Tracking Lists on Dashboard");
		SeleniumUtils.scrollToView(driver, intlDodgeProjectSummary);
		return intlDodgeProjectSummary.isDisplayed();
	}

	public boolean isMyProjectTrackingListsDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for visiblity of My Project Tracking Lists on Dashboard");
		SeleniumUtils.scrollToView(driver, myProjectTrackingLists);
		return myProjectTrackingLists.isDisplayed();
	}

	public String welcomeMessage() {
		extentTest.log(Status.INFO, "Fetching Welcome message");
		String A1 = welcomeUser.getText();
		String A2 = headerUsername.getText();
		String welcomeMessage1 = A1 + " " + A2;
		return welcomeMessage1;
	}

	public boolean isSaveSearchFirstCheckboxDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for Saved search default search checkbox on Customize Dashboard");
		SeleniumUtils.scrollToView(driver, saveSearchFirstCheckbox);
		return saveSearchFirstCheckbox.isDisplayed();
	}

	public boolean isTrackingListFirstCheckboxDisplayed() {
		extentTest
				.log(Status.INFO,
						"Checking for tracking list first checkbox on Customize Dashboard");
		SeleniumUtils.scrollToView(driver, trackingListFirstCheckbox);
		return trackingListFirstCheckbox.isDisplayed();
	}

	public boolean isWhatsNewDisplayed() {
		extentTest.log(Status.INFO, "Checking for What's New Section display");
		SeleniumUtils.scrollToView(driver, whatsNew);
		return whatsNew.isDisplayed();
	}

	public boolean isOnSaveFromCustomizeDashboardDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for Save option Customize Dashboard");
		SeleniumUtils.scrollToView(driver, customiseDashboardPopUpSave);
		return customiseDashboardPopUpSave.isDisplayed();

	}

	public void clickOnSaveFromCustomizeDashboardDisplayed() {
		extentTest.log(Status.INFO,
				"clicking on Save option Customize Dashboard");
		SeleniumUtils.scrollToView(driver, customiseDashboardPopUpSave);
		customiseDashboardPopUpSave.click();

	}

	public boolean isOnCancelFromCustomizeDashboardDisplayed() {
		extentTest.log(Status.INFO,
				"Checking for cancel option Customize Dashboard");
		SeleniumUtils.scrollToView(driver, customiseDashboardPopUpSave);
		return customiseDashboardPopUpCancel.isDisplayed();

	}

	public String HelpLinkUrl() {
		extentTest.log(Status.INFO, "Getting Help page URL");
		String helpUrl = helpLink.getAttribute("href");
		return helpUrl;
	}

	public boolean isMySpecAlertsLinkDisplayed() {
		extentTest.log(Status.INFO, "Check if Spec alerts Link is displayed");
		return mySpecAlertsLink.isDisplayed();
	}

	public void clickOnMySpecAlertsLink() {
		extentTest.log(Status.INFO, "Click On My SpecAlerts Link");
		mySpecAlertsLink.click();
	}

	public SavedSearchesPage clickOnSavedSearchMenu() {
		extentTest.log(Status.INFO, "Click On Saved Search");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(savedSearchMenu));
		savedSearchMenu.click();
		waitforLoadingRing();
		return new SavedSearchesPage(driver);
	}

	public SavedSearchesPage clickOnSavedSearchMenuUnderMyAccount() {
		extentTest.log(Status.INFO, "Click On Saved Search");
		mySavedSearchesMenu.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(homeMenuLink));
		return new SavedSearchesPage(driver);
	}

	public boolean isHomeMenuLinkDisplayed() {
		extentTest.log(Status.INFO, "Check if Home Menu Link is displayed");
		try {
			return homeMenuLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void enterSearchText(String searchText) {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest.log(Status.INFO, "Enter Search Text");
		searchTxtField.clear();
		searchTxtField.sendKeys(searchText);
	}

	public void clearSearchText() {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest.log(Status.INFO, "Clear Search Text");
		searchTxtField.clear();
	}

	public void clickOnSearchButton() {
		extentTest.log(Status.INFO, "Click on Search Button");
		searchBtn.click();
		// waitforLoadingRing();
	}

	public void clickOnSearchButtonHomePage() {
		extentTest.log(Status.INFO, "Click on Search Button on homePage");
		homeSearchBtn.click();
	}

	public CompanyResultsPage clickOnSearchButtonHomePagetoSearchCompanies() {
		extentTest.log(Status.INFO, "Click on Search Button on homePage");
		homeSearchBtn.click();
		return new CompanyResultsPage(driver);
	}

	public void clickOnProjectsCompaniesDropdown() {
		extentTest.log(Status.INFO,
				"Click on Projects Companies dropdown on home Page");
		projectsCompaniesDropdown.click();
	}

	public void clickOnProjectsDropdownMenu() {
		extentTest.log(Status.INFO, "Click on Projects dropdown Menu");
		projectsDropdownMenu.click();
	}

	public void clickOnCompaniesDropdownMenu() {
		extentTest.log(Status.INFO, "Click on Companies dropdown Menu");
		companiesDropdownMenu.click();
	}

	public ProjectPage clickOnSearchButtonDR() {
		extentTest.log(Status.INFO, "Click on Search Button");
		searchBtn.click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnSearchButtonDRHomePage() {
		extentTest.log(Status.INFO, "Click on Search Button");
		homeSearchBtn.click();
		return new ProjectPage(driver);
	}

	public String getSearchText() {
		extentTest.log(Status.INFO, "get keyword Search Text");
		return searchTxtField.getAttribute("value");
	}

	public String getHyperlinkTermsOfUse() {
		extentTest.log(Status.INFO, "get hyperlink for terms of use");
		return termsOfUseLink.getAttribute("href");
	}

	public String getHyperlinkPrivacyPolicy() {
		extentTest.log(Status.INFO, "get hyperlink for Privacy Policy");
		return privacyPolicyLink.getAttribute("href");
	}

	public String getHyperlinkAboutUs() {
		extentTest.log(Status.INFO, "get hyperlink for about us");
		return aboutUsLink.getAttribute("href");
	}

	public String getHyperlinkCustomerCare() {
		extentTest.log(Status.INFO, "get hyperlink for customer Care");
		return customercareLink.getAttribute("href");
	}

	public SavedSearchPopUp clickOnSaveSearchButtonProject() {
		extentTest.log(Status.INFO, "Click on Save Search Button");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(saveSearchBtnProj));
		saveSearchBtnProj.click();
		return new SavedSearchPopUp(driver);
	}

	public boolean isSaveSearchButtonDisplayed() {
		extentTest.log(Status.INFO, "Check Save Search Button is displayed.");
		return CommonUtils.checkElementExist(saveSearchBtnProj, driver);
	}

	public SavedSearchPopUp clickOnSaveSearchButtonCompany() {
		extentTest.log(Status.INFO, "Click on Save Search Button");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(saveSearchBtnCompany));
		saveSearchBtnCompany.click();
		return new SavedSearchPopUp(driver);
	}

	public void clickOnMySearchesDropDown() {
		extentTest.log(Status.INFO, "Click on My Searches dropDown");
		mySearchesDropDown.click();
	}

	public TrackingList clickOnMyTrackingListSubmenu() {
		extentTest.log(Status.INFO, "Click on My Tracking List Submenu");
		myTrackingListSubmenu.click();
		return new TrackingList(driver);
	}

	public boolean isSpecAlertDropDownValueDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if My SpecAlerts option is present in the dropdown");
		return specAlertsDropDownValue.isDisplayed();
	}

	public boolean isMyProjectTrackingListDisplayed() {
		extentTest.log(Status.INFO,
				"Check if My Projects Tracking List is displayed");
		return dashboardMyProjectsTrackingList.get(0).isDisplayed();
	}

	public boolean isMyProjectTrackingListTodayDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under today column in My Projects Tracking List is present in the Dashboard Projects Tab");
		return dashboardMyProjectsTrackingListTodayLinks.get(0).isDisplayed();
	}
	
	public ProjectResultsPage clickOnFirstMyProjectTrackedToday() {
		extentTest.log(Status.INFO,
				"Click on first My Projects Tracked today.");
		SeleniumUtils.isVisible(dashboardMyProjectsTrackingListTodayLinks.get(0), driver);		
		CommonUtils.clickOnElementUsingJavascript(dashboardMyProjectsTrackingListTodayLinks.get(0), driver);
		return new ProjectResultsPage(driver);
	}

	public boolean isMyProjectTrackingListSinceLastVisitDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under 'Since My Last Visit' column in My Projects Tracking List is present in the Dashboard Projects Tab");
		return dashboardMyProjectsTrackingListLastVisitLinks.size()>0;
	}
	
	public ProjectResultsPage clickOnFirstMyProjectLastVisited() {
		extentTest.log(Status.INFO,
				"Click on first My Projects lastly visited.");
		SeleniumUtils.isVisible(dashboardMyProjectsTrackingListLastVisitLinks.get(0), driver);		
		CommonUtils.clickOnElementUsingJavascript(dashboardMyProjectsTrackingListLastVisitLinks.get(0), driver);
		return new ProjectResultsPage(driver);
	}

	public boolean isMyProjectsSavedSearchDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if My Projects Saved Search is present in the Dashboard Projects Tab");
		return !dashboardMyProjectsSavedSearch.isEmpty();
	}

	public boolean isMyProjectsSavedSearchTodayDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under today column in My Projects Saved Search is present in the Dashboard Projects Tab");
		return !dashboardMyProjectsTodayVisitSavedSearchList.isEmpty();
	}

	public boolean isMyProjectsSavedSearchSinceLastVisitDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under 'Since My Last Visit' column in My Projects Saved Search is present in the Dashboard Projects Tab");
		return !dashboardMyProjectsLastVisitSavedSearchList.isEmpty();
	}

	public boolean isDashboardSpecAlertsProgramListTodayDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under today column in SpecAlerts Program List is present in the Dashboard SpecAlerts Tab");
		return dashboardSpecAlertsProgramListAll.get(1).isDisplayed();
	}

	public List<String> getDashboardSpecAlertsProgramList() {
		return CommonUtils
				.getListFromWebElements(dashboardSpecAlertsProgramsList);
	}

	public List<String> getProfileSpecAlertsProgramList() {
		return CommonUtils.getListFromWebElements(profileSpecAlert);
	}

	public boolean isDashboardSpecAlertsProgramListSinceLastVisitDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if count under 'Since My Last Visit' column in SpecAlerts Program List is present in the Dashboard SpecAlerts Tab");
		return dashboardSpecAlertsProgramListAll.get(2).isDisplayed();
	}

	public SpecAlertsResultsPage clickOnDashboardSpecAlertsFirstProgramToday() {
		extentTest
				.log(Status.INFO,
						"Clicking on count under today column in SpecAlerts Program List in the Dashboard SpecAlerts Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardSpecAlertsProgramListAll);
		String programSelected = dashboardSpecAlertsProgramListAll.get(0)
				.getText();
		dashboardSpecAlertsProgramListAll.get(1).click();

		return new SpecAlertsResultsPage(driver, programSelected);
	}

	public SpecAlertsResultsPage clickOnDashboardSpecAlertsFirstProgramSinceLastVisit() {
		extentTest
				.log(Status.INFO,
						"Clicking on count under 'Since My Last Visit' column in SpecAlerts Program List in the Dashboard SpecAlerts Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardSpecAlertsProgramListAll);
		String programSelected = dashboardSpecAlertsProgramListAll.get(0)
				.getText();
		dashboardSpecAlertsProgramListAll.get(2).click();

		return new SpecAlertsResultsPage(driver, programSelected);
	}

	public MyAccount clickOnMyTrackingListsMenuLink() {
		extentTest.log(Status.INFO, "Click On My Tracking Lists Menu Link");
		MyTrackingListsMenuLink.click();
		return new MyAccount(driver);
	}

	public TrackingList clickOnMyTrackingList() {
		extentTest.log(Status.INFO, "Click On My Tracking Lists Menu Link");
		MyTrackingListsMenuLink.click();
		waitforLoadingRing();
		return new TrackingList(driver);
	}

	public void clickOnDashboardProjectsTab() {
		extentTest.log(Status.INFO, "Click on Projects Link");
		dashboardProjectsTab.click();

	}

	public void clickOnDashboardCompaniesTab() {
		extentTest.log(Status.INFO, "Click on Companies Link");
		dashboardCompaniesTab.click();

	}

	public ProjectResultsPage clickOnProjectsLink() {
		extentTest.log(Status.INFO, "Click on Projects Link");
		projectsLink.click();
		// waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickOnProjectsLinkWithPersistantSetting() {
		extentTest.log(Status.INFO,
				"Click on Projects Link with persistant setting.");
		projectsLink.click();
		return new ProjectResultsPage(driver, true);
	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck,
				driver);
	}

	public void clickOnSweetsActivityLink() {
		extentTest.log(Status.INFO, "Click on Sweets Activity Link");
		sweetsActivityLink.click();

	}

	public CompanyResultsPage clickOnCompaniesLink() {
		extentTest.log(Status.INFO, "Click on Companies Link");
		companiesLink.click();
		// waitforLoadingRing();
		return new CompanyResultsPage(driver);
	}

	public AdminReportsPage clickOnReportsLink() {
		extentTest.log(Status.INFO, "Click On Reports Link");
		reportsLink.click();
		return new AdminReportsPage(driver);
	}

	public boolean isSweetsActivityTabDisplayed() {
		extentTest.log(Status.INFO, "Check Sweets Activity Tab is Displayed");
		return CommonUtils.checkElementExist(sweetsActivityLink, driver);
	}

	public MarketingPage clickOnSweetsLink() {
		extentTest.log(Status.INFO, "Click on Sweets Link");
		sweetsActivityLink.click();
		return new MarketingPage(driver);
	}

	public ProjectResultsPage clickOnFirstDashboardProjectsTrackingList() {
		String strSelectedTrackingList = "";
		extentTest.log(Status.INFO,
				"Click on Tracking List from the Dashboard Projects Tab");
		SeleniumUtils.scrollToView(driver, dashboardMyProjectsTrackingList
				.get(dashboardMyProjectsTrackingList.size() - 1));
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardMyProjectsTrackingList);
		for (WebElement webElement : dashboardMyProjectsTrackingList) {
			strSelectedTrackingList = webElement.getText();
			webElement.click();
			break;
		}
		return new ProjectResultsPage(driver, strSelectedTrackingList);
	}

	public boolean IsDashboardProjectsTrackingListMatch(
			List<String> strSelectedTrackingList) {
		boolean isFlag = false;
		extentTest.log(Status.INFO,
				"Click on Tracking List from the Dashboard Projects Tab");
		CommonUtils.IterateList(strSelectedTrackingList);
		CommonUtils.IterateList(CommonUtils
				.getListFromWebElements(dashboardMyProjectsTrackingList));
		for (WebElement webElement : dashboardMyProjectsTrackingList) {
			if (strSelectedTrackingList.contains(webElement.getText()))
				;
			isFlag = true;
		}
		if (isFlag)
			return true;
		else
			return false;
	}

	public boolean IsDashboardCompaniesSavedSearchMatch(
			List<String> strSelectedSavedSearchList) {
		boolean isFlag = false;
		extentTest.log(Status.INFO, "Verify whether the selected list matches");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOfAllElements(customiseDashboardSavedSearch));
		// SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
		// customiseDashboardSavedSearch);
		for (WebElement webElement : customiseDashboardSavedSearch) {
			if (strSelectedSavedSearchList.contains(webElement.getText()))
				;
			isFlag = true;
		}
		if (isFlag)
			return true;
		else
			return false;
	}

	public ProjectResultsPage clickOnFirstDashboardMyProjectsSavedSearchList() {
		extentTest
				.log(Status.INFO,
						"Click on My Project Saved Search List from the Dashboard Projects Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardMyProjectsSavedSearch);
		try {
			dashboardMyProjectsSavedSearch.get(0).click();
		} catch (Exception ex) {
		}
		return new ProjectResultsPage(driver);

	}

	public ProjectResultsPage clickOnSpecificProjectSavedSearch(
			String searchName) {
		extentTest.log(Status.INFO, "Click On specific Saved Search");

		driver.findElement(By.linkText(searchName)).click();
		return new ProjectResultsPage(driver);
	}

	public CompanyResultsPage clickOnFirstDashboardMyCompaniesSavedSearchList() {
		extentTest
				.log(Status.INFO,
						"Click on My Companies Saved Search List from the Dashboard Company Tab");
		CommonUtils.scrollDownTillElement(
				dashboardMyCompaniesSavedSearch.get(0), driver);
		try {
			dashboardMyCompaniesSavedSearch.get(1).click();
		} catch (Exception ex) {
		}
		return new CompanyResultsPage(driver);

	}

	public ProjectResultsPage clickOnSecondDashboardMyProjectsSavedSearchList() {
		extentTest
				.log(Status.INFO,
						"Click on My Project Saved Search List from the Dashboard Projects Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardMyProjectsSavedSearch);
		dashboardMyProjectsSavedSearch.get(1).click();
		waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickOnDashboardMyProjectsSavedSearch(
			String saveSearchName) {
		extentTest
				.log(Status.INFO,
						"Click on My Project Saved Search from the Dashboard Projects Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardMyProjectsSavedSearch);
		for (WebElement saveSearchLink : dashboardMyProjectsSavedSearch) {
			if (saveSearchName
					.equalsIgnoreCase(saveSearchLink.getText().trim())) {
				saveSearchLink.click();
				waitforLoadingRing();
				break;
			}
		}
		return new ProjectResultsPage(driver);
	}

	public SpecAlertsResultsPage clickOnProgramInSpecAlertsDashboard(
			int programIndex) {
		String selectedProgram = "";
		extentTest.log(Status.INFO,
				"Click on First Program from the Dashboard SpecAlerts Tab");
		SeleniumUtils.waitForListOfWebElementsToBePresent(this.driver,
				dashboardSpecAlertsProgramListAll);
		for (int i = programIndex - 1; i < dashboardSpecAlertsProgramListAll
				.size(); i = i + 2) {
			selectedProgram = dashboardSpecAlertsProgramListAll.get(i)
					.getText();
			dashboardSpecAlertsProgramListAll.get(i).click();
			break;
		}
		return new SpecAlertsResultsPage(driver, selectedProgram);
	}

	public boolean isHomeSearchDropDisplayed() {
		extentTest.log(Status.INFO,
				"Check if Home search drop down is displayed");
		return HomeSearchDropdown.isDisplayed();
	}

	public void ClickOnHomeSearchDrodown() {
		extentTest.log(Status.INFO, "Click on Home search drop down");
		HomeSearchDropdown.click();
	}

	public SpecAlertsResultsPage ClickMySpecalertsDropdown() {
		extentTest.log(Status.INFO, "Click on My Specalerts drop down");
		mySpecAlertsDropdown.click();
		return new SpecAlertsResultsPage(driver);

	}

	public String getHomeSearchDropdownProjectsOpton() {
		return HomeSearchDropdownProjectsOpton.getText();
	}

	public String getHomeSearchDropdownCompaniesOpton() {
		return HomeSearchDropdownCompaniesOpton.getText();
	}

	public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText();
	}

	public boolean isCommonProjectsTabDisplayed() {
		extentTest
				.log(Status.INFO, "Check if Common Projects Tab is displayed");
		return CommonProjectsTab.isDisplayed();
	}

	public boolean isCommonCompaniesTabDisplayed() {
		extentTest.log(Status.INFO,
				"Check if Common Companies Tab is displayed");
		return CommonCompaniesTab.isDisplayed();
	}

	public boolean isCommonSpecAlertsTabDisplayed() {
		extentTest.log(Status.INFO,
				"Check if Common SpecAlerts Tab is displayed");
		return CommonSpecAlertsTab.isDisplayed();
	}

	public void clickOnProjectsTab() {
		extentTest.log(Status.INFO, "Click the Common Projects Tab");
		CommonProjectsTab.click();
	}

	public void clickOnSpecAlertsTab() {
		extentTest.log(Status.INFO, "Click the Common SpecAlerts Tab");
		CommonSpecAlertsTab.click();
	}

	public Integer countSelectOptions() {
		return recentLogin.size();
	}

	public void clickOnDodgeLogo() {
		extentTest.log(Status.INFO, "Click on Dodge log to move to Home page");
		dodgeLogo.click();
	}

	public void clickOnRecentLogin() {
		extentTest.log(Status.INFO, "Clicking on Recent Login");
		for (WebElement webElement : recentLogin) {
			webElement.click();
			break;
		}
	}

	public CustomizeProjectDashboardPage clickOnCustomizeDashboard() {
		extentTest.log(Status.INFO, "Clicking on Customize Dashboard");
		SeleniumUtils.isVisible(customiseDashboardLink, driver);
		SeleniumUtils.isClickable(customiseDashboardLink, driver);
		customiseDashboardLink.click();
		return new CustomizeProjectDashboardPage(driver);
	}

	public boolean isCustomizeLinkInDashboardDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if Customize link in Dashboard displayed");
		return customiseDashboardLink.isDisplayed();
	}

	public boolean isLeadEmailLinkInDashboardDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if leadEmail link in Dashboard displayed");
		return leadEmailLink.isDisplayed();
	}

	public void clickOnLeadEmailLink() {
		extentTest.log(Status.INFO, "Clicking on lead email link");
		leadEmailLink.click();
	}

	public List<String> selectFromCustomiseDashboardTrackingLists(
			int numberOfSelect) {
		extentTest
				.log(Status.INFO,
						"Selecting from Tracking List from the Customize Dashboard Pop Up");
		List<String> trackingList = new ArrayList<String>();
		SeleniumUtils.waitForListOfWebElementsToBePresent(driver,
				customiseDashboardTracklists);
		for (int i = 0; i < numberOfSelect; i++) {
			trackingList.add(customiseDashboardTracklists.get(i).getText());
			if (customiseDashboardTracklists.get(i).getAttribute("checked") == null) {
				CommonUtils.scrollDownTillElement(
						customiseDashboardTracklists.get(i), driver);
				customiseDashboardTracklists.get(i).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return trackingList;
	}

	public List<String> selectFromCustomiseDashboardSavedSearch(
			int numberOfSelect) {
		extentTest
				.log(Status.INFO,
						"Selecting from Saved Search from the Customize Dashboard Pop Up");
		List<String> SavedSearchList = new ArrayList<String>();
		SeleniumUtils.waitForListOfWebElementsToBePresent(driver,
				customiseDashboardSavedSearch);
		for (int i = 0; i < numberOfSelect; i++) {
			SavedSearchList.add(customiseDashboardSavedSearch.get(i).getText());
			if (customiseDashboardSavedSearch.get(i).getAttribute("checked") == null) {
				CommonUtils.scrollDownTillElement(
						customiseDashboardSavedSearch.get(i), driver);
				CommonUtils.clickOnElementUsingJavascript(
						customiseDashboardSavedSearch.get(i), driver);
				waitforLoadingRing();
			}
		}
		return SavedSearchList;
	}

	public List<String> getCustomiseDashboardSavedSearchList() {
		extentTest.log(Status.INFO,
				"Saved Search from the Customize Dashboard Pop Up");
		List<String> SavedSearchList = new ArrayList<String>();
		SeleniumUtils.waitForListOfWebElementsToBePresent(driver,
				customiseDashboardSavedSearchList);
		for (WebElement savedSearch : customiseDashboardSavedSearchList) {
			SavedSearchList.add(savedSearch.getAttribute("innerHTML"));
		}
		return SavedSearchList;
	}

	public void uncheckFromCustomiseDashboardSavedSearch() {
		extentTest
				.log(Status.INFO,
						"Uncheck from Saved Search from the Customize Dashboard Pop Up");
		for (int i = 1; i <= customiseDashboardSavedSearch.size() - 1; i++) {
			SeleniumUtils.isClickable(customiseDashboardSavedSearch.get(i),
					driver);
			if (customiseDashboardSavedSearch.get(i).getAttribute("checked") != null) {
				CommonUtils.scrollDownTillElement(
						customiseDashboardSavedSearch.get(i), driver);
				CommonUtils.clickOnElementUsingJavascript(
						customiseDashboardSavedSearch.get(i), driver);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void uncheckaLLFromCustomiseDashboardSavedSearch() {
		extentTest
				.log(Status.INFO,
						"Uncheck from Saved Search from the Customize Dashboard Pop Up");
		for (int i = 0; i <= customiseDashboardSavedSearch.size(); i++) {
			SeleniumUtils.isClickable(customiseDashboardSavedSearch.get(i),
					driver);
			if (customiseDashboardSavedSearch.get(i) != null
					&& customiseDashboardSavedSearch.get(i).getAttribute(
							"checked") != null) {
				CommonUtils.scrollDownTillElement(
						customiseDashboardSavedSearch.get(i), driver);
				customiseDashboardSavedSearch.get(i).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void uncheckFromCustomiseDashboardTrackingLists() {
		extentTest
				.log(Status.INFO,
						"Uncheck from Tracking List from the Customize Dashboard Pop Up");
		for (int i = 0; i < customiseDashboardTracklists.size(); i++) {
			SeleniumUtils.isClickable(customiseDashboardTracklists.get(i),
					driver);
			if (customiseDashboardTracklists.get(i).getAttribute("checked") != null) {
				CommonUtils.scrollDownTillElement(
						customiseDashboardTracklists.get(i), driver);
				customiseDashboardTracklists.get(i).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getNumberOfSavedSearchCheckboxesOnCustomizeCompanyDashboard() {
		return customiseDashboardSavedSearch.size();
	}

	public int getNumberOfTrackingListCheckboxesOnCustomizeCompanyDashboard() {
		return customiseDashboardTracklists.size();
	}

	public int getNumberOfCheckedSavedSearchCheckboxesOnCustomizeCompanyDashboard() {
		int checkedCount = 0;
		for (WebElement savedSearchCheckbox : customiseDashboardSavedSearch) {
			if (savedSearchCheckbox.isSelected())
				checkedCount++;
		}
		return checkedCount;
	}

	public int getNumberOfCheckedTrackingListCheckboxesOnCustomizeCompanyDashboard() {
		int checkedCount = 0;
		for (WebElement trackingListCheckbox : customiseDashboardTracklists) {
			if (trackingListCheckbox.isSelected())
				checkedCount++;
		}
		return checkedCount;
	}

	public void clickOnSaveFromCustomizeDashboard() {
		extentTest
				.log(Status.INFO, "Clicking on Save from Customize Dashboard");
		SeleniumUtils.scrollToView(driver, customiseDashboardPopUpSave);
		customiseDashboardPopUpSave.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(myAccountLink));

	}

	public void clickOnCancelFromCustomizeDashboard() {
		extentTest.log(Status.INFO,
				"Clicking on Cancel from Customize Dashboard");
		SeleniumUtils.scrollToView(driver, customiseDashboardPopUpCancel);
		customiseDashboardPopUpCancel.click();
	}

	public boolean checkControlOnHomePage(String homeTabActiveStr) {
		return homeTabIdentifier.getAttribute("class").toUpperCase().trim()
				.equals(homeTabActiveStr.toUpperCase().trim());
	}

	public boolean isSpecAlertsInDashboardHeadersDisplayed(String Name) {
		extentTest.log(Status.INFO,
				"To verify if spec alerts are present in the dashboard");
		for (WebElement webElement : dashboardHeaders) {
			if (webElement.getText().equalsIgnoreCase(Name))
				return true;
		}
		return false;
	}

	public void clickOnRecentLoginDrpDown() {
		extentTest.log(Status.INFO,
				"Clicking on the Recent Login Dropdown in the dashboard");
		recentLoginDrpDown.click();
	}

	public boolean isSpecAlertsTabSelected() {
		extentTest.log(Status.INFO, "Verify if spec alerts tab is selected");
		String hex = SeleniumUtils.getBackgroundColor(CommonSpecAlertsTab);
		if (hex.equalsIgnoreCase("#58b5cf"))
			return true;
		else
			return false;
	}

	public boolean isProjectsTabSelected() {
		extentTest
				.log(Status.INFO,
						"Verify graph and labels should be contained within the grey background.");
		String hex = SeleniumUtils.getBackgroundColor(CommonProjectsTab);
		if (hex.equalsIgnoreCase("#58b5cf"))
			return true;
		else
			return false;
	}

	public boolean isChart1BackgroundGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the entire graph and labels should be contained within the grey background Chart1.");
		String hex = SeleniumUtils.getBackgroundColor(ChartOneDashboard);
		if (hex.equalsIgnoreCase("#efefef"))
			return true;
		else
			return false;
	}

	public boolean isWashingtonMapBackgroundGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the Washington Map should be contained within the grey background.");
		String hex = SeleniumUtils
				.getBackgroundColor(mapChartProjectDensityWashington);
		if (hex.equalsIgnoreCase("#efefef"))
			return true;
		else
			return false;
	}

	public boolean isWashingtonMapBackgroundDarkGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the Washington Map should be contained within the dark grey background.");
		String style = mapChartProjectDensityWashington.getAttribute("style");
		String[] styleArr = style.split(";");
		for (String styleAttr : styleArr) {
			if (styleAttr.contains("fill")) {
				String[] fill = styleAttr.split(":");
				String[] numbers = fill[1].replace("rgb(", "").replace(")", "")
						.split(",");
				int number1 = Integer.parseInt(numbers[0].trim());
				numbers[1] = numbers[1].trim();
				int number2 = Integer.parseInt(numbers[1].trim());
				numbers[2] = numbers[2].trim();
				int number3 = Integer.parseInt(numbers[2].trim());
				String hex = String.format("#%02x%02x%02x", number1, number2,
						number3);
				if (hex.equalsIgnoreCase("#DDDDDD"))
					return true;
			}
		}
		return false;
	}

	public boolean isMontanaMapBackgroundGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the Montana Map should be contained within the grey background.");
		String hex = SeleniumUtils
				.getBackgroundColor(mapChartProjectDensityMontana);
		if (hex.equalsIgnoreCase("#efefef"))
			return true;
		else
			return false;
	}

	public boolean isTexasMapBackgroundGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the Texas Map should be contained within the grey background.");
		String hex = SeleniumUtils
				.getBackgroundColor(mapChartProjectDensityTexas);
		if (hex.equalsIgnoreCase("#efefef"))
			return true;
		else
			return false;
	}

	public boolean isChart2BackgroundGrey() {
		extentTest
				.log(Status.INFO,
						"Verify the entire graph and labels should be contained within the grey background Chart2.");
		String hex = SeleniumUtils.getBackgroundColor(ChartTwoDashboard);
		if (hex.equalsIgnoreCase("#efefef"))
			return true;
		else
			return false;
	}

	public boolean isLeadEmailDialogDisplayed() {
		extentTest.log(Status.INFO, "Verify if lead email dialog is displayed");
		try {
			return leadEmailDialog.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isLeadEmailDialogCheckboxDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if check box in the lead email dialog is displayed");
		return leadEmailDialogChkbox.isDisplayed();
	}

	public boolean isLeadEmailDialogTextAreaDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if text area in the lead email dialog is displayed");
		return leadEmailDialogTextArea.isDisplayed();
	}

	public boolean isLeadEmailDialogSaveDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if Save button in the lead email dialog is displayed");
		return leadEmailDialogSavebtn.isDisplayed();
	}

	public boolean isLeadEmailDialogCancelDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if Cancel button in the lead email dialog is displayed");
		return leadEmailDialogCancelbtn.isDisplayed();
	}

	public void clickOnCancelInLeadEmailDialog() {
		extentTest.log(Status.INFO,
				"Clicking on the cancel button in the lead email dialog");
		leadEmailDialogCancelbtn.click();
	}

	public void clickOnSaveInLeadEmailDialog() {
		extentTest.log(Status.INFO,
				"Clicking on the save button in the lead email dialog");
		leadEmailDialogSavebtn.click();
	}

	public void enterAddressinTextAreaInLeadEmailDialog(String Name) {
		extentTest
				.log(Status.INFO,
						"Enter email addresses in the textarea in the lead email dialog is displayed");
		leadEmailDialogTextArea.clear();
		leadEmailDialogTextArea.sendKeys(Name);
	}

	public Integer getEnteredTextLengthInLeadEmailDialog() {
		return leadEmailDialogTextArea.getAttribute("value").length();
	}

	public String getEnteredTextInLeadEmailDialog() {
		return leadEmailDialogTextArea.getAttribute("value").trim();
	}

	public List<String> getSelectedSpecProgramList() {
		int i = 0;
		List<String> programList = new ArrayList<String>();
		for (WebElement webElement : SpecProgramListInCustomizeDialog) {
			if (SpecProgramListCheckBoxInCustomizeDialog.get(i).isSelected()) {
				programList.add(webElement.getText());
			}
			i++;
		}
		return programList;
	}

	public void clickOnSaveInCustomiseSpecProgramsPopUp() {
		extentTest.log(Status.INFO, "Clicking on save from Customise Pop Up");
		customiseSpecAlertPopUpSave.click();
	}

	public void clickOnCancelInCustomiseSpecProgramsPopUp() {
		extentTest.log(Status.INFO, "Clicking on cancel from Customise Pop Up");
		customiseSpecAlertPopUpClose.click();
	}

	public String getCustomizeSpecPopUpHeader() {
		extentTest.log(Status.INFO, "Get Customise Spec Alert Pop Up Header");
		return customiseSpecAlertPopUpHeader.getText();
	}

	public String getCustomizeCompanyPopUpHeader() {
		extentTest.log(Status.INFO, "Get Customise Company Pop Up Header");
		return customizeCompanyDashboardTitle.getText();
	}

	public boolean isSessionExpiredDialogDisplayed() {
		extentTest
				.log(Status.INFO,
						"Check if Session alert message is displayed after inactivity in page");

		return expiredSessionMsg1.isDisplayed();
	}

	public void clickSessionExpiredPopUp() {
		extentTest.log(Status.INFO, "Click on Close Expire OK button");
		closeExpiredPopup.click();
	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO,
				"Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO,
				"Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public void uncheckLeadEmailDialogCheckbox() {
		extentTest.log(Status.INFO,
				"Uncheck the check box in the lead email dialog is displayed");
		if (leadEmailDialogChkbox.isSelected()) {
			leadEmailDialogChkbox.click();
		}
	}

	public void checkLeadEmailDialogCheckbox() {
		extentTest.log(Status.INFO,
				"Check the check box in the lead email dialog is displayed");
		if (!leadEmailDialogChkbox.isSelected()) {
			leadEmailDialogChkbox.click();
		}
	}

	public boolean isLeadEmailErrorDisplayed() {
		extentTest.log(Status.INFO, "Error in Lead email dialog displayed");
		return leadEmailDialogErrorMsg.isDisplayed();
	}

	public boolean verifyCommonTabHighlighted() {
		extentTest.log(Status.INFO,
				"Verify if the common header tabs highlighted");
		for (int i = 0; i < commonHeaderTabs.size(); i++) {
			if (commonHeaderTabs.get(i).getAttribute("class")
					.equals("inactive")) {
				return false;
			}
		}
		return true;

	}

	public int getsearchTxtField_length() {
		return searchTxtField.getAttribute("value").length();
	}

	public boolean isUSATodayHeaderDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the today column header displayed under USA section");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaTodayHeader));

			return usaTodayHeader.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSALastVisitHeaderDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' column header displayed under USA section");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaLastVist));

			return usaLastVist.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAAllProjectDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Projects in my license is displayed under USA section");
		try {
			return usaAllProject.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAAllProjectTodayCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Today' count displayed under USA section for All Projects in my license");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaAllProjectTodayCount));
			return usaAllProjectTodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAAllProjectLastVisitCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under USA section for All Project in my license");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaAllProjectLastVisitCount));

			return usaAllProjectLastVisitCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAVersion1Displayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the Version 1 report for displayed under USA section");
		try {
			return usaVersion1.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAVersion1TodayCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Today' count displayed under USA section for Version 1 report");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaVersion1TodayCount));

			return usaVersion1TodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isUSAVersion1LastVisitCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under USA section for version 1 report");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOf(usaVersion1LastVisitCount));

			return usaVersion1LastVisitCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public ProjectResultsPage clickUSAAllProjectTodayCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Today' count displayed under USA section for All Projects in my license");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(usaAllProjectTodayCount));
		usaAllProjectTodayCount.click();
		return new ProjectResultsPage(driver);

	}

	public ProjectResultsPage clickUSAAllProjectLastVisitCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Last Visit' count displayed under USA section for All Project in my license");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(usaAllProjectLastVisitCount));
		usaAllProjectLastVisitCount.click();
		return new ProjectResultsPage(driver);

	}

	public ProjectResultsPage clickUSAVersion1TodayCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Today' count displayed under USA section for Version 1 report");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(usaVersion1TodayCount));
		usaVersion1TodayCount.click();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickUSAVersion1LastVisitCount() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under USA section for version 1 report");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(usaVersion1LastVisitCount));
		usaVersion1LastVisitCount.click();
		return new ProjectResultsPage(driver);
	}

	public boolean isIntTodayHeaderDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the today column header displayed under International section");
		try {
			return intTodayHeader.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntLastVisitHeaderDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' column header displayed under International section");
		try {
			return intLastVist.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntAllProjectTodayCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Today' count displayed under International section for All Projects in my license");
		try {
			return intAllProjectTodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntAllProjectDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Project in my license is displayed under International section");
		try {
			return intAllProject.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntAllProjectLastVisitCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under International section for All Project in my license");
		try {
			return intAllProjectLastVisitCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntVersion1Displayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the Version 1 report displayed under International section ");
		try {
			return intVersion1TodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isIntVersion1TodayCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Today' count displayed under International section for Version 1 report");
		try {
			return intVersion1TodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean islblCompanySummaryDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the header Company Summary displayed");
		try {
			return lblCompanySummary.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean islblTodaySummaryDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the header Today Summary displayed");
		return !lblTodaySummHdr.isEmpty();
	}

	public boolean islblLastVisitSummaryDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the header Since Last Visit Summary displayed");
		return !lblLastVisitSummHdr.isEmpty();
	}

	public boolean isIntVersion1LastVisitCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under International section for version 1 report");
		try {
			return intVersion1LastVisitCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean islblAllCompaniesDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Companies label is being displayed under company summary");
		try {
			return lblAllCompanies.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCompanySummaryTodayCountDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Companies count is displayed under Today header in company summary");
		try {
			return companySummaryTodayCount.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCompanySummaryTodayCountClickable() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Companies count is displayed under Today header in company summary");
		try {
			return SeleniumUtils.isClickable(companySummaryTodayCount, driver);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCompanySummaryLastVisitCountClickable() {
		extentTest
				.log(Status.INFO,
						"Verify if the All Companies count is displayed under Last Visit header in company summary");
		try {
			return SeleniumUtils.isClickable(companySummaryLastVisitCount,
					driver);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCompanySavedSearchNameDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the Saved Search is displayed in company summary");
		try {
			return companySavedSearchName.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public CompanyResultsPage clickCompanySavedSearchName() {
		extentTest.log(Status.INFO, "Click the Company Saved Search displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(companySavedSearchName));
		companySavedSearchName.click();
		return new CompanyResultsPage(driver);

	}

	public boolean isCompanyTrackingListNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Tracking List is displayed");
		try {
			return companyTrackingListName.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public ProjectResultsPage clickIntAllProjectTodayCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Today' count displayed under International section for All Projects in my license");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(intAllProjectTodayCount));
		intAllProjectTodayCount.click();
		return new ProjectResultsPage(driver);

	}

	public ProjectResultsPage clickIntAllProjectLastVisitCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Last Visit' count displayed under International section for All Project in my license");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(intAllProjectLastVisitCount));
		intAllProjectLastVisitCount.click();
		return new ProjectResultsPage(driver);

	}

	public ProjectResultsPage clickIntVersion1TodayCount() {
		extentTest
				.log(Status.INFO,
						"Click the 'Today' count displayed under International section for Version 1 report");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(intVersion1TodayCount));
		intVersion1TodayCount.click();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickIntVersion1LastVisitCount() {
		extentTest
				.log(Status.INFO,
						"Verify if the 'Last Visit' count displayed under International section for version 1 report");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(intVersion1LastVisitCount));
		intVersion1LastVisitCount.click();
		return new ProjectResultsPage(driver);
	}

	public List<String> getHyperlinksOfConstructionSiteOnWhatsNewSection() {
		extentTest
				.log(Status.INFO,
						"Get hyperlink for Construction site links on Whats New section.");
		List<String> hyperlinksConstructionSiteLink = new ArrayList<>();
		for (WebElement hyperlinkConstructionSiteElement : allLinksOfConstructionSiteWhatsNewSection) {
			hyperlinksConstructionSiteLink.add(hyperlinkConstructionSiteElement
					.getAttribute("href"));
		}

		return hyperlinksConstructionSiteLink;
	}

	public boolean verifyHyperlinksOfConstructionSiteOnMyResourcesSection() {
		extentTest
				.log(Status.INFO,
						"Verify hyperlink for Construction site links My Resources section.");
		boolean isVerified = false;
		for (WebElement hyperlinkConstructionSiteElement : allLinksOfConstructionSiteMyResourcesSection) {
			if (!("Upload your plans, specifications and addenda now"
					.equalsIgnoreCase(hyperlinkConstructionSiteElement
							.getText())) && hyperlinkConstructionSiteElement.getAttribute("onclick") != null) {
					isVerified = hyperlinkConstructionSiteElement.getAttribute(
							"onclick").contains("www");
			}
			if (!isVerified) {
				break;
			}
		}
		return isVerified;
	}

	public boolean isHyperlinksOfConstructionSiteOnMyResourcesSectionDisplayed() {
		extentTest
				.log(Status.INFO,
						"Verify presence of hyperlink for Construction site links My Resources section.");
		return allLinksOfConstructionSiteMyResourcesSection.size() > 0;
	}

	public String getHyperlinksOfDownloadTakeOffLinkOnMyResourcesSection() {
		extentTest
				.log(Status.INFO,
						"Get hyperlink of Download Takeoff link on My Resources section.");
		return downloadTakeOffLink.getAttribute("href");
	}

	public String getSearchTextFieldColor() {
		extentTest.log(Status.INFO, "Get Search Textfield color.");
		String searchTextFieldStyleAttr = searchTxtField.getAttribute("style");
		String[] searchTextFieldStyleAttrSplit = searchTextFieldStyleAttr
				.split(";");
		for (String style : searchTextFieldStyleAttrSplit) {
			if (style.contains("color")) {
				return style.split(":")[1];
			}
		}
		return "";
	}

	public String getHomeSearchDropDownColor() {
		extentTest.log(Status.INFO, "Get Home Search drop-down color.");
		String homeSearchStyleAttr = HomeSearchDropdown.getAttribute("style");
		String[] homeSearchStyleAttrSplit = homeSearchStyleAttr.split(";");
		for (String style : homeSearchStyleAttrSplit) {
			if (style.contains("color")) {
				return style.split(":")[1];
			}
		}
		return "";
	}

	public List<String> getAllTrainingVideosLink() {
		extentTest.log(Status.INFO,
				"Get all links in the section Training Videos.");
		List<String> allTrainingVideoLinksList = new ArrayList<>();
		for (WebElement trainingVideoLink : trainingVideosLinks) {
			allTrainingVideoLinksList.add(trainingVideoLink.getText());
		}

		return allTrainingVideoLinksList;
	}

	public List<String> getAllAttendLiveTrainingLink() {
		extentTest.log(Status.INFO,
				"Get all links in the section Attend Live Training.");
		List<String> allAttendLiveTrainingLinksList = new ArrayList<>();
		for (WebElement attendLiveTrainingLink : attendLiveOnlineTrainingLinks) {
			allAttendLiveTrainingLinksList
					.add(attendLiveTrainingLink.getText());
		}

		return allAttendLiveTrainingLinksList;
	}

	public HomePage openHomePageInNewTab() {
		extentTest.log(Status.INFO,
				"Open Home page in new tab using the Dodge Logo.");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.CONTROL).click(dodgeLogo).keyUp(Keys.CONTROL)
				.build().perform();
		return new HomePage(driver);
	}

	public void switchTabs(String currentWindowHandle) {
		extentTest.log(Status.INFO, "Switch tab.");
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			if (!tab.equals(currentWindowHandle)) {
				driver.switchTo().defaultContent();
				driver.switchTo().window(tab);
				break;
			}
		}
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public ProjectResultsPage clickOnfirstSavedSearch() {
		extentTest.log(Status.INFO,
				"Click on first saved search from dashboard");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.elementToBeClickable(firstSavedSearch));
		firstSavedSearch.click();
		return new ProjectResultsPage(driver);
	}

	public boolean isShoppingCartLinkDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the shopping cart link is displayed");
		return lnkShoppingCart.isDisplayed();
	}

	public EcommercePage clickShoppingCartLink() {
		extentTest.log(Status.INFO,
				"Clicking the shopping cart link in the header");
		lnkShoppingCart.click();
		return new EcommercePage(driver);
	}

	public ProjectResultsPage GetProjectResultsPageObjects() {
		return new ProjectResultsPage(driver);
	}

	public boolean Is_searchBtn_Displayed() {
		extentTest.log(Status.INFO, "Verify if the search button is displayed");
		return searchBtn.isDisplayed();
	}

	// Return the keyword search error message quate.
	public String getKeywordSearchErrorMessage() {
		extentTest.log(Status.INFO,
				"Return the keyword search error message quate.");
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(keywordSearchErroMessageLocater));
		return keywordSearchErroMessageLocater.getText().trim();
	}

	public void selectProjectOrCompaniesFromDropdown(String value) {
		projCompDropdown.click();
		driver.findElement(
				By.xpath("//div[@id='HomeSearchDrawr']//div[text()='" + value
						+ "']")).click();
	}

	public List<String> getMyProjectsSavedSearchItemList() {
		extentTest
				.log(Status.INFO,
						"Getting My Projects Saved Search item list is present in the Dashboard Projects Tab");
		final List<String> myProjectsSavedSearchItemList = new ArrayList<String>();
		for (final WebElement savedSearchItem : myProjectsSavedSearch) {
			myProjectsSavedSearchItemList.add(savedSearchItem.getText());
		}
		return myProjectsSavedSearchItemList;
	}

	public List<String> getMyProjectsTrackingListItemList() {

		extentTest
				.log(Status.INFO,
						"Getting My Projects Tracking List item list is present in the Dashboard Projects Tab");
		final List<String> myProjectsTrackingListItemList = new ArrayList<String>();
		for (final WebElement savedSearchItem : myProjectsTrackingList) {
			myProjectsTrackingListItemList.add(savedSearchItem.getText());
		}
		return myProjectsTrackingListItemList;
	}

	public void selectProfileFromDropdown(String value) {
		clickOnMyAccountDropDown.click();
		clickOnMyAccountProfile.click();
	}

	public void selectUserFromDropdown(String value) {
		clickOnMyAccountDropDown.click();
		clickOnMyAccountUsers.click();
	}

	// Check Planroom logo is displayed on the DGN home page.
	public boolean isPlanroomLogoDisplayedAtHomePage() {
		extentTest.log(Status.INFO,
				"Check Planroom logo is displayed on the DGN home page.");
		return SeleniumUtils.isVisible(planroomLogo, driver);
	}

	public void clickOnSpecAlert() {
		extentTest.log(Status.INFO, "Click On Spec Alert Link");
		selectSpecAlert.click();
	}

	public void clickOnEditLink() {
		extentTest.log(Status.INFO, "Click On Edit Link");
		clickOnEditLink.click();
	}

	public void clickOnAddProfileSaveButton() {
		extentTest.log(Status.INFO, "Click On Add Profile Save button Link");
		clickOnAddProfileSaveButton.click();
	}

	public boolean isErrorMessageDisplay() {
		extentTest
				.log(Status.INFO, "Check wheather error message is displayed");
		return errorMessage.isDisplayed();
	}

	public void enterProfileName(String searchText) {
		extentTest.log(Status.INFO, "Enter valid profile name");
		SeleniumUtils.isClickable(profileNameTxtField, driver);
		profileNameTxtField.clear();
		profileNameTxtField.sendKeys(searchText);
	}

	public void enterDescription(String description) {
		extentTest.log(Status.INFO, "Enter valid Description");
		SeleniumUtils.isClickable(descriptionTxtField, driver);
		descriptionTxtField.clear();
		descriptionTxtField.sendKeys(description);
	}

	public void clickOnCancelbtn() {
		extentTest.log(Status.INFO, "click On Cacel Button");
		clickOnCacelButton.click();
	}

	public boolean liscenseNumberDisplay() {
		extentTest.log(Status.INFO,
				"check wheather Liscense number is Displayed On Page");
		return liscensNumberDisplay.isDisplayed();
	}

	public boolean verifyOnClickNewTabIsOpen() {
		extentTest.log(Status.INFO, "verify On Click New Tab Is Open.");
		List<String> browserTabs = new ArrayList<String>(
				driver.getWindowHandles());
		return browserTabs.size() > 1;
	}

	// Verify the string is present on the page.
	public boolean checkForStringOnPage(String expString) {
		extentTest
				.log(Status.INFO, "Verify the string is present on the page.");
		return driver.getPageSource().toUpperCase().trim()
				.contains(expString.toUpperCase().trim());
	}

	public boolean checkDodgeWelcomeMessage(String expString) {
		extentTest.log(Status.INFO, "Check welcome message contain .");
		return welcomeMessage.getText().toUpperCase().trim()
				.contains(expString.toUpperCase().trim());
	}

	public String getAccessPendingMessage() {
		return licenseSectionTextLocator.getText().trim();
	}

	public boolean verifyLegendToMyProjectsSavedSearchItem() {
		extentTest.log(Status.INFO,
				"Verifying lagend image to My Projects Saved Search items");
		boolean isVerified = true;
		for (final WebElement savedSearchItem : myProjectsSavedSearch) {
			isVerified = isVerified
					&& savedSearchItem.findElements(By.tagName("img"))
							.isEmpty();
		}
		return isVerified;
	}

	public boolean verifyLegendToMyProjectsTrackingListItem() {
		extentTest.log(Status.INFO,
				"Verifying lagend image to My Projects Tracking List items");
		boolean isVerified = true;
		for (final WebElement savedSearchItem : myProjectsTrackingList) {
			isVerified = isVerified
					&& savedSearchItem.findElements(By.tagName("img"))
							.isEmpty();
		}
		return isVerified;
	}

	public TrackingLists clickOnMyTrackingListContextMenu() {
		extentTest.log(Status.INFO, "Click On My Tracking Lists Menu Link");
		MyTrackingListsMenuLink.click();
		return new TrackingLists(driver);
	}

	public void getQASweetHomePage() {
		driver.get("http://qa1.sweets.construction.com/");
	}

	public void clickOnSpeccheckBox() {
		extentTest.log(Status.INFO, "click On Check Box");
		clickOnspecAlertsCheckBox.click();
	}

	public void clickOnSpecSaveBtn() {
		extentTest.log(Status.INFO, "click On save button");
		clickOnspecAlertssaveButton.click();
	}

	public boolean checkNewProfileDispaly() {
		extentTest.log(Status.INFO, "check weather new profile is display ");
		return newProfileDisplay.isDisplayed();
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
		extentTest.log(Status.INFO,
				"check weather Description text box is display ");
		return descriptionTextBox.isDisplayed();
	}

	public boolean checkPageTitleDisplay() {
		extentTest
				.log(Status.INFO,
						"check weather title of the page display without verb 'manage'");
		return pageTitleProfile.isDisplayed();
	}

	public boolean checkPageTitleDisplayUser() {
		extentTest
				.log(Status.INFO,
						"check weather title of the page display without verb 'manage'");
		return pageTitleUser.isDisplayed();
	}

	public boolean marketingProfilelabel() {
		extentTest
				.log(Status.INFO,
						"check weather label below project Profile is 'marketing profile'dispaly");
		return marketingProfile.isDisplayed();
	}

	public void bradCrumbOfEditSpecAlert() {
		extentTest.log(Status.INFO,
				"getting the breadcrumb of the spec alert profile edit page");
		breadCrumbDisplay.getText();
	}

	public void navigatetoInternationalTab() {
		extentTest.log(Status.INFO, "try to navigate to international tab ");
		String url = ConfigurationReader.getInstance().getProperty(
				"application.url");
		String urlInt = url + "International.aspx";
		driver.navigate().to(urlInt);

	}

	public boolean checkNewProfileAdded() {
		extentTest.log(Status.INFO,
				"check new profile is addede to specAlert Profile list");
		return profilenewaddedtc1080.isDisplayed();
	}

	public List<String> dashboardProgramDisplay() {
		extentTest.log(Status.INFO, "Get list of programm on DashBoard.");
		List<String> dashboardProgramm = new ArrayList<String>();
		for (WebElement specAlert : listOfProgramm) {
			dashboardProgramm.add(specAlert.getText());
		}
		return dashboardProgramm;
	}

	public List<String> specAlertProgrammDisplay() {
		extentTest.log(Status.INFO,
				"Get list of spec alert programm on dashBoard");
		List<String> specalertProgramm = new ArrayList<String>();
		for (WebElement specAlert : specList) {
			specalertProgramm.add(specAlert.getText());
		}
		return specalertProgramm;
	}

	public RegisterForUnderstandingTheHomeTabDashboardPage clickOnDodgeGlobalNetworkCrashCourseLink() {
		extentTest
				.log(Status.INFO,
						"Navigate to Register for Understanding the Home tab & Dashboard page.");
		dodgeGlobalNetworkCrashCourseLink.click();
		return new RegisterForUnderstandingTheHomeTabDashboardPage(driver);
	}

}

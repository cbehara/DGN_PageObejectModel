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
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String selectedProject;

	@FindBy(how = How.ID, using = "lnkAddnotes")
	private WebElement addnotesMenu;

	@FindBy(how = How.ID, using = "Company-Results")
	private WebElement companyResultsBreadCrumb;

	@FindBy(how = How.ID, using = "Company")
	private WebElement companyBreadCrumb;

	@FindBy(how = How.LINK_TEXT, using = "View this Dodge Report")
	private WebElement viewThisDodgeReportLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "project-check-all")
	private WebElement selectAllSpecTitle;

	@FindBy(how = How.ID, using = "project_check_all_title")
	private WebElement selectAllTitle;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkFirms")
	private WebElement firmsTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkReportSpan")
	private WebElement reportTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkBidders")
	private WebElement biddersTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_spnBidderCount")
	private WebElement biddersIndicatorCount;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvBidderCount']/span[contains(@class,'circle')]")
	private WebElement biddersIndicatorCircle;

	@FindBy(how = How.XPATH, using = "//div[@class='international-image-none']")
	private WebElement USABackgroundImage;

	@FindBy(how = How.XPATH, using = "//div[@class='international-image-display']")
	private WebElement InternationalBackgroundImage;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvBidderCount']/span[contains(@class,'circle')]")
	private WebElement planholdersIndicatorCircle;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvBidderCount']//span[contains(@class,'planHoldertext')]")
	private WebElement biddersIndicatorText;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvPlanholderCount']//span[contains(@class,'planHoldertext')]")
	private WebElement planholdersIndicatorText;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td//table//td[contains(.,'Bid date')]//following-sibling::td[2]")
	private WebElement bidDate;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td//table//td[contains(.,'Target start date:')]//following-sibling::td[2]")
	private WebElement targetStartDate;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td//table//td[contains(.,'Target complete date:')]//following-sibling::td[2]")
	private WebElement targetCompleteDate;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td//table//td[contains(.,'Valuation')]//following-sibling::td[2]")
	private WebElement valuation;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_dvBidderContent")
	private WebElement biddersContentPanel;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_spnPlanholderCount")
	private WebElement planholdersIndicatorCount;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td[@id='tdMapIcon']//following-sibling::td//p")
	private WebElement projectLocation;

	@FindBy(how = How.LINK_TEXT, using = "Plans")
	private WebElement plansTab;

	@FindBy(how = How.LINK_TEXT, using = "Specs")
	private WebElement specsTab;

	@FindBy(how = How.LINK_TEXT, using = "Print Project Details")
	private WebElement printProjectDetailsLink;

	@FindBy(how = How.LINK_TEXT, using = "Order Hard Copy Documents")
	private WebElement orderHardCopyDocumentsLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkAddendaSpan")
	private WebElement addendaTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblProjectTitle")
	private WebElement prjTitle;

	@FindBy(how = How.LINK_TEXT, using = "Addenda")
	private WebElement addendaTabLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkDesignAlerts")
	private WebElement designAlertsTab;

	@FindBy(how = How.ID, using = "ctl01_SpecListLabel")
	private WebElement specAlertsLabel;

	@FindBy(how = How.ID, using = "ctl01_TrackingListLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'contentPlaceHolderHeader_rptNavigator_lnkNotes')]")
	private WebElement notesTab;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl01_TrackingListContainer']/a")
	private List<WebElement> trackCompanyList;

	@FindBy(how = How.CLASS_NAME, using = "TrackedToolTip")
	private WebElement trackCompanyIcon;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//td[contains(text(),'Valuation')]//following-sibling::td//a[contains(@class,'maincontentBlueUnderline')]")
	private WebElement valuationTxt;

	@FindBy(how = How.XPATH, using = ".//*[@id='Table4']/tbody/tr[1]/td[2]")
	private WebElement valuationTooltipTitle;

	@FindBy(how = How.XPATH, using = ".//*[@id='dhtmltooltip']//a")
	private List<WebElement> linksInValuationToolTip;

	@FindBy(how = How.CLASS_NAME, using = "specAlertOnSummary")
	private List<WebElement> specAlertsList;

	@FindBy(how = How.XPATH, using = "(//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//h2[not(img)])[2]")
	private WebElement projectDRNumber;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//h2[not(img)]")
	private WebElement projectDRNumberOnly;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//h2[not(img)]")
	private WebElement projectCrumbDRNumber;

	/*@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_dvBreadCrumbCon")
	private WebElement breadCrumb;*/

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']/div[1]/div[1]/table/tbody/tr[1]/td/h2/span")
	private WebElement projectTitle;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']/div[1]/div[1]/table/tbody/tr[2]/td/table[2]/tbody/tr/td")
	private WebElement projectSubTitle;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'First published date:')]/following-sibling::span")
	private WebElement firstPublishDate;

	@FindBy(how = How.XPATH, using = "//td[contains(.,'Engineer:')]")
	private WebElement engineerInfoSection;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_lnkProjects")
	private WebElement projectsTab;

	@FindBy(how = How.ID, using = ".//*[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoProjectCountryCon']/div[1]/span[2]")
	private WebElement ProjectLocationDropdown;

	@FindBy(how = How.ID, using = "//ul[@class='proj-Section-Action-List']//li[not(@class) and not(@style) and not(contains(@id,'liTryStack'))]")
	private List<WebElement> actionsOptions;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	private WebElement downloadProjectsMenu;

	// Project Location
	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkFirms")
	private WebElement clickOnFirmTab;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	private WebElement downloadProjectActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.ID, using = "downloadCompaniesPopup")
	private WebElement downloadProjectPopUp;

	@FindBy(how = How.XPATH, using = "//img[@src='images/map_icon.png']")
	private WebElement mapIconLink;

	@FindBy(how = How.ID, using = "googleMap")
	private WebElement mapSection;

	public ProjectPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Page");
	}

	public ProjectPage(WebDriver driver, String selectedProject) {
		super(driver);
		this.driver = driver;
		this.selectedProject = selectedProject;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Page");

	}

	public List<String> getSelectedProjectTitle() {
		List<String> selectedProjectsList = new ArrayList<>();
		selectedProjectsList.add(this.selectedProject);
		return selectedProjectsList;
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	public String getProjectTitle() {
		return projectTitle.getText();
	}

	public String getProjectSubTitle() {
		return projectSubTitle.getText();
	}

	public String getCurrentURl() {
		return driver.getCurrentUrl();
	}

	@Override
	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions dropdown");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(actionsDropdowm));
		actionsDropdowm.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printProjectDetailsLink));
	}

	public void clickOnActionsDropDownForOutOfSubscription() {
		extentTest.log(Status.INFO, "Click on Actions dropdown");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(actionsDropdowm));
		actionsDropdowm.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(orderHardCopyDocumentsLink));
	}

	public EcommercePage clickOrderHardCopyDocuments() {
		extentTest.log(Status.INFO,
				"Clicking the Order Hard Copy Documents link under actions drop down for out of subscription projects");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(prjTitle));
		String name = prjTitle.getText();
		orderHardCopyDocumentsLink.click();
		return new EcommercePage(driver, name);
	}

	@Override
	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public PrintProjectDetailsPage mouseOverActionAndClickProjectDetailsLink() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Click Project Details Link under Actions menu");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printProjectDetailsLink));
		printProjectDetailsLink.click();
		return new PrintProjectDetailsPage(driver);
	}

	public TrackPopUpPage mouseOverActionandClickTrackProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Click tracking projects under Actions menu");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		return clickOnTrackProjects();
	}

	@Override
	public NotesPage clickOnAddNotesMenu() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addnotesMenu));
		extentTest.log(Status.INFO, "Click on add notes dropdown menu");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(addnotesMenu));
		addnotesMenu.click();
		return new NotesPage(driver);
	}

	@Override
	public boolean isNotesTabDisabled() {
		extentTest.log(Status.INFO, "Check if Notes tab is Disabled");
		return notesTab.getAttribute("class").equals("disable");
	}

	@Override
	public Boolean mouseoverActionandCheckAddNotesEnabled() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "Check if AddNotes Menu is Enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addnotesMenu));
		return addnotesMenu.isEnabled();
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
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		return CommonUtils.getListFromWebElements(trackCompanyList).containsAll(TrackName);
	}

	public ProjectResultsPage clickTrackNameInExistingTrackingList() {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		for (int i = 0; i < trackCompanyList.size(); i++) {
			trackCompanyList.get(i).click();
			break;
		}
		return new ProjectResultsPage(driver);
	}

	public boolean isTrackingIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Tracking Icon displayed");
		return trackCompanyIcon.isDisplayed();
	}

	public boolean isNoBackgroundImageDisplayed() {
		extentTest.log(Status.INFO, "Verify No Background Image is displayed");
		return USABackgroundImage.isDisplayed();
	}

	public boolean isInternationalBackgroundImageDisplayed() {
		extentTest.log(Status.INFO, "Verify International Background Image is displayed");
		return InternationalBackgroundImage.isDisplayed();
	}

	@Override
	public boolean isNotesTabClickable() {
		extentTest.log(Status.INFO, "Check if Notes tab is Clickable");
		return SeleniumUtils.isClickable(notesTab, driver);
	}

	@Override
	public boolean isFirmsTabClickable() {
		extentTest.log(Status.INFO, "Check if Firms tab is Clickable");
		return SeleniumUtils.isClickable(firmsTab, driver);
	}

	public boolean isViewThisDodgeReportLinkPresent() {
		extentTest.log(Status.INFO, "Check if View this Dodge report Link is present");
		return SeleniumUtils.isClickable(viewThisDodgeReportLink, driver);
	}

	@Override
	public boolean isBiddersTabClickable() {
		extentTest.log(Status.INFO, "Check if Bidders tab is Clickable");
		return SeleniumUtils.isClickable(biddersTab, driver);
	}

	@Override
	public boolean isPlansTabClickable() {
		extentTest.log(Status.INFO, "Check if Plans tab is Clickable");
		return SeleniumUtils.isClickable(plansTab, driver);
	}

	@Override
	public boolean isSpecsTabClickable() {
		extentTest.log(Status.INFO, "Check if Specs tab is Clickable");
		return SeleniumUtils.isClickable(specsTab, driver);
	}

	@Override
	public boolean isAddendaTabClickable() {
		extentTest.log(Status.INFO, "Check if Addenda tab is Clickable");
		return SeleniumUtils.isClickable(addendaTab, driver);
	}

	@Override
	public boolean isDesignAlertsTabClickable() {
		extentTest.log(Status.INFO, "Check if Design Alerts tab is Clickable");
		return SeleniumUtils.isClickable(designAlertsTab, driver);
	}

	@Override
	public boolean isProjectTabClickable() {
		extentTest.log(Status.INFO, "Check if Project tab is Clickable");
		return SeleniumUtils.isClickable(reportTab, driver);
	}

	@Override
	public ProjectPage clickOnProjectTab() {
		extentTest.log(Status.INFO, "Click on Project Tab");
		if (isProjectTabClickable()) {
			firmsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Project Tab as it is not enabled for this project");
		}
		return new ProjectPage(driver);
	}

	@Override
	public ProjectFirmsPage clickOnFirmsTab() {
		extentTest.log(Status.INFO, "Click on firm Tab");
		if (isFirmsTabClickable()) {
			firmsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Firms Tab as it is not enabled for this project");
		}
		return new ProjectFirmsPage(driver);
	}

	@Override
	public ProjectNotesPage clickOnNotesTab() {
		extentTest.log(Status.INFO, "Click on Notes Tab");
		if (isNotesTabClickable()) {
			notesTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Notes tab as it is not enabled for this project");
		}
		return new ProjectNotesPage(driver);
	}

	@Override
	public ProjectBiddersPage clickOnBiddersTab() {
		extentTest.log(Status.INFO, "Click on Bidders Tab");
		if (isBiddersTabClickable()) {
			biddersTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Bidders tab as it is not enabled for this project");
		}
		return new ProjectBiddersPage(driver);
	}

	@Override
	public ProjectPlansPage clickOnPlansTab() {
		extentTest.log(Status.INFO, "Click on Plans Tab");
		if (isPlansTabClickable()) {
			plansTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Plans tab as it is not enabled for this project");
		}
		return new ProjectPlansPage(driver);
	}

	@Override
	public ProjectSpecsPage clickOnSpecsTab() {
		extentTest.log(Status.INFO, "Click on Specs Tab");
		if (isSpecsTabClickable()) {
			specsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Specs tab as it is not enabled for this project");
		}
		return new ProjectSpecsPage(driver);
	}

	@Override
	public ProjectDesignAlertsPage clickOnDesignAlertsTab() {
		extentTest.log(Status.INFO, "Click on Design Alerts Tab");
		if (isDesignAlertsTabClickable()) {
			designAlertsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Design Alerts tab as it is not enabled for this project");
		}
		return new ProjectDesignAlertsPage(driver);
	}

	public void mouseOverValuationTxtField() {
		extentTest.log(Status.INFO, "Mouse Over valuation Text Field");
		Actions action = new Actions(driver);
		action.moveToElement(valuationTxt).perform();
	}

	public String valuationToolTipTitle() throws InterruptedException {
		extentTest.log(Status.INFO, "Verify Valuation TooltipText");
		return valuationTooltipTitle.getText();

	}

	public int countofLinksOnValuationtooltip() {
		extentTest.log(Status.INFO, "Check the count of hyperlinks on Valuation ToolTip");
		return linksInValuationToolTip.size();

	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the trackign list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public ProjectSpecsPage clickOnAnyFromSpecAlertsList() {
		extentTest.log(Status.INFO, "Clicking on any SpecAlerts from the SpecAlerts List");
		for (int i = 0; i < specAlertsList.size(); i++) {
			specAlertsList.get(i).click();
			break;
		}
		return new ProjectSpecsPage(driver);
	}

	public String mouseOverSpecALertsListAndGetTooltip() {
		Actions action = new Actions(driver);
		action.clickAndHold(specAlertsList.get(0)).build().perform();
		extentTest.log(Status.INFO, "Check if Track Projects is displayed");
		return specAlertsList.get(0).getAttribute("original-title");
	}

	public String getProjectCrumbDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the Projects Tab");
		CommonUtils.scrollDownTillElement(projectCrumbDRNumber, driver);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(projectCrumbDRNumber));
		return projectCrumbDRNumber.getText().trim().split(" ")[0];
	}

	public String getProjectDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the Projects Tab");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(projectDRNumber));
		return projectDRNumber.getText().trim().split(" ")[0];
	}

	// Get the DR Number numeric value on Project Page.
	public String getDRNumerOnly() {
		extentTest.log(Status.INFO, "Get the DR Number numeric value on Project Page.");
		return projectDRNumber.getText().trim().split(" ")[0];
	}

	// compare bid date on Project Page.
	public boolean compareBidDate(String dateToCheck) {
		extentTest.log(Status.INFO, "Get the Target start date on Project Page.");
		return bidDate.getText().trim().contains(dateToCheck);
	}

	// Get the Target start date on Project Page.
	public String getTargetStartDate() {
		extentTest.log(Status.INFO, "Get the Target start date on Project Page.");
		return targetStartDate.getText().trim();
	}

	// Get the Target completion date on Project Page.
	public String getTargetCompletionDate() {
		extentTest.log(Status.INFO, "Get the Target completion date on Project Page.");
		return targetCompleteDate.getText().trim();
	}

	/*public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText();
	}*/

	public CompanyResultsPage clickOnCompanyResultsBreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on Company Results BreadCrumb");
		companyResultsBreadCrumb.click();
		return new CompanyResultsPage(driver);
	}

	public CompanyPage clickOnCompanyBreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on Company BreadCrumb");
		companyBreadCrumb.click();
		return new CompanyPage(driver);
	}

	public Boolean isSpecAlertsListDisplayed() {
		extentTest.log(Status.INFO, "Verify if the specalerts displayed");
		return specAlertsList.size() > 0;
	}

	public boolean isBiddersIndicatorCountDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Low Bidders' indicator circle displayed with count");
		try {
			SeleniumUtils.scrollToView(driver, biddersContentPanel);
			return biddersIndicatorCount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isOrderHardCopyDocumentsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Order Hard Copy Documents displayed under actions dropdown");
		try {
			return orderHardCopyDocumentsLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public ProjectBiddersPage clickBiddersIndicatorCount() {
		extentTest.log(Status.INFO, "Click the 'Low Bidders' indicator count");
		biddersIndicatorCount.click();
		return new ProjectBiddersPage(driver);
	}

	public Integer getBiddersIndicatorCount() {
		extentTest.log(Status.INFO, "Get the 'Low Bidders' indicator count");
		return Integer.parseInt(biddersIndicatorCount.getText());
	}

	public boolean isPlanholdersIndicatorCountDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Planholders' indicator circle displayed with count");
		try {
			SeleniumUtils.scrollToView(driver, biddersContentPanel);
			return planholdersIndicatorCount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public ProjectBiddersPage clickPlanholdersIndicatorCount() {
		extentTest.log(Status.INFO, "Click the 'Planholders' indicator count");
		planholdersIndicatorCount.click();
		return new ProjectBiddersPage(driver);
	}

	public Integer getPlanholdersIndicatorCount() {
		extentTest.log(Status.INFO, "Get the 'Planholders' indicator count");
		return Integer.parseInt(planholdersIndicatorCount.getText());
	}

	public boolean isBidderIndicatorCircleBlue() {
		extentTest.log(Status.INFO, "Verify if 'Low Bidders' indicator circle is blue");
		SeleniumUtils.scrollToView(driver, biddersContentPanel);
		return SeleniumUtils.getBackgroundColor(biddersIndicatorCircle).equals("#2c76ab");
	}

	public boolean isPlanholdersIndicatorCircleBlue() {
		extentTest.log(Status.INFO, "Verify if 'Planholders' indicator circle is blue");
		return SeleniumUtils.getBackgroundColor(biddersIndicatorCircle).equals("#2c76ab");
	}

	public boolean isBidderIndicatorLinkUnderlinedBlack() {
		extentTest.log(Status.INFO, "Verify if 'Low Bidders' indicator text is underlined in black");
		SeleniumUtils.scrollToView(driver, biddersContentPanel);
		if (SeleniumUtils.getBackgroundColor(biddersIndicatorText).equals("#000000")
				&& biddersIndicatorText.getCssValue("text-decoration").toString().contains("underline")) {
			return true;
		}
		return false;
	}

	public boolean isPlanholdersIndicatorLinkUnderlinedBlack() {
		extentTest.log(Status.INFO, "Verify if 'Planholders' indicator text is underlined in black");
		SeleniumUtils.scrollToView(driver, biddersContentPanel);
		if (SeleniumUtils.getBackgroundColor(planholdersIndicatorText).equals("#000000")
				&& planholdersIndicatorText.getCssValue("text-decoration").toString().contains("underline")) {
			return true;
		}
		return false;
	}

	public Boolean checkforErrorMessage(String message) {
		extentTest.log(Status.INFO, "Verify if error message" + message + "is displayed");
		return driver.getPageSource().toLowerCase().contains(message.toLowerCase());
	}

	public Boolean isLocationInParenthesis() {
		extentTest.log(Status.INFO, "Verify if the location is in parenthesis");
		String location = projectLocation.getText();
		location = location.substring(location.lastIndexOf("("), location.length());
		return CommonUtils.isWordPatterninBraces(location);
	}

	public boolean isBidDateDisplayed() {
		extentTest.log(Status.INFO, "Verify if the bid date is displayed");
		return bidDate.isDisplayed();
	}

	public boolean isValuationDisplayed() {
		extentTest.log(Status.INFO, "Verify if the valuation is displayed");
		return valuation.isDisplayed();
	}

	public void clickSelectAllTitle() {
		extentTest.log(Status.INFO, "Clicking the Select All Title on the project page");
		selectAllTitle.click();
	}

	// return the text of valuation code
	public String getValuationCode() {
		extentTest.log(Status.INFO, "Return the text of valuation code");
		return valuation.getText();
	}

	public ProjectResultsPage clickOnProjectsTab() {
		extentTest.log(Status.INFO, "Click on Projects Tab.");
		projectsTab.click();
		return new ProjectResultsPage(driver);
	}

	public boolean verifyActionDropDownOptionsOutOfSubscription() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down for Out of subscription");
		boolean isMatch = false;
		int i = 1;
		for (DGNEnum.ActionsDrpDwnOptionsOutOfSubscription opt : DGNEnum.ActionsDrpDwnOptionsOutOfSubscription
				.values()) {
			if (actionsOptions.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public boolean verifyActionDropDownOptions() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.ActionsDrpDwnOptions opt : DGNEnum.ActionsDrpDwnOptions.values()) {
			if (actionsOptions.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public void switchTab(String windowHandle) {
		extentTest.log(Status.INFO, "Switch tab.");
		driver.switchTo().defaultContent();
		driver.switchTo().window(windowHandle);
	}

	public boolean verifyActionDropDownOptionsContainsMoreOnActionStage() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.ActionsDrpDwnOptions opt : DGNEnum.ActionsDrpDwnOptions.values()) {
			if (actionsOptions.size() > 0 && actionsOptions.get(i).getText().equals("More on Action Stage")) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	// Compare the project DR number with expected DR Number.
	public boolean compareDRNumber(String expDRNumber) {
		extentTest.log(Status.INFO, "Compare the project DR number with expected DR Number.");
		return getDRNumerOnly().trim().equals(expDRNumber.trim());
	}

	@Override
	public DownloadProjects mouseOverActionandClickDownloadProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		downloadProjectsMenu.click();
		return new DownloadProjects(driver);
	}

	public String getFirstPublishedDate() {
		extentTest.log(Status.INFO, "Get First Published Date");
		return firstPublishDate.getText().trim();
	}

	public void clickOnFirmOnProjectPage() {
		extentTest.log(Status.INFO, "Click On Firm Tab");
		clickOnFirmTab.click();
	}

	public Boolean mouseOverActionandCheckDownloadProjectDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Download Project is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectActionMenu));
		return downloadProjectActionMenu.isDisplayed();
	}

	public boolean isEngineeringInfoSectionDisplayed() {
		extentTest.log(Status.INFO, "Check if engineering info section is displayed");
		try {
			return engineerInfoSection.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean isPopUpDownloadProjectDisplayed() {
		extentTest.log(Status.INFO, "Download Project is displayed");
		return downloadProjectPopUp.isDisplayed();
	}

	// Check map icon displayed on the company result page.
	public boolean isMapIconLinkDisplayed() {
		extentTest.log(Status.INFO, "Check map icon displayed on the company result page.");
		return CommonUtils.checkElementExist(mapIconLink, driver);
	}

	public void clickMapIcon() {
		extentTest.log(Status.INFO, "Click the map icon");
		mapIconLink.click();
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
}

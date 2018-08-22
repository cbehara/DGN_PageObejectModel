package com.ddaqe.pages;

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

public class ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//a[not(@class)]")
	private List<WebElement> specNumberMatched;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "lnkHideProjects")
	private WebElement hideProjectsMenu;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement downloadFirmsMenu;

	@FindBy(how = How.ID, using = "lnkDownloadDocuments")
	private WebElement downloadDocumentsMenu;

	@FindBy(how = How.ID, using = "lnkUnHideProjects")
	private WebElement unhideProjectsMenu;

	@FindBy(how = How.ID, using = "lnkAddnotes")
	private WebElement addnotesMenu;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	private WebElement downloadProjectsMenu;

	@FindBy(how = How.ID, using = "lblErr")
	private WebElement lblErr;

	@FindBy(how = How.LINK_TEXT, using = "Download eTakeoff file")
	private WebElement downloadetakeOffMenu;

	@FindBy(how = How.LINK_TEXT, using = "Install eTakeoff")
	private WebElement installetakeoffMenu;

	@FindBy(how = How.LINK_TEXT, using = "Print Project Details")
	private WebElement printProjectDetailsLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Product List")
	private WebElement printProductListLink;

	@FindBy(how = How.LINK_TEXT, using = "Add Online Project")
	private WebElement addOnlineProject;

	@FindBy(how = How.LINK_TEXT, using = "Email Project")
	private WebElement emailProjectsMenu;

	@FindBy(how = How.LINK_TEXT, using = "Firms")
	private WebElement firmsTab;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;

	@FindBy(how = How.XPATH, using = "//div[@class='productDesign']")
	private WebElement designAlertProductInfo;

	@FindBy(how = How.XPATH, using = "//div[@class='DesignAlertDisclaimer']")
	private WebElement designAlertDisclaimer;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkReportSpan")
	private WebElement reportTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkPlansSpan")
	private WebElement planTabLabel;
	
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkSpecsSpan")
	private WebElement specsTabLabel;
	
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkAddendaSpan")
	private WebElement addendaTabLabel;

	@FindBy(how = How.LINK_TEXT, using = "Planholders/Bidders")
	private WebElement biddersTab;

	@FindBy(how = How.LINK_TEXT, using = "Plans")
	private WebElement plansTab;

	@FindBy(how = How.LINK_TEXT, using = "Specs")
	private WebElement specsTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkAddenda")
	private WebElement addendaTab;

	@FindBy(how = How.LINK_TEXT, using = "DesignAlerts")
	private WebElement designAlertsTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPager_lnkPrevious")
	private WebElement previousLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPager_lblNext")
	private WebElement nextLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPager_lblPageNo")
	private WebElement pageNoLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPager_lblPageNoSplitter")
	private WebElement pageNoSplitterlbl;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPager_lblTotalPageCount")
	private WebElement totalPageCountLink;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'contentPlaceHolderHeader_rptNavigator_lnkNotes')]")
	private WebElement notesTab;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvBreadCrumbCon']//a")
	private WebElement specAlertBreadCrumb;

	@FindBy(how = How.ID, using = "Project")
	private WebElement breadCrumbProjectLink;
	
	@FindBy(how = How.ID, using = "Project-Results")
	private WebElement breadCrumbProjectResultPage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_rptNavigator_PnlDynamicLinkButton']//*[@class='active']")
	private WebElement highlightedTabInProjectReport;

	@FindBy(how = How.ID, using = "lnkViewCompanies")
	private WebElement viewCompaniesMenu;

	@FindBy(how = How.LINK_TEXT, using = "Map Project")
	private WebElement mapProjectsLink;

	@FindBy(how = How.XPATH, using = "//input[@type ='checkbox']")
	private List<WebElement> checkboxlist;

	@FindBy(how = How.XPATH, using = ".//*[@id='prev-next-nav']")
	private List<WebElement> paginationSection;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'searchText')]")
	private WebElement searchTxtField;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'keywordSearch_mySearchSpan')]")
	private WebElement mySearchesDropDown;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'keywordSearch_btnSearch')]")
	private WebElement searchBtn;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Document")
	private WebElement downloadDocumentLinkAtTop;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'DynamicLinkButton')]//*[@class='active']")
	private WebElement selectedTab;
	
	@FindBy(how = How.LINK_TEXT, using = "Project Results")
	private WebElement projectResultLink;

	// Syn with Planroom
	@FindBy(how = How.XPATH, using = "//a[@class='syncPlanroom']")
	private WebElement btnSynWithPlanroom;

	@FindBy(how = How.ID, using = "planRoomSyncButtonWrapper")
	private WebElement btnSynWithPlanroomByID;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisible;
	
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_dvBreadCrumbCon")
	private WebElement breadCrumb;
	
	@FindBy(how = How.CSS, using ="[id*='ctl00_contentPlaceHolderHeader_rptSpecHead']")
	private List<WebElement> specNumberLinkList;
	
	@FindBy(how = How.XPATH, using ="//*[contains(@id,'contentPlaceHolderHeader_dvSwitch')]//span[@class='Off spanOff']")
	private WebElement onlyShowOFFToggleBtn;
	
	@FindBy(how = How.XPATH, using ="//*[contains(@id,'contentPlaceHolderHeader_dvSwitch')]//span[@class='On spanOn']")
	private WebElement onlyShowONToggleBtn;

	public ProjectCommonContainerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public Boolean isDownloadDocumentLinkAttopDisplayed() {

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(downloadDocumentLinkAtTop));
		return downloadDocumentLinkAtTop.isDisplayed();
	}

	public void selectCheckboxes(int count) throws InterruptedException {
		extentTest.log(Status.INFO, "Select checkboxes from the list");
		for (int i = 0; i < count; i++){
			//checkboxlist.get(i + 1).click();
			CommonUtils.clickOnElementUsingJavascript(checkboxlist.get(i + 1), driver);
			Thread.sleep(1000);
		}
	}

	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions dropdown");
		actionsDropdowm.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
	}

	public String getTitle() {
		extentTest.log(Status.INFO, "getTitle of Project Page");
		return driver.getTitle();
	}

	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public NotesPage clickOnAddNotesMenu() {
		extentTest.log(Status.INFO, "Click on add notes dropdown menu");
		addnotesMenu.click();
		return new NotesPage(driver);
	}

	public String getDesignAlertProductInfo() {
		extentTest.log(Status.INFO, "get designAlert Product Info");
		String message = designAlertProductInfo.getText();
		return message;
	}

	public boolean isNotesTabDisabled() {
		extentTest.log(Status.INFO, "Check if Notes tab is Disabled");
		return notesTab.getAttribute("class").equals("disable");
	}

	public boolean isPreviousLinkDisabled() {
		extentTest.log(Status.INFO, "Check if Previous link is Disabled");
		return previousLink.getAttribute("disabled").equals("true");
	}

	public boolean isNextLinkDisabled() {
		extentTest.log(Status.INFO, "Check if Next link is Disabled");
		try {
			return nextLink.getAttribute("disabled").equals("true");
		} catch (Exception ex) {
			return false;
		}
	}

	public Boolean mouseoverActionandCheckAddNotesEnabled() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "Check if AddNotes Menu is Enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addnotesMenu));
		return addnotesMenu.isEnabled();
	}

	public Boolean mouseoverActionandCheckInstallEtakeOffDisplayed() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "Check if Install etake Off Menu is Enabled");
		SeleniumUtils.isVisible(installetakeoffMenu, driver);
		return installetakeoffMenu.isDisplayed();
	}

	public Boolean mouseoverActionandCheckDownloadProjectDisplayed() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "Check if Install etake Off Menu is Enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		return downloadProjectsMenu.isDisplayed();
	}

	public DownloadProjects mouseOverActionandClickDownloadProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		downloadProjectsMenu.click();

		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);

		return new DownloadProjects(driver);
	}

	public void mouseoverActionandClickInstallEtakeOff() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "click Install etake Off Menu ");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(installetakeoffMenu));

		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens new window
		installetakeoffMenu.click();
		Thread.sleep(2000);

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	public Boolean mouseoverActionandCheckDownloadTakeOffFileDisplayed() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdowm).perform();
		extentTest.log(Status.INFO, "Check if Download take off Menu is Enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadetakeOffMenu));
		return downloadetakeOffMenu.isDisplayed();
	}

	public boolean isPreviousLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if Previous link is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(previousLink));
		try {
			return previousLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isNextLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if Next link is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nextLink));
		try {
			return nextLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isPageNoDisplayed() {
		extentTest.log(Status.INFO, "Verify if Page No is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pageNoLink));
		try {

			return pageNoLink.isDisplayed();

		} catch (Exception e) {
			return false;
		}

	}

	public boolean isPageNoSplitterDisplayed() {
		extentTest.log(Status.INFO, "Verify if Page No Splitter (i.e. of ) is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pageNoSplitterlbl));
		return pageNoSplitterlbl.isDisplayed();
	}

	public boolean isTotalPageCountDisplayed() {
		extentTest.log(Status.INFO, "Verify if Total Page Count is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(totalPageCountLink));
		return totalPageCountLink.isDisplayed();
	}

	public boolean isProjectTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Projects tab is displayed");
		try {
			return reportTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFirmsTabDisplayed() {
		extentTest.log(Status.INFO, "Verify Firms tab is displayed");
		try {
			return firmsTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isBiddersTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Planholders/Bidders tab is displayed");
		try {
			return biddersTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isPlansTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Plans tab is displayed");
		try {
			return plansTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSpecsTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Specs tab is displayed");
		try {
			return specsTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isAddendaTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Addenda tab is displayed");
		try {
			return addendaTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isNotesTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Notes tab is displayed");
		try {
			return notesTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isDesignAlertsTabDisplayed() {
		extentTest.log(Status.INFO, "Verify if Design Alerts tab is displayed");
		try {
			return designAlertsTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isNotesTabClickable() {
		extentTest.log(Status.INFO, "Check if Notes tab is Clickable");
		return SeleniumUtils.isClickable(notesTab, driver);
	}

	public boolean isFirmsTabClickable() {
		extentTest.log(Status.INFO, "Check if Firms tab is Clickable");
		return SeleniumUtils.isClickable(firmsTab, driver);
	}

	public boolean isBiddersTabClickable() {
		extentTest.log(Status.INFO, "Check if Bidders tab is Clickable");
		return SeleniumUtils.isClickable(biddersTab, driver);
	}

	public boolean isPlansTabClickable() {
		extentTest.log(Status.INFO, "Check if Plans tab is Clickable");
		return SeleniumUtils.isClickable(plansTab, driver);
	}

	public boolean isSpecsTabClickable() {
		extentTest.log(Status.INFO, "Check if Specs tab is Clickable");
		return SeleniumUtils.isClickable(specsTab, driver);
	}

	public boolean isAddendaTabClickable() {
		extentTest.log(Status.INFO, "Check if Addenda tab is Clickable");
		return SeleniumUtils.isClickable(addendaTab, driver);
	}

	public boolean isDesignAlertsTabClickable() {
		extentTest.log(Status.INFO, "Check if Design Alerts tab is Clickable");
		return SeleniumUtils.isClickable(designAlertsTab, driver);
	}

	public boolean isProjectTabClickable() {
		extentTest.log(Status.INFO, "Check if Project tab is Clickable");
		return SeleniumUtils.isClickable(reportTab, driver);
	}

	public ProjectPage clickOnProjectTab() {
		extentTest.log(Status.INFO, "Click on Project Tab");
		if (isProjectTabClickable()) {
			reportTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Project Tab as it is not enabled for this project");
		}
		return new ProjectPage(driver);
	}

	public ProjectFirmsPage clickOnFirmsTab() {
		extentTest.log(Status.INFO, "Click on firm Tab");
		if (isFirmsTabClickable()) {
			firmsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Firms Tab as it is not enabled for this project");
		}
		return new ProjectFirmsPage(driver);
	}

	public ProjectNotesPage clickOnNotesTab() {
		extentTest.log(Status.INFO, "Click on Notes Tab");
		if (isNotesTabClickable()) {
			notesTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Notes tab as it is not enabled for this project");
		}
		return new ProjectNotesPage(driver);
	}

	public ProjectBiddersPage clickOnBiddersTab() {
		extentTest.log(Status.INFO, "Click on Bidders Tab");
		if (isBiddersTabClickable()) {
			biddersTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Bidders tab as it is not enabled for this project");
		}
		return new ProjectBiddersPage(driver);
	}

	public ProjectPlansPage clickOnPlansTab() {
		extentTest.log(Status.INFO, "Click on Plans Tab");
		if (isPlansTabClickable()) {
			plansTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Plans tab as it is not enabled for this project");
		}
		return new ProjectPlansPage(driver);
	}

	public ProjectSpecsPage clickOnSpecsTab() {
		extentTest.log(Status.INFO, "Click on Specs Tab");
		if (isSpecsTabClickable()) {
			specsTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Specs tab as it is not enabled for this project");
		}
		return new ProjectSpecsPage(driver);
	}

	public ProjectAddendaPage clickOnAddendaTab() {
		extentTest.log(Status.INFO, "Click on Addenda Tab");
		addendaTab.click();
		return new ProjectAddendaPage(driver);
	}

	public ProjectDesignAlertsPage clickOnDesignAlertsTab() {
		extentTest.log(Status.INFO, "Click on Design Alerts Tab");
		if (isDesignAlertsTabClickable()) {
			addendaTab.click();
		} else {
			extentTest.log(Status.INFO, "Cannot click Design Alerts tab as it is not enabled for this project");
		}
		return new ProjectDesignAlertsPage(driver);
	}

	public void clickOnHideProjects() {
		extentTest.log(Status.INFO, "Clicking on Hide Projects under 'Actions' dropdown");
		hideProjectsMenu.click();
	}

	public void clickOnUnhideProjects() {
		extentTest.log(Status.INFO, "Clicking on unhide Projects under 'Actions' dropdown");
		unhideProjectsMenu.click();
	}

	public SpecAlertsResultsPage clickOnSpecAlertsBreadCrumb() {
		extentTest.log(Status.INFO, "Clicking on SpecAlerts BreadCrumb");
		specAlertBreadCrumb.click();
		return new SpecAlertsResultsPage(driver);
	}

	public boolean isUnhideDisplayed() {
		extentTest.log(Status.INFO, "Verify if unhide Projects under 'Actions' dropdown displayed");
		return unhideProjectsMenu.isDisplayed();
	}

	public boolean isPrintProjectDetailsUnderActionsDisplayed() {
		extentTest.log(Status.INFO, "Verify if Print Project Details under 'Actions' dropdown displayed");
		return printProjectDetailsLink.isDisplayed();
	}

	public boolean isPrintProductListUnderActionsDisplayed() {
		extentTest.log(Status.INFO, "Verify if Print Product List under 'Actions' dropdown displayed");
		return printProductListLink.isDisplayed();
	}

	public boolean isAddOnlineProjectUnderActionsDisplayed() {
		extentTest.log(Status.INFO, "Verify if Add Online Projects under 'Actions' dropdown displayed");
		return addOnlineProject.isDisplayed();
	}

	public PrintProjectDetailsPage clickOnPrintProjectDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project Details' under Actions Dropdown");
		printProjectDetailsLink.click();
		waitforLoadingRing();
		return new PrintProjectDetailsPage(driver);
	}

	public EmailAlertsPage clickEmailProjectsLinkUnderActionsDrpDwn() {
		extentTest.log(Status.INFO, "Click email projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailProjectsMenu));
		emailProjectsMenu.click();
		return new EmailAlertsPage(driver);
	}

	public void clickOnNextLink() {
		extentTest.log(Status.INFO, "Clicking on Next Link");
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(nextLink));
		nextLink.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(pageNoLink));

	}

	public void clickOnPreviousLink() {
		extentTest.log(Status.INFO, "Clicking on Previous Link");
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(previousLink));
		previousLink.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(pageNoLink));

	}

	public Integer getCurrentPageNo() {
		extentTest.log(Status.INFO, "Get the current Page Number");
		return Integer.parseInt(pageNoLink.getText());
	}

	public void clickOnBreadCrumbProjectLink() {
		extentTest.log(Status.INFO, "Click on the Bread Crumb Project Link");
		breadCrumbProjectLink.click();
	}
	
	public ProjectResultsPage clickOnBreadCrumbProjectResultPage() {
		extentTest.log(Status.INFO, "Click on the Bread Crumb Project Result Page");
		breadCrumbProjectResultPage.click();
		waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}

	public void clickOnBreadCrumbProjectResultLink() {
		extentTest.log(Status.INFO, "Click on the Bread Crumb Project Result Link");
		breadCrumbProjectResultPage.click();
		waitforLoadingRing();
	}
	
	public String getDesignAlertDisclaimerText() {
		extentTest.log(Status.INFO, "Return Disclaimer text for Design Alert");
		String disclaimer = designAlertDisclaimer.getText();
		return disclaimer;
	}

	public Boolean mouseOverActionandCheckHideProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hideProjectsMenu));
		extentTest.log(Status.INFO, "Check if Hide Projects displayed");
		return hideProjectsMenu.isDisplayed();
	}

	public void mouseOverActionandClickHideProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hideProjectsMenu));
		extentTest.log(Status.INFO, "Click Hide Projects menu under actions");
		hideProjectsMenu.click();
	}

	public void mouseOverActionandClickDownloadFirms() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadFirmsMenu));
		extentTest.log(Status.INFO, "Click Download Firms menu under actions");
		downloadFirmsMenu.click();
	}

	public void mouseOverActionandClickDownloadDocuments() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		SeleniumUtils.isVisible(downloadDocumentsMenu, driver);
		extentTest.log(Status.INFO, "Click download documents menu under actions");
		downloadDocumentsMenu.click();
	}

	public DownloadDocument mouseOverActionandClickDownloadDocumentsWithReturnObject() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadDocumentsMenu));
		extentTest.log(Status.INFO, "Click download documents menu under actions");
		downloadDocumentsMenu.click();
		return new DownloadDocument(driver);
	}

	public CompanyPage mouseOverActionandClickViewCompanies() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click view companies under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(viewCompaniesMenu));
		viewCompaniesMenu.click();
		return new CompanyPage(driver);
	}

	public Boolean mouseOverActionandCheckMapProjectDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Map Project Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Map Project is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mapProjectsLink));
		return mapProjectsLink.isDisplayed();
	}

	public String getHighlightedTab() {
		extentTest.log(Status.INFO, "Get the highlighted tab in the project reports");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(highlightedTabInProjectReport));
		return highlightedTabInProjectReport.getText();
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public String getPaginationSectionText() {
		extentTest.log(Status.INFO, "Get ProjectDisplayedCount");
		return paginationSection.get(0).getText();
	}

	public String getPaginationSectionBottonText() {
		extentTest.log(Status.INFO, "Get ProjectDisplayedCount");
		return paginationSection.get(1).getText();
	}

	public Boolean isPaginationSectionBottonDisplayed() {
		extentTest.log(Status.INFO, "is Pagination section bottom displayed");
		return paginationSection.get(1).isDisplayed();
	}

	public Boolean isPaginationSectionPresent() {
		extentTest.log(Status.INFO, "is Pagination section top displayed");
		return SeleniumUtils.verifyWebElementListIsPresent(paginationSection);
	}

	public Boolean IsPlansTabHighlighted() {
		extentTest.log(Status.INFO, "Verify if the plans tab is highlighted");
		return SeleniumUtils.getBackgroundColor(planTabLabel).equalsIgnoreCase("#FFFF57");
	}
	
	public boolean isSpecsTabHighlighted() {
		extentTest.log(Status.INFO, "Verify if the Specs tab is highlighted");
		return SeleniumUtils.getBackgroundColor(specsTabLabel).equalsIgnoreCase("#FFFF57");
	}
	
	public boolean isSpecsNumberHighlighted() {
		extentTest.log(Status.INFO, "Verify if the Specs number is highlighted");
		boolean isHighlighted = false;
		for(WebElement specNumber : specNumberLinkList){
			isHighlighted = SeleniumUtils.getBackgroundColor(specNumber).equalsIgnoreCase("#FFFF57");
			if(isHighlighted){
				break;
			}
		}
		return isHighlighted;
	}
	
	public boolean isAddendaTabHighlighted() {
		extentTest.log(Status.INFO, "Verify if the Addenda tab is highlighted");
		return SeleniumUtils.getBackgroundColor(addendaTabLabel).equalsIgnoreCase("#FFFF57");
	}

	public boolean isSearchTextDisplayed() {
		extentTest.log(Status.INFO, "Verify if the search text box is displayed");
		try {
			return searchTxtField.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isMySearchesDrpDownDisplayed() {
		extentTest.log(Status.INFO, "Verify if the my searches drop down is displayed");
		try {
			return mySearchesDropDown.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSearchButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the search button is displayed");
		try {
			return searchBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Get the error message");
		return lblErr.getText().trim();
	}

	public String getSelectedTab() {
		extentTest.log(Status.INFO, "Get selected tab.");
		return selectedTab.getText();
	}

	public boolean isSynWithPlanRoomButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify 'Sync With PlanRoom' button is displayed");
		return btnSynWithPlanroom.isDisplayed();
	}

	public boolean isSynWithPlanRoomBtnDisplayed() {
		extentTest.log(Status.INFO, "Verify 'Sync With PlanRoom' button is displayed");
		return CommonUtils.checkElementExist(btnSynWithPlanroomByID, driver);
	}

	public SyncPlanRoomPopupPage clickOnSynWithPlanRoomButtonDisplayed() {
		extentTest.log(Status.INFO, "Click on 'Sync With PlanRoom' button");
		btnSynWithPlanroom.click();
		return new SyncPlanRoomPopupPage(driver);
	}

	public SyncPlanRoomPopupPage clickOnSynWithPlanRoomBtnDisplayed() {
		extentTest.log(Status.INFO, "Click on 'Sync With PlanRoom' button");
		btnSynWithPlanroomByID.click();
		return new SyncPlanRoomPopupPage(driver);
	}

	public HighlightKeywordsPage clickOnFirstMatchedSpecNumber() {
		specNumberMatched.get(0).click();
		extentTest.log(Status.INFO, "Click on first Spec Number which has a keyword match");
		return new HighlightKeywordsPage(driver, specNumberMatched.get(0).getText());

	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisible, driver);
	}
	
	public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText().trim();
	}
	
	/***
	 * Click on Project Result link from bread crumb
	 * @return : Project Result page object.
	 */
	public ProjectResultsPage clickOnProjectResultLink(){
		extentTest.log(Status.INFO, "click On Creat new template.");
		projectResultLink.click();
		waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}
	
	/***
	 * Check only show filter with matches OFF toggle button is displayed.
	 * @return : Return the boolean result.
	 */
	public boolean isOnlyShowOFFToggleButtonDisplayed(){
		extentTest.log(Status.INFO, "Check only show filter with matches OFF toggle button is displayed.");
		return SeleniumUtils.isVisible(onlyShowOFFToggleBtn, driver);
	}
	
	/***
	 * Check only show filter with matches ON toggle button is displayed.
	 * @return : Return the boolean result.
	 */
	public boolean isOnlyShowONToggleButtonDisplayed(){
		extentTest.log(Status.INFO, "Check only show filter with matches ON toggle button is displayed.");
		return SeleniumUtils.isVisible(onlyShowONToggleBtn, driver);
	}
	
	/***
	 * Click on only show OFF filter with matches toggle button
	 */
	public void clickOnOnlyShowOFFToggleButton(){
		extentTest.log(Status.INFO, "Click on only show OFF filter with matches toggle button.");
		onlyShowOFFToggleBtn.click();
	}
	
	/***
	 * Click on only show ON filter with matches toggle button
	 */
	public void clickOnOnlyShowONToggleButton(){
		extentTest.log(Status.INFO, "Click on only show ON filter with matches toggle button.");
		onlyShowONToggleBtn.click();
	}
	
	/***
	 * Get only show Off filter with matches toggle button label
	 * @return : Return button label
	 */
	public String getOnlyShowOFFToggleButtonText(){
		extentTest.log(Status.INFO, "Get only show OFF filter with matches toggle button label.");
		return onlyShowOFFToggleBtn.getText().trim();
	}
	
	/***
	 * Get only show ON filter with matches toggle button label
	 * @return : Return button label
	 */
	public String getOnlyShowONToggleButtonText(){
		extentTest.log(Status.INFO, "Get only show ON filter with matches toggle button label.");
		return onlyShowONToggleBtn.getText().trim();
	}
	
}

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
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class SpecAlertsResultsPage extends ProjectResultsPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String selectedProgram;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_btnProjectActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.LINK_TEXT, using = "Print Project List")
	private WebElement printProjectListLink;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_chkProjectSelect")
	private WebElement firstProjectchkBox;

	@FindBy(how = How.ID, using = "ctl00_lblProjectTitle")
	private WebElement firstProjectTitle;

	@FindBy(how = How.ID, using = "ctl01_chkProjectSelect")
	private WebElement secondProjectchkBox;

	@FindBy(how = How.ID, using = "project-select-all")
	private WebElement projectSelectAll;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_specText']/span/strong")
	private WebElement specAlertsLabel;

	@FindBy(how = How.ID, using = "ctl00_trackListsLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.ID, using = "emailAddressesSection")
	private WebElement emailAddressSection;

	@FindBy(how = How.ID, using = "optionalMessagesSection")
	private WebElement optionalMessagesSection;

	@FindBy(how = How.ID, using = "emailPopupSubmit")
	private WebElement emailPopupSubmit;

	@FindBy(how = How.LINK_TEXT, using = "HOME")
	private WebElement homeMenuLink;

	@FindBy(how = How.CLASS_NAME, using = "project-title")
	private List<WebElement> projectTitles;

	@FindBy(how = How.LINK_TEXT, using = "Track Projects")
	private WebElement trackProjectsActionsOptions;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement downloadFirmsActionsOptions;

	@FindBy(how = How.LINK_TEXT, using = "Email Projects")
	private WebElement emailProjectsActionsOptions;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_listProjectCountText")
	private WebElement topPageNolbl;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_resultsPerPage']/span")
	private WebElement topResultsPerPagelbl;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_perPageSectionWrapper']//div[@class='pagination']")
	private WebElement topPaginationLink;

	@FindBy(how = How.XPATH, using = "//div[@id='appliedFilterHeader']//following-sibling::div[@class='appliedSelectionCont']//a[contains(@id,'ucSpecAlert')]")
	private List<WebElement> specAlertsProgramsLeftPanel;

	@FindBy(how = How.XPATH, using = "//div[@id='actions-link']//a")
	private List<WebElement> actionOptionsList;

	@FindBy(how = How.XPATH, using = "//img[@id='ctl00_contentPlaceHolderHeader_ucSpecAlert_rptItems_ctl00_imgProgramSelected']//following-sibling::a")
	private WebElement selectedProgramInLeftPanel;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'breadcrumb-container')]//span")
	private WebElement specAlertsbreadCrumb;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ProjectResult_pagerTop_lblTopSearchResults")
	private WebElement topSearchResultPerPage;

	@FindBy(how = How.XPATH, using = "//select[@id='ctl00_contentPlaceHolderHeader_ProjectResult_pagerTop_lblTopSearchResults']//option")
	private List<WebElement> topSearchResultPerPageDrpList;

	@FindBy(how = How.XPATH, using = "//h3[@id='createBetweenHeader']/span")
	private WebElement editDateRangeIcon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucCreateBetweenSpec_lnkCreateBetweenSpecDate")
	private WebElement selectedDateRange;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_ucFooter_logoDiv']/img")
	private WebElement footerLogo;

	@FindBy(how = How.ID, using = "ctl00_lblDrNumber")
	private WebElement firstDRNumber;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_selectAllWrapperCon']")
	private WebElement selectAllRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_ActionWrapperCon']")
	private WebElement actionDropdownRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_sortDropDownWrapper']")
	private WebElement sortDropdownRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@class='paginationWrapper']")
	private List<WebElement> paginationSectionOnPage;

	@FindBy(how = How.ID, using = "Prev1")
	private WebElement companyFirstPageNavigation;

	@FindBy(how = How.ID, using = "Prev2")
	private WebElement companySecondPageNavigation;

	@FindBy(how = How.ID, using = "Prev3")
	private WebElement companyThirdPageNavigation;

	@FindBy(how = How.ID, using = "Prev4")
	private WebElement companyFourPageNavigation;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-left.png']")
	private WebElement previousCompanyPage;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-right.png']")
	private WebElement nextCompanyPage;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-double-left.png']")
	private WebElement firstPageCompanyPageBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_resultcontent")
	private WebElement myDownloadBackgroundPage;

	public SpecAlertsResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the SpecAlerts Results Page");
	}

	public SpecAlertsResultsPage(WebDriver driver, String programName) {
		super(driver);
		this.selectedProgram = programName;
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	@Override
	public void clickOnActionsDropdown() {
		extentTest.log(Status.INFO, "Click on Actions Dropdown");
		actionsDropdown.click();
	}

	public void mouseOverActionButton() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectListLink));
		extentTest.log(Status.INFO, "Mouse over actions drop down button");
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

	@Override
	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	public boolean isSpecAlertsBreadCrumbDisplayedCorrect() {
		extentTest.log(Status.INFO, "Verify if SpecAlerts breadcrumb displayed as expected");
		return specAlertsbreadCrumb.getText().contains(selectedProgramInLeftPanel.getText());
	}

	public boolean isSpecAlertsBreadCrumbDisplayedCorrectWithoutPrj() {
		extentTest.log(Status.INFO, "Verify if SpecAlerts breadcrumb displayed as expected");
		return specAlertsbreadCrumb.getText().contains(selectedProgram);
	}

	public void goToBackPage() {
		SeleniumUtils.goToBackPage(driver);
	}

	public boolean isSpecAlertsProgramListedOnLeft() {
		extentTest.log(Status.INFO, "Verify if SpecAlerts Programs displayed on the left panel");
		for (WebElement webElement : specAlertsProgramsLeftPanel) {
			if (webElement.getText().equalsIgnoreCase(this.selectedProgram))
				return true;
		}
		return false;
	}

	@Override
	public HomePage clickOnHomeTab() {
		extentTest.log(Status.INFO, "Clicking on Home Tab");
		homeMenuLink.click();
		return new HomePage(driver);

	}

	public SpecAlertsResultsPage clickOnDownloadFirmsFromSpecAlertsActions() {
		extentTest.log(Status.INFO, "Clicking on Download Firms under Actions Dropdown in SpecAlerts Results Page");
		SeleniumUtils.isClickable(downloadFirmsActionsOptions, driver);
		downloadFirmsActionsOptions.click();
		return new SpecAlertsResultsPage(driver);
	}

	public ProjectPage clickOnEmailProjectsFromSpecAlertsActions() {
		extentTest.log(Status.INFO, "Clicking on Email Projetcs under Actions Dropdown in SpecAlerts Results Page");
		emailProjectsActionsOptions.click();
		return new ProjectPage(driver);
	}

	public TrackPopUpPage clickOnTrackProjectsFromSpecAlertsActions() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under Actions Dropdown in SpecAlerts Results Page");
		trackProjectsActionsOptions.click();
		return new TrackPopUpPage(driver);
	}

	public boolean verifyActionOptions() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.SpecAlertsResultsActionsOptions opt : DGNEnum.SpecAlertsResultsActionsOptions.values()) {
			if (actionOptionsList.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	// check first project check box is selected.
	public boolean checkURLWithSpecResultPage(String expectedString) {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		return driver.getCurrentUrl().toUpperCase().contains(expectedString.toUpperCase());
	}

	public void clickOnFirstProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		firstProjectchkBox.click();
	}

	@Override
	public void clickOnSecondProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on second project checkbox");
		secondProjectchkBox.click();
	}

	@Override
	public void clickOnSelectAllProjects() {
		extentTest.log(Status.INFO, "Click on Select All in the Projects List");
		projectSelectAll.click();
	}

	@Override
	public ProjectPage clickOnFirstProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on First Project Title");
		projectTitles.get(0).click();
		return new ProjectPage(driver);
	}

	public boolean isPageNoDisplayed() {
		extentTest.log(Status.INFO, "Verify if the top page number is displayed");
		return topPageNolbl.isDisplayed();
	}

	public boolean isSpecAlertsProgramsLeftPanelDisplayed() {
		extentTest.log(Status.INFO, "Verify if the specAlerts Programs Left Panel has content");
		return specAlertsProgramsLeftPanel.get(0).isDisplayed();
	}

	public boolean isResultPerPageDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Results Per Page is displayed");
		return topResultsPerPagelbl.isDisplayed();
	}

	public boolean isPaginationDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Pagination is displayed");
		return topPaginationLink.isDisplayed();
	}

	public boolean isSelectAllDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Select All is displayed");
		return projectSelectAll.isDisplayed();
	}

	@Override
	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	@Override
	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public void clickOnResultPerPage() {
		extentTest.log(Status.INFO, "Clicking on the result per page drop down");
		topSearchResultPerPage.click();
	}

	public void selectResultPerPage(DGNEnum.resultPerPageOptionList optionValue) throws InterruptedException {
		extentTest.log(Status.INFO, "Select given value from result per page option ");
		for (int i = 0; i < topSearchResultPerPageDrpList.size(); i++) {
			if (Integer.parseInt(topSearchResultPerPageDrpList.get(i).getText()) == optionValue.getValue()) {
				topSearchResultPerPageDrpList.get(i).click();
				break;
			}
		}
		Thread.sleep(10000);
	}

	public void clickOnEditDateRangeIcon() {
		extentTest.log(Status.INFO, "Click on the edit date range icon");
		editDateRangeIcon.click();
	}

	public String getSelectedDate() {
		extentTest.log(Status.INFO, "Get the selected date");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(selectedDateRange));
		return selectedDateRange.getText();
	}

	public String getTooltipSelectedDate() {
		extentTest.log(Status.INFO, "Get the selected date tooltip");
		return selectedDateRange.getAttribute("original-title").trim();
	}

	public void mouseOverSelectedDateRangeButton() {
		Actions action = new Actions(driver);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(selectedDateRange));
		action.clickAndHold(selectedDateRange).build().perform();
		extentTest.log(Status.INFO, "Mouse over selected date range");
	}

	public boolean isFooterLogoDodge() {
		extentTest.log(Status.INFO, "Verify the footer logo");
		return footerLogo.getAttribute("alt").trim().equalsIgnoreCase("Dodge Data & Analytics");
	}

	public void setEmailAddress(String email) {
		extentTest.log(Status.INFO, "Enter Email Address in the popup");
		emailAddressSection.clear();
		emailAddressSection.sendKeys(email);
	}

	public void setOptionalMessage(String email) {
		extentTest.log(Status.INFO, "Enter Optional message in the popup");
		optionalMessagesSection.clear();
		optionalMessagesSection.sendKeys(email);
	}

	public void clickEmailPopUpSendBtn() {
		extentTest.log(Status.INFO, "Clicking on the send button in the email pop up");
		emailPopupSubmit.click();
	}

	@Override
	public String getFirstDRNumer() {
		extentTest.log(Status.INFO, "Get the first DR Number on Project Results Page");
		String drText = firstDRNumber.getText();
		return drText.substring(4, 17);
	}

	// Check for Select All redesign wrap.
	public boolean checkSelectAllRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Select All redesign wrap.");
		return CommonUtils.checkElementExist(selectAllRedesignWrap, driver);
	}

	// Check for Action dropdown redesign wrap.
	public boolean checkActionDropdownRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Action dropdown redesign wrap.");
		return CommonUtils.checkElementExist(actionDropdownRedesignWrap, driver);
	}

	// Check for Sort dropdown redesign wrap.
	public boolean checkSortDropdownRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Sort dropdown redesign wrap.");
		return CommonUtils.checkElementExist(sortDropdownRedesignWrap, driver);
	}

	// Check Pagination section present at top and bottom of page.
	public boolean checkPaginationSectionPresentOnPage() {
		extentTest.log(Status.INFO, "Check Pagination section present at top and bottom of page.");
		return paginationSectionOnPage.size() == 2;
	}

	// Click on Second company page.
	public void clickOnSecondCompanyPage() {
		extentTest.log(Status.INFO, "Click on Second company page.");
		companySecondPageNavigation.click();
		waitTillRingDisappear();
	}

	// Check after Click on Second company page is become active
	public boolean isSecondCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on Second company page is become active.");
		return companySecondPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	// Click on third company page.
	public void clickOnThirdCompanyPage() {
		extentTest.log(Status.INFO, "Click on third company page..");
		companyThirdPageNavigation.click();
		waitTillRingDisappear();
	}

	// Check after Click on third company page is become active
	public boolean isThirdCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on third company page is become active.");
		return companyThirdPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	// Click on Previous company page.
	public void clickOnPreviousCompanyPage() {
		extentTest.log(Status.INFO, "Click on Previous company page..");
		previousCompanyPage.click();
		waitTillRingDisappear();
	}

	// Click on Next company page.
	public void clickOnNextCompanyPage() {
		extentTest.log(Status.INFO, "Click on Next company page..");
		nextCompanyPage.click();
		waitTillRingDisappear();
	}

	// Check after Click on first company page is become active
	public boolean isFirstCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on first company page is become active.");
		return companyFirstPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	// Click on first page button company page.
	public void clickOnFirstPageButton() {
		extentTest.log(Status.INFO, "Click on first page button company page.");
		firstPageCompanyPageBtn.click();
		waitTillRingDisappear();
	}

	// Check after Click on fourth company page is become active
	public boolean isFourCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on four company page is become active.");
		return companyFourPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	public void waitTillRingDisappear() {
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}
}

package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectPlansPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'div-PlanOwnerType clearfix')]//div[2]")
	private List<WebElement> plans;

	@FindBy(how = How.XPATH, using = "//div[@id='Title']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planTitle;

	@FindBy(how = How.XPATH, using = "//div[@id='Architectural']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planArchitectural;

	@FindBy(how = How.XPATH, using = "//div[@id='Structural']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planStructural;

	@FindBy(how = How.XPATH, using = "//div[@id='Civil']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planCivil;

	@FindBy(how = How.XPATH, using = "//div[@id='Mechanical']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planMechanical;

	@FindBy(how = How.XPATH, using = "//div[@id='Electrical']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planElectrical;

	@FindBy(how = How.XPATH, using = "//div[@id='Plumbing']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planPlumbing;

	@FindBy(how = How.XPATH, using = "//div[@id='Landscape']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planLandscape;

	@FindBy(how = How.XPATH, using = "//div[@id='OtherSpecial']//div[contains(@class,'item-link div-table-col number')]")
	private List<WebElement> planOtherSpecial;

	@FindBy(how = How.ID, using = "Switch switchOff")
	private List<WebElement> planSwitchOff;

	@FindBy(how = How.XPATH, using = ".//div[contains(@id,'Title')]//a[not(@class)]")
	private List<WebElement> numberMatched;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblDrNumber")
	private WebElement DrNumber;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvProjectViewer']//a[not(contains(@class,'notMatchedDoc')) and contains(@id,'plan')]")
	private List<WebElement> matchedPlans;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'notMatchedDoc')]")
	private List<WebElement> numberNotMatched;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblDrNumber")
	private WebElement drNumber;

	@FindBy(how = How.ID, using = "btnSearchNow")
	private WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'planTittle')]")
	private List<WebElement> planTitleList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnPrint")
	private WebElement printPlanListActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_etakeoffdwnload")
	private WebElement installETaakeOffActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnTakeoff")
	private WebElement downloadetakeOffMenu;

	@FindBy(how = How.XPATH, using = "//input[@type='checkbox']")
	private List<WebElement> otherORSpecialPlansList;

	@FindBy(how = How.ID, using = "rdDownloadLater")
	private WebElement downloadLaterRadioBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='downloadDocumentsPopup']/div[3]/div/div[2]/a")
	private WebElement saveBtn;

	@FindBy(how = How.XPATH, using = "//li[@id='ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_liTryStack']")
	private WebElement takeoffPartnerArea;

	@FindBy(how = How.XPATH, using = "//li[@id='ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_liHrTryStack']//div[contains(text(),'explore our takeoff partners')]")
	private WebElement takeoffPartnerAreaHeader;

	@FindBy(how = How.XPATH, using = "//li[@id='ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_liTryStack']//img[@src='Images/Try_Stack_Icon.png']")
	private WebElement stackIcon;

	@FindBy(how = How.LINK_TEXT, using = "sign up for STACK")
	private WebElement signUpForStackLink;

	@FindBy(how = How.XPATH, using = "//li[@id='ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_liTryStack']//div[contains(text(),'or')]")
	private WebElement orText;

	@FindBy(how = How.LINK_TEXT, using = "find out what it can do for you")
	private WebElement findOutWhatItCanDoForYouLink;

	@FindBy(how = How.ID, using = "rbCSV")
	private WebElement csvRadioBtn;
	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement selectATemplateDropDown;
	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadProjectPopUpCSVFields;

	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadProjectPopUpCSVAvailableFields;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement clickOnAddButtonOnPopUPFrame;

	@FindBy(how = How.XPATH, using = ".//*[@id='listRemove']/option")
	private List<WebElement> downloadProjectPopUpCSVSelectedFields;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement enterTeplateName;
	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement clickOncraeteButton;

	@FindBy(how = How.ID, using = "rbSingleFile")
	private WebElement saveToSingleFileRadioBtn;
	@FindBy(how = How.CSS, using = "#drpCSVTemplateName option")
	private List<WebElement> downloadProjectPopUpCSVTemplateList;

	@FindBy(how = How.ID, using = "downloadProjectSubmit")
	private WebElement downloadButton;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/delayed-load-with-fade.gif']")
	private List<WebElement> delayImageDialog;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'rptNavigator_actionsNav')]//a")
	private List<WebElement> actionOptionList;

	public ProjectPlansPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Plans Page");
	}

	public String getCurrentURl() {
		return driver.getCurrentUrl();
	}

	public LinkedHashMap getPlanListDefault() {
		LinkedHashMap lhm = new LinkedHashMap(10);
		lhm.put(DGNEnum.Plans.TITLE, 0);
		lhm.put(DGNEnum.Plans.ARCHITECTURAL, 1);
		lhm.put(DGNEnum.Plans.STRUCTURAL, 2);
		lhm.put(DGNEnum.Plans.CIVIL, 3);
		lhm.put(DGNEnum.Plans.MECHANICAL, 4);
		lhm.put(DGNEnum.Plans.ELECTRICAL, 5);
		lhm.put(DGNEnum.Plans.PLUMBING, 6);
		lhm.put(DGNEnum.Plans.LANDSCAPE, 7);
		lhm.put(DGNEnum.Plans.OTHERSPECIAL, 8);

		return lhm;
	}

	public List getPatternMatchList(List list) {
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).toString().replaceAll("[0-9]", ""));
		}
		return list;

	}

	public String getDRNumberText()

	{
		return drNumber.getText();
	}

	public String switchWindowandGetTitle() {

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		return (driver.getTitle());

	}

	public boolean checkInnerTableSorting(List<WebElement> webElement) {
		List<String> listElements = CommonUtils.getListFromWebElements(webElement);
		List<String> listArc = CommonUtils.sortWebElements(listElements, false);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean checkPlanOrder() {
		LinkedHashMap defaultPlanList = getPlanListDefault();
		List<WebElement> planList = new ArrayList<WebElement>(plans);
		boolean IsSorted = false, IsEqual = false;
		try {
			for (int i = 0; i < planList.size(); i++) {
				int j = 0;
				switch (DGNEnum.Plans.valueOf(planList.get(i).getText().toUpperCase().replaceAll("[^a-zA-Z]", ""))) {
				case TITLE:
					j = (int) defaultPlanList.get(DGNEnum.Plans.TITLE);
					IsEqual = checkInnerTableSorting(planTitle);
					break;
				case ARCHITECTURAL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.ARCHITECTURAL);
					IsEqual = checkInnerTableSorting(planArchitectural);
					break;
				case STRUCTURAL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.STRUCTURAL);
					IsEqual = checkInnerTableSorting(planStructural);
					break;
				case CIVIL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.CIVIL);
					IsEqual = checkInnerTableSorting(planCivil);
					break;
				case MECHANICAL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.MECHANICAL);
					IsEqual = checkInnerTableSorting(planMechanical);
					break;
				case ELECTRICAL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.ELECTRICAL);
					IsEqual = checkInnerTableSorting(planElectrical);
					break;
				case PLUMBING:
					j = (int) defaultPlanList.get(DGNEnum.Plans.PLUMBING);
					IsEqual = checkInnerTableSorting(planPlumbing);
					break;
				case LANDSCAPE:
					j = (int) defaultPlanList.get(DGNEnum.Plans.LANDSCAPE);
					IsEqual = checkInnerTableSorting(planLandscape);
					break;
				case OTHERSPECIAL:
					j = (int) defaultPlanList.get(DGNEnum.Plans.OTHERSPECIAL);
					IsEqual = checkInnerTableSorting(planOtherSpecial);
					break;
				}

				if (i <= j && IsEqual) {
					IsSorted = true;
				} else {
					IsSorted = false;
					break;
				}
			}
			extentTest.log(Status.INFO, "Verify if plans are sorted in the desired order");
		} catch (Exception ex) {
		}
		return IsSorted;

	}

	public Boolean IsFirstMatchedPlanNumberHighlightedInYellow() {
		extentTest.log(Status.INFO, "Is First Matched plan number hughlighted In yellow");
		return SeleniumUtils.getBackgroundColor(numberMatched.get(0)).equalsIgnoreCase("#FFFF57");
	}

	public HighlightKeywordsPage clickFirstMatchedPlanNumber() {

		extentTest.log(Status.INFO, "click on first Matched Plan number");
		matchedPlans.get(0).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		waitforLoadingRing();
		return new HighlightKeywordsPage(driver);

	}

	public boolean IsSearchButtonDisplayed() {

		extentTest.log(Status.INFO, "Is Search button displayed");
		return SeleniumUtils.isVisible(btnSearch, driver);

	}

	public String getFirstMatchedPlanNumberText() {
		return matchedPlans.get(0).getText();
	}

	// Redirection to different pages
	public HighlightKeywordsPage clickOnFirstMatchedPlanNumber() {
		String strMatched = "";
		try {
			if (numberMatched.size() > 0) {
				strMatched = numberMatched.get(0).getText();
				numberMatched.get(0).click();
				extentTest.log(Status.INFO, "Click on the first matched plan number");
			}
		} catch (Exception ex) {
			extentTest.log(Status.INFO, "No Plans Matched..Quitting");
		}
		return new HighlightKeywordsPage(driver, strMatched);

	}

	public HighlightKeywordsPage clickOnFirstNonMatchedPlanNumber() {
		numberNotMatched.get(0).click();
		extentTest.log(Status.INFO, "Click on first Plan Number which does not have a keyword match");
		return new HighlightKeywordsPage(driver, numberNotMatched.get(0).getText());

	}

	@Override
	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public String getDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the Specs Tab");
		String drNo = drNumber.getText();

		int endIndex1 = drNo.lastIndexOf(" ");
		return drNo.substring(0, endIndex1);
	}

	// Return the size of total plan title displayed on the Plan page.
	public int getPlanTitleCount() {
		extentTest.log(Status.INFO, "Return the size of total plan title displayed on the Plan page.");
		return planTitleList.size();
	}

	// Click on the Print Plan List and return object of print project detail
	// page.
	public PrintProjectDetailsPage clickOnPrintPlanListActionMenu() {
		extentTest.log(Status.INFO, "Click on the Print Plan List and return object of print project detail page.");
		SeleniumUtils.isVisible(printPlanListActionMenu, driver);
		printPlanListActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	// Check action Install eTakeOff is displayed.
	public boolean isInstallETakeOffActionMenuDsiapley() {
		extentTest.log(Status.INFO, "Check action Install eTakeOff is displayed.");
		return CommonUtils.checkElementExist(installETaakeOffActionMenu, driver);
	}

	// Check action Install eTakeOff is displayed in orange on hover.
	public boolean checkInstallETakeOffActionMenuColor() {
		String orangeColorCode = "rgba(242, 77, 0, 1)";
		extentTest.log(Status.INFO, "Check action Install eTakeOff is displayed in orange on hover.");
		Actions action = new Actions(driver);
		action.moveToElement(installETaakeOffActionMenu).build().perform();
		return installETaakeOffActionMenu.getCssValue("color").equalsIgnoreCase(orangeColorCode);
	}

	// Click on first other or special plan.
	public void clickOnFirstOtherOrSpecialPlan() {
		extentTest.log(Status.INFO, "Click on first other or special plan.");
		otherORSpecialPlansList.get(1).click();
	}

	public void clickOnDownloadLater() {
		extentTest.log(Status.INFO, "Clicking on Download later Link");
		SeleniumUtils.isVisible(downloadLaterRadioBtn, driver);
		downloadLaterRadioBtn.click();
	}

	public void clickOnSaveBtn() {
		extentTest.log(Status.INFO, "Clicking on Save Button");
		saveBtn.click();
	}

	// Stack Integration related methods (takeoff partners)
	public boolean isTakeOffPartnerAreaDisplayed() {
		extentTest.log(Status.INFO, "Verify 'explore our takeoff partners' area is dislayed");
		return CommonUtils.checkElementExist(takeoffPartnerArea, driver);
	}

	public void clickOnCSVRadioBtn() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		csvRadioBtn.click();
		SeleniumUtils.isVisible(selectATemplateDropDown, driver);
	}

	public List<String> getFieldList() {
		extentTest.log(Status.INFO, "Get Available Fileld List.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedField : downloadProjectPopUpCSVFields) {
			selectedFilelds.add(selectedField.getText());
		}
		return selectedFilelds;
	}

	public void selectNewFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVAvailableFields.get(0))
				.click(downloadProjectPopUpCSVAvailableFields.get(3))
				.click(downloadProjectPopUpCSVAvailableFields.get(4))
				.click(downloadProjectPopUpCSVAvailableFields.get(6)).keyUp(Keys.CONTROL).build().perform();
	}

	public String getTakeOffPartnerAreaHeader() {
		extentTest.log(Status.INFO, "Verify 'explore our takeoff partners' area header");
		return takeoffPartnerAreaHeader.getText();
	}

	public boolean isStackIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Stack Icon");
		return stackIcon.isDisplayed();
	}

	public String getSignUpLinkText() {
		extentTest.log(Status.INFO, "Verify 'sign up for STACK' link text");
		return signUpForStackLink.getText();
	}

	public String getOrText() {
		extentTest.log(Status.INFO, "Verify 'or' text");
		return orText.getText();
	}

	public String getFindOutWhatText() {
		extentTest.log(Status.INFO, "Verify 'find out what it can do for you' link text");
		return findOutWhatItCanDoForYouLink.getText();
	}

	public void clickOnSignUpForStackLinkAndSwitchDriverControl() {
		extentTest.log(Status.INFO, "Click on 'sign up for STACK' link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signUpForStackLink));
		signUpForStackLink.click();
		CommonUtils.switchToNewTab(driver);
	}

	public void closeNewTab() {
		extentTest.log(Status.INFO, "Close new window");
		CommonUtils.switchToHomeTab(driver);
	}

	public void clickOnFindOutWhatItCanDoForYouAndSwitchDriverControl() {
		extentTest.log(Status.INFO, "Click on 'find out what it can do for you' link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(findOutWhatItCanDoForYouLink));
		findOutWhatItCanDoForYouLink.click();
		CommonUtils.switchToNewTab(driver);
	}

	public String getNewTabUrl() {
		extentTest.log(Status.INFO, "Get new window URL");
		return driver.getCurrentUrl();
	}

	// Get text off Install eTakeOff Menu.
	public String getInstallETakeOffActionMenuText() {
		extentTest.log(Status.INFO, "Get text of Install eTakeOff Menu");
		return installETaakeOffActionMenu.getText().trim();
	}

	// Is 'o' in 'Install eTakeoff' is lowercase.
	public boolean isOInstalleTakeoffLoweCase() {
		extentTest.log(Status.INFO, "Verify 'o' in 'Install eTakeoff' is lowercase");
		return (Character.isLowerCase(getInstallETakeOffActionMenuText().charAt(13)));
	}

	// Is 'Install eTakeoff' menu color same as color used for the other
	// drop-down menus.
	public boolean isInstalleTakeoffMenuColorSameasOtherMenuItemsColors() {
		extentTest.log(Status.INFO,
				"Verify 'Install eTakeoff' menu color is same as color used for the other drop-down menus");
		return installETaakeOffActionMenu.getCssValue("color").equals(printPlanListActionMenu.getCssValue("color"));
	}

	public void clickOnInstallETakeoffAndSwitchDriverControl() {
		extentTest.log(Status.INFO, "Click on 'Install eTakeoff' link");
		installETaakeOffActionMenu.click();
		CommonUtils.switchToNewTab(driver);
	}

	// Get text off 'Download eTakeOff File' Menu.
	public String getDownloadETakeOffFileText() {
		extentTest.log(Status.INFO, "Get text of 'Download eTakeOff File' Menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadetakeOffMenu));
		return downloadetakeOffMenu.getText();
	}

	// Is 'o' in 'eTakeoff' is lowercase.
	public boolean isOInDownloadeTakeoffFileLoweCase() {
		extentTest.log(Status.INFO, "Verify 'o' in 'eTakeoff' in 'Download eTakeoff file' is lowercase");
		return (Character.isLowerCase(getDownloadETakeOffFileText().charAt(14)));
	}

	public void clickOnDownloadeTakeoffFile() {
		extentTest.log(Status.INFO, "Clicking on Download eTakeoff file");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadetakeOffMenu));
		downloadetakeOffMenu.click();
	}

	public void clickOnAddBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOnAddButtonOnPopUPFrame.click();
	}

	public List<String> getSelectedFieldList() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedFieldPopUp : downloadProjectPopUpCSVSelectedFields) {
			selectedFilelds.add(selectedFieldPopUp.getText());
		}
		return selectedFilelds;
	}

	public void getTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "MyNewCSVTemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void clickOnCreateBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOncraeteButton.click();
	}

	public void clickOnSaveToSingleFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to single files radio button.");
		saveToSingleFileRadioBtn.click();
	}

	public List<String> getTemplateList() {
		extentTest.log(Status.INFO, "get default template list");
		List<String> templateList = new ArrayList<String>();
		for (WebElement template : downloadProjectPopUpCSVTemplateList) {
			templateList.add(template.getText());
		}
		return templateList;
	}

	public void clickOnDownloadBtn() {
		extentTest.log(Status.INFO, "Click on Download Button.");
		downloadButton.click();
		SeleniumUtils.isLoadingIconInvisible(delayImageDialog, driver);
	}

	public void selectCreateNewTemplate() {
		extentTest.log(Status.INFO, "click On Creat new template.");
		selectCreatNewTemplateOption.click();
	}

	public boolean checkEPrefixDownloadTakeoffFile() {
		extentTest.log(Status.INFO, "Check 'e' prefix into Download take off file.");
		String[] downloadTakeoff = getDownloadETakeOffFileText().trim().split(" ");
		return downloadTakeoff[1].charAt(0) == 'e';
	}

	public boolean checkPositionOfDownloadETakeoff(String downloadETakeoff, String installETakeOff) {
		extentTest.log(Status.INFO, "Check the position of Download E TakeOff after install eTakeoff option.");
		for (int i = 0; i < actionOptionList.size(); i++) {
			if (actionOptionList.get(i).getText().trim().equalsIgnoreCase(installETakeOff)) {
				return actionOptionList.get(i + 1).getText().trim().equalsIgnoreCase(downloadETakeoff);
			}
		}
		return false;
	}
}

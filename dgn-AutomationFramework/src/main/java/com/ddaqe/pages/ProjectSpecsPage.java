package com.ddaqe.pages;

import java.util.ArrayList;
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
import com.ddaqe.utils.SeleniumUtils;

public class ProjectSpecsPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//a[@style=\"background-color:#FFFF57 !important;\" and @target='_blank']")
	private List<WebElement> specNumberMatched;
	
	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]")
	private WebElement displaySpecLink;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'ctl00_lnkDesignAlert')]")
	private List<WebElement> linkDesignAlerts;
	
	@FindBy(how = How.LINK_TEXT, using = "Notes")
	private WebElement notesLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Firms")
	private WebElement firmsLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Addenda")
	private WebElement addendaLink;

	@FindBy(how = How.LINK_TEXT, using = "Plans")
	private WebElement plansLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Planholders/Bidders")
	private WebElement planholdersBiddersLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'notMatchedDoc')]")
	private List<WebElement> specNumberNotMatched;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblDrNumber")
	private WebElement drNumber;

	@FindBy(how = How.LINK_TEXT, using = "00 00 00")
	private WebElement specTitle_000000;

	@FindBy(how = How.LINK_TEXT, using = "09 00 00")
	private WebElement specTitle_090000;

	@FindBy(how = How.LINK_TEXT, using = "10 00 00")
	private WebElement specTitle_100000;

	@FindBy(how = How.LINK_TEXT, using = "00000")
	private WebElement specTitle_00000;

	@FindBy(how = How.LINK_TEXT, using = "01000")
	private WebElement specTitle_01000;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvProjectViewer']//a[not(contains(@class,'notMatchedDoc')) and contains(@id,'spec')]")
	private List<WebElement> matchedSpecs;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_etakeoffdwnload")
	private WebElement installETaakeOffActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnPrint")
	private WebElement printPlanListActionMenu;

	@FindBy(how = How.XPATH, using = "//input[@filetype='SPECS' and @type='checkbox']")
	private List<WebElement> specCheckboxList;

	// Stack Integration Objects (takeoff partners)
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

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnTakeoff")
	private WebElement downloadetakeOffMenu;

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

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptSpecHead_ctl01_rtSpecReport_ctl00_specTitle")
	private WebElement specDisplay;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'rptNavigator_actionsNav')]//a")
	private List<WebElement> actionOptionList;

	public ProjectSpecsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Specs Page");
	}

	public String getCurrentURl() {
		return driver.getCurrentUrl();
	}

	@Override
	public HighlightKeywordsPage clickOnFirstMatchedSpecNumber() {
		String isMatch = "";
		if (specNumberMatched.size() > 0) {
			isMatch = specNumberMatched.get(0).getText();
			specNumberMatched.get(0).click();
			extentTest.log(Status.INFO, "Click on first Spec Number which has a keyword match");
		}
		return new HighlightKeywordsPage(driver, isMatch);

	}

	public HighlightKeywordsPage clickOnFirstNonMatchedSpecNumber() {
		specNumberNotMatched.get(0).click();
		extentTest.log(Status.INFO, "Click on first Spec Number which does not have a keyword match");
		return new HighlightKeywordsPage(driver, specNumberNotMatched.get(0).getText());

	}

	@Override
	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions dropdown");
		actionsDropdowm.click();
	}

	@Override
	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public String getDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the Specs Tab");
		String dr = drNumber.getText();
		int endIndex1 = dr.lastIndexOf(" ");
		return dr.substring(0, endIndex1);
	}

	public boolean isSpecTitleDisplayed_000000() {
		extentTest.log(Status.INFO, "Verify if the spec tile - 00 00 00 is displayed");
		try {
			return specTitle_000000.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSpecTitleDisplayed_090000() {
		extentTest.log(Status.INFO, "Verify if the spec tile - 09 00 00 is displayed");
		try {
			return specTitle_090000.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSpecTitleDisplayed_100000() {
		extentTest.log(Status.INFO, "Verify if the spec tile - 10 00 00 is displayed");
		try {
			return specTitle_100000.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSpecTitleDisplayed_00000() {
		extentTest.log(Status.INFO, "Verify if the spec tile - 00000 is displayed");
		try {
			return specTitle_00000.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSpecTitleDisplayed_01000() {
		extentTest.log(Status.INFO, "Verify if the spec tile - 01000 is displayed");
		try {
			return specTitle_01000.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public HighlightKeywordsPage clickFirstMatchedSpecNumber() {

		extentTest.log(Status.INFO, "click on first Matched Spec number");
		matchedSpecs.get(0).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		return new HighlightKeywordsPage(driver);

	}

	public String getFirstMatchedSpecNumberText() {
		return matchedSpecs.get(0).getText();
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

	// Click on first Specs.
	public void clickOnFirstSpecCheckbox() {
		extentTest.log(Status.INFO, "Click on first specs.");
		if (specCheckboxList.size() > 0) {
			specCheckboxList.get(0).click();
		} else {
			extentTest.log(Status.INFO, "No specs are available to select.");
		}
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
	
	public boolean isfirmsLinkMenuDisplayed() {
		extentTest.log(Status.INFO, "verify if firmsLink is displayed ");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(firmsLink));
		return firmsLink.isDisplayed();
	}
	
	public boolean isplanholdersBiddersLinkDisplayed() {
		extentTest.log(Status.INFO, "verify if firmsLink is displayed ");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(planholdersBiddersLink));
		return planholdersBiddersLink.isDisplayed();
	}
	
	public boolean isPlansLinkDisplayed() {
		extentTest.log(Status.INFO, "Check if plans link is displa" + "yed");
		return plansLink.isDisplayed();
	}
	

	public void selectNewFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVAvailableFields.get(0))
				.click(downloadProjectPopUpCSVAvailableFields.get(3))
				.click(downloadProjectPopUpCSVAvailableFields.get(4))
				.click(downloadProjectPopUpCSVAvailableFields.get(6)).keyUp(Keys.CONTROL).build().perform();
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

	public String getTakeOffPartnerAreaHeader() {
		extentTest.log(Status.INFO, "Verify 'explore our takeoff partners' area header");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(takeoffPartnerAreaHeader));
		return takeoffPartnerAreaHeader.getText().trim();
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

	// Get text off Install eTakeOff Menu.
	public String getInstallETakeOffActionMenuText() {
		extentTest.log(Status.INFO, "Get text of Install eTakeOff Menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(installETaakeOffActionMenu));
		return installETaakeOffActionMenu.getText().trim();
	}

	// Is 'o' in 'Install eTakeoff' is lowercase.
	public boolean isOInstalleTakeoffLoweCase() {
		extentTest.log(Status.INFO, "Verify 'o' in 'Install eTakeoff' is lowercase");

		String menuText = getInstallETakeOffActionMenuText();
		return (Character.isLowerCase(menuText.charAt(13)));
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
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(installETaakeOffActionMenu));
		installETaakeOffActionMenu.click();
		CommonUtils.switchToNewTab(driver);
	}

	public String getNewTabUrl() {
		extentTest.log(Status.INFO, "Get new window URL");
		return driver.getCurrentUrl();
	}

	public void closeNewTab() {
		extentTest.log(Status.INFO, "Close new window");
		CommonUtils.switchToHomeTab(driver);
	}

	// Get text off 'Download eTakeOff File' Menu.
	public String getDownloadETakeOffFileText() {
		extentTest.log(Status.INFO, "Get text of 'Download eTakeOff File' Menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadetakeOffMenu));
		return downloadetakeOffMenu.getText().trim();
	}

	// Is 'o' in 'eTakeoff' is lowercase.
	public boolean isOInDownloadeTakeoffFileLoweCase() {
		extentTest.log(Status.INFO, "Verify 'o' in 'eTakeoff' in 'Download eTakeoff file' is lowercase");

		String menuText = getDownloadETakeOffFileText();
		return (Character.isLowerCase(menuText.charAt(14)));
	}

	public void clickOnDownloadeTakeoffFile() {
		extentTest.log(Status.INFO, "Clicking on Download eTakeoff file");
		downloadetakeOffMenu.click();
	}

	public boolean specDisplay() {
		extentTest.log(Status.INFO, "spec alert is  Display ");
		return specDisplay.isDisplayed();

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
	
	/***
	 * Check As the toggle is OFF, all spec documents are listed, regardless of whether they are highlighted or not.
	 * @return : Get boolean result
	 */
	public boolean checkUnHighlightedSpecListedWhenToggleBtnOFF() {
		extentTest.log(Status.INFO,
				"Check As the toggle is OFF, all spec documents are listed, regardless of whether they are highlighted or not.");
		return specNumberNotMatched.size() > 0;
	}
	
	/***
	 * Check As the toggle is ON,  only those spec documents should listed which are highlighted because spec match found only for search keyword.
	 * @return : Get boolean result
	 */
	public boolean checkHighlightedSpecListedWhenToggleBtnON() {
		extentTest.log(Status.INFO,
				"Check As the toggle is ON,  only those spec documents should listed which are highlighted because spec match found only for search keyword.");
		return (specNumberMatched.size() > 0 && !(SeleniumUtils.isVisible(specNumberNotMatched.get(0), driver)));
	}
	
	public boolean isAddendaLinkIsDisplayed() {
		extentTest.log(Status.INFO, "Check if Addenda link is displayed");
		return CommonUtils.checkElementExist(addendaLink, driver);
	}
	
	public boolean isNotesLinkIsDisplayed() {
		extentTest.log(Status.INFO, "Check if notes link is displayed");
		return CommonUtils.checkElementExist(notesLink, driver);
	}
	
	public boolean isDesignAlertsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the design alerts link is displayed");
		try {
			return linkDesignAlerts.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}
		
	public boolean isSpecLinkHighlightedInYellow() {
		extentTest.log(Status.INFO, "check if spec link is highlighted");
		return SeleniumUtils.isVisible(displaySpecLink, driver);
	}
}

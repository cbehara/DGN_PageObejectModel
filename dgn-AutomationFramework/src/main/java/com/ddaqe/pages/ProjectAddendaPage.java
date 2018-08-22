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

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectAddendaPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptAddendaInfo_ctl01_addTittle")
	private WebElement firstAddendainGrid;

	@FindBy(how = How.XPATH, using = "//div[@class='item-link div-table-col addnumber']")
	private List<WebElement> allAddendaList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionMenu;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'rptNavigator_actionsNav')]//a")
	private List<WebElement> addendaActionsOptionsList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnPrint")
	private WebElement printAddendaListActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkPrintProjectDetails")
	private WebElement printProjectDetailsActionMenu;

	@FindBy(how = How.ID, using = "lnkAddnotes")
	private WebElement addNotesActionMenu;

	@FindBy(how = How.ID, using = "main-content-right")
	private WebElement LatestUpdateAddendaPosition;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkReport")
	private WebElement projectTabOnAddendaPage;

	@FindBy(how = How.ID, using = "project-check-all")
	private WebElement selectAllAddendaChk;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_etakeoffdwnload")
	private WebElement installETaakeOffActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnPrint")
	private WebElement printPlanListActionMenu;

	@FindBy(how = How.XPATH, using = "//input[@class='checkbox' and @type='checkbox']")
	private List<WebElement> addendaCheckboxList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'addTittle')]")
	private List<WebElement> addendaTitleList;
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

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblDrNumber")
	private WebElement drNumber;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnTakeoff")
	private WebElement downloadetakeOffMenu;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'rptNavigator_actionsNav')]//a")
	private List<WebElement> actionOptionList;

	public ProjectAddendaPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Addenda Page");
	}

	public ProjectAddendaDetailsPage clickOnFirstAddendaInGrid() {
		extentTest.log(Status.INFO, "Click on first addenda in grid");
		firstAddendainGrid.click();
		return new ProjectAddendaDetailsPage(driver);
	}

	public String getCurrentURl() {
		return driver.getCurrentUrl();
	}

	// Check the list of addenda document is present by checking its list size >
	// 0
	public boolean checkAddendaListPresent() {
		extentTest.log(Status.INFO, "Verify List of Addenda document by checking its size.");
		return allAddendaList.size() > 0;
	}

	// Check Actions dropdown menu present on Addenda page
	public boolean isActionMenuPreset() {
		extentTest.log(Status.INFO, "Check if Actions dropdown menu present on Addenda page.");
		return CommonUtils.checkElementExist(actionMenu, driver);
	}

	// Click on Actions dropdown menu present on Addenda pag
	public void clickOnActionMenu() {
		extentTest.log(Status.INFO, "Click on Actions dropdown menu present on Addenda page.");
		SeleniumUtils.isClickable(selectAllAddendaChk, driver);
		actionMenu.click();
	}

	// Verify options of Addenda Actions List and return the result.
	public boolean verifyAddendaActionsList() {
		extentTest.log(Status.INFO, "Verify options of Addenda Actions List.");
		boolean actionOptionFlag = false;

		for (WebElement addendaAction : addendaActionsOptionsList) {
			if (addendaAction.getText().trim() != null && addendaAction.getText().trim().length() > 0) {
				actionOptionFlag = false;
				for (DGNEnum.addendaActionsOptions opt : DGNEnum.addendaActionsOptions.values()) {
					if (opt.getActionsOption().trim().equalsIgnoreCase(addendaAction.getText().trim())) {
						actionOptionFlag = true;
						break;
					}
				}
			}
			if (actionOptionFlag == false) {
				extentTest.log(Status.INFO,
						addendaAction.getText().trim() + " option is not present in the expected Actions List.");
				break;
			}
		}

		return actionOptionFlag;
	}

	// Click on Print Addenda List action menu and return object of Print
	// Project detail page
	public PrintProjectDetailsPage clickOnPrintAddendaListActionMenu() {
		extentTest.log(Status.INFO, "Click on Print Addenda List action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printAddendaListActionMenu, driver);
		printAddendaListActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	// Click on Print Project Details action menu and return object of Print
	// Project detail page
	public PrintProjectDetailsPage clickOnPrintProjectDetailstActionMenu() {
		extentTest.log(Status.INFO, "Click on Print Project Details action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printProjectDetailsActionMenu, driver);
		printProjectDetailsActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	// Click on Add Notes action menu and return object of Print Project detail
	// page.
	public PrintProjectDetailsPage clickOnAddNotesActionMenu() {
		extentTest.log(Status.INFO, "Click on Print Project Details action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(addNotesActionMenu, driver);
		addNotesActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	// Verfiy Addenda Latest Update is displayed at right side of the page
	public boolean verifyAddendaLatestUpdateAtRightSide() {
		extentTest.log(Status.INFO, "Verfiy Addenda Latest Update is displayed at right side of the page.");
		String expPosition = "right";
		String position = LatestUpdateAddendaPosition.getCssValue("float");
		return position.toLowerCase().trim().equals(expPosition);
	}

	// Click on Project tab and return the object of ProjectPage
	@Override
	public ProjectPage clickOnProjectTab() {
		extentTest.log(Status.INFO, "Click on Project tab present on Addenda page.");
		projectTabOnAddendaPage.click();
		return new ProjectPage(driver);
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

	// Click on first addenda.
	public void clickOnFirstAddendaCheckbox() {
		extentTest.log(Status.INFO, "Click on first addenda.");
		if (addendaCheckboxList.size() > 0) {
			addendaCheckboxList.get(0).click();
		} else {
			extentTest.log(Status.INFO, "No addenda are available to select.");
		}
	}

	// Click on first addenda Title.
	public String getFirstAddendaTitle() {
		extentTest.log(Status.INFO, "Click on first addenda Title.");
		if (addendaTitleList.size() > 0) {
			return addendaTitleList.get(0).getText();
		} else {
			extentTest.log(Status.INFO, "No addenda title are available to select.");
		}
		return null;
	}

	// Click on first addenda Title.
	public ProjectAddendaDetailsPage clickOnFirstAddendaTitle() {
		extentTest.log(Status.INFO, "Click on first addenda Title.");
		if (addendaTitleList.size() > 0) {
			addendaTitleList.get(0).click();
		} else {
			extentTest.log(Status.INFO, "No addenda title are available to select.");
		}
		return new ProjectAddendaDetailsPage(driver);
	}

	public void clickOnCSVRadioBtn() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		csvRadioBtn.click();
		SeleniumUtils.isVisible(selectATemplateDropDown, driver);
	}

	// Stack Integration related methods (takeoff partners)

	public boolean isTakeOffPartnerAreaDisplayed() {
		extentTest.log(Status.INFO, "Verify 'explore our takeoff partners' area is dislayed");
		return takeoffPartnerArea.isDisplayed();
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

	public String getNewTabUrl() {
		extentTest.log(Status.INFO, "Get new window URL");
		return driver.getCurrentUrl();
	}

	public void closeNewTab() {
		extentTest.log(Status.INFO, "Close new window");
		CommonUtils.switchToHomeTab(driver);
	}

	public String getDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the addenda Tab");
		String dr = drNumber.getText();
		int endIndex1 = drNumber.getText().lastIndexOf(" ");
		return dr.substring(0, endIndex1);
	}

	// Get text off 'Download eTakeOff File' Menu.
	public String getDownloadETakeOffFileText() {
		extentTest.log(Status.INFO, "Get text of 'Download eTakeOff File' Menu");
		return downloadetakeOffMenu.getText();
	}

	// Is 'o' in 'eTakeoff' is lowercase.
	public boolean isOInDownloadeTakeoffFileLoweCase() {
		extentTest.log(Status.INFO, "Verify 'o' in 'eTakeoff' in 'Download eTakeoff file' is lowercase");
		return (Character.isLowerCase(getDownloadETakeOffFileText().charAt(14)));
	}

	public void clickOnDownloadeTakeoffFile() {
		extentTest.log(Status.INFO, "Clicking on Download eTakeoff file");
		downloadetakeOffMenu.click();
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

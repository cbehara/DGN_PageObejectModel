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

public class ProjectAddendaDetailsPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//input[@filetype='Spec' and @type='checkbox']")
	private List<WebElement> addendaDetailSpecPlanChkList;
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

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_etakeoffdwnload")
	private WebElement installETaakeOffActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnPrint")
	private WebElement printPlanListActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblDrNumber")
	private WebElement drNumber;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkBtnTakeoff")
	private WebElement downloadetakeOffMenu;

	public ProjectAddendaDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Addenda Details Page");
	}

	// Click on first addenda.
	public void clickOnFirstSpecPlanAddendaCheckbox() {
		extentTest.log(Status.INFO, "Click on first addenda.");
		if (addendaDetailSpecPlanChkList.size() > 0) {
			addendaDetailSpecPlanChkList.get(0).click();
		} else {
			extentTest.log(Status.INFO, "No addenda detail Spec/Plan are available to select.");
		}
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

	public void clickOnAddBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOnAddButtonOnPopUPFrame.click();
	}
	// Stack Integration related methods (takeoff partners)

	public boolean isTakeOffPartnerAreaDisplayed() {
		extentTest.log(Status.INFO, "Verify 'explore our takeoff partners' area is dislayed");
		return takeoffPartnerArea.isDisplayed();
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
		SeleniumUtils.isVisible(installETaakeOffActionMenu, driver);
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

	public String getDRNumber() {
		extentTest.log(Status.INFO, "Get the DR Number of the Selected Project from the addenda Tab");
		String dr = drNumber.getText();
		int endIndex1 = dr.lastIndexOf(" ");
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
}

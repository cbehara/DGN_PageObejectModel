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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectFirmsPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl00_lnkCompanyNameSort")
	private WebElement firmsResults;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	private WebElement actionsDropDownDownloadProject;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement actionsDropDownDownloadFirms;

	@FindBy(how = How.ID, using = "rbPdf")
	private WebElement downDownloadProjectPDF;

	@FindBy(how = How.ID, using = "rbCSV")
	private WebElement downDownloadProjectCSV;

	@FindBy(how = How.ID, using = "rbXml")
	private WebElement downDownloadProjectXML;

	@FindBy(how = How.ID, using = "downloadProjectSubmit")
	private WebElement downDownloadProjectDownloadButton;

	@FindBy(how = How.ID, using = "downloadProjectClose")
	private WebElement downDownloadProjectCancelButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='divProjectDetails']//span[contains(@id,'lblRole')]")
	private List<WebElement> firmTypesOnPage;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl00_ddlFirmTypes")
	private WebElement firmTypesDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl00_lnkCompanyNameSort")
	private WebElement firmsGrid;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl01_hypFirmLink")
	private WebElement firstCompanyinList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl01_checkSelect")
	private WebElement firstCompanycheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl02_checkSelect")
	private WebElement secondCompanycheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl02_hypFirmLink")
	private WebElement secondCompanyinList;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'contentPlaceHolderHeader_rptNavigator_lnkNotes')]")
	private WebElement notesTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl00_lnkCompanyNameSort")
	private WebElement companyNameColumnHeader;

	@FindBy(how = How.ID, using = ".//a[contains(@id,'hypFirmLink')]")
	private List<WebElement> companyNameList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptFirmDetails_ctl00_imgSort")
	private WebElement companyNamesortIcon;

	@FindBy(how = How.XPATH, using = ".//*[@id='prev-next-nav']/div[1]")
	private WebElement bottomPagination;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;
	@FindBy(how = How.CSS, using = ".data-top-left p")
	private WebElement companyAddress;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement downloadFirmActionMenu;

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

	public ProjectFirmsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Firms Page");
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	public Boolean isFirmsResultSectionDisplayed() {
		extentTest.log(Status.INFO, "Check if Firms Result section is displayed");
		return firmsResults.isDisplayed();
	}

	public Boolean isBottomPaginationDisplayed() {
		extentTest.log(Status.INFO, "Check if bottom pagination is displayed");
		return bottomPagination.isDisplayed();
	}

	public void clickOnActionsDropdown() {
		extentTest.log(Status.INFO, "Clicking on Actions Dropdown");
		SeleniumUtils.isVisible(actionsDropdown, driver);
		actionsDropdown.click();
	}

	public CompanyPage clickOnFirstCompanyInList() {
		extentTest.log(Status.INFO, "Clicking on first Company in Firms Page");
		firstCompanyinList.click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnSecondCompanyInList() {
		extentTest.log(Status.INFO, "Clicking on second Company in Firms Page");
		secondCompanyinList.click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnFirstCompanycheckbox() {
		extentTest.log(Status.INFO, "Clicking on first Company checkbox");
		firstCompanycheckbox.click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnSecondCompanycheckbox() {
		extentTest.log(Status.INFO, "Clicking on second Company checkbox");
		secondCompanycheckbox.click();
		return new CompanyPage(driver);
	}

	@Override
	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects");
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public Boolean IsFirmsGridDisplayed() {
		extentTest.log(Status.INFO, "Verify if firms grid is displayed");
		return firmsGrid.isDisplayed();
	}

	public Boolean verifyValuesInFirmTypesDropdown() {
		int size = 0;
		extentTest.log(Status.INFO, "Verify if Firm types dropdown has all required values");
		String dropdownValues = "";
		Select select = new Select(firmTypesDropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement optionValues : options) {
			dropdownValues = dropdownValues + optionValues.getText();
		}

		for (WebElement firmTypevalues : firmTypesOnPage) {
			size = firmTypevalues.getText().length();
			String firmTypeValue = firmTypevalues.getText().substring(1, size - 1);
			if (!(dropdownValues.toLowerCase().contains(firmTypeValue.toLowerCase())))
				return false;
		}
		return true;
	}

	public boolean verifyCompanyNameColumnSortingArrowSymbol(Boolean sortOrder) {
		if (sortOrder == true) {
			return companyNamesortIcon.getAttribute("src").contains("Images/icon-sort-up.gif");

		}
		if (sortOrder == false)
			return companyNamesortIcon.getAttribute("src").contains("Images/icon-sort-down.gif");
		return false;

	}

	public boolean verifyCompanyNameColumnSorting(Boolean sortOrder) {
		List<String> listElements = CommonUtils.getListFromWebElements(companyNameList);
		List<String> listArc = CommonUtils.sortWebElements(listElements, sortOrder);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public void clickCompanyNameColumnHeader() {
		extentTest.log(Status.INFO, "Click company name column header");
		companyNameColumnHeader.click();
	}

	public DownloadProjects clickActionsDropDownDownloadProject() {
		extentTest.log(Status.INFO, "Click on download projects in actions dropdown");
		actionsDropDownDownloadProject.click();
		return new DownloadProjects(driver);
	}

	public void clickActionsDropDownDownloadFirms() {
		extentTest.log(Status.INFO, "Click on download firms in actions dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(actionsDropDownDownloadFirms));
		actionsDropDownDownloadFirms.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickDownDownloadProjectPDF() {
		extentTest.log(Status.INFO, "Click PDF option in download projects popup");
		downDownloadProjectPDF.click();
	}

	public void clickDownDownloadProjectCSV() {
		extentTest.log(Status.INFO, "Click CSV option in download projects popup");
		downDownloadProjectCSV.click();
	}

	@Override
	public DownloadProjects mouseOverActionandClickDownloadProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(actionsDropDownDownloadProject));
		actionsDropDownDownloadProject.click();

		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);

		return new DownloadProjects(driver);
	}

	public void clickDownDownloadProjectXML() {
		extentTest.log(Status.INFO, "Click XML option in download projects popup");
		downDownloadProjectXML.click();
	}

	public void clickDownDownloadProjectCancelButton() {
		extentTest.log(Status.INFO, "Click cancel button in download projects popup");
		downDownloadProjectCancelButton.click();
	}

	public void clickDownDownloadProjectDownloadButton() {
		extentTest.log(Status.INFO, "Click download button in download projects popup");
		downDownloadProjectDownloadButton.click();
	}

	public String getCompanyAddressOnProjectFirmPage() {
		extentTest.log(Status.INFO, "getting compnay address on Project firm page.");
		return companyAddress.getText();
	}

	public ProjectResultsPage mouseOverActionandClickOnDownloadFirms() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Check if Download Firms is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadFirmActionMenu));
		downloadFirmActionMenu.click();
		return new ProjectResultsPage(driver);
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
}

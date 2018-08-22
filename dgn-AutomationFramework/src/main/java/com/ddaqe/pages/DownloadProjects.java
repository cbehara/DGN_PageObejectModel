package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class DownloadProjects {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "rbPdf")
	private WebElement pdfRadioBtn;

	@FindBy(how = How.ID, using = "rbCSV")
	private WebElement csvRadioBtn;

	@FindBy(how = How.ID, using = "rbXml")
	private WebElement xmlRadioBtn;

	@FindBy(how = How.ID, using = "downloadProjectSubmit")
	private WebElement downloadButton;

	@FindBy(how = How.ID, using = "downloadProjectClose")
	private WebElement cancelButton;

	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement selectATemplateDropDown;

	@FindBy(how = How.ID, using = "lsTemplateFields")
	private WebElement availableFieldsMultiSelectBox;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement myTemplateNameTxt;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement createBtn;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement addBtn;

	@FindBy(how = How.ID, using = "listRemove")
	private WebElement selectedFieldsMultiSelectBox;

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement savedSingleFileTextField;

	@FindBy(how = How.XPATH, using = "//*[@id='rbXml']/../..//*[@class='batchText']")
	private WebElement batchDownloadWarningMessageXML;

	@FindBy(how = How.XPATH, using = "//*[@id='rbXml']/../..//*[@class='batchText']/span")
	private WebElement clickHereToSeeWhyLinkXML;

	@FindBy(how = How.XPATH, using = "//*[@id='rbXml']/../..//*[@class='batchDownloadInfoSection']")
	private WebElement clickHereToSeeWhyLinkToolTipBoxXML;

	@FindBy(how = How.XPATH, using = "//*[@id='rbXml']/../..//*[@class='batchDownloadInfoSection']//*[@class='batchDownloadText']/div[2]")
	private WebElement clickHereToSeeWhyLinkToolTipTextXML;

	@FindBy(how = How.XPATH, using = "//*[@id='rbPdf']/../..//*[@class='batchText']")
	private WebElement batchDownloadWarningMessagePdf;

	@FindBy(how = How.XPATH, using = "//*[@id='rbPdf']/../..//*[@class='batchText']/span")
	private WebElement clickHereToSeeWhyLinkPdf;

	@FindBy(how = How.XPATH, using = "//*[@id='rbPdf']/../..//*[@class='batchDownloadInfoSection']")
	private WebElement clickHereToSeeWhyLinkToolTipBoxPdf;

	@FindBy(how = How.XPATH, using = "//*[@id='rbPdf']/../..//*[@class='batchDownloadInfoSection']//*[@class='batchDownloadText']/div[2]")
	private WebElement clickHereToSeeWhyLinkToolTipTextPdf;

	@FindBy(how = How.XPATH, using = "//*[@id='rbCSV']/../..//*[@class='batchText']")
	private WebElement batchDownloadWarningMessageCsv;

	@FindBy(how = How.XPATH, using = "//*[@id='rbCSV']/../..//*[@class='batchText']/span")
	private WebElement clickHereToSeeWhyLinkCsv;

	@FindBy(how = How.XPATH, using = "//*[@id='rbCSV']/../..//*[@class='batchDownloadInfoSection']")
	private WebElement clickHereToSeeWhyLinkToolTipBoxCsv;

	@FindBy(how = How.XPATH, using = "//*[@id='rbCSV']/../..//*[@class='batchDownloadInfoSection']//*[@class='batchDownloadText']/div[2]")
	private WebElement clickHereToSeeWhyLinkToolTipTextCsv;

	@FindBy(how = How.XPATH, using = "//input[@class='batchNameTxtBox']")
	private WebElement batchNameTxtBox;

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement downloadNameTxt;

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement downloadNameTxtSpecAlerts;

	@FindBy(how = How.ID, using = "rbSingleFile")
	private WebElement saveToSingleFileRadioBtn;

	@FindBy(how = How.ID, using = "rbSeparateFiles")
	private WebElement saveToSeparateFileRadioBtn;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/delayed-load-with-fade.gif']")
	private List<WebElement> delayImageDialog;

	@FindBy(how = How.ID, using = "divDownloadPopupHeader")
	private WebElement downloadProjectsPopupHeader;

	@FindBy(how = How.ID, using = "downloadCompaniesPopup")
	private WebElement downloadProjectsPopupDialog;

	@FindBy(how = How.XPATH, using = ".//*[@class='batchNameLabel']")
	private WebElement downloadNameTextOnProjectsPopupDialog;

	@FindBy(how = How.ID, using = "divDownloadFormatMsg")
	private WebElement downloadFormatMsg;

	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement downloadProjectsCSVSelectTemplateDropDown;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- ALL FIELDS --']")
	private WebElement selectAllFieldOption;

	@FindBy(how = How.XPATH, using = ".//div[@id='divCreateTemplate']//input")
	private WebElement templateNameAndCreatebutton;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement clickOncraeteButton;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement clickOnAddButtonOnPopUPFrame;

	@FindBy(how = How.XPATH, using = ".//*[@id='listRemove']/option")
	private List<WebElement> downloadProjectPopUpCSVSelectedFields;

	@FindBy(how = How.CSS, using = "#drpCSVTemplateName option")
	private List<WebElement> downloadProjectPopUpCSVTemplateList;

	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadProjectPopUpCSVAvailableFields;

	@FindBy(how = How.ID, using = "btnUp")
	private WebElement clickOnUpButton;
	@FindBy(how = How.ID, using = "btnRemove")
	private WebElement clickOnRemoveButton;
	@FindBy(how = How.ID, using = "btnDown")
	private WebElement clickOnDownButton;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement enterTeplateName;
	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement clickOnCreateBtn;
	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement selectTemplateDropDowndisplayed;

	public DownloadProjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public void clickOnPDFRadioBtn() {
		extentTest.log(Status.INFO, "Click on PDF Radio Button.");
		pdfRadioBtn.click();
	}

	public void clickOnCSVRadioBtn() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		SeleniumUtils.isVisible(csvRadioBtn, driver);
		csvRadioBtn.click();
		SeleniumUtils.isVisible(selectATemplateDropDown, driver);
	}

	public void clickOnXMLRadioBtn() {
		extentTest.log(Status.INFO, "Click on XML Radio Button.");
		xmlRadioBtn.click();
	}

	public void clickOnDownloadBtn() {
		extentTest.log(Status.INFO, "Click on Download Button.");
		downloadButton.click();
		SeleniumUtils.isLoadingIconInvisible(delayImageDialog, driver);
	}

	public void clickOnCancelBtn() {
		extentTest.log(Status.INFO, "Click on Cancel Button.");
		SeleniumUtils.isVisible(cancelButton, driver);
		cancelButton.click();
	}

	public boolean isVerticalScrollBarVisibleOnDownloadProjectsPopup() {
		extentTest.log(Status.INFO, "Verify if vertical scroll bar is present or not on the Download Projects Popup.");
		boolean isVerticalScrollBarVisible = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return $('#downloadCompaniesPopup').scrollHeight>document.documentElement.clientHeight;");
		return isVerticalScrollBarVisible;
	}

	public boolean isSelectATemplateDropDownVisible() {
		extentTest.log(Status.INFO,
				"Verify if Select A Template drop-down visible on the Download Projects Popup - CSV.");
		return selectATemplateDropDown.isDisplayed();
	}

	public boolean isAvailableFieldsMultiSelectBoxVisible() {
		extentTest.log(Status.INFO, "Verify if Available Fields visible on the Download Projects Popup - CSV.");
		return availableFieldsMultiSelectBox.isDisplayed();
	}

	// check SPECALERTS is a last option in available field
	public boolean isSPECALERTSVisibleAtTheLastInAvailableFields() {
		extentTest.log(Status.INFO,
				"Verify if SPECALERTS value is visible at the last in the Available Fields multi-select drop-down.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = availableFieldsMultiSelectBoxSelect.getOptions();
		if (selectedFieldsOptions.get(selectedFieldsOptions.size() - 1).getText().equals("SPECALERTS")) {
			return true;
		}
		return false;
	}

	// select available field dropdown value.
	public void selectAvailableFieldsDropdownOption(String valueToSelect) {
		extentTest.log(Status.INFO, "Select the option from Available Fields multi-select drop-down.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		availableFieldsMultiSelectBoxSelect.selectByVisibleText(valueToSelect);
	}

	// Check the options case of Available Fields multi-select drop-down is in
	// uppercase.
	public boolean checkAvailableFieldOptionCase() {
		extentTest.log(Status.INFO,
				"Check the options case of Available Fields multi-select drop-down is in uppercase.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		if (availableFieldsMultiSelectBoxSelect.getOptions().size() != 0) {
			for (WebElement element : availableFieldsMultiSelectBoxSelect.getOptions()) {
				for (char charOfOption : element.getText().toCharArray()) {
					if (Character.isLetter(charOfOption) && Character.isLowerCase(charOfOption)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// Click on the Create button to create a template.
	public void clickOnCreateMyTemplateButton() {
		extentTest.log(Status.INFO, "Click on the Create button to create a template.");
		createBtn.click();
	}

	// Click on the Add button to add the selected available field option
	public void clickOnAddAvailableFieldButton() {
		extentTest.log(Status.INFO, "Click on the Add button to add the selected available field option.");
		addBtn.click();
	}

	public boolean isSelectedFieldsMultiSelectBoxVisible() {
		extentTest.log(Status.INFO, "Verify if Selected Fields visible on the Download Projects Popup - CSV.");
		return selectedFieldsMultiSelectBox.isDisplayed();
	}

	// Check the options case of Selected Fields multi-select drop-down is in
	// uppercase.
	public boolean checkSelectedFieldOptionCase() {
		extentTest.log(Status.INFO,
				"Check the options case of Selected Fields multi-select drop-down is in uppercase.");
		Select selectedFieldsMultiSelectBoxSelect = new Select(selectedFieldsMultiSelectBox);
		if (selectedFieldsMultiSelectBoxSelect.getOptions().size() != 0) {
			for (WebElement element : selectedFieldsMultiSelectBoxSelect.getOptions()) {
				for (char charOfOption : element.getText().toCharArray()) {
					if (Character.isLetter(charOfOption) && Character.isLowerCase(charOfOption)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String getDefaultSelectedValueOfSelectATemplateDropDown() {
		extentTest.log(Status.INFO, "Get default selected value in Select A Template drop-down.");
		Select selectATemplate = new Select(selectATemplateDropDown);
		return selectATemplate.getFirstSelectedOption().getText();
	}

	// Select Different value/option from Template dropdown.
	public void selectOptionFromTemplateDropDown(String optionToSelect) {
		extentTest.log(Status.INFO, "Select Different value/option from Template dropdown.");
		Select selectATemplate = new Select(selectATemplateDropDown);
		selectATemplate.selectByVisibleText(optionToSelect);
	}

	public boolean isSPECALERTSVisibleAtTheLast() {
		extentTest.log(Status.INFO,
				"Verify if SPECALERTS value is visible at the last in the Selected Fields multi-select drop-down.");
		Select selectedFieldsMultiSelectBoxSelect = new Select(selectedFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		if (selectedFieldsOptions.get(selectedFieldsOptions.size() - 1).getText().equals("SPECALERTS")) {
			return true;
		}
		return false;
	}

	public String getDownloadFileName() {
		extentTest.log(Status.INFO, "Get download file name.");
		return savedSingleFileTextField.getAttribute("value");
	}

	// Set the radio button according to the option/parameter for download batch
	// dialog.
	public void setDownloadBatchFormath(int radioOptionToSelect) {
		extentTest.log(Status.INFO,
				"Set the radio button according to the option/parameter for download batch dialog.");
		switch (radioOptionToSelect) {
		case 1:
			clickOnPDFRadioBtn();
			break;
		case 2:
			clickOnCSVRadioBtn();
			break;
		case 3:
			clickOnXMLRadioBtn();
			break;
		}
	}

	// Set the set My Template Name text field
	public void setMyTemplateName(String downloadName) {
		extentTest.log(Status.INFO, "Set the set My Template Name text field.");
		myTemplateNameTxt.clear();
		myTemplateNameTxt.sendKeys(downloadName);
	}

	// Set the download name in download name text field
	public void setDownloadName(String downloadName) {
		extentTest.log(Status.INFO, "Set the download name in download name text field.");
		downloadNameTxt.clear();
		downloadNameTxt.sendKeys(downloadName);
	}

	public void setDownloadBatchName(String downloadBatchName) {
		extentTest.log(Status.INFO, "Set the download batch name in download batch name text field.");
		batchNameTxtBox.clear();
		batchNameTxtBox.sendKeys(downloadBatchName);
	}

	// Set the download name in download name text field
	public void setDownloadNameSpecAlerts(String downloadName) {
		extentTest.log(Status.INFO, "Set the download name in download name text field.");
		SeleniumUtils.isVisible(downloadNameTxtSpecAlerts, driver);
		downloadNameTxtSpecAlerts.click();
		downloadNameTxtSpecAlerts.clear();
		downloadNameTxtSpecAlerts.sendKeys(downloadName);
	}

	public String getBatchDownloadName() {
		extentTest.log(Status.INFO, "Get the download name in download name text field.");
		return downloadNameTxt.getAttribute("value");
	}

	public boolean isBatchDownloadWarningMessageXMLVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for XML.");
		return batchDownloadWarningMessageXML.isDisplayed();
	}

	public boolean isBatchDownloadWarningMessagePdfVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for PDF.");
		return batchDownloadWarningMessagePdf.isDisplayed();
	}

	public boolean isBatchDownloadWarningMessageCsvVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for CSV.");
		return batchDownloadWarningMessageCsv.isDisplayed();
	}

	public String getBatchDownloadWarningMessageXML() {
		extentTest.log(Status.INFO, "Get batch download warning message for XML.");
		return batchDownloadWarningMessageXML.getText();
	}

	public String getBatchDownloadWarningMessagePdf() {
		extentTest.log(Status.INFO, "Get batch download warning message for PDF.");
		return batchDownloadWarningMessagePdf.getText();
	}

	public String getBatchDownloadWarningMessageCsv() {
		extentTest.log(Status.INFO, "Get batch download warning message for CSV.");
		return batchDownloadWarningMessageCsv.getText();
	}

	public void clickOnClickHereToSeeWhyLinkXML() {
		extentTest.log(Status.INFO, "Click on Click Here To See Why Link for XML.");
		clickHereToSeeWhyLinkXML.click();
	}

	public void clickOnClickHereToSeeWhyLinkPdf() {
		extentTest.log(Status.INFO, "Click on Click Here To See Why Link for PDF.");
		clickHereToSeeWhyLinkPdf.click();
	}

	public void clickOnClickHereToSeeWhyLinkCsv() {
		extentTest.log(Status.INFO, "Click on Click Here To See Why Link for CSV.");
		clickHereToSeeWhyLinkCsv.click();
	}

	public boolean isClickHereToSeeWhyTooltipXMLVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why tool tip is visible for XML.");
		return clickHereToSeeWhyLinkToolTipBoxXML.isDisplayed();
	}

	public boolean isClickHereToSeeWhyTooltipPdfVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why tool tip is visible for PDF.");
		return clickHereToSeeWhyLinkToolTipBoxPdf.isDisplayed();
	}

	public boolean isClickHereToSeeWhyTooltipCsvVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why tool tip is visible for CSV.");
		return clickHereToSeeWhyLinkToolTipBoxCsv.isDisplayed();
	}

	public String getClickHereToSeeWhyTooltipTextXML() {
		extentTest.log(Status.INFO, "Get Click Here To See Why tool tip text for XML.");
		return clickHereToSeeWhyLinkToolTipBoxXML.getText();
	}

	public String getClickHereToSeeWhyTooltipTextPdf() {
		extentTest.log(Status.INFO, "Get Click Here To See Why tool tip text for PDF.");
		return clickHereToSeeWhyLinkToolTipBoxPdf.getText();
	}

	public String getClickHereToSeeWhyTooltipTextCsv() {
		extentTest.log(Status.INFO, "Get Click Here To See Why tool tip text for CSV.");
		return clickHereToSeeWhyLinkToolTipBoxCsv.getText();
	}

	public void clickOnSaveToSeparateFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to separate files radio button.");
		saveToSeparateFileRadioBtn.click();
	}

	public void clickOnSaveToSingleFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to single files radio button.");
		saveToSingleFileRadioBtn.click();
	}

	public boolean isDownloadProjectsPopupDisplayed() {
		return CommonUtils.checkElementExist(downloadProjectsPopupHeader, driver);
	}

	public boolean isDownloadProjectsPopupClosed() {
		return SeleniumUtils.isInvisible(downloadProjectsPopupHeader, driver);
	}

	// Check the download project button is displayed.
	public boolean isDownloadButtonDisplayed() {
		extentTest.log(Status.INFO, "Check the download project button is displayed.");
		boolean result = CommonUtils.checkElementExist(downloadButton, driver);
		return result;
	}

	public List<String> getSelectedFieldList() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedFieldPopUp : downloadProjectPopUpCSVSelectedFields) {
			selectedFilelds.add(selectedFieldPopUp.getText().trim());
		}
		return selectedFilelds;
	}

	public boolean isAvailableFieldsSelectDropDownEmpty() {
		extentTest.log(Status.INFO, "Verify Available Fields multi-select drop-down empty or not.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		return availableFieldsMultiSelectBoxSelect.getOptions().isEmpty();
	}

	public boolean isPDFRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "Check  PDF Radio Button is displayed.");
		return CommonUtils.checkElementExist(pdfRadioBtn, driver);
	}

	public boolean isCSVRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "Check CSV Radio Button is displayed.");
		return CommonUtils.checkElementExist(csvRadioBtn, driver);
	}

	public boolean isXMLRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "check XML Radio Button is displayed.");
		return CommonUtils.checkElementExist(xmlRadioBtn, driver);
	}

	public void clickOnSelectTemplateDropDown() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		downloadProjectsCSVSelectTemplateDropDown.click();
	}

	public void selectCreateNewTemplate() {
		extentTest.log(Status.INFO, "click On Creat new template.");
		selectCreatNewTemplateOption.click();
	}

	public void selectAllFieldOption() {
		extentTest.log(Status.INFO, "Click On All Field Option.");
		selectAllFieldOption.click();
	}

	public boolean isTemplateNameAndCreateButtonVisisble() {
		extentTest.log(Status.INFO, "Check template name and create button is displayed");
		return templateNameAndCreatebutton.isDisplayed();

	}

	public void clickOnCreateBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOncraeteButton.click();
	}

	public void clickOnAddBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOnAddButtonOnPopUPFrame.click();
	}

	public List<String> getTemplateList() {
		extentTest.log(Status.INFO, "get default template list");
		List<String> templateList = new ArrayList<String>();
		for (WebElement template : downloadProjectPopUpCSVTemplateList) {
			templateList.add(template.getText());
		}
		return templateList;
	}

	public boolean isSelectTemplateListDisplayed() {
		extentTest.log(Status.INFO, "get default template list");
		return selectTemplateDropDowndisplayed.isDisplayed();
	}

	public boolean confirmAlert(boolean confirm) {
		if (confirm) {
			driver.switchTo().alert().accept();
		}
		return confirm;

	}

	public void selectFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVAvailableFields.get(0))
				.click(downloadProjectPopUpCSVAvailableFields.get(3))
				.click(downloadProjectPopUpCSVAvailableFields.get(4))
				.click(downloadProjectPopUpCSVAvailableFields.get(6))
				.click(downloadProjectPopUpCSVAvailableFields.get(7)).keyUp(Keys.CONTROL).build().perform();
	}

	public void selectNewFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVAvailableFields.get(1))
				.click(downloadProjectPopUpCSVAvailableFields.get(3))
				.click(downloadProjectPopUpCSVAvailableFields.get(4))
				.click(downloadProjectPopUpCSVAvailableFields.get(6)).keyUp(Keys.CONTROL).build().perform();
	}

	public void selectRightFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVSelectedFields.get(0))
				.click(downloadProjectPopUpCSVSelectedFields.get(3)).keyUp(Keys.CONTROL).build().perform();
	}

	public void clickOnUpBtn() {
		extentTest.log(Status.INFO, "Click On Up Button.");
		clickOnUpButton.click();
	}

	public void clickOnRemoveBtn() {
		extentTest.log(Status.INFO, "Click On Remove Button.");
		clickOnRemoveButton.click();
	}

	public void selectFieldForUpDownButton() {
		extentTest.log(Status.INFO, "Select Field From Right side.");
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVSelectedFields.get(1)).keyUp(Keys.CONTROL).build()
				.perform();
	}

	public void clickOnDownBtn() {

		extentTest.log(Status.INFO, "Click On Up Button.");
		clickOnDownButton.click();

	}

	public List<String> getFieldList() {
		extentTest.log(Status.INFO, "Get Available Fileld List.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedField : downloadProjectPopUpCSVAvailableFields) {
			selectedFilelds.add(selectedField.getText());
		}
		return selectedFilelds;
	}

	public void getTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "MyNewCSVTemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void getGivenTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "mytemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void getCaseSensitiveTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "MyTemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void clickOnCreateButton() {
		extentTest.log(Status.INFO, "click On Create Button.");
		clickOnCreateBtn.click();

	}

	public String getSelectedTemplate() {
		Select select = new Select(downloadProjectPopUpCSVSelectedFields.get(6));
		return select.getFirstSelectedOption().getText();
	}

	public void getTemplateNameWithSpecialCharacter() {
		extentTest.log(Status.INFO, "Enter Template Name start With special Character");
		String templateNameWithspecialCharacter = "*MyTemplateCSV";
		enterTeplateName.sendKeys(templateNameWithspecialCharacter);
	}

	public boolean isFieldPresentInSelectedFields(String fieldToVerify) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present in Selected Fields.");
		Select selectedFieldsMultiSelectBoxSelect = new Select(selectedFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		for (WebElement w : selectedFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return true;
		}

		return false;

	}

	public boolean isFieldPresentInAvailableFields(String fieldToVerify) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present in Available Fields.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		List<WebElement> availableFieldsOptions = availableFieldsMultiSelectBoxSelect.getOptions();

		for (WebElement w : availableFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return true;
		}
		return false;
	}

	// Check if one field is displayed after another field in Selected fields
	// box
	public boolean isFieldPresentAfterFieldInSelectedFieldsBox(String fieldToVerify, String previousField) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present after" + previousField);
		Select selectedFieldsMultiSelectBoxSelect = new Select(selectedFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		int i = 0;
		for (WebElement w : selectedFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return selectedFieldsOptions.get(i - 1).getText().equals(previousField);
			i++;
		}

		return false;
	}

	// Check if one field is displayed before another field in Selected fields
	// box
	public boolean isFieldPresentBeforeFieldInSelectedFieldsBox(String fieldToVerify, String nextField) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present before" + nextField);
		Select selectedFieldsMultiSelectBoxSelect = new Select(selectedFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		int i = 0;
		for (WebElement w : selectedFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return selectedFieldsOptions.get(i + 1).getText().equals(nextField);
			i++;
		}

		return false;
	}

	// Check if one field is displayed after another field in Available fields
	// box
	public boolean isFieldPresentAfterFieldInAvailableFieldsBox(String fieldToVerify, String previousField) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present in before" + previousField);
		Select selectedFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		int i = 0;
		for (WebElement w : selectedFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return selectedFieldsOptions.get(i - 1).getText().equals(previousField);
			i++;
		}

		return false;
	}

	// Check if one field is displayed before another field in Available fields
	// box
	public boolean isFieldPresentBeforeFieldInAvailableFieldsBox(String fieldToVerify, String nextField) {
		extentTest.log(Status.INFO, "Check " + fieldToVerify + " is present in before" + nextField);
		Select selectedFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		List<WebElement> selectedFieldsOptions = selectedFieldsMultiSelectBoxSelect.getOptions();

		int i = 0;
		for (WebElement w : selectedFieldsOptions) {
			if (w.getText().equals(fieldToVerify))
				return selectedFieldsOptions.get(i + 1).getText().equals(nextField);
			i++;
		}

		return false;
	}

	public boolean isTemplateCreated(String templateName) {
		extentTest.log(Status.INFO, "Check if template is created/present in template list");
		Select templateDropDown = new Select(selectATemplateDropDown);
		List<WebElement> templateDropDownList = templateDropDown.getOptions();

		for (WebElement w : templateDropDownList) {
			if (w.getText().equals(templateName))
				return true;
		}
		return false;
	}

	public boolean isSaveToSeparateFilesRadioButtonDisplyed() {
		extentTest.log(Status.INFO, "Check Save to separate files radio button is displayed.");
		try {
			return saveToSeparateFileRadioBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSaveToSingleFilesRadioButtonDisplayed() {
		extentTest.log(Status.INFO, "Check Save to single files radio button is displayed.");
		try {
			return saveToSingleFileRadioBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Check Download Projects Popup Dialog Displayed.
	public boolean isDownloadCompaniesPopupDialogDisplayed() {
		extentTest.log(Status.INFO, "Check Download Projects Popup Dialog Displayed.");
		return CommonUtils.checkElementExist(downloadProjectsPopupDialog, driver);
	}

	// Check Download Name text in download projects popup
	public boolean isDownloadNameTextOnDownloadProjectsPopUpDisplayed() {
		extentTest.log(Status.INFO, "Check Download Name text on Download Projects Popup Dialog.");
		return CommonUtils.checkElementExist(downloadNameTextOnProjectsPopupDialog, driver);

	}

	// Check Download Companies header text.
	public String checkDownloadProjectsPopupDialogHeaderText() {
		extentTest.log(Status.INFO, "Check Download Companies Popup Dialog header text.");
		String headerText = downloadFormatMsg.getText();
		return headerText;
	}
}

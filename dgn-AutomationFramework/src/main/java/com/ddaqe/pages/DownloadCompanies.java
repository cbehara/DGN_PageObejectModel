package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
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

public class DownloadCompanies {

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

	@FindBy(how = How.ID, using = "lsTemplateFields")
	private WebElement availableFieldsInCustomtemplate;

	@FindBy(how = How.ID, using = "listRemove")
	private WebElement selectedFieldsInCustomtemplate;

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement savedSingleFileTextField;

	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement cSVTemplate;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement cSVTemplateName;

	@FindBy(how = How.ID, using = "btnUp")
	private WebElement customCSVUpButton;

	@FindBy(how = How.ID, using = "btnDown")
	private WebElement customCSVDownButton;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement cSVTemplateCreate;

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

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement downloadNameTxt;

	@FindBy(how = How.XPATH, using = "//input[@class='batchNameTxtBox']")
	private WebElement batchNameTxtBox;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/delayed-load-with-fade.gif']")
	private List<WebElement> delayImageDialog;

	@FindBy(how = How.ID, using = "rbSingleFile")
	private WebElement saveToSingleFileRadioBtn;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement addButtonCustomTemplate;

	@FindBy(how = How.ID, using = "btnRemove")
	private WebElement removeButtonCustomTemplate;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement createButtonCustomTemplate;

	@FindBy(how = How.ID, using = "rbSeparateFiles")
	private WebElement saveToSeparateFileRadioBtn;

	@FindBy(how = How.ID, using = "downloadCompaniesPopup")
	private WebElement downloadComapniesPopupDialog;

	@FindBy(how = How.ID, using = "saveProjectErrMessage")
	private WebElement errorMsgOnDownloadCompanies;

	@FindBy(how = How.ID, using = "divDownloadPopupHeader")
	private WebElement downloadCompanyPopupHeader;

	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement csvTemplateDropDown;

	@FindBy(how = How.ID, using = "lsTemplateFields")
	private WebElement availableFieldsMultiSelectBox;

	@FindBy(how = How.ID, using = "listRemove")
	private WebElement selectedFieldsMultiSelectBox;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement addBtn;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement myTemplateNameTxt;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement createBtn;

	@FindBy(how = How.ID, using = "divDownloadFormatMsg")
	private WebElement downloadFormatMsg;

	@FindBy(how = How.ID, using = "dvSaveToThisFile")
	private WebElement saveToThisFileText;

	@FindBy(how = How.XPATH, using = ".//*[@class='batchNameLabel']")
	private WebElement downloadNameTextOnComapniesPopupDialog;
	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadComapnyPopUpCSVFields;

	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadComapnyPopUpCSVAvailableFields;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement clickOnAddButtonOnPopUPFrame;

	@FindBy(how = How.XPATH, using = ".//*[@id='listRemove']/option")
	private List<WebElement> downloadCompanyPopUpCSVSelectedFields;
	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement enterTeplateName;

	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement clickOnCreateBtn;

	@FindBy(how = How.XPATH, using = ".//div[@id='divCreateTemplate']//input")
	private WebElement templateNameAndCreatebutton;

	public DownloadCompanies(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean isDownloadPopupHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify Download pop up header.");
		return CommonUtils.checkElementExist(downloadCompanyPopupHeader, driver);
	}

	public boolean isPDFRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "Click on PDF Radio Button.");
		return CommonUtils.checkElementExist(pdfRadioBtn, driver);
	}

	public boolean isCSVRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		return CommonUtils.checkElementExist(csvRadioBtn, driver);
	}

	public boolean isXMLRadioBtnDisplayed() {
		extentTest.log(Status.INFO, "Click on XML Radio Button.");
		return CommonUtils.checkElementExist(xmlRadioBtn, driver);
	}

	public void clickOnPDFRadioBtn() {
		extentTest.log(Status.INFO, "Click on PDF Radio Button.");
		pdfRadioBtn.click();
	}

	public String clickandReturnOnSelectedFieldboxOption(int i) {
		extentTest.log(Status.INFO, "Click on any particular option in select field box");
		Select sel = new Select(selectedFieldsInCustomtemplate);
		sel.deselectAll();
		List<WebElement> all = sel.getOptions();
		all.get(i).click();
		return all.get(i).getText();

	}

	public void clickOnCustomCSVDownButton() {
		extentTest.log(Status.INFO, "Click on up button while creating a custom template.");
		customCSVDownButton.click();
	}

	public void clickOnCustomCSVUpButton() {
		extentTest.log(Status.INFO, "Click on down button while creating a cutom template");
		customCSVUpButton.click();
	}

	public void clickOnAddButtonCustomTemplate() {
		extentTest.log(Status.INFO, "Click on add Button in Custom Template.");
		addButtonCustomTemplate.click();
	}

	public void clickOnRemoveButtonCustomTemplate() {
		extentTest.log(Status.INFO, "Click on remove Button in Custom Template.");
		removeButtonCustomTemplate.click();
	}

	public void clickOnCreateButtonCustomTemplate() {
		extentTest.log(Status.INFO, "Click on create Button in Custom Template.");
		createButtonCustomTemplate.click();
	}

	public Boolean isDisplayedCreateButtonCustomTemplate() {
		extentTest.log(Status.INFO, "checking for display of create Button in Custom Template.");
		return createButtonCustomTemplate.isDisplayed();
	}

	public String getAlertmessage() {
		extentTest.log(Status.INFO, "Getting the message in the alert");
		Alert al = driver.switchTo().alert();
		String message = al.getText();
		al.accept();
		return message;
	}

	public void clickOnCSVRadioBtn() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		csvRadioBtn.click();
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
		cancelButton.click();
	}

	public boolean isVerticalScrollBarVisibleOnDownloadCompaniesPopup() {
		boolean isVerticalScrollBarVisible = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return $('#downloadCompaniesPopup').scrollHeight>document.documentElement.clientHeight;");
		return isVerticalScrollBarVisible;
	}

	public String getDownloadFileName() {
		return savedSingleFileTextField.getAttribute("value");
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

	public String getBatchDownloadName() {
		extentTest.log(Status.INFO, "Get the download name in download name text field.");
		return downloadNameTxt.getAttribute("value");
	}

	public boolean isBatchDownloadWarningMessageXMLVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for XML.");
		return batchDownloadWarningMessageXML.isDisplayed();
	}

	public void selectTemplate(int index) {
		extentTest.log(Status.INFO, "selecting option in the template dropdown ");
		Select select = new Select(cSVTemplate);
		select.selectByIndex(index);

	}

	public Boolean isValuepresentInSelectTemplate(String toCheck) {
		extentTest.log(Status.INFO, "Checking for value present in Select Template dropdown ");
		Select select = new Select(cSVTemplate);

		List<WebElement> all = select.getOptions();
		List<String> str = CommonUtils.getListFromWebElements(all);

		for (String st : str) {
			if (st.equalsIgnoreCase(toCheck)) {
				return true;

			}
		}

		return false;

	}

	public Boolean isSelectTemplateDropDownSorted() {
		extentTest.log(Status.INFO,
				"Checking whether elements are sorted in proper order in Select Template dropdown ");
		Select select = new Select(cSVTemplate);

		List<WebElement> all = select.getOptions();

		List<String> str = CommonUtils.getListFromWebElements(all);
		List<String> sorted = CommonUtils.sortWebElements(str, true);

		Boolean value = CommonUtils.CompareTwoList(str, sorted);

		return value;
	}

	public String currentSelectedInSelectTemplate(String toCheck) {
		extentTest.log(Status.INFO, "Selected value in Select Template dropdown ");
		Select select = new Select(cSVTemplate);

		WebElement first = select.getFirstSelectedOption();
		String SeloptionName = first.getText();
		return SeloptionName;
	}

	public void selectOptionInAvailableField(int index) {
		extentTest.log(Status.INFO, "selecting option in Available Fields Selected Fields in custom template ");
		Select select = new Select(availableFieldsInCustomtemplate);
		select.selectByIndex(index);

	}

	public void selectOptionInSelectedField(int index) {
		extentTest.log(Status.INFO, "selecting option in Available Fields Selected Fields in custom template ");
		Select select = new Select(selectedFieldsInCustomtemplate);
		select.selectByIndex(index);

	}

	public void deselectAllOptionInSelectedField() {
		extentTest.log(Status.INFO, "selecting option in Available Fields Selected Fields in custom template ");
		Select select = new Select(selectedFieldsInCustomtemplate);
		select.deselectAll();

	}

	public void selectOptionMultipleOptionsInAvailableField() {
		extentTest.log(Status.INFO,
				"selecting multiple options in Available Fields Selected Fields in custom template ");
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL);
		selectOptionInAvailableField(1);
		selectOptionInAvailableField(2);
		selectOptionInAvailableField(3);
		selectOptionInAvailableField(4);
		act.keyUp(Keys.CONTROL);
		act.build().perform();

	}

	public void selectOptionMultipleOptionsInAvailableFieldUsingSHift() {
		extentTest.log(Status.INFO,
				"selecting multiple options in Available Fields Selected Fields in custom template ");
		Actions act = new Actions(driver);
		act.keyDown(Keys.SHIFT);
		selectOptionInAvailableField(1);
		selectOptionInAvailableField(2);
		selectOptionInAvailableField(6);
		selectOptionInAvailableField(5);
		act.keyUp(Keys.SHIFT);
		act.build().perform();

	}

	public void selectOptionMultipleOptionsInSelectedField() {
		extentTest.log(Status.INFO,
				"selecting multiple options in Selected Fields Selected Fields in custom template ");
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL);
		selectOptionInSelectedField(0);
		selectOptionInSelectedField(1);
		selectOptionInSelectedField(2);
		selectOptionInSelectedField(3);
		act.keyUp(Keys.CONTROL);
		act.build().perform();

	}

	public void selectOptionInselectedFieldsInCustomtemplate(int index) {
		extentTest.log(Status.INFO, "selecting option in  Selected Fields custom template ");
		Select select = new Select(selectedFieldsInCustomtemplate);
		select.selectByIndex(index);

	}

	public List<String> getSelectedFieldsInCustomtemplateAllItemsInList() {
		extentTest.log(Status.INFO, "Checking the selected Fields In Custom template for number of elements ");
		Select select = new Select(selectedFieldsInCustomtemplate);
		List<WebElement> options = select.getOptions();
		List<String> list = new ArrayList<>();
		for (WebElement option : options) {
			list.add(option.getText());
		}
		return list;

	}

	public int getSelectedFieldsInCustomtemplateSize() {
		extentTest.log(Status.INFO, "Checking the selected Fields In Custom template for number of elements ");
		Select select = new Select(selectedFieldsInCustomtemplate);
		List<WebElement> options = select.getOptions();
		int size = options.size();
		return size;

	}

	public Boolean checkFieldInSelectedFieldsInCustomtemplate(String tocheck) {
		extentTest.log(Status.INFO, "Check for a particular option in selected Fields In Custom template");
		Select select = new Select(selectedFieldsInCustomtemplate);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(tocheck)) {
				return true;
			}
		}
		return false;

	}

	public Boolean checkFieldSelectTemplatedropdown(String tocheck) {
		extentTest.log(Status.INFO, "Check for a particular option in selected Fields In Custom template");
		Select select = new Select(cSVTemplate);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(tocheck)) {
				return true;
			}
		}
		return false;

	}

	public boolean isBatchDownloadWarningMessagePdfVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for PDF.");
		return batchDownloadWarningMessagePdf.isDisplayed();
	}

	public boolean isBatchDownloadWarningMessageCsvVisible() {
		extentTest.log(Status.INFO, "Verify if the Click Here To See Why message is visible for CSV.");
		return batchDownloadWarningMessageCsv.isDisplayed();
	}

	public boolean iscSVTemplateCreateVisible() {
		extentTest.log(Status.INFO, "Verify if the CSV template create button is displayed.");
		try {
			return cSVTemplateCreate.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean iscSVTemplateNameVisible() {
		extentTest.log(Status.INFO, "Verify if the CSV name input text-box displayed");
		try {
			return cSVTemplateName.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void setCSVtemplateName(String name) {
		extentTest.log(Status.INFO, "Entering a valid name for new CSV template");
		cSVTemplateName.clear();
		cSVTemplateName.sendKeys(name);
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

	public void clickOnSaveToSeparateFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to separate files radio button.");
		saveToSeparateFileRadioBtn.click();
	}

	public void clickOnSaveToSingleFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to single files radio button.");
		saveToSingleFileRadioBtn.click();
	}

	// Check Download Companies Popup Dialog Displayed.
	public boolean isDownloadCompaniesPopupDialogDisplayed() {
		extentTest.log(Status.INFO, "Check Download Companies Popup Dialog Displayed.");
		return CommonUtils.checkElementExist(downloadComapniesPopupDialog, driver);
	}

	// Get the error message of Download companies popup dialog.
	public String getErrorMessageOfDownloadCompanies() {
		extentTest.log(Status.INFO, "Get the error message of Download companies popup dialog.");
		return errorMsgOnDownloadCompanies.getText().trim();
	}

	public void selectCreateNewTemplate() {
		extentTest.log(Status.INFO, "click On Creat new template.");
		selectCreatNewTemplateOption.click();
	}

	// Check Download Company Popup header background color
	public boolean checkForDownloadCompanyPopUpHeaderBackgroundColor() {
		extentTest.log(Status.INFO, "Checking Download Company Popup header background color as #CED2D6");
		String backColor = downloadCompanyPopupHeader.getCssValue("background-color");
		if (backColor.equals("rgba(206, 210, 214, 1)")) {
			return true;
		} else {
			return false;
		}
	}

	// Check Download Company Popup title font
	public boolean checkForDownloadCompanyPopUpTitleFont() {
		extentTest.log(Status.INFO, "Checking Download Company Popup title font as 'Verdana'");
		String font = downloadCompanyPopupHeader.getCssValue("font-family");
		if (font.equals("Verdana")) {
			return true;
		} else {
			return false;
		}
	}

	// Check Download Company Popup title font size
	public boolean checkForDownloadCompanyPopUpTitleFontSize() {
		extentTest.log(Status.INFO, "Checking Download Company Popup title font size as '12 px'");
		String fontSize = downloadCompanyPopupHeader.getCssValue("font-size");
		if (fontSize.equals("12px")) {
			return true;
		} else {
			return false;
		}
	}

	// Check Download Company Popup title font Weight
	public boolean checkForDownloadCompanyPopUpTitleFontWeight() {
		extentTest.log(Status.INFO, "Checking Download Company Popup title font weight as 'bold'");
		String fontWeight = downloadCompanyPopupHeader.getCssValue("font-weight");
		if (fontWeight.equals("bold")) {
			return true;
		} else {
			return false;
		}
	}

	// Check Download Company Popup title text color
	public boolean checkForDownloadCompanyPopUpTitleColor() {
		extentTest.log(Status.INFO, "Checking Download Company Popup title color as 'black'");
		String color = downloadCompanyPopupHeader.getCssValue("color");
		if (color.equals("rgba(0, 0, 0, 1)")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSelectTemplateDropDownDisplayed() {
		extentTest.log(Status.INFO, "Check CSV template drop down is displayed");
		return CommonUtils.checkElementExist(csvTemplateDropDown, driver);
	}

	public String getDefaultSelectedValueOfSelectATemplateDropDown() {
		extentTest.log(Status.INFO, "Get default selected value in Select A Template drop-down.");
		Select selectATemplate = new Select(csvTemplateDropDown);
		return selectATemplate.getFirstSelectedOption().getText();
	}

	public List<String> getSelectTemplateDropdownOptionsInList() {
		extentTest.log(Status.INFO, "Checking the select templatre drop down Fields for ascending order");
		Select select = new Select(csvTemplateDropDown);
		List<WebElement> options = select.getOptions();
		List<String> list = new ArrayList<>();
		for (WebElement option : options) {
			list.add(option.getText());
		}
		return list;

	}

	public boolean isAvailableFieldsMultiSelectBoxVisible() {
		extentTest.log(Status.INFO, "Verify if Available Fields visible on the Download Projects Popup - CSV.");
		return availableFieldsMultiSelectBox.isDisplayed();
	}

	// Select Different value/option from Template dropdown.
	public void selectOptionFromTemplateDropDown(String optionToSelect) {
		extentTest.log(Status.INFO, "Select Different value/option from Template dropdown.");
		Select selectATemplate = new Select(csvTemplateDropDown);
		selectATemplate.selectByVisibleText(optionToSelect);
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

	// select available field dropdown value.
	public void selectAvailableFieldsDropdownOption(String valueToSelect) {
		extentTest.log(Status.INFO, "Select the option from Available Fields multi-select drop-down.");
		Select availableFieldsMultiSelectBoxSelect = new Select(availableFieldsMultiSelectBox);
		availableFieldsMultiSelectBoxSelect.selectByVisibleText(valueToSelect);
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

	// Click on the Add button to add the selected available field option
	public void clickOnAddAvailableFieldButton() {
		extentTest.log(Status.INFO, "Click on the Add button to add the selected available field option.");
		addBtn.click();
	}

	// Set the set My Template Name text field
	public void setMyTemplateName(String downloadName) {
		extentTest.log(Status.INFO, "Set the set My Template Name text field.");
		myTemplateNameTxt.clear();
		myTemplateNameTxt.sendKeys(downloadName);
	}

	// Click on the Create button to create a template.
	public void clickOnCreateMyTemplateButton() {
		extentTest.log(Status.INFO, "Click on the Create button to create a template.");
		createBtn.click();
	}

	public boolean isTemplateCreated(String templateName) {
		extentTest.log(Status.INFO, "Check if template is created/present in template list");
		Select templateDropDown = new Select(csvTemplateDropDown);
		List<WebElement> templateDropDownList = templateDropDown.getOptions();

		for (WebElement w : templateDropDownList) {
			if (w.getText().equals(templateName))
				return true;
		}
		return false;
	}

	// Check Download Companies header text.
	public String checkDownloadCompaniesPopupDialogHeaderText() {
		extentTest.log(Status.INFO, "Check Download Companies Popup Dialog header text.");
		String headerText = downloadFormatMsg.getText();
		return headerText;
	}

	// Check Save To This File text
	public String checkSaveToThisFileTextOnDownloadCompaniesPopUp() {
		extentTest.log(Status.INFO, "Check Save To This File text on Download Companies Popup Dialog.");
		String saveToFileText = saveToThisFileText.getText();
		return saveToFileText;
	}

	// Check Download Name text in download companies popup
	public boolean isDownloadNameTextOnDownloadCompaniesPopUpDisplayed() {
		extentTest.log(Status.INFO, "Check Download Name text on Download Companies Popup Dialog.");
		return CommonUtils.checkElementExist(downloadNameTextOnComapniesPopupDialog, driver);

	}

	public List<String> getFieldList() {
		extentTest.log(Status.INFO, "Get available Field List");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedField : downloadComapnyPopUpCSVFields) {
			selectedFilelds.add(selectedField.getText());
		}
		return selectedFilelds;
	}

	public void selectFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadComapnyPopUpCSVAvailableFields.get(0))
				.click(downloadComapnyPopUpCSVAvailableFields.get(3))
				.click(downloadComapnyPopUpCSVAvailableFields.get(4))
				.click(downloadComapnyPopUpCSVAvailableFields.get(6))
				.click(downloadComapnyPopUpCSVAvailableFields.get(7)).keyUp(Keys.CONTROL).build().perform();
	}

	public void clickOnAddBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOnAddButtonOnPopUPFrame.click();
	}

	public List<String> getSelectedFieldList() {
		extentTest.log(Status.INFO, "Get selected fileld list from roght pen.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedFieldPopUp : downloadCompanyPopUpCSVSelectedFields) {
			selectedFilelds.add(selectedFieldPopUp.getText());
		}
		return selectedFilelds;
	}

	public void getTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "MyCSVTemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void getTemplateNameWithSpecialCharacter() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		String templateNameWithspecialCharacter = "*MyTemplateCSV";
		enterTeplateName.sendKeys(templateNameWithspecialCharacter);
	}

	public void clickOnCreateButton() {
		extentTest.log(Status.INFO, "enter valid template name.");
		clickOnCreateBtn.click();

	}

	public boolean isTemplateNameAndCreateButtonVisisble() {
		extentTest.log(Status.INFO, "Check template name and create button is displayed");
		return templateNameAndCreatebutton.isDisplayed();
	}

	public boolean isMyDownloadInputBoxDisplayed() {
		extentTest.log(Status.INFO, "Verify My download input text box.");
		return CommonUtils.checkElementExist(downloadNameTxt, driver);
	}

}

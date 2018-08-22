package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class SavedSearchPopUp {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "savedSearchName")
	private WebElement nameTxtField;

	@FindBy(how = How.ID, using = "rdbDoNotEmail")
	private WebElement doNotEmailRadioBtn;

	@FindBy(how = How.ID, using = "rdbDailyEmail")
	private WebElement DailyRadioBtn;

	@FindBy(how = How.ID, using = "rdbWeeklyEmail")
	private WebElement WeeklyRadioBtn;

	@FindBy(how = How.ID, using = "rdbTemplateSummary")
	private WebElement summaryRadioBtn;

	@FindBy(how = How.ID, using = "rdbTemplateLimited")
	private WebElement limitedRadioBtn;

	@FindBy(how = How.ID, using = "rdbTemplateCapsule")
	private WebElement capsuleRadioBtn;

	@FindBy(how = How.ID, using = "savePopupSubmit")
	private WebElement saveBtn;
	
	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisibleCheck;

	@FindBy(how = How.CLASS_NAME, using = "powerRankSaveSubmit")
	private WebElement saveBtnPowerRank;

	@FindBy(how = How.ID, using = "savePopupClose")
	private WebElement cancelBtn;

	@FindBy(how = How.CLASS_NAME, using = "powerRankSaveClose")
	private WebElement cancelBtnPowerRank;

	@FindBy(how = How.ID, using = "popUpLB")
	private WebElement saveSearchpopUp;

	@FindBy(how = How.ID, using = "savePopupNew")
	private WebElement saveAsNewBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='popUpLB']/div[2]/div[6]/div")
	private WebElement noteText;

	@FindBy(how = How.XPATH, using = ".//*[@id='save-search-text']/div[2]")
	private WebElement searchCategoryNonAdmin;

	@FindBy(how = How.ID, using = "SavedSearchtType")
	private WebElement saveSearchType;

	@FindBy(how = How.ID, using = "savePopupNew")
	private WebElement newsaveButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "savedSearchName")
	private WebElement nameTextfieldInEditDialog;

	@FindBy(how = How.ID, using = "savePopupSubmit")
	private WebElement saveButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "savePopupClose")
	private WebElement cancelButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "rdbTemplateSummary")
	private WebElement summaryTemplateRadioBtn;

	@FindBy(how = How.ID, using = "SavedSearchtType")
	private WebElement typeFilterEditSaveSearchList;

	@FindBy(how = How.NAME, using = "savedSearchListType")
	private WebElement typeListOnEditDialogForSavedSearch;

	@FindBy(how = How.ID, using = "txtInviteeList")
	private WebElement emailTextArea;

	public SavedSearchPopUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean isEmailTextAreaDisabled() {
		extentTest.log(Status.INFO, "Check if email text area is disabled");
		return SeleniumUtils.isClickable(emailTextArea, driver);
	}

	public void enterEmailText(String email) {
		extentTest.log(Status.INFO, "enter email text");
		emailTextArea.clear();
		emailTextArea.sendKeys(email);
	}

	public boolean isNameTextFieldInfocus() {
		extentTest.log(Status.INFO, "Check if name text field is in focus");
		return nameTxtField.equals(driver.switchTo().activeElement());
	}

	public String getSavedSearchName() throws InterruptedException {
		extentTest.log(Status.INFO, "Retrieve Saved Search Name on Saved Search Popup");
		Thread.sleep(1000);
		return nameTxtField.getAttribute("value");
	}

	public String getNoteText() {
		extentTest.log(Status.INFO, "Retrieve Note Text on Saved Search Popup");
		return noteText.getText();
	}

	public boolean isDoNotEmailRadioBtnChecked() {
		extentTest.log(Status.INFO, "Check if Do not Email Radio button is clicked");
		return doNotEmailRadioBtn.isSelected();
	}

	public void clickOnDoNotEmailRadioBtn() {
		extentTest.log(Status.INFO, "Click on Do not Email Radio button");
		doNotEmailRadioBtn.click();
	}

	public void clickOnCancelBtn() {
		extentTest.log(Status.INFO, "Click on Cancel button");
		if (SeleniumUtils.isVisible(cancelBtn, driver))
			cancelBtn.click();
		else
			cancelBtnPowerRank.click();
	}

	public List<String> verifySavedSearchTypeDropdownValues() {
		Select select = new Select(saveSearchType);
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public void selectPrivatetype() {
		Select select = new Select(saveSearchType);
		select.selectByVisibleText("Private");
	}

	public void selectPublictype() {
		Select select = new Select(saveSearchType);
		select.selectByVisibleText("Public");
	}

	public void clickOnCapsuleRadioBtn() {
		extentTest.log(Status.INFO, "Click on Capsule Radio button");
		capsuleRadioBtn.click();
	}

	public void clickOnDailyRadioBtn() {
		extentTest.log(Status.INFO, "Click on Daily Radio button");
		DailyRadioBtn.click();
	}

	public void clickOnLimitedRadioBtn() {
		extentTest.log(Status.INFO, "Click on Limited Radio button");
		limitedRadioBtn.click();
	}

	public void clickOnWeeklyRadioBtn() {
		extentTest.log(Status.INFO, "Click on Weekly Radio button");
		WeeklyRadioBtn.click();
	}

	public void clickOnSummaryRadioBtn() {
		extentTest.log(Status.INFO, "Click on Summary Radio button");
		summaryRadioBtn.click();
	}

	public boolean isSummaryRadioBtnClicked() {
		extentTest.log(Status.INFO, "Check if Summary Radio button is clicked");
		return summaryRadioBtn.isSelected();
	}

	public boolean isSaveBtnDisplayed() {
		extentTest.log(Status.INFO, "Check if Save button is displayed");
		return saveBtn.isDisplayed();
	}

	public boolean isCancelBtnDisplayed() {
		extentTest.log(Status.INFO, "Check if cancelbutton is dispalyed");
		return cancelBtn.isDisplayed();
	}

	public void enterName(String name) {
		extentTest.log(Status.INFO, "Enter name on Saved Search Popup");
		SeleniumUtils.isVisible(nameTxtField, driver);
		nameTxtField.clear();
		nameTxtField.sendKeys(name);
	}

	public int lengthofNameText() {
		extentTest.log(Status.INFO, "Return the length of name text");
		return nameTxtField.getAttribute("value").length();
	}

	public void selectSearchType(String searchType) {
		extentTest.log(Status.INFO, "Select seacrh type as " + searchType);
		SeleniumUtils.isVisible(saveSearchType, driver);
		Select select = new Select(saveSearchType);
		select.selectByVisibleText(searchType);
	}

	public void clickSave() throws InterruptedException {
		extentTest.log(Status.INFO, "Click On save button");
		saveBtn.click();
		waitforLoadingRing();
	}
	
	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickSavePowerRank() throws InterruptedException {
		extentTest.log(Status.INFO, "Click On save button");
		saveBtnPowerRank.click();
		waitforLoadingRing();
	}

	public void clickSaveAsNew() throws InterruptedException {
		extentTest.log(Status.INFO, "Click On save button");
		saveAsNewBtn.click();
		waitforLoadingRing();
	}

	public boolean isSaveSearchPopupDisplayed() {
		extentTest.log(Status.INFO, "Is Saved Search Popup displayed");
		return saveSearchpopUp.isDisplayed();
	}

	public boolean isSaveSearchCategoryPrivateforNonAdmin() {
		extentTest.log(Status.INFO, "Is search Category Private for Non Admin User");
		if (searchCategoryNonAdmin.getText().contains("Private"))
			return true;
		else
			return false;
	}

	public boolean isSaveAsNewDisabled() {
		extentTest.log(Status.INFO, "Is Save as New Button Enabled");
		if (saveAsNewBtn.getAttribute("disabled") != null) {
			if (saveAsNewBtn.getAttribute("disabled").equals("true"))
				return true;
		} else
			return false;
		return false;
	}

	public boolean isSaveDisabled() {
		extentTest.log(Status.INFO, "Is Save Button Enabled");
		if (saveBtn.getAttribute("disabled") != null) {
			if (saveBtn.getAttribute("disabled").equals("true"))
				return true;
		} else
			return false;
		return false;
	}

	public void ClickOkOnAlertBox() {
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
	}

	public void ClickCancelOnAlertBox() {
		if (isAlertPresent()) {
			driver.switchTo().alert().dismiss();
		}
	}

	public Boolean verifyTextOnAlert(String text) {

		if (isAlertPresent()) {
			Alert alert = driver.switchTo().alert();

			if (alert.getText().contains(text))
				return true;
			else
				return false;
		}
		return false;

	}

	public boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 0);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public boolean checkSavedSearchesEditDialog() {
		extentTest.log(Status.INFO, "Verify the element of edit dialog on Save search page.");
		if (!nameTextfieldInEditDialog.isDisplayed()) {
			return false;
		} else if (!newsaveButtonfieldInEditDialog.isDisplayed()) {
			return false;
		} else if (!saveButtonfieldInEditDialog.isDisplayed()) {
			return false;
		} else if (!cancelButtonfieldInEditDialog.isDisplayed()) {
			return false;
		} else if (!doNotEmailRadioBtn.isDisplayed()) {
			return false;
		} else if (!summaryTemplateRadioBtn.isDisplayed()) {
			return false;
		}
		return true;
	}

	public void clickOnCancelButtonEditSaveSearch() {
		extentTest.log(Status.INFO, "Click on Cancel button of edit dialog of save search page.");
		cancelButtonfieldInEditDialog.click();
	}

	public boolean checkSavedSearchesPlatiniumAdminEditDialog() {
		extentTest.log(Status.INFO, "Verify type filter dropdown is present on edit dialog on save search page.");
		if (!typeFilterEditSaveSearchList.isDisplayed()) {
			return false;
		}
		return true;
	}

	public void selectTypeListInEditDialog(String optionToSelect) {
		extentTest.log(Status.INFO,
				"Making changes to Type filter (applicable only for PLAT admin user) option to set :" + optionToSelect);
		Select typeList = new Select(typeFilterEditSaveSearchList);

		typeList.selectByVisibleText(optionToSelect);
	}

	public SavedSearchesPage clickOnSaveButtonEditDialog() {
		extentTest.log(Status.INFO, "Click on Save button of Edit tracking name dialog.");
		saveButtonfieldInEditDialog.click();
		driver.switchTo().alert().accept();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SavedSearchesPage(driver);
	}

	public SavedSearchesPage clickOnSaveButtonEditDialogWithOverride() {
		extentTest.log(Status.INFO, "Click on Save button of Edit tracking name dialog.");
		saveButtonfieldInEditDialog.click();
		clickOnOKButtonForOverrideSaveSearch();
		return new SavedSearchesPage(driver);
	}

	public SavedSearchesPage clickOnOKButtonForOverrideSaveSearch() {
		extentTest.log(Status.INFO, "Click on the OK button of override save search dialog.");
		driver.switchTo().alert().accept();
		return new SavedSearchesPage(driver);
	}

	public void editSaveSearchNameSetText(String trackingName) {
		extentTest.log(Status.INFO, "Edit/Set the name of Save search from Edit save search dialog.");
		nameTextfieldInEditDialog.clear();
		nameTextfieldInEditDialog.sendKeys(trackingName);
	}

	public SavedSearchesPage clickOnSaveButtonEditSaveSearch() {
		extentTest.log(Status.INFO, "Click on Save button of edit dialog of save search page.");
		saveButtonfieldInEditDialog.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SavedSearchesPage(driver);
	}
	
	public boolean checkShareEditTypeDropdownOption() {
		extentTest.log(Status.INFO,
				"Verify the option present in the type dropdown of the edit dialog with share option/link tracking list.");
		SeleniumUtils.isVisible(typeListOnEditDialogForSavedSearch, driver);
		Select typeOption = new Select(typeListOnEditDialogForSavedSearch);
		return CommonUtils.getListFromWebElements(typeOption.getOptions()).contains("Shared");
	}

	public boolean checkSelectOfEditTypeDropdown(String optionToCheck) {
		extentTest.log(Status.INFO, "Verify the option selected in the edit dialog type dropdown:" + optionToCheck);
		Select typeList = new Select(typeListOnEditDialogForSavedSearch);
		return typeList.getFirstSelectedOption().getText().toUpperCase().trim().equals(optionToCheck.toUpperCase());
	}

	public void clickOnCancelButtonForOverrideSaveSearch() {
		extentTest.log(Status.INFO, "click on Cancel button of override save search dialog.");
		driver.switchTo().alert().dismiss();
	}

	public Boolean checkforValidationMessage(String message) {
		return driver.getPageSource().contains(message);
	}
}

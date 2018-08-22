package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class TrackingListPopupDialog {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-submit']")
	private WebElement saveButtonfieldInEditDialog;

	@FindBy(how = How.NAME, using = "TrackingListType")
	private WebElement typeListOnEditDialog;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrackingListRename']//input[@type='text']")
	private WebElement nameTextfieldInEditDialog;

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private WebElement editTrackingNameLink;

	@FindBy(how = How.XPATH, using = "//a[@class='rename-popup-close']")
	private WebElement cancelButtonfieldInEditDialog;

	@FindBy(how = How.ID, using = "TrackingListType")
	private WebElement trackingListType;

	public TrackingListPopupDialog(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		PageFactory.initElements(driver, this);
	}

	public TrackingLists clickOnSaveButtonEditDialog() {
		extentTest.log(Status.INFO, "Click on Save button of Edit tracking name dialog.");
		saveButtonfieldInEditDialog.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new TrackingLists(driver);
	}

	public void selectTypeListInEditDialog(String optionToSelect) {
		extentTest.log(Status.INFO,
				"Making changes to Type filter (applicable only for PLAT admin user) option to set :" + optionToSelect);
		Select typeList = new Select(typeListOnEditDialog);
		typeList.selectByVisibleText(optionToSelect);
	}

	public void editTrackingNameSetText(String trackingName) {
		extentTest.log(Status.INFO, "Edit/Set the name of Tracking list from Edit tracking name dialog.");
		nameTextfieldInEditDialog.clear();
		nameTextfieldInEditDialog.sendKeys(trackingName);
	}

	public boolean isTrackingListTypeDisplayed() {
		extentTest.log(Status.INFO,
				"Verify that Tracking list type Drop Down- My Tracking Lists is  displayed-PLAT user ");

		return trackingListType.isDisplayed();

	}

	public boolean checkEditTrackingNameDialog() {
		boolean result = true;
		if (!nameTextfieldInEditDialog.isDisplayed()) {
			result = false;
		} else if (!saveButtonfieldInEditDialog.isDisplayed()) {
			result = false;
		} else if (!cancelButtonfieldInEditDialog.isDisplayed()) {
			result = false;
		}
		return result;
	}

	public void clickOnCancelButtonEditDialog() {
		extentTest.log(Status.INFO, "Click on Cancel button of Edit tracking name dialog.");
		cancelButtonfieldInEditDialog.click();
	}

	public boolean checkSelectOfEditTypeDropdown(String optionToCheck) {
		extentTest.log(Status.INFO, "Verify the option selected in the edit dialog type dropdown:" + optionToCheck);
		Select typeList = new Select(typeListOnEditDialog);
		return typeList.getFirstSelectedOption().getText().toUpperCase().trim()
				.equals(optionToCheck.toUpperCase().trim());
	}

	public boolean checkEditTypeDropdownOption() {
		extentTest.log(Status.INFO,
				"Verify the option present in the type dropdown of the edit dialog with private option tracking list.");
		SeleniumUtils.isVisible(typeListOnEditDialog, driver);
		return MyAccountCommonContainerPage.checkOptionOfShareEditTypeDropdown(typeListOnEditDialog);
	}

	public boolean checkETypeDropdownPresentOnEditTrackingDialog() {
		boolean result = true;
		if (!typeListOnEditDialog.isDisplayed()) {
			result = false;
		}
		return result;
	}

	public boolean checkShareEditTypeDropdownOption() {
		extentTest.log(Status.INFO,
				"Verify the option present in the type dropdown of the edit dialog with share option/link tracking list.");
		SeleniumUtils.isVisible(typeListOnEditDialog, driver);
		Select typeOption = new Select(typeListOnEditDialog);
		return CommonUtils.getListFromWebElements(typeOption.getOptions()).contains("Shared");
	}

}

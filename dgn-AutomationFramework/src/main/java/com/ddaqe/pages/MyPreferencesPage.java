package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class MyPreferencesPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.LINK_TEXT, using = "Company Filter")
	private WebElement companyFilterMyPreferences;

	@FindBy(how = How.LINK_TEXT, using = "Documents")
	private WebElement documentsFilterMyPreferences;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSavePreference")
	private WebElement saveBTNMyPreferences;
	@FindBy(how = How.XPATH, using = "//input[@name='ctl00$contentPlaceHolderHeader$chkCrossSearchLeads']")
	private WebElement checkboxCompanyMyPrefrences;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$chkDocument")
	private WebElement checkboxDocuments;

	@FindBy(how = How.ID, using = "My-Account---My-Preferences")
	private WebElement myPreferencesbreadcumbMenu;

	@FindBy(how = How.LINK_TEXT, using = "My Preferences")
	private WebElement myPreferencesOption;

	@FindBy(how = How.LINK_TEXT, using = "My Tracking Lists")
	private WebElement myTrackingLists;

	@FindBy(how = How.ID, using = "myAccountNav18")
	private WebElement myPreferencesmenuleftNav;

	@FindBy(how = How.ID, using = "myAccountHeader")
	private WebElement myAccountHeader;

	@FindBy(how = How.ID, using = "ADMIN TOOLS")
	private WebElement adminToolsHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='submenu_1']")
	private WebElement accountToolsHeader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_chkPrivateNotes")
	private WebElement chkPrivateNotes;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSavePreference")
	private WebElement btnSave;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lnkPrintNotes")
	private WebElement tabPrintNotes;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;

	public MyPreferencesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public void clickOnCompanyFilter() {
		extentTest.log(Status.INFO, "Clicking on Company Filter under my Preferences");

		companyFilterMyPreferences.click();

	}

	public void clickOnDocumentFilter() {
		extentTest.log(Status.INFO, "Clicking on Document Filter under my Preferences");
		documentsFilterMyPreferences.click();
	}

	public boolean isDocumentTabDisplay() {
		extentTest.log(Status.INFO, "Clicking on Document Filter under my Preferences");
		return documentsFilterMyPreferences.isDisplayed();
	}

	public boolean isDocumentCheckBoxSelected() {
		extentTest.log(Status.INFO, "Verify Document checkbox status my Preferences");
		return checkboxDocuments.isSelected();
	}

	public void checkCompanyFilterCheckBox() {
		extentTest.log(Status.INFO, "clickOnProjectFilterCheckBox");

		checkboxCompanyMyPrefrences.click();
		saveBTNMyPreferences.click();

	}

	public Boolean companyFilterCheckBoxisDisplayed() {
		extentTest.log(Status.INFO, "clickOnProjectFilterCheckBox");

		return checkboxCompanyMyPrefrences.isDisplayed();

	}

	public Boolean companyFilterCheckBoxisEnabled() {
		extentTest.log(Status.INFO, "Verify Check box On Company FilterCheckBox");

		return checkboxCompanyMyPrefrences.isEnabled();

	}

	public Boolean companyFilterCheckBoxisDisabled() {
		extentTest.log(Status.INFO, "Verify Check box On Company FilterCheckBox");

		return checkboxCompanyMyPrefrences.isSelected();

	}
	
	public void checkDocumentFilterCheckBox() {
		extentTest.log(Status.INFO, "clickOnDocumentFilterCheckBox");
		if (!checkboxDocuments.isSelected()) {
			checkboxDocuments.click();
		}
		saveBTNMyPreferences.click();
	}

	public void unCheckDocumentFilterCheckBox() {
		extentTest.log(Status.INFO, "clickOnDocumentFilterCheckBox");
		if (checkboxDocuments.isSelected()) {
			checkboxDocuments.click();
		}
		saveBTNMyPreferences.click();
	}

	public void clickOnDocumentFilterCheckBox() {
		extentTest.log(Status.INFO, "click On Document Filter Check Box");
		if (!isDocumentCheckBoxSelected()) {
			checkboxDocuments.click();
		}
	}

	public void clickOnDocumentFilterCheckBoxSaveButton() {
		extentTest.log(Status.INFO, "click On Document Filter CheckBox Save Button");
		saveBTNMyPreferences.click();
	}

	public boolean isBreadcrumbAndMyAccountMyPreferencesDisplayed() {
		extentTest.log(Status.INFO, "Verify that Breadcrumb for My-Account---My-Preferences is  displayed ");

		return myPreferencesbreadcumbMenu.isDisplayed();

	}

	public boolean myPreferencesOptionDisplayed() {
		extentTest.log(Status.INFO, "Verify that My-Preferences option is displayed under Account Tools");

		return myPreferencesOption.isDisplayed();

	}

	public boolean checkFontSize() {
		extentTest.log(Status.INFO, "Verify Font size of myPreferences menu ");
		// Read font-size property and print It In console.
		String fontSize = myPreferencesmenuleftNav.getCssValue("font-size");
		return fontSize.equals("11px");

	}

	public boolean checkifElementisBold() {
		extentTest.log(Status.INFO, "Verify Font Weight of myPreferences menu ");
		String fontWeight = myPreferencesmenuleftNav.getCssValue("font-weight");
		return Integer.parseInt(fontWeight) >= 700;
	}

	public boolean checkPreferencesColor() {
		String colorChar = "rgba(242, 77, 0, 1)";
		extentTest.log(Status.INFO, "Verify myPreferences color.");
		String fontColor = myPreferencesmenuleftNav.getCssValue("color");
		return fontColor.equalsIgnoreCase(colorChar);

	}

	public boolean checkColorofElements() {
		extentTest.log(Status.INFO, "Verify color of myPreferences menu ");
		String fontColor = myPreferencesmenuleftNav.getCssValue("color");
		return fontColor.equals("rgba(242, 77, 0, 1)");
	}

	public boolean iskIncludePrivateNotesCheckboxSelected() {
		extentTest.log(Status.INFO, "Checking the Include Private Notes is selected.");
		return chkPrivateNotes.isSelected();
	}

	public void clickChkIncludePrivateNotes() {
		extentTest.log(Status.INFO, "Checking the Include Private Notes");
		chkPrivateNotes.click();
	}

	public void clickPrivateNotesSaveBtn() {
		extentTest.log(Status.INFO, "Clicking the Private Notes Save button");
		btnSave.click();
	}

	public boolean verifyPrivateNotesChecked() {
		extentTest.log(Status.INFO, "Verify whether the Private Notes are checked");
		return chkPrivateNotes.getAttribute("checked").equals("true");
	}

	public boolean isPrintingNotesTabActive() {
		extentTest.log(Status.INFO, "Verify whether the Printing Notes tab are displayed as active");
		return tabPrintNotes.getAttribute("class").equals("active");
	}

	public TrackingLists clickOnMyTrackingLists() {
		extentTest.log(Status.INFO, "Click On My Account Link");
		myTrackingLists.click();
		return new TrackingLists(driver);
	}

	// Check Include Private Notes Checkbox Displayed and return result
	public boolean isIncludePrivateNotesCheckboxDisplayed() {
		extentTest.log(Status.INFO, "Check Include Private Notes Checkbox Displayed");
		return CommonUtils.checkElementExist(chkPrivateNotes, driver);
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}
}

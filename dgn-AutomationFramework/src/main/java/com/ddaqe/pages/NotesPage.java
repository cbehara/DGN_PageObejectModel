package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class NotesPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "notesTxtBox")
	private WebElement notesTxtField;

	@FindBy(how = How.ID, using = "addEditNotesPopup")
	private WebElement addeditNotesPopup;

	@FindBy(how = How.XPATH, using = ".//*[@id='addEditNotesPopup']/div[1]/span")
	private WebElement addEditNotePopupTitle;

	@FindBy(how = How.ID, using = "privateTypeRadio")
	private WebElement privateRadioBtn;

	@FindBy(how = How.ID, using = "publicTypeRadioButton")
	private WebElement publicRadioBtn;

	@FindBy(how = How.ID, using = "notesPopupSubmit")
	private WebElement saveBtn;

	@FindBy(how = How.ID, using = "notesPopupClose")
	private WebElement cancelBtn;

	@FindBy(how = How.ID, using = "charactersRemainingCount")
	private WebElement charRemainingCount;

	@FindBy(how = How.ID, using = "notesCreatedDate_11")
	private WebElement viewNotesCreatedDate;

	@FindBy(how = How.ID, using = "notesUserMailId_11")
	private WebElement viewNotesEmail;

	@FindBy(how = How.ID, using = "notesUserAccessCode_11")
	private WebElement viewNotesNoteType;

	@FindBy(how = How.ID, using = "notesText_11")
	private WebElement viewNotesNoteText;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnBack")
	private WebElement viewNotesbackBtn;

	@FindBy(how = How.CLASS_NAME, using = "breadcrumb-container clearfix")
	private WebElement ViewNotesBreadcumb;

	@FindBy(how = How.XPATH, using = "//div[@class='breadcrumb-container clearfix']/span[last()]")
	private WebElement lastBreadCrumb;

	public NotesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void enterNoteText(String notesText) {
		extentTest.log(Status.INFO, "Enter note Text");
		notesTxtField.sendKeys(notesText);
	}

	public void clickOnSaveButon() {
		extentTest.log(Status.INFO, "Click on Save Button");
		saveBtn.click();
	}

	public void clickOnCancelButton() {
		extentTest.log(Status.INFO, "Click on Cancel Button");
		cancelBtn.click();
	}

	public void clickOnPublicRadioButton() {
		extentTest.log(Status.INFO, "Click on public Radio Button");
		publicRadioBtn.click();
	}

	public void clickOnPrivateRadioButton() {
		extentTest.log(Status.INFO, "Click on Private Radio Button");
		privateRadioBtn.click();
	}

	public String getAddeditNotePopupTitle() {
		extentTest.log(Status.INFO, "Get Add/Edit note popup Title");
		return addEditNotePopupTitle.getText();
	}

	public Boolean isSavebuttonDisplayed() {
		extentTest.log(Status.INFO, "Check if save button is displayed");
		return saveBtn.isDisplayed();
	}

	public Boolean isAddeditNotesPopupDisplayed() {
		extentTest.log(Status.INFO, "Check if Add/Edit Notes popup is displayed");
		return addeditNotesPopup.isDisplayed();
	}

	public Boolean isPrivateRadioButtonEnabled() {
		extentTest.log(Status.INFO, "Check if Private Radio button is displayed");
		return privateRadioBtn.isEnabled();
	}

	public String getNoteText() {
		extentTest.log(Status.INFO, "Get Note Text");
		return notesTxtField.getText();
	}

	public boolean isNoteTextFieldInfocus() {
		extentTest.log(Status.INFO, "Check if Notes text field is in focus");
		return notesTxtField.equals(driver.switchTo().activeElement());

	}

	public String getCharacterRemaining() {
		extentTest.log(Status.INFO, "Get Character Remaining Count");
		return charRemainingCount.getText();
	}

	public void clearNoteTextField() {
		extentTest.log(Status.INFO, "Clear NoteText Field");
		notesTxtField.clear();
	}

	public int getNoteTextLength() {
		extentTest.log(Status.INFO, "Get Length of Note Text Field");
		String noteText = notesTxtField.getAttribute("value");
		int length = noteText.length();
		return length;
	}

	public String getViewNotesCreatedDate() {
		extentTest.log(Status.INFO, "Get Created date on view notes Page");
		return viewNotesCreatedDate.getText();
	}

	public String getViewNotesEmail() {
		extentTest.log(Status.INFO, "Get Note Email on view Notes Page ");
		return viewNotesEmail.getText();
	}

	public String getViewNotesNoteType() {
		extentTest.log(Status.INFO, "Get Note Type on view notes Page");
		return viewNotesNoteType.getText();
	}

	public String getViewNotesNoteText() {
		extentTest.log(Status.INFO, "Get Note Text on view notes Page");
		return viewNotesNoteText.getText();
	}

	public boolean isBackButtonDisplayed() {
		extentTest.log(Status.INFO, "Check if Back button is displayed on view notes Page");
		return viewNotesbackBtn.isDisplayed();

	}

	public boolean verifyViewNotesBreadCrumb() {
		String lastBreadcrumb = lastBreadCrumb.getText();
		if (lastBreadcrumb.equals("View Notes")) {
			return true;
		} else {
			return false;
		}
	}

}

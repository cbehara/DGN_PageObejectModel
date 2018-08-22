package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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

public class TrackPopUpPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@id='lstTrackingLists']//label")
	private List<WebElement> existingTrackingListName;

	@FindBy(how = How.XPATH, using = "//div[@id='lstTrackingLists']//input")
	private List<WebElement> existingTrackingListChkbox;

	@FindBy(how = How.ID, using = "trackPopupSubmit")
	private WebElement trackPopUpSaveBtn;

	@FindBy(how = How.ID, using = "popUpLBTrack")
	private WebElement trackPopUp;

	@FindBy(how = How.ID, using = "txtTrackingListName")
	private WebElement newTrackingListName;

	@FindBy(how = How.CSS, using = "#rename-tracking-list-text input[value*='My_Tracking']")
	private WebElement renameTrackingListName;

	@FindBy(how = How.ID, using = "trackPopupClose")
	private WebElement trackPopupCloseBtn;

	@FindBy(how = How.ID, using = "chkBoxTrackingAlert")
	private WebElement trackingAlertChkBox;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'chbox') and @type='checkbox']")
	private List<WebElement> trackProjectList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'existingTrackingList')]")
	private WebElement heading1;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'existingTrackingList')]//following-sibling::div[@class='label']")
	private WebElement heading2;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'newTrackingListContainer')]")
	private WebElement newTrackingListContainer;

	@FindBy(how = How.XPATH, using = "//div[@id='lstTrackingLists']//label[not(contains(@class,'opt-private'))]")
	private List<WebElement> privateInExistingTrackingList;

	@FindBy(how = How.XPATH, using = "//div[@id='lstTrackingLists']//label[contains(@class,'opt-public')]")
	private List<WebElement> publicInExistingTrackingList;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrack']//div[contains(@class,'error-message')]")
	private WebElement errorMessage;

	@FindBy(how = How.ID, using = "trackingType")
	private WebElement trackingTypeDropDown;

	@FindBy(how = How.XPATH, using = ".//img[@title='Public']//following-sibling::label[1]")
	private WebElement legendForPublic;

	@FindBy(how = How.XPATH, using = ".//img[@title='Shared']//following-sibling::label[1]")
	private WebElement legendForShared;

	@FindBy(how = How.CSS, using = "input[id='chkOnlyme']")
	private WebElement onlyShowMineCheckBox;

	@FindBy(how = How.CSS, using = "div[class*='bottom_content clearfix']>div:nth-of-type(1)")
	private WebElement labelForNewTrackingList;

	@FindBy(how = How.CSS, using = "[class*='alert_section']>div:nth-of-type(2)")
	private WebElement labelForAlertSection;

	public TrackPopUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Track Popup Page");
	}

	public String getTrackingInitial() {
		return "Track";
	}

	// Select the Existing Tracking List
	public List<String> clickOnOneExistingTrackingListCheckBox(int numberToSelect) {
		extentTest.log(Status.INFO, "Select any Tracking List from the Existing Tracking List Section");
		 List<String> strExistingTrackingName = new ArrayList<>();
		for (int i = 0; i < numberToSelect; i++) {
			strExistingTrackingName.add(existingTrackingListName.get(i).getText());
			existingTrackingListName.get(i).click();
		}
		return strExistingTrackingName;
	}

	public List<String> clickOnMultipleExistingTrackingListCheckBoxes(int numbertoSelect) {
		List<String> trackingList = new ArrayList<String>();
		extentTest.log(Status.INFO, "Select multiple Tracking List from the Existing Tracking List Section");
		for (int i = 0; i < numbertoSelect; i++) {
			trackingList.add(existingTrackingListName.get(i).getText());
			existingTrackingListName.get(i).click();
		}
		return trackingList;
	}

	public void selectOnExistingTrackingList(String listToBeSelected) {
		extentTest.log(Status.INFO, "Selecting Tracking List from the Existing Tracking List Section");
		for (WebElement trackingList : existingTrackingListName) {
			if (trackingList.getText().contains(listToBeSelected)) {
				trackingList.click();
			}
		}
	}

	public int getExistingTrackingListCount() {
		extentTest.log(Status.INFO, "Get the count of existing Tracking List on Track Popup");
		return existingTrackingListName.size();
	}

	public int getPublicInExistingTrackingListCount() {
		extentTest.log(Status.INFO, "Get the count of public in existing Tracking List on Track Popup");
		return publicInExistingTrackingList.size();
	}

	public int getPrivateInExistingTrackingListCount() {
		extentTest.log(Status.INFO, "Get the count of private in existing Tracking List on Track Popup");
		return privateInExistingTrackingList.size();
	}

	public int getExistingTrackingListCheckboxCount() {
		extentTest.log(Status.INFO, "Get the count of existing Tracking List checkbox on Track Popup");
		return existingTrackingListChkbox.size();
	}

	public boolean isAnyExistingCheckboxSelected() {
		extentTest.log(Status.INFO, "Verify if existing tracking list checkbox is selected by default");
		boolean isSelected = false;
		for (WebElement webElement : existingTrackingListChkbox) {
			if (webElement.isSelected()) {
				isSelected = true;
				break;
			}
		}
		return isSelected;
	}

	public boolean isExistingCheckboxSelectedChecked(List<String> selectedExistingTrackList) {
		extentTest.log(Status.INFO, "Verify if selected existing tracking list checkbox is checked");
		boolean isSelected = false;
		for (int i = 0; i < existingTrackingListName.size(); i++) {
			for (int j = 0; j < selectedExistingTrackList.size(); j++) {
				if (existingTrackingListName.get(i).getText().trim()
						.equalsIgnoreCase(selectedExistingTrackList.get(j).trim())) {
					if (existingTrackingListChkbox.get(i).isSelected()) {
						isSelected = true;
						break;
					}
				}
			}
		}
		return isSelected;
	}

	public void uncheckExistingCheckboxSelectedChecked(List<String> selectedExistingTrackList) {
		extentTest.log(Status.INFO, "Verify if selected existing tracking list checkbox is checked");
		for (int i = 0; i < existingTrackingListName.size(); i++) {
			for (int j = 0; j < selectedExistingTrackList.size(); j++) {
				if (existingTrackingListName.get(i).getText().trim()
						.equalsIgnoreCase(selectedExistingTrackList.get(j).trim())) {
					if (existingTrackingListChkbox.get(i).isSelected()) {
						existingTrackingListChkbox.get(i).click();
						break;
					}
				}
			}
		}
	}

	public TrackingList clickOnSaveBtn() {
		extentTest.log(Status.INFO, "Clicking on Save button in the Tracking Popup");
		trackPopUpSaveBtn.click();
		return new TrackingList(driver);
	}

	public boolean isTrackAlertChkBoxClickable() {
		extentTest.log(Status.INFO, "Clicking on Track Alert Check Box in the Tracking Popup");
		return SeleniumUtils.isClickable(trackingAlertChkBox, driver);
	}

	public void clickOnTrackAlertChkBox() {
		extentTest.log(Status.INFO, "Clicking on Track Alert Check Box in the Tracking Popup");
		trackingAlertChkBox.click();
	}

	public void clickOnCancelBtn() {
		extentTest.log(Status.INFO, "Clicking on Cancel button in the Tracking Popup");
		trackPopupCloseBtn.click();
	}

	public Boolean trackPopupCloseBtn() {
		extentTest.log(Status.INFO, "check if cancel button is displayed in the Tracking Popup");
		return trackPopupCloseBtn.isDisplayed();
	}

	public Boolean isTrackPopUpDisplayed() {
		extentTest.log(Status.INFO, "Verify if Track PopUp is displayed");
		return trackPopUp.isDisplayed();
	}

	public Boolean isTrackPopUpCancelBtnDisplayed() {
		extentTest.log(Status.INFO, "Verify if Track PopUp Cancel button is displayed");
		return trackPopupCloseBtn.isDisplayed();
	}

	public Boolean isTrackPopUpSaveBtnDisplayed() {
		extentTest.log(Status.INFO, "Verify if Track PopUp Save button is displayed");
		return trackPopUpSaveBtn.isDisplayed();
	}

	public Boolean isAlertInTrackPopUpDisplayed() {
		extentTest.log(Status.INFO, "Verify if Alert in Track PopUp is displayed");
		return trackingAlertChkBox.isDisplayed();
	}

	public void enterNewTrackingListName(String TrackingListName) {
		extentTest.log(Status.INFO, "Enter the New Tracking List name");
		newTrackingListName.clear();
		newTrackingListName.sendKeys(TrackingListName);
	}

	public Boolean isNewTrackingListContainerDisplayed() {
		extentTest.log(Status.INFO, "Verify if new tracking list is displayed");
		return newTrackingListContainer.isDisplayed();
	}

	public String getErrorMessageText() {
		extentTest.log(Status.INFO, "Get the error message as displayed in Track Pop Up");
		return errorMessage.getText();
	}

	public String getNewTrackingListName() {
		return newTrackingListName.getText();
	}

	public String formatValidNewTrackingListName(String Name) {
		return CommonUtils.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(Name));
	}

	public Boolean checkforValidationMessage(String message) {
		return driver.getPageSource().contains(message);
	}

	public String getTextHeading1() {
		extentTest.log(Status.INFO, "Get the text of heading 1 on Track Popup");
		return heading1.getText();
	}

	public String getTextHeading2() {
		extentTest.log(Status.INFO, "Get the text of heading 2 on Track Popup");
		return heading2.getText();
	}

	public boolean verifyVerticalScrollPresent() {
		extentTest.log(Status.INFO, "Verify if vertical scroll displayed when there are more than 10 tracking list");
		return SeleniumUtils.isVerticalScrollPresent(driver);
	}

	public void selectTrackingType(String trackingType) {
		Select trackingTypeSelect = new Select(trackingTypeDropDown);
		trackingTypeSelect.selectByValue(trackingType);
	}

	// Click on the first track project checkbox from the list.
	public void clickOnFirstTrackProjectCheckbox() {
		extentTest.log(Status.INFO, "Click on the first track project checkbox from the list.");
		trackProjectList.get(0).click();
	}

	public boolean isLegendForPublicDisplayed() {
		extentTest.log(Status.INFO, "Getting legend for Public.");
		return legendForPublic.getAttribute("innerHTML").contains("= Public");
	}

	public boolean isLegendForSharedDisplayed() {
		extentTest.log(Status.INFO, "Getting legend for Shared.");
		return legendForShared.getAttribute("innerHTML").contains("= Shared");
	}

	public boolean isOnlyShowMineCheckBoxDisplayed() {
		extentTest.log(Status.INFO, "Getting checkbox for 'only show mine'.");
		return onlyShowMineCheckBox.isDisplayed();
	}

	public String getTrackingAccessType() {
		WebElement webElement = newTrackingListContainer;
		String accessType = "";
		if (!(webElement == null)) {
			accessType = webElement.findElement(By.cssSelector(".trackingAccessType")).getText();
		}
		return accessType;
	}

	public boolean verifyLabelForNewTrackingList() {
		return labelForNewTrackingList.getAttribute("innerHTML")
				.contains("OR provide name for a <u>new</u> tracking list...");
	}

	public boolean verifyLabelForAlertSection() {
		return labelForAlertSection.getAttribute("innerHTML").contains("Alert me of updates to these");
	}
}

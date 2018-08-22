package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class CustomizeProjectDashboardPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.CSS, using = "[id*='modalPopup_chkListSavedSearch'] input[type='checkbox']")
	private List<WebElement> savedSearchCheckBoxList;

	@FindBy(how = How.CSS, using = "[id*='modalPopup_chkListTrackingList'] input[type='checkbox']")
	private List<WebElement> trackingListCheckBoxList;

	@FindBy(how = How.CSS, using = "[id*='modalPopup_chkListSavedSearch'] label")
	private List<WebElement> savedSearchCheckBoxLabelList;

	@FindBy(how = How.CSS, using = "[id*='modalPopup_chkListTrackingList'] label")
	private List<WebElement> trackingListCheckBoxLabelList;

	@FindBy(how = How.XPATH, using = ".//img[@title='Public']//following-sibling::label[1]")
	private WebElement legendForPublic;

	@FindBy(how = How.XPATH, using = ".//img[@title='Shared']//following-sibling::label[1]")
	private WebElement legendForShared;

	@FindBy(how = How.CSS, using = "input[id='checkBoxShowMine']")
	private WebElement onlyShowMineCheckBox;

	@FindBy(how = How.ID, using = "customisePopupSubmit")
	private WebElement saveButton;
	
	@FindBy(how = How.ID, using = "customisePopupClose")
	private WebElement closeButton;
	
	@FindBy(how = How.CSS, using =".error-messages")
	private WebElement errorMessages;

	public CustomizeProjectDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to Customize Project Dashboard Page");
	}

	public List<WebElement> getSavedSearchCheckBoxList() {
		extentTest.log(Status.INFO, "Getting Saved Search Check Box List.");
		return savedSearchCheckBoxList;
	}

	public List<String> getSavedSearchCheckBoxLabelList() {
		extentTest.log(Status.INFO, "Getting Saved Search Check Box List.");
		final List<String> checkBoxLabelList = new ArrayList<String>();
		String checkBoxActualLabel = "";
		for (WebElement checkBoxLabel : savedSearchCheckBoxLabelList) {
			checkBoxActualLabel = checkBoxLabel.getText();
			if (checkBoxActualLabel.contains("<img")) {
				checkBoxActualLabel = checkBoxActualLabel.substring(0, checkBoxActualLabel.indexOf("<img") - 1);
			}
			checkBoxLabelList.add(checkBoxActualLabel);
		}
		return checkBoxLabelList;
	}

	public List<String> getTrackingListCheckBoxLabelList() {
		extentTest.log(Status.INFO, "Getting Tracking List Check Box List.");
		final List<String> checkBoxLabelList = new ArrayList<String>();
		String checkBoxActualLabel = "";
		for (WebElement checkBoxLabel : trackingListCheckBoxLabelList) {
			checkBoxActualLabel = checkBoxLabel.getText();
			if (checkBoxActualLabel.contains("<img")) {
				checkBoxActualLabel = checkBoxActualLabel.substring(0, checkBoxActualLabel.indexOf("<img") - 1);
			}
			checkBoxLabelList.add(checkBoxActualLabel);
		}
		return checkBoxLabelList;
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

	public void selectOnlyShowMineCheckBox() {
		extentTest.log(Status.INFO, "Selecting checkbox for 'only show mine'.");
		if (!onlyShowMineCheckBox.isSelected()) {
			onlyShowMineCheckBox.click();
		}
	}

	public void deSelectOnlyShowMineCheckBox() {
		extentTest.log(Status.INFO, "Deselecting checkbox for 'only show mine'.");
		if (onlyShowMineCheckBox.isSelected()) {
			onlyShowMineCheckBox.click();
		}
	}

	public void selectDefaultSavedSearchCheckBox() {
		extentTest.log(Status.INFO, "Selecting checkbox for 'Default Saved Search'.");
		if (!savedSearchCheckBoxList.get(0).isSelected()) {
			savedSearchCheckBoxList.get(0).click();
		}
	}

	public void desSelectDefaultSavedSearchCheckBox() {
		extentTest.log(Status.INFO, "Deselecting checkbox for 'Default Saved Search'.");
		if (savedSearchCheckBoxList.get(0).isSelected()) {
			savedSearchCheckBoxList.get(0).click();
		}
	}

	public void selectMyProjectsCheckBox() {
		extentTest.log(Status.INFO, "Selecting checkbox for 'My Projects'.");
		if (!trackingListCheckBoxList.get(0).isSelected()) {
			trackingListCheckBoxList.get(0).click();
		}
	}

	public void deSelectMyProjectsCheckBox() {
		extentTest.log(Status.INFO, "Deselecting checkbox for 'My Projects'.");
		if (trackingListCheckBoxList.get(0).isSelected()) {
			trackingListCheckBoxList.get(0).click();
		}
	}

	public boolean isDefaultSavedSearchCheckBoxSelected() {
		extentTest.log(Status.INFO, "Validating status of checkbox for 'Default Saved Search'.");
		return savedSearchCheckBoxList.get(0).isSelected();
	}

	public boolean isMyProjectsCheckBoxSelected() {
		extentTest.log(Status.INFO, "Validating status of checkbox for 'My Projects'.");
		return trackingListCheckBoxList.get(0).isSelected();
	}

	public HomePage clickSaveButton() {
		extentTest.log(Status.INFO, "Clicking Save button.");
		saveButton.click();
		return new HomePage(driver);
	}

	public HomePage clickCloseButton() {
		extentTest.log(Status.INFO, "Clicking Close button.");
		closeButton.click();
		return new HomePage(driver);
	}
	
	public List<String> clickOnMultipleExistingTrackingListCheckBoxes(int numbertoSelect) {
		List<String> trackingList = new ArrayList<String>();
		extentTest.log(Status.INFO, "Select multiple Tracking List from the Existing Tracking List Section");
		List<WebElement> trackList = new ArrayList<WebElement>();
		trackList.addAll(trackingListCheckBoxLabelList);
		for (int i = 0; i < numbertoSelect; i++) {
			trackingList.add(trackList.get(i).getText());
			trackList.get(i).click();
		}
		return trackingList;
	}
	
	public List<String> clickOnMultipleExistingSavedSearchCheckBoxes(int numbertoSelect) {
		List<String> savedSearchList = new ArrayList<String>();
		extentTest.log(Status.INFO, "Select multiple saved search from the Existing List Section");
		List<WebElement> searchList = new ArrayList<WebElement>();
		searchList.addAll(savedSearchCheckBoxLabelList);
		for (int i = 0; i < numbertoSelect; i++) {
			savedSearchList.add(searchList.get(i).getText());
			searchList.get(i).click();
		}
		return savedSearchList;
	}
	
	public String getErrorMessage(){
		extentTest.log(Status.INFO, "Get error meesage.");
		return errorMessages.getText();
	}
}

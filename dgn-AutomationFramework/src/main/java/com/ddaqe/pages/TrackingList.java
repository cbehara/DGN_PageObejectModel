package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.SeleniumUtils;

public class TrackingList {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "trackPopupSubmit")
	private WebElement trackPopUpSaveBtn;

	@FindBy(how = How.CLASS_NAME, using = "batchDownloadDetailsContainer")
	private WebElement trackingListGrid;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_TLProjectCompanyNav_lnkCompany")
	private WebElement companiesLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_TLProjectCompanyNav_projectSpan")
	private WebElement projectLink;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_hypHome")
	private WebElement homeLink;

	@FindBy(how = How.ID, using = "pr-page-list")
	private WebElement pagination;

	@FindBy(how = How.ID, using = "addNewBlankText")
	private WebElement addNewBlankText;

	@FindBy(how = How.ID, using = "addNewPopUpSubmit")
	private WebElement addNewPopUpSave;

	@FindBy(how = How.XPATH, using = "//div[@id='rename-tracking-list-text']//input[@type='text']")
	private WebElement renameTrackingListText;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrackingListRename']//a[contains(text(),'Save')]")
	private WebElement renamePopupSave;

	@FindBy(how = How.CLASS_NAME, using = "rename-popup-close")
	private WebElement renamePopupCancel;

	@FindBy(how = How.ID, using = "addNewPopUpClose")
	private WebElement addNewPopUpClose;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_TLProjectCompanyNav_lnkCompany")
	private WebElement companyLink;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_ucFooter_logoDiv']/img")
	private WebElement footerLogo;

	@FindBy(how = How.LINK_TEXT, using = "Hidden Projects")
	private WebElement hiddenProjects;

	@FindBy(how = How.LINK_TEXT, using = "My Projects")
	private WebElement myProjects;

	@FindBy(how = How.LINK_TEXT, using = "Unhide")
	private WebElement UnhideProjects;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopShowingPageNo")
	private WebElement pageNoTop;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerBottom_lblBottomShowingPageNo")
	private WebElement pageNoBottom;

	@FindBy(how = How.ID, using = "dvAddTrackList")
	private WebElement addNewBtn;

	@FindBy(how = How.ID, using = "popUpLBTrackingListRename")
	private WebElement popupAddNewTracking;

	@FindBy(how = How.ID, using = "pr-results-per-page")
	private WebElement resultsPerPage;

	@FindBy(how = How.XPATH, using = "//div[@id='popUpLBTrackingListRename']//div[contains(@class,'error-message')]")
	private WebElement errorMessagePopUp;

	@FindBy(how = How.XPATH, using = "//a[@class='listCommands' and text() = 'Edit']")
	private List<WebElement> editLink;

	@FindBy(how = How.XPATH, using = "//a[@class='listCommands' and text() = 'Delete']")
	private List<WebElement> deleteLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'HyperLink3')]")
	private List<WebElement> trackingListNames;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_lblTopSearchResults")
	private WebElement resultPerPageDisplay;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_tlPagerTop_Prev1")
	private WebElement pageNumber2;

	@FindBy(how = How.XPATH, using = "	.//*[@id='pr-page-list']/span")
	private WebElement highlightedpageNumber;

	@FindBy(how = How.CSS, using = "[id*='trackListName']")
	private List<WebElement> trackListName;

	@FindBy(how = How.CSS, using = "div[id*='TrackedList']>a")
	private List<WebElement> companyTrackListName;

	public TrackingList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Accounts - Tracking List Page");
	}

	public void clickOnCompaniesLink() {
		extentTest.log(Status.INFO, "Click on Companies Link");
		SeleniumUtils.isClickable(companiesLink, driver);
		try {
			if (companiesLink.isDisplayed())
				companiesLink.click();
		} catch (Exception ex) {
			return;
		}
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickOnProjectLink() {
		extentTest.log(Status.INFO, "Click on Project Link");
		projectLink.click();
	}

	public ProjectResultsPage clickOnHiddenProjects() {
		extentTest.log(Status.INFO, "Click on Hidden Projects Link");
		hiddenProjects.click();
		return new ProjectResultsPage(driver);
	}

	public ProjectResultsPage clickOnMyProjects() {
		extentTest.log(Status.INFO, "Click on My Projects Link");
		myProjects.click();
		return new ProjectResultsPage(driver);
	}

	public boolean verifyTrackingListDataGrid(String lastnamefirstname) {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("td"));
		Assert.assertEquals(columnHeaders.get(0).getText(), "Click on Tracking List Link to View Results");
		Assert.assertEquals(columnHeaders.get(1).getText(), "Owner");
		Assert.assertEquals(columnHeaders.get(2).getText(), "Type");

		List<WebElement> firstColumn = rows.get(1).findElements(By.tagName("td"));
		Assert.assertEquals(firstColumn.get(1).getText(), "Nonad_With_Specal, Proj_Trackinglist");

		return true;

	}

	public boolean verifyTrackingListColumn1Header() {

		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("td"));
		Assert.assertEquals(columnHeaders.get(0).getText(), "Click on Tracking List Link to View Results");

		return true;
	}

	public Boolean verifyfooterLogoDisplayed() {
		extentTest.log(Status.INFO, "Verify if the footer logo is displayed");
		return footerLogo.isDisplayed();
	}

	public String getfooterLogoText() throws InterruptedException {
		extentTest.log(Status.INFO, "Verify footer logo text");
		Thread.sleep(2000);
		return footerLogo.getAttribute("src");
	}

	public void clickOnUnhideProject() {
		extentTest.log(Status.INFO, "Click on UnHide Project");
		UnhideProjects.click();
	}

	public Boolean verifyAddNewBtnDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Add New' button is displayed");
		return addNewBtn.isDisplayed();
	}

	public Boolean verifyAddNewPopUpDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Tracking Pop Up is displayed");
		return popupAddNewTracking.isDisplayed();
	}

	public void clickOnAddNewButton() {
		extentTest.log(Status.INFO, "Clicking on the 'Add New' button");
		addNewBtn.click();
	}

	public boolean isResultPerPageDisplayed() {
		extentTest.log(Status.INFO, "Results per page is displayed");
		return resultsPerPage.isDisplayed();
	}

	public boolean isPageNoTopDisplayed() {
		extentTest.log(Status.INFO, "Page No is displayed on the top");
		return pageNoTop.isDisplayed();
	}

	public boolean isPageNoBottomDisplayed() {
		extentTest.log(Status.INFO, "page No is displayed on the bottom");
		return pageNoBottom.isDisplayed();
	}

	public boolean isPaginationDisplayed() {
		extentTest.log(Status.INFO, "Pagination is displayed");
		return pagination.isDisplayed();
	}

	public boolean isAddNewBlankTextDisplayed() {
		extentTest.log(Status.INFO, "addNewBlankText is displayed");
		return addNewBlankText.isDisplayed();
	}

	public boolean verifyCursorFocus() {
		extentTest.log(Status.INFO, "If cursor focus is present on the text box");
		return SeleniumUtils.isCursorFocusPresent(addNewBlankText, driver);
	}

	public void clickonSaveFromAddNewPoUp() {
		extentTest.log(Status.INFO, "Click on Save from 'Add New Blank Track List'");
		addNewPopUpSave.click();
	}

	public void clickonCloseFromAddNewPoUp() {
		extentTest.log(Status.INFO, "Click on Close from 'Add New Blank Track List'");
		addNewPopUpClose.click();
	}

	public boolean isErrorMessageOnPopUpDisplayed() {
		extentTest.log(Status.INFO, "Verify if error message on Pop Up displayed");
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errorMessagePopUp));
			return errorMessagePopUp.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void EnterTextInAddNewPopUp(String Name) {
		extentTest.log(Status.INFO, "Enter text in Pop Up");
		addNewBlankText.clear();
		addNewBlankText.sendKeys(Name);
	}

	public boolean isEditDisplayed() {
		extentTest.log(Status.INFO, "Verify if Edit Link displayed");
		return editLink.get(0).isDisplayed();

	}

	public boolean isDeleteDisplayed() {
		extentTest.log(Status.INFO, "Verify if Delete Link displayed");
		return deleteLink.get(0).isDisplayed();

	}

	public void clickOnEditLink() {
		extentTest.log(Status.INFO, "Clicking on the 'Edit' link");
		editLink.get(0).click();
	}

	public void clickOnSaveFromRename() {
		extentTest.log(Status.INFO, "Clicking on the Save button from rename tracking list");
		SeleniumUtils.isClickable(renamePopupSave, driver);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(renamePopupSave));
		renamePopupSave.click();
	}

	public boolean isSaveInRenameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the save button displayed for Rename Tracking List");
		return renamePopupSave.isDisplayed();
	}

	public boolean isCancelInRenameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the cancel button displayed for Rename Tracking List");
		return renamePopupCancel.isDisplayed();
	}

	public void clickOnCancelInRename() {
		renamePopupCancel.click();
	}

	public ProjectResultsPage clickOnExistingTrackngList(String trackingListName) {
		extentTest.log(Status.INFO, "Click on Existing Tracking List");
		driver.findElement(By.linkText(trackingListName)).click();
		return new ProjectResultsPage(driver);
	}

	public CompanyResultsPage clickOnExistingTrackingListCompanies(String trackingListName) {
		extentTest.log(Status.INFO, "Click on Existing Tracking List");
		driver.findElement(By.linkText(trackingListName)).click();
		return new CompanyResultsPage(driver);
	}

	public boolean isNameTextBoxInRenameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the name text box displayed for Rename Tracking List");
		return renameTrackingListText.isDisplayed();
	}

	public void EnterTextInRenamePopUp(String Name) {
		extentTest.log(Status.INFO, "Enter text in 'Rename Tracking List' Pop Up");
		renameTrackingListText.clear();
		renameTrackingListText.sendKeys(Name);
	}

	public void deleteTrackingList() {
		try {
			while (deleteLink.size() != 0) {
				deleteLink.get(0).click();
				driver.switchTo().alert().accept();
				if (!deleteLink.isEmpty()) {
					SeleniumUtils.isClickable(deleteLink.get(0), driver);
				}
			}

			clickOnCompaniesLink();
			while (deleteLink.size() != 0) {

				deleteLink.get(0).click();
				driver.switchTo().alert().accept();
				if (!deleteLink.isEmpty()) {
					SeleniumUtils.isClickable(deleteLink.get(0), driver);
				}
			}
		} catch (Exception ex) {

		}
	}

	public void deleteCompanyTrackingList() {
		int size = deleteLink.size();
		int i = 0;
		while (i <= size - 2) {
			deleteLink.get(i).click();
			driver.switchTo().alert().accept();
			i++;
			size = deleteLink.size();

		}
	}

	public void clickonCompanyLink() {
		companyLink.click();
	}

	public Integer getTrackingListSize() {
		int size = deleteLink.size();
		return size;

	}

	public ProjectResultsPage clickOnTrackingList(int count) {
		extentTest.log(Status.INFO, "Click on the tracking list as provided");
		trackingListNames.get(count).click();
		return new ProjectResultsPage(driver,true);

	}

	public void selectResultPerPageDisplayed(String results) {
		extentTest.log(Status.INFO, "select" + results + " results to be displayed on the page");
		Select select = new Select(resultPerPageDisplay);
		select.selectByVisibleText(results);
	}

	public void clickOnPageNumber2() {
		extentTest.log(Status.INFO, "Click on second Page number.");
		if (!SeleniumUtils.isVisible(pageNumber2, driver))
			throw new SkipException("Page number 2 is not present: Insufficient test data for this test");
		pageNumber2.click();
	}

	public Boolean IsPageNumber1HighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Page number 2 is highlighted in bold");
		String highlightedPage = highlightedpageNumber.getText();
		return highlightedPage.equals("1");
	}

	public TrackingListPopupDialog clickEditLink(final String trackingListName) {
		final List<WebElement> trackingList = driver
				.findElements(By.cssSelector("a[onclick*='" + trackingListName + "']"));
		if (!trackingList.isEmpty()) {
			trackingList.get(0).click();
		}
		return new TrackingListPopupDialog(driver);
	}

	public void clickOnHomeLink() {
		extentTest.log(Status.INFO, "Clicking on Home Link");
		homeLink.click();
	}

	public List<String> getTrackListName() {
		List<String> trackListNames = new ArrayList<String>();
		for (WebElement trackList : trackListName) {
			trackListNames.add(trackList.getText());
		}
		return trackListNames;
	}

	public List<String> getCompanyTrackListName() {
		List<String> trackListNames = new ArrayList<String>();
		for (WebElement trackList : companyTrackListName) {
			trackListNames.add(trackList.getText());
		}
		return trackListNames;
	}
}

package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class MyDownloadsPage extends MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.LINK_TEXT, using = "Company")
	private WebElement companyLink;

	@FindBy(how = How.LINK_TEXT, using = "Documents")
	private WebElement documentsLink;

	@FindBy(how = How.XPATH, using = "//div[@class='breadcrumb-container clearfix']/span[last()]")
	private WebElement lastBreadCrumb;

	@FindBy(how = How.XPATH, using = "//div[@class='breadcrumb-container clearfix']//a")
	private List<WebElement> breadCrumbContainer;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblErrorMsg")
	private WebElement noFileMessage;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerTop$lblTopSearchResults")
	private WebElement resultDropDown;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerBottom$lblBottomSearchResults")
	private WebElement resultDropDownAtBottom;

	@FindBy(how = How.ID, using = "project-results-bottom")
	private WebElement projectTabBar;

	@FindBy(how = How.ID, using = "My-Account---My-Downloads---Company")
	private WebElement breadCrumbCompanyTab;

	@FindBy(how = How.ID, using = "My-Account---My-Downloads---Project")
	private WebElement breadCrumbProjectTab;

	@FindBy(how = How.ID, using = "btnBatchActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private WebElement DeleteLink;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_hypHome")
	private WebElement homeLink;

	@FindBy(how = How.ID, using = "project_check_all_title")
	private WebElement selectAllchkbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptDownload_ctl00_ckbHeaderSelectAll")
	private WebElement selectAllchkboxProjectandComapanies;

	@FindBy(how = How.ID, using = "rdDownloadLater")
	private WebElement downloadLaterRadioBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='downloadDocumentsPopup']/div[3]/div/div[2]/a")
	private WebElement saveBtn;
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblName')]")
	private List<WebElement> downloadNameList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblDate')]")
	private List<WebElement> createdDateList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblDate')]")
	private List<WebElement> dateCreatedList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblType')]")
	private List<WebElement> typeList;

	@FindBy(how = How.XPATH, using = "//span[@id='spanLnkStatus']")
	private List<WebElement> statusList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pagerTop_First")
	private WebElement paginationAtTop;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pagerBottom_First")
	private WebElement paginationAtBottom;

	@FindBy(how = How.ID, using = "project_check_all_title")
	private WebElement selectAllChkbox;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ckbSelect')]")
	private List<WebElement> downloadBatchChkboxList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pagerTop_lblTopShowingPageNo")
	private WebElement topTotalRecordPageCount;

	@FindBy(how = How.XPATH, using = "//div[@class='downloadErrorMessage']")
	private WebElement nonSelectionDeleteMsg;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.CSS, using = ".batchDownloadDetailsContainer tr")
	private List<WebElement> batchDownloadDetailsContainer;

	public MyDownloadsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public Boolean checkforValidationMessage(String message) {
		return driver.getPageSource().contains(message);
	}

	public void clickOnComapnyLink() {
		extentTest.log(Status.INFO, "Clicking on Company Link");
		companyLink.click();
	}

	public void clickOnSaveBtn() {
		extentTest.log(Status.INFO, "Clicking on Save Button");
		saveBtn.click();
	}

	public void clickOnDownloadLater() {
		extentTest.log(Status.INFO, "Clicking on Download later Link");
		downloadLaterRadioBtn.click();
	}

	public void clickOnselectAllchkboxProjectandComapanies() {
		extentTest.log(Status.INFO, "Clicking on SelectAll checkbox for companies and project");
		selectAllchkboxProjectandComapanies.click();

	}

	public boolean isSelectAllchkboxProjectandComapaniesPresent() {
		extentTest.log(Status.INFO, "Verify SelectAll checkbox for companies and project");
		SeleniumUtils.isVisible(selectAllchkboxProjectandComapanies, driver);
		return selectAllchkboxProjectandComapanies.isDisplayed();

	}

	public void clickOnHomeLink() {
		extentTest.log(Status.INFO, "Clicking on Home Link");
		homeLink.click();

	}

	public void clickOnDocumentsLink() {
		extentTest.log(Status.INFO, "Clicking on Documents Link");
		documentsLink.click();

	}

	public boolean isResultDropDowndisplayed() {
		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed ");
		return resultDropDown.isDisplayed();
	}

	public boolean checkBreadCrumbProject() {
		return breadCrumbProjectTab.isDisplayed();
	}

	public boolean checkBreadCrumbCompany() {
		return breadCrumbCompanyTab.isDisplayed();
	}

	public boolean isProjectBarDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project bar is displayed ");
		return projectTabBar.isDisplayed();
	}

	public boolean checkColorOfProjectBar() {
		extentTest.log(Status.INFO, "Verify color of project Bar in download list ");
		String tabBarColor = projectTabBar.getCssValue("color");
		return tabBarColor.equals("rgba(16, 44, 66, 1)");
	}

	public void mouseoverActionandClickDeleteMenu() {
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdown).perform();
		extentTest.log(Status.INFO, "Check if Delete Menu is Enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(DeleteLink));
		DeleteLink.click();
	}

	public String noFileMessage() {

		extentTest.log(Status.INFO, "Verify the message when No Downloads are available ");
		return noFileMessage.getText();
	}

	public boolean isNoDownloadMessageDisplayed() {
		extentTest.log(Status.INFO, "Verify that No Download message is displayed ");

		String expected = noFileMessage.getText();
		return expected.equals("You do not have any batch downloads for projects");

	}

	public boolean isNoDownloadMessageDisplayedForCompanies() {
		extentTest.log(Status.INFO, "Verify that No Download message is displayed ");

		String expected = noFileMessage.getText();
		return expected.equals("You do not have any batch downloads for companies");

	}

	public boolean verifyMyDownloadsDataGridColumnHeaders() {
		WebElement table = driver.findElement(By.className("batchDownloadDetailsContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		List<WebElement> cols = rows.get(0).findElements(By.tagName("td"));

		Assert.assertEquals(cols.get(1).getText(), "Download Name");
		Assert.assertEquals(cols.get(2).getText(), "Date Created");
		Assert.assertEquals(cols.get(3).getText(), "Type");
		Assert.assertEquals(cols.get(4).getText(), "Status");
		return true;
	}

	public boolean verifyMyDownloadsBreadCrumb(String breadcrumbText) {
		String lastBreadcrumb = lastBreadCrumb.getText();
		if (lastBreadcrumb.equals(breadcrumbText)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean IsBreadCrumbDisabled() {
		if (SeleniumUtils.isClickable(lastBreadCrumb, driver))
			return false;
		else
			return true;
	}

	// Verify if breadcrumb is clickable.
	public boolean checkBreadCrumbIsNotClickable() {
		extentTest.log(Status.INFO, "Verify if breadcrumb is clickable.");
		if (breadCrumbContainer.size() == 0)
			return true;

		return false;
	}

	public boolean IsMessageDisabled(String message) {

		return driver.getPageSource().contains(message);
	}

	/**
	 * Verify the download batch name of selected row number
	 * 
	 * @param downladName
	 *            : hold the download name need to compare
	 * @param rowNumber
	 *            : hold the row number whose download name need to compare
	 * @return : boolean result
	 */
	public boolean verifyDownloadName(String downladName) {
		extentTest.log(Status.INFO, "Verify the download batch name of selected row number.");
		return MyAccountCommonContainerPage.checkEditStringPresentInTableNameList(downladName, downloadNameList);
	}
	
	/**
	 * This method verifies the existence of download list.
	 * @return download list size.
	 */
	public int getDownloadLinkCount(){
		extentTest.log(Status.INFO, "Verify the download list.");
		return downloadNameList.size();
	}

	/**
	 * Verify the download batch date created.
	 * 
	 * @param date
	 *            : hold the date name need to compare
	 * @return : boolean result
	 */

	public boolean verifyDownloadDateCreated(String date) {
		extentTest.log(Status.INFO, "Verify the download batch date created.");
		return (dateCreatedList.get(0).getText().contains(date)
				|| ("0" + dateCreatedList.get(0).getText()).contains(date));
	}

	public boolean verifyDownloadDateCreated(String date, int rowNumber) {
		extentTest.log(Status.INFO, "Verify the download batch date created of selected row number : " + rowNumber);
		return (dateCreatedList.get(rowNumber - 1).getText().contains(date)
				|| ("0" + dateCreatedList.get(rowNumber - 1).getText()).contains(date));

	}

	/**
	 * Verify the download batch type
	 * 
	 * @param type
	 *            : hold the type need to compare
	 * @return : boolean result
	 */

	public boolean verifyDownloadType(String type) {
		extentTest.log(Status.INFO, "Verify the download batch type.");
		return typeList.get(0).getText().equalsIgnoreCase(type);
	}

	public boolean verifyDownloadType(String type, int rowNumber) {
		extentTest.log(Status.INFO, "Verify the download batch type of selected row number : " + rowNumber);
		return typeList.get(rowNumber - 1).getText().equalsIgnoreCase(type);

	}

	/**
	 * Verify the download batch status
	 * 
	 * @param status
	 *            : hold the download status need to compare
	 * @return : boolean result
	 */

	public boolean verifyDownloadStatus(String status) {
		extentTest.log(Status.INFO, "Verify the download batch.");
		return statusList.get(0).getText().contains(status);
	}

	public boolean verifyDownloadStatus(String status, int rowNumber) {
		extentTest.log(Status.INFO, "Verify the download batch status of selected row number : " + rowNumber);
		return statusList.get(rowNumber - 1).getText().contains(status);

	}

	// Check if pagination is displayed at Top and return boolean result
	public boolean isPaginationDisplayedAtTop() {
		extentTest.log(Status.INFO, "Check if pagination is displayed at Top.");
		return CommonUtils.checkElementExist(paginationAtTop, driver);
	}

	// Check if pagination is displayed at bottom and return boolean result
	public boolean isPaginationDisplayedAtBottom() {
		extentTest.log(Status.INFO, "Check if pagination is displayed at Bottom.");
		return CommonUtils.checkElementExist(paginationAtBottom, driver);
	}

	// Check if Result Per Page Dropdown is displayed at Bottom.
	public boolean isResultPerPageDropdownDisplayedAtBottom() {
		extentTest.log(Status.INFO, "Check if Result Per Page Dropdown is displayed at Bottom.");
		return CommonUtils.checkElementExist(resultDropDownAtBottom, driver);
	}

	// return the result of option list in Result Per Page
	public boolean checkResultPerPageOption() {
		extentTest.log(Status.INFO, "Verify option list of result per page dropdown.");
		return MyAccountCommonContainerPage.checkResultPerPageDropdownOption(resultDropDown);
	}

	// Check the value set in Result per page dropdown.
	public boolean checkSetValueOfResultPerPage(String valueToCompare) {
		extentTest.log(Status.INFO, "Verify value set in Result per page dropdown.");
		Select typeSelect = new Select(resultDropDown);
		return typeSelect.getFirstSelectedOption().getText().toUpperCase().equals(valueToCompare);
	}

	// Check if Delete Menu is present under Actions drowpdown.
	public boolean checkDeleteMenuPresentUnderActionDropdown() {
		extentTest.log(Status.INFO, "Check if Delete Menu is present under Actions drowpdown.");
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdown).build().perform();
		return CommonUtils.checkElementExist(DeleteLink, driver);
	}

	// Check if Delete Menu is present under Actions drowpdown.
	public void clickOnDeleteMenuPresentUnderActionDropdown() {
		extentTest.log(Status.INFO, "Click on Delete Menu under Actions drowpdown.");
		Actions action = new Actions(driver);
		action.moveToElement(actionsDropdown).build().perform();
		DeleteLink.click();
	}

	// Click on Select All checkbox for download batch.
	public void clickOnSelectAllCheckbox() {
		extentTest.log(Status.INFO, "Click on Select All checkbox for download batch.");
		if (SeleniumUtils.isClickable(selectAllChkbox, driver))
			selectAllChkbox.click();

	}

	// Click on multiple download batch checkbox for download batch.
	public void clickOnMultileDownloadBatchCheckbox(int totalDownloadBatchToSelect) {
		extentTest.log(Status.INFO, "Click on multiple download batch checkbox for download batch..");
		if (totalDownloadBatchToSelect <= downloadBatchChkboxList.size()) {
			for (int i = 0; i < totalDownloadBatchToSelect; i++)
				downloadBatchChkboxList.get(i).click();
		} else {
			extentTest.log(Status.INFO,
					"Total selection count metion is greated than the available download batch count to select.");
		}
	}

	// Click on first download batch checkbox for download batch.
	public void clickOnFirstDownloadBatchCheckbox() {
		extentTest.log(Status.INFO, "Click on first download batch checkbox for download batch..");
		if (downloadBatchChkboxList.size() > 0) {
			downloadBatchChkboxList.get(0).click();
		} else {
			extentTest.log(Status.INFO, "No download batch available to clicked/select.");
		}
	}

	// Check total download batch count download batch.
	public boolean checkTotalTopPageCount(String totalCountToCompare) {
		extentTest.log(Status.INFO, "Check total download batch count download batch.");
		return topTotalRecordPageCount.getText().trim().toUpperCase().equals(totalCountToCompare.toUpperCase());
	}

	// return total download batch count download batch.
	public String getTotalTopPageCount() {
		extentTest.log(Status.INFO, "Check total download batch count download batch.");
		return topTotalRecordPageCount.getText().trim();
	}

	// Check error message when user click on Delete batch without selecting any
	// download batch to delete.
	public boolean checkNonSelectionDeleteMessage(String messageToCheck) {
		extentTest.log(Status.INFO,
				"Check error message when user click on Delete batch without selecting any download batch to delete.");
		return nonSelectionDeleteMsg.getText().trim().toUpperCase().equals(messageToCheck.toUpperCase());
	}

	public boolean verifySortingDownloadName() {
		extentTest.log(Status.INFO, "Check download list is sorted as per created date.");
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(createdDateList),
				CommonUtils.sortDateListInReverse(CommonUtils.getListFromWebElements(createdDateList)));
	}

	// Check Select All checkbox is displayed.
	public boolean isSelectAllCheckboxDisplayed() {
		extentTest.log(Status.INFO, "Check Select All checkbox is displayed.");
		return selectAllchkbox.isDisplayed();
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		System.out.println("ss:"+ SeleniumUtils.isVisible(myDownloadBackgroundPage, driver));
		System.out.println("S:"+myDownloadBackgroundPage.getCssValue("background"));
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}

	public void deleteMyDownload(final String downloadName) {
		extentTest.log(Status.INFO, "Delete my download");
		List<WebElement> containerRows = batchDownloadDetailsContainer;
		for (final WebElement row : containerRows) {
			if (downloadName.equalsIgnoreCase(row.findElement(By.cssSelector("td:nth-of-type(2)")).getText())) {
				row.findElement(By.cssSelector("td:nth-of-type(1) input:nth-of-type(1)")).click();
				break;
			}
		}
		clickOnDeleteMenuPresentUnderActionDropdown();
	}
}

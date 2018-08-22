package com.ddaqe.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.helper.Browser;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class KeystrokeResearchApp_Page {
	private WebDriver driver;
	private ExtentTest extentTest;
	protected Browser browser;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lnkSignout")
	private WebElement SignOutLink;

	@FindBy(how = How.ID, using = "MainContent_txtEmail")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtPassword")
	private WebElement passwordTxtField;

	@FindBy(how = How.ID, using = "MainContent_btnLogin")
	private WebElement signInBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_txtLicenseNo")
	private WebElement licenseKeyField;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_btnSubmit")
	private WebElement submitButton;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblMsg")
	private WebElement ErrorMsg;

	@FindBy(how = How.ID, using = "ctl00_ucHeader_lnk_SignOut")
	private WebElement SignOutBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_txtLicenseNumber")
	private WebElement txtLicenseNumber;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_btnGetKeystroke")
	private WebElement btnGetKeystroke;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_txtLicenseKeystorke")
	private WebElement txtLicenseKeystorke;
	@FindBy(how = How.ID, using = "clear-keystroke-button")
	private WebElement clearkeystrokebutton;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_btnRunSearch")
	private WebElement btnRunSearch;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_lblStatus")
	private WebElement lblStatus;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_lblError")
	private WebElement lblError;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_rbPublishedBetween")
	private WebElement rbPublishedBetween;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_txtFromDate")
	private WebElement FromDatetxt;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_txtToDate")
	private WebElement Todatetxt;
	@FindBy(how = How.XPATH, using = "//*[@class='dp-nav-next-year']")
	private WebElement NextYear;
	@FindBy(how = How.XPATH, using = "//*[@id='dp-popup']//table/tbody/tr[1]/td[1]")
	private WebElement FromDate;
	@FindBy(how = How.XPATH, using = "//*[@id='dp-popup']//table/tbody/tr[5]/td[7]")
	private WebElement ToDate;
	@FindBy(how = How.ID, using = "project-showing")
	private WebElement ProjectResult;
	@FindBy(how = How.XPATH, using = "//*[@id='project-showing']/span[1]")
	private WebElement ProjectResultCount;
	@FindBy(how = How.XPATH, using = "//*[@type='checkbox']")
	private List<WebElement> checkboxList;
	@FindBy(how = How.ID, using = "Keystroke-Research")
	private WebElement BreadCrumb_KeystrokeResearch;
	@FindBy(how = How.ID, using = "Search-Results")
	private WebElement BreadCrumb_SearchResults;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_ddlSortBy")
	private WebElement SoryBy_DD;
	@FindBy(how = How.ID, using = "pr-page-list")
	private WebElement Pegination;
	@FindBy(how = How.ID, using = "ctl00_ucHeader_backToSearchmanagement")
	private WebElement backToSearchmanagementLink;
	@FindBy(how = How.ID, using = "ctl00_ucHeader_lbtnKeyStroke")
	private WebElement KeyStrokeLink;
	@FindBy(how = How.ID, using = "ctl00_ucHeader_lblUser")
	private WebElement UserName;
	@FindBy(how = How.ID, using = "header-left")
	private WebElement HeaderLogo;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_rbLastWeek")
	private WebElement rbLastWeek;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_rbLastMonth")
	private WebElement rbLastMonth;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_rbLastYear")
	private WebElement rbLastYear;

	@FindBy(how = How.CSS, using = ".fromDate .dp-choose-date")
	private WebElement fromDatePicker;
	@FindBy(how = How.CSS, using = ".toDate .dp-choose-date")
	private WebElement toDatePicker;

	@FindBy(how = How.ID, using = "ctl00_lblDrNumber")
	private WebElement ProjectTitle;
	@FindBy(how = How.ID, using = "ctl00_lnkFirm")
	private WebElement FirmLink;
	/*@FindBy(how = How.ID, using = "ctl00_lnkBidders")
	private WebElement lnkBidders;*/
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'lnkBidders')]")
	private List<WebElement> lnkBidders;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'lnkPlans')]")
	private List<WebElement> lnkPlans;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'lnkAddenda')]")
	private List<WebElement> lnkAddenda;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'lnkSpecs')]")
	private List<WebElement> lnkSpecs;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'_lblPublishDate')]")
	private List<WebElement> PublishDateList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_chkproject")
	private WebElement projectCheckBox;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_chkProjectItem")
	private WebElement projectItemCheckBox;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_chkItem")
	private WebElement itemCheckBox;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolder_chkPermit")
	private WebElement permitCheckBox;
	@FindBy(how = How.ID, using = "remaining-characters-label")
	private WebElement remainingCharactersLabel;
	@FindBy(how = How.ID, using = "remaining-characters-left")
	private WebElement remainingCharactersLeft;

	public KeystrokeResearchApp_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean Is_SignOutBtn_Displayed() {
		extentTest.log(Status.INFO, "Ensured that SignOutBtn is displayed");
		return SignOutBtn.isDisplayed();

	}

	public KeystrokeResearchApp_Page loginAs(String emailAddress, String password, String url) {
		extentTest.log(Status.INFO, "Navigate to BidPro URL");
		driver.get(url);
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.clear();
		emailAddressTxtField.sendKeys(emailAddress);
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.clear();
		passwordTxtField.sendKeys(password);
		extentTest.log(Status.INFO, "Click on Login button.");
		signInBtn.click();
		return new KeystrokeResearchApp_Page(driver);
	}

	public void ClickOnLicenseField() {
		extentTest.log(Status.INFO, "Click on license field");
		licenseKeyField.click();
	}

	public void enterValueInLicenseField(String License_Number) {
		extentTest.log(Status.INFO, " Enter value in Activate License Field");
		licenseKeyField.sendKeys(License_Number);
	}

	public void clickOnSubmitButton() {
		extentTest.log(Status.INFO, " Click on Submit field");
		submitButton.click();
	}

	public boolean isExpiredLicenseErrorMsgDisplayed() {
		extentTest.log(Status.INFO, "Verify the Error message on submitting Expired License ");

		String expected = ErrorMsg.getText();
		return expected.equals(
				"The license number you have entered is invalid. Please check your license number and enter again");

	}

	public void clickOnSignOutBtn() {
		extentTest.log(Status.INFO, " Click on SignOut field");
		SignOutBtn.click();
	}

	public void enterLicenseNumber(String license) {
		extentTest.log(Status.INFO, " Enter license number ");
		txtLicenseNumber.clear();
		txtLicenseNumber.sendKeys(license);
	}

	public void enterLicenseKeystorke(String LicenseKeystorke) {
		extentTest.log(Status.INFO, " Enter LicenseKeystorke ");
		txtLicenseKeystorke.sendKeys(LicenseKeystorke);
	}

	public void clickOnbtnGetKeystroke() {
		extentTest.log(Status.INFO, " Click on btnGetKeystroke");
		btnGetKeystroke.click();
	}

	public void clickOnclearkeystrokebutton() {
		extentTest.log(Status.INFO, " Click on clearkeystrokebutton");
		clearkeystrokebutton.click();
		txtLicenseKeystorke.clear();
	}

	public void clickOnbtnRunSearch() {
		extentTest.log(Status.INFO, " Click on btnRunSearch");
		btnRunSearch.click();
	}

	public boolean isRunSearchButtonEnabled() {
		extentTest.log(Status.INFO, "Verify status of Run Search button.");
		return btnRunSearch.isEnabled();
	}

	public String gettxtLicenseKeystorketxt() {
		extentTest.log(Status.INFO, "Ensure LicenseKeystorke field is empty");
		return txtLicenseKeystorke.getText();
	}

	public boolean isLicenseKeystorketxtDisplayed() {
		extentTest.log(Status.INFO, "Ensure LicenseKeystorke field is empty");
		return txtLicenseKeystorke.getText().isEmpty();
	}

	public String gettxtlblStatus() {
		extentTest.log(Status.INFO, "Get error message");
		return lblStatus.getText();
	}

	public String gettxtlblError() {
		extentTest.log(Status.INFO, "Get error message");
		return lblError.getText();
	}

	public void clickOnrbPublishedBetween() {
		extentTest.log(Status.INFO, " Click on rbPublishedBetween");
		rbPublishedBetween.click();
	}

	public boolean isPublishedBetweenSelected() {
		extentTest.log(Status.INFO, "Verify status of Published Between radio button.");
		return rbPublishedBetween.isSelected();
	}

	public void clickOnFromDatetxt() {
		extentTest.log(Status.INFO, " Click on FromDatetxt");
		FromDatetxt.click();
	}

	public void clickOnTodatetxt() {
		extentTest.log(Status.INFO, " Click on Todatetxt");
		Todatetxt.click();
	}

	public void clickOnNextYear() {
		extentTest.log(Status.INFO, " Click on NextYear");
		NextYear.click();
	}

	public void clickOnFromDate() {
		extentTest.log(Status.INFO, " Click on FromDate");
		FromDate.click();
	}

	public void clickOnToDate() {
		extentTest.log(Status.INFO, " Click on ToDate");
		ToDate.click();
	}

	public boolean IsProjectResult_Displayed() {
		extentTest.log(Status.INFO, "Ensure ProjectResult displayed");
		return ProjectResult.isDisplayed();
	}

	public int getExactProjectCount() throws InterruptedException {
		Thread.sleep(10000);
		extentTest.log(Status.INFO, "Get exact project count");
		String count = ProjectResultCount.getText();
		String[] parts = count.split("f");
		String part2 = parts[1];
		return Integer.parseInt(part2.replace(" ", "").replace(",", ""));
	}

	public void clickOnContentScopeCbk(int idx) {
		extentTest.log(Status.INFO, " Click on" + idx + "ContentScopeCbk");
		checkboxList.get(idx).click();
	}

	public void GotoBackPage() throws InterruptedException {
		extentTest.log(Status.INFO, "Go back to KeystrokeResearch page ");
		SeleniumUtils.goToBackPage(driver);
	}

	public boolean IsContentScopeCbk_Selected(int idx) {
		extentTest.log(Status.INFO, " Check " + idx + "ContentScopeCbk");
		return checkboxList.get(idx).isSelected();
	}

	public boolean Is_BreadCrumb_KeystrokeResearch_Displayed() {
		extentTest.log(Status.INFO, "Ensure keystrokeResearch breadcrumb displayed");
		return BreadCrumb_KeystrokeResearch.isDisplayed();
	}

	public boolean Is_BreadCrumb_BreadCrumb_SearchResults_Displayed() {
		extentTest.log(Status.INFO, "Ensure BreadCrumb_SearchResults breadcrumb displayed");
		return BreadCrumb_SearchResults.isDisplayed();
	}

	public void clickOn_BreadCrumb_KeystrokeResearch() {
		extentTest.log(Status.INFO, " Click on BreadCrumb_KeystrokeResearch");
		BreadCrumb_KeystrokeResearch.click();
	}

	public boolean Is_SoryBy_DD_Displayed() {
		extentTest.log(Status.INFO, "Ensure SoryBy_DD displayed");
		return SoryBy_DD.isDisplayed();
	}

	public boolean Is_Pegination_Displayed() {
		extentTest.log(Status.INFO, "Ensure Pegination displayed");
		return Pegination.isDisplayed();
	}

	public String clickOn_ProjectTitle() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on ProjectTitle");
		ProjectTitle.click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;

	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public String clickOn_FirmLink() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on firm");
		FirmLink.click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;
	}

	public String clickOn_lnkBidders() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on lnkBidders");
		CommonUtils.scrollDownTillElement(lnkBidders.get(0), driver);
		lnkBidders.get(0).click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;
	}

	public String clickOn_lnkPlans() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on lnkPlans");
		CommonUtils.scrollDownTillElement(lnkPlans.get(0), driver);
		lnkPlans.get(0).click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;
	}

	public String clickOn_lnkAddenda() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on lnkAddenda");
		CommonUtils.scrollDownTillElement(lnkAddenda.get(0), driver);
		lnkAddenda.get(0).click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;
	}

	public String clickOn_lnkSpecs() throws InterruptedException {
		extentTest.log(Status.INFO, " Click on lnkSpecs");
		CommonUtils.scrollDownTillElement(lnkSpecs.get(0), driver);
		lnkSpecs.get(0).click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		String cUrl = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return cUrl;
	}

	public void clickOnbackToSearchmanagementLink() {
		extentTest.log(Status.INFO, " Click on backToSearchmanagementLink");
		backToSearchmanagementLink.click();
	}

	public void clickOnKeyStrokeLink() {
		extentTest.log(Status.INFO, " Click on KeyStrokeLink");
		KeyStrokeLink.click();
	}

	public boolean IsSignInBtn_Displayed() {
		extentTest.log(Status.INFO, "Successfully loggged out");
		return signInBtn.isDisplayed();
	}

	public boolean IsKeyStrokeLink_Displayed() {
		extentTest.log(Status.INFO, "Ensure Search Management home page displayed ");
		return KeyStrokeLink.isDisplayed();
	}

	public boolean ISbackToSearchmanagementLink_Displayed() {
		extentTest.log(Status.INFO, " Check backToSearchmanagementLink displayed");
		return backToSearchmanagementLink.isDisplayed();
	}

	public boolean ISUserName_Displayed() {
		extentTest.log(Status.INFO, " Check UserName displayed");
		return UserName.isDisplayed();
	}

	public boolean ISHeaderLogo_Displayed() {
		extentTest.log(Status.INFO, " Check HeaderLogo displayed");
		return HeaderLogo.isDisplayed();
	}

	public void clickOnrbLastWeek() {
		extentTest.log(Status.INFO, " Click on rbLastWeek");
		rbLastWeek.click();
	}

	public void clickOnrbLastMonth() {
		extentTest.log(Status.INFO, " Click on rbLastMonth");
		rbLastMonth.click();
	}

	public void clickOnrbLastYear() {
		extentTest.log(Status.INFO, " Click on rbLastYear");
		rbLastYear.click();
	}

	public boolean isLastYearCheckBoxSelected() {
		extentTest.log(Status.INFO, " Verify status of Last Year check box.");
		return rbLastYear.isSelected();
	}

	public boolean checkPublishDate(int dateUnit) throws ParseException {
		List<WebElement> publishDateList = PublishDateList;
		List<String> publishDates = new ArrayList<String>();
		publishDates.addAll(CommonUtils.getListFromWebElements(publishDateList));
		Date uiDate, maxDate, minDate;
		Calendar cal = null;
		boolean isVerified = false;
		for (String pubDate : publishDates) {
			uiDate = new SimpleDateFormat("MM/dd/yyyy").parse(pubDate);
			cal = Calendar.getInstance();
			maxDate = cal.getTime();
			cal.add(dateUnit, -1);
			minDate = cal.getTime();
			if (uiDate.after(minDate) && uiDate.before(maxDate)) {
				isVerified = true;
			} else {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public void selectLastYearDateUsingDatePicker() {
		clickOnFromDatePicker();
		WebElement dpPrevYear = driver.findElement(By.cssSelector("[class='dp-nav-prev-year']"));
		dpPrevYear.click();
		WebElement dpCalendar = driver.findElement(By.cssSelector("[class='dp-calendar']"));
		List<WebElement> columns = dpCalendar.findElements(By.cssSelector("td[class*='current-month'"));
		Calendar cal = Calendar.getInstance();
		String dateToBeSelected = String.valueOf(cal.getTime().getDate());
		for (WebElement cell : columns) {
			if (cell.getText().equals(dateToBeSelected)) {
				cell.click();
				break;
			}
		}
	}

	public void selectCurrentDateUsingDatePicker() {
		clickOnToDatePicker();
		WebElement dpCalendar = driver.findElement(By.cssSelector("[class='dp-calendar']"));
		List<WebElement> columns = dpCalendar.findElements(By.cssSelector("td[class*='current-month'"));
		Calendar cal = Calendar.getInstance();
		String dateToBeSelected = String.valueOf(cal.getTime().getDate());
		for (WebElement cell : columns) {
			if (cell.getText().equals(dateToBeSelected)) {
				cell.click();
				break;
			}
		}
	}

	public void clickOnFromDatePicker() {
		extentTest.log(Status.INFO, " Click on From Date picker.");
		fromDatePicker.click();
	}

	public void clickOnToDatePicker() {
		extentTest.log(Status.INFO, " Click on To Date picker.");
		toDatePicker.click();
	}

	public boolean isProjectCheckBoxSelected() {
		extentTest.log(Status.INFO, " Verify status of Project check box.");
		return projectCheckBox.isSelected();
	}

	public boolean isProjectItemCheckBoxSelected() {
		extentTest.log(Status.INFO, " Verify status of Project Item check box.");
		return projectItemCheckBox.isSelected();
	}

	public boolean isItemCheckBoxSelected() {
		extentTest.log(Status.INFO, " Verify status of Item check box.");
		return itemCheckBox.isSelected();
	}

	public boolean isPermitCheckBoxSelected() {
		extentTest.log(Status.INFO, " Verify status of Permit check box.");
		return permitCheckBox.isSelected();
	}

	public boolean isremainingCharactersStatusDisplayed() {
		extentTest.log(Status.INFO, "Check presence of Remaining Characters status.");
		return remainingCharactersLabel.getText().contains("Characters remaining:")
				&& remainingCharactersLeft.getText().length() > 0;
	}

}

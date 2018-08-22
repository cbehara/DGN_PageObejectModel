package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class CompanyContactsPage extends CompanyCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm;

	@FindBy(how = How.ID, using = "pager-results-per-page")
	private WebElement resultsPerPage;

	@FindBy(how = How.ID, using = "lnkDownloadCompany")
	private WebElement downloadCompanyActionMenu;

	@FindBy(how = How.ID, using = "lnkTrackCompany")
	private WebElement trackCompanyLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_imgTrackedIcon")
	private WebElement trackCompanyIcon;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_companyOverview_divCompanyTrackedSection']//a")
	private List<WebElement> trackCompanyList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_lnkProfile")
	private WebElement companyProfileLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_dropdownList")
	private WebElement QuickSearchDropdown;

	@FindBy(how = How.XPATH, using = ".//*[@id='divCompanyDetails']/div[1]")
	private WebElement breadCrumb;

	@FindBy(how = How.XPATH, using = ".//*[@id='companydetails']/div[1]")
	private WebElement companyOverview;

	@FindBy(how = How.XPATH, using = ".//*[@id='companydetails']/div[2]")
	private WebElement companyListView;

	@FindBy(how = How.ID, using = "pr-page-list")
	private WebElement paginationSection;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_lblContactName")
	private WebElement contactName;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_lblAddress")
	private WebElement contactAddress;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_hypWebsite")
	private WebElement website;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_lblPhoneVal")
	private WebElement phone;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_lblFaxVal")
	private WebElement fax;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptContactDetails_ctl00_hypEmail")
	private WebElement email;

	public boolean isContactNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if contact Name is displayed");
		return contactName.isDisplayed();
	}

	public boolean isAddressDisplayed() {
		extentTest.log(Status.INFO, "Verify if address is displayed");
		return contactAddress.isDisplayed();
	}

	public boolean isWebsiteDisplayed() {
		extentTest.log(Status.INFO, "Verify if website is displayed");
		return website.isDisplayed();
	}

	public boolean isPhoneDisplayed() {
		extentTest.log(Status.INFO, "Verify if phone is displayed");
		return phone.isDisplayed();
	}

	public boolean isFaxDisplayed() {
		extentTest.log(Status.INFO, "Verify if fax is displayed");
		return fax.isDisplayed();
	}

	public boolean isEmailDisplayed() {
		extentTest.log(Status.INFO, "Verify if email is displayed");
		return email.isDisplayed();
	}

	@FindBy(how = How.ID, using = "prev-next-nav")
	private WebElement paginationDiv;

	public CompanyContactsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Company Contacts Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public Boolean mouseOverActionandChecktrackCompanyDisplayed() {
		extentTest.log(Status.INFO, "Mouse over Actions and verify if Track Company Link");
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Track Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyLink));
		return trackCompanyLink.isDisplayed();
	}

	@Override
	public TrackPopUpPage clickOnTrackCompanyActionsLink() {
		extentTest.log(Status.INFO, "Click on the Track Company Actions Link");
		trackCompanyLink.click();
		return new TrackPopUpPage(driver);
	}

	public boolean verifyTrackNameInTrackingList(String TrackName) {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		return CommonUtils.getListFromWebElements(trackCompanyList).contains(TrackName);
	}

	public boolean isTrackingIconDisplayed() {
		extentTest.log(Status.INFO, "Verify Tracking Icon displayed");
		return trackCompanyIcon.isDisplayed();
	}

	public boolean isCompanyOverviewSectionDisplayed() {
		extentTest.log(Status.INFO, "Verify if company Overview Section is displayed");
		SeleniumUtils.isVisible(companyOverview, driver);
		return companyOverview.isDisplayed();
	}

	public boolean isCompanyListviewSectionDisplayed() {
		extentTest.log(Status.INFO, "Verify if company List view Section is displayed");
		return companyListView.isDisplayed();
	}

	public boolean isPaginationSectionDisplayed() {
		extentTest.log(Status.INFO, "Verify if pagination Section is displayed");
		return paginationSection.isDisplayed();
	}

	@Override
	public CompanyPage clickOnCompanyProfile() {
		extentTest.log(Status.INFO, "Clicking on Company Profile");
		companyProfileLink.click();
		return new CompanyPage(driver);
	}

	public boolean isQuickSearchDrodownDisplayed() {
		extentTest.log(Status.INFO, "Check Quick Search Dropdown displayed");
		return QuickSearchDropdown.isDisplayed();
	}

	// Download Company action menu is displayed on Company contact page.
	public boolean isDownloadCompanyActionMenuDisplayed() {
		extentTest.log(Status.INFO, "Download Company action menu is displayed on Company contact page.");
		return CommonUtils.checkElementExist(downloadCompanyActionMenu, driver);
	}

	public String getBreadCrumbText() {
		extentTest.log(Status.INFO, "Get the Breadcrumb Text");
		return breadCrumb.getText().trim();
	}

	public boolean IsPageinationDisplayed() {
		extentTest.log(Status.INFO, "Ensure that the Pagination is displayed");
		return paginationDiv.isDisplayed();
	}
}

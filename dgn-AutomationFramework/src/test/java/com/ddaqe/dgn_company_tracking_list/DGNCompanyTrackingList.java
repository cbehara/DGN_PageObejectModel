package com.ddaqe.dgn_company_tracking_list;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;

@Listeners(TestListener.class)

public class DGNCompanyTrackingList extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanyTrackingList.class);

	@BeforeTest(alwaysRun = true)
	public void setupBeforeTest() {
		log.info("Before Test Started");
	}

	@AfterTest(alwaysRun = true)
	public void tearDownAfterTest() {
		log.info("After Test Ended");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2041(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		companyresultsPage.clickOnFistProjectCheckbox();
		companyresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = companyresultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnCompaniesLink();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertFalse(companyresultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();
		Assert.assertFalse(companyresultsPage.isTrackIconDisplayed());
		Assert.assertTrue(companyresultsPage.isalertOnIconDisplayed());
		companyresultsPage.clickAlertOffLink();
		homePage.clickOnMyAccountLink();
		TrackingList trackList = homePage.clickOnMyTrackingList();
		trackList.clickOnCompaniesLink();
		trackingList.deleteTrackingList();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2042(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		Assert.assertTrue(trackingList.verifyfooterLogoDisplayed());
		Assert.assertEquals(trackingList.getfooterLogoText(),
				"http://www.construction.com/images/logos/DDA_DGN_logo.png");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "Admin", description = "Track link from Summary (TC4144)")
	public void tc2043(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackpopup = companyResultsPage.clickOnTrackCompanies();
		Assert.assertTrue(trackpopup.isNewTrackingListContainerDisplayed());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2044(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		companyresultsPage.clickOnFistProjectCheckbox();
		companyresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = companyresultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnCompaniesLink();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertFalse(companyresultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();
		Assert.assertFalse(companyresultsPage.isTrackIconDisplayed());
		Assert.assertTrue(companyresultsPage.isalertOnIconDisplayed());
		companyresultsPage.clickAlertOffLink();
		homePage.clickOnMyAccountLink();
		TrackingList trackList = homePage.clickOnMyTrackingList();
		trackList.clickOnCompaniesLink();
		trackingList.deleteTrackingList();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "Admin", description = "Track link from Summary (TC4144)")
	public void tc2045(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();

		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackpopup = companyResultsPage.clickOnTrackCompanies();

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		trackpopup.enterNewTrackingListName(trackName);
		trackpopup.clickOnTrackAlertChkBox();
		trackpopup.clickOnSaveBtn();
		Assert.assertTrue(!trackpopup.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "Admin", description = "Track link from Summary (TC4144)")
	public void tc2046(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		String trackName = "Automation" + String.valueOf(new Date().getTime());
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		companyresultsPage.clickOnFistProjectCheckbox();
		companyresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = companyresultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnCompaniesLink();
		trackingList.selectResultPerPageDisplayed("500");
		companyresultsPage = trackingList.clickOnExistingTrackingListCompanies(trackName); 
		companyresultsPage.clickOnFirstCompanyChkBox();
		companyresultsPage.clickOnActionsDropDown();
		CompanyPage companyPage = companyresultsPage.clickOnViewCompaniesUnderActions();
		Assert.assertEquals(companyPage.getTitle(), "Dodge Global Network - Company");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed());
		downloadCompanies.clickOnCancelBtn();
		
		companyPage = new CompanyPage(getDriver());
		EmailAlertsPage emailAlertsPage = companyPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		Assert.assertTrue(emailAlertsPage.isEmailAddressDisplayed());
		emailAlertsPage.clickOnPopUpCancelButton();
		
		companyPage = new CompanyPage(getDriver());
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.mouseOverActionAndClickPrintCompanyDetailsLink();
		Assert.assertTrue(printCompanyDetailsPage.isPrintButtonDisplayed(),"Print Company Details Page is not displayed.");
		printCompanyDetailsPage.clickBackButton();
		
		companyPage = new CompanyPage(getDriver());
		companyPage.clickOnActionsDropDown();
		TrackPopUpPage trackPopUpPage = companyPage.clickOnTrackCompanyActionsLink();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(),"Track popup dialog is not displayed.");
		trackPopUpPage.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanyTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2049(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		char[] specialCharArray = new char[35];// excluding 15 chars length from
		Arrays.fill(specialCharArray, 'A');
		String newTracking50 = new String(specialCharArray);
		newTracking50 = trackPopUpPage.formatValidNewTrackingListName(newTracking50);
		trackPopUpPage.enterNewTrackingListName(newTracking50);
		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTracking50),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertEquals(companyPage.getLengthTrackingListNameMatched(newTracking50), 50,
				"50 Characters String failed to accept in a tracking name");
		Assert.assertTrue(companyPage.isAlertIconDisplayed(), "Alert Icon is failed to be displayed for the Companies");
		homePage.clickOnCompaniesLink();
		homePage.clickOnMySearchesDropDown();
		TrackingList trackingList = homePage.clickOnMyTrackingListSubmenu();
		trackingList.clickOnCompaniesLink();
		trackingList.clickOnExistingTrackngList(newTracking50);
		Assert.assertTrue(companyResultsPage.isalertOnIconDisplayed());
		homePage.clickOnSignOutButton();

	}

}

package com.ddaqe.dgn_company_dashboard;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;

@Listeners(TestListener.class)

public class DGNCompanyDashboard extends DGNCompanyDashboardDataSet {
	static Logger log = Logger.getLogger(DGNCompanyDashboard.class);

	@BeforeTest(alwaysRun = true)
	public void setupBeforeTest() {
		log.info("Before Test Started");
		log.info("Before Test Ended");
	}

	@AfterTest(alwaysRun = true)
	public void tearDownAfterTest() {
		log.info("After Test Started");
		log.info("After Test Ended");
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc1(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = companyPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc2(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnFirstCompanyChkBox();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc3(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnActionsDropdown();
		companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		Assert.assertTrue(companyResultPage.getErrorMessage().equals("Please make a selection"), "");
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc4(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.selectResultsPerPage("10");
		companyResultPage.clickOnSelectAllCompany();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc5(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.isCustomiseSavedSearchesInPopupDisplayed(),
				"Failed to display the Saved Search section in the Customised popup");
		Assert.assertTrue(homePage.isCustomiseTrackingListsInPopupDisplayed(),
				"Failed to display the Tracking Lists section in the Customised popup");
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed(),
				"Failed to display the Save button in the Customised popup");
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed(),
				"Failed to display the Cancel button in the Customised popup");

		homePage.uncheckFromCustomiseDashboardTrackingLists();
		List<String> trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(20);
		homePage.clickOnSaveFromCustomizeDashboard();
		
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardTrackingLists();
		trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(2);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(1);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc6(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.isCustomiseSavedSearchesInPopupDisplayed(),
				"Failed to display the Saved Search section in the Customised popup");
		Assert.assertTrue(homePage.isCustomiseTrackingListsInPopupDisplayed(),
				"Failed to display the Tracking Lists section in the Customised popup");
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed(),
				"Failed to display the Save button in the Customised popup");
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed(),
				"Failed to display the Cancel button in the Customised popup");
		
		
		homePage.uncheckFromCustomiseDashboardTrackingLists();
		List<String> trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(20);
		homePage.clickOnSaveFromCustomizeDashboard();
		
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardTrackingLists();
		trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(2);
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(1);

		homePage.clickOnCancelFromCustomizeDashboard();
		Assert.assertTrue(!homePage.IsDashboardProjectsTrackingListMatch(trackingLists),
				"Should not be able to add tracking list name(s) in the dashboard");

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc7(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.isCustomiseSavedSearchesInPopupDisplayed(),
				"Failed to display the Saved Search section in the Customised popup");
		Assert.assertTrue(homePage.isCustomiseTrackingListsInPopupDisplayed(),
				"Failed to display the Tracking Lists section in the Customised popup");
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed(),
				"Failed to display the Save button in the Customised popup");
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed(),
				"Failed to display the Cancel button in the Customised popup");
		homePage.uncheckFromCustomiseDashboardTrackingLists();
		List<String> trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(20);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(5);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc8(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		Assert.assertTrue(homePage.islblCompanySummaryDisplayed(), "Failed to display the Company summary header");
		Assert.assertTrue(homePage.islblTodaySummaryDisplayed(), "Failed to display the Today summary header");
		Assert.assertTrue(homePage.islblLastVisitSummaryDisplayed(), "Failed to display the Last Visit summary header");
		Assert.assertTrue(homePage.islblAllCompaniesDisplayed(), "Failed to display 'All Companies'");
		Assert.assertTrue(homePage.isCompanySummaryTodayCountDisplayed(),
				"Failed to display the count under 'Today' section in company summary");
		Assert.assertFalse(!homePage.isCompanySummaryTodayCountClickable(),
				"The All Companies Today count should not be hyperlinked");
		Assert.assertFalse(!homePage.isCompanySummaryLastVisitCountClickable(),
				"The All Companies Last Visit count should not be hyperlinked");

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc9(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(!homePage.isCompanySavedSearchNameDisplayed(),
				"The Company saved search should not have been displayed");
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNCompanyDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc10(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(), "Failed to display the customized link");
		homePage.clickOnDashboardCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(1);
		List<String> savedSearchList = homePage.getCustomiseDashboardSavedSearchList();
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.IsDashboardCompaniesSavedSearchMatch(savedSearchList),
				"Failed to match the selected saved search from the customize list in the dashboard");
		homePage.clickOnSignOutButton();
	}
}

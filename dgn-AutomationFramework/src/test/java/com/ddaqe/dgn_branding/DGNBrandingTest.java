package com.ddaqe.dgn_branding;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.AdminReportsPage;
import com.ddaqe.pages.AdminUsersPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LicensePreferencePage;
import com.ddaqe.pages.ManageProfilesPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.MyPurchasesPage;
import com.ddaqe.pages.MyRegistrationInfoPage;
import com.ddaqe.pages.MyShippingAddressPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)
public class DGNBrandingTest extends BaseTest {

	static Logger log = Logger.getLogger(DGNBrandingTest.class);

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

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "DGNT3230", description = "Verify the Intergrate ForeSee survey (TC17795)")
	public void tcDGNT3230(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.performSearch("wooden doors");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Plans Actions menu");
		Assert.assertTrue(projectPlansPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Plans Actions menu");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Specs Actions menu");
		Assert.assertTrue(projectSpecsPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Specs Actions menu");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Addenda Actions menu");
		Assert.assertTrue(projectAddendaPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Addenda Actions menu");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "LicenseEntry", description = "Verify Update branding in license activation page in DGN (TC18057)")
	public void tcDGNT3233(String emailAddress, String password, String stringNotPresent, String stringPresent) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);

		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(),
				" Dodge data and analytic logo is not displayed on the license branding page.");

		Assert.assertFalse(homePage.checkForStringOnPage(stringNotPresent),
				"Mcgraw-hill text is still present on the page.");
		Assert.assertTrue(homePage.checkDodgeWelcomeMessage(stringPresent),
				"Dodge Data & Analytics text is not present on the page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "TermAndConditionPage", description = "Verify Update branding in T&C page (TC18058)")
	public void tcDGNT3234(String emailAddress, String password, String stringNotPresent) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);

		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(),
				" Dodge data and analytic logo is not displayed on the license branding page.");

		Assert.assertFalse(homePage.checkForStringOnPage(stringNotPresent),
				"Mcgraw-hill text is still present on the page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "LicenseEntry", description = "Verify Update branding in license activation page in DGN (TC18057)")
	public void tcDGNT3235(String emailAddress, String password, String stringNotPresent, String stringPresent) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);

		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(),
				" Dodge data and analytic logo is not displayed on the license branding page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "AccessPending", description = "Verify  removal of  McGraw Hill from the 'your access is pending' page (TC19833)")
	public void tcDGNT3238(String emailAddress, String password, String accessPendingMessage) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		Assert.assertEquals(homePage.getAccessPendingMessage(), accessPendingMessage);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Download page (TC23699)")
	public void tcDGNT3241(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Purchases  page (TC23700)")
	public void tcDGNT3242(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();
		Assert.assertTrue(myPurchasesPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Shipping Addresses (TC23701)")
	public void tcDGNT3243(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyShippingAddressPage myShippingAddressPage = myAccount.clickOnMyShippingAddress();
		Assert.assertTrue(myShippingAddressPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Registration Info (TC23708)")
	public void tcDGNT3244(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyRegistrationInfoPage myRegistrationInfoPage = myAccount.clickOnMyRegistration();
		Assert.assertTrue(myRegistrationInfoPage.checkPageBackgroud());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Preferences (TC23709)")
	public void tcDGNT3245(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from License Preferences (TC23710)")
	public void tcDGNT3246(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		LicensePreferencePage licensePreferencePage = myAccount.clickOnLicensePreference();
		Assert.assertTrue(licensePreferencePage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from Users (TC23718)")
	public void tcDGNT3247(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		AdminUsersPage adminUsersPage = myAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from Profiles (TC23719)")
	public void tcDGNT3248(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from Reports (TC23720)")
	public void tcDGNT3249(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		AdminReportsPage adminReportsPage = myAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from Spec Alert List Page (TC23721)")
	public void tcDGNT3250(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		SpecAlertsResultsPage specAlertsResultsPage = myAccount.clickOnMySpecAlertsNavLink();
		Assert.assertTrue(specAlertsResultsPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from Tracking List Page (TC23722)")
	public void tcDGNT3251(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		Assert.assertTrue(trackingLists.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the  graph-paper grid background is removed from My Saved Searches Page (TC23723)")
	public void tcDGNT3252(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();
		Assert.assertTrue(savedSearchesPage.checkPageBackgroud());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNBrandingDataProvider.class, dataProvider = "PlatAdmin", description = "Verify the 'MARKETING' tab is renamed to 'SWEETS ACTIVITY' on DGN (TC24117)")
	public void tcDGNT3253(String emailAddress, String password) {
		HomePage homePage = loginAsWithOutHomeMenu(emailAddress, password);
		Assert.assertTrue(homePage.isSweetsActivityTabDisplayed());
		homePage.clickOnSignOutButton();
	}
}

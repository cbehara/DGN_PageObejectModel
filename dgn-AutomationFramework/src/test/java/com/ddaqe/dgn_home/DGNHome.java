package com.ddaqe.dgn_home;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.PrintProjectListPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.RegisterForUnderstandingTheHomeTabDashboardPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)
public class DGNHome extends DGNHomePageDataSet {

	static Logger log = Logger.getLogger(DGNHome.class);

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

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC519", description = "Welcome message to top of screen (TC519)")
	public void tc831(String emailAddress, String password, String username) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		String welcomeMessage = homePage.welcomeMessage();
		String testString = "Welcome " + username;
		Assert.assertTrue(welcomeMessage.equalsIgnoreCase(testString));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Customize dashboard-scenario1 (TC1648)")
	public void tc832(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.isSaveSearchFirstCheckboxDisplayed());
		Assert.assertTrue(homePage.isTrackingListFirstCheckboxDisplayed());
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed());
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed());
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Home page What's New with Sections announcement (TC25195)")
	public void tc881(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Asserting for presence of  What's New section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Home page What's New with Sections announcement (TC25195)")
	public void tc880(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertEquals(homePage.HelpLinkUrl(),
				"http://www.construction.com/help/dodgenetwork");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Redesigned home-scenario1 (TC1653)")
	public void tc836(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isUSADodgeProjectSummaryDisplayed(),
				"Asserting for presence of  USA dodge project summary section");
		Assert.assertTrue(homePage.isIntlDodgeProjectSummaryDisplayed(),
				"Asserting for presence of Intl Dodge Project summary section");
		Assert.assertTrue(homePage.isMyProjectSavedSearchesDisplayed(),
				"Asserting for presence of  My Project saved searches section");
		Assert.assertTrue(homePage.isMyProjectTrackingListsDisplayed(),
				"Asserting for presence of  My tracking list section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Update logos with new branding/style guidelines - Scenario 4 (TC2776)")
	public void tc838(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectDetailsPage printProjectDetailsPage = projectResultsPage
				.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(printProjectDetailsPage.isprintDodgeLogoDisplayed(),
				"Asserting for Dodge logo");
		printProjectDetailsPage.clickOnBackButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Update logos with new branding/style guidelines - Scenario 4 (TC2776)")
	public void tc839(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage
				.clickOnPrintProjectListUnderActions();
		Assert.assertTrue(printProjectListPage.isprintDodgeLogoDisplayed(),
				"Asserting for Dodge logo");
		printProjectListPage.clickOnBackButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Update logos with new branding/style guidelines - Scenario 1 (TC2773)")
	public void tc837(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(),
				"Asserting for Dodge logo in header");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "regionalLic", description = "Dashboard Page - Verify the default Home page (TC3238)")
	public void tc840(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isDodgeProjectSummaryDisplayed(),
				"Asserting for presence of  dodge project summary section for regional license");
		Assert.assertTrue(homePage.isMyProjectSavedSearchesDisplayed(),
				"Asserting for presence of  My Project saved searches section");
		Assert.assertTrue(homePage.isMyProjectTrackingListsDisplayed(),
				"Asserting for presence of  My tracking list section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "[UI] Update the UI style of the customize dashboard dialog (TC3911)")
	public void tc841(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.isSaveSearchFirstCheckboxDisplayed());
		Assert.assertTrue(homePage.isTrackingListFirstCheckboxDisplayed());
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed());
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed());
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC843", description = "BI Integration - Add an analytics section on the home page - To Verify after clicking on the Analitytics Thumbnail  for a User who has license in Dodge report and Analytics (TC6230)")
	public void tc843(String emailAddress, String password, String url) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isdodgeBusinessIntelligenceDisplayed(),
				"Asserting for presence of  Dodge Business Intelligence section");
		String url1 = homePage.dodgeBusinessIntelligenceLinkUrl();
		Assert.assertEquals(url1, url);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC844", description = "General UI Cleanup -Verify lead email settings dialog's Tip (TC11110)")
	public void tc844(String emailAddress, String password, String tooltip) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnLeadEmailLink();
		String leadEmailDialogTipMess = homePage.leadEmailDialogTip();
		Assert.assertEquals(leadEmailDialogTipMess, tooltip);
		homePage.clickOnCancelInLeadEmailDialog();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC849", description = "Company dashboard the title bar should be 'Customize Company Dashboard' (TC12820)")
	public void tc849(String emailAddress, String password, String dialogTitle) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesTab();
		homePage.clickOnCustomizeDashboard();
		String customizeCompanyDashboardTitle = homePage
				.getcustomizeCompanyDashboardTitle();
		Assert.assertEquals(customizeCompanyDashboardTitle, dialogTitle);
		homePage.clickOnCancelFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC856", description = "[DGN][INC0150001] Unexpected system error while login to DGN for a particular credential (TC21276)")
	public void tc856(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(),
				"Checking for Homepage Dodge Header");
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Checking for What's New section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Verification of presence of customer care phone number in the footer of every page. (TC13320)")
	public void tc854(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isfooterPhoneNumberDisplayed(),
				"Checking for Phone Number in page footer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "To verify that on the user is able to view the WHAT'S NEW section on the home page (TC13287)")
	public void tc851(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Checking for What's New section visibility");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "[UX/UI Dashboard] Changes to objects placements and layout (TC21963)")
	public void tc857(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Checking for What's New section visibility");
		Assert.assertTrue(homePage.isMyResourcesSectionDisplayed(),
				"Checking for My resoucres section visibility");
		Assert.assertTrue(homePage.isdodgeBusinessIntelligenceDisplayed(),
				"Checking for Dodge Business Intelligence section visibility");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "[UX/UI Dashboard] Changes to objects placements and layout (TC21967)")
	public void tc858(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnCompaniesTab();
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed());
		homePage.clickOnProjectsTab();
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed());
		Assert.assertTrue(homePage.isDodgeProjectSummaryDisplayed());
		Assert.assertTrue(homePage.isCommonSpecAlertsTabDisplayed());
		Assert.assertTrue(homePage.isCommonProjectsTabDisplayed());
		Assert.assertTrue(homePage.isCommonCompaniesTabDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "[UX/UI Dashboard] Changes to objects placements and layout (TC21968)")
	public void tc859(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForProjects(searchWord);
		Assert.assertTrue(
				projectResultsPage.isProjectListTogglebuttonDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(projectResultsPage.IsPublishRangeFilter_Displayed(),
				"Checking for UI elements on the page");
		projectResultsPage.clickOnHomeTab();
		CompanyResultsPage companyResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForCompanies(searchWord);
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(companyResultsPage.isResultsDropdownDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed(),
				"Checking for UI elements on the page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Verify the keyword box is present in the body of the dashboard page. (TC22045)")
	public void tc864(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed(),
				"Checking for Google like search text box");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Verify the Project/Company drop down selection is present in the dashboard page (TC22046)")
	public void tc865(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isProjectsInDropdownMenuDisplayed(),
				"Asserting for Projects option in drop down menu");
		homePage.clickOnProjectsCompaniesDropdown();
		Assert.assertTrue(homePage.isCompaniesInDropdownMenuDisplayed(),
				"Asserting for Companies option in drop down menu");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "Verify the keyword appears in the keyword search text box as well as the left nav keyword search box. (TC22047)")
	public void tc866(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.enterSearchText(searchWord);
		ProjectResultsPage projectResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForProjects(searchWord);
		String keyfiltercrumb = projectResultsPage.getcrumbFirstFiltertxt();
		Assert.assertEquals(keyfiltercrumb, "Keyword :" + searchWord,
				"Checking the presence of Keyword in filter crumb");
		projectResultsPage.clickOnHomeTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "Verify the keyword appears in the keyword search text box as well as the left nav keyword search box for Company. (TC22048)")
	public void tc867(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForCompanies(searchWord);
		String keyfiltercrumb = companyResultsPage.getcrumbFirstFiltertxt();
		Assert.assertEquals(keyfiltercrumb, "Keyword :" + searchWord,
				"Checking the presence of Keyword in filter crumb");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "Verify Filters selected should not appear below the quick search keyword search box. (TC22053)")
	public void tc868(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForCompanies(searchWord);
		String keyfiltercrumb = companyResultsPage.getcrumbFirstFiltertxt();
		Assert.assertEquals(keyfiltercrumb, "Keyword :" + searchWord,
				"Checking the presence of Keyword in filter crumb");
		int size = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(size, 1,
				"Asserting that no other filter is applied to the search results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "[UX][Project Search] Quick search to be placed above Search Grid (TC22180)")
	public void tc870(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed());
		CompanyResultsPage companyResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForCompanies(searchWord);
		String testEntered = companyResultsPage.getTextEnteredInTheSearchBox();
		Assert.assertEquals(testEntered, searchWord);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "[HOME DASHBOARD] (TC22262)")
	public void tc872(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isProjectsInDropdownMenuDisplayed(),
				"Asserting for Projects option in drop down menu");
		homePage.clickOnProjectsCompaniesDropdown();
		Assert.assertTrue(homePage.isCompaniesInDropdownMenuDisplayed(),
				"Asserting for Companies option in drop down menu");
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Checking for What's New section visibility");
		Assert.assertTrue(homePage.isMyResourcesSectionDisplayed(),
				"Checking for My resoucres section visibility");
		Assert.assertTrue(homePage.isdodgeBusinessIntelligenceDisplayed(),
				"Checking for Dodge Business Intelligence section visibility");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC873", description = "Verify the Spec alert tab based on license in dashboard (TC22408)")
	public void tc873(String emailAddress, String password,
			String emailAddress1, String password1) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isSpecAlertsTabDisplayed(),
				"Asserting for SpecAlert Tab");
		homePage.clickOnSignOutButton();
		HomePage homePage1 = loginAs(emailAddress1, password1);
		Assert.assertFalse(homePage1.isSpecAlertsTabDisplayed(),
				"Asserting that SpecAlert Tab is not present");
		homePage1.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC873", description = "Verify the Spec alert tab based on license in dashboard (TC22408)")
	public void tc874(String emailAddress, String password,
			String emailAddress1, String password1) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isMySearchesSpecAlertsDisplayed(),
				"Asserting for SpecAlert option in the dropdown");
		homePage.clickOnSignOutButton();
		HomePage homePage1 = loginAs(emailAddress1, password1);
		ProjectResultsPage projectResultsPage1 = homePage1
				.clickOnProjectsLink();
		Assert.assertFalse(
				projectResultsPage1.isMySearchesSpecAlertsDisplayed(),
				"Asserting that SpecAlert option is not present in the dropdown");
		homePage1.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "[UX/FOOTER] Add a configurable text to the footer for browser information (TC22653)")
	public void tc876(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(
				homePage.isfooterAppVersionAndBestViewedInDisplayed(),
				"Checking for visiblity of version number and Best Viewed Browser details in footer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Customize dashboard-scenario2 (TC1649)")
	public void tc833(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardSavedSearch(10);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardSavedSearch(12);
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.iscustomizeDialogErrorMessageDisplayed());
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Customize dashboard-scenario3 (TC1650)")
	public void tc834(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardTrackingLists(20);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardTrackingLists(21);
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.iscustomizeDialogErrorMessageDisplayed());
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC848", description = "Project dashboard the title bar should be 'Customize Project Dashboard' (TC12819)")
	public void tc848(String emailAddress, String password, String dialogTitle) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsTab();
		homePage.clickOnCustomizeDashboard();
		String customizeCompanyDashboardTitle = homePage
				.getcustomizeCompanyDashboardTitle();
		Assert.assertEquals(customizeCompanyDashboardTitle, dialogTitle);
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Verify the Saved Search Control (TC12354)")
	public void tc846(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardSavedSearch(10);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardSavedSearch(12);
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.iscustomizeDialogErrorMessageDisplayed());
		homePage.clickOnCancelFromCustomizeDashboard();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Customize dashboard-scenario5 (TC1652)")
	public void tc835(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardTrackingLists(20);
		homePage.selectFromCustomiseDashboardSavedSearch(10);
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.isMyProjectTrackingListsDisplayed());
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC878", description = "Verify the url on clicking the links under Whats New section on Home Page (TC24755)")
	public void tc878(String emailAddress, String password, String hyperLinkText) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		List<String> allHyperLinksConstructionSiteOnWhatsNewSection = homePage
				.getHyperlinksOfConstructionSiteOnWhatsNewSection();
		Assert.assertTrue(
				allHyperLinksConstructionSiteOnWhatsNewSection != null
						&& !allHyperLinksConstructionSiteOnWhatsNewSection
								.isEmpty(),
				"No links found for Construction site on the Whats New section of the Home page.");
		for (String hyperLinkConstructionSite : allHyperLinksConstructionSiteOnWhatsNewSection) {
			Assert.assertTrue(
					hyperLinkConstructionSite.contains(hyperLinkText), "\""
							+ hyperLinkText
							+ "\" is not added for the construction.com url: "
							+ hyperLinkConstructionSite
							+ " on the Whats New section.");
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC878", description = "Verify the url on clicking the links under My Resources section on Home Page (TC24756)")
	public void tc879(String emailAddress, String password, String hyperLinkText) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(
				homePage.isHyperlinksOfConstructionSiteOnMyResourcesSectionDisplayed(),
				"No links found for Construction site on the My Resources section of the Home page.");
		Assert.assertTrue(
				homePage.verifyHyperlinksOfConstructionSiteOnMyResourcesSection(),
				" hyperLinkText is not added for the construction.com url with www on the My Resources link section.");

		Assert.assertTrue(
				homePage.getHyperlinksOfDownloadTakeOffLinkOnMyResourcesSection()
						.contains(hyperLinkText),
				"\""
						+ hyperLinkText
						+ "\" is not added for the construction.com url: "
						+ homePage
								.getHyperlinksOfDownloadTakeOffLinkOnMyResourcesSection()
						+ " on the My Resources section.");
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC845", description = "Verify the Modal Customize Company Dashboard Dialog  (TC12353)")
	public void tc845(String emailAddress, String password,
			String customizeCompanyDashboardTitle) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesTab();
		Assert.assertTrue(
				homePage.isCustomizeLinkInDashboardDisplayed(),
				"Customize Company Dashboard link is not displayed after clicking on the Companies tab on Dashboard section.");
		homePage.clickOnCustomizeDashboard();
		Assert.assertEquals(homePage.getCustomizeCompanyPopUpHeader(),
				customizeCompanyDashboardTitle,
				"Expected header of the Customize Company pop-up: "
						+ customizeCompanyDashboardTitle + " but Actual:"
						+ homePage.getCustomizeCompanyPopUpHeader());
		Assert.assertTrue(
				homePage.getNumberOfSavedSearchCheckboxesOnCustomizeCompanyDashboard() > 0,
				"No checkboxes found of Saved Searches in the Customize Company Dashboard pop-up.");
		Assert.assertTrue(
				homePage.getNumberOfTrackingListCheckboxesOnCustomizeCompanyDashboard() > 0,
				"No checkboxes found of Saved Searches in the Customize Company Dashboard pop-up.");
		Assert.assertTrue(homePage.isOnSaveFromCustomizeDashboardDisplayed(),
				"Save button not displayed on the Customize Company Dashboard pop-up.");
		Assert.assertTrue(homePage.isOnCancelFromCustomizeDashboardDisplayed(),
				"Cancel button not displayed on the Customize Company Dashboard pop-up.");

		Assert.assertTrue(
				homePage.getNumberOfCheckedSavedSearchCheckboxesOnCustomizeCompanyDashboard() == 0,
				"Checkbox(s) are checked by default in the Saved Searches section Customize Company Dashboard pop-up.");
		Assert.assertTrue(
				homePage.getNumberOfCheckedTrackingListCheckboxesOnCustomizeCompanyDashboard() == 0,
				"Checkbox(s) are checked by default in the Tracking Lists section Customize Company Dashboard pop-up.");

		homePage.clickOnCancelFromCustomizeDashboard();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC850", description = "customizing SpecAlerts dashboard the title bar should be 'Customize SpecAlerts Dashboard' (TC12821)")
	public void tc850(String emailAddress, String password,
			String customizeSpecAlertsDashboardTitle) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		Assert.assertTrue(
				homePage.isCustomizeLinkInDashboardDisplayed(),
				"Customize Spec Alert Dashboard link is not displayed after clicking on the SpecAlerts tab on Dashboard section.");

		homePage.clickOnCustomizeDashboard();
		Assert.assertEquals(homePage.getCustomizeSpecPopUpHeader(),
				customizeSpecAlertsDashboardTitle,
				"Expected header of the Customize Spec Alert pop-up: "
						+ customizeSpecAlertsDashboardTitle + " but Actual:"
						+ homePage.getCustomizeSpecPopUpHeader());
		homePage.clickOnCancelInCustomiseSpecProgramsPopUp();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC852", description = "To verify the links displayed under Training Videos and Attend Live Online Training in the WHAT'S NEW section on the Home page. (TC13289)")
	public void tc852(String emailAddress, String password,
			String trainingVideoLinks, String attendLiveTrainingLinks) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);

		String[] trainingVideoLinksSplit = trainingVideoLinks.split(";");
		if (null != trainingVideoLinksSplit
				&& trainingVideoLinksSplit.length != 0) {
			List<String> allTrainingVideoLinks = homePage
					.getAllTrainingVideosLink();
			Assert.assertTrue(!allTrainingVideoLinks.isEmpty(),
					"No links found in the Training Videos section.");
			for (String trainingVideoLink : trainingVideoLinksSplit) {
				Assert.assertTrue(
						allTrainingVideoLinks.contains(trainingVideoLink),
						"Link: "
								+ trainingVideoLink
								+ " is not present in the Training Videos section.");
			}
		}

		String[] attendLiveTrainingLinksSplit = attendLiveTrainingLinks
				.split(";");
		if (null != attendLiveTrainingLinksSplit
				&& attendLiveTrainingLinksSplit.length != 0) {
			List<String> allAttendLiveTrainingLinks = homePage
					.getAllAttendLiveTrainingLink();
			Assert.assertTrue(!allAttendLiveTrainingLinks.isEmpty(),
					"No links found in the Attend Live Training section.");

			for (String attendLiveTrainingLink : attendLiveTrainingLinksSplit) {
				Assert.assertTrue(
						allAttendLiveTrainingLinks
								.contains(attendLiveTrainingLink),
						"Link: "
								+ attendLiveTrainingLink
								+ " is not present in the Attend Live Training section.");
			}
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Verify application can be opened in more than one tab in the same browser (TC21981)")
	public void tc860(String emailAddress, String password) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		String currentWindowHandle = homePage.getWindowHandle();
		HomePage homePageNewTab = homePage.openHomePageInNewTab();

		homePage.switchTabs(currentWindowHandle);
		Assert.assertTrue(homePageNewTab.isWhatsNewDisplayed(),
				"Home page (New Tab) - Whats New section is not displayed.");
		CompanyResultsPage companyResultsPage = homePageNewTab
				.clickOnCompaniesLink();
		Assert.assertTrue(
				companyResultsPage.isSelectAllcheckboxDisplayed(),
				"Select All checkbox is not displayed, Companies Page (After navigating to New Tab) may not be active.");

		String currentWindowHandleCompanyTab = companyResultsPage
				.getWindowHandle();
		companyResultsPage.switchTabs(currentWindowHandleCompanyTab);
		Assert.assertTrue(homePage.isWhatsNewDisplayed(),
				"Home page - Whats New section is not displayed.");
		homePageNewTab.clickOnDashboardProjectsTab();
		Assert.assertTrue(
				homePage.isCustomizeLinkInDashboardDisplayed(),
				"Customize Projects Dashboard link is not displayed, Home Page may not be active.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC869", description = "Verify the color code in the dashboard page. (TC22120)")
	public void tc869(String emailAddress, String password,
			String searchTextfieldColorCode, String homeSearchDropDownColorCode) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed(),
				"Search Textfield is not displayed on the Home page.");

		Assert.assertEquals(homePage.getSearchTextFieldColor().trim(),
				searchTextfieldColorCode, "Actual search textfield color: "
						+ homePage.getSearchTextFieldColor().trim()
						+ " but Expected is: " + searchTextfieldColorCode);
		Assert.assertEquals(homePage.getHomeSearchDropDownColor().trim(),
				homeSearchDropDownColorCode, "Actual search textfield color: "
						+ homePage.getHomeSearchDropDownColor().trim()
						+ " but Expected is: " + homeSearchDropDownColorCode);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC871", description = "My Searches dropdown should appear beside Quick Search Box (TC22181)")
	public void tc871(String emailAddress, String password,
			String mySearchesDropDownValues) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Point keywordSearchContainerPoint = projectResultsPage
				.getPositionOfKeywordSearchTextfield();
		Point mySearchesDropDownContainerPoint = projectResultsPage
				.getPositionOfMySearchesTextfield();

		Assert.assertTrue(
				(keywordSearchContainerPoint.getY() == mySearchesDropDownContainerPoint
						.getY())
						&& (keywordSearchContainerPoint.getX() > mySearchesDropDownContainerPoint
								.getX()),
				"My Searches dropdown is not on the left side of the Keyword Search textfield Or they are not on the same level.");

		String[] mySearchesDropDownValuesArr = mySearchesDropDownValues
				.split(";");
		if (null != mySearchesDropDownValuesArr
				&& mySearchesDropDownValuesArr.length != 0) {
			projectResultsPage.clickOnMySearchesDropDown();
			List<String> mySearchesDropDownListValues = projectResultsPage
					.getMySearchesDropDownValues();
			Assert.assertTrue(null != mySearchesDropDownListValues
					&& !mySearchesDropDownListValues.isEmpty(),
					"No values found in the My Searches down down.");
			for (String mySearchesDropDownValueArr : mySearchesDropDownValuesArr) {
				Assert.assertTrue(mySearchesDropDownListValues
						.contains(mySearchesDropDownValueArr),
						"My Searches drop down does not contain the value: "
								+ mySearchesDropDownValueArr);
			}
			projectResultsPage.clickOnMySearchesDropDown();
		}
		Assert.assertTrue(projectResultsPage.isMySearchesSpecAlertsDisplayed(),
				"Spec Alerts on My Searches drop down is not displayed.");
		projectResultsPage.clickOnMySearchesSpecAlerts();

		Assert.assertTrue(
				getDriver().getCurrentUrl().contains("SpecAlertResults.aspx"),
				"After clicking on the SpecAlerts value on the My Searches drop-down user is not navigated to the SpecAlertResults.aspx page.");
		getDriver().navigate().back();

		projectResultsPage.clickOnMySearchesDropDown();
		Assert.assertTrue(
				projectResultsPage.isMySearchesTrackingListsDisplayed(),
				"Tracking Lists on My Searches drop down is not displayed.");
		projectResultsPage.clickOnMySearchesTrackingList();
		Assert.assertTrue(
				getDriver().getCurrentUrl().contains("TrackingLists.aspx"),
				"After clicking on the Tracking Lists value on the My Searches drop-down user is not navigated to the TrackingLists.aspx page.");
		getDriver().navigate().back();

		projectResultsPage.clickOnMySearchesDropDown();
		Assert.assertTrue(projectResultsPage.isMySavedSearchesDisplayed(),
				"Saved Searches on My Searches drop down is not displayed.");
		projectResultsPage.clickOnMySavedSearches();
		Assert.assertTrue(
				getDriver().getCurrentUrl().contains("SavedSearches.aspx"),
				"After clicking on the Saved Searches value on the My Searches drop-down user is not navigated to the SavedSearches.aspx page.");
		getDriver().navigate().back();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC855", description = "Verifying pressing enter key searching is initiated (TC16604)")
	public void tc855(String emailAddress, String password, String searchWord) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForProjects(searchWord);
		Assert.assertTrue(
				projectResultsPage.isProjectListTogglebuttonDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(projectResultsPage.IsPublishRangeFilter_Displayed(),
				"Checking for UI elements on the page");
		projectResultsPage.clickOnHomeTab();
		CompanyResultsPage companyResultsPage = homePage
				.enterAndSearchTextUsingEnterKeyForCompanies(searchWord);
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(companyResultsPage.isResultsDropdownDisplayed(),
				"Checking for UI elements on the page");
		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed(),
				"Checking for UI elements on the page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "Internationally-licensed users in DGN can able to search and view for International projects")
	public void t3296(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.switchFrameFancyBox();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.deselectUSACheckBoxPopupWindow();
		projectResultsPage.clickOnUpdateButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(
				projectPage.isInternationalBackgroundImageDisplayed(),
				"Checking for international backgorund image is displayed");
		ProjectResultsPage projectResultsPage1 = homePage.clickOnProjectsLink();
		ProjectPage projectPage1 = projectResultsPage1
				.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage1.isNoBackgroundImageDisplayed(),
				"Checking for No backgorund image is displayed");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "International page should not be accessible through URL")
	public void t3294(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.navigatetoInternationalTab();
		Assert.assertTrue(homePage.isInternationalPageNotFoundDisplayed(),
				"Checking for page not found error message.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "common", description = "International tab is removed from DGN")
	public void t3293(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		projectResultsPage.clickOnHomeTab();
		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		homePage.clickOnHomeTab();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		myAccount.clickOnMyDownloads();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		trackingLists.clickOnFirstTrackingList();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		homePage.clickOnHomeTab();
		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount
				.clickOnSavedSearchMenuLinkMyAccount();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertFalse(homePage.isInternationalTabDisplayed(),
				"Checking if international tab is present");
		homePage.clickOnHomeTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = HomeDataProvider.class, dataProvider = "TC853", description = "To verify the pages displayed when the Introduction to Dashboards link  under Training Videos in the WHAT'S NEW section on the Home page is clicked. (TC13290)")
	public void tc853(String emailAddress, String password, String firstName,
			String lastName, String email, String company,
			String afterRegistrationURL) {
		testPrerequisite(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		RegisterForUnderstandingTheHomeTabDashboardPage registerForUnderstandingTheHomeTabDashboardPage = homePage
				.clickOnDodgeGlobalNetworkCrashCourseLink();
		List tabs = new ArrayList(getDriver().getWindowHandles());
		// Switch to new window
		getDriver().switchTo().window(tabs.get(1).toString());
		registerForUnderstandingTheHomeTabDashboardPage.register(firstName,
				lastName, email, company);
		System.out.println("****" + getDriver().getCurrentUrl());
		System.out.println("****" + afterRegistrationURL);
		Assert.assertTrue(
				getDriver().getCurrentUrl().contains(afterRegistrationURL),
				afterRegistrationURL + " page is not opened.");
		getDriver().close();
		getDriver().switchTo().window(tabs.get(0).toString());
		homePage.clickOnSignOutButton();
	}

}

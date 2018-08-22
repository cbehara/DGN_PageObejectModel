package com.ddaqe.dgn_project_documents;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_project_report.ProjectReportDataProvider;
import com.ddaqe.dgn_specalerts.SpecAlertsDataProvider;
import com.ddaqe.pages.AdminReportsPage;
import com.ddaqe.pages.HighlightKeywordsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LicensePreferencePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNProjectDocuments extends BaseTest {

	static Logger log = Logger.getLogger(DGNProjectDocuments.class);

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
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Dodge Reports -Plans Tab -Scenario1")
	public void tc1013(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecs();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		Assert.assertTrue(projectPlansPage.checkPlanOrder(), "The Plans does not match with the desired sorted order");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of presence of extranous spaces after licence label in reports page. (TC13188)")
	public void tc1018(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		homePage.clickOnMyAccountLink();
		AdminReportsPage adminPage = homePage.clickOnReportsLink();

		Assert.assertTrue(!adminPage.checkWhiteSpaceInLicenseLabel(),
				"There should not be extraneous white spaces after the 'licence' label");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc1021(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		projectResultsPage.clickOnPlansLink();

		projectResultsPage.clickOnSpecsLink();

		projectResultsPage.clickOnAddendaLink();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1024(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();

		try {
			HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();

			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer");

		} catch (Exception ex) {
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1025(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();

		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstNonMatchedSpecNumber();
		
		projectSpecsPage.waitforLoadingRing();

		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is to be downloaded directly");
		
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1026(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();
		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1027(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstNonMatchedPlanNumber();

		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForNonMatched(),
				"The document is failed to be downloaded directly");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1028(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstNonMatchedPlanNumber();

		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForNonMatched(),
				"The document is failed to be downloaded directly");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1029(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();

		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstNonMatchedSpecNumber();

		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForNonMatched(),
				"The document is failed to be downloaded directly");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1030(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();

		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1031(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstNonMatchedPlanNumber();

		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForNonMatched(),
				"The document is failed to be downloaded directly");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc988(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		List<String> sortDropdownExpectedValues = Arrays.asList("Bid Date - Ascending", "Bid Date - Descending",
				"Publish Date - Ascending", "Publish Date - Descending", "Valuation - Low to High",
				"Valuation - High to Low", "Location - Ascending", "Location - Descending", "Project Title - Ascending",
				"Project Title - Descending", "Action Stage - Ascending", "Action Stage - Descending",
				"Project Type - Ascending", "Project Type - Descending");

		List<String> sortDropdownActualValues = projectResultsPage.VerifySortDropdownValues();
		Assert.assertEquals(sortDropdownActualValues, sortDropdownExpectedValues);

		projectResultsPage.selectSortingOption("Valuation - High to Low");
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), "Valuation - High to Low",
				"Failed to get the selected option correctly");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1033(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		// Precondition: User Level PReference is OFF
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(!highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is should have been loaded via Adobe");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Dodge Reports -Plans Tab -Scenario1")
	public void tc989(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();
		Assert.assertTrue(projectPlansPage.getCurrentURl().contains("ProjectPlans.aspx"),
				"Failed to get the project plans page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc994(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecs();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();
		Assert.assertTrue(projectPlansPage.getCurrentURl().contains("ProjectPlans.aspx"),
				"Failed to get the project plans page");
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.getCurrentURl().contains("ProjectSpecs.aspx"),
				"Failed to get the project specs page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc998(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecs();
		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();
		Assert.assertTrue(projectAddendaPage.getCurrentURl().contains("ProjectAddenda.aspx"),
				"Failed to get the project addenda page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc1000(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(!projectPage.verifyActionDropDownOptionsContainsMoreOnActionStage(),
				"Options under actions dropdown should not contain 'More on Action Stage' option");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc1001(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecs();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.getCurrentURl().contains("ProjectSpecs.aspx"),
				"Failed to get the project specs page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc1016(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithBidders();
		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		Assert.assertTrue(projectBiddersPage.isLowBidderCompanyNameDisplayed(),
				"Failed to display the company name under 'Low Bidders' section");
		Assert.assertTrue(projectBiddersPage.isLowBidderContactNameDisplayed(),
				"Failed to display the contact name under 'Low Bidders' section");
		Assert.assertTrue(projectBiddersPage.isLowBidderAddressDisplayed(),
				"Failed to display the address under 'Low Bidders' section");
		Assert.assertTrue(projectBiddersPage.isLowBidderWebsiteClickable(),
				"Failed to display the website as a link under 'Low Bidders' section");
		Assert.assertTrue(projectBiddersPage.isLowBidderPhoneDisplayed(),
				"Failed to display the phone under 'Low Bidders' section");
		Assert.assertTrue(projectBiddersPage.isLowBidderEmailClickable(),
				"Failed to display the email as a link under 'Low Bidders' section");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1019(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1020(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstNonMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForNonMatched(),
				"The document is failed to be downloaded directly");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1035(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		// Precondition: User Level PReference is OFF
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(!highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is should have been loaded via Adobe");

		// Precondition: User Level PReference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (!myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1036(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (!myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		

		
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1038(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnactionStageCatFacet_5_Ckbox();

		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.clickOnOwnershipFederalCkbox();

		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(!highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Number of count should match in both snippet & 'Number of matches' (TC20659)")
	public void tc1042(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("(NEAR(\"helical\",\"anchor*\"),5,false)");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickHighlightLinkInProject();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		Assert.assertEquals(highLightKeywordsPage.getMatchSnippetCount(), highLightKeywordsPage.getlblMatchCountText());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Number of count should match in both snippet & 'Number of matches' (TC20659)")
	public void tc1043(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("(NEAR(\"helical\",\"anchor*\"),5,false)");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickHighlightLinkInProject();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		Assert.assertEquals(highLightKeywordsPage.getMatchSnippetCount(), highLightKeywordsPage.getlblMatchCountText());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1047(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1048(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR3", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1051(String emailAddress, String password, String projectDRNumber1, String projectDRNumber2,
			String projectDRNumber3) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(projectDRNumber1);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_000000(),
				"Failed to display the spec title - 00 00 00");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the date range selection (TC10366)")
	public void tc1056(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		Boolean isSelected = specAlertsResultsPage.setCreatedBetweenRange(
				specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		Assert.assertTrue(!isSelected,
				"Failed to restrict the user so that it should not be able to choose a start date more than 1 year back from the current date");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the date range selection (TC10366)")
	public void tc1057(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		Boolean isSelected = specAlertsResultsPage.setCreatedBetweenRange(
				specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		Assert.assertTrue(!isSelected,
				"Failed to restrict the user so that it should not be able to choose a start date more than 1 year back from the current date");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the date range selection (TC10366)")
	public void tc1058(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		Boolean isSelected = specAlertsResultsPage.setCreatedBetweenRange(
				specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		Assert.assertTrue(!isSelected,
				"Failed to restrict the user so that it should not be able to choose a start date more than 1 year back from the current date");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc3061(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();
		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc3062(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();
		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Number of count should match in both snippet & 'Number of matches' (TC20659)")
	public void tc3067(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
        myPreferencesPage.checkDocumentFilterCheckBox();
        myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("(NEAR(\"helical\",\"anchor*\"),5,false)");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickHighlightLinkInProject();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();
		Assert.assertTrue(!highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of list all documents by default (TC19606)")
	public void tc1014(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecs();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();
		Assert.assertTrue(projectPlansPage.getCurrentURl().contains("ProjectPlans.aspx"),
				"Failed to get the project plans page");
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.getCurrentURl().contains("ProjectSpecs.aspx"),
				"Failed to get the project specs page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1022(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		// Precondition: User Level PReference is OFF
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(!highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is should have been loaded via Adobe");

		// Precondition: User Level PReference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (!myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1023(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		// Precondition: User Level PReference is OFF
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is should have been loaded via Adobe");

		// Precondition: User Level PReference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (!myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		projectResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnFirstMatchedSpecNumber();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of  spec document when keywords are part of search (TC19621)")
	public void tc1032(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition: License Level Preference is OFF
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();
		homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		if (!myPreferencesPage.isDocumentCheckBoxSelected()) {
			myPreferencesPage.clickOnDocumentFilterCheckBox();
			myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		}
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithSpecsMatched();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}

		// Precondition: License Level Preference is ON
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();
		
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlusLicense", description = "Number of count should match in both snippet & 'Number of matches' (TC20659)")
	public void tc1041(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("(NEAR(\"helical\",\"anchor*\"),5,false)");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickHighlightLinkInProject();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();
		Assert.assertEquals(highLightKeywordsPage.getMatchSnippetCount(), highLightKeywordsPage.getlblMatchCountText());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1045(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();

		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectDocumentsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "Verification of spec document when keywords are not part of search (TC19622)")
	public void tc1046(String emailAddress, String password, String searchText) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnFirstProjectTitleWithPlans();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();

		HighlightKeywordsPage highLightKeywordsPage = projectPlansPage.clickOnFirstMatchedPlanNumber();

		if (!highLightKeywordsPage.strNumber.isEmpty()) {
			Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
					"The document is failed to be loaded in the document viewer ");
		}

		homePage.clickOnSignOutButton();
	}
}

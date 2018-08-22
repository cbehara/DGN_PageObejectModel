package com.ddaqe.dgn_project_search;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HighlightKeywordsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectAddendaDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectCommonContainerPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingLists;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNProjectSearch extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2508(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("hospital");
		homePage.clickOnSearchButton();
		projectResultsPage.selectSortingOption("Bid Date - Ascending");
		Assert.assertTrue(projectResultsPage.verifyBidDateSortingAscending());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2509(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("hospital");
		homePage.clickOnSearchButton();
		projectResultsPage.selectSortingOption("Publish Date - Ascending");
		Assert.assertTrue(projectResultsPage.verifyPublishDateSortingAscending());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2510(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("hospital");
		homePage.clickOnSearchButton();
		projectResultsPage.selectSortingOption("Valuation - High to Low");
		Assert.assertTrue(projectResultsPage.verifyValuationSortingAscending());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Reduce the number of pagination choices (TC1452)")
	public void tc2511(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		List<String> resultsDropdownExpectedValues = Arrays.asList("10", "25", "50", "100", "500", "1000", "5000",
				"10000");
		List<String> resultsDropdownActualValues = projectResultsPage.VerifyResultDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Reduce the number of pagination choices (TC1453)")
	public void tc2512(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		List<String> resultsDropdownExpectedValues = Arrays.asList("10", "25", "50", "100", "500", "1000", "5000",
				"10000");
		List<String> resultsDropdownActualValues = projectResultsPage.VerifyResultDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Reduce the number of pagination choices (TC1453)")
	public void tc2513(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		List<String> resultsDropdownExpectedValues = Arrays.asList("10", "25", "50", "100", "500", "1000", "5000",
				"10000");
		List<String> resultsDropdownActualValues = companyResultsPage.VerifyResultDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report (TC1471)")
	public void tc2515(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectCommonContainerPage projectCommonPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertEquals(projectCommonPage.getTitle(), "Dodge Global Network - Project");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report (TC1472)")
	public void tc2516(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report (TC1471)")
	public void tc2517(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectCommonContainerPage projectCommonPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertEquals(projectCommonPage.getTitle(), "Dodge Global Network - Project");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "To verify Redesigned footer (TC1504)")
	public void tc2518(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		Assert.assertEquals(homePage.getHyperlinkTermsOfUse(), "http://www.construction.com/terms-of-use.asp");
		Assert.assertEquals(homePage.getHyperlinkPrivacyPolicy(), "http://www.construction.com/privacy-notice.asp");
		Assert.assertEquals(homePage.getHyperlinkAboutUs(), "http://www.construction.com/about-us/");
		Assert.assertEquals(homePage.getHyperlinkCustomerCare(), "http://www.construction.com/help/dodgenetwork");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Addenda-scenario2 (TC1527)")
	public void tc2519(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertEquals(projectAddendaPage.getTitle(), "Dodge Global Network - Project - Addenda");
		ProjectAddendaDetailsPage addendaDetailsPage = projectAddendaPage.clickOnFirstAddendaInGrid();
		Assert.assertEquals(addendaDetailsPage.getTitle(), "Dodge Global Network - Project - Addenda Details");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Firms-scenario2 (TC1529)")
	public void tc2520(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertEquals(projectFirmsPage.getTitle(), "Dodge Global Network - Project - All Firms");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Firms-scenario2 (TC1529)")
	public void tc2521(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertEquals(projectFirmsPage.getTitle(), "Dodge Global Network - Project - All Firms");
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project > Firms");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Firms-scenario4 (TC1531)")
	public void tc2522(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertEquals(projectFirmsPage.getTitle(), "Dodge Global Network - Project - All Firms");
		Assert.assertTrue(projectFirmsPage.verifyCompanyNameColumnSortingArrowSymbol(true));
		projectFirmsPage.clickCompanyNameColumnHeader();
		Assert.assertTrue(projectFirmsPage.verifyCompanyNameColumnSorting(false));
		Assert.assertTrue(projectFirmsPage.verifyCompanyNameColumnSortingArrowSymbol(false));
		Assert.assertTrue(projectFirmsPage.verifyValuesInFirmTypesDropdown());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Plans-scenario2 (TC1534)")
	public void tc2523(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertEquals(projectPlansPage.getTitle(), "Dodge Global Network - Project - Plans");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Plans-scenario2 (TC1534)")
	public void tc2524(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		projectPage.clickOnPlansTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project > Plans");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Hiding projects from list view-scenario1 (TC1554)")
	public void tc2525(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getCountOfProjectsOnPage(),
				projectResultsPage.getCountOfHideButtonsOnPage());
		String projectTitle = projectResultsPage.getFirstProjectTitle();
		projectResultsPage.clickHideLinkForFirstProject();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectResultsPage = trackingList.clickOnHiddenProjects();
		projectResultsPage.SelectResultDropdownValue("500");

		// Verify if project is found in the hidden projects page
		Assert.assertTrue(projectResultsPage.verifyProjectTitleExistsInList(projectTitle),
				"Failed to find the hidden project in the project results list");
		Assert.assertTrue(projectResultsPage.isUnhideDisplayed(),
				"Failed to display unhide link for the hidden projects");

		projectResultsPage.clickOnUnhideLink();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Unhiding projects from list view-scenario2 (TC1558)")
	public void tc2526(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickHideLinkForFirstProject();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnHiddenProjects();
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckUnhideProjectsDisplayed());
		projectResultsPage.mouseOverActionandClickUnhideProjects();
		Assert.assertTrue(projectResultsPage.checkforValidationMessage("Please make a selection"));
		String projectTitle = projectResultsPage.getFirstProjectTitle();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickUnhideProjects();
		// Verify that project is not found in the hidden projects page
		projectResultsPage.SelectResultDropdownValue("500");
		Assert.assertFalse(projectResultsPage.verifyProjectTitleExistsInList(projectTitle));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Hiding project from report view-scenario1 (TC1559)")
	public void tc2527(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		Assert.assertTrue(projectPage.mouseOverActionandCheckHideProjectsDisplayed());
		projectResultsPage.clickOnPlansLink();
		Assert.assertTrue(projectPage.mouseOverActionandCheckHideProjectsDisplayed());
		projectResultsPage.clickOnAddendaLink();
		Assert.assertTrue(projectPage.mouseOverActionandCheckHideProjectsDisplayed());
		projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectPage.mouseOverActionandCheckHideProjectsDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Unhiding projects from list view-scenario2 (TC1558)")
	public void tc2528(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String projectTitle = projectResultsPage.getFirstProjectTitle();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectPage.mouseOverActionandCheckHideProjectsDisplayed());
		projectPage.mouseOverActionandClickHideProjects();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnHiddenProjects();
		projectResultsPage.SelectResultDropdownValue("500");
		// Verify that project is not found in the hidden projects page
		Assert.assertTrue(projectResultsPage.verifyProjectTitleExistsInList(projectTitle));
		// clear up the test data
		trackingList.clickOnUnhideProject();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2529(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(projectResultsPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnSecondProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectDetailsPage printProjDetailsPage = projectResultsPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertEquals(printProjDetailsPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printProjDetailsPage.isBackButtonDisplayed());
		Assert.assertTrue(printProjDetailsPage.isPrintButtonDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2532(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(projectResultsPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnSecondProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectDetailsPage printProjDetailsPage = projectResultsPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertEquals(printProjDetailsPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printProjDetailsPage.isBackButtonDisplayed());
		Assert.assertTrue(printProjDetailsPage.isPrintButtonDisplayed());
		printProjDetailsPage.clickOnBackButton();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Redesigned Dodge Report - Firms-scenario4 (TC1531)")
	public void tc2536(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertEquals(projectFirmsPage.getTitle(), "Dodge Global Network - Project - All Firms");
		Assert.assertTrue(projectFirmsPage.isBottomPaginationDisplayed());
		Assert.assertTrue(projectFirmsPage.verifyValuesInFirmTypesDropdown());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Unhiding projects from list view-scenario2 (TC1558)")
	public void tc2537(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		String drNumber = projectResultsPage.getFirstDRNumer();
		String firstProjectTitleBeforeHide = projectResultsPage.getFirstProjectTitle();
		projectResultsPage.clickHideLinkForFirstProject();
		String ProjectTitleAfterHide = projectResultsPage.getFirstProjectTitle();
		Assert.assertNotSame(firstProjectTitleBeforeHide, ProjectTitleAfterHide);
		homePage.enterSearchText(drNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		Assert.assertEquals(projectPage.getProjectTitle(), firstProjectTitleBeforeHide);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Links in footer should open in a new browser window (TC2267)")
	public void tc2538(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertEquals(homePage.getHyperlinkTermsOfUse(), "http://www.construction.com/terms-of-use.asp");
		Assert.assertEquals(homePage.getHyperlinkPrivacyPolicy(), "http://www.construction.com/privacy-notice.asp");
		Assert.assertEquals(homePage.getHyperlinkAboutUs(), "http://www.construction.com/about-us/");
		Assert.assertEquals(homePage.getHyperlinkCustomerCare(), "http://www.construction.com/help/dodgenetwork");

		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		Assert.assertEquals(homePage.getHyperlinkTermsOfUse(), "http://www.construction.com/terms-of-use.asp");
		Assert.assertEquals(homePage.getHyperlinkPrivacyPolicy(), "http://www.construction.com/privacy-notice.asp");
		Assert.assertEquals(homePage.getHyperlinkAboutUs(), "http://www.construction.com/about-us/");
		Assert.assertEquals(homePage.getHyperlinkCustomerCare(), "http://www.construction.com/help/dodgenetwork");

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();

		Assert.assertEquals(homePage.getHyperlinkTermsOfUse(), "http://www.construction.com/terms-of-use.asp");
		Assert.assertEquals(homePage.getHyperlinkPrivacyPolicy(), "http://www.construction.com/privacy-notice.asp");
		Assert.assertEquals(homePage.getHyperlinkAboutUs(), "http://www.construction.com/about-us/");
		Assert.assertEquals(homePage.getHyperlinkCustomerCare(), "http://www.construction.com/help/dodgenetwork");

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingListsMenuLink();

		Assert.assertEquals(homePage.getHyperlinkTermsOfUse(), "http://www.construction.com/terms-of-use.asp");
		Assert.assertEquals(homePage.getHyperlinkPrivacyPolicy(), "http://www.construction.com/privacy-notice.asp");
		Assert.assertEquals(homePage.getHyperlinkAboutUs(), "http://www.construction.com/about-us/");
		Assert.assertEquals(homePage.getHyperlinkCustomerCare(), "http://www.construction.com/help/dodgenetwork");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2541(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnProjectsDropdownMenu();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButtonHomePage();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Project Results");
		goToBackPage();
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButtonHomePage();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Company Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2542(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Company Results");
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyContactsPage companyContactsPage = companyPage.clickOnCompanyContactsLink();
		companyContactsPage.clickOnActionsDropDown();
		String actionsDropdownActualValues = companyContactsPage.verifyActionsDropdownValues();
		Assert.assertTrue(actionsDropdownActualValues.contains("Download Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Email Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Add Note"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Track Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Print Company Details"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2543(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Company Results");
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyProjectsPage companyprojectsPage = companyPage.clickOnCompanyProjectsLink();
		companyprojectsPage.clickOnActionsDropdownCompany();
		String actionsDropdownActualValues = companyprojectsPage.getActionsDropdownValues();
		Assert.assertTrue(actionsDropdownActualValues.contains("Download Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Email Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Add Note"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Track Company"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Print Company Details"));
		Assert.assertTrue(actionsDropdownActualValues.contains("View Projects"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Download Projects"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Track Projects"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Print Project Details"));
		Assert.assertTrue(actionsDropdownActualValues.contains("Print Project List"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2544(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project");
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Project Results > Project > Firms > Company");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 2 (TC3092)")
	public void tc2545(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectFirmsPage projectFirmsPage = projectResultsPage.clickOnFirmsLink();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Firms");
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Project Results > Firms > Company");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 3 (TC3093)")
	public void tc2546(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnPageNumber4();
		projectResultsPage.waitforLoadingRing();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project");
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Project Results > Project > Firms > Company");
		companyPage.clickOnProjectResultsreadCrumb();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.IsPageNumber4HighLightedInBold());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 4 (TC3094)")
	public void tc2547(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnPageNumber4();
		projectResultsPage.waitforLoadingRing();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project");
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Project Results > Project > Firms > Company");
		companyPage.clickOnProjectBreadCrumb();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 5 (TC3095)")
	public void tc2548(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnPageNumber4();
		projectResultsPage.waitforLoadingRing();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project");
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Project Results > Project > Firms > Company");
		companyPage.clickOnFirmsBreadCrumb();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project - All Firms");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 1 (TC3112)")
	public void tc2549(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertEquals(companyPage.getBreadCrumbText(), "Company Results > Company");
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		Assert.assertEquals(companyProjectsPage.getBreadCrumbText(), "Company Results > Company > Projects");
		ProjectPage projectPage = companyProjectsPage.clickFirstProjectTitle();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		Assert.assertEquals(projectPage.getBreadCrumbText(), "Company Results > Company > Projects > Project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 2 (TC3113)")
	public void tc2550(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertEquals(companyProjectsPage.getBreadCrumbText(), "Company Results > Projects");
		ProjectPage projectPage = companyProjectsPage.clickFirstProjectTitle();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		Assert.assertEquals(projectPage.getBreadCrumbText(), "Company Results > Projects > Project");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 3 (TC3114)")
	public void tc2551(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("magnolia");
		homePage.clickOnSearchButton();
		companyResultsPage.selectResultsPerPage("25");
		companyResultsPage.clickOnPageNumber4();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleWithProjects();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		ProjectPage projectPage = companyProjectsPage.clickFirstProjectTitle();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		Assert.assertEquals(projectPage.getBreadCrumbText(), "Company Results > Company > Projects > Project");
		projectPage.clickOnCompanyResultsBreadCrumb();
		projectPage.waitforLoadingRing();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Company Results");
		Assert.assertTrue(companyResultsPage.IsPageNumber4HighLightedInBold());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 4 (TC3115)")
	public void tc2552(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("magnolia");
		homePage.clickOnSearchButton();
		companyResultsPage.selectResultsPerPage("25");
		companyResultsPage.clickOnPageNumber4();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleWithProjects();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		ProjectPage projectPage = companyProjectsPage.clickFirstProjectTitle();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		Assert.assertEquals(projectPage.getBreadCrumbText(), "Company Results > Company > Projects > Project");
		projectPage.clickOnCompanyBreadCrumb();
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 4 (TC3115)")
	public void tc2553(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText("200800727590");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		projectPage.clickOnFirmsTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Firms");
		projectPage.clickOnBiddersTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Planholders/Bidders");
		projectPage.clickOnPlansTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Plans");
		projectPage.clickOnSpecsTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Specs");
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Addenda");
		projectAddendaPage.clickOnFirstAddendaInGrid();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project > Addenda > Addenda Details");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2554(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyProjectsPage companyProjectPage = companyPage.clickOnCompanyProjectsLink();
		companyProjectPage.clickOnFistProjectCheckbox();
		companyProjectPage.clickOnSecondProjectCheckbox();
		companyProjectPage.clickOnActionsDropdownCompany();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyProjectPage.clickOnPrintCompanyDetailsUnderActions();
		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailsPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailsPage.isPrintButtonDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2555(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyProjectsPage companyProjectPage = companyResultsPage.clickOnCompanyProjectsLink();
		companyProjectPage.clickOnFistProjectCheckbox();
		companyProjectPage.clickOnSecondProjectCheckbox();
		companyProjectPage.clickOnActionsDropdownCompany();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyProjectPage.clickOnPrintCompanyDetailsUnderActions();
		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailsPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailsPage.isPrintButtonDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 5 (TC3095)")
	public void tc2556(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > Project");
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanycheckbox();
		projectFirmsPage.clickOnSecondCompanycheckbox();
		projectFirmsPage.mouseOverActionandClickViewCompanies();
		companyPage.clickOnNextLink();
		Assert.assertTrue(companyPage.verifyPageDisplayed("2  of 2"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2558(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("xoxoxoxox");
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultsPage.isZeroSearchResultDisplayedforGivenSearch("xoxoxoxox"));
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		Assert.assertTrue(saveSearchPopUp.isSaveSearchPopupDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2559(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Location - Ascending");
		Assert.assertTrue(projectResultsPage.verifyLocationSortingAscending());
		projectResultsPage.selectSortingOption("Location - Descending");
		Assert.assertTrue(projectResultsPage.verifyLocationSortingAscending());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2560(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String resultsDisplayed = projectResultsPage.getResultDropdownSelectedValue();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		String projectCountText = projectPage.getPaginationSectionText();
		String expectedCountText = "1  of  " + resultsDisplayed;
		Assert.assertTrue(projectCountText.toLowerCase().contains(expectedCountText.toLowerCase()));
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed());
		Assert.assertTrue(projectPage.isNextLinkDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Breadcrumb on Dodge Report when clicking through from company projects tab - Scenario 4 (TC3115)")
	public void tc2561(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		projectPage.isPaginationSectionBottonDisplayed();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2562(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String resultsDisplayed = projectResultsPage.getResultDropdownSelectedValue();
		String projectCountText = projectResultsPage.getPaginationSectionText();
		String expectedCountText = "1-" + resultsDisplayed + " of";
		Assert.assertTrue(projectCountText.toLowerCase().contains(expectedCountText.toLowerCase()));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2563(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		String ProjCountBeforeHide = projectresultsPage.getCountOfProjectsDisplayed();
		int ProjectCountBeforeHide = Integer.parseInt(ProjCountBeforeHide);
		int TotalResultsDisplayed = Integer.parseInt(projectresultsPage.getResultDropdownSelectedValue());

		projectresultsPage.clickOnSelectAllProjects();
		projectresultsPage.clickOnActionsDropdown();
		projectresultsPage.clickOnHideProjects();
		String ProjCountAfterHide = projectresultsPage.getCountOfProjectsDisplayed();
		int ProjectCountAfterHide = Integer.parseInt(ProjCountAfterHide);

		int hiddenProjects = ProjectCountBeforeHide - ProjectCountAfterHide;
		Assert.assertEquals(hiddenProjects, TotalResultsDisplayed);

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnHiddenProjects();

		Assert.assertFalse(projectresultsPage.isAlertOnLinkPresent());
		Assert.assertFalse(projectresultsPage.isRemoveLinkPresent());

		// Clear up test data
		projectresultsPage.clickOnSelectAllProjects();
		projectresultsPage.mouseOverActionandClickUnhideProjects();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2565(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.mouseOverActionandClickHideProjects();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnHiddenProjects();
		Assert.assertFalse(projectresultsPage.isAlertOnLinkPresent());
		Assert.assertFalse(projectresultsPage.isRemoveLinkPresent());

		// clearup test data
		projectresultsPage.clickOnSelectAllProjects();
		projectresultsPage.mouseOverActionandClickUnhideProjects();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2566(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String paginationSectionTextBefore = projectResultsPage.getPaginationSectionText();
		homePage.enterSearchText("doo*");
		homePage.clickOnSearchButton();
		String paginationSectionTextAfter = projectResultsPage.getPaginationSectionText();
		Assert.assertNotEquals(paginationSectionTextAfter, paginationSectionTextBefore);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		String AppliedFilter = projectResultsPage.getcrumbFirstFiltertxt();
		Assert.assertTrue(AppliedFilter.contains("Keyword :" + "doo*"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2567(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String paginationSectionTextBefore = projectResultsPage.getPaginationSectionText();
		homePage.enterSearchText("a&");
		homePage.clickOnSearchButton();
		String paginationSectionTextAfter = projectResultsPage.getPaginationSectionText();
		Assert.assertNotEquals(paginationSectionTextAfter, paginationSectionTextBefore);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		String AppliedFilter = projectResultsPage.getcrumbFirstFiltertxt();
		Assert.assertTrue(AppliedFilter.contains("Keyword :" + "a&"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2568(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		String paginationSectionTextBefore = companyResultsPage.getPaginationSectionText();
		homePage.enterSearchText("a&");
		homePage.clickOnSearchButton();
		String paginationSectionTextAfter = companyResultsPage.getPaginationSectionText();
		Assert.assertNotEquals(paginationSectionTextAfter, paginationSectionTextBefore);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		String AppliedFilter = companyResultsPage.getcrumbFirstFiltertxt();
		Assert.assertTrue(AppliedFilter.contains("Keyword :" + "a&"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2569(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		List<String> sortDropdownExpectedValues = Arrays.asList("Bid Date - Ascending", "Bid Date - Descending",
				"Publish Date - Ascending", "Publish Date - Descending", "Valuation - Low to High",
				"Valuation - High to Low", "Location - Ascending", "Location - Descending", "Project Title - Ascending",
				"Project Title - Descending", "Action Stage - Ascending", "Action Stage - Descending",
				"Project Type - Ascending", "Project Type - Descending");

		List<String> sortDropdownActualValues = projectResultsPage.VerifySortDropdownValues();
		Assert.assertEquals(sortDropdownActualValues, sortDropdownExpectedValues);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Allow user to save a search that generates zero results- To Verify that user is able to save the search which yields zero results (TC7256)")
	public void tc2570(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("hospital");
		homePage.clickOnSearchButton();
		projectResultsPage.selectSortingOption("Project Title - Ascending");
		Assert.assertTrue(projectResultsPage.verifyProjectResultsSortingAscending());
		projectResultsPage.selectSortingOption("Project Title - Descending");
		Assert.assertTrue(projectResultsPage.verifyProjectResultsSortingDesccending());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommonNonAdmin", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2572(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommonNonAdmin", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2573(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2574(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		int projectCountBefore = projectResultsPage.getCountOfProjectsOnPage();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		int projectCountAfter = projectResultsPage.getCountOfProjectsOnPage();
		Assert.assertEquals(projectCountAfter, projectCountBefore);
		Assert.assertFalse(projectResultsPage.isProfileFilterVisible());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2575(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isExcludeCheckboxPresent());
		projectResultsPage.clickOnUpdateButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2576(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.isExcludeCheckboxUnChecked());
		projectResultsPage.clickOnUpdateButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2577(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		String filterCrumbTextBefore = projectResultsPage.getNamesOfAllAppliedFilters();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.clickOnUpdateButton();
		String filterCrumbTextAfter = projectResultsPage.getNamesOfAllAppliedFilters();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertEquals(filterCrumbTextBefore, filterCrumbTextAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2579(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingList_PopupOptionList");
		String Expected1 = projectResultsPage.gettrackingList_PopupOption_Second_lbl().trim();
		String Expected2 = projectResultsPage.gettrackingList_PopupOption_first_lbl().trim();
		projectResultsPage.clickOnUpdateButton();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, " ");
		int endIndex2 = CommonUtils.getLastIndexOfCharacter(Expected2, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected2.substring(0, endIndex2)));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2580(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		projectResultsPage.clickExcludeCheckbox_SeeMore();
		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters()
				.contains("Exclude : " + Expected1.substring(0, endIndex1)));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2581(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingList_PopupOptionList");
		String Expected1 = projectResultsPage.gettrackingList_PopupOption_Second_lbl();
		String Expected2 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, " ");
		int endIndex2 = CommonUtils.getLastIndexOfCharacter(Expected2, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected2.substring(0, endIndex2)));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2582(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		String Expected2 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		endIndex1 = Expected2.lastIndexOf(" ");
		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2583(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		projectResultsPage.clickExcludeCheckbox_SeeMore_Specalert();
		String Expected = projectResultsPage.getspecAlertSeeMorePopUpFirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertTrue(projectResultsPage.getspecAlertLHSFirstOption_lbl().contains(Expected));
		Assert.assertTrue(projectResultsPage.IsspecAlertLHSFirstOption_cbk_Selected());
		Assert.assertTrue(projectResultsPage.IsspecAlertLHSExcludeChkbox_Selected());
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.IsspecAlertLHSFirstOption_cbk_Selected());
		Assert.assertTrue(projectResultsPage.IsspecAlertLHSExcludeChkbox_Selected());

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2584(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		projectResultsPage.clickExcludeCheckbox_SeeMore_Specalert();
		String Expected = projectResultsPage.getspecAlertSeeMorePopUpFirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains("Exclude : " + Expected));
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains("Exclude : " + Expected));

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2585(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("trackingListFacetList"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2586(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		List<String> actualTrackingList = projectResultsPage.gettrackingListPopup_all_lbl();
		List<String> expectedTrackingList = CommonUtils.sortWebElements(actualTrackingList, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualTrackingList, expectedTrackingList));
		Assert.assertTrue(projectResultsPage.verifyTrackingListContainsCountInBrackets());
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2587(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.verifyTrackingListContainsIconForPublicShared("TC2587"));
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2589(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		int appliedFilterCountBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.clickOnUpdateButton();
		int appliedFilterCountBeforeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(appliedFilterCountBeforeAfter, appliedFilterCountBefore);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2590(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingList_PopupOptionList");
		String Expected1 = projectResultsPage.gettrackingList_PopupOption_Second_lbl();
		String Expected2 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, " ");
		int endIndex2 = CommonUtils.getLastIndexOfCharacter(Expected2, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected2.substring(0, endIndex2)));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2591(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		String Expected = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex = CommonUtils.getLastIndexOfCharacter(Expected, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected.substring(0, endIndex)));
		Assert.assertEquals(projectResultsPage.mouseOverOncrumbSecondFilterLinkandGetTooltip(), "Tracking List");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2593(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnTrackingListFilter();
		String Expected = projectResultsPage.gettrackingList_LHSFirstOption_lbl();
		int endIndex1 = Expected.lastIndexOf(" ");
		projectResultsPage.SelectOptionsFromTheList(1, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected.substring(0, endIndex1)));

		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected));
		Expected = projectResultsPage.gettrackingList_LHSFirstOption_lbl();
		endIndex1 = Expected.lastIndexOf(" ");
		projectResultsPage.SelectOptionsFromTheList(1, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected.substring(0, endIndex1)));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2594(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommonNonAdmin", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2595(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderComonPlus", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2596(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		int endIndex1 = Expected1.lastIndexOf(" ");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderComonPlus", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2597(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		int endIndex1 = Expected1.lastIndexOf(" ");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2598(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommonNonAdmin", description = "Verification of affect on search criteria when user clicks on update in the select more than one pop up without selecting any tracking list. (TC13070)")
	public void tc2599(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");

		String Expected1 = projectResultsPage.gettrackingList_PopupOption_first_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		int endIndex1 = CommonUtils.getLastIndexOfCharacter(Expected1, "...");
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		projectResultsPage.ClickOncrumbThirdFilterClose();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(
				projectResultsPage.getNamesOfAllAppliedFilters().contains(Expected1.substring(0, endIndex1)));

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderMultipleDRInLicense", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2600(String emailAddress, String password, String MultipleDR, String FirstDRNumberAndVersion,
			String FirstDRTitle, String SecondDRNumberAndVersion, String SecondDRTitle) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(MultipleDR);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		String drGridText = projectResultsPage.getDrGridText();
		Assert.assertTrue(drGridText.contains(FirstDRNumberAndVersion));
		Assert.assertTrue(drGridText.contains(FirstDRTitle));

		Assert.assertTrue(drGridText.contains(SecondDRNumberAndVersion));
		Assert.assertTrue(drGridText.contains(SecondDRTitle));

		Assert.assertTrue(drGridText.contains("View this Dodge Report"));

		projectResultsPage.clickOnHyperLink(FirstDRNumberAndVersion);
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		goToBackPage();

		projectResultsPage.clickOnHyperLink(FirstDRTitle);
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		goToBackPage();

		projectResultsPage.clickOnHyperLink(SecondDRNumberAndVersion);
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		goToBackPage();

		projectResultsPage.clickOnHyperLink(SecondDRTitle);
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		goToBackPage();

		projectResultsPage.clickOnHyperLink("View this Dodge Report");
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		goToBackPage();

		Assert.assertTrue(projectPage.isViewThisDodgeReportLinkPresent());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderMultipleDRInvalid", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2602(String emailAddress, String password, String MultipleDR) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(MultipleDR);
		homePage.clickOnSearchButtonDR();
		Assert.assertTrue(projectResultsPage
				.isMessageDisplayedforGivenSearch("Your search - " + MultipleDR + " - did not match any documents."));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderDR", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2603(String emailAddress, String password, String DR) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.enterSearchText(DR);
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2605(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyProjectsPage companyProjectPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyProjectPage.isMyProjectsTabDisplayed());
		Assert.assertTrue(companyProjectPage.isProjectsNotInMyLicenseTabDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2608(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("questcdn");
		homePage.clickOnSearchButton();
		CompanyProjectsPage companyProjectPage = companyResultsPage.clickOnCompanyProjectsLink();
		if (companyProjectPage.isProjectCountInMyLicenseTabGreaterthanzero()) {
			Assert.assertTrue(companyProjectPage.isMyProjectsTabEnabled());
		}
		Assert.assertFalse(companyProjectPage.isProjectsNotInMyLicenseTabEnabled());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2613(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		String projectCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		String projectCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(projectCountAfter, projectCountBefore);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2617(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		projectPlansPage.IsPlansTabHighlighted();
		Assert.assertTrue(projectPlansPage.IsFirstMatchedPlanNumberHighlightedInYellow());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2618(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		projectPlansPage.IsPlansTabHighlighted();
		Assert.assertTrue(projectPlansPage.IsFirstMatchedPlanNumberHighlightedInYellow());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2627(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnFirstHighlightedSpec();
		String dr = projectSpecsPage.getDRNumber();

		String specNumber = projectSpecsPage.getFirstMatchedSpecNumberText();
		HighlightKeywordsPage highlightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();

		Assert.assertTrue(highlightKeywordsPage.IsLeftHeaderDisplayed());
		Assert.assertEquals(highlightKeywordsPage.getLogoLabelText(), "Document not showing? Click here:");
		Assert.assertEquals(highlightKeywordsPage.getDRText(), "DR#: " + dr + " -  " + specNumber);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2628(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnFirstHighlightedSpec();
		String dr = projectSpecsPage.getDRNumber();

		projectSpecsPage.getFirstMatchedSpecNumberText();
		HighlightKeywordsPage highlightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();
		Assert.assertEquals(highlightKeywordsPage.clickinfoIconandGetURL(),
				"https://www.construction.com/Help/dodgenetwork/Working-w-Documents.asp#viewing");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderDR", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2631(String emailAddress, String password, String DR) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.enterSearchText(DR);
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderDR", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2632(String emailAddress, String password, String DR) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(DR);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		Assert.assertEquals(projectPage.getTitle(), "Dodge Global Network - Project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderMultipleDR", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2633(String emailAddress, String password, String MultipleDR) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(MultipleDR);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		Assert.assertTrue(projectPage.isViewThisDodgeReportLinkPresent());
		Assert.assertFalse(projectPage.isPaginationSectionPresent());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2634(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.enterSearchText("201500496277");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();
		projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectPage.checkforErrorMessage("unexpected system error has occurred"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2636(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specalertsPage = homePage.ClickMySpecalertsDropdown();
		Assert.assertEquals(specalertsPage.getTitle(), "Dodge Global Network - Project Results");
		goToBackPage();

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchPage = homePage.clickOnSavedSearchMenu();
		Assert.assertEquals(savedSearchPage.getTitle(), "Dodge Global Network - My Account - My Saved Searches");
		goToBackPage();

		homePage.clickOnMySearchesDropDown();
		TrackingList trackingList = homePage.clickOnMyTrackingListSubmenu();
		Assert.assertEquals(trackingList.getTitle(), "Dodge Global Network - My Account - My Tracking Lists");
		goToBackPage();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2637(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.enterSearchText("hello");
		projectResultsPage.clickOnSearchBtn();
		String filtertext = projectResultsPage.getAppliedcrumb_txt(0);
		Assert.assertTrue(filtertext.contains("hello"));
		String tooltip = projectResultsPage.mouseOverOncrumbFirstFilterLinkandGetTooltip();
		Assert.assertTrue(tooltip.contains("hello"));
		projectResultsPage.clickOnClearAllLinkInFilter();
		Assert.assertFalse(projectResultsPage.mouseOverOncrumbFirstFilterLinkandGetTooltip().contains("hello"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2640(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.IsfilterclearAllLink_Displayed());
		Assert.assertTrue(projectResultsPage.isShowMoreFilterDisplayed());

		projectResultsPage.clickOnCommonShowMoreFiltes_Arrow();
		Assert.assertTrue(projectResultsPage.isShowLessFilterDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Print project details from report view (HTML)-scenario1 (TC1570)")
	public void tc2643(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.IsfilterclearAllLink_Displayed());
		Assert.assertTrue(projectResultsPage.isShowMoreFilterDisplayed());

		projectResultsPage.clickOnCommonShowMoreFiltes_Arrow();
		Assert.assertTrue(projectResultsPage.isShowLessFilterDisplayed());

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isShowLessFilterDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2740(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		Assert.assertTrue(projectResultsPage.IsMagnifyingGlassSearchButtonInSectionVisualizationSectionsDisplayed());
		Assert.assertTrue(projectResultsPage.IsFindInSearchBoxInSectionVisualtizationDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2652(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("State"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2653(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnDashboard2ToggleButton();
		Assert.assertTrue(projectResultsPage.hoverOverManitbaStateAndGetMapToolTip().contains("Province Name:"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2654(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("Project Count:"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2655(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		List<String> actualsmallStateUSAMapLabels = projectResultsPage.getSmallStatesLabelNames();
		CommonUtils.sortWebElements(actualsmallStateUSAMapLabels, true);
		List<String> expectedSmallStatesUSAMapLables = Arrays.asList("CT", "DE", "DC", "ME", "MD", "MA", "NH", "NJ",
				"RI", "VT");
		CommonUtils.sortWebElements(expectedSmallStatesUSAMapLables, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualsmallStateUSAMapLabels, expectedSmallStatesUSAMapLables));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2656(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		List<String> actualsmallStateCanadaMapLabels = projectResultsPage.getSmallStatesLabelNames();
		CommonUtils.sortWebElements(actualsmallStateCanadaMapLabels, true);
		List<String> expectedSmallStatesCanadaMapLables = Arrays.asList("PE", "NB", "NS");
		CommonUtils.sortWebElements(expectedSmallStatesCanadaMapLables, true);
		Assert.assertTrue(
				CommonUtils.CompareTwoList(actualsmallStateCanadaMapLabels, expectedSmallStatesCanadaMapLables));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2657(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("State Name: Washington"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2658(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnDashboard2ToggleButton();
		Assert.assertTrue(
				projectResultsPage.hoverOverManitbaStateAndGetMapToolTip().contains("Province Name: Manitoba"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2659(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("Project Count:"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2660(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getGeographicalMapTitle(), "Project Density by Location");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2661(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.clickBarChartProjectTypeEngineering();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains("Project Type Category"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2662(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.clickBarChartProjectTypeEngineering();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getClassOfFirstProjectTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondProjectTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2663(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.clickBarChartProjectTypeEngineering();
		projectResultsPage.clickBarChartProjectTypeGeneralBuilding();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getClassOfFirstProjectTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondProjectTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfThirdProjectTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2665(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_CountyFilter();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String projectCountprojectResultsPageBefore = projectResultsPage.getFirstGeographyInFilterProjectCount();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");
		String projectCountafter = projectResultsPage.getChartProjectResultsCount();

		String projectCountprojectResultsPageAfter = projectResultsPage.getFirstGeographyInFilterProjectCount();

		Assert.assertNotEquals(projectCountprojectResultsPageAfter, projectCountprojectResultsPageBefore);
		Assert.assertNotEquals(projectCountafter, projectCountBefore);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2666(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.selectActionStageUnderSecondChartView();
		projectResultsPage.clickOnActionStageFilter();
		String firstActionStageFilterText = projectResultsPage.getActionStage_LHSFirstParentElement_lbl();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getClassOfFirstActionStageTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfsecondActionStageTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		projectResultsPage.clickBarChartProjectTypeEngineering();
		Assert.assertEquals(projectResultsPage.getSelectedBarInChart1Text(), "Engineering");
		Assert.assertTrue(firstActionStageFilterText.contains(projectResultsPage.getSelectedBarInChart2Text()));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2667(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickBarChartProjectTypeEngineering();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickBarChartProjectTypeUtilities();
		projectResultsPage.waitforLoadingRing();

		projectResultsPage.clickBarChartProjectTypeEngineering();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getClassOfFirstProjectTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondProjectTypeBarInSectionVisualizations(), 
				"barWrapper deselectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfThirdProjectTypeBarInSectionVisualizations(), 
				"barWrapper selectedbar");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2669(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		Assert.assertTrue(projectResultsPage.hoverOverAdditionsandgetMapToolTip().contains(","));
		Assert.assertTrue(projectResultsPage.getChartProjectResultsCount().trim().contains(","));
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains(","));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2670(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2672(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2682(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultsPage.isDivisionChartDisplayed(), "Failed to select Division under Dashboard1");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2685(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		List<String> actualCountDivisions = projectResultsPage.getCountDivisions();
		List<String> actualCountDivisionBars = projectResultsPage.getCountDivisionBars();
		List<String> actualCountSpecDivision = projectResultsPage.getCountSpecDiv();
		List<String> expectedCountDivisions = CommonUtils.sortWebElements(actualCountDivisions, true);
		Assert.assertEquals(actualCountDivisions.size(), actualCountDivisionBars.size(),
				actualCountSpecDivision.size());
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountDivisions, expectedCountDivisions));
		Assert.assertTrue(projectResultsPage.mouseOverFirstDivisionBarandGetText().contains("Procurement"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2689(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());
		Assert.assertFalse(projectResultsPage.isBreadCrumbSectionDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());
		Assert.assertFalse(projectResultsPage.isBreadCrumbSectionDisplayed());

		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isTexasStateOnMapDisplayed());
		Assert.assertFalse(projectResultsPage.isBreadCrumbSectionDisplayed());

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isTexasStateOnMapDisplayed());
		Assert.assertFalse(projectResultsPage.isBreadCrumbSectionDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Hiding projects from list view-scenario1 (TC1554)")
	public void tc2691(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertTrue(projectResultsPage.isDashboard1TogglebuttonDisplayed());
		Assert.assertTrue(projectResultsPage.isDashboard2TogglebuttonDisplayed());
		Assert.assertTrue(projectResultsPage.isProjectListTogglebuttonDisplayed());

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		projectResultsPage = savedSearchesPage.clickOnfirstProjectSavedSearch();

		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isDashboard1TogglebuttonDisplayed());
		Assert.assertTrue(projectResultsPage.isDashboard2TogglebuttonDisplayed());
		Assert.assertTrue(projectResultsPage.isProjectListTogglebuttonDisplayed());

		projectResultsPage.clickOnHomeTab();
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.waitforLoadingRing();
		homePage.selectFromCustomiseDashboardSavedSearch(2);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.clickOnSecondDashboardMyProjectsSavedSearchList();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Hiding projects from list view-scenario1 (TC1554)")
	public void tc2692(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createPrivateProjectTrackingListAdminUser(myAccount, emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnMySearchesDropDown();
		TrackingList trackingList = projectResultsPage.clickOnMySearchesTrackingList();
		projectResultsPage = trackingList.clickOnTrackingList(1);

		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		Assert.assertFalse(projectResultsPage.isDashboard1TogglebuttonDisplayed());
		Assert.assertFalse(projectResultsPage.isDashboard2TogglebuttonDisplayed());
		Assert.assertFalse(projectResultsPage.isProjectListTogglebuttonDisplayed());
		homePage.clickOnSignOutButton();
	}

	private int getProjectTrackingListCount(MyAccount myAccount, String typeListOption) {
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.selectTypeListDropdown(typeListOption);
		trackingLists.selectResultPerPageOption("500");
		return trackingLists.getDeleteLinkListSize();
	}

	public void createPrivateProjectTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Private");
		if (projectTrackingCount < 30) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			trackingLists.selectResultPerPage("500");
			for (int i = 0; i < 30 - projectTrackingCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	/* This functionality is removed in 5.2 release
	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2693(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();

		Assert.assertEquals(projectResultsPage.mouseOverProjectListBtnAndGetTooltip(), "Project List");
		Assert.assertEquals(projectResultsPage.mouseOverDashboard1BtnAndGetTooltip(), "Dashboard 1");
		Assert.assertEquals(projectResultsPage.mouseOverDashboard2BtnAndGetTooltip(), "Dashboard 2");
		homePage.clickOnSignOutButton();

	}
*/
	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2695(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String selectedDivision1 = projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		projectResultsPage.waitforLoadingRing();
		String selectedDivision2 = projectResultsPage.clickSecondDivisionBarInSectionVisualizations();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedDivision1));
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedDivision2));
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountBefore, projectCountAfter);
		projectResultsPage.ClickOncrumbSecondFilterClose();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertFalse(projectResultsPage.isAnyBarSelectedinChart1());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2700(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Total:"));
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Divisions"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2703(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.waitforLoadingRing();
		String sectionsSortDropdownActualValues = projectResultsPage.VerifySectionsSortDropdownValues().trim();
		Assert.assertTrue(sectionsSortDropdownActualValues.contains("Count Highest - Lowest"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2704(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getGeographicalMapTitle(), "Project Density by Location");

		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("State Name: Washington"));
		Assert.assertTrue(projectResultsPage.hoverOverWashingTonandgetMapToolTip().contains("Project Count:"));

		// for canada
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.hoverOverManitbaStateAndGetMapToolTip().contains("Province Name:"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2705(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String selectedSection1 = projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");
		String projectCountafter = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.getNamesOfAllAppliedFilters();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedSection1));
		Assert.assertNotEquals(projectCountafter, projectCountBefore);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2706(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String selectedSection = projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedSection));
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountBefore, projectCountAfter);
		Assert.assertEquals(projectResultsPage.mouseOverOncrumbSecondFilterLinkandGetTooltip(),
				"01 33 00 - Submittal Procedures ");
		projectResultsPage.ClickOncrumbSecondFilterClose();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedSection));
		Assert.assertFalse(projectResultsPage.isAnyBarSelectedinChart2());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2707(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String selectedSection1 = projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		String selectedSection2 = projectResultsPage.clickSecondSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		String selectedSection3 = projectResultsPage.clickThirdSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		String selectedSection4 = projectResultsPage.clickFourthSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getGroupedSectionFilterName().trim().contains("4 Filters"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2710(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();

		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		String sectionChartHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();
		Assert.assertFalse(sectionChartHeaderTextAfter.equals(null));
		List<String> actualCountSections = projectResultsPage.getWidthOfSectionBars();
		List<String> expectedCountSections = CommonUtils.sortWebElements(actualCountSections, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountSections, expectedCountSections));

		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		String projectCountOnProjectListView = projectResultsPage.getCountOfProjectsDisplayed();
		Assert.assertTrue(projectResultsPage.IsProcurementSpecDivisionCheckBoxChecked());
		Assert.assertEquals(projectCountOnProjectListView, projectCountAfter.replaceAll(",", ""));

		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getChartProjectResultsCount().replaceAll(",", ""),
				projectCountOnProjectListView);
		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2712(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String washingtonMaptooltipBefore = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		String washingtonMaptooltipAfter = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		Assert.assertNotEquals(washingtonMaptooltipAfter, washingtonMaptooltipBefore);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2715(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertFalse(companyResultsPage.isBreadCrumbSectionDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2717(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String projectTooltipWashingtonStateBefore = projectResultsPage.hoverOverWashingTonandgetMapToolTip();

		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.clickSecondSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");
		String projectCountafter = projectResultsPage.getChartProjectResultsCount();
		String projectTooltiptWashingtonStateAfter = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		Assert.assertNotEquals(projectCountafter, projectCountBefore);
		int endIndex1 = projectTooltipWashingtonStateBefore.lastIndexOf(" ");
		int endIndex2 = projectTooltiptWashingtonStateAfter.lastIndexOf(" ");
		Assert.assertNotEquals(
				projectTooltipWashingtonStateBefore.substring(endIndex1, projectTooltipWashingtonStateBefore.length()),
				projectTooltiptWashingtonStateAfter.substring(endIndex2, projectTooltiptWashingtonStateAfter.length()));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2718(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.isSortDropdownDisplayed());
		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed());
		Assert.assertTrue(companyResultsPage.isResultsDropdownDisplayed());
		Assert.assertTrue(companyResultsPage.isPaginationSectionDisplayed());
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2720(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.clickMapChartProjectDensityWashington();
		projectResultsPage.clickMapChartProjectDensityTexas();
		String projectCountafter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountafter, projectCountBefore);
		List<String> actualCountDivisions = projectResultsPage.getCountDivisions();
		List<String> expectedCountDivisions = CommonUtils.sortWebElements(actualCountDivisions, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountDivisions, expectedCountDivisions));
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Total:"));
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Divisions"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2721(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.clickMapChartProjectDensityWashington();
		projectResultsPage.clickMapChartProjectDensityTexas();
		String projectCountafter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountafter, projectCountBefore);
		Assert.assertTrue(projectResultsPage.isSectionsListDisplayedInSectionChart());
		Assert.assertTrue(projectResultsPage.getSecondChartSubheaderText().contains("Top"));
		Assert.assertTrue(projectResultsPage.getSecondChartSubheaderText().contains("of"));
		Assert.assertTrue(projectResultsPage.getSecondChartSubheaderText().contains("Sections"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2724(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.clickSecondSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.clickSecondSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2726(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2728(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionChartHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		String washingtonMaptooltipBefore = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		String washingtonMaptooltipAfter = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		String sectionChartHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();
		Assert.assertNotEquals(washingtonMaptooltipAfter, washingtonMaptooltipBefore);
		Assert.assertNotEquals(sectionChartHeaderTextAfter, sectionChartHeaderTextBefore);

		List<String> actualCountSections = projectResultsPage.getWidthOfSectionBars();
		List<String> expectedCountSections = CommonUtils.sortWebElements(actualCountSections, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountSections, expectedCountSections));

		projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");

		String washingtonMaptooltipAfterSectionSelection = projectResultsPage.hoverOverWashingTonandgetMapToolTip();

		Assert.assertNotEquals(washingtonMaptooltipAfterSectionSelection, washingtonMaptooltipAfter);
		String washingtonMaptooltipAfterSelectingSection = projectResultsPage.hoverOverWashingTonandgetMapToolTip();

		projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertFalse(projectResultsPage.isAnyBarSelectedinChart2());
		String washingtonMaptooltipAfterSectionDeselection = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		Assert.assertNotEquals(washingtonMaptooltipAfterSectionDeselection, washingtonMaptooltipAfterSelectingSection);
		Assert.assertEquals(projectResultsPage.getSecondChartSubheaderText(), sectionChartHeaderTextAfter);

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2730(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionChartHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		String washingtonMaptooltipBefore = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		String washingtonMaptooltipAfter = projectResultsPage.hoverOverWashingTonandgetMapToolTip();
		String sectionChartHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();
		Assert.assertNotEquals(washingtonMaptooltipAfter, washingtonMaptooltipBefore);
		Assert.assertNotEquals(sectionChartHeaderTextAfter, sectionChartHeaderTextBefore);

		List<String> actualCountSections = projectResultsPage.getWidthOfSectionBars();
		List<String> expectedCountSections = CommonUtils.sortWebElements(actualCountSections, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountSections, expectedCountSections));

		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		Assert.assertFalse(projectResultsPage.isAnyBarSelectedinChart1());
		Assert.assertEquals(projectResultsPage.getSecondChartSubheaderText(), sectionChartHeaderTextBefore);
		// This step will fail if project count changes due to ingestion
		Assert.assertEquals(projectResultsPage.getChartProjectResultsCount(), projectCountBefore);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2733(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		String colorBeforeSelection = projectResultsPage.getConstructionTypeAdditionsColor();
		projectResultsPage.clickOnChartConnstructionTypeAdditions();
		projectResultsPage.waitforLoadingRing();
		String colorAfterSelection = projectResultsPage.getConstructionTypeAdditionsColor();
		Assert.assertTrue(projectResultsPage.verifyConstructionTypeAdditionsFiltered());
		Assert.assertEquals(colorAfterSelection, colorBeforeSelection);
		projectResultsPage.clickOnChartConnstructionTypeAdditions();
		projectResultsPage.waitforLoadingRing();
		String colorAfterDeselection = projectResultsPage.getConstructionTypeAdditionsColor();
		Assert.assertFalse(projectResultsPage.verifyConstructionTypeAdditionsFiltered());
		Assert.assertEquals(colorAfterDeselection, colorAfterSelection);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2734(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();

		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		String divisionHeaderTextBefore = projectResultsPage.getFirstChartSubheaderText();

		projectResultsPage.clickMapChartProjectDensityWashington();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickMapChartProjectDensityTexas();
		projectResultsPage.waitforLoadingRing();
		String projectCountAfterStateSelection = projectResultsPage.getChartProjectResultsCount();

		Assert.assertNotEquals(projectCountAfterStateSelection, projectCountBefore);
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Total:"));
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().contains("Divisions"));
		List<String> actualCountDivisions = projectResultsPage.getCountDivisions();
		List<String> expectedCountDivisions = CommonUtils.sortWebElements(actualCountDivisions, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualCountDivisions, expectedCountDivisions));

		projectResultsPage.clickMapChartProjectDensityWashington();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickMapChartProjectDensityTexas();
		projectResultsPage.waitforLoadingRing();

		String projectCountAfterStateDeSelection = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextAfterStateDeSelection = projectResultsPage.getSecondChartSubheaderText();
		String divisionHeaderTextAfterStateDeSelection = projectResultsPage.getFirstChartSubheaderText();

		Assert.assertEquals(projectCountAfterStateDeSelection, projectCountBefore);
		Assert.assertEquals(sectionsHeaderTextAfterStateDeSelection, sectionsHeaderTextBefore);
		Assert.assertEquals(divisionHeaderTextAfterStateDeSelection, divisionHeaderTextBefore);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2736(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();

		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertNotEquals(sectionsHeaderTextAfter, sectionsHeaderTextBefore);

		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName1);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName1));
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName1);
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String projectCountAfterOpeningSavedSearch = projectResultsPage.getChartProjectResultsCount();
		Assert.assertEquals(projectCountAfterOpeningSavedSearch, projectCountAfter);
		Assert.assertFalse(projectResultsPage.isPlanInFindInFilterChecked());
		Assert.assertTrue(projectResultsPage.isSpecsInFindInFilterChecked());

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2737(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();

		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertNotEquals(sectionsHeaderTextAfter, sectionsHeaderTextBefore);

		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfSecondDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		projectResultsPage.clickOnActionStageFilter();

		projectResultsPage.SelectOptionsFromTheList(2, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String projectCountAfterActionstage = projectResultsPage.getChartProjectResultsCount();

		Assert.assertNotEquals(projectCountAfterActionstage, projectCountAfter);

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName1);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName1));
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName1);
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String projectCountAfterOpeningSavedSearch = projectResultsPage.getChartProjectResultsCount();
		Assert.assertEquals(projectCountAfterOpeningSavedSearch, projectCountAfterActionstage);

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2739(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();

		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.clickSecondSectionBarInRightChart();
		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();

		Assert.assertNotEquals(projectCountAfter, projectCountBefore);

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName1);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName1));
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName1);
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();

		String projectCountAfterOpeningSavedSearch = projectResultsPage.getChartProjectResultsCount();
		Assert.assertEquals(projectCountAfterOpeningSavedSearch, projectCountAfter);

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2741(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		Assert.assertEquals(projectResultsPage.getSectionFindInfilterPreloadedText(), "Enter Section Name");
		String sectionChartHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		String sectionChartHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();
		Assert.assertNotEquals(sectionChartHeaderTextAfter, sectionChartHeaderTextBefore);
		Assert.assertEquals(projectResultsPage.getSectionFindInfilterPreloadedText(), "Enter Section Name");
		projectResultsPage.clickMapChartProjectDensityWashington();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickMapChartProjectDensityTexas();
		projectResultsPage.waitforLoadingRing();
		String sectionChartHeaderTextAfterSelectingStates = projectResultsPage.getSecondChartSubheaderText();
		Assert.assertNotEquals(sectionChartHeaderTextAfterSelectingStates, sectionChartHeaderTextAfter);
		Assert.assertEquals(projectResultsPage.getSectionFindInfilterPreloadedText(), "Enter Section Name");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2743(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.enterTextInSectionVisualizationFindInSearchBox("mas");
		projectResultsPage.clickSectionVisualizationSectionsSearchBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.verifyAllSectionTitlesContainsTheSearchedKeyword("Unit Masonry"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2750(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		List<String> actualsmallStateUSAMapLabels = projectResultsPage.getSmallStatesLabelNames();
		CommonUtils.sortWebElements(actualsmallStateUSAMapLabels, true);
		List<String> expectedSmallStatesUSAMapLables = Arrays.asList("CT", "DE", "DC", "ME", "MD", "MA", "NH", "NJ",
				"RI", "VT");
		CommonUtils.sortWebElements(expectedSmallStatesUSAMapLables, true);
		Assert.assertTrue(CommonUtils.CompareTwoList(actualsmallStateUSAMapLabels, expectedSmallStatesUSAMapLables));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2751(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		List<String> actualsmallStateCanadaMapLabels = projectResultsPage.getSmallStatesLabelNames();
		CommonUtils.sortWebElements(actualsmallStateCanadaMapLabels, true);
		List<String> expectedSmallStatesCanadaMapLables = Arrays.asList("PE", "NB", "NS");
		CommonUtils.sortWebElements(expectedSmallStatesCanadaMapLables, true);
		Assert.assertTrue(
				CommonUtils.CompareTwoList(actualsmallStateCanadaMapLabels, expectedSmallStatesCanadaMapLables));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2752(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("concrete");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isPlanInFindInFilterChecked());
		Assert.assertTrue(projectResultsPage.isSpecsInFindInFilterChecked());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2753(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String projectCountBefore = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextBefore = projectResultsPage.getSecondChartSubheaderText();

		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		projectResultsPage.clickSecondDivisionBarInSectionVisualizations();

		String projectCountAfter = projectResultsPage.getChartProjectResultsCount();
		String sectionsHeaderTextAfter = projectResultsPage.getSecondChartSubheaderText();

		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		Assert.assertNotEquals(sectionsHeaderTextAfter, sectionsHeaderTextBefore);

		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfThirsDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");

		projectResultsPage.clickFirstSectionBarInRightChart();
		Assert.assertEquals(projectResultsPage.getClassOfFirstSectionBarInRightChart(), "rectSection");
		Assert.assertEquals(projectResultsPage.getClassOfThirdSectionBarInRightChart(), "rectSection");

		Assert.assertEquals(projectResultsPage.getClassOfFirstDivisionTypeBarInSectionVisualizations(),
				"barWrapper selectedbar");
		Assert.assertEquals(projectResultsPage.getClassOfThirsDivisionTypeBarInSectionVisualizations(),
				"barWrapper deselectedbar");
		String projectCountAfterSectionSelection = projectResultsPage.getChartProjectResultsCount();

		Assert.assertNotEquals(projectCountAfterSectionSelection, projectCountAfter);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2757(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String selectedSection = projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.getNamesOfAllAppliedFilters();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedSection));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc2758(String emailAddress, String password) throws ParseException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectSectionsUnderSecondChartView();
		projectResultPage.clickMapChartProjectDensityWashington();
		Assert.assertTrue(projectResultPage.verifyMapChartProjectDensityWashingtonSelected(),
				"Failed to get the project density by location selected");
		projectResultPage.clickFirstSectionBarInRightChart();
		projectResultPage.waitforLoadingRing();
		projectResultPage.getNamesOfAllAppliedFilters();
		Assert.assertTrue(projectResultPage.getNamesOfAllAppliedFilters().contains("Washington"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2767(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.enterTextInSectionVisualizationFindInSearchBox("masonry");

		projectResultsPage.clickSectionVisualizationSectionsSearchBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.verifyAllSectionTitlesContainsTheSearchedKeyword("Unit Masonry"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc2768(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderSecondChartView();
		projectResultsPage.enterTextInSectionVisualizationFindInSearchBox("masonry");
		projectResultsPage.pressEnterKey();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.verifyAllSectionTitlesContainsTheSearchedKeyword("Unit Masonry"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification  of  Project saved search behaviour on creating a Saved Search with   bypass setting as off (TC12443)")
	public void tc3659(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPageNumber2();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber2HighLightedInBold());
		projectResultsPage.clickOnNextPageNumber();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnFirstPageIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber1HighLightedInBold());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification that 5000 Page-size option is sticky within the session and not across the session")
	public void tc3316(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		final String existingPageSize = projectResultsPage.getResultDropdownSelectedValue();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.ClickOnCompaniesTab();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), "5000",
				"5000 Page-size option is not sticky.");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), existingPageSize,
				"5000 Page-size option is across the sessions");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification that 10000 Page-size option is sticky within the session and not across the session")
	public void tc3317(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		final String existingPageSize = projectResultsPage.getResultDropdownSelectedValue();
		projectResultsPage.SelectResultDropdownValue("10000");
		projectResultsPage.ClickOnCompaniesTab();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), "10000",
				"10000 Page-size option is not sticky.");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), existingPageSize,
				"10000 Page-size option is across the sessions");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verify Action Drop down option is available on selecting all result per page option for Project Result")
	public void tc3377(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.SelectResultDropdownValue("1000");
		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 1000.");
		projectResultsPage.SelectResultDropdownValue("5000");
		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000.");
		projectResultsPage.SelectResultDropdownValue("10000");
		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 10000.");
	}
}

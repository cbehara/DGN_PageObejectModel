package com.ddaqe.dgn_company_search;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_company_saved_search.DGNCompanySavedSearch;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingLists;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNCompanySearch extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanySavedSearch.class);

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
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verify results per page preference set across sessions.")
	public void tc2906(String username, String password) {
		String resultPerPage = "50";
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		homePage.clickOnSignOutButton();

		homePage = loginAs(username, password);
		projectResultsPage = homePage.clickOnProjectsLink();

		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPage);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2908", description = "To Verify the Breadcrumb displayed in Company Page.")
	public void tc2908(String username, String password, String companyPageBreadcrumb,
			String companyContactsPageBreadcrumb, String companyProjectsPageBreadcrumb) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitleWithContacts();
		Assert.assertEquals(companyPage.getBreadCrumbText(), companyPageBreadcrumb);
		companyResultsPage.waitforLoadingRing();
		CompanyContactsPage companyContactsPage = companyPage.clickOnCompanyContactsLink();
		Assert.assertEquals(companyContactsPage.getBreadCrumbText(), companyContactsPageBreadcrumb);
		companyResultsPage.waitforLoadingRing();
		CompanyProjectsPage companyProjectsPage = companyContactsPage.clickOnCompanyProjectsLink();
		Assert.assertEquals(companyProjectsPage.getBreadCrumbText(), companyProjectsPageBreadcrumb);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "To Verify that Map Tab is been Hidden on the Company Report.")
	public void tc2907(String username, String password, String searchText) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		Assert.assertFalse(companyResultsPage.isMapIconLinkDisplayed(),
				"Map icon link is present on the company result page.");

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.isMapIconLinkDisplayed(),
				"Map icon link is not present on the company result page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "To Verify the View Projects option in  Action Menu in Company Projects Page.")
	public void tc2909(String username, String password) {
		String firstProjDrNumber = "";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyResultsPage.waitforLoadingRing();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnActionsDropdownCompany();

		Assert.assertTrue(companyProjectsPage.isViewProjectActionMenuDisplayed(),
				"View Project Option is not present in the action dropdown.");
		firstProjDrNumber = companyProjectsPage.getFirstDRNumer();

		companyProjectsPage.clickOnFistProjectCheckbox();
		companyProjectsPage.clickOnSecondProjectCheckbox();
		companyProjectsPage.mouseOverActionandClickViewProjects();
		companyProjectsPage.waitforLoadingRing();
		Assert.assertEquals(companyProjectsPage.getDRNumerOnly(), firstProjDrNumber.trim());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "To Verify the View Projects option in  Action Menu in Company Projects Page.")
	public void tc2910(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		companyProjectsPage.waitforLoadingRing();
		companyProjectsPage.clickOnFistProjectCheckbox();
		companyProjectsPage.clickOnSecondProjectCheckbox();
		companyProjectsPage.waitforLoadingRing();
		companyProjectsPage.clickOnActionsDropdownCompany();
		Assert.assertTrue(companyProjectsPage.mouseOverActionandChecktrackProjectsDisplayed(),
				"Track Projects Option is not present in the action dropdown.");
		TrackPopUpPage trackPopUpPage = companyProjectsPage.mouseOverActionandClicktrackProjects();

		trackPopUpPage.clickOnFirstTrackProjectCheckbox();
		Assert.assertTrue(trackPopUpPage.isTrackAlertChkBoxClickable(),
				"Alert me of updates to these projects is not clickable");
		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(companyProjectsPage.isProjectSelectAllDisplayed());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verify Action menu in Company page.")
	public void tc2911(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.verifyCompanyPageActionsList());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2912", description = "To Verify that user is able to send Email from the search result Page.")
	public void tc2912(String username, String password, String searchText, String emailCompanyAddress,
			String emailOptionalMessage) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultsPage.clickEmailCompaniesLinkUnderActionsDrpDwn();

		emailAlertsPage.enterEmailAddress(emailCompanyAddress);
		emailAlertsPage.enterOptionalMessage(emailOptionalMessage);
		emailAlertsPage.clickOnPopUpSaveButton();

		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verification of thousands comma in company profile page.")
	public void tc2913(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectValueFromSortDropdown("Number of Projects - Descending");
		companyResultsPage.waitforLoadingRing();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.checkProjectCountThausandSeparated(), "Project count is not thoausand separated");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verification of Include the results per page selector at the top of  Company projects results list view.")
	public void tc2914(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyProjectsPage.isMyProjectsTabDisplayed(),
				"My Projects Tab is not displayed on company project page.");

		Assert.assertTrue(companyProjectsPage.isProjectsNotInMyLicenseTabDisplayed(),
				"My Projects Tab is not displayed on company project page.");

		Assert.assertTrue(companyProjectsPage.isResultPerPageDropdownAtTopDisplayed(),
				"Result Per Page Dropdown At Top is not Displayed.");
		Assert.assertTrue(companyProjectsPage.isResultPerPageDropdownAtBottomDisplayed(),
				"Result Per Page Dropdown At Bottom is not Displayed.");
		Assert.assertTrue(companyProjectsPage.isProjectCountInMyProjectTabGreaterthanzero(),
				"Not all the project in my project tab are listed.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verifying that 'Profile'  link is changed into 'Company' link in company tab list view page.")
	public void tc2915(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.isCompanyProfileLinkDisplayed(),
				"Company profile link is not present on the company result page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "Verifying Company link in Company save search list view (TC15108)")
	public void tc2916(String username, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();

		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();
		savedSearchesPage.clickOnCompanyTab();
		companyResultsPage = savedSearchesPage.clickOnfirstCompanySavedSearch();

		Assert.assertTrue(companyResultsPage.isCompanyProfileLinkDisplayed(),
				"Company profile link is not present on the company result page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2917", description = "Verifying Company link in Company Tracking list  view (TC15109)")
	public void tc2917(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListDropdown("Public");
		CompanyResultsPage companyResultsPage = trackingLists.clickOnFirstCompanyTrackingList();
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.isCompanyProfileLinkDisplayed(),
				"Company profile link is not present on the company result page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "Verify the keyword is remain in the Quick keyword search box and in left nav keyword search box after doing keyword search (TC22051)")
	public void tc2919(String username, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		homePage.clickOnCompaniesLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		Assert.assertEquals(homePage.getSearchText(), searchText);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verify no filters are present below the keyword Quick search box. (TC22052)")
	public void tc2920(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.ismySearchesDropDownDisplayed(),
				"My Searches dropdown is not present on the company result page.");

		companyResultsPage.clickOnMySearchesDropDown();
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.isMySearchesSpecAlertsDisplayed(),
				"My Searches dropdown does not contain option SpecAlerts present on the company result page.");
		Assert.assertTrue(companyResultsPage.isMySearchesTrackingListsDisplayed(),
				"My Searches dropdown does not contain option Tracking Lists present on the company result page.");
		Assert.assertTrue(companyResultsPage.isMySearchesSaveSearchesDisplayed(),
				"My Searches dropdown does not contain option Save Searches present on the company result page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC3752", description = "The goal of this test case is to verify Error message should be displayed immediately adjacent to the keyword search text-box when keyword with grammar errors entered in the keyword search box.")
	public void tc3752(String username, String password, String searchText, String errorQuate)
			throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		projectResultsPage.clickOnFirstProjectTitle();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		companyPage.clickOnCompanyContactsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		companyPage.clickOnCompanyProjectsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC3771", description = "The goal of this test case is to verify Error message should be displayed immediately adjacent to the keyword search text-box when keyword with grammar errors entered in the keyword search box.")
	public void tc3771(String username, String password, String searchText, String errorQuate)
			throws InterruptedException {
		HomePage homePage = loginAs(username, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		projectResultsPage.clickOnFirstProjectTitle();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		companyPage.clickOnCompanyContactsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		companyPage.clickOnCompanyProjectsLink();
		homePage.enterSearchText(searchText);
		Assert.assertEquals(homePage.getKeywordSearchErrorMessage(), errorQuate);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = " Verify reedesigned view header for Company Search results page views.")
	public void tc3747(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();

		savedSearchesPage.clickOnCompanyTab();
		CompanyResultsPage companyResultsPage = savedSearchesPage.clickOnfirstCompanySavedSearch();

		Assert.assertTrue(companyResultsPage.checkSelectAllRedesignWrap(),
				"Select All is not set as redesign wrapper save search company.");
		Assert.assertTrue(companyResultsPage.checkActionDropdownRedesignWrap(),
				"Action dropdown is not set as redesign wrapper save search company.");
		Assert.assertTrue(companyResultsPage.checkSortDropdownRedesignWrap(),
				"Sort dropdown is not set as redesign wrapper save search company.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Verify redesigned view header is not shown for tracking list")
	public void tc3748(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		CompanyResultsPage companyResultsPage = trackingLists.clickOnFirstCompanyTrackingList();

		Assert.assertTrue(companyResultsPage.checkSelectAllRedesignWrap(),
				"Select All is not set as redesign wrapper save search company.");
		Assert.assertTrue(companyResultsPage.checkActionDropdownRedesignWrap(),
				"Action dropdown is not set as redesign wrapper save search company.");
		Assert.assertTrue(companyResultsPage.checkSortDropdownRedesignWrap(),
				"Sort dropdown is not set as redesign wrapper save search company.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2917", description = " Verify reedesigned view header for SpecAlerts page.")
	public void tc3755(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.ismySearchesDropDownDisplayed(),
				"My Searches dropdown is not present on the company result page.");

		companyResultsPage.clickOnMySearchesDropDown();
		companyResultsPage.waitforLoadingRing();
		SpecAlertsResultsPage specAlertsResultsPage = companyResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.waitforLoadingRing();
		Assert.assertTrue(specAlertsResultsPage.checkSelectAllRedesignWrap(),
				"Select All is not set as redesign wrapper save search company.");
		Assert.assertTrue(specAlertsResultsPage.checkActionDropdownRedesignWrap(),
				"Action dropdown is not set as redesign wrapper save search company.");
		Assert.assertTrue(specAlertsResultsPage.checkSortDropdownRedesignWrap(),
				"Sort dropdown is not set as redesign wrapper save search company.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = " Verify Pagination control for Company Search results page views.")
	public void tc3757(String username, String password) throws InterruptedException {
		String resultPerPage = "10";
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();

		savedSearchesPage.clickOnCompanyTab();
		CompanyResultsPage companyResultsPage = savedSearchesPage.clickOnfirstCompanySavedSearch();
		companyResultsPage.selectResultsPerPage(resultPerPage);

		Assert.assertTrue(companyResultsPage.checkPaginationSectionPresentOnPage(),
				"Pagination section is not present at top and bottom of page.");

		companyResultsPage.clickOnSecondCompanyPage();
		Assert.assertTrue(companyResultsPage.isSecondCompanyPageActive(),
				"Second company page is not loaded after click.");

		companyResultsPage.clickOnThirdCompanyPage();
		;
		Assert.assertTrue(companyResultsPage.isThirdCompanyPageActive(),
				"Third company page is not loaded after click.");

		companyResultsPage.clickOnNextCompanyPage();
		Assert.assertTrue(companyResultsPage.isFourCompanyPageActive(),
				"Four company page is not loaded after click on next page button.");

		companyResultsPage.clickOnPreviousCompanyPage();
		Assert.assertTrue(companyResultsPage.isThirdCompanyPageActive(),
				"Third company page is not loaded after click.");

		companyResultsPage.clickOnPreviousCompanyPage();
		Assert.assertTrue(companyResultsPage.isSecondCompanyPageActive(),
				"Second company page is not loaded after click.");

		companyResultsPage.clickOnFirstPageButton();
		Assert.assertTrue(companyResultsPage.isFirstCompanyPageActive(),
				"first company page is not loaded after click on first page button.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2917", description = "Verify Pagination control for SpecAlert page.")
	public void tc3758(String username, String password) throws InterruptedException {
		String resultPerPage = "10";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.ismySearchesDropDownDisplayed(),
				"My Searches dropdown is not present on the company result page.");

		companyResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = companyResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.SelectResultDropdownValue(resultPerPage);

		Assert.assertTrue(specAlertsResultsPage.checkPaginationSectionPresentOnPage(),
				"Pagination section is not present at top and bottom of page.");

		specAlertsResultsPage.clickOnSecondCompanyPage();
		Assert.assertTrue(specAlertsResultsPage.isSecondCompanyPageActive(),
				"Second company page is not loaded after click.");

		specAlertsResultsPage.clickOnThirdCompanyPage();

		Assert.assertTrue(specAlertsResultsPage.isThirdCompanyPageActive(),
				"Third company page is not loaded after click.");

		specAlertsResultsPage.clickOnNextCompanyPage();
		Assert.assertTrue(specAlertsResultsPage.isFourCompanyPageActive(),
				"Four company page is not loaded after click on next page button.");

		specAlertsResultsPage.clickOnPreviousCompanyPage();
		Assert.assertTrue(specAlertsResultsPage.isThirdCompanyPageActive(),
				"Third company page is not loaded after click.");

		specAlertsResultsPage.clickOnPreviousCompanyPage();
		Assert.assertTrue(specAlertsResultsPage.isSecondCompanyPageActive(),
				"Second company page is not loaded after click.");

		specAlertsResultsPage.clickOnFirstPageButton();
		Assert.assertTrue(specAlertsResultsPage.isFirstCompanyPageActive(),
				"first company page is not loaded after click on first page button.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "Company search - Result page (TC2733).")
	public void tc1758(String username, String password, String searchText) throws InterruptedException {
		String companies = "Companies";
		HomePage homePage = loginAs(username, password);
		homePage.selectProjectOrCompaniesFromDropdown(companies);
		homePage.enterSearchText(searchText);
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		Assert.assertTrue(companyResultsPage.isCompanyAddressDisplayed(), "Company address is not displayed.");
		Assert.assertTrue(companyResultsPage.isCompanyPhoneDisplayed(), "Company phone is not displayed.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "Company search - Result page (TC2734).")
	public void tc1759(String username, String password, String searchText) throws InterruptedException {
		String resultPerPage = "50";
		String companies = "Companies";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage(resultPerPage);
		homePage.clickOnSignOutButton();

		homePage = loginAs(username, password);
		homePage.selectProjectOrCompaniesFromDropdown(companies);
		homePage.enterSearchText(searchText);

		companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();

		Assert.assertEquals(companyResultsPage.getResultDropdownSelectedValue(), resultPerPage);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "Company search - Result page (TC2735).")
	public void tc1760(String username, String password, String searchText) throws InterruptedException {
		String companies = "Companies";
		HomePage homePage = loginAs(username, password);

		homePage.selectProjectOrCompaniesFromDropdown(companies);
		homePage.enterSearchText(searchText);

		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();

		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed(), "Select All checkbox is not displayed.");
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(), "Action dropdown is not displayed.");
		Assert.assertTrue(companyResultsPage.isSortDropdownDisplayed(), "Sort dropdown is not displayed.");

		companyResultsPage.clickOnSelectAllCompany();

		Assert.assertTrue(companyResultsPage.checkAllCompanyChkboxIsSelected(),
				"All company checkbox are not selected after clickon on Select All checkbox.");
		companyResultsPage.clickOnSecondCompanyChkBox();
		companyResultsPage.waitforLoadingRing();
		Assert.assertFalse(companyResultsPage.checkSelectAllCompanyIsSelected(),
				"Unchicking any check not uncheck the select all checkbox ");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2907", description = "To verify the company profile action menu.")
	public void tc1761(String username, String password, String searchText) throws InterruptedException {
		String companies = "Companies";
		HomePage homePage = loginAs(username, password);

		homePage.selectProjectOrCompaniesFromDropdown(companies);
		homePage.enterSearchText(searchText);

		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();

		Assert.assertTrue(companyPage.isActionsDropDownDisplayed(), "Action dropdown is not displayed.");
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.verifyCompanyPageActionsList());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "TC2906", description = "Dodge Report numbers in company alerts should be hyper linked to corresponding dodge report page.")
	public void tc1767(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitleWithProjectLink();
		Assert.assertTrue(companyPage.checkProjectUpdateIsHyperLink(),
				"Project update on company page are not hyperlink.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "PlusUser", description = "Verify Action Drop down option is not available on when Results page-size is 5000 in Company Result[Plus License]")
	public void tc3367TC3368(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("5000");
		Assert.assertTrue(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is not displayed if page size is 5000.");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is displayed if page size is 5000.");

		companyResultsPage.selectResultsPerPage("10000");
		Assert.assertTrue(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is not displayed if page size is 10000.");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is displayed if page size is 10000.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "PlatinumUser", description = "Verify Action Drop down option is not available on when Results page-size is 5000 in Company Result[Platinum License]")
	public void tc3369TC3370(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("5000");
		Assert.assertTrue(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is not displayed if page size is 5000.");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is displayed if page size is 5000.");

		companyResultsPage.selectResultsPerPage("10000");
		Assert.assertTrue(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is not displayed if page size is 10000.");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is displayed if page size is 10000.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "PlatinumUser", description = "Verify Action drop down in Company- Project Power Ranked page for page size of 5000 is selected for both Platinum/Plus License")
	public void tc3371TC3372(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocChkBoxWithMaxValue(1000);
		companyResultsPage.selectResultsPerPage("5000");
		Assert.assertFalse(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is displayed if page size is 5000 and result count is less than 1000.");
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000 and result count is less than 1000.");
		companyResultsPage.clickPowerRank();
		Assert.assertEquals(companyResultsPage.get_powerRankedResults_txt(), "POWER RANKED RESULTS");

		companyResultsPage.clickPowerRank();
		companyResultsPage.selectResultsPerPage("10000");
		Assert.assertFalse(companyResultsPage.isPageSizeGT1000MessageDisplayed(),
				"Warning message is displayed if page size is 10000 and result count is less than 1000.");
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 10000 and result count is less than 1000.");
		companyResultsPage.clickPowerRank();
		Assert.assertEquals(companyResultsPage.get_powerRankedResults_txt(), "POWER RANKED RESULTS");
		companyResultsPage.clickPowerRank();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "DGN-T3321", description = "Company search results page - 5,000 Page-size selection option is sticky within the session and not across the session")
	public void tcDGN3321(String emailAddress, String password, String resultPerPage) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		String resultPerPageDefaultValue = companyResultsPage.getResultDropdownSelectedValue();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		ProjectResultsPage projectResultsPage = companyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPage);

		companyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		Assert.assertEquals(companyResultsPage.getResultDropdownSelectedValue(), resultPerPage);
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);

		companyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		Assert.assertEquals(companyResultsPage.getResultDropdownSelectedValue(), resultPerPageDefaultValue);

		projectResultsPage = companyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageDefaultValue);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "DGN-T3324", description = "Company search results page - 10,000 Page-size selection option is sticky within the session and not across the session")
	public void tcDGN3324(String emailAddress, String password, String resultPerPage) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		String resultPerPageDefaultValue = companyResultsPage.getResultDropdownSelectedValue();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		ProjectResultsPage projectResultsPage = companyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPage);

		companyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		Assert.assertEquals(companyResultsPage.getResultDropdownSelectedValue(), resultPerPage);
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);

		companyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		Assert.assertEquals(companyResultsPage.getResultDropdownSelectedValue(), resultPerPageDefaultValue);

		projectResultsPage = companyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageDefaultValue);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "PlatinumUser", description = "Verify the Companies download on Company result per page of 5000.")
	public void tc3387(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("5000");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000 and result count is less than 1000.");
		companyResultsPage.clickOnCompanyLocChkBoxWithMaxValue(1000);
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000 and result count is less than 1000.");
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed(),
				"PDF radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed(),
				"XML radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isMyDownloadInputBoxDisplayed(),
				"My download input text box is not displayed on Download Company dialog.");
		downloadCompanies.clickOnPDFRadioBtn();
		String downloadName = "Automation" + CommonUtils.getTimeStamp();
		downloadCompanies.clickOnClickHereToSeeWhyLinkPdf();
		downloadCompanies.setDownloadBatchName(downloadName);
		downloadCompanies.clickOnDownloadBtn();
		companyResultsPage.waitforLoadingRing();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.deleteMyDownload(downloadName);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCompanySearchDataProvider.class, dataProvider = "PlatinumUser", description = "Verify the Companies download on Company result per page of 10000.")
	public void tc3388(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.selectResultsPerPage("10000");
		Assert.assertFalse(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000 and result count is less than 1000.");
		companyResultsPage.clickOnCompanyLocChkBoxWithMaxValue(1000);
		Assert.assertTrue(companyResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed if page size is 5000 and result count is less than 1000.");
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed(),
				"PDF radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed(),
				"XML radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isMyDownloadInputBoxDisplayed(),
				"My download input text box is not displayed on Download Company dialog.");
		downloadCompanies.clickOnPDFRadioBtn();
		String downloadName = "Automation" + CommonUtils.getTimeStamp();
		downloadCompanies.clickOnClickHereToSeeWhyLinkPdf();
		downloadCompanies.setDownloadBatchName(downloadName);
		downloadCompanies.clickOnDownloadBtn();
		companyResultsPage.waitforLoadingRing();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.deleteMyDownload(downloadName);
	}
}

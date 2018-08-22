package com.ddaqe.dgn_company_saved_search;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;

@Listeners(TestListener.class)

public class DGNCompanySavedSearch extends DGNCompanySavedSearchDataSet {
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

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1779(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		Assert.assertTrue(saveSearchPopUp.isCancelBtnDisplayed());
		Assert.assertTrue(saveSearchPopUp.isDoNotEmailRadioBtnChecked());
		Assert.assertTrue(saveSearchPopUp.isSummaryRadioBtnClicked());

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1781(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		Assert.assertTrue(saveSearchPopUp.isNameTextFieldInfocus());
		char[] charArray51 = new char[51];
		Arrays.fill(charArray51, 'a');
		String searchName51Char = new String(charArray51);
		saveSearchPopUp.enterName(searchName51Char);
		Assert.assertEquals(saveSearchPopUp.lengthofNameText(), 50);
		saveSearchPopUp.enterName("^^&&dfd");
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.checkforValidationMessage(
				"Please use only alphas (a-z, A-Z), numbers (0-9), underscores (_) and spaces ( ) for the saved search name. "));

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1799(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		List<String> resultsDropdownExpectedValues = Arrays.asList("10", "25", "50", "100", "500");
		List<String> resultsDropdownActualValues = savedSearchesPage.VerifyResultDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);
		savedSearchesPage.selectResultPerPageDisplayed("100");
		savedSearchesPage.clickOnProjectLink();
		Assert.assertEquals(savedSearchesPage.getResultDropdownSelectedValue(), "100");

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1784(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		Assert.assertTrue(saveSearchPopUp.isNameTextFieldInfocus());
		Assert.assertTrue(saveSearchPopUp.isSaveSearchCategoryPrivateforNonAdmin());

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1785(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		Assert.assertTrue(saveSearchPopUp.isNameTextFieldInfocus());
		saveSearchPopUp.clickOnCancelBtn();
		Assert.assertFalse(saveSearchPopUp.isSaveSearchPopupDisplayed());

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertc1789", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1789(String emailAddress, String password, String multipleemail) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);

		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterEmailText(multipleemail);
		saveSearchPopUp.clickSave();

		Assert.assertTrue(saveSearchPopUp.checkforValidationMessage("Only 20 E-Mails can be added in alert list."));
		saveSearchPopUp.enterEmailText(emailAddress);
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.checkforValidationMessage(
				"Email Updates: Your Email address cannot be an alternate address ... Please delete"));
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1791_tc1802(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		Assert.assertTrue(
				savedSearchesPage.verifyShareLinkNotVisible("Non Admin, Company_Saved_Searc", "public", "Share"));
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1792_tc1800(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		
		projectResultsPage.checkHideFilterShowWithFilters();

		companyResultsPage.clickOnCompanyLocationFilter();
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnUKCountryChkbox();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		//projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");

		projectResultsPage.waitforLoadingRing();

		int CountAfter = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		companyResultsPage.clickPowerRank();
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSavePowerRank();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		myAccount.clickOnSavedSearchMenuLink();

		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.verifyPowerRankedSearchIcon(searchName);
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Company Results");
		int CountAfterSSLoad = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(CountAfter, CountAfterSSLoad);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1793(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfter = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Company Results");
		int CountAfterSSLoad = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(CountAfter, CountAfterSSLoad);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1794_tc1815(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		int CountAfter = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSavePowerRank();
		//saveSearchPopUp.ClickOkOnAlertBox();
		companyResultsPage.waitforLoadingPowerRank();
		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		projectResultsPage.waitforLoadingRing();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Company Results");
		int CountAfterSSLoad = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(CountAfter, CountAfterSSLoad);
		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		projectResultsPage.waitforLoadingRing();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.editPowerRankedSearch(searchName);

		saveSearchPopUp.clickSavePowerRank();
		saveSearchPopUp.ClickOkOnAlertBox();
		companyResultsPage.waitforLoadingPowerRank();

		savedSearchesPage.selectTypeListOption("Public");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1795(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		//projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		int CountAfter = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSavePowerRank();

		homePage.clickOnMyAccountLink();
		homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Company Results");
		int CountAfterSSLoad = companyResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(CountAfter, CountAfterSSLoad);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1807(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		String saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		savedSearchesPage.clickOnDeleteSaveSearch();
		Assert.assertFalse(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is not successfully done.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1804(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		List<String> resultsDropdownExpectedValues = Arrays.asList("All", "Private", "Public", "Shared");
		List<String> resultsDropdownActualValues = savedSearchesPage.VerifyTypeDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);
		Assert.assertEquals(savedSearchesPage.getTypeDropdownSelectedValue(), "All");
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.verifyTypeOfSavedSearchDisplayed("Private");
		savedSearchesPage.selectTypeListOption("Public");
		savedSearchesPage.verifyTypeOfSavedSearchDisplayed("Public");
		savedSearchesPage.selectTypeListOption("Shared");
		savedSearchesPage.verifyTypeOfSavedSearchDisplayed("Shared");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1805(String emailAddress, String password) throws InterruptedException {
		//testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		List<String> resultsDropdownExpectedValues = Arrays.asList("All", "Private", "Public", "Shared");
		List<String> resultsDropdownActualValues = savedSearchesPage.VerifyTypeDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);
		Assert.assertEquals(savedSearchesPage.getTypeDropdownSelectedValue(), "All");
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.deleteSavedSearchesPostTest();
		Assert.assertTrue(
				savedSearchesPage.isMessageDisplayedOnPage("You do not have any private company saved searches"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1806(String emailAddress, String password) throws InterruptedException {
		
		createPrivateCompanySaveSearch(emailAddress, password);
		
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "Private", "Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "Shared",
				"Edit");

		saveSearchPopUp.clickOnCancelBtn();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "private", "Share");
		Assert.assertEquals(savedSearchesPage.getUsersDropdownSelectedValue(), "All");
		savedSearchesPage.clickOnActionsDropdown();
		Assert.assertTrue(savedSearchesPage.isShareUnderActionsDisplayed());
		Assert.assertTrue(savedSearchesPage.isUnshareUnderActionsDisplayed());

		savedSearchesPage.clickOnSelectallCheckBox();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.selectUserType("Shared");
		savedSearchesPage.clickOnActionsDropdown();
		Assert.assertFalse(savedSearchesPage.isShareUnderActionsDisplayed());
		Assert.assertTrue(savedSearchesPage.isUnshareUnderActionsDisplayed());
		savedSearchesPage.clickOnSelectallCheckBox();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnUnShareUnderActions();
		savedSearchesPage.selectUserType("Not Shared");
		savedSearchesPage.clickOnActionsDropdown();
		Assert.assertTrue(savedSearchesPage.isShareUnderActionsDisplayed());
		Assert.assertFalse(savedSearchesPage.isUnshareUnderActionsDisplayed());
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1808(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		String saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		savedSearchesPage.clickOnDeleteLink();
		savedSearchesPage.clickCancelOnalert();
		Assert.assertTrue(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is done successfully ");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1810(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "private",
				"Edit");
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickCancelOnAlertBox();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1812(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		saveSearchPopUp.verifySavedSearchTypeDropdownValues();
		List<String> resultsDropdownActualValues = saveSearchPopUp.verifySavedSearchTypeDropdownValues();
		Assert.assertTrue(resultsDropdownActualValues.toString().contains("Private"));
		Assert.assertTrue(resultsDropdownActualValues.toString().contains("Public"));

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1816_tc_1820_tc1821(String emailAddress, String password) throws InterruptedException {
		//testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "Share",
				"Edit");


		String searchName = saveSearchPopUp.getSavedSearchName();
		saveSearchPopUp.clickOnCancelButtonEditSaveSearch();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "private", "Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();

		savedSearchesPage.selectTypeListOption("Shared");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1814(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "private",
				"Edit");
		String searchName = saveSearchPopUp.getSavedSearchName();
		saveSearchPopUp.clickOnCancelButtonEditSaveSearch();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "private", "Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();
		savedSearchesPage.selectTypeListOption("Shared");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1822(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "private",
				"Edit");
		String searchName = saveSearchPopUp.getSavedSearchName();
		saveSearchPopUp.clickOnCancelButtonEditSaveSearch();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "private", "Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();
		savedSearchesPage.selectTypeListOption("Shared");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1817(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "Private",
				"Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();

		savedSearchesPage.performUserAction("User, C_Saved_Search", "Shared", "Edit");

		String searchName = saveSearchPopUp.getSavedSearchName();
		saveSearchPopUp.clickOnCancelButtonEditSaveSearch();
		savedSearchesPage.performUserAction("User, C_Saved_Search", "private", "Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();

		savedSearchesPage.selectTypeListOption("Shared");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc1818(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myaccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myaccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp saveSearchPopUp = savedSearchesPage.performUserAction("User, C_Saved_Search", "private",
				"Share");
		savedSearchesPage.clickFirstUserToShare();
		savedSearchesPage.clickOnActionsDropdown();
		savedSearchesPage.clickOnShareUnderActions();
		savedSearchesPage.clickOnDoneBtn();

		savedSearchesPage.performUserAction("User, C_Saved_Search", "shared", "Edit");
		String searchName = saveSearchPopUp.getSavedSearchName();
		saveSearchPopUp.selectSearchType("Public");
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		savedSearchesPage.selectTypeListOption("Public");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1826(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("Home Depot");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		homePage.enterSearchText("wendy");
		homePage.clickOnSearchButton();
		homePage.clickOnSaveSearchButtonCompany();
		saveSearchPopUp.enterName(" " + searchName + "new");
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName + "new");
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify Updated branding in the footer of Project Save search  page (TC17739)")
	public void tc1827(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		Assert.assertTrue(savedSearchesPage.isfooterLogoDisplayed());
		Assert.assertEquals(savedSearchesPage.getfooterLogoText(), "Dodge Data & Analytics");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of the Revision of  the note on the newly created project saved search dialog regarding who can receive the Summary and Capsule email formats (TC17863)")
	public void tc1830(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		Assert.assertEquals(saveSearchPopUp.getNoteText(),
				"Note: Summary or Capsule can only be delivered to users on the same license");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1833(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("User, C_Saved_Search", searchName, "Summary",
				"Daily");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1834(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickOnLimitedRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("User, C_Saved_Search", searchName, "Limited",
				"Daily");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1835(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnWeeklyRadioBtn();
		saveSearchPopUp.clickOnLimitedRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("User, C_Saved_Search", searchName, "Limited",
				"Monday");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1836(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("User, C_Saved_Search", searchName, "", "");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1843(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		homePage.clickOnSaveSearchButtonCompany();
		saveSearchPopUp.enterName(" " + searchName);
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickCancelOnAlertBox();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1842(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		homePage.clickOnSaveSearchButtonCompany();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickCancelOnAlertBox();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanySavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProvidertcAdmin", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc1844(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("quest");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		companyResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(companyResultsPage.isDownLoadCompaniesMenuDisplayed());
		Assert.assertTrue(companyResultsPage.isPrintCompanyListDisplayed());

	}

}

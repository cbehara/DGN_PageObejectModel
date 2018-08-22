package com.ddaqe.dgn_project_saved_search;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_project_search.ProjectSearchDataProvider;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TechSupportPage;
import com.ddaqe.pages.UserInformationPage;

@Listeners(TestListener.class)

public class DGNProjectSavedSearch extends BaseTest {
	static Logger log = Logger.getLogger(DGNProjectSavedSearch.class);

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
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Test Data cleanup script")
	public void AA_testdataScriptProject(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Public");
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Test Data cleanup script")
	public void AB_testdataScriptCompanies(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Test Data cleanup script")
	public void AC_testdataScriptCompaniesNonAdmin(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Test Data cleanup script")
	public void AD_testdataScriptProjectsNonAdmin(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Redesigned Dodge Report (TC1478)")
	public void tc920(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithValuationContainChar();
		projectPage.mouseOverValuationTxtField();
		Assert.assertEquals(projectPage.valuationToolTipTitle(), "Dodge Estimated Valuation codes");
		Assert.assertEquals(projectPage.countofLinksOnValuationtooltip(), 0);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Update saved this (new) search to use modal dialog and add email notifications - ui only (TC2241)")
	public void tc921(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		Assert.assertTrue(saveSearchPopUp.isNameTextFieldInfocus());
		Assert.assertTrue(saveSearchPopUp.isDoNotEmailRadioBtnChecked());
		Assert.assertTrue(saveSearchPopUp.isSummaryRadioBtnClicked());
		Assert.assertTrue(saveSearchPopUp.isSaveBtnDisplayed());
		Assert.assertTrue(saveSearchPopUp.isCancelBtnDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Allow user to save a search even if no criteria is changed yet (to enable copy/save as) (TC2413)")
	public void tc928(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName1);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		String searchName2 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName2);
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName2));

		savedSearchesPage.deleteSavedSearches();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Extend max saved search name length - To Verify the MAX Saved search name user can Enter (TC7134)")
	public void tc929(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		char[] charArray51 = new char[51];
		Arrays.fill(charArray51, 'a');
		String name51Char = new String(charArray51);

		char[] charArray50 = new char[50];
		Arrays.fill(charArray50, 'b');
		String name50Char = new String(charArray50);

		char[] charArray49 = new char[49];
		Arrays.fill(charArray49, 'c');
		String name49Char = new String(charArray49);

		saveSearchPopUp.enterName(name51Char);
		Assert.assertEquals(saveSearchPopUp.lengthofNameText(), 50);

		saveSearchPopUp.enterName(name49Char);
		Assert.assertEquals(saveSearchPopUp.lengthofNameText(), 49);

		saveSearchPopUp.enterName(name50Char);
		Assert.assertEquals(saveSearchPopUp.lengthofNameText(), 50);

		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		Assert.assertFalse(saveSearchPopUp.isSaveSearchPopupDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[Miscellaneous] Migration: Strip out tracking list/SpecAlerts criteria when 'exclude' filter is on when migrating project saved searches- To Verify the Saved searchs which have Include/Exculde saved searchs (TC12136)")
	public void tc930(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed());
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GY:C2565309] The “Plan Sheet Class” & “Spec Division” filter should not disappear when a “Plans Only Search”  is saved by the user (TC14327)")
	public void tc931(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isDisplayedPlanclassSheetFilter());
		Assert.assertTrue(projectResultsPage.isDisplayedSpecDivisionFilter());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[GZ:I7222815] User get an Internal Server Error when re-saving/renaming a company saved search (TC14329)")
	public void tc932(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopup = savedSearchesPage.clickEditLink(1);

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		savedSearchPopup.enterName(searchName);
		savedSearchPopup.clickSave();
		Assert.assertFalse(savedSearchPopup.isSaveSearchPopupDisplayed());
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying workflow for a new save search for project (TC14528)")
	public void tc933(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying workflow for a new  save search for Company (TC14529)")
	public void tc934(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("door");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying enable of Save as New buton when search name is changed for project (TC14530)")
	public void tc935(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for company with  non existing new name clicking save option (TC14536)")
	public void tc936(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());
		Assert.assertFalse(saveSearchPopUp.isSaveDisabled());
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for company with an existing name clicking save option (TC14538)")
	public void tc937(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());
		Assert.assertFalse(saveSearchPopUp.isSaveDisabled());
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with existing name clicking save option (TC14539)")
	public void tc938(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		Assert.assertSame(saveSearchPopUp.isDoNotEmailRadioBtnChecked(), DoNotEmailCheckStatusAfter);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for company without changinh its name clicking save option. (TC14542)")
	public void tc939(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		homePage.clickOnSaveSearchButtonCompany();
		Assert.assertSame(saveSearchPopUp.isDoNotEmailRadioBtnChecked(), DoNotEmailCheckStatusAfter);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying disable of Save as New buton when search name is unchanged for project (TC14719)")
	public void tc940(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String existingSearchName = saveSearchPopUp.getSavedSearchName();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());
		saveSearchPopUp.enterName(newsearchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());
		saveSearchPopUp.enterName(existingSearchName);
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying enable of Save as New buton when search name is changed for company (TC14720)")
	public void tc941(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());
		saveSearchPopUp.enterName(newsearchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verifying disable of Save as New buton when search name is unchanged for company (TC14721)")
	public void tc942(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		String existingSearchName = saveSearchPopUp.getSavedSearchName();

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());
		saveSearchPopUp.enterName(newsearchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());
		saveSearchPopUp.enterName(existingSearchName);
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with  non existing new name clicking save option (TC14722)")
	public void tc943(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		Assert.assertFalse(saveSearchPopUp.isSaveAsNewDisabled());
		Assert.assertFalse(saveSearchPopUp.isSaveDisabled());
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with out changing its name clicking save option. (TC14723)")
	public void tc944(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		Assert.assertSame(saveSearchPopUp.isDoNotEmailRadioBtnChecked(), DoNotEmailCheckStatusAfter);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with existing name clicking Save as New option (TC14724)")
	public void tc945(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for company with existing name clicking Save as New option (TC14725)")
	public void tc946(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);
		Assert.assertTrue(saveSearchPopUp.isSaveAsNewDisabled());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with  non existing new name clicking Save as New option (TC14726)")
	public void tc947(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);

		saveSearchPopUp.clickSave();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		Assert.assertSame(saveSearchPopUp.isDoNotEmailRadioBtnChecked(), DoNotEmailCheckStatusAfter);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for company with  non existing new name clicking Save as New option (TC14728)")
	public void tc948(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		Boolean DoNotEmailCheckStatusBefore = saveSearchPopUp.isDoNotEmailRadioBtnChecked();
		if (DoNotEmailCheckStatusBefore) {

			saveSearchPopUp.clickOnDailyRadioBtn();
		} else {
			saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		}
		Boolean DoNotEmailCheckStatusAfter = saveSearchPopUp.isDoNotEmailRadioBtnChecked();

		Assert.assertNotEquals(DoNotEmailCheckStatusBefore, DoNotEmailCheckStatusAfter);

		String newsearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(newsearchName);

		saveSearchPopUp.clickSave();

		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(newsearchName);
		Assert.assertEquals(companyResultsPage.getTitle(), "Dodge Global Network - Company Results");
		homePage.clickOnSaveSearchButtonCompany();
		Assert.assertSame(saveSearchPopUp.isDoNotEmailRadioBtnChecked(), DoNotEmailCheckStatusAfter);

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user can create save search after applying structural properties (TC16606)")
	public void tc951(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getFilterCrumbCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getFilterCrumbCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		String CountAfterSSLoad = projectResultsPage.getFilterCrumbCount();
		Assert.assertEquals(CountAfter, CountAfterSSLoad);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify Updated branding in the footer of Project Save search  page (TC17739)")
	public void tc952(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isfooterLogoDisplayed());
		Assert.assertEquals(savedSearchesPage.getfooterLogoText(), "Dodge Data & Analytics");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of the Revision of  the note on the newly created project saved search dialog regarding who can receive the Summary and Capsule email formats (TC17863)")
	public void tc953(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnCaliforniaState();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		Assert.assertEquals(saveSearchPopUp.getNoteText(),
				"Note: Summary or Capsule can only be delivered to users on the same license");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify While saving a public saved search by a non admin user, the system indicates it will save as a private search (TC18049)")
	public void tc954(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickPublicSavedSearch();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		Assert.assertTrue(saveSearchPopUp.isSaveSearchCategoryPrivateforNonAdmin());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify Editing of consecutive saved searches via the Edit link in the My Account section is working fine for Project (TC18051)")
	public void tc955(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopup = savedSearchesPage.clickEditLink(1);

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		savedSearchPopup.enterName(searchName);
		savedSearchPopup.clickSave();
		Assert.assertFalse(savedSearchPopup.isSaveSearchPopupDisplayed());
		savedSearchesPage.clickEditLink(2);
		String searchName2 = "Automation" + String.valueOf(new Date().getTime());
		savedSearchPopup.enterName(searchName2);
		savedSearchPopup.clickSave();
		Assert.assertFalse(savedSearchPopup.isSaveSearchPopupDisplayed());
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName2));

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify Keywords are saved when saving a modified saved search (TC18881)")
	public void tc956(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		projectResultsPage.clickOnCaliforniaState();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertEquals(homePage.getSearchText(), "wood");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Summary type daily email  alert settings displayed on the list of  project saved searches under my account When the user owns the search (TC19182)")
	public void tc957(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickOnSummaryRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Summary",
				"Daily");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Summary type specific day  email alert settings displayed on the list of project saved searches under my account When the user owns the search (TC19183)")
	public void tc958(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnWeeklyRadioBtn();
		saveSearchPopUp.clickOnSummaryRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Summary",
				"Monday");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Limited  type daily email alert settings displayed on the list of project saved searches under my account When the user owns the search (TC19186)")
	public void tc959(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickOnLimitedRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Limited",
				"Daily");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Limited type specific day email alert settings displayed on the list of project saved searches under my account When the user owns the search (TC19187)")
	public void tc960(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnWeeklyRadioBtn();
		saveSearchPopUp.clickOnLimitedRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Limited",
				"Monday");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Capsule  type daily email alert settings displayed on the list of project saved searches under my account When the user owns the search (TC19188)")
	public void tc961(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickOnCapsuleRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Capsule",
				"Daily");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Capsule  type specific day email alert settings displayed on the list of project saved searches under my account When the user owns the search (TC19189)")
	public void tc962(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnWeeklyRadioBtn();
		saveSearchPopUp.clickOnCapsuleRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Capsule",
				"Monday");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of  list of project saved searches under my account When the user owns the search but email alert not set for the search (TC19190)")
	public void tc963(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "", "");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of list of project saved searches under my account When the user does not owns the search (TC19191)")
	public void tc964(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForNotOwnedSavedSearches("1, Auto_Plati_Ad", searchName, "", "");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[Project Saved Search] Saved search should not get saved when we click on  CANCEL button on confirmation popup if the name is same (TC20972)")
	public void tc965(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.isAlertPresent());
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickCancelOnAlertBox();
		Assert.assertTrue(saveSearchPopUp.isSaveSearchPopupDisplayed());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[Project Saved Search] Saved search should get saved when we click on  OK button on confirmation popup if the name is same (TC20973)")
	public void tc966(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.isAlertPresent());
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickOkOnAlertBox();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		Assert.assertEquals(saveSearchPopUp.getSavedSearchName(), searchName);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify pop up message is displayed while trying to save a saved search with an already existing Saved Search name for Projects (TC21258)")
	public void tc969(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.isAlertPresent());
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify saved search is over written while saving with the same name for Projects (TC21260)")
	public void tc970(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickOnDoNotEmailRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.isAlertPresent());
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
		saveSearchPopUp.ClickOkOnAlertBox();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		Assert.assertTrue(saveSearchPopUp.isDoNotEmailRadioBtnChecked());

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify pop up message is displayed while trying to save a saved search with an already existing Saved Search name but with extra space in beginning for Projects (TC21261)")
	public void tc971(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.enterName(" " + searchName);
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickSave();
		Assert.assertTrue(saveSearchPopUp.isAlertPresent());
		Assert.assertTrue(saveSearchPopUp
				.verifyTextOnAlert("A saved search with this name already exists.  Do you want to overwrite ?"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Verify Profile criteria is changed & saved according to Turn Off/Turn On selection in saved search (TC21306)")
	public void tc972(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		projectResultsPage.clickOnProfileFilter();
		projectResultsPage.clickTurnOffProfile();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isTurnOnProfileLinkDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "A new button called Save Search is added next to keyword search in Project Dashboard page (TC22196)")

	public void tc973(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		Assert.assertTrue(projectResultsPage.isSaveSearchButtonDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "A new button called Save Search is added next to keyword search in Company Dashboard page (TC22197)")
	public void tc974(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.isSaveSearchButtonDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Clicking the Save Search button brings a popup as per current DGN implementation. (TC22200)")
	public void tc976(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		saveSearchPopUp.isSaveSearchPopupDisplayed();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "[SAVED SEARCH] Verify the column header in the table should be Click on Saved Search Link to View Results (TC22264)")
	public void tc977(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertEquals(savedSearchesPage.getFirstColumnHeaderText(), "Click on Saved Search Link to View Results");
		savedSearchesPage.clickOnCompanyTab();
		Assert.assertEquals(savedSearchesPage.getFirstColumnHeaderText(), "Click on Saved Search Link to View Results");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify the different option present under Action button are working fine on Project Saved Search result page. (TC22688)")
	public void tc978(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed());
		savedSearchesPage.clickOnfirstProjectSavedSearch();

		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckDownloadProjectsDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckEmailProjectsDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckHideProjectsDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckPrintProjectDetailsDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckPrintProjectListDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandChecktrackProjectsDisplayed());
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckViewProjectsDisplayed());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify the saved project spec search can be retrieved and executed with the same Spec Group/Spec Division search criteria. (TC23484)")
	public void tc985(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(4, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.SwitchToParent();
		projectResultsPage.SwitchToActiveElement();
		int filterCrumbBefore = projectResultsPage.getAppliedFilterCount();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		int filterCrumbAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(filterCrumbBefore, filterCrumbAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify the saved project spec search can be updated with changes to Spec Group/Spec Division search criteria.  (TC23485)")
	public void tc986(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(4, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.SwitchToParent();
		projectResultsPage.SwitchToActiveElement();
		int filterCrumbBefore = projectResultsPage.getAppliedFilterCount();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		int filterCrumbAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(filterCrumbBefore, filterCrumbAfter);
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(6, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.SwitchToParent();
		projectResultsPage.SwitchToActiveElement();
		int filterCrumbUpdatedBefore = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(filterCrumbAfter, filterCrumbUpdatedBefore);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify the saved project spec search can be updated with changes to Spec Group/Spec Division search criteria.  (TC23485)")
	public void tc987(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(4, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.SwitchToParent();
		projectResultsPage.SwitchToActiveElement();
		int filterCrumbBefore = projectResultsPage.getAppliedFilterCount();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		int filterCrumbAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(filterCrumbBefore, filterCrumbAfter);
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(6, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.SwitchToParent();
		projectResultsPage.SwitchToActiveElement();
		int filterCrumbUpdatedBefore = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(filterCrumbAfter, filterCrumbUpdatedBefore);
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		int filterCrumbUpdatedAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(filterCrumbUpdatedBefore, filterCrumbUpdatedAfter);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verify user is updating a save search for project with  non existing new name clicking save option (TC14722)")
	public void tc3673(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertEquals(projectResultsPage.getSavedSearchNameinLeftNav(), searchName);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommon", description = "Verification of Summary type daily email  alert settings displayed on the list of  project saved searches under my account When the user owns the search (TC19182)")
	public void tc3948(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.clickOnDailyRadioBtn();
		saveSearchPopUp.clickOnSummaryRadioBtn();
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.verifyFormatAndFrequencyForOwnSavedSearches("1, Auto_Plati_Ad", searchName, "Summary",
				"Daily");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verify if the content-scope for an existing saved project search with content-scope design alert can be updated to include content scope value.")
	public void tc3069(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.deselect_FindInLHSCheckboxList();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("DESIGNALERTS", true);
		SavedSearchPopUp savedSearchPopUp = projectResultsPage.clickOnSavedSearch();
		String searchName = "Automation" + String.valueOf(new Date().getTime());
		savedSearchPopUp.enterName(searchName);
		savedSearchPopUp.clickSave();

		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		projectResultsPage = savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", true);
		projectResultsPage.selectDeselectFindInLHSCheckboxList("Plans", true);
		savedSearchPopUp = projectResultsPage.clickOnSavedSearch();
		savedSearchPopUp.clickSave();
		savedSearchPopUp.ClickOkOnAlertBox();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.getFindInLHSCheckboxStatus("News"), "News check box is not selected.");
		Assert.assertTrue(projectResultsPage.getFindInLHSCheckboxStatus("Plans"), "Plans check box is not selected.");
		Assert.assertTrue(projectResultsPage.getFindInLHSCheckboxStatus("DESIGNALERTS"),
				"Design Alerts check box is not selected.");

		homePage.clickOnMySearchesDropDown();
		savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearch(searchName);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "TC3049", description = "Verify the Design Alert Content scope of a saved search is displayed properly.")
	public void tc3049(String username, String password, String URL, String userName)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterSSOUserIDTxt(userName);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.getSavedSearchNameList().size() > 1,
				"User saved search are displayed in tech support under user information page.");
	}
}

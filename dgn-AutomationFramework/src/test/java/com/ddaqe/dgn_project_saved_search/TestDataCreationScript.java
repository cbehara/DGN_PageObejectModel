package com.ddaqe.dgn_project_saved_search;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;

@Listeners(TestListener.class)

public class TestDataCreationScript extends BaseTest {

	static Logger log = Logger.getLogger(TestDataCreationScript.class);

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
	public void testdataScriptProject(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
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
	public void testdataScriptCompanies(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Test Data cleanup script")
	public void testdataScriptCompaniesNonAdmin(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectSavedSearchDataProvider.class, dataProvider = "DGNProjectSavedSearchDataProviderCommonNonAdmin", description = "Test Data cleanup script")
	public void testdataScriptProjectsNonAdmin(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();

	}
}

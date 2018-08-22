package com.ddaqe.dgn_cross_search_power_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNCrossSearchPowerRanking extends BaseTest {
	static Logger log = Logger.getLogger(DGNCrossSearchPowerRanking.class);

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
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc690(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		homePage.clickOnSignOutButton();

	}

	public void DGNT530(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		Thread.sleep(10000);
		companyResultsPage.clickPowerRank();
		companyResultsPage.Check_ResultCountLessThan_10000(10000);
		Thread.sleep(10000);
		Assert.assertEquals(companyResultsPage.getPaginationSectionText(), "POWER RANKED RESULTS");
		companyResultsPage.clickPowerRank();
		Assert.assertNotSame(companyResultsPage.getPaginationSectionText(), "POWER RANKED RESULTS");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of clear all for Company location filter.")
	public void DGNT662(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("door");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		companyResultsPage.Check_ResultCountLessThan_10000(10000);

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of clear all for Company location filter.")
	public void DGNT663(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");

		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();

		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		List<String> optionList = new ArrayList<String>();
		optionList.add("Ranked # of Projects - Ascending");
		optionList.add("Ranked # of Projects - Descending");
		optionList.add("Total Valuation - Ascending");
		optionList.add("Total Valuation - Descending");

		List<String> verifyfilterList = companyResultsPage.verifySortDropDown();
		Assert.assertTrue(verifyfilterList.containsAll(optionList), "all options are not present in sort drop Down");

	}

	public boolean isListSorted(List<String> actualList, boolean isAscending) {
		List<String> copiedList = new ArrayList<String>();
		copiedList.addAll(actualList);
		if (isAscending) {
			Collections.sort(copiedList);
		} else {
			Collections.sort(copiedList, Collections.reverseOrder());
		}
		return copiedList.equals(actualList);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of clear all for Company location filter.")
	public void DGNT523(String emailAddress, String password) throws InterruptedException {

		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.checkClearAllSearchIsDisplayed(), "clearAll text is not displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "[Cross Search & Power Ranking] - Verify the power ranking informational UI element (TC10261)")
	public void DGNT497(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnPowerRankResultBtn();
		Assert.assertTrue(companyResultsPage.isPowerrankModalMessagedialogBoxDisplayed());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of clear all for Project Type  filter . (TC23159)")
	public void DGNT583(String emailAddress, String password) {

		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("door");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.Check_ResultCountLessThan_10000(0);
		companyResultsPage.clickOnClearAllSearch();
		Assert.assertFalse(companyResultsPage.checkClearAllSearchIsDisplayed(), "Clear All Search is cleared");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the Clear All functionality of  Company State/Country filter. (TC23100).")
	public void DGNT531(String emailAddress, String password) {

		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void DGNT522(String emailAddress, String password) {

		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC788(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		Assert.assertTrue(companyResultsPage.checkClearAllSearchIsDisplayed());

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc689(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();

		companyResultsPage.clickPowerRank();
		projectResultsPage.ClickOncrumbFilterClose(0);
		int count = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(count, 1);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc692(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		
		goToBackPage();
		Assert.assertTrue(companyResultsPage.checkSortDropdownNotEmpty(), "Sort Order dropdown should not blank");

		companyResultsPage.waitforLoadingRing();
		// User clicks on Project Ownership Type filter checkbox
		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjOwnershipTypeCheckbox();
		// homePage.clickOnSignOutButton();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");

		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");

		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();

		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");

		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc695_696_697_698_700_702(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		// ProjectResultsPage projectResultsPage =
		// homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");

		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");

		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();

		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");

		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc704_705_706(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);

	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc717(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCompanyTypeFilter();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.SelectOptionsFromTheList(1, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		Assert.assertEquals(companyResultsPage.getFilterCrumb_AppliedFilterList_Size(), 1);
		companyResultsPage.waitforLoadingRing();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc718(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCompanyTypeFilter();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.SelectOptionsFromTheList(4, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() > 0);
		companyResultsPage.waitforLoadingRing();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc720_721_722(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnCompanyTypeFilter();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.SelectOptionsFromTheList(1, "CompanyTypeGrp_LHS_ParentFilterList");

		companyResultsPage.waitforLoadingRing();
		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc724_726_727_728_730_731_741_742(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(3, "ProjectLocation_LHS_ParentFilterList");

		companyResultsPage.waitforLoadingRing();

		//projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
			
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());

		projectResultsPage.ClickOnFilterpopClose();

		Assert.assertEquals(companyResultsPage.getFilterCrumb_AppliedFilterList_Size(), 1);
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc745(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnActionStageFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc746_747_749_750_751(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		//projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc753_755_756_757_759_760_761(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		//projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();

		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc763_764_765_766_768_769_770(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getAppliedFilterCount(), 3);

		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingRing();

		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		companyResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(4, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		//projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc772_773_774_775_777_778(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(3, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getAppliedFilterCount(), 3);

		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingRing();

		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		companyResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(4, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		//projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();

		// Verify user count is lesser than 10,000
		int resultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(resultCount <= 10000, "Error: Result count more than 10,000!");
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingPowerRank();
		// Verify Page is Power Ranked now
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		companyResultsPage.waitforLoadingPowerRank();
		Assert.assertTrue(companyResultsPage.getFilterCrumb_AppliedFilterList_Size() == 0);
	}

	@Test(groups = { "Regression",
			"Normal" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the display section for applied( Structural Properties ) filter at the filter crumb area, below the keyword search section for more than 3 selections (TC23180)")
	public void DGNT782(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		// User selects more than 3 check boxes as filters
//		companyResultsPage.clickOnProjOwnershipTypeCheckbox();
		companyResultsPage.clickOnCanadaCompanyLocationCheckbox();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.clickOnDesignProjActionStageCheckbox();
		// Verify Page is not Power Ranked yet
		String hoverTextNonPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking, "Power Rank Results", "Unexpected Power Rank Button Hover text!");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of clear all for Company location filter.")
	public void TC23(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingPowerRank();
		companyResultsPage.clickOnClearAllSearch();
		Assert.assertFalse(companyResultsPage.checkClearAllSearchIsDisplayed(), "clearAll text is not displayed");
		companyResultsPage.clickPowerRank();
		Assert.assertFalse(companyResultsPage.checkClearAllSearchIsDisplayed());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC787(String emailAddress, String password) throws InterruptedException {
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verification of Clear the filters  from power ranked result set")
	public void tc747(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));
		companyResultsPage.clickOnClearAllSearch();
		Assert.assertEquals(projectResultsPage.getFilterCrumb_AppliedFilterList_Size(), 0);

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the addition of new primary company type filter")
	public void TC678(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.IsProjectLocationFilter_Displayed());
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(1, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnPowerRankResultBtn();
		companyResultsPage.clickOnCompanyProjectsLink();

		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify Plus level user is not able to see the cross search project filters")
	public void TC687(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		ProjectResultsPage companyResultPageObj = HomePage.GetProjectResultsPageObjects();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		String CompanyLocationSearch = companyResultPageObj.getAppliedcrumb_txt(0);
		Assert.assertEquals(CompanyLocationSearch, "United Kingdom");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC786(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC785(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));
		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		String hoverTextLeftNavAfterPowerRanked = companyResultsPage.getLeftNavHoverTextAfterPowerRanked();
		Assert.assertEquals(hoverTextLeftNavAfterPowerRanked, "Power ranked results cannot be filtered",
				"Unexpected Power Rank Button Hover text!");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "[Cross Search & Power Ranking] - Verify the power ranking informational UI element (TC10261)")
	public void TC672(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnPowerRankResultBtn();
		Assert.assertTrue(companyResultsPage.isPowerrankModalMessagedialogBoxDisplayed());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "To verify that the project filters are still enabled on filtering with company filter criteria")
	public void TC673(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		HomePage.clickOnCompaniesLink();

		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		ProjectResultsPage companyResultPageObj = HomePage.GetProjectResultsPageObjects();

		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		int ResultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(ResultCount));
		Assert.assertTrue(companyResultsPage.IsProjectLocationFilter_Displayed());
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(1, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		String CompanyLocationSearch = companyResultPageObj.getAppliedcrumb_txt(0);
		Assert.assertEquals(CompanyLocationSearch, "United Kingdom");
		String ProjectLocationSearchFilter = companyResultPageObj.getAppliedcrumb_txt(1);
		Assert.assertEquals(ProjectLocationSearchFilter, "Canada");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the addition of new primary company type filter")
	public void TC674(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyTypeFilter();
		Assert.assertTrue(companyResultsPage.IsPrimarycompanyType_Displayed());
		Assert.assertTrue(companyResultsPage.Is_PrimaryCompany_Type_cbk_Selected());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify Plus level user is not able to see the cross search project filters")
	public void TC732(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.waitforLoadingRing();
		int ResultCount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(ResultCount));
		Assert.assertTrue(companyResultsPage.checkClearAllSearchIsDisplayed());
		companyResultsPage.clickOnClearAllSearch();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the collapsed and disabled mode of Project State/County filter.")
	public void TC734(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		companyResultsPage.clickOnUKCountryChkbox();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(1, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickPowerRank();
		companyResultsPage.waitforLoadingRing();
		int ResultCount1 = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(ResultCount1));
		Assert.assertEquals(companyResultsPage.getPaginationSectionPowerBankText(), "POWER RANKED RESULTS");
		Assert.assertTrue(companyResultsPage.isLeftSpecAlertCollapsedDefault());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the display section for applied(Project State/County) filter at the filter crumb area, below the keyword search section for less than 3 or 3 selections")
	public void TC736(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		ProjectResultsPage companyResultPageObj = HomePage.GetProjectResultsPageObjects();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		String CompanyLocationFilter1 = companyResultPageObj.getAppliedcrumb_txt(0);
		Assert.assertEquals(CompanyLocationFilter1, "Canada");
		String CompanyLocationFilter2 = companyResultPageObj.getAppliedcrumb_txt(1);
		Assert.assertEquals(CompanyLocationFilter2, "USA");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the display section for applied(Project State/County) filter at the filter crumb area, below the keyword search section for more than 3 selections")
	public void TC737(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		ProjectResultsPage companyResultPageObj = HomePage.GetProjectResultsPageObjects();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(3, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		String CompanyLocationFilter1 = companyResultPageObj.getAppliedcrumb_txt(0);
		Assert.assertEquals(CompanyLocationFilter1, "Project Geography - 67 Filters");
		companyResultPageObj.ClickOncrumbFirstFilterClose();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify the functionality of hyperlink present on the top for more than 3 slections Project State/County filter")
	public void TC738(String emailAddress, String password) throws InterruptedException {
		HomePage HomePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = HomePage.clickOnCompaniesLink();
		HomePage.GetProjectResultsPageObjects();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(3, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnFilterslinkinBreadcrumb();
		Assert.assertTrue(companyResultsPage.checkFilterslinkpopupIsDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC666(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));
		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();
		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC668(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.waitforLoadingRing();

		projectResultsPage.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumbAllCloseButton();
		projectResultsPage.clickOnFilterCrumbAllCloseButton();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumbAllCloseButton();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC669(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcountBefore = companyResultsPage.getExactCompanyResultCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		int resultcountAfter = companyResultsPage.getExactCompanyResultCount();
		Assert.assertNotEquals(resultcountAfter, resultcountBefore);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC670(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();

		Assert.assertTrue(companyResultsPage.IsProjectLocationFilter_Displayed());
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcountBefore = companyResultsPage.getExactCompanyResultCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		int resultcountAfter = companyResultsPage.getExactCompanyResultCount();
		Assert.assertNotEquals(resultcountAfter, resultcountBefore);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC680(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjPublishDateFilter();
		Assert.assertTrue(companyResultsPage.getPublishRange_DropDown_OptionText().contains("Last Quarter"));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNCrossSearchPowerRankingDataProvider.class, dataProvider = "DGNCrossSearchPowerRankingDataProviderCommon", description = "Verify clicking on the Remove ranking in the company ranking page.")
	public void TC686(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjPublishDateFilter();
		projectResultsPage.selectPublishRangeFromDropdown("Last Quarter");
		int resultcountBefore = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.getPublishRange_DropDown_OptionText().contains("Last Quarter"));
		projectResultsPage.selectPublishRangeFromDropdown("Last Year");
		int resultcountAfter = companyResultsPage.getExactCompanyResultCount();

	}

}

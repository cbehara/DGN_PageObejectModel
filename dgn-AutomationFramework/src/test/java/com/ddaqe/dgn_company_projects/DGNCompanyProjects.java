package com.ddaqe.dgn_company_projects;

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
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;

@Listeners(TestListener.class)

public class DGNCompanyProjects extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanyProjects.class);

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

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify Applied filter section of company projects list view (TC15968)")
	public void tc1339(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.waitforLoadingRing();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		List<String> publishedDateRangeList = companyProjectsPage.getPublishedDateRangeList();
		boolean isVerified = true;
		for (String dateRange : publishedDateRangeList) {
			if (dateRange.contains("Last Quarter") || dateRange.contains("Last Year date")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified,
				"Published Date Range is not present displaying the Last Quarter or Last Year date.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify More Filter section of company projects list view (TC15969)")
	public void tc1340(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		List<String> filterList = companyProjectsPage.getFilterList();
		boolean isverified = false;
		for (String filter : filterList) {
			if (filter.contains("Geography") || filter.contains("Action Stage") || filter.contains("Project Types")
					|| filter.contains("Bidding Within") || filter.contains("Valuation")
					|| filter.contains("Construction Type") || filter.contains("Ownership Type")) {
				isverified = true;
			}
		}
		Assert.assertTrue(isverified, "all filters are not present in more filter.");
		companyProjectsPage.clickOnActionStageFilterSeeMoreButton();
		companyResultsPage.SwitchToFrame();
		companyProjectsPage.selectDesignOption();
		companyResultsPage.SwitchToParent();
		Assert.assertTrue(companyProjectsPage.getSelecetedFacetItemList().contains("Action Stage"),
				"Action Stage Type filter is present under Applied Filters section");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify no reset option should present in company projects list view (TC15970)")
	public void tc1341(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyProjectsPage.isClearAllLinkInFilterDisplayed(),
				"Clearall filter Tab is not displayed");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify no Save Search option should present in company projects list view (TC15971)")
	public void tc1342(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertFalse(companyProjectsPage.isSaveSearchButtonDisplayed(),
				"Save Search option present in the left pannel.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verification  of project filters after executing a company cross search (TC17817)")
	public void tc1343(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjectGroupTab();
		companyResultsPage.clickOnCommercialcheckbox();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnEditlink();
		companyResultsPage.SwitchToFrame();
		companyProjectsPage.selectTransportOption();
		Assert.assertFalse(companyProjectsPage.isTransportOptionSelected(),
				"Transportation option is not able to select.");

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "[DGN] [GY:I6376161] Action stage filter not working on company projects page (TC18883)")
	public void tc1344(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnDesignProjActionStageCheckbox();
		companyResultsPage.clickOnPre_DesignProjActionStageCheckbox();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyProjectsPage.actionstagedispalyOnComapnyPage(),
				"Action Stage Type filter is present under Applied Filters section");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "TC1345", description = "Verify the Spec division filter is hidden on the on Company Projects page (TC23358)")
	public void tc1345(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnappropriateOptionFromSortDropDown(6);
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		List<String> filterList = companyProjectsPage.getFilterList();
		Assert.assertFalse(filterList.contains("SpecAlerts"), "Spen Alert Filter is present on companyResultPage.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "On Company Projects page, verify If a parent filter is selected then filter crumb is shown for the selected parent and not shown for any children (TC24118)")
	public void tc1347(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnActionStageFilterSeeMoreButton();
		companyResultsPage.SwitchToFrame();
		companyProjectsPage.clickDesignOptionOnPopup();
		Assert.assertTrue(companyProjectsPage.isActionStageFilterDisplayed(),
				"Action Stage Type filter is present under Applied Filters section");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "On Company Projects page, parent or child filter will be displayed based on the selection (TC24122)")
	public void tc1350(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.ClickOnProjectPopupStateLink();
		Assert.assertTrue(companyResultsPage.checkProjectPopupStateLink(),
				"Action Stage Type filter is present under Applied Filters section");

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "On Company Projects page, parent or child filter will be displayed based on the selection (TC24122)")
	public void tc1352(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> companyResultFilter = companyResultsPage.getFilterListCompanyResultPage();
		Assert.assertFalse(companyResultFilter.contains("Find In"),
				"Find LN is present displaying the Last Quarter or Last Year date.");
		Assert.assertFalse(companyResultFilter.contains("Publish Range"),
				"Published Range is  present displaying the Last Quarter or Last Year date.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify By default, filter crumb drawer is closed on Company Projects page (TC24129)")
	public void tc1353(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnProjectLocationFilter();
		companyResultsPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultsPage.clickOnDesignProjActionStageCheckbox();
		companyResultsPage.clickOnProjectGroupTab();
		companyResultsPage.clickOnCommercialcheckbox();
		List<String> expectedFilterCrumb = new ArrayList<String>();
		expectedFilterCrumb.add("Canada");
		expectedFilterCrumb.add("USA");
		expectedFilterCrumb.add("Commercial");
		Assert.assertEquals(companyResultsPage.getFilterCrumbDisplayed(), expectedFilterCrumb,
				"Expected filter Crumb is not Displayed");
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertFalse(companyProjectsPage.isFilterCrumbDisplayed(), "Filter Crumb is Displayed");

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify By default, filter crumb drawer open/closed setting is sticky (TC24133)")
	public void tc1354(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnProjectGroupTab();
		companyResultsPage.SelectOptionsFromTheList(5, "ProjectType_LHS_ParentFilterList");
		companyResultsPage.clickOnShowMore();
		Assert.assertTrue(companyResultsPage.isPopUpDisplayed(), "Show more control is not displayed");
		List<String> expectedFilterCrumb = new ArrayList<String>();
		expectedFilterCrumb.add("1 & 2 Family Homes");
		expectedFilterCrumb.add("Air Pollution Control");
		expectedFilterCrumb.add("Animal/Plant/Fish Facilities");
		expectedFilterCrumb.add("Apartments/Condominiums");
		expectedFilterCrumb.add("Bridges");
		Assert.assertEquals(companyResultsPage.getPopUpFilterCrumbDisplayed(), expectedFilterCrumb,
				"Expected filter Crumb is not Displayed");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Verify By default, filter crumb drawer open/closed setting is sticky (TC24133)")
	public void tc1355(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnappropriateOptionFromSortDropDown(6);
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		List<String> filterList = companyProjectsPage.getFilterList();
		Assert.assertFalse(filterList.contains("SpecAlerts"), "Spen Alert Filter is present on companyResultPage.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyProjectsDataProvider.class, dataProvider = "CompanyProjectsCommonDataProvider", description = "Company search results - Sorting (TC3426)")
	public void tc1366(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> optionList = new ArrayList<String>();
		optionList.add("Relevancy");
		optionList.add("Company Name - Ascending");
		optionList.add("Company Name - Descending");
		optionList.add("Location - Ascending");
		optionList.add("Location - Descending");
		optionList.add("Number of Projects - Ascending");
		optionList.add("Number of Projects - Descending");
		optionList.add("Project Valuation - Ascending");
		optionList.add("Project Valuation - Descending");

		List<String> verifyfilterList = companyResultsPage.verifySortDropDown();
		Assert.assertTrue(verifyfilterList.containsAll(optionList), "all options are not present in sort drop Down");
		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Company Name - Ascending");
		List<String> companyNameList = companyResultsPage.getSortCompanyName();
		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Number of Projects - Ascending");
		List<String> numberOfProjectList = companyResultsPage.getSortNumberOfProject();
		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Project Valuation - Ascending");
		List<String> valuationList = companyResultsPage.getSortProjectvaluation();

		Assert.assertFalse(isListSorted(companyNameList, true), "Company Name List is not sorted.");
		Assert.assertTrue(isListSorted(numberOfProjectList, true), "Number Of Project List is not sorted.");
		Assert.assertTrue(isListSorted(valuationList, true), "Valuation List is not sorted.");

		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Company Name - Descending");
		List<String> companyNameList1 = companyResultsPage.getSortCompanyName();

		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Company Name - Descending");
		List<String> numberOfProjectList1 = companyResultsPage.getSortCompanyName();

		companyResultsPage.clickOnSortDropDown();
		companyResultsPage.selectappropriateOptionFromSortDropDown("Company Name - Descending");
		List<String> valuationList1 = companyResultsPage.getSortCompanyName();

		Assert.assertFalse(isListSorted(companyNameList1, true), "Company Name List is not sorted.");
		Assert.assertFalse(isListSorted(numberOfProjectList1, true), "Number Of Project List is not sorted.");
		Assert.assertFalse(isListSorted(valuationList1, true), "Valuation List is not sorted.");

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

}

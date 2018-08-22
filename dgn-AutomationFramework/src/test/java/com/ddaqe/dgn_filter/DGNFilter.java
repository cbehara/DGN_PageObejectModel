package com.ddaqe.dgn_filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.dgn_project_filter.ProjectFilterDataProvider;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNFilter extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Test Data cleanup script")
	public void testdataScript(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc813(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Action Stage - Ascending");
		Assert.assertTrue(projectResultsPage.verifyactionStageSorting(true));
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), "Action Stage - Ascending");
		projectResultsPage.selectSortingOption("Action Stage - Descending");
		Assert.assertTrue(projectResultsPage.verifyactionStageSorting(false));
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), "Action Stage - Descending");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc812(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.selectSortingOption("Action Stage - Ascending");
		Assert.assertTrue(specAlertsResultsPage.verifyactionStageSorting(true));
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(specAlertsResultsPage.getSelectedSortOption(), "Action Stage - Ascending");
		specAlertsResultsPage.selectSortingOption("Action Stage - Descending");
		Assert.assertTrue(specAlertsResultsPage.verifyactionStageSorting(false));
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(specAlertsResultsPage.getSelectedSortOption(), "Action Stage - Descending");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc810(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Project Type - Ascending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(true));
		projectResultsPage.selectSortingOption("Project Type - Descending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(false));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc811(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		companyProjectsPage.selectSortingOption("Project Type - Ascending");
		Assert.assertTrue(companyProjectsPage.verifyProjectTypeSorting(true));
		companyProjectsPage.selectSortingOption("Project Type - Descending");
		Assert.assertTrue(companyProjectsPage.verifyProjectTypeSorting(false));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc809(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Project Type - Ascending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(true));
		projectResultsPage.selectSortingOption("Project Type - Descending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(false));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCBid", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc790(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc796(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectSortingOption("Company Name - Ascending");
		Assert.assertTrue(companyResultsPage.verifyProjectTypeSorting(true));
		companyResultsPage.selectSortingOption("Company Name - Descending");
		Assert.assertTrue(companyResultsPage.verifyProjectTypeSorting(false));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCFilters", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc817(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc803(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc800(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.clickOnPlanOpenInFindInFilter();
		Assert.assertTrue(projectResultsPage.isDisplayedPlanclassSheetFilter(),
				"Plan Class sheet filter is not present.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCConstruction", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc802(String emailAddress, String password, String lbl1, String lbl2, String lbl3, String lbl4,
			String lbl5, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		Assert.assertEquals(lbl1, projectResultsPage.getSquareArealbl());
		Assert.assertEquals(lbl2, projectResultsPage.getNoOfBuildingslbl());
		Assert.assertEquals(lbl3, projectResultsPage.getStoriesAboveGradelbl());
		Assert.assertEquals(lbl4, projectResultsPage.getStoriesBelowGradelbl());
		Assert.assertEquals(lbl5, projectResultsPage.getBuildingFramelbl());
		Assert.assertTrue(projectResultsPage.CheckisSelected_StructuralProperties());
		Assert.assertTrue(projectResultsPage.checkDefaultValueOfStructPropDDL());
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc798(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		int firstChildFilerCountBefore = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"ProjectTypesLHS_LabelList");
		int secondChildFilerCountBefore = projectResultsPage.get_LHS_FilterWiseProjectCount(1,
				"ProjectTypesLHS_LabelList");
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int parentFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"ACTION_STAGE_CATEGORYLHS_LabelList");
		int firstChildFilerCountafter = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"ProjectTypesLHS_LabelList");
		int secondChildFilerCountafter = projectResultsPage.get_LHS_FilterWiseProjectCount(1,
				"ProjectTypesLHS_LabelList");
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, firstChildFilerCountafter));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, secondChildFilerCountafter));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(firstChildFilerCountBefore, secondChildFilerCountafter));
		Assert.assertTrue(
				projectResultsPage.compareTwoNumbers(secondChildFilerCountBefore, secondChildFilerCountafter));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCFilters", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc823(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc805(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
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

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc806(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String Expected = "Valuation - High to Low";
		projectResultsPage.selectSortingOption("Publish Date - Ascending");
		projectResultsPage.selectSortingOption(Expected);
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), Expected);
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnSavedSearchMenu();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), Expected);
		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc814(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(projectResultsPage.Check_FilterSelectionsShouldAppearHorizontally());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc816(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(companyResultPage.get_savedSearchName(), searchName);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc808(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "GeographyFilterList");
		companyResultPageObj.SelectOptionsFromTheList(2, "GeographyFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.SelectOptionsFromTheList(2, "StateRegionFilterList");
		companyResultPage.waitforLoadingRing();
		int ResultCount = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPage.Check_ResultCountLessThan_10000(ResultCount));
		companyResultPage.clickPowerRank();
		Assert.assertEquals(companyResultPage.get_powerRankedResults_txt(), "POWER RANKED RESULTS");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc825(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertTrue(projectResultsPage.checkSpecDivisionFilterListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCBid", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc815(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3384", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc828(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc830(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertFalse(projectResultsPage.isShowMoreFilterVisible());
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isShowMoreFilterDisplayed());
		projectResultsPage.clickOnCommonShowMoreFiltes_Arrow();
		Assert.assertTrue(projectResultsPage.isShowLessFilterDisplayed());
		projectResultsPage.clickOnCommonShowLessFiltes_Arrow();
		Assert.assertFalse(projectResultsPage.isShowLessFilterDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FilterDataProvider.class, dataProvider = "TCFilters", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc829(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "GeographyFilterList");
		companyResultPageObj.SelectOptionsFromTheList(2, "GeographyFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.SelectOptionsFromTheList(2, "StateRegionFilterList");
		companyResultPage.waitforLoadingRing();
		int ResultCount = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPage.Check_ResultCountLessThan_10000(ResultCount));
		companyResultPage.clickPowerRank();
		Assert.assertEquals(companyResultPage.get_powerRankedResults_txt(), "POWER RANKED RESULTS");
		homePage.clickOnSignOutButton();
	}
}

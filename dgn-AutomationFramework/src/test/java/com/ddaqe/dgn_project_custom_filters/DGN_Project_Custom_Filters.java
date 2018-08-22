package com.ddaqe.dgn_project_custom_filters;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGN_Project_Custom_Filters extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters", description = " (TC3873)")
	public void TC3873(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnHideAllFilterButton();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), false);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.isSpecialFilterDisplayed(), false);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), false);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), false);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), false);
		projectResultsPage.clickOnShowAllFiltersButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters", description = " (TC3933)")
	public void TC3933(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.clickOnValuationFilter();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters", description = " (TC3862)")
	public void TC3862(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.clickOnAdditionalFilters();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3860(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.clickOnPublishRangeFilter();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		CompanyResultsPage CompanyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		projectResultsPage = CompanyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3870(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3874(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		CompanyResultsPage CompanyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		projectResultsPage = CompanyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3875(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		homePage.clickOnSignOutButton();
		HomePage homePage1 = loginAs(emailAddress, password);
		projectResultsPage = homePage1.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3867(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_geographyFilter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_StateRegionFilter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_ACTION_STAGE_CATEGORY_Filter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_geographyFilter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_StateRegionFilter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ACTION_STAGE_CATEGORY_Filter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_Additionalfilters_MySearches();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3868(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		homePage.clickOnSignOutButton();
		HomePage homePage1 = loginAs(emailAddress, password);
		projectResultsPage = homePage1.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters", description = " (TC3862)")
	public void TC3872(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);
	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3865(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		CompanyResultsPage companyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		projectResultsPage = companyResultsPage.clickOnTheProjectsLink();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3861(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_geographyFilter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_StateRegionFilter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ACTION_STAGE_CATEGORY_Filter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_geographyFilter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_StateRegionFilter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_ACTION_STAGE_CATEGORY_Filter_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_MySearches_Additionalfilters();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DataProvider_ProjectCustomFilters.class, dataProvider = "CommonProjectCustomFilters")
	public void tc3869(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnShowAllFiltersButton();
		projectResultsPage.DragAndDrop_MySearches_Additionalfilters();
		projectResultsPage.DragAndDrop_Additionalfilters_MySearches();
		projectResultsPage.DragAndDrop_ChangeOrder_MySearches();
		Assert.assertEquals(projectResultsPage.IsmySearchesDropDown_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsmyAdditionalFilters_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsREPORTSFilterr_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPublishRangeFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsFIND_INFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.isMaterialsEquipFillterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsSTRUCTURAL_PROPERTIESFilter_Displayed(), true);
		Assert.assertEquals(!projectResultsPage.isSpecialFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.isGeographyFilterDisplayed(), true);
		Assert.assertEquals(projectResultsPage.IsStateRegionFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsbiddingWithinFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsACTION_STAGE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsPROJECT_TYPE_CATEGORY_Filter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IstrackingListFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsValuationFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsCONSTRUCTION_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsOWNERSHIP_TYPEFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsspecialConditionsFilter_Displayed(), true);
		Assert.assertEquals(projectResultsPage.IsTRADE_TYPE_CATEGORY_Filter_Displayed(), true);

	}
}
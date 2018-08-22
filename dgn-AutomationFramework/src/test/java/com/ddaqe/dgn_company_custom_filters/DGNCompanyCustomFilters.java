package com.ddaqe.dgn_company_custom_filters;

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
public class DGNCompanyCustomFilters extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanyCustomFilters.class);

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
			"Regression" }, dataProviderClass = DGNCompanyCustomFiltersDataProvider.class, dataProvider = "TC3946", description = "User-customized filters are NOT implemented for the left-nav filters on the following out-of-scope Company pages")
	public void tc3946(String emailAddress, String password, String sourceFilter, String destinationFilter) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage CompanyResultsPage = homePage.clickOnCompaniesLink();
		ProjectResultsPage projectResultsPage = CompanyResultsPage.clickOnTheProjectsLink();
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
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNCompanyCustomFiltersDataProvider.class, dataProvider = "TC3946", description = " (TC3873)")
	public void TC3873(String emailAddress, String password, String sourceFilter, String destinationFilter) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.ClickOnCommonPopupHideZeroProjects_cbk();
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(companyResultPageObj.IsZeroProjCountElement_Displayed_Common_Popup_allSubOptionslbl());
		companyResultPageObj.SwitchToParent();
		companyResultPageObj.PopUpClose();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPage.ClickOngeographyPopupStateLink();
		companyResultPageObj.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Normal",
			"Regression" }, dataProviderClass = DGNCompanyCustomFiltersDataProvider.class, dataProvider = "TC3946")
	public void tc3950(String emailAddress, String password, String sourceFilter, String destinationFilter) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage CompanyResultsPage = homePage.clickOnCompaniesLink();
		ProjectResultsPage projectResultsPage = CompanyResultsPage.clickOnTheProjectsLink();
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
		homePage.clickOnSignOutButton();
	}
}

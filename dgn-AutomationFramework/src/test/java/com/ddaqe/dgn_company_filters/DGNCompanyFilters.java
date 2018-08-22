package com.ddaqe.dgn_company_filters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyNotesPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.PrintCompanyListPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNCompanyFilters extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Include pagination controls at bottom of company profile view (instead of only at the top) - To Verify the Pagination in the Company Profile page (TC7333)")
	public void tc1406_1407(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.selectValueFromSortDropdown("Number of Projects - Descending");
		CompanyPage companyPage = companyResultPage.clickOnCompanyTitleWithContactsAndProjects();
		CompanyContactsPage companyContactsPage  = companyPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyContactsPage.IsPageinationDisplayed());
		companyContactsPage.clickOnCompanyProjectsLink();
		companyResultPage.waitforLoadingRing();
		Assert.assertTrue(companyContactsPage.IsPageinationDisplayed());
		homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		CompanyPage companyprofile = companyResultPage.clickOnCompanyTitle(0);
		Assert.assertTrue(companyprofile.IsPageinationDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify  label  chagne from Total Valuations to Total Valuation (TC14522)")
	public void tc1409(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		CompanyPage companyprofile = companyResultPage.clickOnCompanyTitle(0);
		Assert.assertEquals(companyprofile.get_lblTotalValuations(), "Total Valuation:");
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of panel in the new lightbox dialog in the Company type filter (TC15871)")
	public void tc1410(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Company filter subsection in Applied filter section in Company result  page. (TC15926)")
	public void tc1411_1413(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnGeographyFilter();
		String CompanyLocationlbl_LHS = companyResultPageObj.get_LHS_Filter_lbl(0, "GEOGRAPHY_COUNTRY_LHS_lbl_list");
		int compCountLHS = companyResultPageObj.get_LHS_FilterWiseProjectCount(0,
				"GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List");
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPage.waitforLoadingRing();
		String CompanyLocation_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(0);
		Assert.assertEquals(CompanyLocationlbl_LHS, CompanyLocation_appliedFilterlbl);
		int compResultCount = companyResultPage.getExactCompanyResultCount();
		Assert.assertEquals(compCountLHS, compResultCount);

		companyResultPage.clickOnCompanyTypeFilter();
		String CompanyFilterlbl_LHS = companyResultPage.get_LHS_Filter_lbl(0, "COMPANY_TYPE_GROUP_LHS_lbl_list");
		int compCountLHS1 = companyResultPage.get_LHS_FilterWiseCompanyCount(0,
				"COMPANY_TYPE_GROUP_ProjectCountNextToElement_List");
		companyResultPage.SelectOptionsFromTheList(1, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		String CompanyType_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(1);
		Assert.assertEquals(CompanyFilterlbl_LHS, CompanyType_appliedFilterlbl);
		int compResultCount1 = companyResultPage.getExactCompanyResultCount();
		Assert.assertEquals(compCountLHS1, compResultCount1);
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Project filter subsection in Applied filter section in Company result  page. (TC15931)")
	public void tc1412(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnActionStageFilter();
		String CompanyLocationlbl_LHS = companyResultPageObj.get_LHS_Filter_lbl(0,
				"ACTION_STAGE_CATEGORYLHS_LabelList");
		int BeforeAction = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		String CompanyLocation_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(0);
		Assert.assertEquals(CompanyLocationlbl_LHS, CompanyLocation_appliedFilterlbl);
		int AfterAction = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPageObj.compareTwoNumbers(BeforeAction, AfterAction));

		companyResultPageObj.clickOnCONSTRUCTION_TYPEFilter();
		String CompanyConstructlbl_LHS = companyResultPageObj.get_LHS_Filter_lbl(0,
				"CONSTRUCTION_TYPEFilter_LHS_lblList");
		int BeforeConstruction = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		companyResultPage.waitforLoadingRing();
		String CompanyConstruction_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(1);
		Assert.assertEquals(CompanyConstructlbl_LHS, CompanyConstruction_appliedFilterlbl);
		int AfterConstruction = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPageObj.compareTwoNumbers(BeforeConstruction, AfterConstruction));

		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		String CompanyOwnerlbl_LHS = companyResultPageObj.get_LHS_Filter_lbl(0, "OWNERSHIP_TYPEFilter_LHS_lblList");
		int BeforeOwner = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPage.waitforLoadingRing();
		String CompanyOwner_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(2);
		Assert.assertEquals(CompanyOwnerlbl_LHS, CompanyOwner_appliedFilterlbl);
		int AfterOwner = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPageObj.compareTwoNumbers(BeforeOwner, AfterOwner));
		companyResultPageObj.clickOnClearAllLinkInFilter();

		int BeforeStruct = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultPage.clickOnStructuralPropLink();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 12);
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
		String CompanyStruct_appliedFilterlbl = companyResultPageObj.get_AppliedFilter_lbl(0);
		Assert.assertTrue(CompanyStruct_appliedFilterlbl.contains("Project-Square-Area"));
		int AfterStruct = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPageObj.compareTwoNumbers(BeforeStruct, AfterStruct));
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Power ranking in Company result  page. (TC15936)")
	public void tc1415(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
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
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Save search in Company result  page. (TC15937)")
	public void tc1416(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(companyResultPage.get_savedSearchName(), searchName);
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Clear Search in Company result  page. (TC15938)")
	public void tc1417(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnActionStageFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		int BeforeOwner = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPage.waitforLoadingRing();
		int AfterOwner = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultPageObj.compareTwoNumbers(BeforeOwner, AfterOwner));
		companyResultPageObj.clickOnClearAllLinkInFilter();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 0);
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of the new lightbox dialog in the Project type (cross search) filter (TC16031)")
	public void tc1430(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of the new lightbox dialog in the Action stage  (cross search) filter (TC16032)")
	public void tc1431(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of the new lightbox dialog in the Construction type (cross search)filters (TC16034)")
	public void tc1432(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of the new lightbox dialog in the ownership Type  (cross search) filter (TC16035)")
	public void tc1433(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnOWNERSHIP_TYPESeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Geography filte  under company filter  sections in Company tab . (TC16292)")
	public void tc1437(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Geography filte  under company filter  sections in Company tab . (TC16292)")
	public void tc1438_1420(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Action Stage filte  in Company tab . (TC16296)")
	public void tc1441_DGNT1277(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Construction Type filte  in Company tab . (TC16298)")
	public void tc1442_1485(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Ownership Type filte  in Company tab . (TC16299)")
	public void tc1443_1486(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnOWNERSHIP_TYPESeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.clickOnOWNERSHIP_TYPESeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify When the selected structural properties filter is removed, it doesn't reappear back in More Filters (TC17774)")
	public void tc1444(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultPage.clickOnStructuralPropLink();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 12);
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
		companyResultPageObj.ClickOncrumbFilterClose(0);
		companyResultPage.clickOnStructuralPropLink();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPageObj.checkDefaultValueOfStructPropDDL());
		companyResultPageObj.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 12);
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
		companyResultPageObj.clickOnClearAllLinkInFilter();
		companyResultPage.clickOnStructuralPropLink();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPageObj.checkDefaultValueOfStructPropDDL());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Plus users should not be able to see the cross search project filters (TC19404)")
	public void tc1445(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		int AfterContry = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, AfterContry);
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPage.ClickOngeographyPopupStateLink();
		companyResultPageObj.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		companyResultPage.ClickOnGEOGRAPHYPopupprojectCountyNavLink();
		companyResultPageObj.SelectOptionsFromTheList(1, "COUNTYPopupUASCountyList");
		companyResultPageObj.clickOnUpdateButton();
		int AfterCity = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(AfterContry, AfterCity);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "[DGN] The company types values are not in ascending order in the light box (TC20627)")
	public void tc1446(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		Assert.assertTrue(companyResultPage.verifyCompanyTypeSortingAscending());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "[DGN] Location Ascending for Company tab not working properly (TC20650)")
	public void tc1447(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		companyResultPageObj.ClickOn_CommonPopupParent_cbk(4);
		companyResultPageObj.ClickOn_CommonPopupParent_cbk(10);
		companyResultPageObj.clickOnUpdateButton();
		companyResultPage.clickOnCompanyTypeFilter();
		companyResultPage.SelectOptionsFromTheList(1, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.selectSortingOption("Location - Ascending");
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
		companyResultPage.ClickOn_Next_Page();
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
		companyResultPage.ClickOn_Previous_Page();
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "[DGN] Location Ascending for Company tab not working properly (TC20651)")
	public void tc1448(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		companyResultPageObj.ClickOn_CommonPopupParent_cbk(4);
		companyResultPageObj.ClickOn_CommonPopupParent_cbk(10);
		companyResultPageObj.clickOnUpdateButton();
		companyResultPage.clickOnCompanyTypeFilter();
		companyResultPage.SelectOptionsFromTheList(1, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.selectSortingOption("Location - Descending");
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
		companyResultPage.ClickOn_Next_Page();
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
		companyResultPage.ClickOn_Previous_Page();
		Assert.assertTrue(companyResultPage.verifyCompanyLocationSortingAscending());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Quick search box in the company profile page same as it is present in the company results page. (TC22695)")
	public void tc1451(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProfileLink();
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Search button in the company profile page same as it is present in the company results page. (TC22696)")
	public void tc1452(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProfileLink();
		Assert.assertTrue(homePage.Is_searchBtn_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify My Searches drop list in the company profile page same as it is present in the company results page. (TC22697)")
	public void tc1453(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the functionality of keyword search in the company profile page. (TC22699)")
	public void tc1455(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProfileLink();
		String appcompanyURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyURL.contains("Company.aspx"));
		companyResultPage.enteredInTheSearchBox("company");
		companyResultPage.clickOnSearchButton();
		String appcompanyResultURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyResultURL.contains("CompanyResults.aspx"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Search button box in the company contact page same as it is present in the company results page. (TC22701)")
	public void tc1456(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyResultPage.Is_searchButton_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify My Searches drop list box in the company contact page same as it is present in the company results page. (TC22702)")
	public void tc1457(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the functionality of keyword search in the company contact page. (TC22704)")
	public void tc1459(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyContactsLink();
		String appcompanyURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyURL.contains("CompanyContacts.aspx"));
		companyResultPage.enteredInTheSearchBox("company");
		companyResultPage.clickOnSearchButton();
		String appcompanyResultURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyResultURL.contains("CompanyResults.aspx"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Quick search box in the company projects page same as it is present in the company results page. (TC22705)")
	public void tc1460(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Search button in the company projects page same as it is present in the company results page. (TC22706)")
	public void tc1461(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyResultPage.Is_searchButton_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify My Searches drop list in the company projects page same as it is present in the company results page. (TC22707)")
	public void tc1462(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the functionality of keyword search in the company projects page. (TC22709)")
	public void tc1464(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnCompanyProjectsLink();
		String appcompanyURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyURL.contains("CompanyProjects.aspx"));
		companyResultPage.enteredInTheSearchBox("company");
		companyResultPage.clickOnSearchButton();
		String appcompanyResultURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyResultURL.contains("CompanyResults.aspx"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Quick search box in the company notes  page same as it is present in the company results page. (TC22710)")
	public void tc1465_1466_1467_1469(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		CompanyPage companyprofile = companyResultPage.clickOnCompanyProfileLink();
		companyprofile.clickOnActionsDropDown();
		NotesPage notesPage = companyprofile.clickOnAddNotesMenu();
		String noteText = "Private Notes";
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		CompanyNotesPage companyNotespg = companyprofile.clickOnNotesTab();
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed());
		Assert.assertTrue(companyResultPage.Is_searchButton_displayed());
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
		String appcompanyURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyURL.contains("CompanyNotes.aspx"));
		companyNotespg.clickSelectAllCheckbox();
		companyNotespg.mouseoverNotesActionandClickDeleteNotes();
		companyResultPage.enteredInTheSearchBox("company");
		companyResultPage.clickOnSearchButton();
		String appcompanyResultURL = companyResultPage.get_getCurrentUrl();
		Assert.assertTrue(appcompanyResultURL.contains("CompanyResults.aspx"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify clicking on the rectangular icon on the filter header (TC22725)")
	public void tc1471_1472(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.ClickOnCommonPopupHideZeroProjects_cbk();
		Assert.assertTrue(companyResultPageObj.IsZeroProjCountElement_Displayed_Common_Popup_allSubOptionslbl());
		companyResultPageObj.SwitchToParent();
		companyResultPageObj.PopUpClose();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		companyResultPage.ClickOngeographyPopupStateLink();
		companyResultPageObj.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the selections made for the filter should be displayed on top in the filter crumb section (TC22730)")
	public void tc1474_1475(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnStateRegionFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "StateRegionFilterList");
		companyResultPageObj.waitforLoadingRing();
		String Expected = companyResultPageObj.getStateRegion_LHSFirstFilter_lbl();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(Expected.contains(companyResultPageObj.getcrumbSecondFiltertxt()));
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.ClickOncrumbFilterClose(0);
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 0);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Clear All should clear all the selections. (TC22732)")
	public void tc1476(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnStateRegionFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "StateRegionFilterList");
		companyResultPageObj.waitforLoadingRing();
		String Expected = companyResultPageObj.getStateRegion_LHSFirstFilter_lbl();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertTrue(Expected.contains(companyResultPageObj.getcrumbSecondFiltertxt()));
		Assert.assertNotEquals(Before, After);
		companyResultPageObj.clickOnClearAllLinkInFilter();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 0);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the alignment of state and county in the filter crumb section (TC22733)")
	public void tc1477(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnStateRegionFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "StateRegionFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnGEOGRAPHY_CountyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GEOGRAPHY_COUNTYFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(companyResultPageObj.Check_FilterSelectionsShouldAppearHorizontally());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the multi-level filters (TC22734)")
	public void tc1478(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnStateRegionFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "StateRegionFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnGEOGRAPHY_CountyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GEOGRAPHY_COUNTYFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.ClickOncrumbFilterClose(1);
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 1);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify when a country is selected, top 9 states for the country should be loaded under the States section (TC22735)")
	public void tc1480(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnStateRegionFilter();
		Assert.assertTrue(companyResultPageObj.Check_First9_OptionsAreVisible("StateRegionFilterList"));
		Assert.assertTrue(companyResultPageObj.Check_10th_OptionsVisible("StateRegionFilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the Consrtuction Type is implemented as per new UI on Company Results Page (TC22737)")
	public void tc1481(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnCONSTRUCTION_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(companyResultPage.Is_projectsLink_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the  Ownership Type is implemented as per new UI on Company Results Page (TC22738)")
	public void tc1482(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(companyResultPage.Is_projectsLink_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the Structural Properties Filters is implemented as per new UI on Company Results Page (TC22739)")
	public void tc1483(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnSTRUCTURAL_PROPERTIESFilter();
		Assert.assertTrue(companyResultPage.Is_projectsLink_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the number of  filters selected under the category should be displayed as a number followed by the word Filters which is the hyperlink (TC22745)")
	public void tc1488(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnCONSTRUCTION_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(4, "ConstructionType_LHSFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnOWNERSHIP_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(4, "OWNERSHIP_TYPE_LHSFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.addSearchTextIn_FilterCrumbPopup_Searchbox("Additions");
		Assert.assertTrue(
				companyResultPageObj.getFirstFilter_txt_From_FilterCrumbPopup_FilterList().contains("Additions"));
		companyResultPageObj.ClickOnFilterpopClose();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		int ebefore = companyResultPageObj.getCrumbFilterPopupCloseBtnListSize();
		companyResultPageObj.clickOnCrumbFilterPopupFirstCloseBtn();
		int eafter = companyResultPageObj.getCrumbFilterPopupCloseBtnListSize();
		Assert.assertNotEquals(ebefore, eafter);
		companyResultPageObj.clickOnCrumbFilterPopupDonebtn();
		companyResultPageObj.waitforLoadingRing();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify that Company Type filter is present  in Company Results Page (TC22798)")
	public void tc1495_1496_1497_1500_1501_1502_1503(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPage.Is_CompanyTypeFilter_Displayed());
		companyResultPage.clickOnCompanyTypeFilter();
		Assert.assertTrue(companyResultPage.Is_PrimaryCompany_Type_cbk_Selected());
		companyResultPage.Check_ListOption_Selecteded("CompanyTypeGrp_LHS_ParentFilterList");
		Assert.assertTrue(companyResultPage.Is_CompanyTypeFilterSeeMore_Btn_Displayed());
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		Assert.assertTrue(companyResultPageObj.IsCommonPopupHideZeroProjects_cbk_Displayed());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify that in Company Location filter No default location type is checked (TC22802)")
	public void tc1498(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPageObj.isGeographyFilterDisplayed());
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.Check_ListOption_Selecteded("GeographyFilterList");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify that Company Type filter is present  in Company Results Page (TC22798)")
	public void tc1504(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPage.Is_CompanyTypeFilter_Displayed());
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPage.clickOnCompanyTypeFilter();
		companyResultPage.SelectOptionsFromTheList(4, "CompanyTypeGrp_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the design of the new lightbox dialog in the Construction type (cross search)filters (TC16034)")
	public void tc1421_1429(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOn_Accodion_DownArrow();
		Assert.assertFalse(companyResultPageObj.ISCommonPopop_Accodion_DownArrow_displayed());
		companyResultPageObj.ClickOnPopupUpdateFancyBoxbtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Project Location filter  under project filter section in Company tab . (TC16294)")
	public void tc1439_DGNT1277(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPage.clickOnProjectLocationFilterSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
		companyResultPage.clickOnProjectLocationFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the UI change in Project Location filter  under project filter section in Company tab . (TC16294)")
	public void tc1440(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		Assert.assertTrue(companyResultPage.Check_popupSelectall_lbl_color());
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertNotEquals(Before, After);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the  filters on Company Project Page (TC22675)")
	public void tc1450(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPage.IsProjPublishDateFilter_Displayed());
		Assert.assertTrue(companyResultPage.IsSearchCompanyFiledsFilter_Displayed());
		Assert.assertTrue(companyResultPageObj.isGeographyFilterDisplayed());
		Assert.assertTrue(companyResultPage.Is_CompanyTypeFilter_Displayed());
		Assert.assertTrue(companyResultPage.IsProjectLocationFilter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsACTION_STAGE_CATEGORY_Filter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsPROJECT_TYPE_CATEGORY_Filter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsspecAlertFilter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsCONSTRUCTION_TYPEFilter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsOWNERSHIP_TYPEFilter_Displayed());
		Assert.assertTrue(companyResultPageObj.IsSTRUCTURAL_PROPERTIESFilter_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the contents of My Searches drop list  in the company profile page (TC22698)")
	public void tc1454(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSpecAlertsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesTrackingListsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSavedSearchesDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the contents of My Searches drop list  in the company contact page (TC22703)")
	public void tc1458(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSpecAlertsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesTrackingListsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSavedSearchesDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the contents of My Searches drop list  in the company projects page (TC22708)")
	public void tc1463(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyResultPageObj.IsmySearchesDropDown_Displayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSpecAlertsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesTrackingListsDisplayed());
		Assert.assertTrue(companyResultPageObj.isMySearchesSavedSearchesDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify There is no default project geography in Company result page (TC22837)")
	public void tc1509(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnProjectLocationFilter();
		Assert.assertTrue(companyResultPage.Check_DefaultStatusOf_CheckboxList("ProjectLocation_LHS_ParentFilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the filter crumb sections of Project Location, Project State/County, Action Stage Filters on Company Results Page (TC22840)")
	public void tc1510(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPage.SelectOptionsFromTheList(3, "ProjectLocation_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(CommonUtils.checkStringContains("Filters", companyResultPageObj.getcrumbFirstFiltertxt()));
		companyResultPageObj.ClickOncrumbFirstFilterClose();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 0);
		companyResultPage.SelectOptionsFromTheList(3, "ProjectLocation_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.addSearchTextIn_FilterCrumbPopup_Searchbox("USA");
		Assert.assertTrue(companyResultPageObj.getFirstFilter_txt_From_FilterCrumbPopup_FilterList().contains("USA"));
		companyResultPageObj.ClickOnFilterpopClose();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		int ebefore = companyResultPageObj.getCrumbFilterPopupCloseBtnListSize();
		companyResultPageObj.clickOnCrumbFilterPopupFirstCloseBtn();
		int eafter = companyResultPageObj.getCrumbFilterPopupCloseBtnListSize();
		Assert.assertNotEquals(ebefore, eafter);
		companyResultPageObj.clickOnCrumbFilterPopupDonebtn();
		companyResultPageObj.waitforLoadingRing();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Project Groups , verify  when a parent filter is selected from See More of Project Groups, then parent filter is shown in filter crumb (TC23420)")
	public void tc1515(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(3, "ProjectGroupsParent2FilterList");
		companyResultPageObj.clickOnUpdateButton();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);
		companyResultPageObj.clickOnClearAllLinkInFilter();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(5, "ProjectGroupsParent2FilterList");
		companyResultPageObj.clickOnUpdateButton();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Project Groups , verify when a parent filter is selected, then parent filter is shown in filter crum (TC23421)")
	public void tc1516(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnProjectGroupsFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ProjectGroups_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);
		companyResultPageObj.clickOnClearAllLinkInFilter();
		companyResultPageObj.clickOnProjectGroupsFilter();
		companyResultPageObj.SelectOptionsFromTheList(5, "ProjectGroups_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Action Stage Type , verify the when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23418)")
	public void tc1518(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);
		companyResultPageObj.clickOnClearAllLinkInFilter();
		companyResultPageObj.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Action Stage Type , verify when a parent filter is selected, then parent filter is shown in filter crumb (TC23419)")
	public void tc1517(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnActionStageFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);
		companyResultPageObj.clickOnClearAllLinkInFilter();
		companyResultPageObj.clickOnActionStageFilter();
		companyResultPageObj.SelectOptionsFromTheList(5, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Projects Group filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23426)")
	public void tc1520_1484(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(1, "ProjectGroupsParent2FilterList");
		companyResultPageObj.clickOnUpdateButton();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 1);
		companyResultPageObj.clickOnProjectTypesSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(2, "CommercialOptionsFilterListFromPopup");
		companyResultPageObj.clickOnUpdateButton();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 3);
		companyResultPageObj.clickOnProjectTypesSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(2, "CommercialOptionsFilterListFromPopup");
		companyResultPageObj.SelectOptionsFromTheList(5, "CommercialOptionsFilterListFromPopup");
		companyResultPageObj.clickOnUpdateButton();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "For Projects Group filter, when all children filter are selected for a parent from See More, then the parent will be shown in filter crumb (TC23430)")
	public void tc1524(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnProjectGroupsSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		companyResultPageObj.clickOnUpdateButton();
		Assert.assertEquals(companyResultPageObj.getAppliedFilterCount(), 1);
		companyResultPageObj.clickOnProjectTypesSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		companyResultPageObj.ClickOnPopupSelectAll_cbk();
		companyResultPageObj.clickOnUpdateButton();
		companyResultPageObj.ClickOncrumbFirstGroupFilterLink();
		companyResultPageObj.SwitchToActiveElement();
		Assert.assertTrue(companyResultPageObj.isFilterPopupDisplayed());
		companyResultPageObj.ClickOnFilterpopClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify that filter crumb drawer is visible on company search page when the applied filters size is more than one line. (TC23625)")
	public void tc1527(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		Assert.assertFalse(companyResultPageObj.isShowMoreFilterVisible());
		companyResultPageObj.clickOnProjectGroupsFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ProjectGroups_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnActionStageFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOnCONSTRUCTION_TYPEFilter();
		companyResultPageObj.SelectOptionsFromTheList(3, "ConstructionType_LHSFilterList");
		companyResultPageObj.waitforLoadingRing();
		Assert.assertTrue(companyResultPageObj.isShowMoreFilterDisplayed());
		companyResultPageObj.clickOnCommonShowMoreFiltes_Arrow();
		Assert.assertTrue(companyResultPageObj.isShowLessFilterDisplayed());
		companyResultPageObj.clickOnCommonShowLessFiltes_Arrow();
		Assert.assertFalse(companyResultPageObj.isShowLessFilterDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify if user changes a left nav filter from open to closed, that left nav filter remains closed until explicitly changed by the user. (TC23791)")
	public void tc1531_1533(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnProjectGroupsFilter();
		homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		Assert.assertFalse(companyResultPageObj.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		Assert.assertFalse(companyResultPageObj.IsProjectGrups_Filter_ArrowUp_Visible());
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify if user changes a left nav filter from closed to opened, that left nav filter remains opened until explicitly changed by the user. (TC23792)")
	public void tc1532_1534(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnProjectGroupsFilter();
		companyResultPageObj.CloseProjectGroupsFilter();
		homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPageObj.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPageObj.IsProjectGrups_Filter_ArrowUp_Visible());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify Company Location left nav filter displays a maximum of 9 line items  in all browsers (TC24082)")
	public void tc1536_1540_1541_1542(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.clickOnGeographyFilter();
		Assert.assertTrue(companyResultPageObj.Check_First9_OptionsAreVisible("GeographyFilterList"));
		Assert.assertTrue(companyResultPageObj.Check_10th_OptionsVisible("GeographyFilterList"));
		Assert.assertTrue(companyResultPageObj.Geography_scrollbar_verticalDragger_displayed());
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify presence of Includefilters option when printing a Company List (TC24110)")
	public void tc1537(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.SelectOptionsFromTheList(5, "companyTitleCheckboxes");
		companyResultPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultPage.clickOnPrintCompanyListUnderActions();
		Assert.assertTrue(printCompanyListPage.isIncludeFilterCheckboxPresent());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify default value of Includefilters option  is unchecked when printing a Project List (TC24111)")
	public void tc1538(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		homePage.clickOnCompaniesLink();
		companyResultPage.SelectOptionsFromTheList(5, "companyTitleCheckboxes");
		companyResultPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultPage.clickOnPrintCompanyListUnderActions();
		Assert.assertFalse(printCompanyListPage.isIncludeFilterCheckboxSelected());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify When Includefilters checkbox is checked, then all the search filter criteria are shown on the page. (TC24112)")
	public void tc1539(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPageObj.clickOnGeographyFilter();
		companyResultPageObj.SelectOptionsFromTheList(1, "GeographyFilterList");
		companyResultPageObj.waitforLoadingRing();
		companyResultPage.SelectOptionsFromTheList(5, "companyTitleCheckboxes");
		companyResultPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultPage.clickOnPrintCompanyListUnderActions();
		Assert.assertFalse(printCompanyListPage.isIncludeFilterCheckboxSelected());
		Assert.assertEquals(printCompanyListPage.getAppliedFilter_subheader_txt(), "USA");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the light box content after clicking on see more link for company location filter in Company tab (TC16216)")
	public void tc1707(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.IsZeroProjCountElement_Displayed_Common_Popup_allSubOptionslbl());
		companyResultPageObj.ClickOnCommonPopupHideZeroProjects_cbk();
		Assert.assertTrue(companyResultPageObj.IsZeroProjCountElement_Displayed_Common_Popup_allSubOptionslbl());
		companyResultPageObj.clickOnUpdateButton();
		companyResultPageObj.clickOngeographyFilterSeeMore_Btn();
		companyResultPageObj.SwitchToFrame();
		Assert.assertFalse(companyResultPageObj.IsCommonPopupHideZeroProjects_cbk_Selected());
		companyResultPageObj.clickOnUpdateButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "[Primary Company Type Filter] - Verify the facets for checked and unchecked state (TC10269)")
	public void tc1408(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnCompanyTypeFilter();
		int Before = companyResultPage.getExactCompanyResultCount();
		companyResultPage.clickon_PrimaryCompany_Type_cbk();
		int After = companyResultPage.getExactCompanyResultCount();
		Assert.assertEquals(Before, After);
		companyResultPage.clickon_PrimaryCompany_Type_cbk();
		int After1 = companyResultPage.getExactCompanyResultCount();
		Assert.assertEquals(After, After1);
		

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify all the fields under 'Search Specific Fields' are working properly. (TC22761)")
	public void tc1494(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnSearchCompanyFiledsFilter();
		String CompanyName = "Ministry of Energy and Mines";
		companyResultPage.Insert_CompanyName_textbox(CompanyName);
		companyResultPage.ClickOn_CompanySearch_button();
		Assert.assertEquals(companyResultPage.getCompanyTitle(0), CompanyName);
		
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verification of the functionality of selections of different level checkboxes the new lightbox dialog of Company Type filter (TC15944)")
	public void tc1419(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPage.clickOnCompanyTypeFilterSeeMore_Btn();
		companyResultPage.SwitchToFrame();
		companyResultPageObj.ClickOnPopupSelectAll_cbk();
		Assert.assertFalse(companyResultPageObj.Check_DefaultStatusOf_CheckboxList("Common_Popup_allCbksList"));
		companyResultPageObj.ClickOnPopupSelectAll_cbk();
		Assert.assertTrue(companyResultPageObj.Check_DefaultStatusOf_CheckboxList("Common_Popup_allCbksList"));
		companyResultPageObj.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertFalse(companyResultPageObj.Check_DefaultStatusOf_CheckboxList("Common_Popup_FirstLevel2CbksList"));
		companyResultPageObj.Check_AllLevel_1_cbks();
		Assert.assertFalse(companyResultPageObj.ISPopupSelectAll_cbk_Selected());
		companyResultPageObj.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertFalse(companyResultPageObj.Check_DefaultStatusOf_CheckboxList("Common_Popup_FirstLevel2CbksList"));
		companyResultPageObj.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		companyResultPageObj.SelectOptionsFromTheList(1, "Common_Popup_FirstLevel2CbksList");
		Assert.assertFalse(companyResultPageObj.getCommonPopupParentFilterList_option_status(0));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify clicking on the icon on the filter header should load 'see more' pop-up (TC22833)")
	public void DGN_T1276(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage companyResultPageObj = homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPageObj.waitforLoadingRing();
		companyResultPage.clickOnProjectLocationFilter();
		companyResultPage.SelectOptionsFromTheList(2, "ProjectLocation_LHS_ParentFilterList");
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnProjectLocationFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPage.SwitchToFrame();
		companyResultPageObj.clickOnUpdateButton();
		companyResultPage.clickOnProjectStateFilterSeeMore_Btn();
		Assert.assertTrue(companyResultPageObj.isPopUpDisplayed());
		companyResultPage.SwitchToFrame();
		companyResultPageObj.clickOnUpdateButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the Project Published Date filter is shown if a project filter is selected. (TC23715)")
	public void DGNT1296(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.GetProjectResultsPageObjects();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnProjPublishDateFilter();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		Assert.assertTrue(companyResultPage.getPublishRange_DropdownOptionTxt().contains(sdf.format(cal.getTime())));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CompanyFiltersDataProvider.class, dataProvider = "CommonCompanyFilterDataProvider", description = "Verify the Project Published Date filter provide the same date options that are provided by the Publish Range filter on the Project Results page (TC23716)")
	public void DGNT1297(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPublishRangeFilter();
		String prResultPg = projectResultsPage.getPublishRange_DropDown_OptionText();
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.waitforLoadingRing();
		companyResultPage.clickOnProjPublishDateFilter();
		String cmpyResultPg = companyResultPage.getPublishRange_DropdownOptionTxt();
		Assert.assertEquals(prResultPg, cmpyResultPg);
	}
}

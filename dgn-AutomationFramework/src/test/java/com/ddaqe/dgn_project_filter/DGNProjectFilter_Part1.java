package com.ddaqe.dgn_project_filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.HelpPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.PrintProjectListPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNProjectFilter_Part1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3081", description = "Test Data cleanup script")
	public void A_testdataScript_Part1(String emailAddress, String password, String seachText)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "PlusUser", description = "Test Data cleanup script")
	public void AB_testdataScript_Part1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3081", description = "Refine project search results by special conditions - Scenario 1 (TC46)")
	public void tc3081(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSpecialConditions();
		Assert.assertTrue(projectResultsPage.checkSpecialConditionFilterListSize());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3081", description = "Refine project search results by special conditions - Scenario 3 (TC48)")
	public void tc3082(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.clickOnSpecialConditionGreenBuildelement();
		int iProjResultCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(projectResultsPage.getCountOfGreenBuildingSpecCondition(), iProjResultCountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3083", description = "Refine project search results by special conditions - Scenario 4 (TC49)")
	public void tc3083(String emailAddress, String password, String seachText, String count)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.clickOnMultipleSpecialCondition();
		Assert.assertTrue(projectResultsPage.checkSelectionOfMultipleSpecialContion(Integer.parseInt(count)));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3084", description = "Spec Division navigator - Scenario 1 (TC244)")
	public void tc3084(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertTrue(projectResultsPage.checkSpecDivisionFilterListSize());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3084", description = "Spec Division navigator - Scenario 2 (TC245)")
	public void tc3085(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.clickOnSpecDivisionElectricalType();
		int iProjResultCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(projectResultsPage.getCountOfelectricalSpecDivision(), iProjResultCountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3086", description = "Append state to county name in facet list - Scenario 1 (TC753)")
	public void tc3086(String emailAddress, String password, String seachText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3087", description = "Append state to county name in facet list - Scenario 2 (TC754)")
	public void tc3087(String emailAddress, String password, String seachText, String containString)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnTexaxState();
		Assert.assertTrue(projectResultsPage.checkStateNameInCountryAddress(containString));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3088", description = "Append state to county name in facet list - Scenario 3 (TC755)")
	public void tc3088(String emailAddress, String password, String seachText, String containString)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnCaliforniaState();
		Assert.assertTrue(projectResultsPage.checkStateNameInCountryAddress(containString));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3089", description = "Append state to county name in facet list - Scenario 4 (TC756)")
	public void tc3089(String emailAddress, String password, String seachText, String containString)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnOhioState();
		Assert.assertTrue(projectResultsPage.checkStateNameInCountryAddress(containString));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3090", description = "Append state to county name in facet list - Scenario 5 (TC757)")
	public void tc3090(String emailAddress, String password, String seachText, String containString)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnFloridaState();
		Assert.assertTrue(projectResultsPage.checkStateNameInCountryAddress(containString));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3091", description = "Append state to county name in facet list - Scenario 6 (TC758)")
	public void tc3091(String emailAddress, String password, String seachText, String containString)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(seachText);
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnNewYorkState();
		Assert.assertTrue(projectResultsPage.checkStateNameInCountryAddress(containString));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3092", description = "Bid date navigator searching future dates - Scenario 1 (TC771)")
	public void tc3092(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3092", description = "Bid date navigator searching future dates - Scenario 2 (TC772)")
	public void tc3093(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));
		projectResultsPage.clickOnNextSevenDays();
		Assert.assertTrue(projectResultsPage.checkBiddingFilterForNextDaysMonth(bidDate_1));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3092", description = "Bid date navigator searching future dates - Scenario 4 (TC774)")
	public void tc3094(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));
		projectResultsPage.clickOnNextOneMonthDays();
		Assert.assertTrue(projectResultsPage.checkBiddingFilterForNextDaysMonth("30"));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3384", description = "Verify the display section for more than 3 options are applied of Material/Equip at the filter crumb area, below the keyword search section for Material/Equip")
	public void tc3384(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the functionality after clicked on Clear All link below keyword search section of Spec Alerts filter")
	public void tc3383(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsCarpetCheckBoxUnchecked());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "Verify the display section for more than 3 options are applied of Spec ALerts at the filter crumb area, below the keyword search section")
	public void tc3378(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3507", description = "To Verify the values in the Quick Search drop down should be Projects and Companies in the Home page")
	public void tc3507(String emailAddress, String password, String Expected1, String Expected2)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.ClickOnHomeSearchDrodown();
		Assert.assertEquals(homePage.getHomeSearchDropdownProjectsOpton(), Expected1);
		Assert.assertEquals(homePage.getHomeSearchDropdownCompaniesOpton(), Expected2);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the header of dashboard in the home page should be Projects, Companies and SpecAlerts. (TC22694)")
	public void tc3508(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isCommonProjectsTabDisplayed());
		Assert.assertTrue(homePage.isCommonCompaniesTabDisplayed());
		Assert.assertTrue(homePage.isCommonSpecAlertsTabDisplayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3519", description = "Verify the Publish Range filter should contain Lastquarter as its default value. (TC22780)")
	public void tc3519(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPublishRangeFilter();
		Assert.assertTrue(
				CommonUtils.checkStringContains(Expected, projectResultsPage.getPublishRange_DropDown_OptionText()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3519", description = "Verify the display section of Project Results page has Publish Range by default  at the filter crumb area, below the keyword search section (TC22781)")
	public void tc3520(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(
				CommonUtils.checkStringContains(Expected, projectResultsPage.getPublishRange_DropDown_OptionText()));
		Assert.assertTrue(projectResultsPage.IsPublishRange_DropdownOptionDisplayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3527", description = "Verify the New name of Action Stage Project filter (TC22870)")
	public void tc3527(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getACTION_STAGE_CATEGORY_Filter_txt(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3529", description = "Verify the New name of Project Type Project filter (TC22872)")
	public void tc3529(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getPROJECT_TYPE_CATEGORY_Filter_txt(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3530", description = "Verify the New name of Project Type 2 Project filter (TC22873)")
	public void tc3530(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnprojTypeCatFacet_1_Ckbox();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertEquals(projectResultsPage.getPROJECT_TYPE_CODE_Filter(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3531", description = "Verify the text of exclude sreach box under tracking list Project filter (TC22874)")
	public void tc3531(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getExcludeTL_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3532", description = "Verify the text of exclude sreach box under SpecAlerts Project filter (TC22875)")
	public void tc3532(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertEquals(projectResultsPage.getexcludeSpecAlert_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3092", description = "To Verify that Bid date more dialog box.")
	public void tc3101(String emailAddress, String password, String bidDate_1, String bidDate_2, String bidDate_3,
			String bidDate_4, String bidDate_5, String bidDate_6) throws InterruptedException {
		List<String> bidDateList = new ArrayList<String>(Arrays.asList(bidDate_1.toUpperCase().trim(),
				bidDate_2.toUpperCase().trim(), bidDate_3.toUpperCase().trim(), bidDate_4.toUpperCase().trim(),
				bidDate_5.toUpperCase().trim(), bidDate_6.toUpperCase().trim()));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.checkBiddingWithinList(bidDateList));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3102", description = "To verify that when user select next 7 days, how the result are been displayed.")
	public void tc3102(String emailAddress, String password, String bidDate_1) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnNextSevenDays();
		Assert.assertTrue(projectResultsPage.checkBiddingFilterForNextDaysMonth(bidDate_1));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3103", description = "To verify that when user select next 15 days, how the result are been displayed.")
	public void tc3103(String emailAddress, String password, String bidDate_2) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.clickOnNextFifteenDays();
		Assert.assertTrue(projectResultsPage.checkBiddingFilterForNextDaysMonth(bidDate_2));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3104", description = "To verify that when user select next 1 month, how the result are been displayed.")
	public void tc3104(String emailAddress, String password, String bidDate_3) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnNextOneMonthDays();
		Assert.assertTrue(projectResultsPage.checkBiddingFilterForNextDaysMonth(bidDate_3));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3095", description = "Verifying Redesigned header.")
	public void tc3095(String emailAddress, String password, String searchText, String homeStringCompare,
			String helpULRContain) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		homePage.clickOnDodgeLogo();
		Assert.assertTrue(homePage.checkControlOnHomePage(homeStringCompare));
		HelpPage helpPage = homePage.clickOnHelpLink();
		Assert.assertTrue(helpPage.checkHelpPageDisplayed(helpULRContain));
		helpPage.returnToDefaultContentPage();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3098", description = "Verify the default status of plan class facet scenario3.")
	public void tc3098(String emailAddress, String password, String filterApperanceCheck_1,
			String filterApperanceCheck_2, String attributeName) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		projectResultsPage.clickOnPlanClassSheetFilter();
		Assert.assertTrue(projectResultsPage.checkProjectFilterApperance(filterApperanceCheck_1, filterApperanceCheck_2,
				attributeName), "Plan Class sheet filter is not present after Spec Division filter.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3105", description = "To verify that when user select custom date range, how the result are been displayed.")
	public void tc3105(String emailAddress, String password, String bidFromDate, String bidToDate)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		String iCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.setBidCustomRange(bidFromDate, bidToDate);
		String iCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(iCountBefore, iCountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify functionality after clicking on traingle icon Spec alerts, Spec division , Tracking list filters (TC22983)")
	public void tc3540(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.clickOnValuationFilter();
		Assert.assertFalse(projectResultsPage.IsSPEC_DIVISION_ArrowUpFilter_displayed());
		Assert.assertFalse(projectResultsPage.IsTRACKING_LIST_ArrowUpFilter_displayed());
		Assert.assertFalse(projectResultsPage.IsValuationArrowUpFilterDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3543", description = "Verify the heading of the see more pop up box of Spec Division filter. (TC22995)")
	public void tc3543(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertEquals(projectResultsPage.getspecDivisionNav_RefineYourResults_txt(), Expected);
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3544", description = "Verify the footer of the see more pop up box of Spec Division filter. (TC22997)")
	public void tc3544(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.verifySpecDivisionSeeMorePopupFooter(),
				"spec division see more popup footer is displayed.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3545", description = "Verify the update button is replaced by update results button in the see  more pop up box of Spec division filter. (TC22998)")
	public void tc3545(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertEquals(projectResultsPage.getspecDivisionNav_UpdateResults_btn_Txt(), Expected);
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Geography , verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23361)")
	public void tc3553(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(3, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(3, "geographyPopupUASStatesList");
		projectResultsPage.SelectOptionsFromTheList(5, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Geography , verify when a parent filter is selected  then parent filter is shown in filter crumb (TC23364)")
	public void tc3554(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.SelectOptionsFromTheList(3, "StateRegionFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Action Stage Type , verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23366)")
	public void tc3555(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnACTION_STAGE_CATEGORYUpdateFancyBoxbtn();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnACTION_STAGE_CATEGORYUpdateFancyBoxbtn();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Action Stage Type , verify when a parent filter is selected, then parent filter is shown in filter crumb (TC23367)")
	public void tc3556(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Project Groups , verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23368)")
	public void tc3557(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(4, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Project Groups , verify when a parent filter is selected, then parent filter is shown in filter crum (TC23369)")
	public void tc3558(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Trade filter, verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23370)")
	public void tc3559(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getFilterScrumbTxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Project Groups , verify when a parent filter is selected, then parent filter is shown in filter crumb (TC23371)")
	public void tc3560(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.SelectOptionsFromTheList(3, "Trades_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Materials/Equip filter, verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23372)")
	public void tc3561(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Materials/Equip filter, verify when a parent filter is selected, then parent filter is shown in filter crumb (TC23373)")
	public void tc3562(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.SelectOptionsFromTheList(3, "materialsEquipFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3114", description = "Filters for structural properties - To Verify the UI of the Structural Properties filter (TC7263)")
	public void tc3114(String emailAddress, String password, String lbl1, String lbl2, String lbl3, String lbl4,
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

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3115", description = "Filters for structural properties -To Verify the Error message displayed if user wrong values for the Structural Properties (TC7264)")
	public void tc3115(String emailAddress, String password, String err1, String err2, String err3, String err4)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStrucPropDDList();
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		Assert.assertTrue(CommonUtils.checkStringContains(err1, projectResultsPage.getStructuralErrorStatus()));
		Assert.assertTrue(CommonUtils.checkStringContains(err2, projectResultsPage.getStructuralErrorStatus()));
		Assert.assertTrue(CommonUtils.checkStringContains(err3, projectResultsPage.getStructuralErrorStatus()));
		Assert.assertTrue(CommonUtils.checkStringContains(err4, projectResultsPage.getStructuralErrorStatus()));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3124", description = "Filters for structural properties - To Verify that user is able to search for Structural Properties with out selecting any values (TC7273)")
	public void tc3124(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		Assert.assertEquals(projectResultsPage.getStructuralErrorStatus(), Expected);
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Geography filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23374)")
	public void tc3563(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnGEOGRAPHY_CountyFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "GEOGRAPHY_COUNTYFilterList");
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.SelectOptionsFromTheList(2, "GEOGRAPHY_COUNTYFilterList");
		Assert.assertTrue(StringUtils.isNotBlank(projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Geography filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23375)")
	public void tc3564(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnGEOGRAPHY_COUNTYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnGEOGRAPHYPopupprojectCountyNavLink();
		projectResultsPage.SelectOptionsFromTheList(3, "COUNTYPopupUASCountyList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.clickOnGEOGRAPHY_COUNTYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnGEOGRAPHYPopupprojectCountyNavLink();

		projectResultsPage.SelectOptionsFromTheList(3, "COUNTYPopupUASCountyList");
		projectResultsPage.SelectOptionsFromTheList(5, "COUNTYPopupUASCountyList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3565", description = "For Action Stage filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23376)")
	public void tc3565(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOn_ACTION_STAGE_CODE_Filter();
		projectResultsPage.SelectOptionsFromTheList(3, "actionStageCodeFacetList");
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.SelectOptionsFromTheList(2, "actionStageCodeFacetList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(StringUtils.isNotBlank(projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Action Stage filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23377)")
	public void tc3566(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnACTION_STAGE2_SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "BiddingOptionsFilterListFromPopup");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.clickOnACTION_STAGE2_SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "BiddingOptionsFilterListFromPopup");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Projects Group filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23378)")
	public void tc3567(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.SelectOptionsFromTheList(2, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(StringUtils.isNotBlank(projectResultsPage.getcrumbSecondFiltertxt()));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Projects Group filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23379)")
	public void tc3568(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectGroupsParent2FilterList");
		projectResultsPage.SelectOptionsFromTheList(2, "ProjectGroupsParent2FilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(4, "CommercialOptionsFilterListFromPopup");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommercialOptionsFilterListFromPopup");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Trade filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23380)")
	public void tc3569(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.SelectOptionsFromTheList(3, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.SelectOptionsFromTheList(2, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(StringUtils.isNotBlank(projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Trade filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23381)")
	public void tc3570(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnSpecificTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(6, "SpecificTrades_BuildingUtilities_PopopFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		projectResultsPage.clickOnSpecificTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "SpecificTrades_BuildingUtilities_PopopFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Materials/Equip filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23382)")
	public void tc3571(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquip_LHS_FilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnMaterialsEquip2Filter();
		projectResultsPage.SelectOptionsFromTheList(3, "materialsEquip2_LHS_FilterList");
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.clickOnMaterialsEquip2Filter();
		projectResultsPage.SelectOptionsFromTheList(2, "materialsEquip2_LHS_FilterList");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(StringUtils.isNotBlank(projectResultsPage.getcrumbSecondFiltertxt()));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Materials/Equip filter, when child(ren) filter are selected from See More, then selected child(ren) filter are shown in filter crumb (TC23383)")
	public void tc3572(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.clickOnMaterials_Equip2SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(7, "BuildingCLG_WaterProofing_FilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbThirdFiltertxt());
		Assert.assertNotEquals(Before_FilterName, projectResultsPage.getcrumbForthFiltertxt());
		projectResultsPage.clickOnMaterials_Equip2SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "BuildingCLG_WaterProofing_FilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3573", description = "For Geography filter, when all children filter are selected for a parent, then the parent will be shown in filter crumb (TC23385)")
	public void tc3573(String emailAddress, String password, String Expected, String USA) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), USA);
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		String Before_FilterName = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnGEOGRAPHY_CountyFilter();
		projectResultsPage.ClickOnCounties_LHS_SelectAll_Btn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), USA);
		Assert.assertTrue(
				CommonUtils.checkStringContains(Before_FilterName, projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3573", description = "For Geography filter, when all children filter are selected from See More, then the parent filter will be shown in filter crumb (TC23386)")
	public void tc3574(String emailAddress, String password, String Expected, String USA) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.ClickOnStateRegionPopupSelectAllStates_checkbox();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), USA);
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		projectResultsPage.ClickOnGEOGRAPHYPopupprojectCountyNavLink();
		projectResultsPage.ClickOnGEOGRAPHY_PopupSelectAllCounties_checkbox();
		String eExpected = projectResultsPage.getGEOGRAPHY_AllCountiesOfAlabamaState_checkbox_txt();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), USA);
		Assert.assertTrue(CommonUtils.checkStringContains(eExpected, projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = " For Action Stage filter, when all children filter are selected for a parent from See More, then the parent will be shown in filter crumb (TC23388)")
	public void tc3576(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String eExpected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), eExpected);
		projectResultsPage.clickOnACTION_STAGE2_SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3578", description = "For Projects Group filter, when all children filter are selected for a parent from See More, then the parent will be shown in filter crumb (TC23390)")
	public void tc3578(String emailAddress, String password, String Expected, String eExpected)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), eExpected);
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbThirdFiltertxt()));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Trade filter, when all children filter are selected for a parent from See More, then the parent will be shown in filter crumb (TC23392)")
	public void tc3580(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String eExpected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt().toUpperCase(), eExpected);
		projectResultsPage.clickOnSpecificTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt().toUpperCase(), eExpected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "For Materials/Equip filter,when all children filter are selected for a parent from See More, then the parent will be shown in filter crumb (TC23394)")
	public void tc3582(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String eExpected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt().toUpperCase(), eExpected);
		projectResultsPage.clickOnMaterials_Equip2SeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3583", description = "For Geography filter, when all State and All county will be selected then only USA will be shown in filters crumb (TC23395)")
	public void tc3583(String emailAddress, String password, String USA, String ClearAll) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.ClickOnStateRegionPopupSelectAllStates_checkbox();
		projectResultsPage.ClickOnGEOGRAPHYPopupprojectCountyNavLink();
		projectResultsPage.ClickOnGEOGRAPHY_PopupSelectAllCounties_checkbox();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), USA);
		Assert.assertEquals(projectResultsPage.getFilterCrumb_AppliedFilterList_Size(), 1);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3420", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for StateRegion (TC22543)")
	public void tc3420(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(3, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(5, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), Expected);
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3436", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Action Stage filters (TC22560)")
	public void tc3436(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(5, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3444", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Project type filter. (TC22568)")
	public void tc3444(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify that the SeeMore pop is integrated in the left nav  of Spec Alerts filters (TC22572)")
	public void tc3448(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsspecAlerts_SeeMore_Popup_btn_Displayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the option  selected in see more pop up  should also reflect in the Spec Alerts filter (TC22574)")
	public void tc3449(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		String Expected = projectResultsPage.getspecAlertSeeMorePopUpFirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertTrue(projectResultsPage.getspecAlertLHSFirstOption_lbl().contains(Expected));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify that the counts and values of the selected options in the see more pop-up of Spec Alerts filter is reflected in the filter section(when zero item is not present). (TC22575)")
	public void tc3450(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		String Expected = projectResultsPage.getspecAlertSeeMorePopUpFirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertTrue(projectResultsPage.getspecAlertLHSFirstOption_lbl().contains(Expected));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify that the counts and values of the selected options in the see more pop-up of Spec Alerts filter is reflected in the filter section(when zero item is  present). (TC22576)")
	public void tc3451(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		String Expected = projectResultsPage.getspecAlertSeeMorePopUpFirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertTrue(projectResultsPage.getspecAlertLHSFirstOption_lbl().contains(Expected));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3452", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Spec Alerts filter (TC22577)")
	public void tc3452(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(5, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for Spec Alerts filter (TC22578)")
	public void tc3453(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the content of the pop up opened when clicked on hyperlink for Spec Alerts filter (TC22579)")
	public void tc3454(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 5);
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the functionality after clicked on Clear All link below keyword search section for Spec Alerts filter (TC22580)")
	public void tc3455(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertFalse(projectResultsPage.IsspecAlertLHSFirstOption_cbk_Selected());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify the option  selected in see more pop up  should also reflect in the Tracking lists  filter (TC22582)")
	public void tc3457(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(10, "trackingList_PopupOptionList");
		String Expected = projectResultsPage.gettrackingList_PopupOption_lbl_ByIndex(10);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertEquals(projectResultsPage.gettrackingList_LHSFirstOption_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify that the counts and values of the selected options in the see more pop-up of Tracking list filter is reflected in the filter section(when zero item is not present). (TC22583)")
	public void tc3458(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingList_PopupOptionList");
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		String Expected = projectResultsPage.gettrackingList_PopupOption_Second_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertEquals(projectResultsPage.gettrackingList_LHSFirstOption_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3460", description = "Verify that the counts and values of the selected options in the see more pop-up of Tracking list filter is reflected in the filter section(when zero item is present). (TC22584)")
	public void tc3459(String emailAddress, String password, String ExpectedUnused) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(10, "trackingList_PopupOptionList");
		String Expected = projectResultsPage.gettrackingList_PopupOption_lbl_ByIndex(10);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertEquals(projectResultsPage.gettrackingList_LHSFirstOption_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3460", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Tracking lists filter (TC22585)")
	public void tc3460(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(4, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3460", description = "Verify the functionality of hyperlink present on the top for more than 3 slections (TC22586)")
	public void tc3461(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3460", description = "Verify the content of the pop up opened when clicked on hyperlink for Tracking lists filter (TC22587)")
	public void tc3462(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3468", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for construction type filter (TC22593)")
	public void tc3468(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(4, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3476", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for ownership type filter (TC22602)")
	public void tc3476(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(4, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3484", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Special Conditions filter (TC22610)")
	public void tc3484(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(5, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3491", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Trade filter (TC22617)")
	public void tc3491(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(5, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3499", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for  Material/Equip filter (TC22626)")
	public void tc3499(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getSizeOfFilterCrumb_AllClose_btn();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.scrollDownToFilter("materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3525", description = "Verify the name of options under Report filters (TC22868)")
	public void tc3525(String emailAddress, String password, String ExpectedOption1, String ExpectedOption2)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		Assert.assertTrue(projectResultsPage.getReportFilter_lbl(0).contains(ExpectedOption1));
		Assert.assertEquals(projectResultsPage.getReportFilter_lbl(1), ExpectedOption2);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3526", description = "Verify the New name of Content Type (TC22869)")
	public void tc3526(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.getFIND_INFilter_lbl(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3302", description = "Verify  Special Filters options in project result page (TC22359)")
	public void tc3302(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4,
			String trackName) throws InterruptedException {
		List<String> SpecialFilterListExpected = new ArrayList<String>(Arrays.asList(opt1, opt2, opt3, opt4));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.checkSpecialFiltersOptions(SpecialFilterListExpected));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3309", description = "Verify Structural Properties:  “Add Properties instead of “Modify” (TC22374)")
	public void tc3309(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertEquals(projectResultsPage.getStructuralPropLink_txt(), Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "SeeMorePopup_Height_Width", description = "Verify the see more pop-up for project filters - Materials/Equipment (TC22418)")
	public void tc3324(String emailAddress, String password, String height, String width) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Height(), Integer.parseInt(height));
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Width(), Integer.parseInt(width));
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountBeforeClose = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.PopUpClose();
		String CountAfterClose = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeClose, CountAfterClose);
		String CountBeforeOutSide = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.ClickOnFilterpop_Overlay();
		String CountAfterOutSide = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeOutSide, CountAfterOutSide);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "SeeMorePopup_Height_Width", description = "Verify clicking on the pop-up (TC22425)")
	public void tc3330(String emailAddress, String password, String height, String width) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Height(), Integer.parseInt(height));
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Width(), Integer.parseInt(width));
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountBeforeClose = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.PopUpClose();
		String CountAfterClose = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeClose, CountAfterClose);
		String CountBeforeOutSide = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.ClickOnFilterpop_Overlay();
		String CountAfterOutSide = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeOutSide, CountAfterOutSide);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "SeeMorePopup_Height_Width", description = "Verify clicking on the pop-up for Project Type Filter (TC22429)")
	public void tc3334(String emailAddress, String password, String height, String width) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Height(), Integer.parseInt(height));
		Assert.assertEquals(projectResultsPage.GetFilterFacetPopUp_Width(), Integer.parseInt(width));
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBeforeClose = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.PopUpClose();
		String CountAfterClose = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeClose, CountAfterClose);
		String CountBeforeOutSide = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.ClickOnFilterpop_Overlay();
		String CountAfterOutSide = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeOutSide, CountAfterOutSide);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3340", description = "Verify the display section for applied Project Type filter at the filter crumb area, below the keyword search section (TC22435)")
	public void tc3340(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int eFilterCountSizeBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.SelectOptionsFromTheList(4, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText(Expected));
		projectResultsPage.ClickOncrumbSecondFilterClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3349", description = "Verify the display section for a applied bidding within filter at the filter crumb area, below the keyword search section (TC22467)")
	public void tc3349(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "biddingWithin_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), Expected);
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3369", description = "Verify the functionality of the word Filters hyperlink for Special Conditions filter (TC22487)")
	public void tc3369(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), Expected);
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3114", description = "Verify the display section for Structural Properties at the filter crumb area, below the keyword search section (TC22525)")
	public void tc3402(String emailAddress, String password, String lbl1, String lbl2, String lbl3, String lbl4,
			String lbl5, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		Assert.assertTrue(projectResultsPage.IsStructuralPropLink_Displayed());
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		Assert.assertEquals(lbl1, projectResultsPage.getSquareArealbl());
		Assert.assertEquals(lbl2, projectResultsPage.getNoOfBuildingslbl());
		Assert.assertEquals(lbl3, projectResultsPage.getStoriesAboveGradelbl());
		Assert.assertEquals(lbl4, projectResultsPage.getStoriesBelowGradelbl());
		Assert.assertEquals(lbl5, projectResultsPage.getBuildingFramelbl());
		Assert.assertTrue(projectResultsPage.CheckisSelected_StructuralProperties());
		Assert.assertTrue(projectResultsPage.checkDefaultValueOfStructPropDDL());
		projectResultsPage.SelectCheckboxFromStructProp(6);
		projectResultsPage.SelectCheckboxFromStructProp(7);
		projectResultsPage.SelectCheckboxFromStructProp(8);
		projectResultsPage.SelectCheckboxFromStructProp(9);
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), Expected);
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3235", description = "Verify the keyword text box is extended to 350 characters on the left nav of the Projects tab (TC21881)")
	public void tc3235(String emailAddress, String password, String searchtext, String Expected)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchtext);
		homePage.clickOnSearchButton();
		Assert.assertEquals(homePage.getsearchTxtField_length(), Integer.parseInt(Expected));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3246", description = "Verify when a country is Xed out, all the states and counties associated with the country disappear as well. (TC21961)")
	public void tc3246(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(7, "GEOGRAPHY_COUNTYFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(6, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		int before = projectResultsPage.getFilterCrumb_AppliedFilterList_Size();
		projectResultsPage.ClickOncrumbFirstFilterClose();
		int after = projectResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertNotEquals(before, after);
		Assert.assertEquals(after, 1);
		Assert.assertEquals(projectResultsPage.getcrumbFirstFiltertxt(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3260", description = "Verify the error message if 'To' value is lesser than a 'From' value. (TC22155)")
	public void tc3260(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnValuationFilter();
		projectResultsPage.ClickOnvalMinimumDropdown();
		projectResultsPage.ClickOnvalMinimumDropdownSeventhOption();
		projectResultsPage.ClickOnvalMaximumDropdown();
		projectResultsPage.ClickOnvalMaximumDropdownSecondOption();
		Assert.assertEquals(projectResultsPage.GetValuationStatus_text(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3271", description = "Verify the dropdown list becomes Select a Range if user changes Publish Date values. (TC22177)")
	public void tc3271(String emailAddress, String password, String Expected, String ErrorMsg)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPublishRangeFilter();
		projectResultsPage.enterPublichRangeFrom();
		projectResultsPage.enterPublichRangeTo();
		Assert.assertEquals(projectResultsPage.getPublishRange_DropDown_OptionText(), Expected);
		projectResultsPage.EnterPublichRangeFromMax();
		projectResultsPage.EnterPublichRangeToMin();
		Assert.assertEquals(projectResultsPage.getPublishRange_errorMessage(), ErrorMsg);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3277", description = "Verify a new popup opened by clicking on the rectangular icon, (TC22221)")
	public void tc3277(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecAlertFilter();
		Assert.assertEquals(projectResultsPage.getSpecAlertFilter_Exclude_SpecAlerts_tooltip(), Expected);
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.clickOnUpdateButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3280", description = "Verify that user can perform a dynamic search within the filter list inside the pop-up. (TC22313)")
	public void tc3280(String emailAddress, String password, String searchtext) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.addSearchTextIn_FilterCrumbPopup_Searchbox(searchtext);
		Assert.assertTrue(
				projectResultsPage.getFirstFilter_txt_From_FilterCrumbPopup_FilterList().contains(searchtext));
		projectResultsPage.ClickOnFilterpopClose();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		int ebefore = projectResultsPage.getCrumbFilterPopupCloseBtnListSize();
		projectResultsPage.clickOnCrumbFilterPopupFirstCloseBtn();
		projectResultsPage.clickOnCrumbFilterPopupDonebtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		int eafter = projectResultsPage.getCrumbFilterPopupCloseBtnListSize();
		Assert.assertNotEquals(ebefore, eafter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3106", description = "Custom bid date range (TC2419)")
	public void tc3106(String emailAddress, String password, String bidFromDate, String Expected)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.setBidCustomRange_FromDate(bidFromDate);
		Assert.assertEquals(projectResultsPage.getCustomRangeBiddingRadio_errorMsg_txt(), Expected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3110", description = "Custom bid date range (TC2419)")
	public void tc3110(String emailAddress, String password, String defMinVal, String defMaxVal, String FirstExpected,
			String SecondExpected, String ThirdExpected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnValuationFilter();
		Assert.assertEquals(projectResultsPage.getvalMinimumDropdowntxt(), defMinVal);
		Assert.assertEquals(projectResultsPage.getvalMaximumDropdowntxt(), defMaxVal);
		Assert.assertEquals(projectResultsPage.getVALUATION_Filter_radiobtn_label(0), FirstExpected);
		Assert.assertEquals(projectResultsPage.getVALUATION_Filter_radiobtn_label(1), SecondExpected);
		Assert.assertEquals(projectResultsPage.getVALUATION_Filter_radiobtn_label(2), ThirdExpected);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Structural Property option under More filter section for Out side the License List view for any user (TC15912)")
	public void tc3196(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectCheckboxFromStructProp(6);
		projectResultsPage.SelectCheckboxFromStructProp(7);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.ISCheckboxFromStructProp_Selected(6));
		Assert.assertTrue(projectResultsPage.ISCheckboxFromStructProp_Selected(7));
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Special Condition option under More filter section for Out side the License List view for any user (TC15910)")
	public void tc3195(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Ownership Type option under More filter section for Out side the License List view for any user (TC15905)")
	public void tc3194(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Construction Type option under More filter section for Out side the License List view for any user (TC15904)")
	public void tc3193(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Spec Division option under More filter section for Out side the License List view for any user (TC15903)")
	public void tc3192(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "specDivisionFilterListPopup");
		projectResultsPage.SelectOptionsFromTheList(2, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Is_specDivisionPopup_ThirdCheckbox_Selected());
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Valuation option under More filter section for Out side the License List view for any user (TC15902)")
	public void tc3191(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnValuationFilter();
		projectResultsPage.clickOnvaluationListsecondRadio();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.isvaluationListsecondRadioSelected());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Report panel for Not in my license radio button (TC16321)")
	public void tc3214(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultsPage.Check_ProjectResultList_Size());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verification of the functionality of the Update button the new lightbox dialog of spec alerts  filter (TC15370)")
	public void tc3184(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specAlertSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verification of the design of the new lightbox dialog in the spec alerts  filter (TC15367)")
	public void tc3183(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "PlusUser", description = "Verifying My Preferences link under My Account drop list in the header for Plus license (TC14798)")
	public void TC3145(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.isBreadcrumbAndMyAccountMyPreferencesDisplayed(),
				"My-Account---My-Preferences");
		Assert.assertTrue(myPreferencesPage.myPreferencesOptionDisplayed(), "My Preferences option");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "PlatinumUser", description = "Verifying My Preferences link under My Account drop list in the header for Platinum license (TC14797)")
	public void TC3144(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.isBreadcrumbAndMyAccountMyPreferencesDisplayed(),
				"My-Account---My-Preferences");
		Assert.assertTrue(myPreferencesPage.myPreferencesOptionDisplayed(), "My Preferences option");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3141", description = "Verification of inclusion of building frame keyword in structural properties filter criteria in the project print list output. (TC13433)")
	public void tc3141(String emailAddress, String password, String ForthFilterHeader, String ForthFilterSubHeader1,
			String ForthFilterSubHeader2) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 0, 2);
		projectResultsPage.SelectCheckboxFromStructProp(6);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();
		printProjectListPage.ClickonincludeFilters_cbk();
		Assert.assertEquals(printProjectListPage.getForthFilterHeader_lbl(), ForthFilterHeader);
		Assert.assertEquals(printProjectListPage.getForthFilterSubHeader1_lbl(), ForthFilterSubHeader1);
		Assert.assertEquals(printProjectListPage.getForthFilterSubHeader2_lbl(), ForthFilterSubHeader2);
		printProjectListPage.clickOnBackButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3140", description = "Verification of inclusion of trades filter criteria in the project print list output. (TC13432)")
	public void tc3140(String emailAddress, String password, String ForthFilterHeader, String ForthFilterSubHeader1,
			String ForthFilterSubHeader2) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();
		printProjectListPage.ClickonincludeFilters_cbk();
		Assert.assertEquals(printProjectListPage.getForthFilterHeader_lbl(), ForthFilterHeader);
		Assert.assertEquals(printProjectListPage.getForthFilterSubHeader1_lbl(), ForthFilterSubHeader1);
		Assert.assertEquals(printProjectListPage.getForthFilterSubHeader2_lbl(), ForthFilterSubHeader2);
		printProjectListPage.clickOnBackButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3139", description = "Verification of inclusion of specAlerts filter criteria in the project print list output. (TC13431)")
	public void tc3139(String emailAddress, String password, String ThirdFilterHeadertxt) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "specAlertSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();
		printProjectListPage.ClickonincludeFilters_cbk();
		Assert.assertTrue(printProjectListPage.getThridFilterHeader_txt().contains(ThirdFilterHeadertxt));
		printProjectListPage.clickOnBackButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3138", description = "Verification of inclusion of tracking list filter criteria in the project print list output. (TC13430)")
	public void tc3138(String emailAddress, String password, String ThirdFilterHeadertxt) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingList_PopupOptionList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();
		printProjectListPage.ClickonincludeFilters_cbk();
		Assert.assertEquals(printProjectListPage.getThridFilterHeader_txt(), ThirdFilterHeadertxt);
		printProjectListPage.clickOnBackButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "PlatinumUser", description = "Verify the Options in My Preference page (TC16341)")
	public void TC3217(String emailAddress, String password) {
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.isBreadcrumbAndMyAccountMyPreferencesDisplayed(),
				"My-Account---My-Preferences");
		Assert.assertTrue(myPreferencesPage.myPreferencesOptionDisplayed(), "My Preferences option");
		Assert.assertTrue(myPreferencesPage.verifyPrivateNotesChecked());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3302", description = "Verify if SpecialFilters is showing in the left-nav filter when project search request originated from Projects search page. (TC25097)")
	public void tc3602(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4,
			String trackName) throws InterruptedException {
		List<String> SpecialFilterListExpected = new ArrayList<String>(Arrays.asList(opt1, opt2, opt3, opt4));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.checkSpecialFiltersOptions(SpecialFilterListExpected));
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(2);
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3302", description = "Verify if SpecialFilters is showing in the left-nav filter when project search request originated from  MySavedSearches page. (TC25098)")
	public void tc3603(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4,
			String trackName) throws InterruptedException {
		List<String> SpecialFilterListExpected = new ArrayList<String>(Arrays.asList(opt1, opt2, opt3, opt4));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.checkSpecialFiltersOptions(SpecialFilterListExpected));
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(2);
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3302", description = "Verify if SpecialFilters is showing in the left-nav filter when project search request originated from Projects search page. (TC25097)")
	public void tc3604(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4,
			String trackName) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnExistingTrackingListCompanies(trackName);
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isSpecialFilterDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3605", description = "Verify if Special Filters is showing in the left-nav filter when saved search execution via DGN Home page Dashboard link. (TC25105)")
	public void tc3605(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4)
			throws InterruptedException {
		List<String> SpecialFilterListExpected = new ArrayList<String>(Arrays.asList(opt1, opt2, opt3, opt4));
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnfirstSavedSearch();
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.checkSpecialFiltersOptions(SpecialFilterListExpected));
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(2);
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3605", description = "Verify if SpecialFilters is showing in the left-nav filter when Tracking list execution via DGN Home page Dashboard link. (TC25106)")
	public void tc3606(String emailAddress, String password, String opt1, String opt2, String opt3, String opt4)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstTrackingList();
		Assert.assertTrue(projectResultsPage.isSpecialFilterDisplayed());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3624", description = "Verify the message in the Geographical map, when there is no data in the map. (TC25281)")
	public void tc3624(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.GetProjectDensitybyLocation_ErrorMsg(), Expected);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3623", description = "Verify if Division number suffix is removed from Table of Contents in the filter crumb. (TC25280)")
	public void tc3623(String emailAddress, String password, String TableofContents) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.enterTextInSectionVisualizationFindInSearchBox(TableofContents);
		projectResultsPage.clickSectionVisualizationSectionsSearchBtn();
		String selectedSection = projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.getNamesOfAllAppliedFilters();
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(selectedSection));
		Assert.assertEquals(projectResultsPage.getcrumbSecondFiltertxt(), TableofContents);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3622", description = "Verify the filterCrumb behaviour for spec division (TC25275)")
	public void tc3622(String emailAddress, String password, String Filters, String FormattedText)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.SelectOptionsFromTheList(5, "ChartView_DivGBarList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.getcrumbFirstFiltertxt().contains(Filters));
		Assert.assertTrue(projectResultsPage.getNamesOfAllAppliedFilters().contains(FormattedText));

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3622", description = "Verify if multi selection of divG bar in the Bar chart is allowed. (TC25121)")
	public void tc3615(String emailAddress, String password, String Filters, String FormattedText)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.SelectOptionsFromTheList(3, "ChartView_DivGBarList");
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3622", description = "Verify when a previously selected divG bar is deselected from division chart, Section chart refreshed accordingly. (TC25120)")
	public void tc3614(String emailAddress, String password, String Filters, String FormattedText)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.SelectOptionsFromTheList(1, "ChartView_DivGBarList");
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		String SectionCountBefore = projectResultsPage.getDashboard1_SectionsCount();
		projectResultsPage.ClickOnChartView_FirstDivGBar();
		projectResultsPage.waitforLoadingRing();
		String SectionCountAfter = projectResultsPage.getDashboard1_SectionsCount();
		Assert.assertNotEquals(SectionCountBefore, SectionCountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify choosing Version 1 Reports from the Special Filter(TC22360)")
	public void tc3303_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(1);
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Verify clicking on triangle icon for less than 9 selections. (TC22383)")
	public void tc3312(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		int size = savedSearchesPage.getSavedSearchesSize();
		homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertTrue(projectResultsPage.Are_trackingListFacetListOptionsVisible_AsPerTheLicenses(size));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "Verify that Building Trade & Materials section is removed from Trades pop-up (TC22419)")
	public void tc3325(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.Check_CommonPopupParentFilter_lblList("Building Trade & Materials"));
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "Verify that Equipment/Material/Services section is removed from  the see more pop-up of Materials/Equipment (TC22420)")
	public void tc3326(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.Check_CommonPopupParentFilter_lblList("Equipment/Material/Services"));
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3553", description = "Verify the content of popup box of Spec Division filter (TC22524)")
	public void tc3401(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		// projectResultsPage.SelectOptionsFromTheList(4,
		// "specDivisionFilterList");StateRegionFilterList
		projectResultsPage.SelectOptionsFromTheList(4, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		Assert.assertTrue(CommonUtils.checkStringContains(Expected, projectResultsPage.getFilterScrumbTxt()));
		projectResultsPage.CheckFilterCrumbPopupFunctionality();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify If the content-scope was explicitly set by the user, The content-scope will remains unchanged when all Spec Division filters are removed (TC23597)")
	public void tc3589(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "FindIn_LHS_CbkList");
		projectResultsPage.waitforLoadingRing();

		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();

		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));

		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));

		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnspecDivisionFilterListPopup_LastOption();
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify If the content-scope was implicitly set by the user, the content-scope is reverted to the value it had before when all Spec Division filters are removed (TC23599)")
	public void tc3590(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnREPORTSFilter();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(0));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify If the content-scope was implicitly set by the user, the content-scope is reverted to the value it had before when all Spec Division filters are removed (TC23601)")
	public void tc3591(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnREPORTSFilter();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(0));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify If the content-scope was implicitly set by the user, the content-scope is reverted to the value it had before when all Spec Division filters are removed (TC23602)")
	public void tc3592(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.clickOnREPORTSFilter();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
		
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnspecDivisionFilterListPopup_LastOption();
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Ability to sort projects by project type after opening a Project tracking list (TC17733)")
	public void tc3221(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstTrackingList();
		projectResultsPage.selectSortingOption("Project Type - Ascending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(true));
		projectResultsPage.selectSortingOption("Project Type - Descending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(false));

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Ability to sort projects by action stage in Project save search list (TC17743)")
	public void tc3223(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnfirstSavedSearch();
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
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TestDataUser", description = "Ability to sort projects by action stage in Project tracking  list (TC17744)")
	public void tc3224(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstTrackingList();
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

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "[Outside License] Add navigators to 'outside of license' project list view' -To Verify that user is able to narrow the Project search result for Out of License Projects using filters (TC10070)")
	public void tc3133(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();

		int CountBeforeGeo = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(8, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterGeo = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeGeo, CountAfterGeo));

		int CountBeforeAct = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterAct = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeAct, CountAfterAct));

		int CountBeforeProjGp = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(8, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterProjGp = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeProjGp, CountAfterProjGp));

		int CountBeforeBidDate = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.clickOnNextOneMonthDays();
		projectResultsPage.waitforLoadingRing();
		int CountAfterBidDate = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeBidDate, CountAfterBidDate));

		int CountBeforeValuation = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnValuationFilter();
		projectResultsPage.clickOnvaluationListFirstRadio();
		projectResultsPage.waitforLoadingRing();
		int CountAfterValuation = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeValuation, CountAfterValuation));

		int CountBeforeSpecDiv = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(8, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterSpecDiv = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeSpecDiv, CountAfterSpecDiv));

		int CountBeforeConstru = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterConstru = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeConstru, CountAfterConstru));

		int CountBeforeOwner = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterOwner = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeOwner, CountAfterOwner));

		int CountBeforeMaterial = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		int CountAfterMaterial = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeMaterial, CountAfterMaterial));

		int CountBeforeStructProp = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int CountAfterStructProp = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(CountBeforeStructProp, CountAfterStructProp));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3081", description = "Verify the USA ,International and Canada panel in Geography section for the user tagged to an international license. (TC16315)")
	public void tc3211(String emailAddress, String password, String search) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertEquals(projectResultsPage.getGEOGRAPHY_COUNTRY_LHS_lbl(0), "USA");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the display section for an applied Report contains filter at the filter crumb area, below the keyword search section (TC22499)")
	public void tc3381(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "ProjectsOutSideOfSubscription", description = "Verify the Spec Division filters can be removed (TC23595)")
	public void tc3588(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		int projCountBefore = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		int projCountAfterSpec = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(projCountBefore, projCountAfterSpec));
		projectResultsPage.ClickOncrumbFilterClose(4);
		int projCountAfterSpecX = projectResultsPage.getExactProjectCount();
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(projCountBefore, projCountAfterSpecX));
		projectResultsPage.SelectOptionsFromTheList(3, "specDivisionFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		int projCountAfterSpecRemove = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(projCountAfterSpecRemove, projCountBefore);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3111", description = "Organize project types by general building categories in the select more than one dialog (TC3217)")
	public void tc3111_tc3112(String emailAddress, String password, String PrjTyp_1, String PrjTyp_2, String PrjTyp_3,
			String PrjTyp_4, String PrjTyp_5) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(PrjTyp_1));
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(PrjTyp_2));
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(PrjTyp_3));
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(PrjTyp_4));
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(PrjTyp_5));
		projectResultsPage.clickOnUpdateButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3260", description = "Verify the selection/change in selection of Valuation Range or change in selection of a radio button option triggers a new filter search (TC22165)")
	public void tc3262(String emailAddress, String password, String Expected) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		int projCountBefore = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnValMinimumDdAndSelectFirstOpt();
		projectResultsPage.clickOnValMaximumDdAndSelectSecondOpt();
		int projCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertNotEquals(projCountBefore, projCountAfter);
		int projCountBefore1 = projectResultsPage.getExactProjectCount();
		projectResultsPage.ClickOnvalMaximumDropdownEight_thOption();
		projectResultsPage.ClickOnvalMinimumDropdown();
		projectResultsPage.ClickOnvalMinimumDropdownSeventhOption();
		int projCountAfter1 = projectResultsPage.getExactProjectCount();
		projectResultsPage.clickOnvaluationListsecondRadio();
		Assert.assertNotEquals(projCountBefore1, projCountAfter1);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "TC3237", description = "Verify saved searches upto 350 characters is successfully saved and run. (TC21883)")
	public void tc3237(String emailAddress, String password, String searchtxt) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchtxt);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultsPage.checklnkProjDocHighilight());
		int CountBefore = projectResultsPage.getExactProjectCount();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		int CountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(CountBefore, CountAfter);
	}
}

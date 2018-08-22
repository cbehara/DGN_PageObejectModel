package com.ddaqe.dgn_project_filter;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HighlightKeywordsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectNotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNProjectFilter_Part2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Test Data cleanup script")
	public void A_testdataScript_Part2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.deleteSavedSearches();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Material/Equip")
	public void tc3377(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		Assert.assertTrue(projectResultsPage.IsContratorsEquipCheckBoxSelected());
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsContratorsEquipCheckBoxUnchecked());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Spec Division")
	public void tc3398(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsaddendaSpecDivisionCheckBoxUnchecked());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the display section for more than 3 options are applied of Spec Division at the filter crumb area, below the keyword search section")
	public void tc3397(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getspecDivisionFilterLHS_FirstCheckbox_lbl()
				.contains(projectResultsPage.getcrumbSecondFiltertxt()));
		int eFilterCountSizeBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		int eFilterCountSizeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "To verify the functionality after clicked on Clear All link below keyword search section for Bidding within filter")
	public void tc3504(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinNext7DaysOption();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "To verify there is a dropdown on the left side of the Quick search box in the Project Results page")
	public void tc3506(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isHomeSearchDropDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify Quick search box in the company contact page same as it is present in the company results page. (TC22700)")
	public void tc3509(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage CompaniesPage = homePage.clickOnCompaniesLink();
		CompaniesPage.clickOnCompanyContactsLink();
		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for geography filter (TC22774)")
	public void tc3513(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for State/Region filter (TC22779)")
	public void tc3518(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegion_FirstCheckBox();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the display section of Publish Range filter after applying a value (TC22782)")
	public void tc3521(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPublishRangeFilter();
		projectResultsPage.getPublishRange_DropDown_OptionText();
		Assert.assertTrue(projectResultsPage.IsPublishRange_DropdownOptionDisplayed());
		projectResultsPage.enterPublichRangeFrom();
		projectResultsPage.enterPublichRangeTo();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the display section for Valuation filter at the filter crumb area, below the keyword search section (TC22783)")
	public void tc3522(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnValMinimumDdAndSelectFirstOpt();
		String ValFrom = projectResultsPage.getvalMinimumDropdowntxt();
		projectResultsPage.clickOnValMaximumDdAndSelectSecondOpt();
		String ValTo = projectResultsPage.getvalMaximumDropdowntxt();
		projectResultsPage.clickOnvaluationListsecondRadio();
		int AppliedFilterCountBefore = projectResultsPage.getAppliedFilterCount();
		Assert.assertTrue(CommonUtils.checkStringContains(ValFrom, projectResultsPage.getcrumbSecondFiltertxt()));
		Assert.assertTrue(CommonUtils.checkStringContains(ValTo, projectResultsPage.getcrumbSecondFiltertxt()));
		projectResultsPage.ClickOncrumbSecondFilterClose();
		int AppliedFilterCountAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(AppliedFilterCountBefore, AppliedFilterCountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the the new look and feel of the Valuation filter in the left nav on Projects result page (TC22138)")
	public void tc3257(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isvalMinimumDropdownDisplayed());
		Assert.assertTrue(projectResultsPage.isvalvalMaximumDropdownDisplayed());
		Assert.assertTrue(projectResultsPage.isvaluationListsecondRadioSelected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify a Stage 2 appears on selection of any option under Action Stage filter (TC22784)")
	public void tc3523(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify there is a SelectAll and DeselectAll below Stage 2 of Action Stage filter (TC22785)")
	public void tc3524(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isactionStage_Code_SelectAllLinkDisplayed());
		Assert.assertTrue(projectResultsPage.isactionStage_Code_SelectAllLinkDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the plan class facet scenario1.")
	public void tc3096(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.clickOnPlanOpenInFindInFilter();
		Assert.assertTrue(projectResultsPage.isDisplayedPlanclassSheetFilter(),
				"Plan Class sheet filter is not present.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the default status of plan class facet scenario2.")
	public void tc3097(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		projectResultsPage.clickOnPlanClassSheetFilter();
		Assert.assertTrue(projectResultsPage.checkOptionPresentInPlanClassSheetFilter(),
				"No option present for selection in Plan Class sheet");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify On pop-up load, all Spec Groups are collapsed in the pop up box for Spec division filter (TC23004)")
	public void tc3551(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.CheckAllSpecGroupAreCollapsed());
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular square area (TC7265)")
	public void tc3116(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular Number of buildings (TC7266)")
	public void tc3117(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular  stories above grade (TC7267)")
	public void tc3118(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories above grade", 4, 5, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "(Copy of) Filters for structural properties - To Verify that user is able to search projects with a particular  stories below  grade (TC7268)")
	public void tc3119(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories below grade", 6, 7, 2, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular square area and selecting include Projects (TC7269)")
	public void tc3120(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular Number of buildings  and selecting include Projects. (TC7270)")
	public void tc3121(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(3);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that user is able to search projects with a particular  stories above grade and selecting include Projects. (TC7271)")
	public void tc3122(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories above grade", 4, 5, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(4);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties -To Verify that user is able to search projects with a particular  stories below  grade and selecting include Projects (TC7272)")
	public void tc3123(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories below grade", 6, 7, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(5);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Action Stage filter, when all children filter are selected for a parent, then the parent will be shown in filter crumb (TC23387)")
	public void tc3575(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String BeforeParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOn_ACTION_STAGE_CODE_Filter();
		projectResultsPage.ClickOnactionStage_Code_SelectAllLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String AfterParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertEquals(BeforeParentFilter, AfterParentFilter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Projects Group filter, when all children filter are selected for a parent, then the parent will be shown in filter crumb (TC23389)")
	public void tc3577(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String BeforeParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		projectResultsPage.ClickProjectTypesSelectAll_Btn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String AfterParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertEquals(BeforeParentFilter, AfterParentFilter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Trade filter, when all children filter are selected for a parent, then the parent will be shown in filter crumb (TC23391)")
	public void tc3579(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String BeforeParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.ClickSpecificTradesSelectAll_Btn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String AfterParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(CommonUtils.checkStringContains(BeforeParentFilter, AfterParentFilter));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Materials/Equip filter, when all children filter are selected for a parent, then the parent will be shown in filter crumb (TC23393)")
	public void tc3581(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquip_LHS_FilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String BeforeParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		projectResultsPage.clickOnMaterialsEquip2Filter();
		projectResultsPage.ClickOnMaterials_Equip2SelectAll_Link();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		String AfterParentFilter = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(CommonUtils.checkStringContains(BeforeParentFilter, AfterParentFilter));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Geography filters (TC22531)")
	public void tc3408(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsgeographyFilterSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of StateRegion filters (TC22539)")
	public void tc3416(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_STATESeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option selected in see more pop up  should also reflect in the StateRegion filter (TC22540)")
	public void tc3417(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		String Expected = projectResultsPage.getStateRegion_PopupFirstFilter_Alabama_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertEquals(projectResultsPage.getStateRegion_LHSFirstFilter_lbl(), Expected);
		Assert.assertTrue(projectResultsPage.getStateRegionFilterList_FirstOptionStatus(0));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is not present). (TC22541)")
	public void tc3418(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String firstFilterLabel = projectResultsPage.getStateRegion_LHSFirstFilter_lbl();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		String Expected = projectResultsPage.getStateRegion_PopupFirstFilter_California_lbl();
		Assert.assertEquals(firstFilterLabel, Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is present). (TC22542)")
	public void tc3419(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		String Expected = projectResultsPage.getStateRegion_PopupFirstFilter_Alabama_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertEquals(projectResultsPage.getStateRegion_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for StateRegion (TC22544)")
	public void tc3421(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for StateRegion (TC22546)")
	public void tc3422(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 5);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for StateRegion (TC22547)")
	public void tc3423(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsStateRegin_FirstOption_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Spec Division filters (TC22548)")
	public void tc3424(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsspecDivision_SeeMore_Popup_btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up should also reflect in the Spec Division filter (TC22549)")
	public void tc3425(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterListPopup");
		String Expected = projectResultsPage.getspecDivisionFilterPopup_LastCheckbox_lbl();
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertEquals(projectResultsPage.getspecDivisionFilterLHS_FirstCheckbox_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is not present) for Spec Division filters (TC22550)")
	public void tc3426(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterListPopup");
		String Expected = projectResultsPage.getspecDivisionFilterPopup_LastCheckbox_lbl();
		projectResultsPage.ClickOnspecDivisionPopup_HideShowCheckbox();
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertEquals(projectResultsPage.getspecDivisionFilterLHS_FirstCheckbox_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is present) for Spec Division filters (TC22551)")
	public void tc3427(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterListPopup");
		String Expected = projectResultsPage.getspecDivisionFilterPopup_LastCheckbox_lbl();
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertEquals(projectResultsPage.getspecDivisionFilterLHS_FirstCheckbox_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the display section for applied filter at the filter crumb area, below the keyword search section for Spec Division filters (TC22552)")
	public void tc3428(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		int eFilterCountSizeBefore = projectResultsPage.getAppliedFilterCount();
		projectResultsPage.ClickOnFilterCrumb_AllClose_btn();
		int eFilterCountSizeAfter = projectResultsPage.getAppliedFilterCount();
		Assert.assertNotEquals(eFilterCountSizeBefore, eFilterCountSizeAfter);
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(5, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Material/Equip")
	public void tc3431(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsaddendaSpecDivisionCheckBoxUnchecked());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Action Stage filters (TC22556)")
	public void tc3432(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsACTION_STAGE_CATEGORYSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Action Stage filter (TC22557)")
	public void tc3433(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnActionStageFilter();
		Assert.assertTrue(projectResultsPage.getActionStage_LHSFirstParentElement_lbl().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is not present). (TC22558)")
	public void tc3434(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnActionStageFilter();
		Assert.assertTrue(projectResultsPage.getActionStage_LHSFirstParentElement_lbl().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up is reflected in the filter section(when zero item is present). (TC22559)")
	public void tc3435(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupFirstParentfilter_lbl();
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnActionStageFilter();
		Assert.assertTrue(projectResultsPage.getActionStage_LHSFirstParentElement_lbl().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for Project type filter (TC22569)")
	public void tc3445(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isCrumbSecondFilterLinkDisplayed());
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink (TC22570)")
	public void tc3446(String emailAddress, String password) throws InterruptedException {
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
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 5);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Project type filter (TC22571)")
	public void tc3447(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IsProjTypeLHS_FirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Tacking lists filters (TC22581)")
	public void tc3456(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsTRACKING_LIST_SeeMore_btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Tracking list filter (TC22588)")
	public void tc3463(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertFalse(projectResultsPage.IstrackingList_LHSFirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Construction type filters (TC22589)")
	public void tc3464(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsCONSTRUCTION_TYPESeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Construction type  filter (TC22590)")
	public void tc3465(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getConstructionType_LHSFirstFilter_lbl(), Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Construction type filter is reflected in the filter section(when zero item is not present). (TC22591)")
	public void tc3466(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getConstructionType_LHSFirstFilter_lbl(), Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Construction type filter is reflected in the filter section(when zero item is present). (TC22592)")
	public void tc3467(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getConstructionType_LHSFirstFilter_lbl(), Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for construction type filter (TC22594)")
	public void tc3469(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for construction type filter (TC22596)")
	public void tc3470(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for construction type filter (TC22597)")
	public void tc3471(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		Assert.assertFalse(projectResultsPage.IsConstructionType_LHSFirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Ownership type filters (TC22598)")
	public void tc3472(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsOWNERSHIP_TYPESeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Ownershiptype  filter (TC22599)")
	public void tc3473(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getOWNERSHIP_TYPE_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Ownership type filter is reflected in the filter section(when zero item is not present). (TC22600)")
	public void tc3474(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getOWNERSHIP_TYPE_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Ownership type filter is reflected in the filter section(when zero item is  present). (TC22601)")
	public void tc3475(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		Assert.assertEquals(projectResultsPage.getConstructionType_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for ownership type filter (TC22603)")
	public void tc3477(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for ownership type filter (TC22604)")
	public void tc3478(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for ownership type filter (TC22605)")
	public void tc3479(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		Assert.assertFalse(projectResultsPage.IsOWNERSHIP_TYPE_LHSFirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Special conditions filters (TC22606)")
	public void tc3480(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsSPECIAL_CONDITIONSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the  Special conditions  filter (TC22607)")
	public void tc3481(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		Assert.assertEquals(projectResultsPage.getSPECIAL_Conditions_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Special conditions is reflected in the filter section(when zero item is not present). (TC22608)")
	public void tc3482(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		Assert.assertEquals(projectResultsPage.getSPECIAL_Conditions_LHSFirstFilter_lbl(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Special conditions is reflected in the filter section(when zero item is present). (TC22609)")
	public void tc3483(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		Assert.assertEquals(projectResultsPage.getSPECIAL_Conditions_LHSFirstFilter_lbl(), Expected);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink  for Special Conditions filter (TC22611)")
	public void tc3485(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section  for Special Conditions filter (TC22612)")
	public void tc3486(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		Assert.assertFalse(projectResultsPage.IsSPECIAL_Conditions_LHSFirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Trades filters (TC22613)")
	public void tc3487(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsTradesSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Trades filter (TC22614)")
	public void tc3488(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnTradesFilter();
		Assert.assertTrue(
				projectResultsPage.getTradeCategoryFacet_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Trade filter is reflected in the filter section(when zero item is not present). (TC22615)")
	public void tc3489(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnTradesFilter();
		Assert.assertTrue(
				projectResultsPage.getTradeCategoryFacet_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Trade filter is reflected in the filter section(when zero item is present). (TC22616)")
	public void tc3490(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnTradesFilter();
		Assert.assertTrue(
				projectResultsPage.getTradeCategoryFacet_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for Trade filter (TC22618)")
	public void tc3492(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for Trade filter (TC22620)")
	public void tc3493(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Trade filter (TC22621)")
	public void tc3494(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnTradesFilter();
		Assert.assertFalse(projectResultsPage.IsTradeCategoryFacet_LHSFirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Materials/Equip filters (TC22622)")
	public void tc3495(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsMaterials_EquipSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Materials/Equip  filter (TC22623)")
	public void tc3496(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnMaterialsEquipFilter();
		Assert.assertTrue(projectResultsPage.getmaterialsEquip_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Material/Equip is reflected in the filter section(when zero item is not present). (TC22624)")
	public void tc3497(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnMaterialsEquipFilter();
		Assert.assertTrue(projectResultsPage.getmaterialsEquip_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Material/Equip is reflected in the filter section(when zero item is present). (TC22625)")
	public void tc3498(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		String Expected = projectResultsPage.getCommonPopupParentFilter_lbl(1);
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnMaterialsEquipFilter();
		Assert.assertTrue(projectResultsPage.getmaterialsEquip_LHSFirstFilter_lbl().toUpperCase().contains(Expected));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections for  Material/Equip filter (TC22627)")
	public void tc3500(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for  Material/Equip filter (TC22628)")
	public void tc3501(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for  Material/Equip filter (TC22629)")
	public void tc3502(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnTradesFilter();
		Assert.assertFalse(projectResultsPage.IsmaterialsEquipFilter_FirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify there is a SelectAll and DeselctAll under County for Geography filter (TC22775)")
	public void tc3514(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGEOGRAPHY_CountyFilter();
		Assert.assertTrue(projectResultsPage.IsCounties_LHS_SelectAll_Btn_Displayed());
		Assert.assertTrue(projectResultsPage.IsCounties_LHS_DeselectAll_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section (TC22357)")
	public void tc3300(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "biddingWithin_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertFalse(projectResultsPage.IsbiddingWithin_FirstOption_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the Bidding Within filter after click on Projects Tab (TC22362)")
	public void tc3305(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "biddingWithin_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		homePage.clickOnProjectsLink();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertFalse(projectResultsPage.IsbiddingWithin_FirstOption_Selected());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify Tracking list filter is present with check box select option (TC22378)")
	public void tc3311(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		Assert.assertTrue(projectResultsPage.IsTRACKING_LIST_SeeMore_btn_Displayed());
		Assert.assertTrue(projectResultsPage.checkTrackingListFacetListSize());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify clicking on the pop-up  for  tracking list filter (TC22385)")
	public void tc3314(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("trackingList_PopupOptionList"));
		projectResultsPage.SelectOptionsFromTheList(3, "trackingList_PopupOptionList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountBeforeClose = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.PopUpClose();
		String CountAfterClose = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeClose, CountAfterClose);
		String CountBeforeOutSide = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.ClickOnFilterpop_Overlay();
		String CountAfterOutSide = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBeforeOutSide, CountAfterOutSide);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the Material & Equipment filter is present  for project filters - Material & Equipment (TC22415)")
	public void tc3321(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isMaterialsEquipFillterDisplayed());
		projectResultsPage.clickOnMaterialsEquipFilter();
		Assert.assertTrue(projectResultsPage.checkMaterialsEquipFilterOption_Diaplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the display section after clicking on triangle icon for project filters - Material & Equipment (TC22416)")
	public void tc3322(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("materialsEquip_LHS_FilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("materialsEquip_LHS_FilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify when clicked on the square icon next to filter, all options are displayed in a pop-up. (TC22417)")
	public void tc3323(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Check_ListOptionsAre_Displayed("CommonPopupParentFilterList"));
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the Trade filter is present (TC22422)")
	public void tc3327(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isTradeLeftNavFilterDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify clicking on the trade filter (TC22423)")
	public void tc3328(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("Trades_LHS_ParentFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("Trades_LHS_ParentFilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify when clicked on the square icon next to filter, all options are displayed in a pop-up")
	public void tc3329(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Check_ListOptionsAre_Displayed("CommonPopupParentFilterList"));
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify scroll bar of Project Group Filter (TC22427)")
	public void tc3332(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("ProjectGroups_LHS_ParentFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("ProjectGroups_LHS_ParentFilterList"));
		Assert.assertTrue(projectResultsPage.ProjGrp_scrollbar_verticalDragger_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify when clicked on the square icon next to Project Group filter, all options are displayed in a pop-up. (TC22428)")
	public void tc3333(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Check_ListOptionsAre_Displayed("CommonPopupParentFilterList"));
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 selections  for project filters - Project Group (TC22430)")
	public void tc3335(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isCrumbSecondFilterLinkDisplayed());
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink  for project filters - Project Group (TC22431)")
	public void tc3336(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 5);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section (TC22432)")
	public void tc3337(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("ProjectGroups_LHS_ParentFilterList"));

		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String iCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String iCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(iCountBefore, iCountAfter);
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IsProjTypeLHS_FirstOption_cbk_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that a ProjectType is displayed on selection of any option in ProjectGroup filter (TC22458)")
	public void tc3341(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("ProjectGroups_LHS_ParentFilterList"));
		Assert.assertTrue(projectResultsPage.IsPROJECT_TYPE_CODE_Filter_Diaplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that a SelectAll and a DeselectAll is appeared in the sub-filter (TC22460)")
	public void tc3343(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		Assert.assertTrue(projectResultsPage.IsProjectTypesSelectAll_Btn_Diaplayed());
		Assert.assertTrue(projectResultsPage.IsProjectTypesDeselectAll_Btn_Diaplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that a Material/Equip 2 is displayed on selection of any option in Material/Equip filter")
	public void tc3345(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquip_LHS_FilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("materialsEquip_LHS_FilterList"));
		Assert.assertTrue(projectResultsPage.IsmaterialsEquip2Filter_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that a SelectAll and a DeselectAll is appeared in the sub-filter (TC22464)")
	public void tc3347(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquip_LHS_FilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnMaterialsEquip2Filter();
		Assert.assertTrue(projectResultsPage.IsMaterials_Equip2SelectAll_Link_Diaplayed());
		Assert.assertTrue(projectResultsPage.IsMaterials_Equip2DeselectAll_Link_Diaplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the UI change in Published Range panel (TC16317)")
	public void tc3212(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnPublishRangeFilter();
		Assert.assertTrue(projectResultsPage.IsPublishRangeFilter_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the change Clear link in Project tab (TC16326)")
	public void tc3215(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsfilterclearAllLink_Displayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify add Save Search button (TC16327)")
	public void tc3216(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultsPage.isSaveSearchButtonDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify users can select and deselect within the same filter module in project filters - Lefthand Navigation (TC21835)")
	public void tc3231(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsaddendaSpecDivisionCheckBoxUnchecked());
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertFalse(projectResultsPage.IsaddendaSpecDivisionCheckBoxUnchecked());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify USA is selected by default for Geography filter in project filters - Lefthand Navigation (TC21836)")
	public void tc3232(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.IsgeographyUSAchkbox_Selected());
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.IsgeographyUSAchkbox_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify USA is selected by default for Geography filter in project filters - Lefthand Navigation (TC21836)")
	public void tc3249(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		projectResultsPage.SelectOptionsFromTheList(5, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.Check_ListOptionsAre_Displayed("GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List"));
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertTrue(
				projectResultsPage.Check_ListOptionsAre_Displayed("StateRegion_ProjectCountNextToElement_List"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify State/Region filter in case of more than 9 States are present (TC22132)")
	public void tc3251(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("StateRegionFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("StateRegionFilterList"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify zero count for any element under the Geography and State/Region tab is not displayed. (TC22133)")
	public void tc3252(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertTrue(projectResultsPage.IsZeroProjCountElement_Displayed_GeographyFilterList());
		Assert.assertTrue(projectResultsPage.IsZeroProjCountElement_Displayed_StateRegionFilterList());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify Geography and State/Region filters should be expandable/collapsible (TC22134)")
	public void tc3253(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_COUNTRY_Filter_Expanded());
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_COUNTRY_Filter_Collapsed());
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_STATE_Filter_Expanded());
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_STATE_Filter_Collapsed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify USA is selected by default and it cannot be unchecked unless another country is selected (TC22135)")
	public void tc3254(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.IsgeographyUSAchkbox_Selected());
		projectResultsPage.SelectOptionsFromTheList(1, "GeographyFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsgeographyUSAchkbox_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify once a state/states is/are selected, the County filter drops down appears which can be checked (TC22167)")
	public void tc3264(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_CountyFilter_Displayed());
		projectResultsPage.SelectOptionsFromTheList(1, "GEOGRAPHY_COUNTYFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("GEOGRAPHY_COUNTYFilterList"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify county section in case of more than 9 counties are present (TC22168)")
	public void tc3265(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_CountyFilter_Displayed());
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("GEOGRAPHY_COUNTYFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("GEOGRAPHY_COUNTYFilterList"));
		Assert.assertTrue(projectResultsPage.IsGEOGRAPHY_County_scrollbar_verticalDragger_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the UI redesign for project filters - Display of filter selection for single level filters (TC22283)")
	public void tc3278(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectTypes_LHS_ParentFilterList");
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		String iCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String iCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(iCountBefore, iCountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the Results are refreshed on every check box in a single filter click (TC22336)")
	public void tc3281(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		String iCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String iCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(iCountBefore, iCountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the project result count after select the check box of any option under Ownership Type (TC22337)")
	public void tc3282(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		int iCountFromList = projectResultsPage.getProjectCountOf_FirstOption_OWNERSHIP_TYPE_LHSFilterList();
		projectResultsPage.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int iProjResultCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(iCountFromList, iProjResultCountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the project result count after select the check boxes of multiple option under Ownership Type (TC22342)")
	public void tc3287(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		int iCountFromList = projectResultsPage.getProjectCountOf_FirstThreeOption_OWNERSHIP_TYPE_LHSFilterList();
		projectResultsPage.SelectOptionsFromTheList(3, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int iProjResultCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(iCountFromList, iProjResultCountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the filter name under keyword search after applied (TC22346)")
	public void tc3290(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		String iFirstOptlbl = projectResultsPage.getOWNERSHIP_TYPEFilter_LHSFirstOption_lbl();
		projectResultsPage.SelectOptionsFromTheList(1, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(iFirstOptlbl, projectResultsPage.getcrumbSecondFiltertxt().replaceAll(" ", ""));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the project result count after select any radio button of option under Bidding Within (TC22351)")
	public void tc3295(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		String iCountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnBiddingWithinNext7DaysOption();
		String iCountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(iCountBefore, iCountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the new name of bidding between option under Bidding Within filter (TC22354)")
	public void tc3298(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		Assert.assertTrue(projectResultsPage.IsCustomRangeBiddingRadio_txt_displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify all the options are available under Bidding Within after selecting any option under Bidding Within (TC22355)")
	public void tc3299(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "biddingWithin_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.Check_ListOptionsAre_Displayed("biddingWithin_LHSFilterList"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of geography filter (TC15835)")
	public void tc3190(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.SelectOptionsFromTheList(1, "geographyPopupUASStatesList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of the new lightbox dialog in the geography filter (TC15831)")
	public void tc3188(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOn_Accodion_DownArrow();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the hierarchy of selections in the new lightbox dialog of materials/eqipment  filter (TC15412)")
	public void tc3186(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.ISPopupSelectAll_cbk_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of panel in the new lightbox dialog in the materials/equipment  filter (TC15407)")
	public void tc3185(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the Saved Search Section of Left side (TC15741)")
	public void tc3187(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsmySearchesDropDown_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of Trades  filter (TC15273)")
	public void tc3181(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the hierarchy of selections in the new lightbox dialog of Trades filter (TC15270)")
	public void tc3180(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.ISPopupSelectAll_cbk_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of  structural property filter (TC15252)")
	public void tc3178(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of Tracking List  filter (TC15246)")
	public void tc3177(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "trackingList_PopupOptionList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of Tracking list  filter (TC15245)")
	public void tc3176(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of the new lightbox dialog in the tracking list filter (TC15240)")
	public void tc3175(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of ownership Type filter (TC15220)")
	public void tc3174(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of ownership type  filter (TC15205)")
	public void tc3173(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of the new lightbox dialog in the ownership type  filter (TC15201)")
	public void tc3172(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnOWNERSHIP_TYPESeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of  construction type  filter (TC15199)")
	public void tc3171(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of construction type  filter (TC15196)")
	public void tc3170(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of the new lightbox dialog in the construction type filter (TC15190)")
	public void tc3168(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnCONSTRUCTION_TYPESeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of spec division  filter (TC15189)")
	public void tc3167(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "specDivisionFilterListPopup");
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of spec Division   filter (TC15188)")
	public void tc3166(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of the Update button the new lightbox dialog of special conditions  filter (TC15178)")
	public void tc3164(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(3, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in the new lightbox dialog of Special conditions  filter (TC15177)")
	public void tc3163(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of panel in the new lightbox dialog in the special Condition  filter (TC15175)")
	public void tc3161(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSPECIAL_CONDITIONSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the functionality of selections of different level checkboxes the new lightbox dialog of Action stage  filter (TC15147)")
	public void tc3160(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertFalse(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("CommonPopupParentFilterList"));
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of panel in the new lightbox dialog in the Action stage  filter (TC15144)")
	public void tc3158(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnACTION_STAGE_CATEGORYSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of the Update button  the new lightbox dialog of project type filter (TC15143)")
	public void tc3157(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of Cancel button in  the new lightbox dialog of  project type filter (TC15142)")
	public void tc3156(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of the design of panel in the  new lightbox dialog in the Project type filter (TC15138)")
	public void tc3155(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.ISCommonPopop_Accodion_DownArrow_displayed());
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of basic design of  the new modal lightbox dialog (TC15135)")
	public void tc3152(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.ClickOnFilterpop_Overlay();
		Assert.assertTrue(projectResultsPage.CheckFilterpop_Overlay_BGColor());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the hide or show functionality of see more pop up box for Spec Division filter (TC22999)")
	public void tc3546_tc3547_tc3549(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.Check_DefaultStatusOf_CheckboxList("specDivisionFilterListPopup"));
		Assert.assertTrue(projectResultsPage.IsZeroProjCountElement_Displayed_SpecDivPopopFilterList());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if Project count for divG is not clickable. (TC25109)")
	public void tc3609(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IscountDivision_FirstCount_Clicable());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if spec Division group description in the see more pop up has been modified. (TC25274)")
	public void tc3621(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnspecDivision_SeeMore_Popup_btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.GetspecDivisionFilterPopup_Optionlbl(1).contains("00"));
		Assert.assertTrue(projectResultsPage.GetspecDivisionFilterPopup_Optionlbl(1).contains(","));
		Assert.assertTrue(projectResultsPage.GetspecDivisionFilterPopup_Optionlbl(0).contains("Table of Contents"));
		Assert.assertTrue(projectResultsPage.GetspecDivisionFilterPopup_Optionlbl(36).contains("Addenda"));
		projectResultsPage.ClickOnspecDivisionNav_UpdateResults_btn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if space in the division chart above the division bar is removed (TC25273)")
	public void tc3620(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultsPage.Are_First7_ChartView_DivGBarList_OptionsVisible());
		Assert.assertFalse(projectResultsPage.Is8th_ChartView_DivGBarList_OptionVisible());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if space in the division chart above the division bar is removed (TC25273)")
	public void tc3619(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertEquals(projectResultsPage.GetChartView_DivGBar_Leftlbl(35), "TOC");
		Assert.assertEquals(projectResultsPage.GetChartView_DivGBar_Leftlbl(36), "ADD");
		projectResultsPage.ClickOnDashboard1_FirstDD_CLH();
		Assert.assertEquals(projectResultsPage.GetChartView_DivGBar_Leftlbl(35), "TOC");
		Assert.assertEquals(projectResultsPage.GetChartView_DivGBar_Leftlbl(36), "ADD");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the message in the section chart, when there is no section facets to cahrt. (TC25271)")
	public void tc3618(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOngeographyPopupStateLink();
		projectResultsPage.clickOnLastStateCheckbox();
/*		projectResultsPage.SelectOptionsFromTheList(51, "geographyPopupUASStatesList");
		projectResultsPage.SelectOptionsFromTheList(50, "geographyPopupUASStatesList"); */
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(8, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(6, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderFirstChartView();
		//projectResultsPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertEquals(projectResultsPage.GetSectionChart_ErrorMsg(), "No section data available");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify when a divG is selected from division chart bar, Section Chart getting refreshed according to the selected divG bar. (TC25118)")
	public void tc3613(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getChartProjectResultsCount();
		String SectionCountBefore = projectResultsPage.getDashboard1_SectionsCount();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.SelectOptionsFromTheList(1, "ChartView_DivGBarList");
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		String SectionCountAfter = projectResultsPage.getDashboard1_SectionsCount();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if selection of divG bar on the division chart changes the search content scope to Specs-only (TC25110)")
	public void tc3610(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("Cortega");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(0));
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(1));
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.SelectOptionsFromTheList(1, "ChartView_DivGBarList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(1));
		String CountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if selection of divG in the left left nav Spec Division filter, changes the search content scope to Specs-only (TC25111)")
	public void tc3611(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("Cortega");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(0));
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(1));
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IsFindIn_LHS_CbkList(0));
		Assert.assertTrue(projectResultsPage.IsFindIn_LHS_CbkList(1));
		String CountAfter = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the results are refreshed on every check box in a single filter click. (TC22072)")
	public void tc3248(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		projectResultsPage.SelectOptionsFromTheList(2, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountAfter1 = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountAfter, CountAfter1);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify on clicking  the rectangular icon next to State/Region (TC22140)")
	public void tc3259(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.getCommonPopupParentFilterList_option_status(0));
		projectResultsPage.ClickOngeographyPopupStateLink();
		Assert.assertTrue(projectResultsPage.getgeographyPopupUASStates_California_cbk_status());
		Assert.assertTrue(projectResultsPage.IsCommonPopupHideZeroProjects_cbk_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify choosing Version 1 Reports from the Special Filter(TC22360)")
	public void tc3303(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(1);
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the Caret sign of Project Group and Project Type filter (TC22433)")
	public void tc3338(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultsPage.IsProjGrp_scrollbar_verticalDragger_Displayed());
		projectResultsPage.CloseProjectGroupsFilter();
		Assert.assertFalse(projectResultsPage.IsProjGrp_scrollbar_verticalDragger_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the order of top 3 filters as mentioned in Reports, Published Date Range & Content Type(Find In) filters (TC22981)")
	public void tc3538(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertEquals(projectResultsPage.GetLHS_Filters_lbl(0), "Reports");
		Assert.assertEquals(projectResultsPage.GetLHS_Filters_lbl(1), "Publish Range");
		Assert.assertEquals(projectResultsPage.GetLHS_Filters_lbl(2), "Find In");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that By default, filter crumb drawer is closed. (TC23624)")
	public void tc3593(String emailAddress, String password) throws InterruptedException {
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
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if user changes a left nav filter from open to closed, that left nav filter remains closed until explicitly changed by the user. (TC23789)")
	public void tc3598(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.CloseProjectGroupsFilter();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify if user changes a left nav filter from closed to opened, that left nav filter remains opened until explicitly changed by the user. (TC23790)")
	public void tc3599(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLink();
		Assert.assertFalse(projectResultsPage.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		Assert.assertFalse(projectResultsPage.IsProjectGrups_Filter_ArrowUp_Visible());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the keyword text box is extended to 350 characters on the left nav of the Projects tab (TC21881)")
	public void tc3236(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		HighlightKeywordsPage highLightKeywordsPage = projectSpecsPage.clickOnFirstMatchedSpecNumber();
		Assert.assertTrue(highLightKeywordsPage.IsDocumentLoadedForMatched(),
				"The document is failed to be loaded in the document viewer");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "[MF2014] [DGN] Project Search Sort Option to be retained (TC21901)")
	public void tc3241(String emailAddress, String password) throws InterruptedException {
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
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Geography , verify when a parent filter is selected from See More, then parent filter is shown in filter crumb (TC23361)")
	public void tc3244(String emailAddress, String password) throws InterruptedException {
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

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Ability to sort projects by project type after opening a Project save search (TC17732)")
	public void tc3220(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Project Type - Ascending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(true));
		projectResultsPage.selectSortingOption("Project Type - Descending");
		Assert.assertTrue(projectResultsPage.verifyProjectTypeSorting(false));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Ability to sort projects by action stage in Project tab (TC17742)")
	public void tc3222(String emailAddress, String password) throws InterruptedException {
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
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify no search crumb will be displayed on choosing Version 1 from the dropdown (TC22361)")
	public void tc3304(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "StateRegionFilterList");
		projectResultsPage.waitforLoadingRing();
		int sizeBefore = projectResultsPage.getFilterCrumb_AppliedFilterList_Size();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(1);
		int sizeAfter = projectResultsPage.getFilterCrumb_AppliedFilterList_Size();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertEquals(sizeBefore, sizeAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "[Trade Navigator] - Verify remove filter action for trade navigators (TC11472)")
	public void tc3135(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnTradesFilter();
		String Expected = projectResultsPage.get_LHS_OptionFilterTitle(0, "Trades_LHS_ProjectwiseTitleList");
		int LHSProjectCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "Trades_LHS_ProjectwiseCountList");
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String tooltip = projectResultsPage.getFilterCrumb_AppliedFilter_TooltipText(1, Expected);
		int ProjectCount = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(LHSProjectCount, ProjectCount);
		Assert.assertEquals(tooltip, Expected);
		int LHSSpecificTradeCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"SpecificTrades_LHS_ProjectwiseCountList");
		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int PrjCount = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(LHSSpecificTradeCount, PrjCount);
		Assert.assertNotEquals(projectResultsPage.getcrumbSecondFiltertxt(), Expected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of location sort in projects results page. (TC14033)")
	public void tc3142(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption("Location - Ascending");
		Assert.assertTrue(projectResultsPage.verifyLocationSortingAscending());
		projectResultsPage.selectSortingOption("Location - Descending");
		Assert.assertTrue(projectResultsPage.verifyLocationSortingAscending());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verifying printing when private notes check box is checked in My Preferences page . (TC14882)")
	public void tc3150(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		String noteText = "Private Notes";
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjDetailsPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertEquals(printProjDetailsPage.getnotesText(), noteText);
		printProjDetailsPage.clickOnBackButton();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.clickSelectAllCheckbox();
		projectnotesPage.mouseoverNotesActionandClickDeleteNotes();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verification of honour of content scope USA in the edit mode selections of Geography filter (TC16300)")
	public void tc3209(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOngeographyFilterSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertTrue(projectResultsPage.checkExpectedOptionIsDisplayedIn_CommonPopupParentFilter_lblList("USA"));
		Assert.assertTrue(
				projectResultsPage.checkExpectedOptionIsDisplayedIn_CommonPopupParentFilter_lblList("Canada"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify Scrolling filters in project filters - Lefthand Navigation (TC21837)")
	public void tc3233(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnStateRegionFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("StateRegionFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("StateRegionFilterList"));
		Assert.assertTrue(projectResultsPage.StateRegion_scrollbar_verticalDragger_displayed());

		projectResultsPage.clickOnSpecDivisionFilter();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("specDivisionFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("specDivisionFilterList"));
		Assert.assertTrue(projectResultsPage.SpecDivision_scrollbar_verticalDragger_displayed());

		projectResultsPage.clickOnSpecialConditions();
		Assert.assertTrue(projectResultsPage.Check_First9_OptionsAreVisible("specialConditonFilterList"));
		Assert.assertTrue(projectResultsPage.Check_10th_OptionsVisible("specialConditonFilterList"));
		Assert.assertTrue(projectResultsPage.SpecialCondi_scrollbar_verticalDragger_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the sub option of second level project filter and compare with see more pop up (TC22434)")
	public void tc3339(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(1, "CommonPopupParentFilterList");
		projectResultsPage.clickOnUpdateButton();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.getCommonPopupParentFilterList_option_status(0));
		projectResultsPage.clickOnUpdateButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the options of other filters when one or multiple options are selected under Project type (TC22459)")
	public void tc3342(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int parentFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "ProjectGroupsLHS_LabelList");
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		int firstChildFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "ProjectTypesLHS_LabelList");
		int secondChildFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(1, "ProjectTypesLHS_LabelList");
		projectResultsPage.clickOnTradesFilter();
		int childFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(1, "Trades_LHS_ProjectwiseCountList");
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, firstChildFilerCount));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, secondChildFilerCount));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, childFilerCount));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the options availability of Project Type filter when one or multiple options are selected from other project filter (TC22461)")
	public void tc3344(String emailAddress, String password) throws InterruptedException {
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
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the options of other filters when one or multiple options are selected under Material/Equip (TC22463)")
	public void tc3346(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		int parentFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "materialsEquipLHS_LabelList");
		projectResultsPage.clickOnMaterialsEquip2Filter();
		int firstChildFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "materialsEquip2LHS_LabelList");
		int secondChildFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(1,
				"materialsEquip2LHS_LabelList");
		projectResultsPage.clickOnTradesFilter();
		int childFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(1, "Trades_LHS_ProjectwiseCountList");
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, firstChildFilerCount));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, secondChildFilerCount));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, childFilerCount));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the options availability of Material/Equip filter when one or multiple options are selected from other project filter (TC22465)")
	public void tc3348(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnMaterialsEquip2Filter();
		int firstChildFilerCountBefore = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"materialsEquip2LHS_LabelList");
		int secondChildFilerCountBefore = projectResultsPage.get_LHS_FilterWiseProjectCount(1,
				"materialsEquip2LHS_LabelList");
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int parentFilerCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"ACTION_STAGE_CATEGORYLHS_LabelList");
		int firstChildFilerCountafter = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"materialsEquip2LHS_LabelList");
		int secondChildFilerCountafter = projectResultsPage.get_LHS_FilterWiseProjectCount(1,
				"materialsEquip2LHS_LabelList");
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, firstChildFilerCountafter));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(parentFilerCount, secondChildFilerCountafter));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(firstChildFilerCountBefore, secondChildFilerCountafter));
		Assert.assertTrue(
				projectResultsPage.compareTwoNumbers(secondChildFilerCountBefore, secondChildFilerCountafter));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the when last child filter is removed or deselected, the parent filter will also deselected from lest nav and child filter will be removed from filters crumb (TC23399)")
	public void tc3584(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		String actionFirst = projectResultsPage.getActionStage_LHSFirstParentElement_lbl();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		String actioncrumb = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(actionFirst.replaceAll("\\s+", "").contains(actioncrumb.replaceAll("\\S+", "")));

		projectResultsPage.clickOn_ACTION_STAGE_CODE_Filter();
		String actionCodeFirst = projectResultsPage.getACTION_STAGE_CODELHS_Label(0);
		projectResultsPage.SelectOptionsFromTheList(1, "actionStageCodeFacetList");
		String actionCodecrumb = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(actionCodeFirst.contains(actionCodecrumb));
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertFalse(projectResultsPage.IsactionStageCatFacet_1_Ckbox_Selected());

		projectResultsPage.clickOnActionStageFilter();
		String actionFirstr = projectResultsPage.getActionStage_LHSFirstParentElement_lbl();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		String actioncrumbr = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(actionFirstr.replaceAll("\\s+", "").contains(actioncrumbr.replaceAll("\\s+", "")));

		projectResultsPage.clickOn_ACTION_STAGE_CODE_Filter();
		String actionCodeFirstr = projectResultsPage.getACTION_STAGE_CODELHS_Label(0);
		projectResultsPage.SelectOptionsFromTheList(1, "actionStageCodeFacetList");
		String actionCodecrumrb = projectResultsPage.getcrumbSecondFiltertxt();
		Assert.assertTrue(actionCodeFirstr.replaceAll("\\s+", "").contains(actionCodecrumrb.replaceAll("\\s+", "")));
		projectResultsPage.ClickOnactionStage_Code_DeselectAll_Link();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.IsactionStageCatFacet_1_Ckbox_Selected());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the behavior of child and parent filters when a Saved Search ran. (TC23402)")
	public void tc3585(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.clickOn_ACTION_STAGE_CODE_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "actionStageCodeFacetList");
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectTypesLHS_LabelList");
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.clickOnMaterialsEquip2Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquip2_LHS_FilterList");
		int beforeSize = projectResultsPage.getAppliedFilterCount();
		String filterCrumbTextBefore = projectResultsPage.getNamesOfAllAppliedFilters();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		int afterSize = projectResultsPage.getAppliedFilterCount();
		Assert.assertEquals(beforeSize, afterSize);
		Assert.assertTrue(filterCrumbTextBefore.contains(projectResultsPage.getAppliedcrumb_txt(1)));
		Assert.assertTrue(filterCrumbTextBefore.contains(projectResultsPage.getAppliedcrumb_txt(2)));
		Assert.assertTrue(filterCrumbTextBefore.contains(projectResultsPage.getAppliedcrumb_txt(3)));
		Assert.assertTrue(filterCrumbTextBefore.contains(projectResultsPage.getAppliedcrumb_txt(4)));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "For Projects Group filter, when child(ren) filter are selected for a parent, then selected child(ren) filter are shown in filter crumb (TC23424)")
	public void tc3586(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnCompaniesLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		String Expected = projectResultsPage.get_LHS_OptionFilterTitle(0, "ProjectGroupsLHS_LabelList");
		projectResultsPage.SelectOptionsFromTheList(6, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPROJECT_TYPE_CODE_Filter();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String filterCrumbText = projectResultsPage.getNamesOfAllAppliedFilters();
		Assert.assertFalse(filterCrumbText.contains(Expected));
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectTypes_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the available options of other filters when any option is selected under Bidding Within (TC22352)")
	public void tc3296(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "biddingWithin_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		int ParentFilterCount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "biddingWithinLHS_LabelList");
		projectResultsPage.clickOnProjectGroupsFilter();
		int ChildFilerCount1 = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "ProjectGroupsLHS_LabelList");
		int ChildFilerCount2 = projectResultsPage.get_LHS_FilterWiseProjectCount(1, "ProjectGroupsLHS_LabelList");
		projectResultsPage.clickOnActionStageFilter();
		int ChildFilerCount3 = projectResultsPage.get_LHS_FilterWiseProjectCount(0,
				"ACTION_STAGE_CATEGORYLHS_LabelList");
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(ParentFilterCount, ChildFilerCount1));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(ParentFilterCount, ChildFilerCount2));
		Assert.assertTrue(projectResultsPage.compareTwoNumbers(ParentFilterCount, ChildFilerCount3));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify If user selects plans/Specs or both, the corresponding search will happen within the selected areas indicate and the results will be populated accordingly (TC22188)")
	public void DGN_T2695(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.enterSearchText("Door");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		projectResultsPage.deselect_FindInLHSCheckboxList();
		Assert.assertTrue(projectResultsPage.Is_lnkPlans_links_Displayed());
		projectResultsPage.ClickOn_FindIn_LHS_CbkList(1);
		Assert.assertTrue(projectResultsPage.Is_lnkSpecs_links_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Organize project types by general building categories in the select more than one dialog (TC3220)")
	public void tc3113_tc3112(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("Wood");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnProjectGroupsFilter();
		int firstfiltercount = projectResultsPage.get_LHS_FilterWiseProjectCount(0, "ProjectGroupsLHS_LabelList");
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		int projCount = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(firstfiltercount, projCount);
		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.ClickOnProjGrpPopupCustomHomesyChk();
		int secondlevelPrjcount = projectResultsPage.getProjGrpPopupCustomHomes_Count();
		projectResultsPage.clickOnUpdateButton();
		int projCountAfter = projectResultsPage.getExactProjectCount();
		Assert.assertEquals(secondlevelPrjcount, projCountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the UI change for Equipments/ Materials light box in Project tab (TC16266)")
	public void tc3208(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnMaterials_EquipSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the UI change for Trades light box in Project tab (TC16265)")
	public void tc3207(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "[Trade Navigator] - Saved Search Validation (TC11474)")
	public void tc3137(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);

		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.SelectOptionsFromTheList(2, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore1 = projectResultsPage.getProjectResultsCount();
		SavedSearchPopUp saveSearchPopUp1 = homePage.clickOnSaveSearchButtonProject();
		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp1.enterName(searchName1);
		saveSearchPopUp1.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage1 = homePage.clickOnSavedSearchMenu();
		savedSearchesPage1.clickOnSpecificProjectSavedSearch(searchName1);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		String CountAfter1 = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore1, CountAfter1);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "[Trade Navigator] - Verify remove filter action for trade navigators (TC11472)")
	public void tc3136(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);

		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore1 = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnSpecificTRADE_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "SpecificTrades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		String CountAfter1 = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore1, CountAfter1);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "[Trade Navigator] - Verify the trade navigator on the list view (TC11470)")
	public void tc3134(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(3, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnTradesSeeMore_Btn();
		Assert.assertTrue(projectResultsPage.isPopUpDisplayed());
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "CommonPopupParentFilterList");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Project type filter is reflected in the  filter section(when zero item is  present). (TC22567)")
	public void tc3443(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(7, "CommercialOptionsFilterListFromPopup");
		projectResultsPage.SelectOptionsFromTheList(1, "CommercialOptionsFilterListFromPopup");
		String eExpected = projectResultsPage.getProjTypePopupCommercialParents_FirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getProjTypeLHS_FirstOption_lbl(), eExpected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the counts and values of the selected options in the see more pop-up of Project type filter is reflected in the  filter section(when zero item is not present). (TC22566)")
	public void tc3442(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(7, "CommercialOptionsFilterListFromPopup");
		projectResultsPage.SelectOptionsFromTheList(1, "CommercialOptionsFilterListFromPopup");
		String eExpected = projectResultsPage.getProjTypePopupCommercialParents_FirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getProjTypeLHS_FirstOption_lbl(), eExpected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the option  selected in see more pop up  should also reflect in the Project type filter (TC22565)")
	public void tc3441(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectTypesSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(7, "CommercialOptionsFilterListFromPopup");
		projectResultsPage.SelectOptionsFromTheList(1, "CommercialOptionsFilterListFromPopup");
		String eExpected = projectResultsPage.getProjTypePopupCommercialParents_FirstOption_lbl();
		projectResultsPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultsPage.getProjTypeLHS_FirstOption_lbl(), eExpected);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify that the SeeMore pop is integrated in the left nav  of Project type filters (TC22564)")
	public void tc3440(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsProjectTypesSeeMore_Btn_Displayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality after clicked on Clear All link below keyword search section for Action Stage filter (TC22563)")
	public void tc3439(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertFalse(projectResultsPage.IsactionStageCatFacet_1_Ckbox_Selected());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the functionality of hyperlink present on the top for more than 3 slections (TC22561")
	public void tc3437(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the content of the pop up opened when clicked on hyperlink for Action Stage filter (TC22562)")
	public void tc3438(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(4, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ClickOncrumbFirstGroupFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		Assert.assertTrue(projectResultsPage.IsFilterCrumbPopup_Searchbox_Displayed());
		Assert.assertEquals(projectResultsPage.getCrumbFilterPopupCloseBtnListSize(), 4);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties To Verify that user is able to search structural Properties with including projects (TC7277)")
	public void tc3127(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(2);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(3);
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories above grade", 4, 5, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(4);
		projectResultsPage.SelectValuesFromStructPropDropdown("Stories below grade", 6, 7, 2, 2);
		projectResultsPage.SelectCheckboxFromStructProp(5);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify what happen when user No Maximum is selected with a specific minimum (TC7275)")
	public void tc3126(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 2, 1);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Filters for structural properties - To Verify that if user does not selects no minimum what happens (TC7274)")
	public void tc3125(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnStructuralPropLink();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 0, 2);
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectFilterDataProvider.class, dataProvider = "Common", description = "Verify the New name of  Stage 2 Project filter (TC22871)")
	public void tc3528(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		homePage.clickOnSignOutButton();
	}

}

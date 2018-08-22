package com.ddaqe.dgn_project_grid;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
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
import com.ddaqe.constants.DGNProjectGridConstant;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectNotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)
public class DGNProjectGrid1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNProjectGrid1.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "DCS is accessed via the Gear icon located in the upper left of the project grid view.")
	public void tc3389TC3386(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertFalse(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertFalse(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
		projectResultsPage.clickOnGridViewToggleSelectedButton();
		Assert.assertFalse(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertFalse(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify Error message without selection on Project list view grid page")
	public void tc3390TC3391(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		List<String> dcsCheckBoxLabelUIList = projectResultsPage.getDCSCheckBoxLabelList();
		List<String> dcsSelectedCheckboxList = projectResultsPage.getDCSSelectedCheckboxList();
		List<String> dcsUnselectedCheckboxList = projectResultsPage.getDCSUnselectedCheckboxList();
		List<String> dcsCheckBoxLabelList = DGNProjectGridConstant.getDCSCheckBoxLabelList();
		List<String> unexpectedDCSChkBxLabelList = Arrays.asList("Geocode", "Tracking lists", "Spec alerts",
				"Data history", "Designer", "Contractor");
		Assert.assertTrue(dcsCheckBoxLabelUIList.containsAll(dcsCheckBoxLabelList),
				"DCS is not displayed with expected check box list when grid gear icon is clicked.");
		Assert.assertFalse(dcsCheckBoxLabelUIList.containsAll(unexpectedDCSChkBxLabelList),
				"DCS is displayed with unexpected check box list when grid gear icon is clicked.");
		List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
		Assert.assertTrue(columnHeadingUIList.containsAll(dcsSelectedCheckboxList),
				"Grid is not displayed with selected columns when grid gear icon is clicked.");
		Assert.assertFalse(columnHeadingUIList.containsAll(dcsUnselectedCheckboxList),
				"Grid is displayed with unselected columns when grid gear icon is clicked.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Grid View display reacts appropriately to accomodate selected columns.")
	public void tc3398(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_COUNTY, false);
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_ZIP_CODE, false);
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.verifyHorizontalScrollPresent(), "Horizontal scroll bar is displayed.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the user can use the DCS data column check-boxes to select data columns they want in their Grid.")
	public void tc3392TC85(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		List<String> dcsCheckBoxLabelList = new ArrayList<String>();
		dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSSelectedCheckboxList());
		dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSUnselectedCheckboxList());
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_CITY, false);
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getGridColumnHeadings().contains(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_CITY),
				"Grid is displayed with unselected column when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.selectAllDCSCheckbox();
		Assert.assertTrue(projectResultsPage.getGridColumnHeadings().containsAll(dcsCheckBoxLabelList),
				"Grid is not displayed with selected columns when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.deSelectAllDCSCheckbox();
		Assert.assertFalse(projectResultsPage.getGridColumnHeadings().containsAll(dcsCheckBoxLabelList),
				"Grid is displayed with unselected columns when grid gear icon is clicked.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Project Grid View page is exited when a different view-toggle button is selected")
	public void tc3423(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("500");
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not active.");
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.isGridViewToggleButtonUnSelected(), "Grid view toggle button is active.");
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not active.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify 'RESET TO DEFAULT' pop-up.")
	public void tc3396TC3397TC3395TC3375(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertFalse(projectResultsPage.isProjectGridSelectAllCheckBoxSelected(),
				"Check box to select all project is not selected by default.");
		String projectCount = projectResultsPage.getProjectCountString();
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when grid gear icon is clicked.");
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed(DGNProjectGridConstant.RESET_TO_DEFAULT),
				"Expected heading on DCS dialog is not displayed when grid gear icon is clicked.");
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_CITY, false);
		projectResultsPage.clickOnResetToDefault();
		Assert.assertEquals(projectResultsPage.verifyAlertMessageForResetToDefaultAndDismiss(),
				"Current grid layout will be replaced with original values. Are you sure you want to continue?");
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getProjectCountString(), projectCount,
				"Project count us changed when column grid is updated.");
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.verifyAlertMessageForResetToDefaultAndAccept();
		Assert.assertFalse(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is not displayed when accept alert condition.");
		projectResultsPage.waitforLoadingRing();
		List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
		Assert.assertTrue(columnHeadingUIList.contains(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_CITY),
				"Grid is not displayed with default columns when RESET TO DEFAULT is clicked.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify Error message without selection on Project list view grid page")
	public void tc3421TC3379TC3373(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.verifyAlertMessageForResetToDefaultAndAccept();
		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed when grid view is selected.");
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonSelected(),
				"Grid view toggle button is not displayed.");
		Assert.assertTrue(projectResultsPage.verifyToggleButtonName(DGNProjectGridConstant.GRID_TOGGLE_TITLE),
				"Grid view toggle button name is not as expected.");
		Assert.assertTrue(projectResultsPage.verifyBlankTitleForGridViewUnSelectedToggleButton(),
				"Title is displayed for Grid view toggle button.");
		List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
		List<String> columnHeadingList = DGNProjectGridConstant.getColumnHeadingList();

		Assert.assertTrue(columnHeadingUIList.containsAll(columnHeadingList),
				"Grid is not displayed with expected columns when grid view is selected.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify 'RESET TO DEFAULT' pop-up.")
	public void tc3374(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Grid Gear icon is not diplayed when clicked on Grid view toggle button.");
		projectResultsPage.clickOnNextPageNumber();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Grid Gear icon is not diplayed when clicked on Grid view toggle button.");
		projectResultsPage.clickOnPreviousPageNumber();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Grid Gear icon is not diplayed when clicked on Grid view toggle button.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify availability of grid View toggle button on different pages.")
	public void tc3420(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not displayed.");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not displayed.");
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not displayed.");
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(),
				"Grid view toggle button is not displayed.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Persistence of Grid View in results page when user toggles to view project results via the Grid View and navigates back via breadcrumb")
	public void tc3408(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridFirstProjectTitle();
		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonUnSelected(), "navigate back to Grid result page");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Publish date Format on Project grid")
	public void tc3449(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertTrue(projectResultsPage.verifyPublishDateColumnValues(), "PUBLISH DATE format is not mm/dd/yyyy");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "DCS is accessed via the Gear icon located in the upper left of the project grid view")
	public void tc3389(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed());
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed("RESET TO DEFAULT"));
		projectResultsPage.clickOnMySearchesDropDown();
		Assert.assertFalse(projectResultsPage.isDCSDialogHeaderDisplayed("RESET TO DEFAULT"));
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertTrue(projectResultsPage.isDCSDialogHeaderDisplayed("RESET TO DEFAULT"));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Pagination controls on Project Grid view header and footer")
	public void tc3404(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertTrue(projectResultsPage.areBothResultDropdownDisplayed());
		Assert.assertTrue(projectResultsPage.isSortDropdownDisplayed());
		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed());
		Assert.assertTrue(projectResultsPage.areBothPaginationDisplayed());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Pagination control value sharing: Scenario 1: Results set project count")
	public void tc3409(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.Expand_Project_Delivery_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGeographyFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "GeographyFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "ConstructionType_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		String listPageCount = projectResultsPage.getPaginationSectionText();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		String GridPageCount = projectResultsPage.getPaginationSectionText();
		Assert.assertEquals(listPageCount, GridPageCount, "comparing pagination count on grid view with list view");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Pagination control value sharing: Scenario 2: Results page-size drop-down control")
	public void tc3410(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("10000");
		String listSelected1 = projectResultsPage.getResultDropdownSelectedValue();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		String gridSelected1 = projectResultsPage.getResultDropdownSelectedValue();
		Assert.assertEquals(listSelected1, gridSelected1, "Comparing result dropdown value on Grid and list page");
		projectResultsPage.SelectResultDropdownValue("500");
		String gridSelected2 = projectResultsPage.getResultDropdownSelectedValue();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		String listSelected2 = projectResultsPage.getResultDropdownSelectedValue();
		Assert.assertEquals(listSelected2, gridSelected2, "Comparing result dropdown value on Grid and list page");
		projectResultsPage.SelectResultDropdownValue("50");
		String listSelected3 = projectResultsPage.getResultDropdownSelectedValue();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		String gridSelected3 = projectResultsPage.getResultDropdownSelectedValue();
		Assert.assertEquals(listSelected3, gridSelected3, "Comparing result dropdown value on Grid and list page");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Pagination control value sharing: Scenario 3: Page selection/forward/backward control")
	public void tc3413(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.clickOnPageNumber4();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber4HighLightedInBold());
		projectResultsPage.clickOnPageNumber2();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber2HighLightedInBold());
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Grid Column Ordering")
	public void tc3438TC3448(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.clickOnResetAllOkButton();
		projectResultsPage.waitforLoadingRing();
		List<String> ActualGridTitleValues = projectResultsPage.getGridHeaderTileList();
		Assert.assertEquals(DGNProjectGridConstant.getColumnHeadingList(), ActualGridTitleValues,
				"Comparing Grid title values");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Grid page results should not be sorted on clicking grid column headers")
	public void tc3440(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		String BeforeFirstRowValue = projectResultsPage.GetRequireddataColumnValue(0);
		projectResultsPage.clickOnAllGridHeaderTitles();
		String AfterClickingFirstRowValue = projectResultsPage.GetRequireddataColumnValue(0);
		Assert.assertEquals(BeforeFirstRowValue, AfterClickingFirstRowValue, "Checking before and after value");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Grid view pagination controls work same like the Project List view pagination controls")
	public void tc3407(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		List<String> resultsDropdownExpectedValues = Arrays.asList("10", "25", "50", "100", "500", "1000", "5000",
				"10000");
		List<String> resultsDropdownActualValues = projectResultsPage.VerifyResultDropdownValues();
		Assert.assertEquals(resultsDropdownActualValues, resultsDropdownExpectedValues);

		List<String> sortDropdownExpectedValues = Arrays.asList("Bid Date - Ascending", "Bid Date - Descending",
				"Publish Date - Ascending", "Publish Date - Descending", "Valuation - Low to High",
				"Valuation - High to Low", "Location - Ascending", "Location - Descending", "Project Title - Ascending",
				"Project Title - Descending", "Action Stage - Ascending", "Action Stage - Descending",
				"Project Type - Ascending", "Project Type - Descending");

		List<String> sortDropdownActualValues = projectResultsPage.VerifySortDropdownValues();
		Assert.assertEquals(sortDropdownActualValues, sortDropdownExpectedValues);
		List<String> ActionsDropdownActualValues = projectResultsPage.VerifyActionsDropdownValues();
		Assert.assertEquals(DGNProjectGridConstant.getActionDropdownOptions(), ActionsDropdownActualValues);
		projectResultsPage.clickOnNextPageNumber();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber2HighLightedInBold(), "Checking pagination control");
		projectResultsPage.clickOnPreviousPageNumber();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.IsPageNumber1HighLightedInBold(), "Checking pagination control");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Grid row position remembered upon return to Grid View from Project details page")
	public void tc3425(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("1000");
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.lazyLoadDataInGrid(); // 3 times lazy loading data in
													// grid
		projectResultsPage.lazyLoadDataInGrid();
		projectResultsPage.lazyLoadDataInGrid();
		int indexvalue = projectResultsPage.GetCurrentlyDisplayedFirstdataColumnIndex();
		String value = projectResultsPage.GetRequireddataColumnValue(indexvalue);
		ProjectPage projectPage = projectResultsPage.clickRequiredProjectTitleColumnValue(indexvalue);
		projectPage.clickOnBreadCrumbProjectResultLink();
		Assert.assertTrue(projectResultsPage.verifyGridColumnTitlevalue(value));
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project List Results Page Rendering on User relogin")
	public void tc3417(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnListViewIcon();
		homePage.clickOnSignOutButton();
		HomePage homePage1 = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage1 = homePage1.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage1.isCurrentView_ListView(),
				"Verifying the curent view on Project result page is List View");
		homePage1.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project selection via checkbox 'Actions' drop-down functions on Grid View")
	public void tc3402TC3403(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnProjectGridCheckBox(1);
		projectResultsPage.waitforLoadingRing();
		List<String> actionsDropdownActualValues1 = new ArrayList<>();
		actionsDropdownActualValues1.addAll(projectResultsPage.VerifyActionsDropdownValues());
		Assert.assertEquals(DGNProjectGridConstant.getActionDropdownOptions(), actionsDropdownActualValues1);
		projectResultsPage.clickOnGridViewToggleSelectedButton();
		projectResultsPage.clickOnProjectGridSelectAllCheckBox();
		projectResultsPage.waitforLoadingRing();
		List<String> actionsDropdownActualValues2 = new ArrayList<>();
		actionsDropdownActualValues2.addAll(projectResultsPage.VerifyActionsDropdownValues());
		Assert.assertEquals(DGNProjectGridConstant.getActionDropdownOptions(), actionsDropdownActualValues2);
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify Error message without selection on Project list view grid page")
	public void tc3400TC3401(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		List<String> actionDropdownOptions = DGNProjectGridConstant.getActionDropdownOptions();
		projectResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(projectResultsPage.getprojectActionDropdownOptions().containsAll(actionDropdownOptions),
				"Action drop down is not displayed with expected options when grid view is selected.");
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertEquals(projectResultsPage.getErrorMessage(), "Please make a selection",
				"An error message 'Please make a selection' is not displayed.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the user can use the DCS data column check-boxes to select data columns they want in their Grid.")

	public void tcDGNT3392(String emailAddress, String password) throws InterruptedException {
		String projectColom = "Project";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		try {
			projectResultsPage.clickOnGridViewUnSelectedToggleButton();
			projectResultsPage.clickOnGridGearIcon();
			Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
					"DCS dialog is not displayed when grid gear icon is clicked.");

			String firstUnselectedDCSLabel = projectResultsPage.getFirstDCSUnselectedCheckboxLable();
			projectResultsPage.clickOnFirstDCSUnselectedCheckbox();
			List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
			Assert.assertTrue(
					columnHeadingUIList.get(columnHeadingUIList.size() - 1).equalsIgnoreCase(firstUnselectedDCSLabel),
					"New columns selected from DSC is not added at the end of the grid");

			projectResultsPage.clickOnGridGearIcon();
			List<String> firstSelectedDCSLabel = new ArrayList<>();
			firstSelectedDCSLabel.add(projectResultsPage.getFirstDCSSelectedCheckboxLable());
			projectResultsPage.clickOnFirstDCSSelectedCheckbox();

			columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
			Assert.assertFalse(columnHeadingUIList.containsAll(firstSelectedDCSLabel),
					"New columns unselected from DSC is still present at the end of the project grid");

			projectResultsPage.clickOnGridGearIcon();
			List<String> dcsCheckBoxLabelList = new ArrayList<String>();
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSSelectedCheckboxList());
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSUnselectedCheckboxList());

			projectResultsPage.selectAllDCSCheckbox();
			Assert.assertTrue(projectResultsPage.getGridColumnHeadings().containsAll(dcsCheckBoxLabelList),
					"Grid is not displayed with selected columns when grid gear icon is clicked for all items.");

			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.deSelectAllDCSCheckbox();
			Assert.assertTrue(
					projectResultsPage.getGridColumnHeadings()
							.get(projectResultsPage.getGridColumnHeadings().size() - 1).equalsIgnoreCase(projectColom),
					"Only Project column is not displayed in the Grid when uncheckall the item.");
		} catch (Exception e) {
		} finally {
			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.clickOnResetToDefault();
			projectResultsPage.clickOnResetAllOkButton();
			projectResultsPage.waitforLoadingRing();

		}
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the project fields customization on Grid view.")
	public void tcDGNT3385(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		try {
			projectResultsPage.clickOnGridViewUnSelectedToggleButton();
			projectResultsPage.clickOnGridGearIcon();
			Assert.assertTrue(projectResultsPage.isDCSDialogDisplayed(),
					"DCS dialog is not displayed when grid gear icon is clicked.");

			String firstUnselectedDCSLabel = projectResultsPage.getFirstDCSUnselectedCheckboxLable();
			projectResultsPage.clickOnFirstDCSUnselectedCheckbox();
			List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
			Assert.assertTrue(
					columnHeadingUIList.get(columnHeadingUIList.size() - 1).equalsIgnoreCase(firstUnselectedDCSLabel),
					"New columns selected from DSC is not added at the end of the grid");

			projectResultsPage.clickOnGridGearIcon();
			List<String> dcsCheckBoxLabelList = new ArrayList<String>();
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSSelectedCheckboxList());
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSUnselectedCheckboxList());

			projectResultsPage.selectAllDCSCheckbox();
			Assert.assertTrue(projectResultsPage.getGridColumnHeadings().containsAll(dcsCheckBoxLabelList),
					"Grid is not displayed with selected columns when grid gear icon is clicked for all items.");

			Assert.assertTrue(projectResultsPage.isHorizontalBarForProjGridDisplayed(),
					"Horizontal bar for project grid is not displayed.");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.clickOnResetToDefault();
			projectResultsPage.clickOnResetAllOkButton();
			projectResultsPage.waitforLoadingRing();

		}
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the project data columns in project grid view.")
	public void tcDGNT3379(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.clickOnResetAllOkButton();
		projectResultsPage.waitforLoadingRing();

		List<String> ActualGridTitleValues = projectResultsPage.getGridHeaderTileList();
		Assert.assertEquals(DGNProjectGridConstant.getColumnHeadingList(), ActualGridTitleValues,
				"Comparing Project Grid default columns");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify The Notes link does NOT have a mouse-over tool-tip.")
	public void tcDGNT3432(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String noteText = "Add Note";
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.clickOnPublicRadioButton();
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertFalse(projectResultsPage.checkToolTipForNoteLinkInProjGrid(),
				"Tooltip for Note link is displayed in project grid");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the navigation to Project detail from Project Grid view.")
	public void tcDGNT3380(String emailAddress, String password) throws InterruptedException {
		String projectDetailPageBreadCrumb = "Project Results > Project";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertTrue(projectResultsPage.checkToolTipForProjectTitleInProjGrid(),
				"Tooltip for Project title is not displayed in project grid table.");

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleInProjectGrid();
		Assert.assertEquals(projectPage.getBreadCrumbText().trim(), projectDetailPageBreadCrumb);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Headers in the Project list view Grid page")
	public void tcDGNT3399(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertTrue(projectResultsPage.isActionsDropdownDisplayed(),
				"Action drop down is not displayed when grid view is selected.");
		Assert.assertTrue(projectResultsPage.isProjectGridSelectAllCheckBoxDisplayed(),
				"Select All checkbox is not present as header in project grid.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Lazy Load in the Project grid view.")
	public void tcDGNT3381(String emailAddress, String password) throws InterruptedException {
		int projectTitleLen = 25;
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.SelectResultDropdownValue("100");
		Assert.assertEquals(projectResultsPage.getTotalProjectTitleVisibleInGrid(), projectTitleLen);
		Assert.assertTrue(projectResultsPage.checkLazyLoadDataInGrid(),
				"Lazy loading is not done as loading icon not able on scrolling.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the Grid View display reacts appropriately to accomodate selected columns.")
	public void tcDGNT3398(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		try {
			projectResultsPage.clickOnGridViewUnSelectedToggleButton();
			projectResultsPage.clickOnGridGearIcon();

			List<String> dcsCheckBoxLabelList = new ArrayList<String>();
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSSelectedCheckboxList());
			dcsCheckBoxLabelList.addAll(projectResultsPage.getDCSUnselectedCheckboxList());

			projectResultsPage.selectAllDCSCheckbox();
			projectResultsPage.clickOnAllGridHeaderTitles();
			Assert.assertEquals(projectResultsPage.getLastColumnFromProjGrid(),
					dcsCheckBoxLabelList.get(dcsCheckBoxLabelList.size() - 1).toUpperCase().trim());
		} catch (Exception e) {
		} finally {
			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.clickOnResetToDefault();
			projectResultsPage.clickOnResetAllOkButton();
			projectResultsPage.waitforLoadingRing();

		}
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project results are rendered from the Dashboard 1 Viz View on user re login")
	public void tc3419(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDashboard1TogglebuttonSelected(), "Dashboard 1 icon is not selected");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDashboard1TogglebuttonSelected(),
				"Dashboard 1 icon is not selected after re-login");
		Assert.assertFalse(projectResultsPage.isProjectListTogglebuttonDisplayed(),
				"Project List toggle button is selected by default when user is logged in.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project results are rendered via the Grid View on user re login")
	public void tc3418(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonSelected(),
				"Grid view toggle button is not selected");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isGridViewToggleButtonSelected(),
				"Grid view toggle button is not selected after re-login");
		Assert.assertFalse(projectResultsPage.isProjectListTogglebuttonDisplayed(),
				"Project List toggle button is selected by default when user is logged in.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Results Grid View Settings persistent across user login session.")
	public void tc3415(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_CITY, false);
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getGridColumnHeadings().contains(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_CITY),
				"Grid is displayed with unselected column when grid gear icon is clicked.");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getGridColumnHeadings().contains(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_CITY),
				"Grid is displayed with unselected column when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.clickOnResetAllOkButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Results Grid View Settings persistent across user login session.")
	public void tc3414(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_CITY, false);
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getGridColumnHeadings().contains(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_CITY),
				"Grid is displayed with unselected column when grid gear icon is clicked.");
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.clickOnResetAllOkButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify project search result set and pagination is unchanged after grid view is refreshed")
	public void tc3394(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		String projectCount = projectResultsPage.getProjectCountString();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.selectDeselectDCSCheckbox(DGNProjectGridConstant.DCS_OPTION_CITY, false);
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getProjectCountString(), projectCount,
				"Project count us changed when column grid is updated.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Grid Bid Date Column Display")
	public void tc3447(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		assertTrue(projectResultsPage.verifyBIDDateColumnValues(), "BID dates are not in valid expression.");
		projectResultsPage.clickOnASAPBiddingDays();
		assertTrue(projectResultsPage.verifyBIDDateColumnValues(), "BID dates are not in valid expression.");
		projectResultsPage.clickOnNDSBiddingDays();
		assertTrue(projectResultsPage.verifyBIDDateColumnValues(), "BID dates are not in valid expression.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Project Grid Column data display")
	public void tc3424(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDashboard2TogglebuttonSelected(),
				"Dashboard 2 button is not selected when Dashboard 2 icon is clicked.");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDashboard2TogglebuttonSelected(),
				"Dashboard 2 button is not selected when Dashboard 2 icon is clicked.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Second line of the 'Project' column contains links to the Project Detail Report pages for that DR")
	public void tc3430TC3433(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.clickOnGridGearIcon();
		projectResultsPage.clickOnResetToDefault();
		projectResultsPage.clickOnResetAllOkButton();
		projectResultsPage.waitforLoadingRing();
		assertTrue(projectResultsPage.verifyProjectColumnDRLinkList(), "Project link list is invalid.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "A DR page link is shown only if there is content available for display on the associated Project Detail Report page")
	public void tc3433(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		assertTrue(projectResultsPage.verifyProjectColumnDRLinkListOnAllPages(), "Project link list is invalid.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the grid view available for tracking lists & saved searches.")
	public void tcDGNT3484(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertTrue(projectResultsPage.checkProjectGridTableWrapper(),
				"Project result is not display in grid and separated by column.");

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.checkTrackingListTableExist(),
				"Tracking list result set is not display in grid view only.");

		myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();

		Assert.assertTrue(savedSearchesPage.checkSaveSearchTableExist(),
				"Saved Searches result set is not display in grid view only.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify the DR page link opens the associated Project Detail Report page for that DR.")
	public void tcDGNT3434(String emailAddress, String password) throws InterruptedException {
		String noteText = "Add Note";
		String firmPageBreadCrumb = "Project Results > Firms";
		String projectPlanPageBreadCrumb = "Project Results > Plans";
		String projectPlanholderPageBreadCrumb = "Project Results > Planholders/Bidders";
		String projectAddendaPageBreadCrumb = "Project Results > Addenda";
		String projectNotesPageBreadCrumb = "Project Results > Notes";

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.clickOnPublicRadioButton();
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		ProjectFirmsPage projectFirmsPage = projectResultsPage.clickOnFirmsLink();
		Assert.assertEquals(projectFirmsPage.getBreadCrumbText(), firmPageBreadCrumb);
		projectResultsPage = projectFirmsPage.clickOnProjectResultLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnPlansLink();
		Assert.assertEquals(projectPlansPage.getBreadCrumbText(), projectPlanPageBreadCrumb);
		projectResultsPage = projectPlansPage.clickOnProjectResultLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		ProjectBiddersPage projectBiddersPage = projectResultsPage.clickOnPlanholderBidderLink();
		Assert.assertEquals(projectBiddersPage.getBreadCrumbText(), projectPlanholderPageBreadCrumb);
		projectResultsPage = projectBiddersPage.clickOnProjectResultLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();
		Assert.assertEquals(projectAddendaPage.getBreadCrumbText(), projectAddendaPageBreadCrumb);
		projectResultsPage = projectAddendaPage.clickOnProjectResultLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		ProjectNotesPage projectNotesPage = projectResultsPage.clickOnNotesLink();
		Assert.assertEquals(projectNotesPage.getBreadCrumbText(), projectNotesPageBreadCrumb);

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify selecting Grid View toggle button displays Project Grid View page.")
	public void tcDGNT3422(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertTrue(homePage.isSearchTxtFieldDisplayed(),
				"Keyword search textbox is not present when click on Grid view toggle button.");
		Assert.assertTrue(homePage.Is_searchBtn_Displayed(),
				"Keyword search button is not present when click on Grid view toggle button.");
		Assert.assertTrue(homePage.isSaveSearchButtonDisplayed(),
				"Save Search button is not present when click on Grid view toggle button.");
		Assert.assertTrue(projectResultsPage.IsFIND_INFilter_Displayed(),
				"Filter crumb drawer is not display when click on Grid view toggle buttonn.");
		Assert.assertTrue(projectResultsPage.checkHideFilterShowWithFilters(),
				"Filter drawer is not display when click on Grid view toggle buttonn.");
		Assert.assertTrue(projectResultsPage.checkProjectGridTableWrapper(),
				"Project result is not display in grid and separated by column.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify number of projects displayed in the grid depends on the Results page-size.")
	public void tcDGNT3376(String emailAddress, String password) throws InterruptedException {
		String[] resultPerPageOption = new String[] { "25", "50", "100", "500", "1000", "5000", "10000" };
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[0]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[0]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[1]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[1]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[2]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[2]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[3]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[3]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[4]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[4]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[5]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[5]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

		projectResultsPage.SelectResultDropdownValue(resultPerPageOption[6]);
		Assert.assertEquals(projectResultsPage.getResultDropdownSelectedValue(), resultPerPageOption[6]);
		Assert.assertTrue(projectResultsPage.isGridGearIconDisplayed(),
				"Project Gear icon is not displayed as control is not on Project Grid view.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Verify Grid view behavior after invalid keyword search functionality.")
	public void tcDGNT3457(String emailAddress, String password) throws InterruptedException {
		String searchString = "ABCD";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		searchString = searchString + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		homePage.enterSearchText(searchString);
		homePage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();

		String compareString = "Your search - " + searchString + " - did not match any documents.";
		Assert.assertTrue(projectResultsPage.getProjectResultSummaryMessage().contains(compareString),
				"Project Result Summary message is incorrect if invalid keyword search is perform.");
		Assert.assertTrue(projectResultsPage.isKeywordSearchFilterCrumbDisplayed(),
				"keyword search filter crumb is not displayed at top.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Valuation numbers range on Project Grid.")
	public void tcDGNT3446(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		Assert.assertTrue(
				projectResultsPage.checkGridValuationValueWithChar(projectResultsPage
						.getColumnValueOfAllRows(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_VALUATION)),
				"Valuation column value contain the alphabates.");

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Sorting dropdown functionality on Project Grid page.")
	public void tcDGNT3439(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();

		List<String> projectListViewSortDropDownOptions = projectResultsPage.VerifySortDropdownValues();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		List<String> projectGridViewSortDropDownOptions = projectResultsPage.VerifySortDropdownValues();

		Assert.assertTrue(projectGridViewSortDropDownOptions.containsAll(projectListViewSortDropDownOptions),
				"Project Grid View Sort options are not identical to Project List view sort options");
		try {
			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.selectAllDCSCheckbox();
			projectResultsPage.verifyGridDataSortedBySelection(projectGridViewSortDropDownOptions);
		} catch (Exception e) {
		} finally {
			projectResultsPage.clickOnGridGearIcon();
			projectResultsPage.clickOnResetToDefault();
			projectResultsPage.clickOnResetAllOkButton();
			projectResultsPage.waitforLoadingRing();
		}

	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Sort value persistence across Project Grid and Project List view")
	public void tc3459(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.selectSortingOption("Valuation - Low to High");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectListToggleButton();
		Assert.assertTrue(projectResultsPage.VerifySortDropdownValues().contains("Valuation - Low to High"),
				"Sort option selected on grid view is not displayed on list view.");
		projectResultsPage.selectSortingOption("Project Title - Ascending");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertTrue(projectResultsPage.VerifySortDropdownValues().contains("Project Title - Ascending"),
				"Sort option selected on list view is not displayed on grid view.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderOOS", description = "Verify Project data columns in Project grid for OOS projects")
	public void tc3564TC3565TC3566(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		List<String> columnHeadingUIList = projectResultsPage.getGridColumnHeadings();
		List<String> columnHeadingList = DGNProjectGridConstant.getProjectOOSColumnHeadingList();
		Assert.assertTrue(columnHeadingUIList.containsAll(columnHeadingList),
				"For Projects which are Outside of subscription, Grid is not displayed with expected columns when grid view is selected.");
		assertTrue(projectResultsPage.verifyPublishDateColumnValues(), "Publish dates are not in valid expression.");
		assertTrue(projectResultsPage.verifyDRNumberColumnValues(), "DR Numbers are not in valid expression.");
		assertTrue(projectResultsPage.verifyVersionColumnValues(), "DR Numbers are not in valid expression.");
		assertTrue(projectResultsPage.verifyProjectColumnDRLinkListForOOS(),
				"Project details are displayed with DR link.");
		assertTrue(projectResultsPage.isGridGearIconDisplayed(), "Project grid gear icon is not displayed.");
		projectResultsPage.clickOnGridGearIcon();
		Assert.assertFalse(projectResultsPage.isDCSDialogDisplayed(),
				"DCS dialog is displayed when grid gear icon is clicked.");
		projectResultsPage.clickOnActionsDropdownOutOfSubscription();
		final List<String> projectActionDropdownOptions = projectResultsPage.getprojectActionDropdownOptions();
		Assert.assertTrue(projectActionDropdownOptions.contains("Add Online Project"),
				"'Add Online Project' action dropdown option is not displayed.");
		projectResultsPage.clickAddOnlineProjects();
		Assert.assertTrue(projectResultsPage.verifyMessageToAddOnlineProjects(),
				"'To add project to your license, please change to List View' error message is not displayed to add online projects from grid view.");
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Sort option selected by the User (when User logged in last time) persist in the List View.")
	public void tcDGNT3442(String emailAddress, String password) throws InterruptedException {
		String valuationSortOption = "Valuation - Low to High";
		String projectTitleSortOption = "Project Title - Ascending";

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();

		projectResultsPage.selectSortingOption(valuationSortOption);
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnListViewIcon();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), valuationSortOption);

		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), valuationSortOption);
		projectResultsPage.selectSortingOption(projectTitleSortOption);
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(projectResultsPage.verifyColumnSortedBySelectionValue(projectTitleSortOption),
				"Project Grid result is not sorted as per selection for column : "
						+ projectTitleSortOption.split("-")[0].toUpperCase().trim() + " sort typle : "
						+ projectTitleSortOption.split("-")[1].toUpperCase().trim());

		projectResultsPage.clickOnListViewIcon();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), projectTitleSortOption);
		homePage.clickOnSignOutButton();
	}
}

package com.ddaqe.dgn_visualization;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ddaqe.pages.CustomizeProjectDashboardPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LoginPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNVisualization extends BaseTest {
	static Logger log = Logger.getLogger(DGNVisualization.class);

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

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Sample Test Case")
	public void tcSample(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify if Within a user's session, a Viz Dashboard customization is remembered from that user's previous visit to that Viz dashboard.")
	public void tc3711(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		projectResultPage.selectProjectTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectType(),
				"Failed to display Project Type in the Left Chart View");
		projectResultPage.selectDivisionUnderSecondChartView();
		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectType(),
				"Failed to display Project Type in the Left Chart View");
		Assert.assertTrue(projectResultPage.isChartRightContainsDivision(),
				"Failed to display division in the Right Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TC3684", description = "Verify if Section Chart can be added to any custom-viz-dashboard and can refreshed according to project search result on Dashboard 2 (Section visualization ) page.")
	public void tc3684(String emailAddress, String password, String searchText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();

		projectResultPage.selectedDifferentChartsOnDashboard2();

		String projectCountBefore = projectResultPage.getChartProjectResultsCount();
		String selectedChart1HeaderDashboard2 = projectResultPage.getChart1HeaderDashboard2();
		String selectedChart2HeaderDashboard2 = projectResultPage.getChart2HeaderDashboard2();

		// Verify Chart1 - Dashboard2 sections.
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage.isProjectTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isConstructionTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isDivisionDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isSectionsDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isActionStageDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isOwnershipTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isSpecAlertsDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart1Dashboard2());
		Assert.assertFalse(projectResultPage.verifyLeftVisualizationStatus(selectedChart1HeaderDashboard2));

		// Verify Chart2 - Dashboard2 sections.
		if (projectResultPage.isChart2CustomizeTilePresent()) {
			projectResultPage.clickOnSecondChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage.isProjectTypeDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isConstructionTypeDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isDivisionDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isSectionsDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isActionStageDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isOwnershipTypeDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isSpecAlertsDisplayedOnChart2Dashboard2());
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart2Dashboard2());
		Assert.assertFalse(projectResultPage.verifyRightVisualizationStatus(selectedChart2HeaderDashboard2));

		// Verify sections can be selected on both the charts
		projectResultPage.selectSectionsUnderFirstChartView();
		Assert.assertEquals(projectResultPage.getChart1HeaderDashboard2(), "Sections");

		projectResultPage.selectSectionsUnderSecondChartView();
		Assert.assertEquals(projectResultPage.getChart2HeaderDashboard2(), "Sections");

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getAppliedFilterText(searchText));

		String projectCountAfter = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);

		String washingtonToolTip = projectResultPage.hoverOverWashingTonandgetMapToolTip();
		projectResultPage.clickMapChartProjectDensityWashington();

		String projectCountAfterSelectingState = projectResultPage.getChartProjectResultsCount();
		Assert.assertTrue(washingtonToolTip.contains("Project Count: " + projectCountAfterSelectingState));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc3712(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();

		projectResultPage.selectProjectTypeUnderFirstChartView();

		projectResultPage.selectDivisionUnderSecondChartView();
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectType(),
				"Failed to display Project Type in the Left Chart View");
		Assert.assertTrue(projectResultPage.isChartRightContainsDivision(),
				"Failed to display division in the Right Chart View");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc3780(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(projectResultPage.checkHideFilterShowWithoutFilters(),
				"Project filter are not hidden when click on hide filter");
		Assert.assertTrue(projectResultPage.checkHideFilterShowWithFilters(),
				"Project filter are hidden when click on hide filter");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Hide and Show Left-nav filters on Project Search Results VIZ Dashboard #2.")
	public void tc3781(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(projectResultPage.checkHideFilterShowWithoutFilters(),
				"Project filter are not hidden when click on hide filter");
		Assert.assertTrue(projectResultPage.checkHideFilterShowWithFilters(),
				"Project filter are hidden when click on hide filter");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the level-1 Project Type bar chart is positioned and sized on Chart View as per the mock-up (TC24479).")
	public void tc1618(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeUnderFirstChartView();

		List<String> actualCountDivisions = projectResultPage.getCountDivisions();
		List<String> actualCountDivisionBars = projectResultPage.getCountDivisionBars();
		Assert.assertEquals(actualCountDivisions.size(), actualCountDivisionBars.size());
		List<String> expectedProjectTypeBarChartLable = projectResultPage.getProjectBarChartLabel();
		List<String> actualProjectTypeBarChartLable = projectResultPage.getSortedProjectTypeLabelList();
		Assert.assertTrue(CommonUtils.compareTwoListForStringContains(expectedProjectTypeBarChartLable,
				actualProjectTypeBarChartLable));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify  each donut segment contains a mouse-over tool-tip in Ownership Type.")
	public void tc3666(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		String toolTip = projectResultsPage.hoverOverAdditionsandgetMapToolTip();
		Assert.assertTrue(toolTip.contains("Construction Type") && toolTip.contains("Project Count"),
				"Construction Type donut does not display 'Construction Type' & 'Project Count' in tooltip");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify  each donut segment contains a mouse-over tool-tip in Ownership Type.")
	public void tc3667_tc3664_tc3665(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectOwnershipTypeUnderFirstChartView();
		List<String> legendList = new ArrayList<String>();
		legendList.addAll(projectResultsPage.getLeftLegendList());
		Assert.assertTrue(legendList.contains("Local Government") && legendList.contains("Private")
				&& legendList.contains("State") && legendList.contains("Federal") && legendList.contains("Military"),
				"Legends are not displayed for Ownership Type.");
		Assert.assertTrue(projectResultsPage.getChart1HeaderDashboard2().equalsIgnoreCase("Ownership Type"),
				"Chart heading is not as expected.");
		Assert.assertTrue(projectResultsPage.getFirstChartSubheaderText().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading is not as expected.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify messaging for when no data to plot on Dashboard 1 chart.")
	public void tc3722(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No project type data available"),
				"'No project type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(
				projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No construction type data available"),
				"'No construction type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No division data available"),
				"'No division data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectSectionsUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No section data available"),
				"'No section data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectActionStageUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No action stage data available"),
				"'No action stage data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectOwnershipTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(
				projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No ownership type data available"),
				"'No ownership type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectSpecAlertsUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No SpecAlerts data available"),
				"'No SpecAlerts data available' message is not displayed when no record found fro project type.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify messaging for when no data to plot on Dashboard 2 chart.")
	public void tc3723(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectProjectTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No project type data available"),
				"'No project type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(
				projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No construction type data available"),
				"'No construction type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No division data available"),
				"'No division data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectSectionsUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No section data available"),
				"'No section data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectActionStageUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No action stage data available"),
				"'No action stage data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectOwnershipTypeUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(
				projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No ownership type data available"),
				"'No ownership type data available' message is not displayed when no record found fro project type.");
		projectResultsPage.removeParticularKeywordFilter("ghggghggh");
		projectResultsPage.selectSpecAlertsUnderFirstChartView();
		projectResultsPage.performSearchString("ghggghggh");
		Assert.assertTrue(projectResultsPage.getChartErrorMessage().equalsIgnoreCase("No SpecAlerts data available"),
				"'No SpecAlerts data available' message is not displayed when no record found fro project type.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the  Selection of one or more donut segment(s) in the Ownership Type donut chart.")
	public void tc3668(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectOwnershipTypeUnderFirstChartView();
		projectResultsPage.clickOnDonutForPrivate();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Private"),
				"Breadcrum doesn't contain selected donut text");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the  Selection of one or more donut segment(s) in the Ownership Type donut chart.")
	public void tc3670(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectOwnershipTypeUnderFirstChartView();
		projectResultsPage.clickOnExpandIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getChartHeaderPopup().equalsIgnoreCase("Ownership Type"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultsPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify deselection of bar in the section chart.")
	public void tc3687(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		String projectCountBefore = projectResultPage.getChartProjectResultsCount();
		projectResultPage.selectSectionsUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
			projectResultPage.waitforLoadingRing();
		}
		Assert.assertTrue(projectResultPage.isProjectTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isConstructionTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isDivisionDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isSectionsDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isActionStageDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isOwnershipTypeDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isSpecAlertsDisplayedOnChart1Dashboard2());
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart1Dashboard2());

		projectResultPage.selectSectionsUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		String selectedSection = projectResultPage.clickFirstBarChart1Dashboard();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getAppliedFilterText(selectedSection));
		String projectCountAfterSelectingFirstBar = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfterSelectingFirstBar, projectCountBefore);
		Assert.assertFalse(projectResultPage.getIndexesOfDeselectedBarsInChart1Dashboard2().contains(1));
		String deselectedSection = projectResultPage.clickFirstBarChart1Dashboard();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(projectResultPage.getAppliedFilterText(deselectedSection));
		String projectCountAfterDeSelectingFirstBar = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfterDeSelectingFirstBar, projectCountAfterSelectingFirstBar);
		Assert.assertTrue(projectResultPage.getIndexesOfDeselectedBarsInChart1Dashboard2().size() == 0);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify if Division Chart refreshed according to project search result.")
	public void tc3679(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String firstSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.performSearch("Door");
		String secondSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		Assert.assertNotEquals(secondSearchRecordCount, firstSearchRecordCount,
				"Search is not performed on Devision Type.");
		projectResultsPage.SelectOptionsFromTheList(3, "FindIn_LHS_CbkList");
		Assert.assertNotEquals(projectResultsPage.getChartProjectResultsCount(), secondSearchRecordCount,
				"Search is not performed on Devision Type more options.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify deselection of bar in the division chart.")
	public void tc3681_tc3750(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDivisionTypeUnderFirstChartView();
		String firstSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		projectResultsPage.waitforLoadingRing();

		String secondSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Procurement And Contracting Requirements"),
				"Breadcrum doesn't contain selected Division type");
		Assert.assertNotEquals(secondSearchRecordCount, firstSearchRecordCount,
				"Division type selection is not performed.");
		projectResultsPage.clickFirstDivisionBarInSectionVisualizations();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.getAppliedFilterText("Procurement And	Contracting Requirements"),
				"Breadcrum contains selected Division type");
		Assert.assertEquals(projectResultsPage.getChartProjectResultsCount(), firstSearchRecordCount,
				"Division type selection is not performed.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify if Section Chart can be added to any custom-viz-dashboard and can change Division Group search-criteria according to chart selections on Dashboard 1 (Project visualization ) page.")
	public void tc3682(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectSectionsUnderFirstChartView();
		String firstSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		projectResultsPage.clickFirstSectionBarInLeftChart();
		projectResultsPage.waitforLoadingRing();
		String secondSearchRecordCount = projectResultsPage.getChartProjectResultsCount();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Submittal Procedures"),
				"Breadcrum doesn't contain selected Sections type");
		Assert.assertNotEquals(secondSearchRecordCount, firstSearchRecordCount,
				"Sections type selection is not performed.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Bar chart is refreshed with search results")
	public void tc3695_tc3698_tc3699(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectActionStageUnderSecondChartView();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromActionStageLeftSelectionView("Bidding/Negotiating");
		projectResultsPage.clickActionStageFromLeftSelectionView("Bidding/Negotiating");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Bidding/Negotiating"),
				"Breadcrum doesn't contain selected Action Stage type");
		Assert.assertEquals(projectResultsPage.getSelectedBarInChart2Text(), "Bidding/Negotiating",
				"Expected Sections type is not selected.");
		String toolTip = projectResultsPage.hoverOverAdditionsandgetMapToolTip();
		Assert.assertTrue(toolTip.contains("Construction Type") && toolTip.contains("Project Count"),
				"Construction Type donut does not display 'Construction Type' & 'Project Count' in tooltip");

		projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnPublishRangeFilter();
		projectResultsPage.selectPublishRangeFromDD("Last Week");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromActionStageLeftSelectionView("Bidding/Negotiating"),
				"Action Stage chart data is not changed after performing Publish Range filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the filter crumbs reflects the search criteria request which initiated for any filter via the Chart view (TC24558)")
	public void tc1640_tc1642_tc_1643(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = Integer.parseInt(projectResultsPage.getChartProjectResultsCount().trim()
				.replace("(", "").replace(",", "").replace(")", ""));
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickCONSTRUCTION_TYPEFromLeftSelectionView("Alterations");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Alterations"),
				"Breadcrum doesn't contain selected Contruction type");
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Publish Range filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Publish Range filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Publish Range filter.");
		projectResultsPage.removeParticularFilter("Alterations");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(projectResultsPage.getAppliedFilterText("Alterations"),
				"Breadcrum contains selected Contruction type");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Publish Range filter (TC24508)")
	public void tc1621(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnPublishRangeFilter();
		projectResultsPage.selectPublishRangeFromDD("Last Week");
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Publish Range filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Publish Range filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Publish Range filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of  Find In filter (TC24509)")
	public void tc1622(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", true);
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Find In filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Find In filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Find In filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "UI-Left-nav Hide-and-Show user preference for Project Visualization")
	public void tc3760(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", true);
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(!projectResultsPage.getProjectNameList().isEmpty(),
				"Project List view is not displayed when Project List toggle button is clicked.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of State/Region filter (TC24511)")
	public void tc1624(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnStateRegion_FirstCheckBox();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCountTexas = projectResultsPage.getMapChartProjectDensityTexasValue();
		int initialDensityProjectCountWA = projectResultsPage.getMapChartProjectDensityWashingtonValue();
		projectResultsPage.clickOnStateRegionFilter();
		projectResultsPage.clickOnStateRegion_FirstCheckBox();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing State/Region filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing State/Region filter.");
		Assert.assertNotEquals(initialDensityProjectCountTexas,
				projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is changed after performing State/Region filter for Texas.");
		Assert.assertNotEquals(initialDensityProjectCountWA,
				projectResultsPage.getMapChartProjectDensityWashingtonValue(),
				"Project Density chart data is not changed after performing State/Region filter for WA.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Bidding Within filter (TC24512)")
	public void tc1625(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.clickOnASAPBiddingDays();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnBiddingWithinFilter();
		projectResultsPage.clickOnBiddingWithinNext7DaysOption();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Bidding Within filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Bidding Within filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Bidding Within filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Spec Division filter (TC24513)")
	public void tc1626(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnSpecDivisionFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "specDivisionFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Spec Division filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Spec Division filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Spec Division filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Project Groups filter (TC24515)")
	public void tc1628(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Project Groups filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Project Groups filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Project Groups filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of SpecAlerts filter (TC24516)")
	public void tc1629(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "specAlertFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Spec Alert filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Spec Alert filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Spec Alert filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Tracking List filter (TC24517)")
	public void tc1630(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Tracking List filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Tracking List filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Tracking List filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Valuation filter (TC24518)")
	public void tc1631(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnValuationFilter();
		projectResultsPage.clickOnValMinimumDdAndSelectFirstOpt();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Valuation filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Valuation filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Valuation filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Ownership Type filter (TC24520)")
	public void tc1633(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnOWNERSHIP_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "OWNERSHIP_TYPE_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Ownership Type filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Ownership Type filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Ownership Type filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Special Conditions filter (TC24521)")
	public void tc1634(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnSPECIAL_CONDITIONFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "SPECIAL_Conditions_LHSFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Special Condition filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Special Condition filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Special Condition filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Trades filter (TC24522)")
	public void tc1635(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnTradesFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "Trades_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Trades filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Trades filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Trades filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Chart view is updated whenever the project search result set changes due to a selection of Materials/Equip filter (TC24523)")
	public void tc1636(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDashboard2ToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnMaterialsEquipFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "materialsEquipFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Materials Equipment filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Materials Equipment filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Materials Equipment filter.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc3716(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		// Verify the entire graph and labels should be contained within the
		// grey background.
		Assert.assertTrue(homePage.isChart1BackgroundGrey(), "Failed to background colour as grey in Right Chart View");

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(homePage.isChart2BackgroundGrey(), "Failed to background colour as grey in Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Construction Type Chart can be added to any custom-viz-dashboard")
	public void tc3641(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify deselection of bar in the section chart.")
	public void tc3692(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnActionStageFilter();
		List<String> projectSearchActionStageFilterLblList = projectResultPage
				.get_LHS_FilterWiseLabelList("ACTION_STAGE_CATEGORYLHS_LabelList");
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectTileFromChart1Dashboard2("Action Stage");
		projectResultPage.waitforLoadingRing();
		List<String> actionStageFilterLblList = projectResultPage.getAllFilterTextChart1Dashboard();
		Assert.assertTrue(null != actionStageFilterLblList && !actionStageFilterLblList.isEmpty());
		Assert.assertTrue(actionStageFilterLblList.contains("Bidding/ Negotiating"));
		Assert.assertTrue(actionStageFilterLblList.contains("Design"));
		Assert.assertTrue(actionStageFilterLblList.contains("Construction"));
		Assert.assertTrue(actionStageFilterLblList.contains("Pre-Design"));
		Assert.assertTrue(actionStageFilterLblList.contains("Abandoned"));
		Assert.assertTrue(actionStageFilterLblList.contains("Delayed"));
		Assert.assertTrue(actionStageFilterLblList.contains("Operation"));
		Assert.assertTrue(CommonUtils.compareTwoListForStringContains(actionStageFilterLblList,
				projectSearchActionStageFilterLblList));
		Assert.assertTrue(projectResultPage.areAllFilterBarsHorizontalOnChart1Dashboard());
		Assert.assertTrue(projectResultPage.verifySizeOfEachBarDependingOnFacetCountChart1Dashboard2());
		Assert.assertTrue(projectResultPage.verifyFacetCountDisplayedOnRightOfBarChart1Dashboard());
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify  viz-chart selection made via the viz-chart-selection-widget is saved as user preferences.")
	public void tc3638(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeUnderFirstChartView();
		projectResultPage.selectDivisionUnderSecondChartView();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc3717(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectActionStageUnderFirstChartView();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc3718(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectSpecAlertsUnderFirstChartView();

		Assert.assertTrue(!projectResultPage.checkSpaceExist(), "Failed, space should not be allowed in 'SpecAlerts'");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Viz Dashboard customization is remembered from that user's visit to that Viz dashboard in their previous session.")
	public void tc1637(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the SORT, SHOW, SELECT ALL and Action dropdown are hidden in Chart View (TC24534)")
	public void tc1638(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		Assert.assertTrue(!projectResultPage.isactionStage_Code_SelectAllLinkDisplayed(),
				"Select all link should not be present");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify deselection of bar in the section chart.")
	public void tc3694(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		String projectCountBefore = projectResultPage.getChartProjectResultsCount();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectTileFromChart1Dashboard2("Action Stage");
		String selectedSection = projectResultPage.clickFirstBarChart1Dashboard();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getAppliedFilterText(selectedSection));
		String projectCountAfterSelectingFirstBar = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfterSelectingFirstBar, projectCountBefore);
		Assert.assertFalse(projectResultPage.getIndexesOfDeselectedBarsInChart1Dashboard2().contains(1));
		List<String> selectedActionStageCheckBoxes = projectResultPage.getSelectedActionStageTypes();
		Assert.assertTrue(null != selectedActionStageCheckBoxes && !selectedActionStageCheckBoxes.isEmpty());

		Boolean isSelectedChartFilterPresent = false;
		for (String selectedActionStageCheckbox : selectedActionStageCheckBoxes) {
			if (selectedActionStageCheckbox.contains(selectedSection)) {
				isSelectedChartFilterPresent = true;
				break;
			}
		}
		Assert.assertTrue(isSelectedChartFilterPresent);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify that selected geographical unit(s) are visually identified (TC24576)")
	public void tc1645_tc1647(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickMapChartProjectDensityWashington();
		projectResultPage.hoverOverWashingTonandgetMapToolTip();
		projectResultPage.clickMapChartProjectDensityTexas();
		projectResultPage.hoverOverTexasandgetMapToolTip();
		projectResultPage.clickMapChartProjectDensityWashington();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(homePage.isWashingtonMapBackgroundDarkGrey(),
				"Failed to background colour as grey in WashingtonMap");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Chart-selected geographical search criteria is included in the project search request along with any previously-selected left-nav search criteria of a different filter type. (TC24595)")
	public void tc1648_tc1649(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectConstructionTypeUnderFirstChartView();
		projectResultsPage.selectProjectTypeUnderSecondChartView();
		List<String> countList = projectResultsPage.getProjectTypeRightCountList();
		int initialProjectCount = projectResultsPage
				.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations");
		int initialDensityProjectCount = projectResultsPage.getMapChartProjectDensityTexasValue();
		projectResultsPage.clickOnActionStageFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ACTION_STAGE_CATEGORY_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(countList.equals(projectResultsPage.getProjectTypeRightCountList()),
				"Project Type chart data is not changed after performing Project Groups filter.");
		Assert.assertNotEquals(initialProjectCount,
				projectResultsPage.getProjectCountFromCONSTRUCTION_TYPELeftSelectionView("Alterations"),
				"Construction Type chart data is not changed after performing Project Groups filter.");
		Assert.assertNotEquals(initialDensityProjectCount, projectResultsPage.getMapChartProjectDensityTexasValue(),
				"Project Density chart data is not changed after performing Project Groups filter.");
		projectResultsPage.clickMapChartProjectDensityMontana();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterText("Montana"),
				"Breadcrum doesn't contain selected Density location.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "UI-Left-nav Hide-and-Show user preference")
	public void tc3761(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		customizeProjectDashboardPage.selectOnlyShowMineCheckBox();
		customizeProjectDashboardPage.selectDefaultSavedSearchCheckBox();
		homePage = customizeProjectDashboardPage.clickSaveButton();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnBiddingWithinFilter();
		homePage = projectResultsPage.clickOnHomeTab();
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnDropDownList();
		SavedSearchesPage savedSearchesPage = projectResultsPage.clickOnMySavedSearches();
		projectResultsPage = savedSearchesPage.clickOnfirstProjectSavedSearch();
		homePage = projectResultsPage.clickOnHomeTab();
		projectResultsPage = homePage.clickOnfirstSavedSearch();
		projectResultsPage.clickOnBiddingWithinFilter();
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertFalse(projectResultsPage.isBiddingWithinFilterPaneCollapsed(),
				"BiddingWithin filter pane is not in collapsed state when page loaded.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "UI-Left-nav Hide-and-Show user preference in company search result page.")
	public void tc3759(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.collapseCompanyLocationFilterPane();
		homePage = companyResultsPage.clickOnHomeTab();
		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyLocationFilter();
		homePage = companyResultsPage.clickOnHomeTab();
		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.collapseCompanyLocationFilterPane();
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertFalse(companyResultsPage.isCompanyLocationFilterinCollapsedState(),
				"Company filter is not in collapsed state.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Hide and Show Left-nav filters on Company Search Page.")
	public void tc3778_tc3779(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.collapseCompanyLocationFilterPane();
		Assert.assertFalse(companyResultsPage.isCompanyLocationFilterinCollapsedState(),
				"Company filter is not in collapsed state.");
		companyResultsPage.clickOnCompanyLocationFilter();
		Assert.assertTrue(companyResultsPage.isCompanyLocationFilterinExpandedState(),
				"Company filter is in expanded state.");
		companyResultsPage.clickOnPowerRankResultBtn();
		companyResultsPage.clickPowerRankCancelBtn();
		companyResultsPage.clickOnCompanyLocationFilter();
		Assert.assertTrue(companyResultsPage.isCompanyLocationFilterinExpandedState(),
				"Company filter is in expanded state.");
		LoginPage loginPage = homePage.clickOnSignOutButton();
		Assert.assertTrue(loginPage.isSignInButtonDisplayed(), "User logged out successfully.");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify that selected geographical unit(s) are visually identified (TC24576)")
	public void tc1646(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickMapChartProjectDensityWashington();
		projectResultPage.hoverOverWashingTonandgetMapToolTip();
		projectResultPage.clickMapChartProjectDensityTexas();
		projectResultPage.hoverOverTexasandgetMapToolTip();
		projectResultPage.clickMapChartProjectDensityWashington();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(homePage.isWashingtonMapBackgroundDarkGrey(),
				"Failed to background colour as grey in WashingtonMap");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify if Project Type Chart can be added to any custom-viz-dashboard and refreshed according to project search result on Section visualization page.")
	public void tc3645(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		String projectCountBefore = projectResultPage.getChartProjectResultsCount();
		projectResultPage.selectProjectTypeUnderFirstChartView();
		projectResultPage.selectSectionsUnderSecondChartView();
		projectResultPage.clickBarChartProjectTypeHousing();
		Assert.assertTrue(projectResultPage.verifyBarChartProjectTypeHousingSelected(),
				"Failed to get the value of the bar chart project type selected");
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		String projectCountAfter = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify if Division Chart can be added to any custom-viz-dashboard and can change Div group  search-criteria according to chart selections on Project visualization Dashboard 1 and 2 page.")
	public void tc3646(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectSectionsUnderFirstChartView();
		projectResultPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDivisionChartDisplayed(), "Failed to select Division under Dashboard1");

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectSectionsUnderFirstChartView();
		projectResultPage.selectDivisionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDivisionChartDisplayed(), "Failed to select Division under Dashboard2");
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Type' button renamed to 'Project Groups' on the DGN dashboard 'Select a Visualization' screen.")
	public void dgntc3644_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		Assert.assertTrue(projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom.");
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Type' button renamed to 'Project Groups' on the DGN dashboard 'Select a Visualization' screen.")
	public void dgntc3644_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();

		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Type' button renamed to 'Project Groups' on the DGN dashboard 'Select a Visualization' screen.")
	public void dgntc3644_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Type' button renamed to 'Project Groups' on the DGN dashboard 'Select a Visualization' screen.")
	public void dgntc3644_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 2 Verification
		// Right Quadrant

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertTrue(projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom.");
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' button functionality and persistence.")
	public void dgntc3645_3649_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' button functionality and persistence.")
	public void dgntc3645_3649_2(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' button functionality and persistence.")
	public void dgntc3645_3649_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' button functionality and persistence.")
	public void dgntc3645_3649_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 2 Verification	
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Project Groups VIZ chart shows Project Groups by default")
	public void dgntc3650_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Project Groups VIZ chart shows Project Groups by default")
	public void dgntc3650_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();	

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Project Groups VIZ chart shows Project Groups by default")
	public void dgntc3650_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
	}


	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Project Groups VIZ chart shows Project Groups by default")
	public void dgntc3650_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' VIZ chart subtitle.")
	public void dgntc3651_1(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.VerifyProjectGroupSubtitleOnLeftChartDisplayed(),
				"Failed to display Project Groups subtitle in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' VIZ chart subtitle.")
	public void dgntc3651_2(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.VerifyProjectGroupSubtitleOnRightChartDisplayed(),
				"Failed to display Project Groups subtitle in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' VIZ chart subtitle.")
	public void dgntc3651_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.VerifyProjectGroupSubtitleOnLeftChartDisplayed(),
				"Failed to display Project Groups subtitle in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify 'Project Gropus' VIZ chart subtitle.")
	public void dgntc3651_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();		
		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.VerifyProjectGroupSubtitleOnRightChartDisplayed(),
				"Failed to display Project Groups subtitle in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Customize icon functionality on Project Groups VIZ chart.")
	public void dgntc3652_1(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart1Dashboard2(),
				"Failed to display Back button on the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisabledOnChart(),
				"Failed to display Project Groups Tile Disabled in the Left Chart View");
		projectResultPage.clickOnBackBtnOnChart1Dashboard2();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");
		projectResultPage.clickOnFirstChartCustomizeTile();
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Customize icon functionality on Project Groups VIZ chart.")
	public void dgntc3652_2(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification

		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisabledOnChart(),
				"Failed to display Project Groups Tile Disabled in the Left Chart View");
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart2Dashboard2());
		projectResultPage.clickOnBackBtnOnChart2Dashboard2();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());
		homePage.clickOnSignOutButton();
	}


	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Customize icon functionality on Project Groups VIZ chart.")
	public void dgntc3652_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();


		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisabledOnChart(),
				"Failed to display Project Groups Tile Disabled in the Left Chart View");
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart1Dashboard2());
		projectResultPage.clickOnBackBtnOnChart1Dashboard2();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");
		projectResultPage.clickOnFirstChartCustomizeTile();
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Customize icon functionality on Project Groups VIZ chart.")
	public void dgntc3652_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();


		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnLeftChart());
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisabledOnChart(),
				"Failed to display Project Groups Tile Disabled in the Left Chart View");
		Assert.assertTrue(projectResultPage.isBackBtnDisplayedOnChart2Dashboard2());
		projectResultPage.clickOnBackBtnOnChart2Dashboard2();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isSelectionVisualizationScreenDisplayedOnRightChart());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Expand icon functionality on Project Group bar chart.")
	public void dgntc3657_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Types"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();
		homePage.clickOnSignOutButton();
	}
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Expand icon functionality on Project Group bar chart.")
	public void dgntc3657_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Types"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Expand icon functionality on Project Group bar chart.")
	public void dgntc3657_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();


		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconLeftChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Types"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();
		homePage.clickOnSignOutButton();
	}



	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify Expand icon functionality on Project Group bar chart.")
	public void dgntc3657_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();


		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();

		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnExpandIconRightChart();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChartHeaderPopup().equalsIgnoreCase("Project Types"),
				"Chart heading on expanded view is not as expected.");
		Assert.assertTrue(projectResultPage.getChartSubheaderPopup().equalsIgnoreCase("Distribution by Count"),
				"Chart sub heading on expanded view is not as expected.");
		projectResultPage.clickOnexpandOnCloseBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button within user's login session")
	public void dgntc3683_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button within user's login session")
	public void dgntc3683_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button within user's login session")
	public void dgntc3683_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();		

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button within user's login session")
	public void dgntc3683_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();		

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button within user's login session")
	public void dgntc3690_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button within user's login session")
	public void dgntc3690_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button within user's login session")
	public void dgntc3690_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button within user's login session")
	public void dgntc3690_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button within user's login session")
	public void dgntc3689_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button within user's login session")
	public void dgntc3689_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button within user's login session")
	public void dgntc3689_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		projectResultPage.clickOnHomeTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button within user's login session")
	public void dgntc3689_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.ClickOnCompaniesTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button across user's login session")
	public void dgntc3679_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button across user's login session")
	public void dgntc3679_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button across user's login session")
	public void dgntc3679_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Group' radio button across user's login session")
	public void dgntc3679_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonDisplayed(),
				"Failed to display Project Groups Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button across user's login session")
	public void dgntc3682_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button across user's login session")
	public void dgntc3682_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");


		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button across user's login session")
	public void dgntc3682_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Left Chart View");


		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Categories' radio button across user's login session")
	public void dgntc3682_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonDisplayed(),
				"Failed to display Project Categories Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button across user's login session")
	public void dgntc3681_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button across user's login session")
	public void dgntc3681_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button across user's login session")
	public void dgntc3681_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Left Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Persistence behavior of 'Project Types' radio button across user's login session")
	public void dgntc3681_4(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonDisplayed(),
				"Failed to display Project Types Radio button in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage.ClickOnProjectsTab();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Group' radio button is selected.")
	public void dgntc3646_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Group' radio button is selected.")
	public void dgntc3646_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnRightChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Group' radio button is selected.")
	public void dgntc3646_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Group' radio button is selected.")
	public void dgntc3646_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeGroups(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectGroupsRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnRightChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.waitforLoadingRing();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Categories' radio button is selected.")
	public void dgntc3647_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Categories.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Categories' radio button is selected.")
	public void dgntc3647_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();	
		// Right Quadrant

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnRightChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Categories.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Categories' radio button is selected.")
	public void dgntc3647_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeCategories(),
				"Failed to display Project Categories in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Categories Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Categories.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Categories' radio button is selected.")
	public void dgntc3647_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypeCategories(),
				"Failed to display Project Groups in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectCategoriesRadioButtonSelected(),
				"Failed to select Project Groups Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnRightChart(2);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.waitforLoadingRing();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Types' radio button is selected.")
	public void dgntc3648_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypes(),
				"Failed to display Project Types in the Left Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(1);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Types.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Types' radio button is selected.")
	public void dgntc3648_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		Assert.assertTrue(projectResultPage.isChartRightContainsProjectTypes(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnRightChart(1);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Types.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Types' radio button is selected.")
	public void dgntc3648_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectType(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(1);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify click action when 'Project Types' radio button is selected.")
	public void dgntc3648_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant//
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectTypesRadioButton();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectType(),
				"Failed to display Project Types in the Right Chart View");
		Assert.assertTrue(projectResultPage.isProjectTypesRadioButtonSelected(),
				"Failed to select Project Types Radio button in the Right Chart View");
		projectResultPage.clickOnMultipleBarsOnLeftChart(1);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList().contains("Project Type Category -"),
				"Crumb filter link is not displayed for Project Groups.");
		projectResultPage.clickOnProjectGroupsFilter();
		Assert.assertTrue(projectResultPage.isProjectCatgoriesFilterCheckboxesChecked(),
				"Failed due to respective project categories not selected in left nav filter");
		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.waitforLoadingRing();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Project Type Group/Category/Type labeling is changed is as required in BRD")
	public void dgntc3691_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Left nav project type labels
		projectResultPage.clickOnMultipleBarsOnLeftChart(2);
		Assert.assertEquals(projectResultPage.getLeftNavProjectTypeCategoriesFilterLabel().equalsIgnoreCase(
				"Prj. Type Categories"), "Failed due to Project Type Categories not displayed in left nav");
		Assert.assertEquals(projectResultPage.getLeftNavProjectTypeFilterLabel().equalsIgnoreCase("Project Types "),
				"Failed due to Project Type Categories not displayed in left nav");
		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.waitforLoadingRing();

		// Dashboard 1 Verification
		// Left Quadrant//
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.verifyProjectGroupsRadioButtonLabel(),
				"Failed to display Project Groups Radio button label as 'Groups' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectCategoriesRadioButtonLabel(),
				"Failed to display Project Categories Radio button label as 'Categories' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectTypesRadioButtonLabel(),
				"Failed to display Project Types Radio button label as 'Types' in the Left Chart View");
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Types"),
				"Chart heading on chart is not as expected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Project Type Group/Category/Type labeling is changed is as required in BRD")
	public void dgntc3691_2(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();


		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.verifyProjectGroupsRadioButtonLabel(),
				"Failed to display Project Groups Radio button label as 'Groups' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectCategoriesRadioButtonLabel(),
				"Failed to display Project Categories Radio button label as 'Categories' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectTypesRadioButtonLabel(),
				"Failed to display Project Types Radio button label as 'Types' in the Left Chart View");
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Types"),
				"Chart heading on chart is not as expected.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Project Type Group/Category/Type labeling is changed is as required in BRD")
	public void dgntc3691_3(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.verifyProjectGroupsRadioButtonLabel(),
				"Failed to display Project Groups Radio button label as 'Groups' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectCategoriesRadioButtonLabel(),
				"Failed to display Project Categories Radio button label as 'Categories' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectTypesRadioButtonLabel(),
				"Failed to display Project Types Radio button label as 'Types' in the Left Chart View");
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Project Types"),
				"Chart heading on chart is not as expected.");


		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify the Project Type Group/Category/Type labeling is changed is as required in BRD")
	public void dgntc3691_4(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 2 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.verifyProjectGroupsRadioButtonLabel(),
				"Failed to display Project Groups Radio button label as 'Groups' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectCategoriesRadioButtonLabel(),
				"Failed to display Project Categories Radio button label as 'Categories' in the Left Chart View");
		Assert.assertTrue(projectResultPage.verifyProjectTypesRadioButtonLabel(),
				"Failed to display Project Types Radio button label as 'Types' in the Left Chart View");
		Assert.assertTrue(projectResultPage.getChart1HeaderDashboard2().equalsIgnoreCase("Ownership Type"),
				"Chart heading on expanded view is not as expected.");
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Type Groups"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Type Categories"),
				"Chart heading on chart is not as expected.");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.getChart2HeaderDashboard2().equalsIgnoreCase("Project Types"),
				"Chart heading on chart is not as expected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3661_1(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"display Project Type in the Left Chart View");
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getLeftManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(1) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		List<String> NameList1 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(3));
		List<String> NameList2 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3661_2(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getRightManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		projectResultPage.waitforLoadingRing();
		List<Integer> valueList2 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.waitforLoadingRing();
		List<String> NameList1 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(3));
		projectResultPage.waitforLoadingRing();
		List<String> NameList2 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList2.equals(CommonUtils.sortWebElements(NameList2, false)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3661_3(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"display Project Type in the Left Chart View");
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getLeftManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(1) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		List<String> NameList1 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(3));
		List<String> NameList2 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3661_4(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getRightManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		projectResultPage.waitforLoadingRing();
		List<Integer> valueList2 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.waitforLoadingRing();
		List<String> NameList1 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(3));
		projectResultPage.waitforLoadingRing();
		List<String> NameList2 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList2.equals(CommonUtils.sortWebElements(NameList2, false)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3660_1(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"display Project Type in the Left Chart View");
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getLeftManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(1) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		List<String> NameList1 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(3));
		List<String> NameList2 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3660_2(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getRightManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		projectResultPage.waitforLoadingRing();
		List<Integer> valueList2 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.waitforLoadingRing();
		List<String> NameList1 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(3));
		projectResultPage.waitforLoadingRing();
		List<String> NameList2 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList2.equals(CommonUtils.sortWebElements(NameList2, false)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3660_3(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isChartLeftContainsProjectTypeGroups(),
				"display Project Type in the Left Chart View");
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getLeftManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(1) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		List<String> NameList1 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(3));
		List<String> NameList2 = projectResultPage.getLeftChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify sort functionality on Project Categories VIZ chart")
	public void dgnt3660_4(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Right Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(), "Count Highest - Lowest",
				"Default sort drop down option for Project Groups in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		Assert.assertTrue(projectResultPage.getRightManufacturersSortDropdownValueList().equals(sortOptionList),
				"Project Groups sort drop down list is valid.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		projectResultPage.waitforLoadingRing();
		List<Integer> valueList2 = projectResultPage.getDistributionCountListRightQuadron();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is as expected when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.waitforLoadingRing();
		List<String> NameList1 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList1.equals(CommonUtils.sortWebElements(NameList1, true)),
				"Sorting is as expected when " + sortOptionList.get(2) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(3));
		projectResultPage.waitforLoadingRing();
		List<String> NameList2 = projectResultPage.getRightChartBarTextValueList();
		Assert.assertTrue(NameList2.equals(CommonUtils.sortWebElements(NameList2, false)),
				"Sorting is as expected when " + sortOptionList.get(3) + " is selected.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify UI/Styles of sort drop down on Project Groups VIZ chart")
	public void dgnt3659_1(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.isLeftSortDropDownOptionDisplayed(), "Sort drop down field is displayed as per Mock up.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify UI/Styles of sort drop down on Project Groups VIZ chart")
	public void dgnt3659_2(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.isRightSortDropDownOptionDisplayed(), "Sort drop down field is displayed as per Mock up.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify UI/Styles of sort drop down on Project Groups VIZ chart")
	public void dgnt3659_3(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.isLeftSortDropDownOptionDisplayed(), "Sort drop down field is displayed as per Mock up.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "Verify UI/Styles of sort drop down on Project Groups VIZ chart")
	public void dgnt3659_4(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.isRightSortDropDownOptionDisplayed(), "Sort drop down field is displayed as per Mock up.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "When the radio buttons are changed, the previously selected sort option is honored.")
	public void dgnt3663_1(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "When the radio buttons are changed, the previously selected sort option is honored.")
	public void dgnt3663_2(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "When the radio buttons are changed, the previously selected sort option is honored.")
	public void dgnt3663_3(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnFirstChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnLeftChart());
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getLeftSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = VisualizationDataProvider.class, dataProvider = "TCPlatiAdminSpec", description = "When the radio buttons are changed, the previously selected sort option is honored.")
	public void dgnt3663_4(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		// Dashboard 1 Verification
		// Left Quadrant
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(projectResultPage.isProjectTypeTileDisplayedOnRightChart());
		Assert.assertEquals("Project Type", projectResultPage.VerifyProjectTypeTileTextOnChart());
		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickProjectGroupsRadioButton();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest",
				"Alphabetical - A to Z", "Alphabetical - Z to A");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(2));
		projectResultPage.clickProjectCategoriesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
		projectResultPage.clickProjectTypesRadioButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectResultPage.getRightSelectedSortOptionForManufacturers(),"Alphabetical - A to Z");
	}
}

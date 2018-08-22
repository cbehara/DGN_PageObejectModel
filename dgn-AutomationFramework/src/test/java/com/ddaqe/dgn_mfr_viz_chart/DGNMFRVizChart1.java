package com.ddaqe.dgn_mfr_viz_chart;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.dgn_project_report.ProjectReportDataProvider;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LicensePreferencePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNMFRVizChart1 extends DGNMFRVizChartDataSet {
	static Logger log = Logger.getLogger(DGNMFRVizChart1.class);

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

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tcDGNT1846(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("fire alarm");
		homePage.clickOnSearchButton();
		if (projectResultPage.isFindInContentTypeCollapsed()) {
			projectResultPage.clickFindInCollapsed();
		}
		projectResultPage.checkPlanInFindInContentType();
		projectResultPage.uncheckAllFindInFilterExceptOne("Plans");
		ProjectPlansPage projectPlanPage = projectResultPage.clickOnPlansLink();
		Assert.assertTrue(projectPlanPage.IsPlansTabHighlighted(),
				"Failed to get the Plans tab highlighted");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Display of checkboxes on the MFR VIZ chart")
	public void tcDGNT3465(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed");

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed in dashboard 2.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed in dashboard 2.");
		
		projectResultPage.closeMfrUserFavouredPopupButton();
		
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Display of checkboxes on the MFR VIZ chart")
	public void tcDGNT3466(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.closeMfrUserFavouredPopupButton();
		// Basic of design checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with Basis of Design is not Selected by default in dashboard 1.");

		// Specified checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// Basic of design checkbox operation
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Unchecking Basic of Design checkbox does not remove the record from MFR VIZ in dashboard 1.");

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Checking Basic of Design checkbox does not update the record in MFR VIZ in dashboard 1.");

		// Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Unchecking Specified checkbox does not remove the record from MFR VIZ in dashboard 1.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Checking Specified checkbox does not update the record in MFR VIZ in dashboard 1.");

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.selectManufacturersUnderFirstChartView();

		// Basic of design checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed in dashboard 2.");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with Basis of Design is not Selected by default in dashboard 2.");

		// Specified checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed in dashboard 2");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// Basic of design checkbox operation
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Unchecking Basic of Design checkbox does not remove the record from MFR VIZ in dashboard 2.");

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Checking Basic of Design checkbox does not update the record in MFR VIZ in dashboard 2.");

		// Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Unchecking Specified checkbox does not remove the record from MFR VIZ in dashboard 2.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Checking Specified checkbox does not update the record in MFR VIZ in dashboard 2.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sort drop down options and default option")
	public void tcDGNT3491(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertEquals(
				projectResultPage.getLeftSelectedSortOptionForManufacturers(),
				"Count Highest - Lowest",
				"Default sort drop down option for manufacturers in not 'Count Highest - Lowest'");
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest",
				"Count Lowest - Highest");
		Assert.assertTrue(
				projectResultPage.getLeftManufacturersSortDropdownValueList()
						.equals(sortOptionList),
				"Manufactures sort drop down list is invalid.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertEquals(
				projectResultPage.getLeftSelectedSortOptionForManufacturers(),
				"Count Highest - Lowest",
				"Default sort drop down option for manufacturers in not 'Count Highest - Lowest'");
		Assert.assertTrue(
				projectResultPage.getLeftManufacturersSortDropdownValueList()
						.equals(sortOptionList),
				"Manufactures sort drop down list is invalid.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sorting according to the options selected in the dropdown.")
	public void tcDGNT3492(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest",
				"Count Lowest - Highest");
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList2 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList3 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList4 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sorting according to the 'BOD' and 'Specified' check box selection.")
	public void tcDGNT3493_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest",
				"Count Lowest - Highest");
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList1 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList2 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList3 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList4 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sorting according to the 'BOD' and 'Specified' check box selection.")
	public void tcDGNT3493_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest",
				"Count Lowest - Highest");
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList1 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList2 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList3 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList4 = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sorting according to the 'BOD' and 'Specified' check box selection.")
	public void tcDGNT3493_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest",
				"Count Lowest - Highest");
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList1 = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList2 = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(0));
		List<Integer> valueList3 = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0)
						+ " is selected.");

		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers(sortOptionList
						.get(1));
		List<Integer> valueList4 = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils
				.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1)
						+ " is selected.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "De-selection of the BOD and Specified checkboxes on MFR VIZ chart.")
	public void tcDGNT3468(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// Basic of design checkbox operation
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Unchecking Basic of Design checkbox does not remove the record from MFR VIZ in dashboard 1.");

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();

		// Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Unchecking Specified checkbox does not remove the record from MFR VIZ in dashboard 1.");
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		// Dashboard 2
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed in dashboard 2.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed in dashboard 2");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with Basis of Design is not Selected by default in dashboard 2.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// Basic of design checkbox operation
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Unchecking Basic of Design checkbox does not remove the record from MFR VIZ in dashboard 2.");

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();

		// Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Unchecking Specified checkbox does not remove the record from MFR VIZ in dashboard 2.");
		projectResultPage.selectMFRVizSpecifiedCheckbox();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Manufacturer name on the MFR VIZ chart.")
	public void tcDGNT3470(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.checkDisplayeNameAtLeftChart1Dashboard(),
				"MFR name is not displayed on the left side of the MFR bar in dashboard 1.");
		Assert.assertTrue(
				projectResultPage
						.checkDisplayEllipsesOnExceedCharCountChart1Dashboard(),
				"Display ellipses is not shown if more than two lines or exceed character count limit in dashboarde 1.");

		// Dashboard 2
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.checkDisplayeNameAtLeftChart1Dashboard(),
				"MFR name is not displayed on the left side of the MFR bar in dashboard 2.");
		Assert.assertTrue(
				projectResultPage
						.checkDisplayEllipsesOnExceedCharCountChart1Dashboard(),
				"Display ellipses is not shown if more than two lines or exceed character count limit in dashboarde 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify display of MFRs on the Chart.")
	public void tcDGNT3473(String emailAddress, String password) {
		String defaultSortOption = "Count Highest - Lowest";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(projectResultPage
				.verifyTopHundredFilterPresentChart1Dashboard(),
				"Displays up-to 100 manufacturers on the VIZ chart is not shown.");
		Assert.assertEquals(
				projectResultPage.getLeftSelectedSortOptionForManufacturers(),
				defaultSortOption);

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(projectResultPage
				.verifyTopHundredFilterPresentChart2Dashboard(),
				"Displays up-to 100 manufacturers on the VIZ chart is not shown.");
		Assert.assertEquals(
				projectResultPage.getRightSelectedSortOptionForManufacturers(),
				defaultSortOption);

		// Dashboard 2
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage
						.verifyTopHundredFilterPresentChart1Dashboard(),
				"Displays up-to 100 manufacturers on the VIZ chart is not shown on dashboard 2.");
		Assert.assertEquals(
				projectResultPage.getLeftSelectedSortOptionForManufacturers(),
				defaultSortOption);

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage
						.verifyTopHundredFilterPresentChart2Dashboard(),
				"Displays up-to 100 manufacturers on the VIZ chart is not shown on dashboard 2.");
		Assert.assertEquals(
				projectResultPage.getRightSelectedSortOptionForManufacturers(),
				defaultSortOption);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify the project count shown for each MFR.")
	public void tcDGNT3474(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Project counts shown to the right of each VIZ bar BOD counts is not shown for all bars in left VIZ.");
		Assert.assertTrue(
				projectResultPage
						.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Project counts shown to the right of each VIZ bar Specified counts is not shown for all bars in left VIZ.");
		Assert.assertTrue(
				projectResultPage.isLeftAllProjectCountBODClickbale(),
				"The project count shown for each MFR BOD is a clickable link in left chart.");
		Assert.assertTrue(
				projectResultPage.isLeftAllProjectCountSpecifiedClickbale(),
				"The project count shown for each MFR Specified is a clickable link in left chart.");
		Assert.assertTrue(
				projectResultPage.verifyScrollBarPresentVizChart1Dashboard(),
				"The MFR VIZ chart does not have scroll bar which enables scrolling through the list of MFRs.");

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart2(),
				"Project counts shown to the right of each VIZ bar BOD counts is not shown for all bars in right VIZ.");
		Assert.assertTrue(
				projectResultPage
						.checkProjectCountSpecifiedPresentForAllBarChart2(),
				"Project counts shown to the right of each VIZ bar Specified counts is not shown for all bars in right VIZ.");
		Assert.assertTrue(
				projectResultPage.isRightAllProjectCountBODClickbale(),
				"The project count shown for each MFR BOD is a clickable link in right chart.");
		Assert.assertTrue(
				projectResultPage.isRightAllProjectCountSpecifiedClickbale(),
				"The project count shown for each MFR BOD is a clickable link in right chart.");
		Assert.assertTrue(
				projectResultPage.verifyScrollBarPresentVizChart2Dashboard(),
				"The MFR VIZ chart does not have scroll bar which enables scrolling through the list of MFRs for right chart.");

		// Dashboard 2
		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Project counts shown to the right of each VIZ bar BOD counts is not shown for all bars in left VIZ.");
		Assert.assertTrue(
				projectResultPage
						.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Project counts shown to the right of each VIZ bar Specified counts is not shown for all bars in left VIZ.");
		Assert.assertTrue(
				projectResultPage.isLeftAllProjectCountBODClickbale(),
				"The project count shown for each MFR BOD is a clickable link in left chart.");
		Assert.assertTrue(
				projectResultPage.isLeftAllProjectCountSpecifiedClickbale(),
				"The project count shown for each MFR Specified is a clickable link in left chart.");
		Assert.assertTrue(
				projectResultPage.verifyScrollBarPresentVizChart1Dashboard(),
				"The MFR VIZ chart does not have scroll bar which enables scrolling through the list of MFRs.");

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart2(),
				"Project counts shown to the right of each VIZ bar BOD counts is not shown for all bars in right VIZ.");
		Assert.assertTrue(
				projectResultPage
						.checkProjectCountSpecifiedPresentForAllBarChart2(),
				"Project counts shown to the right of each VIZ bar Specified counts is not shown for all bars in right VIZ.");
		Assert.assertTrue(
				projectResultPage.isRightAllProjectCountBODClickbale(),
				"The project count shown for each MFR BOD is a clickable link in right chart.");
		Assert.assertTrue(
				projectResultPage.isRightAllProjectCountSpecifiedClickbale(),
				"The project count shown for each MFR BOD is a clickable link in right chart.");
		Assert.assertTrue(
				projectResultPage.verifyScrollBarPresentVizChart2Dashboard(),
				"The MFR VIZ chart does not have scroll bar which enables scrolling through the list of MFRs for right chart.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' checkbox selections persist within session.")
	public void tcDGNT3494_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' checkbox selections persist within session.")
	public void tcDGNT3494_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' checkbox selections persist within session.")
	public void tcDGNT3494_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertFalse(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' checkbox selections persist within session.")
	public void tcDGNT3494_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertFalse(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Filter crumb for each selected Manufacturer when both 'BOD' and 'Specified' checkboxes are selected in the chart")
	public void tcDGNT3495(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnClearAllLinkInFilter();

		List<String> selectedManufacturer = new ArrayList<String>();
		String selectedSection = projectResultPage
				.clickFirstManufacturerBarChart1Dashboard();
		selectedManufacturer.add(selectedSection);
		Assert.assertEquals(projectResultPage.getcrumbSecondFiltertxt(),
				selectedManufacturer.get(0),
				"Selected manufacturer is not displayed in bread crumb.");
		projectResultPage.clickOnClearAllLinkInFilter();
		List<String> selectedManufacturers = projectResultPage
				.clickOnMultipleManufacturerBarChart1Dashboard(5);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.contains("Manufacturers -"),
				"Crumb filter link is not displayed for manufacturer.");
		projectResultPage.clickOnCrumbGroupFilterLink("Manufacturers");
		Assert.assertEquals(
				projectResultPage.getCrumbFilterTextListFromFilterPopup(),
				selectedManufacturers,
				"Selected manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupFirstCloseBtn();
		Assert.assertNotEquals(
				projectResultPage.getCrumbFilterTextListFromFilterPopup(),
				selectedManufacturers,
				"Selected manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupDonebtn();
		projectResultPage.removeCrumbGroupFilter("Manufacturers");
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.isEmpty(), "Crumb filter link is displayed for manufacturer.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'Specified' checkbox selections persist within session.")
	public void tcDGNT3496_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are displayed.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'Specified' checkbox selections persist within session.")
	public void tcDGNT3496_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are displayed.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'Specified' checkbox selections persist within session.")
	public void tcDGNT3496_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are displayed.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'Specified' checkbox selections persist within session.")
	public void tcDGNT3496_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are displayed.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify order of buttons on the DGN dashboard 'Select a Visualization' screen.")
	public void tcDGNT3475_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertTrue(
				projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom.");

		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(
				projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify order of buttons on the DGN dashboard 'Select a Visualization' screen.")
	public void tcDGNT3475_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();

		projectResultPage.clickOnDashboard2ToggleButton();
		projectResultPage.clickOnFirstChartCustomizeTile();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom in Dashboard 2.");

		projectResultPage.clickOnSecondChartCustomizeTile();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.verifyVisualizationBtnInAscendingOrder(),
				"Buttons on the 'Select a Visualization' screen are not ordered in ascending alphabetical order, from left-to-right,  top-to-bottom in Dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' and 'Specified' checkbox selections persist within session")
	public void tcDGNT3497_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' and 'Specified' checkbox selections persist within session")
	public void tcDGNT3497_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' and 'Specified' checkbox selections persist within session")
	public void tcDGNT3497_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify that 'BOD' and 'Specified' checkbox selections persist within session")
	public void tcDGNT3497_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertFalse(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertFalse(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");

		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Default setting of both bars will be shown if User logs out and logs back in")
	public void tcDGNT3498_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);
		projectResultPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed by default.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed by default.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Default setting of both bars will be shown if User logs out and logs back in")
	public void tcDGNT3498_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);
		projectResultPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed by default.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed by default.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Default setting of both bars will be shown if User logs out and logs back in")
	public void tcDGNT3498_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);
		projectResultPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed by default.");
		Assert.assertTrue(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are not displayed by default.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Default setting of both bars will be shown if User logs out and logs back in")
	public void tcDGNT3498_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnSignOutButton();

		homePage = loginAs(emailAddress, password);
		projectResultPage = homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basic of Design' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked by default.");
		Assert.assertTrue(
				projectResultPage.checkRightBasicOfDesignBarValuePresent(),
				"'Basic of Design' values are not displayed by default.");
		Assert.assertTrue(
				projectResultPage.checkRightSpecifiedBarValuePresent(),
				"'Specified' values are not displayed by default.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Add the Manufacturer (MFR) visualization (VIZ) chart to the current dashboard.")
	public void tcDGNT3476_1(String emailAddress, String password)
			throws InterruptedException {
		String vizMFR = "Manufacturers";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 1.");
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ is currently shown, so 'Manufacturers' button is not grayed-out for left chart (i.e., not clickable) in dashboard 1.");
		projectResultPage.clickOnSectionTypeUnderFirstChartView();

		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 1.");
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnSecondChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyRightVisualizationStatus(vizMFR),
				"MFR VIZ is currently shown, so 'Manufacturers' button is not grayed-out for right chart (i.e., not clickable) in dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Add the Manufacturer (MFR) visualization (VIZ) chart to the current dashboard.")
	public void tcDGNT3476_2(String emailAddress, String password)
			throws InterruptedException {
		String vizMFR = "Manufacturers";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeTileUnderFirstChartView();
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 2.");
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ is currently shown, so 'Manufacturers' button is not grayed-out for left chart (i.e., not clickable) in dashboard 2.");
		projectResultPage.clickOnSectionTypeUnderFirstChartView();

		projectResultPage.selectProjectTypeTileUnderSecondChartView();
		projectResultPage.clickOnSecondChartCustomizeTile();
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 2.");
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnSecondChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyRightVisualizationStatus(vizMFR),
				"MFR VIZ is currently shown, so 'Manufacturers' button is not grayed-out for right chart (i.e., not clickable) in dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "The MFR VIZ chart can be shown only once on a dashboard..")
	public void tcDGNT3479_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart.");

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the right MFR VIZ chart.");
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 1.");

		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart.");
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the right chart in dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "The MFR VIZ chart can be shown only once on a dashboard..")
	public void tcDGNT3479_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart.");

		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the right MFR VIZ chart.");
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the left chart in dashboard 2.");

		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart.");
		Assert.assertTrue(
				projectResultPage
						.isManufacturersUnderVisaulChartViewDisplayed(),
				"Manufacturer (MFR) visualization (VIZ) chart is not displayed in the right chart in dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Display of MFR VIZ chart is persisted on the dashboard within session.")
	public void tcDGNT3480_1(String emailAddress, String password)
			throws InterruptedException {
		String vizMFR = "Manufacturers";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 1.");

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 1.");
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ chart is not displayed in the same quadrants of a Dashboard 1 in which it was added (upper left or the upper right quadrant) in dashboard 1.");
		projectResultPage.clickOnLeftDashboardBackArrow();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Display of MFR VIZ chart is persisted on the dashboard within session.")
	public void tcDGNT3480_2(String emailAddress, String password)
			throws InterruptedException {
		String vizMFR = "Manufacturers";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 2.");

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 2.");
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ chart is not displayed in the same quadrants of a Dashboard 2 in which it was added (upper left or the upper right quadrant) in dashboard 2.");
		projectResultPage.clickOnLeftDashboardBackArrow();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Display of MFR VIZ chart is persisted on the dashboard within different session.")
	public void tcDGNT3481(String emailAddress, String password)
			throws InterruptedException {
		String vizMFR = "Manufacturers";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();

		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 1.");
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ chart is not displayed in the same quadrants of a Dashboard 1 in which it was added (upper left or the upper right quadrant) in dashboard 2.");
		projectResultPage.clickOnLeftDashboardBackArrow();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"MFR chart is not open in the left MFR VIZ chart in dashboard 2.");
		projectResultPage.clickOnFirstChartCustomizeTile();

		Assert.assertFalse(
				projectResultPage.verifyLeftVisualizationStatus(vizMFR),
				"MFR VIZ chart is not displayed in the same quadrants of a Dashboard 2 in which it was added (upper left or the upper right quadrant) in dashboard 2.");
		projectResultPage.clickOnLeftDashboardBackArrow();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Both “BOD” and “Specified” checkboxes cannot be de-selected or unchecked at the same time.")
	public void tcDGNT3482_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		// Basic of design checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed in dashboard 1.");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with Basis of Design is not Selected by default in dashboard 1.");

		// Specified checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed in dashboard 1.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// UnSelect Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Only BOD portion of current result set is not displayed on VIZ chart in dashboard 1.");

		// Checking Specified should be automatically be selected.
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected in dashboard 1.");
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Only Specified portion of current result set is not displayed on VIZ chart in dashboard 1.");

		// Checking BOD should be automatically be selected.
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with BOD is not Selected in dashboard 1.");
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Only BOD portion of current result set is not displayed on VIZ chart in dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Both “BOD” and “Specified” checkboxes cannot be de-selected or unchecked at the same time.")
	public void tcDGNT3482_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		// Basic of design checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"Checkboxes with Basis of Design label is not displayed in dashboard 2.");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with Basis of Design is not Selected by default in dashboard 2.");

		// Specified checkbox operation
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"Checkboxes with Specified label is not displayed in dashboard 1.");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected by default in dashboard 1.");

		// UnSelect Specified checkbox operation
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Only BOD portion of current result set is not displayed on VIZ chart in dashboard 1.");

		// Checking Specified should be automatically be selected.
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"Checkboxes with Specified is not Selected in dashboard 1.");
		Assert.assertFalse(
				projectResultPage.checkLeftBasicOfDesignBarValuePresent(),
				"Only Specified portion of current result set is not displayed on VIZ chart in dashboard 1.");

		// Checking BOD should be automatically be selected.
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"Checkboxes with BOD is not Selected in dashboard 1.");
		Assert.assertFalse(
				projectResultPage.checkLeftSpecifiedBarValuePresent(),
				"Only BOD portion of current result set is not displayed on VIZ chart in dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying Specified only bars")
	public void tcDGNT3484_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();

		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(projectResultPage
				.compareProjectResultsCount(specifiedBarValueList.get(0)
						.toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizLeftSpecifiedFilterBarIntegerValueList()),
				"Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying Specified only bars")
	public void tcDGNT3484_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();

		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(
				projectResultPage
						.compareProjectResultsCount(specifiedBarValueList
								.get(0).toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria in dashboard 2.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizLeftSpecifiedFilterBarIntegerValueList()),
				"Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboard 2.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2 .");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying BOD only bars")
	public void tcDGNT3485_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(projectResultPage
				.compareProjectResultsCount(basicOfDesignBarValueList.get(0)
						.toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria.");
		Assert.assertTrue(
				basicOfDesignBarValueList.containsAll(projectResultPage
						.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList()),
				"Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = basicOfDesignBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying BOD only bars")
	public void tcDGNT3485_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(
				projectResultPage
						.compareProjectResultsCount(basicOfDesignBarValueList
								.get(0).toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria in dashboard 2.");
		Assert.assertTrue(
				basicOfDesignBarValueList.containsAll(projectResultPage
						.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList()),
				"Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboard 2.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = basicOfDesignBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying both Specified and BOD Specified bars on left quadrant dashboard 1.")
	public void tcDGNT3486_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(projectResultPage
				.compareProjectResultsCount(specifiedBarValueList.get(0)
						.toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria.");
		Assert.assertTrue(
				basicOfDesignBarValueList.containsAll(projectResultPage
						.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList()),
				"BOD - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizLeftSpecifiedFilterBarIntegerValueList()),
				"Specified - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying both Specified and BOD Specified bars on right quadrant dashboard 1.")
	public void tcDGNT3486_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizRightBasicOfDesignFilterBarIntegerValueList();
		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart2Dashboard();
		Assert.assertTrue(projectResultPage
				.compareProjectResultsCount(specifiedBarValueList.get(0)
						.toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria.");
		Assert.assertTrue(
				basicOfDesignBarValueList
						.containsAll(projectResultPage
								.getMFRVizRightBasicOfDesignFilterBarIntegerValueList()),
				"BOD - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizRightSpecifiedFilterBarIntegerValueList()),
				"Specified - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");

		projectResultPage.clickFirstManufacturerBarChart2Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying both Specified and BOD Specified bars on left quadrant dashboard 2.")
	public void tcDGNT3486_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList();
		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertTrue(
				projectResultPage
						.compareProjectResultsCount(specifiedBarValueList
								.get(0).toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
		Assert.assertTrue(
				basicOfDesignBarValueList.containsAll(projectResultPage
						.getMFRVizLeftBasicOfDesignFilterBarIntegerValueList()),
				"BOD - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboarde 2.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizLeftSpecifiedFilterBarIntegerValueList()),
				"Specified - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboarde 2.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");

		projectResultPage.clickFirstManufacturerBarChart1Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Select MFR(s) as advanced criteria for filtering when chart is displaying both Specified and BOD Specified bars on right quadrant dashboard 2.")
	public void tcDGNT3486_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();

		List<Integer> basicOfDesignBarValueList = projectResultPage
				.getMFRVizRightBasicOfDesignFilterBarIntegerValueList();
		List<Integer> specifiedBarValueList = projectResultPage
				.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		projectResultPage.clickFirstManufacturerBarChart2Dashboard();
		Assert.assertTrue(
				projectResultPage
						.compareProjectResultsCount(specifiedBarValueList
								.get(0).toString()),
				" Search results are filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
		Assert.assertTrue(
				basicOfDesignBarValueList
						.containsAll(projectResultPage
								.getMFRVizRightBasicOfDesignFilterBarIntegerValueList()),
				"BOD - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboarde 2.");
		Assert.assertTrue(
				specifiedBarValueList.containsAll(projectResultPage
						.getMFRVizRightSpecifiedFilterBarIntegerValueList()),
				"Specified - Project counts by all MFR bars are changed, regardless of whether the MFR bar is selected or not in dashboarde 2.");

		projectResultPage.clickOnClearAllLinkInFilter();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(3);

		int firstMFRSelectValue = specifiedBarValueList.get(0);
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");

		projectResultPage.clickFirstManufacturerBarChart2Dashboard();
		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
		projectResultPage.clickOnClearAllLinkInFilter();

		Assert.assertFalse(
				projectResultPage.compareProjectResultsCount(String
						.valueOf(firstMFRSelectValue)),
				"Search results are not filtered according to selected MFR(s) advanced-criteria in dashboarde 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Filter crumb for each selected Manufacturer when only 'BOD' checkbox is selected in the chart.")
	public void tcDGNT3505(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnClearAllLinkInFilter();

		List<String> selectedManufacturer = new ArrayList<String>();
		String selectedSection = projectResultPage
				.clickFirstManufacturerBarChart1Dashboard();
		selectedManufacturer.add(selectedSection);
		Assert.assertTrue(
				projectResultPage.getcrumbSecondFiltertxt().contains(
						selectedManufacturer.get(0)),
				"Selected BOD is not displayed in bread crumb.");
		projectResultPage.clickOnClearAllLinkInFilter();
		List<String> selectedManufacturers = projectResultPage
				.clickOnMultipleManufacturerBarChart1Dashboard(5);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.contains("BOD Manufacturers -"),
				"Crumb filter link is not displayed for BOD Manufacturers.");
		projectResultPage.clickOnCrumbGroupFilterLink("BOD Manufacturers");
		Assert.assertTrue(
				projectResultPage
						.verifyCrumbFilterTextListFromFilterPopup(selectedManufacturers),
				"Selected BOD Manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupFirstCloseBtn();
		Assert.assertFalse(
				projectResultPage
						.verifyCrumbFilterTextListFromFilterPopup(selectedManufacturers),
				"Selected BOD Manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupDonebtn();
		projectResultPage.removeCrumbGroupFilter("BOD Manufacturers");
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.isEmpty(),
				"Crumb filter link is displayed for BOD Manufacturers.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Filter crumb for each selected Manufacturer when only 'Specified' checkbox is selected in the chart.")
	public void tcDGNT3506(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnClearAllLinkInFilter();

		List<String> selectedManufacturer = new ArrayList<String>();
		String selectedSection = projectResultPage
				.clickFirstManufacturerBarChart1Dashboard();
		selectedManufacturer.add(selectedSection);
		Assert.assertTrue(
				projectResultPage.getcrumbSecondFiltertxt().contains(
						selectedManufacturer.get(0)),
				"Selected BOD is not displayed in bread crumb.");
		projectResultPage.clickOnClearAllLinkInFilter();
		List<String> selectedManufacturers = projectResultPage
				.clickOnMultipleManufacturerBarChart1Dashboard(5);
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.contains("Manufacturers -"),
				"Crumb filter link is not displayed for specified Manufacturers.");
		projectResultPage.clickOnCrumbGroupFilterLink("Manufacturers");
		Assert.assertTrue(
				projectResultPage
						.verifyCrumbFilterTextListFromFilterPopup(selectedManufacturers),
				"Selected specified Manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupFirstCloseBtn();
		Assert.assertFalse(
				projectResultPage
						.verifyCrumbFilterTextListFromFilterPopup(selectedManufacturers),
				"Selected specified Manufacturers are not displayed on pupup.");
		projectResultPage.clickOnCrumbFilterPopupDonebtn();
		projectResultPage.removeCrumbGroupFilter("Manufacturers");
		Assert.assertTrue(projectResultPage.getCrumbGroupFilterLinkList()
				.isEmpty(),
				"Crumb filter link is displayed for specified Manufacturers.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR Search criteria: Create save searches with MFR criteria.(p-manufacturer).")
	public void tcDGNT3508(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		List<String> selectedManufacturers = projectResultsPage
				.clickOnMultipleManufacturerBarChart1Dashboard(3);
		SavedSearchPopUp savedSearchPopUp = projectResultsPage
				.clickOnSavedSearch();
		String searchName = CommonUtils
				.appendRandomNumberInString("Automation");
		savedSearchPopUp.enterName(searchName);
		savedSearchPopUp.clickSave();

		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is not checked by default.");
		Assert.assertTrue(projectResultsPage.getAllCrumbFilterTextList()
				.containsAll(selectedManufacturers),
				"'Specified' check box selection doesn't persist when Save search performed.'");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR Search criteria: Saved search with MFR criteria can be retrieved and executed with that MFR criteria.")
	public void tcDGNT3509_tcDGNT3510(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		SavedSearchesPage savedSearchesPage;
		String searchName = CommonUtils
				.appendRandomNumberInString("Automation");
		try {
			List<String> selectedManufacturers = projectResultsPage
					.clickOnMultipleManufacturerBarChart1Dashboard(3);
			SavedSearchPopUp savedSearchPopUp = projectResultsPage
					.clickOnSavedSearch();
			savedSearchPopUp.enterName(searchName);
			savedSearchPopUp.clickSave();
			projectResultsPage.waitforLoadingRing();
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
			projectResultsPage.waitforLoadingRing();
			projectResultsPage.clickDashboard1ToggleBtn();
			projectResultsPage.waitforLoadingRing();

			Assert.assertTrue(
					projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
					"'Specified' check box is not checked by default.");
			Assert.assertTrue(projectResultsPage.getAllCrumbFilterTextList()
					.containsAll(selectedManufacturers),
					"'Specified' check box selection doesn't persist when Save search performed.'");

			projectResultsPage.ClickOncrumbSecondFilterClose();
			projectResultsPage.waitforLoadingRing();
			savedSearchPopUp = projectResultsPage.clickOnSavedSearch();
			savedSearchPopUp.clickSave();
			savedSearchPopUp.clickOnOKButtonForOverrideSaveSearch();
			projectResultsPage.waitforLoadingRing();
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
			projectResultsPage.waitforLoadingRing();
			projectResultsPage.clickDashboard1ToggleBtn();
			projectResultsPage.waitforLoadingRing();

			Assert.assertTrue(
					projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
					"'Specified' check box is not checked by default.");
			Assert.assertNotEquals(
					projectResultsPage.getAllCrumbFilterTextList(),
					selectedManufacturers,
					"'Specified' check box selection doesn't persist when Save search performed.'");

		} finally {
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.deleteSavedSearch(searchName);
		}
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tcDGNT3518(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tcDGNT3519(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isexpandOnCloseBtnDisplayed(),
				"Expand-close button is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnCloseBtn();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tcDGNT3520(String emailAddress, String password) {
		String searchText = "Sambeet123098";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSavedSearch();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "BOD Search criteria: Create save searches with BOD search criteria (p-manufacturer-bod).")
	public void tcDGNT3511_tcDGNT3515(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		List<String> selectedManufacturers = new ArrayList<>();
		selectedManufacturers.add("USA");
		selectedManufacturers.addAll(projectResultsPage
				.clickOnMultipleManufacturerBarChart1Dashboard(3));
		SavedSearchPopUp savedSearchPopUp = projectResultsPage
				.clickOnSavedSearch();
		String searchName = CommonUtils
				.appendRandomNumberInString("Automation");
		savedSearchPopUp.enterName(searchName);
		savedSearchPopUp.clickSave();

		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"'Basis of Design' check box is not checked by default.");
		Assert.assertFalse(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"'Specified' check box is checked.");
		Assert.assertFalse(
				projectResultsPage.checkLeftSpecifiedBarValuePresent(),
				"'Specified' values are displayed.");
		Assert.assertTrue(
				projectResultsPage
						.verifyCrumbFilterTextListFromMainFilterCrumb(selectedManufacturers),
				"'Basis of Design' check box selection doesn't persist when Save search performed.'");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "BOD Search criteria: Saved search with BOD criteria can be retrieved and executed with that criteria.")
	public void tcDGNT3512_tcDGNT3513(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		SavedSearchesPage savedSearchesPage;
		String searchName = CommonUtils
				.appendRandomNumberInString("Automation");
		try {
			List<String> selectedManufacturers = new ArrayList<>();
			selectedManufacturers.add("USA");
			selectedManufacturers.addAll(projectResultsPage
					.clickOnMultipleManufacturerBarChart1Dashboard(3));
			SavedSearchPopUp savedSearchPopUp = projectResultsPage
					.clickOnSavedSearch();
			savedSearchPopUp.enterName(searchName);
			savedSearchPopUp.clickSave();
			projectResultsPage.waitforLoadingRing();
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
			projectResultsPage.waitforLoadingRing();
			projectResultsPage.clickDashboard1ToggleBtn();
			projectResultsPage.waitforLoadingRing();

			Assert.assertTrue(
					projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
					"'Basis of Design' check box is not checked by default.");
			Assert.assertTrue(
					projectResultsPage
							.verifyCrumbFilterTextListFromMainFilterCrumb(selectedManufacturers),
					"'Basis of Design' check box selection doesn't persist when Save search performed.'");

			projectResultsPage.ClickOncrumbSecondFilterClose();
			projectResultsPage.waitforLoadingRing();
			savedSearchPopUp = projectResultsPage.clickOnSavedSearch();
			savedSearchPopUp.clickSave();
			savedSearchPopUp.clickOnOKButtonForOverrideSaveSearch();
			projectResultsPage.waitforLoadingRing();
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
			projectResultsPage.waitforLoadingRing();
			projectResultsPage.clickDashboard1ToggleBtn();
			projectResultsPage.waitforLoadingRing();

			Assert.assertTrue(
					projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
					"'Basis of Design' check box is not checked by default.");
			Assert.assertFalse(
					projectResultsPage
							.verifyCrumbFilterTextListFromMainFilterCrumb(selectedManufacturers),
					"'Basis of Design' check box selection doesn't persist when Save search performed.'");

		} finally {
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.deleteSavedSearch(searchName);
		}
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of MFR criteria box for BOD MFR.")
	public void tcDGNT3595(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(25);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of MFR criteria box for specified and BOD MFR.")
	public void tcDGNT3596(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(25);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of MFR criteria box for specified MFR.")
	public void tcDGNT3594(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(25);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify MFR criteria box is not displayed in PDF viewer for documents not MFR-enriched.")
	public void tcDGNT3598(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(25);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage
				.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Saved search cannot have both MFR and BOD search criteria.")
	public void tcDGNT3514(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		SavedSearchesPage savedSearchesPage;
		String searchName = CommonUtils
				.appendRandomNumberInString("Automation");
		try {
			List<String> selectedManufacturers = new ArrayList<>();
			selectedManufacturers.add("USA");
			selectedManufacturers.addAll(projectResultsPage
					.clickOnMultipleManufacturerBarChart1Dashboard(3));
			SavedSearchPopUp savedSearchPopUp = projectResultsPage
					.clickOnSavedSearch();
			savedSearchPopUp.enterName(searchName);
			savedSearchPopUp.clickSave();
			projectResultsPage.waitforLoadingRing();
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			Assert.assertTrue(
					savedSearchesPage.isSavedSearchPresent(searchName),
					"Saved search is not displayed under My Saved Search list.");
		} finally {
			homePage.clickOnMyAccountLink();
			savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
			savedSearchesPage.deleteSavedSearch(searchName);
		}
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Saved search cannot have both MFR and BOD search criteria.")
	public void tcDGNT3516(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultsPage.isExpandIconOnLeftChartDisplayed(),
				"Expand icon on left chart is not displayed.");
		projectResultsPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultsPage.isExpandIconOnRightChartDisplayed(),
				"Expand icon on right chart is not displayed.");

		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultsPage.isExpandIconOnLeftChartDisplayed(),
				"Expand icon on left chart is not displayed.");
		projectResultsPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultsPage.isExpandIconOnRightChartDisplayed(),
				"Expand icon on right chart is not displayed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify find-a-manufacturer text-box prompt text.")
	public void tcDGNT3559_1(String emailAddress, String password)
			throws InterruptedException {
		String placeholderString = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		Assert.assertEquals(
				projectResultsPage.getVizLeftFilterSearchTextPlaceholder(),
				placeholderString);

		projectResultsPage.selectManufacturersUnderSecondChartView();
		Assert.assertEquals(
				projectResultsPage.getVizRightFilterSearchTextPlaceholder(),
				placeholderString);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify find-a-manufacturer text-box prompt text in dashboard 2.")
	public void tcDGNT3559_2(String emailAddress, String password)
			throws InterruptedException {
		String placeholderString = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		Assert.assertEquals(
				projectResultsPage.getVizLeftFilterSearchTextPlaceholder(),
				placeholderString);

		projectResultsPage.selectManufacturersUnderSecondChartView();
		Assert.assertEquals(
				projectResultsPage.getVizRightFilterSearchTextPlaceholder(),
				placeholderString);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "'Find a manufacturer' text box can search all manufacturers")
	public void tcDGNT3556_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.enterMFRVizLeftFilterSearchText(CommonUtils
				.appendRandomNumberInString("Automation"));
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getChartErrorMessage(),
				"No manufacturer data available",
				"Expected error message is not displayed for no record found.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "'Find a manufacturer' text box can search all manufacturers")
	public void tcDGNT3556_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage = projectResultsPage
				.enterMFRVizRightFilterSearchText(CommonUtils
						.appendRandomNumberInString("Automation"));
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getChartErrorMessage(),
				"No manufacturer data available",
				"Expected error message is not displayed for no record found.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "'Find a manufacturer' text box can search all manufacturers")
	public void tcDGNT3556_3(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.enterMFRVizLeftFilterSearchText(CommonUtils
				.appendRandomNumberInString("Automation"));
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getChartErrorMessage(),
				"No manufacturer data available",
				"Expected error message is not displayed for no record found.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "'Find a manufacturer' text box can search all manufacturers")
	public void tcDGNT3556_4(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage = projectResultsPage
				.enterMFRVizRightFilterSearchText(CommonUtils
						.appendRandomNumberInString("Automation"));
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getChartErrorMessage(),
				"No manufacturer data available",
				"Expected error message is not displayed for no record found.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Selects single Manufacturer in the find-a-manufacturer dropdown.")
	public void tcDGNT3560_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		String firstBarText = projectResultsPage
				.getFirstBarTextChart1Dashboard();
		projectResultsPage
				.clickOnLeftSelectedMFRFindInMFRDropdown(firstBarText);
		Assert.assertEquals(projectResultsPage.getMFRVizLeftFilterSearchText(),
				firstBarText);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Selects single Manufacturer in the find-a-manufacturer dropdown.")
	public void tcDGNT3560_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		String firstBarText = projectResultsPage
				.getFirstBarTextChart2Dashboard();

		projectResultsPage
				.clickOnRightSelectedMFRFindInMFRDropdown(firstBarText);
		Assert.assertEquals(
				projectResultsPage.getMFRVizRightFilterSearchText(),
				firstBarText);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Selects single Manufacturer in the find-a-manufacturer dropdown.")
	public void tcDGNT3560_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		String firstBarText = projectResultsPage
				.getFirstBarTextChart1Dashboard();

		projectResultsPage
				.clickOnLeftSelectedMFRFindInMFRDropdown(firstBarText);
		Assert.assertEquals(projectResultsPage.getMFRVizLeftFilterSearchText(),
				firstBarText);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Selects single Manufacturer in the find-a-manufacturer dropdown.")
	public void tcDGNT3560_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		String firstBarText = projectResultsPage
				.getFirstBarTextChart2Dashboard();
		projectResultsPage
				.clickOnRightSelectedMFRFindInMFRDropdown(firstBarText);
		Assert.assertEquals(
				projectResultsPage.getMFRVizRightFilterSearchText(),
				firstBarText);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Find specific manufacturer(s) in the MFR VIZ chart by entering characters into the text-box.")
	public void tcDGNT3561_1(String emailAddress, String password) {
		String searchText = "General";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.enterMFRVizLeftFilterSearchText("Ge");
		Assert.assertFalse(
				projectResultsPage.isLeftChartSearchIconDisplayed(),
				"Magnifying glass is shown for 1 or 2 characters entered left chart dashboard 1.");

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);
		Assert.assertTrue(projectResultsPage.isLeftChartSearchIconDisplayed(),
				"Magnifying glass is not displayed entered left chart dashboard 1.");
		Assert.assertTrue(projectResultsPage
				.isSearchTextContainInFindInMFRListDisplayed(),
				"Suggestion drop down list is not displayed entered left chart dashboard 1.");
		Assert.assertTrue(
				projectResultsPage
						.checkSearchTextContainInFindInMFRList(searchText),
				"Suggestion drop down not displays list of Manufacturer names matching the characters entered in the text box in left chart dashboard 1.");

		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isFirstBarTextChart1Dashboard(),
				"MFRs which contain the characters entered are not displayed in left chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Find specific manufacturer(s) in the MFR VIZ chart by entering characters into the text-box.")
	public void tcDGNT3561_2(String emailAddress, String password) {
		String searchText = "General";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		projectResultsPage.enterMFRVizRightFilterSearchText("Ge");
		Assert.assertFalse(
				projectResultsPage.isRightChartSearchIconDisplayed(),
				"Magnifying glass is shown for 1 or 2 characters entered right chart dashboard 1.");

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);
		Assert.assertTrue(projectResultsPage.isRightChartSearchIconDisplayed(),
				"Magnifying glass is not displayed entered right chart dashboard 1.");
		Assert.assertTrue(projectResultsPage
				.isSearchTextContainInFindInMFRListDisplayed(),
				"Suggestion drop down list is not displayed entered right chart dashboard 1.");
		Assert.assertTrue(
				projectResultsPage
						.checkSearchTextContainInFindInMFRList(searchText),
				"Suggestion drop down not displays list of Manufacturer names matching the characters entered in the text box in left chart dashboard 1.");

		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isFirstBarTextChart2Dashboard(),
				"MFRs which contain the characters entered are not displayed in right chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Find specific manufacturer(s) in the MFR VIZ chart by entering characters into the text-box.")
	public void tcDGNT3561_3(String emailAddress, String password) {
		String searchText = "General";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.enterMFRVizLeftFilterSearchText("Ge");
		Assert.assertFalse(
				projectResultsPage.isLeftChartSearchIconDisplayed(),
				"Magnifying glass is shown for 1 or 2 characters entered left chart dashboard 2.");

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);
		Assert.assertTrue(projectResultsPage.isLeftChartSearchIconDisplayed(),
				"Magnifying glass is not displayed entered left chart dashboard 2.");
		Assert.assertTrue(projectResultsPage
				.isSearchTextContainInFindInMFRListDisplayed(),
				"Suggestion drop down list is not displayed entered left chart dashboard 2.");
		Assert.assertTrue(
				projectResultsPage
						.checkSearchTextContainInFindInMFRList(searchText),
				"Suggestion drop down not displays list of Manufacturer names matching the characters entered in the text box in left chart dashboard 2.");

		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isFirstBarTextChart1Dashboard(),
				"MFRs which contain the characters entered are not displayed in left chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Find specific manufacturer(s) in the MFR VIZ chart by entering characters into the text-box.")
	public void tcDGNT3561_4(String emailAddress, String password) {
		String searchText = "General";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		projectResultsPage.enterMFRVizRightFilterSearchText("Ge");
		Assert.assertFalse(
				projectResultsPage.isRightChartSearchIconDisplayed(),
				"Magnifying glass is shown for 1 or 2 characters entered right chart dashboard 2.");

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);
		Assert.assertTrue(projectResultsPage.isRightChartSearchIconDisplayed(),
				"Magnifying glass is not displayed entered right chart dashboard 2.");
		Assert.assertTrue(projectResultsPage
				.isSearchTextContainInFindInMFRListDisplayed(),
				"Suggestion drop down list is not displayed entered right chart dashboard 2.");
		Assert.assertTrue(
				projectResultsPage
						.checkSearchTextContainInFindInMFRList(searchText),
				"Suggestion drop down not displays list of Manufacturer names matching the characters entered in the text box in left chart dashboard 2.");

		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isFirstBarTextChart2Dashboard(),
				"MFRs which contain the characters entered are not displayed in right chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify functionality of 'X' in the find-a-manufacturer text-box.")
	public void tcDGNT3562_1(String emailAddress, String password) {
		String searchText = "General";
		String placeholder = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);

		Assert.assertTrue(
				projectResultsPage.isLeftChartRemoveIconDisplayed(),
				"'x' is not displayed on the right side of the :find-a-manufacturer text box in left chart dashboard 1.");

		projectResultsPage.clickOnLeftChartRemoveIcon();
		Assert.assertFalse(
				projectResultsPage.getMFRVizLeftFilterSearchText().equals(
						searchText),
				"All characters are not removed from the find-a -manufacturer textbox in left chart dashboard 1 .");
		Assert.assertEquals(
				projectResultsPage.getVizLeftFilterSearchTextPlaceholder(),
				placeholder);
		Assert.assertTrue(projectResultsPage
				.verifyTopHundredFilterPresentChart1Dashboard(),
				"The original MFR list is not restored in left chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify functionality of 'X' in the find-a-manufacturer text-box.")
	public void tcDGNT3562_2(String emailAddress, String password) {
		String searchText = "General";
		String placeholder = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);

		Assert.assertTrue(
				projectResultsPage.isRightChartRemoveIconDisplayed(),
				"'x' is not displayed on the right side of the :find-a-manufacturer text box in right chart dashboard 1.");

		projectResultsPage.clickOnRightChartRemoveIcon();
		Assert.assertFalse(
				projectResultsPage.getMFRVizRightFilterSearchText().equals(
						searchText),
				"All characters are not removed from the find-a -manufacturer textbox in right chart dashboard 1.");
		Assert.assertEquals(
				projectResultsPage.getVizRightFilterSearchTextPlaceholder(),
				placeholder);
		Assert.assertTrue(projectResultsPage
				.verifyTopHundredFilterPresentChart2Dashboard(),
				"The original MFR list is not restored in right chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify functionality of 'X' in the find-a-manufacturer text-box.")
	public void tcDGNT3562_3(String emailAddress, String password) {
		String searchText = "General";
		String placeholder = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);

		Assert.assertTrue(
				projectResultsPage.isLeftChartRemoveIconDisplayed(),
				"'x' is not displayed on the right side of the :find-a-manufacturer text box in left chart dashboard 2.");

		projectResultsPage.clickOnLeftChartRemoveIcon();
		Assert.assertFalse(
				projectResultsPage.getMFRVizLeftFilterSearchText().equals(
						searchText),
				"All characters are not removed from the find-a -manufacturer textbox in left chart dashboard 2 .");
		Assert.assertEquals(
				projectResultsPage.getVizLeftFilterSearchTextPlaceholder(),
				placeholder);
		Assert.assertTrue(projectResultsPage
				.verifyTopHundredFilterPresentChart1Dashboard(),
				"The original MFR list is not restored in left chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify functionality of 'X' in the find-a-manufacturer text-box.")
	public void tcDGNT3562_4(String emailAddress, String password) {
		String searchText = "General";
		String placeholder = "Enter Manufacturer Name";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);

		Assert.assertTrue(
				projectResultsPage.isRightChartRemoveIconDisplayed(),
				"'x' is not displayed on the right side of the :find-a-manufacturer text box in right chart dashboard 2.");

		projectResultsPage.clickOnRightChartRemoveIcon();
		Assert.assertFalse(
				projectResultsPage.getMFRVizRightFilterSearchText().equals(
						searchText),
				"All characters are not removed from the find-a -manufacturer textbox in right chart dashboard 2.");
		Assert.assertEquals(
				projectResultsPage.getVizRightFilterSearchTextPlaceholder(),
				placeholder);
		Assert.assertTrue(projectResultsPage
				.verifyTopHundredFilterPresentChart2Dashboard(),
				"The original MFR list is not restored in right chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sub-header is cognizant of number of MFRs in result set.")
	public void tcDGNT3563_DGNT3527_1(String emailAddress, String password) {
		String searchText = "General";
		String defaultSubHeader = "Top 100 Manufacturers";
		String randomString = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		Assert.assertEquals(projectResultsPage.getLeftChartSubHeaderText(),
				defaultSubHeader);

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage.compareNthCountForLeftChartSubHeaderText(),
				"Sub-header is not displayed as N Manufacturers in left chart dashboard 1.");

		projectResultsPage.enterMFRVizLeftFilterSearchText(randomString);
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertFalse(projectResultsPage.isLeftChartSubHeaderDisplayed(),
				"Sub-header is displayed not record Manufacturers in left chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sub-header is cognizant of number of MFRs in result set.")
	public void tcDGNT3563_DGNT3527_2(String emailAddress, String password) {
		String searchText = "General";
		String defaultSubHeader = "Top 100 Manufacturers";
		String randomString = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		Assert.assertEquals(projectResultsPage.getRightChartSubHeaderText(),
				defaultSubHeader);

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage.compareNthCountForRightChartSubHeaderText(),
				"Sub-header is not displayed as N Manufacturers in Right chart dashboard 1.");

		projectResultsPage.enterMFRVizRightFilterSearchText(randomString);
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertFalse(projectResultsPage.isRightChartSubHeaderDisplayed(),
				"Sub-header is displayed not record Manufacturers in Right chart dashboard 1.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sub-header is cognizant of number of MFRs in result set.")
	public void tcDGNT3563_DGNT3527_3(String emailAddress, String password) {
		String searchText = "General";
		String defaultSubHeader = "Top 100 Manufacturers";
		String randomString = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		Assert.assertEquals(projectResultsPage.getLeftChartSubHeaderText(),
				defaultSubHeader);

		projectResultsPage.enterMFRVizLeftFilterSearchText(searchText);
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage.compareNthCountForLeftChartSubHeaderText(),
				"Sub-header is not displayed as N Manufacturers in left chart dashboard 2.");

		projectResultsPage.enterMFRVizLeftFilterSearchText(randomString);
		projectResultsPage.clickOnLeftChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertFalse(projectResultsPage.isLeftChartSubHeaderDisplayed(),
				"Sub-header is displayed not record Manufacturers in left chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart sub-header is cognizant of number of MFRs in result set.")
	public void tcDGNT3563_DGNT3527_4(String emailAddress, String password) {
		String searchText = "General";
		String defaultSubHeader = "Top 100 Manufacturers";
		String randomString = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();

		Assert.assertEquals(projectResultsPage.getRightChartSubHeaderText(),
				defaultSubHeader);

		projectResultsPage.enterMFRVizRightFilterSearchText(searchText);
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage.compareNthCountForRightChartSubHeaderText(),
				"Sub-header is not displayed as N Manufacturers in Right chart dashboard 2.");

		projectResultsPage.enterMFRVizRightFilterSearchText(randomString);
		projectResultsPage.clickOnRightChartSearchIcon();
		projectResultsPage.waitforLoadingRing();

		Assert.assertFalse(projectResultsPage.isRightChartSubHeaderDisplayed(),
				"Sub-header is displayed not record Manufacturers in Right chart dashboard 2.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart rendering : In-session Specified persistence for saved search includes Specified search-criteria.")
	public void tcDGNT3567_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		homePage.clickOnCompaniesLink();
		projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BOD check box should be checked in left chart dashboard 1.");
		Assert.assertFalse(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"Specified check box is selected in left chart dashboard 1 and it is not persist.");

		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		SavedSearchPopUp saveSearchPopUp = homePage
				.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Private");
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage
						.checkMFRSaveSearchUnderMySearches(searchName),
				"Save search name should appear under My Searches drop down in left chart dashboard 1.");

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount
				.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.deleteSavedSearch(searchName);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart rendering : In-session Specified persistence for saved search includes Specified search-criteria.")
	public void tcDGNT3567_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		homePage.clickOnCompaniesLink();
		projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BOD check box should be checked in left chart dashboard 1.");
		Assert.assertFalse(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"Specified check box is selected in left chart dashboard 1 and it is not persist.");

		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(3);
		SavedSearchPopUp saveSearchPopUp = homePage
				.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Private");
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage
						.checkMFRSaveSearchUnderMySearches(searchName),
				"Save search name should appear under My Searches drop down in left chart dashboard 1.");

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount
				.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.deleteSavedSearch(searchName);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart rendering : In-session BOD persistence for saved search includes BOD search-criteria.")
	public void tcDGNT3568_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnCompaniesLink();
		projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"Specified check box should be checked in left chart dashboard 1.");
		Assert.assertFalse(
				projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BOD check box is selected in left chart dashboard 1 and it is not persist.");

		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		SavedSearchPopUp saveSearchPopUp = homePage
				.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Private");
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage
						.checkMFRSaveSearchUnderMySearches(searchName),
				"Save search name should appear under My Searches drop down in left chart dashboard 1.");

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount
				.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.deleteSavedSearch(searchName);
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart rendering : In-session BOD persistence for saved search includes BOD search-criteria.")
	public void tcDGNT3568_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();

		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		homePage.clickOnCompaniesLink();
		projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.isMFRVizSpecifiedCheckboxSelected(),
				"Specified check box should be checked in left chart dashboard 1.");
		Assert.assertFalse(
				projectResultsPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BOD check box is selected in left chart dashboard 1 and it is not persist.");

		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		SavedSearchPopUp saveSearchPopUp = homePage
				.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Private");
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();

		Assert.assertTrue(
				projectResultsPage
						.checkMFRSaveSearchUnderMySearches(searchName),
				"Save search name should appear under My Searches drop down in left chart dashboard 1.");

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount
				.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption("Private");
		savedSearchesPage.deleteSavedSearch(searchName);
	}

	@Test(groups = { "Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tcDGNT1849(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisabled(),
				"Failed to display the previous link as disabled");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(),
				"Failed to display the next link as enabled");
		goToBackPage();
		projectResultPage.clickOnSecondProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(),
				"Failed to display the previous link as enabled");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(),
				"Failed to display the next link as enabled");
		goToBackPage();

		projectResultPage.clickOnLastProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(),
				"Failed to display the previous link as enabled");
		Assert.assertTrue(!projectPage.isNextLinkDisabled(),
				"Failed to display the next link as disabled");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_1_tcDGNT3573(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_3(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_4(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_5(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_6(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_7(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned ON.")
	public void tcDGNT3570_8(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		licensePreferencePage.switchToDefault();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Check box for 'Enable highlighting within documents' is not checked.");

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		projectSpecsPage = projectResultsPage.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_3(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_4(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_5(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_6(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_7(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Visibility of MFR highlighting: License level highlighting option is turned OFF.")
	public void tcDGNT3571_8(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.unCheckEnableKeywordHighlightCheckbox();
		licensePreferencePage.clickOnSaveButton();
		Assert.assertFalse(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Check box for 'Enable highlighting within documents' is checked.");

		licensePreferencePage.switchToDefault();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultsPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultsPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted.");
		Assert.assertTrue(projectSpecsPage.isSpecsNumberHighlighted(),
				"Specs number is not highlighted.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Filter Crumb removal from the filter crumb drawer.")
	public void tcDGNT3524_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		List<String> appliedFilterList = projectResultsPage
				.getAppliedFilterTexts();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertNotEquals(projectResultsPage.getAppliedFilterTexts(),
				appliedFilterList, "Applied filter is not removed.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Filter Crumb removal from the filter crumb drawer.")
	public void tcDGNT3524_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		List<String> appliedFilterList = projectResultsPage
				.getAppliedFilterTexts();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertNotEquals(projectResultsPage.getAppliedFilterTexts(),
				appliedFilterList, "Applied filter is not removed.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Filter Crumb removal from the filter crumb drawer.")
	public void tcDGNT3524_3(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		List<String> appliedFilterList = projectResultsPage
				.getAppliedFilterTexts();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertNotEquals(projectResultsPage.getAppliedFilterTexts(),
				appliedFilterList, "Applied filter is not removed.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Filter Crumb removal from the filter crumb drawer.")
	public void tcDGNT3524_4(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		List<String> appliedFilterList = projectResultsPage
				.getAppliedFilterTexts();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		Assert.assertNotEquals(projectResultsPage.getAppliedFilterTexts(),
				appliedFilterList, "Applied filter is not removed.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Left-nav filter selection / deselection.")
	public void tcDGNT3525_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.uncheckNewsFindInFilter();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
		projectResultsPage.clickOnClearAllLinkInFilter();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Left-nav filter selection / deselection.")
	public void tcDGNT3525_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.uncheckNewsFindInFilter();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
		projectResultsPage.clickOnClearAllLinkInFilter();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Left-nav filter selection / deselection.")
	public void tcDGNT3525_3(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.uncheckNewsFindInFilter();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
		projectResultsPage.clickOnClearAllLinkInFilter();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on Left-nav filter selection / deselection.")
	public void tcDGNT3525_4(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(2,
				"ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.uncheckNewsFindInFilter();
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
		projectResultsPage.clickOnClearAllLinkInFilter();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' for keyword search.")
	public void tcDGNT3581(String emailAddress, String password)
			throws InterruptedException {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on other VIZ Chart selection / deselection.")
	public void tcDGNT3526_1(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String selectedSection = projectResultsPage
				.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.getAppliedFilterTexts().contains(
						selectedSection), selectedSection
						+ " is not selected and not displayed in bread crumb.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getAppliedFilterTexts().contains(
						selectedSection), selectedSection
						+ " is not selected and not displayed in bread crumb.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "MFR VIZ chart refresh based on other VIZ Chart selection / deselection.")
	public void tcDGNT3526_2(String emailAddress, String password)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		String expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.selectSectionsUnderSecondChartView();
		String selectedSection = projectResultsPage
				.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultsPage.getAppliedFilterTexts().contains(
						selectedSection), selectedSection
						+ " is not selected and not displayed in bread crumb.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");

		expectedProjectResultCount = projectResultsPage
				.getChartProjectResultsCount();
		projectResultsPage.clickFirstSectionBarInRightChart();
		projectResultsPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultsPage.getAppliedFilterTexts().contains(
						selectedSection), selectedSection
						+ " is not selected and not displayed in bread crumb.");
		Assert.assertNotEquals(
				projectResultsPage.getChartProjectResultsCount(),
				expectedProjectResultCount,
				"MFR VIZ chart should not be refreshed.");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only BOD search and keyword+BOD search.")
	public void tcDGNT3582_1(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "BOD";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"BOD selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"BOD selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only BOD search and keyword+BOD search.")
	public void tcDGNT3582_2(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "BOD";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"BOD selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"BOD selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only MFR search and keyword+MFR search (Only Specified checkbox checked).")
	public void tcDGNT3584_1(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		criteria = projectResultPage.getFirstBarTextChart1Dashboard();

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only MFR search and keyword+MFR search (Only Specified checkbox checked).")
	public void tcDGNT3584_2(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		criteria = projectResultPage.getFirstBarTextChart1Dashboard();

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only MFR search and keyword+MFR search (Both BOD and Specified checkbox checked).")
	public void tcDGNT3585_1(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		criteria = projectResultPage.getFirstBarTextChart1Dashboard();

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only MFR search and keyword+MFR search (Both BOD and Specified checkbox checked).")
	public void tcDGNT3585_2(String emailAddress, String password)
			throws InterruptedException {
		String searchTextWood = "wood";
		String searchText = "door";
		String criteria = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(3);

		criteria = projectResultPage.getFirstBarTextChart1Dashboard();

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchText),
				"keyword entered not contain in search criteria.");

		homePage.enterSearchText(searchTextWood);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(criteria),
				"Specified selection criteria is not contain.");
		Assert.assertTrue(projectResultPage
				.checkFilterCrumbContainSeachCriteria(searchTextWood),
				"keyword entered not contain in search criteria.");

		projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify behavior of the 'Only show specs with matches' toggle on the Project Details 'Specs' page for only MFR search and keyword+MFR search (Both BOD and Specified checkbox checked).")
	public void tcDGNT3586(String emailAddress, String password)
			throws InterruptedException {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();
		Assert.assertTrue(
				projectSpecsPage.isOnlyShowOFFToggleButtonDisplayed(),
				"Only show specs with matches - toggle is not in OFF state");
		Assert.assertTrue(
				projectSpecsPage.checkUnHighlightedSpecListedWhenToggleBtnOFF(),
				"As the toggle is OFF, all spec documents are not listed, regardless of whether they are highlighted or not.");
		projectSpecsPage.clickOnOnlyShowOFFToggleButton();
		Assert.assertTrue(
				projectSpecsPage.checkHighlightedSpecListedWhenToggleBtnON(),
				"As the toggle is ON,  only those spec documents are not listed which are highlighted because spec match found only for search keyword.");
		projectSpecsPage.clickOnOnlyShowONToggleButton();

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] If MFR VIZ chart has no data then the Expand icon is not shown(TC20998)")
	public void tc3517_1(String emailAddress, String password) {
		String searchText = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.isexpandOnManufacturerIconDisplayed();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
		homePage.clearSearchText();
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconRightChartDisplayed(),
				"Expand icon is displayed on manufacturer right chart");
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
		homePage.clearSearchText();
		homePage.clickOnSearchButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] If MFR VIZ chart has no data then the Expand icon is not shown(TC20998)")
	public void tc3517_2(String emailAddress, String password) {
		String searchText = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.isexpandOnManufacturerIconDisplayed();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-2");
		homePage.clearSearchText();
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconRightChartDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
		homePage.clearSearchText();
		homePage.clickOnSearchButton();
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Clicking on Close button in expanded chart pop-up closes expanded chart pop-up (TC20998)")
	public void tc3518(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnexpandOnCloseBtn();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify expanded chart is a pop-up(TC20998)")
	public void tc3519(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is displayed on manufacturer chart");
		projectResultPage.clickOnexpandOnManufacturerIcon();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isexpandOnCloseBtnDisplayed(),
				"Expand-close button is displayed on manufacturer chart");
		Assert.assertTrue(projectResultPage.isexpandedChartGraphDisplayed(),
				"Chart Graph is displayed after expand of manufacturer");
		projectResultPage.clickOnexpandOnCloseBtn();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");

		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"Specified project count displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Executed Saved Search show filter crumbs for each MFR/BOD saved search criteria regardless of whether or not that MFR/BOD was included in search results(TC20998)")
	public void tc3520(String emailAddress, String password) {
		String searchText = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickOnSavedSearch();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify MFR VIZ chart when no MFR data to plot in the MFR VIZ chart")
	public void tc3521(String emailAddress, String password) {
		String searchText = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"BasicOfDesignCheckbox is displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"SpecifiedCheckbox is displayed");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify MFR VIZ chart when MFR has zero BOD count and BOD check box checked")
	public void tcDGNT3522(String emailAddress, String password) {
		String searchText = "Automation" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		Assert.assertFalse(
				projectResultPage.isexpandOnManufacturerIconDisplayed(),
				"Expand icon is not displayed when blank chart on dashboard-1");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"BasicOfDesignCheckbox is displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxDisplayed(),
				"SpecifiedCheckbox is displayed");
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BasicOfDesignCheckbox is selected");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"SpecifiedCheckbox is selected");
		Assert.assertFalse(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"BasicOfDesignCheckbox count is not displayed");
		Assert.assertFalse(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] BOD checkbox is re-labeled to Basis of Design")
	public void tcDGNT3523(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxDisplayed(),
				"BasicOfDesignCheckbox is displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify sub-header on MFR VIZ chart when 0, >100 or >0 and <100 MFRs return in the result set")
	public void tcDGNT3527(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage
				.selectOptionFromLeftSortDropdownManufacturers("Lowest");
		projectResultPage.checkProjectCountBODPresentForAllBarChart1();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify that BOD and Specified checkbox settings persisted within session on all Dashboard occurrences of MFR VIZ chart")
	public void tcDGNT3532(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BasicOfDesignCheckbox is selected");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"SpecifiedCheckbox is selected");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BasicOfDesignCheckbox is selected");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"SpecifiedCheckbox is selected");
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart2(),
				"Basic Of Design project count displayed");
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.checkProjectCountBODPresentForAllBarChart1(),
				"Basic Of Design project count displayed");
		Assert.assertFalse(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify identification of selected MFRs removed on VIZ when BOD / pecifie checkbox settings changed to BOD only")
	public void tcDGNT3534(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BasicOfDesignCheckbox is selected");
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"SpecifiedCheckbox is selected");
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertFalse(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizSpecifiedCheckboxSelected(),
				"SpecifiedCheckbox is selected");
		Assert.assertTrue(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		Assert.assertTrue(
				projectResultPage.isMFRVizBasicOfDesignCheckboxSelected(),
				"BasicOfDesignCheckbox is selected");
		Assert.assertFalse(projectResultPage
				.checkProjectCountSpecifiedPresentForAllBarChart1(),
				"SpecifiedCheckbox count is not displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify the label on the Only show specs with keyword matches toggle changed to Only show specs with matches on the Project Details Specs page")
	public void tcDGNT3580(String emailAddress, String password) {
		String searchText = "door";
		String expectedResult = "Only show specs with matches";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();

		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnFirstHighlightedSpec();

		Assert.assertEquals(projectSpecsPage.getOnlyShowOFFToggleButtonText(),
				expectedResult);

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify that Specs tab link is still highlighted when user switches to any other tab link on all Project Details pages when MFR/BOD filters applied to a search")
	public void tcDGNT3587(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickOncheckPlansFindInFilter();
		projectResultPage.clickOnDesignAlertInFindInFilter();
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();

		Assert.assertTrue(projectSpecsPage.isfirmsLinkMenuDisplayed(),
				"firms Link Menu  is Displayed");
		Assert.assertTrue(projectSpecsPage.isplanholdersBiddersLinkDisplayed(),
				" plan holders Bidders Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isPlansLinkDisplayed(),
				"Plans Link  is Displayed");

		Assert.assertTrue(projectSpecsPage.isAddendaLinkIsDisplayed(),
				"Addenda Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isNotesLinkIsDisplayed(),
				"NotesLink is Displayed");

		Assert.assertTrue(projectSpecsPage.isDesignAlertsLinkDisplayed(),
				"DesignAlerts Link is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify that Specs tab link is still highlighted when user switches to any other tab link on all Project Details pages when MFR/BOD filters applied to a search")
	public void tcDGNT3587_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickOncheckPlansFindInFilter();
		projectResultPage.clickOnDesignAlertInFindInFilter();
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();

		Assert.assertTrue(projectSpecsPage.isfirmsLinkMenuDisplayed(),
				"firms Link Menu  is Displayed");
		Assert.assertTrue(projectSpecsPage.isplanholdersBiddersLinkDisplayed(),
				" plan holders Bidders Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isPlansLinkDisplayed(),
				"Plans Link  is Displayed");

		Assert.assertTrue(projectSpecsPage.isAddendaLinkIsDisplayed(),
				"Addenda Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isNotesLinkIsDisplayed(),
				"NotesLink is Displayed");

		Assert.assertTrue(projectSpecsPage.isDesignAlertsLinkDisplayed(),
				"DesignAlerts Link is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify that Specs tab link is still highlighted when user switches to any other tab link on all Project Details pages when MFR/BOD filters applied to a search")
	public void tcDGNT3587_3(String emailAddress, String password) {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickOncheckPlansFindInFilter();
		projectResultPage.clickOnDesignAlertInFindInFilter();
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();

		Assert.assertTrue(projectSpecsPage.isfirmsLinkMenuDisplayed(),
				"firms Link Menu  is Displayed");
		Assert.assertTrue(projectSpecsPage.isplanholdersBiddersLinkDisplayed(),
				" plan holders Bidders Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isPlansLinkDisplayed(),
				"Plans Link  is Displayed");

		Assert.assertTrue(projectSpecsPage.isAddendaLinkIsDisplayed(),
				"Addenda Link  is Displayed");
		Assert.assertTrue(projectSpecsPage.isNotesLinkIsDisplayed(),
				"NotesLink is Displayed");

		Assert.assertTrue(projectSpecsPage.isDesignAlertsLinkDisplayed(),
				"DesignAlerts Link is Displayed");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify spec document link highlighting on the Project Details Specs page for BOD (p-manufacturer-bod) search-criteria")
	public void tcDGNT3578(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecLinkHighlightedInYellow(),
				"firms Link Menu  is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify spec document link highlighting on the Project Details Specs page for BOD (p-manufacturer-bod) search-criteria")
	public void tcDGNT3578_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecLinkHighlightedInYellow(),
				"firms Link Menu  is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify spec document link highlighting on the Project Details Specs page for BOD (p-manufacturer-bod) search-criteria")
	public void tcDGNT3578_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecLinkHighlightedInYellow(),
				"firms Link Menu  is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Refresh of other VIZ charts when MFR criteria selected and/or deselected")
	public void tcDGNT3529_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart2CustomizeTilePresent()) {
			projectResultPage.clickOnSecondChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart2Dashboard());
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Refresh of other VIZ charts when MFR criteria selected and/or deselected")
	public void tcDGNT3529_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart2CustomizeTilePresent()) {
			projectResultPage.clickOnSecondChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart2Dashboard());
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Verify spec document link highlighting on the Project Details Specs page for BOD (p-manufacturer-bod) search-criteria")
	public void tcDGNT3578_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage
				.clickOnSpecsLink();
		Assert.assertTrue(projectSpecsPage.isSpecLinkHighlightedInYellow(),
				"firms Link Menu  is Displayed");
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Text label for the highlighting checkbox option is changed to Enable highlighting within documents on the my License...License Preferences page")
	public void tcDGNT3593(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		Assert.assertTrue(myAccount.isLicensePreferenceDisplayed(),
				"LicensePreference Menu  is Displayed");
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.switchToDefault();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(
				licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Enabled key word highlighting check box Menu ");
		licensePreferencePage.switchToDefault();

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Text label for the highlighting checkbox option is changed to Enable highlighting within documents")
	public void tcDGNT3569(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = myAccount
				.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnEnableKeywordHighlightChk();
		licensePreferencePage.switchToDefault();
		licensePreferencePage.switchToFrame();
		licensePreferencePage.clickOnSaveButton();
		licensePreferencePage.switchToDefault();
		myAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.isDocumentTabDisplay(),
				"Document tab should displayed ");
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.checkDocumentFilterCheckBox();
		Assert.assertTrue(myPreferencesPage.isDocumentCheckBoxSelected(),
				"Document check box status enabled ");

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Remove display of MF95/MF14 children facets in the Spec Division See more pop-up")
	public void tcDGNT3483(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnspecDivision_SeeMore_Popup_btn();
		Assert.assertFalse(
				projectResultPage.isSpecDivisionPopupCollapseAllLinkDisplayed(),
				"Is collapse-all link displayed ");
		Assert.assertFalse(
				projectResultPage.isSpecDivisionPopupExpandAllLinkDisplayed(),
				"Is expand-all link displayed ");
		Assert.assertTrue(projectResultPage
				.checkspecDivision_SeeMore_Popup_SpecGroupsAreCollapsedIsDisplayed());

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Refresh of other VIZ charts when MFR criteria selected and/or deselected")
	public void tcDGNT3528_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart2CustomizeTilePresent()) {
			projectResultPage.clickOnSecondChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart2Dashboard());
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);

	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Refresh of other VIZ charts when MFR criteria selected and/or deselected")
	public void tcDGNT3528_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage
				.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart1CustomizeTilePresent()) {
			projectResultPage.clickOnFirstChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart1Dashboard());
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(2);
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		if (projectResultPage.isChart2CustomizeTilePresent()) {
			projectResultPage.clickOnSecondChartCustomizeTile();
		}
		Assert.assertTrue(projectResultPage
				.isManufacturersDisplayedOnChart2Dashboard());
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(2);

	}
	
	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify edit icon click and mouse hover text")
	public void tcDGNT3616_1DGNT3635_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		
		//Checking for Viz Dashboard 1 Qudrant 1
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.checkCustomizeManufacturersLinkIsDisplayed(),"Customize MFR link not visible");
		assertEquals("Customize Manufacturers", projectResultPage.getCustomizeManufacturersLinkTooltiptext(),"comparing tooltip text for user favored customize MFR link");
		homePage.clickOnSignOutButton();
		
	}

	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify edit icon click and mouse hover text")
	public void tc3616_2DGNT3635_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();		
		//Checking for Viz Dashboard 1 Qudrant 2
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.checkCustomizeManufacturersLinkIsDisplayed(),"Customize MFR link not visible");
		assertEquals("Customize Manufacturers", projectResultPage.getCustomizeManufacturersLinkTooltiptext(),"comparing tooltip text for user favored customize MFR link");
		homePage.clickOnSignOutButton();
		
	}
	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify edit icon click and mouse hover text")
	public void tc3616_3DGNT3635_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		//Checking for Viz Dashboard 2 qudrant 1
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.checkCustomizeManufacturersLinkIsDisplayed(),"Customize MFR link not visible");
		assertEquals("Customize Manufacturers", projectResultPage.getCustomizeManufacturersLinkTooltiptext(),"comparing tooltip text for user favored customize MFR link");			
		homePage.clickOnSignOutButton();
		
	}
	
	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify edit icon click and mouse hover text")
	public void tc3616_4DGNT3635_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		//Checking for Viz Dashboard 2 Qudrant 2
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		assertTrue(projectResultPage.checkCustomizeManufacturersLinkIsDisplayed(),"Customize MFR link not visible");
		assertEquals("Customize Manufacturers", projectResultPage.getCustomizeManufacturersLinkTooltiptext(),"comparing tooltip text for user favored customize MFR link");
		homePage.clickOnSignOutButton();
		
	}
	
	@Test(groups = { "Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify edit icon click and mouse hover text")
	public void tc3619(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		int count = projectResultPage.getSelectedMFRCountAsUserFavoured();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		int count1 = projectResultPage.getSelectedMFRCountAsUserFavoured();
		assertEquals(count, count1-4);
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		int count2 = projectResultPage.getSelectedMFRCountAsUserFavoured();
		assertEquals(count1, count2);
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.removeMFRInUserFavoredPopup(3);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		int count3 = projectResultPage.getSelectedMFRCountAsUserFavoured();
		assertEquals(count+1, count3);
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Customize Manufacturers pop-up  behavior when user does not have any User-favored MFRs defined")
	public void dgnt3674_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(1);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Customize Manufacturers pop-up  behavior when user does not have any User-favored MFRs defined")
	public void dgnt3674_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(1);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Customize Manufacturers pop-up  behavior when user does not have any User-favored MFRs defined")
	public void dgnt3674_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(1);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Customize Manufacturers pop-up  behavior when user does not have any User-favored MFRs defined")
	public void dgnt3674_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.waitforLoadingRing();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnGridViewUnSelectedToggleButton();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(1);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL | My Mfrs toggle across a user's login session")
	public void dgnt3678_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3687_1(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3687_2(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL | My Mfrs toggle across a user's login session")
	public void dgnt3678_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL | My Mfrs toggle across a user's login session")
	public void dgnt3678_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL | My Mfrs toggle across a user's login session")
	public void dgnt3678_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();
		homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3687_3(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL toggle within a user's login session")
	public void dgnt3672_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();

	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL toggle within a user's login session")
	public void dgnt3672_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.selectManufacturersUnderSecondChartView();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL toggle within a user's login session")
	public void dgnt3672_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderFirstChartView();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Persistence behavior of the ALL toggle within a user's login session")
	public void dgnt3672_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.selectManufacturersUnderSecondChartView();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(),
				"The toggle is not showing top 100 MFR in All MFR");
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Customize Manufacturer pop up should open every time user selects MFR VIZ chart until the User selects one or more User favored manufacturers")
	public void dgnt3632(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		projectResultPage.selectActionStageUnderFirstChartView();
		projectResultPage.selectManufacturersUnderSecondChartView();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify customize MFR popup behavior when no user favored MFR previously selected")
	public void dgnt3639(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(), "No User Favoured MFR Messaged is not getting displayed.");
		projectResultPage.selectManufacturersUnderSecondChartView();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		assertFalse(projectResultPage.isNoUserFavouredMFRMessagedisplayed(), "No User Favoured MFR Messaged is getting displayed.");
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify  message on the MFR VIZ chart when user does not select Manufacturer(s) in the pop up and clicks CANCEL or clicks (X)")
	public void dgnt3677_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertFalse(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is getting displayed.");
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing top 100 MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify  message on the MFR VIZ chart when user does not select Manufacturer(s) in the pop up and clicks CANCEL or clicks (X)")
	public void dgnt3677_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertFalse(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is getting displayed.");
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing top 100 MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify  message on the MFR VIZ chart when user does not select Manufacturer(s) in the pop up and clicks CANCEL or clicks (X)")
	public void dgnt3677_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertFalse(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is getting displayed.");
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing top 100 MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify  message on the MFR VIZ chart when user does not select Manufacturer(s) in the pop up and clicks CANCEL or clicks (X)")
	public void dgnt3677_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		assertFalse(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is getting displayed.");
		assertTrue(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing top 100 MFR");
		projectResultPage.clickOnUserfavouredMFRToggle();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.clickOnCustomizeMFRPopupCancelButton();
		assertTrue(projectResultPage.isNoUserFavouredMFRMessagedisplayed(),
				"No User Favoured MFR Messaged is not getting displayed.");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(4);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify user-favored MFRs and \"My MFRs\" toggle persistence when user removes and adds MFR VIZ chart")
	public void dgnt3669_3673_3671_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		List<String> selectedMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getSelectedMFRInCustomizeMFRPopupList());
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		projectResultPage.waitforLoadingRing();
		List<String> userFavouredMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart1Dashboard());
		assertTrue(userFavouredMFR.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.selectOwnershipTypeUnderFirstChartView();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.selectManufacturersUnderFirstChartView();
		List<String> userFavouredMFRNew = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart1Dashboard());
		assertTrue(userFavouredMFRNew.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify user-favored MFRs and \"My MFRs\" toggle persistence when user removes and adds MFR VIZ chart")
	public void dgnt3669_3673_3671_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard1ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		List<String> selectedMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getSelectedMFRInCustomizeMFRPopupList());
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		projectResultPage.waitforLoadingRing();
		List<String> userFavouredMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart2Dashboard());
		assertTrue(userFavouredMFR.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.selectActionStageUnderSecondChartView();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.selectManufacturersUnderSecondChartView();
		List<String> userFavouredMFRNew = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart2Dashboard());
		assertTrue(userFavouredMFRNew.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify user-favored MFRs and \"My MFRs\" toggle persistence when user removes and adds MFR VIZ chart")
	public void dgnt3669_3673_3671_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		List<String> selectedMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getSelectedMFRInCustomizeMFRPopupList());
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		projectResultPage.waitforLoadingRing();
		List<String> userFavouredMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart1Dashboard());
		assertTrue(userFavouredMFR.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.selectOwnershipTypeUnderFirstChartView();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.selectManufacturersUnderFirstChartView();
		List<String> userFavouredMFRNew = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart1Dashboard());
		assertTrue(userFavouredMFRNew.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify user-favored MFRs and \"My MFRs\" toggle persistence when user removes and adds MFR VIZ chart")
	public void dgnt3669_3673_3671_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnListViewIcon();
		projectResultPage.clickDashboard2ToggleBtn();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup is not getting displayed.");
		projectResultPage.selectMFRInUserFavoredPopup(4);
		List<String> selectedMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getSelectedMFRInCustomizeMFRPopupList());
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		projectResultPage.waitforLoadingRing();
		List<String> userFavouredMFR = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart2Dashboard());
		assertTrue(userFavouredMFR.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		projectResultPage.selectActionStageUnderSecondChartView();
		homePage.clickOnCompaniesLink();
		homePage.clickOnProjectsLinkWithPersistantSetting();
		projectResultPage.selectManufacturersUnderSecondChartView();
		List<String> userFavouredMFRNew = projectResultPage
				.removeWhiteSpaceInStringInList(projectResultPage.getAllBarTextChart2Dashboard());
		assertTrue(userFavouredMFRNew.containsAll(selectedMFR),
				"All MFR selected as user favoured are not displayed in the chart");
		assertFalse(projectResultPage.isAllMFRTop100Subtitledisplayed(), "The toggle is not showing user favoured MFR");
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify user-favored MFRs Popup on MFR viz")
	public void dgnt3617(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		assertTrue(projectResultPage.isCustomizeMFRPopupDisplayed(), "Customize MFR popup Title is not correct");
		assertEquals(projectResultPage.getCustomizeMFRPopupheaderTitle(), "CUSTOMIZE MANUFACTURERS",
				"Customize MFR Popup header title not correct");
		assertEquals(projectResultPage.getCustomizeMFRPopupheaderSubTitle(), "(Only 24 selections)",
				"Customize MFR Popup header Sub-title not correct");
		assertEquals(projectResultPage.getCustomizeMFRPopupAllAvailableMFRSectionTitle(), "SELECT MANUFACTURERS",
				"Customize MFR Popup All Available MFR Section Title not correct");
		assertEquals(projectResultPage.getCustomizeMFRPopupSelectedMFRSectionTitle(), "SELECTED MANUFACTURERS",
				"Customize MFR Popup Selected MFR Section Title not correct");
		assertEquals(projectResultPage.getTextFromCustomizeMFRPopupSelectedMFRSectionBox(), "",
				"No message should get displayed");
		projectResultPage.selectMFRInUserFavoredPopup(25);
		assertEquals(projectResultPage.getCustomizeMFRPopupSelectedMFRCount(), "24 of 24",
				"Incorrect selected user favored count is displayed");
		assertTrue(projectResultPage.isCustomizeMFRPopupSelectedMFRCountDisplayedInRed(),
				"Text not highlighted in red");
		assertTrue(projectResultPage.isCustomizeMFRPopupheaderSubTitleDisplayedInRed(), "Text not highligted in red");
		projectResultPage.clickOnCustomizeMFRPopupCloseButton();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		assertEquals(projectResultPage.getTextFromCustomizeMFRPopupSelectedMFRSectionBox(), "",
				"No message should get displayed");
		assertEquals(projectResultPage.getCustomizeMFRPopupSelectedMFRCount(), "0 of 24",
				"Incorrect selected user favored count is displayed");
		projectResultPage.selectMFRInUserFavoredPopup(5);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify \"find-a-manufacturer\" text-box in user-favored MFRs Popup")
	public void dgnt3618(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		assertEquals(projectResultPage.getHelpTextInCustomizeMFRPopupFindAManufacturerTextBox(), "Search",
				"Incorrect preloaded text displayed in the search box");
		int before1 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("sh");
		int After1 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		assertEquals(After1, before1, "The results are changing on entering less than 3 characters in search box.");
		int before2 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("wi");
		int After2 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		assertEquals(After2, before2, "The results are changing on entering less than 3 characters in search box.");
		int before3 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("el");
		int After3 = projectResultPage.getAllMFRCountInUserFavoredPopup();
		assertEquals(After3, before3, "The results are changing on entering less than 3 characters in search box.");
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("she");
		projectResultPage.waitforLoadingRing();
		List<String> After4 = projectResultPage.getAllMFRInUserFavoredPopup();
		assertTrue(projectResultPage.isListValueContainsStringInAllElements(After4, "she"),
				"The results are not customized as per entered keyword.");
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("wil");
		projectResultPage.waitforLoadingRing();
		List<String> After5 = projectResultPage.getAllMFRInUserFavoredPopup();
		assertTrue(projectResultPage.isListValueContainsStringInAllElements(After5, "wil"),
				"The results are not customized as per entered keyword.");
		projectResultPage.addTextInCustomizeMFRPopupFindAManufacturerTextBox("ele");
		projectResultPage.waitforLoadingRing();
		List<String> After6 = projectResultPage.getAllMFRInUserFavoredPopup();
		assertTrue(projectResultPage.isListValueContainsStringInAllElements(After6, "ele"),
				"The results are not customized as per entered keyword.");
		projectResultPage.clickOnCustomizeMFRPopupCloseButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"ALL\" to show all manufacturers ( Top 100 Mfrs )")
	public void dgnt3630_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"ALL\" to show all manufacturers ( Top 100 Mfrs )")
	public void dgnt3630_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"ALL\" to show all manufacturers ( Top 100 Mfrs )")
	public void dgnt3630_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"ALL\" to show all manufacturers ( Top 100 Mfrs )")
	public void dgnt3630_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.clickOnUserfavouredMFRToggle();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"My Mfrs\" to show user-favored manufacturers")
	public void dgnt3631_1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(20);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"My Mfrs\" to show user-favored manufacturers")
	public void dgnt3631_3(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(20);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromLeftSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizLeftSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"My Mfrs\" to show user-favored manufacturers")
	public void dgnt3632_2(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(20);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify when Toggle switched to \"My Mfrs\" to show user-favored manufacturers")
	public void dgnt3631_4(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartView();
		projectResultPage.removeAllMFRInUserFavoredPopup();
		projectResultPage.clickOnCustomizeMFRlinkPopup();
		projectResultPage.selectMFRInUserFavoredPopup(20);
		projectResultPage.clickOnCustomizeMFRPopupSaveButton();
		// for specified MFR
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		List<String> sortOptionList = Arrays.asList("Count Highest - Lowest", "Count Lowest - Highest");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList1 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList1.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList1, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList2 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList2.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList2, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// For BOD
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList3 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList3.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList3, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList4 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList4.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList4, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		// for Both BOD and specified
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(0));
		List<Integer> valueList5 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList5.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList5, false)),
				"Sorting is invalid when " + sortOptionList.get(0) + " is selected.");
		projectResultPage.selectOptionFromRightSortDropdownManufacturers(sortOptionList.get(1));
		List<Integer> valueList6 = projectResultPage.getMFRVizRightSpecifiedFilterBarIntegerValueList();
		Assert.assertTrue(valueList6.equals(CommonUtils.sortIntegerListAndGetSortedList(valueList6, true)),
				"Sorting is invalid when " + sortOptionList.get(1) + " is selected.");
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3687_4(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer-bod/ BOD only )")
	public void dgnt3686_1(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}

	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3686_2(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer-bod/ BOD only )")
	public void dgnt3686_3(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria (Both Specified and BOD)")
	public void dgnt3686_4(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.unselectMFRVizSpecifiedCheckbox();

		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer/ Specified only )")
	public void dgnt3685_1(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer/ Specified only )")
	public void dgnt3685_2(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer/ Specified only )")
	public void dgnt3685_3(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with both keyword and MFR criteria ( p-manufacturer/ Specified only )")
	public void dgnt3685_4(String emailAddress, String password) {
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();

		projectResultPage.waitforLoadingRing();

		projectResultPage.selectMFRVizSpecifiedCheckbox();

		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);

		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with keyword but no MFR criteria")
	public void dgnt3684(String emailAddress, String password){
		String searchText = "door";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultPage.waitforLoadingRing();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");

		projectResultPage = projectSpecsPage.clickOnProjectResultLink();
		ProjectAddendaPage projectAddendaPage = projectResultPage.clickOnFirstHighlightedAddenda();
		Assert.assertTrue(projectAddendaPage.isAddendaTabHighlighted(),
				"Addenda tab is not highlighted on Project Addenda page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_1(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_2(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_3(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderFirstChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart1Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_4(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_5(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectMFRVizSpecifiedCheckbox();
		projectResultPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Verify Plans/Specs/Addenda document type links highlighting for a project search with MFR criteria but no keyword search")
	public void dgnt3688_6(String emailAddress, String password){
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnListViewIcon();
		homePage.clickOnSearchButton();
		projectResultPage.clickDashboard2ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectManufacturersUnderSecondChartViewEnhanced();
		projectResultPage.waitforLoadingRing();
		projectResultPage.unselectMFRVizSpecifiedCheckbox();
		projectResultPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultPage.clickOnMultipleManufacturerBarChart2Dashboard(1);
		projectResultPage.clickOnListViewIcon();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstHighlightedSpec();
		Assert.assertTrue(projectSpecsPage.isSpecsTabHighlighted(),
				"Specs tab is not highlighted on Project Specs page.");
	}
	
}

package com.ddaqe.dgn_specalerts;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_email_projects.DGNEmailProjectsDataProvider;
import com.ddaqe.dgn_print_projects.PrintProjectsDataProvider;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.PrintCompanyListPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.PrintProjectListPage;
import com.ddaqe.pages.ProjectCommonContainerPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DGNEnum;

@Listeners(TestListener.class)

public class DGNSpecAlerts extends BaseTest {
	static Logger log = Logger.getLogger(DGNSpecAlerts.class);

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
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the date range selection (TC10366)")
	public void tc107__132(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");
		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");
		specAlertsResultsPage.clickOnEditDateRangeIcon();

		specAlertsResultsPage.setCreatedBetweenRange(specAlertsResultsPage.selectOneYearBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		specAlertsResultsPage.clickOnUpdateInCreatedBetweenDateRange();

		Assert.assertTrue(!specAlertsResultsPage.getSelectedDate().equals(specAlertsResultsPage.getCurrentDateFromTo()),
				"Failed to reflect the selected date range");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntNonSpecAlerts", description = "[SpecAlerts] - Verify the spec alerts navigator in the project list view. (TC10141)")
	public void tc108(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(!projectResultPage.isMEnuExistsInLeftPane("SpecAlerts"),
				"The spec alerts programs filter should not be present for the non-spec alerts customer.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlusNonSpecAlerts", description = "[SpecAlerts] - Verify that spec alerts filter is not displayed for a non spec alerts customer (TC10143)")
	public void tc109(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(!projectResultPage.isMEnuExistsInLeftPane("SpecAlerts"),
				"The spec alerts programs filter should not be present for the non-spec alerts customer.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlusNonSpecAlerts", description = "[SpecAlerts] - Verify that spec alerts filter is not displayed for a non spec alerts customer (TC10143)")
	public void tc160(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(!companyResultPage.isMEnuExistsInLeftPane("SpecAlerts"),
				"The spec alerts programs filter should not be present for the non-spec alerts customer.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify that spec alerts filter is not displayed for a non spec alerts customer (TC10143)")
	public void tc161(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultPage.isMEnuExistsInLeftPane("SpecAlerts"),
				"The spec alerts programs filter should be present for the non-spec alerts customer.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Verify the dashboard contents (TC10145)/[SpecAlerts - Dashboard] - Verify the drop list for recent visits (TC10146)")
	public void tc110__tc111(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListTodayDisplayed(),
				"Failed to display Today under SpecAlerts Programs list");
		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListSinceLastVisitDisplayed(),
				"Failed to display 'Since My Last Visit' under SpecAlerts Programs list");

		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		goToBackPage();
		homePage.clickOnDashboardSpecAlertsFirstProgramToday();
		goToBackPage();
		homePage.clickOnDashboardSpecAlertsFirstProgramSinceLastVisit();
		goToBackPage();
		homePage.clickOnRecentLoginDrpDown();
		Assert.assertTrue(homePage.countSelectOptions() == 5, "Failed to display 5 most recent logins");
		homePage.clickOnRecentLogin();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the links (TC10147)")
	public void tc112(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		Assert.assertTrue(homePage.isCustomizeLinkInDashboardDisplayed(),
				"Failed to display Customize SpecAlerts Dashboard Link");
		Assert.assertTrue(homePage.isLeadEmailLinkInDashboardDisplayed(),
				"Failed to display Lead Email Link in Dashboard");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlusNonSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the dashboard for a non-spec alerts customer (TC10149)")
	public void tc113(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(!homePage.isSpecAlertsInDashboardHeadersDisplayed("SpecAlerts"),
				"SpecAlerts tab should not be displayed in the dashboard header");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the ''sticky'' feature of the dashboard selctor (TC10157)")
	public void tc114(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnProjectsLink();
		goToBackPage();
		Assert.assertTrue(homePage.isSpecAlertsTabSelected(), "Failed to get the SpecAlerts Tab as selected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the ''sticky'' feature of the dashboard selector across sessions (TC10159)")
	public void tc115(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();

		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);

		Assert.assertTrue(homePage.isSpecAlertsTabSelected(), "Failed to get the SpecAlerts Tab as selected");

		homePage.clickOnProjectsTab();
		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);

		Assert.assertTrue(homePage.isProjectsTabSelected(), "Failed to get the Projects Tab as selected");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify lead email settings pop up (TC10160)")
	public void tc116(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnLeadEmailLink();
		Assert.assertTrue(homePage.isLeadEmailDialogDisplayed(), "Failed to display Lead Email Dialog");
		Assert.assertTrue(homePage.isLeadEmailDialogCheckboxDisplayed(),
				"Failed to display checkbox in the Lead Email Dialog");
		Assert.assertTrue(homePage.isLeadEmailDialogTextAreaDisplayed(),
				"Failed to display textarea in the Lead Email Dialog");
		Assert.assertTrue(homePage.isLeadEmailDialogSaveDisplayed(),
				"Failed to display Save button in the Lead Email Dialog");
		Assert.assertTrue(homePage.isLeadEmailDialogCancelDisplayed(),
				"Failed to display Cancel button in the Lead Email Dialog");

		homePage.clickOnCancelInLeadEmailDialog();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify email addresses text box in the lead email settings pop up (TC10162)")
	public void tc117(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnLeadEmailLink();

		char[] charArray500 = new char[500];
		Arrays.fill(charArray500, 'a');
		String text500Char = new String(charArray500);

		homePage.enterAddressinTextAreaInLeadEmailDialog(text500Char);

		char[] charArray501 = new char[501];
		Arrays.fill(charArray501, 'a');
		String text501Char = new String(charArray501);
		homePage.enterAddressinTextAreaInLeadEmailDialog(text501Char);
		Assert.assertTrue(homePage.getEnteredTextLengthInLeadEmailDialog() == 500,
				"Failed to prevent from entering more than 500 characters");

		homePage.clickOnCancelInLeadEmailDialog();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - List View] - Verify the spec alerts programs listed (TC10168)")
	public void tc118(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.isSpecAlertsProgramListedOnLeft(),
				"Failed to list specalerts program on left");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify customized dashboard is saved across sessions (TC10174)")
	public void tc119(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnCustomizeDashboard();
		List<String> selectedSpecProgramList = homePage.getSelectedSpecProgramList();
		homePage.clickOnCancelInCustomiseSpecProgramsPopUp();
		CommonUtils.IterateList(selectedSpecProgramList);
		CommonUtils.IterateList(homePage.getDashboardSpecAlertsProgramList());
		Assert.assertEquals(homePage.getDashboardSpecAlertsProgramList(), selectedSpecProgramList);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - List View] - Verify the entry points (TC10184)")
	public void tc120(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SpecAlertsResultsPage specAlertsResultsPage = myAccount.clickOnMySpecAlertsNavLink();

		specAlertsResultsPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnDashboardSpecAlertsFirstProgramToday();
		specAlertsResultsPage.clickOnHomeTab();
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		Assert.assertTrue(projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(),
				"Failed to apply filter from Spec Alerts program");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - List View] - Verify page title and breadcrumb (TC10185)")
	public void tc121(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");
		specAlertsResultsPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(4);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts List View] - Verify actions menu (TC10343)")
	public void tc122(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		specAlertsResultsPage.mouseOverActionButton();
		Assert.assertTrue(specAlertsResultsPage.verifyActionOptions(),
				"Failed to match all the options in actions drop down");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts List View] - Verify View Projects (TC10359)")
	public void tc123(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		specAlertsResultsPage.clickOnActionsDropdown();
		ProjectPage projectPage = specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		specAlertsResultsPage.clickOnFirstProjectCheckbox();
		specAlertsResultsPage.clickOnSecondProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		specAlertsResultsPage.clickOnSelectAllProjects();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "(Copy of) [SpecAlerts List View] - Verify Track Projects (TC10362)")
	public void tc126(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		specAlertsResultsPage.clickOnFirstProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = specAlertsResultsPage.clickOnTrackProjectsFromSpecAlertsActions();
		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");

		goToBackPage();
		specAlertsResultsPage.clickOnSelectAllProjects();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnTrackProjectsFromSpecAlertsActions();
		selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");

		specAlertsResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts List View] - Verify error messages (TC10363)")
	public void tc127(String emailAddress, String password) {

		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");
		
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnDownloadFirmsFromSpecAlertsActions();
		Assert.assertTrue(specAlertsResultsPage.getErrorMessage().trim().equalsIgnoreCase("Please make a selection"),
				"Error message is failed to be displayed");

		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnEmailProjectsFromSpecAlertsActions();
		Assert.assertTrue(specAlertsResultsPage.getErrorMessage().trim().equalsIgnoreCase("Please make a selection"),
				"Error message is failed to be displayed");

		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnTrackProjectsFromSpecAlertsActions();
		Assert.assertTrue(specAlertsResultsPage.getErrorMessage().trim().equalsIgnoreCase("Please make a selection"),
				"Error message is failed to be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Verify the date picker in the list view (TC10364)")
	public void tc128(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		specAlertsResultsPage.clickOnEditDateRangeIcon();
		specAlertsResultsPage.setCreatedBetweenRange(specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		specAlertsResultsPage.clickOnUpdateInCreatedBetweenDateRange();

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.mouseOverSelectedDateRangeButton();
		Assert.assertEquals(specAlertsResultsPage.getTooltipSelectedDate(),
				specAlertsResultsPage.getCurrentDateFromTo(), "Failed to display the tooltip with selected date range");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Verify the date picker in the list view (TC10364)")
	public void tc129(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		specAlertsResultsPage.clickOnEditDateRangeIcon();
		specAlertsResultsPage.setCreatedBetweenRange(specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		specAlertsResultsPage.clickOnUpdateInCreatedBetweenDateRange();

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.mouseOverSelectedDateRangeButton();
		Assert.assertEquals(specAlertsResultsPage.getTooltipSelectedDate(),
				specAlertsResultsPage.getCurrentDateFromTo(), "Failed to display the tooltip with selected date range");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the date range selection (TC10366)")
	public void tc130(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		Boolean isSelected = specAlertsResultsPage.setCreatedBetweenRange(
				specAlertsResultsPage.selectLastQuarterBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		Assert.assertTrue(!isSelected,
				"Failed to restrict the user so that it should not be able to choose a start date more than 1 year back from the current date");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlatinumSpecAlerts", description = "[SpecAlerts] On the SpecAlerts list view, display projects for a selected SpecAlerts program- To Verify that Spec Alert List view (TC10665)")
	public void tc131(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.isPageNoDisplayed(), "Failed to display the page number");
		Assert.assertTrue(specAlertsResultsPage.isResultPerPageDisplayed(), "Failed to display the 'Results Per Page'");
		Assert.assertTrue(specAlertsResultsPage.isPaginationDisplayed(), "Failed to display the pagination");
		Assert.assertTrue(specAlertsResultsPage.isSelectAllDisplayed(), "Failed to display the Select All feature");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Display SpecAlert tags in the summary section of project list views - To Verify the Spec Alert tabs in the Spec alert List view (TC10693)")
	public void tc133(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertTrue(specAlertsResultsPage.getSpecAlertSize() < 5,
				"The specalerts in the specalerts results page should not be more than 5");

		String toolTipOfSpecAlertsClicked = specAlertsResultsPage.mouseOverSpecALertsListAndGetTooltip();
		ProjectCommonContainerPage ProjectCommonContainerPage = specAlertsResultsPage
				.clickFirstSpecAlertsInFirstProject();
		specAlertsResultsPage.clickOnBtnCancelMerx();
		String highlightedTab = ProjectCommonContainerPage.getHighlightedTab();
		Assert.assertTrue(toolTipOfSpecAlertsClicked.trim().contains(highlightedTab.trim()),
				"Failed to find the match");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the spec alerts tags on the dodge report page (TC10711)")
	public void tc139(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();

		ProjectPage projectPage = projectResultPage.clickOnSingleProjectTitleWithSpecAlerts();
		String toolTipOfSpecAlertsClicked = projectPage.mouseOverSpecALertsListAndGetTooltip();

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnAnyFromSpecAlertsList();

		String highlightedTab = projectSpecsPage.getHighlightedTab();
		try {
			Assert.assertTrue(toolTipOfSpecAlertsClicked.trim().contains(highlightedTab.trim()),
					"Failed to find the match");
		} catch (Exception ex) {
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the programs on the spec alerts dashboard (TC10706)")
	public void tc135(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();

		List<String> sortedSpecPrograms = CommonUtils.sortWebElements(homePage.getDashboardSpecAlertsProgramList(),
				true);
		Assert.assertTrue(CommonUtils.compareTwoListForStringContains(homePage.getDashboardSpecAlertsProgramList(),
				sortedSpecPrograms), "Failed to get the spec alerts program in sorted order");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Add SpecAlerts tags to print project list output (TC10715)")
	public void tc140(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		List<String> getSelectedProjectWithSpecAlerts = projectResultPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		projectResultPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc141(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		List<String> getSelectedProjectWithSpecAlerts = projectResultPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		projectResultPage.clickOnActionsDropdown();
		PrintProjectDetailsPage printProjectDetailPage = projectResultPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(
				printProjectDetailPage
						.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project details page");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify print output from the project report view (TC10717)")
	public void tc142(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		ProjectPage projectPage = projectResultPage.clickOnSingleProjectTitleWithSpecAlerts();

		List<String> getSelectedProjectWithSpecAlerts = projectPage.getSelectedProjectTitle();
		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(
				printProjectDetailPage
						.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project details page");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify the spec alerts on the print output when the projects are tracked (TC10719)")
	public void tc143(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		ProjectPage projectPage = projectResultPage.clickOnSingleProjectTitleWithSpecAlerts();

		List<String> getSelectedProjectWithSpecAlerts = projectPage.getSelectedProjectTitle();
		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(
				printProjectDetailPage
						.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project details page");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Update spec alert folder font - make it bold (TC11120)")
	public void tc145(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		Assert.assertTrue(projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(),
				"Failed to apply filter from Spec Alerts program");
		Assert.assertTrue(projectResultPage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");

		projectResultPage.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		projectResultPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultPage.clickOnPrintProjectListUnderActions();
		Assert.assertTrue(printProjectListPage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");
		goToBackPage();

		ProjectPage projectPage = projectResultPage.clickOnSingleProjectTitleWithSpecAlerts();
		Assert.assertTrue(projectPage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");

		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(printProjectDetailPage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");

		goToBackPage();
		projectResultPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");

		projectResultPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.verifyIsSpecAlertsBold(), "Failed to display SpecAlerts in Font Bold");
		homePage.clickOnCancelInCustomiseSpecProgramsPopUp();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Update tracking list folder  font - make it bold (TC11121)")
	public void tc146(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultPage.clickOnTrackProjects();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		Assert.assertTrue(projectResultPage.verifyIsTrackingListBold(), "Failed to display Tracking List in Font Bold");

		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultPage.clickOnPrintProjectListUnderActions();
		Assert.assertTrue(printProjectListPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");
		goToBackPage();

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyIsTrackingListBold(), "Failed to display Tracking List in Font Bold");

		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(printProjectDetailPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");

		goToBackPage();
		projectResultPage.clickOnHomeTab();
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		specAlertsResultsPage.clickOnFistProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnTrackProjects();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		Assert.assertTrue(specAlertsResultsPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");

		projectResultPage.clickOnHomeTab();
		homePage.clickOnProjectsTab();
		homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(homePage.verifyIsTrackingListBold(), "Failed to display Tracking List in Font Bold");
		homePage.clickOnCancelFromCustomizeDashboard();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		// Precondition: To track company first
		companyResultsPage.clickOnTrackLink();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		Assert.assertTrue(companyResultsPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");

		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyIsTrackingListBold(), "Failed to display Tracking List in Font Bold");

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();
		Assert.assertTrue(printCompanyDetailPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");

		goToBackPage();
		homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultsPage.clickOnPrintCompanyListUnderActions();
		Assert.assertTrue(printCompanyListPage.verifyIsTrackingListBold(),
				"Failed to display Tracking List in Font Bold");
		goToBackPage();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - List View] - Verify the entry points (TC10184)")
	public void tc153(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnSpecAlertFilter();
		String selectedProgram = projectResultPage.ClickSpecAlertFilterCount();

		Assert.assertTrue(
				projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(
						CommonUtils.formatIntoCharactersOnlyWithSpace(selectedProgram.trim())),
				"Failed to apply filter from Spec Alerts program");

		projectResultPage.selectSortingOption("Location - Ascending");
		Assert.assertEquals(projectResultPage.getSelectedSortOption(), "Location - Ascending",
				"Failed to get the selected option correctly");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Display SpecAlert tags in the summary section of project list views - To Verify the Spec Alert tabs in the Spec alert List view (TC10693)")
	public void tc154(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		Assert.assertTrue(projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(),
				"Failed to apply filter from Spec Alerts program");

		String toolTipOfSpecAlertsClicked = projectResultPage.mouseOverSpecALertsListAndGetTooltip();

		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstFromSpecAlertsListInSummary();
		String highlightedTab = projectSpecsPage.getHighlightedTab();
		Assert.assertTrue(toolTipOfSpecAlertsClicked.trim().contains(highlightedTab.trim()),
				"Failed to find the match");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Display SpecAlert tags in the summary section of project list views - To Verify the Spec Alert tabs in the Spec alert List view (TC10693)")
	public void tc158(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		Assert.assertTrue(projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(),
				"Failed to apply filter from Spec Alerts program");
		String toolTipOfSpecAlertsClicked = projectResultPage.mouseOverSpecALertsListAndGetTooltip();
		ProjectSpecsPage projectSpecsPage = projectResultPage.clickOnFirstFromSpecAlertsListInSummary();
		String highlightedTab = projectSpecsPage.getHighlightedTab();
		Assert.assertTrue(toolTipOfSpecAlertsClicked.trim().contains(highlightedTab.trim()),
				"Failed to find the match");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Access Spec alert details from Spec Alert folder, user should be able to view the particular defect (TC21105)")
	public void tc166(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(0);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		ProjectSpecsPage projectSpecsPage = specAlertsResultsPage.clickOnSpecsLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(1);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print spec alert project list navigated from Spec Alert dropdown in home page (TC21219)")
	public void tc167(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();

		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print spec alert project list navigated from Spec Alert dashboard in home page (TC21220)")
	public void tc168(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Navigating from Spec alerts folders to Project Details Page and view Project Details (TC21221)")
	public void tc169(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		Integer currentPageNumber = projectPage.getCurrentPageNo();
		projectPage.clickOnNextLink();
		Integer nextPageNumber = projectPage.getCurrentPageNo();

		Assert.assertTrue(nextPageNumber == (currentPageNumber + 1), "Failed to display the next page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print single project from spec alert project list (TC21222)")
	public void tc170(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();

		specAlertsResultsPage.clickOnMultipleProjectsWithSpecAlertsChkBox(1);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");

		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Verify that while Changing/ viewing the documents in the Project Details page doesnot break (TC21223)")
	public void tc171(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		String projectDRNumber = projectPage.getProjectCrumbDRNumber().trim();

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnAnyFromSpecAlertsList();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		Assert.assertEquals(projectSpecsPage.getDRNumber().trim(), projectDRNumber,
				"Failed to match the DR number of the same selected project from different tabs");
		homePage.clickOnSignOutButton();
	}

	// Due to large data set traversion (i.e. ten records and get each project
	// title in the list so as to check the same in the next page, execution
	// time is huge, thus would run this test individually and not with other
	// tests
	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print 10 projects from spec alert project list (TC21224)", enabled = false)
	public void tc172(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(10);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Verify that while Changing/ viewing the Spec Alert Projects using Browser Back button, behavior doesn't break (TC21225)")
	public void tc173(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		String projectDRNumber = projectPage.getProjectCrumbDRNumber().trim();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnAnyFromSpecAlertsList();
		Assert.assertEquals(projectSpecsPage.getDRNumber().trim(), projectDRNumber,
				"Failed to match the DR number of the same selected project from different tabs");
		goToBackPage();
		goToBackPage();
		specAlertsResultsPage.clickOnThirdProjectTitle();
		projectDRNumber = projectPage.getProjectCrumbDRNumber().trim();
		projectPage.clickOnAnyFromSpecAlertsList();
		Assert.assertEquals(projectSpecsPage.getDRNumber().trim(), projectDRNumber,
				"Failed to match the DR number of the same selected project from different tabs");
		goToBackPage();
		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	// Due to large data set traversion (i.e. twenty five records and get each
	// project title in the list so as to check the same in the next page,
	// execution time is huge, thus would run this test individually and not
	// with other tests
	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print 25 projects from spec alert project list (TC21226)", enabled = false)
	public void tc174(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(25);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	// Due to large data set traversion (i.e. fifty records and get each project
	// title in the list so as to check the same in the next page, execution
	// time is huge, thus would run this test individually and not with other
	// tests
	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print 50 projects from spec alert project list (TC21227)", enabled = false)
	public void tc175(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnResultPerPage();
		specAlertsResultsPage.selectResultPerPage(DGNEnum.resultPerPageOptionList.OPTION_FIFTY);

		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(50);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	// Due to large data set traversion (i.e. hundred records and get each
	// project title in the list so as to check the same in the next page,
	// execution time is huge, thus would run this test individually and not
	// with other tests
	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify user is able to print 50 projects from spec alert project list (TC21227)", enabled = false)
	public void tc176(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnResultPerPage();
		specAlertsResultsPage.selectResultPerPage(DGNEnum.resultPerPageOptionList.OPTION_HUNDRED);

		List<String> getSelectedProjectWithSpecAlerts = specAlertsResultsPage
				.clickOnMultipleProjectsWithSpecAlertsChkBox(100);
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = specAlertsResultsPage.clickOnPrintProjectListUnderActions();
		Assert.assertEquals(printProjectListPage.getTitle(), "Dodge Global Network - Print",
				"Failed to redirect to the print project list page");
		Assert.assertTrue(
				printProjectListPage.IsProjectTitlesSameAsSelectedFromProjectResults(getSelectedProjectWithSpecAlerts),
				"Failed to match the selected projects from project results having specalerts with that in the print project list page");
		Assert.assertTrue(printProjectListPage.isPrintLinkClickable(), "Failed to get the print feature");

		goToBackPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Verify when coming form Spec Alert links form Project List page, Project details of the same project Is displayed (TC21230)")
	public void tc178(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickingOnSpecAlertsFilter();
		Assert.assertTrue(projectResultPage.isleftMenuAppliedFilterListContainsSpecAlertProgram(),
				"Failed to apply filter from Spec Alerts program");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		goToBackPage();
		projectResultPage = new ProjectResultsPage(getDriver());
		Assert.assertTrue(projectResultPage.isResultContentDisplayed(),
				"System failed to display the project results in list view");
		projectResultPage.clickingOnSpecAlertsFilter();
		projectResultPage.clickOnSingleProjectTitleWithSpecAlerts();
		String projectDRNumber = projectPage.getProjectCrumbDRNumber().trim();
		
		
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnAnyFromSpecAlertsList();

		Assert.assertEquals(projectSpecsPage.getDRNumber().trim(), projectDRNumber,
				"Failed to match the DR number of the same selected project from different tabs");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Navigating through Breadcrum in Spec Alert Project Details page should not break the report details (TC21233)")
	public void tc180(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitleWithSpecs();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");
		Integer currentPageNumber = projectPage.getCurrentPageNo();
		projectPage.clickOnNextLink();
		Assert.assertTrue(projectPage.getCurrentPageNo() == (currentPageNumber + 1), "Failed to display the next page");

		projectPage.clickOnPreviousLink();

		Assert.assertTrue(projectPage.getCurrentPageNo() == currentPageNumber, "Failed to display the previous page");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		String projectSpecsDRNumber = projectSpecsPage.getDRNumber().trim();
		projectPage.clickOnBreadCrumbProjectLink();

		Assert.assertEquals(projectSpecsDRNumber, projectPage.getProjectCrumbDRNumber().trim(),
				"Failed to match the DR number of the same selected project from different tabs");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Verify that spec alerts filter is not displayed for a non spec alerts customer (TC10143)")
	public void tc183(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitleWithSpecs();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");
		Integer currentPageNumber = projectPage.getCurrentPageNo();
		projectPage.clickOnNextLink();
		Assert.assertTrue(projectPage.getCurrentPageNo() == (currentPageNumber + 1), "Failed to display the next page");

		projectPage.clickOnPreviousLink();

		Assert.assertTrue(projectPage.getCurrentPageNo() == currentPageNumber, "Failed to display the previous page");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		String projectSpecsDRNumber = projectSpecsPage.getDRNumber().trim();
		projectPage.clickOnBreadCrumbProjectLink();
		Assert.assertEquals(projectSpecsDRNumber, projectPage.getProjectCrumbDRNumber().trim(),
				"Failed to match the DR number of the same selected project from different tabs");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Select a count from the today column of the dashboard and verify the date picker on the spec alerts list view. (TC10708)")
	public void tc136(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListTodayDisplayed(),
				"Failed to display Today under SpecAlerts Programs list");

		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnDashboardSpecAlertsFirstProgramToday();
		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getTodayDateFromTo(),
				"Failed to reflect the selected date range");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] - Select a count from the last login column of the dashboard and verify the date picker on the spec alerts list view. (TC10709)")
	public void tc137(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();

		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListSinceLastVisitDisplayed(),
				"Failed to display 'Since My Last Visit' under SpecAlerts Programs list");

		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnDashboardSpecAlertsFirstProgramSinceLastVisit();
		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getTodayDateFromTo(),
				"Failed to reflect the selected date range");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts - Dashboard] - Verify the drop list which identifies the user's recent visits. (TC10710)")
	public void tc138(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();

		homePage.clickOnRecentLoginDrpDown();
		Assert.assertTrue(homePage.countSelectOptions() == 5, "Failed to display 5 most recent logins");
		homePage.clickOnRecentLogin();

		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnDashboardSpecAlertsFirstProgramToday();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[Miscellaneous] General UI clean up - To Verify the General UI clean up (TC11352)")
	public void tc148(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnCustomizeDashboard();
		List<String> selectedSpecProgramList = homePage.getSelectedSpecProgramList();
		homePage.clickOnCancelInCustomiseSpecProgramsPopUp();
		CommonUtils.IterateList(selectedSpecProgramList);
		CommonUtils.IterateList(homePage.getDashboardSpecAlertsProgramList());
		Assert.assertEquals(homePage.getDashboardSpecAlertsProgramList(), selectedSpecProgramList);

		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify Updated branding in the footer of Spec Alert page (TC17741)")
	public void tc155(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.isFooterLogoDodge(),
				"Failed to display the footer logo for Dodge Data & Analytics");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] General UI clean up to verify the changed error message for Spec Alert email setting (TC19602)")
	public void tc159(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnLeadEmailLink();
		Assert.assertTrue(homePage.isLeadEmailDialogDisplayed(), "Failed to display Lead Email Dialog");
		Assert.assertTrue(homePage.isLeadEmailDialogCheckboxDisplayed(),
				"Failed to display checkbox in the Lead Email Dialog");

		homePage.uncheckLeadEmailDialogCheckbox();
		homePage.enterAddressinTextAreaInLeadEmailDialog(emailAddress);
		homePage.clickOnSaveInLeadEmailDialog();

		Assert.assertTrue(homePage.isLeadEmailErrorDisplayed(), "Failed to display error in lead email pop up");
		homePage.clickOnCancelInLeadEmailDialog();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Documents link from Spec Alerts folder brings user to first project (TC21103)")
	public void tc164(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(0);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		ProjectSpecsPage projectSpecsPage = specAlertsResultsPage.clickOnSpecsLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(1);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] Documents link from Spec Alerts folder brings user to first project (TC21103)")
	public void tc165(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		// TODO : Email Verification pending
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(0);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		ProjectSpecsPage projectSpecsPage = specAlertsResultsPage.clickOnSpecsLink();
		specAlertsResultsPage.clickOnBtnCancelMerx();

		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		specAlertsResultsPage.clickOnMentionedProjectTitleWithPlansAndSpecs(1);
		specAlertsResultsPage.clickOnPlansLink();
		specAlertsResultsPage.clickOnSpecsLink();
		projectSpecsPage.clickOnSpecAlertsBreadCrumb();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] View SpecAlert Projects in the same month while Navigating from Spec Alert Folder (TC21234)")
	public void tc181(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		specAlertsResultsPage.setCreatedBetweenRange(specAlertsResultsPage.selectOneYearBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		specAlertsResultsPage.clickOnUpdateInCreatedBetweenDateRange();

		Assert.assertTrue(!specAlertsResultsPage.getSelectedDate().equals(specAlertsResultsPage.getCurrentDateFromTo()),
				"Failed to reflect the selected date range");

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[DGN] View SpecAlert Projects in different month while Navigating from Spec Alert Folder (TC21235)")
	public void tc182(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);

		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		Assert.assertEquals(specAlertsResultsPage.getSelectedDate(), specAlertsResultsPage.getCurrentDateFromTo(),
				"Failed to reflect the selected date range");

		specAlertsResultsPage.clickOnEditDateRangeIcon();

		specAlertsResultsPage.setCreatedBetweenRange(specAlertsResultsPage.selectOneYearBackCreatedBetween(),
				CommonUtils.getUSADateFormat(Calendar.getInstance().getTime()));
		specAlertsResultsPage.clickOnUpdateInCreatedBetweenDateRange();

		Assert.assertTrue(!specAlertsResultsPage.getSelectedDate().equals(specAlertsResultsPage.getCurrentDateFromTo()),
				"Failed to reflect the selected date range");

		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify on selection of the Spec alerts on the See More Popup the search is refreshed (TC22324)")
	public void tc186(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();

		projectResultsPage.clickOnFewOptions(3);

		projectResultsPage.clickOnUpdateButtonSeeMoreSpecAlertPopup();
		projectResultsPage.SwitchToParent();
		Assert.assertTrue(projectResultsPage.getSpecAlertFewAppliedFilterCount() == 2,
				"Failed to display the specalerts filter crumb");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify ‘My Spec Alerts’ link under My Account for the user who has subscribed the Spec Alerts (TC22723)")
	public void tc187(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccount.isMySpecAlertsDisplayed(),
				"'My SpecAlerts should be present under ''My Accounts'' menu for non spec alerts user'");

		Assert.assertTrue(homePage.isSpecAlertsInDashboardHeadersDisplayed("SpecAlerts"),
				"SpecAlerts tab should not be displayed in the dashboard header");

		ProjectResultsPage projectResults = homePage.clickOnProjectsLink();

		Assert.assertTrue(projectResults.isMySearchesSpecAlertsDisplayed(),
				"Failed to display my specalerts in the my searches dropdown");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlusNonSpecAlerts", description = "Verify ‘My Spec Alerts’ link under My Account for the user who has not subscribed the Spec Alerts (TC22724)")
	public void tc188(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(!myAccount.isMySpecAlertsDisplayed(),
				"'My SpecAlerts should not be present under ''My Accounts'' menu for non spec alerts user'");

		Assert.assertTrue(!homePage.isSpecAlertsInDashboardHeadersDisplayed("SpecAlerts"),
				"SpecAlerts tab should not be displayed in the dashboard header");

		ProjectResultsPage projectResults = homePage.clickOnProjectsLink();
		projectResults.clickOnDropDownList();

		Assert.assertTrue(!projectResults.isMySearchesSpecAlertsDisplayed(),
				"My specalerts should not be displayed in the my searches dropdown");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCPlusNonSpecAlerts", description = "Spec Alert filter is visible only for users with Spec Alerts License (TC23568)")
	public void tc189(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		Assert.assertTrue(!companyResults.isMEnuExistsInLeftPane("SpecAlerts"),
				"The spec alerts programs filter should not be present for the non-spec alerts customer.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify spec Alert filter is 'Collapsed' by default in Company result page (TC23569)")
	public void tc190(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResults.isLeftSpecAlertCollapsedDefault(),
				"Failed to get specalerts as collapsed by default");
		homePage.clickOnSignOutButton();
	}

	// Test is failing as actually the specalerts program are slightly different
	// in both project result page and company result page in the qa3 server.
	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlertsLatestBuild", description = "Verify filter crumb functionality in Spec Alert filter in Company results page (TC23572)", enabled = true)
	public void tc191(String emailAddress, String password, String latestBuild) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
		projectResultsPage.SwitchToFrame();

		List<String> ProjectSpecAlertFilterList = projectResultsPage.getSpecAlertsSeeMorePopUpProgramsList();

		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();

		goToBackPage();

		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		companyResults.clickOnSeeMoreSpecAlertsIcon();

		companyResults.SwitchToFrame();
		List<String> CompanySpecAlertFilterList = projectResultsPage.getSpecAlertsSeeMorePopUpProgramsList();

		companyResults.SwitchToParent();
		projectResultsPage.PopUpClose();

		CommonUtils.IterateList(ProjectSpecAlertFilterList);

		CommonUtils.IterateList(CompanySpecAlertFilterList);

		Assert.assertTrue(CommonUtils.CompareTwoList(ProjectSpecAlertFilterList, CompanySpecAlertFilterList),
				"Failed to get the items from the specalerts filter in company result page ordered similar to that of project result page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify 'See More' pop up is implemented properly in Spec Alert filter in Company results page (TC23571)")
	public void tc192(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		companyResults.clickOnSeeMoreSpecAlertsIcon();
		Assert.assertTrue(companyResults.isSeeMoreSpecALertsSorted(), "Failed to get the specalerts list as sorted");

		companyResults.SwitchToFrame();
		companyResults.clickOnSelectAllInSeeMoreSpecAlerts();
		Assert.assertTrue(companyResults.isAllCheckboxSelectedInSeeMoreSpecAlerts(),
				"Failed to get all specalerts selected in the see more popup");

		Assert.assertTrue(companyResults.isExcludeProjectsInSeeMoreSpecAlertsDisplayed(),
				"Failed to display the excluded projects check box in the see more specalerts list");
		companyResults.SwitchToParent();
		companyResults.clickOnCloseIconForSeeMoreSpecAlerts();
		Assert.assertTrue(!companyResults.isSeeMoreSpecAlertsPopUpDisplayed(),
				"Failed to remove the ''See More'' specalerts popup");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify filter crumb functionality in Spec Alert filter in Company results page (TC23572)")
	public void tc193(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		companyResults.clickOnSeeMoreSpecAlertsIcon();
		Assert.assertTrue(companyResults.isSeeMoreSpecALertsSorted(), "Failed to get the specalerts list as sorted");

		companyResults.SwitchToFrame();
		companyResults.clickOnSelectAllInSeeMoreSpecAlerts();
		Assert.assertTrue(companyResults.isAllCheckboxSelectedInSeeMoreSpecAlerts(),
				"Failed to get all specalerts selected in the see more popup");

		Assert.assertTrue(companyResults.isExcludeProjectsInSeeMoreSpecAlertsDisplayed(),
				"Failed to display the excluded projects check box in the see more specalerts list");
		companyResults.SwitchToParent();
		companyResults.clickOnCloseIconForSeeMoreSpecAlerts();
		Assert.assertTrue(!companyResults.isSeeMoreSpecAlertsPopUpDisplayed(),
				"Failed to remove the ''See More'' specalerts popup");

		companyResults.clickOnSeeMoreSpecAlertsIcon();
		companyResults.SwitchToFrame();
		companyResults.clickOnSelectAllInSeeMoreSpecAlerts();

		companyResults.clickOnUpdateButtonSeeMoreSpecAlertPopup();
		companyResults.SwitchToParent();
		Assert.assertTrue(companyResults.isSpecAlertsFilterDisplayed(),
				"Failed to display the specalerts filter crumb");
		companyResults.clickOnSpecAlertsFilterRemove();
		Assert.assertTrue(!companyResults.isSpecAlertsFilterDisplayed(),
				"Specalerts filter should be removed from the filter crumb");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify selecting less than 4 Spec Alert filter options in Company results page (TC23573)")
	public void tc194(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResults = homePage.clickOnCompaniesLink();
		companyResults.clickOnSeeMoreSpecAlertsIcon();
		Assert.assertTrue(companyResults.isSeeMoreSpecALertsSorted(), "Failed to get the specalerts list as sorted");

		companyResults.SwitchToFrame();
		companyResults.clickOnFewOptions(3);

		companyResults.clickOnUpdateButtonSeeMoreSpecAlertPopup();
		companyResults.SwitchToParent();
		Assert.assertTrue(companyResults.getSpecAlertFewAppliedFilterCount() == 2,
				"Failed to display the specalerts filter crumb");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify the Special filter  on Tracking List View page  after login in  DGN  app (TC25251)")
	public void tc195(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isResultContentDisplayed(),
				"System failed to display the project results in list view");

		projectResultsPage.clickOnDropDownList();
		TrackingList trackingListPage = projectResultsPage.clickOnMySearchesTrackingList();

		trackingListPage.clickOnProjectLink();
		trackingListPage.clickOnTrackingList(1);
		Assert.assertTrue(projectResultsPage.isResultContentDisplayed(),
				"System failed to display the project results in list view");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "Verify the Special filter  on Tracking List View page  after login in  DGN  app (TC25251)")
	public void tc199(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSpecialFilter();

		Assert.assertTrue(projectResultsPage.verifySpecialFilterOptions(),
				"Failed to match the special filters options");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlertsDRMohawk", description = "[SpecAlerts] Display SpecAlert tags in the summary section of project list views - To Verify the Spec Alert tabs in the Spec alert List view (TC10693)")
	public void tc185(String emailAddress, String password, String DRNo) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isResultContentDisplayed(),
				"System failed to display the project results in list view");

		projectResultsPage.clickOnProjectGroupsSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.clickOnProjectGrpEducationPrimary();
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		projectResultsPage.clickOnSpecAlertFilter();
		projectResultsPage.clickOnSpecAlertFilterCarpetMohawk();
		projectResultsPage.waitforLoadingRing();

		homePage.enterSearchText(DRNo);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		Assert.assertTrue(projectPage.isSpecAlertsListDisplayed(),
				"Failed to display the specalerts in the project page");

		String toolTipOfSpecAlertsClicked = projectPage.mouseOverSpecALertsListAndGetTooltip();

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnAnyFromSpecAlertsList();

		projectResultsPage.clickOnBtnCancelMerx();
		String highlightedTab = projectSpecsPage.getHighlightedTab();
		Assert.assertTrue(toolTipOfSpecAlertsClicked.trim().contains(highlightedTab.trim()),
				"Failed to find the match");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc125(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();

		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc184(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();

		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}
	
}

package com.ddaqe.dgn_project_dashboard;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_email_projects.DGNEmailProjectsDataProvider;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DGNEnum;

@Listeners(TestListener.class)

public class DGNProjectDashboard extends BaseTest {
	static Logger log = Logger.getLogger(DGNProjectDashboard.class);

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
			"Medium" }, dataProviderClass = DGNProjectDashboardDataProvider.class, dataProvider = "TCAdminUSAInt", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc18(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isUSADodgeProjectSummaryDisplayed(), "Failed to display the USA section");

		Assert.assertTrue(homePage.isUSATodayHeaderDisplayed(),
				"Failed to display the 'Today' header under the USA section");
		Assert.assertTrue(homePage.isUSALastVisitHeaderDisplayed(),
				"Failed to display the 'Last Visit' header under the USA section");

		Assert.assertTrue(homePage.isUSAAllProjectTodayCountDisplayed(),
				"Failed to display the count under Today column for USA section for the All Project in my license");
		ProjectResultsPage projectResultPage = homePage.clickUSAAllProjectTodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAAllProjectLastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for USA section for the All Project in my license");
		homePage.clickUSAAllProjectLastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAVersion1TodayCountDisplayed(),
				"Failed to display the count under Today column for USA section for the Version 1 report");
		homePage.clickUSAVersion1TodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAVersion1LastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for USA section for the Version 1 report");
		homePage.clickUSAVersion1LastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNProjectDashboardDataProvider.class, dataProvider = "TCAdminUSAInt", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc19(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isIntlDodgeProjectSummaryDisplayed(),
				"Failed to display the Intl Dodge Project Summary section");

		Assert.assertTrue(homePage.isIntTodayHeaderDisplayed(),
				"Failed to display the 'Today' header under the International section");
		Assert.assertTrue(homePage.isIntLastVisitHeaderDisplayed(),
				"Failed to display the 'Last Visit' header under the International section");

		Assert.assertTrue(homePage.isIntAllProjectTodayCountDisplayed(),
				"Failed to display the count under Today column for International section for the All Project in my license");
		ProjectResultsPage projectResultPage = homePage.clickIntAllProjectTodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntAllProjectLastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for International section for the All Project in my license");
		homePage.clickIntAllProjectLastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntVersion1TodayCountDisplayed(),
				"Failed to display the count under Today column for International section for the Version 1 report");
		homePage.clickIntVersion1TodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntVersion1LastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for International section for the Version 1 report");
		homePage.clickIntVersion1LastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNProjectDashboardDataProvider.class, dataProvider = "TCAdminUSAInt", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc20(String EmailAddress, String Password) {

		HomePage homePage = loginAs(EmailAddress, Password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isIntlDodgeProjectSummaryDisplayed(),
				"Failed to display the Intl Dodge Project Summary section");
		Assert.assertTrue(homePage.isUSADodgeProjectSummaryDisplayed(),
				"Failed to display the USA Dodge Project Summary section");

		Assert.assertTrue(homePage.isIntTodayHeaderDisplayed(),
				"Failed to display the 'Today' header under the International section");
		Assert.assertTrue(homePage.isIntLastVisitHeaderDisplayed(),
				"Failed to display the 'Last Visit' header under the International section");

		Assert.assertTrue(homePage.isUSATodayHeaderDisplayed(),
				"Failed to display the 'Today' header under the USA section");
		Assert.assertTrue(homePage.isUSALastVisitHeaderDisplayed(),
				"Failed to display the 'Last Visit' header under the USA section");

		Assert.assertTrue(homePage.isUSAAllProjectDisplayed(),
				"Failed to display the All Project in my license for USA section");
		Assert.assertTrue(homePage.isUSAVersion1Displayed(), "Failed to display the Version 1 report for USA section");

		Assert.assertTrue(homePage.isIntAllProjectDisplayed(),
				"Failed to display the All Project in my license for International section");
		Assert.assertTrue(homePage.isIntVersion1Displayed(),
				"Failed to display the Version 1 report for International section");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNProjectDashboardDataProvider.class, dataProvider = "TCAdminUSAInt", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc21(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		homePage.clickOnDashboardProjectsTab();
		Assert.assertTrue(homePage.isIntlDodgeProjectSummaryDisplayed(),
				"Failed to display the Intl Dodge Project Summary section");
		Assert.assertTrue(homePage.isUSADodgeProjectSummaryDisplayed(),
				"Failed to display the USA Dodge Project Summary section");
		Assert.assertTrue(homePage.isUSATodayHeaderDisplayed(),
				"Failed to display the 'Today' header under the USA section");
		Assert.assertTrue(homePage.isUSALastVisitHeaderDisplayed(),
				"Failed to display the 'Last Visit' header under the USA section");

		Assert.assertTrue(homePage.isUSAAllProjectTodayCountDisplayed(),
				"Failed to display the count under Today column for USA section for the All Project in my license");
		ProjectResultsPage projectResultPage = homePage.clickUSAAllProjectTodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAAllProjectLastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for USA section for the All Project in my license");
		homePage.clickUSAAllProjectLastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAVersion1TodayCountDisplayed(),
				"Failed to display the count under Today column for USA section for the Version 1 report");
		homePage.clickUSAVersion1TodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isUSAVersion1LastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for USA section for the Version 1 report");
		homePage.clickUSAVersion1LastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntAllProjectTodayCountDisplayed(),
				"Failed to display the count under Today column for International section for the All Project in my license");
		homePage.clickIntAllProjectTodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntAllProjectLastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for International section for the All Project in my license");
		homePage.clickIntAllProjectLastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntVersion1TodayCountDisplayed(),
				"Failed to display the count under Today column for International section for the Version 1 report");
		homePage.clickIntVersion1TodayCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		Assert.assertTrue(homePage.isIntVersion1LastVisitCountDisplayed(),
				"Failed to display the count under Last Visit column for International section for the Version 1 report");
		homePage.clickIntVersion1LastVisitCount();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"));
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc23(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc24(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnActionsDropdown();
		projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		Assert.assertTrue(projectResultPage.getErrorMessage().equals("Please make a selection"), "");
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc26(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		// Precondition: track projects
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackLinkFromSummary();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		projectPage.clickTrackNameInExistingTrackingList();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnFistProjectCheckbox();
		EmailAlertsPage emailAlertsPage = projectResultsPage.mouseOverActionandClickEmailProjects();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

		// Precondition: hide projects
		homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickHideProjects();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectResultsPage = trackingList.clickOnHiddenProjects();

		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickEmailProjects();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc28(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpCancelButton();

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc29(String emailAddress, String password, String alternateEmailAddress, String optionalMsg)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");

		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc30(String emailAddress, String password, String alternateEmailAddress, String optionalMsg)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();

		projectResultPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultPage.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		projectResultPage.waitforLoadingRing();
		Thread.sleep(5000);
		Assert.assertTrue(
				projectResultPage.checkConstructionTypeFiltered(
						DGNEnum.ConstructionTypeDonutLegends.valueOf(CommonUtils.formatIntoCharactersOnly(
								projectResultPage.getConstructionType_LHSFirstFilter_lbl().toUpperCase()))),
				"Failed to filter the construction type");

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNProjectDashboardDataProvider.class, dataProvider = "TCNonAdmin_NotInSubscription", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc31(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		String projectCountBefore = projectResultPage.getChartProjectResultsCount();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		String projectCountAfter = projectResultPage.getChartProjectResultsCount();
		Assert.assertNotEquals(projectCountAfter, projectCountBefore);
		homePage.clickOnSignOutButton();
	}
}

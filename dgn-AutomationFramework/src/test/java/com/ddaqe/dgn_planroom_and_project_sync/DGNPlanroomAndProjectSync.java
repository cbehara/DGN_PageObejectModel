package com.ddaqe.dgn_planroom_and_project_sync;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_visualization.DGNVisualization;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SyncPlanRoomPopupPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNPlanroomAndProjectSync extends BaseTest {
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

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGNT1178", description = "UI- Syncing projects with planroom, with remaining-projects > 0.")
	public void tcDGNT1178(String emailAddress, String password, String message) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomButtonDisplayed();
		Assert.assertEquals(syncPlanRoomPopupPage.getPlanroomDialogMessage(),
				message);
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN1137", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT1140(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1145", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT1145(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertFalse(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertFalse(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertFalse(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertFalse(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1141", description = "Verify the 'Sync to PlanRoom' button is available not on Project report page (TC23929)")
	public void tcDGNT1141(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();
		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertFalse(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertFalse(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertFalse(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertFalse(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Sync to PlanRoom' button on Project Report page (TC23931)")
	public void tcDGNT1142(String emailAddress, String password) {
		String resultPerPage = "500";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertFalse(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertFalse(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertFalse(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertFalse(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGNT1179", description = "UI- Syncing projects with planroom, with remaining-projects > 0.")
	public void tcDGNT1170(String emailAddress, String password,
			String message, String planroomUserMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomButtonDisplayed();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(syncPlanRoomPopupPage.getPlanroomDialogMessage(),
				message);
		Assert.assertEquals(
				syncPlanRoomPopupPage.getPlanroomUserNameDialogMessage(),
				planroomUserMessage);
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGNT1178", description = "UI- Syncing projects with planroom, with remaining-projects > 0.")
	public void tcDGNT1180(String emailAddress, String password, String message) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomButtonDisplayed();
		Assert.assertEquals(syncPlanRoomPopupPage.getPlanroomDialogMessage(),
				message);
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGNT1179", description = "UI- Syncing projects with planroom, with remaining-projects > 0.")
	public void tcDGNT1143(String emailAddress, String password,
			String message, String planroomUserMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomButtonDisplayed();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(syncPlanRoomPopupPage.getPlanroomDialogMessage(),
				message);
		Assert.assertEquals(
				syncPlanRoomPopupPage.getPlanroomUserNameDialogMessage(),
				planroomUserMessage);
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Sync to PlanRoom' button on Project Report page (TC23931)")
	public void tcDGNT1175(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertFalse(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertFalse(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertFalse(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertFalse(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN1137", description = "Verify if DGN user is allowed to access the PlanRoom for their license, the PLANROOM tab is shown (TC23925).")
	public void tcDGNT1173(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());
		Assert.assertFalse(homePage.isPlanroomLogoDisplayedAtHomePage(),
				"Planroom logo is not displayed at DGN home page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Sync to PlanRoom' button on Project Report page (TC23931)")
	public void tcDGNT1174(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertFalse(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertFalse(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertFalse(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertFalse(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertFalse(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Assign Users' activation widget did not display on Home page for users whose license have DocuPro product (TC24070)")
	public void tcDGNT1148(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isAssignUsersBtnDisplayed(),
				"Assigned Users button is not displayed on home page");

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed(),
				"PlanRoom Widget is displayed on Projects tab.");

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed(),
				"PlanRoom Widget is displayed on Companies tab.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Assign Users' activation widget did not display on Home page for users whose license have DocuPro product (TC24070)")
	public void tcDGNT1149(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isAssignUsersBtnDisplayed(),
				"Assigned Users button is not displayed on home page");

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed(),
				"PlanRoom Widget is displayed on Projects tab.");

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed(),
				"PlanRoom Widget is displayed on Companies tab.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1138", description = "Verify if DGN user is not allowed to access the PlanRoom for their license, the PLANROOM tab is not shown. (TC23926)")
	public void tcDGNT1151(String emailAddress, String password,
			String marketingMsg) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());
		Assert.assertEquals(homePage.getPlanroomMarketingMessageText(),
				marketingMsg);

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Project tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Company tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1138", description = "Verify if DGN user is not allowed to access the PlanRoom for their license, the PLANROOM tab is not shown. (TC23926)")
	public void tcDGNT1164(String emailAddress, String password,
			String marketingMsg) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());
		Assert.assertEquals(homePage.getPlanroomMarketingMessageText(),
				marketingMsg);

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Project tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Company tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1138", description = "Verify if DGN user is not allowed to access the PlanRoom for their license, the PLANROOM tab is not shown. (TC23926)")
	public void tcDGNT1153(String emailAddress, String password,
			String marketingMsg) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Project tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Company tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1138", description = "Verify if DGN user is not allowed to access the PlanRoom for their license, the PLANROOM tab is not shown. (TC23926)")
	public void tcDGNT1165(String emailAddress, String password,
			String marketingMsg) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Project tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Company tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1138", description = "Verify if DGN user is not allowed to access the PlanRoom for their license, the PLANROOM tab is not shown. (TC23926)")
	public void tcDGNT1154(String emailAddress, String password,
			String marketingMsg) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed());
		Assert.assertEquals(homePage.getPlanroomMarketingMessageText(),
				marketingMsg);

		homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Project tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnCompaniesLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for Company tab.");
		Assert.assertFalse(homePage.isPlanroomWidgetDisplayed());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1145", description = "Verify the 'Sync to PlanRoom' button  on Project Report Page (TC24024)")
	public void tcDGNT1144(String emailAddress, String password) {
		String resultPerPage = "500";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T1142", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT1182(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertFalse(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertFalse(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is displayed for home tab.");

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		projectPage.clickOnBiddersTab();
		Assert.assertTrue(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		projectPage.clickOnAddendaTab();
		Assert.assertTrue(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3345", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT1185(String emailAddress, String password) {
		String resultPerPage = "500";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"PlanRoom tab is not displayed");

		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(
				projectBiddersPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(
				projectAddendaPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();
	}


	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3318", description = "Verify PRP widget on homepage when DGN license has a PRP, and user does not have access to this DGN-licensed PRP, and user does not have access to any other PRP")
	public void tcDGNT3318(String emailAddress, String password, String message) {
		HomePage homePage = loginAs(emailAddress, password);

		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed(),
				"The widget for PRP is not display");
		Assert.assertEquals(homePage.getPlanroomWidgetMessage(), message);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN1137", description = "PlanRoom Tab Display: Scenario 1: DGN License and User has access to PRP ( PlannRoomPro )")
	public void tcDGNT3347(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on home page.");

		homePage.clickOnProjectsLink();
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on Project page.");

		homePage.clickOnCompaniesLink();
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on Company page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3355", description = "Verify that DGN PlanRoom tab links to the PRP PlanRoom and on click opens PRP PlanRoom in new browser tab when DGN license has a PRP PlanRoom")
	public void tcDGNT3355(String emailAddress, String password,
			String expectedURL) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnPlanroomTab();
		CommonUtils.switchToNewTab(getDriver());

		Assert.assertTrue(CommonUtils.checkStringContains(expectedURL,
				getDriver().getCurrentUrl()),
				"new tab with planroo pro is not open.");
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3356", description = "Verify that DGN PlanRoom tab links to the PR16 PlanRoom and on click opens PR16 PlanRoom in new browser tab when DGN license does not have a PRP PlanRoom, but does have a PR16 PlanRoom.")
	public void tcDGNT3356(String emailAddress, String password,
			String expectedURL) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnPlanroomTab();
		CommonUtils.switchToNewTab(getDriver());

		Assert.assertTrue(CommonUtils.checkStringContains(expectedURL,
				getDriver().getCurrentUrl()),
				"new tab with old planroom is not open.");
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3364", description = "Sync an already synced DR to the user's PRP PlanRoom - Sync is already exist.")
	public void tcDGNT3364(String emailAddress, String password,
			String existMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomBtnDisplayed();
		Assert.assertEquals(syncPlanRoomPopupPage.getPRPSynctMsg(),
				existMessage);
		syncPlanRoomPopupPage.clickOnOKBtnOfSyncPRPProjectExceedUser();
		
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3365", description = "Sync to PlanRoom button for out-of-subscription projects is NOT shown for a user with access to PRP (PlanRoom Pro).")
	public void tcDGNT3365(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();

		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnFirstAddendaLinkProjectAddendaPage();

		Assert.assertTrue(projectAddendaPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed project for out of subscribtion.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3345", description = "PlanRoom Tab Display: Scenario 1: DGN License and User has access to PRP ( PlannRoomPro )")
	public void tcDGNT3345(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on home page.");

		homePage.clickOnProjectsLink();
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on Project page.");

		homePage.clickOnCompaniesLink();
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not display on Company page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3345", description = "Display of the PRP Sync to PlanRoom button : Scenario 1: If DGN license has a PRP, and user has access to this DGN-licensed PRP, then PRP Sync to PlanRoom button is shown")
	public void tcDGNT3352(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project page.");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project firm page.");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(projectBiddersPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project bidder page.");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project plan page.");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project spec page.");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project addenda page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3353", description = "Display of the PRP Sync to PlanRoom button : Scenario 2: If DGN license has a PRP, and DGN license also has a PR16, and user has access to the DGN-licensed PRP, then PRP Sync to PlanRoom button is shown")
	public void tcDGNT3353(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project page.");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project firm page.");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(projectBiddersPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project bidder page.");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project plan page.");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project spec page.");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project addenda page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3353", description = "Display of the PRP Sync to PlanRoom button : Scenario 3: If DGN license has a PRP, and DGN license also has a PR16, and user has access to both the DGN-licensed PRP and the PR16, then PRP Sync to PlanRoom button is shown")
	public void tcDGNT3354(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project page.");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project firm page.");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(projectBiddersPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project bidder page.");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project plan page.");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project spec page.");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed on project addenda page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3357", description = "Verify that DGN PlanRoom tab links to the PRP PlanRoom and on click opens PRP PlanRoom in new browser tab when DGN license has a PRP PlanRoom, and also has a PR16 PlanRoom.")
	public void tcDGNT3357(String emailAddress, String password,
			String expectedURL) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not present into main header at home page.");

		homePage.clickOnPlanroomTab();
		CommonUtils.switchToNewTab(getDriver());

		Assert.assertTrue(CommonUtils.checkStringContains(expectedURL,
				getDriver().getCurrentUrl()),
				"new tab with planroo pro is not open.");
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3359", description = "Sync a DR to the user's PRP PlanRoom - Sync is successful.")
	public void tcDGNT3359(String emailAddress, String password,
			String existMessage) {
		String sortDropDownOption = "Project Title - Ascending";
		String message = "The Project could not be added to your PlanRoom. Please try again later.";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.selectSortingOption(sortDropDownOption);

		ProjectPage projectPage = projectResultsPage
				.clickOnSpecProjectTitle((int) (Math.random()));
		Assert.assertTrue(projectPage.isSynWithPlanRoomBtnDisplayed(),
				"Sync With PlanRoom button is not displayed");
		SyncPlanRoomPopupPage syncPlanRoomPopupPage = projectPage
				.clickOnSynWithPlanRoomBtnDisplayed();

		if (syncPlanRoomPopupPage.getPRPSynctMsg().equals(message)) {
			syncPlanRoomPopupPage.clickOnOKBtnOfSyncPRPProjectExceedUser();
		} else {
			Assert.assertEquals(syncPlanRoomPopupPage.getPRPSynctMsg(),
					message);
			syncPlanRoomPopupPage.clickOnOKBtnOfSyncPRPProjectExceedUser();
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "NoPRP", description = "Verify PRP tab for no prp access present for SSO.")
	public void tcDGNT3348_NoPRP(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertFalse(
				homePage.isPlanroomTabDisplayed(),
				"Planroom tab is present for SSO who dont have PRP access on main header at home page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3353", description = "Verify PRP tab for SSO which had both PRP and PR16 access.")
	public void tcDGNT3348_PRPAndPR16_Access(String emailAddress,
			String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not present for SSO who had both PRP PR16 access.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN1137", description = "Verify PRP tab for SSO which had PR16 access.")
	public void tcDGNT3348_PRP16(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not present for SSO who PR16 access.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "PRPAccess_LicenseToDiffPRP", description = "Verify PRP tab for SSO which had PRR and already with different PRP.")
	public void tcDGNT3348_PRPAccess_LicenseToDiffPRP(String emailAddress,
			String password) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(
				homePage.isPlanroomTabDisplayed(),
				"Planroom tab is not present for SSO with PRP access whose DGN license already had different PRP.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3349", description = "Verify PRP widget on homepage when DGN license has a PRP, and user does not have access to this DGN-licensed PRP, but user does have access to a different PRP.")
	public void tcDGNT3349(String emailAddress, String password, String extMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		Assert.assertTrue(homePage.isPlanroomWidgetDisplayed(),
				"Planroom widget is not displayed on the home page.");
		Assert.assertEquals(homePage.getPlanroomMarketingMessageText(), extMsg);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN1137", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT3362(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		Assert.assertTrue(projectFirmsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		Assert.assertTrue(
				projectBiddersPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(
				projectAddendaPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3345", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page (TC23928)")
	public void tcDGNT3363_prp(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(!projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3356", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page")
	public void tcDGNT3363_pr_16(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3353", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page")
	public void tcDGNT3363_prp_pr_all(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium", "Regression" }, dataProviderClass = PlanroomAndProjectSyncDataProvider.class, dataProvider = "DGN-T3363", description = "Verify the 'Sync to PlanRoom' button is avaiable on Project Report page")
	public void tcDGNT3363_prp_pr_null(String emailAddress, String password) {
		String resultPerPage = "100";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		ProjectPage projectPage = projectResultsPage
				.clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda();

		Assert.assertTrue(!projectPage.isSynWithPlanRoomButtonDisplayed(),
				"Sync With PlanRoom button is not displayed");

		homePage.clickOnSignOutButton();

	}
}

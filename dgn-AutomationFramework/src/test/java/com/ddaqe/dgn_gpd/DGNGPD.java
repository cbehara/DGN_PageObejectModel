package com.ddaqe.dgn_gpd;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectDesignAlertsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;

@Listeners(TestListener.class)

public class DGNGPD extends BaseTest {
	static Logger log = Logger.getLogger(DGNGPD.class);

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
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify the Design Alerts icon beside the DR number (TC22921)")
	public void tc2485(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify the content of Special Filters (TC22922)")
	public void tc2486(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		Assert.assertTrue(projectResultPage.specialFilterDesignAlertsOnlyDisplayed(),
				"Failed to display the design alerts icon");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify the other functionality like track,hide option,save search for newly GPD projects (TC22924)")
	public void tc2488(String EmailAddress, String Password) throws InterruptedException {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// Precondition: track projects
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultPage.clickOnTrackProjects();
		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");
		homePage.clickOnProjectsLink();

		// Hide Projects
		String projectTitle = projectResultPage.getFirstProjectTitle();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		projectResultPage.clickOnHideProjects();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectResultPage = trackingList.clickOnHiddenProjects();

		// Verify if project is found in the hidden projects page
		Assert.assertTrue(projectResultPage.verifyProjectTitleExistsInList(projectTitle),
				"Failed to find the hidden project in the project results list");

		// Save Search
		homePage.clickOnProjectsLink();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageOption("500");
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify there is a Design Alerts tab in the Design Alerts page of the project (TC22940)")
	public void tc2495(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");

		ProjectPage ProjectPage = projectResultPage.clickOnProjectTitleWithDesignAlerts();

		Assert.assertTrue(ProjectPage.isDesignAlertsTabDisplayed(), "Failed to display the design alerts tab");
		ProjectDesignAlertsPage designAlertsPage = ProjectPage.clickOnDesignAlertsTab();

		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerHeaderDisplayed(),
				"Failed to display the design alerts legal disclaimer header");
		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerContentsDisplayed(),
				"Failed to display the design alerts legal disclaimer contents");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify the display of GPD products in the grid system. (TC22941)")
	public void tc2496(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");

		homePage.enterSearchText("201600721340");
		ProjectPage ProjectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(ProjectPage.isDesignAlertsTabDisplayed(), "Failed to display the design alerts tab");
		ProjectDesignAlertsPage designAlertsPage = ProjectPage.clickOnDesignAlertsTab();

		Assert.assertTrue(designAlertsPage.isDesignAlertsEmptyGrid(),
				"Failed to dislay the expected messasge when the grid is empty");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify there is no pagination in the Design Alerts page and all GPD products are displayed in the same page (TC22943)")
	public void tc2498(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");

		ProjectPage ProjectPage = projectResultPage.clickOnProjectTitleWithDesignAlerts();

		Assert.assertTrue(ProjectPage.isDesignAlertsTabDisplayed(), "Failed to display the design alerts tab");
		ProjectDesignAlertsPage designAlertsPage = ProjectPage.clickOnDesignAlertsTab();

		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerHeaderDisplayed(),
				"Failed to display the design alerts legal disclaimer header");
		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerContentsDisplayed(),
				"Failed to display the design alerts legal disclaimer contents");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "Verify Print Actions on DesignAlerts page & Printed DesignAlert Products List (TC23499)")
	public void tc2502(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");

		ProjectPage ProjectPage = projectResultPage.clickOnProjectTitleWithDesignAlerts();

		Assert.assertTrue(ProjectPage.isDesignAlertsTabDisplayed(), "Failed to display the design alerts tab");
		ProjectPage.clickOnDesignAlertsTab();

		Assert.assertTrue(ProjectPage.getBreadCrumbText().equalsIgnoreCase("Project Results > Project > DesignAlerts"),
				"Failed to get the expected breadcrumb");
		ProjectPage.clickOnActionsDropDown();

		Assert.assertTrue(ProjectPage.isPrintProjectDetailsUnderActionsDisplayed(),
				"Failecd to get the print project details displayed");

		Assert.assertTrue(ProjectPage.isPrintProductListUnderActionsDisplayed(),
				"Failecd to get the print product list displayed");
		PrintProjectDetailsPage printProjectDetailPage = ProjectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(printProjectDetailPage.isPrintButtonDisplayed(), "Failed to get the print button displayed");
		Assert.assertTrue(printProjectDetailPage.isBackButtonDisplayed(), "Failed to get the back button displayed");
		goToBackPage();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNGPDDashboardDataProvider.class, dataProvider = "TCPlatinum", description = "[GPD] [DESIGN ALERTS] Verify sorting order of Products in DesignAlert Page (TC23554)")
	public void tc2507(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		if (projectResultPage.isSpecialFilterCollapsed()) {
			projectResultPage.clickOnSpecialFilterCollapsedPanel();
		}
		projectResultPage.clickSpecialFilterDesignAlertsOnly();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.isDesignAlertsIconDisplayed(), "Failed to display the design alerts icon");

		ProjectPage ProjectPage = projectResultPage.clickOnProjectTitleWithDesignAlerts();

		Assert.assertTrue(ProjectPage.isDesignAlertsTabDisplayed(), "Failed to display the design alerts tab");
		ProjectDesignAlertsPage designAlertsPage = ProjectPage.clickOnDesignAlertsTab();

		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerHeaderDisplayed(),
				"Failed to display the design alerts legal disclaimer header");
		Assert.assertTrue(designAlertsPage.isDesignAlertsLegalDisclaimerContentsDisplayed(),
				"Failed to display the design alerts legal disclaimer contents");

		homePage.clickOnSignOutButton();
	}

}

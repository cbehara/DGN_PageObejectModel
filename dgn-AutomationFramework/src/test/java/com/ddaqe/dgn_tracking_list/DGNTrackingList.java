package com.ddaqe.dgn_tracking_list;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.EmailCompanyPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.PrintCompanyListPage;
import com.ddaqe.pages.ProjectCommonContainerPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingLists;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNTrackingList extends TrackingListDataSet {
	static Logger log = Logger.getLogger(DGNTrackingList.class);

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

	@Test(groups = { "Medium",
			"Track" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Display a tracking list-scenario1 (TC1627)")
	public void tc2844TC2846TC2850TC2852(String emailAddress, String password) throws InterruptedException {
		CreateNewTrackingList(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		List<String> trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(1);
		homePage.clickOnSaveFromCustomizeDashboard();
		Assert.assertTrue(homePage.IsDashboardProjectsTrackingListMatch(trackingLists),
				"Failed to add tracking list name(s) in the dashboard");
		Assert.assertTrue(homePage.isMyProjectTrackingListDisplayed(),
				"Tracking list names failed to display as link in 'My Project Tracking List' section");
		Assert.assertTrue(homePage.isMyProjectTrackingListTodayDisplayed(),
				"Count under today column is failed to display as link in My projects Tracking List section");
		Assert.assertTrue(homePage.isMyProjectTrackingListSinceLastVisitDisplayed(),
				"Count under 'Since My Last Visit' column is failed to display as link in My projects saved search section");
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardProjectsTrackingList();
		Assert.assertTrue(projectResultsPage.isResultContentDisplayed(),
				"System failed to display the project results in list view");
		Assert.assertTrue(projectResultsPage.IsSelectedTrackingDisplayed(),
				"System failed to reflects the tracking list selected in the selection area");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Customize dashboard-scenario1 (TC1648)")
	public void tc832(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();

		Assert.assertTrue(homePage.isSaveSearchFirstCheckboxDisplayed());

		homePage.clickOnSaveFromCustomizeDashboard();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Redesigned home-scenario8 (TC1660)")
	public void tc2847_tc2849(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardSavedSearch(2);
		homePage.clickOnSaveFromCustomizeDashboard();
		// Following asserts for multiple test cases
		Assert.assertTrue(homePage.isMyProjectsSavedSearchDisplayed(),
				"Saved search name is failed to displayed as link in My projects saved search section");
		Assert.assertTrue(homePage.isMyProjectsSavedSearchTodayDisplayed(),
				"Count under today column is failed to display as link in My projects saved search section");
		Assert.assertTrue(homePage.isMyProjectsSavedSearchSinceLastVisitDisplayed(),
				"Count under 'Since My Last Visit' column is failed to display as link in My projects saved search section");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4143)")
	public void tc2867(String emailAddress, String password) {
		CreateNewTrackingList(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertTrue(companyPage.getOriginalTitleTracking().trim().contains("Tracked Company"),
				"Tracking Icon is failed to be displayed for the Companies");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2868(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.getOriginalTitleTracking().trim().contains("Tracked Company"),
				"Tracking Icon is failed to be displayed for the Companies");
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2869(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed(),
				"Track Companies Link is failed to display in the Company Results Page - Action menu drop down ");
		companyResultsPage.clickOnTrackCompanyActionsLink();
		Assert.assertTrue(companyResultsPage.isErrorMessageDisplayed(), "Error message is failed to be displayed");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2870(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyContactsPage companyContactsPage = companyResultsPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyContactsPage.mouseOverActionandChecktrackCompanyDisplayed(),
				"Track Company Link is failed to display in the Company Contacts Page - Action menu drop down ");
		TrackPopUpPage trackPopUpPage = companyContactsPage.clickOnTrackCompanyActionsLink();
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyContactsPage.clickOnCompanyProfile();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertTrue(companyPage.getOriginalTitleTracking().trim().contains("Tracked Company"),
				"Tracking Icon is failed to be displayed for the Companies");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2871(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		Assert.assertTrue(companyProjectsPage.mouseOverActionandChecktrackCompanyDisplayed(),
				"Track Company Link is failed to display in the Company Projects Page - Action menu drop down ");
		TrackPopUpPage trackPopUpPage = companyProjectsPage.clickOnTrackCompanyActionsLink();
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyProjectsPage.clickOnCompanyProfile();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertTrue(companyPage.getOriginalTitleTracking().trim().contains("Tracked Company"),
				"Tracking Icon is failed to be displayed for the Companies");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2872(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleWithoutTracked();
		Assert.assertTrue(companyPage.mouseOverActionandChecktrackCompanyDisplayed(),
				"Track Company Link is failed to display in the Company Profile Page - Action menu drop down ");
		TrackPopUpPage trackPopUpPage = companyPage.clickOnTrackCompanyActionsLink();
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertTrue(companyPage.getOriginalTitleTracking().trim().contains("Tracked Company"),
				"Tracking Icon is failed to be displayed for the Companies");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2873(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleWithoutTracked();
		Assert.assertTrue(companyPage.mouseOverActionandChecktrackCompanyDisplayed(),
				"Track Company Link is failed to display in the Company Profile Page - Action menu drop down ");
		TrackPopUpPage trackPopUpPage = companyPage.clickOnTrackCompanyActionsLink();
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnCancelBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		Assert.assertTrue(!companyPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Companies selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2874(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		Assert.assertTrue(trackPopUpPage.isAlertInTrackPopUpDisplayed(), "Alert in Track Company is failed to display");

		trackPopUpPage.clickOnCancelBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		companyResultsPage.clickOnSelectAllCompany();
		Assert.assertTrue(companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed(),
				"Track Companies Link is failed to display in the Company Results Page - Action menu drop down ");
		companyResultsPage.clickOnTrackCompanyActionsLink();
		Assert.assertTrue(trackPopUpPage.isAlertInTrackPopUpDisplayed(), "Alert in Track Company is failed to display");
		trackPopUpPage.clickOnCancelBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4144)")
	public void tc2875_tc2877(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();

		char[] specialCharArray = new char[35];// excluding 15 chars length from
												// 50 chars for datettimestamp
												// to maintain unique name
		Arrays.fill(specialCharArray, 'A');
		String newTracking50 = new String(specialCharArray);
		newTracking50 = trackPopUpPage.formatValidNewTrackingListName(newTracking50);
		trackPopUpPage.enterNewTrackingListName(newTracking50);
		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTracking50),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertEquals(companyPage.getLengthTrackingListNameMatched(newTracking50), 50,
				"50 Characters String failed to accept in a tracking name");
		Assert.assertTrue(companyPage.isAlertIconDisplayed(), "Alert Icon is failed to be displayed for the Companies");
		homePage.clickOnCompaniesLink();
		trackPopUpPage = companyResultsPage.clickOnTrackLink();

		char[] specialCharArray49 = new char[34];// excluding 34 chars length
													// from 49 chars for
													// datettimestamp to
													// maintain unique name
		Arrays.fill(specialCharArray49, 'A');
		String newTracking49 = new String(specialCharArray49);
		newTracking49 = trackPopUpPage.formatValidNewTrackingListName(newTracking49);
		trackPopUpPage.enterNewTrackingListName(newTracking49);
		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTracking49),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertEquals(companyPage.getLengthTrackingListNameMatched(newTracking49), 49,
				"50 Characters String failed to accept in a tracking name");
		homePage.clickOnCompaniesLink();
		trackPopUpPage = companyResultsPage.clickOnTrackLink();

		char[] specialCharArray51 = new char[36];// excluding 15 chars length
													// from 51 chars for
													// datettimestamp to
													// maintain unique name
		Arrays.fill(specialCharArray49, 'A');
		String newTracking51 = new String(specialCharArray51);
		newTracking51 = trackPopUpPage.formatValidNewTrackingListName(newTracking51);
		trackPopUpPage.enterNewTrackingListName(newTracking51);
		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(newTracking51),
				"The Companies selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();

	}

	// Precondition: Atleast one Existing Tracking List should be present
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking projects from list view-scenario3 (TC1545)")
	public void tc2831(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");
		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to remove");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking projects from list view-scenario4 (TC1546)")
	public void tc2832(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to remove");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking projects from list view-scenario5 (TC1547)")
	public void tc2833(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnCancelBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to remove");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(!projectPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking projects from list view-scenario1 (TC1543)")
	public void tc2829TC2884(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(projectResultsPage.isTrackProjectsMenuDisplayed(),
				"Failed to contain Track Project under 'Actions' dropdown");
		Assert.assertTrue(projectResultsPage.isTrackLinkDisplayed(),
				"Failed to display the Track Link in the Project Summary");
		Assert.assertEquals(projectResultsPage.getTrackLinksCountInSummary(),
				projectResultsPage.getProjectSummaryCount(), "Failed to display Track link in each project summary");
		TrackPopUpPage trackPopUpPage = projectResultsPage.mouseOverActionandClicktrackProjects();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isErrorMessageDisplayed(), "Error message is failed to be displayed");

		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClicktrackProjects();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to display");
		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");

		Assert.assertTrue(
				(trackPopUpPage.getPublicInExistingTrackingListCount()
						+ trackPopUpPage.getPrivateInExistingTrackingListCount() - 1) <= trackPopUpPage
								.getExistingTrackingListCount(),
				"User's private and public tracking list is failed to display in the existing tracking list in Track pop up");
		int count = trackPopUpPage.getExistingTrackingListCheckboxCount();
		trackPopUpPage.clickOnCancelBtn();
		projectResultsPage.waitforLoadingRing();
		String newTrackingListName = "";
		if (count < 4) {
			for (int i = 0; i <= (4 - count); i++) {
				projectResultsPage.clickOnFistProjectCheckbox();
				projectResultsPage.mouseOverActionandClicktrackProjects();
				projectResultsPage.waitforLoadingRing();
				newTrackingListName = trackPopUpPage
						.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
				trackPopUpPage.enterNewTrackingListName(newTrackingListName);
				trackPopUpPage.clickOnSaveBtn();
				projectResultsPage.waitforLoadingRing();
			}
		}
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClicktrackProjects();
		projectResultsPage.waitforLoadingRing();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(4);
		trackPopUpPage.clickOnSaveBtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(trackPopUpPage.getErrorMessageText().equalsIgnoreCase("Please select up to 3 tracking lists"),
				"Failed to display error message to notify user for not allowing more than 3 existing tracking list selected.");
	}

	// Precondition: Atleast one Existing Tracking List should be present
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking projects from list view-scenario2 (TC1544)")
	public void tc2830(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(projectResultsPage.isTrackProjectsMenuDisplayed(),
				"Failed to contain Track Project under 'Actions' dropdown");
		Assert.assertTrue(projectResultsPage.isTrackLinkDisplayed(),
				"Failed to displayh the Track Link in the Project Summary");
		Assert.assertEquals(projectResultsPage.getTrackLinksCountInSummary(),
				projectResultsPage.getProjectSummaryCount(), "Failed to display Track link in each project summary");

		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackLinkFromSummary();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to display");
		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");

		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);

		Assert.assertTrue(!trackPopUpPage.isAnyExistingCheckboxSelected(),
				"Any selection in the existing list listbox should be unselected (reset) , If text is entered for a new list");

		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);

		Assert.assertTrue(trackPopUpPage.getNewTrackingListName().isEmpty(),
				"Failed to remove any text entered for a new list (reset) ,If an existing list is selected");

		trackPopUpPage.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Tracking project from report view-scenario1 (TC1548)")
	public void tc2834_tc2839(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectPage.isProjectTabDisplayed(), "Failed to display Projects Tab");
		Assert.assertTrue(projectPage.isPlansTabDisplayed(), "Failed to display Plans Tab");
		Assert.assertTrue(projectPage.isSpecsTabDisplayed(), "FaIled to display Specs Tab");
		Assert.assertTrue(projectPage.isAddendaTabDisplayed(), "Failed to display Addenda Tab");

		projectPage.clickOnProjectTab();
		projectPage.clickOnActionsDropDown();
		TrackPopUpPage trackPopUpPage = projectPage.clickOnTrackProjects();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to display");

		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");

		Assert.assertTrue(!trackPopUpPage.isAnyExistingCheckboxSelected(),
				"No Tracking list should be selected by default");
		int count = trackPopUpPage.getExistingTrackingListCheckboxCount();
		trackPopUpPage.clickOnCancelBtn();
		String newTrackingListName = "";
		for (int i = count; i <= 4; i++) {
			trackPopUpPage = projectPage.mouseOverActionandClickTrackProjects();
			newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
			trackPopUpPage.enterNewTrackingListName(newTrackingListName);
			trackPopUpPage.enterNewTrackingListName(newTrackingListName);
			trackPopUpPage.clickOnSaveBtn();
			newTrackingListName = "";
			projectPage.waitforLoadingRing();
		}
		projectPage.mouseOverActionandClickTrackProjects();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(4);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(trackPopUpPage.getErrorMessageText().contains("Please select up to 3 tracking lists"),
				"Failed to display error message to notify user for not allowing more than 3 existing tracking list selected.");

		trackPopUpPage.clickOnCancelBtn();
		trackPopUpPage = projectPage.mouseOverActionandClickTrackProjects();
		Assert.assertTrue(trackPopUpPage.verifyVerticalScrollPresent(),
				"Failed to display Vertical Scroll in Track Popup");

		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(newTrackingListName);
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);

		Assert.assertTrue(!trackPopUpPage.isAnyExistingCheckboxSelected(),
				"Any selection in the existing list listbox should be unselected (reset) , If text is entered for a new list");

		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);

		Assert.assertTrue(trackPopUpPage.getNewTrackingListName().isEmpty(),
				"Failed to remove any text entered for a new list (reset) ,If an existing list is selected");
		trackPopUpPage.clickOnCancelBtn();

		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to remove");

		projectPage = projectPage.clickOnProjectTab();
		Assert.assertTrue(!projectPage.verifyTrackNameInTrackingList(newTrackingListName),
				"The Projects selected should not have been tracked in the tracking List");

		projectPage.clickOnActionsDropDown();
		trackPopUpPage = projectPage.clickOnTrackProjects();

		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		projectPage.clickOnProjectTab();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");

		projectPage.clickOnActionsDropDown();
		trackPopUpPage = projectPage.clickOnTrackProjects();

		newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(newTrackingListName);
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		projectPage.clickOnProjectTab();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Projects selected failed to be tracked in the tracking List");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Update track projects dialog to add alert option - ui only (TC2245)")
	public void tc2854_tc2855(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();

		projectResultsPage.clickOnSelectAllProjects();

		Assert.assertTrue(projectResultsPage.mouseOverActionandChecktrackProjectsDisplayed(),
				"Track Projects Link is failed to display in the Company Results Page - Action menu drop down ");
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to display");

		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Project to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");
		Assert.assertTrue(trackPopUpPage.isAlertInTrackPopUpDisplayed(), "Alert in Track Company is failed to display");

		trackPopUpPage.clickOnCancelBtn();

		homePage.clickOnSignOutButton();

	}

	// Precondition: Atleast one Existing Tracking List should be present
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track company modal dialog (TC4055)")
	public void tc2857_tc2860__tc2863_tc2866(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();

		companyResultsPage.clickOnFirstCompanyChkBox();

		companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();

		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to display");

		String heading1txt = trackPopUpPage.getTextHeading1();
		Assert.assertEquals(heading1txt, "Add the selected companies to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(trackPopUpPage.isNewTrackingListContainerDisplayed(),
				"Failed to display Add Selected Company to the New Tracking list(Text box)");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpSaveBtnDisplayed(),
				"Failed to display Save Button in Track Popup");
		Assert.assertTrue(trackPopUpPage.isTrackPopUpCancelBtnDisplayed(),
				"Failed to display Cancel Button in Track PopUp");

		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");

		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Companies selected failed to be tracked in the Existing tracking List");
		// TODO : Cleanup code for removal of alert in tracking list - Temporary
		// commented
		/*
		 * Assert.assertTrue(companyPage.isTrackingIconDisplayed(),
		 * "Tracking Icon is failed to be displayed for the Companies");
		 */
		companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);

		char[] charArray = new char[35];// excluding 15 chars length from 50
										// chars for datettimestamp to maintain
										// unique name
		Arrays.fill(charArray, 'A');
		String newTracking50 = new String(charArray);
		newTracking50 = trackPopUpPage.formatValidNewTrackingListName(newTracking50);
		trackPopUpPage.enterNewTrackingListName(newTracking50);

		Assert.assertTrue(!trackPopUpPage.isAnyExistingCheckboxSelected(),
				"Any selection in the existing list listbox should be unselected (reset) , If text is entered for a new list");
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);

		Assert.assertTrue(trackPopUpPage.getNewTrackingListName().isEmpty(),
				"Failed to remove any text entered for a new list (reset) ,If an existing list is selected");

		trackPopUpPage.clickOnCancelBtn();

		companyResultsPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();

		companyPage = companyResultsPage.clickOnCompanyProfileLink();
		Assert.assertTrue(companyPage.verifyTrackNameInExistingTrackingList(selectedExistingTrackingListName),
				"The Companies selected failed to be tracked in the Existing tracking List");
		// TODO : Cleanup code for removal of alert in tracking list - Temporary
		// commented
		/*
		 * Assert.assertTrue(companyPage.isTrackingIconDisplayed(),
		 * "Tracking Icon is failed to be displayed for the Companies");
		 */

		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();

		char[] charArray51 = new char[36];// excluding 15 chars length from 50
											// chars for datettimestamp to
											// maintain unique name
		Arrays.fill(charArray51, 'A');
		String newTracking51 = new String(charArray51);

		newTracking51 = trackPopUpPage.formatValidNewTrackingListName(newTracking51);
		trackPopUpPage.enterNewTrackingListName(newTracking51);

		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		companyResultsPage.clickOnCompanyProfileLink();

		Assert.assertTrue(!companyPage.verifyTrackNameInTrackingList(newTracking51),
				"The Companies selected failed to be tracked in the tracking List");

		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();

		char[] specialCharArray = new char[10];// excluding 15 chars length from
												// 50 chars for datettimestamp
												// to maintain unique name
		Arrays.fill(specialCharArray, '(');
		String specialCharStr = new String(specialCharArray);

		trackPopUpPage.enterNewTrackingListName(specialCharStr);

		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		Assert.assertTrue(
				trackPopUpPage.getErrorMessageText().contains(
						"Tracking list names should be made up of alphanumeric characters including underscores and spaces"),
				"Failed to display the expected error message in the tracking List");
		trackPopUpPage.clickOnCancelBtn();

		companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.mouseOverActionandChecktrackCompaniesDisplayed();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();

		char[] charArrayIncludingSpaceUnderscore = new char[10];// excluding 15
																// chars length
																// from 50 chars
																// for
																// datettimestamp
																// to maintain
																// unique name
		Arrays.fill(charArrayIncludingSpaceUnderscore, '_');
		String NewValidTrackNameIncludingSpaceUnderscore = new String(charArrayIncludingSpaceUnderscore);

		NewValidTrackNameIncludingSpaceUnderscore = trackPopUpPage
				.formatValidNewTrackingListName(NewValidTrackNameIncludingSpaceUnderscore);
		trackPopUpPage.enterNewTrackingListName(NewValidTrackNameIncludingSpaceUnderscore);

		trackPopUpPage.clickOnTrackAlertChkBox();
		trackPopUpPage.clickOnSaveBtn();

		companyPage = companyResultsPage.clickOnCompanyProfileLink();
		companyPage.ScrollCompanyPageToViewAllTrackingList();
		Assert.assertTrue(companyPage.verifyTrackNameInTrackingList(NewValidTrackNameIncludingSpaceUnderscore),
				"The Companies selected failed to be tracked in the tracking List");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4143)")
	public void tc2882__tc2886(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();

		Integer projectCount = projectResultsPage.getListProjectCountText();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();

		trackPopUpPage.clickOnSaveBtn();
		Assert.assertEquals(trackPopUpPage.getErrorMessageText(), "Please select or enter a tracking list name");

		String newTrackingListName = trackPopUpPage.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
		trackPopUpPage.enterNewTrackingListName(newTrackingListName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Project Tracking List Popup is failed to remove");

		Assert.assertTrue(projectResultsPage.getListProjectCountText().intValue() == projectCount.intValue(),
				"Failed to retain the count after tracking the project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Hiding projects from list view-scenario2 (TC1555)")
	public void tc2840_tc2843(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		String projectTitle = projectResultsPage.getFirstProjectTitle();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickHideProjects();
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectResultsPage = trackingList.clickOnHiddenProjects();

		// Verify if project is found in the hidden projects page
		Assert.assertTrue(projectResultsPage.verifyProjectTitleExistsInList(projectTitle),
				"Failed to find the hidden project in the project results list");
		Assert.assertTrue(projectResultsPage.isUnhideDisplayed(),
				"Failed to display unhide link for the hidden projects");
		projectResultsPage.clickOnUnhideLink();
		homePage.clickOnMyAccountLink();
		trackingList = homePage.clickOnMyTrackingList();
		projectResultsPage = trackingList.clickOnHiddenProjects();
		projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		ProjectCommonContainerPage projectCommonContainer = projectResultsPage
				.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		Assert.assertTrue(projectCommonContainer.isProjectTabDisplayed(), "Failed to display Projects Tab");
		Assert.assertTrue(projectCommonContainer.isPlansTabDisplayed(), "Failed to display Plans Tab");
		Assert.assertTrue(projectCommonContainer.isSpecsTabDisplayed(), "FaIled to display Specs Tab");
		Assert.assertTrue(projectCommonContainer.isAddendaTabDisplayed(), "Failed to display Addenda Tab");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Update tracking list rename to use modal dialog (TC2251)")
	public void tc2893_tc2896__tc2856__tc2885(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();

		Assert.assertTrue(trackingList.verifyAddNewBtnDisplayed(),
				"Failed to display the 'Add New' button for the projects");

		trackingList.clickOnAddNewButton();
		Assert.assertTrue(trackingList.verifyAddNewPopUpDisplayed(), "Failed to display the 'Add New' popup");

		char[] charArray = new char[5];
		Arrays.fill(charArray, 'A');
		String textCharArray = new String(charArray);
		textCharArray = CommonUtils.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(textCharArray));

		trackingList.EnterTextInRenamePopUp(textCharArray);
		trackingList.clickOnSaveFromRename();
		Assert.assertTrue(!trackingList.verifyAddNewPopUpDisplayed(), "Failed to remove the 'Add New' popup");

		Assert.assertTrue(trackingList.isEditDisplayed(), "Failed to display 'Edit' link for the tracking lists");
		Assert.assertTrue(trackingList.isDeleteDisplayed(), "Failed to display 'Delete' link for the tracking lists");

		trackingList.clickOnEditLink();

		Assert.assertTrue(trackingList.verifyAddNewPopUpDisplayed(),
				"Failed to display the 'Rename Tracking List' popup");
		Assert.assertTrue(trackingList.isNameTextBoxInRenameDisplayed(), "Failed to display the 'Name text box'");
		Assert.assertTrue(trackingList.isSaveInRenameDisplayed(),
				"Failed to display 'Save' button in the rename tracking list popup");
		Assert.assertTrue(trackingList.isCancelInRenameDisplayed(),
				"Failed to display 'Cancel' button in the rename tracking list popup");

		char[] charArr = new char[51];
		Arrays.fill(charArr, '(');
		String textSpecialCharStr = new String(charArr);

		trackingList.EnterTextInRenamePopUp(textSpecialCharStr);
		trackingList.clickOnSaveFromRename();

		Assert.assertTrue(trackingList.isErrorMessageOnPopUpDisplayed(),
				"Failed to display error message when invalid characters more than 32 text entered and performed save");

		char[] validCharArrayMoreThan32 = new char[51];
		Arrays.fill(validCharArrayMoreThan32, 'A');
		String textMoreThan32ValidChar = new String(validCharArrayMoreThan32);
		textMoreThan32ValidChar = CommonUtils
				.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(textMoreThan32ValidChar));
		trackingList.EnterTextInRenamePopUp(textMoreThan32ValidChar);
		trackingList.clickOnSaveFromRename();

		Assert.assertTrue(trackingList.isErrorMessageOnPopUpDisplayed(),
				"Failed to display error message when valid characters more than 50 text entered and performed save");
		trackingList.clickOnCancelInRename();

		trackingList.clickOnAddNewButton();
		char[] undScorechar = new char[2];
		Arrays.fill(undScorechar, '_');
		String undScoreStr = new String(undScorechar);
		undScoreStr = CommonUtils.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(undScoreStr));
		trackingList.EnterTextInRenamePopUp(undScoreStr);
		trackingList.clickOnSaveFromRename();
		Assert.assertTrue(!trackingList.isErrorMessageOnPopUpDisplayed(), "Failed to performe save");
		trackingList.deleteTrackingList();

		trackingList.clickOnCompaniesLink();

		// Handle precondition for below steps
		trackingList.deleteTrackingList();

		Assert.assertTrue(trackingList.verifyAddNewBtnDisplayed(),
				"Failed to display the 'Add New' button for the company");

		trackingList.clickOnAddNewButton();
		Assert.assertTrue(trackingList.verifyAddNewPopUpDisplayed(), "Failed to display the 'Add New' popup");

		Assert.assertTrue(trackingList.isAddNewBlankTextDisplayed(), "Failed to display the 'Add new blank text box'");
		Assert.assertTrue(trackingList.verifyCursorFocus(), "Failed to find cursor focus on the text field");

		trackingList.clickonSaveFromAddNewPoUp();
		Assert.assertTrue(trackingList.isErrorMessageOnPopUpDisplayed(),
				"Failed to display error message when no text entered and performed save");

		char[] arr = new char[5];
		Arrays.fill(arr, '(');
		String newText = new String(arr);
		trackingList.EnterTextInAddNewPopUp(newText);
		trackingList.clickonSaveFromAddNewPoUp();

		Assert.assertTrue(trackingList.isErrorMessageOnPopUpDisplayed(),
				"Failed to display error message when invalid characters text entered and performed save");

		char[] specialCharArrayInclUnderscore = new char[35];
		Arrays.fill(specialCharArrayInclUnderscore, '_');
		String NewValidTrackNameIncludingSpaceUnderscore = new String(specialCharArrayInclUnderscore);
		NewValidTrackNameIncludingSpaceUnderscore = CommonUtils
				.formatIntoAlphanumeric(CommonUtils.appendDateTimeStamp(NewValidTrackNameIncludingSpaceUnderscore));
		trackingList.EnterTextInAddNewPopUp(NewValidTrackNameIncludingSpaceUnderscore);
		trackingList.clickonSaveFromAddNewPoUp();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Track link from Summary (TC4143)")
	public void tc2891(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResults = homePage.clickOnProjectsLink();
		projectResults.clickOnDropDownList();
		TrackingList trackingList = projectResults.clickOnMySearchesTrackingList();
		Assert.assertTrue(trackingList.isResultPerPageDisplayed(),
				"Failed to display results per page in the tracking list");

		Assert.assertTrue(trackingList.isPageNoTopDisplayed(),
				"Failed to display page no on the top in the tracking list");

		Assert.assertTrue(trackingList.isPageNoBottomDisplayed(),
				"Failed to display page no on the bottom in the tracking list");

		Assert.assertTrue(trackingList.isPaginationDisplayed(), "Failed to display pagination in the tracking list");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Redesigned home-scenario14 (TC1666)")
	public void tc2853(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnRecentLoginDrpDown();
		Assert.assertTrue(homePage.countSelectOptions() == 5, "Failed to display 5 most recent logins");
		homePage.clickOnRecentLogin();
	}

	@Test(dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void za_DeleteProjectTrackingList(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void zb_DeleteCompanyTrackingList(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickonCompanyLink();
		trackingList.deleteTrackingList();
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCNonAdmin_WithoutSpecAlerts", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void zc_DeleteProjectSavedSearches(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.deleteSavedSearches();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Tracking List actions should be present after Page 1")
	public void tc2382(String emailAddress, String password) throws InterruptedException {
		testPrerequisite1(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnSelectAllProjects();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		List<String> selectedExistingTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(3);
		trackPopUpPage.clickOnSaveBtn();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanies();
		List<String> selectedExistingCompanyTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(3);
		trackPopUpPage.clickOnSaveBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.selectResultPerPage("25");
		projectResultsPage = trackingLists.clickOnTrackingList(selectedExistingTrackingListName.get(0));
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isAlertOnLinkPresent(), "Alert link is not displayed.");
		Assert.assertTrue(projectResultsPage.isRemoveLinkPresent(), "Remove link is not displayed.");
		Assert.assertTrue(projectResultsPage.isTrackLinkDisplayed(), "Track link is not displayed.");
		Assert.assertTrue(projectResultsPage.isHideLinkDisplayed(), "Remove link is not displayed.");

		myAccount = homePage.clickOnMyAccountLink();
		trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
		trackingLists.selectResultPerPage("25");
		companyResultsPage = trackingLists.clickOnCompanyTrackingList(selectedExistingCompanyTrackingListName.get(0));
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(companyResultsPage.isAlertOnLinkPresent(), "Alert link is not displayed.");
		Assert.assertTrue(companyResultsPage.isRemoveLinkPresent(), "Remove link is not displayed.");
		Assert.assertTrue(companyResultsPage.isTrackLinkDisplayed(), "Track link is not displayed.");
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Verify the different option present under Action button are working fine for Company Tracking List. (TC22691)")
	public void tc2046(String emailAddress, String password) throws InterruptedException {
		testPrerequisite1(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackCompanies();
		List<String> selectedExistingCompanyTrackingListName = trackPopUpPage.clickOnOneExistingTrackingListCheckBox(3);
		trackPopUpPage.clickOnSaveBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
		trackingLists.selectResultPerPage("25");
		companyResultsPage = trackingLists.clickOnCompanyTrackingList(selectedExistingCompanyTrackingListName.get(0));
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(companyResultsPage.isPrintCompanyListDisplayed(),
				"Print Company List menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isPrintCompanyDetailsDisplayed(),
				"Print Company Details menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isDownLoadCompaniesMenuDisplayed(),
				"Download Companies menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isTrackCompanyActionsLinkDisplayed(),
				"Track Companies menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isRemoveCompaniesActionsLinkDisplayed(),
				"Remove Companies menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isViewCompaniesActionsLinkDisplayed(),
				"View Companies menu item is not displayed.");
		Assert.assertTrue(companyResultsPage.isEmailCompaniesLinkDisplayed(),
				"Email Companies menu item is not displayed.");

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		CompanyPage companyPage = companyResultsPage.clickOnViewCompaniesUnderActions();
		Assert.assertEquals(companyPage.getTitle(), "Dodge Global Network - Company");
		companyResultsPage = companyPage.clickOnCompanyResultsLink();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		Assert.assertTrue(downloadCompanies.isDownloadPopupHeaderDisplayed(), "Download Popup is not displayed.");
		downloadCompanies.clickOnCancelBtn();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		EmailCompanyPage emailCompanyPage = companyResultsPage.clickOnEmailCompanyActionMenu();
		Assert.assertTrue(emailCompanyPage.isEmailCompaniesPopupDialogDisplayed(),
				"Email Companies Popup Dialog is not Displayed on Company Result page.");
		emailCompanyPage.clickOnCancelButton();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnSelectAllProjects();
		List<String> expecteCompanyTitleList = companyResultsPage.getListCompanyProjectTitle();
		companyResultsPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultsPage.clickOnPrintCompanyListUnderActions();
		Assert.assertTrue(printCompanyListPage.compareCompanyTitleList(expecteCompanyTitleList),
				"Print company list page list is not matching with the list of selected company on Company page.");
		goToBackPage();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnSelectAllProjects();
		expecteCompanyTitleList.clear();
		expecteCompanyTitleList = companyResultsPage.getListCompanyProjectTitle();
		companyResultsPage.clickOnActionsDropdown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyResultsPage.clickOnPrintCompanyDetailsUnderActions();
		Assert.assertTrue(printCompanyDetailsPage.getCompanyTitleList().containsAll(expecteCompanyTitleList),
				"Print company detail page list is not matching with the list of selected company on Company page.");
		goToBackPage();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		trackPopUpPage = companyResultsPage.clickOnTrackCompanyActionsLink();
		Assert.assertTrue(trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to display");
		trackPopUpPage.clickOnCancelBtn();
		companyResultsPage.waitforLoadingRing();

		companyResultsPage.clickOnFirstCompanyChkBox();
		expecteCompanyTitleList = companyResultsPage.getListCompanyProjectTitle();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnRemoveCompaniesActionsLink();
		companyResultsPage.waitforLoadingRing();
		Assert.assertFalse(companyResultsPage.getListCompanyProjectTitle().equals(expecteCompanyTitleList),
				"Selected company is not removed from Company page.");
	}

	@Test(groups = { "Regression",
			"High" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithoutSpecAlerts", description = "Verification on clicking the count in the Today column for a tracking list, from Project Dashboard (TC13327)")
	public void tc2887TC2890(String emailAddress, String password) throws InterruptedException {
		testPrerequisite1(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCustomizeDashboard();
		homePage.selectFromCustomiseDashboardTrackingLists(5);
		homePage.clickOnSaveFromCustomizeDashboard();
		homePage.waitforLoadingRing();
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstMyProjectTrackedToday();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getCurrentURL().contains("ProjectResults.aspx"));
		homePage = projectResultsPage.clickOnHomeTab();
		projectResultsPage= homePage.clickOnFirstMyProjectLastVisited();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getCurrentURL().contains("ProjectResults.aspx"));
	}
}

package com.ddaqe.dgn_public_private_share;

import java.util.Collections;
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
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.CustomizeProjectDashboardPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.PrintCompanyDetailsPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingListPopupDialog;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)
public class DGNPublicPrivateShareTest1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

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
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- UI Changes to Customize Dashboard pop-up (TC10740)")
	public void tc56(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(customizeProjectDashboardPage.getSavedSearchCheckBoxList().size() >= 10,
				"Less than 15 items are displayed under Saved Search section.");
		Assert.assertTrue(customizeProjectDashboardPage.isLegendForPublicDisplayed(),
				"Legend for Public is not displayed.");
		Assert.assertTrue(customizeProjectDashboardPage.isLegendForSharedDisplayed(),
				"Legend for Shared is not displayed.");
		Assert.assertTrue(customizeProjectDashboardPage.isOnlyShowMineCheckBoxDisplayed(),
				"Check box for 'only show mine' is not displayed.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Selecting \"Only Show mine\" the filter check box (TC10741)")
	public void tc55_tc57_69(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		customizeProjectDashboardPage.selectOnlyShowMineCheckBox();
		final List<String> savedSearchLabelList = customizeProjectDashboardPage.getSavedSearchCheckBoxLabelList();
		final List<String> trackingListLabelList = customizeProjectDashboardPage.getTrackingListCheckBoxLabelList();
		Assert.assertTrue(
				savedSearchLabelList.size() >= 1 && savedSearchLabelList.get(0).equals("Default Saved Search"),
				"System doesn't display default filter options for Saved Search when 'only show mine' checkbox selected.");
		Assert.assertTrue(trackingListLabelList.size() >= 1 && trackingListLabelList.get(0).equals("My Projects"),
				"System doesn't display default filter options for Tracking List when 'only show mine' checkbox selected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Deselecting \"Only Show mine\" the filter check box (TC10742)")
	public void tc58_tc80_tc82_tc83(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		final List<String> savedSearchLabelList = customizeProjectDashboardPage.getSavedSearchCheckBoxLabelList();
		final List<String> trackingListLabelList = customizeProjectDashboardPage.getTrackingListCheckBoxLabelList();
		customizeProjectDashboardPage.selectOnlyShowMineCheckBox();
		customizeProjectDashboardPage.selectDefaultSavedSearchCheckBox();
		customizeProjectDashboardPage.selectMyProjectsCheckBox();
		Thread.sleep(1000);
		customizeProjectDashboardPage.deSelectOnlyShowMineCheckBox();
		Thread.sleep(1000);
		Assert.assertTrue(savedSearchLabelList.contains("Default Saved Search"),
				"System doesn't display default filter options for Saved Search when 'only show mine' checkbox deselected.");
		Assert.assertTrue(trackingListLabelList.contains("My Projects"),
				"System doesn't display default filter options for Tracking List when 'only show mine' checkbox deselected.");
		Assert.assertFalse(customizeProjectDashboardPage.isDefaultSavedSearchCheckBoxSelected(),
				"System doesn't display default filter options unchecked for Saved Search when 'only show mine' checkbox deselected.");
		Assert.assertFalse(customizeProjectDashboardPage.isMyProjectsCheckBoxSelected(),
				"System doesn't display default filter options unchecked for Tracking List when 'only show mine' checkbox deselected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Adding Items to Dashboard (TC10743)")
	public void tc59_66_68_70(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		customizeProjectDashboardPage.selectOnlyShowMineCheckBox();
		customizeProjectDashboardPage.selectDefaultSavedSearchCheckBox();
		customizeProjectDashboardPage.selectMyProjectsCheckBox();
		homePage = customizeProjectDashboardPage.clickSaveButton();
		Thread.sleep(5000);
		Assert.assertTrue(homePage.getMyProjectsSavedSearchItemList().contains("Default Saved Search"),
				"System doesn't display 'Default Saved Search' on home page when selected from customized page.");
		Assert.assertTrue(homePage.getMyProjectsTrackingListItemList().contains("My Projects"),
				"System doesn't display 'My Projects' on home page when selected from customized page.");
		Assert.assertTrue(homePage.verifyLegendToMyProjectsSavedSearchItem(),
				"System displays lagend to private Saved Search items.");
		Assert.assertTrue(homePage.verifyLegendToMyProjectsTrackingListItem(),
				"System displays legend to private tracking list items.");
		customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		customizeProjectDashboardPage.deSelectOnlyShowMineCheckBox();
		customizeProjectDashboardPage.desSelectDefaultSavedSearchCheckBox();
		customizeProjectDashboardPage.deSelectMyProjectsCheckBox();
		customizeProjectDashboardPage.clickSaveButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Rename Saved Search Item in dashboard (TC10744)")
	public void tc60_63(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		SavedSearchPopUp savedSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		final String savedSearchName = String.valueOf(new Date().getTime());
		savedSearchPopUp.enterName(savedSearchName);
		savedSearchPopUp.clickSave();

		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchPopUp = savedSearchesPage.clickEditLink(savedSearchName);
		final String newSavedSearchName = String.valueOf(new Date().getTime());
		savedSearchPopUp.enterName(newSavedSearchName);
		savedSearchesPage = savedSearchPopUp.clickOnSaveButtonEditSaveSearch();
		Assert.assertTrue(savedSearchesPage.AllSavedSearchesList().contains(newSavedSearchName),
				"System doesn't perform rename functionality for saved search.");
		savedSearchesPage.deleteSavedSearch(newSavedSearchName);
		Assert.assertFalse(savedSearchesPage.AllSavedSearchesList().contains(newSavedSearchName),
				"System doesn't perform delete functionality for saved search.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Rename Tracking List Item in dashboard (TC10745)")
	public void tc61_64(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackLinkFromSummary();
		final String trackingListName = String.valueOf(new Date().getTime());
		trackPopUpPage.enterNewTrackingListName(trackingListName);
		trackPopUpPage.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		TrackingListPopupDialog trackingListPopupDialog = trackingList.clickEditLink(trackingListName);
		final String newTrackingListName = String.valueOf(new Date().getTime());
		trackingListPopupDialog.editTrackingNameSetText(newTrackingListName);
		TrackingLists trackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();
		Assert.assertTrue(trackingLists.AllTrackingList().contains(newTrackingListName),
				"System doesn't perform rename functionality for 'Tracking Lists'.");
		trackingLists.deleteTrackingList(newTrackingListName);
		Assert.assertFalse(trackingLists.AllTrackingList().contains(newTrackingListName),
				"System doesn't perform delete functionality for 'Tracking Lists'.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify Customize dashboard to include public and shared lists - PLAT user- Rename Items in dashboard and Cross-verify in Dashboard (TC10746)")
	public void tc62_65_67_88(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		SavedSearchPopUp savedSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		final String savedSearchName = String.valueOf(new Date().getTime());
		savedSearchPopUp.enterName(savedSearchName);
		savedSearchPopUp.clickSave();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackLinkFromSummary();
		final String trackingListName = String.valueOf(new Date().getTime());
		trackPopUpPage.enterNewTrackingListName(trackingListName);
		trackPopUpPage.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchPopUp = savedSearchesPage.clickEditLink(savedSearchName);
		final String newSavedSearchName = String.valueOf(new Date().getTime());
		savedSearchPopUp.enterName(newSavedSearchName);
		savedSearchesPage = savedSearchPopUp.clickOnSaveButtonEditSaveSearch();
		Assert.assertTrue(savedSearchesPage.AllSavedSearchesList().contains(newSavedSearchName),
				"System doesn't perform rename functionality for saved search.");
		Assert.assertTrue(savedSearchesPage.getSavedSearchType(newSavedSearchName).contains("Private"),
				"System doesn't perform rename functionality for saved search.");

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		TrackingListPopupDialog trackingListPopupDialog = trackingList.clickEditLink(trackingListName);
		final String newTrackingListName = String.valueOf(new Date().getTime());
		trackingListPopupDialog.editTrackingNameSetText(newTrackingListName);
		TrackingLists trackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();
		Assert.assertTrue(trackingLists.AllTrackingList().contains(newTrackingListName),
				"System doesn't perform rename functionality for 'Tracking Lists'.");
		Assert.assertTrue(trackingLists.getTrackingListType(newTrackingListName).contains("Private"),
				"System doesn't perform rename functionality for 'Tracking Lists'.");

		trackingList.clickOnHomeLink();
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		Assert.assertTrue(customizeProjectDashboardPage.getSavedSearchCheckBoxLabelList().contains(newSavedSearchName),
				"System doesn't display options for Saved Search.");
		Assert.assertTrue(
				customizeProjectDashboardPage.getTrackingListCheckBoxLabelList().contains(newTrackingListName),
				"System doesn't display options for Tracking List.");

		customizeProjectDashboardPage.clickCloseButton();

		homePage.clickOnMyAccountLink();
		trackingLists = homePage.clickOnMyTrackingListContextMenu();
		trackingLists.deleteTrackingList(newTrackingListName);

		homePage.clickOnMyAccountLink();
		savedSearchesPage = homePage.clickOnSavedSearchMenuUnderMyAccount();
		savedSearchesPage.deleteSavedSearch(newSavedSearchName);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify add to tracking list to include public and shared lists - PLAT admin user - Projects Tracking Lists (Private) (TC10756)")
	public void tc71_tc73_tc74_tc93_tc95_tc96_tc97(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		TrackPopUpPage trackPopUpPage = projectPage.mouseOverActionandClickTrackProjects();
		String heading1txt = trackPopUpPage.getTextHeading1();
		String headng2txt = trackPopUpPage.getTextHeading2();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(headng2txt.contains("Choose up to 3 existing tracking lists"));
		Assert.assertEquals(trackPopUpPage.getExistingTrackingListCheckboxCount(),
				trackPopUpPage.getExistingTrackingListCount());
		Assert.assertTrue(trackPopUpPage.isLegendForPublicDisplayed(), "Legend for Public is not displayed.");
		Assert.assertTrue(trackPopUpPage.isLegendForSharedDisplayed(), "Legend for Shared is not displayed.");
		Assert.assertTrue(trackPopUpPage.isOnlyShowMineCheckBoxDisplayed(),
				"Check box for 'only show mine' is not displayed.");
		Assert.assertEquals(trackPopUpPage.getTrackingAccessType(), "Private",
				"'Private' access type is not displayed.");
		Assert.assertTrue(trackPopUpPage.verifyLabelForNewTrackingList(),
				"Label for New Tracking list is not displayed.");
		Assert.assertTrue(trackPopUpPage.verifyLabelForAlertSection(), "Label for alert section is not displayed.");
		final String trackingListName = String.valueOf(new Date().getTime());
		trackPopUpPage.enterNewTrackingListName(trackingListName);
		TrackingList trackingList = trackPopUpPage.clickOnSaveBtn();
		List<String> trackListName = trackingList.getTrackListName();
		Assert.assertTrue(trackListName.contains(trackingListName),
				"Newly created tracking list name is not displayed on main page.");
		Collections.sort(trackListName);
		Assert.assertEquals(trackListName, trackingList.getTrackListName(),
				"Tracking list name is not sorted on main page.");
		PrintProjectDetailsPage printProjectDetailsPage = projectPage.mouseOverActionAndClickProjectDetailsLink();
		Assert.assertEquals(trackListName, printProjectDetailsPage.getTrackingListName(),
				"Tracking list name is not displayed on Print Project Details page.");
		printProjectDetailsPage.clickOnBackButton();

		homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = homePage.clickOnMyTrackingListContextMenu();
		trackingLists.deleteTrackingList(trackingListName);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify add to tracking list to include public and shared lists - PLAT admin user - Projects Tracking Lists (Public) (TC10757)")
	public void tc72(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		TrackPopUpPage trackPopUpPage = projectPage.mouseOverActionandClickTrackProjects();
		trackPopUpPage.selectOnExistingTrackingList("B Public");
		TrackingList trackingList = trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(trackingList.getTrackListName().contains("B Public"),
				"Newly created tracking list name is not displayed on main page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify add to tracking list to include public and shared lists - PLAT admin user - Companies Tracking Lists (Private) (TC10760)")
	public void tc75_tc77_tc78_tc94_tc98_tc99_tc100(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		TrackPopUpPage trackPopUpPage = companyPage.mouseOverActionandClickTrackProjects();
		String heading1txt = trackPopUpPage.getTextHeading1();
		String headng2txt = trackPopUpPage.getTextHeading2();
		Assert.assertEquals(heading1txt, "Add the selected companies to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(headng2txt.contains("Choose up to 3 existing tracking lists"));
		Assert.assertEquals(trackPopUpPage.getExistingTrackingListCheckboxCount(),
				trackPopUpPage.getExistingTrackingListCount());
		Assert.assertTrue(trackPopUpPage.isLegendForPublicDisplayed(), "Legend for Public is not displayed.");
		Assert.assertTrue(trackPopUpPage.isLegendForSharedDisplayed(), "Legend for Shared is not displayed.");
		Assert.assertTrue(trackPopUpPage.isOnlyShowMineCheckBoxDisplayed(),
				"Check box for 'only show mine' is not displayed.");
		Assert.assertEquals(trackPopUpPage.getTrackingAccessType(), "Private",
				"'Private' access type is not displayed.");
		Assert.assertTrue(trackPopUpPage.verifyLabelForNewTrackingList(),
				"Label for New Tracking list is not displayed.");
		Assert.assertTrue(trackPopUpPage.verifyLabelForAlertSection(), "Label for alert section is not displayed.");
		final String trackingListName = String.valueOf(new Date().getTime());
		trackPopUpPage.enterNewTrackingListName(trackingListName);
		TrackingList trackingList = trackPopUpPage.clickOnSaveBtn();
		List<String> companyTrackNameList = trackingList.getCompanyTrackListName();
		Assert.assertTrue(companyTrackNameList.contains(trackingListName),
				"Newly created tracking list name is not displayed on main page.");
		Collections.sort(companyTrackNameList);
		Assert.assertEquals(companyTrackNameList, trackingList.getCompanyTrackListName(),
				"Company tracking list name is not sorted on main page.");
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.mouseOverActionAndClickPrintCompanyDetailsLink();
		Assert.assertEquals(companyTrackNameList, printCompanyDetailsPage.getTrackingListNameForPrintCompanyDetails(),
				"Tracking list name is not displayed on Print Company Details page.");
		printCompanyDetailsPage.clickBackButton();

		homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFistProjectCheckbox();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnPrintCompanyListUnderActions();
		Assert.assertTrue(
				companyTrackNameList.containsAll(printCompanyDetailsPage.getTrackingListNameForPrintCompanyList()),
				"Tracking list name is not displayed on Print Company Details page.");
		printCompanyDetailsPage.clickBackButton();

		homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = homePage.clickOnMyTrackingListContextMenu();
		trackingLists.switchToCompanyTrackingListSection();
		trackingLists.deleteTrackingList(trackingListName);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Modify add to tracking list to include public and shared lists - PLAT admin user - Companies Tracking Lists (Public) (TC10761)")
	public void tc76(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		TrackPopUpPage trackPopUpPage = companyPage.mouseOverActionandClickTrackProjects();
		trackPopUpPage.selectOnExistingTrackingList("My Companies");
		TrackingList trackingList = trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(trackingList.getCompanyTrackListName().contains("My Companies"),
				"Newly created tracking list name is not displayed on main page.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Changes to number of items that can be displayed on dashboard - PLAT user - Capturing Error Message (TC10766)")
	public void tc79(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CustomizeProjectDashboardPage customizeProjectDashboardPage = homePage.clickOnCustomizeDashboard();
		customizeProjectDashboardPage.clickOnMultipleExistingTrackingListCheckBoxes(21);
		homePage = customizeProjectDashboardPage.clickSaveButton();
		Thread.sleep(1000);
		Assert.assertTrue(
				customizeProjectDashboardPage.getErrorMessage().contains("You can choose up to 20 tracking lists."),
				"System does not display error message for more than 20 tracking list selection");
		customizeProjectDashboardPage.clickOnMultipleExistingSavedSearchCheckBoxes(11);
		homePage = customizeProjectDashboardPage.clickSaveButton();
		Thread.sleep(1000);
		Assert.assertTrue(
				customizeProjectDashboardPage.getErrorMessage().contains("You can choose up to 10 saved searches."),
				"System does not display error message for more than 10 saved searches selection");
		customizeProjectDashboardPage.clickCloseButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Display tracking list on results page - remove them from tool tip - Projects - Verifying Project P1 with exactly 5 Tracking lists (TC10780)")
	public void tc84_tc85_tc86_tc87(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		trackPopUpPage.selectOnExistingTrackingList("My Projects");
		trackPopUpPage.clickOnSaveBtn();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingListFacetList");
		Assert.assertFalse(projectResultsPage.getProjectTrackList().isEmpty(),
				"Tracking list is not displayed on Project Result page.");
		Assert.assertTrue(projectResultsPage.isTrackIconDisplayed(),
				"Star icon is not displayed for Tracked project on Project Result page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - Display tracking list on results page - remove them from tool tip - Companies - Verifying Company P1 with exactly 5 Tracking lists (TC10785)")
	public void tc89_tc90_tc91_tc_92(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		trackPopUpPage.selectOnExistingTrackingList("My Companies");
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(companyResultsPage.verifyFirstTrackingListNameExists("My Companies"),
				"Tracking list is not displayed on Company Result page.");
		Assert.assertTrue(companyResultsPage.isTrackIconDisplayed(),
				"Star icon is not displayed for Tracked project on Company Result page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "delete company private tracking list.")
	public void deleteCompanyTrackingList(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		do {
			trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "delete project private tracking list.")
	public void deleteProjectTrackingList(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		do {
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}
}

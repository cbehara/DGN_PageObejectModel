package com.ddaqe.dgn_project_tracking_list;

import static org.testng.Assert.assertTrue;

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
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)

public class DGNProjectTrackingList extends BaseTest {

	static Logger log = Logger.getLogger(DGNProjectTrackingList.class);

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
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void AA_createTrackingListWithAlertOn(String emailAddress, String password, String lastnamefirstname)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName("AlertON");
		trackPopup.clickOnTrackAlertChkBox();
		trackPopup.clickOnSaveBtn();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName("AlertON");
		trackPopup.clickOnTrackAlertChkBox();
		trackPopup.clickOnSaveBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void AB_createTrackingListWithAlertOff(String emailAddress, String password, String lastnamefirstname)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();

		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName("AlertOFF");
		trackPopup.clickOnSaveBtn();
		if (trackPopup.isTrackPopUpCancelBtnDisplayed())
			trackPopup.clickOnCancelBtn();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName("AlertOFF");
		trackPopup.clickOnSaveBtn();
		if (trackPopup.isTrackPopUpCancelBtnDisplayed())
			trackPopup.clickOnCancelBtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc882(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnTrackAlertChkBox();
		trackPopup.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");

		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertTrue(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOffLink();
		Assert.assertTrue(projectResultsPage.isTrackIconDisplayed());
		Assert.assertFalse(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc883(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertFalse(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();
		Assert.assertFalse(projectResultsPage.isTrackIconDisplayed());
		Assert.assertTrue(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOffLink();

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc884(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertFalse(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickAlertOn();
		Assert.assertFalse(projectResultsPage.isTrackIconDisplayed());
		Assert.assertTrue(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOffLink();
		projectResultsPage.clickOnSelectAllProjects();
		projectResultsPage.mouseOverActionandClickAlertOn();
		Assert.assertFalse(projectResultsPage.isTrackIconDisplayed());
		Assert.assertTrue(projectResultsPage.isalertOnIconDisplayed());

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc885(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnTrackAlertChkBox();
		trackPopup.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnExistingTrackngList(trackName);
		Assert.assertTrue(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.mouseOverActionandClickAlertOff();
		Assert.assertTrue(projectResultsPage.isTrackIconDisplayed());
		Assert.assertFalse(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();
		projectResultsPage.clickOnSelectAllProjects();
		projectResultsPage.mouseOverActionandClickAlertOff();
		Assert.assertTrue(projectResultsPage.isTrackIconDisplayed());
		Assert.assertFalse(projectResultsPage.isalertOnIconDisplayed());
		projectResultsPage.clickAlertOnLink();

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc886(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.verifyTrackingListDataGrid(lastnamefirstname);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc887(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickOnCompaniesLink();
		trackingList.verifyTrackingListDataGrid(lastnamefirstname);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc888(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		// List<String> trackingLists =
		// homePage.selectFromCustomiseDashboardTrackingLists(1);
		homePage.clickOnSaveFromCustomizeDashboard();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");
		ProjectResultsPage projectResultsPage = trackingList.clickOnTrackingList(1);
		projectResultsPage.selectSortingOption("Project Title - Descending");
		projectResultsPage.clickOnHomeTab();

		homePage.clickOnFirstDashboardProjectsTrackingList();
		Assert.assertEquals(projectResultsPage.getSelectedSortOption(), "Project Title - Descending");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc889(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		companyResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = companyResultsPage.clickOnTrackCompanies();
		trackPopup.enterNewTrackingListName(trackName);
		trackPopup.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.selectResultPerPageDisplayed("500");
		trackingList.clickOnCompaniesLink();
		trackingList.clickOnExistingTrackingListCompanies(trackName);
		Assert.assertFalse(companyResultsPage.isalertOnIconDisplayed());
		companyResultsPage.clickAlertOnLink();
		Assert.assertTrue(companyResultsPage.isalertOnIconDisplayed());
		Assert.assertTrue(companyResultsPage.isAlertoffDisplayed());
		companyResultsPage.clickAlertOffLink();

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc890(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		Assert.assertTrue(trackingList.verifyfooterLogoDisplayed());
		Assert.assertEquals(trackingList.getfooterLogoText(),
				"http://www.construction.com/images/logos/DDA_DGN_logo.png");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc891(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		for (int i = 0; i < 10; i++) {
			String trackName = "Automation" + String.valueOf(new Date().getTime());
			ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
			projectresultsPage.SelectResultDropdownValue("25");
			projectresultsPage.clickOnSelectAllProjects();
			projectresultsPage.clickOnActionsDropdown();
			TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
			trackPopup.enterNewTrackingListName(trackName);
			trackPopup.clickOnTrackAlertChkBox();
			trackPopup.clickOnSaveBtn();
		}
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		TrackingList trackingList = homePage.clickOnMyTrackingListSubmenu();
		trackingList.selectResultPerPageDisplayed("10");
		trackingList.clickOnPageNumber2();
		homePage.waitforLoadingRing();
		ProjectResultsPage projectresultsPage = trackingList.clickOnTrackingList(1);
		projectresultsPage.waitforLoadingRing();
		Assert.assertTrue(projectresultsPage.IsPageNumber1HighLightedInBold());

		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc892(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String savedSearchPrefix = "ExcludeTrackingList";
		deleteProjectSaveSearchAdminUser(homePage, savedSearchPrefix);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnTrackingListFilter();
		projectResultsPage.clikcExcludeTrackingListChkbox();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(1, "trackingListFacetList");
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = savedSearchPrefix + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageOption("500");
		projectResultsPage.waitforLoadingRing();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnTrackingListFilterSeeMore_Btn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isExcludeCheckboxUnChecked());
	}

	private void deleteProjectSaveSearchAdminUser(HomePage homePage, String savedSearchPrefix) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectResultPerPageOption("500");
		while (savedSearchesPage.isSavedSearchDisplayed(savedSearchPrefix)) {
			savedSearchesPage.deleteSavedSearch(savedSearchPrefix);
		}
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc893(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		List<String> existingTrackingList1 = trackPopup.clickOnOneExistingTrackingListCheckBox(1);

		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(!trackPopup.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();

		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(existingTrackingList1));
		Assert.assertTrue(projectPage.isTrackingIconDisplayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc894(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.clickOnMultipleExistingTrackingListCheckBoxes(5);
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(trackPopup.checkforValidationMessage("Please select up to 3 tracking lists"));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc895(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(trackPopup.checkforValidationMessage("Please select or enter a tracking list name"));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc896(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		String heading1txt = trackPopup.getTextHeading1();
		String headng2txt = trackPopup.getTextHeading2();
		Assert.assertEquals(heading1txt, "Add the selected projects to up to 3 existing tracking lists OR a new one");
		Assert.assertTrue(headng2txt.contains("Choose up to 3 existing tracking lists"));
		Assert.assertEquals(trackPopup.getExistingTrackingListCheckboxCount(),
				trackPopup.getExistingTrackingListCount());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc897(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		trackPopup.clickOnMultipleExistingTrackingListCheckBoxes(3);
		trackPopup.clickOnTrackAlertChkBox();
		trackPopup.clickOnSaveBtn();
		Assert.assertFalse(trackPopup.isTrackPopUpDisplayed());
		Assert.assertTrue(projectresultsPage.isAlertIconDisplayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc898(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		TrackingList trackingList = homePage.clickOnMyTrackingListSubmenu();
		trackingList.verifyTrackingListColumn1Header();

		homePage.clickOnCompaniesLink();
		homePage.clickOnMySearchesDropDown();
		homePage.clickOnMyTrackingListSubmenu();
		trackingList.verifyTrackingListColumn1Header();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc899(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnSecondProjectCheckbox();
		projectresultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectresultsPage.clickOnTrackProjects();
		List<String> existingTrackingList = trackPopup.clickOnOneExistingTrackingListCheckBox(1);
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(!trackPopup.isTrackPopUpDisplayed(), "Project Tracking List Popup has failed to remove");
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(existingTrackingList));
		Assert.assertTrue(projectPage.isTrackingIconDisplayed());
		homePage.clickOnProjectsLink();
		projectresultsPage.clickOnSecondProjectTitle();
		Assert.assertTrue(projectPage.verifyTrackNameInExistingTrackingList(existingTrackingList));
		Assert.assertTrue(projectPage.isTrackingIconDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc900(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		String ProjCountBeforeHide = projectresultsPage.getCountOfProjectsDisplayed();
		int ProjectCountBeforeHide = Integer.parseInt(ProjCountBeforeHide);
		projectresultsPage.clickOnFistProjectCheckbox();
		String ProjTitle = projectresultsPage.getFirstProjectTitle();

		projectresultsPage.clickOnActionsDropdown();
		projectresultsPage.clickOnHideProjects();
		String ProjCountAfterHide = projectresultsPage.getCountOfProjectsDisplayed();
		int ProjectCountAfterHide = Integer.parseInt(ProjCountAfterHide);

		int hiddenProjects = ProjectCountBeforeHide - ProjectCountAfterHide;
		Assert.assertEquals(hiddenProjects, 1);

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectresultsPage = trackingList.clickOnHiddenProjects();

		// String HiddenProjTitle = projectresultsPage.getFirstProjectTitle();
		assertTrue(projectresultsPage.checkHiddenProject(ProjTitle),
				ProjTitle + " is not present in hidden project list");
		// trackingList.clickOnUnhideProject();
		projectresultsPage.unhidSpecificProject(ProjTitle);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc901(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		TrackPopUpPage trackPopup = companyPage.clickOnTrackCompanyActionsLink();
		List<String> existingTrackingList = trackPopup.clickOnOneExistingTrackingListCheckBox(1);
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(!trackPopup.isTrackPopUpDisplayed());

		Assert.assertTrue(companyPage.verifyTrackNameInExistingTrackingList(existingTrackingList),
				"The Companies selected failed to be tracked in the tracking List");
		Assert.assertTrue(companyPage.isTrackingIconDisplayed(),
				"Tracking Icon is failed to be displayed for the Companies");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc902(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		TrackingList trackingList = homePage.clickOnMyTrackingListSubmenu();
		trackingList.deleteTrackingList();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();

		String trackName = "Automation" + String.valueOf(new Date().getTime());
		trackPopUpPage.enterNewTrackingListName(trackName);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		Assert.assertTrue(companyResultsPage.verifyFirstTrackingListNameExists(trackName));
		homePage.clickOnMyAccountLink();
		homePage.clickOnMyTrackingList();
		trackingList.deleteTrackingList();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc903(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		Assert.assertTrue(!trackPopUpPage.isTrackPopUpDisplayed(), "Company Tracking List Popup is failed to remove");
		String PageTitle = companyResultsPage.getTitle();
		Assert.assertEquals(PageTitle, "Dodge Global Network - Company Results");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc904(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectFirmsPage projectfirmsPage = projectresultsPage.clickOnFirmsLink();
		projectfirmsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = projectfirmsPage.clickOnTrackProjects();
		trackPopup.clickOnOneExistingTrackingListCheckBox(1);
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(!trackPopup.isTrackPopUpDisplayed(), "Project Tracking List Popup has failed to remove");
		Assert.assertTrue(projectfirmsPage.IsFirmsGridDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc905(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsPage = homePage.ClickMySpecalertsDropdown();
		projectresultsPage.clickOnFistProjectCheckbox();
		projectresultsPage.clickOnSecondProjectCheckbox();
		specAlertsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopup = specAlertsPage.clickOnTrackProjects();
		trackPopup.clickOnOneExistingTrackingListCheckBox(1);
		trackPopup.clickOnSaveBtn();
		Assert.assertTrue(!trackPopup.isTrackPopUpDisplayed(), "Project Tracking List Popup has failed to remove");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc906(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnDashboardProjectsTab();
		homePage.clickOnCustomizeDashboard();
		List<String> trackingLists = homePage.selectFromCustomiseDashboardTrackingLists(1);
		homePage.clickOnSaveFromCustomizeDashboard();

		ProjectResultsPage projectresultsPage = homePage.clickOnFirstTrackingList();
		String PageTitle = projectresultsPage.getTitle();
		Assert.assertEquals(PageTitle, "Dodge Global Network - Project Results");
		Assert.assertTrue(projectresultsPage.IsVisualizeProjectIconPresent());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ProjectTrackingListDataProvider.class, dataProvider = "ProjectTrackingListProviderCommon", description = "Verify displaying the owner of public/shared project tracking lists under my account section (TC14598)")
	public void tc907(String emailAddress, String password, String lastnamefirstname) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists custAccountPage = myAccount.clickOnMyTrackingLists();
		ProjectResultsPage projResultsPage = custAccountPage.clickOnFirstTrackingList();
		String PageTitle = projResultsPage.getTitle();
		Assert.assertEquals(PageTitle, "Dodge Global Network - Project Results");
		Assert.assertTrue(projResultsPage.IsVisualizeProjectIconPresent());

		homePage.clickOnSignOutButton();
	}

}

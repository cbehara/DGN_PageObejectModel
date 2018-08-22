package com.ddaqe.dgn_project_profile;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_company_saved_search.DGNCompanySavedSearch;
import com.ddaqe.pages.AdminUsersPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ManageProfilesPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyRegistrationInfoPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;

@Listeners(TestListener.class)
public class DGNProjectProfile extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanySavedSearch.class);

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

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Creating Saved Search")
	public void CreateSavedSearch(String emailAddress, String password, String ChildAdminEmail,
			String ChildAdminPassword) throws InterruptedException {
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();

		String searchName = "";
		SavedSearchPopUp saveSearchPopUp;
		for (int i = 0; i < 5; i++) {
			saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

			searchName = "Automation" + String.valueOf(new Date().getTime());
			saveSearchPopUp.enterName(searchName);
			if (i == 0) {
				saveSearchPopUp.clickSave();
			} else {
				saveSearchPopUp.clickSaveAsNew();
			}
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2811(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2507(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		/*HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);*/

		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(4);
		homePage.clickOnSaveFromCustomizeDashboard();
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardMyProjectsSavedSearchList();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2808(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
//		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		/*MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
//		manageProfilesPage.AddUserProfile();
		// End of precondition
//		homePage.clickOnSignOutButton();
*/
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword); 
		
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(4);
		homePage.clickOnSaveFromCustomizeDashboard();
	
		homePage =  new HomePage(getDriver());
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardMyProjectsSavedSearchList();
		
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2804(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2806(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnCustomizeDashboard();
		homePage.uncheckFromCustomiseDashboardSavedSearch();
		homePage.selectFromCustomiseDashboardSavedSearch(4);
		homePage.clickOnSaveFromCustomizeDashboard();
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardMyProjectsSavedSearchList();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2810(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.enterSearchText("doors");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2805(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.enterSearchText("doors");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2803(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2802(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(!projectResultsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2794(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2795(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2789(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2790(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2800(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2780(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2784(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2783(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2782(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2781(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2778(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2785(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2787(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2786(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2798(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2777(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2796(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2779(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2799(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2797(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2823(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2770(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2771(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2776(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2774(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2772(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2773(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2775(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2769(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2822(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2821(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyRegistrationInfoPage myregistrationPage = myAccount.clickOnMyRegistration();
		myregistrationPage.clickEditPersonalInfo();
		myregistrationPage.enterCompanyName("test");
		myregistrationPage.clickUpdatePersonalInfo();
		myregistrationPage.switchToPreviousTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2816(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2819(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2817(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnProjectsLink();
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		companyProjectsPage.clickOnGeographyFilter();
		Assert.assertTrue(companyProjectsPage.checkGeographyFilter());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2815(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		/*HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition
		homePage.clickOnSignOutButton();*/
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		companyResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectValuesFromStructPropDropdown("square area", 0, 1, 19, 1);
		projectResultsPage.SelectValuesFromStructPropDropdown("Number of Buildings", 2, 3, 5, 1);
		Thread.sleep(10000);

		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();

		int resultcount = companyResultsPage.getExactCompanyResultCount();
		Assert.assertTrue(companyResultsPage.Check_ResultCountLessThan_10000(resultcount));

		companyResultsPage.clickPowerRank();
		String hoverTextPowerRanking = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextPowerRanking, "Remove Power Ranking", "Unexpected Power Rank Button Hover text!");
		// Click Power Rank button in order to remove Power Ranking
		companyResultsPage.clickPowerRank();

		// Verify Page is not Power Ranked now
		String hoverTextNonPowerRanking2 = companyResultsPage.getPowerRankButtonHoverText();
		Assert.assertEquals(hoverTextNonPowerRanking2, "Power Rank Results",
				"Unexpected Power Rank Button Hover text!");

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		/*projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());*/
		
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2845(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		/*HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);*/
		
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNProjectProfileDataProvider.class, dataProvider = "PlatinumAdminBoth", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc2814(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword)
			throws InterruptedException {
		/*HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.byPassUser(ChildAdminEmail);
		// End of precondition
		homePage.clickOnSignOutButton();
		loginAs(ChildAdminEmail, ChildAdminPassword);*/
		
		HomePage homePage = loginAs(ChildAdminEmail, ChildAdminPassword); 
		
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGeographyFilter();
		Assert.assertTrue(projectResultsPage.checkGeographyFilter());
		projectResultsPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultsPage.isACTION_STAGE_CODE_FilterDisplayed());
		Assert.assertTrue(projectResultsPage.checkactionStageCodeFacetListSize());
		
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanythatcontainsProjectsLink();
		
		homePage.clickOnSignOutButton();
	}

}

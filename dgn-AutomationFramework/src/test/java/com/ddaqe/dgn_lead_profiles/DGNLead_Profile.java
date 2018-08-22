package com.ddaqe.dgn_lead_profiles;

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
import com.ddaqe.pages.AdminUsersPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ManageProfilesPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;

@Listeners(TestListener.class)
public class DGNLead_Profile extends BaseTest {
	static Logger log = Logger.getLogger(DGNLead_Profile.class);

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
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of title and breadcrumb in Specalerts Profile page. (TC13350)")
	public void tc1060(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.isBreadCrumbCorrect("My Account - Profiles"),
				"Failed to display the expected bread crumb");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of breadcrumb of edit SpecAlerts profile page. (TC13353)")
	public void tc1063(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnspecEditLink();
		Assert.assertFalse(manageProfilesPage.isBreadCrumbEditspec(), "breadCrumb is not Display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of name field in add profile page. (TC13356)")
	public void tc1065(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(), "Error message is not display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Validation of name of profile for SpecAlerts profiles (TC13359)")
	public void tc1067(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		if (manageProfilesPage.getExistingProfileNameList().contains("TC1067_Spec_Search")) {
			manageProfilesPage.deleteSavedProfile("TC1067_Spec_Search");
		}
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC1067_Spec_Search");
		manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription");
		manageProfilesPage.clickOnSpeccheckBox();
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.getExistingProfileNameList().contains("TC1067_Spec_Search"),
				"Failed to display the profile name");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Validation of description of profile for SpecAlerts profiles. (TC13360)")
	public void tc1068(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC1068_Spec_Search");
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(), "Error is not displayed");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of sources  in specALerts tab of specALerts  profile page. (TC13373)")
	public void tc1075(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		List<String> specAlertProgramCheckBox = manageProfilesPage.getSpecAlertProgram();
		Assert.assertTrue(!specAlertProgramCheckBox.isEmpty(),
				"All the filters are not present in Company Project page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of affect on profile when cancel is clicked. (TC13374)")
	public void tc1076(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC1076_Spec_Search");
		manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
		manageProfilesPage.clickOnSpecSelectAll();
		manageProfilesPage.clickOnCancelButton();
		Assert.assertTrue(manageProfilesPage.liscenseNumberDisplay(),
				"Check wheather driver is navigate to profile page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of adding of a SpecAlerts profile when a profile with the same name exists. (TC13376)")
	public void tc1078(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC_1076_Spec_Search");
		manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
		manageProfilesPage.clickOnSpeccheckBox();
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(), "Error Message is not Displayed for Description");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of error message when save is clicked and no SpecAlerts program is selected (TC13377)")
	public void tc1079(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC_1079_Spec_Search");
		manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(), "Error Message is not Displayed for Description");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of adding of a SpecAlerts profile when a profile with the same name does not exists. (TC13378)")
	public void tc1080(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		if (manageProfilesPage.getExistingProfileNameList().contains("TC_1080_Spec_Search")) {
			manageProfilesPage.deleteSavedProfile("TC_1080_Spec_Search");
		}
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.enterTextInProfileName("TC_1080_Spec_Search");
		manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
		manageProfilesPage.clickOnSpeccheckBox();
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(manageProfilesPage.getExistingProfileNameList().contains("TC_1080_Spec_Search"),
				"Failed to display the profile name");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of editing of a SpecAlerts profile when name has not been changed. (TC13379)")
	public void tc1081(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnEditLink();
		manageProfilesPage.clickOnSpeccheckBox();
		manageProfilesPage.clickOnSpecSaveBtn();
		manageProfilesPage.isAddProfileButtonDisplayed();
		manageProfilesPage.checkAddProfileButtonDisplay();
		Assert.assertTrue(manageProfilesPage.checkEditedProfileNamedisplayed(), "change in profile name");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of editing of a SpecAlerts profile when name is changed and another profile exists with the same name. (TC13380)")
	public void tc1082(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		List<String> existingProfileNameList = manageProfilesPage.getExistingProfileNameList();
		if (existingProfileNameList.size() >= 2) {
			manageProfilesPage.editSavedProfile(existingProfileNameList.get(0));
			manageProfilesPage.enterTextInProfileName(existingProfileNameList.get(1));
			manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
			manageProfilesPage.clickOnSpeccheckBox();
			manageProfilesPage.clickOnSaveButton();
			Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(),
					"Error Message is not Displayed for Description");
		} else if (existingProfileNameList.size() == 1) {
			manageProfilesPage.clickOnAddProfile();
			String newProfileName = String.valueOf(new Date().getTime());
			manageProfilesPage.enterTextInProfileName(newProfileName);
			manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
			manageProfilesPage.clickOnSpeccheckBox();
			manageProfilesPage.clickOnSaveButton();
			manageProfilesPage.editSavedProfile(existingProfileNameList.get(0));
			manageProfilesPage.enterTextInProfileName(newProfileName);
			manageProfilesPage.enterTextInProfileDescription("dgnLeadProfileSpecDescription020170106110104");
			manageProfilesPage.clickOnSpeccheckBox();
			manageProfilesPage.clickOnSaveButton();
			Assert.assertTrue(manageProfilesPage.isErrorMessageDisplay(),
					"Error Message is not Displayed for Description");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of editing of a SpecAlerts profile when name is changed and another profile does not exists with the same name. (TC13381)")
	public void tc1083(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		if (manageProfilesPage.getExistingProfileNameList().contains("TC_1083_Spec_Search")) {
			manageProfilesPage.editSavedProfile("TC_1083_Spec_Search");
			manageProfilesPage.enterTextInProfileName("TC_1083_Spec_Search_updated");
			manageProfilesPage.clickOnSpeccheckBox();
			manageProfilesPage.clickOnSpecSaveBtn();
		}
		Assert.assertTrue(manageProfilesPage.getExistingProfileNameList().contains("TC_1083_Spec_Search_updated"),
				"change in profile name");
		if (manageProfilesPage.getExistingProfileNameList().contains("TC_1083_Spec_Search_updated")) {
			manageProfilesPage.editSavedProfile("TC_1083_Spec_Search_updated");
			manageProfilesPage.enterTextInProfileName("TC_1083_Spec_Search");
			manageProfilesPage.clickOnSpeccheckBox();
			manageProfilesPage.clickOnSpecSaveBtn();
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "View  'Manage Profile ' Page to verify the implementation of Hide lead profile feature (TC13434)")
	public void tc1084(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.isBreadCrumbCorrect("My Account - Profiles"),
				"Failed to display the expected bread crumb");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of Add Profile page for SpecAlerts/Lead profiles page (TC13775)")
	public void tc1085(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		Assert.assertTrue(manageProfilesPage.checkAddProfileButtonDisplay(), "Add profile button is not Display");
		manageProfilesPage.clickOnAddProfile();
		Assert.assertTrue(manageProfilesPage.checkProfileNameTextBoxDisplay(), "Profile name text box is not Display");
		Assert.assertTrue(manageProfilesPage.checkDescriptionTextboxDisplay(), "Description text box is not Display");
		Assert.assertTrue(manageProfilesPage.checkSaveButtonDisplay(), "Save button is not Display");
		Assert.assertTrue(manageProfilesPage.checkCancelButtonDispaly(), "Cancel Button is not Display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "value in drop list when a user is assigned a SpecAlerts ( lead ) profile (TC13802)")
	public void tc1087(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = myAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.marketProfileUserDisplay(), "marketing profile user is not display");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of add/edit page in Profile page for SpecAlerts profile when edit link is clicked. (TC13880)")
	public void tc1105(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnEditSpecBtn();
		Assert.assertFalse(manageProfilesPage.isBreadCrumbEditspec(), "breadCrumb is not Display");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of list of specAlert programmes in header when no lead profile is applied to the user. (TC13899)")
	public void tc1106(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnEditSpecBtn();
		List<String> specAlertProgramCheckBox = manageProfilesPage.getSpecAlertProgram();
		Assert.assertTrue(!specAlertProgramCheckBox.isEmpty(),
				"All the filters are not present in Company Project page.");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification that SpecAlerts  profile should not be applied to the summary area on the projects results view page. (TC13905)")
	public void tc1112(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("500");
		List<String> specAlertProgramCheck = projectResultsPage.getProjectTitleWithspec();
		Assert.assertTrue(!specAlertProgramCheck.isEmpty(),
				"All spec Alerts associate with project should not display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification that SpecAlerts profile should not be applied to the specAlerts listed in the DR page. (TC13906)")
	public void tc1113(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();

		Assert.assertTrue(projectSpecsPage.specDisplay(), "Spec alert is not Display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of customize SpecAlerts dashboard when  the user is not assigned a SpecAlerts profile (TC14051)")
	public void tc1114(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		homePage.clickOnProjectsTab();
		List<String> allProgramDisplay = homePage.dashboardProgramDisplay();
		Assert.assertTrue(!allProgramDisplay.isEmpty(), "List of programm On DashBorad is empty");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of customize SpecAlerts dashboard when the user is assigned a SpecAlerts profile but specifies one or more SpecAlerts programs (TC14053)")
	public void tc1116(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		List<String> allSpecProgramDisplay = homePage.specAlertProgrammDisplay();
		Assert.assertTrue(!allSpecProgramDisplay.isEmpty(), "List of programm On DashBorad is empty");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of  Changing label  on Users view from 'Lead profile' to 'Marketing Profile (TC14108)")
	public void tc1120(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = myAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.ckeckMarketingProfileDisplay(), "marketing profile button is not display");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verify the removal of  the verb 'Manage' from  the links area on the left side of the views within the Admin Tools section (TC14115)")
	public void tc1123(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.isBreadCrumbCorrect("My Account - Profiles"),
				"manage profile does not change to profile");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of removal of  the verb 'Manage' from 'Manage Profiles' view (TC14117)")
	public void tc1125(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.isBreadCrumbCorrect("My Account - Profiles"),
				"Failed to display the expected bread crumb");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNLead_ProfileDataProvider.class, dataProvider = "Lead_ProfileCommonDataProvider", description = "Verification of Removal of  the verb Manage from the Add/Edit Profile view (for both Project and Marketing/SpecAlerts  profiles) (TC14119)")
	public void tc1127(String username, String password) {
		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.clickOnSpecAlert();
		manageProfilesPage.clickOnAddProfile();
		Assert.assertFalse(manageProfilesPage.isBreadCrumbCorrect("My Account - Profiles"),
				"Failed to display the expected bread crumb");
	}

}

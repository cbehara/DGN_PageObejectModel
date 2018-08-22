package com.ddaqe.dgn_customer_account_tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.ActivationPage;
import com.ddaqe.pages.AdminUsersPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LoginPage;
import com.ddaqe.pages.ManageProfilesPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.MyPurchasesPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.ShareUsers;
import com.ddaqe.pages.TrackingListPopupDialog;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)

public class DGNCustomerAccountTools extends DGNCustomerAccountToolsDataSet {

	static Logger log = Logger.getLogger(DGNCustomerAccountTools.class);

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
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1140(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
		homePage.clickOnSignOutButton();

		// Cleanup activity for test data
		deleteProjectTrackingListPlusUser(emailAddress, password);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies - Edit tracking list pop-up based on Role (TC10556) - Plus.")
	public void tc1276(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"All the element are not present on edit tracking list dialog for PLUS User.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Companies Saved Searches - Navigate to Edit from dropdown- Plus.")
	public void tc1168(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for PLUS User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Projects Saved Searches - Navigate to Edit from dropdown - Plus.")
	public void tc1167(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for PLUS User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "[My Account - My Saved Searches] Delete a saved search")
	public void TC1181(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForPlusUser(emailAddress, password);
		String saveSearchSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();
		saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		Assert.assertTrue(savedSearchesPage.checkDeleteSaveSearchComfirmDialogPresent(),
				"Are you sure you want to delete? confirmation dialog is not present.");
		savedSearchesPage.clickOnCancelBtnDeleteSaveSearchOnly();
		Assert.assertTrue(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is not done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "Verification of Add user preference to allow user to turn the keyword highlighting feature on/off for Plus licence ")
	public void tc1338(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount myAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();

		Assert.assertTrue(myPreferencesPage.isDocumentTabDisplay(), "Document tab for my preference is not displayed.");
		Assert.assertFalse(myPreferencesPage.isDocumentCheckBoxSelected(), "Document check is selected by default");
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();

		TrackingLists trackingLists = myPreferencesPage.clickOnMyTrackingLists();
		MyPreferencesPage myPreferencesPage_1 = trackingLists.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage_1.isDocumentCheckBoxSelected(),
				"Document check is not selected by after save by user");
		myPreferencesPage.clickOnDocumentFilterCheckBox();
		myPreferencesPage.clickOnDocumentFilterCheckBoxSaveButton();

		TrackingLists trackingLists_1 = myPreferencesPage.clickOnMyTrackingLists();
		MyPreferencesPage myPreferencesPage_2 = trackingLists_1.clickOnMyPreferences();
		Assert.assertFalse(myPreferencesPage_2.isDocumentCheckBoxSelected(),
				"Document check is still selected when user had uncheck it.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "Verify My Preferences link is added to My Account for user's on a Plus license")
	public void TC1337(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.isBreadcrumbAndMyAccountMyPreferencesDisplayed(),
				"My-Account---My-Preferences");
		Assert.assertTrue(myPreferencesPage.myPreferencesOptionDisplayed(), "My Preferences option");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up on Project tab- PLUS user.")
	public void TC1144_Plus(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.isMyCompaniesTrackingNameDisplayed(),
				"My Companies tracking name is not present.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(), "Tracking name are not sorted.");

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog contain name textfied with Save and Cancel button.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS user.")
	public void TC1142_Plus(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());

		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.isMyCompaniesTrackingNameDisplayed());
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog contain name textfied with Save and Cancel button.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP3", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link (TC9986)")
	public void tc1194(String emailAddress, String password) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(),
				"My Saved Searches option is not present under My Account tool section.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(),
				"My Tracking Lists option is not present under My Account tool section.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verify that 'Tracking List' and 'Saved Searches' are displayed as options")
	public void TC1134(String emailAddress, String password) {
		// createPrivateProjectTrackingListNonAdmin(emailAddress, password);

		HomePage HomePage = loginAs(emailAddress, password);
		HomePage.clickOnMyAccountLink();
		MyAccount MyAccount = HomePage.clickOnMyTrackingListsMenuLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verify that 'Tracking List' and 'Saved Searches' are displayed as options")
	public void TC1135(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		MyAccount myAccount = homePage.clickOnMyTrackingListsMenuLink();
		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();
		Assert.assertTrue(trackingLists.ismyTrackingListsMenuLinkDisplayed(), "My Tracking Lists");
		Assert.assertTrue(trackingLists.ismySavedSearchesMenuLinkDisplayed(), "My SavedSearches");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP2", description = "[My Account - My Saved Searches] List saved searches  - user view")
	public void TC1160(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();
		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		Assert.assertTrue(savedSearchesPage.ismyTrackingListsMenuLinkDisplayed(), "My Tracking Lists");
		Assert.assertTrue(savedSearchesPage.ismySavedSearchesMenuLinkDisplayed(), "My SavedSearches");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP2", description = "[My Account - My Tracking Lists] Create My Tracking Lists page")
	public void TC1185(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP2", description = "[My Account - My Tracking Lists - Create My Tracking Lists page - My Account Link - My Tracking List (left section)")
	public void TC1186(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();

		Assert.assertTrue(trackingLists.isBreadcrumbMyTrackingDisplayed(), "My-Account---My-Tracking-Lists");
		Assert.assertTrue(trackingLists.ismyTrackingListsMenuLinkDisplayed(), "My Tracking Lists");
		Assert.assertTrue(trackingLists.ismySavedSearchesMenuLinkDisplayed(), "My SavedSearches");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Saved Searches] Create My Saved Searches page")
	public void TC1194(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		HomePage.clickOnMyAccountLink();
		MyAccount MyAccount = HomePage.clickOnMyTrackingListsMenuLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verification of message displayed for batch download page when there is no batch download for project batch download.")
	public void TC1161(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLink();
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "[My Account - My Tracking Lists] Share a tracking list (admin only)")
	public void TC1233(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		MyAccount myAccount = homePage.clickOnMyTrackingListsMenuLink();
		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();
		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(), "Project tab");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company Tab");
		Assert.assertTrue(trackingLists.isShareLinkPresent(), "Share link for PLAT user");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "[My Account - My Tracking Lists] Share a tracking list (admin only)")
	public void TC1236(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();

		MyAccount myAccount = homePage.clickOnMyTrackingListsMenuLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();
		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(), "Project tab");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company Tab");
		Assert.assertTrue(trackingLists.isShareLinkPresent(), "Share link for PLAT user");
		ShareUsers shareUsers = trackingLists.clickShareLink();

		Assert.assertTrue(shareUsers.isShareDropDownDisplayed(), "Share Drop Down");
		Assert.assertTrue(shareUsers.filterDropdownValue(), "The First value of DropDown ");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Tracking Lists] Delete a tracking list.")
	public void TC1153(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String saveSearchSizeBeforeDeletion = "";
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();

		saveSearchSizeBeforeDeletion = trackingLists.getTrackingNameSize();
		Assert.assertTrue(trackingLists.deleteTrackingListComfirmDialogPresent(),
				"Are you sure you want to delete? delete tracking list dialog is not present");
		trackingLists.clickOnCancelButtonForDeleteDialogOnly();
		Assert.assertTrue(trackingLists.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "General UI Cleanup - My Account header bar on the left side of My Account pages (TC11105)")
	public void TC1313(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		Assert.assertTrue(myAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(myAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();
		Assert.assertTrue(trackingLists.checkColorofElementsTrackingList(), "Color of elements");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "[My Account - My Tracking Lists] Make a tracking list Public/Private (admin only)")
	public void TC1274(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		TrackingLists trackingLists = myAccount.clickOnMyTrackingListsNavLink();
		Assert.assertTrue(trackingLists.isShareLinkPresent(), "Share link is not present");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "On the My Purchases page, the order date format should be mm/dd/yyyy")
	public void TC1316(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();
		if (!"You do not have any purchase history details".equalsIgnoreCase(myPurchasesPage.getErrorMessage())) {
			Assert.assertTrue(myPurchasesPage.appendDateTimeStamp(), "Order date");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Validate the label for the SpecAlerts filter")
	public void TC1336(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnCompanyFilter();
		Assert.assertTrue(myPreferencesPage.companyFilterCheckBoxisDisplayed(),
				"View and enable filtering by SpecAlerts");
		myPreferencesPage.checkCompanyFilterCheckBox();
		Assert.assertTrue(myPreferencesPage.companyFilterCheckBoxisEnabled(),
				"View and enable filtering by SpecAlerts is Unchecked");
		myPreferencesPage.checkCompanyFilterCheckBox();
		Assert.assertFalse(myPreferencesPage.companyFilterCheckBoxisDisabled(),
				"View and enable filtering by SpecAlerts is Checked");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verification of message displayed for batch download page when there is no batch download for project batch download.")
	public void TC1317(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = MyAccount.clickOnDownloadNavLink();
		Assert.assertEquals(myDownloadsPage.noFileMessage(), "You do not have any batch downloads for projects");
		Assert.assertTrue(myDownloadsPage.isNoDownloadMessageDisplayed(), "No batch File to Download");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1144PlatAdminUser(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");
		Assert.assertTrue(trackingListPopupDialog.isTrackingListTypeDisplayed(),
				"Tracking List type Drop Down is not displayed");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1144PlatNonAdminUser(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingList = MyAccount.clickOnMyTrackingLists();

		TrackingListPopupDialog trackingListPopupDialog = trackingList.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1140PlatAdminUser(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");

		Assert.assertTrue(trackingListPopupDialog.isTrackingListTypeDisplayed(),
				"Tracking List type Drop Down is not displayed.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1141PlatAdminUser(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");
		Assert.assertTrue(trackingListPopupDialog.isTrackingListTypeDisplayed(),
				"Tracking List type Drop Down is not displayed.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verify[My Account - My Tracking Lists] Changes to Modal Pop-up - PLUS vs. PLAT (Admin vs. Non-Admin)")
	public void TC1141PlusUser(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit text box does not have name text field, Save and Cancel option");
		Assert.assertTrue(trackingListPopupDialog.isTrackingListTypeDisplayed(),
				"Tracking List type Drop Down is not displayed");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP4", description = "Verifying Activate My Account page")
	public void TC1129(String emailAddress, String password) {
		// No prerequisite required
		LoginPage loginPage = new LoginPage(getBrowser().getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);

		Assert.assertTrue(activationPage.isLicenseInputPageDisplayed(), "License Input page");
		Assert.assertTrue(activationPage.isWelcomeTextdisplayed(), "License Page welcome text");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP5", description = "Verifying Activate My Account page (license management) - Scenario 3 (TC1507)")
	public void TC1131(String emailAddress, String password, String License_Number) {
		// No prerequisite required
		LoginPage loginPage = new LoginPage(getBrowser().getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);
		Assert.assertTrue(activationPage.isLicenseInputPageDisplayed(), "Welcome text of license input page");
		Assert.assertTrue(activationPage.isWelcomeTextdisplayed(), "Welcome text of license header");
		activationPage.clickOnLicenseField();

		activationPage.enterValueInLicenseField(License_Number);
		activationPage.clickOnSubmitButton();
		Assert.assertTrue(activationPage.isExpiredLicenseDisplayed(), "The expired license message");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP6", description = "Verifying Activate My Account page (license management) - Scenario 2 (TC1506)")
	public void TC1130(String emailAddress, String password, String License_Number) {
		// No prerequisite required
		LoginPage loginPage = new LoginPage(getBrowser().getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);
		Assert.assertTrue(activationPage.isLicenseInputPageDisplayed(), "Welcome text of license input page");
		Assert.assertTrue(activationPage.isWelcomeTextdisplayed(), "Welcome text of license header");
		activationPage.clickOnLicenseField();

		activationPage.enterValueInLicenseField(License_Number);
		activationPage.clickOnSubmitButton();
		Assert.assertTrue(activationPage.isInvalidLicenseDisplayed(), "The Invalid license message");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP4", description = "Verifying Activate My Account page (license management) - Scenario 5 (TC1509)")
	public void TC1132(String emailAddress, String password) {
		// No prerequisite required
		LoginPage loginPage = new LoginPage(getBrowser().getDriver());

		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);
		Assert.assertTrue(activationPage.isLicenseInputPageDisplayed(), "Welcome text of license input page");
		Assert.assertTrue(activationPage.isWelcomeTextdisplayed(), "Welcome text of license header");
		activationPage.clickOnSignOut();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP8", description = "Verification of Display of  the owner  name in the  public project saved searches")
	public void TC1324(String emailAddress, String password, String OwnerName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();

		Assert.assertTrue(savedSearchesPage.verifyOwnerNameFormat(OwnerName), "OwnerName Format");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP8", description = "Verification of display of the owner of public company saved searches")
	public void TC1325(String emailAddress, String password, String OwnerName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();
		Assert.assertTrue(savedSearchesPage.verifyOwnerNameFormat(OwnerName), "OwnerName Format");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP9", description = "Verification  of display of the owner of shared  company saved searches")
	public void TC1323(String emailAddress, String password, String OwnerName) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();
		savedSearchesPage.clickOnCompanyTab();
		Assert.assertTrue(savedSearchesPage.verifyOwnerNameFormatCompany(OwnerName), "Owner name Format ");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verifying My Preferences page from  My Account menu in the header")
	public void TC1326(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.checkFontSize(), "11px");
		Assert.assertTrue(myPreferencesPage.checkColorofElements(), "#00000");
		Assert.assertTrue(myPreferencesPage.checkPreferencesColor(),
				"Preferance string is not displayed with RED color.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "Verifying My Preferences page from Account tools section on the left side of My Account pages.")
	public void TC1327(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);

		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		Assert.assertTrue(myPreferencesPage.checkFontSize(), "11px");
		Assert.assertTrue(myPreferencesPage.checkColorofElements(), "#00000");
		Assert.assertTrue(myPreferencesPage.checkPreferencesColor(),
				"Preferance string is not displayed with RED color.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verification of  Include the results per page selector at the top of My Account - My Tracking Lists view ")
	public void TC1319(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();

		Assert.assertTrue(trackingLists.isTrackingListResultDropDownDisplayed(), "result dropdown list");
		Assert.assertTrue(trackingLists.isProjectBarDisplayed(), "Project bar");
		Assert.assertTrue(trackingLists.checkColorOfProjectBar(), "#102C42");
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.isTrackingListResultDropDownDisplayed(), "Company result dropdown list");
		Assert.assertTrue(trackingLists.isProjectBarDisplayed(), "Project bar");
		Assert.assertTrue(trackingLists.checkColorOfProjectBar(), "#102C42");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verification of  Include the results per page selector at the top of My Downloads view ")
	public void TC1320(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLink();
		Assert.assertTrue(savedSearchesPage.isSavedSearchResultDropDownDisplayed(), "result dropdown list");
		Assert.assertTrue(savedSearchesPage.isProjectBarDisplayed(), "Project bar");
		Assert.assertTrue(savedSearchesPage.checkColorOfProjectBar(), "#102C42");
		savedSearchesPage.clickOnCompanyTab();
		Assert.assertTrue(savedSearchesPage.isSavedSearchCompanyResultDropDownDisplayed(),
				"Company result dropdown list");
		Assert.assertTrue(savedSearchesPage.isProjectBarDisplayed(), "Project bar");
		Assert.assertTrue(savedSearchesPage.checkColorOfProjectBar(), "#102C42");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "Verification of  Include the results per page selector at the top of My Account - My Saved Searches view  ")
	public void TC1321(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = MyAccount.clickOnDownloadNavLink();
		Assert.assertTrue(myDownloadsPage.isResultDropDowndisplayed(), "result dropdown list");
		Assert.assertTrue(myDownloadsPage.isProjectBarDisplayed(), "Project bar");
		Assert.assertTrue(myDownloadsPage.checkColorOfProjectBar(), "#102C42");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Project Tracking Lists - Pagination.")
	public void TC1133(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isPaginationDetailAtTop());
		Assert.assertTrue(trackingLists.isPaginationDetailAtBotton());
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection());
		Assert.assertTrue(trackingLists.checkResultPerPageOption());
		Assert.assertTrue(trackingLists.checkTypeDropdownOption());
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1136", description = "[My Account - My Tracking Lists] List tracking lists  - user view.")
	public void tc1136(String emailAddress, String password, String myTrackingListLable, String expBreadCrumb) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		Assert.assertTrue(MyAccount.checkMyTrackingListsPositionForPlusUser(myTrackingListLable));

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());

		Assert.assertTrue(trackingLists.checkTrackingListBreadCrumb(expBreadCrumb));

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List (right section) - Project Tracking Lists.")
	public void TC1137(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());
		Assert.assertTrue(trackingLists.checkTypeDropdownOption());
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());

		ProjectResultsPage projResultPage = trackingLists.clickOnMyProjectsTrackingList();
		Assert.assertTrue(projResultPage.checkMyProjectTrackingListResultDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List (right section) - Project Tracking Lists.")
	public void TC1138(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());
		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.checkTypeDropdownOption());
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());

		ProjectResultsPage projResultPage = trackingLists.clickMyCompaniesTrackingList();
		Assert.assertTrue(projResultPage.checkMyCompaniesTrackingListResultDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Project Tracking Lists - Pagination.")
	public void TC1139(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.isPaginationDetailAtTop());
		Assert.assertTrue(trackingLists.isPaginationDetailAtBotton());
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection());
		Assert.assertTrue(trackingLists.checkResultPerPageOption());
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(), "Type header option are different than expected.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up - Platinium user.")
	public void TC1142_Platinium(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());

		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.isMyCompaniesTrackingNameDisplayed());
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList());

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog contain name textfied with Save and Cancel button.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up - Platinium user.")
	public void TC1143(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed());
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed());

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		Assert.assertTrue(trackingLists.isEditLinkPresent());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up on Project tab- Platinium user.")
	public void TC1144_Platinium(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed());
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed());
		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.isMyCompaniesTrackingNameDisplayed(),
				"My Companies tracking name is not present.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(), "Tracking name are not sorted.");

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog contain name textfied with Save and Cancel button.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1146", description = "My Account - My Tracking Lists - Editing a Project Tracking List - Cancelling edit operation.")
	public void TC1146(String emailAddress, String password, String editTrackingName) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTrackingName),
				"Edit name is present in the list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[My Account - My Tracking Lists] Changes to Modal Pop-up on Project tab- Platinium user.")
	public void TC1145(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		projectResultsPage.clickOnMySearchesDropDown();

		TrackingLists trackingLists = projectResultsPage.clickOnTrackingListFromMySearchesDropDown();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(), "Prjoect tab is not present on My tracking list page");
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog contain name textfied with Save and Cancel button.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(), "Type header option are different than expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1147", description = "My Account - My Tracking Lists - Editing a Project Tracking List - Saving changes and verify updation  (from dropdown).")
	public void tc1147(String emailAddress, String password, String newtrackingName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		newtrackingName = newtrackingName + trackingLists.getTimeStamp();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(newtrackingName);
		TrackingLists edtiTrackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		Assert.assertTrue(edtiTrackingLists.checkEditTrackingNameReplace(newtrackingName),
				"Edit name " + newtrackingName + " not save in My Tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[Miscellaneous] General UI clean up - part 3")
	public void TC1314(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount myAccount = HomePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnDownloadNavLink();
		Assert.assertTrue(myDownloadsPage.checkBreadCrumbProject(), "My-Account---My-Downloads---Project");
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.checkBreadCrumbCompany(), "My-Account---My-Downloads---Company");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "[Miscellaneous] General UI clean up - part 3")
	public void TC1315(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount myAccount = HomePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnDownloadNavLink();
		Assert.assertTrue(myDownloadsPage.checkBreadCrumbProject(), "My-Account---My-Downloads---Project");
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.checkBreadCrumbCompany(), "My-Account---My-Downloads---Company");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1147_Platinium", description = "My Account - My Tracking Lists - Editing a Project Tracking List - Saving changes and verify updation for Platinium admin (from dropdown).")
	public void tc1147_Platinium_Admin(String emailAddress, String password, String newtrackingName, String typeFilter,
			String editTypeFilter) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		newtrackingName = newtrackingName + trackingLists.getTimeStamp();
		trackingLists.selectTypeListOption(typeFilter);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(newtrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilter);
		TrackingLists edtiTrackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		edtiTrackingLists.selectTypeListOption(editTypeFilter);
		Assert.assertTrue(edtiTrackingLists.checkEditTrackingNameReplace(newtrackingName),
				"Edit name " + newtrackingName + " not save in My Tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1147", description = "My Account - My Tracking Lists - Editing a Company Tracking List - Edit tracking list pop-up based on Role (from dropdown).")
	public void tc1149(String emailAddress, String password, String newtrackingName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		newtrackingName = newtrackingName + trackingLists.getTimeStamp();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(newtrackingName);
		TrackingLists edtiTrackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		Assert.assertTrue(edtiTrackingLists.checkEditTrackingNameReplace(newtrackingName),
				"Edit name " + newtrackingName + " not save in My Tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1146", description = "My Account - My Tracking Lists - Editing a Company Tracking List - Cancelling edit operation  (from dropdown).")
	public void TC1150(String emailAddress, String password, String editTrackingName) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTrackingName),
				"Edit name is present in the list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1147", description = "My Account - My Tracking Lists - Editing a Company Tracking List - Saving changes and verify updation  (from dropdown).")
	public void tc1151(String emailAddress, String password, String newtrackingName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String typeFilter = "Private";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		newtrackingName = newtrackingName + trackingLists.getTimeStamp();
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilter);

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(newtrackingName);
		TrackingLists edtiTrackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();
		edtiTrackingLists.selectTypeListOption(typeFilter);
		Assert.assertTrue(edtiTrackingLists.checkEditTrackingNameReplace(newtrackingName),
				"Edit name " + newtrackingName + " not save in My Tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1151_Platinium", description = "My Account - My Tracking Lists - Editing a Company Tracking List - Saving changes and verify updation Platinium Admin user (from dropdown).")
	public void tc1151_Platinium_Admin(String emailAddress, String password, String newtrackingName,
			String typeFilter) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		newtrackingName = newtrackingName + trackingLists.getTimeStamp();
		trackingLists.clickOnCompanyTabForMyTrackingLists();

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.editTrackingNameSetText(newtrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(typeFilter);
		TrackingLists edtiTrackingLists = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		Assert.assertTrue(edtiTrackingLists.checkEditTrackingNameReplace(newtrackingName),
				"Edit name " + newtrackingName + " not save in My Tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Deleting a Project Tracking List - Saving changes and verify updation  (from dropdown)r.")
	public void tc1154(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String tranckingNameSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		tranckingNameSizeBeforeDeletion = trackingLists.getTrackingNameSize();
		trackingLists.deleteTrackingList();
		Assert.assertFalse(trackingLists.verifyTrackingNameCountAfterDeletion(tranckingNameSizeBeforeDeletion),
				"Deletion is not successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Deleting a Company Tracking List - Cancelling delete operation  (from dropdown).")
	public void tc1156(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String tranckingNameSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		tranckingNameSizeBeforeDeletion = trackingLists.getTrackingNameSize();
		trackingLists.clickOnCancelButtonForDeleteDialog();
		Assert.assertTrue(trackingLists.verifyTrackingNameCountAfterDeletion(tranckingNameSizeBeforeDeletion),
				"Deletion is successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Deleting a Company Tracking List - Saving changes and verify updation  (from dropdown).")
	public void tc1157(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String tranckingNameSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		tranckingNameSizeBeforeDeletion = trackingLists.getTrackingNameSize();
		trackingLists.deleteTrackingList();
		Assert.assertFalse(trackingLists.verifyTrackingNameCountAfterDeletion(tranckingNameSizeBeforeDeletion),
				"Deletion is not successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1158", description = "My Account - My Tracking Lists - Type Filter (Projects tracking lists).")
	public void tc1158(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project Tab is not present on My Tracking Lists page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company Tab is not present on My Tracking Lists page");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option is different than mention on My Tracking Lists page");

		trackingLists.selectTypeListOption(typeFilterOption);
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.clickOnProjectTabForMyTrackingLists();

		Assert.assertFalse(trackingLists.checkTypeFilterSelectedOption(typeFilterOption),
				"Type filer selection is remember when control switch between different tab on My tracking page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1158", description = "My Account - My Tracking Lists - Type Filter (Companies tracking lists).")
	public void tc1159(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project Tab is not present on My Tracking Lists page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company Tab is not present on My Tracking Lists page");

		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option is different than mention on My Tracking Lists page");

		trackingLists.selectTypeListOption(typeFilterOption);
		trackingLists.clickOnProjectTabForMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertFalse(trackingLists.checkTypeFilterSelectedOption(typeFilterOption),
				"Type filer selection is remember when control switch between different tab on My tracking page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1163", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches (right section) - Project Saved Searches.")
	public void tc1163(String emailAddress, String password, String saveSearchName) throws InterruptedException {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");

		savedSearchesPage.clickOnSpecificProjectSavedSearch(saveSearchName);
		SavedSearchPopUp saveSearchPopUp = HomePage.clickOnSaveSearchButtonProject();
		Assert.assertEquals(saveSearchPopUp.getSavedSearchName(), saveSearchName,
				"Expected save search : " + saveSearchName + " is not open");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1164", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches (right section) - Company Saved Searches.")
	public void tc1164(String emailAddress, String password, String saveSearchName) throws InterruptedException {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");

		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
		savedSearchesPage.selectTypeListOption("Public");
		savedSearchesPage.clickOnSpecificProjectSavedSearch(saveSearchName);
		SavedSearchPopUp saveSearchPopUp = HomePage.clickOnSaveSearchButtonCompany();
		Assert.assertEquals(saveSearchPopUp.getSavedSearchName(), saveSearchName,
				"Expected save search : " + saveSearchName + " is not open");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches - Project Saved Searches - Pagination.")
	public void tc1165(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtBotton(),
				"Pagination is not present at botton on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtTop(),
				"Pagination is not present at top on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkResultPerPageOption(),
				"Option in result per page dropdown are not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches - Company Saved Searches - Pagination.")
	public void tc1166(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtBotton(),
				"Pagination is not present at botton on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtTop(),
				"Pagination is not present at top on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkResultPerPageOption(),
				"Option in result per page dropdown are not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Projects Saved Searches - Navigate to Edit from dropdown - Platinium.")
	public void tc1167_Platinium(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for NON-Platinium User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Projects Saved Searches - Navigate to Edit from dropdown - Platinium Admin.")
	public void tc1167_PlatiniumAdmin(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesPlatiniumAdminEditDialog(),
				"Type filter dropdwon is not pesent on edit dialog for Platinium admin user on save search page.");
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for Platinium Admin User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Companies Saved Searches - Navigate to Edit from dropdown Platinium.")
	public void tc1168_Platinium(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for NON-Platinium User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Saved Searches - Changes to Modal Pop-up - Companies Saved Searches - Navigate to Edit from dropdown - Platinium Admin.")
	public void tc1168_PlatiniumAdmin(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesPlatiniumAdminEditDialog(),
				"Type filter dropdwon is not pesent on edit dialog for Platinium admin user on save search page.");
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog for Platinium Admin User.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Editing a Project Saved Searches - Edit saved searches pop-up based on Role.")
	public void tc1170(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1146", description = "My Account - My Saved Searches - Editing a Project Saved Searches - Cancelling edit operation.")
	public void TC1171(String emailAddress, String password, String editSaveSearchName) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.editSaveSearchNameSetText(editSaveSearchName);
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();

		Assert.assertFalse(savedSearchesPage.checkEditSaveSearch(editSaveSearchName),
				"Edited name" + editSaveSearchName + " is present in the list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1172", description = "My Account - My Saved Searches - Editing a Project Saved Searches - Saving changes and verify updation.")
	public void tc1172(String emailAddress, String password, String editSaveSearch) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String privateTypeFilter = "Private";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		editSaveSearch = editSaveSearch + savedSearchesPage.getTimeStamp();
		savedSearchesPage.selectTypeListOption(privateTypeFilter);
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.editSaveSearchNameSetText(editSaveSearch);
		SavedSearchesPage editSavedSearchesPage = savedSearchPopUp.clickOnSaveButtonEditSaveSearch();

		Assert.assertTrue(editSavedSearchesPage.checkEditSaveSearch(editSaveSearch),
				"Edit name " + editSaveSearch + " not save in Saved Search table.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Editing a Company Saved Searches - Edit saved searches pop-up based on Role.")
	public void tc1174(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkSavedSearchesEditDialog(),
				"All the element are not present on edit save search dialog.");
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1172", description = "My Account - My Saved Searches - Editing a Company Saved Searches - Cancelling edit operation.")
	public void TC1175(String emailAddress, String password, String editSaveSearchName) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.editSaveSearchNameSetText(editSaveSearchName);
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();

		Assert.assertFalse(savedSearchesPage.checkEditSaveSearch(editSaveSearchName),
				"Edited name" + editSaveSearchName + " is present in the list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1172", description = "My Account - My Saved Searches - Editing a Company Saved Searches - Saving changes and verify updation.")
	public void tc1176(String emailAddress, String password, String editSaveSearch) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		editSaveSearch = editSaveSearch + savedSearchesPage.getTimeStamp();
		savedSearchesPage.clickOnCompanyTab();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.editSaveSearchNameSetText(editSaveSearch);
		SavedSearchesPage editSavedSearchesPage = savedSearchPopUp.clickOnSaveButtonEditSaveSearch();

		Assert.assertTrue(editSavedSearchesPage.checkEditSaveSearch(editSaveSearch),
				"Edit name " + editSaveSearch + " not save in Saved Search table.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "[My Account - My Saved Searches] Delete a saved search")
	public void tc1169(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();

		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption(optionToSelect);
		Assert.assertFalse(savedSearchesPage.isEditLinkPresent(),
				"Edit Link is present for the saved search which is not created by owner.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "My Account - My Saved Searches - Editing a Company Saved Searches - Edit link displayed on condition (from dropdown) (TC9933)")
	public void tc1173(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();

		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.selectTypeListOption(optionToSelect);
		Assert.assertFalse(savedSearchesPage.isEditLinkPresent(),
				"Edit Link is present for the saved search which is not created by owner");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "My Account - My Saved Searches - Deleting a Project Saved Searches - Delete link displayed on condition (from dropdown) (TC9941)")
	public void tc1177(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(optionToSelect);
		Assert.assertFalse(savedSearchesPage.isDeleteLinkPresent(),
				"Delete Link is present for the saved search which is not created by owner.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Deleting a Project Saved Searches - Cancelling delete operation .")
	public void tc1178(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String saveSearchSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		savedSearchesPage.clickOnCancelBtnDeleteSaveSearch();
		Assert.assertTrue(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Deleting a Project Saved Searches - Saving changes and verify updation.")
	public void tc1179(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String saveSearchSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		savedSearchesPage.clickOnDeleteSaveSearch();
		Assert.assertFalse(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is not successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "My Account - My Saved Searches - Deleting a Company Saved Searches - Delete link displayed on condition (from dropdown) (TC9947)")
	public void tc1180(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists");

		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.selectTypeListOption(optionToSelect);
		Assert.assertFalse(savedSearchesPage.isDeleteLinkPresent(),
				"Delete Link is present for the saved search which is not created by owner.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Deleting a Company Saved Searches - Saving changes and verify updation.")
	public void tc1182(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		String saveSearchSizeBeforeDeletion = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		saveSearchSizeBeforeDeletion = savedSearchesPage.getTrackingNameSize();
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnDeleteSaveSearch();
		Assert.assertFalse(savedSearchesPage.verifyTrackingNameCountAfterDeletion(saveSearchSizeBeforeDeletion),
				"Deletion is not successfully done.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1158", description = "My Account - My Saved Searches - Type Filter (Projects saved searches).")
	public void tc1183(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project Tab is not present on Save search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company Tab is not present on Save search page");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type dropdown option is different than mention on Save search page");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.clickOnProjectLink();

		Assert.assertTrue(savedSearchesPage.checkTypeFilterSelectedOption(typeFilterOption),
				"Type filer selection is not remember when control switch between different tab on save search page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1158", description = "My Account - My Saved Searches - Type Filter (Companies saved searches).")
	public void tc1184(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project Tab is not present on Save search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company Tab is not present on Save search page");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type dropdown option is different than mention on Save search page");

		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.selectTypeListOption(typeFilterOption);
		savedSearchesPage.clickOnProjectLink();
		savedSearchesPage.clickOnCompanyTab();

		Assert.assertTrue(savedSearchesPage.checkTypeFilterSelectedOption(typeFilterOption),
				"Type filer selection is not remember when control switch between different tab on save search page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Create My Tracking Lists page - My Account Link - My Tracking List (right section) - Project Tracking Lists.")
	public void TC1187(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();

		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isPaginationDetailAtTop(),
				"Pagination is not present on Top on My Tracking list page");
		Assert.assertTrue(trackingLists.isPaginationDetailAtBotton(),
				"Pagination is not present on bottom on My Tracking list page");
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default type filter selection is not set to ALL on My Tracking list page");
		Assert.assertTrue(trackingLists.checkResultPerPageOption(),
				"Result per page option is not as expected on My Tracking list page");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type filter option is not as expected My Tracking list page");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking name list are not in ascending order on My Tracking list page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Create My Tracking Lists page - My Account Link - My Tracking List (right section) - Company Tracking Lists.")
	public void TC1188(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.isPaginationDetailAtTop(),
				"Pagination is not present on Top on My Tracking list page");
		Assert.assertTrue(trackingLists.isPaginationDetailAtBotton(),
				"Pagination is not present on bottom on My Tracking list page");
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default type filter selection is not set to ALL on My Tracking list page");
		Assert.assertTrue(trackingLists.checkResultPerPageOption(),
				"Result per page option is not as expected on My Tracking list page");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type filter option is not as expected My Tracking list page");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking name list are not in ascending order on My Tracking list page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1136", description = "My Account - My Tracking Lists - Create My Tracking Lists page - My Account Link - My Tracking List (left section).")
	public void tc1189(String emailAddress, String password, String myTrackingListLable, String expBreadCrumb) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		Assert.assertTrue(MyAccount.checkMyTrackingListsPositionForPlusUser(myTrackingListLable),
				"My Tracking List option is not at first position.");

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not pesent on My tracking list page");
		Assert.assertTrue(trackingLists.checkTrackingListBreadCrumb(expBreadCrumb),
				"Breadcrumb : " + expBreadCrumb + " is not present on My tracking list page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1190", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link - My Saved Searches (left section).")
	public void tc1190(String emailAddress, String password, String myTrackingList, String mySavedSearch,
			String expBreadCrumb) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		Assert.assertTrue(MyAccount.checkMyTrackingListsPositionForPlusUser(myTrackingList),
				"My Tracking Lists option is not at first position.");
		Assert.assertTrue(MyAccount.checkMySavedSearchPositionForPlusUser(mySavedSearch),
				"My Saved Search option is not at second position.");

		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Saved Searches page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not pesent on My Saved Searches page");
		Assert.assertTrue(savedSearchesPage.checkSavedSearchesBreadCrumb(expBreadCrumb),
				"Breadcrumb : " + expBreadCrumb + " is not present on My Saved Searches page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link - My Saved Searches (right section) - Project Saved Searches.")
	public void tc1191(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtTop(),
				"Pagination is not present at top on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtBotton(),
				"Pagination is not present at bottom on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkResultPerPageOption(),
				"Result per page option is not as expected on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link - My Saved Searches (right section) - Company Saved Searches.")
	public void tc1192(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.clickOnCompanyTab();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtTop(),
				"Pagination is not present at top on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isPaginationDetailAtBotton(),
				"Pagination is not present at bottom on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isResultPerPageDisplayed(),
				"Result per page is not displayed on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkResultPerPageOption(),
				"Result per page option is not as expected on Save Searches page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1190", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link - My Saved Searches (left section) (TC9983)")
	public void tc1193(String emailAddress, String password, String myTrackingList, String mySavedSearch,
			String expBreadCrumb) {
		testPrerequisiteForPlusUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		Assert.assertTrue(MyAccount.checkMyTrackingListsPositionForPlusUser(myTrackingList),
				"My Tracking Lists option is not at first position.");
		Assert.assertTrue(MyAccount.checkMySavedSearchPositionForPlusUser(mySavedSearch),
				"My Saved Search option is not at second position.");

		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Saved Searches page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not pesent on My Saved Searches page");
		Assert.assertTrue(savedSearchesPage.checkSavedSearchesBreadCrumb(expBreadCrumb),
				"Breadcrumb : " + expBreadCrumb + " is not present on My Saved Searches page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - Create My Saved Searches page - My Account Link - My Saved Searches (right section) - Company Saved Searches.")
	public void tc1195(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		HomePage.clickOnMyAccountLink();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Project Tracking Lists (TC10337).")
	public void tc1196(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My Tracking lists page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My Tracking lists page.");

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifyInfoIconForShareUser(),
				"Info icon for each share SSO user is not present on the Share SSO page.");
		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Project Tracking Lists - Edit Profile details and verify info details (TC10338).")
	public void tc1197(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My Tracking lists page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My Tracking lists page.");

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Company Tracking Lists (TC10339).")
	public void tc1198(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My Tracking lists page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My Tracking lists page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifyInfoIconForShareUser(),
				"Info icon for each share SSO user is not present on the Share SSO page.");
		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Company Tracking Lists - Edit Profile details and verify info details (TC10340).")
	public void tc1199(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My Tracking lists page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My Tracking lists page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Project Saved Searches (TC10341).")
	public void tc1200(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifyInfoIconForShareUser(),
				"Info icon for each share SSO user is not present on the Share SSO page.");
		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - SSO info on Share page - Project Saved Searches - Edit Profile details and verify info details (TC10342).")
	public void tc1201(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		shareUsers.clickOnFirstInfoIcon();
		Assert.assertTrue(shareUsers.checkSSOInfoDialog(), "SSO Info dialog is not as expected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - Tracking list page for Share and UnShare - admin view - Share Link (TC10344).")
	public void tc1202(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(), "Project tab is not present on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default type filter selection is not set to ALL on My Tracking list page");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type filter option is not as expected My Tracking list page");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking name list are not in ascending order on My Tracking list page");

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.isShareTrackingListDisplayed(),
				"User is not re-directed to share tracking list page on clicking a Share link.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1203", description = "My Account - Tracking list page for Share and UnShare - admin view - Share Icon / link for a shared item (TC10345).")
	public void tc1203(String emailAddress, String password, String trackingStatus) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabDisplayed(), "Project tab is not present on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");
		Assert.assertTrue(trackingLists.isShareTrackingPeopleIconPresent(),
				"Share tracking list people image icon is not present on Tracking list page.");
		Assert.assertTrue(trackingLists.isShareLinkPresent(), "Share link is not present on My Tracking list page");
		Assert.assertTrue(trackingLists.checkTrackingListStatus(trackingStatus),
				"Tracking status " + trackingStatus + " is not present in the my tracking lists table.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1204", description = "My Account - Tracking list page for Share and UnShare - admin view - Share link for a private item (TC10346).")
	public void tc1204(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");

		trackingLists.selectTypeListOption(optionToSelectFromType);
		Assert.assertTrue(trackingLists.checkShareLinkForPrivateTrackingList_ProjectTab(optionToSelectFromType),
				"all the private tracking list dont have Share link.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1205", description = "My Account - Tracking list page for Share and UnShare - admin view - Share link NOT displayed for a Public item (TC10347).")
	public void tc1205(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");

		trackingLists.selectTypeListOption(optionToSelectFromType);
		Assert.assertTrue(trackingLists.checkShareLinkForPrivateTrackingList_ProjectTab(optionToSelectFromType),
				"Share link are present for Public tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1206", description = "[My Account] Tracking list and Saved Search page for Share and UnShare - admin view")
	public void tc1206_tc1207(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption,
			String searchShareTypeFilter) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		ShareUsers shareUser = trackingLists_1.clickOnSelectedTrackingListShareLink(trackingListName);
		shareUser.clickOnShareLink();
		TrackingLists trackingLists_2 = shareUser.clickOnDoneButton_TrackingList();

		Assert.assertTrue(trackingLists_2.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");

		trackingLists_2.selectTypeListOption(searchShareTypeFilter);
		Assert.assertTrue(trackingLists_2.checkEditTrackingNameReplace(trackingListName),
				"Tracking list is not present as shared after making it shared to other users.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1268", description = "My Account - Tracking list page for Share and UnShare - admin view - Unshare (TC10350)")
	public void tc1208(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");

		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		shareUsers.clickOnUnshareLink();
		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");
		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"After unsharing the Share list is not present as a private tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - Saved Searches page for Share and UnShare - admin view - Share Link (TC10351).")
	public void tc1209(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(),
				"Default type filter selection is not set to ALL on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected My Tracking list page");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking name list are not in ascending order on Saved search page");

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();
		Assert.assertTrue(shareUsers.isShareTrackingListDisplayed(),
				"User is not re-directed to share tracking list page on clicking a Share link.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1203", description = "My Account - Saved Searches page for Share and UnShare - admin view - Share Icon / link for a shared item (TC10352).")
	public void tc1210(String emailAddress, String password, String saveSearchStatus) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isShareTrackingPeopleIconPresent(),
				"Share saved search list people image icon is not present on Saved searc page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on My Tracking list page");
		Assert.assertTrue(savedSearchesPage.checkTrackingListStatus(saveSearchStatus),
				"Save Searchn status " + saveSearchStatus + " is not present in the Saved Search table.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1204", description = "My Account - Saved Searches page for Share and UnShare - admin view - Share link for a private item (TC10353).")
	public void tc1211(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");

		savedSearchesPage.selectTypeListOption(optionToSelectFromType);
		Assert.assertTrue(savedSearchesPage.checkShareLinkForAllSavedSearch_ProjectTab(optionToSelectFromType),
				"all the private saved searches dont have Share link.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1205", description = "My Account - Saved Searches page for Share and UnShare - admin view - Share link NOT displayed for a Public item (TC10354).")
	public void tc1212(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");

		savedSearchesPage.selectTypeListOption(optionToSelectFromType);
		Assert.assertTrue(savedSearchesPage.checkShareLinkForAllSavedSearch_ProjectTab(optionToSelectFromType),
				"Share link are present for Public saved searches.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1206", description = "My Account - Saved Searches page for Share and UnShare - admin view - Public to Shared (TC10355) and My Account - Saved Searches page for Share and UnShare - admin view  -  Changing a Public list to Shared - Cross-Verification (TC10356)")
	public void tc1213_tc1214(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption,
			String searchShareTypeFilter) {
		testPrerequisiteForAdminUser(emailAddress, password);
		String saveSearchName = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		SavedSearchesPage savedSearchesPage_1 = savedSearchPopUp.clickOnSaveButtonEditDialog();

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		ShareUsers shareUser = savedSearchesPage_1.clickOnSelectedSaveSearchShareLink(saveSearchName);
		shareUser.clickOnShareLink();
		SavedSearchesPage savedSearchesPage_2 = shareUser.clickOnDoneButton_SavedSearch();

		Assert.assertTrue(savedSearchesPage_2.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to Save search page after clicking on Done button of Share user page.");

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage_2.checkEditSaveSearch(saveSearchName),
				"Saved search is not present as private after changing it to private from public.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1268", description = "My Account - Saved Searches page for Share and UnShare - admin view - Unshare (TC10357)")
	public void tc1215(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on save search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on save search page");

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		shareUsers.clickOnUnshareLink();
		SavedSearchesPage savedSearchesPage_1 = shareUsers.clickOnDoneButton_SavedSearch();
		Assert.assertTrue(savedSearchesPage_1.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to Save search page after clicking on Done button of Share user page.");
		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"After unsharing the Share list is not present as a private saved search.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1204", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects - Edit tracking list pop-up based on Role (TC10358).")
	public void tc1216(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on My Tracking list page");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(), "Company tab is not present on My Tracking list page");

		trackingLists.selectTypeListOption(optionToSelectFromType);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"Edit dialog not contain either Name, Save or cancel button.");

		Assert.assertTrue(trackingListPopupDialog.checkETypeDropdownPresentOnEditTrackingDialog(),
				"Edit dialog not contain Type dropdown for platinum admin user.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List (right section) - Project Tracking Lists (TC10422).")
	public void TC1217(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default Type selection is not set to ALL on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option are not as expected on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.isResultPerPageDisplayed(),
				"Result per page dropdown is not present on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1203", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Project Tracking Lists - Verifying Shared Icon (TC10423).")
	public void tc1218(String emailAddress, String password, String saveSearchStatus) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.isShareTrackingPeopleIconPresent(),
				"Share saved search list people image icon is not present on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Project Tracking Lists -  User NOT the owner then do not display Share, Edit and Delete options (TC10424)")
	public void tc1219(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default Type selection is not set to ALL on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option are not as expected on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(optionToSelectFromType);

		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link are present for Public/not own tracking list on My tracking list page.");
		Assert.assertFalse(trackingLists.isEditLinkPresent(),
				"Edit link are present for Public/not own tracking list on My tracking list page.");
		Assert.assertFalse(trackingLists.isDeleteLinkPresent(),
				"Delete link are present for Public/not own tracking list on My tracking list page.");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List (right section) - Company Tracking Lists (TC10425).")
	public void TC1220(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		Assert.assertTrue(trackingLists.isProjectTabDisplayed(),
				"Project tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default Type selection is not set to ALL on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option are not as expected on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.isShareLinkPresent(),
				"Share link is not present for tracking list on My tracking list page.");
		Assert.assertTrue(trackingLists.isEditLinkPresent(),
				"Edit link are not present for tracking list on My tracking list page.");
		Assert.assertTrue(trackingLists.isDeleteLinkPresent(),
				"Delete link are not present for tracking list on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1203", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Company Tracking Lists - Verifying Shared Icon (TC10426).")
	public void tc1221(String emailAddress, String password, String saveSearchStatus) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.isShareTrackingPeopleIconPresent(),
				"Share saved search list people image icon is not present on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1173", description = "My Account - My Tracking Lists - List tracking lists - user view - My Account Link - My Tracking List - Company Tracking Lists -  User NOT the owner then do not display Share, Edit and Delete options (TC10427)")
	public void tc1222(String emailAddress, String password, String optionToSelectFromType) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.checkDefaultTypeSelection(),
				"Default Type selection is not set to ALL on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTypeDropdownOption(),
				"Type dropdown option are not as expected on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(optionToSelectFromType);

		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link are present for Public/not own tracking list on My tracking list page.");
		Assert.assertFalse(trackingLists.isEditLinkPresent(),
				"Edit link are present for Public/not own tracking list on My tracking list page.");
		Assert.assertFalse(trackingLists.isDeleteLinkPresent(),
				"Delete link are present for Public/not own tracking list on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Create Share tracking list page - Display Share link on condition - PLAT non-admin user (TC10428).")
	public void tc1223(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link are present for non-platinium on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Create Share tracking list page - Display Share link on condition - PLUS user (TC10430).")
	public void tc1224(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link are present for non-plus on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Tracking Lists - Create Share tracking list page -  PLAT admin user - Verify options in Share Page (TC10432).")
	public void tc1225(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.isShareLinkPresent(),
				"Share link are not present for platinium admin on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Tracking Lists - Create Share tracking list page -  PLAT admin user - Verify options in Share Page (TC10432).")
	public void tc1226(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithCheckbox(),
				"All users not displayed with checkbox on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My saved searches - Create Share saved searches page - Display Share link on condition - PLAT non-admin user (TC10435).")
	public void tc1228(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on Saved Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Saved Searches page.");
		Assert.assertFalse(savedSearchesPage.isShareLinkPresent(),
				"Share link are present for non-platinium on Saved Searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My saved searches - Create Share saved searches page - Display Share link on condition - PLUS user (TC10437).")
	public void tc1229(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on Saved Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Saved Searches page.");
		Assert.assertFalse(savedSearchesPage.isShareLinkPresent(),
				"Share link are present for non-plus on Saved Searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My saved searches - Create Share saved searches page - Display Share link on condition - PLAT admin user (TC10438).")
	public void tc1230(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on Saved Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Saved Searches page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(),
				"Share link are not present for platinium admin on Saved Searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My saved searches - Create Share saved searches page -  PLAT admin user - Verify options in Share Page (TC10439).")
	public void tc1231(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on Saved searched page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Saved searched page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved searched page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved searched page.");

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();
		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithCheckbox(),
				"All users not displayed with checkbox on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1234", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Projects - Display Share link on condition - User who is not the OWNER (TC10446).")
	public void tc1234(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");
		trackingLists.selectTypeListOption(optionToSelect);
		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link is present for the tracking list which is not present user.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1235", description = "My Account - My Tracking Lists -Share a tracking list (admin only)  - Projects - Verify Share page options (TC10447).")
	public void tc1235(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		trackingLists.selectTypeListOption(optionToSelect);

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithCheckbox(),
				"All users not displayed with checkbox on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed o Share User page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1237", description = "My Account - My Tracking Lists -Share a tracking list (admin only) -  Projects -Verify User Filter - Not Shared (TC10449).")
	public void tc1237(String emailAddress, String password, String userFilterOption, String breadCrumb,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed o Share User page.");
		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1238", description = "My Account - My Tracking Lists -Share a tracking list (admin only) -  Projects -Verify User Filter - Shared (TC10450).")
	public void tc1238(String emailAddress, String password, String userFilterOption, String breadCrumb,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed o Share User page.");
		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1240", description = "My Account - My Tracking Lists -Share a tracking list (admin only) -  Projects -Sharing an unshared list (TC10451).")
	public void tc1239_tc1240(String emailAddress, String password, String breadCrumb, String typeFilterOption,
			String shareWithUserName, String shareWithUserLoginId, String shareWithUserPassword,
			String sharedTypeFilter, String publicTypeFilter) {
		String shareTrackingListName = "";
		testPrerequisiteForAdminSharedUser(emailAddress, password, shareWithUserName);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		breadCrumb = breadCrumb + " " + shareTrackingListName;

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(shareWithUserName);
		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");

		trackingLists_1.selectTypeListOption(sharedTypeFilter);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(shareTrackingListName),
				"Tracking list is not present as shared after making it shared to other users.");

		ShareUsers shareUsers_1 = trackingLists.clickOnShareTrackingNameLink();
		TrackingLists trackingLists_2 = shareUsers_1.navigateViaMyTrackingBreadCrumb();

		Assert.assertTrue(trackingLists_2.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on breadcrumb on Share user page.");

		HomePage.clickOnSignOutButton();

		HomePage HomePage_1 = loginAs(shareWithUserLoginId, shareWithUserPassword);
		MyAccount MyAccount_1 = HomePage_1.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount_1.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount_1.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists_3 = MyAccount_1.clickOnMyTrackingLists();

		trackingLists_3.selectTypeListOption(sharedTypeFilter);
		Assert.assertTrue(trackingLists_3.checkEditTrackingNameReplace(shareTrackingListName),
				"Shared Tracking list : " + shareTrackingListName + " - is not present as Public tracking list .");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1241", description = "My Account - My Tracking Lists -Share a tracking list (admin only) -  Projects -Unsharing a shared list (TC10453).")
	public void tc1241_tc1242(String emailAddress, String password, String breadCrumb, String sharedTypeFilter,
			String shareWithUserName, String shareWithUserLoginId, String shareWithUserPassword,
			String privateTypeFilter, String publicTypeFilter) {
		String unshareTrackingListName = "";
		testPrerequisiteForAdminSharedUser(emailAddress, password, shareWithUserName);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.selectTypeListOption(sharedTypeFilter);
		unshareTrackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		breadCrumb = breadCrumb + " " + unshareTrackingListName;

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(shareWithUserName);
		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");

		trackingLists_1.selectTypeListOption(privateTypeFilter);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(unshareTrackingListName),
				"Tracking list is not present as private after making it unshared to other users.");

		ShareUsers shareUsers_1 = trackingLists_1.clickOnShareTrackingNameLink();
		TrackingLists trackingLists_2 = shareUsers_1.navigateViaMyTrackingBreadCrumb();
		Assert.assertTrue(trackingLists_2.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on breadcrumb on Share user page.");

		trackingLists_2.clickOnSingoutLink();

		HomePage HomePage_1 = loginAs(shareWithUserLoginId, shareWithUserPassword);
		MyAccount MyAccount_1 = HomePage_1.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount_1.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount_1.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists_3 = MyAccount_1.clickOnMyTrackingLists();

		trackingLists_3.selectTypeListOption(publicTypeFilter);
		Assert.assertFalse(trackingLists_3.checkEditTrackingNameReplace(unshareTrackingListName),
				"Unshared Tracking list : " + unshareTrackingListName + " - is present as Public tracking list .");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Display Share link on condition - PLAT admin user (TC10455).")
	public void tc1243(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.isShareLinkPresent(),
				"Share link is not present for Platnium admin user in Company tab on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1234", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Display Share link on condition - User who is not the OWNER (TC10456).")
	public void tc1244(String emailAddress, String password, String optionToSelect) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(optionToSelect);
		Assert.assertFalse(trackingLists.isShareLinkPresent(),
				"Share link is present for tracking list Platnium admin user who is not owner in Company tab on My tracking list page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1239", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Verify Share page options (TC10457).")
	public void tc1245(String emailAddress, String password, String breadCrumb, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithCheckbox(),
				"All users not displayed with checkbox on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed on Share User page.");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1239", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Verify User Filter - All (TC10458).")
	public void tc1246(String emailAddress, String password, String breadCrumb, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();

		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1237", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Verify User Filter - Shared (TC10460).")
	public void tc1247(String emailAddress, String password, String userFilterOption, String breadCrumb,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		trackingLists.clickOnCompanyTabForMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed o Share User page.");
		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1238", description = "My Account - My Tracking Lists -Share a tracking list (admin only) -  Projects -Verify User Filter - Shared (TC10450).")
	public void tc1248(String emailAddress, String password, String userFilterOption, String breadCrumb,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyTotalNoUsers(),
				"Total share user count is not displayed o Share User page.");
		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1239", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Sharing an unshared list (TC10461).")
	public void tc1249(String emailAddress, String password, String breadCrumb, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");

		ShareUsers shareUsers_1 = trackingLists.clickOnShareTrackingNameLink();
		shareUsers_1.clickOnShareLink();

		TrackingLists trackingLists_2 = shareUsers.clickOnDoneButton_TrackingList();
		ShareUsers shareUsers_2 = trackingLists.clickOnShareTrackingNameLink();
		shareUsers_2.clickOnUnshareLink();

		TrackingLists trackingLists_3 = shareUsers_2.navigateViaMyTrackingBreadCrumb();

		Assert.assertTrue(trackingLists_3.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on breadcrumb on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1239", description = "My Account - My Tracking Lists -Share a tracking list (admin only) - Companies -  Unsharing a shared list (TC10463).")
	public void tc1250(String emailAddress, String password, String breadCrumb, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOption);
		breadCrumb = breadCrumb + " " + trackingLists.getFirstTrackingNameWithShareLink();

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(breadCrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + breadCrumb);
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");

		ShareUsers shareUsers_1 = trackingLists_1.clickOnShareTrackingNameLink();

		shareUsers_1.clickOnShareLink();
		shareUsers_1.clickOnUnshareLink();
		TrackingLists trackingLists_2 = shareUsers_1.clickOnDoneButton_TrackingList();

		ShareUsers shareUsers_2 = trackingLists_2.clickOnShareTrackingNameLink();
		TrackingLists trackingLists_3 = shareUsers_2.navigateViaMyTrackingBreadCrumb();
		Assert.assertTrue(trackingLists_3.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on breadcrumb on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1251", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - User filter 'ALL' (TC10517).")
	public void tc1251(String emailAddress, String password, String shareTrackingLabel, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1252", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - User filter 'NOT SHARED' (TC10518).")
	public void tc1252(String emailAddress, String password, String shareTrackingLabel, String typeFilterOption,
			String UserOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.selectUserOptionFromDropdown(UserOption);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertFalse(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is present in Actions dropdown on Share user page when Not Shared option is selected from the User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1253", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - User filter 'SHARED' (TC10519).")
	public void tc1253(String emailAddress, String password, String shareTrackingLabel, String typeFilterOption,
			String userFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		shareUsers.adjustShareUnshareList(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertFalse(shareUsers.isActionSharedLinkPresent(),
				"Share link is present in Actions dropdown on Share user page when Shared option is selected from the User dropdown.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page when Shared option is selected from the User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1254", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - Sharing an unshared list - Single selection (TC10521).")
	public void tc1254(String emailAddress, String password, String shareTrackingLabel, String userNotShareOption,
			String userShareOption, String userName, String userStatus, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		shareUsers.selectUserOptionFromDropdown(userNotShareOption);

		Assert.assertFalse(shareUsers.verifyUserPresent(userName),
				"User : " + userName + " present in the not shared list even when it had made Share.");

		shareUsers.selectUserOptionFromDropdown(userShareOption);
		Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
				"User : " + userName + " which was shared is not present in shared list.");
		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1255", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - Sharing an unshared list - Multiple selection (TC10522).")
	public void tc1255(String emailAddress, String password, String shareTrackingLabel, String userName_1,
			String userName_2, String userName_3, String userNotShareOption, String userShareOption, String userStatus,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		List<String> userNameList = new ArrayList<String>(Arrays.asList(userName_1, userName_2, userName_3));
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		shareUsers.selectUserOptionFromDropdown(userNotShareOption);

		for (String userName : userNameList) {
			Assert.assertFalse(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " present in the not shared list even when it had made Share.");
		}

		shareUsers.selectUserOptionFromDropdown(userShareOption);
		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " which was shared is not present in shared list.");
		}

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
					"User : " + userName + " which was shared does not have unshare link infront.");
		}
		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1256", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists -Unsharing a shared list - Single selection (TC10523).")
	public void tc1256(String emailAddress, String password, String shareTrackingLabel, String userShareOption,
			String userNotShareOption, String userName, String userStatus, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		shareUsers.selectUserOptionFromDropdown(userShareOption);
		Assert.assertTrue(shareUsers.verifyUserPresent(userName),
				"User : " + userName + " is not present in the shared list even when it had already shared.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		shareUsers.selectUserOptionFromDropdown(userNotShareOption);
		Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
				"User : " + userName + " which was unshared is not present in with share link.");
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1257", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Tracking Lists - Unsharing a shared list - Multiple selection (TC10524).")
	public void tc1257(String emailAddress, String password, String shareTrackingLabel, String userName_1,
			String userName_2, String userName_3, String userShareOption, String userNotShareOption, String userStatus,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		List<String> userNameList = new ArrayList<String>(Arrays.asList(userName_1, userName_2, userName_3));
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		Assert.assertTrue(trackingLists.isProjectTabSelectedDefault(),
				"Project tab selected by default on My tracking list page.");
		Assert.assertTrue(trackingLists.isCompanyTabDisplayed(),
				"Company tab is not present on My tracking list page.");
		Assert.assertTrue(trackingLists.checkAllTrackingListStatus(),
				"Tracking list table is not public,private or shared on My tracking list page.");
		Assert.assertTrue(trackingLists.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on My tracking list page.");

		trackingLists.selectTypeListOption(typeFilterOption);
		shareTrackingLabel = shareTrackingLabel + " " + trackingLists.getFirstTrackingNameWithShareLink();
		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareTrackingLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareTrackingLabel);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");

		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		shareUsers.selectUserOptionFromDropdown(userNotShareOption);
		for (String userName : userNameList) {
			Assert.assertFalse(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " present in the shared list even when it had already shared.");
		}

		shareUsers.selectUserOptionFromDropdown(userShareOption);

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " which was shared is not present in unshared lisk.");
		}

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
					"User : " + userName + " which was shared does not have unshare link infront.");
		}
		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1258", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - User filter 'ALL' (TC10525).")
	public void tc1258(String emailAddress, String password, String sharedSaveSearchLabel, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1259", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - User filter 'NOT SHARED' (TC10527).")
	public void tc1259(String emailAddress, String password, String sharedSaveSearchLabel, String typeFilterOption,
			String UserOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.selectUserOptionFromDropdown(UserOption);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertFalse(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is present in Actions dropdown on Share user page when Not Shared option is selected from the User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1260", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - User filter 'SHARED' (TC10528).")
	public void tc1260(String emailAddress, String password, String sharedSaveSearchLabel, String typeFilterOption,
			String userFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		shareUsers.adjustShareUnshareList(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.moveToActionsDropdown();
		Assert.assertFalse(shareUsers.isActionSharedLinkPresent(),
				"Share link is present in Actions dropdown on Share user page when Shared option is selected from the User dropdown.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page when Shared option is selected from the User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "Verify profile summaries after making updates")
	public void tc1335(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = myAccount.clickOnProfiles();
		manageProfilesPage.switchToFrame();
		if (!manageProfilesPage.isEditLinkDisplayed()) {
			manageProfilesPage.AddUserProfile();
			manageProfilesPage.switchToFrame();
		}
		manageProfilesPage.clickOnManageProfileEditLink();
		manageProfilesPage.clickOnGeographyEditButton();
		manageProfilesPage.clickOnGoegraphyState();
		Assert.assertFalse(manageProfilesPage.checkGoegraphyStateUSACheckbox(),
				"Country check box is selected for State.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "Verification of the functionality of the Update button the new lightbox dialog of  Plan spec filter")
	public void tc1334(String emailAddress, String password) throws InterruptedException {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectGroupsFilter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectGroups_LHS_ParentFilterList");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPlanOpenInFindInFilter();

		projectResultsPage.clickOnPlanClassSheetFilter();
		Assert.assertTrue(projectResultsPage.isDisplayedPlanclassSheetFilter(),
				"PlanClass Sheet filter is not displayed");
		projectResultsPage.clickOnPlanClassSheetSheetSeeMore_btn();
		projectResultsPage.switchFrameFancyBox();
		String selectCheckboxPlanSheet = projectResultsPage.getTextFirstCheckboxPlanClassSheet();
		projectResultsPage.clickOnFirstCheckboxPlanSheetFrame();
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.getAppliedFilterTexts().contains(
				selectCheckboxPlanSheet.trim().substring(0, selectCheckboxPlanSheet.lastIndexOf("(") - 1).trim()));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "Verification of Cancel button in the new lightbox dialog of  spec alerts  filter")
	public void tc1333(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		if (projectResultsPage.isSpecAlertCheckBoxesDisplayed()) {
			projectResultsPage.clickOnSeeMoreSpecAlertsIcon();
			projectResultsPage.switchFrameFancyBox();
			String selectCheckboxSpecAlert = projectResultsPage.getTextFirstCheckboxSpecAlert();
			projectResultsPage.clickOnTextFirstCheckboxSpecAlert();
			projectResultsPage.SwitchToParent();
			projectResultsPage.PopUpClose();
			Assert.assertFalse(projectResultsPage.getAppliedFilterTexts().contains(selectCheckboxSpecAlert));
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "Platinium_2", description = "Verification of design of the selections in the lightbox dialogue of spec alerts filter")
	public void tc1332(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		if (projectResultsPage.isSpecAlertCheckBoxesDisplayed()) {
			projectResultsPage.ClickOnSpecalerts_SeeMore_Popup_btn();
			projectResultsPage.switchFrameFancyBox();
			Assert.assertFalse(projectResultsPage.isExcludeCheckboxPresent(),
					"'Exclude projects that are matched to the selected programs' check box is not present");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verification of design of the selections in the lightbox dialogue")
	public void tc1331(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		projectResultsPage.clickOnSeeMoreSpecAlertsOwnershipType();
		projectResultsPage.switchFrameFancyBox();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verification of the functionality of the Update button the new lightbox dialog of structural property  filter")
	public void tc1330(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();

		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.switchFrameFancyBox();
		String selectCheckboxBuildingFrame = projectResultsPage.getTextFirstCheckboxBuildingFrame();
		projectResultsPage.clickOnFirstCheckboxBuildingFrame();
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		Assert.assertTrue(projectResultsPage.getAppliedFilterTexts().contains(selectCheckboxBuildingFrame));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP7", description = "Verification of design of the selections in the lightbox dialogue")
	public void tc1329(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = HomePage.clickOnProjectsLink();
		projectResultsPage.clickOnSTRUCTURAL_PROPERTIESFilter();
		projectResultsPage.clickOnStructuralAddProperties();
		projectResultsPage.switchFrameFancyBox();
		projectResultsPage.getTextFirstCheckboxBuildingFrame();
		projectResultsPage.clickOnFirstCheckboxBuildingFrame();
		Assert.assertFalse(projectResultsPage.checkFirstCheckboxBuildingFrameIsDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1261", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - Sharing an unshared list - Single selection (TC10529).")
	public void tc1261(String emailAddress, String password, String sharedSaveSearchLabel, String userNotShareOption,
			String userShareOption, String userName, String userStatus, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		shareUsers.selectUserOptionFromDropdown(userNotShareOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		Assert.assertFalse(shareUsers.verifyUserPresent(userName),
				"User : " + userName + " present in the not shared list even when it had made Share.");

		shareUsers.selectUserOptionFromDropdown(userShareOption);
		Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
				"User : " + userName + " which was shared is not present in shared list.");
		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1262", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - Sharing an unshared list - Multiple selection (TC10530).")
	public void tc1262(String emailAddress, String password, String sharedSaveSearchLabel, String userName_1,
			String userName_2, String userName_3, String userNotShareOption, String userShareOption, String userStatus,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		List<String> userNameList = new ArrayList<String>(Arrays.asList(userName_1, userName_2, userName_3));
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		shareUsers.selectUserOptionFromDropdown(userNotShareOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page.");

		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		for (String userName : userNameList) {
			Assert.assertFalse(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " present in the not shared list even when it had made Share.");
		}

		shareUsers.selectUserOptionFromDropdown(userShareOption);

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyUserPresent(userName),
					"User : " + userName + " which was shared is not present in shared list.");
		}

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
					"User : " + userName + " which was shared does not have unshare link infront.");
		}
		shareUsers.clickOnMultipleUserShareUnshareLink(userNameList);
		HomePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1263", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches -Unsharing a shared list - Single selection (TC10531).")
	public void tc1263(String emailAddress, String password, String sharedSaveSearchLabel, String userShareOption,
			String userNotShareOption, String userName, String userStatus, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		shareUsers.selectUserOptionFromDropdown(userShareOption);
		Assert.assertTrue(shareUsers.verifyUserPresent(userName),
				"User : " + userName + " not present in the shared list even when it had already shared.");

		shareUsers.clickOnSpecificUserShareUnshareLink(userName);
		shareUsers.selectUserOptionFromDropdown(userNotShareOption);
		Assert.assertTrue(shareUsers.verifyShareStatusWithSpecificUser(userName, userStatus),
				"User : " + userName + " which was shared is not present in share link.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1264", description = "My Account - Action menu items for Sharing a tracking list and saved searches (admin only) - Saved searches - Unsharing a shared list - Multiple selection (TC10532).")
	public void tc1264(String emailAddress, String password, String sharedSaveSearchLabel, String userName_1,
			String userName_2, String userName_3, String userShareOption, String userNotShareOption, String userStatus,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		List<String> userNameList = new ArrayList<String>(Arrays.asList(userName_1, userName_2, userName_3));
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab selected by default on saved searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved searches table is not public,private or shared on saved searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Saved searches name are not sorted on saved searches page.");

		savedSearchesPage.selectTypeListOption(userShareOption);
		sharedSaveSearchLabel = sharedSaveSearchLabel + " "
				+ savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(sharedSaveSearchLabel),
				"Share saved search page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + sharedSaveSearchLabel);

		shareUsers.selectUserOptionFromDropdown(userShareOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page.");

		SavedSearchesPage savedSearchesPage_1 = shareUsers.clickOnDoneButton_SavedSearch();
		savedSearchesPage_1.selectTypeListOption(typeFilterOption);
		ShareUsers shareUsers_1 = savedSearchesPage_1.clickOnShareSavedSearchLink();

		shareUsers_1.clickOnMultipleUserShareUnshareLink(userNameList);
		shareUsers_1.selectUserOptionFromDropdown(userNotShareOption);
		for (String userName : userNameList) {
			Assert.assertFalse(shareUsers_1.verifyUserPresent(userName),
					"User : " + userName + " present in the shared list even when it had already shared.");
		}

		shareUsers_1.selectUserOptionFromDropdown(userShareOption);

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers_1.verifyUserPresent(userName),
					"User : " + userName + " which was shared is not present in unshared lisk.");
		}

		for (String userName : userNameList) {
			Assert.assertTrue(shareUsers_1.verifyShareStatusWithSpecificUser(userName, userStatus),
					"User : " + userName + " which was shared does not have unshare link infront.");
		}
		shareUsers_1.clickOnMultipleUserShareUnshareLink(userNameList);
		savedSearchesPage.clickOnSingoutLink();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1265", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects - Selecting Edit for a Shared tracking list - Verify Cancel operation (TC10542).")
	public void TC1265(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTypeDropdownOption(),
				"Edit dialog with shared icon link contain different option than Shared, private and public.");

		Assert.assertTrue(trackingListPopupDialog.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with shared icon link contain different default selected option than " + typeFilterOpen);

		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1266", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects - Selecting Edit for a Private tracking list - Verify Cancel operation (TC10543).")
	public void TC1266(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertFalse(trackingListPopupDialog.checkShareEditTypeDropdownOption(),
				"Edit dialog with private share link contain different option than private and public.");

		Assert.assertTrue(trackingListPopupDialog.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with shared private link contain different default selected option than "
						+ typeFilterOpen);

		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1267", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects - Selecting Edit for a Public tracking list - Verify Cancel operation (TC10544).")
	public void TC1267(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertFalse(trackingListPopupDialog.checkShareEditTypeDropdownOption(),
				"Edit dialog with public edit link contain different option than private and public.");

		Assert.assertTrue(trackingListPopupDialog.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with public edit link contain different default selected option than " + typeFilterOpen);

		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1269", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects -  Changing a Shared list to Private (TC10545).")
	public void tC1268_tC1269(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption,
			String shareWithUserName) {
		testPrerequisiteForAdminSharedUser(emailAddress, password, shareWithUserName);
		String trackingListName = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();
		trackingLists_1.selectTypeListOption(editTypeFilterOption);

		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list which had edited to private is not change to private.");

		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name present with share icon even when it is change to Private.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1270", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects -  Changing a Shared list to Public (TC10547).")
	public void tc1270_tc1271(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		String trackingListName = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name is not become Public as it is not displayed as Public list.");

		Assert.assertFalse(trackingLists_1.isShareLinkPresent(), "Share link is present on public tracking list.");
		Assert.assertTrue(trackingLists_1.isEditLinkPresent(), "Edit link is not present on tracking list.");
		Assert.assertTrue(trackingLists_1.isDeleteLinkPresent(), "Delete link is not present on tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1272", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Projects -  Changing a Private list to Public (TC10549).")
	public void tc1272_tc1273(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		String trackingListName = "";
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Private tracking list name is not become Public as it is not displayed as Public list.");

		Assert.assertFalse(trackingLists_1.isShareLinkPresent(), "Share link is present on public tracking list.");
		Assert.assertTrue(trackingLists_1.isEditLinkPresent(), "Edit link is not present on tracking list.");
		Assert.assertTrue(trackingLists_1.isDeleteLinkPresent(), "Delete link is not present on tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1275", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) -  Projects - Changing a Public list to Private (TC10554).")
	public void TC1275(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Pulic tracking list name present with even when it is change to Private.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies - Edit tracking list pop-up based on Role (TC10556) - Platinium.")
	public void tc11276_Platinium(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"All the element are not present on edit tracking list dialog for NON-Platinium User.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1276", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies - Edit tracking list pop-up based on Role (TC10556) - Platinium Admin.")
	public void tc1276_PlatiniumAdmin(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		Assert.assertTrue(trackingListPopupDialog.checkETypeDropdownPresentOnEditTrackingDialog(),
				"Type filter dropdwon is not pesent on edit dialog for Platinium admin user on tracking list page.");
		Assert.assertTrue(trackingListPopupDialog.checkEditTrackingNameDialog(),
				"All the element are not present on edit tracking list dialog for Platinium Admin User.");
		trackingListPopupDialog.clickOnCancelButtonEditDialog();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1266", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies - Selecting Edit for a Private tracking list - Verify Cancel operation (TC10558).")
	public void TC1177(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertFalse(trackingListPopupDialog.checkShareEditTypeDropdownOption(),
				"Edit dialog with different option than private and public.");

		Assert.assertTrue(trackingListPopupDialog.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with contain different default selected option than " + typeFilterOpen);

		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTypeFilterOption),
				"Edit tracking name changes even when cancel button is selected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1267", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies - Selecting Edit for a Public tracking list - Verify Cancel operation (TC10559).")
	public void TC1178(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();

		Assert.assertFalse(trackingListPopupDialog.checkShareEditTypeDropdownOption(),
				"Edit dialog with different option than private and public.");

		Assert.assertTrue(trackingListPopupDialog.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with contain different default selected option than " + typeFilterOpen);

		trackingListPopupDialog.editTrackingNameSetText(editTrackingName);
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		trackingListPopupDialog.clickOnCancelButtonEditDialog();

		Assert.assertFalse(trackingLists.checkEditTrackingNameReplace(editTypeFilterOption),
				"Edit tracking name changes even when cancel button is selected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1269", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies -  Changing a Shared list to Private (TC10560).")
	public void tc1279_tc1280(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption,
			String shareWithUserName) {
		String trackingListName = "";
		testPrerequisiteForAdminSharedUser(emailAddress, password, shareWithUserName);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list which had edited to private is not change to private.");

		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name present with share icon even when it is change to Private.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1270", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) -  Companies - Changing a Shared list to Public (TC10562).")
	public void tc1281_tc1282(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name is not become Public as it is not displayed as Public list.");

		Assert.assertFalse(trackingLists_1.isShareLinkPresent(), "Share link is present on public tracking list.");
		Assert.assertTrue(trackingLists_1.isEditLinkPresent(), "Edit link is not present on tracking list.");
		Assert.assertTrue(trackingLists_1.isDeleteLinkPresent(), "Delete link is not present on tracking list.");

		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name had not become public after editing.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1272", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) - Companies -  Changing a Private list to Public (TC10564).")
	public void tc1283_tc1284(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();

		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Private tracking list name present with even when it is change to Public.");

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share icon tracking list name is not become Public as it is not displayed as Public list.");

		Assert.assertFalse(trackingLists_1.isShareLinkPresent(), "Share link is present on public tracking list.");
		Assert.assertTrue(trackingLists_1.isEditLinkPresent(), "Edit link is not present on tracking list.");
		Assert.assertTrue(trackingLists_1.isDeleteLinkPresent(), "Delete link is not present on tracking list.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1285", description = "[My Account - My Tracking Lists] Make a tracking list Public/Private (admin only)")
	public void tc1285_tc1286(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();

		TrackingLists trackingLists = MyAccount.clickOnMyTrackingListsNavLink();
		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();

		ShareUsers shareUsers = trackingLists.clickOnShareTrackingNameLink();

		shareUsers.clickOnShareLink();
		TrackingLists trackingLists_1 = shareUsers.clickOnDoneButton_TrackingList();
		Assert.assertTrue(trackingLists_1.isMyTrackingListDisplayed(),
				"User is not navigated to My tracking list page after clicking on Done button of Share user page.");
		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Share list is still displayed as a private tracking list.");

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Private tracking list is not change to shared even after sharing.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1275", description = "My Account - My Tracking Lists - Make a tracking list Public/Private (admin only) -  Companies - Changing a Public list to Private (TC10568).")
	public void tc1287_tc1288(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String trackingListName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		TrackingLists trackingLists = MyAccount.clickOnMyTrackingLists();

		trackingLists.clickOnCompanyTabForMyTrackingLists();
		trackingLists.selectTypeListOption(typeFilterOpen);
		trackingListName = trackingLists.getFirstTrackingNameWithShareLink();
		TrackingListPopupDialog trackingListPopupDialog = trackingLists.clickOnEditTrackingNameLink();
		trackingListPopupDialog.selectTypeListInEditDialog(editTypeFilterOption);
		TrackingLists trackingLists_1 = trackingListPopupDialog.clickOnSaveButtonEditDialog();

		trackingLists_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Tracking list is not present as private after changing it to private from pulic.");

		trackingLists_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(trackingLists_1.checkEditTrackingNameReplace(trackingListName),
				"Tracking list is still present as public after changing it to private.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "CustAcctTool_Commom_DP", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches (right section) - Project Saved Searches (TC10576).")
	public void tc1290(String emailAddress, String password) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved Searches Status - Private, Public or Shared are not present on saved search pages");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1276", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches - Project Saved Searches - Verifying Shared Icon (TC10577).")
	public void tc1291(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isShareTrackingPeopleIconPresent(),
				"Share saved search list people image icon is not present on Saved searc page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1158", description = "My Account - My Saved Searches - List saved searches - user view - My Account Link - My Saved Searches - Project Saved Searches -  User NOT the owner then do not display Share, Edit and Delete options (TC10578).")
	public void tc1292(String emailAddress, String password, String typeFilteOption) {
		testPrerequisiteForNonAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"My Saved Search is not displayed on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(),
				"Project tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not selected by default on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(),
				"Company tab is not present on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkDefaultTypeSelection(), "Default type filter is not set to 'All'");
		Assert.assertTrue(savedSearchesPage.checkTypeDropdownOption(),
				"Type filter option is not as expected on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Save search name are not in ascending sorted order on Save Searches page.");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved Searches Status - Private, Public or Shared are not present on saved search pages");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Saved Searches Status - Private, Public or Shared are not present on saved search pages");

		savedSearchesPage.selectTypeListOption(typeFilteOption);
		Assert.assertFalse(savedSearchesPage.isShareLinkPresent(),
				"Share link present for saved search which is not created by owner on saved search pages");
		Assert.assertFalse(savedSearchesPage.isEditLinkPresent(),
				"Edit link present for saved search which is not created by owner on saved search pages");
		Assert.assertFalse(savedSearchesPage.isDeleteLinkPresent(),
				"Delete link present for saved search which is not created by owner on saved search pages");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1235", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Display Share link on condition - PLAT admin user (TC10579).")
	public void tc1293(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		savedSearchesPage.selectTypeListOption(typeFilterOption);
		Assert.assertTrue(savedSearchesPage.checkShareLinkForAllSavedSearch_ProjectTab(typeFilterOption),
				"All the private tracking list dont have Share link on saved searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1234", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Display Share link on condition - User who is not the OWNER (TC10580).")
	public void tc1294(String emailAddress, String password, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		savedSearchesPage.selectTypeListOption(typeFilterOption);
		Assert.assertTrue(savedSearchesPage.checkShareLinkForAllSavedSearch_ProjectTab(typeFilterOption),
				"All the saved search which is not create by owner dont have Share link on saved searches page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1295", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Verify Share page options (TC10581).")
	public void tc1295(String emailAddress, String password, String shareSaveSearchBreadcrumb,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchBreadcrumb = shareSaveSearchBreadcrumb + " "
				+ savedSearchesPage.getFirstSavedSearchWithShareLink();

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();
		Assert.assertTrue(shareUsers.verifyBreadCrumb(shareSaveSearchBreadcrumb),
				"Breadcrumb for share user page is not as expected. Actual : " + shareUsers.getShareUserBreadCrumb()
						+ " Expected : " + shareSaveSearchBreadcrumb);

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown selection is not ALL on Share user page.");
		Assert.assertTrue(shareUsers.checkUsersDropdownOption(),
				"User dropdown options are not as expected on Share user page.");
		shareUsers.moveToActionsDropdown();
		Assert.assertTrue(shareUsers.isActionSharedLinkPresent(),
				"Share link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.isActionUnsharedLinkPresent(),
				"Unshare link is not present in Actions dropdown on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithCheckbox(),
				"All users not displayed with checkbox on Share user page.");
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share OR Unshare link is not displayed for each user on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1296", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Verify User Filter - All (TC10582).")
	public void tc1296(String emailAddress, String password, String shareSaveSearchLabel, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchLabel = shareSaveSearchLabel + " " + savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareSaveSearchLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareSaveSearchLabel);
		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown selection is not ALL on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1297", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Verify User Filter - Not Shared (TC10583).")
	public void tc1297(String emailAddress, String password, String userFilterOption, String shareSaveSearchLabel,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchLabel = shareSaveSearchLabel + " " + savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareSaveSearchLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		shareUsers.selectUserOptionFromDropdown(userFilterOption);

		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Share link is not displayed for each user on Share user page when Not Share option is selected from User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1298", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Verify User Filter - Shared (TC10584).")
	public void tc1298(String emailAddress, String password, String userFilterOption, String shareSaveSearchLabel,
			String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchLabel = shareSaveSearchLabel + " " + savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareSaveSearchLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");
		shareUsers.selectUserOptionFromDropdown(userFilterOption);
		shareUsers.adjustShareUnshareList(userFilterOption);
		Assert.assertTrue(shareUsers.checkAllUsersWithShareLink(),
				"Unshare link is not displayed for each user on Share user page when Shared option is selected from User dropdown.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1296", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Sharing an unshared list (TC10585).")
	public void tc1299(String emailAddress, String password, String shareSaveSearchLabel, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchLabel = shareSaveSearchLabel + " " + savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareSaveSearchLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");
		shareUsers.clickOnShareLink();
		SavedSearchesPage savedSearchesPage_1 = shareUsers.clickOnDoneButton_SavedSearch();
		Assert.assertTrue(savedSearchesPage_1.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to Saved search page after clicking on Done button of Share user page.");

		ShareUsers shareUsers_1 = savedSearchesPage_1.clickOnShareSavedSearchLink();

		SavedSearchesPage savedSearchesPage_2 = shareUsers_1.navigateViaSavedSearchBreadCrumb();

		Assert.assertTrue(savedSearchesPage_2.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to Saved search list page after clicking on breadcrumb on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1296", description = "My Account - My Saved Searches -Share a saved searches (admin only) - Unsharing a shared list (TC10587).")
	public void tc1300(String emailAddress, String password, String shareSaveSearchLabel, String typeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		Assert.assertTrue(savedSearchesPage.isProjectTabDisplayed(), "Project tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.isProjectTabSelectedDefault(),
				"Project tab is not default selected on Saved search page");
		Assert.assertTrue(savedSearchesPage.isCompanyTabDisplayed(), "Company tab is not present on Saved search page");
		Assert.assertTrue(savedSearchesPage.checkAllSavedSearchedStatus(),
				"Tracking list table is not public,private or shared on Saved search page.");
		Assert.assertTrue(savedSearchesPage.checkTrackingNameSortedList(),
				"Tracking list name are not sorted on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isShareLinkPresent(), "Share link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage.isDeleteLinkPresent(), "Delete link is not present on Saved search page.");

		savedSearchesPage.selectTypeListOption(typeFilterOption);
		shareSaveSearchLabel = shareSaveSearchLabel + " " + savedSearchesPage.getFirstSavedSearchesNameWithShareLink();
		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();

		Assert.assertTrue(shareUsers.verifySharePageLabel(shareSaveSearchLabel),
				"Share page label is not as expected. Actual : " + shareUsers.getShareTrackingListLabel()
						+ " Expected : " + shareSaveSearchLabel);

		Assert.assertTrue(shareUsers.checkDefaultUserSelection(),
				"Default User dropdown options are is not ALL on Share user page.");

		shareUsers.clickOnShareLink();
		shareUsers.clickOnUnshareLink();
		SavedSearchesPage savedSearchesPage_1 = shareUsers.clickOnDoneButton_SavedSearch();
		Assert.assertTrue(savedSearchesPage.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to saved search page after clicking on Done button of Share user page.");

		ShareUsers shareUsers_1 = savedSearchesPage_1.clickOnShareSavedSearchLink();
		SavedSearchesPage savedSearchesPage_2 = shareUsers_1.navigateViaSavedSearchBreadCrumb();
		Assert.assertTrue(savedSearchesPage_2.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to save search page after clicking on breadcrumb on Share user page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1265", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) - Selecting Edit for a Shared saved searches - Verify Cancel operation (TC10592).")
	public void TC1301(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertTrue(savedSearchPopUp.checkShareEditTypeDropdownOption(),
				"Edit dialog with shared icon link contain different option than Shared, private and public.");

		Assert.assertTrue(savedSearchPopUp.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with shared icon link contain different default selected option than " + typeFilterOpen);

		savedSearchPopUp.editSaveSearchNameSetText(editTrackingName);
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();

		Assert.assertFalse(savedSearchesPage.checkEditSaveSearch(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1266", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) - Selecting Edit for a Private saved searches - Verify Cancel operation (TC10593).")
	public void TC1302(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertFalse(savedSearchPopUp.checkShareEditTypeDropdownOption(),
				"Edit dialog with private link contain different option than private and public.");

		Assert.assertTrue(savedSearchPopUp.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with private link contain different default selected option than " + typeFilterOpen);

		savedSearchPopUp.editSaveSearchNameSetText(editTrackingName);
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();

		Assert.assertFalse(savedSearchesPage.checkEditSaveSearch(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1267", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) - Selecting Edit for a Public saved searches - Verify Cancel operation (TC10594).")
	public void tC1303(String emailAddress, String password, String editTrackingName, String typeFilterOpen,
			String editTypeFilterOption) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		Assert.assertFalse(savedSearchPopUp.checkShareEditTypeDropdownOption(),
				"Edit dialog with public link contain different option than private and public.");

		Assert.assertTrue(savedSearchPopUp.checkSelectOfEditTypeDropdown(typeFilterOpen),
				"Edit dialog with public link contain different default selected option than " + typeFilterOpen);

		savedSearchPopUp.editSaveSearchNameSetText(editTrackingName);
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		savedSearchPopUp.clickOnCancelButtonEditSaveSearch();

		Assert.assertFalse(savedSearchesPage.checkEditSaveSearch(editTrackingName),
				"Edit tracking name is present in the list even after click on cancel button of edit dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1268", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) -  Changing a Shared list to Private (TC10595).")
	public void tC1304(String emailAddress, String password, String typeFilterOpen, String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		SavedSearchesPage savedSearchesPage_1 = savedSearchPopUp.clickOnSaveButtonEditDialogWithOverride();
		Assert.assertFalse(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Share saved search is not change to private from edit save search.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1270", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) -  Changing a Shared list to Public (TC10597).")
	public void tC1305_tC1306(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		SavedSearchesPage savedSearchesPage_1 = savedSearchPopUp.clickOnSaveButtonEditDialogWithOverride();
		Assert.assertFalse(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Share saved search is not change to public from edit save search.");

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage.checkEditSaveSearch(saveSearchName),
				"Private saved search is not change to public from edit save search..");
		Assert.assertFalse(savedSearchesPage_1.isShareLinkPresent(), "Share link is present on public Saved search.");
		Assert.assertTrue(savedSearchesPage_1.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage_1.isDeleteLinkPresent(),
				"Delete link is not present on Saved search page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1272", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) -  Changing a Private list to Public (TC10599) and (TC10600) .")
	public void tC1307_tC1308(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		SavedSearchesPage savedSearchesPage_1 = savedSearchPopUp.clickOnSaveButtonEditDialogWithOverride();

		Assert.assertFalse(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Private saved search is not change to public from edit save search..");

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage.checkEditSaveSearch(saveSearchName),
				"Private saved search is not change to public from edit save search..");
		Assert.assertFalse(savedSearchesPage_1.isShareLinkPresent(), "Share link is present on public Saved search.");
		Assert.assertTrue(savedSearchesPage_1.isEditLinkPresent(), "Edit link is not present on Saved search page.");
		Assert.assertTrue(savedSearchesPage_1.isDeleteLinkPresent(),
				"Delete link is not present on Saved search page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1285", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) -  Changing a Private list to Shared (TC10601) and (TC10602)")
	public void tc1309_tc1310(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();

		ShareUsers shareUsers = savedSearchesPage.clickOnShareSavedSearchLink();
		shareUsers.clickOnShareLink();
		SavedSearchesPage savedSearchesPage_1 = shareUsers.clickOnDoneButton_SavedSearch();

		Assert.assertTrue(savedSearchesPage_1.IsMySavedSearchesLabelDispalyed(),
				"User is not navigated to saved searches page after clicking on Done button of Share user page.");

		savedSearchesPage_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Share list is still displayed as a private saved search.");

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Private saved search is not change to shared even after sharing.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1275", description = "My Account - My Saved Searches - Make a saved searches Public/Private (admin only) -  Changing a Public list to Private (TC10603) and (TC10604).")
	public void tc1311_tc1312(String emailAddress, String password, String typeFilterOpen,
			String editTypeFilterOption) {
		String saveSearchName = "";
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();

		savedSearchesPage.selectTypeListOption(typeFilterOpen);
		saveSearchName = savedSearchesPage.getFirstSavedSearchWithShareLink();
		SavedSearchPopUp savedSearchPopUp = savedSearchesPage.clickOnEditSaveSearchLink();
		savedSearchPopUp.selectTypeListInEditDialog(editTypeFilterOption);
		SavedSearchesPage savedSearchesPage_1 = savedSearchPopUp.clickOnSaveButtonEditDialog();

		savedSearchesPage_1.selectTypeListOption(editTypeFilterOption);
		Assert.assertTrue(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Saved search is not present as private after changing it to private from pulic.");

		savedSearchesPage_1.selectTypeListOption(typeFilterOpen);
		Assert.assertFalse(savedSearchesPage_1.checkEditSaveSearch(saveSearchName),
				"Saved search is still present as public after changing it to private.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAccountToolsDataProvider.class, dataProvider = "TC1276", description = "Verification of  Action Menu items name for 'Manage Users' page . (TC13098).")
	public void tc1318(String emailAddress, String password) {
		testPrerequisiteForAdminUser(emailAddress, password);
		HomePage HomePage = loginAs(emailAddress, password);
		MyAccount MyAccount = HomePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isMySavedSearchedNavLinkDisplayed(), "My Saved Searches option is not present.");
		Assert.assertTrue(MyAccount.isMyTrackingListNavLinkDisplayed(), "My Tracking Lists option is not present.");
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLinkWithSwithFrame();
		adminUsersPage.clickOnActionsMenu();

		Assert.assertTrue(adminUsersPage.verifyAllowDoNotAllowUserActions(), "Failed to get the user actions");
	}

}
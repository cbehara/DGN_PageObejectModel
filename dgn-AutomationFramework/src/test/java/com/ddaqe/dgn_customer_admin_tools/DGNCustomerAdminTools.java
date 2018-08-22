package com.ddaqe.dgn_customer_admin_tools;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.AccessPendingPage;
import com.ddaqe.pages.ActivationPage;
import com.ddaqe.pages.AdminReportsPage;
import com.ddaqe.pages.AdminUsersPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LicensePreferencePage;
import com.ddaqe.pages.LoginPage;
import com.ddaqe.pages.ManageProfilesPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.MyRegistrationInfoPage;
import com.ddaqe.pages.TermsOfUsePage;
import com.ddaqe.utils.DGNEnum;

@Listeners(TestListener.class)

public class DGNCustomerAdminTools extends BaseTest {

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithoutAccept", description = "Accept Terms of use page for new users (TC7465)")
	public void tc2310(String AdminEmail, String AdminPassword, String UserEmail, String UserPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);

		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2 : Remove user from license if added already
		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(UserEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
		// End of Precondition

		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(UserEmail, UserPassword);

		activationPage.enterValueInLicenseField(LicenseNumber);
		TermsOfUsePage termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		termsOfUsePage.clickOnSignOutButton();

		activationPage = loginPage.loginAsNonLicenseUser(UserEmail, UserPassword);

		Assert.assertTrue(!homePage.isHomeMenuLinkDisplayed(),
				"The home page should not be displayed until the user accepts the terms and conditions");
		termsOfUsePage.clickOnSignOutButton();

	}

	// Precondition: Existing valid user should not have accepted license
	// USer: QA3789@construction.comDoNotAcceptTermsConditions
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithoutAccept", description = "Accept Terms of use page for registered users (TC7467)")
	public void tc2311(String AdminEmail, String AdminPassword, String UserEmail, String UserPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		// Precondition:
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(UserEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(UserEmail, UserPassword);

		activationPage.enterValueInLicenseField(LicenseNumber);
		TermsOfUsePage termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		termsOfUsePage.clickOnSignOutButton();

		activationPage = loginPage.loginAsNonLicenseUser(UserEmail, UserPassword);

		Assert.assertTrue(!homePage.isHomeMenuLinkDisplayed(),
				"The home page should not be displayed until the user accepts the terms and conditions");
	}

	// Precondition: existing valid user should have accepted license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithAccept", description = "Once accepted, the terms of use page should not be displayed again for the same user (TC7469)")
	public void tc2312(String AdminEmail, String AdminPassword) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = new HomePage(getDriver());
		loginPage.loginAsNonLicenseUser(AdminEmail, AdminPassword);
		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(),
				"The home page should be displayed as the user had accepted the terms and conditions");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithAccept", description = "[Customer Admin - Manage Users] - Verify entry points to Manage users page (TC10011)")
	public void tc2313(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);

		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		MyDownloadsPage myDownloadsPage = MyAccount.clickOnMyDownloads();
		myDownloadsPage.clickElementWithTextFromAdminTools("Users");

		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminNonAdminLicense", description = "[Customer Admin - Manage Users] - Verify access to the manage users page (TC10021)")
	public void tc2314(String AdminEmail, String AdminPassword, String NonAdminEmail, String NonAdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);

		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb for users page");
		homePage.clickOnSignOutButton();

		loginAs(NonAdminEmail, NonAdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		Assert.assertTrue(!adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected page");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Manage Users] - Verify the breadcrumb (TC10026)")
	public void tc2315(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);

		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		Assert.assertTrue(!homePage.verifyCommonTabHighlighted(), "Failed to get all the tabs non highlighted");

		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Manage Users] - Verify the page elements (TC10030)")
	public void tc2316(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");
		Assert.assertTrue(adminUsersPage.verifyMyAccountsAdminToolsOptions(),
				"Failed to verify the My Accounts - Admin Tools options");
		Assert.assertTrue(adminUsersPage.verifyMyAccountsAccountToolsOptions(),
				"Failed to verify the My Accounts - Account Tools Options");
		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.verifyUserFilterOptions(), "Failed to verify users filter options");
		Assert.assertTrue(adminUsersPage.isLicenseNumberDisplayed(), "Failed to display the license number");
		Assert.assertTrue(adminUsersPage.verifyUserActions(), "Failed to get the user actions");
		Assert.assertTrue(adminUsersPage.isSelectAllCheckBoxDisplayed(), "Failed to display the 'Select All' checkbox");
		Assert.assertTrue(adminUsersPage.isUserCountDisplayed(), "Failed to display the user count");
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Manage Users]  - Verify the user details list view. (TC10031)")
	public void tc2317(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		adminUsersPage.switchToFrame();

		Assert.assertTrue(adminUsersPage.getChkUsers() == adminUsersPage.getUserCount(),
				"Failed to get user checkboxes for each users");
		Assert.assertTrue(adminUsersPage.getUserNamesSize() == adminUsersPage.getUserCount()
				&& adminUsersPage.verifyUserNameFormat(), "Failed to get the expected name format");
		Assert.assertTrue(adminUsersPage.getUserIconSize() == adminUsersPage.getUserCount(),
				"Failed to get the icon beside every user");
		Assert.assertTrue(adminUsersPage.getUserRemoveLicenseSize() == adminUsersPage.getUserCount(),
				"Failed to get the 'Remove user from license' button beside every user");
		Assert.assertTrue(adminUsersPage.getUserRoleSize() == adminUsersPage.getUserCount(),
				"Failed to get the 'User Role' beside every user");
		Assert.assertTrue(adminUsersPage.getUserStatusSize() == adminUsersPage.getUserCount(),
				"Failed to get the 'User Status' beside every user");
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Manage Users] Allow admin to filter list by user attribute (e.g. approved/not approved) - To Verify the Manage Users page (TC10042)")
	public void tc2318(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.verifyUserFilterOptions(), "Failed to verify users filter options");

		adminUsersPage.clickUserFilterOptions(DGNEnum.MyAccountUserFilterOptions.ADMIN);
		Assert.assertTrue(adminUsersPage.verifyRoleSelected(DGNEnum.MyAccountUserFilterOptions.ADMIN),
				"Failed to get the list of Admin users as filtered");

		adminUsersPage.clickUserFilterOptions(DGNEnum.MyAccountUserFilterOptions.USERS);
		Assert.assertTrue(adminUsersPage.verifyRoleSelected(DGNEnum.MyAccountUserFilterOptions.USERS),
				"Failed to get the list of Admin users as filtered");

		adminUsersPage.clickUserFilterOptions(DGNEnum.MyAccountUserFilterOptions.APPROVED);
		Assert.assertTrue(adminUsersPage.verifyStatusSelected(DGNEnum.MyAccountUserFilterOptions.APPROVED),
				"Failed to get the list of Admin users as filtered");

		adminUsersPage.clickUserFilterOptions(DGNEnum.MyAccountUserFilterOptions.NOT_APPROVED);
		Assert.assertTrue(adminUsersPage.verifyStatusSelected(DGNEnum.MyAccountUserFilterOptions.NOT_APPROVED),
				"Failed to get the list of Admin users as filtered");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildAdminLicense", description = "[Customer Admin - Manage Users] Allow admin to make a user an admin or regular user - To Verify that Admin user is able to change the User to Admin user using the toggle button (TC10049)")
	public void tc2324(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();

		}

		// End of Precondition

		loginPage.loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isReportsDisplayed(), "Failed to login user " + nameMatched + " as Admin");
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// PRecondition : userEmail should be a non admin user// Change the user
	// role to previous state
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildAdminLicense", description = "[Customer Admin - Manage Users] Allow admin to make a user an admin or regular user- To Verify that Admin user is able to change the multiple User to Admin (TC10050)")
	public void tc2325(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, false);
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnMakeAdminActions();
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();

		}
		// End of Precondition

		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isReportsDisplayed(), "Failed to login user " + nameMatched + " as Admin");
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// PRecondition : userEmail should be an admin user // Change the user role
	// to previous state
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildNonAdminLicense", description = "[Customer Admin - Manage Users] Allow admin to make a user an admin or regular user -To Verify that Admin user is able to change the Admin User to user using the toggle button (TC10052)")
	public void tc2326(String AdminEmail, String AdminPassword, String ChildNonAdminEmail, String ChildNonAdminPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// check box from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2: Make Child user Admin
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildNonAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 3: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildNonAdminEmail, ChildNonAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();
			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();
		}
		loginPage.loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isReportsDisplayed(), "Failed to login user " + nameMatched + " as User");
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildNonAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// PRecondition : userEmail should be an admin user // Change the user role
	// to previous state
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildNonAdminLicense", description = "[Customer Admin - Manage Users] Allow admin to make a user an admin or regular user -To Verify that Admin user is able to change the Admin User to user using the toggle button (TC10052)")
	public void tc2327(String AdminEmail, String AdminPassword, String ChildNonAdminEmail, String ChildNonAdminPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// check box from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2: Make Child user Admin
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildNonAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 3: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());
			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildNonAdminEmail, ChildNonAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();
			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();
		}
		loginPage.loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isReportsDisplayed(), "Failed to login user " + nameMatched + " as User");
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildNonAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildPendingLicense", description = "[Customer Admin - Manage Users] - Verify error message (TC10055)")
	public void tc2329(String AdminEmail, String AdminPassword, String ChildNonAdminEmail, String ChildNonAdminPassword,
			String LicenseNumber) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		adminUsersPage.switchToFrame();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		Assert.assertTrue(adminUsersPage.verifyErrorMessage().trim().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected error message");

		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "(Copy of) [Customer Admin - Manage Users] - Verify error message when admin tries to remove without selection (TC10059)")
	public void tc2332(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Users"),
				"Failed to display the expected bread crumb");

		adminUsersPage.switchToFrame();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnRemoveLink();
		Assert.assertTrue(adminUsersPage.verifyErrorMessage().trim().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected error message");

		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiNonAdminLicense", description = "[Customer Admin - Reports] - Verify the Reports option for a Platinum non admin user (TC11608)")
	public void tc2371(String NonAdminEmail, String NonAdminPassword) {
		HomePage homePage = loginAs(NonAdminEmail, NonAdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(!MyAccount.isReportsDisplayed(), "The 'Reports' link should not be displayed for this user");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlusLicense", description = "[Customer Admin - Reports] - Verify the Reports option for a plus user (TC11609)")
	public void tc2372(String PlusEmail, String PlusPassword) {
		HomePage homePage = loginAs(PlusEmail, PlusPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(!MyAccount.isReportsDisplayed(), "The 'Reports' link should not be displayed for this user");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildPendingLicense", description = "[Customer Admin - License Preferences] - Verify landing page after approval of license (TC10046)")
	public void tc2320(String AdminEmail, String AdminPassword, String ChildPendingEmail, String ChildPendingPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the access pending page, check the
		// required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (!licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildPendingEmail, false);
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			AccessPendingPage accessPendingPage = new AccessPendingPage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				accessPendingPage = activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();

			}
			homePage.clickOnSignOutButton();

			// Precondition 4 : Check again in the users list with the admin
			// user in
			// order to approve the pending child user

			loginAs(AdminEmail, AdminPassword);
			homePage.clickOnMyAccountLink();
			MyAccount.clickOnUsersLink();
			adminUsersPage.switchToFrame();
			nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildPendingEmail, false);
			adminUsersPage.clickOnActionsMenu();
			adminUsersPage.clickOnApproveLink();
			adminUsersPage.switchToDefault();
			homePage.clickOnSignOutButton();

			// Precondition 5: Accept the terms and conditions when approved
			// user logs in again
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();
		}
		// End of Precondition

		loginAs(ChildPendingEmail, ChildPendingPassword);
		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(), "Failed to display the homepage");
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();

		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// Precondition : License preference - uncheck 1st check box // Remove from
	// license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCNonPlatiChildAdminLicense", description = "[Customer Admin - License Preferences] - Verify landing page for non-platinum user (TC10047)")
	public void tc2321(String AdminEmail, String AdminPassword, String ChildEmail, String ChildPassword,
			String LicenseNumber) {

		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the access pending page, check the
		// required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2: Remove from license
		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 5: Accept the terms and conditions when approved
		// user logs in again
		TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildEmail, ChildPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		}
		termsOfUsePage.clickAcceptBtn();
		// End of Precondition

		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(), "Failed to display the homepage");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCNonPlatiChildAdminLicense", description = "[Customer Admin - Manage Users] - Verify that users removed from license do not have access to Network. (TC10060)")
	public void tc2333(String AdminEmail, String AdminPassword, String ChildEmail, String ChildPassword,
			String LicenseNumber) {
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the access pending page, check the
		// required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2: Remove from license
		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 5: Accept the terms and conditions when approved
		// user logs in again
		TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildEmail, ChildPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		}
		termsOfUsePage.clickAcceptBtn();
		// End of Precondition

		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(), "Failed to display the homepage");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Profiles] - Add 'Manage Profiles' item the My Account drop list in the header (TC11479)")
	public void tc2352(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isProfilesDisplayed(),
				"Failed to display the 'Profiles' link under My Accounts menu");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiNonAdminLicense", description = "[Customer Admin - Profiles] - Verify PLAT Non admin user (TC11480)")
	public void tc2353(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(!MyAccount.isProfilesDisplayed(),
				"The 'Profiles' link under My Accounts menu should not be displayed for this user");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlusLicense", description = "[Customer Admin - Profiles] - Verify for PLUS license (TC11481)")
	public void tc2354(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(!MyAccount.isProfilesDisplayed(),
				"The 'Profiles' link under My Accounts menu should not be displayed for this user");
		homePage.clickOnSignOutButton();
	}

	// test data : precondition: uncheck notify check box or provide email to
	// notify after checking
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - License Preferences] Allow admin to require approval of users who attach to license - To Verify that Allow admin to require approval of users who attach to license (TC10061)")
	public void tc2334(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		if (!licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
		}
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredChecked(),
				"Failed to get the approval required checked");
		licensePreferencePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	// test data : precondition: uncheck notify check box or provide email to
	// notify after checking // Remove user from license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithoutAccept", description = "[Customer Admin - License Preferences] Allow admin to require approval of users who attach to license - To Verify that new user will get the access pending page after admin as done the Changes in License preference page (TC10062)")
	public void tc2335(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1: to remove the user so that terms and access page can
		// be displayed when added to license
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		if (!licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
		}
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredChecked(),
				"Failed to get the approval required checked");
		licensePreferencePage.switchToDefault();

		homePage.clickOnSignOutButton();

		LoginPage loginPage = new LoginPage(getDriver());
		AccessPendingPage accessPendingPage = new AccessPendingPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			accessPendingPage = activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();

		}

		Assert.assertTrue(accessPendingPage.getCurrentURL().contains("AccessPending"),
				"Failed to display the Access Pending Page");
		homePage.clickOnSignOutButton();

		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		// Precondition 1: to remove the user so that terms and access page can
		// be displayed when added to license
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		homePage.clickOnMyAccountLink();
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
		}
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(!licensePreferencePage.isApprovalRequiredChecked(),
				"Failed to get the approval required checked");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
		TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		}
		Assert.assertTrue(termsOfUsePage.getCurrentURL().contains("Terms"),
				"Failed to display the Access Pending Page");
		homePage.clickOnSignOutButton();

		// Post Condition:
		loginAs(AdminEmail, AdminPassword);

		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// test data : precondition: uncheck notify check box or provide email to
	// notify after checking // Remove user from license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicenseWithoutAccept", description = "[Customer Admin - License Preferences] Allow admin to require approval of users who attach to license - To Verify that new user will not get the access pending page after admin as done the Changes in License prefrence page (TC10063)")
	public void tc2336(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1: to remove the user so that terms and access page can
		// be displayed when added to license
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildAdminEmail);
		adminUsersPage.switchToDefault();

		homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
		}
		licensePreferencePage.clickOnSaveButton();
		Assert.assertTrue(!licensePreferencePage.isApprovalRequiredChecked(),
				"Failed to get the approval required checked");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

		LoginPage loginPage = new LoginPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
		TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

		}
		Assert.assertTrue(termsOfUsePage.getCurrentURL().contains("Terms"),
				"Failed to display the Access Pending Page");
		homePage.clickOnSignOutButton();

		// Post Condition:
		loginAs(AdminEmail, AdminPassword);

		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// test data : precondition: uncheck notify check box or provide email to
	// notify after checking // Remove user from license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - License Preferences] Allow admin to choose to be notified of users who attach to license - To Verify the notify admin check box License Prefrence page (TC10066)")
	public void tc2337(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isNotifyAdminDisplayed(),
				"Failed to display the notify admin checkbox in the license preference page");

		if (licensePreferencePage.isNotifyAdminChecked()) {
			licensePreferencePage.clickOnNotifyAdminChk();
			licensePreferencePage.clickOnSaveButton();

		}
		Assert.assertTrue(!licensePreferencePage.isNotifyAdminChecked(), "Failed to get the approval required checked");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition:
		loginAs(AdminEmail, AdminPassword);

		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isNotifyAdminChecked()) {
			licensePreferencePage.clickOnNotifyAdminChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - License Preferences] Create license preferences page - To Verify the License Preference page (TC10097)")
	public void tc2338(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();

		Assert.assertTrue(!homePage.verifyCommonTabHighlighted(), "Failed to get all the tabs non highlighted");

		Assert.assertTrue(licensePreferencePage.isBreadCrumbCorrect("My Account - License Preferences"),
				"Failed to display the expected bread crumb");

		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isNotifyAdminDisplayed(),
				"Failed to display the notify admin checkbox in the license preference page");

		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");

		Assert.assertTrue(licensePreferencePage.isLicenseDisplayed(),
				"Failed to display the license label in the license preference page");

		licensePreferencePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiNonAdminLicense", description = "[Customer Admin - License Preferences] Create license preferences page - To Verify that non Admin user will not be able to access the License Preference page (TC10098)")
	public void tc2339(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);

		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		Assert.assertTrue(!MyAccount.isLicensePreferenceDisplayed(),
				"The license preference page shoud not be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - License Preferences] Create license preferences page - To Verify the Admin user Drop down in License preference page (TC10099)")
	public void tc2340(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.verifyAdminListSorted(), "Failed to get the Admin list sorted");
		licensePreferencePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Manage Users] - Style guide updates to Manage Users (TC10264)")
	public void tc2341(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.verifyUserToolTip(), "Failed to display the tooltip");
		Assert.assertTrue(adminUsersPage.isLicenseNumberDisplayed(), "Failed to display the license number");
		Assert.assertTrue(adminUsersPage.isSelectAllCheckBoxDisplayed(), "Failed to display the 'Select All' checkbox");
		Assert.assertTrue(adminUsersPage.isUserCountDisplayed(), "Failed to display the user count");

		adminUsersPage.switchToDefault();
		homePage.clickOnMyAccountLink();
		MyRegistrationInfoPage myRegistrationInfoPage = MyAccount.clickOnMyRegistration();

		Assert.assertTrue(myRegistrationInfoPage.isEditPersonalInfoDisplayed(),
				"Failed to display the My registration page");
		myRegistrationInfoPage.switchToPreviousTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Manage Users] - Style guide updates to License Preferences (TC10265)")
	public void tc2342(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();

		Assert.assertTrue(licensePreferencePage.isLicenseDisplayed(),
				"Failed to display the license label in the license preference page");
		Assert.assertTrue(!licensePreferencePage.verifyIsApprovalRequiredLabelInBold(),
				"The Approval Required label should not be displayed in bold");
		Assert.assertTrue(!licensePreferencePage.verifyIsNotifyAdminLabelInBold(),
				"The Notify Admin label should not be displayed in bold");
		Assert.assertTrue(!licensePreferencePage.verifyIsEnablePDFViewerLabelInBold(),
				"The Notify Admin label should not be displayed in bold");

		licensePreferencePage.switchToDefault();
		MyRegistrationInfoPage myRegistrationInfoPage = MyAccount.clickOnMyRegistration();
		Assert.assertTrue(myRegistrationInfoPage.isEditPersonalInfoDisplayed(),
				"Failed to display the My registration page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Profiles] Verify the entry points, page title and breadcrumb (TC11465)")
	public void tc2346(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		Assert.assertTrue(MyAccount.isProfilesDisplayed(),
				"Failed to display the Manage Profiles link under My Accounts menu");
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");
		adminUsersPage.clickOnProfilesLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		Assert.assertTrue(adminUsersPage.isBreadCrumbCorrect("My Account - Profiles"),
				"Failed to display the expected bread crumb");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[Customer Admin - Profiles] - Verify the Manage Profiles page UI elements (TC11466)")
	public void tc2347(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		Assert.assertTrue(!manageProfilesPage.verifyCommonTabHighlighted(),
				"Failed to get all the tabs non highlighted");

		Assert.assertTrue(manageProfilesPage.verifyMyAccountsAdminToolsOptions(),
				"Failed to verify the My Accounts - Admin Tools options");
		Assert.assertTrue(manageProfilesPage.verifyMyAccountsAccountToolsOptions(),
				"Failed to verify the My Accounts - Account Tools Options");
		manageProfilesPage.switchToFrame();
		Assert.assertTrue(manageProfilesPage.isLicenseNumberDisplayed(), "Failed to display the license number");
		Assert.assertTrue(manageProfilesPage.isAddProfileButtonDisplayed(),
				"Failed to display the 'Add Profile' button");

		Assert.assertTrue(manageProfilesPage.isProfileCountLabelDisplayed(), "Failed to display the profile count");
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify the profile elements on the Manage Profiles page (TC11467)")
	public void tc2348(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		LoginPage loginPage = new LoginPage(getDriver());
		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();

			// Precondition 3 : Check again in the users list with the admin
			// user in
			// order to toggle the
			// the child admin user into admin

			loginAs(AdminEmail, AdminPassword);
			homePage.clickOnMyAccountLink();
			MyAccount.clickOnUsersLink();
			adminUsersPage.switchToFrame();
			nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
			adminUsersPage.switchToDefault();
			homePage.clickOnSignOutButton();
		}
		// End of Precondition

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition

		manageProfilesPage.switchToFrame();
		Assert.assertTrue(manageProfilesPage.isProfileNameDisplayed(), "Failed to display the profile name");
		Assert.assertTrue(manageProfilesPage.isProfileDescriptionDisplayed(),
				"Failed to display the profile description");
		Assert.assertTrue(manageProfilesPage.isProfileDescriptionLengthBelow50(),
				"Failed to get the profile description length as expected");
		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		Assert.assertTrue(manageProfilesPage.isEditLinkDisplayed(), "Failed to display the 'Edit' link");
		Assert.assertTrue(manageProfilesPage.isDeleteLinkDisplayed(), "Failed to display the 'Delete' link");

		// Delete the profile list
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();
		// Post Condition: To revert the child admin user's role as USER

		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		// homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] Verify the entry points, page title and breadcrumb on show users page (TC11476)")
	public void tc2349(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		LoginPage loginPage = new LoginPage(getDriver());
		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();

			// Precondition 3 : Check again in the users list with the admin
			// user in
			// order to toggle the
			// the child admin user into admin

			loginAs(AdminEmail, AdminPassword);
			homePage.clickOnMyAccountLink();
			MyAccount.clickOnUsersLink();
			adminUsersPage.switchToFrame();
			nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
			adminUsersPage.switchToDefault();
			homePage.clickOnSignOutButton();
		}
		// End of Precondition

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition 4 : Add Profile to display in the list
		manageProfilesPage.AddUserProfile();
		// End of precondition

		homePage.clickOnMyAccountLink();

		MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToFrame();
		String userName = manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToDefault();

		Assert.assertTrue(
				manageProfilesPage
						.isBreadCrumbCorrect("My Account - Profiles > Show Users for project profile: " + userName),
				"Failed to display the expected bread crumb");

		Assert.assertTrue(!manageProfilesPage.verifyCommonTabHighlighted(),
				"Failed to get all the tabs non highlighted");

		// Delete the profile list
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnProfiles();
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();
		// Post Condition: To revert the child admin user's role as USER

		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// Precondition : No Users should be assigned a profile
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify the UI elements on the show users page (TC11477)")
	public void tc2350(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();

		// Precondition 2 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 3: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		LoginPage loginPage = new LoginPage(getDriver());
		if (nameMatched.isEmpty()) {
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildAdminEmail, ChildAdminPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();

			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();

			// Precondition 4 : Check again in the users list with the admin
			// user in
			// order to toggle the
			// the child admin user into admin

			loginAs(AdminEmail, AdminPassword);
			homePage.clickOnMyAccountLink();
			MyAccount.clickOnUsersLink();
			adminUsersPage.switchToFrame();
			nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
			adminUsersPage.switchToDefault();
			homePage.clickOnSignOutButton();
		}

		// Precondition 5 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();

		// Precondition 6 : Remove from profile
		manageProfilesPage.switchToFrame();
		String userName = manageProfilesPage.clickOnShowUsersLink();
		manageProfilesPage.clickOnRemoveFromProfile();
		manageProfilesPage.switchToDefault();
		// End of precondition

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();
		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnShowUsersLink();

		Assert.assertTrue(manageProfilesPage.getShowUsersProfileName().equalsIgnoreCase(userName),
				"Failed to get the username in the show users page as expected");
		Assert.assertTrue(manageProfilesPage.isShowUsersDoneButtonDisplayed(),
				"Failed to display the Done Button for Show Users page");
		Assert.assertTrue(manageProfilesPage.verifyShowUserProfileOptions(),
				"Failed to match the user profiles filter options");
		Assert.assertTrue(
				manageProfilesPage.getSelectedShowUserProfileOptions()
						.equals(DGNEnum.MyAccountShowUserOptions.AVAIL_ASSIGN_PROFILE.description()),
				"Failed to get the selected option as expected when there are no profiles assigned");

		manageProfilesPage.clickActionsDropDownForAssign();
		Assert.assertTrue(manageProfilesPage.isAssignProfileLinkDisplayedUnderActionsDropDown(),
				"Failed to display the 'Assign This Profile' link under the Actions Drop down");

		manageProfilesPage.clickOnAssignProfileForFirstUser();
		manageProfilesPage.switchToDefault();

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnShowUsersLink();

		Assert.assertTrue(
				manageProfilesPage.getSelectedShowUserProfileOptions()
						.equals(DGNEnum.MyAccountShowUserOptions.CURRENTLY_ASSIGNED_PROFILE.description()),
				"Failed to get the selected option as expected when there are no profiles assigned");

		manageProfilesPage.clickOnRemoveFromProfile();
		Assert.assertTrue(manageProfilesPage.getErrorMessage()
				.equalsIgnoreCase("There are no users currently assigned this profile"));
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.clickOnBreadCrumbManageProfileLink();
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	// Precondition : No Users should be assigned a profile
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] Verify the users' list view on the show users page (TC11478)")
	public void tc2351(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();

		// Remove from profile
		manageProfilesPage.switchToFrame();
		String userName = manageProfilesPage.clickOnShowUsersLink();
		manageProfilesPage.clickOnRemoveFromProfile();
		manageProfilesPage.switchToDefault();
		// End Of Precondition

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnShowUsersLink();

		Assert.assertTrue(manageProfilesPage.isShowUsersDoneButtonDisplayed(),
				"Failed to display the Done Button for Show Users page");

		Assert.assertTrue(
				manageProfilesPage.getSelectedShowUserProfileOptions()
						.equals(DGNEnum.MyAccountShowUserOptions.AVAIL_ASSIGN_PROFILE.description()),
				"Failed to get the selected option as expected when there are no profiles assigned");

		Assert.assertTrue(manageProfilesPage.getUserNamesSize() == manageProfilesPage.getUserCount()
				&& manageProfilesPage.verifyUserNameFormat(), "Failed to get the expected name format");

		Assert.assertTrue(manageProfilesPage.getUserIconSize() == manageProfilesPage.getUserCount(),
				"Failed to get the icon beside every user");

		manageProfilesPage.clickOnAssignProfileForFirstUser();
		manageProfilesPage.switchToDefault();

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnShowUsersLink();

		Assert.assertTrue(
				manageProfilesPage.getSelectedShowUserProfileOptions()
						.equals(DGNEnum.MyAccountShowUserOptions.CURRENTLY_ASSIGNED_PROFILE.description()),
				"Failed to get the selected option as expected when there are no profiles assigned");

		manageProfilesPage.clickOnRemoveFromProfile();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.clickOnBreadCrumbManageProfileLink();
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify entry points to add/edit a project profile dialog (TC11502)")
	public void tc2355(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");
		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Add Project Profile"),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");
		manageProfilesPage.clickOnCancelButton();
		String name = manageProfilesPage.clickOnEditProjectProfile();
		Assert.assertTrue(
				manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Edit Project Profile: " + name),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");
		manageProfilesPage.clickOnCancelButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify the add/edit project profile dialog elements (TC11508)")
	public void tc2356(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");
		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Add Project Profile"),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");
		Assert.assertTrue(manageProfilesPage.isAddProfileNameDisplayed(),
				"Failed to display the name in Add Project Profiles");
		Assert.assertTrue(manageProfilesPage.isAddProfileDescriptionDisplayed(),
				"Failed to display the description in Add Project Profiles");
		Assert.assertTrue(manageProfilesPage.isGeographyFacetContainerDisplayed(),
				"Failed to display the Geography Facet Container");
		Assert.assertTrue(manageProfilesPage.isProjectTypeFacetContainerDisplayed(),
				"Failed to display the Project Type Facet Container");
		Assert.assertTrue(manageProfilesPage.isActionStageFacetContainerDisplayed(),
				"Failed to display the Action Stage Facet Container");
		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");
		manageProfilesPage.clickOnGeographyDoneButton();
		manageProfilesPage.clickOnProjectTypeEditButton();
		Assert.assertTrue(manageProfilesPage.isProjectTypesPanelDisplayed(),
				"Failed to display the Project Types Attributes Panel");
		manageProfilesPage.clickOnProjectTypeDoneButton();
		manageProfilesPage.clickOnActionStageEditButton();
		Assert.assertTrue(manageProfilesPage.isActionStagePanelDisplayed(),
				"Failed to display the Action Stage Attributes Panel");
		manageProfilesPage.clickOnActionStageDoneButton();
		manageProfilesPage.clickOnCancelButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify the add/edit project profile dialog elements (TC11508)")
	public void tc2367(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");

		Assert.assertTrue(manageProfilesPage.isStateDisplayed(), "Failed to display state in the geography section");
		manageProfilesPage.clickOnGeographyDoneButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Field level validation on name field in the add project profile dialog (TC11513)")
	public void tc2357(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		Assert.assertTrue(manageProfilesPage.verifyCursorFocusOnName(),
				"Failed to get the cursor focus on the Profile Name under Add Project Profile");

		char[] specialCharArray = new char[10];
		Arrays.fill(specialCharArray, '(');
		String textSpecialChar = new String(specialCharArray);
		manageProfilesPage.enterTextInProfileName(textSpecialChar);
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile().equalsIgnoreCase(
						"Profile name can only include alphanumeric characters and spaces or underscores"),
				"Failed to get the error message in Add/Edit Project Profile");

		char[] validCharArrayMoreThan50 = new char[51];
		Arrays.fill(validCharArrayMoreThan50, 'A');
		String textMoreThan50ValidChar = new String(validCharArrayMoreThan50);

		textMoreThan50ValidChar = manageProfilesPage.formatValid(textMoreThan50ValidChar);
		manageProfilesPage.enterTextInProfileName(textMoreThan50ValidChar);
		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile()
						.equalsIgnoreCase("Profile description cannot be blank"),
				"Failed to accept the profile name under Add Project Profile");

		Assert.assertTrue(manageProfilesPage.getProfileNameLength() <= 50,
				"Failed to apply max length of 50 for the Profile Name under Add Project Profile");
		manageProfilesPage.clickOnCancelButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Field level validation on description field in the add project profile dialog (TC11522)")
	public void tc2358(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		char[] specialCharArray = new char[10];
		Arrays.fill(specialCharArray, '(');
		String textSpecialChar = new String(specialCharArray);

		manageProfilesPage.enterTextInProfileName(textSpecialChar);
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile().equalsIgnoreCase(
						"Profile name can only include alphanumeric characters and spaces or underscores"),
				"Failed to get the error message in Add/Edit Project Profile");

		// Precondition 2 : Enter valid name to get error msg for description
		char[] CharArray = new char[10];
		Arrays.fill(CharArray, 'A');
		String textChar = new String(CharArray);
		manageProfilesPage.enterTextInProfileName(textChar);

		char[] validCharArrayMoreThan50 = new char[51];
		Arrays.fill(validCharArrayMoreThan50, 'A');
		String textMoreThan50ValidChar = new String(validCharArrayMoreThan50);

		manageProfilesPage.enterTextInProfileDescription(textMoreThan50ValidChar);
		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(manageProfilesPage.getProfileNameLength() <= 50,
				"Failed to apply max length of 50 for the Profile description under Add Project Profile");

		char[] descCharArray = new char[10];
		Arrays.fill(descCharArray, '(');
		String desctextChar = new String(descCharArray);
		manageProfilesPage.enterTextInProfileDescription(desctextChar);
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(
				!manageProfilesPage.getErrorMessageInAddEditProfile().equalsIgnoreCase(
						"Profile name can only include alphanumeric characters and spaces or underscores"),
				"Failed to get the error message in Add/Edit Project Profile");
		manageProfilesPage.clickOnCancelButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify error messages in add/edit project profile dialog (TC11542)")
	public void tc2364(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		char[] validName = new char[11];
		Arrays.fill(validName, 'A');
		String textName = new String(validName);

		textName = manageProfilesPage.formatValid(textName);
		manageProfilesPage.enterTextInProfileName(textName);

		char[] validDesc = new char[11];
		Arrays.fill(validDesc, 'B');
		String textDesc = new String(validDesc);

		manageProfilesPage.enterTextInProfileDescription(textDesc);

		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile()
						.equalsIgnoreCase("You must make at least one selection in order to save a profile"),
				"Failed to get the expected message under Add Project Profile");
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Create new project profile (TC11540)")
	public void tc2362(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		char[] validName = new char[11];
		Arrays.fill(validName, 'A');
		String textName = new String(validName);

		textName = manageProfilesPage.formatValid(textName);
		manageProfilesPage.enterTextInProfileName(textName);

		char[] validDesc = new char[11];
		Arrays.fill(validDesc, 'B');
		String textDesc = new String(validDesc);

		manageProfilesPage.enterTextInProfileDescription(textDesc);

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");

		manageProfilesPage.clickSelectAllGeography();

		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(!manageProfilesPage.isErrorMessageInAddEditProfileDisplayed(),
				"Failed to create the profile successfully");
		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify the cancel functionality in add/edit project profile dialog (TC11539)")
	public void tc2361(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();

		// End of Precondition
		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnFirstEditProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Edit Project Profile ");

		char[] validName = new char[11];
		Arrays.fill(validName, 'A');
		String textName = new String(validName);
		textName = manageProfilesPage.formatValid(textName);
		manageProfilesPage.enterTextInProfileName(textName);

		char[] validDesc = new char[11];
		Arrays.fill(validDesc, 'B');
		String textDesc = new String(validDesc);

		manageProfilesPage.enterTextInProfileDescription(textDesc);

		manageProfilesPage.clickOnCancelButton();

		Assert.assertTrue(!manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to remove the Edit Project Profile ");

		manageProfilesPage.clickOnAddProfile();

		textName = manageProfilesPage.formatValid(textName);
		manageProfilesPage.enterTextInProfileName(textName);

		manageProfilesPage.enterTextInProfileDescription(textDesc);

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");

		manageProfilesPage.clickSelectAllGeography();

		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnCancelButton();

		Assert.assertTrue(!manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to remove the Edit Project Profile ");
		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify the manage profiles page (TC11559)")
	public void tc2368(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Edit Project Profile ");

		manageProfilesPage.clickOnCancelButton();

		manageProfilesPage.clickOnFirstEditProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Edit Project Profile ");

		manageProfilesPage.clickOnCancelButton();

		String userName = manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToDefault();

		Assert.assertTrue(
				manageProfilesPage
						.isBreadCrumbCorrect("My Account - Profiles  > Show Users for project profile: " + userName),
				"Failed to display the expected bread crumb");

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();
		manageProfilesPage.clickOnFirstDeleteProfileWithOK();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Edit existing project profile (TC11541)")
	public void tc2363(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnFirstEditProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Edit Project Profile ");

		// Precondition : profile name save which should be duplicated
		char[] validName = new char[11];
		Arrays.fill(validName, 'A');
		String textName = new String(validName);
		manageProfilesPage.enterTextInProfileName(textName);
		manageProfilesPage.clickOnSaveButton();

		manageProfilesPage.clickOnSecondEditProfile();

		manageProfilesPage.enterTextInProfileName(textName);
		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile()
						.equalsIgnoreCase("Profile name already exists, please choose another name"),
				"Failed to get the expected message under Add Project Profile");

		manageProfilesPage.enterTextInProfileName(textName);
		manageProfilesPage.clickOnSaveButton();

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] Create view to add/edit a project profile - API integration (Ready for QA)-To verify the manage profile report page in Network for Project . (TC12120)")
	public void tc2403(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		Assert.assertTrue(manageProfilesPage.isLicenseNumberDisplayed(), "Failed to display the license number");
		Assert.assertTrue(manageProfilesPage.isAddProfileButtonDisplayed(),
				"Failed to display the 'Add Profile' button");

		Assert.assertTrue(manageProfilesPage.isProfileCountLabelDisplayed(), "Failed to display the profile count");
		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		Assert.assertTrue(manageProfilesPage.isEditLinkDisplayed(), "Failed to display the 'Edit' link");
		Assert.assertTrue(manageProfilesPage.isDeleteLinkDisplayed(), "Failed to display the 'Delete' link");
		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] Create view to add/edit a project profile - API integration (Ready for QA)-Verify the modal dialog for Add Project Profile Button (TC12121)")
	public void tc2404(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Edit Project Profile ");

		Assert.assertTrue(manageProfilesPage.isProfileTxtNameDisplayed(), "Failed to display the profile name");
		Assert.assertTrue(manageProfilesPage.isProfileTxtDescriptionDisplayed(),
				"Failed to display the profile description");
		Assert.assertTrue(manageProfilesPage.isSaveButtonDisplayed(), "Failed to display the 'Save' button");
		Assert.assertTrue(manageProfilesPage.isCancelButtonDisplayed(), "Failed to display the 'Cancel' button");

		Assert.assertTrue(manageProfilesPage.isGeographyFacetContainerDisplayed(),
				"Failed to display the Geography Facet Container");
		Assert.assertTrue(manageProfilesPage.isProjectTypeFacetContainerDisplayed(),
				"Failed to display the Project Type Facet Container");
		Assert.assertTrue(manageProfilesPage.isActionStageFacetContainerDisplayed(),
				"Failed to display the Action Stage Facet Container");

		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify show users page with users filter - Available to assign this profile (TC11561)")
	public void tc2369(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.clickAvailShowUserProfileOptions();

		manageProfilesPage.clickAssignThisProfileLinkForFirstProfile();
		manageProfilesPage.clickActionsDropDownForAssign();
		manageProfilesPage.clickAssignThisProfileLinkUnderActionsDropdown();
		manageProfilesPage.waitforLoadingRing();
		Assert.assertTrue(manageProfilesPage.getErrorMessageonPage().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected error message");

		manageProfilesPage.clickOnFirstProfileChk();
		manageProfilesPage.clickActionsDropDownForAssign();
		manageProfilesPage.clickAssignThisProfileLinkUnderActionsDropdown();

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify show users page with users filter - Available to assign this profile (TC11561)")
	public void tc2429(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.clickAvailShowUserProfileOptions();

		manageProfilesPage.clickAssignThisProfileLinkForFirstProfile();

		manageProfilesPage.clickOnFirstProfileChk();
		manageProfilesPage.clickActionsDropDownForAssign();
		manageProfilesPage.clickAssignThisProfileLinkUnderActionsDropdown();

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify show users page with users filter - Currently assigned this profile (TC11562)")
	public void tc2370(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition
		
		manageProfilesPage.switchToFrame();
		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		// Precondition: To assign ercord first in order to remove
		manageProfilesPage.clickAvailShowUserProfileOptions();
		manageProfilesPage.clickAssignThisProfileLinkForFirstProfile();
		manageProfilesPage.clickAssignThisProfileLinkForFirstProfile();
		// End
		
		manageProfilesPage.clickCurrentShowUserProfileOptions();
		manageProfilesPage.clickActionsDropDownForRemove();
		manageProfilesPage.clickRemoveThisProfileLinkUnderActionsDropdown();
		Assert.assertTrue(manageProfilesPage.getErrorMessageonPage().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected error message");
		manageProfilesPage.clickOnFirstProfileChk();
		manageProfilesPage.clickActionsDropDownForRemove();
		manageProfilesPage.clickRemoveThisProfileLinkUnderActionsDropdown();
		manageProfilesPage.switchToDefault();
		
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles] - Verify remove from profile - show users page (TC11666)")
	public void tc2373(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		manageProfilesPage.AddUserProfile();
		// End of Precondition
		
		manageProfilesPage.switchToFrame();
		Assert.assertTrue(manageProfilesPage.isShowUsersLinkDisplayed(), "Failed to display the 'Show Users' link");
		manageProfilesPage.clickOnShowUsersLink();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		
		// Precondition: To assign ercord first in order to remove
		manageProfilesPage.clickAvailShowUserProfileOptions();
		manageProfilesPage.clickAssignThisProfileLinkForFirstProfile();
		// End
		
		manageProfilesPage.clickCurrentShowUserProfileOptions();
		manageProfilesPage.clickRemoveThisProfileLinkForFirstProfile();
		manageProfilesPage.switchToDefault();
		
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Manage Profiles - Project] - Verify remove from profile - Manage Profiles page (TC11668)")
	public void tc2374(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();

		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		// manageProfilesPage.AddUserProfile();

		MyAccount.clickOnUsersLink();
		// adminUsersPage.clickUserProfileListOptions();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2375(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		adminUsersPage.clickReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2376(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		Assert.assertTrue(adminReportsPage.getTitle().equalsIgnoreCase("Dodge Global Network - My Account - Reports"));

		Assert.assertTrue(!homePage.verifyCommonTabHighlighted(), "Failed to get all the tabs non highlighted");

		Assert.assertTrue(adminReportsPage.isBreadCrumbCorrect("My Account - Reports"),
				"Failed to display the expected bread crumb");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2377(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");
		adminReportsPage.switchToFrame();
		Assert.assertTrue(adminReportsPage.isLicenseLabelDisplayed(), "Failed to display the license number");

		String selectedOption = adminReportsPage.getSelectedReportTypeOptions();
		Assert.assertTrue(selectedOption.equals("Active User"), "Failed to get the expected selected options");
		Assert.assertTrue(adminReportsPage.verifyReportTypeOptions(), "Failed to get the expected options");
		Assert.assertTrue(adminReportsPage.isRunButtonDisplayed(), "Failed to display the 'Run' button");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2379(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");

		adminUsersPage.clickReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2384(String AdminEmail, String AdminPassword) throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");
		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.COMPANY_DOWNLOAD_DETAIL.description());
		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		Assert.assertTrue(
				adminReportsPage.getFromDateText().equals(adminReportsPage.selectLastWeekBackCreatedBetween()),
				"Failed to get the expected date selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2387(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.PROJECT_DOWNLOAD_SUMMARY.description());

		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		Assert.assertTrue(
				adminReportsPage.getFromDateText().equals(adminReportsPage.selectLastWeekBackCreatedBetween()),
				"Failed to get the expected date selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2392(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.COMPANY_DOWNLOAD_SUMMARY.description());

		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		Assert.assertTrue(
				adminReportsPage.getFromDateText().equals(adminReportsPage.selectLastWeekBackCreatedBetween()),
				"Failed to get the expected date selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2394(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.PROJECT_DOWNLOAD_DETAIL.description());

		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		Assert.assertTrue(
				adminReportsPage.getFromDateText().equals(adminReportsPage.selectLastWeekBackCreatedBetween()),
				"Failed to get the expected date selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2405(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnFirstEditProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");

		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().contains("Edit Project Profile"),
				"Failed to get the modal dialog with 'Edit Project Profile' as expected");

		Assert.assertTrue(manageProfilesPage.isAddProfileNameDisplayed(),
				"Failed to display the name in Edit Project Profiles");
		Assert.assertTrue(manageProfilesPage.isAddProfileDescriptionDisplayed(),
				"Failed to display the description in Edit Project Profiles");
		Assert.assertTrue(manageProfilesPage.isGeographyFacetContainerDisplayed(),
				"Failed to display the Geography Facet Container");
		Assert.assertTrue(manageProfilesPage.isProjectTypeFacetContainerDisplayed(),
				"Failed to display the Project Type Facet Container");
		Assert.assertTrue(manageProfilesPage.isActionStageFacetContainerDisplayed(),
				"Failed to display the Action Stage Facet Container");

		Assert.assertTrue(manageProfilesPage.isEditGeographyDisplayed(),
				"Failed to display Edit button on Geography Attributes Panel");

		Assert.assertTrue(manageProfilesPage.isEditProjectTypeDisplayed(),
				"Failed to display Edit button on Project Types Attributes Panel");

		Assert.assertTrue(manageProfilesPage.isEditActionStageDisplayed(),
				"Failed to display Edit button on Action Stage Attributes Panel");

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2406(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		char[] validDesc = new char[51];
		Arrays.fill(validDesc, '$');
		String textSpecialChar = new String(validDesc);

		manageProfilesPage.enterTextInProfileName(textSpecialChar);
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile().equalsIgnoreCase(
						"Profile name can only include alphanumeric characters and spaces or underscores"),
				"Failed to get the error message in Add/Edit Project Profile");

		char[] validCharArray = new char[51];
		Arrays.fill(validCharArray, '(');
		String textValidChar = new String(validCharArray);

		textValidChar = manageProfilesPage.formatValid(textValidChar);
		manageProfilesPage.enterTextInProfileName(textSpecialChar);
		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile().equalsIgnoreCase(
						"Profile name can only include alphanumeric characters and spaces or underscores"),
				"Failed to accept the profile name under Add Project Profile");

		Assert.assertTrue(manageProfilesPage.getProfileNameLength() <= 50,
				"Failed to apply max length of 50 for the Profile Name under Add Project Profile");
		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2407(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();

		char[] validCharArray = new char[51];
		Arrays.fill(validCharArray, 'A');
		String textValidChar = new String(validCharArray);

		textValidChar = manageProfilesPage.formatValid(textValidChar);
		manageProfilesPage.enterTextInProfileName(textValidChar);
		manageProfilesPage.clickOnSaveButton();

		String textSpecialChar = "";

		manageProfilesPage.enterTextInProfileDescription(textSpecialChar);
		manageProfilesPage.clickOnSaveButton();
		Assert.assertTrue(
				manageProfilesPage.getErrorMessageInAddEditProfile()
						.equalsIgnoreCase("You must make at least one selection in order to save a profile"),
				"Failed to get the error message in Add/Edit Project Profile");

		char[] validDesc = new char[51];
		Arrays.fill(validDesc, 'A');
		String txtValidDesc = new String(validDesc);

		textValidChar = manageProfilesPage.formatValid(textValidChar);
		manageProfilesPage.enterTextInProfileDescription(textValidChar);
		manageProfilesPage.clickOnSaveButton();

		Assert.assertTrue(manageProfilesPage.getProfileNameLength() <= 50,
				"Failed to apply max length of 50 for the Profile description under Add Project Profile");
		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2408(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");

		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Add Project Profile"),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");

		Assert.assertTrue(manageProfilesPage.isGeographyFacetContainerDisplayed(),
				"Failed to display the Geography Facet Container");
		Assert.assertTrue(manageProfilesPage.isProjectTypeFacetContainerDisplayed(),
				"Failed to display the Project Type Facet Container");
		Assert.assertTrue(manageProfilesPage.isActionStageFacetContainerDisplayed(),
				"Failed to display the Action Stage Facet Container");

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2409(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile");

		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Add Project Profile"),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");

		Assert.assertTrue(manageProfilesPage.isGeographyFacetContainerDisplayed(),
				"Failed to display the Geography Facet Container");
		Assert.assertTrue(manageProfilesPage.isProjectTypeFacetContainerDisplayed(),
				"Failed to display the Project Type Facet Container");
		Assert.assertTrue(manageProfilesPage.isActionStageFacetContainerDisplayed(),
				"Failed to display the Action Stage Facet Container");

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");
		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnProjectTypeEditButton();
		Assert.assertTrue(manageProfilesPage.isProjectTypesPanelDisplayed(),
				"Failed to display the Project Types Attributes Panel");
		manageProfilesPage.clickOnProjectTypeDoneButton();

		manageProfilesPage.clickOnActionStageEditButton();
		Assert.assertTrue(manageProfilesPage.isActionStagePanelDisplayed(),
				"Failed to display the Action Stage Attributes Panel");
		manageProfilesPage.clickOnActionStageDoneButton();

		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2410(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");
		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnProjectTypeEditButton();
		Assert.assertTrue(manageProfilesPage.isProjectTypesPanelDisplayed(),
				"Failed to display the Project Types Attributes Panel");
		manageProfilesPage.clickOnProjectTypeDoneButton();

		manageProfilesPage.clickOnActionStageEditButton();
		Assert.assertTrue(manageProfilesPage.isActionStagePanelDisplayed(),
				"Failed to display the Action Stage Attributes Panel");
		manageProfilesPage.clickOnActionStageDoneButton();

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2411(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();

		manageProfilesPage.clickOnAddProfile();

		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(manageProfilesPage.isGeographyAttributesPanelDisplayed(),
				"Failed to display the Geography Attributes Panel");

		Assert.assertTrue(manageProfilesPage.isSelectAllDisplayed(), "Failed to display the select all button");
		manageProfilesPage.clickSelectAllGeography();

		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnGeographyEditButton();

		manageProfilesPage.deselectUSAchk();

		Assert.assertTrue(!manageProfilesPage.isSelectAllChecked(), "Failed to get the select all checkbox deselected");
		manageProfilesPage.clickSelectAllGeography();

		Assert.assertTrue(manageProfilesPage.isSelectAllChecked(), "Failed to get the select all checkbox selected");
		manageProfilesPage.clickOnGeographyDoneButton();

		manageProfilesPage.clickOnProjectTypeEditButton();

		manageProfilesPage.clickOnGeographyEditButton();
		Assert.assertTrue(!manageProfilesPage.isSelectAllChecked(), "Failed to get the select all checkbox selected");
		manageProfilesPage.clickOnGeographyDoneButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2412(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		int count = manageProfilesPage.getProfileCount();
		manageProfilesPage.clickOnFirstDeleteProfileWithOK();

		Assert.assertTrue(manageProfilesPage.getProfileCount() == count - 1, "Failed to delete the user");

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2413(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		int count = manageProfilesPage.getProfileCount();
		manageProfilesPage.clickOnFirstDeleteProfileWithCancel();

		Assert.assertTrue(manageProfilesPage.getProfileCount() != count - 1, "Record should noy the deleted");

		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2414(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		int count = manageProfilesPage.getProfileCount();
		manageProfilesPage.clickOnFirstDeleteProfileWithOK();

		Assert.assertTrue(manageProfilesPage.getProfileCount() == count - 1, "Failed to delete the user");

		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2415(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		int count = manageProfilesPage.getProfileCount();
		manageProfilesPage.clickOnFirstDeleteProfileWithCancel();

		Assert.assertTrue(manageProfilesPage.getProfileCount() != count - 1, "Record should not be deleted");

		manageProfilesPage.switchToDefault();
		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	// Precondition : No Users should be assigned a profile
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2416(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) throws InterruptedException {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition
		manageProfilesPage.switchToFrame();

		String userName = manageProfilesPage.clickOnShowUsersLink();
		manageProfilesPage.clickOnRemoveFromProfile();
		manageProfilesPage.switchToDefault();

		manageProfilesPage.clickOnBreadCrumbManageProfileLink();

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnShowUsersLink();

		Assert.assertTrue(manageProfilesPage.getShowUsersProfileName().equalsIgnoreCase(userName),
				"Failed to get the username in the show users page as expected");

		manageProfilesPage.clickAvailShowUserProfileOptions();
		if (!manageProfilesPage.isErrorMessageDisplayed()) {

			manageProfilesPage.clickShowUsersSelectAll();

			manageProfilesPage.clickActionsDropDownForAssign();

			manageProfilesPage.clickAssignThisProfileLinkUnderActionsDropdown();
		}
		Assert.assertTrue(manageProfilesPage.getErrorMessageonPage()
				.equalsIgnoreCase("There are no users available to assign this profile"));

		// post condition
		manageProfilesPage.clickCurrentShowUserProfileOptions();
		if (!manageProfilesPage.isErrorMessageDisplayed()) {

			manageProfilesPage.clickShowUsersSelectAll();

			manageProfilesPage.clickActionsDropDownForRemove();

			manageProfilesPage.clickRemoveThisProfileLinkUnderActionsDropdown();
		}
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2438(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.SYSTEM_USAGE.description());
		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		adminReportsPage.clickOnUsersDrop();
		Assert.assertTrue(!adminReportsPage.isSelectAllChecked(), "Failed to get the select all unchecked");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2437(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");
		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.SYSTEM_USAGE.description());
		adminReportsPage = adminReportsPage.clickOnUsersDrop();
		adminReportsPage.checkSelectAll();
		Assert.assertTrue(adminReportsPage.isAllUserChecked(), "Failed to get all the users selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2436(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.SYSTEM_USAGE.description());

		adminReportsPage.clickOnUsersDrop();

		Assert.assertTrue(adminReportsPage.isFirstChkSelectAllInDrp(),
				"Failed to get the first check box as 'Select All'");
		adminReportsPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2435(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.SYSTEM_USAGE.description());

		adminReportsPage.clickOnUsersDrop();

		adminReportsPage.checkRandomUser(1);
		adminReportsPage.checkRandomUser(2);

		Assert.assertTrue(adminReportsPage.isRandomUserChecked(1), "Failed to get the first user selected");
		Assert.assertTrue(adminReportsPage.isRandomUserChecked(2), "Failed to get the second user selected");
		adminReportsPage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2319(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		Assert.assertTrue(adminUsersPage.verifyUserToolTip(), "Failed to display the items properly in the tooltip");
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	// test data : precondition: uncheck notify check box or provide email to
	// notify after checking // Remove user from license
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildPendingLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2322(String AdminEmail, String AdminPassword, String ChildPendingEmail, String ChildPendingPassword,
			String LicenseNumber) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LoginPage loginPage = new LoginPage(getDriver());
		// Precondition 1 : In order to get the access pending page, check the
		// required check box from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (!licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		// Precondition 2 : Remove user from license if added already
		homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();
		// End of Precondition

		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildPendingEmail, false);
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		if (nameMatched.isEmpty()) {
			ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();
			}
			homePage.clickOnSignOutButton();

			// Precondition 4 : Check again in the users list with the admin
			// user in order to approve the pending child user
			loginAs(AdminEmail, AdminPassword);
			homePage.clickOnMyAccountLink();
			MyAccount.clickOnUsersLink();
			adminUsersPage.switchToFrame();
			nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildPendingEmail, false);
			adminUsersPage.clickOnActionsMenu();
			adminUsersPage.clickOnApproveLink();
			adminUsersPage.switchToDefault();
			homePage.clickOnSignOutButton();

			// Precondition 5: Accept the terms and conditions when approved
			// user logs in again
			TermsOfUsePage termsOfUsePage = new TermsOfUsePage(getDriver());

			loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
			if (activationPage.isLicenseInputPageDisplayed()) {
				activationPage.enterValueInLicenseField(LicenseNumber);
				termsOfUsePage = activationPage.clickOnSubmitLicenseButtonToGetTermsPage();
			}
			termsOfUsePage.clickAcceptBtn();
			homePage.clickOnSignOutButton();
		}
		// End of Precondition

		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			AccessPendingPage accessPendingPage = activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();
			Assert.assertTrue(accessPendingPage.getCurrentURL().contains("AccessPending"),
					"Failed to display the Access Pending Page");
			Assert.assertTrue(accessPendingPage.isMessageAsExpected(),
					"Failed to get the message as expected for the access pending page");
			Assert.assertTrue(accessPendingPage.getTitle().equalsIgnoreCase("Dodge Global Network - Access Pending"));
		}
		homePage.clickOnSignOutButton();

		// Post Condition: To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
	}

	// TODO : remove the pending user after execution
	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminAuto2License", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2323(String AdminEmail, String AdminPassword, String ChildPendingEmail, String ChildPendingPassword,
			String LicenseNumber) throws InterruptedException {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");
		LoginPage loginPage = new LoginPage(getDriver());

		// Precondition 1 : In order to get the access pending page, check the
		// required check box from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (!licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
		licensePreferencePage.switchToDefault();
		// End of Precondition

		// Precondition 2 : Remove user from license if added already
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
		// End of Precondition

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();
		}
		homePage.clickOnSignOutButton();

		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Remove user from license if added already
		homePage = loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
		// End of Precondition

		// Precondition 2: If child user is not in the list then Add Child Admin
		// User to license in order to get the user in the list

		loginPage.loginAsNonLicenseUser(ChildPendingEmail, ChildPendingPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			activationPage.clickOnSubmitLicenseButtonToGetAccessPendingPage();
		}
		homePage.clickOnSignOutButton();

		homePage = loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.clickFirstPendingButton();

		Assert.assertFalse(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");
		firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		if (StringUtils.isNotBlank(firstPendingButtonID)) {
			Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
					"Failed to get the expected text");
		}
		String firstApprovedButtonID = adminUsersPage.getFirstApprovedButtonID();
		adminUsersPage.findElements(firstApprovedButtonID);
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstApprovedButtonID, "APPROVED"),
				"Failed to get the expected text");

		// post condition
		adminUsersPage.removeUserFromLicense(ChildPendingEmail);
		adminUsersPage.switchToDefault();
		homePage.clickOnMyAccountLink();
		// Uncheck the required check box back in order to get the terms page in
		// future
		MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isApprovalRequiredDisplayed(),
				"Failed to display the approval required checkbox in the license preference page");
		if (licensePreferencePage.isApprovalRequiredChecked()) {
			licensePreferencePage.clickOnApprovalRequiredChk();
			licensePreferencePage.clickOnSaveButton();
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)", enabled = false)
	public void tc2328(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");

		String firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");

		String firstApprovedButtonID = adminUsersPage.getFirstApprovedButtonID();
		adminUsersPage.findElements(firstApprovedButtonID);
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstApprovedButtonID, "APPROVED"),
				"Failed to get the expected text");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiNonAdminLicenseBoth", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)", enabled = false)
	public void tc2330(String LicenseNumber, String AdminEmail, String AdminPassword, String NonAdminEmail,
			String NonAdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");

		String firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();

		adminUsersPage.clickFirstPendingButton();

		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");
		int count = adminUsersPage.getUserNamesSize();
		// TODO use different admin :
		// adminUsersPage.removeUserFromLicense(NonAdminEmail);

		Assert.assertTrue(adminUsersPage.getUserNamesSize() == count - 1, "Failed to remove the user license");
		firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");

		String firstApprovedButtonID = adminUsersPage.getFirstApprovedButtonID();
		adminUsersPage.findElements(firstApprovedButtonID);
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstApprovedButtonID, "APPROVED"),
				"Failed to get the expected text");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)", enabled = false)
	public void tc2331(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();

		Assert.assertTrue(adminUsersPage.getCurrentURL().contains("ManageUsers"),
				"Failed to display the Manage Profiles Page");

		String firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();

		adminUsersPage.clickFirstPendingButton();

		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");
		int count = adminUsersPage.getUserNamesSize();
		adminUsersPage.removeUserFromLicense(AdminEmail);

		Assert.assertTrue(adminUsersPage.getUserNamesSize() == count - 1, "Failed to remove the user license");
		firstPendingButtonID = adminUsersPage.getFirstPendingButtonID();
		adminUsersPage.clickOnActionsMenu();
		adminUsersPage.clickOnApproveLink();
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstPendingButtonID, "APPROVED"),
				"Failed to get the expected text");

		String firstApprovedButtonID = adminUsersPage.getFirstApprovedButtonID();
		adminUsersPage.findElements(firstApprovedButtonID);
		Assert.assertTrue(adminUsersPage.isElementFoundTextMatched(firstApprovedButtonID, "APPROVED"),
				"Failed to get the expected text");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiNonAdminLicenseBoth", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2343(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();

		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();

		licensePreferencePage.switchToFrame();

		Assert.assertTrue(licensePreferencePage.isNotifyAdminDisplayed(),
				"Failed to display the notify admin checkbox in the license preference page");

		if (licensePreferencePage.isNotifyAdminChecked()) {
			licensePreferencePage.clickOnNotifyAdminChk();
		}
		licensePreferencePage.getAdminSelected(nameMatched);
		licensePreferencePage.clickOnSaveButton();
		licensePreferencePage.switchToDefault();
		adminUsersPage.userRemove(DGNEnum.MyAccountUserFilterOptions.ADMIN, ChildAdminEmail, true);
		Assert.assertTrue(
				adminUsersPage.getTextFromAlert().equalsIgnoreCase(
						"The user you deleted was selected to receive email notifications when anyone attaches themselves to the license. Please go to 'Manage Users' page and set a user who will receive the email notification"),
				"Failed to get the expected message on removing admin user who was selected to receive email notification");

		// Back to original state
		MyAccount.clickOnUsersLink();

		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);

		homePage.clickOnSignOutButton();
		LoginPage loginPage = new LoginPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(nameMatched, AdminPassword);
		activationPage.enterValueInLicenseField(LicenseNumber);
		activationPage.clickOnSubmitButton();
		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(), "Failed to display the homepage");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2441(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.getCurrentURL().contains("AdminReports"),
				"Failed to display the Manage Profiles Page");

		adminReportsPage.switchToFrame();
		adminReportsPage.clickReportOptions(DGNEnum.MyAccountReportsOptions.SYSTEM_USAGE.description());
		Assert.assertTrue(adminReportsPage.isUserDropDwnDisplayed(), "Failed to get the user drop down");
		Assert.assertTrue(adminReportsPage.isFromDateDisplayed(), "Failed to get the 'From Date' drop down");
		Assert.assertTrue(adminReportsPage.isToDateDropDwnDisplayed(), "Failed to get the 'To Date' drop down");
		adminReportsPage.clickOnUsersDrop();
		Assert.assertTrue(!adminReportsPage.isSelectAllChecked(), "Failed to get the select all unchecked");
		String firstUser = adminReportsPage.checkFirstUser();
		Assert.assertTrue(adminReportsPage.getSelectedUserInTable().equals(firstUser),
				"Failed to match the selected user in the user info grid");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2344(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();

		Assert.assertTrue(licensePreferencePage.isNotifyAdminDisplayed(),
				"Failed to display the notify admin checkbox in the license preference page");

		if (!licensePreferencePage.isNotifyAdminChecked()) {
			licensePreferencePage.clickOnNotifyAdminChk();
		}
		licensePreferencePage.clickOnSaveButton();
		licensePreferencePage.switchToDefault();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRemove(DGNEnum.MyAccountUserFilterOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();

		// Back to original state
		MyAccount.clickOnUsersLink();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		homePage.clickOnSignOutButton();

		LoginPage loginPage = new LoginPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(AdminEmail, AdminPassword);
		if (activationPage.isLicenseInputPageDisplayed()) {
			activationPage.enterValueInLicenseField(LicenseNumber);
			activationPage.clickOnSubmitButton();
		}
		homePage = new HomePage(getDriver());
		homePage.waitforLoadingRing();
		Assert.assertTrue(homePage.isHomeMenuLinkDisplayed(), "Failed to display the homepage");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc55(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		myPreferencesPage.clickChkIncludePrivateNotes();
		myPreferencesPage.clickPrivateNotesSaveBtn();

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		MyAccount.clickOnMyPreferences();

		Assert.assertTrue(myPreferencesPage.verifyPrivateNotesChecked(), "Failed to get the private notes checked");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2431(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		LicensePreferencePage licensePreferencesPage = MyAccount.clickOnLicensePreference();
		Assert.assertTrue(licensePreferencesPage.isFooterLogoDodge(),
				"Failed to display the footer logo for Dodge Data & Analytics");

		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		Assert.assertTrue(adminUsersPage.isFooterLogoDodge(),
				"Failed to display the footer logo for Dodge Data & Analytics");

		ManageProfilesPage manageProfilePage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilePage.isFooterLogoDodge(),
				"Failed to display the footer logo for Dodge Data & Analytics");

		AdminReportsPage adminReportsPage = MyAccount.clickOnReportsLink();
		Assert.assertTrue(adminReportsPage.isFooterLogoDodge(),
				"Failed to display the footer logo for Dodge Data & Analytics");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2432(String AdminEmail, String AdminPassword) {
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();

		Assert.assertTrue(myPreferencesPage.isPrintingNotesTabActive(), "Failed to get the printing notes tab active");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2365(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.clickOnProjectTypeEditButton();
		Assert.assertTrue(manageProfilesPage.isProjectTypesPanelDisplayed(),
				"Failed to display the Project Types Attributes Panel");
		Assert.assertTrue(manageProfilesPage.isProfileLevel1Displayed(),
				"Failed to display the Project Type profile level 1");
		Assert.assertTrue(manageProfilesPage.isProfileLevel2Displayed(),
				"Failed to display the Project Type profile level 2");
		Assert.assertTrue(manageProfilesPage.isProfileLevel3Displayed(),
				"Failed to display the Project Type profile level 3");
		manageProfilesPage.clickOnProjectTypeDoneButton();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiAdminLicense", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2366(String AdminEmail, String AdminPassword) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		manageProfilesPage.clickOnActionStageEditButton();
		Assert.assertTrue(manageProfilesPage.isActionStagePanelDisplayed(),
				"Failed to display the Action Stage Attributes Panel");
		Assert.assertTrue(manageProfilesPage.isActionStageLevel1Displayed(),
				"Failed to display the Action Stage profile level 1");
		Assert.assertTrue(manageProfilesPage.isActionStageLevel2Displayed(),
				"Failed to display the Action Stage profile level 2");
		manageProfilesPage.clickOnActionStageDoneButton();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildNonAdminLicense", description = "(Copy of) [Customer Admin - Manage Users] Allow admin to make a user an admin or regular user-To Verify that Admin user is able to change the multiple Admin User  to User (TC10054)")
	public void tc2433(String AdminEmail, String AdminPassword, String ChildNonAdminEmail, String ChildNonAdminPassword,
			String LicenseNumber) {

		// LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : In order to get the terms page, uncheck the required
		// checkbox from license preference
		LicensePreferencePage licensePreferencePage = MyAccount.clickOnLicensePreference();
		licensePreferencePage.switchToFrame();
		Assert.assertTrue(licensePreferencePage.isEnableKeywordDisplayed(),
				"Failed to display the Keyword highlighting checkbox in the license preference page");
		if (!licensePreferencePage.isEnableKeywordHighlightChecked()) {
			licensePreferencePage.clickOnEnableKeywordHighlightChk();
			licensePreferencePage.clickOnSaveButton();
		}
		Assert.assertTrue(licensePreferencePage.isEnableKeywordHighlightChecked(),
				"Failed to check the keyword highting checkbox in the license preference page");
		licensePreferencePage.switchToDefault();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildNonAdminLicense", description = "(Copy of) [Customer Admin - Manage Users] Allow admin to make a user an admin or regular user-To Verify that Admin user is able to change the multiple Admin User  to User (TC10054)")
	public void tc2434(String AdminEmail, String AdminPassword, String ChildNonAdminEmail, String ChildNonAdminPassword,
			String LicenseNumber) {

		// LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();

		Assert.assertTrue(myPreferencesPage.isPrintingNotesTabActive(), "Failed to get the Printing tab active");
		Assert.assertTrue(myPreferencesPage.isDocumentTabDisplay(), "Failed to get the document tab displayed");
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.checkDocumentFilterCheckBox();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomerAdminToolsDataProvider.class, dataProvider = "TCPlatiChildProfileLicense", description = "[Customer Admin - Profiles - Project] - Verify entry points to add/edit a project profile dialog (TC11502)")
	public void tc2444(String AdminEmail, String AdminPassword, String ChildAdminEmail, String ChildAdminPassword,
			String LicenseNumber) {

		HomePage homePage = loginAs(AdminEmail, AdminPassword);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();

		// Precondition 1 : Find the user and Make admin to perform profile
		// functionalities using that user
		AdminUsersPage adminUsersPage = MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		String nameMatched = adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.USER, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Precondition 2 : Add Profile to display in the list
		loginAs(ChildAdminEmail, ChildAdminPassword);
		homePage.clickOnMyAccountLink();
		ManageProfilesPage manageProfilesPage = MyAccount.clickOnProfiles();
		Assert.assertTrue(manageProfilesPage.getCurrentURL().contains("ManageProfiles"),
				"Failed to display the Manage Profiles Page");
		manageProfilesPage.AddUserProfile();
		// End of Precondition

		manageProfilesPage.switchToFrame();
		manageProfilesPage.clickOnAddProfile();
		Assert.assertTrue(manageProfilesPage.isAddEditProjectProfileModalDialogDisplayed(),
				"Failed to display the Add Project Profile ");
		Assert.assertTrue(manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Add Project Profile"),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");
		manageProfilesPage.clickOnCancelButton();
		String name = manageProfilesPage.clickOnEditProjectProfile();
		Assert.assertTrue(
				manageProfilesPage.getAddEditProjectProfileText().equalsIgnoreCase("Edit Project Profile: " + name),
				"Failed to get the modal dialog with 'Add Project Profile' as expected");
		manageProfilesPage.clickOnCancelButton();
		manageProfilesPage.switchToDefault();

		// Post Condition 1 : Delete the profile list
		manageProfilesPage.switchToFrame();
		manageProfilesPage.deleteProfileList();
		manageProfilesPage.switchToDefault();
		homePage.clickOnSignOutButton();

		// Post Condition 2 : To revert the child admin user's role as USER
		loginAs(AdminEmail, AdminPassword);
		homePage.clickOnMyAccountLink();
		MyAccount.clickOnUsersLink();
		adminUsersPage.switchToFrame();
		adminUsersPage.userRoleToggle(DGNEnum.UserRoleOptions.ADMIN, ChildAdminEmail, true);
		adminUsersPage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

}

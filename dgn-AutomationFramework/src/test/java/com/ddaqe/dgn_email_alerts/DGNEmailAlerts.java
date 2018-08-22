package com.ddaqe.dgn_email_alerts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LoginPage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyRegistrationInfoPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ResetPasswordPage;
import com.ddaqe.pages.YopmailPage;

@Listeners(TestListener.class)

public class DGNEmailAlerts extends BaseTest {
	static Logger log = Logger.getLogger(DGNEmailAlerts.class);

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
			"Medium" }, dataProviderClass = DGNEmailAlertsDataProvider.class, dataProvider = "TCChangePwd", description = "Verify the email from changing the password (TC17483)")
	public void tc2922(String emailAddress, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		ResetPasswordPage resetPasswordPage = loginPage.clickResetPwd();
		resetPasswordPage.EnterEmailAddress(emailAddress);
		resetPasswordPage.EnterConfirmEmailAddress(emailAddress);
		resetPasswordPage.clickSubmitButton();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(emailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedForPasswordReset(),
				"Failed to receive mail from Dodge Data and Analytics for password reset");
		yopMailPage.clickSubjectResetPwd();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();

		Assert.assertTrue(yopMailPage.getMailHeader().equalsIgnoreCase("Dodge Data & Analytics - Reset Password"));

		yopMailPage.switchToDefault();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailAlertsDataProvider.class, dataProvider = "TCChangePwd", description = "Verify the email from changing user info (TC17484)")
	public void tc2923(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyRegistrationInfoPage myregistrationPage = myAccount.clickOnMyRegistration();
		myregistrationPage.clickEditPersonalInfo();
		myregistrationPage.enterCompanyName("test");
		myregistrationPage.clickUpdatePersonalInfo();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(emailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedForPasswordReset(),
				"Failed to receive mail from Dodge Data and Analytics for password reset");
		yopMailPage.clickSubjectResetPwd();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		Assert.assertTrue(yopMailPage.getMailHeader().equalsIgnoreCase("Dodge Data & Analytics - Update Profile"));

		yopMailPage.switchToDefault();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailAlertsDataProvider.class, dataProvider = "TCNonAdEmail", description = "Verify update branding in the footer of Customer Admin notification email (TC17703)")
	public void tc2924(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();
		Assert.assertTrue(yopMailPage.isDodgeLogoDisplayed(),
				"Failed to get the footer logo as Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailAlertsDataProvider.class, dataProvider = "TCChangePwd", description = "[SSO] Validation of updated branding in SSO (part deux) (TC17925)")
	public void tc2925(String emailAddress, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		ResetPasswordPage resetPasswordPage = loginPage.clickResetPwd();
		resetPasswordPage.EnterEmailAddress(emailAddress);
		resetPasswordPage.EnterConfirmEmailAddress(emailAddress);
		resetPasswordPage.clickSubmitButton();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(emailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedForPasswordReset(),
				"Failed to receive mail from Dodge Data and Analytics for password reset");
		yopMailPage.clickSubjectResetPwd();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		Assert.assertTrue(yopMailPage.getMailHeader().equalsIgnoreCase("Dodge Data & Analytics - Reset Password"));

		yopMailPage.switchToDefault();

		
	}

}

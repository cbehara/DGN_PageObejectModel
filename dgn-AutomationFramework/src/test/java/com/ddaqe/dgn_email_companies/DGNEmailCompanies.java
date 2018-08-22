package com.ddaqe.dgn_email_companies;

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
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.YopmailPage;

@Listeners(TestListener.class)

public class DGNEmailCompanies extends BaseTest {
	static Logger log = Logger.getLogger(DGNEmailCompanies.class);

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
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1543(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnFirstCompanyChkBox();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

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
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1544(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnFirstCompanyChkBox();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

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
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1545(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		companyResultPage.clickPowerRank();

		companyResultPage.clickOnFirstCompanyChkBox();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

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
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "Verify that usre is able to send Email through Company List view - Email a Company list (TC4256)")
	public void tcDGN1315(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();

		companyResultPage.clickOnFirstCompanyChkBox();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "Verify that user is able to send Email through Profile page - Email a Companylist (TC4244)")
	public void tcDGN1316(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = companyPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}
	
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "DGNT1317", description = "Verify what error message is been displayed when user does not select any Company and Click on Email Project - Email a Company list (TC4257)")
	public void tcDGN1317(String emailAddress, String password, String errorMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		companyResultPage.clickOnActionsDropdown();
		companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		
		Assert.assertEquals(companyResultPage.getErrorMessage(), errorMessage);
		homePage.clickOnSignOutButton();
	}
	
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailCompaniesDataProvider.class, dataProvider = "TCNonAdEmail", description = "Verify that user is able to send Email through Company List view using select all - Email a Company list (TC4258)")
	public void tcDGN1318(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();

		companyResultPage.clickOnSelectAllCompany();
		companyResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}
	
}

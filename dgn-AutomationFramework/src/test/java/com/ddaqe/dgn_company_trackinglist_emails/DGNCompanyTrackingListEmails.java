package com.ddaqe.dgn_company_trackinglist_emails;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.YopmailPage;

@Listeners(TestListener.class)

public class DGNCompanyTrackingListEmails extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanyTrackingListEmails.class);

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
			"Medium" }, dataProviderClass = DGNCompanyTrackingListEmailsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc919(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
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

}

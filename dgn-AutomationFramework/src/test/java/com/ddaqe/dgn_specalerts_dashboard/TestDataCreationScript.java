package com.ddaqe.dgn_specalerts_dashboard;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class TestDataCreationScript extends BaseTest {

	static Logger log = Logger.getLogger(TestDataCreationScript.class);

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

	@Test(dataProviderClass = DGNSpecAlertsDashboardDataProvider.class, dataProvider = "TCNonAdEmail", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void EmailProjects(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();

		homePage.clickOnSignOutButton();
	}

}

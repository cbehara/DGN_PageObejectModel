package com.ddaqe.dgn_specalerts_dashboard;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_specalerts.SpecAlertsDataProvider;
import com.ddaqe.pages.HomePage;

@Listeners(TestListener.class)

public class DGNSpecAlertsDashboard extends BaseTest {
	static Logger log = Logger.getLogger(DGNSpecAlertsDashboard.class);

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
			"Medium" }, dataProviderClass = DGNSpecAlertsDashboardDataProvider.class, dataProvider = "TCAdminUSAInt", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2307(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		homePage.clickOnSpecAlertsTab();
		homePage.clickOnLeadEmailLink();
		homePage.checkLeadEmailDialogCheckbox();

		homePage.enterAddressinTextAreaInLeadEmailDialog(EmailAddress);
		homePage.clickOnSaveInLeadEmailDialog();
		Assert.assertTrue(!homePage.isLeadEmailDialogDisplayed(), "Failed to display Lead Email Dialog");

		homePage.clickOnLeadEmailLink();
		Assert.assertTrue(homePage.getEnteredTextInLeadEmailDialog().equalsIgnoreCase(EmailAddress),
				"Failed to get the saved email address in the lead email settings");
		homePage.clickOnCancelInLeadEmailDialog();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = SpecAlertsDataProvider.class, dataProvider = "TCIntSpecAlerts", description = "[SpecAlerts] Verify the dashboard contents (TC10145)/[SpecAlerts - Dashboard] - Verify the drop list for recent visits (TC10146)")
	public void tc3876(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListTodayDisplayed(),
				"Failed to display Today under SpecAlerts Programs list");
		Assert.assertTrue(homePage.isDashboardSpecAlertsProgramListSinceLastVisitDisplayed(),
				"Failed to display 'Since My Last Visit' under SpecAlerts Programs list");

		homePage.clickOnProgramInSpecAlertsDashboard(1);
		goToBackPage();
		homePage.clickOnDashboardSpecAlertsFirstProgramToday();

		goToBackPage();
		homePage.clickOnDashboardSpecAlertsFirstProgramSinceLastVisit();
		goToBackPage();
		homePage.clickOnRecentLoginDrpDown();
		Assert.assertTrue(homePage.countSelectOptions() == 5, "Failed to display 5 most recent logins");
		homePage.clickOnRecentLogin();

		homePage.clickOnSignOutButton();
	}

}

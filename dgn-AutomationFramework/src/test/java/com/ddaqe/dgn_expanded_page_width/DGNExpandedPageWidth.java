package com.ddaqe.dgn_expanded_page_width;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNExpandedPageWidth extends BaseTest {

	static Logger log = Logger.getLogger(DGNExpandedPageWidth.class);

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
			"Regression" }, dataProviderClass = ExpandedPageWidthDataProvider.class, dataProvider = "TCNonAdEmail", description = "UI-Verify My Search box")
	public void TC3795(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.checkScrollExistifWidthLess1024(),
				"Failed to get the browser display width is less than 1024px, then a horizontal scroll-bar appears");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ExpandedPageWidthDataProvider.class, dataProvider = "TCNonAdEmail", description = "UI-Verify the keyword search text box is left-aligned")
	public void TC3858(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.checkScrollExistifWidthLess1024(),
				"Failed to get the browser display width is less than 1024px, then a horizontal scroll-bar appears");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = ExpandedPageWidthDataProvider.class, dataProvider = "TCNonAdEmail", description = "UI-Verify the keyword search text box is left-aligned")
	public void TC3798(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		Assert.assertTrue(projectResultPage.checkScrollExistifWidthLess1024(),
				"Failed to get the browser display width is less than 1024px, then a horizontal scroll-bar appears");

		homePage.clickOnSignOutButton();

	}

}

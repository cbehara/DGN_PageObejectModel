package com.ddaqe.dgn_project_search_outside_license;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_ecommerce.DGNEcommerceDashboardDataProvider;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNProjectSearchOutsideLicense extends BaseTest {
	static Logger log = Logger.getLogger(DGNProjectSearchOutsideLicense.class);

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
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc3080(String EmailAddress, String Password) throws InterruptedException {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultPage.SwitchToFrame();
		projectResultPage.ClickOngeographyPopupStateLink();
		projectResultPage.ClickOnStateRegionPopupSelectAllStates_checkbox();
		projectResultPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultPage.getcrumbFirstFiltertxt(), "USA");
		projectResultPage.clickOnactionStageCatFacet_1_Ckbox();
		Assert.assertTrue(projectResultPage.isactionStage_Code_SelectAllLinkDisplayed());
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc3079(String EmailAddress, String Password) throws InterruptedException {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnGEOGRAPHY_STATESeeMore_Btn();
		projectResultPage.SwitchToFrame();
		projectResultPage.ClickOngeographyPopupStateLink();
		projectResultPage.ClickOnStateRegionPopupSelectAllStates_checkbox();
		projectResultPage.clickOnUpdateButton();
		Assert.assertEquals(projectResultPage.getcrumbFirstFiltertxt(), "USA");
		projectResultPage.clickOnClearAllLinkInFilter();

		homePage.clickOnSignOutButton();
	}

}

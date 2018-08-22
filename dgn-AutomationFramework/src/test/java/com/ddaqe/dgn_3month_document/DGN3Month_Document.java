package com.ddaqe.dgn_3month_document;

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

public class DGN3Month_Document extends BaseTest {

	static Logger log = Logger.getLogger(DGN3Month_Document.class);

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
			"Medium" }, dataProviderClass = DGN3Month_DocumentDataProvider.class, dataProvider = "3MonthDocumentCommonDataProvider", description = "Verify Spec data for DRs published since *_Specs-in-Dodge-start-date_* can be searched and identified in search results")
	public void tc3769(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.applyFilter();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.checkSpecIsDisplayed(), "spec is not Displyed");
		Assert.assertTrue(projectResultsPage.checkPublishedDateIsDisplayed(), "spec is not Displyed");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGN3Month_DocumentDataProvider.class, dataProvider = "3MonthDocumentCommonDataProvider", description = "Verify Spec data for DRs published since *_Specs-in-Dodge-start-date_* can be searched and identified in search results")
	public void tc3770(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.applyFilter();
		projectResultsPage.performSearch("wooden doors");
		projectResultsPage.clickOnSearchButton();
		Assert.assertTrue(projectResultsPage.isSpecLinkHighlightedInYellow(), "spec is not highlighted with yellow");
	}
}

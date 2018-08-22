package com.ddaqe.dgn_redesigned_view_header;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNRedesignedViewHeader extends BaseTest {

	static Logger log = Logger.getLogger(DGNRedesignedViewHeader.class);

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
			"Regression" }, dataProviderClass = RedesignedViewHeaderDataProvider.class, dataProvider = "TCNonAdEmail", description = "UI-Verify My Search box")
	public void dgnT3145(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isMySearchesVerticallyAlignedWithLeftNav());

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.isMySearchesVerticallyAlignedWithLeftNav());

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = RedesignedViewHeaderDataProvider.class, dataProvider = "TCNonAdEmail", description = "UI-Verify the keyword search text box is left-aligned")
	public void dgnT3146(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isKeywordSearchTextBoxLeftAligned());

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		Assert.assertTrue(companyResultsPage.isKeywordSearchTextBoxLeftAligned());

		homePage.clickOnSignOutButton();

	}

}

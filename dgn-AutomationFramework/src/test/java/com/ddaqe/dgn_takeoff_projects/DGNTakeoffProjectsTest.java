package com.ddaqe.dgn_takeoff_projects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;

@Listeners(TestListener.class)
public class DGNTakeoffProjectsTest extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

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
			"Medium" }, dataProviderClass = DGNTakeoffProjectsDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Verify Takeoff from report view-scenario1 (TC1536)")
	public void tc1669(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.performSearch("wooden doors");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Plans Actions menu");
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Specs Actions menu");
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.mouseoverActionandCheckInstallEtakeOffDisplayed(),
				"'Install eTakeoff' option is not displayed under Addenda Actions menu");

		projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Plans Actions menu");
		projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Specs Actions menu");
		projectAddendaPage = projectPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed(),
				"'Download eTakeoff file' option is not displayed under Addenda Actions menu");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTakeoffProjectsDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Verify the updated company zip code format [zip+4] (TC10186)")
	public void tc1671(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> companiesAddress = new ArrayList<String>();
		companiesAddress.addAll(companyResultsPage.getCompanyLocations());
		for (String address : companiesAddress) {
			Assert.assertTrue(companyResultsPage.verifyZipCodeInAddress(address),
					"Zip code is not as per format in company address [ " + address + " ]");
		}
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		String companyAddress = companyPage.getCompanyAddressInOverviewSection();
		Assert.assertTrue(companyResultsPage.verifyZipCodeInAddress(companyAddress),
				"Zip code is not as per format in company address [ " + companyAddress + " ] in overview section.");
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		companyAddress = companyProjectsPage.getCompanyAddressOnCompanyProjectPage();
		Assert.assertTrue(companyResultsPage.verifyZipCodeInAddress(companyAddress),
				"Zip code is not as per format in company address [ " + companyAddress + " ] on Company Project page.");
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectFirmsPage projectFirmsPage = projectResultsPage.clickOnFirmsLink();
		companyAddress = projectFirmsPage.getCompanyAddressOnProjectFirmPage();
		Assert.assertTrue(companyResultsPage.verifyZipCodeInAddress(companyAddress),
				"Zip code is not as per format in company address [ " + companyAddress + " ] on Project firm page.");
	}

}

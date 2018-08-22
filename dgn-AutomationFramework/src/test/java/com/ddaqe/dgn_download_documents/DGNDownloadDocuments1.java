package com.ddaqe.dgn_download_documents;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HighlightKeywordsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.ProjectAddendaDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.utils.DatabaseUtils;

@Listeners(TestListener.class)

public class DGNDownloadDocuments1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNDownloadDocuments1.class);

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
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3036_tc3045_tc3038_tc3039(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		homePage.clickOnMyAccountLink();
		myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnDocumentsLink();
		if (!myDownloadsPage.IsMessageDisabled("You do not have any documents to download")) {
			myDownloadsPage.clickOnSelectAllCheckbox();
			myDownloadsPage.mouseoverActionandClickDeleteMenu();
			Assert.assertTrue(myDownloadsPage.IsMessageDisabled("You do not have any documents to download"));
		}
		Assert.assertTrue(myDownloadsPage.IsMessageDisabled("You do not have any documents to download"));
		myDownloadsPage.clickOnHomeLink();
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		ProjectAddendaPage projAddendaPage = projectresultsPage.clickOnAddendaLink();
		ProjectAddendaDetailsPage projAddendaDetailsPage = projAddendaPage.clickOnFirstAddendaInGrid();
		projAddendaDetailsPage.selectCheckboxes(1);
		projAddendaDetailsPage.mouseOverActionandClickDownloadDocuments();
		myDownloadsPage.clickOnDownloadLater();
		myDownloadsPage.clickOnSaveBtn();
		Assert.assertTrue(projAddendaDetailsPage.isDownloadDocumentLinkAttopDisplayed());
		homePage.clickOnMyAccountLink();
		myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnDocumentsLink();
		myDownloadsPage.mouseoverActionandClickDeleteMenu();
		Assert.assertTrue(myDownloadsPage.IsMessageDisabled("Please make a selection"));
		myDownloadsPage.clickOnSelectAllCheckbox();
		myDownloadsPage.mouseoverActionandClickDeleteMenu();
		Assert.assertTrue(myDownloadsPage.IsMessageDisabled("You do not have any documents to download"));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3046(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnProjectListToggleButton();
		projectresultsPage.selectDeselectFindInLHSCheckboxList("Plans", true);
		projectresultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		ProjectPlansPage projPlansPage = projectresultsPage.clickOnPlansLink();
		projPlansPage.selectCheckboxes(2);
		Assert.assertTrue(projPlansPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed());
		Assert.assertTrue(projPlansPage.mouseoverActionandCheckInstallEtakeOffDisplayed());
		projPlansPage.mouseoverActionandClickInstallEtakeOff();
		Assert.assertEquals(projPlansPage.getCurrentURL(), "https://www.construction.com/help/download-plan-viewer/");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3047(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnProjectListToggleButton();
		projectresultsPage.selectDeselectFindInLHSCheckboxList("Specs", true);
		projectresultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		ProjectSpecsPage projSpecsPage = projectresultsPage.clickOnSpecsLink();
		projSpecsPage.selectCheckboxes(1);
		Assert.assertTrue(projSpecsPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed());
		Assert.assertTrue(projSpecsPage.mouseoverActionandCheckInstallEtakeOffDisplayed());
		projSpecsPage.mouseoverActionandClickInstallEtakeOff();
		Assert.assertEquals(projSpecsPage.getCurrentURL(), "https://www.construction.com/help/download-plan-viewer/");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3048(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnProjectListToggleButton();
		projectresultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		ProjectAddendaPage projAddendaPage = projectresultsPage.clickOnAddendaLink();
		projAddendaPage.selectCheckboxes(1);
		Assert.assertTrue(projAddendaPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed());
		Assert.assertTrue(projAddendaPage.mouseoverActionandCheckInstallEtakeOffDisplayed());
		projAddendaPage.mouseoverActionandClickInstallEtakeOff();
		Assert.assertEquals(projAddendaPage.getCurrentURL(), "https://www.construction.com/help/download-plan-viewer/");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3049(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		projectresultsPage.clickOnProjectListToggleButton();
		projectresultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		ProjectAddendaPage projAddendaPage = projectresultsPage.clickOnAddendaLink();
		ProjectAddendaDetailsPage projAddendaDetailsPage = projAddendaPage.clickOnFirstAddendaInGrid();
		projAddendaDetailsPage.selectCheckboxes(1);
		Assert.assertTrue(projAddendaDetailsPage.mouseoverActionandCheckDownloadTakeOffFileDisplayed());
		Assert.assertTrue(projAddendaDetailsPage.mouseoverActionandCheckInstallEtakeOffDisplayed());
		projAddendaDetailsPage.mouseoverActionandClickInstallEtakeOff();
		Assert.assertEquals(projAddendaDetailsPage.getCurrentURL(),
				"https://www.construction.com/help/download-plan-viewer/");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3054(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		Assert.assertTrue(projectPlansPage.IsPlansTabHighlighted());
		String drNumber = projectPlansPage.getDRNumber();
		String highlightedPlanNumber = projectPlansPage.getFirstMatchedPlanNumberText();
		projectPlansPage.clickFirstMatchedPlanNumber();
		String pageTitle = projectPlansPage.switchWindowandGetTitle();
		String expectedPageTitle = drNumber + " - plan " + highlightedPlanNumber;
		Assert.assertEquals(pageTitle, expectedPageTitle);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3074(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		Assert.assertTrue(projectPlansPage.IsPlansTabHighlighted());
		HighlightKeywordsPage highlightKeywordsPage = projectPlansPage.clickFirstMatchedPlanNumber();

		Assert.assertTrue(highlightKeywordsPage.IsSearchTextBoxPresent());
		Assert.assertEquals(highlightKeywordsPage.getsearchTextBoxText(), "doors");
		highlightKeywordsPage.entersearchText("doors and windows");
		highlightKeywordsPage.clickSearchBtn();
		Assert.assertTrue(highlightKeywordsPage.IsSnippetDisplayed());
		Assert.assertTrue(highlightKeywordsPage.IsMatchedPageNumberDsplayed());

		char[] charArray400 = new char[400];
		Arrays.fill(charArray400, 'a');
		String searchtext400char = new String(charArray400);

		highlightKeywordsPage.entersearchText(searchtext400char);
		highlightKeywordsPage.clickSearchBtn();

		int length = highlightKeywordsPage.getsearchTextBoxText().length();
		Assert.assertEquals(length, 300);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNDownloadDocumentsDataProvider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc3075(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		ProjectSpecsPage projectSpecsPage = projectResultsPage.clickOnFirstHighlightedSpec();
		HighlightKeywordsPage highlightKeywordsPage = projectSpecsPage.clickFirstMatchedSpecNumber();

		Assert.assertTrue(highlightKeywordsPage.IsSearchTextBoxPresent());
		Assert.assertEquals(highlightKeywordsPage.getsearchTextBoxText(), "doors");
		highlightKeywordsPage.entersearchText("doors and windows");
		highlightKeywordsPage.clickSearchBtn();
		Assert.assertTrue(highlightKeywordsPage.IsSnippetDisplayed());
		Assert.assertTrue(highlightKeywordsPage.IsMatchedPageNumberDsplayed());

		char[] charArray400 = new char[400];
		Arrays.fill(charArray400, 'a');
		String searchtext400char = new String(charArray400);

		highlightKeywordsPage.entersearchText(searchtext400char);
		highlightKeywordsPage.clickSearchBtn();

		int length = highlightKeywordsPage.getsearchTextBoxText().length();
		Assert.assertEquals(length, 350);
	}
}

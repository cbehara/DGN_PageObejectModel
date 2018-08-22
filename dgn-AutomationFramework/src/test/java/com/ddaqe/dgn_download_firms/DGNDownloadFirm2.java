package com.ddaqe.dgn_download_firms;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNDownloadFirm2 extends BaseTest {
	static Logger log = Logger.getLogger(DGNDownloadFirm2.class);

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
			"Medium" }, dataProviderClass = DGNDownloadFirmDataProvider.class, dataProvider = "TC1711", description = "Downloading firm in certain scenarios yields no unexpected error anymore (TC14637).")
	public void tc1711(String username, String password, String DR_Num) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.enterSearchTxt(DR_Num);
		ProjectPage projectPage = projectResultsPage.clickOnSearchButton();
		projectPage.mouseOverActionandClickDownloadFirms();
		projectPage.waitforLoadingRing();
		File downloadedCSVFileName = CommonUtils.getLatestDownloadedFile(downloadDestination);
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName.getName(), 25, 2000));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadFirmDataProvider.class, dataProvider = "TC1714_2", description = "Verify the Download Firm functionality on Spec Alerts Page (TC22683)")
	public void tc1714(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertResultPage = projectResultsPage.clickOnMySearchesSpecAlerts();
		String firstDRNumber = projectResultsPage.getFirstDRNumer();
		specAlertResultPage.clickOnFirstProjectCheckbox();
		specAlertResultPage.clickOnActionsDropdown();
		specAlertResultPage.clickOnDownloadFirmsFromSpecAlertsActions();
		CommonUtils.isFileDownloaded(downloadDestination, "", 1, 10000);
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		List<String> dRNumberList = csvReader.getValuesFromKey("PROJECT NUMBER");
		boolean isVerified = true;
		for (String dRnumber : dRNumberList) {
			if (StringUtils.isNotEmpty(dRnumber)) {
				isVerified = isVerified && dRnumber.contains(firstDRNumber.trim());
			}
		}
		Assert.assertTrue(isVerified, "One of the DR number in the downloaded csv file is not currect");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadFirmDataProvider.class, dataProvider = "TC1710", description = "Download project firms in csv format list view-To Verify that user is able to download firms in csv format.")
	public void tc1710(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		projectResultsPage.clickOnDownloadFirmsActionMenu();
		projectResultsPage.waitforLoadingRing();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 5, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 5, 2000));
		}
		File downloadedCSVFileName = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String latestDownloadedFileName = downloadedCSVFileName.getName().substring(0, 27);
		String fileNameFormat = projectResultsPage.getFirstDRNumer().trim() + "_Firms_"
				+ CommonUtils.getTimeStamp().substring(0, 8);
		Assert.assertEquals(latestDownloadedFileName, fileNameFormat);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadFirmDataProvider.class, dataProvider = "TC1712", description = "Verify the Download Firms functionality on Project Search Results.")
	public void tc1712(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		String firstDRNumber = projectResultsPage.getFirstDRNumer();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.clickOnDownloadFirmsActionMenu();
		projectResultsPage.waitforLoadingRing();
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		List<String> dRNumberList = csvReader.getValuesFromKey("PROJECT NUMBER");
		boolean isVerified = true;
		for (String dRnumber : dRNumberList) {
			if (StringUtils.isNotEmpty(dRnumber)) {
				isVerified = isVerified && dRnumber.contains(firstDRNumber.trim());
			}
		}
		Assert.assertTrue(isVerified, "One of the DR number in the downloaded csv file is not currect");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadFirmDataProvider.class, dataProvider = "TC1713", description = "Verify the Download firm functionality on Project Firm Tab")
	public void tc1713(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		String firstDRNumber = projectResultsPage.getFirstDRNumer();
		ProjectFirmsPage projectFirmPage = projectResultsPage.clickOnFirmsLink();
		projectFirmPage.mouseOverActionandClickOnDownloadFirms();
		projectFirmPage.waitforLoadingRing();
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		List<String> dRNumberList = csvReader.getValuesFromKey("PROJECT NUMBER");
		boolean isVerified = true;
		for (String dRnumber : dRNumberList) {
			if (StringUtils.isNotEmpty(dRnumber)) {
				isVerified = isVerified && dRnumber.contains(firstDRNumber.trim());
			}
		}
		Assert.assertTrue(isVerified, "One of the DR number in the downloaded csv file is not currect");
	}
}

package com.ddaqe.dgn_tech_support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.ManageUsers;
import com.ddaqe.pages.TechSupportPage;
import com.ddaqe.pages.UserSelectionPage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNTech_Support2 extends BaseTest {
	static Logger log = Logger.getLogger(DGNTech_Support2.class);

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
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "Tech_SupportCommonDataProvider", description = "[Customer Admin - Tech Support] Replicate report views within tech support application -To Verify that tech support  is able to Download the System Usage  CSV document (TC12101)")
	public void tc2944_2945(String username, String password, String URL, String licenseID)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnReportsLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickReportTypeDropDown();
		manageUsers.selectReportType("System Usage");
		manageUsers.clickReportUserDropdown();
		manageUsers.clickSelectAllUsersDDOption();
		manageUsers.clickRunButton();
		Assert.assertTrue(
				manageUsers
						.verifyErrorMessage("There were no project downloads for the selected user(s) and date range"),
				"No project available for download.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "Tech_SupportCommonDataProvider", description = "[Customer Admin - Tech Support] Replicate report views within tech support application -To Verify that tech support  is able to Download the System Usage  CSV document (TC12101)")
	public void tc2945(String username, String password, String URL, String licenseID)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnReportsLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickReportTypeDropDown();
		manageUsers.selectReportType("System Usage");
		manageUsers.clickReportUserDropdown();
		manageUsers.clickSelectAllUsersDDOption();
		manageUsers.clickRunButton();
		if (!manageUsers
				.verifyErrorMessage("There were no project downloads for the selected user(s) and date range")) {
			CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 10000);
			File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
			CSVReader csvReader = new CSVReader(downloadedFile);
			List<String> keyToCheckList = Arrays.asList("User email address", "User last name", "User first name",
					"Last Login date/time stamp", "Number of logins");
			for (String keyToCheck : keyToCheckList) {
				Assert.assertTrue(csvReader.verifyKey(keyToCheck), keyToCheck + " column is not fount.");
			}
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "Tech_SupportCommonDataProvider", description = "[Customer Admin - Tech Support] Replicate report views within tech support application -To Verify that tech support is able to Download the project download summary CSV document (TC12102)")
	public void tc2946(String username, String password, String URL, String licenseID)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnReportsLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickReportTypeDropDown();
		manageUsers.selectReportType("Project Download Summary");
		manageUsers.clickReportUserDropdown();
		manageUsers.clickSelectAllUsersDDOption();
		manageUsers.clickRunButton();
		if (manageUsers.verifyErrorMessage("There were no project downloads for the selected user(s) and date range")) {
			Assert.assertTrue(true, "No project available for download.");
		} else {
			CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 2000);
			File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
			CSVReader csvReader = new CSVReader(downloadedFile);
			List<String> keyToCheckList = Arrays.asList("User email address", "User legacy id", "Contract #",
					"Full name", "Company Zip Code", "Download count");
			for (String keyToCheck : keyToCheckList) {
				Assert.assertTrue(csvReader.verifyKey(keyToCheck), keyToCheck + " column is not fount.");
			}
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "Tech_SupportCommonDataProvider", description = "[Customer Admin - Tech Support] Replicate report views within tech support application -To Verify that tech support  is able to Download the project download detail  CSV document (TC12103)")
	public void tc2947(String username, String password, String URL, String licenseID)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnReportsLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickReportTypeDropDown();
		manageUsers.selectReportType("Project Download Detail");
		manageUsers.clickReportUserDropdown();
		manageUsers.clickSelectAllUsersDDOption();
		manageUsers.clickRunButton();
		if (manageUsers.verifyErrorMessage("There were no project downloads for the selected user(s) and date range")) {
			Assert.assertTrue(true, "No project available for download.");
		} else {
			CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 2000);
			File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
			CSVReader csvReader = new CSVReader(downloadedFile);
			List<String> keyToCheckList = Arrays.asList("User email address", "User legacy id", "Contract #",
					"Full name", "Company Zip Code", "Downloaded Dodge Report number", "Download date");
			for (String keyToCheck : keyToCheckList) {
				Assert.assertTrue(csvReader.verifyKey(keyToCheck), keyToCheck + " column is not fount.");
			}
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "Tech_SupportCommonDataProvider", description = "[TECHSUPPORT] A pop up message will come if the User clicks on Save button without mentioning email address in Customer Set Up page (TC21677)")
	public void tc3013(String username, String password, String URL, String licenseID)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnReportsLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickReportTypeDropDown();
		manageUsers.selectReportType("System Usage");
		manageUsers.clickReportUserDropdown();
		manageUsers.clickSelectAllUsersDDOption();
		manageUsers.clickRunButton();
		if (manageUsers.verifyErrorMessage("There were no project downloads for the selected user(s) and date range")) {
			Assert.assertTrue(true, "No project available for download.");
		} else {
			CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 10000);
			File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
			CSVReader csvReader = new CSVReader(downloadedFile);
			List<String> keyToCheckList = Arrays.asList("User email address", "User last name", "User first name",
					"Last Login date/time stamp", "Number of logins");
			for (String keyToCheck : keyToCheckList) {
				Assert.assertTrue(csvReader.verifyKey(keyToCheck), keyToCheck + " column is not fount.");
			}
		}
	}
}

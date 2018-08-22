package com.ddaqe.dgn_public_private_share;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNPublicPrivateShareTest2 extends BaseTest {

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
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - PROJECT - Update to CSV download (batch and real time) to include public/shared lists - Display fields available for PLATINUM users (TC10815)")
	public void tc101_tc102_tc103(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "VERSION", "TITLE", "SUB-TITLE", "ADDRESS LINE 1",
				"ADDRESS LINE 2", "CITY", "STATE", "ZIP", "COUNTRY", "COUNTY", "PROJECT TYPE(S)", "ACTION PHASE(S)",
				"PUBLISH DATE", "FIRST PUBLISH DATE", "TARGET START DATE", "TARGET COMPLETION DATE", "STATUS",
				"PROJECT DELIVERY SYSTEM", "LOW VALUE(S)", "HIGH VALUE(S)", "PLANS AVAILABLE", "SPECS AVAILABLE",
				"ADDENDA AVAILABLE", "URL LINK TO PROJECT", "DETAILS: BID DATE", "DETAILS: BID DATE/TIME",
				"DETAILS: BID SUBMIT TO", "DETAILS: SITE NAME", "DETAILS: SQUARE FOOTAGE", "DETAILS: NBR STORIES ABOVE",
				"DETAILS: NBR STORIES BELOW", "DETAILS: NUMBER OF BLDGS", "DETAILS: FRAMING TYPE", "Owner Info",
				"Architect Info", "GC Info", "Electrical Info", "Landscape Info", "Mechanical Info", "Civil Info",
				"Structural Info", "Consultant Info", "Engineer Info", "Construction Manager", "USER NOTES (PRIVATE)",
				"USER NOTES (PUBLIC)", "TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)", "TYPE OF CONSTRUCTION",
				"ADDITIONAL FEATURES", "SPECALERTS");
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertTrue(selectedFieldLst.containsAll(keyList), "Given Options are not displayed");
		Assert.assertTrue(downloadProjects.isAvailableFieldsSelectDropDownEmpty(),
				"Available field select drop down is empty.");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		CommonUtils.isFileDownloaded(downloadDestination, "", 1, 5000);
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNPublicPrivateShareDataProvider.class, dataProvider = "ComPlatinumUserDataProvider", description = "Public/Share - COMPANY - Update to CSV download (batch and real time) to include public/shared lists - Display fields available for PLATINUM users (TC10818)")
	public void tc104_tc105_tc106(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompany = companyPage.mouseOverActionandClickDownloadCompanies();
		companyPage.isPopUpDownloadCompanyDisplayed();
		downloadCompany.clickOnCSVRadioBtn();
		List<String> keyList = Arrays.asList("COMPANY NAME", "COMPANY TYPE", "COMPANY ADDRESS LINE1",
				"COMPANY ADDRESS LINE2", "COMPANY CITY", "COMPANY STATE", "COMPANY ZIP", "COMPANY COUNTY",
				"COMPANY COUNTRY", "COMPANY PHONE", "COMPANY FAX", "COMPANY EMAIL", "COMPANY WEBSITE", "COMPANY URL",
				"TOTAL PROJECTS", "TOTAL VALUATION", "USER NOTES (PRIVATE)", "USER NOTES (PUBLIC)",
				"TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)", "CONTACT TITLE", "CONTACT FULL NAME",
				"CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME", "CONTACT ADDRESS LINE1",
				"CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP", "CONTACT COUNTY",
				"CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");
		List<String> selectedFieldLst = downloadCompany.getSelectedFieldList();
		Assert.assertTrue(selectedFieldLst.containsAll(keyList), "Given Options are not displayed");
		Assert.assertTrue(downloadCompany.getFieldList().isEmpty(), "Available field select drop down is empty.");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompany.clickOnDownloadBtn();
		CommonUtils.isFileDownloaded(downloadDestination, "", 1, 5000);
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}
	}
}

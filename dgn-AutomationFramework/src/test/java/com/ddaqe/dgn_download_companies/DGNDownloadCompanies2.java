package com.ddaqe.dgn_download_companies;

import java.io.File;
import java.io.FileNotFoundException;
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
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.HomePage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNDownloadCompanies2 extends BaseTest {
	static Logger log = Logger.getLogger(DGNDownloadCompanies2.class);

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
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To verify the 'Download Company' functionality from Company Profile page..")
	public void tc1716(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed(),
				"PDF radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed(),
				"XML radio button is not displayed on Download Company dialog.");

		String downloadedPDFFileName = downloadCompanies.getDownloadFileName() + ".pdf";
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 15, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1719", description = "To verify the Download Companies - Save to a single file.")
	public void tc1719(String emailAddress, String password, String errorMessage) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadName = "compDownloadPDF" + CommonUtils.getTimeStamp();
		String downlaodNameWithSpace = "Company donwload 1";
		String downloadNameWithSpecChar = "CompanyDownload$@!@";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFirstCompanyChkBox();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.setDownloadName(downlaodNameWithSpace);
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertEquals(downloadCompanies.getErrorMessageOfDownloadCompanies(), errorMessage);

		downloadCompanies.setDownloadName(downloadNameWithSpecChar);
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertEquals(downloadCompanies.getErrorMessageOfDownloadCompanies(), errorMessage);

		downloadCompanies.setDownloadName("");
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertEquals(downloadCompanies.getErrorMessageOfDownloadCompanies(), errorMessage);

		downloadCompanies.setDownloadName(companyDownloadName);
		String downloadedPDFFileName = downloadCompanies.getDownloadFileName() + ".pdf";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 15, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To Verify that User is able to Download companies in  CSV format.")
	public void tc1721(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadName = "compDownloadCSV" + CommonUtils.getTimeStamp();

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.clickOnFirstCompanyChkBox();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(companyDownloadName);
		String downloadedCSVFileName = downloadCompanies.getDownloadFileName() + ".csv";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 15, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To Verify that User is able to  Download all the  companies in  CSV format from a page.")
	public void tc1722(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadName = "compDownloadCSV" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(companyDownloadName);
		String downloadedCSVFileName = downloadCompanies.getDownloadFileName() + ".csv";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To Verify the Data displayed in the CSV file is proper.")
	public void tc1723(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String companyDownloadName = "compDownloadCSV" + CommonUtils.getTimeStamp();
		List<String> keyList = Arrays.asList("TRACKING LISTS (PRIVATE)", "CONTACT TITLE", "CONTACT FULL NAME",
				"CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME", "CONTACT ADDRESS LINE1",
				"CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP", "CONTACT COUNTY",
				"CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");
		String resultPerPage = "10";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(companyDownloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "Download companies in CSV format from Profile- To Verify that User is able to Download companies in  CSV format (TC4355).")
	public void tc1724(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		String downloadedCSVFileName = downloadCompanies.getDownloadFileName() + ".csv";
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To Verify the Data displayed in the CSV file is proper.")
	public void tc1725(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		List<String> keyList = Arrays.asList("TRACKING LISTS (PRIVATE)", "CONTACT TITLE", "CONTACT FULL NAME",
				"CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME", "CONTACT ADDRESS LINE1",
				"CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP", "CONTACT COUNTY",
				"CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1729", description = "Verify if the ranked values(# projects and total valuation) of companies are properly included in the downloaded CSV file in DGN.")
	public void tc1729(String emailAddress, String password) throws InterruptedException, FileNotFoundException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadNameCSV = "compDownloadCSV" + CommonUtils.getTimeStamp();
		String totalProjectKey = "TOTAL PROJECTS";
		String valuationAmtKey = "TOTAL VALUATION";

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnCanadaCompanyLocationCheckbox();
		companyResultsPage.clickOnDesignProjActionStageCheckbox();

		companyResultsPage.clickOnPowerRankResultBtn();
		companyResultsPage.clickOnFirstCompanyChkBox();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(companyDownloadNameCSV);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertEquals(csvReader.getValuesFromKey(totalProjectKey).get(0),
				companyResultsPage.getTotalProjectCountFromProjectList(0));
		Assert.assertEquals(csvReader.getValuesFromKey(valuationAmtKey).get(0),
				companyResultsPage.getValuationTextFromValuationList(0));
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1729", description = "Verify the Download Company functionality for less than 100 company on Company Search Results.")
	public void tc1733(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadName = "compDownloadPDF" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCanadaCompanyLocationCheckbox();
		companyResultsPage.clickOnDesignProjActionStageCheckbox();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.setDownloadName(companyDownloadName);
		String downloadedPDFFileName = downloadCompanies.getDownloadFileName() + ".pdf";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 50, 2000));
		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed(), "Control is not at company result page.");
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1729", description = "Verify the Download Companies functionality for less than 100 comany on Company Ranking Page.")
	public void tc1734(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		String companyDownloadNameCSV = "compDownloadCSV" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCanadaCompanyLocationCheckbox();
		companyResultsPage.clickOnDesignProjActionStageCheckbox();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(companyDownloadNameCSV);
		String downloadedCSVFileName = downloadCompanies.getDownloadFileName() + ".csv";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 50, 2000));
		Assert.assertTrue(companyResultsPage.isSelectAllcheckboxDisplayed(), "Control is not at company result page.");

		homePage.clickOnSignOutButton();
	}
}

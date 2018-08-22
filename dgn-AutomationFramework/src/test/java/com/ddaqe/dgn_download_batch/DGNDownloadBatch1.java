package com.ddaqe.dgn_download_batch;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_project_search.ProjectSearchDataProvider;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNDownloadBatch1 extends BaseTest {
	static Logger log = Logger.getLogger(DGNDownloadBatch1.class);

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
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc200(String emailAddress, String password) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectDownload(downloadName, projectResultsPage, 1);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();
		myDownloads.verifyMyDownloadsDataGridColumnHeaders();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC202", description = "To verify the batch download details that are displayed in a table in 'My Downloads' page.")
	public void tc202(String emailAddress, String password, String type, String status) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectDownload(downloadName, projectResultsPage, 1);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.verifyDownloadName(downloadName),
				"Download batch name is not as expected " + downloadName);
		Assert.assertTrue(myDownloads.verifyDownloadDateCreated(CommonUtils.getSystemDate()),
				"Download batch date is not as expected " + CommonUtils.getSystemDate());
		Assert.assertTrue(myDownloads.verifyDownloadType(type), "Download batch Type is not as expected " + type);
		Assert.assertTrue(myDownloads.verifyDownloadStatus(status),
				"Download batch Status is not as expected " + status);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "To verify the pagination on project/company batch downloads page and project/company batch download details page.")
	public void tc203(String emailAddress, String password) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 1);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyDownload(downloadName, companyResultsPage, 1);

		companyResultsPage.waitforLoadingRing();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(myDownloads.isPaginationDisplayedAtTop(), "Pagination is not present at Top.");
		Assert.assertTrue(myDownloads.isPaginationDisplayedAtBottom(), "Pagination is not present at Bottom.");

		myDownloads.clickOnComapnyLink();
		companyResultsPage.waitforLoadingRing();
		Assert.assertTrue(myDownloads.isPaginationDisplayedAtTop(), "Pagination is not present at Top.");
		Assert.assertTrue(myDownloads.isPaginationDisplayedAtBottom(), "Pagination is not present at Bottom.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc201(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloads.checkforValidationMessage("You do not have any batch downloads for projects"));
		myDownloads.clickOnComapnyLink();
		Assert.assertTrue(myDownloads.checkforValidationMessage("You do not have any batch downloads for companies"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "To verify the available page size options and the 'sticky' feature in batch downloads page.")
	public void tc204(String emailAddress, String password) throws InterruptedException {
		String resultPerPageValue = "50";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPageValue);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.isResultPerPageDropdownDisplayedAtBottom(),
				"Result per page dropdown is not present at bottom.");
		Assert.assertTrue(myDownloads.checkResultPerPageOption(),
				"Result per page does not have option 10,25,50,100,500.");
		Assert.assertTrue(myDownloads.checkSetValueOfResultPerPage(resultPerPageValue),
				"The value set in Result Per page option on Project is not same on My download page i.e."
						+ resultPerPageValue);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "Verify the sorting options and sort order in batch downloads page.")
	public void tc205(String emailAddress, String password) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 1);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.verifySortingDownloadName(),
				"Download name are not sorted according to created date.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc206(String emailAddress, String password) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 1);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyDownload(downloadName, companyResultsPage, 1);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.verifyMyDownloadsBreadCrumb("My Account - My Downloads - Project"));
		Assert.assertTrue(myDownloads.checkBreadCrumbIsNotClickable(), "Breadcrumb is clickable.");

		myDownloads.clickOnComapnyLink();
		Assert.assertTrue(myDownloads.verifyMyDownloadsBreadCrumb("My Account - My Downloads - Company"));
		Assert.assertTrue(myDownloads.checkBreadCrumbIsNotClickable());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "To verify that user is able to delete the batch download.")
	public void tc207(String emailAddress, String password) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		String totalPageCount = "";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 1);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyDownload(downloadName, companyResultsPage, 1);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		totalPageCount = myDownloads.getTotalTopPageCount();
		Assert.assertTrue(myDownloads.checkDeleteMenuPresentUnderActionDropdown(),
				"Delete menu is not present under Action dropdown.");
		myDownloads.clickOnFirstDownloadBatchCheckbox();
		myDownloads.clickOnDeleteMenuPresentUnderActionDropdown();
		Assert.assertTrue(myDownloads.checkTotalTopPageCount(totalPageCount),
				"Download batch is not deleted after selection.");

		myDownloads.clickOnSelectAllCheckbox();
		myDownloads.clickOnDeleteMenuPresentUnderActionDropdown();
		Assert.assertTrue(myDownloads.checkforValidationMessage("You do not have any batch downloads for projects"));

		myDownloads.clickOnComapnyLink();
		myDownloads.clickOnSelectAllCheckbox();
		myDownloads.clickOnDeleteMenuPresentUnderActionDropdown();
		Assert.assertTrue(myDownloads.checkforValidationMessage("You do not have any batch downloads for companies"));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC209", description = "To verify that a user friendly error message is displayed when user click Delete Batch option without selecting any batches.")
	public void tc209(String emailAddress, String password, String messageToCheck) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 1);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.checkDeleteMenuPresentUnderActionDropdown(),
				"Delete menu is not present under Action dropdown.");
		myDownloads.clickOnDeleteMenuPresentUnderActionDropdown();
		Assert.assertTrue(myDownloads.checkNonSelectionDeleteMessage(messageToCheck),
				"Error message is different than : " + messageToCheck);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC202", description = "[Batch Download] [XML] Allow XML option for project and companies.")
	public void tc215(String emailAddress, String password, String type, String status) throws InterruptedException {
		String downloadName = "myDownload" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectDownload(downloadName, projectResultsPage, 3);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyDownload(downloadName, companyResultsPage, 3);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloads = myAccount.clickOnMyDownloads();

		Assert.assertTrue(myDownloads.verifyDownloadName(downloadName),
				"Download batch name is not as expected " + downloadName + " in project download.");

		myDownloads.clickOnComapnyLink();
		Assert.assertTrue(myDownloads.verifyDownloadName(downloadName),
				"Download batch name is not as expected " + downloadName + " in company download.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc277(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("1000");
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(companyResultsPage.isDownLoadCompaniesMenuDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderCommon", description = "Display a tracking list-scenario1_3/Redesigned home-scenario11_13")
	public void tc278(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("1000");
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(companyResultsPage.isDownLoadCompaniesMenuDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC216", description = "To verify that the user is intimated by a user friendly message when the user is downloading more than 100 companies in XML format")
	public void tc216(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = projectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnXMLRadioBtn();

		Assert.assertTrue(downloadProjects.isBatchDownloadWarningMessageXMLVisible());
		Assert.assertEquals(downloadProjects.getBatchDownloadWarningMessageXML(), batchDownloadWarningMessage);

		downloadProjects.clickOnClickHereToSeeWhyLinkXML();
		Assert.assertTrue(downloadProjects.isClickHereToSeeWhyTooltipXMLVisible());
		Assert.assertTrue(downloadProjects.getClickHereToSeeWhyTooltipTextXML().contains(clickHereToSeeWhyToolTip));
		downloadProjects.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC217", description = "To verify that the user is intimated by a user friendly message when the user is downloading more than 100 companies in XML format")
	public void tc217(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnXMLRadioBtn();

		Assert.assertTrue(downloadCompanies.isBatchDownloadWarningMessageXMLVisible());
		Assert.assertEquals(downloadCompanies.getBatchDownloadWarningMessageXML(), batchDownloadWarningMessage);

		downloadCompanies.clickOnClickHereToSeeWhyLinkXML();
		Assert.assertTrue(downloadCompanies.isClickHereToSeeWhyTooltipXMLVisible());
		Assert.assertTrue(downloadCompanies.getClickHereToSeeWhyTooltipTextXML().contains(clickHereToSeeWhyToolTip));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC218", description = "To Verify that user will be able to get the Pop up on downloading more than 100 projects for PDF")
	public void tc218(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = projectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnPDFRadioBtn();

		Assert.assertTrue(downloadProjects.isBatchDownloadWarningMessagePdfVisible());
		Assert.assertEquals(downloadProjects.getBatchDownloadWarningMessagePdf(), batchDownloadWarningMessage);

		downloadProjects.clickOnClickHereToSeeWhyLinkPdf();
		Assert.assertTrue(downloadProjects.isClickHereToSeeWhyTooltipPdfVisible());
		Assert.assertTrue(downloadProjects.getClickHereToSeeWhyTooltipTextPdf().contains(clickHereToSeeWhyToolTip));
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC218", description = "To Verify that user will be able to get the Pop up on downloading more than 100 projects for CSV")
	public void tc219(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = projectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		Assert.assertTrue(downloadProjects.isBatchDownloadWarningMessageCsvVisible());
		Assert.assertEquals(downloadProjects.getBatchDownloadWarningMessageCsv(), batchDownloadWarningMessage);

		downloadProjects.clickOnClickHereToSeeWhyLinkCsv();
		Assert.assertTrue(downloadProjects.isClickHereToSeeWhyTooltipCsvVisible());
		Assert.assertTrue(downloadProjects.getClickHereToSeeWhyTooltipTextCsv().contains(clickHereToSeeWhyToolTip));

		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "To Verify what happen when user click on cancel button when doing a batch download")
	public void tc221(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = projectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed());
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.clickOnCancelBtn();

		Assert.assertFalse(downloadProjects.isDownloadProjectsPopupDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC222", description = "To Verify that user will be able to get the Pop on on downloading more than 100 Companies for PDF")
	public void tc222(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnPDFRadioBtn();

		Assert.assertTrue(downloadCompanies.isBatchDownloadWarningMessagePdfVisible());
		Assert.assertEquals(downloadCompanies.getBatchDownloadWarningMessagePdf(), batchDownloadWarningMessage);

		downloadCompanies.clickOnClickHereToSeeWhyLinkPdf();
		Assert.assertTrue(downloadCompanies.isClickHereToSeeWhyTooltipPdfVisible());
		Assert.assertTrue(downloadCompanies.getClickHereToSeeWhyTooltipTextPdf().contains(clickHereToSeeWhyToolTip));

		downloadCompanies.setDownloadName(downloadName);
		downloadCompanies.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC222", description = "To Verify that user will be able to get the Pop up on downloading more than 100 Companies for CSV")
	public void tc223(String emailAddress, String password, String batchDownloadWarningMessage,
			String clickHereToSeeWhyToolTip) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();

		Assert.assertTrue(downloadCompanies.isBatchDownloadWarningMessageCsvVisible());
		Assert.assertEquals(downloadCompanies.getBatchDownloadWarningMessageCsv(), batchDownloadWarningMessage);

		downloadCompanies.clickOnClickHereToSeeWhyLinkCsv();
		Assert.assertTrue(downloadCompanies.isClickHereToSeeWhyTooltipCsvVisible());
		Assert.assertTrue(downloadCompanies.getClickHereToSeeWhyTooltipTextCsv().contains(clickHereToSeeWhyToolTip));

		downloadCompanies.setDownloadName(downloadName);
		downloadCompanies.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "[Batch Download] - Verify the display of progress bar when user is downloading more than 100 projects (TC10133)")
	public void tc237(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage ProjectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = ProjectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(projectResultsPageUpdated.isProjectListTogglebuttonDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "To Verify the Download name field in the Download pop up for Batch download")
	public void tc227(String emailAddress, String password) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(downloadName);
		downloadCompanies.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "[Batch Download] - Verify the display of progress bar when user is downloading more than 100 companies (TC10134)")
	public void tc238(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("500");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertTrue(companyResultsPage.isSortDropdownDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "(Copy of) [Batch Download] - Verify that progress bar is not displayed for 100 or less than 100 projects (TC10135)")
	public void tc239(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage ProjectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = ProjectResultsPage
				.selectResultDropdownValueUpdatedResultPage("10");
		projectResultsPageUpdated.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(projectResultsPageUpdated.isProjectListTogglebuttonDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "[Batch Download] - Verify that progress bar is not displayed for 100 or less than 100 companies (TC10136)")
	public void tc240(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("10");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnDownloadBtn();
		Assert.assertTrue(companyResultsPage.isSortDropdownDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "Verification of message displayed for batch download page when there is no batch download for company batch download. (TC12989)")
	public void tc241(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("500");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnDownloadBtn();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		myDownloadsPage.clickOnselectAllchkboxProjectandComapanies();
		myDownloadsPage.mouseoverActionandClickDeleteMenu();
		assertTrue(myDownloadsPage.isNoDownloadMessageDisplayedForCompanies());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "Verifying batch Download for Companies  showing tracking icon symbol (TC15156)")
	public void tc244(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("500");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		assertTrue(downloadCompanies.isBatchDownloadWarningMessagePdfVisible());
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "Verify the Download Company functionality for 100 or more than 100 company on Company Search Results. (TC22664)")
	public void tc252(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("100");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		assertTrue(downloadCompanies.isBatchDownloadWarningMessagePdfVisible());
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC254", description = "Verify the Download Companies functionality for 100 or more than 100 comany on Company Ranking Page (TC22667)")
	public void tc253(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("Metal");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.clickPowerRank();
		companyResultsPage.selectResultsPerPage("500");
		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isBatchDownloadWarningMessagePdfVisible());
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC254", description = "Verify the Download Projects functionality for 100 or more than 100 projects on Spec Alerts Page (TC22682)")
	public void tc254(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.SelectResultDropdownValue("100");
		specAlertsResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = specAlertsResultsPage.SpecAlertmouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(specAlertsResultsPage.isSpecAlertsProgramsLeftPanelDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "DGNDownLoadBatchDataProviderNoDownLoad", description = "[Unfinished] [Batch Download - Projects] UI - create Batch folder where user can view status of their downloads and download files upon successful completion of batch process - 2 status - Requested and Completed -To Verify that User should be able to (TC10056)")
	public void tc236(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage ProjectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = ProjectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnDownloadBtn();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		myAccount.isMyDownlaodsDisplayed();
		assertTrue(myAccount.isMyDownlaodsDisplayed());
		assertTrue(myAccount.isMyRegisterationDisplayed());
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		assertTrue(myDownloadsPage.isPaginationDisplayedAtBottom());
		assertTrue(myDownloadsPage.isProjectBarDisplayed());
		assertTrue(myDownloadsPage.isProjectBarDisplayed());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "To Verify  what happens should be displayed when user enter download name  which is already existing")
	public void tc228(String emailAddress, String password) throws InterruptedException {
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(downloadName);
		downloadCompanies.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));

		CompanyResultsPage companyResultsPageUpdated1 = homePage.clickOnCompaniesLink();
		companyResultsPageUpdated1.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies1 = companyResultsPageUpdated1.mouseOverActionandClickDownloadCompanies();
		downloadCompanies1.clickOnCSVRadioBtn();
		downloadCompanies1.setDownloadName(downloadName);
		downloadCompanies1.clickOnDownloadBtn();

		MyAccount myAccount1 = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage1 = myAccount1.clickOnMyDownloads();
		myDownloadsPage1.clickOnComapnyLink();
		Assert.assertTrue(myDownloadsPage1.verifyDownloadName(downloadName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC229", description = "To Verify that user should not been allowed to enter more than 60 Character download name")
	public void tc229(String emailAddress, String password, String moreThan60CharText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPage.selectResultsPerPageUpdatedResultPage("500");
		companyResultsPageUpdated.clickOnSelectAllCompany();

		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.setDownloadName(moreThan60CharText);
		Assert.assertTrue(downloadCompanies.getBatchDownloadName().length() == 60);
		downloadCompanies.clickOnCancelBtn();

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectResultsPage projectResultsPageUpdated = projectResultsPage
				.selectResultDropdownValueUpdatedResultPage("500");
		projectResultsPageUpdated.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadBatchName(moreThan60CharText);
		Assert.assertTrue(downloadProjects.getBatchDownloadName().length() == 60);
		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommon", description = "Verification of 10000 Batch download functionality on Project result page")
	public void tc3305TC3306_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");

		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectResultDropdownValue("10000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderCommonNonAdmin", description = "Verification of 10000 Batch download functionality on Project result page")
	public void tc3305TC3306_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");

		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectResultDropdownValue("10000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderComonPlus", description = "Verification of 10000 Batch download functionality on Project result page")
	public void tc3305TC3306_3(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");

		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectResultDropdownValue("10000");
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectSearchDataProvider.class, dataProvider = "DGNProjectSearchDataProviderComonPlus", description = "Verification of 5000 Batch download functionality on SpecAlert results page.")
	public void tc3307(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickOnFistProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		DownloadProjects downloadProjects = specAlertsResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download option popup is not displayed.");
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"User dont have 'PDF' as a download format option.");
		downloadProjects.clickOnCancelBtn();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupClosed(), "Download option popup is displayed.");
		homePage.clickOnSignOutButton();
	}

	/**
	 * download the project batch with 500 records.
	 * 
	 * @param downloadName
	 * @param projectResultsPage
	 * @param radioOptionToSelect
	 *            : 1 = PDF , 2 = CSV, 3= XML
	 */
	public void projectDownload(String downloadName, ProjectResultsPage projectResultsPage, int radioOptionToSelect) {
		log.info("download the project batch with 500 records.");
		String resultPerPageValue = "500";

		projectResultsPage.SelectResultDropdownValue(resultPerPageValue);
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.setDownloadBatchFormath(radioOptionToSelect);
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();
	}

	/**
	 * download the company batch with 500 records.
	 * 
	 * @param downloadName
	 * @param companyResultsPage
	 * @param radioOptionToSelect
	 *            : 1 = PDF , 2 = CSV, 3= XML
	 */
	public void companyDownload(String downloadName, CompanyResultsPage companyResultsPage, int radioOptionToSelect) {
		log.info("download the project batch with 500 records.");
		String resultPerPageValue = "500";
		companyResultsPage.selectResultsPerPage(resultPerPageValue);
		companyResultsPage.clickOnSelectAllProjects();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.setDownloadBatchFormath(radioOptionToSelect);
		downloadCompanies.setDownloadBatchName(downloadName);
		downloadCompanies.clickOnDownloadBtn();
	}

}
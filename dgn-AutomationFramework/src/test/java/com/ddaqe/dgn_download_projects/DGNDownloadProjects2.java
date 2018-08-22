package com.ddaqe.dgn_download_projects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.Download;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.DownloadDocument;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.ProjectAddendaDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackingLists;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.XMLReader;

@Listeners(TestListener.class)

public class DGNDownloadProjects2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNDownloadProjects2.class);

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
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To Verify the Project Downloaded file in CSV format")
	public void tc1918(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		List<Integer> projectTitlesToCheck = new ArrayList<Integer>();
		projectTitlesToCheck.add(0);
		projectResultsPage.selectProjectsByIndex(projectTitlesToCheck);
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 5, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To Verify the Company Downloaded file  in CSV format ")
	public void tc1920(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<Integer> companyTitlesToCheck = new ArrayList<Integer>();
		companyTitlesToCheck.add(0);
		companyResultsPage.selectCompaniesByIndex(companyTitlesToCheck);
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();

		String downloadedCSVFileName = downloadCompanies.getDownloadFileName() + ".csv";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 5, 2000));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1922", description = "To Verify that User is able to download Firms in CSV format from Tracking List")
	public void tc1922(String username, String password, String trackingListName) throws InterruptedException {
		HomePage homePage = loginAs(username, password);

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		ProjectResultsPage projectResultsPage = trackingLists.clickOnATrackingList(trackingListName);

		List<Integer> projectTitlesToCheck = new ArrayList<Integer>();
		projectTitlesToCheck.add(0);
		projectResultsPage.selectProjectsByIndex(projectTitlesToCheck);

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 5, 2000));

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verifying downloading when private notes check box is checked/unchecked  in My Preferences page.")
	public void tc1950(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String projectPDF = "projectPDF" + CommonUtils.getTimeStamp();

		HomePage homePage = loginAs(username, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();

		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		if (!myPreferencesPage.iskIncludePrivateNotesCheckboxSelected()) {
			myPreferencesPage.clickChkIncludePrivateNotes();
		}
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();

		// Add a private note.
		projectPage.clickOnActionsDropDown();
		addNotesOnProjectsPage(projectPage, true);
		Assert.assertTrue(projectPage.isNotesTabClickable());
		projectResultsPage = projectPage.clickOnProjectsTab();
		projectResultsPage.clickOnFistProjectCheckbox();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.setDownloadName(projectPDF);
		String downloadedPDFFileName = downloadProjects.getDownloadFileName() + ".pdf";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 25, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Project Download while pressing Enter Key (TC16121).")
	public void tc1951(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String resultPerPage = "10";
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		String downloadedPDFFileName = downloadProjects.getDownloadFileName() + ".pdf";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 25, 2000));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "[DGN] [GY:I6353310] Reporting 2 issues with DGN users downloading projects in XML format (TC18039).")
	public void tc1952(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		String downloadedPDFFileName = downloadProjects.getDownloadFileName() + ".pdf";
		downloadProjects.clickOnDownloadBtn();

		Download download = projectResultsPage.switchToDownloadNewTab();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 25, 2000));

		Assert.assertTrue(download.checkDodgeLogo(), "Blank white screen is returned when download project is done.");
		download.returnToDefaultContentPage();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the default file name when downloading documents from plan tab")
	public void tc1955(String emailAddress, String password) throws InterruptedException {
		String projectDR = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		projectDR = projectPage.getDRNumerOnly();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();

		projectPlansPage.clickOnFirstOtherOrSpecialPlan();
		DownloadDocument downloadDocument = projectPlansPage.mouseOverActionandClickDownloadDocumentsWithReturnObject();

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadDocument.clickOnSavePlanDocumentButton();
		CommonUtils.isFileDownloaded(downloadDestination, "", 1, 10000);

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains(projectDR + "_Plans.zip"));
		CommonUtils.deleteFile(downloadDestination, projectDR + "_Plans.zip");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1966", description = "Verify the low and high valuation value when project having a specific valuation range in downloaded project CSV file (TC14730).")
	public void tc1966(String username, String password, String templateDropdownOption, String keyToCheck)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String projectCSV = "projectCSV" + CommonUtils.getTimeStamp();

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		List<Integer> projectTitlesToCheck = new ArrayList<Integer>();
		projectTitlesToCheck.add(0);
		projectTitlesToCheck.add(1);
		projectTitlesToCheck.add(2);
		projectResultsPage.selectProjectsByIndex(projectTitlesToCheck);

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();

		downloadProjects.selectOptionFromTemplateDropDown(templateDropdownOption);

		downloadProjects.selectAvailableFieldsDropdownOption(keyToCheck);
		downloadProjects.clickOnAddAvailableFieldButton();
		downloadProjects.clickOnCreateMyTemplateButton();

		downloadProjects.setDownloadName(projectCSV);
		CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";

		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download is not possible without creating new Template for Engineer Info.");

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 10, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "[DGN][DOWNLOADS]Error found in TC9514: Prod: User is not able to open PDF documents which are downloaded using save separate files (TC21283)")
	public void tc1985(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "companyPDF" + CommonUtils.getTimeStamp();
		String resultPerPage = "25";

		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllProjects();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		downloadCompanies.setDownloadName(downloadName);

		String downloadedPDFFileName = downloadCompanies.getDownloadFileName() + ".pdf";
		downloadCompanies.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedPDFFileName, 60, 2000));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "Common", description = "Verify the Download Project functionality on Project Firm Tab (TC22672)")
	public void tc1988(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectFirmsPage projectFirmsPage = projectResultsPage.clickOnFirmsLink();

		DownloadProjects downloadProjects = projectFirmsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 15, 2000));
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed());
		Assert.assertTrue(projectFirmsPage.getSelectedTab().contains("Firms"));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To Verify the Company Downloaded file")
	public void tc1919(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage("10");

		companyResultsPage.clickOnSelectAllCompany();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnPDFRadioBtn();

		String downloadedPDFFileName = downloadCompanies.getDownloadFileName();
		Assert.assertTrue(downloadedPDFFileName.matches(
				"Companies_(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[_\\/.](0[1-9]|1[012])([0-5]?\\d)([0-5]?\\d)(AM|PM)$"));
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File lastestDownloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		Assert.assertTrue(lastestDownloadedFile.getName().matches(
				"Companies_(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[_\\/.](0[1-9]|1[012])([0-5]?\\d)([0-5]?\\d)(AM|PM).(.*)$"));

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To Verify the Project Downloaded file in PDFformat")
	public void tc1921(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("10");
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnPDFRadioBtn();

		String downloadedPDFFileName = downloadProjects.getDownloadFileName();
		Assert.assertTrue(downloadedPDFFileName.matches(
				"Projects_(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[_\\/.](0[1-9]|1[012])([0-5]?\\d)([0-5]?\\d)(AM|PM)$"));
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File lastestDownloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		Assert.assertTrue(lastestDownloadedFile.getName().matches(
				"Projects_(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[_\\/.](0[1-9]|1[012])([0-5]?\\d)([0-5]?\\d)(AM|PM).(.*)$"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To Verify that User is able to download Firms in CSV format from DR Page")
	public void tc1924(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		projectPage.mouseOverActionandClickDownloadFirms();
		projectPage.waitforLoadingRing();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 5, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 5, 2000));
		}

		File lastestDownloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		Assert.assertTrue(lastestDownloadedFile.getName().matches(
				"^([0-9]{12})_(.*)_(19|20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[_\\/.](0[1-9]|1[012])([0-5]?\\d)([0-5]?\\d)(AM|PM).(.*)$"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1935", description = "Verify semi-colons separation for multiple  SpecAlerts in Specaleart column.")
	public void tc1935(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "specAlertCSV" + CommonUtils.getTimeStamp();
		String keyToCheck = "SPECALERTS";
		String characterToCheck = ";";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();

		specAlertsResultsPage.clickOnFirstProjectCheckbox();

		DownloadProjects downloadProjects = specAlertsResultsPage
				.mouseOverActionandClickDownloadProjects_SpecAlertPage();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadNameSpecAlerts(downloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		projectResultsPage.waitforLoadingRing();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKey(keyToCheck));
		Assert.assertTrue(csvReader.verifyKeyValueContainCharacter(keyToCheck, characterToCheck));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1936", description = "Verify addition of  SpecAlerts programs to the project downloaded  XML file in 'target-lead' field for all combinations like speacAlert is present , not present.")
	public void tc1936(String username, String password, String tagName)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectXML" + CommonUtils.getTimeStamp();

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnFirstProjectCheckbox();

		DownloadProjects downloadProjects = specAlertsResultsPage
				.mouseOverActionandClickDownloadProjects_SpecAlertPage();
		downloadProjects.clickOnXMLRadioBtn();
		downloadProjects.setDownloadNameSpecAlerts(downloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		projectResultsPage.waitforLoadingRing();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File latestDownloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		XMLReader xmlReader = new XMLReader(latestDownloadedFile);
		Assert.assertTrue(xmlReader.isTagPresent(tagName));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify low and high valuation value when project having a specific valuation in downloaded project CSV file (TC14588).")
	public void tc1937(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String projectCSV = "projectCSV" + CommonUtils.getTimeStamp();
		String lowValue = "LOW VALUE(S)";
		String highValue = "HIGH VALUE(S)";
		String valuation = "N/A";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(projectCSV);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		projectResultsPage.waitforLoadingRing();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(highValue, valuation));
		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(lowValue, valuation));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1942", description = "Verify SPECALERTS field for the project download CSV for create template option and verify the csv downloaded file.")
	public void tc1942(String username, String password, String templateDropdownOption, String keyToCheck)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String myTemplate = "myCSVTemplate" + CommonUtils.getTimeStamp();
		String downloadName = "specAlertCSV" + CommonUtils.getTimeStamp();
		String characterToCheck = ";";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();

		specAlertsResultsPage.clickOnFirstProjectCheckbox();

		DownloadProjects downloadProjects = specAlertsResultsPage
				.mouseOverActionandClickDownloadProjects_SpecAlertPage();

		downloadProjects.clickOnCSVRadioBtn();

		downloadProjects.selectOptionFromTemplateDropDown(templateDropdownOption);
		Assert.assertTrue(downloadProjects.isSPECALERTSVisibleAtTheLastInAvailableFields(),
				keyToCheck + " is not displayed at last in available field dropdown.");

		downloadProjects.selectAvailableFieldsDropdownOption(keyToCheck);
		downloadProjects.clickOnAddAvailableFieldButton();

		downloadProjects.setMyTemplateName(myTemplate);
		downloadProjects.clickOnCreateMyTemplateButton();

		downloadProjects.setDownloadNameSpecAlerts(downloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKey(keyToCheck));
		Assert.assertTrue(csvReader.verifyKeyValueContainCharacter(keyToCheck, characterToCheck));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1948", description = "Verify the low and high valuation value when project having a specific valuation range in downloaded project CSV file (TC14730).")
	public void tc1948(String username, String password, String defaultTemplateDropdownValue)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String projectCSV = "projectCSV" + CommonUtils.getTimeStamp();
		String lowValue = "LOW VALUE(S)";
		String highValue = "HIGH VALUE(S)";
		String valuation = "N/A";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertEquals(downloadProjects.getDefaultSelectedValueOfSelectATemplateDropDown(),
				defaultTemplateDropdownValue);

		downloadProjects.setDownloadName(projectCSV);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(highValue, valuation));
		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(lowValue, valuation));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the low and high valuation value when project having a specific valuation range in downloaded project CSV file (TC14730).")
	public void tc1949(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String projectCSV = "projectCSV" + CommonUtils.getTimeStamp();
		String lowValue = "LOW VALUE(S)";
		String highValue = "HIGH VALUE(S)";
		String valuation = "N/A";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();

		downloadProjects.setDownloadName(projectCSV);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(highValue, valuation));
		Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(lowValue, valuation));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the default file name when downloading documents from spec tab")
	public void tc1956(String emailAddress, String password) throws InterruptedException {
		String projectDR = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithSpecs();
		projectDR = projectPage.getDRNumerOnly();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();

		projectSpecsPage.clickOnFirstSpecCheckbox();
		DownloadDocument downloadDocument = projectSpecsPage.mouseOverActionandClickDownloadDocumentsWithReturnObject();

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadDocument.clickOnSavePlanDocumentButton();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains(projectDR + "_Specs.zip"));
		CommonUtils.deleteFile(downloadDestination, projectDR + "_Specs.zip");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the default file name when downloading documents from addenda tab")
	public void tc1957(String emailAddress, String password) throws InterruptedException {
		String projectDR = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectDR = projectPage.getDRNumerOnly();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();

		projectAddendaPage.clickOnFirstAddendaCheckbox();
		DownloadDocument downloadDocument = projectAddendaPage
				.mouseOverActionandClickDownloadDocumentsWithReturnObject();

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadDocument.clickOnSavePlanDocumentButton();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains(projectDR + "_Addenda.zip"));
		CommonUtils.deleteFile(downloadDestination, projectDR + "_Addenda.zip");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the default file name when downloading documents from addenda details  tab")
	public void tc1958(String emailAddress, String password) throws InterruptedException {
		String projectDR = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectDR = projectPage.getDRNumerOnly();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();

		String firstAddendaTitle = projectAddendaPage.getFirstAddendaTitle();
		ProjectAddendaDetailsPage projectAddendaDetailsPage = projectAddendaPage.clickOnFirstAddendaTitle();
		projectAddendaDetailsPage.clickOnFirstSpecPlanAddendaCheckbox();

		DownloadDocument downloadDocument = projectAddendaDetailsPage
				.mouseOverActionandClickDownloadDocumentsWithReturnObject();

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadDocument.clickOnSavePlanDocumentButton();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains(projectDR + "_Addenda_" + firstAddendaTitle + ".zip"));

		CommonUtils.deleteFile(downloadDestination, projectDR + "_Addenda_" + firstAddendaTitle + ".zip");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Check the add fields to Project Download functionality..")
	public void tc1971(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";
		List<String> keyToCheckList = Arrays.asList("DETAILS: BID DATE", "TARGET START DATE", "TARGET COMPLETION DATE");

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 10, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String keyToCheck : keyToCheckList)
			Assert.assertTrue(csvReader.verifyKey(keyToCheck),
					keyToCheck + " field is not present in the downloaded file.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Check the add fields to Project Download functionality.")
	public void tc1972(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String searchString = "TARGET COMPLETION DATE";
		List<String> keyToCheckList = Arrays.asList("DETAILS: BID DATE", "TARGET START DATE", "TARGET COMPLETION DATE");
		List<String> valueList = new ArrayList<>();
		String dateFormat = "MM/dd/yyyy";

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.enterSearchText(searchString);
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.clickOnNewsSearchDropdownOption();
		projectResultsPage.clickOnProjectDetailTextNewsSearchDropdownOption();

		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String keyToCheck : keyToCheckList) {
			valueList = csvReader.getValuesFromKey(keyToCheck);

			Assert.assertTrue(projectResultsPage.isValidDateFormate(valueList.get(0).trim(), dateFormat),
					keyToCheck + " date is not in MM/DD/YYYY format.");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Check the add fields to Project Download functionality.")
	public void tc1973(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";
		List<String> keyToCheckPosition = Arrays.asList("DETAILS: BID DATE/TIME", "FIRST PUBLISH DATE",
				"TARGET START DATE");

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnNextSevenDays();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);
		Assert.assertTrue(csvReader.verifyKey(keyToCheckPosition.get(0)));
		Assert.assertTrue(csvReader.verifyKey(keyToCheckPosition.get(1)));
		Assert.assertTrue(csvReader.verifyKey(keyToCheckPosition.get(2)));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Check the add fields to Project Download functionality.")
	public void tc1974(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String searchString = "TARGET COMPLETION DATE";
		List<String> keyToCheckList = Arrays.asList("DETAILS: BID DATE", "TARGET START DATE", "TARGET COMPLETION DATE");
		List<String> valueList = new ArrayList<>();

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.enterSearchText(searchString);
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.clickOnNewsSearchDropdownOption();
		projectResultsPage.clickOnProjectDetailTextNewsSearchDropdownOption();

		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.compareBidDate(csvReader.getValuesFromKey(keyToCheckList.get(0)).get(0).trim()));

		Assert.assertEquals(csvReader.getValuesFromKey(keyToCheckList.get(1)).get(0).trim(),
				projectPage.getTargetStartDate());

		Assert.assertEquals(csvReader.getValuesFromKey(keyToCheckList.get(2)).get(0).trim(),
				projectPage.getTargetCompletionDate());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Check the Value of the Null Fields.")
	public void tc1976(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String valueToCheck = "N/A";
		List<String> keyToCheckList = Arrays.asList("DETAILS: BID DATE", "TARGET START DATE", "TARGET COMPLETION DATE");

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnBiddingWithinNext7DaysOption();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String keyToCheck : keyToCheckList) {
			Assert.assertTrue(csvReader.verifyKey(keyToCheck),
					keyToCheck + " field is not present in the downloaded file.");
			Assert.assertTrue(csvReader.verifyKeyDoesNotContainValue(keyToCheck, valueToCheck),
					keyToCheck + " contain value " + valueToCheck);
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1978", description = "Check whether NDS or ASAP is getting populated to the details:bid date field.")
	public void tc1978(String username, String password, String biddingFilter)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		List<String> keyToCheckList = Arrays.asList("DETAILS: BID DATE", "TARGET START DATE", "TARGET COMPLETION DATE");
		List<String> valueList = new ArrayList<>();
		valueList.add(biddingFilter);

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnASAPBiddingDays();
		projectResultsPage.clickOnFistProjectCheckbox();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 10, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String keyToCheck : keyToCheckList)
			Assert.assertTrue(csvReader.verifyKey(keyToCheck),
					keyToCheck + " field is not present in the downloaded file.");

		Assert.assertTrue(csvReader.verifyKeyValues(keyToCheckList.get(0), valueList),
				keyToCheckList.get(0) + " does not contain the bidding filter value : " + valueList.get(0));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify that the URL in the CSV download for the DRs are working (TC20647).")
	public void tc1982(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String firstDRNumber = "";
		String downloadName = "projectCSV" + CommonUtils.getTimeStamp();
		String key = "URL LINK TO PROJECT";
		List<String> valueList = new ArrayList<>();

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		firstDRNumber = projectResultsPage.getFirstDRNumer();
		projectResultsPage.clickOnFistProjectCheckbox();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.setDownloadName(downloadName);
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 10, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 10, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);

		CSVReader csvReader = new CSVReader(downloadedFile);

		valueList = csvReader.getValuesFromKey(key);
		ProjectPage projectPage = projectResultsPage.navigateToProjectPageURL(valueList.get(0));

		Assert.assertTrue(projectPage.compareDRNumber(firstDRNumber),
				"URL is not navigated to same downloaded project.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1924", description = "Verify the project accessed is getting downloaded when multiple reports are accessed in different tabs")
	public void tc1983(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPageFirstProject = projectResultsPage.openProjectPageInNewTab(0);
		ProjectPage projectPageFifthProject = projectResultsPage.openProjectPageInNewTab(4);
		Set<String> allWindowHanldes = projectResultsPage.getWindowHandles();
		List<String> allWindowHandlesList = new ArrayList<>(allWindowHanldes);
		projectResultsPage.switchTab(allWindowHandlesList.get(1));
		DownloadProjects downloadProjects = projectPageFirstProject.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnXMLRadioBtn();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
		File lastestDownloadedFileFirstProj = CommonUtils.getLatestDownloadedFile(downloadDestination);
		projectPageFirstProject.switchTab(allWindowHandlesList.get(2));
		DownloadProjects downloadProjectsNewTab = projectPageFifthProject.mouseOverActionandClickDownloadProjects();
		downloadProjectsNewTab.clickOnXMLRadioBtn();
		downloadProjectsNewTab.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
				lastestDownloadedFileFirstProj.lastModified(), retryAttempts, retryDelay));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "To Verify that User is able to download Firms in CSV format for all the Projects in a page.")
	public void tc1923(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		List<String> keyList = Arrays.asList("FIRM ADDRESS LINE1", "FIRM ADDRESS LINE2", "FIRM CITY", "FIRM STATE",
				"FIRM ZIP", "FIRM COUNTY", "FIRM COUNTRY", "FIRM PHONE", "FIRM PHONE EXT", "FIRM FAX", "FIRM EMAIL",
				"FIRM WEBSITE");

		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		projectFirmsPage.clickOnActionsDropdown();
		projectFirmsPage.clickActionsDropDownDownloadFirms();
		projectFirmsPage.waitforLoadingRing();
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project firm csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "To Verify the data displayed in the CSV document which has been downloaded from DR page .")
	public void tc1925(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		List<String> keyList = Arrays.asList("FIRM ADDRESS LINE1", "FIRM ADDRESS LINE2", "FIRM CITY", "FIRM STATE",
				"FIRM ZIP", "FIRM COUNTY", "FIRM COUNTRY", "FIRM PHONE", "FIRM PHONE EXT", "FIRM FAX", "FIRM EMAIL",
				"FIRM WEBSITE");
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		projectFirmsPage.clickOnActionsDropdown();
		projectFirmsPage.clickActionsDropDownDownloadFirms();
		projectFirmsPage.waitforLoadingRing();
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project firm csv file");
		}
		homePage.clickOnSignOutButton();
	}

	public void addNotesOnProjectsPage(ProjectPage projectPage, boolean isPrivateNote) {
		NotesPage notesPagePrivateNote = projectPage.clickOnAddNotesMenu();
		if (isPrivateNote) {
			notesPagePrivateNote.clickOnPrivateRadioButton();
		} else {
			notesPagePrivateNote.clickOnPublicRadioButton();
		}
		notesPagePrivateNote.enterNoteText("Test Note " + CommonUtils.getTimeStamp());
		notesPagePrivateNote.clickOnSaveButon();
	}
}

package com.ddaqe.dgn_download_batch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.XMLReader;
import com.ddaqe.utils.ZipFileReader;

@Listeners(TestListener.class)

public class DGNDownloadBatch2 extends BaseTest {
	static Logger log = Logger.getLogger(DGNDownloadBatch2.class);

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
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "Allow downloading of XML as multiple files - multiple projects - Less than or equal to 100")
	public void tc225AndTc226(String emailAddress, String password)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectResultsPage projectResultsPageWithNote = null;
		if (!projectResultsPage.isProjectTitleWithNotesPresent()) {
			ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
			// Add a private note.
			projectPage.clickOnActionsDropDown();
			addNotesOnProjectsPage(projectPage, true);
			Assert.assertTrue(projectPage.isNotesTabClickable());
			// Add a public note.
			projectPage.clickOnActionsDropDown();
			addNotesOnProjectsPage(projectPage, false);
			Assert.assertTrue(projectPage.isNotesTabClickable());
			projectResultsPageWithNote = projectPage.clickOnProjectsTab();
		} else {
			projectResultsPageWithNote = projectResultsPage;
		}
		if (projectResultsPageWithNote.getTrackedProjectCount() == 0) {
			// Add private tracking list
			addTrackingListOnProjectsPage(projectResultsPageWithNote, "private");
			// Add public tracking list
			addTrackingListOnProjectsPage(projectResultsPageWithNote, "public");
		}
		ProjectResultsPage projectResultsPageUpdated = projectResultsPageWithNote
				.selectResultDropdownValueUpdatedResultPage("50");
		projectResultsPageUpdated.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnXMLRadioBtn();
		downloadProjects.clickOnSaveToSingleFilesRadioButton();
		String downloadFileName = downloadProjects.getDownloadFileName() + ".xml";
		// Get the latest downloaded file before downloading a new file.
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedFileName = downloadedFile.getName();
		Assert.assertEquals(downloadedFileName, downloadFileName);
		XMLReader xmlReader = new XMLReader(downloadedFile);
		Assert.assertTrue(xmlReader.isTagPresent("public-note-line"));
		Assert.assertTrue(xmlReader.isTagPresent("private-note-line"));
		Assert.assertTrue(xmlReader.isTagPresent("public-folders-line"));
		Assert.assertTrue(xmlReader.isTagPresent("private-folders-line"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "Allow downloading of XML as multiple files - multiple projects - Less than or equal to 100")
	public void tc230AndTc231(String emailAddress, String password)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		String drNumber = projectResultsPage.getFirstDRNumer();
		ProjectResultsPage projectResultsPageWithNote = null;
		if (!projectResultsPage.isProjectTitleWithNotesPresent()) {
			ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
			// Add a private note.
			projectPage.clickOnActionsDropDown();
			addNotesOnProjectsPage(projectPage, true);
			Assert.assertTrue(projectPage.isNotesTabClickable());
			// Add a public note.
			projectPage.clickOnActionsDropDown();
			addNotesOnProjectsPage(projectPage, false);
			Assert.assertTrue(projectPage.isNotesTabClickable());
			projectResultsPageWithNote = projectPage.clickOnProjectsTab();
		} else {
			projectResultsPageWithNote = projectResultsPage;
		}
		if (projectResultsPageWithNote.getTrackedProjectCount() == 0) {
			// Add private tracking list
			addTrackingListOnProjectsPage(projectResultsPageWithNote, "private");
			// Add public tracking list
			addTrackingListOnProjectsPage(projectResultsPageWithNote, "public");
		}
		ProjectResultsPage projectResultsPageUpdated = projectResultsPageWithNote
				.selectResultDropdownValueUpdatedResultPage("50");
		projectResultsPageUpdated.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPageUpdated.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnXMLRadioBtn();
		downloadProjects.clickOnSaveToSeparateFilesRadioButton();
		// Get the latest downloaded file before downloading a new file.
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadProjects.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains("Download"));
		ZipFileReader zipFileReader = new ZipFileReader(downloadedZipFile);
		InputStream xmlInputStream = zipFileReader.readFileInsideZip((drNumber.trim()) + ".xml");
		Assert.assertTrue(xmlInputStream != null);
		XMLReader xmlReader = new XMLReader(xmlInputStream);
		Assert.assertTrue(xmlReader.isTagPresent("public-note-line"));
		Assert.assertTrue(xmlReader.isTagPresent("private-note-line"));
		Assert.assertTrue(xmlReader.isTagPresent("public-folders-line"));
		Assert.assertTrue(xmlReader.isTagPresent("private-folders-line"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "Allow downloading of XML as a single file - multiple companies - less than or equal to 100")
	public void tc232(String emailAddress, String password)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> companyProjectTitles = companyResultsPage.getListCompanyProjectTitle();
		int companyNameCounter = 0;
		boolean companyNameFound = false;
		for (String companyProjectTitle : companyProjectTitles) {
			if (companyProjectTitle.length() > 2) {
				companyNameFound = true;
				break;
			}
			companyNameCounter++;
		}
		Assert.assertTrue(companyNameFound);
		// Add Notes to a company.
		CompanyResultsPage companyResultsPageWithNote = null;
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleByIndex(companyNameCounter);
		// Add a private note.
		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, true);
		Assert.assertTrue(companyPage.isNotesTabClickable());
		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, false);
		Assert.assertTrue(companyPage.isNotesTabClickable());
		companyResultsPageWithNote = homePage.clickOnCompaniesLink();
		CompanyResultsPage companyResultsPageUpdated = companyResultsPageWithNote
				.selectResultsPerPageUpdatedResultPage("50");
		companyResultsPageUpdated.clickOnSelectAllProjects();
		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnXMLRadioBtn();
		downloadCompanies.clickOnSaveToSingleFilesRadioButton();
		String downloadFileName = downloadCompanies.getDownloadFileName();

		// Get the latest downloaded file before downloading a new file.
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedFileName = downloadedFile.getName();
		Assert.assertEquals(downloadedFileName, downloadFileName + ".xml");
		XMLReader xmlReader = new XMLReader(downloadedFile);
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-public"));
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-private"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "Allow downloading of XML as a single file - multiple companies - less than or equal to 100")
	public void tc233(String emailAddress, String password)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> companyProjectTitles = companyResultsPage.getListCompanyProjectTitle();
		int companyNameCounter = 0;
		boolean companyNameFound = false;
		for (String companyProjectTitle : companyProjectTitles) {
			if (companyProjectTitle.length() > 2) {
				companyNameFound = true;
				break;
			}
			companyNameCounter++;
		}
		Assert.assertTrue(companyNameFound);
		// Add Notes to a company.
		CompanyResultsPage companyResultsPageWithNote = null;
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleByIndex(companyNameCounter);
		// Add a private note.
		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, true);
		Assert.assertTrue(companyPage.isNotesTabClickable());

		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, false);
		Assert.assertTrue(companyPage.isNotesTabClickable());

		companyResultsPageWithNote = homePage.clickOnCompaniesLink();

		CompanyResultsPage companyResultsPageUpdated = companyResultsPageWithNote
				.selectResultsPerPageUpdatedResultPage("50");

		companyResultsPageUpdated.clickOnSelectAllProjects();
		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnXMLRadioBtn();
		downloadCompanies.clickOnSaveToSingleFilesRadioButton();
		String downloadFileName = downloadCompanies.getDownloadFileName();

		// Get the latest downloaded file before downloading a new file.
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedFileName = downloadedFile.getName();
		Assert.assertEquals(downloadedFileName, downloadFileName + ".xml");
		XMLReader xmlReader = new XMLReader(downloadedFile);
		Assert.assertTrue(xmlReader.isTagPresent("companies"));
		Assert.assertTrue(xmlReader.isTagPresent("company"));
		Assert.assertTrue(xmlReader.isTagPresent("summary"));
		Assert.assertTrue(xmlReader.isTagPresent("company-information"));
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-public"));
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-private"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadBatchDataProvider.class, dataProvider = "TC230", description = "Allow downloading of XML as multiple files - multiple projects - Less than or equal to 100")
	public void tc234AndTc235(String emailAddress, String password)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<String> companyProjectTitles = companyResultsPage.getListCompanyProjectTitle();
		int companyNameCounter = 0;
		boolean companyNameFound = false;
		for (String companyProjectTitle : companyProjectTitles) {
			if (companyProjectTitle.length() > 2) {
				companyNameFound = true;
				break;
			}
			companyNameCounter++;
		}
		Assert.assertTrue(companyNameFound);
		// Add Notes to a company.
		CompanyResultsPage companyResultsPageWithNote = null;
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleByIndex(companyNameCounter);
		// Add a private note.
		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, true);
		Assert.assertTrue(companyPage.isNotesTabClickable());

		companyPage.clickOnActionsDropDown();
		addNotesOnCompaniesPage(companyPage, false);
		Assert.assertTrue(companyPage.isNotesTabClickable());

		companyResultsPageWithNote = homePage.clickOnCompaniesLink();

		// Track the company.
		addTrackingListOnCompaniesPage(companyResultsPageWithNote, companyNameCounter, "private");
		addTrackingListOnCompaniesPage(companyResultsPageWithNote, companyNameCounter, "public");

		CompanyResultsPage companyResultsPageUpdated = companyResultsPageWithNote
				.selectResultsPerPageUpdatedResultPage("50");

		companyResultsPageUpdated.clickOnSelectAllProjects();
		DownloadCompanies downloadCompanies = companyResultsPageUpdated.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnXMLRadioBtn();
		downloadCompanies.clickOnSaveToSeparateFilesRadioButton();

		// Get the latest downloaded file before downloading a new file.
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedZipFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		String downloadedZipFileName = downloadedZipFile.getName();
		Assert.assertTrue(downloadedZipFileName.contains("Download"));

		ZipFileReader zipFileReader = new ZipFileReader(downloadedZipFile);
		InputStream xmlInputStream = zipFileReader
				.readFileInsideZip((companyProjectTitles.get(companyNameCounter)) + ".xml");
		Assert.assertTrue(xmlInputStream != null);
		XMLReader xmlReader = new XMLReader(xmlInputStream);
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-public"));
		Assert.assertTrue(xmlReader.isTagPresent("user-company-notes-private"));
		Assert.assertTrue(xmlReader.isTagPresent("public-folders-line"));
		Assert.assertTrue(xmlReader.isTagPresent("private-folders-line"));

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

	public void addNotesOnCompaniesPage(CompanyPage companyPage, boolean isPrivateNote) {
		NotesPage notesPagePrivateNote = companyPage.clickOnAddNotesMenu();
		if (isPrivateNote) {
			notesPagePrivateNote.clickOnPrivateRadioButton();
		} else {
			notesPagePrivateNote.clickOnPublicRadioButton();
		}
		notesPagePrivateNote.enterNoteText("Test Note " + CommonUtils.getTimeStamp());
		notesPagePrivateNote.clickOnSaveButon();
	}

	public void addTrackingListOnProjectsPage(ProjectResultsPage projectResultsPage, String trackingType) {
		TrackPopUpPage trackPopUpPagePrivate = projectResultsPage.clickOnTrackLinkFromSummary();
		trackPopUpPagePrivate.selectTrackingType(trackingType);
		trackPopUpPagePrivate.enterNewTrackingListName("Tracking List " + CommonUtils.getTimeStamp());
		trackPopUpPagePrivate.clickOnSaveBtn();
	}

	public void addTrackingListOnCompaniesPage(CompanyResultsPage companyResultsPage, int companyIndex,
			String trackingType) {
		TrackPopUpPage trackPopUpPagePrivate = companyResultsPage.clickOnTrackLinkFromSummary(companyIndex);
		trackPopUpPagePrivate.selectTrackingType(trackingType);
		trackPopUpPagePrivate.enterNewTrackingListName("Tracking List " + CommonUtils.getTimeStamp());
		trackPopUpPagePrivate.clickOnSaveBtn();
	}

}
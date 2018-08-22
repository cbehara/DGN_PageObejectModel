package com.ddaqe.dgn_custom_csv;

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
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectAddendaDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNCustomCSV2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNCustomCSV2.class);

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
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Downloading few fields without creating CSV template (TC9561)")
	public void DGNT413(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKey("COMPANY TYPE"), "key  is not present in the downloaded csv file");

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "commonplus", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - ALL FIELDS (PLUS User) (TC9564)")
	public void DGNT416(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		Assert.assertFalse(downloadCompanies.iscSVTemplateNameVisible());
		Assert.assertFalse(downloadCompanies.iscSVTemplateCreateVisible());

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
		List<String> keyList = Arrays.asList("COMPANY NAME", "COMPANY ADDRESS LINE1", "COMPANY ADDRESS LINE2",
				"COMPANY CITY", "COMPANY STATE", "COMPANY ZIP", "COMPANY COUNTY", "COMPANY COUNTRY", "COMPANY PHONE",
				"COMPANY FAX", "COMPANY EMAIL", "COMPANY WEBSITE", "COMPANY URL", "TOTAL PROJECTS", "TOTAL VALUATION",
				"USER NOTES (PRIVATE)", "USER NOTES (PUBLIC)", "TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)",
				"CONTACT TITLE", "CONTACT FULL NAME", "CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME",
				"CONTACT ADDRESS LINE1", "CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP",
				"CONTACT COUNTY", "CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - ALL FIELDS (PLATINUM User) (TC9565)")
	public void DGNT417(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		Assert.assertFalse(downloadCompanies.iscSVTemplateNameVisible());
		Assert.assertFalse(downloadCompanies.iscSVTemplateCreateVisible());
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();
		List<String> keyList = Arrays.asList("COMPANY NAME", "COMPANY ADDRESS LINE1", "COMPANY ADDRESS LINE2",
				"COMPANY CITY", "COMPANY STATE", "COMPANY ZIP", "COMPANY COUNTY", "COMPANY COUNTRY", "COMPANY PHONE",
				"COMPANY FAX", "COMPANY EMAIL", "COMPANY WEBSITE", "COMPANY URL", "TOTAL PROJECTS", "TOTAL VALUATION",
				"USER NOTES (PRIVATE)", "USER NOTES (PUBLIC)", "TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)",
				"CONTACT TITLE", "CONTACT FULL NAME", "CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME",
				"CONTACT ADDRESS LINE1", "CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP",
				"CONTACT COUNTY", "CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T426", description = "CUSTOM CSV - COMPANY - Enable custom CSV template capability from Company Search Results pages - Company Results page (TC9581)")
	public void DGNT426(String emailAddress, String password, String searchText, String createTemplate,
			String companyName, String templateName) throws Exception {

		List<String> keyList = Arrays.asList("COMPANY NAME");

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		companyResultsPage.clickOnFirstCompanyChkBox();

		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		downloadCompanies.clickOnCSVRadioBtn();

		downloadCompanies.selectOptionFromTemplateDropDown(createTemplate);
		downloadCompanies.selectAvailableFieldsDropdownOption(companyName);

		downloadCompanies.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadCompanies.isFieldPresentInSelectedFields(companyName));
		downloadCompanies.setMyTemplateName(templateName);
		downloadCompanies.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadCompanies.isTemplateCreated(templateName));
		downloadCompanies.clickOnSaveToSingleFilesRadioButton();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T430", description = "CUSTOM CSV - COMPANY - Enable custom CSV template capability from Company Detail pages - Company Contacts page (TC9585)")
	public void DGNT430(String emailAddress, String password, String createTemplate, String companyName,
			String templateName) throws Exception {

		List<String> keyList = Arrays.asList("COMPANY NAME");

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnCompanyContactsLink();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		downloadCompanies.clickOnCSVRadioBtn();

		downloadCompanies.selectOptionFromTemplateDropDown(createTemplate);
		downloadCompanies.selectAvailableFieldsDropdownOption(companyName);

		downloadCompanies.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadCompanies.isFieldPresentInSelectedFields(companyName));
		downloadCompanies.setMyTemplateName(templateName);
		downloadCompanies.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadCompanies.isTemplateCreated(templateName));

		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompanies.clickOnDownloadBtn();
		homePage.clickOnSignOutButton();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "  User ability to create a new CSV template - Downloading few fields without creating CSV template (TC9072)")
	public void tc533(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.mouseOverActionandCheckdownloadCompanyDisplayed(),
				"Download Company Option is not Dispalyed");
		DownloadCompanies downloadCompany = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(companyPage.isPopUpDownloadCompanyDisplayed(), "Download Company pop is not Dispalyed");
		downloadCompany.clickOnCSVRadioBtn();
		downloadCompany.selectCreateNewTemplate();
		List<String> selectedFieldList = downloadCompany.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		downloadCompany.selectFieldByCTRL();
		downloadCompany.clickOnAddBtn();
		List<String> selectedFieldLst = downloadCompany.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		downloadCompany.getTemplateName();
		downloadCompany.clickOnCreateButton();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		downloadCompany.clickOnDownloadBtn();

		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, 20, 2000));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), 20, 2000));
		}

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC536", description = "User ability to create a new CSV template - ALL FIELDS (PLUS User) (TC9075)")
	public void tc536(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		List<String> selectedLst = downloadProjects.getFieldList();
		Assert.assertTrue(selectedLst.isEmpty(), "selected list is empty");
		Assert.assertFalse(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is displayed");
		List<String> keyList = Arrays.asList("ZIP", "TYPE OF CONSTRUCTION", "DETAILS: BID DATE", "STATE",
				"DETAILS: SQUARE FOOTAGE", "SPECALERTS", "SUB-TITLE", "TARGET START DATE", "ADDITIONAL FEATURES",
				"STATUS", "DETAILS: SITE NAME", "COUNTRY", "ADDENDA AVAILABLE", "HIGH VALUE(S)",
				"TRACKING LISTS (PRIVATE)", "DETAILS: BID DATE/TIME", "DETAILS: NBR STORIES ABOVE", "COUNTY",
				"DETAILS: BID SUBMIT TO", "PLANS AVAILABLE", "ACTION PHASE(S)", "FIRST PUBLISH DATE",
				"DETAILS: FRAMING TYPE", "TARGET COMPLETION DATE", "SPECS AVAILABLE", "PROJECT NUMBER",
				"PROJECT TYPE(S)", "DETAILS: NUMBER OF BLDGS", "PUBLISH DATE", "LOW VALUE(S)",
				"DETAILS: NBR STORIES BELOW", "CITY", "URL LINK TO PROJECT", "VERSION", "PROJECT DELIVERY SYSTEM",
				"TITLE", "ADDRESS LINE 1", "ADDRESS LINE 2");
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
		Assert.assertTrue(csvReader.getKeyList().containsAll(keyList),
				"Expected Key List is not present in the companies download csv file");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "Enable custom CSV template capability from Project Report pages - Project page (TC9181)")
	public void tc549(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckDownloadProjectsDisplayed(),
				"Download Project Option is not dispalyed");
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		List<String> selectedFieldList = downloadProjects.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		downloadProjects.selectNewFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		downloadProjects.getTemplateName();
		downloadProjects.clickOnCreateBtn();
		List<String> templateList = downloadProjects.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		downloadProjects.clickOnSaveToSingleFilesRadioButton();
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		List<String> keyList = Arrays.asList("VERSION", "SUB-TITLE", "ADDRESS LINE 1", "CITY");
		String fileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, fileName, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		Assert.assertTrue(csvReader.getKeyList().containsAll(keyList),
				"Expected Key List is not present in the companies download csv file");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "Enable custom CSV template capability from Project Report pages - Project Firms page (TC9182)")
	public void tc550(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		projectFirmsPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectFirmsPage.mouseOverActionandClickDownloadProjects();
		projectFirmsPage.clickOnCSVRadioBtn();
		projectFirmsPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectFirmsPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectFirmsPage.selectNewFieldByCTRL();
		projectFirmsPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectFirmsPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectFirmsPage.getTemplateName();
		projectFirmsPage.clickOnCreateBtn();
		List<String> templateList = projectFirmsPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "SUB-TITLE", "ADDRESS LINE 1", "CITY");
		projectFirmsPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "Enable custom CSV template capability from Project Report pages - Project Bidders page (TC9183)")
	public void tc551(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithBidders();
		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		projectBiddersPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectBiddersPage.mouseOverActionandClickDownloadProjects();
		projectBiddersPage.clickOnCSVRadioBtn();
		projectBiddersPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectBiddersPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectBiddersPage.selectNewFieldByCTRL();
		projectBiddersPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectBiddersPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectBiddersPage.getTemplateName();
		projectBiddersPage.clickOnCreateBtn();
		List<String> templateList = projectBiddersPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		projectBiddersPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "Enable custom CSV template capability from Project Report pages - Project Plans page (TC9184)")
	public void tc552(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectPlansPage.mouseOverActionandClickDownloadProjects();
		projectPlansPage.clickOnCSVRadioBtn();
		projectPlansPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectPlansPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectPlansPage.selectNewFieldByCTRL();
		projectPlansPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectPlansPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectPlansPage.getTemplateName();
		projectPlansPage.clickOnCreateBtn();
		List<String> templateList = projectPlansPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "SUB-TITLE", "ADDRESS LINE 1", "CITY");
		projectPlansPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "Enable custom CSV template capability from Project Report pages - Project Specs page (TC9185)")
	public void tc553(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectSpecsPage.mouseOverActionandClickDownloadProjects();
		projectSpecsPage.clickOnCSVRadioBtn();
		projectSpecsPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectSpecsPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectSpecsPage.selectNewFieldByCTRL();
		projectSpecsPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectSpecsPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectSpecsPage.getTemplateName();
		projectSpecsPage.clickOnCreateBtn();
		List<String> templateList = projectSpecsPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "SUB-TITLE", "ADDRESS LINE 1", "CITY");
		projectSpecsPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " Enable custom CSV template capability from Project Report pages - Project Addenda page (TC9186)")
	public void tc554(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectAddendaPage.mouseOverActionandClickDownloadProjects();
		projectAddendaPage.clickOnCSVRadioBtn();
		projectAddendaPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectAddendaPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectAddendaPage.selectNewFieldByCTRL();
		projectAddendaPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectAddendaPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectAddendaPage.getTemplateName();
		projectAddendaPage.clickOnCreateBtn();
		List<String> templateList = projectAddendaPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "SUB-TITLE", "ADDRESS LINE 1", "CITY");
		projectAddendaPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the companies download csv file");
		}

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " Enable custom CSV template capability from Project Report pages - Project Addenda Details page (TC9187)")
	public void tc555(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		ProjectAddendaDetailsPage projectAddendaDetailsPage = projectAddendaPage.clickOnFirstAddendaTitle();
		projectAddendaDetailsPage.mouseoverActionandCheckDownloadProjectDisplayed();
		projectAddendaDetailsPage.mouseOverActionandClickDownloadProjects();
		projectAddendaDetailsPage.clickOnCSVRadioBtn();
		projectAddendaPage.selectCreateNewTemplate();
		List<String> selectedFieldList = projectAddendaDetailsPage.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		projectAddendaDetailsPage.selectNewFieldByCTRL();
		projectAddendaDetailsPage.clickOnAddBtn();
		List<String> selectedFieldLst = projectAddendaDetailsPage.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		projectAddendaDetailsPage.getTemplateName();
		projectAddendaDetailsPage.clickOnCreateBtn();
		List<String> templateList = projectAddendaDetailsPage.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyNewCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
		File lastestDownloadedFileBeforeNewDownload = CommonUtils.getLatestDownloadedFile(downloadDestination);
		projectAddendaDetailsPage.clickOnDownloadBtn();
		if (null == lastestDownloadedFileBeforeNewDownload) {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, 0L, retryAttempts, retryDelay));
		} else {
			Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination,
					lastestDownloadedFileBeforeNewDownload.lastModified(), retryAttempts, retryDelay));
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - ALL FIELDS (PLATINUM User) (TC9543)")
	public void tc571(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertFalse(selectedFieldLst.isEmpty(), "selected list is empty");
		List<String> selectedFieldList = downloadProjects.getFieldList();
		Assert.assertTrue(selectedFieldList.isEmpty(), "selected list is empty");
		Assert.assertFalse(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "VERSION", "TITLE", "SUB-TITLE", "ADDRESS LINE 1",
				"ADDRESS LINE 2", "CITY", "STATE", "ZIP", "COUNTRY", "COUNTY", "PROJECT TYPE(S)", "ACTION PHASE(S)",
				"PUBLISH DATE", "STATUS", "PROJECT DELIVERY SYSTEM", "LOW VALUE(S)", "HIGH VALUE(S)", "PLANS AVAILABLE",
				"SPECS AVAILABLE", "ADDENDA AVAILABLE", "URL LINK TO PROJECT", "DETAILS: BID DATE/TIME",
				"DETAILS: BID SUBMIT TO", "DETAILS: SITE NAME", "DETAILS: SQUARE FOOTAGE", "DETAILS: NBR STORIES ABOVE",
				"DETAILS: NBR STORIES BELOW", "DETAILS: NUMBER OF BLDGS", "DETAILS: FRAMING TYPE",
				"TRACKING LISTS (PRIVATE)", "TYPE OF CONSTRUCTION", "ADDITIONAL FEATURES", "SPECALERTS");
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
		Assert.assertTrue(csvReader.getKeyList().containsAll(keyList),
				"Expected Key List is not present in the companies download csv file");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T457", description = "Verify Construction Manager field is added to the project CSV export template (TC17869)")
	public void tcDGNT457(String emailAddress, String password, String constructionManager)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {

		List<String> keyList = Arrays.asList("CONSTRUCTION MANAGER: COMPANY NAME", "CONSTRUCTION MANAGER: ADDRESS",
				"CONSTRUCTION MANAGER: CITY", "CONSTRUCTION MANAGER: STATE", "CONSTRUCTION MANAGER: ZIP",
				"CONSTRUCTION MANAGER: PHONE", "CONSTRUCTION MANAGER: FAX", "CONSTRUCTION MANAGER: E-MAIL",
				"CONSTRUCTION MANAGER: CONTACT NAME");

		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(constructionManager));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T460", description = "Verify Construction Manager field is added to a saved project CSV export template (TC17872)")
	public void tcDGNT460(String emailAddress, String password, String createTemplate, String constructionMgr)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		List<String> keyList = Arrays.asList("CONSTRUCTION MANAGER: COMPANY NAME", "CONSTRUCTION MANAGER: ADDRESS",
				"CONSTRUCTION MANAGER: CITY", "CONSTRUCTION MANAGER: STATE", "CONSTRUCTION MANAGER: ZIP",
				"CONSTRUCTION MANAGER: PHONE", "CONSTRUCTION MANAGER: FAX", "CONSTRUCTION MANAGER: E-MAIL",
				"CONSTRUCTION MANAGER: CONTACT NAME");

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(constructionMgr));

		downloadProjects.selectAvailableFieldsDropdownOption(constructionMgr);
		downloadProjects.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(constructionMgr));
		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		downloadProjects.selectOptionFromTemplateDropDown(templateName);
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(constructionMgr));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of Addition of 'Engineer Info' in the list of available fields of downloaded CSV file (TC17881)")
	public void tcDGNT462(String emailAddress, String password, String createTemplate, String engineerInfo)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		List<String> keyList = Arrays.asList("ENGINEER: COMPANY NAME", "ENGINEER: ADDRESS", "ENGINEER: CITY",
				"ENGINEER: STATE", "ENGINEER: ZIP", "ENGINEER: PHONE", "ENGINEER: FAX", "ENGINEER: E-MAIL",
				"ENGINEER: CONTACT NAME");

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(engineerInfo));

		downloadProjects.selectAvailableFieldsDropdownOption(engineerInfo);
		downloadProjects.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(engineerInfo));
		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		downloadProjects.selectOptionFromTemplateDropDown(templateName);
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(engineerInfo));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		for (String key : keyList) {
			Assert.assertTrue(csvReader.verifyKey(key), key + " is not present in the project download csv file");
		}
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of no value of 'Engineer Info' in the list of available fields of downloaded CSV file (TC17894)")
	public void tcDGNT463(String emailAddress, String password, String createTemplate, String engineerInfo)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String templateName = "Template" + CommonUtils.getTimeStamp();

		List<String> keyList = Arrays.asList("ENGINEER: COMPANY NAME", "ENGINEER: ADDRESS", "ENGINEER: CITY",
				"ENGINEER: STATE", "ENGINEER: ZIP", "ENGINEER: PHONE", "ENGINEER: FAX", "ENGINEER: E-MAIL",
				"ENGINEER: CONTACT NAME");

		HomePage homePage = loginAs(emailAddress, password);

		homePage.enterSearchText("201700548768");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();

		// Check Engineering info section not present on project page
		Assert.assertFalse(projectPage.isEngineeringInfoSectionDisplayed());

		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();

		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(engineerInfo));

		downloadProjects.selectAvailableFieldsDropdownOption(engineerInfo);
		downloadProjects.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(engineerInfo));
		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		downloadProjects.selectOptionFromTemplateDropDown(templateName);
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(engineerInfo));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));

		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		for (String key : keyList) {
			if (key == "ENGINEER: ZIP")
				Assert.assertTrue(csvReader.getValuesFromKey(key).get(0).equals("'N/A'"));
			else
				Assert.assertTrue(csvReader.getValuesFromKey(key).get(0).equals("N/A"));
		}

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T478", description = "Verify the enhancement of the project download function and split title and sub-title fields (TC20303)")
	public void tcDGNT478(String emailAddress, String password, String allFields, String createTemplate, String title,
			String subTitle) throws InterruptedException, ParserConfigurationException, SAXException, IOException {

		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(title));
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(subTitle));
		Assert.assertTrue(downloadProjects.isFieldPresentAfterFieldInSelectedFieldsBox(subTitle, title));

		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);

		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(title));
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(subTitle));
		Assert.assertTrue(downloadProjects.isFieldPresentAfterFieldInAvailableFieldsBox(subTitle, title));

		downloadProjects.selectOptionFromTemplateDropDown(allFields);
		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T478", description = "Verify the Enhancement of  the project download function and split title and sub-title fields (TC20304)")
	public void tcDGNT479(String emailAddress, String password, String allFields, String createTemplate, String title,
			String subTitle) throws InterruptedException, ParserConfigurationException, SAXException, IOException {

		HomePage homePage = loginAs(emailAddress, password);

		// Enter DR Number and Searh
		homePage.enterSearchText("201400512889");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();

		String projectTitle = projectPage.getProjectTitle();
		String projectSubTitle = projectPage.getProjectSubTitle();

		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertEquals(csvReader.getValuesFromKey(title).get(0), projectTitle);
		Assert.assertTrue(projectSubTitle.contains(csvReader.getValuesFromKey(subTitle).get(0)));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T478", description = "Verify the Enhancement of  the project download function and split title and sub-title fields (TC20305)")
	public void tcDGNT480(String emailAddress, String password, String allFields, String createTemplate, String title,
			String subTitle) throws InterruptedException, ParserConfigurationException, SAXException, IOException {

		String templateName = "Template" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		// Enter DR Number and Searh
		homePage.enterSearchText("201400512889");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();

		String projectTitle = projectPage.getProjectTitle();
		String projectSubTitle = projectPage.getProjectSubTitle();

		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		downloadProjects.selectAvailableFieldsDropdownOption(title);
		downloadProjects.selectAvailableFieldsDropdownOption(subTitle);
		downloadProjects.clickOnAddAvailableFieldButton();

		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(title));
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(subTitle));

		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertEquals(csvReader.getValuesFromKey(title).get(0), projectTitle);
		Assert.assertTrue(projectSubTitle.contains(csvReader.getValuesFromKey(subTitle).get(0)));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of Addition of 'First Publish Date' in the list of available fields of downloaded CSV file (TC17941)")
	public void tcDGNT466(String emailAddress, String password, String createTemplate, String firstPublishDate)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(firstPublishDate));

		downloadProjects.selectAvailableFieldsDropdownOption(firstPublishDate);
		downloadProjects.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(firstPublishDate));
		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertTrue(csvReader.verifyKey(firstPublishDate),
				firstPublishDate + " is not present in the project download csv file");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of 'First Publish Date' mapping to the  'first publish date' tag (TC17943)")
	public void tcDGNT468(String emailAddress, String password, String createTemplate, String firstPublishDate)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String templateName = "Template" + CommonUtils.getTimeStamp();

		HomePage homePage = loginAs(emailAddress, password);
		homePage.enterSearchText("201700564455");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();
		String date[] = projectPage.getFirstPublishedDate().split("/");
		String firstPublicationDate1 = date[2] + "0" + date[0] + "0" + date[1];

		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(firstPublishDate));

		downloadProjects.selectAvailableFieldsDropdownOption(firstPublishDate);
		downloadProjects.clickOnAddAvailableFieldButton();
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(firstPublishDate));
		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		Assert.assertEquals(csvReader.getValuesFromKey(firstPublishDate).get(0), firstPublicationDate1);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T481", description = "Verify the enhancement of the project download function and split title and sub-title fields (TC20308)")
	public void tcDGNT481(String emailAddress, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnPDFRadioBtn();

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".pdf";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 3000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T481", description = "Verify the enhancement of the project download function and split title and sub-title fields (TC20309)")
	public void tcDGNT482(String emailAddress, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnPDFRadioBtn();
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

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T481", description = "Verify the enhancement of the project download function and split title and sub-title fields. (TC20310)")
	public void tcDGNT483(String emailAddress, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnXMLRadioBtn();

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".xml";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 3000));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T481", description = "Verify the enhancement of the project download function and split title and sub-title fields. (TC20310)")
	public void tcDGNT484(String emailAddress, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
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

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T478", description = "Verify the enhancement of the project download function and split title and sub-title fields (TC20315)")
	public void tcDGNT485(String emailAddress, String password, String allFields, String createTemplate, String title,
			String subTitle) throws InterruptedException, ParserConfigurationException, SAXException, IOException {

		String templateName = "Template" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(emailAddress, password);

		// Enter DR Number and Searh
		homePage.enterSearchText("201400512889");
		ProjectPage projectPage = homePage.clickOnSearchButtonDRHomePage();

		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);

		// Add Sub-Title and Title
		downloadProjects.selectAvailableFieldsDropdownOption(subTitle);
		downloadProjects.clickOnAddAvailableFieldButton();
		downloadProjects.selectAvailableFieldsDropdownOption(title);
		downloadProjects.clickOnAddAvailableFieldButton();

		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(subTitle));
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(title));

		downloadProjects.setMyTemplateName(templateName);
		downloadProjects.clickOnCreateMyTemplateButton();
		Assert.assertTrue(downloadProjects.isTemplateCreated(templateName));

		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();

		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);

		List<String> keyList = csvReader.getKeyList();

		Assert.assertEquals(keyList.get(0), subTitle);
		Assert.assertEquals(keyList.get(1), title);

		homePage.clickOnSignOutButton();
	}
}
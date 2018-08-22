package com.ddaqe.dgn_custom_csv;

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
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNCustomCSV1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNCustomCSV1.class);

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
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Custom Templates (TC9566)")
	public void DGNT418(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.selectTemplate(1);
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 0);
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		Assert.assertTrue(downloadCompanies.iscSVTemplateCreateVisible());
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of  Addition of  'Engineer Info'  in the list of available fields of CSV export template (TC17870)")
	public void tcDGNT458(String emailAddress, String password, String createTemplate, String engineerInfo) {

		String resultPerPage = "10";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);

		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(engineerInfo));
		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of presence of 'Engineer Info'  in  newly created CSV template (TC17871)")
	public void tcDGNT459(String emailAddress, String password, String createTemplate, String engineerInfo) {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "10";

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

		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Display fields available for PLATINUM users (TC9563)")
	public void DGNT415(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		Assert.assertFalse(downloadCompanies.iscSVTemplateNameVisible());
		Assert.assertFalse(downloadCompanies.iscSVTemplateCreateVisible());
		List<String> keyList = Arrays.asList("COMPANY NAME", "COMPANY TYPE", "COMPANY ADDRESS LINE1",
				"COMPANY ADDRESS LINE2", "COMPANY CITY", "COMPANY STATE", "COMPANY ZIP", "COMPANY COUNTY",
				"COMPANY COUNTRY", "COMPANY PHONE", "COMPANY FAX", "COMPANY EMAIL", "COMPANY WEBSITE", "COMPANY URL",
				"TOTAL PROJECTS", "TOTAL VALUATION", "USER NOTES (PRIVATE)", "USER NOTES (PUBLIC)",
				"TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)", "CONTACT TITLE", "CONTACT FULL NAME",
				"CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME", "CONTACT ADDRESS LINE1",
				"CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP", "CONTACT COUNTY",
				"CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");

		List<String> options = downloadCompanies.getSelectedFieldsInCustomtemplateAllItemsInList();
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

		Assert.assertTrue(CommonUtils.CompareTwoList(options, keyList));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "commonplus", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Display fields available for PLUS users (TC9562)")
	public void DGNT414(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		Assert.assertFalse(downloadCompanies.iscSVTemplateNameVisible());
		Assert.assertFalse(downloadCompanies.iscSVTemplateCreateVisible());
		List<String> keyList = Arrays.asList("COMPANY NAME", "COMPANY TYPE", "COMPANY ADDRESS LINE1",
				"COMPANY ADDRESS LINE2", "COMPANY CITY", "COMPANY STATE", "COMPANY ZIP", "COMPANY COUNTY",
				"COMPANY COUNTRY", "COMPANY PHONE", "COMPANY FAX", "COMPANY EMAIL", "COMPANY WEBSITE", "COMPANY URL",
				"TOTAL PROJECTS", "TOTAL VALUATION", "USER NOTES (PRIVATE)", "USER NOTES (PUBLIC)",
				"TRACKING LISTS (PRIVATE)", "TRACKING LISTS (PUBLIC)", "CONTACT TITLE", "CONTACT FULL NAME",
				"CONTACT PREFIX", "CONTACT FIRST NAME", "CONTACT LAST NAME", "CONTACT ADDRESS LINE1",
				"CONTACT ADDRESS LINE2", "CONTACT CITY", "CONTACT STATE", "CONTACT ZIP", "CONTACT COUNTY",
				"CONTACT COUNTRY", "CONTACT PHONE", "CONTACT FAX", "CONTACT EMAIL");

		List<String> options = downloadCompanies.getSelectedFieldsInCustomtemplateAllItemsInList();
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

		Assert.assertTrue(CommonUtils.CompareTwoList(options, keyList));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Style Guide (UAT Testing) (TC9567)")
	public void DGNT419(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company page");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.checkForDownloadCompanyPopUpHeaderBackgroundColor());
		Assert.assertTrue(downloadCompanies.checkForDownloadCompanyPopUpTitleFont());
		Assert.assertTrue(downloadCompanies.checkForDownloadCompanyPopUpTitleFontSize());
		Assert.assertTrue(downloadCompanies.checkForDownloadCompanyPopUpTitleFontWeight());
		Assert.assertTrue(downloadCompanies.checkForDownloadCompanyPopUpTitleColor());
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T420", description = "CUSTOM CSV - COMPANY - User ability to view existing CSV templates for selection - Select a Template drop down (TC9568)")
	public void DGNT420(String emailAddress, String password, String defaultValueOfSelectATemplateDropDown,
			String createTemplate, String companyName) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company page");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		downloadCompanies.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadCompanies.isSelectTemplateDropDownDisplayed(),
				"CSV select template drop down is not displayed on Download Company dialog.");
		Assert.assertEquals(downloadCompanies.getDefaultSelectedValueOfSelectATemplateDropDown(),
				defaultValueOfSelectATemplateDropDown);
		List<String> options = downloadCompanies.getSelectTemplateDropdownOptionsInList();
		boolean ascending = false;
		List<String> listElements = CommonUtils.sortWebElements(options, ascending);
		Assert.assertTrue(CommonUtils.CompareTwoList(options, listElements));
		downloadCompanies.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadCompanies.isFieldPresentInAvailableFields(companyName));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T421", description = "CUSTOM CSV - COMPANY - User ability to view existing CSV templates for selection - Creating CSV template (Scenario 1) (TC9569)")
	public void DGNT421(String emailAddress, String password, String createTemplate, String companyName,
			String templateName) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company page");
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
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T423", description = "CUSTOM CSV - COMPANY - User ability to view existing CSV templates for selection - Creating CSV template (Scenario 2) (TC9571)")
	public void DGNT423(String emailAddress, String password, String createTemplate, String companyName,
			String templateName) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company page");
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
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T443", description = "CUSTOM CSV - COMPANY - Send CSV template file to Batch if user selects more than 100 companies - Download CSV option once user selects 100 companies (TC10075)")
	public void DGNT443(String emailAddress, String password, String expectedHeaderMsg) throws Exception {

		String resultPerPage = "500";

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		companyResultsPage.selectResultsPerPage(resultPerPage);
		companyResultsPage.clickOnSelectAllProjects();

		companyResultsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyResultsPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company page");

		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Companies Popup Dialog is not displayed");
		downloadCompanies.clickOnCSVRadioBtn();

		Assert.assertEquals(downloadCompanies.checkDownloadCompaniesPopupDialogHeaderText(), expectedHeaderMsg);
		Assert.assertTrue(downloadCompanies.isDownloadNameTextOnDownloadCompaniesPopUpDisplayed());

		Assert.assertFalse(downloadCompanies.isSaveToSingleFilesRadioButtonDisplayed(),
				"Save To Single Files radio button is displayed on Download Company dialog.");
		Assert.assertFalse(downloadCompanies.isSaveToSeparateFilesRadioButtonDisplyed(),
				"Save To Separate Files radio button is displayed on Download Company dialog.");

		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of Addition of 'First Publish Date' in the list of available fields of CSV export template (TC17939)")
	public void tcDGNT464(String emailAddress, String password, String createTemplate, String firstPublishDate) {
		String resultPerPage = "10";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(firstPublishDate));

		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of presence of 'First Publish Date' in newly created CSV template (TC17940)")
	public void tcDGNT465(String emailAddress, String password, String createTemplate, String firstPublishDate) {
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

		downloadProjects.selectOptionFromTemplateDropDown(templateName);
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(firstPublishDate));

		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of presence of 'Engineer Info' in newly created CSV template(Batch Download) (TC17951)")
	public void tcDGNT469(String emailAddress, String password, String createTemplate, String engineerInfo) {

		String resultPerPage = "500";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(engineerInfo));

		downloadProjects.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T458", description = "Verification of presence of 'Engineer Info' in newly created CSV template(Batch Download) (TC17951)")
	public void tcDGNT470(String emailAddress, String password, String createTemplate, String engineerInfo) {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "500";

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

		downloadProjects.clickOnCancelBtn();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of Addition of 'First Publish Date' in the list of available fields of CSV export template(Batch download) (TC17955)")
	public void tcDGNT473(String emailAddress, String password, String createTemplate, String firstPublishDate) {

		String resultPerPage = "500";

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectOptionFromTemplateDropDown(createTemplate);
		Assert.assertTrue(downloadProjects.isFieldPresentInAvailableFields(firstPublishDate));

		downloadProjects.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T464", description = "Verification of presence of 'First Publish Date' in newly created CSV template (Batch Down load) (TC17956)")
	public void tcDGNT474(String emailAddress, String password, String createTemplate, String firstPublishDate) {
		String templateName = "Template" + CommonUtils.getTimeStamp();
		String resultPerPage = "500";

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

		downloadProjects.selectOptionFromTemplateDropDown(templateName);
		Assert.assertTrue(downloadProjects.isFieldPresentInSelectedFields(firstPublishDate));

		downloadProjects.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "DGN-T450", description = "CUSTOM CSV - PROJECT - Send CSV template file to Batch if user selects more than 100 projects - Download CSV option once user selects 100 projects (TC10082)")
	public void DGNT450(String emailAddress, String password, String expectedHeaderMsg) throws Exception {

		String resultPerPage = "500";

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.SelectResultDropdownValue(resultPerPage);
		projectResultsPage.clickOnSelectAllProjects();

		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckDownloadProjectsDisplayed(),
				"Download Company action menu is not present for Company page");

		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isDownloadProjectsPopupDisplayed(),
				"Download Companies Popup Dialog is not displayed");
		downloadProjects.clickOnCSVRadioBtn();

		Assert.assertEquals(downloadProjects.checkDownloadProjectsPopupDialogHeaderText(), expectedHeaderMsg);
		Assert.assertTrue(downloadProjects.isDownloadNameTextOnDownloadProjectsPopUpDisplayed());

		Assert.assertFalse(downloadProjects.isSaveToSingleFilesRadioButtonDisplayed(),
				"Save To Single Files radio button is displayed on Download Company dialog.");
		Assert.assertFalse(downloadProjects.isSaveToSeparateFilesRadioButtonDisplyed(),
				"Save To Separate Files radio button is displayed on Download Company dialog.");

		downloadProjects.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating CSV template (TC9560)")
	public void DGNT412(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);

		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Giving it a name (US10323) (TC9559)")
	public void DGNT411(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "US10323";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Giving it a name (US10323) (TC9559)")
	public void DGNT406(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName("");
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		String ErrorMessage = "Invalid or missing template name.  Allowable characters in template names are a-z, A-Z, and 0-9.";
		String AlertMessage = downloadCompanies.getAlertmessage();
		Assert.assertEquals(AlertMessage, ErrorMessage);
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Download Companies CSV options - On selecting 'Create a new Template' (TC9553)")
	public void DGNT405(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Download Companies CSV options (TC9552)")
	public void DGNT404(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Download Companies pop-up options (TC9551)")
	public void DGNT403(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isSaveToSeparateFilesRadioButtonDisplyed());
		Assert.assertTrue(downloadCompanies.isSaveToSingleFilesRadioButtonDisplayed());
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Download Navigation (TC9550)")
	public void DGNT402(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed());
		Assert.assertTrue(downloadCompanies.isSaveToSeparateFilesRadioButtonDisplyed());
		Assert.assertTrue(downloadCompanies.isSaveToSingleFilesRadioButtonDisplayed());
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Download Companies CSV options (TC9552)")
	public void DGNT401(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(2);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE1"));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - CSV template names -  Verifying Case Sensitivity (TC9548)")
	public void DGNT400(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName1 = "mytemplate";
		String templateName2 = "MyTemplate";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName1);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName1));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName1), templateName1);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(2);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName2);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName2));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName2), templateName2);
		Assert.assertTrue(downloadCompanies.checkFieldSelectTemplatedropdown(templateName1));
		Assert.assertTrue(downloadCompanies.checkFieldSelectTemplatedropdown(templateName2));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - CSV template names -  Creating CSV template (TC9547)")
	public void DGNT399(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate123";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.isValuepresentInSelectTemplate(templateName));
		Assert.assertEquals(downloadCompanies.currentSelectedInSelectTemplate(templateName), templateName);
		Assert.assertTrue(downloadCompanies.isSelectTemplateDropDownSorted());
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - CSV template names - Verify characters allowed (TC9546)")
	public void DGNT398(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate_@123";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionInAvailableField(1);
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		String ErrorMessage = downloadCompanies.getAlertmessage();
		Assert.assertEquals(ErrorMessage,
				"Invalid or missing template name.  Allowable characters in template names are a-z, A-Z, and 0-9.");
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Display fields multiple selection (TC9555)")
	public void DGNT407(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplate456";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionMultipleOptionsInAvailableField();
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY TYPE"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE1"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE2"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY CITY"));
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionMultipleOptionsInAvailableFieldUsingSHift();
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName + "New");
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY TYPE"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE1"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY STATE"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ZIP"));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Up button (TC9557)")
	public void DGNT408(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplateUpButton";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionMultipleOptionsInAvailableField();
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		String optionToMove = downloadCompanies.clickandReturnOnSelectedFieldboxOption(2);
		downloadCompanies.clickOnCustomCSVUpButton();
		downloadCompanies.clickOnCustomCSVUpButton();
		String optionMoved = downloadCompanies.clickandReturnOnSelectedFieldboxOption(0);
		Assert.assertEquals(optionMoved, optionToMove);
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY TYPE"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE1"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE2"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY CITY"));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Down button (TC9558)")
	public void DGNT410(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplateDownButton";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionMultipleOptionsInAvailableField();
		downloadCompanies.clickOnAddButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		String optionToMove = downloadCompanies.clickandReturnOnSelectedFieldboxOption(0);
		downloadCompanies.clickOnCustomCSVDownButton();
		downloadCompanies.clickOnCustomCSVDownButton();
		String optionMoved = downloadCompanies.clickandReturnOnSelectedFieldboxOption(2);
		Assert.assertEquals(optionMoved, optionToMove);
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY TYPE"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE1"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY ADDRESS LINE2"));
		Assert.assertTrue(downloadCompanies.checkFieldInSelectedFieldsInCustomtemplate("COMPANY CITY"));
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "common", description = "CUSTOM CSV - COMPANY - User ability to create a new CSV template - Creating a new Companies CSV template - Add / Remove buttons (TC9556)")
	public void DGNT409(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		String templateName = "NewTemplateDownButtonAddRemove";
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnSelectAllCompany();
		companyResultsPage.clickOnActionsDropDown();
		DownloadCompanies downloadCompanies = companyResultsPage.clickOnDownloadCompaniesActionMenuwithReturn();
		downloadCompanies.clickOnCSVRadioBtn();
		int size = downloadCompanies.getSelectedFieldsInCustomtemplateSize();
		Assert.assertEquals(size, 35);
		downloadCompanies.selectTemplate(1);
		downloadCompanies.selectOptionMultipleOptionsInAvailableField();
		downloadCompanies.clickOnAddButtonCustomTemplate();
		downloadCompanies.deselectAllOptionInSelectedField();
		downloadCompanies.selectOptionMultipleOptionsInSelectedField();
		downloadCompanies.clickOnRemoveButtonCustomTemplate();
		Assert.assertTrue(downloadCompanies.iscSVTemplateNameVisible());
		downloadCompanies.setCSVtemplateName(templateName);
		Assert.assertTrue(downloadCompanies.isDisplayedCreateButtonCustomTemplate());
		downloadCompanies.clickOnCreateButtonCustomTemplate();
		String ErrorMessage = downloadCompanies.getAlertmessage();
		Assert.assertEquals(ErrorMessage, "At least one field must be selected.");
		downloadCompanies.clickOnCancelBtn();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Download Navigation (TC9061)")
	public void tc522(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Download Projects pop-up options (TC9062)")
	public void tc523(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		Assert.assertTrue(projectResultsPage.mouseOverActionandCheckDownloadProjectsDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(downloadProjects.isPDFRadioBtnDisplayed(),
				"PDF radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadProjects.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadProjects.isXMLRadioBtnDisplayed(),
				"XML radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadProjects.isSaveToSingleFilesRadioButtonDisplayed(),
				"Save To Single Files radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadProjects.isSaveToSeparateFilesRadioButtonDisplyed(),
				"Save To Separate Files radio button is not displayed on Download Company dialog.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Download Projects pop-up options (TC9062)")
	public void tc524(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectATemplateDropDownVisible());
		Assert.assertTrue(downloadProjects.isAvailableFieldsMultiSelectBoxVisible());
		Assert.assertTrue(downloadProjects.isSelectedFieldsMultiSelectBoxVisible());
		Assert.assertTrue(downloadProjects.isSPECALERTSVisibleAtTheLast());

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Download Projects CSV options (TC9063))")
	public void tc525(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectAllFieldOption();
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Creating a new Project CSV template - Capturing Error Message (TC9065)")
	public void tc526(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.clickOnCreateBtn();
		Assert.assertTrue(downloadProjects.confirmAlert(true), "Cannot navigate to download project Pop-uop");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " User ability to create a new CSV template - Creating a new Project CSV template - Display fields multiple selection (TC9066)")
	public void tc527(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		boolean isVerified = false;
		for (String dateRange : selectedFieldLst) {
			if (dateRange.contains("PROJECT NUMBER") || dateRange.contains("SUB-TITLE")
					|| dateRange.contains("ADDRESS LINE 1") || dateRange.contains("CITY")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "Selected Fields are empty");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " User ability to create a new CSV template - Creating a new Project CSV template - Display fields multiple selection (TC9067))")
	public void tc528(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		boolean isVerified = false;
		for (String dateRange : selectedFieldLst) {
			if (dateRange.contains("PROJECT NUMBER") || dateRange.contains("SUB-TITLE")
					|| dateRange.contains("ADDRESS LINE 1") || dateRange.contains("CITY")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "Selected Fields are empty");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "  User ability to create a new CSV template - Creating a new Project CSV template - Up button (TC9068)")
	public void tc529(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		downloadProjects.selectRightFieldByCTRL();
		downloadProjects.clickOnRemoveBtn();
		downloadProjects.selectFieldForUpDownButton();
		downloadProjects.clickOnUpBtn();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "  User ability to create a new CSV template - Creating a new Project CSV template - Down button (TC9069)")
	public void tc530(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		downloadProjects.selectRightFieldByCTRL();
		downloadProjects.clickOnRemoveBtn();
		downloadProjects.selectFieldForUpDownButton();
		downloadProjects.clickOnDownBtn();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " User ability to create a new CSV template - Creating a new Project CSV template - Giving it a name (US10316) (TC9070)")
	public void tc531(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		List<String> selectedFieldList = downloadProjects.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertNotNull(selectedFieldLst, "selected list is empty");
		downloadProjects.getTemplateName();
		downloadProjects.clickOnCreateBtn();
		List<String> templateList = downloadProjects.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyCSVTemplate")) {
				isVerified = true;
			}
		}
		Assert.assertTrue(isVerified, "MyCSVTemplate is not present in dropdown list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "  User ability to create a new CSV template - Creating CSV template (TC9071)")
	public void tc532(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		List<String> selectedFieldList = downloadProjects.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		downloadProjects.selectFieldByCTRL();
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
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC534", description = "  User ability to create a new CSV template - Creating a new Project CSV template - Display fields available for PLUS users (TC9073)")
	public void tc534(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "VERSION", "TITLE", "SUB TITLE", "ADDRESS LINE 1",
				"ADDRESS LINE 2", "CITY", "STATE", "ZIP", "COUNTRY", "COUNTY", "PROJECT TYPE(S)", "ACTION PHASE(S)",
				"PUBLISH DATE", "STATUS", "PROJECT DELIVERY SYSTEM", "LOW VALUE(S)", "HIGH VALUE(S)", "PLANS AVAILABLE",
				"SPECS AVAILABLE", "ADDENDA AVAILABLE", "URL LINK TO PROJECT", "DETAILS: BID DATE/TIME",
				"DETAILS: BID SUBMIT TO", "DETAILS: SITE NAME", "DETAILS: SQUARE FOOTAGE", "DETAILS: NBR STORIES ABOVE",
				"DETAILS: NBR STORIES BELOW", "DETAILS: NUMBER OF BLDGS", "DETAILS: FRAMING TYPE",
				"TRACKING LISTS (PRIVATE)", "TYPE OF CONSTRUCTION", "ADDITIONAL FEATURES", "SPECALERTS");
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertFalse(selectedFieldLst.containsAll(keyList), "Given Options are not displayed");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC535", description = " User ability to create a new CSV template - Creating a new Project CSV template - Display fields available for PLATINUM users (TC9074)")
	public void tc535(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		List<String> keyList = Arrays.asList("PROJECT NUMBER", "VERSION", "TITLE", "SUB TITLE", "ADDRESS LINE 1",
				"ADDRESS LINE 2", "CITY", "STATE", "ZIP", "COUNTRY", "COUNTY", "PROJECT TYPE(S)", "ACTION PHASE(S)",
				"PUBLISH DATE", "STATUS", "PROJECT DELIVERY SYSTEM", "LOW VALUE(S)", "HIGH VALUE(S)", "PLANS AVAILABLE",
				"SPECS AVAILABLE", "ADDENDA AVAILABLE", "URL LINK TO PROJECT", "DETAILS: BID DATE/TIME",
				"DETAILS: BID SUBMIT TO", "DETAILS: SITE NAME", "DETAILS: SQUARE FOOTAGE", "DETAILS: NBR STORIES ABOVE",
				"DETAILS: NBR STORIES BELOW", "DETAILS: NUMBER OF BLDGS", "DETAILS: FRAMING TYPE", "SPECALERTS");
		List<String> selectedFieldLst = downloadProjects.getSelectedFieldList();
		Assert.assertFalse(selectedFieldLst.containsAll(keyList), "Given Options are not displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names - text box available for naming the templates (TC9076)")
	public void tc537(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names - Verify maximum character length (TC9077)")
	public void tc538(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names - Verify characters allowed (TC9078)")
	public void tc539(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.getTemplateNameWithSpecialCharacter();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names -  Creating CSV template (TC9079)")
	public void tc540(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
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

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names -  Verifying Case Sensitivity (TC9080)")
	public void tc541(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		downloadProjects.getGivenTemplateName();
		downloadProjects.clickOnCreateBtn();
		downloadProjects.selectCreateNewTemplate();
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		downloadProjects.getCaseSensitiveTemplateName();
		downloadProjects.clickOnCreateBtn();
		List<String> templateList = downloadProjects.getTemplateList();
		boolean isVerified = false;
		for (String dateRange : templateList) {
			if (dateRange.contains("MyTemplate") && (dateRange.contains("mytemplate"))) {
				isVerified = true;
			}
		}
		Assert.assertFalse(isVerified, "MyCSVTemplate is not present in dropdown list");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " CSV template names -  Naming the template with an existing name (TC9081)")
	public void tc542(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
		downloadProjects.getGivenTemplateName();
		downloadProjects.clickOnCreateBtn();
		Assert.assertFalse(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to view existing CSV templates for selection -  Select a template drop down (TC9085)")
	public void tc543(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC544", description = " User ability to view existing CSV templates for selection -  Creating CSV template (Scenario 1) (TC9086)")
	public void tc544(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
		downloadProjects.selectCreateNewTemplate();
		Assert.assertTrue(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
		downloadProjects.selectFieldByCTRL();
		downloadProjects.clickOnAddBtn();
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
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC545", description = " User ability to view existing CSV templates for selection -  Templates are associated to a user ID not license (Scenario 1) (TC9087)")
	public void tc545(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC546", description = " User ability to view existing CSV templates for selection -  Creating CSV template  (Scenario 2) (TC9088)")
	public void tc546(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		downloadProjects.selectCreateNewTemplate();
		List<String> selectedFieldList = downloadProjects.getFieldList();
		Assert.assertNotNull(selectedFieldList, "selected list is empty");
		downloadProjects.selectFieldByCTRL();
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
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "TC547", description = "User ability to view existing CSV templates for selection -  Templates are associated to a user ID not license (Scenario 2) (TC9089)")
	public void tc547(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to view existing CSV templates for selection -  Legacy to 2.0 (OnHold - Do not test) (TC9090)")
	public void tc548(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		DownloadProjects downloadProjects = projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");
		downloadProjects.clickOnCSVRadioBtn();
		Assert.assertTrue(downloadProjects.isSelectTemplateListDisplayed(),
				"Select Template DropDown is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " Zip CSV files when 'Download as multiple file' option is selected - New Template (TC9203)")
	public void tc561(String username, String password)
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
		downloadProjects.clickOnSaveToSeparateFilesRadioButton();
		downloadProjects.clickOnDownloadBtn();
		Assert.assertFalse(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " Zip CSV files when 'Download as multiple file' option is selected - Existing Template (TC9204)")
	public void tc562(String username, String password)
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
		downloadProjects.clickOnSaveToSeparateFilesRadioButton();
		downloadProjects.clickOnDownloadBtn();
		Assert.assertFalse(downloadProjects.isTemplateNameAndCreateButtonVisisble(),
				"Template Name and Create Button is not Displayed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " CSV template names - text box available for naming the templates (TC9523)")
	public void tc568(String username, String password) throws InterruptedException {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.mouseOverActionandCheckdownloadCompanyDisplayed(),
				"Download Company action menu is not present for Company page");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Project Pop is not Dispalyed");
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.selectCreateNewTemplate();
		Assert.assertTrue(downloadCompanies.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "CSV template names - Verify maximum character length (TC9524)")
	public void tc569(String username, String password) throws InterruptedException {
		String templateName = "My CSV Template";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.mouseOverActionandCheckdownloadCompanyDisplayed(),
				"Download Company action menu is not present for Company page");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Project Pop is not Dispalyed");
		downloadCompanies.clickOnCSVRadioBtn();
		downloadCompanies.selectCreateNewTemplate();
		Assert.assertTrue(downloadCompanies.isTemplateNameAndCreateButtonVisisble(),
				"Template name n Create button is not visible");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = " Compare UI Experience with Projects Download pop-up (TC9531)")
	public void tc570(String username, String password) throws InterruptedException {
		String templateName = "My CSV Template";
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.mouseOverActionandCheckdownloadCompanyDisplayed(),
				"Download Company action menu is not present for Company page");
		DownloadCompanies downloadCompanies = companyPage.mouseOverActionandClickDownloadCompanies();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Project Pop is not Dispalyed");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = CustomCSVDataProvider.class, dataProvider = "Custom_CSVCommonDataProvider", description = "User ability to create a new CSV template - Style Guide (UAT Testing) (TC9545)")
	public void tc573(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFistProjectName();
		Assert.assertTrue(projectPage.mouseOverActionandCheckDownloadProjectDisplayed(),
				"Download Project Menu is not Displayed");
		projectPage.mouseOverActionandClickDownloadProjects();
		Assert.assertTrue(projectPage.isPopUpDownloadProjectDisplayed(), "Download Project Pop is not Dispalyed");

	}

}
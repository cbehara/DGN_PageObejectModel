package com.ddaqe.dgn_notes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyNotesPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectNotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNNotes extends TestDataCreationScript {

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

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create project notes tab page - To Verify the Create Projects Note section (TC9091)")
	public void tc474(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		Assert.assertTrue(projectnotesPage.verifyProjectNotesBreadCrumb());
		Assert.assertTrue(projectnotesPage.isProjectOverViewSectionDisplayed());
		Assert.assertTrue(projectnotesPage.isimpProjectUpdatesSectionDisplayed());
		Assert.assertTrue(projectnotesPage.isSelectAllSChkBoxDisplayed());
		Assert.assertTrue(projectnotesPage.verifyNotesDataGrid());
		projectnotesPage.mouseoverNotesActionandVerifyDropdownMenu();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create project notes tab page - To Verify the sorting the Notes Data column (TC9092)")
	public void tc475(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();

		Assert.assertTrue(projectnotesPage.verifyDateColumnSorting(true));
		Assert.assertTrue(projectnotesPage.verifyDateColumnSortingArrowSymbol(true));
		projectnotesPage.clickDateColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyDateColumnSorting(false));
		Assert.assertTrue(projectnotesPage.verifyDateColumnSortingArrowSymbol(false));

		projectnotesPage.clickUserColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyUserColumnSorting(false));
		Assert.assertTrue(projectnotesPage.verifyUserColumnSortingArrowSymbol(false));
		projectnotesPage.clickUserColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyUserColumnSorting(true));
		Assert.assertTrue(projectnotesPage.verifyUserColumnSortingArrowSymbol(true));

		projectnotesPage.clickTypeColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyTypeColumnSorting(false));
		Assert.assertTrue(projectnotesPage.verifyTypeColumnSortingArrowSymbol(false));
		projectnotesPage.clickTypeColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyTypeColumnSorting(true));
		Assert.assertTrue(projectnotesPage.verifyTypeColumnSortingArrowSymbol(true));

		projectnotesPage.clickNoteColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyNoteColumnSorting(false));
		Assert.assertTrue(projectnotesPage.verifyNoteColumnSortingArrowSymbol(false));
		projectnotesPage.clickNoteColumnHeader();
		Assert.assertTrue(projectnotesPage.verifyNoteColumnSorting(true));
		Assert.assertTrue(projectnotesPage.verifyNoteColumnSortingArrowSymbol(true));

		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create project notes tab page - Verify the Filter in the Notes data grid column (TC9093)")
	public void tc476(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		int countPrivateNotes = projectnotesPage.getNoteUserCount("Private", emailAddress);
		int countPublicNotes = projectnotesPage.getNoteUserCount("Public", emailAddress);
		projectnotesPage.selectNoteTypeFiler("Private");
		int notesDisplayed = projectnotesPage.getDisplayedNotesCount();
		Assert.assertEquals(notesDisplayed, countPrivateNotes);
		projectnotesPage.selectNoteTypeFiler("Public");
		notesDisplayed = projectnotesPage.getDisplayedNotesCount();
		Assert.assertEquals(notesDisplayed, countPublicNotes);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create project notes tab page - To Verify that When there zero Notes what options should be displayed (TC9094)")
	public void tc477(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnProjectTitleWithoutNotes();
		Assert.assertTrue(projectPage.isNotesTabDisabled());
		Assert.assertTrue(projectPage.mouseoverActionandCheckAddNotesEnabled());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderPlusUser", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc478(String emailAddress, String password, String noteText) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isNotesTabClickable());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Add notes link to project list view- To Verify that Note Link is present the Project summary view (TC9096)")
	public void tc479(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectNotesPage projectNotesPage = projectresultsPage.clickOnNotesLink();
		Assert.assertTrue(projectNotesPage.isNotesActionsDropdownDisplayed());
		Assert.assertTrue(projectNotesPage.isNotesGridDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc480(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyresultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companynotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companynotesPage.verifyProjectNotesBreadCrumb());
		Assert.assertTrue(companynotesPage.isimpProjectUpdatesSectionDisplayed());
		Assert.assertTrue(companynotesPage.isSelectAllSChkBoxDisplayed());
		Assert.assertTrue(companynotesPage.verifyNotesDataGrid());
		companynotesPage.mouseoverNotesActionandVerifyDropdownMenu();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create company notes tab page- To Verify the sorting the Notes Data column (TC9172)")
	public void tc481(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyresultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companynotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companynotesPage.verifyDateColumnSorting(true));

		Assert.assertTrue(companynotesPage.verifyDateColumnSortingArrowSymbol(true));
		companynotesPage.clickDateColumnHeader();
		Assert.assertTrue(companynotesPage.verifyDateColumnSorting(false));
		Assert.assertTrue(companynotesPage.verifyDateColumnSortingArrowSymbol(false));

		companynotesPage.clickUserColumnHeader();
		Assert.assertTrue(companynotesPage.verifyUserColumnSorting(false));
		Assert.assertTrue(companynotesPage.verifyUserColumnSortingArrowSymbol(false));
		companynotesPage.clickUserColumnHeader();
		Assert.assertTrue(companynotesPage.verifyUserColumnSorting(true));
		Assert.assertTrue(companynotesPage.verifyUserColumnSortingArrowSymbol(true));

		companynotesPage.clickTypeColumnHeader();
		Assert.assertTrue(companynotesPage.verifyTypeColumnSorting(false));
		Assert.assertTrue(companynotesPage.verifyTypeColumnSortingArrowSymbol(false));
		companynotesPage.clickTypeColumnHeader();
		Assert.assertTrue(companynotesPage.verifyTypeColumnSorting(true));
		Assert.assertTrue(companynotesPage.verifyTypeColumnSortingArrowSymbol(true));

		companynotesPage.clickNoteColumnHeader();
		Assert.assertTrue(companynotesPage.verifyNoteColumnSorting(false));
		Assert.assertTrue(companynotesPage.verifyNoteColumnSortingArrowSymbol(false));
		companynotesPage.clickNoteColumnHeader();
		Assert.assertTrue(companynotesPage.verifyNoteColumnSorting(true));
		Assert.assertTrue(companynotesPage.verifyNoteColumnSortingArrowSymbol(true));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc482(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyresultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyresultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companynotesPage = companyPage.clickOnNotesTab();
		int countPrivateNotes = companynotesPage.getNoteUserCount("Private", emailAddress);
		int countPublicNotes = companynotesPage.getNoteUserCount("Public", emailAddress);
		companynotesPage.selectNoteTypeFiler("Private");
		int notesDisplayed = companynotesPage.getDisplayedNotesCount();
		Assert.assertEquals(notesDisplayed, countPrivateNotes);
		companynotesPage.selectNoteTypeFiler("Public");
		notesDisplayed = companynotesPage.getDisplayedNotesCount();
		Assert.assertEquals(notesDisplayed, countPublicNotes);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create company notes tab page- Verify the Filter in the Notes data grid column (TC9173)")
	public void tc483(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyNotesPage companyNotesPage = companyResultsPage.clickOnNotesLink();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		Assert.assertTrue(companyNotesPage.isNotesGridDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc484(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnProjectTitleWithoutNotes();
		Assert.assertTrue(projectPage.isNotesTabDisabled());
		String urlBefore = projectPage.getURL();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String urlAfter = projectnotesPage.getURL();
		Assert.assertEquals(urlBefore, urlAfter);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Add notes tab to company profile view - To verify the Tabs displayed in the Company profile view page (TC9178)")
	public void tc485(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.isNotesTabDisplayed());
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesGridDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc486(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyTitleWithoutNotes();
		Assert.assertTrue(companyPage.isNotesTabDisabled());
		String urlBefore = companyPage.getURL();
		CompanyNotesPage companynotesPage = companyPage.clickOnNotesTab();
		String urlAfter = companynotesPage.getURL();
		Assert.assertEquals(urlBefore, urlAfter);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc487(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		Assert.assertTrue(companyNotesPage.isNotesGridDisplayed());
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To verify the Tabs displayed in the Company profile view page (TC9189)")
	public void tc488(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		Assert.assertTrue(notesPage.isAddeditNotesPopupDisplayed());
		Assert.assertEquals(notesPage.getAddeditNotePopupTitle(), "Add Note");
		Assert.assertTrue(notesPage.isPrivateRadioButtonEnabled());
		Assert.assertEquals(notesPage.getNoteText(), "");
		Assert.assertTrue(notesPage.isNoteTextFieldInfocus());
		notesPage.clickOnCancelButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To Verify the Add notes Modal Dialog box in Company Profile Page (TC9190)")
	public void tc489(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		Assert.assertTrue(notesPage.isAddeditNotesPopupDisplayed());
		Assert.assertEquals(notesPage.getAddeditNotePopupTitle(), "Add Note");
		Assert.assertTrue(notesPage.isPrivateRadioButtonEnabled());
		Assert.assertEquals(notesPage.getNoteText(), "");
		Assert.assertTrue(notesPage.isNoteTextFieldInfocus());
		notesPage.clickOnCancelButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To Verify the Add notes Modal Dialog box in Company Profile Page (TC9190)")
	public void tc490(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		char[] charArray999 = new char[999];
		Arrays.fill(charArray999, 'a');
		String noteText999Char = new String(charArray999);

		char[] charArray1000 = new char[1000];
		Arrays.fill(charArray1000, 'b');
		String noteText1000Char = new String(charArray1000);

		char[] charArray1001 = new char[1001];
		Arrays.fill(charArray1001, 'c');
		String noteText1001Char = new String(charArray1001);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		notesPage.enterNoteText(noteText999Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "1");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1000Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1001Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		Assert.assertEquals(notesPage.getNoteTextLength(), 1000);
		notesPage.clickOnCancelButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To Verify the  Add notes Cancel button (TC9195)")
	public void tc491(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		notesPage.clickOnCancelButton();
		Assert.assertFalse(notesPage.isAddeditNotesPopupDisplayed());
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String recentnoteText = projectnotesPage.getRecentNoteFromNotesTab();
		Assert.assertNotEquals(noteText, recentnoteText);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog - To Verify the Edit notes Modal Dialog box in Company Profile Page (TC9207)")
	public void tc492(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		notesPage.clickOnCancelButton();
		Assert.assertFalse(notesPage.isAddeditNotesPopupDisplayed());
		CompanyNotesPage companynotesPage = companyPage.clickOnNotesTab();
		String recentnoteText = companynotesPage.getRecentNoteFromNotesTab();
		Assert.assertNotEquals(noteText, recentnoteText);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To Verify the Add notes Modal Dialog box in Company Profile Page (TC9190)")
	public void tc493(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		char[] charArray999 = new char[999];
		Arrays.fill(charArray999, 'a');
		String noteText999Char = new String(charArray999);

		char[] charArray1000 = new char[1000];
		Arrays.fill(charArray1000, 'b');
		String noteText1000Char = new String(charArray1000);

		char[] charArray1001 = new char[1001];
		Arrays.fill(charArray1001, 'c');
		String noteText1001Char = new String(charArray1001);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		notesPage.enterNoteText(noteText999Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "1");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1000Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1001Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		Assert.assertEquals(notesPage.getNoteTextLength(), 1000);
		notesPage.clickOnCancelButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Create add/edit note modal dialog- To Verify the Add notes Modal Dialog box in Company Profile Page (TC9190)")
	public void tc494(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		char[] charArray999 = new char[999];
		Arrays.fill(charArray999, 'a');
		String noteText999Char = new String(charArray999);

		char[] charArray1000 = new char[1000];
		Arrays.fill(charArray1000, 'b');
		String noteText1000Char = new String(charArray1000);

		char[] charArray1001 = new char[1001];
		Arrays.fill(charArray1001, 'c');
		String noteText1001Char = new String(charArray1001);

		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnThirdCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.clickFirstNoteCheckbox();
		NotesPage notesPage = companyNotesPage.mouseoverNotesActionandClickEditNotes();
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText999Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "1");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1000Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		notesPage.clearNoteTextField();
		notesPage.enterNoteText(noteText1001Char);
		Assert.assertEquals(notesPage.getCharacterRemaining(), "0");
		Assert.assertEquals(notesPage.getNoteTextLength(), 1000);
		notesPage.clickOnCancelButton();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen when user selects the edit notes with out selecting any notes (TC9212)")
	public void tc495(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		Assert.assertTrue(projectnotesPage.isNotesActionsDropdownDisplayed());
		projectnotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc496(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.clickFirstNoteCheckbox();
		projectnotesPage.clickSecondNoteCheckbox();
		projectnotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("Please make one selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc497(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.clickFirstNoteCheckbox();
		NotesPage notesPage = projectnotesPage.mouseoverNotesActionandClickEditNotes();
		notesPage.clickOnPrivateRadioButton();
		notesPage.clearNoteTextField();
		notesPage.enterNoteText("Edited Description");
		notesPage.clickOnSaveButon();
		Assert.assertEquals(projectnotesPage.getRecentNoteTypeFromNotesTab(), "Private");
		Assert.assertEquals(projectnotesPage.getRecentNoteFromNotesTab(), "Edited Description");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc498(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.selectNoteAddedyOtherUser(emailAddress);
		projectnotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("You may only edit your own notes"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Delete one or more project notes - To Verify that what happen when user select Delete notes without selecting Notes (TC9216)")
	public void tc499(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		Assert.assertTrue(projectnotesPage.isNotesActionsDropdownDisplayed());
		projectnotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc500(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.selectNoteAddedyOtherUser(emailAddress);
		projectnotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("You may only delete your own notes"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc501(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.clickFirstNoteCheckbox();
		String noteTextBeforeDeletion = projectnotesPage.getRecentNoteFromNotesTab();
		String noteDateBeforeDeletion = projectnotesPage.getRecentNoteDateFromNotesTab();
		projectnotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertNotEquals(projectnotesPage.getRecentNoteTypeFromNotesTab(), noteTextBeforeDeletion);
		Assert.assertNotEquals(projectnotesPage.getRecentNoteDateFromNotesTab(), noteDateBeforeDeletion);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "View one or more project notes - To Verify the what happen when user clicks on View Notes without selecting Notes (TC9231)")
	public void tc502(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		Assert.assertTrue(projectnotesPage.isNotesActionsDropdownDisplayed());
		projectnotesPage.mouseoverNotesActionandClickViewNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Print one or more project notes - To Verify that what happen when user clicks on print Notes with out selecting the notes (TC9233)")
	public void tc503(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		Assert.assertTrue(projectnotesPage.isNotesActionsDropdownDisplayed());
		projectnotesPage.mouseoverNotesActionandClickPrintNotes();
		Assert.assertTrue(projectnotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Add a company note - To Verify that user is able to create Public the Notes in the Company Profile page (TC9236)")
	public void tc505(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.clickOnPublicRadioButton();
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
		String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
		Assert.assertEquals(noteText, recentNoteText);
		Assert.assertEquals("Public", recentNoteType);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a company note -To Verify that what happen when user selects the edit notes with out selecting any notes (TC9276)")
	public void tc506(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a company note -To Verify that what happen  when user select more than one selection (TC9277)")
	public void tc507(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.clickFirstNoteCheckbox();
		companyNotesPage.clickSecondNoteCheckbox();
		companyNotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("Please make one selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a company note -To Verify that what happen  when user select more than one selection (TC9277)")
	public void tc508(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnThirdCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.clickFirstNoteCheckbox();
		companyNotesPage.clickSecondNoteCheckbox();
		companyNotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("Please make one selection"));
		companyNotesPage.clickSecondNoteCheckbox();
		companyNotesPage.mouseoverNotesActionandClickEditNotes();
		NotesPage notesPage = companyNotesPage.mouseoverNotesActionandClickEditNotes();
		notesPage.clickOnPrivateRadioButton();
		notesPage.clearNoteTextField();
		notesPage.enterNoteText("Edited Description");
		notesPage.clickOnSaveButon();
		Assert.assertEquals(companyNotesPage.getRecentNoteTypeNotesTab(), "Private");
		Assert.assertEquals(companyNotesPage.getRecentNoteFromNotesTab(), "Edited Description");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc509(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		companyNotesPage.selectNoteAddedyOtherUser(emailAddress);
		companyNotesPage.mouseoverNotesActionandClickEditNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("You may only edit your own notes"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Delete one or more company notes - To Verify that what happen when user selects Delete notes for notes which are not created by the User (TC9281)")
	public void tc510(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc511(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		companyNotesPage.selectNoteAddedyOtherUser(emailAddress);
		companyNotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("You may only delete your own notes"));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Edit a project note - To Verify that what happen  when user select more than one selection (TC9213)")
	public void tc512(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		companyNotesPage.selectNoteTypeFiler("Private");
		String noteTextBeforeDeletion = companyNotesPage.getRecentNoteFromNotesTab();
		String noteDateBeforeDeletion = companyNotesPage.getRecentNoteDateFromNotesTab();
		companyNotesPage.clickFirstNoteCheckbox();
		
		companyNotesPage.mouseoverNotesActionandClickDeleteNotes();
		Assert.assertNotEquals(companyNotesPage.getRecentNoteFromNotesTab(), noteTextBeforeDeletion);
		Assert.assertNotEquals(companyNotesPage.getRecentNoteDateFromNotesTab(), noteDateBeforeDeletion);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "View one or more company notes - To Verify the what happen when user clicks on View Notes without selecting Notes (TC9308)")
	public void tc513(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnThirdCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
		String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
		String recentNoteDate = companyNotesPage.getRecentNoteDateFromNotesTab();
		String recentNoteEmail = companyNotesPage.getRecentNoteUserNotesTab();
		companyNotesPage.selectNoteAddedyByLoggedInUser(emailAddress);
		NotesPage notesPage = companyNotesPage.mouseoverNotesActionandClickViewNotes();
		Assert.assertEquals(notesPage.getViewNotesNoteText(), recentNoteText);
		Assert.assertEquals(notesPage.getViewNotesNoteType(), recentNoteType);
		Assert.assertEquals(notesPage.getViewNotesCreatedDate(), recentNoteDate);
		Assert.assertEquals(notesPage.getViewNotesEmail(), recentNoteEmail);
		Assert.assertTrue(notesPage.isBackButtonDisplayed());
		notesPage.verifyViewNotesBreadCrumb();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "View one or more company notes - To Verify the what happen when user clicks on View Notes without selecting Notes (TC9308)")
	public void tc514(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		Assert.assertTrue(companyNotesPage.isNotesActionsDropdownDisplayed());
		companyNotesPage.mouseoverNotesActionandClickViewNotes();
		Assert.assertTrue(companyNotesPage.checkforValidationMessage("Please make a selection"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "- To Verify that user is able to Add Notes from the Project report page Add 'add note' action to project report view action menu (TC9363)")
	public void tc515(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String recentnoteText = projectnotesPage.getRecentNoteFromNotesTab();
		String recentnoteType = projectnotesPage.getRecentNoteTypeFromNotesTab();
		Assert.assertEquals(noteText, recentnoteText);
		Assert.assertEquals("Private", recentnoteType);
		homePage.clickOnProjectsLink();
		String notestooltipText = projectresultsPage.getNoteslinkTooltipText(noteText);
		Assert.assertTrue(notestooltipText.contains(recentnoteText));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "- To Verify that user is able to Add Notes from the Project report page Add 'add note' action to project report view action menu (TC9363)")
	public void tc517(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String recentnoteText = projectnotesPage.getRecentNoteFromNotesTab();
		String recentnoteType = projectnotesPage.getRecentNoteTypeFromNotesTab();
		Assert.assertEquals(noteText, recentnoteText);
		Assert.assertEquals("Private", recentnoteType);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Show most recent added/edited note on mouse over of notes link on list view - To Verify that Show most recent added/edited note on mouse over of notes link on list view (TC9362)")
	public void tc516(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnThirdCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
		String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
		Assert.assertEquals(noteText, recentNoteText);
		Assert.assertEquals("Private", recentNoteType);
		homePage.clickOnCompaniesLink();
		String notestooltipText = companyResultsPage.getNoteslinkTooltipText(noteText);
		Assert.assertTrue(notestooltipText.contains(recentNoteText));
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Add 'add note' action to company profile view action menu - To Verify that Notes is been displayed in the downloaded PDF Company Profile  page (TC9376)")
	public void tc518(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnThirdCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
		String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
		Assert.assertEquals(noteText, recentNoteText);
		Assert.assertEquals("Private", recentNoteType);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Verify that the 'Project Note' can be added. (TC9518)")
	public void tc519(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String recentnoteText = projectnotesPage.getRecentNoteFromNotesTab();
		Assert.assertEquals(noteText, recentnoteText);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Verify the Add Note functionality on Company Project page (TC22656)")
	public void tc520(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforCompanies(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		companyPage.clickOnActionsDropDown();
		NotesPage notesPage = companyPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
		String recentnoteText = companyNotesPage.getRecentNoteFromNotesTab();
		Assert.assertEquals(noteText, recentnoteText);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = NotesDataProvider.class, dataProvider = "NotesDataProviderTestData", description = "Verify the Add Note functionality on Project Firm Tab (TC22676)")
	public void tc521(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		testdataforProjects(emailAddress,password,noteText,emailAddress2,password2);

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnFirstProjectTitle();
		ProjectFirmsPage projectfirmsPage = projectPage.clickOnFirmsTab();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		Assert.assertTrue(projectfirmsPage.isFirmsResultSectionDisplayed());
		Assert.assertFalse(notesPage.isSavebuttonDisplayed());
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		String recentnoteText = projectnotesPage.getRecentNoteFromNotesTab();
		Assert.assertEquals(noteText, recentnoteText);
		homePage.clickOnSignOutButton();
	}
}

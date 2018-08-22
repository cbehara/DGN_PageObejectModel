package com.ddaqe.dgn_notes;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyNotesPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.ProjectNotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class TestDataCreationScript extends BaseTest {

	static Logger log = Logger.getLogger(TestDataCreationScript.class);

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

	public void testdataforProjects(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		String URl = projectPage.getCurrentURl();

		if (projectPage.isNotesTabDisabled()) {
			for (int i = 1; i < 3; i++) {
				projectPage.clickOnActionsDropDown();
				NotesPage notesPage = projectPage.clickOnAddNotesMenu();
				noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				ProjectNotesPage projectNotesPage = projectPage.clickOnNotesTab();
				Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
				Assert.assertEquals("Private", projectNotesPage.getRecentNoteTypeFromNotesTab());
				projectPage.clickOnActionsDropDown();
				projectPage.clickOnAddNotesMenu();
				noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.clickOnPublicRadioButton();
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				projectNotesPage.getRecentNoteFromNotesTab();
				Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
				Assert.assertEquals("Public", projectNotesPage.getRecentNoteTypeFromNotesTab());
			}
		}

		homePage.clickOnProjectsLink();

		projectResultsPage.clickOnProjectTitleWithoutNotes();
		if (!projectPage.isNotesTabDisabled()) {
			projectPage.clickOnActionsDropDown();
			ProjectNotesPage projectNotesPage = projectPage.clickOnNotesTab();
			projectNotesPage.clickSelectAllCheckbox();
			projectNotesPage.mouseoverNotesActionandClickDeleteNotes();
		}

		homePage.clickOnSignOutButton();
		loginAs(emailAddress2, password2);
		homePage.openURL(URl);
		ProjectNotesPage projectNotesPage = projectPage.clickOnNotesTab();

		for (int i = 1; i < 2; i++) {
			projectPage.clickOnActionsDropDown();

			NotesPage notesPage = projectPage.clickOnAddNotesMenu();
			noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
			notesPage.enterNoteText(noteText);
			notesPage.clickOnSaveButon();
			Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
			Assert.assertEquals("Private", projectNotesPage.getRecentNoteTypeFromNotesTab());

			projectPage.clickOnActionsDropDown();
			projectPage.clickOnAddNotesMenu();
			noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
			notesPage.clickOnPublicRadioButton();
			notesPage.enterNoteText(noteText);
			notesPage.clickOnSaveButon();
			projectNotesPage.getRecentNoteFromNotesTab();
			Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
			Assert.assertEquals("Public", projectNotesPage.getRecentNoteTypeFromNotesTab());
		}

		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);

		homePage.clickOnProjectsLink();
		projectResultsPage.clickOnThirdProjectTitle();

		if (projectPage.isNotesTabDisabled()) {
			for (int i = 1; i < 3; i++) {
				projectPage.clickOnActionsDropDown();
				NotesPage notesPage = projectPage.clickOnAddNotesMenu();
				noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				projectPage.clickOnNotesTab();
				Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
				Assert.assertEquals("Private", projectNotesPage.getRecentNoteTypeFromNotesTab());

				projectPage.clickOnActionsDropDown();
				projectPage.clickOnAddNotesMenu();
				noteText = "Auto" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.clickOnPublicRadioButton();
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				projectNotesPage.getRecentNoteFromNotesTab();
				Assert.assertEquals(noteText, projectNotesPage.getRecentNoteFromNotesTab());
				Assert.assertEquals("Public", projectNotesPage.getRecentNoteTypeFromNotesTab());
			}
		}
		homePage.clickOnSignOutButton();

	}

	public void testdataforCompanies(String emailAddress, String password, String noteText, String emailAddress2,
			String password2) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		String URl = companyPage.getCurrentURl();

		if (companyPage.isNotesTabDisabled()) {
			for (int i = 1; i < 3; i++) {
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

				companyPage.clickOnActionsDropDown();
				companyPage.clickOnAddNotesMenu();
				noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.clickOnPublicRadioButton();
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				companyNotesPage.getRecentNoteFromNotesTab();
				String recentNoteTextPublic = companyNotesPage.getRecentNoteFromNotesTab();
				String recentNoteTypePublic = companyNotesPage.getRecentNoteTypeNotesTab();
				Assert.assertEquals(noteText, recentNoteTextPublic);
				Assert.assertEquals("Public", recentNoteTypePublic);
			}
		}

		homePage.clickOnCompaniesLink();

		companyResultsPage.clickOnCompanyTitleWithoutNotes();
		if (!companyPage.isNotesTabDisabled()) {
			companyPage.clickOnActionsDropDown();
			CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();
			companyNotesPage.clickSelectAllCheckbox();
			companyNotesPage.mouseoverNotesActionandClickDeleteNotes();
		}

		homePage.clickOnSignOutButton();
		loginAs(emailAddress2, password2);
		homePage.openURL(URl);
		CompanyNotesPage companyNotesPage = companyPage.clickOnNotesTab();

		for (int i = 1; i < 2; i++) {
			companyPage.clickOnActionsDropDown();

			NotesPage notesPage = companyPage.clickOnAddNotesMenu();
			noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
			notesPage.enterNoteText(noteText);
			notesPage.clickOnSaveButon();
			String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
			String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
			Assert.assertEquals(noteText, recentNoteText);
			Assert.assertEquals("Private", recentNoteType);

			companyPage.clickOnActionsDropDown();
			companyPage.clickOnAddNotesMenu();
			noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
			notesPage.clickOnPublicRadioButton();
			notesPage.enterNoteText(noteText);
			notesPage.clickOnSaveButon();
			companyNotesPage.getRecentNoteFromNotesTab();
			String recentNoteTextPublic = companyNotesPage.getRecentNoteFromNotesTab();
			String recentNoteTypePublic = companyNotesPage.getRecentNoteTypeNotesTab();
			Assert.assertEquals(noteText, recentNoteTextPublic);
			Assert.assertEquals("Public", recentNoteTypePublic);
		}

		homePage.clickOnSignOutButton();
		loginAs(emailAddress, password);

		homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnThirdCompanyTitle();

		if (companyPage.isNotesTabDisabled()) {
			for (int i = 1; i < 3; i++) {
				companyPage.clickOnActionsDropDown();
				NotesPage notesPage = companyPage.clickOnAddNotesMenu();
				noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				companyPage.clickOnNotesTab();
				String recentNoteText = companyNotesPage.getRecentNoteFromNotesTab();
				String recentNoteType = companyNotesPage.getRecentNoteTypeNotesTab();
				Assert.assertEquals(noteText, recentNoteText);
				Assert.assertEquals("Private", recentNoteType);

				companyPage.clickOnActionsDropDown();
				companyPage.clickOnAddNotesMenu();
				noteText = noteText + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
				notesPage.clickOnPublicRadioButton();
				notesPage.enterNoteText(noteText);
				notesPage.clickOnSaveButon();
				companyNotesPage.getRecentNoteFromNotesTab();
				String recentNoteTextPublic = companyNotesPage.getRecentNoteFromNotesTab();
				String recentNoteTypePublic = companyNotesPage.getRecentNoteTypeNotesTab();
				Assert.assertEquals(noteText, recentNoteTextPublic);
				Assert.assertEquals("Public", recentNoteTypePublic);
			}
		}
		homePage.clickOnSignOutButton();

	}

}

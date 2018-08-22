package com.ddaqe.dgn_email_projects;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.YopmailPage;

@Listeners(TestListener.class)

public class DGNEmailProjects extends BaseTest {
	static Logger log = Logger.getLogger(DGNEmailProjects.class);

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
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1546(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();
		Assert.assertTrue(yopMailPage.isDodgeLogoDisplayed(),
				"Failed to get the footer logo as Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1547(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();
		Assert.assertTrue(yopMailPage.isDodgeLogoDisplayed(),
				"Failed to get the footer logo as Dodge Data and Analytics");
		yopMailPage.switchToDefault();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1548(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();

		Assert.assertTrue(yopMailPage.isProjectTitleListDisplayed(), "Failed to get display the project title list");
		yopMailPage.clickProjectTitle();
		yopMailPage.switchToDefault();

		ProjectPage projectPage = new ProjectPage(getDriver());
		loginAs(emailAddress, password);
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the project page");

		Assert.assertTrue(projectPage.isLocationInParenthesis(), "Failed to get the location in parenthesis");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1549(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = projectPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();

		Assert.assertTrue(yopMailPage.isProjectTitleListDisplayed(), "Failed to get display the project title list");
		yopMailPage.clickProjectTitle();
		yopMailPage.switchToDefault();

		loginAs(emailAddress, password);
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the project page");

		projectPage.clickOnPlansTab();
		projectPage.clickOnSpecsTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1550(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = projectPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();

		Assert.assertTrue(yopMailPage.isProjectTitleListDisplayed(), "Failed to get display the project title list");
		String prjName = yopMailPage.clickProjectTitle();
		yopMailPage.switchToDefault();

		loginAs(emailAddress, password);
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the project page");
		Assert.assertTrue(projectPage.getProjectTitle().equalsIgnoreCase(prjName));
		projectPage.clickOnPlansTab();
		projectPage.clickOnSpecsTab();
		projectPage.clickOnAddendaTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1551(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = projectPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();

		Assert.assertTrue(yopMailPage.isProjectTitleListDisplayed(), "Failed to get display the project title list");
		yopMailPage.clickProjectAddendaLink();
		yopMailPage.switchToDefault();

		loginAs(emailAddress, password);
		ProjectAddendaPage projectAddendaPage = new ProjectAddendaPage(getDriver());
		Assert.assertTrue(projectAddendaPage.getCurrentURl().contains("ProjectAddenda.aspx"),
				"Failed to get the project addenda page");
		projectPage.clickOnPlansTab();
		projectPage.clickOnSpecsTab();
		projectPage.clickOnAddendaTab();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1552(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();
		projectPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = projectPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

		loginAs(emailAddress, password);
		homePage.clickOnSignOutButton();
		YopmailPage yopMailPage = new YopmailPage(getDriver());
		yopMailPage.CheckInbox(alternateEmailAddress);
		yopMailPage.switchToInboxFrame();
		Assert.assertTrue(yopMailPage.isMailReceivedFromDodge(),
				"Failed to receive mail from Dodge Data and Analytics");
		yopMailPage.clickLatestSubject();
		yopMailPage.switchToDefault();
		yopMailPage.switchToBodyFrame();
		yopMailPage.clickShowPictures();

		Assert.assertTrue(yopMailPage.isProjectTitleListDisplayed(), "Failed to get display the project title list");
		yopMailPage.clickProjectTitle();
		yopMailPage.switchToDefault();

		loginAs(emailAddress, password);
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the project page");

		String projectDRNumber = projectPage.getProjectDRNumber().trim();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Plans URL");

		ProjectSpecsPage projectSpecsPage = projectPlansPage.clickOnSpecsTab();
		String projectSpecsDRNumber = projectSpecsPage.getDRNumber().trim();
		Assert.assertTrue(projectSpecsPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Specs URL");

		ProjectAddendaPage projectAddendaPage = projectSpecsPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Addenda URL");

		projectPage.clickOnBreadCrumbProjectLink();

		Assert.assertEquals(projectSpecsDRNumber, projectPage.getProjectDRNumber().trim(),
				"Failed to match the DR number of the same selected project from different tabs");

		homePage.enterSearchText(projectDRNumber);
		homePage.clickOnSearchButtonDR();

		projectDRNumber = projectPage.getProjectDRNumber().trim();
		projectPage.clickOnPlansTab();
		Assert.assertTrue(projectPlansPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Plans URL");

		projectPlansPage.clickOnSpecsTab();
		projectSpecsPage.getDRNumber().trim();
		Assert.assertTrue(projectSpecsPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Specs URL");

		projectSpecsPage.clickOnAddendaTab();
		Assert.assertTrue(projectAddendaPage.getCurrentURL().trim().contains(projectDRNumber),
				"Failed to match the DR number of the same selected project from Addenda URL");

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1553(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();

		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1554(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("firms");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithFirms();
		projectPage.clickOnActionsDropDown();
		EmailAlertsPage emailAlertsPage = projectPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEmailProjectsDataProvider.class, dataProvider = "TCNonAdEmail", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1555(String emailAddress, String password, String alternateEmailAddress, String optionalMsg) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnDropDownList();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnMultipleProjectsWithSpecAlertsChkBox(2);
		specAlertsResultsPage.clickOnActionsDropdown();

		EmailAlertsPage emailAlertsPage = projectResultPage.clickEmailProjectsLinkUnderActionsDrpDwn();
		emailAlertsPage.enterEmailAddress(alternateEmailAddress);
		emailAlertsPage.enterOptionalMessage(optionalMsg);
		emailAlertsPage.clickOnPopUpSaveButton();
		homePage.clickOnSignOutButton();
	}
}

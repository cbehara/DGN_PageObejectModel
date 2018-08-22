package com.ddaqe.dgn_print_projects;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.NotesPage;
import com.ddaqe.pages.PrintCompanyListPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.PrintProjectListPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectNotesPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingLists;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNPrintProjects extends DGNPrintProjectsDataSet {

	static Logger log = Logger.getLogger(DGNPrintProjects.class);

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
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1883", description = "To verify that the Print Addenda List option is available in the Actions Menu of the Addenda Tab")
	public void tc1883(String emailAddress, String password, String searchString) {
		String projectTitle = "";
		String drNumber = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchString);
		homePage.clickOnSearchButton();

		Assert.assertTrue(projectResultsPage.isAddendaLinkIsDisplayed(),
				"Addenda link is not present on the project result page.");
		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();

		Assert.assertTrue(projectAddendaPage.checkAddendaListPresent(),
				"List of Addenda document is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.isActionMenuPreset(),
				"Action menu dropdown is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.verifyAddendaLatestUpdateAtRightSide(),
				"Addenda latest update is not displayed at right side of Addenda page.");

		ProjectPage projectPage = projectAddendaPage.clickOnProjectTab();
		projectTitle = projectPage.getProjectTitle();
		drNumber = projectPage.getProjectDRNumber();

		ProjectAddendaPage projectAddendaPage_1 = projectPage.clickOnAddendaTab();
		projectAddendaPage_1.clickOnActionMenu();
		Assert.assertTrue(projectAddendaPage_1.verifyAddendaActionsList(),
				"Actions list is not matching with the expected actions list.");

		PrintProjectDetailsPage printProjectDetailsPage = projectAddendaPage_1.clickOnPrintAddendaListActionMenu();

		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed(),
				"Print button is not displayed on print project detail page.");
		Assert.assertTrue(printProjectDetailsPage.isBackButtonDisplayed(),
				"Back button is not displayed on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPrintProjectTitlePrintAddendaList(projectTitle),
				"Project title is not as expected : " + projectTitle + " on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPrintDRNumberPrintAddendaList(drNumber),
				"DR Number is not as expected : " + drNumber + " on print project detail page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1883", description = "To Verify that the  printer friendly version of a Addenda  list in HTML format will be displayed on clicking the Print Addenda list option.")
	public void tc1884(String emailAddress, String password, String searchString) {
		String projectTitle = "";
		String drNumber = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchString);
		homePage.clickOnSearchButton();

		Assert.assertTrue(projectResultsPage.isAddendaLinkIsDisplayed(),
				"Addenda link is not present on the project result page.");
		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();

		Assert.assertTrue(projectAddendaPage.checkAddendaListPresent(),
				"List of Addenda document is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.isActionMenuPreset(),
				"Action menu dropdown is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.verifyAddendaLatestUpdateAtRightSide(),
				"Addenda latest update is not displayed at right side of Addenda page.");

		ProjectPage projectPage = projectAddendaPage.clickOnProjectTab();
		projectTitle = projectPage.getProjectTitle();
		drNumber = projectPage.getProjectDRNumber();

		ProjectAddendaPage projectAddendaPage_1 = projectPage.clickOnAddendaTab();
		projectAddendaPage_1.clickOnActionMenu();
		Assert.assertTrue(projectAddendaPage_1.verifyAddendaActionsList(),
				"Actions list is not matching with the expected actions list.");

		PrintProjectDetailsPage printProjectDetailsPage = projectAddendaPage_1.clickOnPrintAddendaListActionMenu();

		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed(),
				"Print button is not displayed on print project detail page.");
		Assert.assertTrue(printProjectDetailsPage.isBackButtonDisplayed(),
				"Back button is not displayed on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPrintProjectTitlePrintAddendaList(projectTitle),
				"Project title is not as expected : " + projectTitle + " on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPrintDRNumberPrintAddendaList(drNumber),
				"DR Number is not as expected : " + drNumber + " on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.isprintDodgeLogoDisplayed(),
				"Dodge logo is not present on print project detail page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1883", description = "To verify that the valuation range is displayed beside the code on print project details page.")
	public void tc1887(String emailAddress, String password, String searchString) {
		String valuationCode = "";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(searchString);
		homePage.clickOnSearchButton();

		Assert.assertTrue(projectResultsPage.isAddendaLinkIsDisplayed(),
				"Addenda link is not present on the project result page.");
		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();

		Assert.assertTrue(projectAddendaPage.checkAddendaListPresent(),
				"List of Addenda document is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.isActionMenuPreset(),
				"Action menu dropdown is not present on Addenda page.");
		Assert.assertTrue(projectAddendaPage.verifyAddendaLatestUpdateAtRightSide(),
				"Addenda latest update is not displayed at right side of Addenda page.");

		ProjectPage projectPage = projectAddendaPage.clickOnProjectTab();
		valuationCode = projectPage.getValuationCode();
		ProjectAddendaPage projectAddendaPage_1 = projectPage.clickOnAddendaTab();
		projectAddendaPage_1.clickOnActionMenu();

		PrintProjectDetailsPage printProjectDetailsPage = projectAddendaPage_1.clickOnPrintProjectDetailstActionMenu();

		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed(),
				"Print button is not displayed on print project detail page.");
		Assert.assertTrue(printProjectDetailsPage.isBackButtonDisplayed(),
				"Back button is not displayed on print project detail page.");

		Assert.assertTrue(printProjectDetailsPage.verifyValuationcodePrintProjectDetails(valuationCode),
				"Project Valuation code is not present on print project detail page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1883", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1888(String emailAddress, String password, String searchString) {
		testPrerequisiteForAdminUser(emailAddress, password);
		String publicTypeFilter = "Private";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		// working with save search
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = MyAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListOption(publicTypeFilter);
		projectResultsPage = savedSearchesPage.clickOnfirstProjectSavedSearch();
		Assert.assertTrue(projectResultsPage.isAddendaLinkIsDisplayed(),
				"Addenda link is not present on the project result page.");
		ProjectAddendaPage projectAddendaPage = projectResultsPage.clickOnAddendaLink();
		checkValuationCodeWithDifferentOption(projectAddendaPage);

		// working on Tracking list
		MyAccount MyAccount_1 = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = MyAccount_1.clickOnMyTrackingLists();
		ProjectResultsPage projectResultsPage_1 = trackingLists.clickOnMyProjectsTrackingList();
		Assert.assertTrue(projectResultsPage.isAddendaLinkIsDisplayed(),
				"Addenda link is not present on the project result page.");
		ProjectAddendaPage projectAddendaPage_2 = projectResultsPage_1.clickOnAddendaLink();
		checkValuationCodeWithDifferentOption(projectAddendaPage_2);

		// working on company project page
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.selectValueFromSortDropdown("Number of Projects - Descending");
		companyResultsPage.waitforLoadingRing();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		ProjectPage projectPage = companyProjectsPage.clickFirstProjectTitle();
		String valuationCode = projectPage.getValuationCode();
		projectPage.clickOnActionsDropDown();

		PrintProjectDetailsPage printProjectDetailsPage = projectPage.clickOnPrintProjectDetailsUnderActions();
		Assert.assertTrue(printProjectDetailsPage.verifyValuationcodePrintProjectDetails(valuationCode),
				"Project Valuation code is not present on print project detail page.");
	}

	private ProjectAddendaPage checkValuationCodeWithDifferentOption(ProjectAddendaPage projectAddendaPage) {
		ProjectPage projectPage = projectAddendaPage.clickOnProjectTab();
		String valuationCode = projectPage.getValuationCode();
		ProjectAddendaPage projectAddendaPage_1 = projectPage.clickOnAddendaTab();
		projectAddendaPage_1.clickOnActionMenu();

		PrintProjectDetailsPage printProjectDetailsPage = projectAddendaPage_1.clickOnPrintProjectDetailstActionMenu();

		Assert.assertTrue(printProjectDetailsPage.verifyValuationcodePrintProjectDetails(valuationCode),
				"Project Valuation code is not present on print project detail page.");
		return printProjectDetailsPage.clickOnBackButtonReturnToAddendaPage();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1889", description = "Verifying printing when private notes check box is not checked in My Preferences page.")
	public void tc1889(String emailAddress, String password, String noteText) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();
		if (myPreferencesPage.iskIncludePrivateNotesCheckboxSelected()) {
			myPreferencesPage.clickChkIncludePrivateNotes();
			myPreferencesPage.clickPrivateNotesSaveBtn();
		}

		ProjectResultsPage projectresultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectresultsPage.clickOnThirdProjectTitle();
		projectPage.clickOnActionsDropDown();
		NotesPage notesPage = projectPage.clickOnAddNotesMenu();
		noteText = noteText + CommonUtils.getTimeStamp();
		notesPage.enterNoteText(noteText);
		notesPage.clickOnSaveButon();
		ProjectNotesPage projectnotesPage = projectPage.clickOnNotesTab();
		projectnotesPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectnotesPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertFalse(printProjectDetailsPage.checkAddNotesPresent(noteText),
				"Added note " + noteText + " is present on the print detail page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "To verify the projects shown in the print list view while All the projects are selected and then few are unselected.")
	public void tc1890(String emailAddress, String password) throws InterruptedException {
		String resultPerPageValue = "25";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPageValue);
		projectResultsPage.clickOnSelectAllProjects();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectResult_Chkbox_List");
		projectResultsPage.scrollResultPerPageAtBottom();
		List<String> projectTitleList = projectResultsPage.getListProjectTitle();
		projectTitleList = projectTitleList.subList(2, projectTitleList.size() - 1);
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		List<String> actualProjTitleList = printProjectListPage.getListProjectTitlePrintProjectDetails();
		Assert.assertFalse(CommonUtils.CompareTwoList(projectTitleList, actualProjTitleList),
				"Print project detail page list is not matching with the list of selected project on Project page.");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1891", description = "To verify the projects shown in the print list view while All the projects are selected and then few are unselected.")
	public void tc1891(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount MyAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = MyAccount.clickOnMyPreferences();

		Assert.assertTrue(myPreferencesPage.isIncludePrivateNotesCheckboxDisplayed(),
				"Include Private Notes checkbox is not displayed for non- platinium user");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "[DGN] Print 10 Projects from Project Results list page (TC20976).")
	public void tc1897(String emailAddress, String password) throws InterruptedException {
		String resultPerPageValue = "10";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue(resultPerPageValue);
		projectResultsPage.clickOnSelectAllProjects();
		projectResultsPage.scrollResultPerPageAtBottom();

		List<String> expecteProjectTitleList = projectResultsPage.getListProjectTitle();
		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		Assert.assertTrue(printProjectListPage.compareProjectTitleList(expecteProjectTitleList),
				"Print project detail page list is not matching with the list of selected project on Project page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "[DGN] Print 10 Projects from Company Results list page (TC20976).")
	public void tc1898(String emailAddress, String password) throws InterruptedException {
		String resultPerPageValue = "10";
		HomePage homePage = loginAs(emailAddress, password);

		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.selectResultsPerPage(resultPerPageValue);
		companyResultsPage.clickOnSelectAllProjects();
		companyResultsPage.scrollResultPerPageAtBottom();

		List<String> expecteCompanyTitleList = companyResultsPage.getListCompanyProjectTitle();
		companyResultsPage.clickOnActionsDropdown();
		PrintCompanyListPage printCompanyListPage = companyResultsPage.clickOnPrintCompanyListUnderActions();

		Assert.assertTrue(printCompanyListPage.compareCompanyTitleList(expecteCompanyTitleList),
				"Print project detail page list is not matching with the list of selected project on Company page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1905", description = "Verify the Print Project List functionality on Spec Alerts Page")
	public void tc1905(String emailAddress, String password) throws InterruptedException {
		String specAlertResult = "SpecAlertResults";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();

		specAlertsResultsPage.clickOnFirstProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		PrintProjectDetailsPage printProjectDetailsPage = specAlertsResultsPage
				.clickOnPrintProjectListActionsMenuProjectDetailPage();

		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed(),
				"Print button is not present on print project details page.");
		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed(),
				"Back button is not present on print project details page.");
		SpecAlertsResultsPage specAlertsResultsPage_1 = printProjectDetailsPage
				.clickOnBackButtonReturnToSpecAlertResult();

		Assert.assertTrue(specAlertsResultsPage_1.checkURLWithSpecResultPage(specAlertResult),
				"Click on back button bring the control again back to Spec Aleart result page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties.")
	public void tc1906(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithBidders();
		PrintProjectDetailsPage printProjectDetailsPage = projectPage.mouseOverActionAndClickProjectDetailsLink();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection() || printProjectDetailsPage.verifyPlanholdersSection(),
				"All available lowest bidders and Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties")
	public void tc1907(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("500");
		homePage.enterSearchText("bidder");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirmBidderPlans();
		ProjectFirmsPage projectFirmsPage = projectPage.clickOnFirmsTab();
		projectFirmsPage.clickOnActionsDropDown();

		PrintProjectDetailsPage printProjectDetailsPage = projectFirmsPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection(),
				"All available lowest bidders are not present on Print project details page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPlanholdersSection(),
				"All available Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties.")
	public void tc1908(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		
		projectResultsPage.SelectResultDropdownValue("500");
		homePage.enterSearchText("bidder");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirmBidderPlans();
		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();
		projectBiddersPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectBiddersPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection(),
				"All available lowest bidders are not present on Print project details page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPlanholdersSection(),
				"All available Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties")
	public void tc1909(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		
		projectResultsPage.SelectResultDropdownValue("500");
		homePage.enterSearchText("bidder");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirmBidderPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectPlansPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection(),
				"All available lowest bidders are not present on Print project details page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPlanholdersSection(),
				"All available Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties")
	public void tc1910(String emailAddress, String password) throws InterruptedException {
		String sortDropDownOption = "Publish Date - Ascending";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		
		projectResultsPage.SelectResultDropdownValue("500");
		homePage.enterSearchText("bidder");
		homePage.clickOnSearchButton();
		projectResultsPage.selectSortingOption(sortDropDownOption); 
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithBidderPlansSpec();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectSpecsPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection(),
				"All available lowest bidders are not present on Print project details page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPlanholdersSection(),
				"All available Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties")
	public void tc1911(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithBidderPlansAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectAddendaPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection() || printProjectDetailsPage.verifyPlanholdersSection(),
				"All available lowest bidders and Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "The printed Dodge Report (Action 'Print Project Details') includes all available Low Bidder and Planholder information, for all specialties")
	public void tc1912(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		
		projectResultsPage.SelectResultDropdownValue("500");
		homePage.enterSearchText("bidder");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithFirmBidderPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailsPage = projectPlansPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailsPage.verifyLowestBidderSection(),
				"All available lowest bidders are not present on Print project details page.");

		Assert.assertTrue(printProjectDetailsPage.verifyPlanholdersSection(),
				"All available Planholders are not present on Print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "Verify presence of 'Include filters' option when printing a Project List (TC24107).")
	public void tc1913(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();

		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		Assert.assertTrue(printProjectListPage.isIncludeFilterCheckboxPresent(),
				"Include filter is not present on print project details page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "Verify default value of 'Include filters' option  is unchecked when printing a Project List (TC24108).")
	public void tc1914(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();

		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		Assert.assertTrue(printProjectListPage.isIncludeFilterCheckboxPresent(),
				"Include filter is not present on print project details page.");

		Assert.assertFalse(printProjectListPage.isIncludeFilterCheckboxSelected(),
				"Include filter is selected by default on print project details page.");

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "Verify when 'Include filters' option  is checked when printing a Project List (TC24109).")
	public void tc1915(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();

		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		Assert.assertTrue(printProjectListPage.isIncludeFilterCheckboxPresent(),
				"Include filter is not present on print project details page.");

		Assert.assertFalse(printProjectListPage.isIncludeFilterCheckboxSelected(),
				"Include filter is selected by default on print project details page.");
		printProjectListPage.ClickonincludeFilters_cbk();

		Assert.assertTrue(printProjectListPage.verifyResultOfIncludeFilterCheckboxSelection(),
				"All the search criteria are not shown when Include filter is selected.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintProjectsDataProvider.class, dataProvider = "TC1890", description = "Verify Include Filters Checkbox on Print Project List page (TC24360).")
	public void tc1916(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		projectResultsPage.clickOnBiddingWithinNext7DaysOption();
		projectResultsPage.clickOnStateRegion_FirstCheckBox();
		projectResultsPage.clickOnSelectAllProjects();

		projectResultsPage.clickOnActionsDropdown();
		PrintProjectListPage printProjectListPage = projectResultsPage.clickOnPrintProjectListUnderActions();

		Assert.assertTrue(printProjectListPage.isIncludeFilterCheckboxPresent(),
				"Include filter is not present on print project details page.");

		Assert.assertFalse(printProjectListPage.isIncludeFilterCheckboxSelected(),
				"Include filter is selected by default on print project details page.");
		printProjectListPage.ClickonincludeFilters_cbk();

		Assert.assertTrue(printProjectListPage.verifyResultOfIncludeFilterCheckboxSelection(),
				"All the search criteria are not shown when Include filter is selected.");
	}

}

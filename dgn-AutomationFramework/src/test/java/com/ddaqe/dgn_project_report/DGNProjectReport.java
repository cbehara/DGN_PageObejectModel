package com.ddaqe.dgn_project_report;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectBiddersPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DGNEnum;

@Listeners(TestListener.class)

public class DGNProjectReport extends BaseTest {
	static Logger log = Logger.getLogger(DGNProjectReport.class);

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
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1849(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisabled(), "Failed to display the previous link as disabled");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link as enabled");
		goToBackPage();
		projectResultPage.clickOnSecondProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link as enabled");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link as enabled");
		goToBackPage();

		projectResultPage.clickOnLastProjectTitle();

		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link as enabled");
		Assert.assertTrue(!projectPage.isNextLinkDisabled(), "Failed to display the next link as disabled");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1846(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("fire alarm");
		homePage.clickOnSearchButton();
		if (projectResultPage.isFindInContentTypeCollapsed()) {
			projectResultPage.clickFindInCollapsed();
		}
		projectResultPage.checkPlanInFindInContentType();
		projectResultPage.uncheckAllFindInFilterExceptOne("Plans");
		ProjectPlansPage projectPlanPage = projectResultPage.clickOnPlansLink();
		Assert.assertTrue(projectPlanPage.IsPlansTabHighlighted(), "Failed to get the Plans tab highlighted");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1850(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

		String projectDRNumber = projectPage.getProjectDRNumber().trim();
//		projectDRNumber = CommonUtils.formatIntoNumbersOnly(projectDRNumber).substring(0, projectDRNumber.indexOf(" "));
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
		String drNumber = projectPage.getProjectDRNumber().trim();
		if (drNumber.contains(" ")) {
			drNumber = drNumber.substring(0, drNumber.indexOf(" "));

		}

		Assert.assertEquals(projectSpecsDRNumber, drNumber,
				"Failed to match the DR number of the same selected project from different tabs");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1851(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		ProjectPage projectPage = specAlertsResultsPage.clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda();

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
		String drNumber = projectPage.getProjectDRNumber().trim();
		if (drNumber.contains(" ")) {
			drNumber = drNumber.substring(0, drNumber.indexOf(" "));

		}
		Assert.assertEquals(projectSpecsDRNumber, drNumber,
				"Failed to match the DR number of the same selected project from different tabs");

		homePage.enterSearchText(projectDRNumber);
		homePage.clickOnSearchButtonDR();

		projectDRNumber = projectPage.getProjectDRNumber().trim();
//		projectDRNumber = CommonUtils.formatIntoNumbersOnly(projectDRNumber).substring(0, projectDRNumber.indexOf(" "));
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
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1852(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		Assert.assertTrue(projectPage.isBiddersTabDisplayed(),
				"Failed to display the Planholders/Bidders section as enabled");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1853(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		projectResultPage.clickOnActionsDropdown();
		ProjectPage projectPage = projectResultPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();
		projectResultPage.clickOnActionsDropdown();
		projectResultPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdown();
		projectResultPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1854(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultPage = homePage.clickOnCompaniesLink();

		homePage.enterSearchText("QuestCDN");
		homePage.clickOnSearchButtonDR();

		CompanyProjectsPage companyProjectPage = companyResultPage.clickOnCompanyProjectsLink();

		companyProjectPage.clickOnActionsDropdownCompany();
		ProjectPage projectPage = companyProjectPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		companyProjectPage.clickOnFistProjectCheckbox();
		companyProjectPage.clickOnSecondProjectCheckbox();
		companyProjectPage.clickOnActionsDropdownCompany();
		companyProjectPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		companyProjectPage.clickOnSelectAllProjects();
		companyProjectPage.clickOnActionsDropdownCompany();
		companyProjectPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1855(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithFirms();

		projectPage.clickOnActionsDropDown();
		PrintProjectDetailsPage printProjectDetailPage = projectPage.clickOnPrintProjectDetailsUnderActions();

		Assert.assertTrue(printProjectDetailPage.isPrintButtonDisplayed(),
				"Failed to display the print project details page");

		goToBackPage();
		Assert.assertTrue(projectPage.isFirmsTabDisplayed(), "Failed to display Project Firm");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1856(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSpecAlertsTab();
		SpecAlertsResultsPage specAlertsResultsPage = homePage.clickOnProgramInSpecAlertsDashboard(1);
		Assert.assertTrue(specAlertsResultsPage.getTitle().equalsIgnoreCase("Dodge Global Network - Project Results"),
				"Failed to get the expected title");

		specAlertsResultsPage.clickOnActionsDropdown();
		ProjectPage projectPage = specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		specAlertsResultsPage.clickOnFirstProjectCheckbox();
		specAlertsResultsPage.clickOnSecondProjectCheckbox();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");

		goToBackPage();
		specAlertsResultsPage.clickOnSelectAllProjects();
		specAlertsResultsPage.clickOnActionsDropdown();
		specAlertsResultsPage.clickOnViewProjectsFromActions();
		Assert.assertTrue(projectPage.isPreviousLinkDisplayed(), "Failed to display the previous link");
		Assert.assertTrue(projectPage.isNextLinkDisplayed(), "Failed to display the next link");
		Assert.assertTrue(projectPage.isPageNoDisplayed(), "Failed to display the Page No.");
		Assert.assertTrue(projectPage.isPageNoSplitterDisplayed(),
				"Failed to display the Page number splitter (i.e. of)");
		Assert.assertTrue(projectPage.isTotalPageCountDisplayed(), "Failed to display the total page count");
		homePage.clickOnSignOutButton();
	}

	// This test is failing since the data not found for specific DR.
	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR3", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1857(String emailAddress, String password, String projectDRNumber1, String projectDRNumber2,
			String projectDRNumber3) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText(projectDRNumber1);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_000000(),
				"Failed to display the spec title - 00 00 00");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_090000(),
				"Failed to display the spec title - 09 00 00");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_100000(),
				"Failed to display the spec title - 10 00 00");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_00000(),
				"Failed to display the spec title - 00000 for DR '" + projectDRNumber1 + "'");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_01000(),
				"Failed to display the spec title - 01000 for DR '" + projectDRNumber1 + "'");

		homePage.enterSearchText(projectDRNumber2);
		homePage.clickOnSearchButtonDR();
		projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_000000(),
				"Failed to display the spec title - 00 00 00");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_00000(),
				"Failed to display the spec title - 00000 for DR '" + projectDRNumber2 + "'");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_01000(),
				"Failed to display the spec title - 01000 for DR '" + projectDRNumber2 + "'");

		homePage.enterSearchText(projectDRNumber3);
		homePage.clickOnSearchButtonDR();
		projectPage.clickOnSpecsTab();
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_000000(),
				"Failed to display the spec title - 00 00 00");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_00000(),
				"Failed to display the spec title - 00000 for DR '" + projectDRNumber3 + "'");
		Assert.assertTrue(projectSpecsPage.isSpecTitleDisplayed_01000(),
				"Failed to display the spec title - 01000 for DR '" + projectDRNumber3 + "'");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1858(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithBidders();

		Assert.assertTrue(projectPage.isBiddersTabDisplayed(), "Failed to display Planholders / Bidders");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1859(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");
		Assert.assertTrue(projectPage.getBiddersIndicatorCount() >= 1,
				"Failed to get the project which has 1 or more bidders");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1860(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Integer biddersIndicatorCount = projectPage.getBiddersIndicatorCount();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(biddersIndicatorCount == projectBiddersPage.getBiddersTotalCount(),
				"Failed to get the bidders count in the indicator circle equal to total number of bidders");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1861(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		ProjectBiddersPage projectBiddersPage = projectPage.clickBiddersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");
		goToBackPage();

		projectPage.clickPlanholdersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1862(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");
		Assert.assertTrue(projectPage.getPlanholdersIndicatorCount() >= 1,
				"Failed to get the project which has 1 or more bidders");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1863(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Integer biddersIndicatorCount = projectPage.getPlanholdersIndicatorCount();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(biddersIndicatorCount == projectBiddersPage.getPlanholdersTotalCount(),
				"Failed to get the planholders count in the indicator circle equal to total number of planholders");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1864(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		ProjectBiddersPage projectBiddersPage = projectPage.clickBiddersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");
		goToBackPage();

		projectPage.clickPlanholdersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdmin", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1865(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithBidders();

		Assert.assertTrue(projectPage.isBiddersTabClickable(),
				"Failed to get the Planholders / Bidders link as enabled");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1866(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersTabClickable(), "Failed to get the Planholders / Bidders link ");

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		Assert.assertTrue(projectPage.isBidderIndicatorCircleBlue(),
				"Failed to get the bidders indicator color as blue");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCircleBlue(),
				"Failed to get the planholders indicator color as blue");

		Assert.assertTrue(projectPage.isBidderIndicatorLinkUnderlinedBlack(),
				"Failed to get the bidders indicator link as underlined in black");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorLinkUnderlinedBlack(),
				"Failed to get the planholders indicator color as underlined in black");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1867(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		Assert.assertTrue(projectPage.isBidderIndicatorCircleBlue(),
				"Failed to get the bidders indicator color as blue");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCircleBlue(),
				"Failed to get the planholders indicator color as blue");

		ProjectBiddersPage projectBiddersPage = projectPage.clickBiddersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		Assert.assertTrue(projectPage.getBreadCrumbText().contains("Project > Planholders/Bidders"));

		goToBackPage();

		projectPage.clickPlanholdersIndicatorCount();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		Assert.assertTrue(projectPage.getBreadCrumbText().contains("Project > Planholders/Bidders"));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1868(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersTabClickable(),
				"Failed to get the Planholders / Bidders link as enabled");

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		Assert.assertTrue(projectPage.isBidderIndicatorCircleBlue(),
				"Failed to get the bidders indicator color as blue");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCircleBlue(),
				"Failed to get the planholders indicator color as blue");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		Assert.assertTrue(projectBiddersPage.getBiddersTotalCount() > 0,
				"Failed to display the bidders detail under 'Low Bidders' section");

		Assert.assertTrue(projectBiddersPage.getPlanholdersTotalCount() > 0,
				"Failed to display the bidders detail under 'Planholders' section");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1869(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText(projectDRNumber);
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		Assert.assertTrue(projectPage.isBiddersTabClickable(),
				"Failed to get the Planholders / Bidders link as enabled");

		Assert.assertTrue(projectPage.isBiddersIndicatorCountDisplayed(),
				"Failed to display 'Low Bidders' indicator circle with count");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCountDisplayed(),
				"Failed to display 'Planholders' indicator circle with count");

		Assert.assertTrue(projectPage.isBidderIndicatorCircleBlue(),
				"Failed to get the bidders indicator color as blue");

		Assert.assertTrue(projectPage.isPlanholdersIndicatorCircleBlue(),
				"Failed to get the planholders indicator color as blue");

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(projectBiddersPage.getCurrentURL().contains("ProjectBiddersList"),
				"Failed to redirect to the Project bidders page");

		Assert.assertTrue(projectBiddersPage.getBiddersTotalCount() > 0,
				"Failed to display the bidders detail under 'Low Bidders' section");

		Assert.assertTrue(projectBiddersPage.getPlanholdersTotalCount() > 0,
				"Failed to display the bidders detail under 'Planholders' section");

		Assert.assertTrue(projectBiddersPage.isLowBiddersInTabular(),
				"Failed to display the 'Low Bidders' in tabular format");

		Assert.assertTrue(projectBiddersPage.isPlanholdersInTabular(),
				"Failed to display the 'Planholders' in tabular format");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1870(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText("200300725010");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(projectBiddersPage.isLowBiddersInTabular(),
				"Failed to display the 'Low Bidders' in tabular format");

		Assert.assertTrue(projectBiddersPage.isPlanholdersInTabular(),
				"Failed to display the 'Planholders' in tabular format");

		Assert.assertTrue(projectBiddersPage.compareShowDrpOptionsWithHeaders(),
				"Failed to match the options list with each headers in 'Low Bidders' and 'Planholders'");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1871(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText("200300725010");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		Assert.assertTrue(projectBiddersPage.isLowBiddersInTabular(),
				"Failed to display the 'Low Bidders' in tabular format");

		Assert.assertTrue(projectBiddersPage.isPlanholdersInTabular(),
				"Failed to display the 'Planholders' in tabular format");

		Assert.assertTrue(projectBiddersPage.compareShowDrpOptionsWithHeaders(),
				"Failed to match the options list with each headers in 'Low Bidders' and 'Planholders'");

		projectBiddersPage.clickShowDrpDown();

		projectBiddersPage.selectShowDrpDownOptions();

		projectBiddersPage.isSelectedHeaderListFiltered();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1872(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText("200300725010");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		projectBiddersPage.clickShowDrpDown();

		List<String> optionsList = projectBiddersPage.getShowDrpDownOptions();
		List<String> sortedOptionsList = CommonUtils.sortWebElements(optionsList, true);

		Assert.assertTrue(CommonUtils.CompareTwoList(optionsList, sortedOptionsList));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1873(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();

		homePage.enterSearchText("200300725010");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();

		ProjectBiddersPage projectBiddersPage = projectPage.clickOnBiddersTab();

		projectBiddersPage.clickShowDrpDown();

		List<String> optionsList = projectBiddersPage.getShowDrpDownOptions();
		List<String> sortedOptionsList = CommonUtils.sortWebElements(optionsList, true);

		Assert.assertTrue(CommonUtils.CompareTwoList(optionsList, sortedOptionsList));

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1874(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isDonutChartDisplayed(), "Failed to display the chart donut");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1875(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.verifyConstructionDonutLegends(),
				"Failed to verify the construction types being displayed as donuts");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1877(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		projectResultPage.clickOnChartConnstructionTypeInteriors();
		Assert.assertTrue(projectResultPage.verifyChartConstructionTypes(),
				"Failed to match the construction types chart");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1878(String emailAddress, String password, String projectDRNumber) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		Assert.assertTrue(projectResultPage.isChartHeaderConstructionTypeDisplayed(),
				"Failed to display the chart header as 'Construction Type'");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1879(String emailAddress, String password, String projectDRNumber) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();

		projectResultPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultPage.SelectOptionsFromTheList(1, "ConstructionType_LHSFilterList");
		projectResultPage.waitforLoadingRing();

		Assert.assertTrue(projectResultPage.isChartHeaderConstructionTypeDisplayed(),
				"Failed to display the chart header as 'Construction Type'");

		Assert.assertTrue(
				projectResultPage.checkConstructionTypeFiltered(
						DGNEnum.ConstructionTypeDonutLegends.valueOf(CommonUtils.formatIntoCharactersOnly(
								projectResultPage.getConstructionType_LHSFirstFilter_lbl().toUpperCase()))),
				"Failed to filter the construction type");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1880(String emailAddress, String password, String projectDRNumber) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeUnderFirstChartView();

		projectResultPage.clickBarChartProjectTypeEngineering();
		Assert.assertTrue(projectResultPage.verifyBarChartProjectTypeEngineeringSelected(),
				"Failed to get the value of the bar chart project type selected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1881(String emailAddress, String password, String projectDRNumber) throws ParseException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.waitforLoadingRing();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.clickMapChartProjectDensityWashington();
		Assert.assertTrue(projectResultPage.verifyMapChartProjectDensityWashingtonSelected(),
				"Failed to get the project density by location selected");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = ProjectReportDataProvider.class, dataProvider = "TCPlatiAdminDR", description = "[SpecAlerts] Add SpecAlerts tags to print project details output (TC10716)")
	public void tc1882(String emailAddress, String password, String projectDRNumber) throws ParseException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectConstructionTypeUnderFirstChartView();
		String constructionTypeStr = projectResultPage.ClickOnIndexFromTheList(3);
		Assert.assertTrue(
				projectResultPage.checkConstructionTypeFiltered(DGNEnum.ConstructionTypeDonutLegends
						.valueOf(CommonUtils.formatIntoCharactersOnly(constructionTypeStr).toUpperCase())),
				"Failed to filter the construction type");
		projectResultPage.waitforLoadingRing();
		projectResultPage.selectProjectTypeUnderFirstChartView();
		projectResultPage.clickBarChartProjectTypeHousing();
		Assert.assertTrue(projectResultPage.verifyBarChartProjectTypeHousingSelected(),
				"Failed to get the value of the bar chart project type selected");

		projectResultPage.clickMapChartProjectDensityTexas();
		Assert.assertTrue(projectResultPage.verifyMapChartProjectDensityTexasSelected(),
				"Failed to get the project density by location selected");
		homePage.clickOnSignOutButton();
	}

}

package com.ddaqe.dgn_download_projects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SpecAlertsResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNDownloadProjects1 extends BaseTest {

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1917", description = "To Verify the Breadcrumbs in the Projects Page.")
	public void tc1917(String username, String password, String projectBreadcrumb, String firmsBreadcrumb,
			String planholdersBiddersBreadcrumb, String plansBreadcrumb, String specsBreadcrumb,
			String addendaBreadcrumb, String addendaWithDetailsBreadcrumb) {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("500");
		Assert.assertTrue(projectResultsPage.getListProjectTitle().size() > 0,
				"No project title found on the Projects page.");

		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isProjectTabDisplayed(),
				"Projects tab is not displayed on the Project Results page.");
		Assert.assertTrue(projectPage.getBreadCrumbText().contains(projectBreadcrumb));

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		ProjectPage projectPageWithFirms = projectResultsPage.clickOnFirstProjectTitleWithFirms();
		projectPageWithFirms.clickOnFirmsTab();
		Assert.assertTrue(projectPageWithFirms.getBreadCrumbText().contains(firmsBreadcrumb));

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		ProjectPage projectPageWithBidders = projectResultsPage.clickOnFirstProjectTitleWithBidders();
		projectPageWithBidders.clickOnBiddersTab();
		Assert.assertTrue(projectPageWithBidders.getBreadCrumbText().contains(planholdersBiddersBreadcrumb));

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		ProjectPage projectPageWithSpecs = projectResultsPage.clickOnFirstProjectTitleWithSpecs();
		projectPageWithSpecs.clickOnSpecsTab();
		Assert.assertTrue(projectPageWithSpecs.getBreadCrumbText().contains(specsBreadcrumb));

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		ProjectPage projectPageWithAddenda = projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPageWithAddenda.clickOnAddendaTab();
		Assert.assertTrue(projectPageWithAddenda.getBreadCrumbText().contains(addendaBreadcrumb));

		projectAddendaPage.clickOnFirstAddendaInGrid();
		Assert.assertTrue(projectPageWithAddenda.getBreadCrumbText().contains(addendaWithDetailsBreadcrumb));
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To verify that scrollbar should not be displayed when PDF option is selected in projects download dialog")
	public void tc1930(String username, String password) {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		List<Integer> projectTitlesToCheck = new ArrayList<Integer>();
		projectTitlesToCheck.add(0);
		projectTitlesToCheck.add(1);
		projectResultsPage.selectProjectsByIndex(projectTitlesToCheck);
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnPDFRadioBtn();
		Assert.assertFalse(downloadProjects.isVerticalScrollBarVisibleOnDownloadProjectsPopup());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC-Common", description = "To verify that scrollbar should not be displayed when PDF option is selected in companies download dialog")
	public void tc1931(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		List<Integer> companyTitlesToCheck = new ArrayList<Integer>();
		companyTitlesToCheck.add(0);
		companyTitlesToCheck.add(1);
		companyResultsPage.selectCompaniesByIndex(companyTitlesToCheck);
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
		downloadCompanies.clickOnPDFRadioBtn();
		Assert.assertFalse(downloadCompanies.isVerticalScrollBarVisibleOnDownloadCompaniesPopup());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1933", description = "Verify SPECALERTS field in the project download CSV selected field list for All fields option.")
	public void tc1933(String username, String password, String defaultValueOfSelectATemplateDropDown) {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		List<Integer> projectTitlesToCheck = new ArrayList<Integer>();
		projectTitlesToCheck.add(0);
		projectResultsPage.selectProjectsByIndex(projectTitlesToCheck);
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();

		Assert.assertTrue(downloadProjects.isSelectATemplateDropDownVisible());
		Assert.assertEquals(downloadProjects.getDefaultSelectedValueOfSelectATemplateDropDown(),
				defaultValueOfSelectATemplateDropDown);
		Assert.assertTrue(downloadProjects.isAvailableFieldsMultiSelectBoxVisible());
		Assert.assertTrue(downloadProjects.isSelectedFieldsMultiSelectBoxVisible());
		Assert.assertTrue(downloadProjects.isSPECALERTSVisibleAtTheLast());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify When users try to print the plans list, the page should show proper document")
	public void tc1953(String emailAddress, String password) {
		int planDocumentCount = 0;
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		planDocumentCount = projectPlansPage.getPlanTitleCount();

		projectPlansPage.clickOnActionsDropDown();

		PrintProjectDetailsPage printProjectDetailsPage = projectPlansPage.clickOnPrintPlanListActionMenu();

		Assert.assertTrue(printProjectDetailsPage.checkPlanTitleCount(planDocumentCount),
				"Total document mention on Plan page is not shown on Print Plan List page.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "General UI clean up to Change the link text from download to Install eTakeoff")
	public void tc1954(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();
		Assert.assertTrue(projectPlansPage.isInstallETakeOffActionMenuDsiapley(),
				"Install eTakeoff action menu is not displayed under plan action menu.");
		Assert.assertTrue(projectPlansPage.checkInstallETakeOffActionMenuColor(),
				"Install eTakeoff menu color is not shown as ORANGE on hover.");

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		projectPage = projectResultsPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.clickOnActionsDropDown();

		Assert.assertTrue(projectSpecsPage.isInstallETakeOffActionMenuDsiapley(),
				"Install eTakeoff action menu is not displayed under plan action menu.");
		Assert.assertTrue(projectSpecsPage.checkInstallETakeOffActionMenuColor(),
				"Install eTakeoff menu color is not shown as ORANGE on hover.");

		projectResultsPage = projectResultsPage.clickOnProjectFilterBreadcrumb();
		projectPage = projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.clickOnActionMenu();

		Assert.assertTrue(projectAddendaPage.isInstallETakeOffActionMenuDsiapley(),
				"Install eTakeoff action menu is not displayed under plan action menu.");
		Assert.assertTrue(projectAddendaPage.checkInstallETakeOffActionMenuColor(),
				"Install eTakeoff menu color is not shown as ORANGE on hover.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "[DGN] Adding documents to “Download Later” queue when the project search criteria exceeds 200 characters.")
	public void tc1975(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		projectResultsPage.enterSearchText("doors");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		projectPlansPage.waitforLoadingRing();
		projectPlansPage.selectCheckboxes(2);
		projectPlansPage.mouseOverActionandClickDownloadDocuments();
		projectResultsPage.waitforLoadingRing();
		projectPlansPage.clickOnDownloadLater();
		projectPlansPage.clickOnSaveBtn();
		projectPlansPage.waitforLoadingRing();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnDocumentsLink();
		Assert.assertTrue(myDownloadsPage.isSelectAllCheckboxDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = " [DGN] Adding documents to “Download Later” queue for a project of SavedSearch having search criteria exceeding 200 characters..")
	public void tc1979(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.clickOnPlanOpenInFindInFilter();
		projectResultsPage.enterSearchText("doors");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = "AutoPriSaveSearch" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.selectSearchType("Private");
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();
		ProjectPlansPage projectPlansPage = projectResultsPage.clickOnFirstHighlightedPlan();
		projectResultsPage.waitforLoadingRing();
		projectPlansPage.selectCheckboxes(2);
		projectResultsPage.waitforLoadingRing();
		projectPlansPage.mouseOverActionandClickDownloadDocuments();
		projectResultsPage.waitforLoadingRing();
		projectPlansPage.clickOnDownloadLater();
		projectPlansPage.clickOnSaveBtn();
		projectPlansPage.waitforLoadingRing();
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		myDownloadsPage.clickOnDocumentsLink();
		Assert.assertTrue(myDownloadsPage.isSelectAllCheckboxDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the Download Projects functionality on Project Search Results.")
	public void tc1986(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		List<String> resultPerPageList = Arrays.asList("10", "25", "50", "100", "500", "1000");
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		for (int i = 0; i < resultPerPageList.size(); i++) {
			projectResultsPage.SelectResultDropdownValue(resultPerPageList.get(i));
			projectResultsPage.clickOnSelectAllProjects();
			DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
			downloadProjects.clickOnCancelBtn();
			Thread.sleep(5000);
			Assert.assertTrue(downloadProjects.isDownloadButtonDisplayed(),
					"Download project popup dialog still appear on click a Cancel button.");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1951", description = "Verify the Download Projects functionality on Company Project page")
	public void tc1987(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		List<String> resultPerPageList = Arrays.asList("10", "25", "50", "100", "500", "1000");
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		for (int i = 0; i < resultPerPageList.size(); i++) {
			companyResultsPage.selectResultsPerPage(resultPerPageList.get(i));
			companyResultsPage.clickOnSelectAllProjects();
			DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();
			downloadCompanies.clickOnCancelBtn();
			Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
					"Download comapnies popup dialog still appear on click a Cancel button.");
		}
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadProjectsDataProvider.class, dataProvider = "TC1935", description = "Verify the Download Projects functionality for less than 100 projects on Spec Alerts Page.")
	public void tc1989(String username, String password)
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		String downloadName = "specAlertPDF" + CommonUtils.getTimeStamp();
		HomePage homePage = loginAs(username, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnMySearchesDropDown();
		SpecAlertsResultsPage specAlertsResultsPage = projectResultsPage.clickOnMySearchesSpecAlerts();
		specAlertsResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = specAlertsResultsPage
				.mouseOverActionandClickDownloadProjects_SpecAlertPage();
		downloadProjects.setDownloadNameSpecAlerts(downloadName);
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(specAlertsResultsPage.isSelectAllDisplayed(), "Download Project popup not get closed.");
	}
}

package com.ddaqe.dgn_design_alert_search;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyPreferencesPage;
import com.ddaqe.pages.PrintProjectDetailsPage;
import com.ddaqe.pages.ProjectDesignAlertsPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.TrackPopUpPage;

@Listeners(TestListener.class)

public class DGNDesignAlertSearch1 extends BaseTest {

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify if The left-nav 'Find In' filter contains a 'DesignAlerts' check-box for properly licensed user.")
	public void DGNT3034(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "verify if the the DesignAlerts check-box's check-uncheck functionality enables user to change the content-scope of the Project Search")
	public void DGNT3036(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed());
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		Assert.assertTrue(projectResultsPage.IsdesignalertFindInCheckBoxUnchecked());
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		Assert.assertFalse(projectResultsPage.IsdesignalertFindInCheckBoxUnchecked());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "dGNT3037", description = "Verify the Keyword search for Design Alert")
	public void DGNT3037(String emailAddress, String password, String searchText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed());
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		Assert.assertTrue(projectResultsPage.IsdesignalertFindInCheckBoxUnchecked());
		String CountBefore = projectResultsPage.getProjectResultsCount();
		homePage.enterSearchText(searchText);
		homePage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify if The left-nav 'Find In' filter contains a 'DesignAlerts' check-box for properly licensed user.")
	public void DGNT3035(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify blank search for design alert")

	public void DGNT3038(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.uncheckNewsFindInFilter();
		projectResultsPage.uncheckNewsFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.uncheckSpecsFindInFilter();
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectResultsPage.isDesignAlertsIconDisplayed());
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify if the DesignAlert print-friendly page header includes the search-term used.")
	public void DGNT3051(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		homePage.enterSearchText("door");
		homePage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
		ProjectDesignAlertsPage projectDesignAlertsPage = projectResultsPage.clickOnFirstHighlightedDesignAlertsLink();
		projectResultsPage.waitforLoadingRing();
		projectDesignAlertsPage.clickOnPrintProductListActionMenu();
		projectResultsPage.waitforLoadingRing();
		goToBackPage();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify the color of the product grid header.")
	public void DGNT3052(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify the color of the 2nd product grid row is the first alternate row")
	public void DGNT3054(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "UI-Verify border color on DGN DesignAlerts product grid")
	public void DGNT3055(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "DGNT3056", description = "Verify clicking any URL in the DGN DesignAlerts Product grid")
	public void DGNT3056(String emailAddress, String password, String Expected) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.enterSearchText("doors");
		ProjectPage projectPage = homePage.clickOnSearchButtonDR();
		ProjectDesignAlertsPage projectDesignAlertsPage = projectPage.clickOnDesignAlertsTab();
		projectDesignAlertsPage.clickOnDesignAlertProductLink();
		projectDesignAlertsPage.getNewTabUrl();
		Assert.assertEquals(Expected, projectDesignAlertsPage.getNewTabUrl());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify Print Actions on DesignAlerts page & Printed DesignAlert Products List (TC23499)")
	public void DGNT3064(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
		ProjectDesignAlertsPage projectDesignAlertsPage = projectResultsPage.clickOnFirstDesignAlertsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(homePage.getBreadCrumbText(), "Project Results > DesignAlerts");
		Assert.assertTrue(projectDesignAlertsPage.VerifyPrintProjectDetailsOptionInActionMenu());
		Assert.assertTrue(projectDesignAlertsPage.VerifyPrintProductListOptionInActionMenu());
		PrintProjectDetailsPage printProjectDetailsPage = projectDesignAlertsPage
				.clickOnPrintProjectDetailsActionMenu();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(printProjectDetailsPage.isPrintButtonDisplayed());
		Assert.assertTrue(printProjectDetailsPage.isBackButtonDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common_key", description = "Verify  when Design Alerts is not included in the content-scope for a Project Search, then Design Alert content is not searched.")
	public void DGNT3039(String emailAddress, String password, String keyword) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText(keyword);
		projectResultsPage.clickOnSearchBtn();
		Assert.assertFalse(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common_key", description = "Verify all Project Search criteria is honored along with the DesignAlerts content-scope")
	public void DGNT3040(String emailAddress, String password, String keyword) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.clickOnprojectGroupsFilterWithParticularIndex(0);
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common_key", description = "Verify the Design Alerts icon beside the DR number (TC22921)")
	public void DGNT3057(String emailAddress, String password, String keyword) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		Assert.assertTrue(projectResultsPage.isDesignAlertsIconDisplayed());
		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify the content of Special Filters (TC22922)")
	public void DGNT3058(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.specialFilterDesignAlertsOnlyDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "dGNT3061", description = "Verify there is a Design Alerts tab in the Design Alerts page of the project (TC22940)")
	public void DGNT3061(String emailAddress, String password, String keyword, String legalText) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.uncheckNewsFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.uncheckSpecsFindInFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		homePage.enterSearchText(keyword);
		projectResultsPage.clickOnSearchBtn();
		ProjectDesignAlertsPage projectDesignAlertsPage = projectResultsPage.clickOnDesignAlertLink();
		String disclaimer = projectDesignAlertsPage.getDesignAlertDisclaimerText();
		assertEquals(disclaimer, legalText);
		Assert.assertTrue(projectDesignAlertsPage.isFirmsTabClickable());
		Assert.assertTrue(projectDesignAlertsPage.isNotesTabClickable());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "dGNT3062", description = "Verify the display of GPD products in the grid system. (TC22941)")
	public void DGNT3062(String emailAddress, String password, String messageTest) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("201500578096");
		ProjectPage projectPage = projectResultsPage.clickOnSearchButtonForDR();
		ProjectDesignAlertsPage projectDesignAlertsPage = projectPage.clickOnDesignAlertsTab();
		String Message = projectDesignAlertsPage.getDesignAlertProductInfo();
		Assert.assertEquals(Message, messageTest);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify there is no pagination in the Design Alerts page and all GPD products are displayed in the same page (TC22943)")
	public void DGNT3063(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		ProjectDesignAlertsPage projectDesignAlertsPage = projectResultsPage.clickOnDesignAlertLink();
		Assert.assertTrue(projectDesignAlertsPage.isPageNoDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify the other functionality like track,hide option,save search for newly GPD projects")
	public void DGNT3059(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackLinkFromSummary();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();
		projectResultsPage.clickHideLinkForFirstProject();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "plususer", description = "Verify projects without Design Alert for Out of subscription projects")
	public void DGNT3042(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		assertFalse(projectResultsPage.specialFilterDesignAlertsOnlyDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "plususer", description = "Verify  If user does not have licensed access to DesignAlerts then user cannot view or search DesignAlerts.")
	public void DGNT3043(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		assertFalse(projectResultsPage.isDesignAlertsLinkDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify  if user's purchase includes NEWS-only,  then user canot view and search DesignAlerts if the user licence does not have design alert")
	public void dgnT3044(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed(),
				"'Design Alerts' is displayed in 'Find In' filter");
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.specialFilterDesignAlertsOnlyDisplayed(),
				"'DesignAlerts Only' is displayed in 'Special Filters");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify  if user's purchase includes  only PLAN/SPEC digital or hardcopy documents,  then user cannot view and search DesignAlerts if the user licence does not have design alert")
	public void dgnT3045(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.clickOnFindInFilter();
		Assert.assertTrue(projectResultsPage.isDesignAlertCheckBoxDisplayed(),
				"'Design Alerts' is displayed in 'Find In' filter");
		projectResultsPage.clickOnSpecialFilter();
		Assert.assertTrue(projectResultsPage.specialFilterDesignAlertsOnlyDisplayed(),
				"'DesignAlerts Only' is displayed in 'Special Filters");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify DesignAlert Highlighting of Search Term provided by API")
	public void dgnT3048(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPreferencesPage myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.unCheckDocumentFilterCheckBox();
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.uncheckSpecsFindInFilter();
		projectResultsPage.uncheckNewsFindInFilter();
		homePage.enterSearchText("door");
		homePage.clickOnSearchButton();

		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkHighlighted());
		ProjectDesignAlertsPage projectDesignAlertsPage = projectResultsPage.clickOnFirstHighlightedDesignAlertsLink();

		Assert.assertTrue(projectDesignAlertsPage.isDesignAlertsLinkHighlighted());
		Assert.assertTrue(projectDesignAlertsPage.isKeywordPresentOnDesignAlertsPage("door"));
		Assert.assertTrue(projectDesignAlertsPage.isKeywordHighlightedOnDesignAlertsPage());

		myAccount = homePage.clickOnMyAccountLink();
		myPreferencesPage = myAccount.clickOnMyPreferences();
		myPreferencesPage.clickOnDocumentFilter();
		myPreferencesPage.checkDocumentFilterCheckBox();

		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickOnDesignAlertInFindInFilter();
		projectResultsPage.uncheckSpecsFindInFilter();
		projectResultsPage.uncheckNewsFindInFilter();

		homePage.enterSearchText("door");
		homePage.clickOnSearchButton();

		Assert.assertTrue(projectResultsPage.isDesignAlertsLinkHighlighted());
		projectDesignAlertsPage = projectResultsPage.clickOnFirstHighlightedDesignAlertsLink();

		Assert.assertTrue(projectDesignAlertsPage.isDesignAlertsLinkHighlighted());
		Assert.assertTrue(projectDesignAlertsPage.isKeywordPresentOnDesignAlertsPage("door"));
		Assert.assertTrue(projectDesignAlertsPage.isKeywordHighlightedOnDesignAlertsPage());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "ProjectsOutSideOfSubscriptionWithDesignAlerts", description = "Verify projects with Design Alert for Out of subscription projects.")
	public void DGNT3041(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.checkDesignAlertFindInFilter();
		projectResultsPage.uncheckNewsFindInFilter();
		projectResultsPage.uncheckPlansFindInFilter();
		projectResultsPage.uncheckSpecsFindInFilter();
		projectResultsPage.clickOnSpecialFilter();
		projectResultsPage.clickSpecialFilterDesignAlertsOnly();
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultsPage.isDesignAlertsLabelDisplayed());
	}
}
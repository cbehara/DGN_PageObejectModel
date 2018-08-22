package com.ddaqe.dgn_company_profile;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_company_saved_search.DGNCompanySavedSearch;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.EmailAlertsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectFirmsPage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)
public class DGNCompanyProfile extends BaseTest {
	static Logger log = Logger.getLogger(DGNCompanySavedSearch.class);

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

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1694(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Company Results");
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyContactsPage companyContactsPage = companyPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyContactsPage.isCompanyOverviewSectionDisplayed());
		Assert.assertTrue(companyContactsPage.isCompanyListviewSectionDisplayed());
		Assert.assertTrue(companyContactsPage.isContactNameDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1696(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		Assert.assertEquals(homePage.getTitle(), "Dodge Global Network - Company Results");
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyContactsPage companyContactsPage = companyPage.clickOnCompanyContactsLink();
		Assert.assertTrue(companyContactsPage.isPaginationSectionDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1697(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsCompaniesDropdown();
		homePage.clickOnCompaniesDropdownMenu();
		homePage.enterSearchText("questcdn");
		CompanyResultsPage companyResultsPage = homePage.clickOnSearchButtonHomePagetoSearchCompanies();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.isCompanyOverviewDisplayed());
		Assert.assertTrue(companyPage.isTotalProjectsDisplayed());
		Assert.assertTrue(companyPage.isValuationDisplayed());
		Assert.assertTrue(companyPage.isAlsoKnownAsDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1698(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		ProjectFirmsPage projectFirmsPage = projectResultsPage.clickOnFirmsLink();
		CompanyPage companyPage = projectFirmsPage.clickOnFirstCompanyInList();
		Assert.assertEquals(companyPage.getTitle(), "Dodge Global Network - Company");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1709(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFistProjectCheckbox();
		companyResultsPage.clickOnSecondProjectCheckbox();
		companyResultsPage.clickOnActionsDropdown();
		CompanyPage companyPage = companyResultsPage.clickOnViewCompaniesUnderActions();
		Assert.assertTrue(companyPage.getPaginationDivText().contains("of"));
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = DGNCompanyProfileDataProvider.class, dataProvider = "DGNCompanyProfileDataProviderCommon", description = "Breadcrumb on company profile when clicking through from Dodge Report firm tab - Scenario 1 (TC3091)")
	public void tc1702(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnFistProjectCheckbox();
		companyResultsPage.clickOnActionsDropdown();
		EmailAlertsPage emailAlertsPage = companyResultsPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		char[] charArray491 = new char[491];
		Arrays.fill(charArray491, 'a');
		String email499Char = new String(charArray491) + "@abc.com";
		System.out.println("email499Char : "+ email499Char.length());
		emailAlertsPage.enterEmailAddress(email499Char);
		emailAlertsPage.clickOnPopUpSaveButton();
		Assert.assertFalse(emailAlertsPage.isEmailAddressDisplayed());
		
		companyResultsPage = new CompanyResultsPage(getDriver());
		companyResultsPage.clickOnActionsDropdown();
		emailAlertsPage = companyResultsPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		char[] charArray492 = new char[492];
		Arrays.fill(charArray492, 'a');
		String email500Char = new String(charArray492) + "@abc.com";
		System.out.println("email500Char : "+ email500Char.length());
		emailAlertsPage.enterEmailAddress(email500Char);
		emailAlertsPage.clickOnPopUpSaveButton();
		Assert.assertFalse(emailAlertsPage.isEmailAddressDisplayed());
		
		companyResultsPage = new CompanyResultsPage(getDriver());
		companyResultsPage.clickOnActionsDropdown();
		emailAlertsPage = companyResultsPage.clickEmailCompaniesLinkUnderActionsDrpDwn();
		char[] charArray493 = new char[500];
		Arrays.fill(charArray493, 'a');
		String email501Char = new String(charArray493) + "@abc.com";
		System.out.println("email501Char : "+ email501Char.length());
		emailAlertsPage.enterEmailAddress(email501Char);
		emailAlertsPage.clickOnPopUpSaveButton();
		Thread.sleep(3000);
		Assert.assertTrue(emailAlertsPage.isEmailAddressDisplayed());
	}

}

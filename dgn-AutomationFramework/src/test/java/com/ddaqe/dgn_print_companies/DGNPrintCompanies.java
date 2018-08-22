package com.ddaqe.dgn_print_companies;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyContactsPage;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.PrintCompanyDetailsPage;

@Listeners(TestListener.class)

public class DGNPrintCompanies extends BaseTest {

	static Logger log = Logger.getLogger(DGNPrintCompanies.class);

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
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1675(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doos");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.clickOnActionsDropdown();

		Assert.assertTrue(companyResultsPage.isPrintProjectDetailsDisplayed(),
				"Failed to display the expected link 'Print Company Details'");
		companyResultsPage.clickOnPrintCompanyDetailsUnderActions();
		Assert.assertTrue(companyResultsPage.getErrorMessage().contains("Please make a selection"),
				"Failed to get the expected mesasge");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1676(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1677(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1681(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		CompanyContactsPage companyContactsPage = companyResultsPage.clickOnCompanyContactsLink();
		companyContactsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyResultsPage.isPrintProjectDetailsDisplayed(),
				"Failed to display the expected link 'Print Company Details'");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1682(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		CompanyContactsPage companyContactsPage = companyResultsPage.clickOnCompanyContactsLink();
		companyContactsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyResultsPage.isPrintProjectDetailsDisplayed(),
				"Failed to display the expected link 'Print Company Details'");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1683(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		CompanyContactsPage companyContactsPage = companyResultsPage.clickOnCompanyContactsLink();
		companyContactsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyResultsPage.isPrintProjectDetailsDisplayed(),
				"Failed to display the expected link 'Print Company Details'");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1678(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.clickOnActionsDropdown();

		Assert.assertTrue(companyResultsPage.isPrintCompanyListDisplayed(),
				"Failed to display the expected link 'Print Company List'");
		companyResultsPage.clickOnPrintCompanyListUnderActions();
		Assert.assertTrue(companyResultsPage.getErrorMessage().contains("Please make a selection"),
				"Failed to get the expected mesasge");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1679(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1680(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1687(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();

		companyResultsPage.clickPowerRank();
		Assert.assertTrue(companyResultsPage.isPowerRankTextDisplayed(), "Failed to get the Power Rank Text");

		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1688(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();

		companyResultsPage.clickPowerRank();
		Assert.assertTrue(companyResultsPage.isPowerRankTextDisplayed(), "Failed to get the Power Rank Text");

		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();

		companyPage.clickOnActionsDropDown();
		PrintCompanyDetailsPage printCompanyDetailsPage = companyPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailsPage.getTitle(), "Dodge Global Network - Print");
		goToBackPage();

		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1684(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();

		companyProjectsPage.clickOnActionsDropdownCompany();
		PrintCompanyDetailsPage printCompanyDetailPage = companyProjectsPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1686(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();

		companyProjectsPage.clickOnActionsDropdownCompany();
		PrintCompanyDetailsPage printCompanyDetailPage = companyProjectsPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = PrintCompaniesDataProvider.class, dataProvider = "PlatinumUser", description = "To verify that the valuation range is displayed beside the code on print project details page when navigated from different screens.")
	public void tc1685(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();

		companyProjectsPage.clickOnActionsDropdownCompany();
		PrintCompanyDetailsPage printCompanyDetailPage = companyProjectsPage.clickOnPrintCompanyDetailsUnderActions();

		Assert.assertEquals(printCompanyDetailPage.getTitle(), "Dodge Global Network - Print");
		Assert.assertTrue(printCompanyDetailPage.isBackButtonDisplayed());
		Assert.assertTrue(printCompanyDetailPage.isPrintButtonDisplayed());

		goToBackPage();
		homePage.clickOnSignOutButton();

	}

}

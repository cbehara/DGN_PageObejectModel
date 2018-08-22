package com.ddaqe.dgn_download_companies;

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
import com.ddaqe.pages.DownloadCompanies;
import com.ddaqe.pages.EmailCompanyPage;
import com.ddaqe.pages.HomePage;

@Listeners(TestListener.class)
public class DGNDownloadCompanies1 extends BaseTest {
	static Logger log = Logger.getLogger(DGNDownloadCompanies1.class);

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
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "To verify the 'Download Company' functionality from Company Profile page.")
	public void tc1715(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitleWithContacts();
		Assert.assertTrue(companyPage.mouseOverActionandCheckdownloadCompanyDisplayed(),
				"Download Company action menu is not present for Company page");

		CompanyContactsPage companyContactsPage = companyPage.clickOnCompanyContactsLink();
		companyContactsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyContactsPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company contact page");

		CompanyProjectsPage companyProjectsPage = companyContactsPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnActionsDropdownCompany();
		Assert.assertTrue(companyProjectsPage.isDownloadCompanyActionMenuDisplayed(),
				"Download Company action menu is not present for Company contact page");

		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1717", description = "To verify the Download Companies Action item from company list view.")
	public void tc1717(String emailAddress, String password, String errorMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnDownloadCompaniesActionMenu();
		Assert.assertEquals(companyResultsPage.getErrorMessage(), errorMessage);
		homePage.clickOnSignOutButton();
	}

	@Test(dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1717", description = "To verify the Download Companies dialog and Cancel functionality.")
	public void tc1718(String emailAddress, String password, String errorMessage) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnActionsDropdown();
		companyResultsPage.clickOnDownloadCompaniesActionMenu();
		Assert.assertEquals(companyResultsPage.getErrorMessage(), errorMessage);

		companyResultsPage.clickOnFirstCompanyChkBox();
		DownloadCompanies downloadCompanies = companyResultsPage.mouseOverActionandClickDownloadCompanies();

		Assert.assertTrue(downloadCompanies.isPDFRadioBtnDisplayed(),
				"PDF radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isCSVRadioBtnDisplayed(),
				"CSV radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isXMLRadioBtnDisplayed(),
				"XML radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isSaveToSingleFilesRadioButtonDisplayed(),
				"Save To Single Files radio button is not displayed on Download Company dialog.");
		Assert.assertTrue(downloadCompanies.isSaveToSeparateFilesRadioButtonDisplyed(),
				"Save To Separate Files radio button is not displayed on Download Company dialog.");

		downloadCompanies.clickOnCancelBtn();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Companies Popup Dialog Displayed when click on cancel button");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1715", description = "Verify the Download Company functionality on Company Project page.")
	public void tc1731(String username, String password) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();

		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();

		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnActionsDropdownCompany();
		DownloadCompanies downloadCompanies = companyProjectsPage.clickOnDownloadCompanyActionMenu();
		downloadCompanies.clickOnCancelBtn();
		Assert.assertTrue(downloadCompanies.isDownloadCompaniesPopupDialogDisplayed(),
				"Download Companies Popup Dialog Displayed when cancel button is selected on Company project page.");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNDownloadCompaniesDataProvider.class, dataProvider = "TC1732", description = "Verify the Email Company functionality on Company Project page.")
	public void tc1732(String username, String password, String emailAddress) {
		HomePage homePage = loginAs(username, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.selectValueFromSortDropdown("Number of Projects - Descending");
		companyResultsPage.waitforLoadingRing();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		CompanyProjectsPage companyProjectsPage = companyPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnActionsDropdownCompany();
		EmailCompanyPage emailCompanyPage = companyProjectsPage.clickOnEmailCompanyActionMenu();

		Assert.assertTrue(emailCompanyPage.isEmailCompaniesPopupDialogDisplayed(),
				"Email Companies Popup Dialog is not Displayed on Company project page.");

		emailCompanyPage.enterEmailAddress(emailAddress);
		emailCompanyPage.clickOnSendEmailButton();

		Assert.assertTrue(companyProjectsPage.isProjectSelectAllDisplayed(),
				"Company Project page is not displayed after disappear of email company dialog.");

		homePage.clickOnSignOutButton();
	}

}

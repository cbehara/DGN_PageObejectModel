package com.ddaqe.dgn_stack_integration;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectAddendaDetailsPage;
import com.ddaqe.pages.ProjectAddendaPage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.ProjectSpecsPage;

@Listeners(TestListener.class)

public class DGNSTACK_Integration1 extends BaseTest {

	static Logger log = Logger.getLogger(DGNSTACK_Integration1.class);

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
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3763", description = "UI- Stack option verification in the Actions dropdown for plans tab")
	public void tc3763(String emailAddress, String password, String takeOffPartnerAreaHeader, String signUpLink,
			String orText, String whatItCanDoLink) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();

		Assert.assertTrue(projectPlansPage.isTakeOffPartnerAreaDisplayed());
		Assert.assertEquals(projectPlansPage.getTakeOffPartnerAreaHeader(), takeOffPartnerAreaHeader);
		Assert.assertTrue(projectPlansPage.isStackIconDisplayed());
		Assert.assertEquals(projectPlansPage.getSignUpLinkText(), signUpLink);
		Assert.assertEquals(projectPlansPage.getOrText(), orText);
		Assert.assertEquals(projectPlansPage.getFindOutWhatText(), whatItCanDoLink);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3763", description = "UI- Stack option verification in the Actions dropdown for specs tab")
	public void tc3762(String emailAddress, String password, String takeOffPartnerAreaHeader, String signUpLink,
			String orText, String whatItCanDoLink) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.clickOnActionsDropDown();

		Assert.assertTrue(projectSpecsPage.isTakeOffPartnerAreaDisplayed());
		Assert.assertEquals(projectSpecsPage.getTakeOffPartnerAreaHeader(), takeOffPartnerAreaHeader);
		Assert.assertTrue(projectSpecsPage.isStackIconDisplayed());
		Assert.assertEquals(projectSpecsPage.getSignUpLinkText(), signUpLink);
		Assert.assertEquals(projectSpecsPage.getOrText(), orText);
		Assert.assertEquals(projectSpecsPage.getFindOutWhatText(), whatItCanDoLink);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3763", description = "UI- Stack option verification in the Actions dropdown for addenda tab")
	public void tc3772(String emailAddress, String password, String takeOffPartnerAreaHeader, String signUpLink,
			String orText, String whatItCanDoLink) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.clickOnActionsDropDown();

		Assert.assertTrue(projectAddendaPage.isTakeOffPartnerAreaDisplayed());
		Assert.assertEquals(projectAddendaPage.getTakeOffPartnerAreaHeader(), takeOffPartnerAreaHeader);
		Assert.assertTrue(projectAddendaPage.isStackIconDisplayed());
		Assert.assertEquals(projectAddendaPage.getSignUpLinkText(), signUpLink);
		Assert.assertEquals(projectAddendaPage.getOrText(), orText);
		Assert.assertEquals(projectAddendaPage.getFindOutWhatText(), whatItCanDoLink);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3763", description = "UI- Stack option verification in the Actions dropdown for addenda Addenda Details page")
	public void tc3764(String emailAddress, String password, String takeOffPartnerAreaHeader, String signUpLink,
			String orText, String whatItCanDoLink) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		ProjectAddendaDetailsPage projectAddendaDetailsPage = projectAddendaPage.clickOnFirstAddendaTitle();
		projectAddendaDetailsPage.clickOnActionsDropDown();

		Assert.assertTrue(projectAddendaDetailsPage.isTakeOffPartnerAreaDisplayed());
		Assert.assertEquals(projectAddendaDetailsPage.getTakeOffPartnerAreaHeader(), takeOffPartnerAreaHeader);
		Assert.assertTrue(projectAddendaDetailsPage.isStackIconDisplayed());
		Assert.assertEquals(projectAddendaDetailsPage.getSignUpLinkText(), signUpLink);
		Assert.assertEquals(projectAddendaDetailsPage.getOrText(), orText);
		Assert.assertEquals(projectAddendaDetailsPage.getFindOutWhatText(), whatItCanDoLink);

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3766", description = "Selecting Stack options in the Actions dropdown of plans tab directs user to the particular pages")
	public void tc3766(String emailAddress, String password, String signUpForStackUrl, String whatItCanDoUrl) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();

		Assert.assertTrue(projectPlansPage.isTakeOffPartnerAreaDisplayed());

		projectPlansPage.clickOnSignUpForStackLinkAndSwitchDriverControl();
		Assert.assertEquals(projectPlansPage.getNewTabUrl(), signUpForStackUrl);
		projectPlansPage.closeNewTab();

		projectResultPage = homePage.clickOnProjectsLink();
		projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();
		projectPlansPage.clickOnFindOutWhatItCanDoForYouAndSwitchDriverControl();

		Assert.assertEquals(projectPlansPage.getNewTabUrl(), whatItCanDoUrl);
		projectPlansPage.closeNewTab();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3740", description = "UI- Design of 'Install Takeoff' present in the Actions drop-down in plans tab")
	public void tc3740(String emailAddress, String password, String installeTakeoffMenuText,
			String InstalleTakeoffUrl) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();

		Assert.assertEquals(projectPlansPage.getInstallETakeOffActionMenuText(), installeTakeoffMenuText);
		Assert.assertTrue(projectPlansPage.isOInstalleTakeoffLoweCase());
		Assert.assertTrue(projectPlansPage.isInstalleTakeoffMenuColorSameasOtherMenuItemsColors());

		projectPlansPage.clickOnInstallETakeoffAndSwitchDriverControl();
		Assert.assertEquals(projectPlansPage.getNewTabUrl(), InstalleTakeoffUrl);
		projectPlansPage.closeNewTab();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3740", description = "UI- Design of 'Install Takeoff' present in the Actions dropdown in specs tab")
	public void tc3741(String emailAddress, String password, String installeTakeoffMenuText,
			String InstalleTakeoffUrl) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.clickOnActionsDropDown();
		Assert.assertEquals(projectSpecsPage.getInstallETakeOffActionMenuText(), installeTakeoffMenuText);
		Assert.assertTrue(projectSpecsPage.isOInstalleTakeoffLoweCase());
		Assert.assertTrue(projectSpecsPage.isInstalleTakeoffMenuColorSameasOtherMenuItemsColors());

		projectSpecsPage.clickOnInstallETakeoffAndSwitchDriverControl();
		Assert.assertEquals(projectSpecsPage.getNewTabUrl(), InstalleTakeoffUrl);
		projectSpecsPage.closeNewTab();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3740", description = "UI- Design of 'Install Takeoff' present in the Actions dropdown in addenda tab")
	public void tc3742(String emailAddress, String password, String installeTakeoffMenuText,
			String InstalleTakeoffUrl) {

		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.clickOnActionsDropDown();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectAddendaPage.getInstallETakeOffActionMenuText(), installeTakeoffMenuText);
		Assert.assertTrue(projectAddendaPage.isOInstalleTakeoffLoweCase());
		Assert.assertTrue(projectAddendaPage.isInstalleTakeoffMenuColorSameasOtherMenuItemsColors());

		projectAddendaPage.clickOnInstallETakeoffAndSwitchDriverControl();
		Assert.assertEquals(projectAddendaPage.getNewTabUrl(), InstalleTakeoffUrl);
		projectAddendaPage.closeNewTab();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3740", description = "UI- Design of 'Install Takeoff' present in the Actions dropdown in Addenda Details Page")
	public void tc3773(String emailAddress, String password, String installeTakeoffMenuText,
			String InstalleTakeoffUrl) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();

		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		ProjectAddendaDetailsPage projectAddendaDetailsPage = projectAddendaPage.clickOnFirstAddendaTitle();

		Assert.assertTrue(projectAddendaDetailsPage.mouseoverActionandCheckInstallEtakeOffDisplayed());
		Assert.assertTrue(projectAddendaDetailsPage.isOInstalleTakeoffLoweCase());
		Assert.assertTrue(projectAddendaDetailsPage.isInstalleTakeoffMenuColorSameasOtherMenuItemsColors());

		projectAddendaDetailsPage.clickOnInstallETakeoffAndSwitchDriverControl();
		Assert.assertEquals(projectAddendaDetailsPage.getNewTabUrl(), InstalleTakeoffUrl);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3743", description = "UI- Design of 'Download Takeoff file' present in the Actions dropdown for specs tab")
	public void tc3744(String emailAddress, String password, String downloadETakeOffFile) throws InterruptedException {
		String downLoadedFileName = "DR";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();

		// Generate download file name with '.mhctl' extension
		downLoadedFileName = downLoadedFileName + projectSpecsPage.getDRNumber() + ".mhctl";
		projectSpecsPage.clickOnActionsDropDown();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectSpecsPage.getDownloadETakeOffFileText(), downloadETakeOffFile);
		Assert.assertTrue(projectSpecsPage.isOInDownloadeTakeoffFileLoweCase());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3743", description = "UI- Design of 'Download Takeoff file' present in the Actions dropdown for addenda tab")
	public void tc3774(String emailAddress, String password, String downloadETakeOffFile) throws InterruptedException {
		String downLoadedFileName = "DR";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectResultPage.waitforLoadingRing();
		// Generate download file name with '.mhctl' extension
		downLoadedFileName = downLoadedFileName + projectAddendaPage.getDRNumber() + ".mhctl";

		projectAddendaPage.clickOnActionsDropDown();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectAddendaPage.getDownloadETakeOffFileText(), downloadETakeOffFile);
		Assert.assertTrue(projectAddendaPage.isOInDownloadeTakeoffFileLoweCase());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3743", description = "UI- Design of 'Download Takeoff file' present in the Actions dropdown for addenda details page")
	public void tc3745(String emailAddress, String password, String downloadETakeOffFile) throws InterruptedException {
		String downLoadedFileName = "DR";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		projectResultPage.SelectResultDropdownValue("500");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		ProjectAddendaDetailsPage projectAddendaDetailsPage = projectAddendaPage.clickOnFirstAddendaTitle();

		// Generate download file name with '.mhctl' extension
		downLoadedFileName = downLoadedFileName + projectAddendaDetailsPage.getDRNumber() + ".mhctl";

		projectAddendaDetailsPage.clickOnActionsDropDown();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectAddendaDetailsPage.getDownloadETakeOffFileText(), downloadETakeOffFile);
		Assert.assertTrue(projectAddendaDetailsPage.isOInDownloadeTakeoffFileLoweCase());

		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "DGN-T1515", description = "Add 'e' to 'TakeOff' label and re-position in actions dropdown on Project report page")
	public void tcDGNT1515(String emailAddress, String password, String downloadETakeOffFile, String InstallETakeoff)
			throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("500");

		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();
		projectPlansPage.clickOnActionsDropDown();
		Assert.assertTrue(projectPlansPage.checkEPrefixDownloadTakeoffFile(),
				"'e' prefix should be added to the text 'Download Takeoff file' Project Plans Page");
		Assert.assertTrue(projectPlansPage.checkEPrefixDownloadTakeoffFile(),
				"Position of Download E TakeOff is not after install eTakeoff option Project Plans Page");

		projectResultPage = projectResultPage.clickOnProjectFilterBreadcrumb();
		projectPage = projectResultPage.clickOnFirstProjectTitleWithSpecs();
		ProjectSpecsPage projectSpecsPage = projectPage.clickOnSpecsTab();
		projectSpecsPage.clickOnActionsDropDown();

		Assert.assertTrue(projectSpecsPage.checkEPrefixDownloadTakeoffFile(),
				"'e' prefix should be added to the text 'Download Takeoff file' Project Specs Page");
		Assert.assertTrue(projectSpecsPage.checkEPrefixDownloadTakeoffFile(),
				"Position of Download E TakeOff is not after install eTakeoff option Project Specs Page");

		projectResultPage = projectResultPage.clickOnProjectFilterBreadcrumb();
		projectPage = projectResultPage.clickOnFirstProjectTitleWithAddenda();
		ProjectAddendaPage projectAddendaPage = projectPage.clickOnAddendaTab();
		projectAddendaPage.clickOnActionsDropDown();

		Assert.assertTrue(projectAddendaPage.checkEPrefixDownloadTakeoffFile(),
				"'e' prefix should be added to the text 'Download Takeoff file' Project Addenda Page");
		Assert.assertTrue(projectAddendaPage.checkEPrefixDownloadTakeoffFile(),
				"Position of Download E TakeOff is not after install eTakeoff option Project Addenda Page");
	}
}

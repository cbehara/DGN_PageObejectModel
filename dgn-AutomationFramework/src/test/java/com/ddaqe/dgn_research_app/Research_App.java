package com.ddaqe.dgn_research_app;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.KeystrokeResearchApp_Page;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class Research_App extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Create keystroke research page (UI only) - Authentication (TC7719)")
	public void DGN_T14132(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify ability to clear the keystroke box (TC7746)")
	public void DGN_T1427(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseKeystorke("keystrokes");
		keystrokeResearchAppPage.clickOnclearkeystrokebutton();
		Assert.assertTrue(keystrokeResearchAppPage.isLicenseKeystorketxtDisplayed());
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify clear functionality for get keystroke (TC7808)")
	public void DGN_T1433_1428(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnclearkeystrokebutton();
		
		Assert.assertFalse(keystrokeResearchAppPage.isLicenseKeystorketxtDisplayed());
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify error message for get keystroke for an empty license (TC7749)")
	public void DGN_T1430(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify clear functionality for get keystroke (TC7808)")
	public void DGN_T1429(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760sdf93457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		Assert.assertEquals(keystrokeResearchAppPage.gettxtlblStatus(), "The license is invalid");
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify error message for running search with empty keystroke (TC7739)")
	public void DGN_T1420(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertEquals(keystrokeResearchAppPage.gettxtlblError(), "The keystroke is empty");
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify error message for running the search with an invalid keystroke (TC7740)")
	public void DGN_T1421(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseKeystorke("keystrokes sad asd");
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertEquals(keystrokeResearchAppPage.gettxtlblStatus(),
				"The keystroke is invalid, please check and try again");
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify results for future publish date criteria (TC7886)")
	public void DGN_T1434(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnrbPublishedBetween();
		keystrokeResearchAppPage.clickOnFromDatetxt();
		keystrokeResearchAppPage.clickOnNextYear();
		keystrokeResearchAppPage.clickOnFromDate();
		keystrokeResearchAppPage.clickOnTodatetxt();
		keystrokeResearchAppPage.clickOnNextYear();
		keystrokeResearchAppPage.clickOnToDate();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertEquals(keystrokeResearchAppPage.gettxtlblStatus(), "Your search did not match any documents");
		keystrokeResearchAppPage.clickOnSignOutBtn();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify search results for a generated keystroke (TC7750)")
	public void DGN_T1431(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.IsProjectResult_Displayed());
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify search results for content scope - select all (TC7742)")
	public void DGN_T1423_1422(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		Assert.assertTrue(keystrokeResearchAppPage.IsContentScopeCbk_Selected(0));
		Assert.assertTrue(keystrokeResearchAppPage.IsContentScopeCbk_Selected(1));
		Assert.assertTrue(keystrokeResearchAppPage.IsContentScopeCbk_Selected(2));
		Assert.assertFalse(keystrokeResearchAppPage.IsContentScopeCbk_Selected(3));
		keystrokeResearchAppPage.clickOnContentScopeCbk(3);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		int before = keystrokeResearchAppPage.getExactProjectCount();
		keystrokeResearchAppPage.GotoBackPage();
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnContentScopeCbk(0);
		keystrokeResearchAppPage.clickOnContentScopeCbk(1);
		keystrokeResearchAppPage.clickOnContentScopeCbk(2);
		keystrokeResearchAppPage.clickOnContentScopeCbk(3);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		int after = keystrokeResearchAppPage.getExactProjectCount();
		Assert.assertEquals(before, after);
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify search results for individual report type selection (TC7743)")
	public void DGN_T1424(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnContentScopeCbk(1);
		keystrokeResearchAppPage.clickOnContentScopeCbk(2);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.IsProjectResult_Displayed());
		keystrokeResearchAppPage.GotoBackPage();
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnContentScopeCbk(0);
		keystrokeResearchAppPage.clickOnContentScopeCbk(1);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.IsProjectResult_Displayed());
		keystrokeResearchAppPage.GotoBackPage();
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnContentScopeCbk(1);
		keystrokeResearchAppPage.clickOnContentScopeCbk(2);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.IsProjectResult_Displayed());
		keystrokeResearchAppPage.GotoBackPage();
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnContentScopeCbk(2);
		keystrokeResearchAppPage.clickOnContentScopeCbk(3);
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.IsProjectResult_Displayed());
		keystrokeResearchAppPage.GotoBackPage();
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify the breadcrumb in the common header (TC7725)")
	public void DGN_T1419_1416(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_BreadCrumb_SearchResults_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.Is_SoryBy_DD_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.Is_Pegination_Displayed());
		keystrokeResearchAppPage.clickOn_BreadCrumb_KeystrokeResearch();
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify the navigation to dodge report from keystroke results (TC7751)")
	public void DGN_T1432(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_ProjectTitle().contains("drNo"));
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_FirmLink().contains("Firms"));
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_lnkBidders().contains("BiddersList"));
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_lnkPlans().contains("Plans"));
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_lnkAddenda().contains("Addenda"));
		Assert.assertTrue(keystrokeResearchAppPage.clickOn_lnkSpecs().contains("Specs"));
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify the links in common header (TC7724)")
	public void DGN_T1418(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnSignOutBtn();
		Assert.assertTrue(keystrokeResearchAppPage.IsSignInBtn_Displayed());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		keystrokeResearchAppPage.clickOnSignOutBtn();
		Assert.assertTrue(keystrokeResearchAppPage.IsSignInBtn_Displayed());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnbackToSearchmanagementLink();
		Assert.assertTrue(keystrokeResearchAppPage.IsKeyStrokeLink_Displayed());
		keystrokeResearchAppPage.clickOnKeyStrokeLink();
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		keystrokeResearchAppPage.clickOnbackToSearchmanagementLink();
		Assert.assertTrue(keystrokeResearchAppPage.IsKeyStrokeLink_Displayed());
		keystrokeResearchAppPage.clickOnKeyStrokeLink();
		keystrokeResearchAppPage.clickOnSignOutBtn();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "MultipleUsers", description = "Create keystroke research page (UI only) - Authentication (TC7719)")
	public void DGN_T1413_1415(String emailAddress, String password, String URL, String emailAddress1, String password1)
			throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress1, password1, URL);
		Assert.assertTrue(keystrokeResearchAppPage.ISbackToSearchmanagementLink_Displayed());
		keystrokeResearchAppPage.clickOnSignOutBtn();
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Create keystroke research page (UI only) (TC7720)")
	public void DGN_T1414(String emailAddress, String password, String URL) {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.isLastYearCheckBoxSelected(),
				"Last Year check box is not selected by default.");
		Assert.assertTrue(keystrokeResearchAppPage.isProjectCheckBoxSelected(),
				"Project check box is not selected by default.");
		Assert.assertTrue(keystrokeResearchAppPage.isProjectItemCheckBoxSelected(),
				"Project Item check box is not selected by default.");
		Assert.assertTrue(keystrokeResearchAppPage.isItemCheckBoxSelected(),
				"Item check box is not selected by default.");
		Assert.assertFalse(keystrokeResearchAppPage.isPermitCheckBoxSelected(),
				"Permit check box is selected by default.");
		keystrokeResearchAppPage.clickOnFromDatePicker();
		Assert.assertTrue(keystrokeResearchAppPage.isPublishedBetweenSelected(),
				"Published Between radio button is not selected.");
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		Assert.assertTrue(keystrokeResearchAppPage.isRunSearchButtonEnabled(),
				"Run Search button is disabled when less than 5000 characters entered in key stroke text field.");
		Assert.assertTrue(keystrokeResearchAppPage.isremainingCharactersStatusDisplayed(),
				"Remaining characters status is not displayed.");
		int stringLength = keystrokeResearchAppPage.gettxtLicenseKeystorketxt().length();
		keystrokeResearchAppPage.enterLicenseKeystorke(CommonUtils.generateString("sampleText", 5050 - stringLength));
		Assert.assertFalse(keystrokeResearchAppPage.isRunSearchButtonEnabled(),
				"Run Search button is not disabled when more than 5000 characters entered in key stroke text field.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify search results for default publish date criteria (TC7744)")
	public void DGN_T1425(String emailAddress, String password, String URL) throws ParseException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.isLastYearCheckBoxSelected(),
				"Last Year check box is not selected by default.");
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.checkPublishDate(1),
				"System displayed result for selected last year date option.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify search results for publish date criteria (TC7745)")
	public void DGN_T1426(String emailAddress, String password, String URL) throws ParseException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		keystrokeResearchAppPage.clickOnrbLastWeek();
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.checkPublishDate(3),
				"System displayed result for selected last week date option.");

		keystrokeResearchAppPage.clickOn_BreadCrumb_KeystrokeResearch();
		keystrokeResearchAppPage.clickOnrbLastMonth();
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.checkPublishDate(2),
				"System displayed result for selected last month date option.");

		keystrokeResearchAppPage.clickOn_BreadCrumb_KeystrokeResearch();
		keystrokeResearchAppPage.selectLastYearDateUsingDatePicker();
		keystrokeResearchAppPage.selectCurrentDateUsingDatePicker();
		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.checkPublishDate(1),
				"System displayed result for selected last year date option.");

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = Research_App_DataProvider.class, dataProvider = "Common_ResearchApp", description = "Verify common header UI for keystroke research pages (TC7723)")
	public void DGN_T1417(String emailAddress, String password, String URL) throws InterruptedException {
		KeystrokeResearchApp_Page keystrokeResearchAppPage = new KeystrokeResearchApp_Page(getDriver());
		keystrokeResearchAppPage.loginAs(emailAddress, password, URL);
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISbackToSearchmanagementLink_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISUserName_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISHeaderLogo_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.Is_SignOutBtn_Displayed());

		keystrokeResearchAppPage.enterLicenseNumber("298760324493457");
		keystrokeResearchAppPage.clickOnbtnGetKeystroke();
		keystrokeResearchAppPage.clickOnbtnRunSearch();
		Assert.assertTrue(keystrokeResearchAppPage.Is_SignOutBtn_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.Is_BreadCrumb_KeystrokeResearch_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISbackToSearchmanagementLink_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISUserName_Displayed());
		Assert.assertTrue(keystrokeResearchAppPage.ISHeaderLogo_Displayed());

	}

}

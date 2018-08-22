package com.ddaqe.dgn_project_pds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.constants.DGNProjectGridConstant;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.dgn_tech_support.DGNTech_SupportDataProvider;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.pages.TechSupportPage;
import com.ddaqe.pages.UserInformationPage;

@Listeners(TestListener.class)

public class DGNPDS extends BaseTest {

	static Logger log = Logger.getLogger(DGNPDS.class);

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
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter functionality check in conjunction with other project filters")
	public void dgnt3287(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.Expand_Project_Delivery_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectDeliveryFilterFacets");
		Assert.assertTrue(projectResultsPage.isLoadingRingDisplayed());
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectDeliveryFilterFacets");
		Assert.assertTrue(projectResultsPage.isLoadingRingDisplayed());
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnGeographyFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "GeographyFilterList");
		Assert.assertTrue(projectResultsPage.isLoadingRingDisplayed());
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnCONSTRUCTION_TYPEFilter();
		projectResultsPage.SelectOptionsFromTheList(2, "ConstructionType_LHSFilterList");
		Assert.assertTrue(projectResultsPage.isLoadingRingDisplayed());
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "t3280", description = "Project Delivery System filter default for first-time/new user")
	public void dgnt3280(String emailAddress, String password, String filtername) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		Assert.assertFalse(projectResultsPage.IsProject_Delivery_Filter_Expanded(),
				"Checking that the project delivery filter is not expanded intially");
		Assert.assertTrue(projectResultsPage.IsProjectDeliveryFilterpresentinMyfilter(),
				"Checking Project delivery filter is present in my filter section");
		Assert.assertEquals(projectResultsPage.getLastFilterNamepresentinMyfilter(), filtername);
		homePage.clickOnSignOutButton();

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "PDS filter crumbs are grouped within a PDS filter group crumb If more than 3 PDS filters selected on Project result search list")
	public void dgnt3282(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.Expand_Project_Delivery_Filter();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectDeliveryFilterFacets");
		projectResultsPage.CheckFilterCrumbPopupFunctionality();
		projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.clickOnCrumbFilterPopupFirstCloseBtn();
		projectResultsPage.clickOnCrumbFilterPopupDonebtn();
		projectResultsPage.waitforLoadingRing();
		String HyperlinkNumberAfterEdit = projectResultsPage.getFirstHyperlinkNumberInFilterCrumb();
		Assert.assertEquals(HyperlinkNumberAfterEdit, "4 Filters",
				"checking the number of filters clubbed in the hyperlink");
		projectResultsPage.ClickOncrumbSecondFilterClose();
		int appliedFilterSize = projectResultsPage.getFilterCrumb_AppliedFilterList_Size();
		Assert.assertEquals(appliedFilterSize, 1, "Checking all filters are removed from filter crumb");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter functionality check in conjunction with other project filters")
	public void t3287(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.checkHideFilterShowWithFilters();
		projectResultsPage.Expand_Project_Delivery_Filter();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectDeliveryFilterFacets");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter name and triangle verification")
	public void DGNT3279(String emailAddress, String password) throws InterruptedException {
		String filterName = "Project Delivery";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present on left nav.");
		Assert.assertEquals(projectResultsPage.checkProjectDelivaryFilterName(), filterName,
				"PDS filter name is as expected");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.ExpandAndCollapsePDSUsingArrowClick();
		projectResultsPage.ExpandAndCollapsePDSUsingArrowClick();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter should be displayed in Filter Crumb with X whenever PDS filter selection(s) are applied to the Project search results list")
	public void DGNT3281(String emailAddress, String password) throws InterruptedException {
		String filterName = "Project Delivery";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present on left nav.");
		projectResultsPage.clickOnClearAllLinkInFilter();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.SelectOptionsFromTheList(2, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		String CountBeforeFilterRemove = projectResultsPage.getProjectResultsCount();
		projectResultsPage.ClickOncrumbSecondFilterClose();
		String CountAfterFilterRemove = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBeforeFilterRemove, CountAfterFilterRemove);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "dgnt3274", description = "Project Delivery System filter should be implemented for the left-nav filters on the following Project pages")
	public void DGNT3274(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnSecondDashboardMyProjectsSavedSearchList();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page.");
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page out side subscription.");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page save search executed from My Accounts > My Saved Searches.");
		// add code for email alert step
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Clicking on 'X' closes 'See More' popup of Project Delivery System (PDS) filter")
	public void DGNT3284(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present on left nav.");
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(4, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Apply Project Delivery System (PDS) filter using 'See More' popup")
	public void DGNT3285(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present on left nav.");
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(3, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Change/Modify Project Delivery System (PDS) filter using  'See More' popup")
	public void DGNT3286(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present on left nav.");
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		projectResultsPage.SelectOptionsFromTheList(3, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertTrue(projectResultsPage.iscrumbSecondFilterDisplayed());
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		String CountBeforeSecond = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.SelectOptionsFromTheList(4, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfterSecond = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBeforeSecond, CountAfterSecond);
		projectResultsPage.IsFirstFourCurmbFiltersDisplayed();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Functionality of Project Delivery System filter's 'See More' popup on project save search page")
	public void DGNT3289(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardMyProjectsSavedSearchList();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		String CountBeforeSecond = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.SelectOptionsFromTheList(4, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfterSecond = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBeforeSecond, CountAfterSecond);
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnfirstProjectSavedSearch();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.SelectOptionsFromTheList(3, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBeforeSecond, CountAfterSecond);
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "dgnt3274", description = "Functionality of Project Delivery System filter's 'See More' popup on project save search page - Projects out-of-subscription")
	public void DGNT3292(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page.");
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.isSeeMorePopUpDisplayed());
		projectResultsPage.SelectOptionsFromTheList(4, "pdsSeeMorePopUpListChkBox");
		projectResultsPage.ClickOnPopupUpdateFancyBoxbtn();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Verify 'See More' pop-up contents of Project Delivery System (PDS) filter")
	public void DGNT3275_3429(String emailAddress, String password) throws InterruptedException {
		String popupHeading = "Project Delivery";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page.");
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertTrue(projectResultsPage.IsCommonPopupHideZeroProjects_cbk_Displayed(),
				"Check-box labeled 'Hide Selections with Zero Projects' is displayed on See more popup");
		Assert.assertEquals(projectResultsPage.checkPDSSeeMorePopupHeading(), popupHeading,
				"PDS See More popup heading is 'Project Delivery'");
		Assert.assertTrue(projectResultsPage.ISPopupSelectAll_cbk_displayed(),
				" 'Select All' check box is displayed on see more popup.");
		Assert.assertTrue(projectResultsPage.ISSeeMorePopup_UpdateBtn_displayed(),
				" 'Update' button is displayed on see more popup.");
		projectResultsPage.SwitchToParent();
		Assert.assertTrue(projectResultsPage.ISXCloseButtonOn_displayed(),
				" 'X' button is displayed on see more popup.");
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Verify defaut values in 'See More' pop-up of Project Delivery System (PDS) filter")
	public void DGNT3276(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page.");
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertFalse(projectResultsPage.IsCommonPopupHideZeroProjects_cbk_Selected(),
				" Hide Selection with zero projects check box is unselected/unchecked.");
		Assert.assertFalse(projectResultsPage.ISPopupSelectAll_cbk_Selected(),
				" Hide Selection with zero projects check box is unselected/unchecked.");
		Assert.assertFalse(projectResultsPage.Check_PDS_Filters_See_More_Selecteded("pdsSeeMorePopUpListChkBox"),
				" All PDS filter check boxes are unchecked by default.");
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "The PDS filter 'See More' pop-up provides the ability to select PDS filters")
	public void DGNT3278(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		projectResultsPage.SelectOptionsFromTheList(2, "pdsSeeMorePopUpListChkBox");
		Assert.assertTrue(projectResultsPage.Check_PDS_Filters_See_More_Selecteded("pdsSeeMorePopUpListChkBox"),
				" Hide Selection with zero projects check box is unselected/unchecked.");
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertFalse(projectResultsPage.Check_PDS_Filters_See_More_Selecteded("pdsSeeMorePopUpListChkBox"),
				" Selected PDS filter check boxes is unchecked.");
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertTrue(projectResultsPage.Check_PDS_Filters_See_More_Selecteded("pdsSeeMorePopUpListChkBox"),
				" All indivisual filters are checked.");
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		Assert.assertFalse(projectResultsPage.Check_PDS_Filters_See_More_Selecteded("pdsSeeMorePopUpListChkBox"),
				" All indivisual filters are un-checked.");
		projectResultsPage.SelectOptionsFromTheList(2, "pdsSeeMorePopUpListChkBox");
		Assert.assertFalse(projectResultsPage.ISPopupSelectAll_cbk_Selected(),
				"Selec All check box is unselected/unchecked.");
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.ClickOnPopupSelectAll_cbk();
		projectResultsPage.SelectOptionsFromTheList(12, "pdsSeeMorePopUpListChkBox");
		Assert.assertTrue(projectResultsPage.ISPopupSelectAll_cbk_Selected(),
				"Selec All check box is selected/checked.");
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "PDS Filter 'See More' pop-up - 'Hide Selections with Zero Projects' check-box funcationality")
	public void DGNT3277(String emailAddress, String password) throws InterruptedException {
		String hideLabelText = "Hide Selections with Zero Projects";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnProjectListToggleButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.clickOnPDSSeeMore_Btn();
		projectResultsPage.SwitchToFrame();
		Assert.assertEquals(projectResultsPage.checkPDSSeeMoreHideZeroLabelText(), hideLabelText,
				"Label text is 'Hide Selections with Zero Projects'");
		Assert.assertFalse(projectResultsPage.IsCommonPopupHideZeroProjects_cbk_Selected(),
				" Hide Selection with zero projects check box is unselected/unchecked.");
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		Assert.assertFalse(projectResultsPage.IsCheckZeroProjectFilter(), "Zero count projects hided.");
		projectResultsPage.ClickOnCommonPopupHideZeroProjects_cbk();
		Assert.assertEquals(projectResultsPage.CheckPDSSeeMoreFilterCount(), 12, "All filters are displayed.'");
		projectResultsPage.SwitchToParent();
		projectResultsPage.PopUpClose();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Saved project search with PDS criteria can be retrieved and executed with that PDS criteria.")
	public void DGNT3436(String emailAddress, String password) throws InterruptedException {
		String searchName = "AAAAAPDS";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnSecondDashboardMyProjectsSavedSearchList();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		// add code for email alert step
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Saved project search with PDS criteria can be updated with changes to the PDS criteria and retrieved and executed with the updated PDS criteria")
	public void DGNT3437(String emailAddress, String password) throws InterruptedException {
		String searchName = "AAAAAAPDS";
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnFirstDashboardMyProjectsSavedSearchList();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickOnSpecificProjectSavedSearch(searchName);
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		projectResultsPage.clickOnClearAllLinkInFilter();
		projectResultsPage.waitforLoadingRing();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		projectResultsPage.SelectOptionsFromTheList(3, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		homePage.clickOnSaveSearchButtonProject();
		saveSearchPopUp.clickSave();
		saveSearchPopUp.ClickOkOnAlertBox();
		projectResultsPage.waitforLoadingRing();
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		projectResultsPage.IsFirstThreeCurmbFiltersDisplayed();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project search saved with PDS search filter criteria.")
	public void DGNT3435(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();

		homePage.clickOnProjectsLink();
		String CountBefore = projectResultsPage.getProjectResultsCount();
		homePage.enterSearchText("wooden doors");
		homePage.clickOnSearchButton();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.SelectOptionsFromTheList(2, "ProjectDeliveryFilterFacets");
		projectResultsPage.IsFirstTwoCurmbFiltersDisplayed();
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();
		String CountAfter = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBefore, CountAfter);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");

		homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		String CountBeforeNew = projectResultsPage.getProjectResultsCount();
		projectResultsPage.SelectOptionsFromTheList(5, "ProjectDeliveryFilterFacets");
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isCrumbSecondFilterLinkDisplayed());
		projectResultsPage.ClickOncrumbSecondFilterLink();
		projectResultsPage.SwitchToActiveElement();
		Assert.assertTrue(projectResultsPage.isFilterPopupDisplayed());
		projectResultsPage.ClickOnFilterpopClose();

		homePage.clickOnSaveSearchButtonProject();
		String saveSearchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(saveSearchName);
		saveSearchPopUp.clickSave();
		projectResultsPage.waitforLoadingRing();
		String CountAfterNew = projectResultsPage.getProjectResultsCount();
		Assert.assertNotEquals(CountBeforeNew, CountAfterNew);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - Project Results");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter should exhibit features as in story “User-customizable Filter Preference”")
	public void DGNT3283(String emailAddress, String password) throws InterruptedException {

		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage.Expand_Project_Delivery_Filter();
		projectResultsPage.DragAndDrop_ProjectDelivarySystem_Filter_Additionalfilters_MySearches();
		Assert.assertTrue(projectResultsPage.IsProjectDelivarySystem_Filter_Collapsed());

		CompanyResultsPage CompanyResultsPage = projectResultsPage.ClickOnCompaniesTab();
		ProjectResultsPage projectResultsPage1 = CompanyResultsPage.clickOnTheProjectsLink();
		projectResultsPage1.DragAndDrop_ProjectDelivarySystem_Filter_MySearches_Additionalfilters();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Facets in the See More pop-up for Project Delivery System (PDS) filter are sorted in alphabetical order")
	public void DGNT3288(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage.clickOnSeeMorePDSIcon();
		Assert.assertTrue(projectResultsPage.verifyAscendingSortOrderForFacetsOnPDSPopup());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "common", description = "Project Delivery System filter, individual PDS filters (aka facets) check")
	public void DGNT3295(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		Assert.assertTrue(projectResultsPage.verifyPDSFilterList(),
				"Project Delivery System ( PDS ) filter facet are not displayed with valid components.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "PDS search criteria header and details are styled as per other search criteria in this pop-up")
	public void tc3460(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnProjectSaveSearch();
		Assert.assertTrue(techSupportPage.IsSavedSearchCriteriaDispalyed());
		Assert.assertTrue(techSupportPage.IsPDS_HeaderDisplayed());
		Assert.assertTrue(techSupportPage.IsPublishedSinceLabelsHighLightedInBold());
		Assert.assertTrue(techSupportPage.IsFindInLabelHighLightedInBold());
		Assert.assertTrue(techSupportPage.IsGeographyLabelHighLightedInBold());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "PDS search criteria header and details are not shown if the project saved search do not have PDS search criteria.")
	public void tc3461(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnDefaultSavedSearch();
		Assert.assertTrue(techSupportPage.IsSavedSearchCriteriaDispalyed());
		Assert.assertTrue(techSupportPage.IsDefaultSavedSearchHeaderDisplayed());
		Assert.assertFalse(techSupportPage.IsPDSSearchHeaderNotDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Verify PDS search criteria in 'Saved Search Criteria' pop-up")
	public void tc3463(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnProjectSaveSearch();
		Assert.assertTrue(techSupportPage.IsSavedSearchCriteriaDispalyed());
		Assert.assertTrue(techSupportPage.IsPDS_HeaderDisplayed());
		Assert.assertTrue(techSupportPage.IsPDSDescriptionDisplayed());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Manufacturer search criteria header and details are not shown if the project saved search do not have PDS search criteria.")
	public void tc3603(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.ClickOnSearchWithoutMFRSearch();
		Assert.assertFalse(techSupportPage.isManufactuereHeaderPresent());
		Assert.assertFalse(techSupportPage.isManufactuereTextPresent());
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "If more than one PDS search criteria, then concatenate onto same line, delimited by comma + space")
	public void tc3464(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		String ExpectedPDS = "GC to be Competitively Bid, Design/Build, Construction Management at Risk, Integrated Project Delivery";
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnMultiplePDSSearch();
		Assert.assertTrue(techSupportPage.IsPDS_HeaderDisplayed());
		String actualPDS = techSupportPage.IsMultiplePDSSearchDisplayed();
		Assert.assertEquals(ExpectedPDS, actualPDS);

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "If more than one Manufacturer search criteria, then concatenate onto same line, delimited by comma + space")
	public void tc3602(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		String ExpectedManufacturers = "Sherwin Williams, General Electric, Dow Corning, Crane Co, CertainTeed Corp";
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnMultiple_Manufacturers_Search();
		String actualManufacturers = techSupportPage.IsMultipleManufacturerSearchDisplayed();
		Assert.assertEquals(ExpectedManufacturers, actualManufacturers);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Manufacturer search criteria header and details are styled as per other search criteria in this pop-up")
	public void tc3604(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		String ExpectedManufacturersHeader = "Manufacturers:";
		String ExpectedBODManufacturersHeader = "Basis of Design Manufacturers:";
		String pManufacturerSavedSearch = "P_Manufacturer_Saved_Search";
		String bodManufacturerSavedSearch = "BOD_Manufacturer_Saved_Search";
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnPManufacturersSearch(pManufacturerSavedSearch);
		Assert.assertTrue(techSupportPage.isManufactuereHeaderPresent());
		Assert.assertTrue(techSupportPage.isManufactuereTextPresent());
		String actualManufacturersHeader = techSupportPage.isPManufacturerHeaderDisplayed();
		Assert.assertEquals(ExpectedManufacturersHeader, actualManufacturersHeader);
		techSupportPage.clickOnClose();
		techSupportPage.clickOnBODManufacturersSearch(bodManufacturerSavedSearch);
		String actualManufacturers = techSupportPage.isBODManufacturerHeaderDisplayed();
		Assert.assertEquals(ExpectedBODManufacturersHeader, actualManufacturers);
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Verify display of MFR & BOD criteria in Saved Searches")
	public void tc3601(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		String ExpectedBODManufacturersHeader = "Basis of Design Manufacturers:";
		String BODandSpecifiedManufacturerSavedSearch = "BODandSpecified_Saved_Search";
		String bodManufacturerSavedSearch = "BOD_Manufacturer_Saved_Search";
		String Specified_Saved_Search = "Specified_Saved_Search";
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.findSubscriberdisplay(), "Find Subscriber is not displayed");
		techSupportPage.enterSSOUserIDTxt(username);
		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		techSupportPage.clickOnSpecifiedManufacturersSearch(Specified_Saved_Search);
		Assert.assertTrue(techSupportPage.isManufactuereHeaderPresent());
		Assert.assertTrue(techSupportPage.isManufactuereTextPresent());
		techSupportPage.clickOnClose();
		techSupportPage.clickOnBODManufacturersSearch(bodManufacturerSavedSearch);
		String actualManufacturers = techSupportPage.isBODManufacturerHeaderDisplayed();
		Assert.assertEquals(ExpectedBODManufacturersHeader, actualManufacturers);
		techSupportPage.clickOnClose();
		techSupportPage.clickOnBODandspecifiedManufacturersSearch(BODandSpecifiedManufacturerSavedSearch);
		// Need to be Checked with existing behaviour.
		Assert.assertTrue(techSupportPage.isManufactuereHeaderPresent());
		Assert.assertTrue(techSupportPage.isManufactuereTextPresent());
		techSupportPage.clickOnClose();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = PDSDataProvider.class, dataProvider = "dgnt3274", description = "Sync to PlanRoom button for out-of-subscription projects is NOT shown for a user with access to PR16 (the old PlanRoom)")
	public void DGNT3366(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnSecondDashboardMyProjectsSavedSearchList();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Saved Search Page.");
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.isPDSFilterDisplayed(),
				"Project Delivery System ( PDS ) filter is present in left nav on Project Result Page.");
		projectResultsPage.clickOnREPORTSFilter();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.enterSearchText("Door");
		projectResultsPage.clickOnSearchButton();
		projectResultsPage.clickOnSpecLink();
		Assert.assertFalse(projectResultsPage.isSyncPlanRoomButtonVisible());
		projectResultsPage.clickOnPlansLink();
		Assert.assertFalse(projectResultsPage.isSyncPlanRoomButtonVisible());
		projectResultsPage.clickOnAddendaLink();
		Assert.assertFalse(projectResultsPage.isSyncPlanRoomButtonVisible());
	}

}
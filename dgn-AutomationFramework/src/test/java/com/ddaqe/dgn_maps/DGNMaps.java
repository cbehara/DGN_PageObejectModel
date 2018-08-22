package com.ddaqe.dgn_maps;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DGNMaps extends BaseTest {
	static Logger log = Logger.getLogger(DGNMaps.class);

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
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc33(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		companyPage.clickMapIcon();
		Assert.assertTrue(companyPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc32(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc36(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc38(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc37(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc34(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnFirstCompanyTitle();
		Assert.assertTrue(companyPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		companyPage.clickMapIcon();
		Assert.assertTrue(companyPage.mouseOverActionandCheckMapCompanyDisplayed(),
				"Map Company should have been displayed");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc35(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.mouseOverActionandCheckMapProjectDisplayed(),
				"Map Project should have been displayed");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc43(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc45(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc39(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc44(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapSectionDisplayed(), "Failed to display the map section");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc41(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitle();
		projectPage.clickMapIcon();
		Assert.assertTrue(projectPage.isMapIconLinkDisplayed(), "Failed to display the map icon");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc42(String emailAddress, String password) throws ParseException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.clickMapChartProjectDensityWashington();
		Assert.assertTrue(projectResultPage.verifyMapChartProjectDensityWashingtonSelected(),
				"Failed to get the project density by location selected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMapsDataProvider.class, dataProvider = "MapsPlatiAdmin", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc40(String emailAddress, String password) throws ParseException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.SelectResultDropdownValue("10");
		projectResultPage.clickDashboard1ToggleBtn();
		projectResultPage.clickMapChartProjectDensityWashington();
		Assert.assertTrue(projectResultPage.verifyMapChartProjectDensityWashingtonSelected(),
				"Failed to get the project density by location selected");
		homePage.clickOnSignOutButton();
	}

}

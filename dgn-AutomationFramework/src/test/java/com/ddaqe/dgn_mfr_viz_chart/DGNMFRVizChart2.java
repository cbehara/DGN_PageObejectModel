package com.ddaqe.dgn_mfr_viz_chart;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNMFRVizChart2 extends DGNMFRVizChartDataSet {
	static Logger log = Logger.getLogger(DGNMFRVizChart2.class);

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 1: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3553_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 1: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3553_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 1: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3555_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 1: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3555_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard1ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 2: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3557_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 2: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3557_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.unselectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.selectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 2: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3558_1(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderFirstChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart1Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNMFRVizChartDataProvider.class, dataProvider = "TCPlatiAdmin", description = "Dashboard 2: Verify batch download XML request in the database for p-manufacturer search criteria when page size is 5,000/10,000 and projects are > 1,000")
	public void tcDGNT3558_2(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectResultDropdownValue("5000");
		projectResultsPage.clickDashboard2ToggleBtn();
		projectResultsPage.waitforLoadingRing();
		projectResultsPage.selectManufacturersUnderSecondChartView();
		projectResultsPage.selectMFRVizBasicOfDesignCheckbox();
		projectResultsPage.unselectMFRVizSpecifiedCheckbox();
		projectResultsPage.clickOnClearAllLinkInFilter();

		projectResultsPage.clickOnMultipleManufacturerBarChart2Dashboard(3);
		projectResultsPage.clickOnListViewIcon();
		projectResultsPage.clickOnSelectAllProjects();
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadName = "My Download" + CommonUtils.getTimeStamp();
		downloadProjects.setDownloadBatchName(downloadName);
		downloadProjects.clickOnDownloadBtn();

		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.verifyDownloadName(downloadName));
		myDownloadsPage.clickOnFirstDownloadBatchCheckbox();
		myDownloadsPage.clickOnDeleteMenuPresentUnderActionDropdown();
	}
}

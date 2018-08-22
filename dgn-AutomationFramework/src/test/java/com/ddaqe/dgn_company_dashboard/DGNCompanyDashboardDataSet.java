package com.ddaqe.dgn_company_dashboard;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)

public class DGNCompanyDashboardDataSet extends BaseTest {

	static Logger log = Logger.getLogger(DGNCompanyDashboardDataSet.class);

	protected HomePage testPrerequisite(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		CreateSavedSearch(homePage);
		CreateTrackingLists(homePage);
		homePage.clickOnSignOutButton();
		return homePage;
	}

	public int getCompanySaveSearchCount(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		return savedSearchesPage.getSavedSearchesSize();
	}

	public int getCompanyTrackingListCount(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
		trackingLists.selectResultPerPage("500");
		return trackingLists.getDeleteLinkListSize();
	}

	public void CreateSavedSearch(HomePage homePage) {
		int companyCount = getCompanySaveSearchCount(homePage);
		if (companyCount < 20) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("door");
			homePage.clickOnSearchButton();
			String searchName = "";
			SavedSearchPopUp saveSearchPopUp;
			for (int i = 0; i < 20 - companyCount; i++) {
				saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				searchName = "Automation" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				if (i == 0) {
					try {
						saveSearchPopUp.clickSave();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						saveSearchPopUp.clickSaveAsNew();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void CreateTrackingLists(HomePage homePage) {
		int companyCount = getCompanyTrackingListCount(homePage);
		if (companyCount < 20) {
			CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
			for (int i = 0; i <= 20 - companyCount; i++) {
				TrackPopUpPage trackPopUpPage = companyResultsPage.clickOnTrackLink();
				String newTrackingListName = trackPopUpPage
						.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
				trackPopUpPage.enterNewTrackingListName(newTrackingListName);
				trackPopUpPage.clickOnSaveBtn();
				newTrackingListName = "";
			}
		}
	}

	public void DeleteCompanyTrackingList(HomePage homePage, String emailAddress, String password) {
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		trackingList.clickonCompanyLink();
		trackingList.deleteTrackingList();
	}

	public void DeleteCompanySavedSearches(HomePage homePage, String emailAddress, String password) {
		homePage.clickOnCompaniesLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		savedSearchesPage.clickOnCompanyTab();
		savedSearchesPage.deleteSavedSearches();
	}
}

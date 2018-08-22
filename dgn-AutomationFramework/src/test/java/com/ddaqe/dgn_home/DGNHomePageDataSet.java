package com.ddaqe.dgn_home;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)

public class DGNHomePageDataSet extends BaseTest {

	static Logger log = Logger.getLogger(DGNHomePageDataSet.class);

	public void testCleanup(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		do {
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		do {
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		do {
			savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public HomePage testPrerequisite(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		createPrivateProjectSaveSearchAdminUser(homePage);
		createPrivateCompanySaveSearchAdminUser(homePage);
		createPrivateProjectTrackingListAdminUser(homePage);
		homePage.clickOnSignOutButton();
		return homePage;
	}

	public int getPrivateProjectSaveSearchCount(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		return savedSearchesPage.getSavedSearchesSize();
	}

	public int getPrivateCompanySaveSearchCount(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
		savedSearchesPage.selectResultPerPageDisplayed("500");
		return savedSearchesPage.getSavedSearchesSize();
	}

	public int getPrivateProjectTrackingListSize(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.selectResultPerPage("500");
		return trackingLists.getDeleteLinkListSize();
	}

	public void createPrivateProjectSaveSearchAdminUser(HomePage homePage) {
		int projectCount = getPrivateProjectSaveSearchCount(homePage);
		if (projectCount < 20) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 20 - projectCount; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				saveSearchPopUp.selectSearchType("Private");
				try {
					if (i == 0) {
						saveSearchPopUp.clickSave();
					} else {
						saveSearchPopUp.clickSaveAsNew();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createPrivateCompanySaveSearchAdminUser(HomePage homePage) {
		int companyCount = getPrivateCompanySaveSearchCount(homePage);
		if (companyCount < 20) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 20 - companyCount; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				saveSearchPopUp.selectSearchType("Private");
				try {
					if (i == 0) {
						saveSearchPopUp.clickSave();
					} else {
						saveSearchPopUp.clickSaveAsNew();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createPrivateProjectTrackingListAdminUser(HomePage homePage) {
		int projectCount = getPrivateProjectTrackingListSize(homePage);
		if (projectCount < 20) {
			MyAccount myAccount = homePage.clickOnMyAccountLink();
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			trackingLists.selectResultPerPage("50");
			for (int i = 0; i < 20 - projectCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

}

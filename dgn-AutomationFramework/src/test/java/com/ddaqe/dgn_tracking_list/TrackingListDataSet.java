package com.ddaqe.dgn_tracking_list;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;
import com.ddaqe.pages.TrackingLists;

public class TrackingListDataSet extends BaseTest {

	static Logger log = Logger.getLogger(TrackingListDataSet.class);

	public void CreateNewTrackingList(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		if (trackingList.getTrackingListSize() < 2) {
			ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
			ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
			projectPage.clickOnActionsDropDown();
			TrackPopUpPage trackPopUpPage = projectPage.clickOnTrackProjects();
			String newTrackingListName = trackPopUpPage
					.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
			trackPopUpPage.enterNewTrackingListName(newTrackingListName);
			trackPopUpPage.clickOnSaveBtn();
		}
		homePage.clickOnSignOutButton();
	}

	public void CreateNewTrackingLisPlatiAdmint(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		if (trackingList.getTrackingListSize() < 2) {
			ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
			ProjectPage projectPage = projectResultsPage.clickOnFirstProjectTitle();
			projectPage.clickOnActionsDropDown();
			TrackPopUpPage trackPopUpPage = projectPage.clickOnTrackProjects();
			String newTrackingListName = trackPopUpPage
					.formatValidNewTrackingListName(trackPopUpPage.getTrackingInitial());
			trackPopUpPage.enterNewTrackingListName(newTrackingListName);
			trackPopUpPage.clickOnSaveBtn();
			homePage.clickOnSignOutButton();
		}
	}

	public void CreateNewSavedSearch(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		if (savedSearchesPage.getSavedSearchesSize() < 2) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

			String searchName1 = "Automation" + String.valueOf(new Date().getTime());
			saveSearchPopUp.enterName(searchName1);
			saveSearchPopUp.clickSave();
		}
		homePage.clickOnSignOutButton();
	}

	public HomePage testPrerequisite1(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		createPrivateProjectTrackingListAdminUser(homePage);
		createPrivateCompanyTrackingListAdminUser(homePage);
		homePage.clickOnSignOutButton();
		return homePage;
	}

	public void createPrivateProjectTrackingListAdminUser(HomePage homePage) {
		int projectCount = getPrivateProjectTrackingListSize(homePage);
		if (projectCount < 30) {
			MyAccount myAccount = homePage.clickOnMyAccountLink();
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			trackingLists.selectResultPerPage("50");
			for (int i = 0; i < 30 - projectCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public void createPrivateCompanyTrackingListAdminUser(HomePage homePage) {
		int companyCount = getPrivateCompanyTrackingListSize(homePage);
		if (companyCount < 30) {
			MyAccount myAccount = homePage.clickOnMyAccountLink();
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			trackingLists.selectResultPerPage("50");
			for (int i = 0; i < 30 - companyCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public int getPrivateProjectTrackingListSize(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.selectResultPerPage("500");
		return trackingLists.getDeleteLinkListSize();
	}

	public int getPrivateCompanyTrackingListSize(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
		trackingLists.selectResultPerPage("500");
		return trackingLists.getDeleteLinkListSize();
	}

}

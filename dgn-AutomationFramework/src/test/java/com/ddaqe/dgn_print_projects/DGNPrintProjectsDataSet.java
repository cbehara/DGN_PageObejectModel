package com.ddaqe.dgn_print_projects;

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

public class DGNPrintProjectsDataSet extends BaseTest {

	static Logger log = Logger.getLogger(DGNPrintProjectsDataSet.class);

	private int getProjectTrackingListCount(MyAccount myAccount, String typeListOption) {
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.selectTypeListDropdown(typeListOption);
		trackingLists.selectResultPerPageOption("500");
		return trackingLists.getDeleteLinkListSize();
	}

	private int getCompanyTrackingListCount(MyAccount myAccount, String typeListOption) {
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
		trackingLists.selectTypeListDropdown(typeListOption);
		trackingLists.selectResultPerPageOption("500");
		return trackingLists.getDeleteLinkListSize();
	}

	private int getProjectSaveSearchCount(HomePage homePage, String typeListOption) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.selectTypeListDropdown(typeListOption);
		savedSearchesPage.selectResultPerPageOption("500");
		return savedSearchesPage.getDeleteLinkListSize();
	}

	private int getCompanySaveSearchCount(HomePage homePage, String typeListOption) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
		savedSearchesPage.selectTypeListDropdown(typeListOption);
		savedSearchesPage.selectResultPerPageOption("500");
		return savedSearchesPage.getDeleteLinkListSize();
	}

	protected void testPrerequisiteForAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createPrivateProjectTrackingListAdminUser(myAccount, emailAddress, password);
		createPrivateCompanyTrackingListAdminUser(myAccount, emailAddress, password);
		createPrivateProjectSaveSearchAdminUser(homePage, emailAddress, password);
		createPrivateCompanySaveSearchAdminUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}

	public void createPrivateProjectTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Private");
		if (projectTrackingCount < 30) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			trackingLists.selectResultPerPage("500");
			for (int i = 0; i < 30 - projectTrackingCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public void createPrivateCompanyTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password) {
		int companyTrackingCount = getCompanyTrackingListCount(myAccount, "Private");
		if (companyTrackingCount < 30) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			for (int i = 0; i < 30 - companyTrackingCount; i++) {
				trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public void createPrivateProjectSaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int projectSaveSearch = getProjectSaveSearchCount(homePage, "Private");
		if (projectSaveSearch < 30) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 30 - projectSaveSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				saveSearchPopUp.selectSearchType("Private");
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

	public void createPrivateCompanySaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Private");
		if (companySavedSearch < 30) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 30 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				saveSearchPopUp.selectSearchType("Private");
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
}
package com.ddaqe.dgn_company_saved_search;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;

@Listeners(TestListener.class)

public class DGNCompanySavedSearchDataSet extends BaseTest {

	static Logger log = Logger.getLogger(DGNCompanySavedSearchDataSet.class);

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
		homePage.clickOnMyAccountLink();
		createPrivateCompanySaveSearchAdminUser(homePage, emailAddress, password);
		createPublicCompanySaveSearchAdminUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}
	
	
	public void createPrivateCompanySaveSearch(String emailAddress, String password) {
		
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnCompaniesLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
	    SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
		String searchName = "Auto" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		try {
				saveSearchPopUp.clickSave();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		homePage.clickOnSignOutButton();
			
}

	public void createPrivateCompanySaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Private");
		if (companySavedSearch < 5) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 5 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "Auto" + String.valueOf(new Date().getTime());
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

	public void createPublicCompanySaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Public");
		if (companySavedSearch < 5) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 5 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "Auto" + String.valueOf(new Date().getTime());
				saveSearchPopUp.enterName(searchName);
				saveSearchPopUp.selectSearchType("Public");
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

			SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
			saveSearchPopUp.enterName("SpecificSaveSearch");
			saveSearchPopUp.selectSearchType("Public");
			try {
				saveSearchPopUp.clickSaveAsNew();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
package com.ddaqe.dgn_customer_account_tools;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.ShareUsers;
import com.ddaqe.pages.TrackingLists;

@Listeners(TestListener.class)

public class DGNCustomerAccountToolsDataSet extends BaseTest {

	static Logger log = Logger.getLogger(DGNCustomerAccountToolsDataSet.class);

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

	private int getProjectDownloadCount(HomePage homePage) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		return myDownloadsPage.getDownloadLinkCount();
	}

	private int getCompanySaveSearchCount(HomePage homePage, String typeListOption) {
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
		savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
		savedSearchesPage.selectTypeListDropdown(typeListOption);
		savedSearchesPage.selectResultPerPageOption("500");
		return savedSearchesPage.getDeleteLinkListSize();
	}

	protected void testPrerequisiteForPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createPrivateProjectTrackingListPlusUser(myAccount, emailAddress, password);
		createPrivateCompanyTrackingListPlusUser(myAccount, emailAddress, password);
		createPrivateCompanySaveSearchPlusUser(homePage, emailAddress, password);
		createPrivateProjectSaveSearchPlusUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}

	protected void testPrerequisiteForAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createPrivateProjectTrackingListAdminUser(myAccount, emailAddress, password);
		createPublicProjectTrackingListAdminUser(myAccount, emailAddress, password);
		createPrivateCompanyTrackingListAdminUser(myAccount, emailAddress, password);
		createPublicCompanyTrackingListAdminUser(myAccount, emailAddress, password);
		createPrivateProjectSaveSearchAdminUser(homePage, emailAddress, password);
		createPublicProjectSaveSearchAdminUser(homePage, emailAddress, password);
		createPrivateCompanySaveSearchAdminUser(homePage, emailAddress, password);
		createPublicCompanySaveSearchAdminUser(homePage, emailAddress, password);
		createProjectDownloadListAdminUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}

	protected void testPrerequisiteForAdminSharedUser(String emailAddress, String password, String shareWithUserName) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createSharedProjectTrackingListAdminUser(myAccount, emailAddress, password, shareWithUserName);
		createSharedCompanySaveSearchAdminUser(homePage, emailAddress, password, shareWithUserName);
		createSharedProjectSaveSearchAdminUser(homePage, emailAddress, password, shareWithUserName);
		createSharedCompanyTrackingListAdminUser(myAccount, emailAddress, password, shareWithUserName);
		homePage.clickOnSignOutButton();
	}

	protected void testPrerequisiteForNonAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		createPrivateProjectTrackingListNonAdmin(myAccount, emailAddress, password);
		createPrivateCompanyTrackingListNonAdmin(myAccount, emailAddress, password);
		createPrivateProjectSaveSearchNonAdminUser(homePage, emailAddress, password);
		createPrivateCompanySaveSearchNonAdminUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}

	protected void testPrerequisiteForNonPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		createPrivateProjectSaveSearchNonPlusUser(homePage, emailAddress, password);
		homePage.clickOnSignOutButton();
	}

	protected void createPrivateProjectTrackingListPlusUser(MyAccount myAccount, String emailAddress, String password) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Private");
		if (projectTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			for (int i = 0; i < 15 - projectTrackingCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	protected void createPrivateCompanyTrackingListPlusUser(MyAccount myAccount, String emailAddress, String password) {
		int companyTrackingCount = getCompanyTrackingListCount(myAccount, "Private");
		if (companyTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			for (int i = 0; i < 15 - companyTrackingCount; i++) {
				trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	protected void createPrivateCompanySaveSearchPlusUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Private");
		if (companySavedSearch < 15) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 15 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	public void createSharedProjectTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password,
			String shareWithUserName) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Shared");
		if (projectTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String typeFilter = "Private";

			for (int i = 0; i < 15 - projectTrackingCount; i++) {
				trackingLists.selectTypeListOption(typeFilter);
				ShareUsers shareUsers_i = trackingLists.clickShareLink();
				shareUsers_i.clickOnSpecificUserShareUnshareLink(shareWithUserName);
				shareUsers_i.clickOnDoneButton_TrackingList();
			}
		}
	}

	public void createSharedCompanyTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password,
			String shareWithUserName) {
		int companyTrackingCount = getCompanyTrackingListCount(myAccount, "Shared");
		if (companyTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String typeFilter = "Private";
			for (int i = 0; i < 15 - companyTrackingCount; i++) {
				trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
				trackingLists.selectTypeListOption(typeFilter);
				ShareUsers shareUsers_i = trackingLists.clickShareLink();
				shareUsers_i.clickOnSpecificUserShareUnshareLink(shareWithUserName);
				shareUsers_i.clickOnDoneButton_TrackingList();
			}
		}
	}

	public void createPublicProjectTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Public");
		if (projectTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			String publicType = "Public";

			for (int i = 0; i < 15 - projectTrackingCount; i++) {
				trackName = "AutoPublic" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.selectAddNewTrackingListType(publicType);
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

	public void createPublicCompanyTrackingListAdminUser(MyAccount myAccount, String emailAddress, String password) {
		int companyTrackingList = getCompanyTrackingListCount(myAccount, "Public");
		if (companyTrackingList < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			String publicType = "Public";
			for (int i = 0; i < 15 - companyTrackingList; i++) {
				trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
				trackName = "AutoPublic" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.selectAddNewTrackingListType(publicType);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public void createPrivateProjectTrackingListNonAdmin(MyAccount myAccount, String emailAddress, String password) {
		int projectTrackingCount = getProjectTrackingListCount(myAccount, "Private");
		if (projectTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			for (int i = 0; i < 15 - projectTrackingCount; i++) {
				trackName = "AutoPrivate" + String.valueOf(new Date().getTime());
				trackingLists.clickOnAddNewButton();
				trackingLists.setNewTrackinListName(trackName);
				trackingLists.clickOnSaveButtonAddNewTrackingDialog();
			}
		}
	}

	public void createPrivateCompanyTrackingListNonAdmin(MyAccount myAccount, String emailAddress, String password) {
		int companyTrackingCount = getCompanyTrackingListCount(myAccount, "Private");
		if (companyTrackingCount < 15) {
			TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
			String trackName = "";
			for (int i = 0; i < 15 - companyTrackingCount; i++) {
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

	public void createPublicProjectSaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int projectSavedSearch = getProjectSaveSearchCount(homePage, "Public");
		if (projectSavedSearch < 15) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 15 - projectSavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	public void createPublicCompanySaveSearchAdminUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Public");
		if (companySavedSearch < 15) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 15 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	public void createProjectDownloadListAdminUser(HomePage homePage, String emailAddress, String password) {
		int projectDownload = getProjectDownloadCount(homePage);
		if (projectDownload < 5) {
			for (int i = 0; i < 5 - projectDownload; i++) {
				ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
				projectResultsPage.clickOnProjectListToggleButton();
				projectResultsPage.SelectResultDropdownValue("500");
				projectResultsPage.clickOnSelectAllProjects();
				projectResultsPage.clickOnActionsDropdown();
				DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
				downloadProjects.clickOnDownloadBtn();
			}
		}
	}

	public void createPrivateProjectSaveSearchNonAdminUser(HomePage homePage, String emailAddress, String password) {
		int projectSavedSearch = getProjectSaveSearchCount(homePage, "Private");
		if (projectSavedSearch < 15) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 15 - projectSavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	protected void createPrivateProjectSaveSearchNonPlusUser(HomePage homePage, String emailAddress, String password) {
		int projectSaveSearchCount = getProjectSaveSearchCount(homePage, "Private");
		if (projectSaveSearchCount < 15) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 15 - projectSaveSearchCount; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	protected void createPrivateProjectSaveSearchPlusUser(HomePage homePage, String emailAddress, String password) {
		int projectSaveSearchCount = getProjectSaveSearchCount(homePage, "Private");
		if (projectSaveSearchCount < 15) {
			homePage.clickOnProjectsLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();
			for (int i = 0; i < 15 - projectSaveSearchCount; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	public void createPrivateCompanySaveSearchNonAdminUser(HomePage homePage, String emailAddress, String password) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Private");
		if (companySavedSearch < 15) {
			homePage.clickOnCompaniesLink();
			homePage.enterSearchText("wood");
			homePage.clickOnSearchButton();

			for (int i = 0; i < 15 - companySavedSearch; i++) {
				SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonCompany();
				String searchName = "AutoPrivate" + String.valueOf(new Date().getTime());
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

	public void createSharedCompanySaveSearchAdminUser(HomePage homePage, String emailAddress, String password,
			String shareWithUserName) {
		int companySavedSearch = getCompanySaveSearchCount(homePage, "Shared");
		if (companySavedSearch < 15) {
			MyAccount myAccount = homePage.clickOnMyAccountLink();
			SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
			String typeFilter = "Private";

			for (int i = 0; i < 15 - companySavedSearch; i++) {
				savedSearchesPage.clickOnCompanyTab();
				savedSearchesPage.selectTypeListOption(typeFilter);
				ShareUsers shareUsers_i = savedSearchesPage.clickOnShareSavedSearchLink();
				shareUsers_i.clickOnSpecificUserShareUnshareLink(shareWithUserName);
				shareUsers_i.clickOnDoneButton_SavedSearch();
			}
		}
	}

	public void createSharedProjectSaveSearchAdminUser(HomePage homePage, String emailAddress, String password,
			String shareWithUserName) {
		int projectSavedSearch = getProjectSaveSearchCount(homePage, "Shared");
		if (projectSavedSearch < 15) {
			MyAccount myAccount = homePage.clickOnMyAccountLink();
			SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();
			String typeFilter = "Private";
			for (int i = 0; i < 15 - projectSavedSearch; i++) {
				savedSearchesPage.selectTypeListOption(typeFilter);
				ShareUsers shareUsers_i = savedSearchesPage.clickOnShareSavedSearchLink();
				shareUsers_i.clickOnSpecificUserShareUnshareLink(shareWithUserName);
				shareUsers_i.clickOnDoneButton_SavedSearch();
			}
		}
	}

	protected void deleteProjectTrackingListPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		do {
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	protected void deleteCompanyTrackingListPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();

		do {
			trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	protected void deleteProjectSaveSearchPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	protected void deleteCompanySaveSearchPlusUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteProjectTrackingListAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		do {
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteCompanyTrackingListAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();

		do {
			trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteProjectTrackingListNonAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();
		do {
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteCompanyTrackingListNonAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		TrackingLists trackingLists = myAccount.clickOnMyTrackingLists();

		do {
			trackingLists.clickOnCompanyTabForMyTrackingListsTestData();
			trackingLists.deleteTrackingList();
		} while (trackingLists.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteProjectSaveSearchAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteCompanySaveSearchAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteProjectSaveSearchtNonAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

	public void deleteCompanySaveSearchNonAdminUser(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		SavedSearchesPage savedSearchesPage = myAccount.clickOnSavedSearchMenuLinkMyAccount();

		do {
			savedSearchesPage.clickOnCompanyTabForSaveSearchTestData();
			savedSearchesPage.deleteSavedSearchesPostTest();
		} while (savedSearchesPage.isDeleteLinkPresent());
		homePage.clickOnSignOutButton();
	}

}
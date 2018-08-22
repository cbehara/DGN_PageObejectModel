package com.ddaqe.dgn_design_alert_save_search;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;

@Listeners(TestListener.class)

public class DGN_DesignAlert_saveSearch extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGN_DesignAlert_saveSearch_DataProvider.class, dataProvider = "CommonDesignAlert_saveSearch", description = "A project search with content-scope DesignAlert can be saved. Also Verify if Project save search with content scope Design Alert can be retrived and executed, Also Verify if the content-scope for an existing saved project search can be updated to include DesignAlert scope.")
	public void tcTest3647_3648_3649(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFindInFilter();
		projectResultsPage.clickFindInFilterDesignAlertsOnly();
		projectResultsPage.waitforLoadingRing();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName);
		saveSearchPopUp.clickSave();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		savedSearchesPage.clickSpecificProjectSavedSearch(searchName);
		Assert.assertEquals(projectResultsPage.getTitle(), "Dodge Global Network - My Account - My Saved Searches");
		projectResultsPage.Check_DefaultStatusOf_CheckboxList(searchName);
		homePage.clickOnSignOutButton();
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGN_DesignAlert_saveSearch_DataProvider.class, dataProvider = "CommonDesignAlert_saveSearch", description = "Verify the MARKETING tab is renamed to SWEETS ACTIVITY on DGN (TC24117)")
	public void tcTest1611(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnSweetsLink();
		homePage.clickOnSignOutButton();

	}

}
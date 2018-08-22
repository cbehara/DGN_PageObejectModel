package com.ddaqe.dgn_stack_integration;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectPlansPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)

public class DGNSTACK_Integration2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNSTACK_Integration2.class);

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
			"Regression" }, dataProviderClass = DGNSTACK_IntegrationDataProvider.class, dataProvider = "TC3743", description = "UI- Design of 'Download Takeoff file' present in the Actions dropdown for plans tab")
	public void tc3743(String emailAddress, String password, String downloadETakeOffFile) throws InterruptedException {
		String downLoadedFileName = "DR";
		HomePage homePage = loginAs(emailAddress, password);

		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.clickOnProjectListToggleButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstProjectTitleWithPlans();
		ProjectPlansPage projectPlansPage = projectPage.clickOnPlansTab();

		// Generate download file name with '.mhctl' extension
		downLoadedFileName = downLoadedFileName + projectPlansPage.getDRNumber() + ".mhctl";
		projectPlansPage.clickOnActionsDropDown();
		projectResultPage.waitforLoadingRing();
		Assert.assertEquals(projectPlansPage.getDownloadETakeOffFileText(), downloadETakeOffFile);
		Assert.assertTrue(projectPlansPage.isOInDownloadeTakeoffFileLoweCase());

		// Generate download file name with '.mhctl' extension
		downLoadedFileName = downLoadedFileName + projectPlansPage.getDRNumber() + ".mhctl";
		projectPlansPage.clickOnDownloadeTakeoffFile();
		projectPlansPage.waitforLoadingRing();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downLoadedFileName, 50, 2000));
		Assert.assertTrue(CommonUtils.deleteFile(downloadDestination, downLoadedFileName));
		homePage.clickOnSignOutButton();
	}
}

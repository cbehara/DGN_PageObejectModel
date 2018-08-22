package com.ddaqe.dgn_project_grid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.constants.DGNProjectGridConstant;
import com.ddaqe.pages.DownloadProjects;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;

@Listeners(TestListener.class)
public class DGNProjectGrid2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNProjectGrid2.class);

	@Test(groups = { "Regression",
			"Medium" }, dataProviderClass = ProjectGridDataProvider.class, dataProvider = "DGNProjectGridDataProviderCommon", description = "Select All and Action functionality on Project Grid view")
	public void tc3405(String emailAddress, String password) throws Exception {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnGridViewUnSelectedToggleButton();
		projectResultsPage.SelectResultDropdownValue("100");
		projectResultsPage.clickOnProjectGridSelectAllCheckBox();
		projectResultsPage.waitforLoadingRing();
		Assert.assertTrue(projectResultsPage.verifyProjectGridSelectAllCheckBoxFunctionality(),
				"All project check boxes are not geeting selected by clicking on Select All check box.");
		List<String> actionsDropdownActualValues = new ArrayList<>();
		actionsDropdownActualValues.addAll(projectResultsPage.VerifyActionsDropdownValues());
		Assert.assertEquals(DGNProjectGridConstant.getActionDropdownOptions(), actionsDropdownActualValues);
		DownloadProjects downloadProjects = projectResultsPage.mouseOverActionandClickDownloadProjects();
		downloadProjects.isDownloadProjectsPopupDisplayed();
		downloadProjects.clickOnCSVRadioBtn();
		String downloadedCSVFileName = downloadProjects.getDownloadFileName() + ".csv";
		downloadProjects.clickOnDownloadBtn();
		Assert.assertTrue(CommonUtils.isFileDownloaded(downloadDestination, downloadedCSVFileName, 25, 2000));
		File downloadedFile = CommonUtils.getLatestDownloadedFile(downloadDestination);
		CSVReader csvReader = new CSVReader(downloadedFile);
		Assert.assertEquals(csvReader.getValuesFromKey("PROJECT NUMBER").size(), 100,
				"All reocrd are not downloaded to CSV from Grid.");
	}
}

package com.ddaqe.dgn_project_dashboard;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNProjectDashboardDataProvider {

	@DataProvider(name = "TCAdminUSAInt")
	public static Object[][] TCAdminUSAInt() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Dashboard.testdata.file"), 0);
		return excelInputReader.getData("TCAdminUSAInt", true);
	}

	@DataProvider(name = "TCNonAdmin_NotInSubscription")
	public static Object[][] TCNonAdmin_NotInSubscription() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Dashboard.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdmin_NotInSubscription", true);
	}
	
	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Dashboard.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}
}

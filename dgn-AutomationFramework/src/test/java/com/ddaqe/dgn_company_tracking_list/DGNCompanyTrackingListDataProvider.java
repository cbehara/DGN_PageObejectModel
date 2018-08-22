package com.ddaqe.dgn_company_tracking_list;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyTrackingListDataProvider {

	@DataProvider(name = "TCNonAdmin_WithoutSpecAlerts")
	public static Object[][] TCNonAdmin_WithoutSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdmin_WithoutSpecAlerts", true);
	}

	@DataProvider(name = "TCNonAdmin_WithSpecAlerts")
	public static Object[][] TCNonAdmin_WithSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdmin_WithSpecAlerts", true);
	}

	@DataProvider(name = "TCPlatiAdmin_WithoutSpecAlerts")
	public static Object[][] TCPlatiAdmin_WithoutSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdmin_WithoutSpecAlerts", true);
	}

	@DataProvider(name = "TCPlatiAdmin_WithPurchases")
	public static Object[][] TCPlatiAdmin_WithPurchases() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdmin_WithPurchases", true);
	}

	@DataProvider(name = "Admin")
	public static Object[][] Admin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("Admin", true);
	}

}

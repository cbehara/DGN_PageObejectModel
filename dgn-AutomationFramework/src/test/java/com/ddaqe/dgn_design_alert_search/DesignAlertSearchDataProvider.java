package com.ddaqe.dgn_design_alert_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DesignAlertSearchDataProvider {
	
	@DataProvider(name = "common")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("common", true);
	}
	
	@DataProvider(name = "plususer")
	public static Object[][] plususer() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("plususer", true);
	}
	

	@DataProvider(name = "common_key")
	public static Object[][] common_key() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("common_key", true);
	}
	
	@DataProvider(name = "dGNT3061")
	public static Object[][] dGNT3061() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("dGNT3061", true);
	}
	
	@DataProvider(name = "dGNT3062")
	public static Object[][] dGNT3062() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("dGNT3062", true);
	}
	
	@DataProvider(name = "dGNT3056")
	public static Object[][] dGNT3056() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("dGNT3056", true);
	}
	
	@DataProvider(name = "dGNT3037")
	public static Object[][] dGNT3037() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("dGNT3037", true);
	}
		
	@DataProvider(name = "DGN-T3044")
	public static Object[][] tcDGNT3044() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3044", true);
	}
	
	@DataProvider(name = "DGN-T3045")
	public static Object[][] tcDGNT3045() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3045", true);
	}
	
	@DataProvider(name = "ProjectsOutSideOfSubscription")
	public static Object[][] ProjectsOutSideOfSubscription() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("ProjectsOutSideOfSubscription", true);
	}
	
	@DataProvider(name = "ProjectsOutSideOfSubscriptionWithDesignAlerts")
	public static Object[][] ProjectsOutSideOfSubscriptionWithDesignAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_search.testdata.file"), 0);
		return excelInputReader.getData("ProjectsOutSideOfSubscriptionWithDesignAlerts", true);
	}
	
}

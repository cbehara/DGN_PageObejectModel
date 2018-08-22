package com.ddaqe.dgn_project_saved_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNProjectSavedSearchDataProvider {

	
	@DataProvider(name = "DGNProjectSavedSearchDataProviderCommon")
	public static Object[][] DGNSavedSearchCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
	
	@DataProvider(name = "DGNProjectSavedSearchDataProviderCommonNonAdmin")
	public static Object[][] DGNSavedSearchCommonNonAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommonNonAdmin", true);
	}
	
	@DataProvider(name = "TC3049")
	public static Object[][] TC3049() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TC3049", true);
	}
	
}

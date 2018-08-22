package com.ddaqe.dgn_project_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ProjectSearchDataProvider {

	@DataProvider(name = "DGNProjectSearchDataProviderCommon")
	public static Object[][] DGNSearchCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

	@DataProvider(name = "DGNProjectSearchDataProviderCommonNonAdmin")
	public static Object[][] DGNSearchCommonNonAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommonNonAdmin", true);
	}

	@DataProvider(name = "DGNProjectSearchDataProviderMultipleDR")
	public static Object[][] DGNSearchmultipleDR() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2633", true);
	}

	@DataProvider(name = "DGNProjectSearchDataProviderMultipleDRInLicense")
	public static Object[][] DGNSearchmultipleDRInLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("MultipleDRInlicense", true);
	}
	
	@DataProvider(name = "DGNProjectSearchDataProviderMultipleDRInvalid")
	public static Object[][] DGNSearchmultipleDRInvalid() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("MultipleDRInvalid", true);
	}

	@DataProvider(name = "DGNProjectSearchDataProviderDR")
	public static Object[][] DGNSearchDR() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("Dr", true);
	}

	@DataProvider(name = "DGNProjectSearchDataProviderComonPlus")
	public static Object[][] DGNSearchPlus() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommonPlus", true);
	}
}

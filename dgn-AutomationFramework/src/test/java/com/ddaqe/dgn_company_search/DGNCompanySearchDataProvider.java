package com.ddaqe.dgn_company_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanySearchDataProvider {

	@DataProvider(name = "TC2906")
	public static Object[][] tc2906() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2906", true);
	}

	@DataProvider(name = "TC2908")
	public static Object[][] tc2908() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2908", true);
	}

	@DataProvider(name = "TC2907")
	public static Object[][] tc2907() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2907", true);
	}

	@DataProvider(name = "TC2912")
	public static Object[][] tc2912() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2912", true);
	}

	@DataProvider(name = "TC2917")
	public static Object[][] tc2917() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC2917", true);
	}

	@DataProvider(name = "TC3752")
	public static Object[][] tc3752() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC3752", true);
	}

	@DataProvider(name = "TC3771")
	public static Object[][] tc3771() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("TC3771", true);
	}
	
	@DataProvider(name = "PlatinumUser")
	public static Object[][] tcPlatinumUser() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("PlatinumUser", true);
	}
	
	@DataProvider(name = "PlusUser")
	public static Object[][] tcPlusUser() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("PlusUser", true);
	}
	
	@DataProvider(name = "DGN-T3321")
	public static Object[][] tcDGNT3321() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3321", true);
	}
	
	@DataProvider(name = "DGN-T3324")
	public static Object[][] tcDGNT3324() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Search.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3324", true);
	}
}

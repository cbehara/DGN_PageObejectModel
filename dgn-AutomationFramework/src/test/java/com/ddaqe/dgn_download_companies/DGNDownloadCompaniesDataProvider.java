package com.ddaqe.dgn_download_companies;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNDownloadCompaniesDataProvider {

	@DataProvider(name = "TC1715")
	public static Object[][] tc1715() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Companies.testdata.file"), 0);
		return excelInputReader.getData("TC1715", true);
	}
	
	@DataProvider(name = "TC1717")
	public static Object[][] tc1717() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Companies.testdata.file"), 0);
		return excelInputReader.getData("TC1717", true);
	}
	
	@DataProvider(name = "TC1719")
	public static Object[][] tc1719() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Companies.testdata.file"), 0);
		return excelInputReader.getData("TC1719", true);
	}
	
	@DataProvider(name = "TC1732")
	public static Object[][] tc1732() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Companies.testdata.file"), 0);
		return excelInputReader.getData("TC1732", true);
	}
	
	@DataProvider(name = "TC1729")
	public static Object[][] tc1729() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Companies.testdata.file"), 0);
		return excelInputReader.getData("TC1729", true);
	}
	
}

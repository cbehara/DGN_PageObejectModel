package com.ddaqe.dgn_print_projects;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class PrintProjectsDataProvider {
	
	@DataProvider(name = "TC1883")
	public static Object[][] tc1883() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1883", true);
	}
	
	@DataProvider(name = "TC1889")
	public static Object[][] tc1889() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1889", true);
	}
	
	@DataProvider(name = "TC1890")
	public static Object[][] tc1890() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1890", true);
	}
	
	@DataProvider(name = "TC1891")
	public static Object[][] tc1891() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1891", true);
	}
	
	@DataProvider(name = "TC1905")
	public static Object[][] tc1905() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1905", true);
	}
}

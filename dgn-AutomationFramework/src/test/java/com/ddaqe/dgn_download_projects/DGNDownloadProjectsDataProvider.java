package com.ddaqe.dgn_download_projects;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNDownloadProjectsDataProvider {
	
	@DataProvider(name = "Common")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("Common", true);
	}

	@DataProvider(name = "TC-Common")
	public static Object[][] tcCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC-Common", true);
	}
	
	@DataProvider(name = "TC1917")
	public static Object[][] tc1917() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1917", true);
	}
	
	@DataProvider(name = "TC1924")
	public static Object[][] tc1924() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1924", true);
	}
	
	@DataProvider(name = "TC1933")
	public static Object[][] tc1933() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1933", true);
	}
	
	@DataProvider(name = "TC1935")
	public static Object[][] tc1935() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1935", true);
	}
	
	@DataProvider(name = "TC1936")
	public static Object[][] tc1936() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1936", true);
	}
	
	@DataProvider(name = "TC1942")
	public static Object[][] tc1942() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1942", true);
	}
	
	@DataProvider(name = "TC1948")
	public static Object[][] tc1948() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1948", true);
	}
	
	@DataProvider(name = "TC1951")
	public static Object[][] tc1951() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1951", true);
	}
	
	@DataProvider(name = "TC1966")
	public static Object[][] tc1966() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1966", true);
	}
	
	@DataProvider(name = "TC1978")
	public static Object[][] tc1978() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1978", true);
	}
}

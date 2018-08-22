package com.ddaqe.dgn_download_firms;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNDownloadFirmDataProvider {
	
	@DataProvider(name = "TC1710")
	public static Object[][] tc1710() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1710", true);
	}
	@DataProvider(name = "TC1711")
	public static Object[][] tc1711() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1711", true);
	}
	@DataProvider(name = "TC1712")
	public static Object[][] tc1712() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1712", true);
	}
	@DataProvider(name = "TC1713")
	public static Object[][] tc1713() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1713", true);
	}
	@DataProvider(name = "TC1714")
	public static Object[][] tc1714() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1714", true);
	}
	@DataProvider(name = "TC1714_2")
	public static Object[][] tc1714_2() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Firms.testdata.file"), 0);
		return excelInputReader.getData("TC1714_2", true);
	}
}

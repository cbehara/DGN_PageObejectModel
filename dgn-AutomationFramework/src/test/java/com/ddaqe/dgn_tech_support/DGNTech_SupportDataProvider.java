package com.ddaqe.dgn_tech_support;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNTech_SupportDataProvider {
	@DataProvider(name = "TCCommon")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tech_Support.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

	@DataProvider(name = "TC2937")
	public static Object[][] tc2937() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tech_Support.testdata.file"), 0);
		return excelInputReader.getData("TC2937", true);
	}

	@DataProvider(name = "TC2953")
	public static Object[][] tc2953() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Tech_Support.testdata.file"), 0);
		return excelInputReader.getData("TC2953", true);
	}

}

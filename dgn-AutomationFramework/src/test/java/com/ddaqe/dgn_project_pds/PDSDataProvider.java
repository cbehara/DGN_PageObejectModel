package com.ddaqe.dgn_project_pds;

import org.testng.annotations.DataProvider;
import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class PDSDataProvider {

	@DataProvider(name = "common")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_PDS.testdata.file"), 0);
		return excelInputReader.getData("common", true);
	}

	@DataProvider(name = "dgnt3274")
	public static Object[][] dgnt3274() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_PDS.testdata.file"), 0);
		return excelInputReader.getData("dgnt3274", true);
	}

	@DataProvider(name = "t3280")
	public static Object[][] t3280() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_PDS.testdata.file"), 0);
		return excelInputReader.getData("t3280", true);
	}
}

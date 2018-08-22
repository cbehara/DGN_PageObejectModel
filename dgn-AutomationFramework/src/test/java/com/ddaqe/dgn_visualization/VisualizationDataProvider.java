package com.ddaqe.dgn_visualization;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class VisualizationDataProvider {

	@DataProvider(name = "TCPlatiAdminSpec")
	public static Object[][] TCPlatiAdminSpec() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Visualization.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminSpec", true);
	}

	@DataProvider(name = "TCPlatiAdminNonSpec")
	public static Object[][] TCPlatiAdminNonSpec() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Visualization.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminNonSpec", true);
	}

	@DataProvider(name = "TC3684")
	public static Object[][] TC3684() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Visualization.testdata.file"), 0);
		return excelInputReader.getData("TC3684", true);
	}
}

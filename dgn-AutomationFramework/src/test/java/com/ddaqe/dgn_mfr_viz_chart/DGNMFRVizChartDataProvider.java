package com.ddaqe.dgn_mfr_viz_chart;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNMFRVizChartDataProvider {
	
	@DataProvider(name = "TCPlatiAdmin")
	public static Object[][] TCPlatiAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_MFR_Viz_Chart.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdmin", true);
	}

	@DataProvider(name = "TCPlatiAdminDR")
	public static Object[][] TCPlatiAdminDR() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_MFR_Viz_Chart.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminDR", true);
	}

	@DataProvider(name = "TCPlatiAdminDR3")
	public static Object[][] TCPlatiAdminDR3() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_MFR_Viz_Chart.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminDR3", true);
	}
}

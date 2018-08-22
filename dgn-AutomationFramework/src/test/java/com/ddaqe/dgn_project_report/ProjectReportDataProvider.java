package com.ddaqe.dgn_project_report;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ProjectReportDataProvider {

	@DataProvider(name = "TCPlatiAdmin")
	public static Object[][] TCPlatiAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ProjectReport.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdmin", true);
	}

	@DataProvider(name = "TCPlatiAdminDR")
	public static Object[][] TCPlatiAdminDR() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ProjectReport.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminDR", true);
	}
	
	@DataProvider(name = "TCPlatiAdminDR3")
	public static Object[][] TCPlatiAdminDR3() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ProjectReport.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminDR3", true);
	}

}

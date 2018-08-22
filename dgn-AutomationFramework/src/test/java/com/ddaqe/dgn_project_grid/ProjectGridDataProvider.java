package com.ddaqe.dgn_project_grid;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ProjectGridDataProvider {

	@DataProvider(name = "DGNProjectGridDataProviderCommon")
	public static Object[][] DGNGridCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Grid.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
	
	@DataProvider(name = "DGNProjectGridDataProviderOOS")
	public static Object[][] DGNGridOOS() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Grid.testdata.file"), 0);
		return excelInputReader.getData("TCOOS", true);
	}
}

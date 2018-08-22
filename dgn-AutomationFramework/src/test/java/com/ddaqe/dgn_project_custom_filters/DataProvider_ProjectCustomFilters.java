package com.ddaqe.dgn_project_custom_filters;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DataProvider_ProjectCustomFilters {

	@DataProvider(name = "CommonProjectCustomFilters")
	public static Object[][] CommonProjectCustomFilters() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ProjectCustomFilters.testdata.file"), 0);
		return excelInputReader.getData("CommonProjectCustomFilters", true);
	}
}	
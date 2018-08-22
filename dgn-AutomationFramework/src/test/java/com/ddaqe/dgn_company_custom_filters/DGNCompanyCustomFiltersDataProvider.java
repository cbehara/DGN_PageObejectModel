package com.ddaqe.dgn_company_custom_filters;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyCustomFiltersDataProvider {
	
	@DataProvider(name = "TC3946")
	public static Object[][] tc3946() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Custom_Filters.testdata.file"), 0);
		return excelInputReader.getData("TC3946", true);
	}
}

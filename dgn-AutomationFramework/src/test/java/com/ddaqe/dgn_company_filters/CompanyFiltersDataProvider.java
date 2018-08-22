package com.ddaqe.dgn_company_filters;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class CompanyFiltersDataProvider {

	@DataProvider(name = "CommonCompanyFilterDataProvider")
	public static Object[][] CommonCompanyFilterDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Filters.testdata.file"), 0);
		return excelInputReader.getData("CommonCompanyFilterDataProvider", true);
	}
	
	@DataProvider(name = "PlusUser")
	public static Object[][] PlusUser() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Filters.testdata.file"), 0);
		return excelInputReader.getData("PlusUser", true);
	}
}
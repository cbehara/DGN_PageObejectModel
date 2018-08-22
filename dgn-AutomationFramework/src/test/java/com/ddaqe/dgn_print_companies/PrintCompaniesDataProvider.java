package com.ddaqe.dgn_print_companies;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class PrintCompaniesDataProvider {

	@DataProvider(name = "PlatinumUser")
	public static Object[][] PlatinumUser() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Print_Companies.testdata.file"), 0);
		return excelInputReader.getData("PlatinumUser", true);
	}

}

package com.ddaqe.dgn_company_dashboard;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyDashboardDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Dashboard.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}

	@DataProvider(name = "TCNonAdCommon")
	public static Object[][] TCNonAdCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Dashboard.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdCommon", true);
	}
}

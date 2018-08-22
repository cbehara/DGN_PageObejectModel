package com.ddaqe.dgn_company_projects;
import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyProjectsDataProvider {

	@DataProvider(name = "CompanyProjectsCommonDataProvider")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Projects.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}	
	@DataProvider(name = "TC1345")
	public static Object[][] tc1345() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Projects.testdata.file"), 0);
		return excelInputReader.getData("TC1345", true);
	}
}

	



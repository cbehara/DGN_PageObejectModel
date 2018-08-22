package com.ddaqe.dgn_company_saved_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanySavedSearchDataProvider {

	
	@DataProvider(name = "DGNProjectSavedSearchDataProviderCommon")
	public static Object[][] DGNSavedSearchCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
	
	@DataProvider(name = "DGNProjectSavedSearchDataProviderCommonNonAdmin")
	public static Object[][] DGNSavedSearchCommonNonAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommonNonAdmin", true);
	}
	
	@DataProvider(name = "DGNProjectSavedSearchDataProviderWithNoSS")
	public static Object[][] DGNSavedSearchCommonAdminNoSS() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommonNoSS", true);
	}
	
	
	@DataProvider(name = "DGNProjectSavedSearchDataProvidertc1789")
	public static Object[][] DGNSavedSearchtc1789() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("tc1789", true);
	}
	

	@DataProvider(name = "DGNProjectSavedSearchDataProvidertcAdmin")
	public static Object[][] Admin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Saved_Search.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
	
	
	
	
}

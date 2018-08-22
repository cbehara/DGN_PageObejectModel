package com.ddaqe.dgn_docu_pro;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DocuPro_DataProvider {
	
	@DataProvider(name = "Common_DocuProUser")
	public static Object[][] Common_DocuProUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DocuPro.testdata.file"), 0);
		return excelInputReader.getData("Common_DocuProUser", true);
	}
	
	@DataProvider(name = "NonDocuProUser")
	public static Object[][] NonDocuProUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DocuPro.testdata.file"), 0);
		return excelInputReader.getData("NonDocuProUser", true);
	}
	
	@DataProvider(name = "MultipleUsers")
	public static Object[][] MultipleUsersDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DocuPro.testdata.file"), 0);
		return excelInputReader.getData("MultipleUsers", true);
	}

}

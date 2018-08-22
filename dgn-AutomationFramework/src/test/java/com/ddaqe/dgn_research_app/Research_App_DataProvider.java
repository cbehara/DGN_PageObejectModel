package com.ddaqe.dgn_research_app;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class Research_App_DataProvider {
	
	@DataProvider(name = "Common_ResearchApp")
	public static Object[][] Common_ResearchAppDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ResearchApp.testdata.file"), 0);
		return excelInputReader.getData("Common_ResearchAppUser", true);
	}

	@DataProvider(name = "MultipleUsers")
	public static Object[][] MultipleUsersDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ResearchApp.testdata.file"), 0);
		return excelInputReader.getData("MultipleUsers", true);
	}
}

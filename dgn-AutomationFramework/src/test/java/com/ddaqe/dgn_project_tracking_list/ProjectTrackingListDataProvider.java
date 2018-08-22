package com.ddaqe.dgn_project_tracking_list;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ProjectTrackingListDataProvider {
	@DataProvider(name = "ProjectTrackingListProviderCommon")
	public static Object[][] commonplatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}	
	
	
	@DataProvider(name = "ProjectTrackingListProviderSpecAlert")
	public static Object[][] commonspecalert() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Tracking_List.testdata.file"), 0);
		return excelInputReader.getData("TCCommonSpecAlertUser", true);
	}	
	
	
}

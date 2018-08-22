package com.ddaqe.dgn_takeoff_projects;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNTakeoffProjectsDataProvider {
	
	@DataProvider(name = "ComPlatinumUserDataProvider")
	public static Object[][] commonPlatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGNPublicPrivateShare.testdata.file"), 0);
		return excelInputReader.getData("TcPLATI", true);
	}
}

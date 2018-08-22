package com.ddaqe.dgn_project_profile;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNProjectProfileDataProvider {

	@DataProvider(name = "PlatinumAdmin")
	public static Object[][] PlatinumAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Profile.testdata.file"), 0);
		return excelInputReader.getData("PlatinumAdmin", true);
	}

	@DataProvider(name = "PlatinumAdminBoth")
	public static Object[][] PlatinumAdminBoth() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Profile.testdata.file"), 0);
		return excelInputReader.getData("PlatinumAdminBoth", true);
	}

}

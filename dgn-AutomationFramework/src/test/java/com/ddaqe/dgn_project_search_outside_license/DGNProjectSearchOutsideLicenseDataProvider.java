package com.ddaqe.dgn_project_search_outside_license;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNProjectSearchOutsideLicenseDataProvider {

	@DataProvider(name = "TCOutOfSubscription")
	public static Object[][] TCOutOfSubscription() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_ProjectSearch_OutsideLicense.testdata.file"), 0);
		return excelInputReader.getData("TCOutOfSubscription", true);
	}

}

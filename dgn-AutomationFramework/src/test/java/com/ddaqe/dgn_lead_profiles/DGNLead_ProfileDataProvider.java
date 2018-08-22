package com.ddaqe.dgn_lead_profiles;
import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNLead_ProfileDataProvider {
	
	@DataProvider(name = "Lead_ProfileCommonDataProvider")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Lead_Profile.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
}



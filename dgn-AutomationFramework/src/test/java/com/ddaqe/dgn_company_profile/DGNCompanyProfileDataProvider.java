package com.ddaqe.dgn_company_profile;
import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyProfileDataProvider {
	
	@DataProvider(name = "DGNCompanyProfileDataProviderCommon")
	public static Object[][] DGNSearchCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Company_Profile.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

}

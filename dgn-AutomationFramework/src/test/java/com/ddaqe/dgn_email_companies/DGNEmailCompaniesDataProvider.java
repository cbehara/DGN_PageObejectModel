package com.ddaqe.dgn_email_companies;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNEmailCompaniesDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Email_Projects.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}
	
	@DataProvider(name = "DGNT1317")
	public static Object[][] tcDGNT1317() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Email_Companies.testdata.file"), 0);
		return excelInputReader.getData("DGNT1317", true);
	}

}

package com.ddaqe.dgn_email_alerts;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNEmailAlertsDataProvider {

	@DataProvider(name = "TCChangePwd")
	public static Object[][] TCChangePwd() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Email_Alerts.testdata.file"), 0);
		return excelInputReader.getData("TCChangePwd", true);
	}
	
	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Email_Alerts.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}

}

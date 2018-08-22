package com.ddaqe.dgn_specalerts_emails;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNSpecAlertsEmailsDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts_Emails.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}
}

package com.ddaqe.dgn_company_trackinglist_emails;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCompanyTrackingListEmailsDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CompanyTrackingList_Emails.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}

}

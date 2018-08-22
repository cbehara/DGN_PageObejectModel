package com.ddaqe.dgn_email_projects;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNEmailProjectsDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Email_Projects.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}

}

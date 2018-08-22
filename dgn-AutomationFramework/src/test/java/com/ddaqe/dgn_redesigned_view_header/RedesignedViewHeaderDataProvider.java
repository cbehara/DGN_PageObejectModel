package com.ddaqe.dgn_redesigned_view_header;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class RedesignedViewHeaderDataProvider {
	
	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Redesigned_View_Header.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}
	
}

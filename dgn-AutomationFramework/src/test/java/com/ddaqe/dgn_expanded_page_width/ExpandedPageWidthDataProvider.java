package com.ddaqe.dgn_expanded_page_width;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ExpandedPageWidthDataProvider {

	@DataProvider(name = "TCNonAdEmail")
	public static Object[][] TCNonAdEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Expanded_Page_Width.testdata.file"), 0);
		return excelInputReader.getData("TCNonAdEmail", true);
	}

}

package com.ddaqe.dgn_usage_tracking;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGN_Usage_Tracking_Data_Provider {
	@DataProvider(name = "DownloadDocsDataProviderCommon")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Documents.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

}

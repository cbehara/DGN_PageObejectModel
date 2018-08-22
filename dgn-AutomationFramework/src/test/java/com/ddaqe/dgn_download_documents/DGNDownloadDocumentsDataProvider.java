package com.ddaqe.dgn_download_documents;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNDownloadDocumentsDataProvider {
	@DataProvider(name = "DownloadDocsDataProviderCommon")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Download_Documents.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}	
	
	

	
	
}

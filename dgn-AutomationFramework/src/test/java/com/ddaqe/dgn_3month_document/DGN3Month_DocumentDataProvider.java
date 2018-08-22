package com.ddaqe.dgn_3month_document;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGN3Month_DocumentDataProvider {
	
	@DataProvider(name = "3MonthDocumentCommonDataProvider")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN3Month_Document.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
}

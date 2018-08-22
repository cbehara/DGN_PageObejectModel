package com.ddaqe.dgn_notes;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class NotesDataProvider {
	
	@DataProvider(name = "NotesDataProviderTestData")
	public static Object[][] commontestdata() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Notes.testdata.file"), 0);
		return excelInputReader.getData("TCTestData", true);
	}
	@DataProvider(name = "NotesDataProviderCommon")
	public static Object[][] commonplatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Notes.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}	
	
	@DataProvider(name = "NotesDataProviderPlusUser")
	public static Object[][] commonplus() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Notes.testdata.file"), 0);
		return excelInputReader.getData("TCCommonPlusUser", true);
	}	
}

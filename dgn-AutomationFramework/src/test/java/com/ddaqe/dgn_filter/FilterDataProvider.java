package com.ddaqe.dgn_filter;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class FilterDataProvider {

	@DataProvider(name = "TCPlatiAdmin")
	public static Object[][] TCPlatiAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Filter.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdmin", true);
	}

	@DataProvider(name = "TCBid")
	public static Object[][] TCBid() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Filter.testdata.file"), 0);
		return excelInputReader.getData("TCBid", true);
	}

	@DataProvider(name = "TCFilters")
	public static Object[][] TCFilters() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Filter.testdata.file"), 0);
		return excelInputReader.getData("TCFilters", true);
	}

	@DataProvider(name = "TCConstruction")
	public static Object[][] TCConstruction() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Filter.testdata.file"), 0);
		return excelInputReader.getData("TCConstruction", true);
	}

}

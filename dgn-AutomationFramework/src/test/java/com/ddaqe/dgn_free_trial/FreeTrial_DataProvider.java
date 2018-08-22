package com.ddaqe.dgn_free_trial;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class FreeTrial_DataProvider {

	@DataProvider(name = "Valid_BidProUser")
	public static Object[][] Valid_BidProUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_FreeTrial.testdata.file"), 0);
		return excelInputReader.getData("Valid_BidProUser", true);
	}
	
	@DataProvider(name = "Expired_DGNUser")
	public static Object[][] Expired_DGNUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_FreeTrial.testdata.file"), 0);
		return excelInputReader.getData("Expired_DGNUser", true);
	}
	
	@DataProvider(name = "Valid_DGNUser")
	public static Object[][] Valid_DGNUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_FreeTrial.testdata.file"), 0);
		return excelInputReader.getData("Valid_DGNUser", true);
	}
	
	@DataProvider(name = "Expired_BidProUser")
	public static Object[][] Expired_BidProUserDataProvider() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_FreeTrial.testdata.file"), 0);
		return excelInputReader.getData("Expired_BidProUser", true);
	}
}

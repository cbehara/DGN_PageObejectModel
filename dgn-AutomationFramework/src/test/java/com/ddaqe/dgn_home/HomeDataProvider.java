package com.ddaqe.dgn_home;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class HomeDataProvider {
	
	@DataProvider(name = "TC519")
	public static Object[][] TC519() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC519", true);
	}
	
	@DataProvider(name = "common")
	public static Object[][] common() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("common", true);
	}
	
	@DataProvider(name = "regionalLic")
	public static Object[][] regionalLic() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("regionalLic", true);
	}
	
	@DataProvider(name = "TC843")
	public static Object[][] tc843() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC843", true);
	}
		
	@DataProvider(name = "TC844")
	public static Object[][] tc844() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC844", true);
	}
	
	@DataProvider(name = "TC849")
	public static Object[][] tc849() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC849", true);
	}
	
	@DataProvider(name = "TC856")
	public static Object[][] tc856() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC856", true);
	}
	
	@DataProvider(name = "TC855")
	public static Object[][] tc855() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC855", true);
	}
	
	@DataProvider(name = "TC873")
	public static Object[][] tc873() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC873", true);
	}
	
	@DataProvider(name = "TC848")
	public static Object[][] tc848() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC848", true);
	}
	
	@DataProvider(name = "TC878")
	public static Object[][] tc878() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC878", true);
	}
	
	@DataProvider(name = "TC871")
	public static Object[][] tc871() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC871", true);
	}
	
	@DataProvider(name = "TC869")
	public static Object[][] tc869() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC869", true);
	}
	
	@DataProvider(name = "TC850")
	public static Object[][] tc850() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC850", true);
	}
	
	@DataProvider(name = "TC852")
	public static Object[][] tc852() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC852", true);
	}
	
	@DataProvider(name = "TC853")
	public static Object[][] tc853() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC853", true);
	}
	
	@DataProvider(name = "TC845")
	public static Object[][] tc845() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Home.testdata.file"), 0);
		return excelInputReader.getData("TC845", true);
	}
}

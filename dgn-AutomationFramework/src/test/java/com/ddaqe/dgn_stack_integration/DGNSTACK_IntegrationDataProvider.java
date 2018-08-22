package com.ddaqe.dgn_stack_integration;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNSTACK_IntegrationDataProvider {
	
	@DataProvider(name = "TC3763")
	public static Object[][] tc3763() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_STACK_Integration.testdata.file"), 0);
		return excelInputReader.getData("TC3763", true);
	}
	
	@DataProvider(name = "TC3766")
	public static Object[][] tc3766() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_STACK_Integration.testdata.file"), 0);
		return excelInputReader.getData("TC3766", true);
	}
	
	@DataProvider(name = "TC3740")
	public static Object[][] tc3740() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_STACK_Integration.testdata.file"), 0);
		return excelInputReader.getData("TC3740", true);
	}
	
	@DataProvider(name = "TC3743")
	public static Object[][] tc3743() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_STACK_Integration.testdata.file"), 0);
		return excelInputReader.getData("TC3743", true);
	}
	
	@DataProvider(name = "DGN-T1515")
	public static Object[][] tcDGNT1515() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_STACK_Integration.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1515", true);
	}
	
}

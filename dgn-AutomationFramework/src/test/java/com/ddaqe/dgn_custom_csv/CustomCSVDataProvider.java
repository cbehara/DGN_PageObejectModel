package com.ddaqe.dgn_custom_csv;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class CustomCSVDataProvider {
	@DataProvider(name = "Custom_CSVCommonDataProvider")
	public static Object[][] common1() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}
	@DataProvider(name = "common")
	public static Object[][] common2() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("common", true);
	}
	
	@DataProvider(name = "DGN-T457")
	public static Object[][] tcDGNT457() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T457", true);
	}
	
	@DataProvider(name = "DGN-T458")
	public static Object[][] tcDGNT458() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T458", true);
	}
	
	@DataProvider(name = "DGN-T460")
	public static Object[][] tcDGNT460() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T460", true);
	}
	
	@DataProvider(name = "DGN-T478")
	public static Object[][] tcDGNT478() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T478", true);
	}
	
	@DataProvider(name = "DGN-T481")
	public static Object[][] tcDGNT481() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T481", true);
	}
	
	@DataProvider(name = "DGN-T464")
	public static Object[][] tcDGNT464() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T464", true);
	}
	@DataProvider(name = "commonplus")
	public static Object[][] commonplus() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("commonplus", true);
	}
	
	@DataProvider(name = "DGN-T420")
	public static Object[][] DGNT420() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T420", true);
	}
	
	@DataProvider(name = "DGN-T421")
	public static Object[][] DGNT421() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T421", true);
	}
	
	@DataProvider(name = "DGN-T423")
	public static Object[][] DGNT423() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T423", true);
	}
	
	@DataProvider(name = "DGN-T426")
	public static Object[][] DGNT426() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T426", true);
	}
	
	@DataProvider(name = "DGN-T427")
	public static Object[][] DGNT427() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T427", true);
	}
	
	@DataProvider(name = "DGN-T430")
	public static Object[][] DGNT430() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T430", true);
	}
	
	@DataProvider(name = "DGN-T443")
	public static Object[][] DGNT443() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T443", true);
	}
	
	@DataProvider(name = "DGN-T450")
	public static Object[][] DGNT450() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("DGN-T450", true);
	}
	
	@DataProvider(name = "TC524")
	public static String[][] tc524() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC524", true);
	}
	
	@DataProvider(name = "TC534")
	public static Object[][] tc534() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC534", true);
	}
	@DataProvider(name = "TC535")
	public static Object[][] tc535() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC535", true);
	}
	@DataProvider(name = "TC536")
	public static Object[][] tc536() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC536", true);
	}
	@DataProvider(name = "TC544")
	public static Object[][] tc544() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC544", true);
	}
	@DataProvider(name = "TC545")
	public static Object[][] tc545() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC545", true);
	}
	@DataProvider(name = "TC546")
	public static Object[][] tc546() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC546", true);
	}

	@DataProvider(name = "TC547")
	public static Object[][] tc547() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC547", true);
	}
	@DataProvider(name = "TC571")
	public static Object[][] tc571() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Custom_CSV.testdata.file"), 0);
		return excelInputReader.getData("TC571", true);
	}

}

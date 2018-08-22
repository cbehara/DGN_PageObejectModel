package com.ddaqe.dgn_specalerts;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class SpecAlertsDataProvider {

	@DataProvider(name = "TCPlusNonSpecAlerts")
	public static Object[][] TCPlusNonSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCPlusNonSpecAlerts", true);
	}

	@DataProvider(name = "TCIntSpecAlerts")
	public static Object[][] TCIntSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlerts", true);
	}

	@DataProvider(name = "TCIntNonSpecAlerts")
	public static Object[][] TCIntNonSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntNonSpecAlerts", true);
	}

	@DataProvider(name = "TCIntSpecAlertsDR")
	public static Object[][] TCIntSpecAlertsDR() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlertsDR", true);
	}
	
	@DataProvider(name = "TCIntSpecAlertsDRMohawk")
	public static Object[][] TCIntSpecAlertsDRMohawk() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlertsDRMohawk", true);
	}
	
	@DataProvider(name = "TCIntSpecAlertsLatestBuild")
	public static Object[][] TCIntSpecAlertsLatestBuild() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlertsLatestBuild", true);
	}

	@DataProvider(name = "TCIntSpecAlertsEmail")
	public static Object[][] TCIntSpecAlertsEmail() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlertsEmail", true);
	}

	@DataProvider(name = "TCIntSpecAlertsDateRange")
	public static Object[][] TCIntSpecAlertsDateRange() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCIntSpecAlertsDateRange", true);
	}

	@DataProvider(name = "TCPlatinumSpecAlerts")
	public static Object[][] TCPlatinumSpecAlerts() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_SpecAlerts.testdata.file"), 0);
		return excelInputReader.getData("TCPlatinumSpecAlerts", true);
	}

}

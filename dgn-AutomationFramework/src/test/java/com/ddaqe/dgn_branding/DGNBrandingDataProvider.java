package com.ddaqe.dgn_branding;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNBrandingDataProvider {
	
	@DataProvider(name = "ComPlatinumUserDataProvider")
	public static Object[][] commonPlatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGNPublicPrivateShare.testdata.file"), 0);
		return excelInputReader.getData("TcPLATI", true);
	}
	
	@DataProvider(name = "DGNT3230")
	public static Object[][] tcDGNT3230() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Branding.testdata.file"), 0);
		return excelInputReader.getData("DGNT3230", true);
	}
	
	@DataProvider(name = "LicenseEntry")
	public static Object[][] tcLicenseEntry() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Branding.testdata.file"), 0);
		return excelInputReader.getData("LicenseEntry", true);
	}
	
	@DataProvider(name = "TermAndConditionPage")
	public static Object[][] tcTermAndConditionPage() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Branding.testdata.file"), 0);
		return excelInputReader.getData("TermAndConditionPage", true);
	}
	
	@DataProvider(name = "AccessPending")
	public static Object[][] tcAccessPending() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Branding.testdata.file"), 0);
		return excelInputReader.getData("AccessPending", true);
	}
	
	@DataProvider(name = "PlatAdmin")
	public static Object[][] tcPlatAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Branding.testdata.file"), 0);
		return excelInputReader.getData("PlatAdmin", true);
	}
}

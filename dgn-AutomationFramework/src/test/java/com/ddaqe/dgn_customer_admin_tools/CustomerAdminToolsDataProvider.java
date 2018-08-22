package com.ddaqe.dgn_customer_admin_tools;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class CustomerAdminToolsDataProvider {

	@DataProvider(name = "TCPlatiAdminLicenseWithoutAccept")
	public static Object[][] TCPlatiAdminLicenseWithoutAccept() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminLicenseWithoutAccept", true);
	}

	@DataProvider(name = "TCPlatiAdminLicenseWithAccept")
	public static Object[][] TCPlatiAdminLicenseWithAccept() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminLicenseWithAccept", true);
	}

	@DataProvider(name = "TCPlatiAdminLicense")
	public static Object[][] TCPlatiAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminLicense", true);
	}

	@DataProvider(name = "TCPlatiAdminNonAdminLicense")
	public static Object[][] TCPlatiAdminNonAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminNonAdminLicense", true);
	}

	@DataProvider(name = "TCPlatiChildAdminLicense")
	public static Object[][] TCPlatiChildAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiChildAdminLicense", true);
	}

	@DataProvider(name = "TCPlatiChildNonAdminLicense")
	public static Object[][] TCPlatiChildNonAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiChildNonAdminLicense", true);
	}

	@DataProvider(name = "TCPlatiChildPendingLicense")
	public static Object[][] TCPlatiChildPendingLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiChildPendingLicense", true);
	}

	@DataProvider(name = "TCPlatiNonAdminLicense")
	public static Object[][] TCPlatiNonAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiNonAdminLicense", true);
	}

	@DataProvider(name = "TCPlusLicense")
	public static Object[][] TCPlusLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlusLicense", true);
	}

	@DataProvider(name = "TCNonPlatiChildAdminLicense")
	public static Object[][] TCNonPlatiChildAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCNonPlatiChildAdminLicense", true);
	}

	@DataProvider(name = "TCPlatiChildProfileLicense")
	public static Object[][] TCPlatiChildProfileLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiChildProfileLicense", true);
	}

	@DataProvider(name = "TCPlatiAdminAuto2License")
	public static Object[][] TCPlatiAdminAuto2License() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_CustomerAdminTools.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminAuto2License", true);
	}
}

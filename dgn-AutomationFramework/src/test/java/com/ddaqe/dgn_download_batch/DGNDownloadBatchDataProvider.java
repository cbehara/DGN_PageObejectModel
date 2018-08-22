package com.ddaqe.dgn_download_batch;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNDownloadBatchDataProvider {
	@DataProvider(name = "DGNDownLoadBatchDataProviderNoDownLoad")
	public static Object[][] DGNTrackingListNoDownLoad() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TCCommonNoDownloadUser", true);
	}

	@DataProvider(name = "DGNDownLoadBatchDataProviderCommon")
	public static Object[][] DGNTrackingListCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

	@DataProvider(name = "TC202")
	public static Object[][] tc202() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC202", true);
	}

	
	@DataProvider(name = "TC216")
	public static Object[][] tc216() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC216", true);
	}
	
	@DataProvider(name = "TC217")
	public static Object[][] tc217() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC217", true);
	}
	
	@DataProvider(name = "TC218")
	public static Object[][] tc218() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC218", true);
	}
	
	@DataProvider(name = "TC222")
	public static Object[][] tc222() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC222", true);
	}
	
	@DataProvider(name = "TC254")
	public static Object[][] tc254() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC254", true);
	}
	
	@DataProvider(name = "TC229")
	public static Object[][] tc229() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC229", true);
	}
	
	@DataProvider(name = "TC209")
	public static Object[][] tc209() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC209", true);
	}
	
	@DataProvider(name = "TC230")
	public static Object[][] tc230() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DownLoad_Batch.testdata.file"), 0);
		return excelInputReader.getData("TC230", true);
	}
}

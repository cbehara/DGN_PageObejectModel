package com.ddaqe.dgn_project_documents;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class ProjectDocumentsDataProvider {

	@DataProvider(name = "TCPlatiAdminLicense")
	public static Object[][] TCPlatiAdminLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Documents.testdata.file"), 0);
		return excelInputReader.getData("TCPlatiAdminLicense", true);
	}

	@DataProvider(name = "TCPlusLicense")
	public static Object[][] TCPlusLicense() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Project_Documents.testdata.file"), 0);
		return excelInputReader.getData("TCPlusLicense", true);
	}



}

package com.ddaqe.dgn_maps;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNMapsDataProvider {

	@DataProvider(name = "MapsPlatiAdmin")
	public static Object[][] MapsPlatiAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Maps.testdata.file"), 0);
		return excelInputReader.getData("MapsPlatiAdmin", true);
	}

	
}

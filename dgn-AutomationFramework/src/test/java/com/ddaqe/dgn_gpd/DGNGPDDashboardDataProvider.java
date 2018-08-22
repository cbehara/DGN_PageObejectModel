package com.ddaqe.dgn_gpd;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNGPDDashboardDataProvider {

	@DataProvider(name = "TCPlatinum")
	public static Object[][] TCPlatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_GPD.testdata.file"), 0);
		return excelInputReader.getData("TCPlatinum", true);
	}

}

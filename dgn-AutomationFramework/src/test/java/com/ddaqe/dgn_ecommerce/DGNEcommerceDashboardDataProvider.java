package com.ddaqe.dgn_ecommerce;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNEcommerceDashboardDataProvider {

	@DataProvider(name = "TCOutOfSubscription")
	public static Object[][] TCOutOfSubscription() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Ecommerce.testdata.file"), 0);
		return excelInputReader.getData("TCOutOfSubscription", true);
	}

}

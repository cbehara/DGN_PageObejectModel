package com.ddaqe.dgn_public_private_share;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNPublicPrivateShareDataProvider {
	
	@DataProvider(name = "ComPlatinumUserDataProvider")
	public static Object[][] commonPlatinum() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGNPublicPrivateShare.testdata.file"), 0);
		return excelInputReader.getData("TcPLATI", true);
	}
}

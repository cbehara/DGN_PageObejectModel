package com.ddaqe.dgn_design_alert_save_search;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGN_DesignAlert_saveSearch_DataProvider {

	@DataProvider(name = "CommonDesignAlert_saveSearch")
	public static Object[][] CommonDesignAlert_saveSearch() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_DesignAlert_saveSearch.testdata.file"), 0);
		return excelInputReader.getData("CommonDesignAlert_saveSearch", true);
	}
}	

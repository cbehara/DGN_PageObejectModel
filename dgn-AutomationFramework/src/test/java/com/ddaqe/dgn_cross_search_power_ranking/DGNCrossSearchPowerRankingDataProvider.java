package com.ddaqe.dgn_cross_search_power_ranking;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class DGNCrossSearchPowerRankingDataProvider {

	@DataProvider(name = "DGNCrossSearchPowerRankingDataProviderCommon")
	public static Object[][] DGNSavedSearchCommon() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Cross_Search_Power_Ranking.testdata.file"), 0);
		return excelInputReader.getData("TCCommon", true);
	}

}

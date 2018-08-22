package com.ddaqe.dgn_customer_account_tools;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class CustomerAccountToolsDataProvider {

	@DataProvider(name = "CustAcctTool_Commom_DP")
	public static Object[][] loginModuleTest() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC-NormalUser1", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP2")
	public static Object[][] CustAccountTool1() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC-Common", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP3")
	public static Object[][] CustAccountTool2() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("Plus_User", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP4")
	public static Object[][] CustAccountTool3() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("Non-LicenseUser", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP5")
	public static Object[][] CustAccountTool4() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("ExpiredLicense", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP6")
	public static Object[][] CustAccountTool5() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("InvalidLicense", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP7")
	public static Object[][] CustAccountTool6() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("PLAT_User", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP8")
	public static Object[][] CustAccountTool7() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC-NameFormat", true);
	}

	@DataProvider(name = "CustAcctTool_Commom_DP9")
	public static Object[][] CustAccountTool8() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC-NameFormat-PLAT", true);
	}

	@DataProvider(name = "TC1136")
	public static Object[][] tc1136() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1136", true);
	}

	@DataProvider(name = "TC1146")
	public static Object[][] tc1146() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1146", true);
	}

	@DataProvider(name = "TC1147")
	public static Object[][] tc1147() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1147", true);
	}

	@DataProvider(name = "TC1147_Platinium")
	public static Object[][] tc1147_Platinium() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1147_Platinium", true);
	}

	@DataProvider(name = "TC1151_Platinium")
	public static Object[][] tc1151_Platinium() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1151_Platinium", true);
	}

	@DataProvider(name = "TC1158")
	public static Object[][] tc1158() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1158", true);
	}

	@DataProvider(name = "TC1163")
	public static Object[][] tc1163() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1163", true);
	}

	@DataProvider(name = "TC1164")
	public static Object[][] tc1164() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1164", true);
	}

	@DataProvider(name = "TC1172")
	public static Object[][] tc1172() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1172", true);
	}

	@DataProvider(name = "TC1190")
	public static Object[][] tc1190() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1190", true);
	}

	@DataProvider(name = "TC1203")
	public static Object[][] tc1203() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1203", true);
	}

	@DataProvider(name = "TC1204")
	public static Object[][] tc1204() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1204", true);
	}

	@DataProvider(name = "TC1205")
	public static Object[][] tc1205() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1205", true);
	}

	@DataProvider(name = "TC1206")
	public static Object[][] tc1206() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1206", true);
	}

	@DataProvider(name = "TC1234")
	public static Object[][] tc1234() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1234", true);
	}

	@DataProvider(name = "TC1235")
	public static Object[][] tc1235() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1235", true);
	}

	@DataProvider(name = "TC1237")
	public static Object[][] tc1237() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1237", true);
	}

	@DataProvider(name = "TC1238")
	public static Object[][] tc1238() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1238", true);
	}

	@DataProvider(name = "TC1239")
	public static Object[][] tc1239() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1239", true);
	}

	@DataProvider(name = "TC1251")
	public static Object[][] tc1251() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1251", true);
	}

	@DataProvider(name = "TC1252")
	public static Object[][] tc1252() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1252", true);
	}

	@DataProvider(name = "TC1253")
	public static Object[][] tc1253() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1253", true);
	}

	@DataProvider(name = "TC1254")
	public static Object[][] tc1254() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1254", true);
	}

	@DataProvider(name = "TC1255")
	public static Object[][] tc1255() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1255", true);
	}

	@DataProvider(name = "TC1256")
	public static Object[][] tc1256() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1256", true);
	}

	@DataProvider(name = "TC1257")
	public static Object[][] tc1257() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1257", true);
	}

	@DataProvider(name = "TC1258")
	public static Object[][] tc1258() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1258", true);
	}

	@DataProvider(name = "TC1259")
	public static Object[][] tc1259() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1259", true);
	}

	@DataProvider(name = "TC1260")
	public static Object[][] tc1260() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1260", true);
	}

	@DataProvider(name = "TC1261")
	public static Object[][] tc1261() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1261", true);
	}

	@DataProvider(name = "TC1262")
	public static Object[][] tc1262() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1262", true);
	}

	@DataProvider(name = "TC1263")
	public static Object[][] tc1263() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1263", true);
	}

	@DataProvider(name = "TC1264")
	public static Object[][] tc1264() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1264", true);
	}

	@DataProvider(name = "TC1265")
	public static Object[][] tc1265() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1265", true);
	}

	@DataProvider(name = "TC1266")
	public static Object[][] tc1266() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1266", true);
	}

	@DataProvider(name = "TC1267")
	public static Object[][] tc1267() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1267", true);
	}

	@DataProvider(name = "TC1268")
	public static Object[][] tc1268() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1268", true);
	}
	@DataProvider(name = "TC1269")
	public static Object[][] tc1269() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1269", true);
	}
	
	@DataProvider(name = "TC1270")
	public static Object[][] tc1270() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1270", true);
	}

	@DataProvider(name = "TC1272")
	public static Object[][] tc1272() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1272", true);
	}

	@DataProvider(name = "TC1275")
	public static Object[][] tc1275() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1275", true);
	}

	@DataProvider(name = "TC1276")
	public static Object[][] tc1276() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1276", true);
	}

	@DataProvider(name = "TC1285")
	public static Object[][] tc1285() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1285", true);
	}

	@DataProvider(name = "TC1295")
	public static Object[][] tc1295() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1295", true);
	}

	@DataProvider(name = "TC1296")
	public static Object[][] tc1296() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1296", true);
	}

	@DataProvider(name = "TC1297")
	public static Object[][] tc1297() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1297", true);
	}

	@DataProvider(name = "TC1298")
	public static Object[][] tc1298() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1298", true);
	}

	@DataProvider(name = "TC1240")
	public static Object[][] tc1240() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1240", true);
	}

	@DataProvider(name = "TC1241")
	public static Object[][] tc1241() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1241", true);
	}

	@DataProvider(name = "TC1173")
	public static Object[][] tc1173() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TC1173", true);
	}

	@DataProvider(name = "PrivateTrackName")
	public static Object[][] PrivateTrackName() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("PrivateTrackName", true);
	}

	@DataProvider(name = "PrivateTrackNameNonAdmin")
	public static Object[][] PrivateTrackNameNonAdmin() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("PrivateTrackNameNonAdmin", true);
	}

	@DataProvider(name = "TrackNamePlusUser")
	public static Object[][] TrackNamePlusUser() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("TrackNamePlusUser", true);
	}

	@DataProvider(name = "Platinium_2")
	public static Object[][] platinium_2() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("CustomerAccountTools.testdata.file"), 0);
		return excelInputReader.getData("Platinium_2", true);
	}
}

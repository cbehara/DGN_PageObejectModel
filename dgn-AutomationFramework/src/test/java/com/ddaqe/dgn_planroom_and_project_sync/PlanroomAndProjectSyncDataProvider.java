package com.ddaqe.dgn_planroom_and_project_sync;

import org.testng.annotations.DataProvider;

import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.ExcelInputReader;

public class PlanroomAndProjectSyncDataProvider {
	
	@DataProvider(name = "DGN1137")
	public static Object[][] tcDGN1137() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN1137", true);
	}
	
	@DataProvider(name = "DGNT1178")
	public static Object[][] tcDGN1178() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGNT1178", true);
	}
	
	@DataProvider(name = "DGN-T1145")
	public static Object[][] tcDGNT1145() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1145", true);
	}
	
	@DataProvider(name = "DGNT1179")
	public static Object[][] tcDGN1179() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGNT1179", true);
	}
	
	@DataProvider(name = "DGN-T1141")
	public static Object[][] tcDGNT1141() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1141", true);
	}
	
	@DataProvider(name = "DGN-T1142")
	public static Object[][] tcDGNT1142() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1142", true);
	}
	
	@DataProvider(name = "DGN-T1138")
	public static Object[][] tcDGNT1138() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1138", true);
	}
	
	@DataProvider(name = "DGN-T1139")
	public static Object[][] tcDGNT1139() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1139", true);
	}
	
	@DataProvider(name = "DGN-T1157")
	public static Object[][] tcDGNT1157() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1157", true);
	}
	
	@DataProvider(name = "DGN-T1160")
	public static Object[][] tcDGNT1160() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1160", true);
	}
	
	@DataProvider(name = "DGN-T1159")
	public static Object[][] tcDGNT1159() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T1159", true);
	}
	
	@DataProvider(name = "DGN-T3318")
	public static Object[][] tcDGNT3318() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3318", true);
	}
		
	@DataProvider(name = "DGN-T3356")
	public static Object[][] tcDGNT3356() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3356", true);
	}
	
	@DataProvider(name = "DGN-T3355")
	public static Object[][] tcDGNT3355() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3355", true);
	}
	
	@DataProvider(name = "DGN-T3364")
	public static Object[][] tcDGNT3364() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3364", true);
	}
	
	@DataProvider(name = "DGN-T3365")
	public static Object[][] tcDGNT3365() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3365", true);
	}
	
	@DataProvider(name = "DGN-T3345")
	public static Object[][] tcDGNT3345() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3345", true);
	}
	
	@DataProvider(name = "DGN-T3353")
	public static Object[][] tcDGNT3353() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3353", true);
	}
	
	@DataProvider(name = "DGN-T3357")
	public static Object[][] tcDGNT3357() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3357", true);
	}
	
	@DataProvider(name = "DGN-T3359")
	public static Object[][] tcDGNT3359() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3359", true);
	}
	
	@DataProvider(name = "NoPRP")
	public static Object[][] tcNoPRP() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("NoPRP", true);
	}
	
	@DataProvider(name = "NoPRP_DGNLicenseWithPRP")
	public static Object[][] tcNoPRP_DGNLicenseWithPRP() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("NoPRP_DGNLicenseWithPRP", true);
	}
	
	@DataProvider(name = "PRPAccess_LicenseToDiffPRP")
	public static Object[][] tcPRPAccess_LicenseToDiffPRP() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("PRPAccess_LicenseToDiffPRP", true);
	}
	
	@DataProvider(name = "DGN-T3349")
	public static Object[][] tcDGNT3349() throws Exception {
		ExcelInputReader excelInputReader = new ExcelInputReader(
				ConfigurationReader.getInstance().getProperty("DGN_Planroom_And_Project_Sync.testdata.file"), 0);
		return excelInputReader.getData("DGN-T3349", true);
	}
}

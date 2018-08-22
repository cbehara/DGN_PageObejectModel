package com.ddaqe.dgn_usage_tracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.AdminReportsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.utils.CSVReader;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DatabaseUtils;

@Listeners(TestListener.class)

public class DGNUsageTracking2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNUsageTracking2.class);

	@Test(description = "DB test")
	public void tc1858()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {
		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		String Query = "Select * from uao_user_action_other ua where ua.app_code='PNP'and ua.action_type like '%PROJECT_SEARCH%'and ua.asset_type like '%TARGET%' and ua.asset_value is not null and trunc(ua.action_timestamp) >= '09-Aug-2014'order by ua.action_timestamp desc";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				if (i > 1)

					if (rsmd.getColumnName(i).equals("ASSET_VALUE"))
						resultset = resultset + rs.getString(i);

			}
		}
		Assert.assertTrue(resultset.length() > 0);

		connection.close();
	}

	@Test(description = "DB test")
	public void tc1857()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();

		String Query = "select * from uat_user_action uat where UAT_SYS_id= '1192759' order by uat.action_timestamp desc";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				if (i > 1)

					if (rsmd.getColumnName(i).equals("SOURCE_URL"))
						resultset = resultset + rs.getString(i);

			}
		}
		Assert.assertTrue(resultset.contains("http://qaprod.network.construction.com"));

		connection.close();
	}

	@Test(description = "DB test")
	public void tc1870()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();

		String Query = "SELECT UAA.SSO_ID,UAA.CREATE_TIMESTAMP,UAA.ACTION_TYPE,UAA.ASSET_TYPE,UAA.ASSET_ID,UAA.FILE_TYPE,UAA.FILE_FORMAT,UAA.FILE_NAME,uaa.SOURCE_TYPE FROM UAA_USER_ACT_CON_ACCESS UAA WHERE UAA.APP_CODE = 'PNP' AND UAA.SSO_ID = '1192759' ORDER BY UAA.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				if (i > 1)

					if (rsmd.getColumnName(i).equals("SOURCE_TYPE"))
						resultset = resultset + rs.getString(i);

			}
		}
		Assert.assertTrue(resultset.contains("PROJECT-DODGE-REPORT-MUTLIPLE"));

		connection.close();
	}

	@Test(description = "DB test")
	public void tc1860()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {
		Connection connection = DatabaseUtils.establishCUSQDatabaseConnection();
		String Query = "select * from acd_app_config_detail acd where acd.app_env = 'QA' and acd.app_id = 'PNP_WEB' and acd.config_group = 'DODGE_API'";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				if (i > 1)

					if (rsmd.getColumnName(i).equals("APP_ID"))
						resultset = resultset + rs.getString(i);

			}
		}
		Assert.assertTrue(resultset.contains("PNP_WEB"));

		connection.close();
	}

	@Test(description = "DB test")
	public void tc1859()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM UAT_USER_ACTION UAT WHERE UAT.APP_CODE = 'PNP' AND UAT.SSO_ID = '1192759' ORDER BY UAT.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		String resultset = "";

		while (rs.next()) {

			resultset = resultset + rs.getString(6);

			if (resultset.contains("SIGN-IN") && resultset.contains("SIGN-OUT")) {
				bool = true;
				break;
			}

		}

		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1863_1864()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM UAT_USER_ACTION UAT WHERE UAT.APP_CODE = 'PNP' AND UAT.SSO_ID = '1192759' ORDER BY UAT.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(6);
			if (resultset.contains("PROJECT-SEARCH") || resultset.contains("COMPANY-SEARCH")) {
				bool = true;
				break;
			}

		}

		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1865_1866()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM UAO_USER_ACTION_OTHER UAO WHERE UAO.APP_CODE = 'PNP' and UAO.ASSET_TYPE = 'EDIT-FILTER-PAGE' and UAO.SSO_ID = '1244660' ORDER BY UAO.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(11);
			if (resultset.contains("PROJECT-SEARCH") || resultset.contains("COMPANY-SEARCH")) {
				bool = true;
				break;
			}

		}

		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1849()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM UAT_USER_ACTION UAT WHERE UAT.APP_CODE = 'PNP' AND UAT.ACTION_TYPE = 'DOWNLOAD' AND UAT.SSO_ID = '1192759' ORDER BY UAT.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(6);
			if (resultset.contains("DOWNLOAD")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1846()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) WHERE APP_CODE = 'PNP' AND SSO_ID = '1305856' AND ACTION_TYPE = 'ECOMMERCE' AND TRUNC (CREATE_TIMESTAMP) = TRUNC (SYSDATE) ORDER BY CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(5) + rs.getString(6);
			if (resultset.contains("PURCHASE-TYPE") && resultset.contains("DIGITAL")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1844()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "SELECT * FROM ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) UAO WHERE UAO.APP_CODE = 'PNP' AND UAO.SSO_ID = '1211957'AND Action_type = 'APPROVE-USER' AND ASSET_VALUE ='SINGLE' ORDER BY UAO.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(6) + rs.getString(11);
			if (resultset.contains("APPROVE-USER") && resultset.contains("SINGLE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1868()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select ua.sso_id,ua.asset_type,ua.asset_value,ua.action_type,ua.action_timestamp,ua.umd_sys_id from uao_user_action_other ua where ua.action_type like '%SEARCH%' and ua.sso_id='1211957' order by ua.action_timestamp desc";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(2);
			if (resultset.contains("SEARCH-RESULTS")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);

	}

	@Test(description = "DB test")
	public void tc1861()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select *from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) ua where ua.umd_sys_id in (select ua1.umd_sys_id from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) ua1 where ua1.action_type like '%FATAL%' and ua1.sso_id = '1211957') order by ua.action_timestamp desc";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(5);
			if (resultset.contains("METHOD") && resultset.contains("STACK-TRACE") && resultset.contains("REFERRAL-URI")
					&& resultset.contains("ERROR-DESC")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1813()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1211960' AND ACTION_TYPE = 'SAVED-SEARCHES' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2) + rs.getString(3);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PUBLIC")
					&& resultset.contains("SAVED-SEARCHES")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1818()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1211960' AND ACTION_TYPE = 'SAVED-SEARCHES' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2) + rs.getString(3);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PUBLIC")
					&& resultset.contains("SAVED-SEARCHES")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1816()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1211960' AND ACTION_TYPE = 'SAVED-SEARCHES' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2) + rs.getString(3);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("SHARED")
					&& resultset.contains("SAVED-SEARCHES")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1819()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1211960' AND ACTION_TYPE = 'SAVED-SEARCHES' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2) + rs.getString(3);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PRIVATE")
					&& resultset.contains("SAVED-SEARCHES")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1814()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1211960' AND ACTION_TYPE = 'SAVED-SEARCHES' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2) + rs.getString(3);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("UPDATE")
					&& resultset.contains("SAVED-SEARCHES")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1834()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(3);
			if (resultset.contains("TRACKING-LIST-ID") && resultset.contains("TRACKING-LIST")
					&& rs.getString(2).length() > 0) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1843()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("USER-ACTION") && resultset.contains("UPDATE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1841()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("PRIVATE") && resultset.contains("UPDATE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1836()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PUBLIC")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1835()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("UPDATE") && resultset.contains("TRACKING-LIST")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1833()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("USER-ACTION") && resultset.contains("DELETE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1826()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("USER-ACTION") && resultset.contains("UPDATE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1824()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PRIVATE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1830()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("PUBLIC")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1828_1837()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("SHARE-ACCESS-TYPE") && resultset.contains("SHARED")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1827()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("USER-ACTION") && resultset.contains("UPDATE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1840()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {

		Connection connection = DatabaseUtils.establishCDWQDatabaseConnection();
		Boolean bool = false;

		String Query = "select uao.asset_type, uao.asset_value, uao.action_type from ulog_db.UAO_USER_ACTION_OTHER PARTITION (SYS_P5333) uao  where uao.SSO_ID = '1440574' AND ACTION_TYPE = 'TRACKING-LIST' order by uao.CREATE_TIMESTAMP DESC";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";

		while (rs.next()) {
			resultset = resultset + rs.getString(1) + rs.getString(2);
			if (resultset.contains("USER-ACTION") && resultset.contains("DELETE")) {
				bool = true;
				break;
			}
		}
		Assert.assertTrue(bool);
	}

	@Test(description = "DB test")
	public void tc1823()
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {
		Connection connection = DatabaseUtils.establishCUSQDatabaseConnection();
		String Query = "select ss.search_spec_group_file, ss.auto_search_text, ss.share_access_type, ss.saved_search_delete_ind, ss.* from sch_search ss where ss.saved_search_name = 'Savesearch1'";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);

		int rows = rs.getRow();
		Assert.assertTrue(rows == 0);

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DGN_Usage_Tracking_Data_Provider.class, dataProvider = "DownloadDocsDataProviderCommon", description = "Update list view summary area to allow user to turn alerts on/off (TC2243)")
	public void tc1867_1845(String emailAddress, String password)
			throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		HomePage homePage = loginAs(emailAddress, password);
		homePage.clickOnMyAccountLink();
		AdminReportsPage adminPage = homePage.clickOnReportsLink();
		adminPage.switchToFrame();
		adminPage.clickReportOptions("System Usage");
		adminPage.clickOnUsersDrop();
		adminPage.checkFirstUser();
		adminPage.clickRunButton();
		File downloadedFile = CommonUtils.getLatestDownloadedFile("");
		CSVReader csvReader = new CSVReader(downloadedFile);
		Assert.assertTrue(csvReader != null);
	}
}

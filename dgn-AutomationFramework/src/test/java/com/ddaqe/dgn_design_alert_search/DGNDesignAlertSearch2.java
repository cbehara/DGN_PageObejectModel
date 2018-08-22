package com.ddaqe.dgn_design_alert_search;

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
import com.ddaqe.utils.DatabaseUtils;

@Listeners(TestListener.class)

public class DGNDesignAlertSearch2 extends BaseTest {

	static Logger log = Logger.getLogger(DGNDesignAlertSearch2.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DesignAlertSearchDataProvider.class, dataProvider = "common", description = "Verify the product rate code for Only DGN users (TC22936).")
	public void tc3060(String emailAddress, String password)
			throws ClassNotFoundException, SQLException, FileNotFoundException, InterruptedException, IOException {
		Connection connection = DatabaseUtils.establishCUSQDatabaseConnection();
		String Query = "select * from lix_license_xml lix where lix.license_nbr='391341532727411'";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		String resultset = "";
		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				if (i > 1)
					if (rsmd.getColumnName(i).equals("HORIZON_PRODUCT_ABBRV"))
						resultset = resultset + rs.getString(i);
			}
		}
		Assert.assertTrue(resultset.contains("MEDLEAD"),
				"User doesn't get the product code 'MEDLEAD' in the LIX table.");
		connection.close();
	}
}
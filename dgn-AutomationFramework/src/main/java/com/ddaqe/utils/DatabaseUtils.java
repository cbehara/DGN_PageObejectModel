package com.ddaqe.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.ddaqe.constants.DGNDatabaseConstant;
import com.ddaqe.utils.DBUtils;

/**
 * The Class DBUtils.
 */
public class DatabaseUtils {

	/**
	 * This method establishes connection with CDWQ database.
	 * @return Object Connection
	 * @throws SQLException SQL Exception
	 */
	public static Connection establishCDWQDatabaseConnection() throws SQLException {
		return DBUtils.establishDatabaseConnection(DGNDatabaseConstant.CDWQ_CONNECTION_STRING,
				DGNDatabaseConstant.CDW_READ_USER, DGNDatabaseConstant.CDW_READ_USER_PASSWORD);
	}

	/**
	 * This method establishes connection with CUSQ database.
	 * @return Object Connection
	 * @throws SQLException SQL Exception
	 */
	public static Connection establishCUSQDatabaseConnection() throws SQLException {
		return DBUtils.establishDatabaseConnection(DGNDatabaseConstant.CUSQ_CONNECTION_STRING,
				DGNDatabaseConstant.CUS_READ_USER, DGNDatabaseConstant.CUS_READ_USER_PASSWORD);
	}
}

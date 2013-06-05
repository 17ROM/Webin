package com.webin.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RobotDatabase {
	private static RobotDatabase _inst = null;
	private InitialContext mInitContext = null;
	private DataSource mDataSource = null;
	private Connection mConnection = null;

	private RobotDatabase() {
		try {
			mInitContext = new InitialContext();
			mDataSource = (DataSource) mInitContext.lookup("java:comp/env/jdbc/WebinRobotDb");
			mConnection = mDataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static RobotDatabase getDefault() {
		if (_inst == null) {
			_inst = new RobotDatabase();
		}
		return _inst;
	}
	
	public int executeUpdate(String code, String replay) {
		int result = -1;
		String sql = "UPDATE WebinRobot SET _reply0 = +'" + replay + "' WHERE _code = '" + code + "'";
		try {
			Statement statement =  mConnection.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean executeInsert(String code, String replay) {
		boolean result = false;
		String sql = "INSERT INTO WebinRobot "
				+ "(_id, _code, _weight, _reply0) "
				+ "VALUES (NULL,'"
				+ code + "',"
				+ "1, '"
				+ replay + "')";
		try {
			Statement statement = mConnection.createStatement();
			result = statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet executeQuery(String code) {
		ResultSet result = null;
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			PreparedStatement statement = mConnection.prepareStatement(sql);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void release() {
		if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mConnection = null;
		}
	}

}

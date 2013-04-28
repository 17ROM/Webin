package com.webin.core.robot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RobotDatabase {
	private static RobotDatabase _inst = null;
	private InitialContext mInitContext = null;
	private DataSource mDataSource = null;
	private Connection mConnection = null;
	private PreparedStatement mStatement = null;

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
	
	/*
	WebinRobot _id _code _weight _reply0
	*/

	public ResultSet executeQuery(String code) {
		ResultSet result = null;
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			mStatement = mConnection.prepareStatement(sql);
			result = mStatement.executeQuery();
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
		if (mStatement != null) {
			try {
				mStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mStatement = null;
		}
	}

}

package com.webin.core.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

import com.sina.sae.util.SaeUserInfo;

public class RobotDatabase {
	private static RobotDatabase _inst = null;
	private Connection mConnection = null;

	private RobotDatabase() {
	}
	
	public Connection getConnection(){
		Connection connect = null;
		String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_webin";
		String username = SaeUserInfo.getAccessKey();
		String password = SaeUserInfo.getSecretKey();
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setMaxActive(14);
		bds.setMaxWait(3000);
		try {
			connect = bds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mConnection = connect;
		return connect;
	}
	
	public void freeConnection(){
		if (mConnection != null){
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			Statement statement =  getConnection().createStatement();
			result = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		freeConnection();
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
			Statement statement = getConnection().createStatement();
			result = statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		freeConnection();
		return result;
	}
	
	public boolean executeQuery(String code) {
		boolean result = false;
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			Statement statement = getConnection().createStatement();
			ResultSet query = statement.executeQuery(sql);
			result = query.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		freeConnection();
		return result;
	}
	
	public String executeQueryResult(String code) {
		String result = null;
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			Statement statement = getConnection().createStatement();
			ResultSet query = statement.executeQuery(sql);
			if (query.first()){
				int weight = query.getInt(3);
				result = query.getString(3 + weight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		freeConnection();
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
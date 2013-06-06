package com.webin.core.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOperate extends DbConnectManager {
	public int executeUpdate(String code, String replay) {
		Connection conn = getConnection();
		int result = -1;
		String sql = "UPDATE WebinRobot SET _reply0 = +'" + replay + "' WHERE _code = '" + code + "'";
		try {
			Statement statement =  conn.createStatement();
			result = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		freeConnection(conn);
		return result;
	}
	
	public boolean executeInsert(String code, String replay) {
		boolean result = false;
		Connection conn = getConnection();
		String sql = "INSERT INTO WebinRobot "
				+ "(_id, _code, _weight, _reply0) "
				+ "VALUES (NULL,'"
				+ code + "',"
				+ "1, '"
				+ replay + "')";
		try {
			Statement statement = conn.createStatement();
			result = statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		freeConnection(conn);
		return result;
	}
	
	public boolean executeQuery(String code) {
		boolean result = false;
		Connection conn = getConnection();
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			Statement statement = conn.createStatement();
			ResultSet query = statement.executeQuery(sql);
			result = query.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		freeConnection(conn);
		return result;
	}
	
	public String executeQueryResult(String code) {
		String result = null;
		Connection conn = getConnection();
		String sql = "SELECT * FROM WebinRobot WHERE _code='" + code + "'";
		try {
			Statement statement = conn.createStatement();
			ResultSet query = statement.executeQuery(sql);
			if (query.first()){
				int weight = query.getInt(3);
				result = query.getString(3 + weight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		freeConnection(conn);
		return result;
	}
}

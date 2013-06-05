package com.webin.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOperate {
	private static final String ID = "_id";
	private static final String CODE = "_code";
	private static final String WEIGHT = "_weight";
	private static final String REPLY = "_reply0";
	private static final String TABLE = "WebinRobot";
	private static final DbManager DBMG = DbManager.getDefault();
	
	private static Connection getConnection(){
		return DBMG.getConnection();
	}
	
	private static void freeConnection(Connection conn){
		DBMG.freeConnection(conn);
	}
	
	public static int executeUpdate(String code, String replay) {
		int result = -1;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(TABLE);
		sql.append(" SET ");
		sql.append(REPLY);
		sql.append(" = '");
		sql.append(replay);
		sql.append("' WHERE ");
		sql.append(CODE);
		sql.append(" = '");
		sql.append(code);
		sql.append("'");
		try {
			Connection conn = getConnection();
			Statement statement =  conn.createStatement();
			result = statement.executeUpdate(sql.toString());
			freeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean executeInsert(String code, String replay) {
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(TABLE);
		sql.append(" ("+ID+","+CODE+","+WEIGHT+","+REPLY+") ");
		sql.append("VALUES (NULL,'");
		sql.append(code);
		sql.append("',");
		sql.append("1,'");
		sql.append(replay);
		sql.append("')");
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			result = statement.execute(sql.toString());
			freeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static ResultSet executeQuery(String code) {
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(TABLE);
		sql.append(" WHERE ");
		sql.append(CODE);
		sql.append("='");
		sql.append(code);
		sql.append("'");
		try {
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql.toString());
			result = statement.executeQuery();
			freeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		return result;
	}

}

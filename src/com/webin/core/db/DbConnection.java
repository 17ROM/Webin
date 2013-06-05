package com.webin.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sina.sae.util.SaeUserInfo;

public class DbConnection {
	private static DbConnection _inst = null;
	private InitialContext mInitContext = null;
	private DataSource mDataSource = null;
	private Connection mConnection = null;
	
	public Connection getConnection(){
		return mConnection;
	}
	
	private DbConnection(){
		initFromContext();
		if (mConnection == null){
			initFromCode();
		}
	}
	
	public static DbConnection getDefault() {
		if (_inst == null) {
			_inst = new DbConnection();
		}
		return _inst;
	}
	
	private void initFromCode(){
		String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_webin";
		String Username = SaeUserInfo.getAccessKey();
		String Password = SaeUserInfo.getSecretKey();
		String Driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(Driver).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			mConnection = DriverManager.getConnection(URL, Username, Password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initFromContext(){
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
}

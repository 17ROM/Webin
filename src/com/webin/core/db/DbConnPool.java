package com.webin.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sina.sae.util.SaeUserInfo;

class DbConnPool {
	String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_webin";
	String Username = SaeUserInfo.getAccessKey();
	String Password = SaeUserInfo.getSecretKey();
	String Driver = "com.mysql.jdbc.Driver";
	private int mUsed = 0;
	private int maxConn = 3;
	private ArrayList<Connection> mFreeConnect = new ArrayList<Connection>();

	public synchronized Connection getConnection() {
		Connection connect = null;
		if (mFreeConnect.size() > 0) {
			connect = (Connection) mFreeConnect.get(0);
			mFreeConnect.remove(0);
			if (connect == null) {
				connect = getConnection();
			}
		} else {
			connect = newConnection();
		}
		if (maxConn == 0 || maxConn < mUsed) {
			connect = null;
		}
		if (connect != null) {
			mUsed++;
		}
		return connect;
	}

	private Connection newConnection() {
		Connection connect = null;
		try {
			Class.forName(Driver);
			connect = DriverManager.getConnection(URL, Username, Password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public synchronized void freeConnection(Connection con) {
		mFreeConnect.add(con);
		mUsed--;
	}

	public synchronized void release() {
		for (Connection connect : mFreeConnect){
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		mFreeConnect.clear();
	}
}

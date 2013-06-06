package com.webin.core.db;

import java.sql.Connection;

import com.sina.sae.util.SaeUserInfo;

public class DbConnectManager {
	private String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_webin";
	private String driver = "com.mysql.jdbc.Driver";
	private String username = SaeUserInfo.getAccessKey();
	private String password = SaeUserInfo.getSecretKey();
	private int maxConn = 10;
	private DbConnectPool mPool;

	public DbConnectManager() {
		mPool = new DbConnectPool(driver, url, username, password, maxConn);
	}

	public void release() {
		mPool.release();
	}

	public Connection getConnection() {
		return mPool.getConnection();
	}

	public void freeConnection(Connection conn) {
		mPool.freeConnection(conn);
	}

}

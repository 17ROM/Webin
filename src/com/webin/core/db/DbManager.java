package com.webin.core.db;

import java.sql.Connection;

public class DbManager {
	private DbConnPool mPool;
	private static DbManager _inst = null;
	
	private DbManager(){
		mPool = new DbConnPool();
	}

	public static DbManager getDefault() {
		if (_inst == null) {
			_inst = new DbManager();
		}
		return _inst;
	}

	public Connection getConnection() {
		return mPool.getConnection();
	}
	
	public void freeConnection(Connection conn){
		mPool.freeConnection(conn);
	}
}

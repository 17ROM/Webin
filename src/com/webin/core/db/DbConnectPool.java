package com.webin.core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.dbcp.BasicDataSource;

public class DbConnectPool {
	private int maxConn;
	private String driver;
	private String username;
	private String password;
	private String url;

	private Vector<Connection> freeConns = new Vector<Connection>();
	
	public DbConnectPool(String driver, String url, String username, String password,int maxConn){
		this.url = url;
		this.driver = driver;
		this.username = username;
		this.password = password;
		this.maxConn = maxConn;
	}

	public synchronized void freeConnection(Connection conn) {
		try {
			if (!conn.isClosed()){
				freeConns.addElement(conn);
			}else{
				freeConns.remove(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		notifyAll();
	}

	public synchronized Connection getConnection(long timeout) {
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null) {
			try {
				wait(timeout);
			} catch (InterruptedException e) {
			}
			if ((new Date().getTime() - startTime) >= timeout) {
				return null;
			}
		}
		return con;
	}

	public synchronized Connection getConnection() {
		Connection con = null;
		if (freeConns.size() > 0) {
			con = (Connection) freeConns.firstElement();
			freeConns.removeElementAt(0);
			try {
				if (con.isClosed()) {
					con = getConnection();
				}
			} catch (SQLException e) {
				con = getConnection();
			}
			if (freeConns.size() > maxConn) {
				releaseOne();
			}
		}

		else if ((maxConn == 0) || (freeConns.size() < maxConn)) {
			con = openNewConnect();
		}
		return con;
	}

	private void releaseOne() {
		if (freeConns.firstElement() != null) {
			Connection con = (Connection) freeConns.firstElement();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection openNewConnect() {
		Connection connect = null;
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driver);
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
		return connect;
	}

	public void release() {
		Enumeration<Connection> allConnections = freeConns.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		freeConns.removeAllElements();
	}
}

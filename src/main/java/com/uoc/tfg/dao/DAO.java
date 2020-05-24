package com.uoc.tfg.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DAO implements AutoCloseable{

	private final Connection conn;
	
	public DAO() throws Exception{
		Properties props = new Properties();
		InputStream is = DAO.class.getClassLoader().getResourceAsStream("app.properties");
		props.load(is);
		is.close();
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
	}
	
	public Connection getConn() {
		return this.conn;
	}

	public void close() throws Exception {

		this.conn.close();
		
	}
}

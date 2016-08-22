package com.mario.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.Server;

public class DBConnection {
	Server hsqlServer = null;
	Connection connection = null;
	ResultSet rs = null;
	
	public DBConnection() {
		
	}
	
	public Connection getConnection() {
		if(connection==null){
			hsqlServer = new Server();
			hsqlServer.setLogWriter(null);
			hsqlServer.setSilent(true);
			hsqlServer.setDatabaseName(0, "mario");
			hsqlServer.setDatabasePath(0, "file:mariodb");
			
			hsqlServer.start();
			// making a connection
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mario", "sa", "");
				connection.prepareStatement("create table if not exists users (id integer, name varchar(20) not null,gems integer,health integer,map BLOB);").execute();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
		}
		return connection;
	}
	
	public void closeConnection(){
		hsqlServer.stop();
		hsqlServer = null;
	}	
}

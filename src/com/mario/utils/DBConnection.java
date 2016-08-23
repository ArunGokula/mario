package com.mario.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
				DatabaseMetaData dbm = connection.getMetaData();

				ResultSet tables = dbm.getTables(null, null, "riddles", null);
				if (!tables.next()) {
					String riddlesql = "create table riddles (difficulty integer,question varchar(500),answer varchar(100));";
					connection.createStatement().execute(riddlesql);
					connection.commit();
					runSqlFile();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	private void runSqlFile() throws FileNotFoundException, IOException, SQLException {
		try (BufferedReader br = new BufferedReader(new FileReader("riddles.sql"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	connection.createStatement().execute(line);
		    	//System.out.println(line);
		    }
		    connection.commit();
		}
	}

	public void closeConnection(){
		hsqlServer.stop();
		hsqlServer.shutdown();
		hsqlServer = null;
	}	
}

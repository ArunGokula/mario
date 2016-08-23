package com.mario.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.Server;

public class HSQLDBConnection {
	static Server hsqlServer = null;
	static Connection connection = null;
	ResultSet rs = null;

	private HSQLDBConnection() {

	}

	public static Connection getConnection() {
		if (connection == null) {
			startDatabase();
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mario", "sa", "");
				bootStrapData();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException impException) {
				System.err.println("If you are developer, Place riddles.sql file in classpath");
				impException.printStackTrace();
			}
		}
		return connection;
	}

	private static void startDatabase() {
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "mario");
		hsqlServer.setDatabasePath(0, "file:data/mariodb");
		hsqlServer.start();
	}

	private static void bootStrapData() throws FileNotFoundException, IOException, SQLException {
		createTables();
		runSqlFile();
	}

	private static void createTables() throws SQLException {
		connection
				.prepareStatement(
						"create table if not exists users (id integer, name varchar(20) not null,level integer,gems integer,health integer,map BLOB);")
				.execute();
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "riddles".toUpperCase(), null);
		if (!tables.next()) {
			String riddlesql = "create table riddles (difficulty integer,question varchar(500),answer varchar(100));";
			connection.createStatement().execute(riddlesql);
		}
		connection.commit();
	}

	private static void runSqlFile() throws FileNotFoundException, IOException, SQLException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(HSQLDBConnection.class.getClassLoader().getResourceAsStream("riddles.sql"), "UTF8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				connection.createStatement().execute(line);
				// To find error line
				// System.out.println(line);
			}
			connection.commit();
		}
	}

	public static void closeConnection() {
		hsqlServer.stop();
		hsqlServer.shutdown();
		hsqlServer = null;
	}
}

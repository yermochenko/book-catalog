package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String JDBC_DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static final String JDBC_URL = "jdbc:postgresql://localhost/book_catalog_db";
	private static final String JDBC_USER_NAME = "root";
	private static final String JDBC_USER_PASSWORD = "root";

	public static void init() throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER_CLASS_NAME);
	}

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER_NAME, JDBC_USER_PASSWORD);
	}
}

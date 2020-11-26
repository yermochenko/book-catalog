package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC - Java DataBase Connectivity
		Class.forName("org.postgresql.Driver");
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/book_catalog_db", "root", "root");
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT id, first_name, last_name FROM author");
		while(r.next()) {
			System.out.println("id = " + r.getLong("id"));
			System.out.println("first name = " + r.getString("first_name"));
			System.out.println("last name = " + r.getString("last_name"));
			System.out.println("-----------------------");
		}
		r.close();
		s.close();
		c.close();
	}
}

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.AuthorDao;
import domain.Author;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// JDBC - Java DataBase Connectivity
		Class.forName("org.postgresql.Driver");
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/book_catalog_db", "root", "root");
		AuthorDao authorDao = new AuthorDao();
		authorDao.setConnection(c);
		List<Author> authors = authorDao.readAll();
		for(Author author : authors) {
			System.out.println(author);
		}
		c.close();
	}
}

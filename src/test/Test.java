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
		/*
		 * Loading JDBC-driver class into JVM memory
		 */
		Class.forName("org.postgresql.Driver");
		/*
		 * Opening connection to database
		 * Parameters of method getConnection():
		 * 1) JDBC-URL where
		 *       "jdbc:" - show that this URL is URL to database connection
		 *       "postgresql:" - show that we connect to PostgreSQL server but not
		 *                       to MySQL or Oracle or some other relational
		 *                       database management system
		 *       "//localhost" - show IP-address or domain name of database server
		 *       "/book_catalog_db" - show name of database on server
		 * 2) Username for access to database
		 * 3) Password for access to database
		 */
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/book_catalog_db", "root", "root");
		/*
		 * Creating of Data Access Object for authors
		 */
		AuthorDao authorDao = new AuthorDao();
		/*
		 * Giving the opened connection to Data Access Object
		 */
		authorDao.setConnection(c);
		/*
		 * Reading information about all authors from database
		 * as list of objects of Java-class
		 */
		List<Author> authors = authorDao.readAll();
		/*
		 * Outputting list of authors to console
		 */
		for(Author author : authors) {
			System.out.println(author);
		}
		System.out.println("-----------------------------------------");
		/*
		 * Reading information about one concrete author with ID=2 from database
		 * as object of Java-class
		 */
		Author author = authorDao.read(2L);
		/*
		 * Outputting author to console
		 */
		System.out.println(author);
		System.out.println("-----------------------------------------");
		/*
		 * Trying to read information about one concrete author
		 * with non existing ID=123 from database as object of Java-class
		 */
		author = authorDao.read(123L);
		/*
		 * Outputting author to console
		 */
		System.out.println(author);
		/*
		 * Closing connection to database
		 */
		c.close();
	}
}

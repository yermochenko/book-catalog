package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Author;

public class AuthorDao {
	/*
	 * In this field we will store some connection to database. But we will not
	 * to open connection to database inside this class
	 */
	private Connection connection;

	/*
	 * This method allow to some outer class give the opened connection to
	 * object of this class
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Long create(Author author) {
		// INSERT
		return null;
	}

	public Author read(Long id) throws SQLException {
		/*
		 * In this variable we will store SQL-query which will be executed
		 * by this method
		 */
		String sql = "SELECT id, first_name, last_name FROM author WHERE id = ?";
		/*
		 * References to objects of PreparedStatement and ResultSet classes.
		 * These references we need to declare before block "try" to have access
		 * to these objects in block "finally" also
		 */
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		/*
		 * "try" block allow to execute some operation with control possible
		 * errors. If in some line of this block we will receive error, JVM will
		 * stop to execute this block and not allow to execute next commands,
		 * which can work incorrectly after error happened
		 */
		try {
			/*
			 * Creating object of PreparedStatement class for given SQL-query.
			 * PreparedStatement allow later to substitute into SQL-query some
			 * user data instead of question marks
			 */
			statement = connection.prepareStatement(sql);
			/*
			 * Substitution of value from variable "id" of type "Long" instead
			 * of first question mark inside SQL-query. PreparedStatement will
			 * format this value according rules understandable for database.
			 * First parameter of method setLong() show serial number of
			 * question mark inside SQL-query (numbering starts from 1)
			 */
			statement.setLong(1, id);
			/*
			 * Execution of SQL-query from PreparedStatement with substituted
			 * data and receiving of result of SELECT-query as object of
			 * ResultSet-class
			 */
			resultSet = statement.executeQuery();
			/*
			 * Declaration of reference to object of class Author. Even if we
			 * do not find author with given ID, we return reference which will
			 * be null-reference that means that we did not find this
			 * information
			 */
			Author author = null;
			/*
			 * Moving to first row of result table (record in table
			 * "author") and check if it exists
			 */
			if(resultSet.next()) {
				/*
				 * If we found author with given ID we create empty object of
				 * class "Author" for copying information from database
				 * into this object
				 */
				author = new Author();
				/*
				 * In next 3 rows we are copying information from each column
				 * of result table and current row of this table into
				 * Java-object. We need to know data type of each column to
				 * choose which method we should call from object of
				 * ResultSet-class (for example getLong(), getDouble(),
				 * getBoolean(), getString() etc.). Parameter of these methods
				 * show serial number of column (numbering starts from 1) or
				 * string with name of this column. Some row is becoming
				 * current after calling method next() of object of
				 * ResultSet-class
				 */
				author.setId(resultSet.getLong("id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
			}
			/*
			 * Returning result object or null-reference if object not found
			 */
			return author;
		/*
		 * Block "finally" will be called in any case even if inside block
		 * "try" we receive error or try to exit from method using
		 * return-operation. It guarantees us that we will try to free all
		 * resources in any case
		 */
		} finally {
			/*
			 * Trying to close object of ResultSet-class. Technically it's
			 * possible to receive error during closing this resource. But we
			 * can't do anything in this situation. That is why we ignore this
			 * error
			 */
			try { resultSet.close(); } catch(SQLException e) {}
			/*
			 * Trying to close object of PreparedStatement-class. Situation
			 * with error is the same as with closing of object of
			 * ResultSet-class. But we need to trying to close this resource
			 * in separate block "try" because of it's possible that we can't
			 * close "resultSet" and can close "statement" but if we will try
			 * to close both of them in one block "try" error while closing
			 * "resultSet" not will allow us to try to close "statement"
			 */
			try { statement.close(); } catch(SQLException e) {}
		}
	}

	public List<Author> readAll() throws SQLException {
		/*
		 * In this variable we will store SQL-query which will be executed
		 * by this method
		 */
		String sql = "SELECT id, first_name, last_name FROM author";
		/*
		 * References to objects of Statement and ResultSet classes. These
		 * references we need to declare before block "try" to have access
		 * to these objects in block "finally" also
		 */
		Statement statement = null;
		ResultSet resultSet = null;
		/*
		 * "try" block allow to execute some operation with control possible
		 * errors. If in some line of this block we will receive error, JVM will
		 * stop to execute this block and not allow to execute next commands,
		 * which can work incorrectly after error happened
		 */
		try {
			/*
			 * Creating object of Statement class. Statement allow to
			 * executes SQL-queries
			 */
			statement = connection.createStatement();
			/*
			 * Execution of the given SQL-query using Statement and receiving
			 * result of SELECT-query as object of ResultSet-class
			 */
			resultSet = statement.executeQuery(sql);
			/*
			 * Creation of empty list of authors. Even if we will not find any
			 * author, we will return empty list but not reference which will
			 * be null-reference
			 */
			List<Author> authors = new ArrayList<>();
			/*
			 * Moving to the next row of result table (record in table "author")
			 * until it exists
			 */
			while(resultSet.next()) {
				/*
				 * Creating empty object of class "Author" for copying
				 * information from database from current record into
				 * this object
				 */
				Author author = new Author();
				/*
				 * In next 3 rows we are copying information from each column
				 * of result table and current row of this table into
				 * Java-object. We need to know data type of each column to
				 * choose which method we should call from object of
				 * ResultSet-class (for example getLong(), getDouble(),
				 * getBoolean(), getString() etc.). Parameter of these methods
				 * show serial number of column (numbering starts from 1) or
				 * string with name of this column. Row is becoming
				 * current after calling method next() of object of
				 * ResultSet-class
				 */
				author.setId(resultSet.getLong("id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				/*
				 * Adding filled object of Author-class into list of authors
				 */
				authors.add(author);
			}
			/*
			 * Returning filled list of authors or empty list if
			 * table "author" is empty
			 */
			return authors;
			/*
			 * Block "finally" will be called in any case even if inside block
			 * "try" we receive error or try to exit from method using
			 * return-operation. It guarantees us that we will try to free all
			 * resources in any case
			 */
		} finally {
			/*
			 * Trying to close object of ResultSet-class. Technically it's
			 * possible to receive error during closing this resource. But we
			 * can't do anything in this situation. That is why we ignore this
			 * error
			 */
			try { resultSet.close(); } catch(SQLException e) {}
			/*
			 * Trying to close object of PreparedStatement-class. Situation
			 * with error is the same as with closing of object of
			 * ResultSet-class. But we need to trying to close this resource
			 * in separate block "try" because of it's possible that we can't
			 * close "resultSet" and can close "statement" but if we will try
			 * to close both of them in one block "try" error while closing
			 * "resultSet" not will allow us to try to close "statement"
			 */
			try { statement.close(); } catch(SQLException e) {}
		}
	}

	public void update(Author author) {
		// UPDATE
	}

	public void delete(Long id) {
		// DELETE
	}
}

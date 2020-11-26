package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Author;

public class AuthorDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Long create(Author author) {
		// INSERT
		return null;
	}

	public Author read(Long id) throws SQLException {
		// SELECT
		return null;
	}

	public List<Author> readAll() throws SQLException {
		String sql = "SELECT id, first_name, last_name FROM author";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			List<Author> authors = new ArrayList<>();
			while(resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getLong("id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				authors.add(author);
			}
			return authors;
		} finally {
			try { resultSet.close(); } catch(SQLException e) {}
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

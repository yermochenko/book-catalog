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
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Long create(Author author) throws SQLException {
		String sql = "INSERT INTO author(first_name, last_name) VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, author.getFirstName());
			statement.setString(2, author.getLastName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getLong(1);
		} finally {
			try { statement.close(); } catch(SQLException e) {}
		}
	}

	public Author read(Long id) throws SQLException {
		String sql = "SELECT id, first_name, last_name FROM author WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Author author = null;
			if(resultSet.next()) {
				author = new Author();
				author.setId(resultSet.getLong("id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
			}
			return author;
		} finally {
			try { resultSet.close(); } catch(SQLException e) {}
			try { statement.close(); } catch(SQLException e) {}
		}
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

	public void update(Author author) throws SQLException {
		String sql = "UPDATE author SET first_name = ?, last_name = ? WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, author.getFirstName());
			statement.setString(2, author.getLastName());
			statement.setLong(3, author.getId());
			statement.executeUpdate();
		} finally {
			try { statement.close(); } catch(SQLException e) {}
		}
	}

	public void delete(Long id) throws SQLException {
		String sql = "DELETE FROM author WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} finally {
			try { statement.close(); } catch(SQLException e) {}
		}
	}
}

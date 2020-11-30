package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoCreator implements AutoCloseable {
	public AuthorDao getAuthorDao() throws SQLException {
		AuthorDao authorDao = new AuthorDao();
		authorDao.setConnection(getConnection());
		return authorDao;
	}

	private Connection connection = null;
	private Connection getConnection() throws SQLException {
		if(connection == null) {
			connection = Connector.connect();
		}
		return connection;
	}

	@Override
	public void close() {
		try { connection.close(); } catch(Exception e) {}
	}
}

package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorDao;
import dao.Connector;
import dao.DaoCreator;
import domain.Author;

@WebServlet("/author/list.html")
public class AuthorListServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		try {
			Connector.init();
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(DaoCreator creator = new DaoCreator()) {
			AuthorDao authorDao = creator.getAuthorDao();
			List<Author> authors = authorDao.readAll();
			req.setAttribute("authors", authors);
			req.getRequestDispatcher("/WEB-INF/author/list.html").forward(req, resp);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}

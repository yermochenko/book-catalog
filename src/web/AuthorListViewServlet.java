package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;

@WebServlet("/WEB-INF/author/list.html")
public class AuthorListViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Author> authors = (List<Author>) req.getAttribute("authors");
		int amount = authors.size();
		PrintWriter pw = resp.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.printf("<link rel=\"stylesheet\" type=\"text/css\" href=\"%s/main.css\">\n", req.getContextPath());
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1 style=\"color: blue;\">Authors</h1>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th>Firstname</th>");
		pw.println("<th>Lastname</th>");
		pw.println("</tr>");
		for(Author author : authors) {
			pw.println("<tr>");
			pw.printf("<td>%s</td>\n", author.getFirstName());
			pw.printf("<td>%s</td>\n", author.getLastName());
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.printf("<p>Total %d authors</p>\n", amount);
		pw.println("</body>");
		pw.println("</html>");
	}
}

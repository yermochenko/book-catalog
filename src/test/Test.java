package test;

import java.sql.SQLException;
import java.util.List;

import dao.AuthorDao;
import dao.Connector;
import dao.DaoCreator;
import domain.Author;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		Connector.init();
		try(DaoCreator creator = new DaoCreator()) {
			AuthorDao authorDao = creator.getAuthorDao();
			List<Author> authors = authorDao.readAll();
			for(Author author : authors) {
				System.out.println(author);
			}
			System.out.println("-----------------------------------------");
			Author newAuthor = new Author();
			newAuthor.setFirstName("Jack");
			newAuthor.setLastName("Cerouak");
			Long id = authorDao.create(newAuthor);
			newAuthor.setId(id);
			System.out.println("Author was added successfull with ID = " + id);
			authors = authorDao.readAll();
			for(Author author : authors) {
				System.out.println(author);
			}
			System.out.println("-----------------------------------------");
			newAuthor.setLastName("Kerouac");
			authorDao.update(newAuthor);
			System.out.println("Author with ID = " + newAuthor.getId() + " was updated successfull");
			authors = authorDao.readAll();
			for(Author author : authors) {
				System.out.println(author);
			}
			System.out.println("-----------------------------------------");
			authorDao.delete(newAuthor.getId());
			System.out.println("Author with ID = " + newAuthor.getId() + " was deleted successfull");
			authors = authorDao.readAll();
			for(Author author : authors) {
				System.out.println(author);
			}
			System.out.println("-----------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

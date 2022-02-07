package in.conceptarchitect.bookmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.conceptarchitect.jdbc.Connector;
import in.conceptarchitect.jdbc.DatabaseException;
import in.conceptarchitect.jdbc.JdbcManager;

public class JdbcBookManager {

	JdbcManager jdbc;

	

	public JdbcBookManager(JdbcManager jdbc) {
		super();
		this.jdbc = jdbc;
	}

	public List<Book> getAllBooks() {

		return jdbc.execute( statement ->{
			
			//var rs = statement.executeQuery("select * from books"); //mysql
			var rs = statement.executeQuery("select * from public.\"Books\";"); //postgres
			ArrayList<Book> books = new ArrayList<>();

			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String cover = rs.getString("cover");
				double price = rs.getDouble("price");
				double rating = rs.getDouble("rating");

				var book = new Book();
				book.setIsbn(isbn);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPrice(price);
				book.setRating(rating);
				book.setCover(cover);

				books.add(book);
			}

			return books;

			
			
		});
		
			

	}

	public Book getBookByIsbn(String isbn) {


		return jdbc.execute(statement ->{
			var rs = statement.executeQuery(String.format("select * from books where isbn='%s'", isbn));

			if (rs.next()) {
				String _isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String cover = rs.getString("cover");
				double price = rs.getDouble("price");
				double rating = rs.getDouble("rating");

				var book = new Book();
				book.setIsbn(_isbn);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPrice(price);
				book.setRating(rating);
				book.setCover(cover);

				return book;
			} else {
				// return null;
				throw new BookNotFoundException(isbn, "No Such Book");
			}		
			
		});
	}
	
	
	public int addBook(Book book) {
		
		
		return jdbc.execute( statement ->{
			
			var qry=String.format("insert into books(isbn,title,author,cover,price,rating) "+
					  "values('%s','%s','%s','%s','%f','%f') ", 
					  book.getIsbn(),
					  book.getTitle(),
					  book.getAuthor(),
					  book.getCover(),
					  book.getPrice(),
					  book.getRating()
		
			);

			return statement.executeUpdate(qry); 			
			
		});
	}


	public int deleteBook(String isbn) {

		return jdbc.execute(statement->{
			
			var qry=String.format("delete from  books where isbn='%s' ", isbn); 
			
			var rows= statement.executeUpdate(qry); //total rows affected. ideal value should be 1
			if(rows==0)
				throw new BookNotFoundException(isbn, "Book not found with isbn "+isbn);
			
			return rows;
			
		});
		
		
	}

	
	
	
}

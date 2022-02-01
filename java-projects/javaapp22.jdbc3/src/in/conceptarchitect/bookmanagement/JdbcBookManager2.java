package in.conceptarchitect.bookmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.conceptarchitect.jdbc.Connector;
import in.conceptarchitect.jdbc.DatabaseException;
import in.conceptarchitect.jdbc.JdbcManager;

public class JdbcBookManager2 {

	JdbcManager jdbc;

	

	public JdbcBookManager2(JdbcManager jdbc) {
		super();
		this.jdbc = jdbc;
	}
	
	private Book rsToBook(ResultSet rs) throws SQLException {
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
		
		return book;

	}

	public List<Book> getAllBooks() {
		
		return jdbc.getAll("select * from books", this::rsToBook);

	}

	public Book getBookByIsbn(String isbn) {

		var qry =String.format("select * from books where isbn='%s'", isbn);

		var book= jdbc.getOne(qry, this::rsToBook);
		
		if(book==null)
			return book;
		else {
			throw new BookNotFoundException(isbn, "No Such Book");
		}		
			

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

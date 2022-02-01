package in.conceptarchitect.bookmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookManager {
	
	Connector connector;
	
	public JdbcBookManager(Connector connector) {
		super();
		this.connector = connector;
	}

	public List<Book> getAllBooks(){
	
		
		Connection connection=null;
		
		try {
			connection= connector.open();
			var statement= connection.createStatement();
			var rs= statement.executeQuery("select * from books");
			ArrayList<Book> books=new ArrayList<>();
			
			while(rs.next()) {
				String isbn=rs.getString("isbn");
				String title=rs.getString("title");
				String author=rs.getString("author");
				String cover=rs.getString("cover");
				double price=rs.getDouble("price");
				double rating=rs.getDouble("rating");
				
				var book=new Book();
				book.setIsbn(isbn);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPrice(price);
				book.setRating(rating);
				book.setCover(cover);
				
				books.add(book);
			}
			
			return books;
			
		}catch(SQLException ex) {
			
			throw new DatabaseException(ex.getMessage(),ex);
			
		}finally {
			try{
				connection.close();
			}catch(Exception ex) {
				System.out.println("FATAL ERROR:");
				ex.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	

}

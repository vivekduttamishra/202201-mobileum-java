package javaapp20.jdbc01;

import java.sql.DriverManager;
import java.sql.SQLException;

import in.conceptarchitect.bookmanagement.Book;
import in.conceptarchitect.bookmanagement.BookNotFoundException;
import in.conceptarchitect.bookmanagement.Connector;
import in.conceptarchitect.bookmanagement.JdbcBookManager;
import in.conceptarchitect.utils.Input;

public class App {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//Get a connection from the DriverManager
		
		String connectionUrl="jdbc:mysql://localhost:3306/booksdb";
		String userName="vivek";
		String password="training";
		
		//step #1 Get Connection
		Connector connector= ()-> DriverManager.getConnection(connectionUrl, userName, password);
		Input input=new Input();
		
		JdbcBookManager manager= new JdbcBookManager(connector);
		
		
		while(true) {
			
			
			int choice=input.readInt("1. Get All Books 2. Get Book By id 3. Add Book 4. Delete Book 0.Exit?");
			
			switch(choice) {
			
			case 1:
				doGetAllBooks(manager);
				break;
			case 2:
				doGetBookByIsbn(manager);
				break;
				
			case 3:
				doAddBook(manager);
				break;
				
			case 4:
				doDeleteBook(manager);
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid choice. Retry");
			
			}
			
			
			
		}
	}

	private static void doDeleteBook(JdbcBookManager manager) {
		// TODO Auto-generated method stub
		try {
			Input input=new Input();
			var isbn=input.readString("ISBN?");
			manager.deleteBook(isbn);
			System.out.println("book is deleted");
			
		}catch(BookNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	private static void doAddBook(JdbcBookManager manager) {
		// TODO Auto-generated method stub
		Input input=new Input();
		
		Book book=new Book();
		
		book.setIsbn(input.readString("ISBN ?" ));
		book.setTitle(input.readString("TITLE? "));
		book.setAuthor(input.readString("Author? "));
		book.setPrice(input.readInt("Price?"));
		book.setRating(input.readInt("Rating? "));
		book.setCover(input.readString("Cover?"));
		
		
		manager.addBook(book);
		
		
	}

	private static void doGetBookByIsbn(JdbcBookManager manager) {
		// TODO Auto-generated method stub
		Input input=new Input();
		var isbn=input.readString("isbn?");
		try {
			var book=manager.getBookByIsbn(isbn);
			System.out.println(book);
		}catch(BookNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	private static void doGetAllBooks(JdbcBookManager manager) {
		// TODO Auto-generated method stub
		
		for(var book : manager.getAllBooks()) {
			System.out.println(book);
		}
		
	}

}

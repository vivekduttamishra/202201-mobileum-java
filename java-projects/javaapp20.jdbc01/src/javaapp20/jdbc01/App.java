package javaapp20.jdbc01;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//Get a connection from the DriverManager
		
		String connectionUrl="jdbc:mysql://localhost:3306/booksdb";
		String userName="root";
		String password="root";
		
		//step #1 Get Connection
		var connection=DriverManager.getConnection(connectionUrl, userName, password);
	
		//Step #2 Create an Statement
		var statement= connection.createStatement();
		
		//Step 3 Execute the Statement and get a result set
		var rs = statement.executeQuery("select * from books");
		
		//Step 4. Now step through all the books in the database

		while(rs.next()) { //next() moves the cursor to record as we call
			
			System.out.println(rs.getString(1));
			
		}
		
		
	}

}

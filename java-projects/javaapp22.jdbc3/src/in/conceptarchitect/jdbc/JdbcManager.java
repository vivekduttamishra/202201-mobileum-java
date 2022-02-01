package in.conceptarchitect.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcManager {
	
	Connector connector;

	public JdbcManager(Connector connector) {
		super();
		this.connector = connector;
	}
	
	public <T> T execute(StatementExecutor<T> statementExecutor) {
		
		Connection connection=null;
		
		try {
			connection=connector.open();
			var statement=connection.createStatement();			
			//do something with statement
			
			return statementExecutor.execute(statement);
			
		}catch(SQLException ex) {
			throw new DatabaseException(ex.getMessage(),ex);
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				
				} catch(Exception ex) {
					System.err.println("FATAL ERROR");
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	
	public <T> List<T> getAll(String qry, ResultSetConverter<T> converter){
		
		return null;
	}
	
	public <T> T getOne(String qry, ResultSetConverter<T> converter) {

		return null;
	}
	
	

}

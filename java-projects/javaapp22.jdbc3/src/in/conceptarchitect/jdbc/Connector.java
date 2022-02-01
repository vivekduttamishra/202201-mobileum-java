package in.conceptarchitect.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface Connector {
	
	Connection open() throws SQLException;

}

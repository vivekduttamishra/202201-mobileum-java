package in.conceptarchitect.bookmanagement;

import java.sql.Connection;

@FunctionalInterface
public interface Connector {
	
	Connection open();

}

package in.conceptarchitect.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementExecutor<T> {

	T execute(Statement statement) throws SQLException;

}

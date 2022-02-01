package in.conceptarchitect.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

//converts a Resultset into some object
public interface ResultSetConverter<T> {

	T convert(ResultSet rs) throws SQLException;
}

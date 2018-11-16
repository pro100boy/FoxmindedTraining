package task_3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TResultHandler<T> {
    T handle (ResultSet resultSet) throws SQLException;
}
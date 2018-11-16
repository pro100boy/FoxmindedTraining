package task_3.dao;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Log
public class TExecutor {
    public <T> T execQuery(Connection connection, String query, TResultHandler<T> handler) throws SQLException {
        return this.execQuery(connection, query, null, handler);
    }

    public <T> T execQuery(Connection connection, String query, List<Object> values, TResultHandler<T> handler) throws SQLException {
        log.info(String.format("query: '%s', params: %s", query, isNull(values) ? "none" : values));

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            if (nonNull(values)) {
                for (int i = 0; i < values.size(); i++) {
                    statement.setObject(i + 1, values.get(i));
                }
            }

            try (ResultSet result = statement.executeQuery()) {
                return handler.handle(result);
            }
        }
    }

    public int execUpdate(Connection connection, String query, Object... values) throws SQLException {
        log.info(String.format("query: '%s', params: %s", query, Arrays.asList(values)));

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            return statement.executeUpdate(query);
        }
    }
}

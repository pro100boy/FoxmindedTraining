package task_3.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface IGateway<T> {

    T findOne(Integer id) throws IOException, SQLException;

    Collection<T> findAll() throws SQLException;

    Collection<T> findByCondition(String query);

    void saveOne(T item);

    void saveAll(Collection<T> collection);
}

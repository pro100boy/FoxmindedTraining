package task_3.dao;

import lombok.extern.java.Log;
import task_3.model.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Log
public class ServiceGateway implements IGateway<Service> {
    private static final String QUERY = "" +
            "SELECT name,price,duration FROM services WHERE id = ?";

    private static final String SELECT_ALL = "" +
            "SELECT id,name,price,duration FROM services";

    private final TExecutor tExecutor;
    private final Connection conn;

    public ServiceGateway(TExecutor tExecutor, Connection conn) {
        this.tExecutor = tExecutor;
        this.conn = conn;
    }

    @Override
    public Service findOne(Integer id) throws SQLException {
        return tExecutor.execQuery(
                conn,
                QUERY,
                Collections.singletonList(id), rs -> {
                    rs.next();

                    return Service.builder()
                            .id(id)
                            .name(rs.getString("name"))
                            .duration(rs.getInt("duration"))
                            .price(rs.getLong("price"))
                            .build();
                });
    }

    @Override
    public Collection<Service> findAll() throws SQLException {
        return tExecutor.execQuery(
                conn,
                SELECT_ALL,
                rs -> {
                    List<Service> list = new ArrayList<>();

                    while (rs.next()) {

                        list.add(Service.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .duration(rs.getInt("duration"))
                                .price(rs.getLong("price"))
                                .build());
                    }

                    return list;
                });
    }

    @Override
    public void saveOne(Service item) {

    }

    @Override
    public void saveAll(Collection<Service> collection) {

    }

    @Override
    public Collection<Service> findByCondition(String query) {
        return null;
    }
}

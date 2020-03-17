package net.opsource.app.dao;

import net.opsource.app.db.JdbcConnectionManager;
import net.opsource.app.model.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao {
    private JdbcConnectionManager jdbcConnectionManager;

    protected AbstractDao() {
        jdbcConnectionManager = new JdbcConnectionManager();
    }

    protected Connection getConnection() {
        return jdbcConnectionManager.getConnection();
    }

    protected List<Server> toServerList(final ResultSet resultSet) throws SQLException {
        final List<Server> servers = new ArrayList<>();
        while (resultSet.next()) {
            final Server.ServerBuilder serverBuilder = Server.builder()
                    .withId(resultSet.getLong(1))
                    .withName(resultSet.getString(2));
            servers.add(serverBuilder.build());
        }
        return servers;
    }
}

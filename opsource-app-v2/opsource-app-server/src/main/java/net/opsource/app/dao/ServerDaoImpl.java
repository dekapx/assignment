package net.opsource.app.dao;

import net.opsource.app.model.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerDaoImpl extends AbstractDao implements ServerDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerDaoImpl.class);

    public ServerDaoImpl() {
    }

    @Override
    public void add(final Server server) {
        final String SQL = "INSERT INTO SERVERS (NAME) VALUES(?);";
        final Connection connection = getConnection();
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(SQL)) {
            preparedStatement.setString(1, server.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing SQL for adding new server", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Exception while closing database connection...", e);
            }
        }
    }

    @Override
    public List<Server> getServers() {
        final String SQL = "SELECT * FROM SERVERS";

        final List<Server> servers = new ArrayList<>();
        final Connection connection = getConnection();
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(SQL)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                servers.addAll(toServerList(resultSet));
            } catch (SQLException e) {
                LOGGER.error("Exception while fetching ResultSet for fetching servers.", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing SQL for fetching servers", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Exception while closing database connection...", e);
            }
        }
        return servers;
    }
}

package net.opsource.simpleapp.dao.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.opsource.simpleapp.dao.AbstractJdbcDao;
import net.opsource.simpleapp.dao.ServerMonitoringDao;
import net.opsource.simpleapp.model.Server;

public class ServerMonitoringDaoImpl extends AbstractJdbcDao implements ServerMonitoringDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerMonitoringDaoImpl.class);

	private static final String INSERT_SQL = "INSERT INTO SERVER (ID, NAME) VALUES (?, ?)";
	private static final String DELETE_SQL = "DELETE FROM SERVER WHERE ID=?";
	private static final String UPDATE_SQL = "UPDATE SERVER SET NAME=? WHERE ID=?";
	private static final String GET_BY_ID_SQL = "SELECT * FROM SERVER WHERE ID=?";
	private static final String GET_COUNT_SQL = "SELECT COUNT(*) AS TOTAL_COUNT FROM SERVER";
	private static final String GET_ALL_SQL = "SELECT * FROM SERVER ORDER BY NAME";

	private static final int NO_RECORD_FOUND = -1;

	private PreparedStatement preparedStatement = null;

	@Override
	public int save(final Server server) {
		int statusCode = STATUS_SUCCESS;
		try {
			preparedStatement = getConnection().prepareStatement(INSERT_SQL);
			preparedStatement.setLong(1, server.getId());
			preparedStatement.setString(2, server.getName());
			statusCode = preparedStatement.executeUpdate();
			if (statusCode == STATUS_ERROR) {
				throw new IllegalStateException(
						"Unable to delete object from SERVER table with ID: " + server.getId() + " and NAME: " + server.getName());
			}
			return statusCode;
		} catch (Exception e) {
			LOGGER.error("Unable to persists the Server object for ID {} and NAME {}.", server.getId(), server.getName(), e);
			throw new ServerOperationsException(
					"Unable to save object from SERVER table with ID: " + server.getId() + " and NAME: " + server.getName(), e);
		}

	}

	@Override
	public int delete(final int serverId) {
		int statusCode = STATUS_SUCCESS;
		try {
			preparedStatement = getConnection().prepareStatement(DELETE_SQL);
			preparedStatement.setLong(1, serverId);
			statusCode = preparedStatement.executeUpdate();
			if (statusCode == STATUS_ERROR) {
				throw new IllegalStateException("Unable to delete object from SERVER table with ID: " + serverId);
			}
			return statusCode;
		} catch (Exception e) {
			LOGGER.error("Unable to delete object from SERVER table for ID: {}.", serverId, e);
			throw new ServerOperationsException(e.getMessage(), e);
		}

	}

	@Override
	public int update(final Server server) {
		int statusCode = STATUS_SUCCESS;
		try {
			preparedStatement = getConnection().prepareStatement(UPDATE_SQL);
			preparedStatement.setString(1, server.getName());
			preparedStatement.setLong(2, server.getId());
			statusCode = preparedStatement.executeUpdate();
			if (statusCode == STATUS_ERROR) {
				throw new IllegalStateException("Unable to update the SERVER table for ID: " + server.getId() + " and NAME: " + server.getName());
			}
			return statusCode;
		} catch (Exception e) {
			LOGGER.error("Unable to update SERVER table for ID: {}.", server.getId(), e);
			throw new ServerOperationsException(
					"Unable to update object from SERVER table with ID: " + server.getId() + " and NAME: " + server.getName(), e);
		}
	}

	@Override
	public Server getServerById(final long id) {
		try {
			preparedStatement = getConnection().prepareStatement(GET_BY_ID_SQL);
			preparedStatement.setLong(1, id);
			final ResultSet resultSet = preparedStatement.executeQuery();
			final Server server = new Server();
			server.setId(resultSet.getLong(1));
			server.setName(resultSet.getString(2));
			return server;
		} catch (Exception e) {
			LOGGER.error("Unable to find the server for ID: {}.", id, e);
			throw new ServerOperationsException("Unable to find the server for ID: " + id, e);
		}

	}

	@Override
	public int getServerCount() {
		int totalServers = NO_RECORD_FOUND;
		try {
			preparedStatement = getConnection().prepareStatement(GET_COUNT_SQL);
			final ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				totalServers = resultSet.getInt(1);
			}
			return totalServers;
		} catch (Exception e) {
			LOGGER.error("Unable to execute the count query.", e);
			throw new ServerOperationsException("Unable to find the server count.", e);
		}
	}

	@Override
	public Collection<Server> getAllServers() {
		final Collection<Server> servers = new HashSet<>();
		try {
			preparedStatement = getConnection().prepareStatement(GET_ALL_SQL);
			final ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				final Server server = new Server();
				server.setId(resultSet.getLong(1));
				server.setName(resultSet.getString(2));
				servers.add(server);
			}
			return servers;
		} catch (Exception e) {
			LOGGER.error("Unable to find the servers .", e);
			throw new ServerOperationsException("Unable to find any servers.", e);
		}
	}

}

package net.opsource.simpleapp.db;

import static net.opsource.simpleapp.db.JdbcConstants.DATABASE_URL;
import static net.opsource.simpleapp.db.JdbcConstants.DRIVER_CLASS;
import static net.opsource.simpleapp.db.JdbcConstants.PASSWORD;
import static net.opsource.simpleapp.db.JdbcConstants.USER_NAME;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcConnectionManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectionManager.class);

	private Properties properties;

	public JdbcConnectionManager() {
		loadProperties();
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			final String driverClass = properties.getProperty(DRIVER_CLASS);
			final String databaseUrl = properties.getProperty(DATABASE_URL);
			final String username = properties.getProperty(USER_NAME);
			final String password = properties.getProperty(PASSWORD);

			Class.forName(driverClass);
			connection = DriverManager.getConnection(databaseUrl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Exception while making a database connection...", e);
			throw new DatabaseException("Unable to connect with the database...", e);
		}

		return connection;
	}

	private void loadProperties() {
		try {
			properties = new Properties();
			properties.load(ClassLoader.getSystemResourceAsStream("config/db-connection.properties"));
		} catch (IOException e) {
			LOGGER.error("Unable to load db connection properties.", e);
		}
	}
}

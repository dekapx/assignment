package net.opsource.app.db;

import net.opsource.app.util.PropertyLoader;
import net.opsource.app.util.PropertyLoaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.opsource.app.db.JdbcConfigParams.*;

public class JdbcConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectionManager.class);

    private static final String DB_CONFIG_PROPERTY_FILE = "config/db-config.properties";

    final PropertyLoader propertyLoader;

    public JdbcConnectionManager() {
        propertyLoader = new PropertyLoaderImpl(DB_CONFIG_PROPERTY_FILE);
    }


    public Connection getConnection() {
        Connection connection = null;
        try {
            final String driverClass = propertyLoader.getProperty(JDBC_DRIVERCLASS.asString());
            final String databaseUrl = propertyLoader.getProperty(JDBC_URL.asString());
            final String username = propertyLoader.getProperty(JDBC_USERNAME.asString());
            final String password = propertyLoader.getProperty(JDBC_PASSWORD.asString());

            Class.forName(driverClass);
            connection = DriverManager.getConnection(databaseUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Exception while trying connect to the database...", e);
            throw new DatabaseException("Unable to connect to the database! Please check the connection properties...", e);
        }

        return connection;
    }
}

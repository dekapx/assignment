package com.dekapx.serverservice.db;

import com.dekapx.serverservice.util.PropertiesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.dekapx.serverservice.dao.JdbcConstants.*;

public class JdbcConnectionProviderImpl implements JdbConnectionProvider {
    @Override
    public Connection getConnection() {
        final PropertiesManager propertiesManager = PropertiesManager.INSTANCE;

        try {
            final String driverClass = propertiesManager.getProperty(JDBC_DRIVER_CLASS);
            final String databaseUrl = propertiesManager.getProperty(JDBC_URL);
            final String username = propertiesManager.getProperty(JDBC_USERNAME);
            final String password = propertiesManager.getProperty(JDBC_PASSWORD);

            Class.forName(driverClass);
            return DriverManager.getConnection(databaseUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException("Unable to connect with the database...", e);
        }
    }
}


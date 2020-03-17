package com.dekapx.serverservice.db;

import java.sql.Connection;

public interface JdbConnectionProvider {
    Connection getConnection();
}

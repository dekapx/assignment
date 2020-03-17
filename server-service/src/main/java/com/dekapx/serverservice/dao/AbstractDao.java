package com.dekapx.serverservice.dao;

import com.dekapx.serverservice.db.JdbConnectionProviderFactory;

import java.sql.Connection;

public abstract class AbstractDao {
    protected Connection getConnection() {
        return JdbConnectionProviderFactory.getConnectionProvider().getConnection();
    }
}

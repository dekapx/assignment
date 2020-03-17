package net.opsource.simpleapp.dao;

import java.sql.Connection;

import net.opsource.simpleapp.db.JdbcConnectionManager;

public abstract class AbstractJdbcDao {
	private JdbcConnectionManager jdbcConnectionManager;

	public AbstractJdbcDao() {
		jdbcConnectionManager = new JdbcConnectionManager();
	}

	protected Connection getConnection() {
		return jdbcConnectionManager.getConnection();
	}

}

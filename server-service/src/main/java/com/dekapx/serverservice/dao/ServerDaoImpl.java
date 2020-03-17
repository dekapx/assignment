package com.dekapx.serverservice.dao;

import com.dekapx.serverservice.model.Server;

import java.sql.Connection;

public class ServerDaoImpl extends AbstractDao implements ServerDao {
    @Override
    public Server getById(Long id) {
        final Connection connection = getConnection();
        return new Server(1L, "Microsoft Windows 2018 Server");
    }
}

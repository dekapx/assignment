package com.dekapx.serverservice.dao;

import com.dekapx.serverservice.model.Server;

public interface ServerDao {
    Server getById(Long id);
}
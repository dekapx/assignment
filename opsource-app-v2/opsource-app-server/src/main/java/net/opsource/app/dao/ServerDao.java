package net.opsource.app.dao;

import net.opsource.app.model.Server;

import java.util.List;

public interface ServerDao {
    void add(Server server);
    List<Server> getServers();
}

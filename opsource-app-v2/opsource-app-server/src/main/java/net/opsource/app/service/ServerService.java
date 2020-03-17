package net.opsource.app.service;

import net.opsource.app.model.Server;

import java.util.List;

public interface ServerService {
    void add(Server server);
    List<Server> getServers();
}

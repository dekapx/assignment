package net.opsource.app.service;

import net.opsource.app.dao.ServerDao;
import net.opsource.app.dao.ServerDaoImpl;
import net.opsource.app.model.Server;

import java.util.List;

public class ServerServiceImpl implements ServerService {
    public ServerDao serverDao;

    public ServerServiceImpl() {
        serverDao = new ServerDaoImpl();
    }

    @Override
    public void add(Server server) {
        serverDao.add(server);
    }

    @Override
    public List<Server> getServers() {
        return serverDao.getServers();
    }
}

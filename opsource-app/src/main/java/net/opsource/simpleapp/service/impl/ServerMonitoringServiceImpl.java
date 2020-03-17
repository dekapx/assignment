package net.opsource.simpleapp.service.impl;

import java.util.Collection;

import net.opsource.simpleapp.dao.ServerMonitoringDao;
import net.opsource.simpleapp.dao.impl.ServerMonitoringDaoImpl;
import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.service.ServerMonitoringService;

public class ServerMonitoringServiceImpl implements ServerMonitoringService {
	private ServerMonitoringDao serverMonitoringDao;

	public ServerMonitoringServiceImpl() {
		serverMonitoringDao = new ServerMonitoringDaoImpl();
	}

	@Override
	public int save(final Server server) {
		return serverMonitoringDao.save(server);
	}

	@Override
	public int delete(final int serverId) {
		return serverMonitoringDao.delete(serverId);
	}

	@Override
	public int update(Server server) {
		return serverMonitoringDao.update(server);
	}

	@Override
	public Server getServerById(long id) {
		return serverMonitoringDao.getServerById(id);
	}

	@Override
	public int getServerCount() {
		return serverMonitoringDao.getServerCount();
	}

	@Override
	public Collection<Server> getAllServers() {
		return serverMonitoringDao.getAllServers();
	}
}

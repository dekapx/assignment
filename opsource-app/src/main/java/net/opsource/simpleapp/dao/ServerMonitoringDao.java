package net.opsource.simpleapp.dao;

import java.util.Collection;

import net.opsource.simpleapp.model.Server;

public interface ServerMonitoringDao {
	int save(Server server);

	int update(Server server);

	int delete(int serverId);

	Server getServerById(long id);

	int getServerCount();

	Collection<Server> getAllServers();
}

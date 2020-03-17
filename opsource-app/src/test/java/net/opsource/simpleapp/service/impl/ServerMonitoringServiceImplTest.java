package net.opsource.simpleapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import net.opsource.simpleapp.dao.ServerMonitoringDao;
import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.service.ServerMonitoringService;

@RunWith(MockitoJUnitRunner.class)
public class ServerMonitoringServiceImplTest {
	@Mock
	private Server mockedServer;
	@Mock
	private ServerMonitoringDao mockedServerMonitoringDao;

	private ServerMonitoringService objUnderTest;

	@Before
	public void setUp() throws Exception {
		objUnderTest = new ServerMonitoringServiceImpl();
		Whitebox.setInternalState(objUnderTest, "serverMonitoringDao", mockedServerMonitoringDao);
	}

	@Test
	public void save_withValidServerModel_returnSuccessStatusCode() {
		when(mockedServerMonitoringDao.save(any(Server.class))).thenReturn(1);
		final int actual = objUnderTest.save(mockedServer);
		assertEquals(1, actual);
	}

	@Test
	public void save_withValidServerModel_returnErrorStatusCode() {
		when(mockedServerMonitoringDao.save(any(Server.class))).thenReturn(0);
		final int actual = objUnderTest.save(mockedServer);
		assertEquals(0, actual);
	}

	@Test
	public void delete_withValidServerId_returnSuccessStatusCode() {
		when(mockedServerMonitoringDao.delete(any(int.class))).thenReturn(1);
		final int actual = objUnderTest.delete(1);
		assertEquals(1, actual);
	}

	@Test
	public void delete_withValidServerId_returnErrorStatusCode() {
		when(mockedServerMonitoringDao.delete(any(int.class))).thenReturn(0);
		final int actual = objUnderTest.delete(1);
		assertEquals(0, actual);
	}

	@Test
	public void update_withValidServerModel_returnSuccessStatusCode() {
		when(mockedServerMonitoringDao.update(any(Server.class))).thenReturn(1);
		final int actual = objUnderTest.update(mockedServer);
		assertEquals(1, actual);
	}

	@Test
	public void update_withValidServerModel_returnErrorStatusCode() {
		when(mockedServerMonitoringDao.update(any(Server.class))).thenReturn(0);
		final int actual = objUnderTest.update(mockedServer);
		assertEquals(0, actual);
	}

	@Test
	public void getServerById_withValidServerId_returnServerObject() {
		when(mockedServerMonitoringDao.getServerById(any(long.class))).thenReturn(mockedServer);
		final Server server = objUnderTest.getServerById(1L);
		assertSame(mockedServer, server);
	}

	@Test
	public void getServerCount_returnPositiveNumbersAsServerCount() {
		when(mockedServerMonitoringDao.getServerCount()).thenReturn(5);
		final int serverCount = objUnderTest.getServerCount();
		assertEquals(5, serverCount);
	}

	@Test
	public void getAllServers_returnCollectionOfServers() {
		when(mockedServerMonitoringDao.getAllServers()).thenReturn(createServers());
		final Collection<Server> servers = objUnderTest.getAllServers();
		assertNotNull(servers);
		assertEquals(1, servers.size());
		assertSame(mockedServer, servers.iterator().next());
	}

	private Collection<Server> createServers() {
		final Collection<Server> servers = new HashSet<>(1);
		servers.add(mockedServer);
		return servers;
	}
}

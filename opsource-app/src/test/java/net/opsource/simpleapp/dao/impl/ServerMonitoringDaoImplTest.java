package net.opsource.simpleapp.dao.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;

import net.opsource.simpleapp.db.JdbcConnectionManager;
import net.opsource.simpleapp.model.Server;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(JdbcConnectionManager.class)
public class ServerMonitoringDaoImplTest {
	@Mock
	private Connection mockedConnection;
	@Mock
	private PreparedStatement mockedPreparedStatement;
	@Mock
	private JdbcConnectionManager mockedJdbcConnectionManager;
	@Mock
	private ResultSet mockedResultSet;

	@InjectMocks
	private ServerMonitoringDaoImpl objectUnderTest;

	@Before
	public void setUp() throws Exception {
		when(mockedJdbcConnectionManager.getConnection()).thenReturn(mockedConnection);
		when(mockedConnection.prepareStatement(any(String.class))).thenReturn(mockedPreparedStatement);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void save_withValidServerObject_throwsSQLException() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenThrow(SQLException.class);
		objectUnderTest.save(server);
	}

	@Test(expected = ServerOperationsException.class)
	public void save_withValidServerObject_throwsIllegalStateException() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_ERROR);
		objectUnderTest.save(server);
		verify(mockedPreparedStatement).executeUpdate();
	}

	@Test
	public void save_withValidServerObject_returnSuccessStatusCode() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_SUCCESS);
		int statusCode = objectUnderTest.save(server);
		verify(mockedPreparedStatement).executeUpdate();
		assertEquals(1, statusCode);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void delete_withValidServerIdObject_throwsServerOperationsException() throws Exception {
		final int serverId = 1;
		when(mockedPreparedStatement.executeUpdate()).thenThrow(SQLException.class);
		objectUnderTest.delete(serverId);
	}

	@Test(expected = ServerOperationsException.class)
	public void delete_withValidServerObject_throwsIllegalStateException() throws Exception {
		final int serverId = 1;
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_ERROR);
		objectUnderTest.delete(serverId);
		verify(mockedPreparedStatement).executeUpdate();
	}

	@Test
	public void delete_withValidServerId_returnSuccessStatusCode() throws Exception {
		final int serverId = 1;
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_SUCCESS);
		int statusCode = objectUnderTest.delete(serverId);
		verify(mockedPreparedStatement).executeUpdate();
		assertEquals(1, statusCode);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void update_withValidServerObject_throwsSQLException() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenThrow(SQLException.class);
		objectUnderTest.update(server);
	}

	@Test(expected = ServerOperationsException.class)
	public void update_withValidServerObject_throwsIllegalStateException() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_ERROR);
		objectUnderTest.update(server);
		verify(mockedPreparedStatement).executeUpdate();
	}

	@Test
	public void update_withValidServerObject_returnSuccessStatusCode() throws Exception {
		final Server server = new Server();
		server.setId(1L);
		server.setName("test server");
		when(mockedPreparedStatement.executeUpdate()).thenReturn(STATUS_SUCCESS);
		final int statusCode = objectUnderTest.update(server);
		verify(mockedPreparedStatement).executeUpdate();
		assertEquals(1, statusCode);
	}

	@Test
	public void getServerById_withValidServerId_returnValidServerObject() throws Exception {
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedResultSet.getLong(1)).thenReturn(1L);
		when(mockedResultSet.getString(2)).thenReturn("TestServer");
		final Server server = objectUnderTest.getServerById(1);
		assertEquals(1, server.getId());
		assertEquals("TestServer", server.getName());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void getServerById_withValidServerId_throwsServerOperationsException() throws Exception {
		when(mockedPreparedStatement.executeQuery()).thenThrow(SQLException.class);
		objectUnderTest.getServerById(1);
	}

	@Test
	public void getServerCount_returnTotalServers() throws Exception {
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedResultSet.next()).thenReturn(true);
		when(mockedResultSet.getInt(1)).thenReturn(10);
		int totalServers = objectUnderTest.getServerCount();
		assertEquals(10, totalServers);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void getServerCount_throwsServerOperationsException() throws Exception {
		when(mockedPreparedStatement.executeQuery()).thenThrow(SQLException.class);
		objectUnderTest.getServerCount();
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServerOperationsException.class)
	public void getAllServers_throwsServerOperationsException() throws Exception {
		when(mockedPreparedStatement.executeQuery()).thenThrow(SQLException.class);
		objectUnderTest.getAllServers();
	}

	@Test
	public void getAllServers_returnCollectionOfServersWithOneElement() throws Exception {
		final AtomicInteger atomicInteger = new AtomicInteger(0);
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedResultSet.next()).thenAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(final InvocationOnMock invocation) throws Throwable {
				final int counter = atomicInteger.getAndIncrement();
				if (counter == 0) {
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			}
		});
		when(mockedResultSet.getLong(1)).thenReturn(1L);
		when(mockedResultSet.getString(2)).thenReturn("TestServer");
		final Collection<Server> servers = objectUnderTest.getAllServers();
		assertEquals(1, servers.size());
		final Server server = servers.iterator().next();
		assertEquals(1, server.getId());
		assertEquals("TestServer", server.getName());
	}

}

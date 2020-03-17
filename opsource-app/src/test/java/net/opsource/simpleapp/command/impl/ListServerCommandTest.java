package net.opsource.simpleapp.command.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_MODELS;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_CODE;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.service.ServerMonitoringService;

@RunWith(MockitoJUnitRunner.class)
public class ListServerCommandTest {
	@Mock
	private Server mockedServer;
	@Mock
	private CommandRequest mockedCommandRequest;
	@Mock
	private CommandResponse mockedCommandResponse;
	@Mock
	private ServerMonitoringService mockedServerMonitoringService;

	@InjectMocks
	private ListServerCommand objectUnderTest;

	@Before
	public void setUp() throws Exception {
		when(mockedServer.getId()).thenReturn(1L);
		when(mockedServer.getName()).thenReturn("TestServer");
		when(mockedServerMonitoringService.getAllServers()).thenReturn(createServers());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void execute_withCommandRequest_returnSuccessStatusWithCommandResponse() {
		final CommandResponse commandResponse = objectUnderTest.execute(mockedCommandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		assertEquals(3, responseAttributes.size());
		assertEquals(STATUS_SUCCESS, responseAttributes.get(STATUS_CODE));
		final Collection<Server> servers = (Collection<Server>) responseAttributes.get(SERVER_MODELS);
		assertEquals(1, servers.size());
		final Server server = servers.iterator().next();
		assertEquals(1L, server.getId());
		assertEquals("TestServer", server.getName());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void execute_withCommandRequest_returnErrorStatusWithCommandResponse() {
		when(mockedServerMonitoringService.getAllServers()).thenThrow(Exception.class);
		final CommandResponse commandResponse = objectUnderTest.execute(mockedCommandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		assertEquals(2, responseAttributes.size());
		assertEquals(STATUS_ERROR, responseAttributes.get(STATUS_CODE));
	}

	private Collection<Server> createServers() {
		final Collection<Server> servers = new HashSet<>(1);
		servers.add(mockedServer);
		return servers;
	}

}

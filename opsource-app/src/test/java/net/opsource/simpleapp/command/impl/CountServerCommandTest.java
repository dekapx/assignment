package net.opsource.simpleapp.command.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_COUNT;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_CODE;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.service.ServerMonitoringService;

@RunWith(MockitoJUnitRunner.class)
public class CountServerCommandTest {
	@Mock
	private CommandRequest mockedCommandRequest;
	@Mock
	private CommandResponse mockedCommandResponse;
	@Mock
	private ServerMonitoringService mockedServerMonitoringService;

	@InjectMocks
	private CountServersCommand objectUnderTest;

	@Test
	public void execute_withCommandRequest_returnSuccessStatusWithCommandResponse() {
		final int TOTAL_SERVERS = 5;
		when(mockedServerMonitoringService.getServerCount()).thenReturn(TOTAL_SERVERS);
		final CommandResponse commandResponse = objectUnderTest.execute(mockedCommandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		assertEquals(2, responseAttributes.size());
		assertEquals(STATUS_SUCCESS, responseAttributes.get(STATUS_CODE));
		assertEquals(TOTAL_SERVERS, responseAttributes.get(SERVER_COUNT));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void execute_withCommandRequest_returnErrorStatusWithCommandResponse() {
		when(mockedServerMonitoringService.getServerCount()).thenThrow(Exception.class);
		final CommandResponse commandResponse = objectUnderTest.execute(mockedCommandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		assertEquals(2, responseAttributes.size());
		assertEquals(STATUS_ERROR, responseAttributes.get(STATUS_CODE));
	}

}

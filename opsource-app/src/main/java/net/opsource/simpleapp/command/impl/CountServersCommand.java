package net.opsource.simpleapp.command.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_COUNT;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_CODE;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.opsource.simpleapp.command.AbstractCommand;
import net.opsource.simpleapp.command.Command;
import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.service.ServerMonitoringService;
import net.opsource.simpleapp.service.impl.ServerMonitoringServiceImpl;

public class CountServersCommand extends AbstractCommand implements Command {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountServersCommand.class);

	private ServerMonitoringService serverMonitoringService;

	public CountServersCommand() {
		serverMonitoringService = new ServerMonitoringServiceImpl();
	}

	@Override
	public CommandResponse execute(final CommandRequest commandRequest) {
		CommandResponse commandResponse = null;
		try {
			final int serverCount = serverMonitoringService.getServerCount();
			final Map<String, Object> attributes = new HashMap<>(2);
			attributes.put(STATUS_CODE, STATUS_SUCCESS);
			attributes.put(SERVER_COUNT, serverCount);
			commandResponse = new CommandResponse(attributes);
		} catch (Exception e) {
			final String statusMessage = "Failed to get the server count";
			commandResponse = buildCommandResponse(STATUS_ERROR, statusMessage);
			LOGGER.error("Failed to execute {}", this.getClass().getSimpleName(), e);
		}

		return commandResponse;
	}

}

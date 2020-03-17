package net.opsource.simpleapp.command.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_ID;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.opsource.simpleapp.command.AbstractCommand;
import net.opsource.simpleapp.command.Command;
import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.service.ServerMonitoringService;
import net.opsource.simpleapp.service.impl.ServerMonitoringServiceImpl;

public class DeleteServerCommand extends AbstractCommand implements Command {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteServerCommand.class);

	private ServerMonitoringService serverMonitoringService;

	public DeleteServerCommand() {
		serverMonitoringService = new ServerMonitoringServiceImpl();
	}

	@Override
	public CommandResponse execute(final CommandRequest commandRequest) {
		CommandResponse commandResponse = null;
		final Map<String, Object> requestAttributes = commandRequest.getCommandRequest();
		final int serverId = (int) requestAttributes.get(SERVER_ID);
		try {
			serverMonitoringService.delete(serverId);
			final String statusMessage = "Successfull deleted the server for ID: " + serverId;
			commandResponse = buildCommandResponse(STATUS_SUCCESS, statusMessage);
		} catch (Exception e) {
			commandResponse = buildCommandResponse(STATUS_ERROR, e.getMessage());
			LOGGER.error("Failed to execute {}", this.getClass().getSimpleName(), e);
		}

		return commandResponse;
	}

}

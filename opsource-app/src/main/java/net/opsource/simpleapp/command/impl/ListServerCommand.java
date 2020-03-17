package net.opsource.simpleapp.command.impl;

import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_ERROR;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_SUCCESS;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.opsource.simpleapp.command.AbstractCommand;
import net.opsource.simpleapp.command.Command;
import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.service.ServerMonitoringService;
import net.opsource.simpleapp.service.impl.ServerMonitoringServiceImpl;

public class ListServerCommand extends AbstractCommand implements Command {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteServerCommand.class);

	private ServerMonitoringService serverMonitoringService;

	public ListServerCommand() {
		serverMonitoringService = new ServerMonitoringServiceImpl();
	}

	@Override
	public CommandResponse execute(final CommandRequest commandRequest) {
		CommandResponse commandResponse = null;
		try {
			final Collection<Server> servers = serverMonitoringService.getAllServers();
			final String statusMessage = "Loaded all servers successfully.";
			commandResponse = buildCommandResponse(STATUS_SUCCESS, statusMessage, servers);
		} catch (Exception e) {
			commandResponse = buildCommandResponse(STATUS_ERROR, e.getMessage());
			LOGGER.error("Failed to execute {}", this.getClass().getSimpleName(), e);
		}

		return commandResponse;
	}

}

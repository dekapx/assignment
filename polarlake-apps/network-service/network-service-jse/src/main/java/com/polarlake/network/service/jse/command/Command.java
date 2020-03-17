package com.polarlake.network.service.jse.command;

public interface Command {
	CommandResponse execute(CommandRequest commandRequest);

}

package net.opsource.simpleapp.command;

public interface Command {
	CommandResponse execute(CommandRequest commandRequest);
}

package com.bloomberg.services.network.command;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException() {
        super("Invalid command type. Command not found for the given command type.");
    }
}

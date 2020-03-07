package com.bloomberg.services.network.command;

import com.bloomberg.services.network.common.CommandType;

public interface CommandFactory {
    Command getCommand(CommandType commandType);
}

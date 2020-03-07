package com.bloomberg.services.network.command;

import com.bloomberg.services.network.common.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class CommandFactoryImpl implements CommandFactory {
    private final List<Command> commands;

    @Autowired
    public CommandFactoryImpl(final List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public Command getCommand(final CommandType commandType) {
        try {
            return commands
                    .stream()
                    .filter(isMatchingCommandType(commandType))
                    .findFirst()
                    .get();
        } catch (Exception e) {
            throw new CommandNotFoundException();
        }
    }

    private Predicate<Command> isMatchingCommandType(final CommandType commandType) {
        return c -> c.getCommandType().equals(commandType);
    }
}

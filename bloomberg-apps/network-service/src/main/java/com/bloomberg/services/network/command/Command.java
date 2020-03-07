package com.bloomberg.services.network.command;

import com.bloomberg.services.network.common.CommandType;

public interface Command<T, R> {
    R execute(T t);

    CommandType getCommandType();
}

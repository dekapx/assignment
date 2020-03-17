package net.opsource.app.command;

public interface Command<T, R> {
    R execute(T type);
}

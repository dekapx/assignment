package net.opsource.app.command;

public class CommandInvoker<T, R> {
    private Command<T, R> command;

    public CommandInvoker(final Command<T, R> command) {
        this.command = command;
    }

    public R execute(T type) {
        return command.execute(type);
    }
}

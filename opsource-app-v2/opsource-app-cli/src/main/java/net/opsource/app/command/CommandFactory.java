package net.opsource.app.command;

import net.opsource.app.util.ServiceManager;

public class CommandFactory {
    public static <T> T loadCommand(final Class<T> clazz) {
        return loadService(clazz);
    }

    private static <T> T loadService(final Class<T> clazz) {
        final ServiceManager serviceManager = ServiceManager.INSTANCE;
        return serviceManager.getService(clazz);
    }
}

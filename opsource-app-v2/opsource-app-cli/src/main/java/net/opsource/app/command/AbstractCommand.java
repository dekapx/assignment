package net.opsource.app.command;

import net.opsource.app.util.ServiceManager;

public abstract class AbstractCommand {
    protected <T> T loadService(final Class<T> clazz) {
        final ServiceManager serviceManager = ServiceManager.INSTANCE;
        return serviceManager.getService(clazz);
    }
}

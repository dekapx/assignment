package net.opsource.app.util;

import java.util.Iterator;
import java.util.ServiceLoader;

public enum ServiceManager {
    INSTANCE;

    public <T> T getService(final Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Unable to find implementation for null class");
        }

        final ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        final Iterator iterator = serviceLoader.iterator();
        if (iterator == null) {
            throw new ServiceNotFoundException("Was not able to find any implementation for [" + clazz + "]. Please check your packaging!");
        }

        Object impl = iterator.next();
        if (impl == null) {
            throw new ServiceNotFoundException("Was not able to find any implementation for [" + clazz + "]. Please check your packaging!");
        }

        return (T) impl;
    }

}
package com.dekapx.serverservice.db;

import com.dekapx.serverservice.util.ServiceManager;

public class JdbConnectionProviderFactory {
    public static JdbConnectionProvider getConnectionProvider() {
        return ServiceManager.INSTANCE.getService(JdbConnectionProvider.class);
    }
}

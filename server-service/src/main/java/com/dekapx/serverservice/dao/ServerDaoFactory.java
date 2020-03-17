package com.dekapx.serverservice.dao;

import com.dekapx.serverservice.util.ServiceManager;

public class ServerDaoFactory {
    public static ServerDao getServerDao() {
        return ServiceManager.INSTANCE.getService(ServerDao.class);
    }
}

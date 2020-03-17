package com.dekapx.serverservice.model;

public class Server {
    private Long id;
    private String name;

    public Server() {}

    public Server(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

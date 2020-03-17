package net.opsource.app.client;

import net.opsource.app.command.CommandFactory;
import net.opsource.app.command.CommandInvoker;
import net.opsource.app.command.server.*;
import net.opsource.app.model.Server;

import java.util.List;

public class ServerClient {
    public static void main(String[] args) {
        addServer();
        listServers();
    }

    private static void addServer() {
        final Server server = Server.builder()
                .withName("Dummy Server 1")
                .build();

        final AddServerRequest request = AddServerRequest.builder()
                .withServer(server)
                .build();

        final AddServerCommand command = CommandFactory.loadCommand(AddServerCommand.class);
        final CommandInvoker<AddServerRequest, AddServerResponse> invoker = new CommandInvoker<>(command);
        final AddServerResponse response = invoker.execute(request);
    }

    private static void listServers() {
        final ListServerResponse response = new ListServerCommand().execute(null);
        final List<Server> servers = response.getServers();
        servers.stream().map(s -> s.getName()).forEach(System.out::println);
    }
}

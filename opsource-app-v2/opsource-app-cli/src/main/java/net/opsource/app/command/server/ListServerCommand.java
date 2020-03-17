package net.opsource.app.command.server;

import net.opsource.app.command.AbstractCommand;
import net.opsource.app.command.Command;
import net.opsource.app.model.Server;
import net.opsource.app.service.ServerService;

import java.util.List;
import java.util.Optional;

public class ListServerCommand extends AbstractCommand implements Command<Optional<ListServerRequest>, ListServerResponse> {
    @Override
    public ListServerResponse execute(final Optional<ListServerRequest> listServerRequest) {
        final ServerService serverService = loadService(ServerService.class);
        final List<Server> servers = serverService.getServers();
        return buildResponse(servers);
    }

    private ListServerResponse buildResponse(final List<Server> servers) {
        final ListServerResponse response = ListServerResponse.builder()
                .withServers(servers)
                .build();
        return response;
    }

}
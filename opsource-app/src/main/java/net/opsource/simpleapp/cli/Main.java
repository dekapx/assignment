package net.opsource.simpleapp.cli;

import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_COUNT;
import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_ID;
import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_MODEL;
import static net.opsource.simpleapp.util.ApplicationConstants.SERVER_MODELS;
import static net.opsource.simpleapp.util.ApplicationConstants.STATUS_MESSAGE;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import net.opsource.simpleapp.command.Command;
import net.opsource.simpleapp.command.CommandRequest;
import net.opsource.simpleapp.command.CommandResponse;
import net.opsource.simpleapp.command.impl.AddServerCommand;
import net.opsource.simpleapp.command.impl.CountServersCommand;
import net.opsource.simpleapp.command.impl.DeleteServerCommand;
import net.opsource.simpleapp.command.impl.EditServerCommand;
import net.opsource.simpleapp.command.impl.ListServerCommand;
import net.opsource.simpleapp.model.Server;
import net.opsource.simpleapp.util.AsyncDatabasePopulator;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		boolean running = true;

		showHelp();

		final Scanner scanner = new Scanner(System.in);
		while (running) {
			String option = scanner.nextLine();
			if (option.equals("help")) {
				showHelp();
			} else if (option.equals("quit")) {
				running = false;
			} else if (option.startsWith("countServers")) {
				getServerCount();
			} else if (option.startsWith("addServer")) {
				addServer(option);
			} else if (option.startsWith("deleteServer")) {
				deleteServer(option);
			} else if (option.startsWith("editServer")) {
				editServer(option);
			} else if (option.startsWith("listServers")) {
				listServers();
			} else if (option.startsWith("importDataFromXml")) {
				importDataFromXml();
			}
		}
		scanner.close();
	}

	private static void showHelp() {
		System.out.println("help - to display this message");
		System.out.println("countServers - to display the current number of servers present");
		System.out.println("addServer - to display the current number of servers present");
		System.out.println("editServer - to change the name of a server identified by id (takes 2 additional args - the id and the new name)");
		System.out.println("deleteServer - to delete a server (takes one more arg - the id of the server to delete)");
		System.out.println("listServers - to display details of all servers servers");
		System.out.println("importDataFromXml - to populate database from XML");
	}

	private static void addServer(final String option) {
		final String[] tokens = option.split(" ");
		if (tokens.length < 3) {
			System.out.println("Invalid command syntex. Use 'addServer <ID> <Server Name>'");
			return;
		}

		final int serverId = Integer.parseInt(tokens[1]);
		final String serverName = tokens[2];
		final Map<String, Object> requestAttributes = new HashMap<>(1);
		final Server server = new Server();
		server.setId(serverId);
		server.setName(serverName);
		requestAttributes.put(SERVER_MODEL, server);
		final CommandRequest commandRequest = new CommandRequest(requestAttributes);
		final Command command = new AddServerCommand();
		final CommandResponse commandResponse = command.execute(commandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		System.out.println(responseAttributes.get(STATUS_MESSAGE));
	}

	private static void deleteServer(final String option) {
		final String[] tokens = option.split(" ");
		if (tokens.length < 2) {
			System.out.println("Invalid command syntex. Use 'deleteServer <ID>'");
			return;
		}
		final int serverId = Integer.parseInt(tokens[1]);
		final Map<String, Object> requestAttributes = new HashMap<>(1);
		requestAttributes.put(SERVER_ID, serverId);
		final CommandRequest commandRequest = new CommandRequest(requestAttributes);
		final Command command = new DeleteServerCommand();
		final CommandResponse commandResponse = command.execute(commandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		System.out.println(responseAttributes.get(STATUS_MESSAGE));
	}

	private static void editServer(final String option) {
		final String[] tokens = option.split(" ");
		if (tokens.length < 3) {
			System.out.println("Invalid command syntex. Use 'editServer <ID> <New Server Name>'");
			return;
		}

		final int serverId = Integer.parseInt(tokens[1]);
		final String serverName = tokens[2];
		final Map<String, Object> requestAttributes = new HashMap<>(1);
		final Server server = new Server();
		server.setId(serverId);
		server.setName(serverName);
		requestAttributes.put(SERVER_MODEL, server);
		final CommandRequest commandRequest = new CommandRequest(requestAttributes);
		final Command command = new EditServerCommand();
		final CommandResponse commandResponse = command.execute(commandRequest);
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		System.out.println(responseAttributes.get(STATUS_MESSAGE));
	}

	private static void getServerCount() {
		final Command command = new CountServersCommand();
		final CommandResponse commandResponse = command.execute(new CommandRequest(new HashMap<String, Object>()));
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		final int serverCount = (int) responseAttributes.get(SERVER_COUNT);
		System.out.println("Total Servers are: " + serverCount);
	}

	@SuppressWarnings("unchecked")
	private static void listServers() {
		final Command command = new ListServerCommand();
		final CommandResponse commandResponse = command.execute(new CommandRequest(new HashMap<String, Object>()));
		final Map<String, Object> responseAttributes = commandResponse.getCommandResponse();
		final Collection<Server> servers = (Collection<Server>) responseAttributes.get(SERVER_MODELS);
		if (servers.isEmpty()) {
			System.out.println("No servers found...");
			return;
		}
		for (Server server : servers) {
			System.out.println(server.getId() + " " + server.getName());
		}
	}

	private static void importDataFromXml() {
		final AsyncDatabasePopulator populator = AsyncDatabasePopulator.getInstance();
		populator.loadXmlAndpopulateDatabase();
	}

}

package net.opsource.simpleapp.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRequest {
	private Map<String, Object> attributes;

	public CommandRequest(final Map<String, Object> attributes) {
		this.attributes = new HashMap<>();
		this.attributes.putAll(attributes);
	}

	public Map<String, Object> getCommandRequest() {
		return attributes;
	}
}

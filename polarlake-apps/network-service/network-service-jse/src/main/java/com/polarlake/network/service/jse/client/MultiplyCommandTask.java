package com.polarlake.network.service.jse.client;

import java.util.concurrent.Callable;

import com.polarlake.network.service.jse.command.Command;
import com.polarlake.network.service.jse.command.MultiplyCommand;
import com.polarlake.network.service.jse.command.MultiplyCommandRequest;
import com.polarlake.network.service.jse.command.MultiplyCommandResponse;

public class MultiplyCommandTask implements Callable<MultiplyCommandResponse> {
	private MultiplyCommandRequest commandRequest;

	public MultiplyCommandTask(final MultiplyCommandRequest commandRequest) {
		this.commandRequest = commandRequest;
	}

	@Override
	public MultiplyCommandResponse call() throws Exception {
		final Command multiplyCommand = new MultiplyCommand();
		return (MultiplyCommandResponse) multiplyCommand.execute(commandRequest);
	}

}

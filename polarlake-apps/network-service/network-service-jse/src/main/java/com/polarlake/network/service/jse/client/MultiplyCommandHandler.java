package com.polarlake.network.service.jse.client;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarlake.network.service.jse.command.Command;
import com.polarlake.network.service.jse.command.MultiplyCommand;
import com.polarlake.network.service.jse.command.MultiplyCommandRequest;
import com.polarlake.network.service.jse.command.MultiplyCommandResponse;

public class MultiplyCommandHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MultiplyCommandHandler.class);

	private final Random random = new Random();

	private static final int MAX_CONCURRENT_REQUESTS = 500;

	public void invokeMultiplyCommand() {
		LOGGER.debug("executing MultiplyCommand...");
		final MultiplyCommandRequest commandRequest = new MultiplyCommandRequest(3, 5);
		final Command multiplyCommand = new MultiplyCommand();
		final MultiplyCommandResponse commandResponse = (MultiplyCommandResponse) multiplyCommand.execute(commandRequest);
		LOGGER.debug("MultiplyCommand output is {}", commandResponse.getOutput());
	}

	public void invokeConcurrentMultiplyCommands() {

		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		try {
			for (int i = 0; i < MAX_CONCURRENT_REQUESTS; i++) {
				final int firstNum = getPositiveNumber();
				final int secondNum = getPositiveNumber();
				final MultiplyCommandRequest commandRequest = new MultiplyCommandRequest(firstNum, secondNum);
				final MultiplyCommandTask task = new MultiplyCommandTask(commandRequest);
				final Future<MultiplyCommandResponse> future = executorService.submit(task);

				final MultiplyCommandResponse commandResponse = future.get();
				LOGGER.debug("------------- Multiplecation of {} & {} is {} -------------", firstNum, secondNum, commandResponse.getOutput());
			}
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Exception while executing commands concurrently...", e);
		} finally {
			executorService.shutdown();
		}
	}

	private int getPositiveNumber() {
		return random.nextInt(Integer.MAX_VALUE);
	}

}

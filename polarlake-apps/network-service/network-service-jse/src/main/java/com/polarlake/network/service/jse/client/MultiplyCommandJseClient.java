package com.polarlake.network.service.jse.client;

public class MultiplyCommandJseClient {
	public static void main(String[] args) {
		final MultiplyCommandHandler commandInvoker = new MultiplyCommandHandler();
		commandInvoker.invokeMultiplyCommand();
		commandInvoker.invokeConcurrentMultiplyCommands();
	}
}

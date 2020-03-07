package com.bloomberg.services.network.service;

import com.bloomberg.services.network.command.Command;
import com.bloomberg.services.network.command.CommandFactory;
import com.bloomberg.services.network.common.CommandType;
import com.bloomberg.services.network.model.MultiplyRequest;
import com.bloomberg.services.network.model.MultiplyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService {
    @Autowired
    private CommandFactory commandFactory;

    @Override
    public int multiply(final int firstArg, final int secondArg) {
        final MultiplyRequest request = prepareMultiplyRequest(firstArg, secondArg);
        final Command<MultiplyRequest, MultiplyResponse> command = commandFactory.getCommand(CommandType.MULTIPLY);
        final MultiplyResponse response = command.execute(request);
        return response.getResult();
    }

    private MultiplyRequest prepareMultiplyRequest(final int firstArg, final int secondArg) {
        return MultiplyRequest.builder()
                .firstArg(firstArg)
                .secondArg(secondArg)
                .build();
    }
}

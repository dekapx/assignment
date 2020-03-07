package com.bloomberg.services.network.command;

import com.bloomberg.services.network.common.CommandType;
import com.bloomberg.services.network.model.MultiplyRequest;
import com.bloomberg.services.network.model.MultiplyResponse;
import org.springframework.stereotype.Component;

@Component
public class MultiplyCommand implements Command<MultiplyRequest, MultiplyResponse> {
    @Override
    public MultiplyResponse execute(MultiplyRequest multiplyRequest) {
        return null;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MULTIPLY;
    }
}

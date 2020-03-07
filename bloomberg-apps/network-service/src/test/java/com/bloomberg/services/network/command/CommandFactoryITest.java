package com.bloomberg.services.network.command;

import com.bloomberg.services.network.common.CommandType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CommandFactoryITest {
    @Autowired
    private CommandFactory commandFactory;

    @Test
    public void getCommand_withInvalidInput_throwsCommandNotFoundException() {
        Exception exception = assertThrows(CommandNotFoundException.class, () -> {
            commandFactory.getCommand(null);
        });
        String expectedMessage = "Invalid command type. Command not found for the given command type.";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void getCommand_withValidCommandType_returnCommand() {
        Command command = commandFactory.getCommand(CommandType.MULTIPLY);
        assertThat(command).isNotNull().isInstanceOf(MultiplyCommand.class);
        assertThat(command.getCommandType()).isEqualTo(CommandType.MULTIPLY);
    }
}

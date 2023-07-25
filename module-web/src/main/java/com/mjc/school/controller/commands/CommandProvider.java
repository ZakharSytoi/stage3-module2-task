package com.mjc.school.controller.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommandProvider {
    private final Map<CommandType, Command> commands;

    @Autowired
    private CommandProvider(List<Command> list) {
        this.commands = list.stream().collect(Collectors.toMap(Command::getCommandType, Function.identity()));
    }

    public Command getCommand(CommandType command) {
        return commands.get(command);

    }
}

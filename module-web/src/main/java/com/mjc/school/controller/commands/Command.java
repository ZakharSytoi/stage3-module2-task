package com.mjc.school.controller.commands;

import java.util.Scanner;

public interface Command {

    void execute(Scanner scanner);

    CommandType getCommandType();
}

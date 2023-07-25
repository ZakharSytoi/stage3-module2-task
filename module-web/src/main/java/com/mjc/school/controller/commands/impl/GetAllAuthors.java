package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.AuthorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.GET_ALL_AUTHORS;


@Component
public class GetAllAuthors implements Command {

    private final AuthorController authorController;

    @Autowired
    public GetAllAuthors(AuthorController authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_ALL_AUTHORS.getOperation());
        authorController.readAll().forEach(System.out::println);

    }

    @Override
    public CommandType getCommandType() {
        return GET_ALL_AUTHORS;
    }
}

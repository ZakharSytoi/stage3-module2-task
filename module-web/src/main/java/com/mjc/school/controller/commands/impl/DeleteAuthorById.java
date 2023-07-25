package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.AuthorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.Utils.getKeyboardNumber;
import static com.mjc.school.controller.commands.CommandType.DELETE_AUTHOR_BY_ID;
import static com.mjc.school.controller.commands.Constants.AUTHOR_ID;
import static com.mjc.school.controller.commands.Constants.AUTHOR_ID_ENTER;

@Component
public class DeleteAuthorById implements Command {

    private final AuthorController authorController;

    @Autowired
    public DeleteAuthorById(AuthorController authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(DELETE_AUTHOR_BY_ID.getOperation());
        System.out.println(AUTHOR_ID_ENTER);
        System.out.println(authorController.deleteById(getKeyboardNumber(scanner, AUTHOR_ID)));
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_AUTHOR_BY_ID;
    }
}

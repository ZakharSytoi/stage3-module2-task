package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.CREATE_AUTHOR;


@Component
public class CreateAuthor implements Command {

    private final AuthorController authorController;

    @Autowired
    public CreateAuthor(AuthorController authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute(Scanner scanner) {
        AuthorDtoRequest authorDto = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(CREATE_AUTHOR.getOperation());
                System.out.println(Constants.AUTHOR_NAME_ENTER);
                String name = scanner.nextLine();
                authorDto = new AuthorDtoRequest(null, name);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(authorController.create(authorDto));
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_AUTHOR;
    }
}

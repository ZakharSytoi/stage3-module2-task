package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.Utils;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.UPDATE_AUTHOR;


@Component
public class UpdateAuthor implements Command {
    private final AuthorController authorController;

    @Autowired
    public UpdateAuthor(AuthorController authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute(Scanner scanner) {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(UPDATE_AUTHOR.getOperation());
                System.out.println(Constants.AUTHOR_ID_ENTER);
                Long authorId = Utils.getKeyboardNumber(scanner, Constants.AUTHOR_ID);
                System.out.println(Constants.AUTHOR_NAME_ENTER);
                String name = scanner.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, name);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(authorController.update(dtoRequest));

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.UPDATE_AUTHOR;
    }
}

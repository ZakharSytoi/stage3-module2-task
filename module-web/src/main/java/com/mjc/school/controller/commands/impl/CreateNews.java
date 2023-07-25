package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.Utils;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mjc.school.controller.commands.CommandType.CREATE_NEWS;

import java.util.Scanner;

@Component
public class CreateNews implements Command {
    private final NewsController newsController;

    @Autowired
    public CreateNews(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(CREATE_NEWS.getOperation());
                System.out.println(Constants.NEWS_TITLE_ENTER);
                String title = scanner.nextLine();
                System.out.println(Constants.NEWS_CONTENT_ENTER);
                String content = scanner.nextLine();
                System.out.println(Constants.AUTHOR_ID_ENTER);
                Long authorId = Utils.getKeyboardNumber(scanner, Constants.AUTHOR_ID);
                dtoRequest = new NewsDtoRequest(null, title, content, authorId);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(newsController.create(dtoRequest));
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_NEWS;
    }
}

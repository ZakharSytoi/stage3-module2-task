package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.Utils.getKeyboardNumber;
import static com.mjc.school.controller.commands.CommandType.UPDATE_NEWS;
import static com.mjc.school.controller.commands.Constants.*;

@Component
public class UpdateNews implements Command {

    private final NewsController newsController;

    @Autowired
    public UpdateNews(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(UPDATE_NEWS.getOperation());
                System.out.println(NEWS_ID_ENTER);
                Long newsId = getKeyboardNumber(scanner, NEWS_ID);
                System.out.println(NEWS_TITLE_ENTER);
                String title = scanner.nextLine();
                System.out.println(NEWS_CONTENT_ENTER);
                String content = scanner.nextLine();
                System.out.println(AUTHOR_ID_ENTER);
                Long authorId = getKeyboardNumber(scanner, AUTHOR_ID);
                dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(newsController.update(dtoRequest));
    }

    @Override
    public CommandType getCommandType() {
        return UPDATE_NEWS;
    }
}

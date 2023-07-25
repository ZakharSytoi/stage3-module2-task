package com.mjc.school.controller.commands.impl;


import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.Utils.getKeyboardNumber;
import static com.mjc.school.controller.commands.CommandType.DELETE_NEWS_BY_ID;
import static com.mjc.school.controller.commands.Constants.NEWS_ID;
import static com.mjc.school.controller.commands.Constants.NEWS_ID_ENTER;


@Component
public class DeleteNewsById implements Command {

    private final NewsController newsController;

    @Autowired
    public DeleteNewsById(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(DELETE_NEWS_BY_ID.getOperation());
        System.out.println(NEWS_ID_ENTER);
        System.out.println(newsController.deleteById(getKeyboardNumber(scanner, NEWS_ID)));
    }

    @Override
    public CommandType getCommandType() {
        return DELETE_NEWS_BY_ID;
    }
}

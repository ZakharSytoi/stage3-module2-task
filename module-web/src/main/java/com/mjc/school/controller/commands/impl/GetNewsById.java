package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.Utils;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.GET_NEWS_BY_ID;


@Component
public class GetNewsById implements Command {

    private final NewsController newsController;

    @Autowired
    public GetNewsById(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_NEWS_BY_ID.getOperation());
        System.out.println(Constants.NEWS_ID_ENTER);
        System.out.println(newsController.readById(Utils.getKeyboardNumber(scanner, Constants.NEWS_ID)));
    }

    @Override
    public CommandType getCommandType() {
        return GET_NEWS_BY_ID;
    }
}

package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.GET_ALL_NEWS;


@Component
public class GetAllNews implements Command {
    private final NewsController newsController;

    @Autowired
    public GetAllNews(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }

    @Override
    public CommandType getCommandType() {
        return GET_ALL_NEWS;
    }
}

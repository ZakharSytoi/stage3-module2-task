package com.mjc.school.main;

import com.mjc.school.controller.Utils;
import com.mjc.school.controller.commands.CommandProvider;
import com.mjc.school.controller.commands.CommandType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

import static com.mjc.school.controller.commands.Constants.COMMAND_NOT_FOUND;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        CommandProvider commandProvider = context.getBean("commandProvider", CommandProvider.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Utils.printMainMenu();
                String key = scanner.nextLine();
                switch (key) {
                    case "1" -> commandProvider.getCommand(CommandType.GET_ALL_NEWS).execute(scanner);
                    case "2" -> commandProvider.getCommand(CommandType.GET_NEWS_BY_ID).execute(scanner);
                    case "3" -> commandProvider.getCommand(CommandType.CREATE_NEWS).execute(scanner);
                    case "4" -> commandProvider.getCommand(CommandType.UPDATE_NEWS).execute(scanner);
                    case "5" -> commandProvider.getCommand(CommandType.DELETE_NEWS_BY_ID).execute(scanner);
                    case "6" -> commandProvider.getCommand(CommandType.GET_ALL_AUTHORS).execute(scanner);
                    case "7" -> commandProvider.getCommand(CommandType.GET_AUTHOR_BY_ID).execute(scanner);
                    case "8" -> commandProvider.getCommand(CommandType.CREATE_AUTHOR).execute(scanner);
                    case "9" -> commandProvider.getCommand(CommandType.UPDATE_AUTHOR).execute(scanner);
                    case "10" -> commandProvider.getCommand(CommandType.DELETE_AUTHOR_BY_ID).execute(scanner);
                    case "0" -> System.exit(0);
                    default -> System.out.println(COMMAND_NOT_FOUND);
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

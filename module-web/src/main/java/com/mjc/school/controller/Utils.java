package com.mjc.school.controller;

import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;

import java.util.Scanner;

public class Utils {

    public static void printMainMenu() {
        System.out.println(Constants.NUMBER_OPERATION_ENTER);
        for (CommandType command : CommandType.values()) {
            System.out.println(command.getOperationWithNumber());
        }
    }

    public static long getKeyboardNumber(Scanner scanner, String param) {
        long enter;
        try {
            enter = scanner.nextLong();
            scanner.nextLine();
        } catch (Exception ex) {
            scanner.nextLine();
            throw new ValidatorException(
                    String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), param));
        }
        return enter;
    }



}


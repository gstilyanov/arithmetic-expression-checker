package com.stilyanov;

import java.util.Scanner;

import static com.stilyanov.ArithmeticExpressionChecker.isArithmeticExpression;

public class Main {

    public static void main(String[] args) {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (isArithmeticExpression(input)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}

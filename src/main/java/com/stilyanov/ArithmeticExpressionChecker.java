package com.stilyanov;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

public class ArithmeticExpressionChecker {

    private static final String INVALID_ARITHMETIC_EXPRESSION_MESSAGE = "Invalid arithmetic expression";

    public static boolean isArithmeticExpression(String input) {
        try {
            evaluateExpression(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void evaluateExpression(String input) throws IllegalArgumentException {

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            if (currentCharacter == '(') {
                count++;
            } else if (currentCharacter == ')') {
                count--;
            }
            if (count < 0) {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
        }
        if (count != 0) {
            throw new IllegalArgumentException("Unbalanced parentheses");
        }

        try (Context context = Context.create()) {
            Value bindings = context.getBindings("js");
            for (int i = 0; i < input.length(); i++) {
                char currentCharacter = input.charAt(i);
                if (Character.isLetter(currentCharacter)) {
                    bindings.putMember(String.valueOf(currentCharacter), 1);
                }
            }
            context.eval("js", input);
        } catch (PolyglotException e) {
            throw new IllegalArgumentException(INVALID_ARITHMETIC_EXPRESSION_MESSAGE);
        }
    }
}

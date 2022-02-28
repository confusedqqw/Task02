package task02;

import java.util.*;

public class Logic{
    private static String operators = "+-*/";
    private static String delimiters = "() " + operators;
    public static boolean flag = true;

    private static boolean isDelimiter(String token) {
        if (token.length() != 1)
        {
            return false;
        }
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) {
            return true;
        }
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static int priority(String token) {
        if (token.equals("(")) {
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 2;
        }
        if (token.equals("*") || token.equals("/")) {
            return 3;
        }
        return 4;
    }

    public static List<String> parseExpression(String infix) {
        List<String> postfix = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String previous = "";
        String current = "";

        while (tokenizer.hasMoreTokens()) {
            current = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(current)) {
                System.out.println("Incorrect expression.");
                flag = false;
                return postfix;
            }
            if (current.equals(" ")) {
                continue;
            }

            else if (isDelimiter(current)) {
                if (current.equals("(")){
                    stack.push(current);
                }
                else if (current.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("brackets are inconsistent.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                }
                else {
                    if (current.equals("-") && (previous.equals("") || (isDelimiter(previous)  && !previous.equals(")")))) {
                        current = "u-";
                    }
                    else {
                        while (!stack.isEmpty() && (priority(current) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(current);
                }

            }

            else {
                postfix.add(current);
            }
            previous = current;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("brackets are inconsistent.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    public static Double calc(List<String> postfix) {
        Deque<Double> stackRes = new ArrayDeque<Double>();
        for (String x : postfix) {
            if (x.equals("+")){
                stackRes.push(stackRes.pop() + stackRes.pop());
            }
            else if (x.equals("-")) {
                Double b = stackRes.pop(), a = stackRes.pop();
                stackRes.push(a - b);
            }
            else if (x.equals("*")) {
                stackRes.push(stackRes.pop() * stackRes.pop());
            }
            else if (x.equals("/")) {
                Double b = stackRes.pop(), a = stackRes.pop();
                stackRes.push(a / b);
            } else if (x.equals("u-")) {
                stackRes.push(-stackRes.pop());
            }
            else{
                stackRes.push(Double.valueOf(x));
            }
        }
        return stackRes.pop();
    }
}
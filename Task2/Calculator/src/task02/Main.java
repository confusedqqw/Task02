package task02;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        Logic logic = new Logic();
        boolean flag = logic.flag;

        String str = input.stringInput("Enter the expression: ");
        input.inputCheck(str);

        List<String> expression = logic.parseExpression(str);

        if (flag) {
            System.out.println();
            System.out.println(logic.calc(expression));

        }
    }
}



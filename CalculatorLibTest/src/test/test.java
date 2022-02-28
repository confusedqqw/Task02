package test;

import task02.Input;
import task02.Logic;

import java.util.List;

public class test {
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

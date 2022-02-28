package task02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input{
    public String stringInput(String mes){
        System.out.println(mes);
        Scanner sc = new Scanner(System.in);
        String input="";
        input = sc.nextLine();
        return input;
    }

    public void inputCheck (String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            throw new RuntimeException("Incorrect input");
        }
    }
}
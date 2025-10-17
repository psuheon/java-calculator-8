package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Application {

    // 유저가 입력한 문자열을 구분자를 기준으로 하여 숫자들로 나누는 함수
    public ArrayList<String> splitString(String str) {
        ArrayList<String> separated = new ArrayList<String>();
        ArrayList<Character> separator = new ArrayList<>(Arrays.asList(',', ':'));
        int currentIndex = 0;

        if (checkCustomSeparator(str)) {
            separator.add(str.charAt(2));
            currentIndex = 5;
        }

        for(int i = currentIndex; i < str.length(); i++) {
            for(char c : separator) {
                if(str.charAt(i) == c) {
                    separated.add(str.substring(currentIndex, i));
                    currentIndex = i + 1;

                    break;
                }
            }
        }
        separated.add(str.substring(currentIndex));

        return separated;
    }

    // String 형식으로 된 숫자들을 Double 형식으로 바꾸어 더하는 함수
    public Double calculate(ArrayList<String> nList) {
        Double value = 0.0;
        for(int i = 0; i < nList.size(); i++) {
            String temp = nList.get(i);
            value += Double.parseDouble(temp);
        }

        return value;
    }

    public Boolean checkCustomSeparator(String str) {
        if(Character.isDigit(str.charAt(0)))
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    public static void main(String[] args) {
        Application calculator = new Application();

        String userInput;
        Double result;

        userInput = Console.readLine();

        if(userInput == "") {
            result = 0.0;
        }
        else {
            ArrayList<String> numList = calculator.splitString(userInput);
            result = calculator.calculate(numList);
        }
        System.out.println(result);

        Console.close();
    }
}

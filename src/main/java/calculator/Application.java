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
    public double calculate(ArrayList<String> nList) {
        double value = 0.0;
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

    public void checkIllegalInput(String input) {
        int startIndex = 0;
        ArrayList<Character> separator = new ArrayList<>(Arrays.asList(',', ':'));

        if(!Character.isDigit(input.charAt(0))) {
            if (input.startsWith("//")) {
                if(!input.contains("\\n")) // 커스텀 구분자 지정 포맷 오류
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                else {
                    if(input.indexOf("\\n") != 3) // 커스텀 구분자가 하나가 아닌 경우
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                }

                if(input.charAt(2) == '.') // "."을 커스텀 구분자로 지정한 경우
                    throw new IllegalArgumentException("잘못된 입력입니다.");
            }
            else // 숫자가 아닌 문자로 시작하거나 커스텀 구분자 지정 포맷 오류
                throw new IllegalArgumentException("잘못된 입력입니다.");

            startIndex = 5;
            separator.add(input.charAt(2));
        }

        if(!Character.isDigit(input.charAt(input.length() - 1))) // 숫자가 아닌 문자로 문자열이 끝나는 경우
            throw new IllegalArgumentException("잘못된 입력입니다.");

        int floatingPoint = 0;
        for(int i = startIndex; i < input.length(); i++) {
            char current = input.charAt(i);
            if(!Character.isDigit(current)) {
                if(current =='.') {
                    if(floatingPoint == 0) {
                        floatingPoint = 1;
                    }
                    else { //'.' 문자가 floating point가 너무 많은 경우
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }
                }
                else {
                    int isSeparator = 0;
                    for(Character c : separator) {
                        if(current == c) {
                            isSeparator = 1;
                            floatingPoint = 0;
                        }
                    }

                    if(isSeparator == 0) { // 지정된 구분자가 아닌 다른 문자가 사용된 경우
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }
                    else {
                        if(!Character.isDigit(input.charAt(i + 1))) { // 구분자 뒤에 숫자가 아닌 문자가 오는 경우
                            throw new IllegalArgumentException("잘못된 입력입니다.");
                        }
                    }
                }
            }
        }

    }
    public static void main(String[] args) throws IllegalArgumentException {
        Application calculator = new Application();

        String userInput;
        double result;

        userInput = Console.readLine();

        calculator.checkIllegalInput(userInput);

        if(userInput == "") {
            result = 0.0;
        }
        else {
            ArrayList<String> numList = calculator.splitString(userInput);
            result = calculator.calculate(numList);
        }

        if(result % 1 == 0.0) {
            System.out.println("결과 : " + (int)result);
        }
        else {
            System.out.println("결과 : " + result);
        }

        Console.close();
    }
}

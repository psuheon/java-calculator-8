package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class Application {

    // 유저가 입력한 문자열을 구분자를 기준으로 하여 숫자들로 나누는 함수
    public ArrayList<String> splitString(String str, char separator) {
        ArrayList<String> separated = new ArrayList<String>();
        int currentIndex = 0;
        int numCount = 0;

        if(separator == ' ') {
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == ',' || str.charAt(i) == ':') {
                    separated.add(str.substring(currentIndex, i));
                    numCount++;
                    currentIndex = i + 1;
                }
            }
        }
        else {
            currentIndex = 5;
            for(int i = 5; i < str.length(); i++) {
                if(str.charAt(i) == separator) {
                    separated.add(str.substring(currentIndex, i));
                    numCount++;
                    currentIndex = i + 1;
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

    public static void main(String[] args) {
        Application calculator = new Application();

        String userInput;
        Double result;

        userInput = Console.readLine();

        if(userInput == "") {
            result = 0.0;
        }
        else if(Character.isDigit(userInput.charAt(0))) {
            ArrayList<String> numList = calculator.splitString(userInput, ' ');
            result = calculator.calculate(numList);
        }
        else {
            ArrayList<String> numList = calculator.splitString(userInput, userInput.charAt(2));
            result = calculator.calculate(numList);
        }

        Console.close();
    }
}
